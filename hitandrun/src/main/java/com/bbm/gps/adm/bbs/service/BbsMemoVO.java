package com.bbm.gps.adm.bbs.service;

import com.bbm.gps.cmm.service.GpsDefaultVO;

import java.io.Serializable;

/**
 * <p><b>NOTE:</b>게시판메모정보VO
 * @author 통계포털 황기연
 * @since 2011.07.04
 * @version 1.0
 * @see
 *
 * <pre>
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.07.04     황기연          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class BbsMemoVO extends GpsDefaultVO implements Serializable {
	/** 게시판ID **/
	private String bbsId = "";
	/** 게시물 일련번호 **/
	private int bbsSn = 0;
	/** 메모 일련번호 **/
	private int memoSn = 0;
	/** 메모 내용 **/
	private String memoCn;
	/** 메모 작성자 아이디 **/
	private String memoWrterId = "";
	/** 메모 작성자명 **/
	private String memoWrterNm = "";
	/** 메모 작성자 유형 **/
	private String updtusrId = "";
	/** 메모 비밀번호 **/
	private String memoWrterPassword = "";
	/** 메모 아바타 유형 **/
	private String memoAvatar = "";
	/** 메모 추천수 **/
	private int memoRecom = 0;
	
	
	public String getBbsId() {
		return bbsId;
	}
	public void setBbsId(String bbsId) {
		this.bbsId = bbsId;
	}
	public int getBbsSn() {
		return bbsSn;
	}
	public void setBbsSn(int bbsSn) {
		this.bbsSn = bbsSn;
	}
	public int getMemoSn() {
		return memoSn;
	}
	public void setMemoSn(int memoSn) {
		this.memoSn = memoSn;
	}
	public String getMemoCn() {
		return memoCn;
	}
	public void setMemoCn(String memoCn) {
		this.memoCn = memoCn;
	}
	public String getMemoWrterId() {
		return memoWrterId;
	}
	public void setMemoWrterId(String memoWrterId) {
		this.memoWrterId = memoWrterId;
	}
	public String getMemoWrterNm() {
		return memoWrterNm;
	}
	public void setMemoWrterNm(String memoWrterNm) {
		this.memoWrterNm = memoWrterNm;
	}
	public String getUpdtusrId() {
		return updtusrId;
	}
	public void setUpdtusrId(String updtusrId) {
		this.updtusrId = updtusrId;
	}
	public String getMemoWrterPassword() {
		return memoWrterPassword;
	}
	public void setMemoWrterPassword(String memoWrterPassword) {
		this.memoWrterPassword = memoWrterPassword;
	}
	public String getMemoAvatar() {
		return memoAvatar;
	}
	public void setMemoAvatar(String memoAvatar) {
		this.memoAvatar = memoAvatar;
	}
	public int getMemoRecom() {
		return memoRecom;
	}
	public void setMemoRecom(int memoRecom) {
		this.memoRecom = memoRecom;
	}
	
}
