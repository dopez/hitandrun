package com.bbm.gps.adm.menu.service;

import java.io.Serializable;

/**
 * 메뉴관리 시스템관련 value object 들을 정의
 * <p><b>NOTE:</b>
 * @author 이관/포털 황기연
 * @since 2011.06.07
 * @version 1.0
 * @see
 *
 * <pre>
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.06.07  황기연          최초 생성
 *
 * </pre>
 */

@SuppressWarnings("serial")
public class SystemVO extends MenuDefaultVO implements Serializable{
	/** 시스템ID **/
	private String sysId = "";
	
	/** 상위시스템ID **/
	private String upperSysId = "";

	/** 시스템NO **/
	private int sysNo = 0;

	/** 기관ID **/
	private String orgId = "";

	/** 생산ID **/
	private String prdctnId = "";

	/** 회차 **/
	private int svyOdr = 0;
	
	/** 조사명 **/
	private String svyNm = ""; 

	/** 시스템(사이트)명 **/
	private String sysNm = "";

	/** 시스템(사이트)단축명 **/
	private String sysAbrv = "";

	/** 시스템(사이트)영문명 **/
	private String sysEngNm = "";

	/** 사용자관리사용여부 **/
	private String userManageUseAt = "Y";

	/** 권한관리사용여부 **/
	private String authorManageUseAt = "Y";

	/** 메뉴관리사용여부 **/
	private String menuManageUseAt = "Y";

	/** 공통코드관리사용여부 **/
	private String codeManageUseAt = "Y";

	/** 프로그램관리사용여부 **/
	private String programManageUseAt = "Y";

	/** 메인이미지관리사용여부 **/
	private String mainImageManageUseAt = "N";

	/** 로그인이미지관리사용여부 **/
	private String loginImageManageUseAt = "N";

	/** 게시판관리사용여부 **/
	private String boardManageUseAt = "N";

	/** 추천사이트관리사용여부 **/
	private String recomendSiteManageUseAt = "N";

	/** 약관관리사용여부 **/
	private String stplatManageUseAt = "N";

	/** 만족도조사사용여부 **/
	private String snstUseAt = "N";

	/** 배너관리사용여부 **/
	private String bannerManageUseAt = "N";

	/** 설문조사사용여부 **/
	private String qestnarUseAt = "N";

	/** 스케줄관리사용여부 **/
	private String schdulManageUseAt = "N";

	/** 이벤트관리사용여부 **/
	private String eventManageUseAt = "N";

	/** 접속통계사용여부 **/
	private String conectStatsUseAt = "N";

	/** 파일관리자사용여부 **/
	private String fileManageUseAt = "N";

	/** 팝업관리사용여부 **/
	private String popupManageUseAt = "N";

	/** 기간(시작일) **/
	private String bgnde = "";

	/** 기간(종료일) **/
	private String endde = "";

	/** 비고 **/
	private String sysRm = "";
	
	public String getSvyNm() {
		return svyNm;
	}

	public void setSvyNm(String svyNm) {
		this.svyNm = svyNm;
	}
	
	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getUpperSysId() {
		return upperSysId;
	}

	public void setUpperSysId(String upperSysId) {
		this.upperSysId = upperSysId;
	}

	public int getSysNo() {
		return sysNo;
	}

