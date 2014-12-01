package com.security.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "crudpermission")
public class CrudPermission {

	@Id
	@Column(name = "CRUDPERMISSIONID")
	private int crudPermissionId;

	@Column(name = "C")
	private String createPerm;

	@Column(name = "R")
	private String readPerm;

	@Column(name = "U")
	private String upadatePerm;

	@Column(name = "D")
	private String deletePerm;

	public int getCrudPermissionId() {
		return crudPermissionId;
	}

	public void setCrudPermissionId(int crudPermissionId) {
		this.crudPermissionId = crudPermissionId;
	}

	public String getCreatePerm() {
		return createPerm;
	}

	public void setCreatePerm(String createPerm) {
		this.createPerm = createPerm;
	}

	public String getReadPerm() {
		return readPerm;
	}

	public void setReadPerm(String readPerm) {
		this.readPerm = readPerm;
	}

	public String getUpadatePerm() {
		return upadatePerm;
	}

	public void setUpadatePerm(String upadatePerm) {
		this.upadatePerm = upadatePerm;
	}

	public String getDeletePerm() {
		return deletePerm;
	}

	public void setDeletePerm(String deletePerm) {
		this.deletePerm = deletePerm;
	}

}
