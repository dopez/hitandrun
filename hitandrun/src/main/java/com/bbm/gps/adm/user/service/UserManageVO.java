package com.bbm.gps.adm.user.service;

import java.io.Serializable;

/** 
 * 사용자관리에 사용되는 value object들을 정의한다. 
 * <p><b>NOTE:</b>
 * @author 범정부통계포털 이관형 
 * @since 2011.06.17 
 * @version 1.0 
 * @see 
 * 
 * </pre>  
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 */
@SuppressWarnings("serial")
public class UserManageVO extends UserManage implements Serializable {
	
	private String listType = "";
    /** 기관 리스트 */
	private String orgList = "";
	
	private String orgId = "";
	
	private String orgNm = "";
	
	private String authorId = "";
	
	private String userAbsnceFlg = "Y";
	
	private String userAuthorId = ""; 
	
	private String transferUserId;
	
	private String selectedAuthorId;

    /** 승인처리시 사용자id 리스트 */
    private String appUserIdList = "";

    /** 승인여부 검색조건 */
    private String approvalAtCond;
    
	public String getSelectedAuthorId() {
		return selectedAuthorId;
	}

	public void setSelectedAuthorId(String selectedAuthorId) {
		this.selectedAuthorId = selectedAuthorId;
	}

	public String getTransferUserId() {
		return transferUserId;
	}

	public void setTransferUserId(String transferUserId) {
		this.transferUserId = transferUserId;
	}

	public String getUserAuthorId() {
		return userAuthorId;
	}

	public void setUserAuthorId(String userAuthorId) {
		this.userAuthorId = userAuthorId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgNm() {
		return orgNm;
	}

	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}

	public String getOrgList() {
		return orgList;
	}

	public void setOrgList(String orgList) {
		this.orgList = orgList;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getAppUserIdList() {
		return appUserIdList;
	}

	public void setAppUserIdList(String appUserIdList) {
		this.appUserIdList = appUserIdList;
	}

	public String getApprovalAtCond() {
		return approvalAtCond;
	}

	public void setApprovalAtCond(String approvalAtCond) {
		this.approvalAtCond = approvalAtCond;
	}

	public String getUserAbsnceFlg() {
		return userAbsnceFlg;
	}

	public void setUserAbsnceFlg(String userAbsnceFlg) {
		this.userAbsnceFlg = userAbsnceFlg;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}
    
}