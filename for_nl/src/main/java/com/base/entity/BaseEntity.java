package com.base.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.familyuser.entity.FamilyUser;

@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -5427533689772884514L;

	@Id
	@GeneratedValue(generator = "myIdStrategy")
	@GenericGenerator(name = "myIdStrategy", strategy = "uuid")
	private String id;

	private int status;

	private Date createDate = new Date();
	@ManyToOne
	private FamilyUser createBy;

	private Date lastUpdateDate = new Date();
	@ManyToOne
	private FamilyUser lastUpdateBy;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public FamilyUser getCreateBy() {
		return createBy;
	}

	public void setCreateBy(FamilyUser createBy) {
		this.createBy = createBy;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public FamilyUser getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(FamilyUser lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

}
