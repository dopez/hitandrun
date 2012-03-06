package com.bbm.gps.adm.author.intergrated.service;

import java.io.Serializable;

import com.bbm.gps.adm.author.service.UserAuthor;

@SuppressWarnings("serial")
public class IgrUserAuthorManageVO extends UserAuthor implements Serializable {
	
	private String orgId;
	
	private String userIdList;

	private String userAuthorIdList;
	
	private String nm;
	
	private String authorKind;
	
	private String authorNm;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getUserAuthorIdList() {
		return userAuthorIdList;
	}

	public void setUserAuthorIdList(String userAuthorIdList) {
		this.userAuthorIdList = userAuthorIdList;
	}

	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	public String getAuthorKind() {
		return authorKind;
	}

	public void setAuthorKind(String authorKind) {
		this.authorKind = authorKind;
	}

	public String getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(String userIdList) {
		this.userIdList = userIdList;
	}

	public String getAuthorNm() {
		return authorNm;
	}

	public void setAuthorNm(String authorNm) {
		this.authorNm = authorNm;
	}

}
