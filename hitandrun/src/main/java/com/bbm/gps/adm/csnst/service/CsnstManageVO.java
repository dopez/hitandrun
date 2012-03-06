package com.bbm.gps.adm.csnst.service;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CsnstManageVO extends CsnstManage implements Serializable{

	private String sysNm;
	
	private boolean fileDeleteFlg;
	
	private int rspnsCnt;

	private int rspnsTotal;
	
	public String getSysNm() {
		return sysNm;
	}

	public void setSysNm(String sysNm) {
		this.sysNm = sysNm;
	}

	public boolean isFileDeleteFlg() {
		return fileDeleteFlg;
	}

	public void setFileDeleteFlg(boolean fileDeleteFlg) {
		this.fileDeleteFlg = fileDeleteFlg;
	}
	
	public int getRspnsCnt() {
		return rspnsCnt;
	}

	public void setRspnsCnt(int rspnsCnt) {
		this.rspnsCnt = rspnsCnt;
	}

	public int getRspnsTotal() {
		return rspnsTotal;
	}

	public void setRspnsTotal(int rspnsTotal) {
		this.rspnsTotal = rspnsTotal;
	}

}
