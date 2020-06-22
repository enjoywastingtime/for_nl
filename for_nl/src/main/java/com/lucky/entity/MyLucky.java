package com.lucky.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.base.entity.BaseEntity;
import com.shiro.entity.SysUser;

@Entity
@Table
public class MyLucky extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String name;

	private String remark;

	private Integer used=0;// 0未被使用；1已被使用

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getUsed() {
		return used;
	}

	public void setUsed(Integer used) {
		this.used = used;
	}

}
