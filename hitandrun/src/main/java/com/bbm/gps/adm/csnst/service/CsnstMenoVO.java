package com.bbm.gps.adm.csnst.service;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CsnstMenoVO extends CsnstMeno implements Serializable {

	private String memoSnList;

	public String getMemoSnList() {
		return memoSnList;
	}

	public void setMemoSnList(String memoSnList) {
		this.memoSnList = memoSnList;
	}
	
}
