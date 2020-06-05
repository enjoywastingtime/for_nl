package com.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiro.entity.SysUser;
import com.shiro.service.SysUserService;
import com.shiro.util.PasswordHelper;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private PasswordHelper passwordHelper;

	/**
	 * 转到登录页面
	 * 
	 * @return
	 */
	@RequestMapping("/login")
	public Object login() {
		return "/pages/login/login.jsp";
	}

	/**
	 * 获取登录人员信息
	 * 
	 * @return
	 */
	@RequestMapping("/getUser")
	@ResponseBody
	public SysUser index() {
		Subject subject = SecurityUtils.getSubject();
		SysUser user = (SysUser) subject.getSession().getAttribute("sysUser");
		return user;
	}

	/**
	 * 未授权的返回值
	 * 
	 * @return
	 */
	@RequestMapping("/unauthc")
	@ResponseBody
	public String unauthc() {
		return "unauthc";
	}

	@RequestMapping("/doLogin")
	@ResponseBody
	public Object doLogin(String username, String password) {
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
		return "success";
	}

	@RequestMapping("/register")
	@ResponseBody
	public Object register(String username, String password) {
		SysUser sysUser = new SysUser();
		sysUser.setUsername(username);
		sysUser.setPassword(password);
		sysUser.setRemarkPswd("--" + password + "--");
		passwordHelper.encryptPassword(sysUser);
		sysUserService.save(sysUser);
		return "SUCCESS";
	}

	@RequestMapping("logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
		}
		return "/pages/login/login.jsp";
	}
}