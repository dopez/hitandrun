package com.bbm.gps.adm.banner.service;

import com.bbm.gps.cmm.service.GpsDefaultVO;

import java.io.Serializable;

/** 
 * 배너관리에 사용되는 value object들을 정의한다. 
 * <p><b>NOTE:</b>
 * @author 범정부통계포털 이관형 
 * @since 2011.06.17 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.06.17     이관형      최초 생성 
 * 
 * </pre> 
 */
@SuppressWarnings("serial")
public class BannerManageVO extends GpsDefaultVO implements Serializable {
 
	/** 배너 이미지 삭제 플레그 */
	private boolean bannerImgDelFlg;
	
	/** 배너 위치 리스트 */
	private String lcList = "";

	/** 타켓 위치 리스트 */
	private String targetList = "";
	
	/** 일련번호 리스트 */
	private String bannerSnList;
	
	/** 시스템명 */
	private String sysNm;
	
	/** 일련번호 */
	private String bannerSn;
	
	/** 베너위치 */
	private String lc;
	
	/** 베너순서 */
    private String ordr;

    /** 베너명 */
    private String nm;

    /** 베너 보이는지 여부 */
    private String actvtyAt;

    /** 시작일 */
    private String bgnde;

    /** 종료일 */
    private String endde;

    /** 로고 이미지 파일명 */
    private String logoImageFileNm;

    /** 로고 이미지 파일 마스크 */
    private String logoImageFileMask;

    /** 로고 이미지 파일 사이즈 */
    private String logoImageFileSize;

    /** 로고 이미지 파일 MIME */
    private String logoImageFileMime;

    /** 로고 이미지 넓이 */
    private String logoImageWidth;

    /** 로고 이미지 높이 */
    private String logoImageHeight;

    /** 오버 이미지 파일명 */
    private String overImageFileNm;

    /** 오버 이미지 파일 마스크 */
    private String overImageFileMask;

    /** 오버 이미지 파일 사이즈 */
    private String overImageFileSize;

    /** 오버 이미지 넓이 */
    private String overImageWidth;

    /** 오버 이미지 높이 */
    private String overImageHeight;

    /** 오버 이미지 파일 MIME */
    private String overImageFileMime;

    /** 이미지 설명 */
    private String imageAlt;

    /** 메뉴 URL */
    private String url;

    /** 메뉴 URL 클릭 시 타겟 */
    private String urlTrget;

    /** 베너설명 */
    private String dc;

    /** 베너 타겟 */
    private String trget;

    /** 최초등록자 */
    private String registerId;
		
    /** 최초등록자IP */
	private String registerIp;

	/** 최초등록일 */
    private String registDt;

    /** 최종수정일 */
    private String updtDt;

    /** 최종수정자 */
    private String updtusrId;

    /** 시스템 검색 조건*/
    private String sysIdSearch;
    
    /** 사용여부 검색 조건*/
    private String actvtyAtSearch;
    
	public void setBannerImgDelFlg(boolean bannerImgDelFlg) {
		this.bannerImgDelFlg = bannerImgDelFlg;
	}

	public String getLcList() {
		return lcList;
	}

	public void setLcList(String lcList) {
		this.lcList = lcList;
	}

	public String getTargetList() {
		return targetList;
	}

	public void setTargetList(String targetList) {
		this.targetList = targetList;
	}
	
    public String getBannerSnList() {
		return bannerSnList;
	}

	public void setBannerSnList(String bannerSnList) {
		this.bannerSnList = bannerSnList;
	}

	public String getLc() {
		return lc;
	}

	public void setLc(String lc) {
		this.lc = lc;
	}

	public String getOrdr() {
		return ordr;
	}

	public void setOrdr(String ordr) {
		this.ordr = ordr;
	}

	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	public String getActvtyAt() {
		return actvtyAt;
	}

	public void setActvtyAt(String actvtyAt) {
		this.actvtyAt = actvtyAt;
	}

