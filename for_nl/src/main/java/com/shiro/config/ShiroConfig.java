package com.shiro.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shiro.util.PasswordHelper;

@Configuration
public class ShiroConfig {

	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {

		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		shiroFilterFactoryBean.setLoginUrl("/login/login");
		shiroFilterFactoryBean.setUnauthorizedUrl("login/unauthc");

		filterChainDefinitionMap.put("/login/**", "anon");
		filterChainDefinitionMap.put("/sources/**", "anon");
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	/*
	 * 提供散列算法的信息
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName(PasswordHelper.ALGORITHM_NAME); // 散列算法
		hashedCredentialsMatcher.setHashIterations(PasswordHelper.HASH_ITERATION); // 散列次数
		return hashedCredentialsMatcher;
	}

	/*
	 * 认证域
	 */
	@Bean
	public MyAuthorizingRealm shiroRealm() {
		// 自己实现的Realm
		MyAuthorizingRealm shiroRealm = new MyAuthorizingRealm();
		shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher()); // 设置加密算法
		return shiroRealm;
	}

	/*
	 * 设置安全管理类的realm为上面写的realm
	 */
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(shiroRealm());
		return securityManager;
	}

	@Bean
	public PasswordHelper passwordHelper() {
		return new PasswordHelper();
	}
}
