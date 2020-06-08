package com.lucky.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.base.entity.BaseEntity;
import com.shiro.entity.SysUser;

@Entity
@Table
public class LuckyThing extends BaseEntity {

	private static final long serialVersionUID = 8947416296024987192L;

	private String name;

	private String remark;

	private String code;// 0初始；1赋予某人；2已被某人消费

	private Date giveDate;// 赋予时间

	private Date useDate;// 使用时间

	@ManyToOne
	@JoinColumn(name = "owner_id")
	private SysUser owner;

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

	public Date getGiveDate() {
		return giveDate;
	}

	public void setGiveDate(Date giveDate) {
		this.giveDate = giveDate;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public SysUser getOwner() {
		return owner;
	}

	public void setOwner(SysUser owner) {
		this.owner = owner;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
