package com.shiro.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImpl;
import com.shiro.dao.SysPermissionDao;
import com.shiro.entity.SysPermission;

@Service
public class SysPermissionService extends BaseServiceImpl<SysPermission, String> {
	@SuppressWarnings("unused")
	private SysPermissionDao sysPermissionDao;

	@Resource
	public void setBaseDao(SysPermissionDao sysPermissionDao) {
		this.sysPermissionDao = sysPermissionDao;
		super.setBaseDao(sysPermissionDao);
	}
}
