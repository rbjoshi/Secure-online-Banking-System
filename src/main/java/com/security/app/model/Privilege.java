package com.security.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "privilege")
public class Privilege {

	@Id
	@Column(name = "PRIVILEGEID")
	private String privilegeId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ROLEIDTOROLE")
	private Role role;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TRANCRUDPERMIDTOCRUDPERM")
	private CrudPermission tranCRUDPermission;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TRANCRUDPERMIDTOCRUDPERM")
	private CrudPermission persInfoCRUDPermission;
	
	public Privilege() {
		// TODO Auto-generated constructor stub
	}

	public String getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(String privilegeId) {
		this.privilegeId = privilegeId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public CrudPermission getTranCRUDPermission() {
		return tranCRUDPermission;
	}

	public void setTranCRUDPermission(CrudPermission tranCRUDPermission) {
		this.tranCRUDPermission = tranCRUDPermission;
	}

	public CrudPermission getPersInfoCRUDPermission() {
		return persInfoCRUDPermission;
	}

	public void setPersInfoCRUDPermission(CrudPermission persInfoCRUDPermission) {
		this.persInfoCRUDPermission = persInfoCRUDPermission;
	}
}
