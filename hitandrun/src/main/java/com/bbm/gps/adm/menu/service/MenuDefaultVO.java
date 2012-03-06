package com.bbm.gps.adm.menu.service;

import com.bbm.gps.cmm.service.GpsDefaultVO;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MenuDefaultVO extends GpsDefaultVO implements Serializable{
	
	/** 구분(시스템,사이트) **/
	private String sysSe = "";

	/** root 시스템ID**/
	private String rootSysId = "";
	
	/** 사용자ID **/
	private String usrId = "";

	/** 권한ID **/
	private String authorId = "";
	
	/** 전체 사용여부 **/
	private String useAt = "Y";

	/** 삭제여부 **/
	private String deleteAt = "";
	

	public String getSysSe() {
		return sysSe;
	}

	public void setSysSe(String sysSe) {
		this.sysSe = sysSe;
	}

	public String getRootSysId() {
		return rootSysId;
	}

	public void setRootSysId(String rootSysId) {
		this.rootSysId = rootSysId;
	}

	public String getUsrId() {
		return usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	
	public String getUseAt() {
		return useAt;
	}

	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}
	
	public String getDeleteAt() {
		return deleteAt;
	}

	public void setDeleteAt(String deleteAt) {
		this.deleteAt = deleteAt;
	}
}
