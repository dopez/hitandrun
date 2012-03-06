package com.bbm.gps.adm.author.intergrated.service;

import java.io.Serializable;

import com.bbm.gps.adm.author.service.AuthorManage;

/** 
 * 권한관리에 사용되는 value object들을 정의한다. 
 * <p><b>NOTE:</b>
 * @author 포탈통계 이관형 
 * @since 2011.08.18 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.08.18     이관형      최초 생성 
 * 
 * </pre> 
 */
@SuppressWarnings("serial")
public class IgrAuthorManageVO extends AuthorManage implements Serializable {

	private String upperMenuId = "";

	/** 세션정보를 취득하기 위한 사용자ID */
	private String userId;
	
	/** 트리레벨 */
	private int treeLv;
	
	/** 임시트리레벨(전 트리레벨) */
	private int tempTreeLv;
	
	/** 메뉴NO */
	private int menuNo;

	/** 시스템명 */
	private String sysNm;
	
	private String upperAuthorNm;

	public String getUpperMenuId() {
		return upperMenuId;
	}
	public void setUpperMenuId(String upperMenuId) {
		this.upperMenuId = upperMenuId;
	}
	public String getSysNm() {
		return sysNm;
	}
	public void setSysNm(String sysNm) {
		this.sysNm = sysNm;
	}
	public String getUpperAuthorNm() {
		return upperAuthorNm;
	}
	public void setUpperAuthorNm(String upperAuthorNm) {
		this.upperAuthorNm = upperAuthorNm;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getTreeLv() {
		return treeLv;
	}
	public void setTreeLv(int treeLv) {
		this.treeLv = treeLv;
	}
	public int getTempTreeLv() {
		return tempTreeLv;
	}
	public void setTempTreeLv(int tempTreeLv) {
		this.tempTreeLv = tempTreeLv;
	}
	public int getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}
}