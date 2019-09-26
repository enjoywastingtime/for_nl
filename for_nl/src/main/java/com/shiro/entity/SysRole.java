package com.shiro.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.base.entity.BaseEntity;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames="roleName"))
public class SysRole extends BaseEntity {

	private static final long serialVersionUID = 257876793839614844L;

	private String roleName;
	private String description;
	private boolean avalibale;
	@ManyToOne
	@JoinColumn(name = "parent_role_id")
	private SysRole parentRole;
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "sys_user_role", joinColumns = @JoinColumn(name = "sys_role_id"), inverseJoinColumns = @JoinColumn(name = "sys_user_id"))
	private Set<SysUser> users;
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "sys_role_permission", joinColumns = @JoinColumn(name = "sys_role_id"), inverseJoinColumns = @JoinColumn(name = "sys_permission_id"))
	private Set<SysPermission> permissions;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isAvalibale() {
		return avalibale;
	}

	public void setAvalibale(boolean avalibale) {
		this.avalibale = avalibale;
	}

	public SysRole getParentRole() {
		return parentRole;
	}

	public void setParentRole(SysRole parentRole) {
		this.parentRole = parentRole;
	}

	public Set<SysUser> getUsers() {
		return users;
	}

	public void setUsers(Set<SysUser> users) {
		this.users = users;
	}

	public Set<SysPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<SysPermission> permissions) {
		this.permissions = permissions;
	}

}
