package com.bbm.sample.dcm.service;

import java.io.Serializable;

import com.bbm.gps.adm.user.service.UserManageVO;

/**
 * @Class Name : LoginVO.java
 * @Description : Login VO class
 *  
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2011.07.01  이관형          최초 생성 
 *  @version 1.0
 *  @see
 *  
 */
@SuppressWarnings("serial")
public class LoginExaminationLoginVO extends UserManageVO implements Serializable {
   
	private String seqno;
   
    /** returnUrl **/
	private String returnUrl = "";
	
	public String getReturnUrl() {
		return returnUrl;
	}
	
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	
	// 개발 로그인 여부 
	public String getSeqno() {
		return seqno;
	}
	
	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
}
