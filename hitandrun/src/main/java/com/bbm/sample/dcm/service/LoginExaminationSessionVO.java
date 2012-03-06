package com.bbm.sample.dcm.service;

import egovframework.rte.psl.dataaccess.SpsAbstractDAO;

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
public class LoginExaminationSessionVO extends SpsAbstractDAO implements Serializable {
	
	
	/** 생산ID */
	private String orgId;

	/** 생산회차 */
	private String orgNm;
	
	/** 명부시퀀스 */
	private String seqno;
	
	
	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
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
