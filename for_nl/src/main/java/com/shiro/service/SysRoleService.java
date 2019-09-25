package com.shiro.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImpl;
import com.shiro.dao.SysRoleDao;
import com.shiro.entity.SysRole;

@Service
public class SysRoleService extends BaseServiceImpl<SysRole, String> {
	@SuppressWarnings("unused")
	private SysRoleDao sysRoleDao;

	@Resource
	public void setBaseDao(SysRoleDao sysRoleDao) {
		this.sysRoleDao = sysRoleDao;
		super.setBaseDao(sysRoleDao);
	}
}
