package com.example.demo.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * shiro配置类
 * 
 * */


@Configuration
public class shiroConfig {
   
	/*  
	 * 创建shiroFilterFactoryBean
	 * */
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
		 //设置安全管理器
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		
		/**
		 * shiro过滤器
		 * anon 无需认证即可登录访问
		 * authc 必须认证才可以	 访问
		 * user 如果使用rememberMe的功能就可以实现访问
		 * 
		 * */
		Map<String,String> filterMap=new LinkedHashMap<String, String>();
		/*
		 * filterMap.put("/add", "authc"); filterMap.put("/update", "authc");
		 */
		
		//放行login请求
		filterMap.put("/login","anon");
		
		
		filterMap.put("/add","perms[pages:add]");
		
		filterMap.put("/*", "authc");
		
		//修改调整的登录界面
		shiroFilterFactoryBean.setLoginUrl("/tologin");
		
		shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");
		
	   shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
		return shiroFilterFactoryBean;
	}
	
	
	/*
	 * 创建DefaultWebSecurityManager
	 * */
	@Bean(name="securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager( UserRealm userRealm) {
		DefaultWebSecurityManager securityManager=new  DefaultWebSecurityManager();
		//关联realm
		securityManager.setRealm(userRealm);
		return securityManager;
	}
	
	/*
	 * 创建Realm
	 * */
	@Bean
	public UserRealm getRealm() {
		
		return new UserRealm();
	}
}
