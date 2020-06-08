package com.lucky.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.util.CheckUtil;
import com.lucky.entity.LuckyThing;
import com.lucky.service.LuckyThingService;

@Controller
@RequestMapping("/luckything")
public class LuckyThingController {
	
	@Autowired
	private LuckyThingService luckyThingService;
	
	/**
	 * 获取新添加还未使用的奖品
	 * @return
	 */
	@RequestMapping("/getAvailable")
	@ResponseBody
	public List<LuckyThing> getAvailable(){
		String sql="select * from lucky_thing where code=0 order by create_date desc";
		return luckyThingService.findListEntityBySql(sql, null, LuckyThing.class);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(LuckyThing obj){
		try {
			if(obj==null||CheckUtil.isInvalid(obj.getName())){
				return "invalid";
			}
			obj.setCode("0");
			luckyThingService.save(obj);
			return "done";
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		
	}

}
