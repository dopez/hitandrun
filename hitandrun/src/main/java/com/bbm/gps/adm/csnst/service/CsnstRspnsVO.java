package com.bbm.gps.adm.csnst.service;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CsnstRspnsVO extends CsnstRspns implements Serializable {

    private String memoCn;

	public String getMemoCn() {
		return memoCn;
	}

	public void setMemoCn(String memoCn) {
		this.memoCn = memoCn;
	}

}
