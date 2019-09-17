package com.familyuser.entity;

import javax.persistence.Entity;

import com.base.entity.BaseEntity;

@Entity
public class FamilyUser extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private String name;
	private String sex;
	private int age;
	private String account;
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
