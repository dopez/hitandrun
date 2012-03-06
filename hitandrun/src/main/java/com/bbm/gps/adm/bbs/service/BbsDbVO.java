package com.bbm.gps.adm.bbs.service;

import java.io.Serializable;
/**
 * 게시판DB정보VO
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
public class BbsDbVO extends BbsSearchVO implements Serializable {
	
	/** 게시판 DB 이름  **/
	private String dbName = "";
	
	/** 게시판 설명 **/
	private String dbComment = "";
	
	/** 게시판 DB 관리자 이름 **/
	private String dbMngrNm = "";
	
	/** 게시판 DB 관리자 아이디 **/
	private String dbMngrId = "";
	
	/** 게시판 DB 관리자 암호 **/
	private String dbMngrPassword = "";
	
	
	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	
	public String getDbComment() {
		return dbComment;
	}

	public void setDbComment(String dbComment) {
		this.dbComment = dbComment;
	}

	public String getDbMngrNm() {
		return dbMngrNm;
	}

	public void setDbMngrNm(String dbMngrNm) {
		this.dbMngrNm = dbMngrNm;
	}

	public String getDbMngrId() {
		return dbMngrId;
	}

	public void setDbMngrId(String dbMngrId) {
		this.dbMngrId = dbMngrId;
	}

	public String getDbMngrPassword() {
		return dbMngrPassword;
	}

	public void setDbMngrPassword(String dbMngrPassword) {
		this.dbMngrPassword = dbMngrPassword;
	}
	
}
