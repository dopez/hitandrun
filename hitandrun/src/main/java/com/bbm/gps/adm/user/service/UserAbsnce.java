package com.bbm.gps.adm.user.service;

import java.io.Serializable;

import com.bbm.gps.cmm.service.GpsDefaultVO;

@SuppressWarnings("serial")
public class UserAbsnce extends GpsDefaultVO implements Serializable {

	private String absnceUserId;
	
	private String transferUserId;
	
	private String authorId;
	
	private String userAuthorId;

	public String getAbsnceUserId() {
		return absnceUserId;
	}

	public void setAbsnceUserId(String absnceUserId) {
		this.absnceUserId = absnceUserId;
	}

	public String getTransferUserId() {
		return transferUserId;
	}

	public void setTransferUserId(String transferUserId) {
		this.transferUserId = transferUserId;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getUserAuthorId() {
		return userAuthorId;
	}

	public void setUserAuthorId(String userAuthorId) {
		this.userAuthorId = userAuthorId;
	}
	
	
}
