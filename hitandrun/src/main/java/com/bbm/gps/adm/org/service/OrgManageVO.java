package com.bbm.gps.adm.org.service;

import java.io.Serializable;


/** 
 * 기관관리에 사용되는 value object들을 정의한다. 
 * <p><b>NOTE:</b>
 * @author 범정부통계포털 이관형 
 * @since 2011.06.17 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.06.17     이관형      최초 생성 
 * 
 * </pre> 
 */
@SuppressWarnings("serial")
public class OrgManageVO extends OrgManage implements Serializable {

	/** 검색 조건 시작일 */
    private String fromDate = "";

    /** 검색 조건 종료일 */
	private String toDate = "";

    /** 기관ID리스트 */
    private String orgIdList;

    public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getOrgIdList() {
		return orgIdList;
	}

	public void setOrgIdList(String orgIdList) {
		this.orgIdList = orgIdList;
	}
    
}
