package com.bbm.gps.adm.org.service;

import com.bbm.gps.cmm.service.GpsDefaultVO;

import java.io.Serializable;

/** 
 * 기관관리에 사용되는 value object들을 정의한다. 
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
public class OrgManage extends GpsDefaultVO implements Serializable {

    /** 기관ID */
    private String orgId;

    /** 통계작성여부 */
    private String statMkAt = "N";

    /** 상위기관ID */
    private String upOrgId;

    /** 표준통계작성기관여부 */
    private String stdStatMkAt = "N";

    /** 최초작성일 */
    private String fstRegDe;

    /** 홈페이지주소 */
    private String httpCn;

    /** 최초작성자 */
    private String fstRegNm;

    /** 최종변경일 */
    private String lstChnDe;

    /** 우편코드 */
    private String zipId;

    /** 최종변경자 */
    private String lstChnNm;

    /** 기관명 */
    private String orgNm;

    /** 기관영문명 */
    private String orgNmEng;

    /** 주소 */
    private String addrCn;

    /** 전화번호 */
    private String phonCn;

    /** FAX */
    private String faxPhonCn;

    /** 설립근거유형 */
    private String deptAt = "N";

    /** 종류 */
    private String estbKd;

    /** 기관종류 */
    private String orgKd;

    /** 상세주소 */
    private String dtlAddrCn;


    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getStatMkAt() {
        return statMkAt;
    }

    public void setStatMkAt(String statMkAt) {
        this.statMkAt = statMkAt;
    }

    public String getUpOrgId() {
        return upOrgId;
    }

    public void setUpOrgId(String upOrgId) {
        this.upOrgId = upOrgId;
    }

    public String getStdStatMkAt() {
        return stdStatMkAt;
    }

    public void setStdStatMkAt(String stdStatMkAt) {
        this.stdStatMkAt = stdStatMkAt;
    }

    public String getFstRegDe() {
        return fstRegDe;
    }

    public void setFstRegDe(String fstRegDe) {
        this.fstRegDe = fstRegDe;
    }

    public String getHttpCn() {
        return httpCn;
    }

    public void setHttpCn(String httpCn) {
        this.httpCn = httpCn;
    }

    public String getFstRegNm() {
        return fstRegNm;
    }

    public void setFstRegNm(String fstRegNm) {
        this.fstRegNm = fstRegNm;
    }

    public String getLstChnDe() {
        return lstChnDe;
    }

    public void setLstChnDe(String lstChnDe) {
        this.lstChnDe = lstChnDe;
    }

    public String getZipId() {
        return zipId;
    }

    public void setZipId(String zipId) {
        this.zipId = zipId;
    }

    public String getLstChnNm() {
        return lstChnNm;
    }

    public void setLstChnNm(String lstChnNm) {
        this.lstChnNm = lstChnNm;
    }

    public String getOrgNm() {
        return orgNm;
    }

    public void setOrgNm(String orgNm) {
        this.orgNm = orgNm;
    }

    public String getOrgNmEng() {
        return orgNmEng;
    }

    public void setOrgNmEng(String orgNmEng) {
        this.orgNmEng = orgNmEng;
    }

    public String getAddrCn() {
        return addrCn;
    }

    public void setAddrCn(String addrCn) {
        this.addrCn = addrCn;
    }

    public String getPhonCn() {
        return phonCn;
    }

    public void setPhonCn(String phonCn) {
        this.phonCn = phonCn;
    }

    public String getFaxPhonCn() {
        return faxPhonCn;
    }

    public void setFaxPhonCn(String faxPhonCn) {
        this.faxPhonCn = faxPhonCn;
    }

    public String getDeptAt() {
        return deptAt;
    }

    public void setDeptAt(String deptAt) {
        this.deptAt = deptAt;
    }

    public String getEstbKd() {
        return estbKd;
    }

    public void setEstbKd(String estbKd) {
        this.estbKd = estbKd;
    }

    public String getOrgKd() {
        return orgKd;
    }

    public void setOrgKd(String orgKd) {
        this.orgKd = orgKd;
    }

    public String getDtlAddrCn() {
        return dtlAddrCn;
    }

    public void setDtlAddrCn(String dtlAddrCn) {
        this.dtlAddrCn = dtlAddrCn;
    }

}