	public String getBgnde() {
		return bgnde;
	}

	public void setBgnde(String bgnde) {
		this.bgnde = bgnde;
	}

	public String getEndde() {
		return endde;
	}

	public void setEndde(String endde) {
		this.endde = endde;
	}

	public String getLogoImageFileNm() {
		return logoImageFileNm;
	}

	public void setLogoImageFileNm(String logoImageFileNm) {
		this.logoImageFileNm = logoImageFileNm;
	}

	public String getLogoImageFileMask() {
		return logoImageFileMask;
	}

	public void setLogoImageFileMask(String logoImageFileMask) {
		this.logoImageFileMask = logoImageFileMask;
	}

	public String getLogoImageFileSize() {
		return logoImageFileSize;
	}

	public void setLogoImageFileSize(String logoImageFileSize) {
		this.logoImageFileSize = logoImageFileSize;
	}

	public String getLogoImageFileMime() {
		return logoImageFileMime;
	}

	public void setLogoImageFileMime(String logoImageFileMime) {
		this.logoImageFileMime = logoImageFileMime;
	}

	public String getLogoImageWidth() {
		return logoImageWidth;
	}

	public void setLogoImageWidth(String logoImageWidth) {
		this.logoImageWidth = logoImageWidth;
	}

	public String getLogoImageHeight() {
		return logoImageHeight;
	}

	public void setLogoImageHeight(String logoImageHeight) {
		this.logoImageHeight = logoImageHeight;
	}

	public String getOverImageFileNm() {
		return overImageFileNm;
	}

	public void setOverImageFileNm(String overImageFileNm) {
		this.overImageFileNm = overImageFileNm;
	}

	public String getOverImageFileMask() {
		return overImageFileMask;
	}

	public void setOverImageFileMask(String overImageFileMask) {
		this.overImageFileMask = overImageFileMask;
	}

	public String getOverImageFileSize() {
		return overImageFileSize;
	}

	public void setOverImageFileSize(String overImageFileSize) {
		this.overImageFileSize = overImageFileSize;
	}

	public String getOverImageWidth() {
		return overImageWidth;
	}

	public void setOverImageWidth(String overImageWidth) {
		this.overImageWidth = overImageWidth;
	}

	public String getOverImageHeight() {
		return overImageHeight;
	}

	public void setOverImageHeight(String overImageHeight) {
		this.overImageHeight = overImageHeight;
	}

	public String getOverImageFileMime() {
		return overImageFileMime;
	}

	public void setOverImageFileMime(String overImageFileMime) {
		this.overImageFileMime = overImageFileMime;
	}

	public String getImageAlt() {
		return imageAlt;
	}

	public void setImageAlt(String imageAlt) {
		this.imageAlt = imageAlt;
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

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}

	public String getTrget() {
		return trget;
	}

	public void setTrget(String trget) {
		this.trget = trget;
	}

	public String getRegisterId() {
		return registerId;
	}

	public void setRegisterId(String registerId) {
		this.registerId = registerId;
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

	public String getUpdtusrId() {
		return updtusrId;
	}

	public void setUpdtusrId(String updtusrId) {
		this.updtusrId = updtusrId;
	}

	public boolean isBannerImgDelFlg() {
		return bannerImgDelFlg;
	}

	public String getBannerSn() {
		return bannerSn;
	}

	public void setBannerSn(String bannerSn) {
		this.bannerSn = bannerSn;
	}

	public String getSysIdSearch() {
		return sysIdSearch;
	}

	public void setSysIdSearch(String sysIdSearch) {
		this.sysIdSearch = sysIdSearch;
	}

	public String getActvtyAtSearch() {
		return actvtyAtSearch;
	}

	public void setActvtyAtSearch(String actvtyAtSearch) {
		this.actvtyAtSearch = actvtyAtSearch;
	}

	public String getSysNm() {
		return sysNm;
	}

	public void setSysNm(String sysNm) {
		this.sysNm = sysNm;
	}

}