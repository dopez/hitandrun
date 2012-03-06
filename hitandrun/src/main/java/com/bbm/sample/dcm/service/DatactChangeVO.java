package com.bbm.sample.dcm.service;

import com.bbm.common.cmm.ComDefaultVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**  
 * 선택항목 미리보기   
 * @Class Name : DatactChangeVO.java
 * @Description : 
 * @
 * @  수정일      		수정자           수정내용
 * @ ---------   	   ---------  	----------------------------------
 * @ 2011. 08. 26.	 	최종대            최초생성
 * 
 * @author : 조사표 설계/수집 개발팀 최종대
 * @since  : 2011. 08. 26.
 * @version 1.0
 * 
 *  Copyright (C) by Ucore All right reserved.
 */

public class DatactChangeVO extends ComDefaultVO implements Serializable {
	
	/** 게시판 항목 고유  ID */ 
	 
	private String prdctnid = "";	
	
	private String svyodr = "";	
	
	private String recordid = "";	
	
	private String nmsthistsn = "";	
	
	private String replchistsn = "";	
	
	private String changebeforehshld = "";	
	
	private String changebeforebsnes = "";	
	
	private String replcresn = "";	
	
	private String changerid = "";	
	
	private String changedt = "";	
	
	
	private int page = 1;

	private String sidx="";

	private int rows = 0;
	
	private int total = 0;
	
	private int records = 0;

	private String sord="";	
	
	
	/** 상위코드 ID */
    private String upperCodeId = "";
	
    /** 사용자 코드 ID */
    private String useCodeId = "";
    


	/** 코드 ID */
    private String codeId = "";
    
	/** 코드명 */
    private String codeNm = "";
    
    /** 정렬형태 */
    private String orderType = "";
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
		setPageIndex(page);
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
		setRecordCountPerPage(rows);
	}


	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRecords() {
		return records;
	}
	public void setRecords(int records) {
		this.records = records;
	}

	public String getSord() {
		return sord;
	}


	public void setSord(String sord) {
		this.sord = sord;
	}
	
	public String getUpperCodeId() {
		return upperCodeId;
	}

	public void setUpperCodeId(String upperCodeId) {
		this.upperCodeId = upperCodeId;
	}
	
    public String getOrderType() {
		return orderType;
	}

    
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	public String getUseCodeId() {
		return useCodeId;
	}

	public void setUseCodeId(String useCodeId) {
		this.useCodeId = useCodeId;
	}
	
	/**
     * codeNm attribute를 리턴한다.
     * 
     */
    public String getCodeNm() {
	return codeNm;
    }

    /**
     * codeNm attribute 값을 설정한다.
     */
    public void setCodeNm(String codeNm) {
	this.codeNm = codeNm;
    }
	

	
	//실제검색에 필요한 vo	
	public String getPrdctnid() {
		return prdctnid;
	}
	public void setPrdctnid(String prdctnid) {
		this.prdctnid = prdctnid;
	}	
	
	public String getSvyodr() {
		return svyodr;
	}
	public void setSvyodr(String svyodr) {
		this.svyodr = svyodr;
	}
	
	public String getRecordid() {
		return recordid;
	}
	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}
	
	public String getNmsthistsn() {
		return nmsthistsn;
	}
	public void setNmsthistsn(String nmsthistsn) {
		this.nmsthistsn = nmsthistsn;
	}
	
	public String getReplchistsn() {
		return replchistsn;
	}
	public void setReplchistsn(String replchistsn) {
		this.replchistsn = replchistsn;
	}
	
	public String getChangebeforehshld() {
		return changebeforehshld;
	}
	public void setChangebeforehshld(String changebeforehshld) {
		this.changebeforehshld = changebeforehshld;
	}
	
	public String getChangebeforebsnes() {
		return changebeforebsnes;
	}
	public void setChangebeforebsnes(String changebeforebsnes) {
		this.changebeforebsnes = changebeforebsnes;
	}
	
	public String getReplcresn() {
		return replcresn;
	}
	public void setReplcresn(String replcresn) {
		this.replcresn = replcresn;
	}
	
	public String getChangerid() {
		return changerid;
	}
	public void setChangerid(String changerid) {
		this.changerid = changerid;
	}
	
	public String getChangedt() {
		return changedt;
	}
	public void setChangedt(String changedt) {
		this.changedt = changedt;
	}
	
    public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	
}
