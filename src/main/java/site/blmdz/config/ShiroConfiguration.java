package site.blmdz.config;

import java.util.Map;
import java.util.Objects;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
 * @author yangyz
 * @date 2016年12月2日下午5:22:10
 */
@Slf4j
@Configuration
public class ShiroConfiguration {
	
	private static Map<String, String> filterChainDefinitionMap = Maps.newLinkedHashMap();
	  
    @Bean
    public JspRealm getShiroRealm() {  
        return new JspRealm();
    }  
  
    /**
     * 权限缓存
     */
    @Bean
    public EhCacheManager getEhCacheManager() {
        EhCacheManager em = new EhCacheManager(); 
        
        CacheConfiguration config = new CacheConfiguration();
        config.setMaxEntriesLocalHeap(10000);
        config.setEternal(false);
        config.setTimeToIdleSeconds(120);
        config.setTimeToLiveSeconds(120);
        config.setOverflowToDisk(false);
        config.setDiskPersistent(false);
        config.setDiskExpiryThreadIntervalSeconds(120);
        
        net.sf.ehcache.config.Configuration c = new net.sf.ehcache.config.Configuration();
        c.setDefaultCacheConfiguration(config);
        
        em.setCacheManager(new CacheManager(c));
        
        return em;  
    }  
  
    /**
     * 生命周期处理器
     * 用于在实现了Initializable接口的Shiro bean初始化时调用Initializable接口回调，
     * 在实现了Destroyable接口的Shiro bean销毁时调用 Destroyable接口回调。
     */
    @Bean(name="lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
  
    /**
     * Shiro默认会使用Servlet容器的Session,可通过sessionMode属性来指定使用Shiro原生Session
     * 即<property name="sessionMode" value="native"/>,详细说明见官方文档
     * 这里主要是设置自定义的单Realm应用,若有多个Realm,可使用'realms'属性代替
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager() {  
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();  
        dwsm.setRealm(getShiroRealm());  
        dwsm.setCacheManager(getEhCacheManager());  
        return dwsm;  
    }  
  
    /**
     * Web应用中,
     * Shiro可控制的Web请求必须经过Shiro主过滤器的拦截,
     * Shiro对基于Spring的Web应用提供了完美的支持
     */
    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(
    		@Value("${jsp.shiro.login}")String login,
    		@Value("${jsp.shiro.login}")String success,
    		@Value("${jsp.shiro.login}")String unauth
    		) {
    	
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager());
        shiroFilterFactoryBean.setLoginUrl(login);
        shiroFilterFactoryBean.setSuccessUrl(success);
        shiroFilterFactoryBean.setUnauthorizedUrl(unauth);

        //requests
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
        
        //roles perms
        AuthUtils.readRoles().forEach(roles -> {
        	AuthUtils.readAuthsRolesTreeMap(roles).keySet().forEach(auths -> {
        		String k = filterChainDefinitionMap.get(AuthUtils.readAuthsRolesTreeMap(roles).get(auths).getResources());
        		if (!Objects.isNull(k))
        			k += "," + "roles[" + roles + "]"
        					+ "," + "perms[" + auths + "]";
        		else
        			k = "roles[" + roles + "]" + "," + "perms[" + auths + "]";
        		filterChainDefinitionMap.put(AuthUtils.readAuthsRolesTreeMap(roles).get(auths).getResources(), k);
        		
        	});
        });
        
        //roles requests
        AuthUtils.readRolesAuths().keySet().forEach(auth -> {
        	AuthUtils.readRolesAuths().get(auth).getRequests().forEach(url -> {
        		String k = filterChainDefinitionMap.get(url);
        		if (!Objects.isNull(k))
        			k += "," + "roles[" + auth + "]";
        		else
        			k = "roles[" + auth + "]";
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
