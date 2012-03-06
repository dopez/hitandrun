package com.bbm.gps.login.service;

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
public class GpsLoginVO extends UserManageVO implements Serializable {
	
	/** 아이디저장 **/
	private String saveId = "";
	
	/** 마지막로그인 IP **/
	private String loginLastIp = "";
	
	/** 로그인 실패 횟수 **/
	private int loginFailrCo = 0;
	
	/** 로그인 실패 횟수 **/
	private String loginLockTy = "N";
	
	/** 멀티 로그인 사용 구분  **/
	private String loginMultiTy = "Y";
	
	/** 권한ID목록 **/
	private String authorIdArray;
	
	/** 로그인구분 **/
	private String loginSe;
	
	public String getLoginSe() {
		return loginSe;
	}

	public void setLoginSe(String loginSe) {
		this.loginSe = loginSe;
	}

	public String getAuthorIdArray() {
		return authorIdArray;
	}

	public void setAuthorIdArray(String authorIdArray) {
		this.authorIdArray = authorIdArray;
	}

	public String getLoginLastIp() {
		return loginLastIp;
	}

	public void setLoginLastIp(String loginLastIp) {
		this.loginLastIp = loginLastIp;
	}

	public int getLoginFailrCo() {
		return loginFailrCo;
	}

	public void setLoginFailrCo(int loginFailrCo) {
		this.loginFailrCo = loginFailrCo;
	}

	public String getLoginLockTy() {
		return loginLockTy;
	}

	public void setLoginLockTy(String loginLockTy) {
		this.loginLockTy = loginLockTy;
	}

	public String getLoginMultiTy() {
		return loginMultiTy;
	}

	public void setLoginMultiTy(String loginMultiTy) {
		this.loginMultiTy = loginMultiTy;
	}
	
	public String getSaveId() {
		return saveId;
	}

	public void setSaveId(String saveId) {
		this.saveId = saveId;
	}
}
