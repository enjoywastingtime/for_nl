package com.lucky.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImpl;
import com.lucky.dao.LuckyThingDao;
import com.lucky.entity.LuckyThing;

@Service
public class LuckyThingService extends BaseServiceImpl<LuckyThing, String> {
	@SuppressWarnings("unused")
	private LuckyThingDao luckyThingDao;

	@Resource
	public void setBaseDao(LuckyThingDao luckyThingDao) {
		this.luckyThingDao = luckyThingDao;
		super.setBaseDao(luckyThingDao);
	}
}
