package com.bbm.gps.cmm.service;

import java.io.Serializable;

/**
 * 포털 공통 VO
 * <p><b>NOTE:</b>
 * @author 통계포털 황기연
 * @since 2011.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.06.01  황기연          최초 생성
 *	 2011.07.29  황기연          메뉴관련 사용자ID,권한ID추가
 * </pre>
 */

@SuppressWarnings("serial")
public class GpsDefaultVO implements Serializable{
	/** 검색조건 */
    private String searchCondition = "";
    
    /** 검색Keyword */
    private String searchKeyword = "";

    /** OrderByClause */
    private String orderByClause = "";
    
	/** 현재페이지 */
    private int pageIndex = 1;
    
    /** 페이지갯수 */
    private int pageUnit = 10;
    
    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 0;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;
    
    /** rowNo */
    private int rowNo = 0;
    
    /** 등록자ID */
    private String registerId = "";
    
    /** 등록자IP */
	private String registerIp = "";
	
	/** 등록일 */
	private String registDt = "";
	
	/** 수정자ID */
	private String updtusrId = "";

	/** 수정일 */
	private String updtDt = "";
	
	/** 첨부파일 총갯수 */
	private String fileListCnt = "";
	
	/** 시스템 사용자 여부 */
	private String sysUserAt = "";
	
	/** LEFT 메뉴ID */
	private String leftMenuId = "";

	/** 메뉴ID **/
	private String menuId = "";
	
	/** 로그인후 페이지 **/
	private String returnUrl = "";
	
	/** 시스템ID **/
	private String sysId = "";

	/** 검색조건(시스템아이디) */
	public String searchSysId;
	
	/** 게시판ID **/
	private String bbsId = "";
	
	/** 게시글 일련번호 **/
	private int bbsSn = 0;

	/** 사용자ID **/
	private String userId;

	/** 권한레벨 **/
	private String authorLevel = "";
	
	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	
	public String getSearchSysId() {
		return searchSysId;
	}

	public void setSearchSysId(String searchSysId) {
		this.searchSysId = searchSysId;
	}

	public int getBbsSn() {
		return bbsSn;
	}

	public void setBbsSn(int bbsSn) {
		this.bbsSn = bbsSn;
	}

	public String getLeftMenuId() {
		return leftMenuId;
	}

	public void setLeftMenuId(String leftMenuId) {
		this.leftMenuId = leftMenuId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

	public String getRegisterId() {
		return registerId;
	}

	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public String getRegistDt() {
		return registDt;
	}

	public void setRegistDt(String registDt) {
		this.registDt = registDt;
	}

	public String getUpdtusrId() {
		return updtusrId;
	}

	public void setUpdtusrId(String updtusrId) {
		this.updtusrId = updtusrId;
	}

	public String getUpdtDt() {
		return updtDt;
	}

	public void setUpdtDt(String updtDt) {
		this.updtDt = updtDt;
	}

	public String getFileListCnt() {
		return fileListCnt;
	}

	public void setFileListCnt(String fileListCnt) {
		this.fileListCnt = fileListCnt;
	}
	
	public String getSysUserAt() {
		return sysUserAt;
	}

	public void setSysUserAt(String sysUserAt) {
		this.sysUserAt = sysUserAt;
	}
	
	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getBbsId() {
		return bbsId;
	}

	public void setBbsId(String bbsId) {
		this.bbsId = bbsId;
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAuthorLevel() {
		return authorLevel;
	}

	public void setAuthorLevel(String authorLevel) {
		this.authorLevel = authorLevel;
	}
	
}
