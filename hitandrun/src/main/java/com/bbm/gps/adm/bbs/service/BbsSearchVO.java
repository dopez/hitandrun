package com.bbm.gps.adm.bbs.service;

import com.bbm.gps.cmm.service.GpsDefaultVO;

import java.io.Serializable;

/**
 * 게시판검색관련VO
 * <p><b>NOTE:</b>
 * @author 통계포털 황기연
 * @since 2011.07.04
 * @version 1.0
 * @see
 *
 * <pre>
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.07.04  황기연          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class BbsSearchVO extends GpsDefaultVO implements Serializable {
	
	/** 사용자ID **/
	private String usrId = "";

	/** 시스템 검색 구분 **/
	private String searchSysId = "";

	/** 게시판 DB 접두어 **/
	private String dbTname = "";

	public String getDbTname() {
		return dbTname;
	}

	public void setDbTname(String dbTname) {
		this.dbTname = dbTname;
	}
	
	public String getSearchSysId() {
		return searchSysId;
	}

	public void setSearchSysId(String searchSysId) {
		this.searchSysId = searchSysId;
	}
	
	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
}
