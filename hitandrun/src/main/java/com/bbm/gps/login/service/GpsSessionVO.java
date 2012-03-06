package com.bbm.gps.login.service;

import com.bbm.gps.cmm.service.GpsDefaultVO;

import java.io.Serializable;

/**
 * 세션 VO 클래스
 * <p><b>NOTE:</b>
 * @author 포털통계 이관형
 * @since 2011.07.01
 * @version 1.0
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2011.07.01  이관형          최초 생성  
 *  
 *  </pre>
 */
@SuppressWarnings("serial")
public class GpsSessionVO extends GpsDefaultVO implements Serializable {
	/** 사용자ID */
	private String usrId;
	
	/** 사용자명 */
	private String usrNm;

	/** 기관ID */
	private String orgId;

	/** 기관명 */
	private String orgNm;
	
	/**  회원구분 */
	private String trnsferInfo;
	
	/** 권한ID목록 **/
	private String authorIdArray;
	
	public String getAuthorIdArray() {
		return authorIdArray;
	}

	public void setAuthorIdArray(String authorIdArray) {
		this.authorIdArray = authorIdArray;
	}
	
	public String getTrnsferInfo() {
		return trnsferInfo;
	}

	public void setTrnsferInfo(String trnsferInfo) {
		this.trnsferInfo = trnsferInfo;
	}

	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public String getUsrNm() {
		return usrNm;
	}

	public void setUsrNm(String usrNm) {
		this.usrNm = usrNm;
	}

	public String getOrgNm() {
		return orgNm;
	}

	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}
