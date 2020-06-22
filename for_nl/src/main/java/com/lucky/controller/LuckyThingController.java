package com.lucky.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.util.CheckUtil;
import com.base.util.ConfigProperties;
import com.lucky.entity.LuckyThing;
import com.lucky.entity.MyLucky;
import com.lucky.service.LuckyThingService;
import com.lucky.service.MyLuckyService;
import com.shiro.entity.SysUser;

@Controller
@RequestMapping("/luckything")
public class LuckyThingController {

	@Autowired
	private LuckyThingService luckyThingService;
	@Autowired
	private MyLuckyService myLuckyService;

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

	/**
	 * 获取我的奖品列表
	 * 
	 * @return
	 */
	@RequestMapping("/getMyList")
	@ResponseBody
	public List<MyLucky> getMyList() {
		Subject subject = SecurityUtils.getSubject();
		SysUser user = (SysUser) subject.getSession().getAttribute("sysUser");
		String id = user.getId();
		String sql = "select * from my_lucky where create_by_id='" + id + "' order by create_date desc";
		return myLuckyService.findListEntityBySql(sql, null, MyLucky.class);
	}
	
	/**
	 * 设置一条我的奖品数据为已使用
	 * @param id
	 * @return
	 */
	@RequestMapping("/setUsed")
	@ResponseBody
	public String setUsed(String id){
		String sql="update my_lucky set used=1 where id='"+id+"'";
		myLuckyService.executeSql(sql);
		return "done";
	}
	
	@RequestMapping("/setRemark")
	@ResponseBody
	public String setRemark(String id,String remark){
		String sql="update my_lucky set remark='"+remark+"' where id='"+id+"'";
		myLuckyService.executeSql(sql);
		return "done";
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
	 * 
	 * @param inOrOout
	 * @return
	 */
	@RequestMapping("/movePool")
	@ResponseBody
	public String movePool(String id, String inOrOut) {
		try {
			String sql = "update lucky_thing set in_pool=" + ("in".equals(inOrOut) ? 1 : 0) + " where id='" + id + "' and code=0";
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
	 * 
	 * @return
	 */
	@RequestMapping("/getPoolThings")
	@ResponseBody
	public List<LuckyThing> getPoolThings() {
		String sql = "select * from lucky_thing where code=0 and in_pool=1 and rownum<=9";
		return luckyThingService.findListEntityBySql(sql, null, LuckyThing.class);
	}

	/**
	 * 确认奖品
	 * 
	 * @return
	 */
	@RequestMapping("/confirmLucky")
	@ResponseBody
	public String confirmLucky(String name, String code) {
		if (!ConfigProperties.CONFIRM_CODE.equals(code)) {
			return "领奖码不正确！";
		}
		if (CheckUtil.isInvalid(name)) {
			return "未获取到奖品！";
		}
		MyLucky ml = new MyLucky();
		ml.setName(name);
		myLuckyService.save(ml);
		return "done";
	}

}
