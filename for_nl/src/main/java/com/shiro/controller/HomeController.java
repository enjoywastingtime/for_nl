package com.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shiro.entity.SysUser;
import com.shiro.service.SysUserService;
import com.shiro.util.PasswordHelper;

@RestController
@RequestMapping
public class HomeController {

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private PasswordHelper passwordHelper;

	@GetMapping("login")
	public Object login() {
		return "Here is Login page";
	}

	@GetMapping("unauthc")
	public Object unauthc() {
		return "Here is Unauthc page";
	}

	@GetMapping("doLogin")
	public Object doLogin(@RequestParam String username, @RequestParam String password) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
		} catch (IncorrectCredentialsException ice) {
			return "password error!";
		} catch (UnknownAccountException uae) {
			return "username error!";
		}

		SysUser sysUser = sysUserService.findByUsername(username);
		subject.getSession().setAttribute("sysUser", sysUser);
		return "SUCCESS";
	}

	@GetMapping("register")
	public Object register(@RequestParam String username, @RequestParam String password) {
		SysUser sysUser = new SysUser();
		sysUser.setUsername(username);
		sysUser.setPassword(password);
		passwordHelper.encryptPassword(sysUser);
		sysUserService.save(sysUser);
		return "SUCCESS";
	}
	@GetMapping("logout")
	public void logout() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
		}
	}
}