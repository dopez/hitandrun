package com.bbm.gps.adm.user.service;

import com.bbm.gps.cmm.service.GpsDefaultVO;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserManage extends GpsDefaultVO implements Serializable {

	/** 사용자 이미지 삭제 플레그 */
	private boolean userImgDelFlg;
	
	/** upOrgId(상위기관ID의 등록 처리 기능은 mapping-narastat-gps-user.xml 에서 처리) */
	
	private String password;
	private String nm;
	private String se;
	private String grad;
	private String psitn;
	private String photoCpcty;
	private String zip;
	private String addrCn;
	private String addrDtlCn;
	private String phonCn;
	private String moblphonCn;
	private String email;
	private String httpCn;
	private String wrcNm;
	private String deptNm;
	private String rspofc;
	private String wrcPhonCn;
	private String wrcZip;
	private String wrcAddrCn;
	private String wrcAddrDtlCn;
	private String photoOthbcAt;
	private String httpOthbcAt;
	private String emailOthbcAt;
	private String moblphonOthbcAt;
	private String wrcOthbcAt;
	private String smsRecptnAt;
	private String newsRecptnAt;
	private int loginCo;
	private String lastLoginTime;
	private String engNm;
	private String wrcFaxCn;
	private String photoFileNm;
	private String photoFileMask;
	private String approvalAt;
	private String addrOthbcAt;
	private String phonOthbcAt;
	private String wrcPhonOthbcAt;
	private String wrcFaxOthbcAt;
	private String wrcAddrOthbcAt;
    private String orgChooseAt;
	private String orgKd;
    private String orgId;
    private String orgDeptId;
    private String emailCo;
    private String emailCoNm;
    private String trnsferInfo;
    private String offiNm;
	/** 비밀번호 변경시간 **/
    private String pwChangeTime;
    
    /** 실명인증리턴값 */
    private String retInfo = "";
    
    /** 실명인증리턴값 */
    private String scriptGb = "";
    
    /** 회원가입 실명인증 중복체크 비교값  */
    private String ipin = "";
    
    /** 회원가입 gpin 중복체크 비교값 */
    private String gpin = "";
    
    /** 회원가입 gpki 중복체크 비교값 */
    private String gpki = "";
    
    public String getIpin() {
		return ipin;
	}
	public void setIpin(String ipin) {
		this.ipin = ipin;
	}
	public String getGpin() {
		return gpin;
	}
	public void setGpin(String gpin) {
		this.gpin = gpin;
	}
    
    public String getRetInfo() {
		return retInfo;
	}
	public void setRetInfo(String retInfo) {
		this.retInfo = retInfo;
	}
	public String getScriptGb() {
		return scriptGb;
	}
	public void setScriptGb(String scriptGb) {
		this.scriptGb = scriptGb;
	}
	public String getGpki() {
		return gpki;
	}
	public void setGpki(String gpki) {
		this.gpki = gpki;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public String getSe() {
		return se;
	}
	public void setSe(String se) {
		this.se = se;
	}
	public String getGrad() {
		return grad;
	}
	public void setGrad(String grad) {
		this.grad = grad;
	}
	public String getPsitn() {
		return psitn;
	}
	public void setPsitn(String psitn) {
		this.psitn = psitn;
	}
	public String getPhotoCpcty() {
		return photoCpcty;
	}
	public void setPhotoCpcty(String photoCpcty) {
		this.photoCpcty = photoCpcty;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getAddrCn() {
		return addrCn;
	}
	public void setAddrCn(String addrCn) {
		this.addrCn = addrCn;
	}
	public String getAddrDtlCn() {
		return addrDtlCn;
	}
	public void setAddrDtlCn(String addrDtlCn) {
		this.addrDtlCn = addrDtlCn;
	}
	public String getPhonCn() {
		return phonCn;
	}
	public void setPhonCn(String phonCn) {
		this.phonCn = phonCn;
	}
	public String getMoblphonCn() {
		return moblphonCn;
	}
	public void setMoblphonCn(String moblphonCn) {
		this.moblphonCn = moblphonCn;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHttpCn() {
		return httpCn;
	}
	public void setHttpCn(String httpCn) {
		this.httpCn = httpCn;
	}
	public String getWrcNm() {
		return wrcNm;
	}
	public void setWrcNm(String wrcNm) {
		this.wrcNm = wrcNm;
	}
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
	public String getRspofc() {
		return rspofc;
	}
	public void setRspofc(String rspofc) {
		this.rspofc = rspofc;
	}
	public String getWrcPhonCn() {
		return wrcPhonCn;
	}
	public void setWrcPhonCn(String wrcPhonCn) {
		this.wrcPhonCn = wrcPhonCn;
	}
	public String getWrcZip() {
		return wrcZip;
	}
	public void setWrcZip(String wrcZip) {
		this.wrcZip = wrcZip;
	}
	public String getWrcAddrCn() {
		return wrcAddrCn;
	}
	public void setWrcAddrCn(String wrcAddrCn) {
		this.wrcAddrCn = wrcAddrCn;
	}
	public String getWrcAddrDtlCn() {
		return wrcAddrDtlCn;
	}
	public void setWrcAddrDtlCn(String wrcAddrDtlCn) {
		this.wrcAddrDtlCn = wrcAddrDtlCn;
	}
	public String getPhotoOthbcAt() {
		return photoOthbcAt;
	}
	public void setPhotoOthbcAt(String photoOthbcAt) {
		this.photoOthbcAt = photoOthbcAt;
	}
	public String getHttpOthbcAt() {
		return httpOthbcAt;
	}
	public void setHttpOthbcAt(String httpOthbcAt) {
		this.httpOthbcAt = httpOthbcAt;
	}
	public String getEmailOthbcAt() {
		return emailOthbcAt;
	}
	public void setEmailOthbcAt(String emailOthbcAt) {
		this.emailOthbcAt = emailOthbcAt;
	}
	public String getMoblphonOthbcAt() {
		return moblphonOthbcAt;
	}
	public void setMoblphonOthbcAt(String moblphonOthbcAt) {
		this.moblphonOthbcAt = moblphonOthbcAt;
	}
	public String getWrcOthbcAt() {
		return wrcOthbcAt;
	}
	public void setWrcOthbcAt(String wrcOthbcAt) {
		this.wrcOthbcAt = wrcOthbcAt;
	}
	public String getSmsRecptnAt() {
		return smsRecptnAt;
	}
	public void setSmsRecptnAt(String smsRecptnAt) {
		this.smsRecptnAt = smsRecptnAt;
	}
	public String getNewsRecptnAt() {
		return newsRecptnAt;
	}
	public void setNewsRecptnAt(String newsRecptnAt) {
		this.newsRecptnAt = newsRecptnAt;
	}
	public int getLoginCo() {
		return loginCo;
	}
	public void setLoginCo(int loginCo) {
		this.loginCo = loginCo;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getEngNm() {
		return engNm;
	}
	public void setEngNm(String engNm) {
		this.engNm = engNm;
	}
	public String getWrcFaxCn() {
		return wrcFaxCn;
	}
	public void setWrcFaxCn(String wrcFaxCn) {
		this.wrcFaxCn = wrcFaxCn;
	}
	public String getPhotoFileNm() {
		return photoFileNm;
	}
	public void setPhotoFileNm(String photoFileNm) {
		this.photoFileNm = photoFileNm;
	}
	public String getPhotoFileMask() {
		return photoFileMask;
	}
	public void setPhotoFileMask(String photoFileMask) {
		this.photoFileMask = photoFileMask;
	}
	public String getApprovalAt() {
		return approvalAt;
	}
	public void setApprovalAt(String approvalAt) {
		this.approvalAt = approvalAt;
	}
	public String getAddrOthbcAt() {
		return addrOthbcAt;
	}
	public void setAddrOthbcAt(String addrOthbcAt) {
		this.addrOthbcAt = addrOthbcAt;
	}
	public String getPhonOthbcAt() {
		return phonOthbcAt;
	}
	public void setPhonOthbcAt(String phonOthbcAt) {
		this.phonOthbcAt = phonOthbcAt;
	}
	public String getWrcPhonOthbcAt() {
		return wrcPhonOthbcAt;
	}
	public void setWrcPhonOthbcAt(String wrcPhonOthbcAt) {
		this.wrcPhonOthbcAt = wrcPhonOthbcAt;
	}
	public String getWrcFaxOthbcAt() {
		return wrcFaxOthbcAt;
	}
	public void setWrcFaxOthbcAt(String wrcFaxOthbcAt) {
		this.wrcFaxOthbcAt = wrcFaxOthbcAt;
	}
	public String getWrcAddrOthbcAt() {
		return wrcAddrOthbcAt;
	}
	public void setWrcAddrOthbcAt(String wrcAddrOthbcAt) {
		this.wrcAddrOthbcAt = wrcAddrOthbcAt;
	}
	public boolean isUserImgDelFlg() {
		return userImgDelFlg;
	}
	public void setUserImgDelFlg(boolean userImgDelFlg) {
		this.userImgDelFlg = userImgDelFlg;
	}
	public String getOrgKd() {
		return orgKd;
	}
	public void setOrgKd(String orgKd) {
		this.orgKd = orgKd;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgDeptId() {
		return orgDeptId;
	}
	public void setOrgDeptId(String orgDeptId) {
		this.orgDeptId = orgDeptId;
	}
	public String getEmailCo() {
		return emailCo;
	}
	public void setEmailCo(String emailCo) {
		this.emailCo = emailCo;
	}
	public String getEmailCoNm() {
		return emailCoNm;
	}
	public void setEmailCoNm(String emailCoNm) {
		this.emailCoNm = emailCoNm;
	}
	public String getOrgChooseAt() {
		return orgChooseAt;
	}
	public void setOrgChooseAt(String orgChooseAt) {
		this.orgChooseAt = orgChooseAt;
	}
	
	public String getTrnsferInfo() {
		return trnsferInfo;
	}
	public void setTrnsferInfo(String trnsferInfo) {
		this.trnsferInfo = trnsferInfo;
	}
	public String getOffiNm() {
		return offiNm;
	}
	public void setOffiNm(String offiNm) {
		this.offiNm = offiNm;
	}
	public String getPwChangeTime() {
			return pwChangeTime;
	}
	public void setPwChangeTime(String pwChangeTime) {
		this.pwChangeTime = pwChangeTime;
	}
	
	
	
}
