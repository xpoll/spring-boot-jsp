package site.blmdz;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import site.blmdz.realm.JspRealm;

@Configuration
public class ShiroConfiguration {

	
	private static Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();  
	  
    @Bean
    public JspRealm getShiroRealm() {  
        return new JspRealm();
    }  
  
    @Bean
    public EhCacheManager getEhCacheManager() {  
        EhCacheManager em = new EhCacheManager();  
        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");  
        return em;  
    }  
  
    @Bean
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {  
        return new LifecycleBeanPostProcessor();  
    }  
  
    @Bean  
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {  
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();  
        daap.setProxyTargetClass(true);  
        return daap;  
    }  
  
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager() {  
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();  
        dwsm.setRealm(getShiroRealm());  
        dwsm.setCacheManager(getEhCacheManager());  
        return dwsm;  
    }  
  
    @Bean  
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {  
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();  
        aasa.setSecurityManager(getDefaultWebSecurityManager());  
        return new AuthorizationAttributeSourceAdvisor();  
    }  
  
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean() {  
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();  
        shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager());  
        shiroFilterFactoryBean.setLoginUrl("/login");  
        shiroFilterFactoryBean.setSuccessUrl("/sa/index");
//        shiroFilterFactoryBean.setUnauthorizedUrl("");
        filterChainDefinitionMap.put("/index", "authc");  
        filterChainDefinitionMap.put("/admin", "authc, roles[admin]");
        filterChainDefinitionMap.put("/normal", "authc, roles[normal]");
        filterChainDefinitionMap.put("/admins", "authc, roles[admin], perms[admin_permissions]");
        filterChainDefinitionMap.put("/normals", "authc, roles[normal], perms[normal_permissions]");
        filterChainDefinitionMap.put("/**", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);  
        return shiroFilterFactoryBean;  
    } 
}
