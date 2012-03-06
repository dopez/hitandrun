package com.bbm.gps.bbs.service;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BbsMemoVO extends BbsSearchVO implements Serializable {
	/** 메모 일련번호 **/
	private int memoSn = 0;
	
	/** 메모 내용 **/
	private String memoCn;
	
	/** 메모 작성자 아이디 **/
	private String memoWrterId = "";
	
	/** 메모 작성자명 **/
	private String memoWrterNm = "";
	
	/** 메모 작성자 유형 **/
	private String memoWrterTy = "";
	
	/** 메모 비밀번호 **/
	private String memoWrterPassword = "";
	
	/** 메모 아바타 유형 **/
	private String memoAvatar = "";
	
	/** 메모 추천수 **/
	private int memoRecom = 0;
	
	/** 메모 삭제 비밀번호 **/
	private String memoDeletePassword = "";
	
	public String getMemoDeletePassword() {
		return memoDeletePassword;
	}

	public void setMemoDeletePassword(String memoDeletePassword) {
		this.memoDeletePassword = memoDeletePassword;
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

	public String getMemoWrterTy() {
		return memoWrterTy;
	}

	public void setMemoWrterTy(String memoWrterTy) {
		this.memoWrterTy = memoWrterTy;
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
