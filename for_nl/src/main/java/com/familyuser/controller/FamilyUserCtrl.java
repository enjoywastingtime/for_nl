package com.familyuser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.controller.BaseCtrl;
import com.familyuser.entity.FamilyUser;
import com.familyuser.service.FamilyUserService;

@RestController
@RequestMapping("familyUser")
public class FamilyUserCtrl extends BaseCtrl{
	@Autowired
	private FamilyUserService familyUserService;

	@RequestMapping("getUsers")
	public List<FamilyUser> getUsers() {
		return familyUserService.findAll();
	}

	@RequestMapping("insertUser")
	public String insertUser() {
		FamilyUser u = new FamilyUser();
		u.setName("zqh");
		u.setAccount("02515aa6");
		familyUserService.save(u);
		return u.getId();
	}

}
