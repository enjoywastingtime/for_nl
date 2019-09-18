package com.familyuser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.familyuser.dao.FamilyUserDao;
import com.familyuser.entity.FamilyUser;

@RestController
@RequestMapping("familyUser")
public class FamilyUserCtrl {
	@Autowired
	FamilyUserDao familyUserDao;

	@RequestMapping("getUsers")
	public Object getUsers() {
		return familyUserDao.findAll();
	}

	@RequestMapping("insertUser")
	public String insertUser() {
		FamilyUser u = new FamilyUser();
		u.setName("nl");
		u.setAccount("025156");
		familyUserDao.save(u);
		return u.getId();
	}
	
	@RequestMapping("pageUsers")
	public List<FamilyUser> pageUsers() {
		String sql="select * from family_user";
		return  familyUserDao.findPageEntityBySql(new PageRequest(0, 1), sql, null,FamilyUser.class).getContent();
	}
	
	@RequestMapping("countUser")
	public int countUser() {
		String sql="select count(*) from family_user";
		return  familyUserDao.getCountBySQL(sql);
	}
}
