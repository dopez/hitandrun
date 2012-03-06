package com.bbm.gps.adm.menu.service;

import java.io.Serializable;

/**
 * 메뉴관리 메뉴관련 value object 들을 정의
 * <p><b>NOTE:</b>
 * @author 이관/포털 황기연
 * @since 2011.06.07
 * @version 1.0
 * @see
 *
 * <pre>
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.06.07  황기연          최초 생성
 *
 * </pre>
 */

@SuppressWarnings("serial")
public class MenuManageVO extends MenuDefaultVO implements Serializable{

	/** target MenuID **/
	private String targetMenuId = "";
	
	/** 메뉴 이미지 플래그 **/
	private boolean menuImgFlg = true;
	
	/** 상위시스템ID **/
	private String upperSysId = "";

	/** 상위메뉴ID **/
	private String upperMenuId = "";
	
	/** 메뉴 레벨 **/
	private int menuLv = 0;
	
	/** 메뉴 번호 **/
	private int menuNo = 0;
	
	/** 이전 메뉴 ID **/
	private String preMenuId = "";

	/** 이전 메뉴 번호 **/
	private int preMenuNo = 0;

	/** 메뉴상위번호 **/
	private int upperNo = 0;
	
	/** 메뉴소속코드 **/
	private String psitnCode = "";
	
	/** 메뉴코드 **/
	private String menuCode = "";
	
	/** 메뉴순서 **/
	private int menuOrdr = 0;
	
	/** 시스템사용타입 **/
	private String sysUseTy = "";
	
	/** 메뉴명칭 **/
	private String menuNm = "";
	
	/** 메뉴약칭 **/
	private String menuAbrv = "";
	
	/** 메뉴영문명 **/
	private String menuEngNm = "";
	
	/** 메뉴타입 **/
	private String menuTy = "F";
	
	/** 메뉴구분코드 **/
	private String menuSeCode = "";
	
	/** 메뉴에적용되는스킨 **/
	private String menuSkin = "";
	
	/** 메뉴에적용되는스킨버튼 **/
	private String menuSkinButton = "";
	
	/** 메뉴URL **/
	private String menuUrl = "";
	
	/** 메뉴URL 클릭시 타겟 **/
	private String menuUrlTarget = "";
	
	/** 탑 메뉴 사용여부 **/
	private String topMenuUseAt = "Y";
	
	/** 왼쪽 메뉴 사용여부 **/
	private String leftMenuUseAt = "Y";
	
	/** 하단 메뉴 사용여부 **/
	private String bottomMenuUseAt = "Y";
	
	/** 사이트맵 사용여부 **/
	private String sitemapUseAt = "Y";
	
	/** 메뉴 상단 이미지이름 **/
	private String topImageNm = "";
	
	/** 메뉴 상단 이미지마스크 **/
	private String topImageMask = "";
	
	/** 메뉴 상단 이미지주석 **/
	private String topImageCm = "";
	
	/** 메뉴 상단 이미지마임 **/
	private String topImageMime = "";
	
	/** 메뉴 상단 이미지크기 **/
	private int topImageSize = 0;
	
	/** 메뉴 상단 마우스오버 이미지 이름 **/
	private String topImageMouseoverNm = "";
	
	/** 메뉴 상단마우스오버 이미지 마스크 **/
	private String topImageMouseoverMask = "";
	
	/** 메뉴 상단마우스오버 이미지 주석 **/
	private String topImageMouseoverCm = "";
	
	/** 메뉴 상단마우스오버 이미지 마임 **/
	private String topImageMouseoverMime = "";
	
	/** 메뉴 상단마우스오버 이미지 크기 **/
	private int topImageMouseoverSize = 0;
	
	/** 메뉴 왼쪽 이미지 이름 **/
	private String leftImageNm = "";
	
	/** 메뉴 왼쪽 이미지 마스크 **/
	private String leftImageMask = "";
	
	/** 메뉴 왼쪽 이미지 주석 **/
	private String leftImageCm = "";
	
	/** 메뉴 왼쪽 이미지 마임 **/
	private String leftImageMime = "";
	
	/** 메뉴 왼쪽 이미지 사이즈 **/
	private int leftImageSize = 0;
	
	/** 메뉴 왼쪽 마우스오버 이미지 이름 **/
	private String leftImageMouseoverNm = "";
	
