package com.bbm.gps.adm.popup.service;

import com.bbm.gps.cmm.service.GpsDefaultVO;

import java.io.Serializable;


/** 
 * 팝업관리에 사용되는 value object들을 정의한다. 
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
public class PopupManageVO extends GpsDefaultVO implements Serializable {
	/** 일련번호 */
	private int popupSn;
	
    /** 일련번호 리스트 */
	private String popupSnList;
	
	/** 검색조건(팝업 활성화 여부)  */
	public String searchActvtyAt;
	
	/** 제목 */
    private int ordr;
    
    /** 팝업순서 */
    private String sj;
    
    /** HTML 여부 */
    private String htmlAt;
    
    /** 팝업 WIDTH */
    private int width;
    
    /** 팝업 HEIGHT */
    private int height;
    
    /** 팝업창 위치(TOP) */
    private int lcTop;
    
    /** 팝업창 위치(LEFT) */
    private int lcLeft;
    
    /** 팝업창 위치(SCROLL) */
    private int lcScroll;
    
    /** 팝업 활성화 시작일 */
    private String actvtyBgnde;
    
    /** 팝업 활성화 종료일 */
    private String actvtyEndde;
    
    /** 팝업 보이는지 여부 */
    private String actvtyAt;
    
    /** 팝업 쿠키보기 */
    private String cookieSkll;
    
    /** 첨부 파일1 */
    private String atchmnflOne;
    
    /** 첨부 파일 마스크1 */
    private String atchmnflOneMask;
    
    /** 첨부 파일 사이즈1 */
    private int atchmnflOneSize;
    
    /** 첨부파일 마임타임1 */
    private String atchmnflOneMime;
    
    /** 첨부 파일2 */
    private String atchmnflTwo;
    
    /** 첨부 파일2 마스크 */
    private String atchmnflTwoMask;
    
    /** 첨부 파일2 사이즈 */
    private int atchmnflTwoSize;
    
    /** 첨부파일 마임타임2 */
    private String atchmnflTwoMime;
    
    /** 첨부 파일3 */
    private String atchmnflThree;
    
    /** 첨부 파일3 마스크 */
    private String atchmnflThreeMask;
    
    /** 첨부 파일3 사이즈 */
    private int atchmnflThreeSize;
    
    /** 첨부파일 마임타임3 */
    private String atchmnflThreeMime;
    
    /** 팝업 타겟 */
    private String trget;
    
    /** 팝업 URL */
    private String url;
    
    /** 팝업 URL 클릭 시 타겟 */
    private String urlTrget;
    
    /** 최초등록자IP */
    private String registerIp;
    
    /** 최초등록일 */
    private String registDt;
    
    /** 최종수정일 */
    private String updtDt;
    
    /** 최초등록자 */
    private String registerId;
    
    /** 팝업타입 */
    private String popupTy;
    
    /** 최종수정자 */
    private String updtusrId;
    
    /** 내용 */
    private String cn;

    public int getOrdr() {
        return ordr;
    }

    public void setOrdr(int ordr) {
        this.ordr = ordr;
    }

    public String getSj() {
        return sj;
    }

    public void setSj(String sj) {
        this.sj = sj;
    }

    public String getHtmlAt() {
        return htmlAt;
    }

    public void setHtmlAt(String htmlAt) {
        this.htmlAt = htmlAt;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLcTop() {
        return lcTop;
    }

    public void setLcTop(int lcTop) {
        this.lcTop = lcTop;
    }

    public int getLcLeft() {
        return lcLeft;
    }

    public void setLcLeft(int lcLeft) {
        this.lcLeft = lcLeft;
    }

    public int getLcScroll() {
        return lcScroll;
    }

    public void setLcScroll(int lcScroll) {
        this.lcScroll = lcScroll;
    }

    public String getActvtyBgnde() {
        return actvtyBgnde;
    }

    public void setActvtyBgnde(String actvtyBgnde) {
        this.actvtyBgnde = actvtyBgnde;
    }

    public String getActvtyEndde() {
        return actvtyEndde;
    }

    public void setActvtyEndde(String actvtyEndde) {
        this.actvtyEndde = actvtyEndde;
    }

    public String getActvtyAt() {
        return actvtyAt;
    }

    public void setActvtyAt(String actvtyAt) {
        this.actvtyAt = actvtyAt;
    }

    public String getCookieSkll() {
        return cookieSkll;
    }

    public void setCookieSkll(String cookieSkll) {
        this.cookieSkll = cookieSkll;
    }

    public String getAtchmnflOne() {
        return atchmnflOne;
    }

    public void setAtchmnflOne(String atchmnflOne) {
        this.atchmnflOne = atchmnflOne;
    }

    public String getAtchmnflOneMask() {
        return atchmnflOneMask;
    }

    public void setAtchmnflOneMask(String atchmnflOneMask) {
        this.atchmnflOneMask = atchmnflOneMask;
    }

    public int getAtchmnflOneSize() {
        return atchmnflOneSize;
    }

    public void setAtchmnflOneSize(int atchmnflOneSize) {
        this.atchmnflOneSize = atchmnflOneSize;
    }

    public String getAtchmnflOneMime() {
        return atchmnflOneMime;
    }

    public void setAtchmnflOneMime(String atchmnflOneMime) {
        this.atchmnflOneMime = atchmnflOneMime;
    }

    public String getAtchmnflTwo() {
        return atchmnflTwo;
    }

    public void setAtchmnflTwo(String atchmnflTwo) {
        this.atchmnflTwo = atchmnflTwo;
    }

    public String getAtchmnflTwoMask() {
        return atchmnflTwoMask;
    }

    public void setAtchmnflTwoMask(String atchmnflTwoMask) {
        this.atchmnflTwoMask = atchmnflTwoMask;
    }

    public int getAtchmnflTwoSize() {
        return atchmnflTwoSize;
    }

    public void setAtchmnflTwoSize(int atchmnflTwoSize) {
        this.atchmnflTwoSize = atchmnflTwoSize;
    }

    public String getAtchmnflTwoMime() {
        return atchmnflTwoMime;
    }

    public void setAtchmnflTwoMime(String atchmnflTwoMime) {
        this.atchmnflTwoMime = atchmnflTwoMime;
    }

    public String getAtchmnflThree() {
        return atchmnflThree;
    }

    public void setAtchmnflThree(String atchmnflThree) {
        this.atchmnflThree = atchmnflThree;
    }

    public String getAtchmnflThreeMask() {
        return atchmnflThreeMask;
    }

    public void setAtchmnflThreeMask(String atchmnflThreeMask) {
        this.atchmnflThreeMask = atchmnflThreeMask;
    }

    public int getAtchmnflThreeSize() {
        return atchmnflThreeSize;
    }

    public void setAtchmnflThreeSize(int atchmnflThreeSize) {
        this.atchmnflThreeSize = atchmnflThreeSize;
    }

    public String getAtchmnflThreeMime() {
        return atchmnflThreeMime;
    }

    public void setAtchmnflThreeMime(String atchmnflThreeMime) {
        this.atchmnflThreeMime = atchmnflThreeMime;
    }

    public String getTrget() {
        return trget;
    }

    public void setTrget(String trget) {
        this.trget = trget;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlTrget() {
        return urlTrget;
    }

    public void setUrlTrget(String urlTrget) {
        this.urlTrget = urlTrget;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public String getRegistDt() {
        return registDt;
    }

    public void setRegistDt(String registDt) {
        this.registDt = registDt;
    }

    public String getUpdtDt() {
        return updtDt;
    }

    public void setUpdtDt(String updtDt) {
        this.updtDt = updtDt;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public String getPopupTy() {
        return popupTy;
    }

    public void setPopupTy(String popupTy) {
        this.popupTy = popupTy;
    }

    public String getUpdtusrId() {
        return updtusrId;
    }

    public void setUpdtusrId(String updtusrId) {
        this.updtusrId = updtusrId;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public int getPopupSn() {
        return popupSn;
    }

    public void setPopupSn(int popupSn) {
        this.popupSn = popupSn;
    }

    public String getPopupSnList() {
		return popupSnList;
	}

	public void setPopupSnList(String popupSnList) {
		this.popupSnList = popupSnList;
	}    
	
	public String getSearchActvtyAt() {
		return searchActvtyAt;
	}

	public void setSearchActvtyAt(String searchActvtyAt) {
		this.searchActvtyAt = searchActvtyAt;
	}	
	
}