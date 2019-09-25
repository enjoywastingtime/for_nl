package com.shiro.util;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.shiro.entity.SysUser;

public class PasswordHelper {

	// 随机字符串生成器
	private RandomNumberGenerator randomOb = new SecureRandomNumberGenerator();

	// 散列算法名（md5方式）
	private static final String ALGORITHM_NAME = "md5";

	// 散列迭代次数
	private static final int HASH_ITERATION = 2;

	/**
	 * 加密用户
	 * 
	 * @param user
	 *            用户，用户名(name)、密码(pwd)、盐\加密因子(salt)
	 */
	public void encryptPassword(SysUser user) {
		// 生成加密因子，保存盐。
		user.setSalt(randomOb.nextBytes().toHex());
		// 加密密码 SimpleHash（算法名，密码，盐的byte，次数）.toHex()
		String newPassword = new SimpleHash(ALGORITHM_NAME, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), HASH_ITERATION).toHex();
		// 更新密码
		user.setPassword(newPassword);
	}
	
}
