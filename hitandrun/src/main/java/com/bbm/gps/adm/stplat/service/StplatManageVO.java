package com.bbm.gps.adm.stplat.service;

import com.bbm.gps.cmm.service.GpsDefaultVO;

import java.io.Serializable;

/** 
 * 약관관리에 사용되는 value object들을 정의한다. 
 * <p><b>NOTE:</b>
 * @author 범정부통계포털 이관형 
 * @since 2011.06.17 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.06.17     이관형      최초 생성 
 * 
 * </pre> 
 */
@SuppressWarnings("serial")
public class StplatManageVO extends GpsDefaultVO implements Serializable {
 
	/**약관구분**/
	private String stplatSe = "";
	
	/**약관명**/
	private String stplatSeNm = "";
	
	/**약관ID**/
	private String stplatId = "";
	
	/**시스템구분**/
	private String sysNm;
	
	/**약관명**/
	private String stplatNm = "";
	
	/**약관내용**/
	private String stplatCn;
	
	/**사용여부**/
	private String stplatUseSe = "";
	
	/** 시스템 검색 조건*/
    private String sysIdSearch;
    
    /** 사용여부 검색 조건*/
    private String actvtyAtSearch;
    
	public String getStplatSe() {
		return stplatSe;
	}

	public void setStplatSe(String stplatSe) {
		this.stplatSe = stplatSe;
	}
	
	public String getStplatSeNm() {
		return stplatSeNm;
	}

	public void setStplatSeNm(String stplatSeNm) {
		this.stplatSeNm = stplatSeNm;
	}

	public String getStplatId() {
		return stplatId;
	}

	public void setStplatId(String stplatId) {
		this.stplatId = stplatId;
	}

	public String getSysNm() {
		return sysNm;
	}

	public void setSysNm(String sysNm) {
		this.sysNm = sysNm;
	}

	public String getStplatNm() {
		return stplatNm;
	}

	public void setStplatNm(String stplatNm) {
		this.stplatNm = stplatNm;
	}

	public String getStplatCn() {
		return stplatCn;
	}

	public void setStplatCn(String stplatCn) {
		this.stplatCn = stplatCn;
	}

	public String getStplatUseSe() {
		return stplatUseSe;
	}

	public void setStplatUseSe(String stplatUseSe) {
		this.stplatUseSe = stplatUseSe;
	}

	public String getSysIdSearch() {
		return sysIdSearch;
	}

	public void setSysIdSearch(String sysIdSearch) {
		this.sysIdSearch = sysIdSearch;
	}

	public String getActvtyAtSearch() {
		return actvtyAtSearch;
	}

	public void setActvtyAtSearch(String actvtyAtSearch) {
		this.actvtyAtSearch = actvtyAtSearch;
	}

}