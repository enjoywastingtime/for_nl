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
@Table(uniqueConstraints=@UniqueConstraint(columnNames="username"))
public class SysUser extends BaseEntity {

	private static final long serialVersionUID = 8947416296024987192L;

	private String username;

	private String password;

	private String salt;

	@ManyToOne
	@JoinColumn(name = "default_role_id")
	private SysRole defaultRole;
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "sys_user_role", joinColumns = @JoinColumn(name = "sys_user_id"), inverseJoinColumns = @JoinColumn(name = "sys_role_id"))
	private Set<SysRole> roles;

	private boolean isLocked;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public SysRole getDefaultRole() {
		return defaultRole;
	}

	public void setDefaultRole(SysRole defaultRole) {
		this.defaultRole = defaultRole;
	}

	public Set<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<SysRole> roles) {
		this.roles = roles;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

}
