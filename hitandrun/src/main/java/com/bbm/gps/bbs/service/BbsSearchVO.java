package com.bbm.gps.bbs.service;

import java.io.Serializable;

import com.bbm.gps.cmm.service.GpsDefaultVO;

@SuppressWarnings("serial")
public class BbsSearchVO extends GpsDefaultVO implements Serializable {
	
	/** 분류코드검색 **/
	private String searchCtgryCode = "";
	
	/** 권한확인용 비밀번호 **/
	private String authorPassword = "";

	public String getAuthorPassword() {
		return authorPassword;
	}

	public void setAuthorPassword(String authorPassword) {
		this.authorPassword = authorPassword;
	}

	public String getSearchCtgryCode() {
		return searchCtgryCode;
	}

	public void setSearchCtgryCode(String searchCtgryCode) {
		this.searchCtgryCode = searchCtgryCode;
	}
}
