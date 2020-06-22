package com.lucky.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImpl;
import com.lucky.dao.MyLuckyDao;
import com.lucky.entity.MyLucky;

@Service
public class MyLuckyService extends BaseServiceImpl<MyLucky, String> {
	@SuppressWarnings("unused")
	private MyLuckyDao myLuckyDao;

	@Resource
	public void setBaseDao(MyLuckyDao myLuckyDao) {
		this.myLuckyDao = myLuckyDao;
		super.setBaseDao(myLuckyDao);
	}
}