	/** 메뉴 왼쪽 마우스오버 이미지 마스크 **/
	private String leftImageMouseoverMask = "";
	
	/** 메뉴 왼쪽 마우스오버 이미지 주석 **/
	private String leftImageMouseoverCm = "";
	
	/** 메뉴 왼쪽 마우스오버 이미지 마임 **/
	private String leftImageMouseoverMime = "";
	
	/** 메뉴 왼쪽 마우스오버 이미지 사이즈 **/
	private int leftImageMouseoverSize = 0;
	
	/** 메뉴 하단 이미지 이름 **/
	private String bottomImageNm = "";
	
	/** 메뉴 하단 이미지 마스크 **/
	private String bottomImageMask = "";
	
	/** 메뉴 하단 이미지 주석 **/
	private String bottomImageCm = "";
	
	/** 메뉴 하단 이미지 마임 **/
	private String bottomImageMime = "";
	
	/** 메뉴 하단 이미지 사이즈 **/
	private int bottomImageSize = 0;
	
	/** 메뉴 하단 마우스오버 이미지 이름 **/
	private String bottomImageMouseoverNm = "";
	
	/** 메뉴 하단 마우스오버 이미지 마스크 **/
	private String bottomImageMouseoverMask = "";
	
	/** 메뉴 하단 마우스오버 이미지 주석 **/
	private String bottomImageMouseoverCm = "";
	
	/** 메뉴 하단 마우스오버 이미지 마임 **/
	private String bottomImageMouseoverMime = "";
	
	/** 메뉴 하단 마우스오버 이미지 사이즈 **/
	private int bottomImageMouseoverSize = 0;
	
	/** 사이트맵 이미지 이름 **/
	private String sitemapImageNm = "";
	
	/** 사이트맵 이미지 마스크 **/
	private String sitemapImageMask = "";
	
	/** 사이트맵 이미지 주석 **/
	private String sitemapImageCm = "";
	
	/** 사이트맵 이미지 마임 **/
	private String sitemapImageMime = "";
	
	/** 사이트맵 이미지 사이즈 **/
	private int sitemapImageSize = 0;
	
	/** 사이트맵 마우스오버 이미지 이름 **/
	private String sitemapImageMouseoverNm = "";
	
	/** 사이트맵 마우스오버 이미지 마스크 **/
	private String sitemapImageMouseoverMask = "";
	
	/** 사이트맵 마우스오버 이미지 주석 **/
	private String sitemapImageMouseoverCm = "";
	
	/** 사이트맵 마우스오버 이미지 마임 **/
	private String sitemapImageMouseoverMime = "";
	
	/** 사이트맵 마우스오버 이미지 사이즈 **/
	private int sitemapImageMouseoverSize = 0;
	
	/** 타이틀 이미지 이름 **/
	private String titleImageNm = "";

	/** 타이틀 이미지 마스크 **/
	private String titleImageMask = "";
	
	/** 타이틀 이미지 마임 **/
	private String titleImageMime = "";
	
	/** 타이틀 이미지 사이즈 **/
	private int titleImageSize = 0;
	
	/** 비고 **/
	private String menuRm = "";
	
	/** 프로그램파일명 **/
	private String programId = "";
	
	/** 만족도조사 사용여부 **/
	private String snstUseAt = "N";

	/** 첨부파일ID **/
	private String attachmentFileId = "";
	
	/** 게시판ID **/
	private String bbsId = "";
	
	/** 게시판명 **/
	private String bbsNm = "";

	/** 팝업넓이 **/
	private int popupWidth = 0;
	
	/** 팝업높이 **/
	private int popupHeight = 0;
	
	/** 팝업위쪽위치 **/
	private int popupTop = 0;
	
	/** 팝업왼쪽위치 **/
	private int popupLeft = 0;

	/** 마지막 depth 여부 **/
	private int leaf = 0;
	
	/** <ul> open 여부 **/
	private int ulOpenAt = 0;
	
	/** </ul></li> 갯수 **/
	private int endTagCnt = 0;
	
	/** 메뉴 이미지파일 삭제시 구분**/
	private int deleteImgSe = 0;
	
	/** 서브페이지 ID **/
	private String subPageId = "";
	
	/** 메뉴권한여부 **/
	private String menuAuthorAt = "";
	
	/** 메뉴NO목록 **/
	private String menuNoList = "";
	
