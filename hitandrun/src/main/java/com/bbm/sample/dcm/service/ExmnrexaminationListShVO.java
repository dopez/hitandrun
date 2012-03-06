/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bbm.sample.dcm.service;

import com.bbm.common.cmm.ComDefaultVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 자료수집수행 VO
 * @filename  DataColctExcVO
 * @author 조사표 설계/수집 개발팀 최종대
 * @since 2011.07.22
 * @version 1.0
 * @see
 *
 * <pre>
 *  == Modification Information ==
 *   
 *   date         author               note
 *  -------      --------    ---------------------------
 *   2011.07.22   최종대     최초 생성
 *							1. 자료수집수행 리스트에서 사용되는 value object 정의.
 * </pre>
 */

public class ExmnrexaminationListShVO extends ComDefaultVO implements Serializable {
	
	/** 게시판 항목 고유  ID */ 
	private String prdctnid = "";	
	private String svyodr = "";	
	private String nmsttableid = "";	
	private String recordid = "";	
	private String nmsthistsn = "";	
	private String changeitemfield = "";	
	private String changeitemnm = "";	
	private String changebeforeval = "";	
	private String changeafterval = "";	
	private String changerid = "";	
	private String changedt = "";	
	private String seqno = "";	
	
	/** 상위코드 ID */
    private String upperCodeId = "";
	
    /** 사용자 코드 ID */
    private String useCodeId = "";
    
    /** 코드명 */
    private String codeNm = "";
    
    /** 정렬형태 */
    private String orderType = "";
	
	private int page = 1;

	private String sidx="";

	private int rows = 0;
	
	private int total = 0;
	
	private int records = 0;

	private String sord="";	
	
	private String userid="";
	
	private String zonetype="";
	
	private String tableid = "";
	
	/** 행정구역관련 */ 
    private String searchCondition1  = "";
    
    public String getSearchCondition1() {
		return searchCondition1;
	}
	public void setSearchCondition1(String searchCondition1) {
		this.searchCondition1 = searchCondition1;
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
     * @return the codeNm
     */
    public String getCodeNm() {
	return codeNm;
    }

    /**
     * codeNm attribute 값을 설정한다.
     * 
     * @param codeNm
     *            the codeNm to set
     */
    public void setCodeNm(String codeNm) {
	this.codeNm = codeNm;
    }

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

	
	//실제검색에 필요한 vo	
	
	public String getPrdctnid() {
		return prdctnid;
	}
	public void setPrdctnid(String prdctnid){
		this.prdctnid = prdctnid;
	}
	
	public String getSvyodr() {
		return svyodr;
	}
	public void setSvyodr(String svyodr){
		this.svyodr = svyodr;
	}
	
	public String getNmsttableid() {
		return nmsttableid;
	}
	public void setNmsttableid(String nmsttableid){
		this.nmsttableid = nmsttableid;
	}
	
	public String getRecordid() {
		return recordid;
	}
	public void setRecordid(String recordid){
		this.recordid = recordid;
	}
	
	public String getNmsthistsn() {
		return nmsthistsn;
	}
	public void setNmsthistsn(String nmsthistsn){
		this.nmsthistsn = nmsthistsn;
	}
	
	public String getChangeitemfield() {
		return changeitemfield;
	}
	public void setChangeitemfield(String changeitemfield){
		this.changeitemfield = changeitemfield;
	}
	
	public String getChangeitemnm() {
		return changeitemnm;
	}
	public void setChangeitemnm(String changeitemnm){
		this.changeitemnm = changeitemnm;
	}
	
	public String getChangebeforeval() {
		return changebeforeval;
	}
	public void setChangebeforeval(String changebeforeval){
		this.changebeforeval = changebeforeval;
	}
	
	public String getChangeafterval() {
		return changeafterval;
	}
	public void setChangeafterval(String changeafterval){
		this.changeafterval = changeafterval;
	}
	
	public String getChangerid() {
		return changerid;
	}
	public void setChangerid(String changerid){
		this.changerid = changerid;
	}
	
	public String getChangedt() {
		return changedt;
	}
	public void setChangedt(String changedt){
		this.changedt = changedt;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid){
		this.userid = userid;
	}
	
	public String getZonetype() {
		return zonetype;
	}
	public void setZonetype(String zonetype){
		this.zonetype = zonetype;
	}
	
	public String getSeqno() {
		return seqno;
	}
	public void setSeqno(String seqno){
		this.seqno = seqno;
	}
	
	public String getTableid() {
		return tableid;
	}
	public void setTableid(String tableid) {
		this.tableid = tableid;
	}
	
}
