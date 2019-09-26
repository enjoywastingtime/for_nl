package com.shiro.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImpl;
import com.shiro.dao.SysUserDao;
import com.shiro.entity.SysUser;

@Service
public class SysUserService extends BaseServiceImpl<SysUser, String> {
	private SysUserDao sysUserDao;

	@Resource
	public void setBaseDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
		super.setBaseDao(sysUserDao);
	}
	
	public SysUser findByUsername(String userName){
		return sysUserDao.findByUsername(userName);
	}
}
