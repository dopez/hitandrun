package com.bbm.sample.service;

import com.bbm.sample.ccm.cde.service.CmmnDetailCodeVO;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 
 * 공통상세코드 VO 클래스
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *
 * </pre>
 */
public class SampleJqgridVO extends CmmnDetailCodeVO implements Serializable {
	
	
	/** total pages for the query*/
	private int total = 0;
	
	/** current page of the query*/
	private int page = 1;	
	
	/** total number of records for the query*/
	private int records = 0;
	
	/** sort column*/
	private String sidx="";
	
	/**  */
	private int rows = 0;

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

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
		setPageIndex(page);
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}
	

    
}
