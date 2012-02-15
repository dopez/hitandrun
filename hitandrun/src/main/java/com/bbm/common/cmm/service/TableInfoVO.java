package com.bbm.common.cmm.service;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @Class Name : TableInfoVO.java
 * @Description : 테이블 정보를 위한 VO 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2011. 7. 6.     송인겸
 *
 * @author 송인겸
 * @since 2011. 7. 6.
 * @version
 * @see
 *
 */

public class TableInfoVO implements Serializable {

    /**
     * 컬럼이름 
     */
    public String columnname = "";
    /**
     * 데이터 타입
     */
    public String datatype = "";
    /**
     * 데이터 길이 
     */
    public String datalength = "";
    /**
     * 널여부  
     */
    public String nullable = "";

	/**
     * pk여부 
     */
    public String pkyeobu = "";
    
    public String getColumnname() {
		return columnname;
	}

	public void setColumnname(String columnname) {
		this.columnname = columnname;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getDatalength() {
		return datalength;
	}

	public void setDatalength(String datalength) {
		this.datalength = datalength;
	}

	public String getNullable() {
		return nullable;
	}

	public void setNullable(String nullable) {
		this.nullable = nullable;
	}

	public String getPkyeobu() {
		return pkyeobu;
	}

	public void setPkyeobu(String pkyeobu) {
		this.pkyeobu = pkyeobu;
	}
    	
}
