package com.base.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseCtrl extends ApplicationObjectSupport {
	protected Object getBean(String name) {
		return this.getApplicationContext().getBean(name);
	}

	public HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public HttpSession getSession() {
		return getRequest().getSession();
	}

	public HttpServletResponse getResponse() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	}

	/**
	 * 请求方式判断
	 * 
	 * @param request
	 * @return
	 */
	public boolean isAjaxRequest(HttpServletRequest request) {
		if (!(request.getHeader("accept").indexOf("application/json") > -1
				|| (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1) || "XMLHttpRequest"
					.equalsIgnoreCase(request.getParameter("X_REQUESTED_WITH")))) {
			return false;
		}
		return true;
	}

	protected String getCookie(String cookieName) {
		Cookie[] cookies = getRequest().getCookies();
		for (Cookie cookie : cookies) {
			if (cookieName.equals(cookie.getName())) {
				return cookie.getValue();
			}
		}

		return null;
	}

	/**
	 * 设置 cookie
	 * 
	 * @param cookieName
	 * @param value
	 * @param age
	 */
	protected void setCookie(String cookieName, String value, int age) {
		Cookie cookie = new Cookie(cookieName, value);
		cookie.setMaxAge(age);
		// cookie.setHttpOnly(true);
		getResponse().addCookie(cookie);
	}

}
