package com.familyuser.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImpl;
import com.familyuser.dao.FamilyUserDao;
import com.familyuser.entity.FamilyUser;

@Service
public class FamilyUserService extends BaseServiceImpl<FamilyUser, String> {
	@SuppressWarnings("unused")
	private FamilyUserDao familyUserDao;

	@Resource
	public void setBaseDao(FamilyUserDao familyUserDao) {
		this.familyUserDao = familyUserDao;
		super.setBaseDao(familyUserDao);
	}
}
