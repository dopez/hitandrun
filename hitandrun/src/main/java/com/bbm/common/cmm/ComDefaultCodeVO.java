package com.bbm.common.cmm;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *  클래스
 * @author 기술지원팀 송인겸
 * @since 2011.07.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------       --------    ---------------------------
 *   2011.7.20   송인겸          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class ComDefaultCodeVO implements Serializable {
	
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

    
    
    /** 특정테이블명 - 사용안함 */
    private String tableNm = "";	//특정테이블에서 코드정보를추출시 사용
    
    /** 상세 조건 여부  - 사용안함 */
    private String haveDetailCondition = "N";
    
    /** 상세 조건  - 사용안함  */
    private String detailCondition = "";
    
	public String getUseCodeId() {
		return useCodeId;
	}

	public void setUseCodeId(String useCodeId) {
		this.useCodeId = useCodeId;
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

	/**
     * codeId attribute를 리턴한다.
     * 
     * @return the codeId
     */
    public String getCodeId() {
	return codeId;
    }

    /**
     * codeId attribute 값을 설정한다.
     * 
     * @param codeId
     *            the codeId to set
     */
    public void setCodeId(String codeId) {
	this.codeId = codeId;
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

  


    /**
     * tableNm attribute를 리턴한다.
     * 
     * @return the tableNm
     */
    public String getTableNm() {
	return tableNm;
    }

    /**
     * tableNm attribute 값을 설정한다.
     * 
     * @param tableNm
     *            the tableNm to set
     */
    public void setTableNm(String tableNm) {
	this.tableNm = tableNm;
    }

    /**
     * haveDetailCondition attribute를 리턴한다.
     * 
     * @return the haveDetailCondition
     */
    public String getHaveDetailCondition() {
	return haveDetailCondition;
    }

    /**
     * haveDetailCondition attribute 값을 설정한다.
     * 
     * @param haveDetailCondition
     *            the haveDetailCondition to set
     */
    public void setHaveDetailCondition(String haveDetailCondition) {
	this.haveDetailCondition = haveDetailCondition;
    }

    /**
     * detailCondition attribute를 리턴한다.
     * 
     * @return the detailCondition
     */
    public String getDetailCondition() {
	return detailCondition;
    }

    /**
     * detailCondition attribute 값을 설정한다.
     * 
     * @param detailCondition
     *            the detailCondition to set
     */
    public void setDetailCondition(String detailCondition) {
	this.detailCondition = detailCondition;
    }

    /**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
}
