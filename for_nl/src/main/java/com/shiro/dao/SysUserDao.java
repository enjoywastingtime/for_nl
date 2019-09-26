package com.shiro.dao;

import com.base.dao.BaseDao;
import com.shiro.entity.SysUser;

public interface SysUserDao extends BaseDao<SysUser, String> {
	
	public SysUser findByUsername(String userName);

}
