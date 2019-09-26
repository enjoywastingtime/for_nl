package com.shiro.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.base.entity.BaseEntity;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames="permission"))
public class SysPermission extends BaseEntity {

	private static final long serialVersionUID = 3748409779686841481L;
	private String permission;
	private String description;
	private boolean available;
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "sys_role_permission", joinColumns = @JoinColumn(name = "sys_permission_id"), inverseJoinColumns = @JoinColumn(name = "sys_role_id"))
	private Set<SysRole> roles;

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Set<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<SysRole> roles) {
		this.roles = roles;
	}

}