	/** 권한메뉴여부 **/
	private String authorMenuAt = "";
	
	private String csnstId = "0";

	/** 다음메뉴레벨 **/
	private int nextMenuLv = 0;
	
	/** 온라인도움말사용여부 **/
	private String helpDocAt = "";
	
	/** 권한명 **/
	private String authorNm = "";
	
	public String getAuthorNm() {
		return authorNm;
	}
	
	public void setAuthorNm(String authorNm) {
		this.authorNm = authorNm;
	}
	
	public String getHelpDocAt() {
		return helpDocAt;
	}

	public void setHelpDocAt(String helpDocAt) {
		this.helpDocAt = helpDocAt;
	}

	public int getNextMenuLv() {
		return nextMenuLv;
	}

	public void setNextMenuLv(int nextMenuLv) {
		this.nextMenuLv = nextMenuLv;
	}
	
	public boolean isMenuImgFlg() {
		return menuImgFlg;
	}

	public void setMenuImgFlg(boolean menuImgFlg) {
		this.menuImgFlg = menuImgFlg;
	}

	public String getMenuAuthorAt() {
		return menuAuthorAt;
	}

	public void setMenuAuthorAt(String menuAuthorAt) {
		this.menuAuthorAt = menuAuthorAt;
	}

	public String getSubPageId() {
		return subPageId;
	}

	public void setSubPageId(String subPageId) {
		this.subPageId = subPageId;
	}

	public String getUpperSysId() {
		return upperSysId;
	}

	public void setUpperSysId(String upperSysId) {
		this.upperSysId = upperSysId;
	}

	public String getUpperMenuId() {
		return upperMenuId;
	}

	public void setUpperMenuId(String upperMenuId) {
		this.upperMenuId = upperMenuId;
	}

	public int getMenuLv() {
		return menuLv;
	}

	public void setMenuLv(int menuLv) {
		this.menuLv = menuLv;
	}

