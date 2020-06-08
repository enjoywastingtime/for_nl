package com.base.util;

/**
 * 常用输入检查工具类
 * @author cba
 * @date 2020年6月8日
 */
public class CheckUtil {
	/**
	 * 检查字符串是否无效
	 * 当为null，“”，“   ”，“undefined”，“null”时均为true
	 * @param str
	 * @return
	 */
	public static boolean isInvalid(String str){
		if(str==null){
			return true;
		}
		if(str.trim().length()==0||str.equals("undefined")||str.equals("null")){
			return true;
		}
		return false;
	}
	
	/**
	 * 只检测字符串是否为空
	 * @param str
	 * @return
	 */
	public boolean isEmpty(String str){
		if(str==null||str.equals("")){
			return true;
		}else{
			return false;
		}
	}
}
