package com.security.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role {
	public Role(String roleName) {
		this.roleName = roleName;
	}

	public Role() {
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue	
	@Column(name = "ROLEID")
	private long roleId;

	@Column(name = "ROLENAME")
	private String roleName;
	
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleName() {
		return roleName;
	}
}