	public int getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}

	public String getPreMenuId() {
		return preMenuId;
	}

	public void setPreMenuId(String preMenuId) {
		this.preMenuId = preMenuId;
	}

	public int getPreMenuNo() {
		return preMenuNo;
	}

	public void setPreMenuNo(int preMenuNo) {
		this.preMenuNo = preMenuNo;
	}

	public int getUpperNo() {
		return upperNo;
	}

	public void setUpperNo(int upperNo) {
		this.upperNo = upperNo;
	}

	public String getPsitnCode() {
		return psitnCode;
	}

	public void setPsitnCode(String psitnCode) {
		this.psitnCode = psitnCode;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public int getMenuOrdr() {
		return menuOrdr;
	}

	public void setMenuOrdr(int menuOrdr) {
		this.menuOrdr = menuOrdr;
	}

	public String getSysUseTy() {
		return sysUseTy;
	}

	public void setSysUseTy(String sysUseTy) {
		this.sysUseTy = sysUseTy;
	}

	public String getMenuNm() {
		return menuNm;
	}

	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}

	public String getMenuAbrv() {
		return menuAbrv;
	}

	public void setMenuAbrv(String menuAbrv) {
		this.menuAbrv = menuAbrv;
	}

	public String getMenuEngNm() {
		return menuEngNm;
	}

	public void setMenuEngNm(String menuEngNm) {
		this.menuEngNm = menuEngNm;
	}

	public String getMenuTy() {
		return menuTy;
	}

	public void setMenuTy(String menuTy) {
		this.menuTy = menuTy;
	}

	public String getMenuSeCode() {
		return menuSeCode;
	}

	public void setMenuSeCode(String menuSeCode) {
		this.menuSeCode = menuSeCode;
	}

	public String getMenuSkin() {
		return menuSkin;
	}

	public void setMenuSkin(String menuSkin) {
		this.menuSkin = menuSkin;
	}

	public String getMenuSkinButton() {
		return menuSkinButton;
	}

	public void setMenuSkinButton(String menuSkinButton) {
		this.menuSkinButton = menuSkinButton;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuUrlTarget() {
		return menuUrlTarget;
	}

	public void setMenuUrlTarget(String menuUrlTarget) {
		this.menuUrlTarget = menuUrlTarget;
	}

	public String getTopMenuUseAt() {
		return topMenuUseAt;
	}

	public void setTopMenuUseAt(String topMenuUseAt) {
		this.topMenuUseAt = topMenuUseAt;
	}

	public String getLeftMenuUseAt() {
		return leftMenuUseAt;
	}

	public void setLeftMenuUseAt(String leftMenuUseAt) {
		this.leftMenuUseAt = leftMenuUseAt;
	}

	public String getBottomMenuUseAt() {
		return bottomMenuUseAt;
	}

	public void setBottomMenuUseAt(String bottomMenuUseAt) {
		this.bottomMenuUseAt = bottomMenuUseAt;
	}

	public String getSitemapUseAt() {
		return sitemapUseAt;
	}

	public void setSitemapUseAt(String sitemapUseAt) {
		this.sitemapUseAt = sitemapUseAt;
	}

	public String getTopImageNm() {
		return topImageNm;
	}

	public void setTopImageNm(String topImageNm) {
		this.topImageNm = topImageNm;
	}

	public String getTopImageMask() {
		return topImageMask;
	}

	public void setTopImageMask(String topImageMask) {
		this.topImageMask = topImageMask;
	}

	public String getTopImageCm() {
		return topImageCm;
	}

	public void setTopImageCm(String topImageCm) {
		this.topImageCm = topImageCm;
	}

	public String getTopImageMime() {
		return topImageMime;
	}

	public void setTopImageMime(String topImageMime) {
		this.topImageMime = topImageMime;
	}

	public int getTopImageSize() {
		return topImageSize;
	}

	public void setTopImageSize(int topImageSize) {
		this.topImageSize = topImageSize;
	}

	public String getTopImageMouseoverNm() {
		return topImageMouseoverNm;
	}

	public void setTopImageMouseoverNm(String topImageMouseoverNm) {
		this.topImageMouseoverNm = topImageMouseoverNm;
	}

	public String getTopImageMouseoverMask() {
		return topImageMouseoverMask;
	}

	public void setTopImageMouseoverMask(String topImageMouseoverMask) {
		this.topImageMouseoverMask = topImageMouseoverMask;
	}

	public String getTopImageMouseoverCm() {
		return topImageMouseoverCm;
	}

	public void setTopImageMouseoverCm(String topImageMouseoverCm) {
		this.topImageMouseoverCm = topImageMouseoverCm;
	}

	public String getTopImageMouseoverMime() {
		return topImageMouseoverMime;
	}

	public void setTopImageMouseoverMime(String topImageMouseoverMime) {
		this.topImageMouseoverMime = topImageMouseoverMime;
	}

	public int getTopImageMouseoverSize() {
		return topImageMouseoverSize;
	}

	public void setTopImageMouseoverSize(int topImageMouseoverSize) {
		this.topImageMouseoverSize = topImageMouseoverSize;
	}

	public String getLeftImageNm() {
		return leftImageNm;
	}

	public void setLeftImageNm(String leftImageNm) {
		this.leftImageNm = leftImageNm;
	}

	public String getLeftImageMask() {
		return leftImageMask;
	}

	public void setLeftImageMask(String leftImageMask) {
		this.leftImageMask = leftImageMask;
	}

	public String getLeftImageCm() {
		return leftImageCm;
	}

	public void setLeftImageCm(String leftImageCm) {
		this.leftImageCm = leftImageCm;
	}

	public String getLeftImageMime() {
		return leftImageMime;
	}

	public void setLeftImageMime(String leftImageMime) {
		this.leftImageMime = leftImageMime;
	}

	public int getLeftImageSize() {
		return leftImageSize;
	}

	public void setLeftImageSize(int leftImageSize) {
		this.leftImageSize = leftImageSize;
	}

	public String getLeftImageMouseoverNm() {
		return leftImageMouseoverNm;
	}

	public void setLeftImageMouseoverNm(String leftImageMouseoverNm) {
		this.leftImageMouseoverNm = leftImageMouseoverNm;
	}

	public String getLeftImageMouseoverMask() {
		return leftImageMouseoverMask;
	}

	public void setLeftImageMouseoverMask(String leftImageMouseoverMask) {
		this.leftImageMouseoverMask = leftImageMouseoverMask;
	}

	public String getLeftImageMouseoverCm() {
		return leftImageMouseoverCm;
	}

	public void setLeftImageMouseoverCm(String leftImageMouseoverCm) {
		this.leftImageMouseoverCm = leftImageMouseoverCm;
	}

	public String getLeftImageMouseoverMime() {
		return leftImageMouseoverMime;
	}

	public void setLeftImageMouseoverMime(String leftImageMouseoverMime) {
		this.leftImageMouseoverMime = leftImageMouseoverMime;
	}

	public int getLeftImageMouseoverSize() {
		return leftImageMouseoverSize;
	}

	public void setLeftImageMouseoverSize(int leftImageMouseoverSize) {
		this.leftImageMouseoverSize = leftImageMouseoverSize;
	}

	public String getBottomImageNm() {
		return bottomImageNm;
	}

	public void setBottomImageNm(String bottomImageNm) {
		this.bottomImageNm = bottomImageNm;
	}

	public String getBottomImageMask() {
		return bottomImageMask;
	}

	public void setBottomImageMask(String bottomImageMask) {
		this.bottomImageMask = bottomImageMask;
	}

	public String getBottomImageCm() {
		return bottomImageCm;
	}

	public void setBottomImageCm(String bottomImageCm) {
		this.bottomImageCm = bottomImageCm;
	}

	public String getBottomImageMime() {
		return bottomImageMime;
	}

	public void setBottomImageMime(String bottomImageMime) {
		this.bottomImageMime = bottomImageMime;
	}

	public int getBottomImageSize() {
		return bottomImageSize;
	}

	public void setBottomImageSize(int bottomImageSize) {
		this.bottomImageSize = bottomImageSize;
	}

	public String getBottomImageMouseoverNm() {
		return bottomImageMouseoverNm;
	}

	public void setBottomImageMouseoverNm(String bottomImageMouseoverNm) {
		this.bottomImageMouseoverNm = bottomImageMouseoverNm;
	}

	public String getBottomImageMouseoverMask() {
		return bottomImageMouseoverMask;
	}

	public void setBottomImageMouseoverMask(String bottomImageMouseoverMask) {
		this.bottomImageMouseoverMask = bottomImageMouseoverMask;
	}

	public String getBottomImageMouseoverCm() {
		return bottomImageMouseoverCm;
	}

	public void setBottomImageMouseoverCm(String bottomImageMouseoverCm) {
		this.bottomImageMouseoverCm = bottomImageMouseoverCm;
	}

	public String getBottomImageMouseoverMime() {
		return bottomImageMouseoverMime;
	}

	public void setBottomImageMouseoverMime(String bottomImageMouseoverMime) {
		this.bottomImageMouseoverMime = bottomImageMouseoverMime;
	}

	public int getBottomImageMouseoverSize() {
		return bottomImageMouseoverSize;
	}

	public void setBottomImageMouseoverSize(int bottomImageMouseoverSize) {
		this.bottomImageMouseoverSize = bottomImageMouseoverSize;
	}

	public String getSitemapImageNm() {
		return sitemapImageNm;
	}

	public void setSitemapImageNm(String sitemapImageNm) {
		this.sitemapImageNm = sitemapImageNm;
	}

	public String getSitemapImageMask() {
		return sitemapImageMask;
	}

	public void setSitemapImageMask(String sitemapImageMask) {
		this.sitemapImageMask = sitemapImageMask;
	}

	public String getSitemapImageCm() {
		return sitemapImageCm;
	}

	public void setSitemapImageCm(String sitemapImageCm) {
		this.sitemapImageCm = sitemapImageCm;
	}

	public String getSitemapImageMime() {
		return sitemapImageMime;
	}

	public void setSitemapImageMime(String sitemapImageMime) {
		this.sitemapImageMime = sitemapImageMime;
	}

	public int getSitemapImageSize() {
		return sitemapImageSize;
	}

	public void setSitemapImageSize(int sitemapImageSize) {
		this.sitemapImageSize = sitemapImageSize;
	}

	public String getSitemapImageMouseoverNm() {
		return sitemapImageMouseoverNm;
	}

	public void setSitemapImageMouseoverNm(String sitemapImageMouseoverNm) {
		this.sitemapImageMouseoverNm = sitemapImageMouseoverNm;
	}

	public String getSitemapImageMouseoverMask() {
		return sitemapImageMouseoverMask;
	}

	public void setSitemapImageMouseoverMask(String sitemapImageMouseoverMask) {
		this.sitemapImageMouseoverMask = sitemapImageMouseoverMask;
	}

	public String getSitemapImageMouseoverCm() {
		return sitemapImageMouseoverCm;
	}

	public void setSitemapImageMouseoverCm(String sitemapImageMouseoverCm) {
		this.sitemapImageMouseoverCm = sitemapImageMouseoverCm;
	}

	public String getSitemapImageMouseoverMime() {
		return sitemapImageMouseoverMime;
	}

	public void setSitemapImageMouseoverMime(String sitemapImageMouseoverMime) {
		this.sitemapImageMouseoverMime = sitemapImageMouseoverMime;
	}

	public int getSitemapImageMouseoverSize() {
		return sitemapImageMouseoverSize;
	}

	public void setSitemapImageMouseoverSize(int sitemapImageMouseoverSize) {
		this.sitemapImageMouseoverSize = sitemapImageMouseoverSize;
	}
	
	public String getTitleImageNm() {
		return titleImageNm;
	}

	public void setTitleImageNm(String titleImageNm) {
		this.titleImageNm = titleImageNm;
	}

	public String getTitleImageMask() {
		return titleImageMask;
	}

	public void setTitleImageMask(String titleImageMask) {
		this.titleImageMask = titleImageMask;
	}

	public String getTitleImageMime() {
		return titleImageMime;
	}

	public void setTitleImageMime(String titleImageMime) {
		this.titleImageMime = titleImageMime;
	}

	public int getTitleImageSize() {
		return titleImageSize;
	}

	public void setTitleImageSize(int titleImageSize) {
		this.titleImageSize = titleImageSize;
	}

	public String getMenuRm() {
		return menuRm;
	}

	public void setMenuRm(String menuRm) {
		this.menuRm = menuRm;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public String getSnstUseAt() {
		return snstUseAt;
	}

	public void setSnstUseAt(String snstUseAt) {
		this.snstUseAt = snstUseAt;
	}

	public String getAttachmentFileId() {
		return attachmentFileId;
	}

	public void setAttachmentFileId(String attachmentFileId) {
		this.attachmentFileId = attachmentFileId;
	}

	public String getBbsId() {
		return bbsId;
	}

	public void setBbsId(String bbsId) {
		this.bbsId = bbsId;
	}
	
	public String getBbsNm() {
		return bbsNm;
	}

	public void setBbsNm(String bbsNm) {
		this.bbsNm = bbsNm;
	}

	public int getPopupWidth() {
		return popupWidth;
	}

	public void setPopupWidth(int popupWidth) {
		this.popupWidth = popupWidth;
	}

	public int getPopupHeight() {
		return popupHeight;
	}

	public void setPopupHeight(int popupHeight) {
		this.popupHeight = popupHeight;
	}

	public int getPopupTop() {
		return popupTop;
	}

	public void setPopupTop(int popupTop) {
		this.popupTop = popupTop;
	}

	public int getPopupLeft() {
		return popupLeft;
	}

	public void setPopupLeft(int popupLeft) {
		this.popupLeft = popupLeft;
	}

	public int getLeaf() {
		return leaf;
	}

	public void setLeaf(int leaf) {
		this.leaf = leaf;
	}

	public int getUlOpenAt() {
		return ulOpenAt;
	}

	public void setUlOpenAt(int ulOpenAt) {
		this.ulOpenAt = ulOpenAt;
	}

	public int getEndTagCnt() {
		return endTagCnt;
	}

	public void setEndTagCnt(int endTagCnt) {
		this.endTagCnt = endTagCnt;
	}

	public int getDeleteImgSe() {
		return deleteImgSe;
	}

	public void setDeleteImgSe(int deleteImgSe) {
		this.deleteImgSe = deleteImgSe;
	}
	
	public String getMenuNoList() {
		return menuNoList;
	}

	public void setMenuNoList(String menuNoList) {
		this.menuNoList = menuNoList;
	}
	
	public String getAuthorMenuAt() {
		return authorMenuAt;
	}

	public void setAuthorMenuAt(String authorMenuAt) {
		this.authorMenuAt = authorMenuAt;
	}

	public String getTargetMenuId() {
		return targetMenuId;
	}

	public void setTargetMenuId(String targetMenuId) {
		this.targetMenuId = targetMenuId;
	}

	public String getCsnstId() {
		return csnstId;
	}

	public void setCsnstId(String csnstId) {
		this.csnstId = csnstId;
	}
	
	
}
