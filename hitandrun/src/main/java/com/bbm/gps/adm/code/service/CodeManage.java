package com.bbm.gps.adm.code.service;

import com.bbm.gps.cmm.service.GpsDefaultVO;

import java.io.Serializable;

/** 
 * 코드관리에 사용되는 value object들을 정의한다. 
 * <p><b>NOTE:</b>
 * @author 범정부통계포털 이관형 
 * @since 2011.06.17 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.06.17     이관형      최초 생성 
 * 
 * </pre> 
 */
@SuppressWarnings("serial")
public class CodeManage extends GpsDefaultVO implements Serializable {

	/** 코드ID */
    private String codeId;

    /** 코드구분 */
    private String codeSe;

	/** 상위코드 */
    private String upperCodeId;

	/** 코드타입번호 */
    private String codeTyNo;

	/** 한글코드명 */
    private String codeNm;

	/** 코드색인번호 */
    private String codeIndexNo;

	/** 영문코드명 */
    private String codeEngNm;

	/** 한글코드약어명 */
    private String codeAbbrNm;

	/** 영문코드약어명 */
    private String codeAbbrEngNm;

	/** 상위코드타입번호 */
    private String upperCodeTyNo;

	/** 상위코드색인번호 */
    private String upperCodeIndexNo;

	/** 유효시작일 */
    private String validBgnde;

	/** 유효종료일 */
    private String validEndde;

	/** 함수값여부 */
    private String fncValAt = "Y";;

	/** 조직ID */
    private String orgId;

	/** 실사용ID */
    private String useCodeId;

	/** 추가정보 */
    private String addInfo;
    
    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getCodeSe() {
        return codeSe;
    }

    public void setCodeSe(String codeSe) {
        this.codeSe = codeSe;
    }

	public String getUpperCodeId() {
        return upperCodeId;
    }

    public void setUpperCodeId(String upperCodeId) {
        this.upperCodeId = upperCodeId;
    }

    public String getCodeTyNo() {
        return codeTyNo;
    }

    public void setCodeTyNo(String codeTyNo) {
        this.codeTyNo = codeTyNo;
    }

    public String getCodeNm() {
        return codeNm;
    }

    public void setCodeNm(String codeNm) {
        this.codeNm = codeNm;
    }

    public String getCodeIndexNo() {
        return codeIndexNo;
    }

    public void setCodeIndexNo(String codeIndexNo) {
        this.codeIndexNo = codeIndexNo;
    }

    public String getCodeEngNm() {
        return codeEngNm;
    }

    public void setCodeEngNm(String codeEngNm) {
        this.codeEngNm = codeEngNm;
    }

    public String getCodeAbbrNm() {
        return codeAbbrNm;
    }

    public void setCodeAbbrNm(String codeAbbrNm) {
        this.codeAbbrNm = codeAbbrNm;
    }

    public String getCodeAbbrEngNm() {
        return codeAbbrEngNm;
    }

    public void setCodeAbbrEngNm(String codeAbbrEngNm) {
        this.codeAbbrEngNm = codeAbbrEngNm;
    }

    public String getUpperCodeTyNo() {
        return upperCodeTyNo;
    }

    public void setUpperCodeTyNo(String upperCodeTyNo) {
        this.upperCodeTyNo = upperCodeTyNo;
    }

    public String getUpperCodeIndexNo() {
        return upperCodeIndexNo;
    }

    public void setUpperCodeIndexNo(String upperCodeIndexNo) {
        this.upperCodeIndexNo = upperCodeIndexNo;
    }

    public String getValidBgnde() {
        return validBgnde;
    }

    public void setValidBgnde(String validBgnde) {
        this.validBgnde = validBgnde;
    }

    public String getValidEndde() {
        return validEndde;
    }

    public void setValidEndde(String validEndde) {
        this.validEndde = validEndde;
    }

    public String getFncValAt() {
        return fncValAt;
    }

    public void setFncValAt(String fncValAt) {
        this.fncValAt = fncValAt;
    }

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getUseCodeId() {
        return useCodeId;
    }

    public void setUseCodeId(String useCodeId) {
        this.useCodeId = useCodeId;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }
}
