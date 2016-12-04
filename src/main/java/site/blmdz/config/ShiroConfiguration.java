package site.blmdz.config;

import java.util.Map;
import java.util.Objects;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import site.blmdz.auth.AuthUtils;
import site.blmdz.realm.JspRealm;

/**
 * shiro 总配置文件
 * 
 * @author yangyz
 * @date 2016年12月2日下午5:22:10
 */
@Slf4j
@Configuration
@Service
public class ShiroConfiguration {

	private static Map<String, String> filterChainDefinitionMap = Maps.newLinkedHashMap();

	// /**
	// * shiro session dao
	// */
	// @Bean
	// public EnterpriseCacheSessionDAO sessionDao(){
	// return new EnterpriseCacheSessionDAO();
	// }
	// /**
	// * shiro session manager
	// */
	// @Bean
	// @ConditionalOnBean(EnterpriseCacheSessionDAO.class)
	// public DefaultWebSessionManager sessionManager(
	// EnterpriseCacheSessionDAO sessionDao){
	// DefaultWebSessionManager s = new DefaultWebSessionManager();
	// s.setSessionDAO(sessionDao);
	// s.setGlobalSessionTimeout(timeout * 1000);
	// s.setSessionIdCookieEnabled(true);
	// return s;
	// }

	/**
	 * 缓存
	 */
	@Bean
	public EhCacheManager ehCacheManager(
			@Value("${jsp.shiro.ehcache.enable}") Boolean enable,
			@Value("${jsp.shiro.ehcache.timeout}") Long timeout
			) {
		
		if (!enable)
			return null;
		EhCacheManager ehCacheManager = new EhCacheManager();

		CacheConfiguration config = new CacheConfiguration();
		config.setMaxEntriesLocalHeap(10000);
		config.setEternal(false);
		config.setTimeToIdleSeconds(timeout);
		config.setTimeToLiveSeconds(timeout);

		net.sf.ehcache.config.Configuration c = new net.sf.ehcache.config.Configuration();
		c.setDefaultCacheConfiguration(config);

		ehCacheManager.setCacheManager(new CacheManager(c));

		return ehCacheManager;
	}

	/**
	 * 生命周期处理器 用于在实现了Initializable接口的Shiro bean初始化时调用Initializable接口回调，
	 * 在实现了Destroyable接口的Shiro bean销毁时调用 Destroyable接口回调。
	 */
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * Shiro默认会使用Servlet容器的Session,可通过sessionMode属性来指定使用Shiro原生Session 即
	 * <property name="sessionMode" value="native"/>,详细说明见官方文档
	 * 这里主要是设置自定义的单Realm应用,若有多个Realm,可使用'realms'属性代替
	 */
	@Bean
	@ConditionalOnBean({ EhCacheManager.class, JspRealm.class })
	public DefaultWebSecurityManager defaultWebSecurityManager(
			JspRealm jspRealm,
			EhCacheManager ehCacheManager
			) {
		
		DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
		dwsm.setRealm(jspRealm);
		dwsm.setCacheManager(ehCacheManager);
		// dwsm.setSessionManager(sessionManager);
		
		return dwsm;
	}

	/**
	 * realm
	 */
	@Bean
	public JspRealm jspRealm() {
		
		return new JspRealm();
	}

	/**
	 * Web应用中, Shiro可控制的Web请求必须经过Shiro主过滤器的拦截, Shiro对基于Spring的Web应用提供了完美的支持
	 */
	@Bean
	@ConditionalOnBean(DefaultWebSecurityManager.class)
	public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager,
			@Value("${jsp.shiro.login}") String login,
			@Value("${jsp.shiro.success}") String success,
			@Value("${jsp.shiro.unauth}") String unauth
			) {

		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
		shiroFilterFactoryBean.setLoginUrl(login);
		shiroFilterFactoryBean.setSuccessUrl(success);
		shiroFilterFactoryBean.setUnauthorizedUrl(unauth);

		// requests
		AuthUtils.readAuths().keySet().forEach(auth -> {
			AuthUtils.readAuths().get(auth).getRequests().forEach(url -> {
				String k = filterChainDefinitionMap.get(url);
				if (!Objects.isNull(k))
					k += "," + auth;
				else
					k = auth;
				filterChainDefinitionMap.put(url, k);
			});
		});

		// roles perms
		AuthUtils.readRoles().forEach(roles -> {
			AuthUtils.readAuthsRolesTreeMap(roles).keySet().forEach(auths -> {
				String k = filterChainDefinitionMap
						.get(AuthUtils.readAuthsRolesTreeMap(roles).get(auths).getResources());
				if (!Objects.isNull(k)) {
					if (!k.contains("perms[" + auths + "]"))
						k += "," + "perms[" + auths + "]";
				} else {
					k = "authc," + "perms[" + auths + "]";
				}
				filterChainDefinitionMap.put(AuthUtils.readAuthsRolesTreeMap(roles).get(auths).getResources(), k);
			});
		});

		// roles requests
		AuthUtils.readRolesAuths().keySet().forEach(auth -> {
			AuthUtils.readRolesAuths().get(auth).getRequests().forEach(url -> {
				String k = filterChainDefinitionMap.get(url);
				if (!Objects.isNull(k))
					k += "," + "roles[" + auth + "]";
				else
					k = "authc," + "roles[" + auth + "]";
				filterChainDefinitionMap.put(url, k);
			});
		});

		ObjectMapper mapper = new ObjectMapper();
		try {
			log.debug(mapper.writeValueAsString(filterChainDefinitionMap));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}
}
