package com.security.app.model;

import java.util.Set;

import javax.persistence.Entity;

@Entity
public class PlaceHolder {
	private String placeholderstring;
	private String authorizationid;
	
	public String getPlaceholderstring() {
		return placeholderstring;
	}
	public void setPlaceholderstring(String placeholderstring) {
		this.placeholderstring = placeholderstring;
	}
	public String getAuthorizationid() {
		return authorizationid;
	}
	public void setAuthorizationid(String authorizationid) {
		this.authorizationid = authorizationid;
	}
}
