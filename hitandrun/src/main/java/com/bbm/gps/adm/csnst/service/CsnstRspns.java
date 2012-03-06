package com.bbm.gps.adm.csnst.service;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CsnstRspns extends CsnstManageKey implements Serializable{

	private String rspnsSn;

    public String getRspnsSn() {
        return rspnsSn;
    }

    public void setRspnsSn(String rspnsSn) {
        this.rspnsSn = rspnsSn;
    }

}