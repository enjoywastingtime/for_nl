package com.shiro.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.shiro.entity.SysPermission;
import com.shiro.entity.SysRole;
import com.shiro.entity.SysUser;
import com.shiro.service.SysUserService;

public class MyAuthorizingRealm extends AuthorizingRealm {

	@Autowired
	private SysUserService sysUserService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 需要返回的权限信息
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		String userName = (String) principals.getPrimaryPrincipal();
		SysUser user = sysUserService.findByUsername(userName);
		for (SysRole role : user.getRoles()) {
			// 设置角色
			authorizationInfo.addRole(role.getRoleName());
			for (SysPermission permission : role.getPermissions()) {
				// 设置权限
				authorizationInfo.addStringPermission(permission.getPermission());
			}
		}
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 通过token获取用户名
		String userName = (String) token.getPrincipal();
		SysUser user = sysUserService.findByUsername(userName);
		if (user == null)
			return null;
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(),
				ByteSource.Util.bytes(user.getSalt()), getName());
		return authenticationInfo;
	}
}
