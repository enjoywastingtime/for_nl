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
	 * 
	 * @return
	 */
	@RequestMapping("/getAvailable")
	@ResponseBody
	public List<LuckyThing> getAvailable() {
		String sql = "select * from lucky_thing where code=0 order by create_date desc";
		return luckyThingService.findListEntityBySql(sql, null, LuckyThing.class);
	}

	@RequestMapping("/add")
	@ResponseBody
	public String add(LuckyThing obj) {
		try {
			if (obj == null || CheckUtil.isInvalid(obj.getName())) {
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

	@RequestMapping("/dele")
	@ResponseBody
	public String dele(String id) {
		try {
			String sql = "delete from lucky_thing where id='" + id + "' and code=0";
			int i = luckyThingService.executeSql(sql);
			if (i >= 0) {
				return "done";
			} else {
				return "used";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
	}
	
	/**
	 * 将奖品放入或拿出抽奖池
	 * @param inOrOout
	 * @return
	 */
	@RequestMapping("/movePool")
	@ResponseBody
	public String movePool(String id,String inOrOut) {
		try {
			String sql = "update lucky_thing set in_pool="+("in".equals(inOrOut)?1:0)+" where id='" + id + "' and code=0";
			int i = luckyThingService.executeSql(sql);
			if (i >= 0) {
				return "done";
			} else {
				return "used";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
	}
	/**
	 * 获取抽奖池中的奖品
	 * @return
	 */
	@RequestMapping("/getPoolThings")
	@ResponseBody
	public List<LuckyThing> getPoolThings() {
		String sql="select * from lucky_thing where code=0 and in_pool=1 and rownum<=9";
		return luckyThingService.findListEntityBySql(sql, null, LuckyThing.class);
	}

}
