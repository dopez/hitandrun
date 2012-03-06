package com.bbm.sample.dcm.service;

import com.bbm.common.cmm.ComDefaultVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** 
 * 명부업로드 리스트에서 사용되는 value object들을 정의한다. 
 * <p><b>NOTE:</b> 
 * @author 오션정보기술 김선민 
 * @since 2011.07.18 
 * @version 1.0 
 * @see 
 * 
 * <pre>  
 *  == Modification Information == 
 *   
 *    date          author                note 
 *  -----------    -------    --------------------------- 
 *  2011.07.18      김선민               최초 생성 
 * 
 * </pre> 
 */

public class DtColcErExaminVO extends ComDefaultVO implements Serializable {
	
	/** 게시판 항목 고유  ID */ 
	
	/** 통계기관ID */ 
	private String seqno = "";	
	
	private String item = "";	
	private String beforevalue = "";	
	private String aftervalue = "";	
	private String wdate = "";	
	private String writer = "";	
	
	
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
	
	/** 게시판 분류 */ 
    private String searchCondition1  = "";
    
    private String searchCondition2  = "";
    
    public String getSearchCondition1() {
		return searchCondition1;
	}
	public void setSearchCondition1(String searchCondition1) {
		this.searchCondition1 = searchCondition1;
	}
	
	
	    
    public String getSearchCondition2() {
		return searchCondition2;
	}
	public void setSearchCondition2(String searchCondition2) {
		this.searchCondition2 = searchCondition2;
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
	
	
	
	//실제검색에 필요한 vo	
	public String getSeqno() {
		return seqno;
	}
	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}
	
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	
	public String getAftervalue() {
		return aftervalue;
	}
	public void setAftervalue(String aftervalue) {
		this.aftervalue = aftervalue;
	}
	
	public String getBeforevalue() {
		return beforevalue;
	}
	public void setBeforevalue(String beforevalue) {
		this.beforevalue = beforevalue;
	}
	
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate){
		this.wdate = wdate;
	}
	
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer){
		this.writer = writer;
	}
	
	
}
