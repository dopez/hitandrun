package com.bbm.gps.adm.csnst.service;

import java.io.Serializable;

import com.bbm.gps.cmm.service.GpsDefaultVO;

@SuppressWarnings("serial")
public class CsnstManageKey extends GpsDefaultVO implements Serializable{

    private String csnstId;

    private String csnstSn;

    private String qesitmSn = "0";

    private String iemSn = "0";

	public String getCsnstId() {
		return csnstId;
	}

	public void setCsnstId(String csnstId) {
		this.csnstId = csnstId;
	}

	public String getCsnstSn() {
		return csnstSn;
	}

	public void setCsnstSn(String csnstSn) {
		this.csnstSn = csnstSn;
	}

	public String getQesitmSn() {
		return qesitmSn;
	}

	public void setQesitmSn(String qesitmSn) {
		this.qesitmSn = qesitmSn;
	}

	public String getIemSn() {
		return iemSn;
	}

	public void setIemSn(String iemSn) {
		this.iemSn = iemSn;
	}

}