	public void setSysNo(int sysNo) {
		this.sysNo = sysNo;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getPrdctnId() {
		return prdctnId;
	}

	public void setPrdctnId(String prdctnId) {
		this.prdctnId = prdctnId;
	}

	public int getSvyOdr() {
		return svyOdr;
	}

	public void setSvyOdr(int svyOdr) {
		this.svyOdr = svyOdr;
	}

	public String getSysNm() {
		return sysNm;
	}

	public void setSysNm(String sysNm) {
		this.sysNm = sysNm;
	}

	public String getSysAbrv() {
		return sysAbrv;
	}

	public void setSysAbrv(String sysAbrv) {
		this.sysAbrv = sysAbrv;
	}

	public String getSysEngNm() {
		return sysEngNm;
	}

	public void setSysEngNm(String sysEngNm) {
		this.sysEngNm = sysEngNm;
	}

	public String getUserManageUseAt() {
		return userManageUseAt;
	}

	public void setUserManageUseAt(String userManageUseAt) {
		this.userManageUseAt = userManageUseAt;
	}

	public String getAuthorManageUseAt() {
		return authorManageUseAt;
	}

	public void setAuthorManageUseAt(String authorManageUseAt) {
		this.authorManageUseAt = authorManageUseAt;
	}

	public String getMenuManageUseAt() {
		return menuManageUseAt;
	}

	public void setMenuManageUseAt(String menuManageUseAt) {
		this.menuManageUseAt = menuManageUseAt;
	}

	public String getCodeManageUseAt() {
		return codeManageUseAt;
	}

	public void setCodeManageUseAt(String codeManageUseAt) {
		this.codeManageUseAt = codeManageUseAt;
	}

	public String getProgramManageUseAt() {
		return programManageUseAt;
	}

	public void setProgramManageUseAt(String programManageUseAt) {
		this.programManageUseAt = programManageUseAt;
	}

	public String getMainImageManageUseAt() {
		return mainImageManageUseAt;
	}

	public void setMainImageManageUseAt(String mainImageManageUseAt) {
		this.mainImageManageUseAt = mainImageManageUseAt;
	}

	public String getLoginImageManageUseAt() {
		return loginImageManageUseAt;
	}

	public void setLoginImageManageUseAt(String loginImageManageUseAt) {
		this.loginImageManageUseAt = loginImageManageUseAt;
	}

	public String getBoardManageUseAt() {
		return boardManageUseAt;
	}

	public void setBoardManageUseAt(String boardManageUseAt) {
		this.boardManageUseAt = boardManageUseAt;
	}

	public String getRecomendSiteManageUseAt() {
		return recomendSiteManageUseAt;
	}

	public void setRecomendSiteManageUseAt(String recomendSiteManageUseAt) {
		this.recomendSiteManageUseAt = recomendSiteManageUseAt;
	}

	public String getStplatManageUseAt() {
		return stplatManageUseAt;
	}

	public void setStplatManageUseAt(String stplatManageUseAt) {
		this.stplatManageUseAt = stplatManageUseAt;
	}

	public String getSnstUseAt() {
		return snstUseAt;
	}

	public void setSnstUseAt(String snstUseAt) {
		this.snstUseAt = snstUseAt;
	}

	public String getBannerManageUseAt() {
		return bannerManageUseAt;
	}

	public void setBannerManageUseAt(String bannerManageUseAt) {
		this.bannerManageUseAt = bannerManageUseAt;
	}

	public String getQestnarUseAt() {
		return qestnarUseAt;
	}

	public void setQestnarUseAt(String qestnarUseAt) {
		this.qestnarUseAt = qestnarUseAt;
	}

	public String getSchdulManageUseAt() {
		return schdulManageUseAt;
	}

	public void setSchdulManageUseAt(String schdulManageUseAt) {
		this.schdulManageUseAt = schdulManageUseAt;
	}

	public String getEventManageUseAt() {
		return eventManageUseAt;
	}

	public void setEventManageUseAt(String eventManageUseAt) {
		this.eventManageUseAt = eventManageUseAt;
	}

	public String getConectStatsUseAt() {
		return conectStatsUseAt;
	}

	public void setConectStatsUseAt(String conectStatsUseAt) {
		this.conectStatsUseAt = conectStatsUseAt;
	}

	public String getFileManageUseAt() {
		return fileManageUseAt;
	}

	public void setFileManageUseAt(String fileManageUseAt) {
		this.fileManageUseAt = fileManageUseAt;
	}

	public String getPopupManageUseAt() {
		return popupManageUseAt;
	}

	public void setPopupManageUseAt(String popupManageUseAt) {
		this.popupManageUseAt = popupManageUseAt;
	}

	public String getBgnde() {
		return bgnde;
	}

	public void setBgnde(String bgnde) {
		this.bgnde = bgnde;
	}

	public String getEndde() {
		return endde;
	}

	public void setEndde(String endde) {
		this.endde = endde;
	}

	public String getSysRm() {
		return sysRm;
	}

	public void setSysRm(String sysRm) {
		this.sysRm = sysRm;
	}
}
