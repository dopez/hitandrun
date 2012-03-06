package com.bbm.gps.adm.bbs.service;

import java.io.Serializable;
/**
 * 게시판기본정보VO
 * <p><b>NOTE:</b>
 * @author 통계포털 황기연
 * @since 2011.07.04
 * @version 1.0
 * @see
 *
 * <pre>
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.07.04  황기연          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class BbsInfoVO extends BbsSearchVO implements Serializable {
	
	/** 시스템명 **/
	private String sysNm = "";
	
	/** 시스템구분 **/
	private String sysSe = "";

	/** 게시판 이름 **/
	private String bbsNm = "";
	
	/** 게시판 타이틀 **/
	private String bbsTitle = "";
	
	/** 게시판 설명  **/
	private String bbsDc = "";
	
	/** 게시판 관리자 ID **/
	private String bbsMngrId = "";
	
	/** 게시판 관리자 이름 **/
	private String bbsMngrNm = "";
	
	/** 게시판 관리자 암호 **/
	private String bbsMngrPassword = "";
	
	/** 게시판 관리자/대표 이메일 **/
	private String bbsMngrEmail = "";
	
	/** 스킨 정보 **/
	private String skinInfo = "default";
	
	/** 게시판 타이틀 이미지 **/
	private String titleImage = "";
	
	/** 테이블 사이즈  **/
	private int tableSize = 700;
	
	/** NEW 아이콘 이미지 **/
	private String newIconImage = "";
	
	/** NEW 아이콘 표시 기간  **/
	private int newIconIndictPd = 1;
	
	/** COOL 아이콘 이미지 **/
	private String coolIconImage = "";
	
	/** COOL 아이콘 표시 조회수 **/
	private int coolIconIndictRdcnt = 1;
	
	/** HOT 아이콘 이미지  **/
	private String hotIconImage = "";
	
	/** HOT 아이콘 표시 조회수  **/
	private int hotIconIndictRdcnt = 1;
	
	/** 페이지ID **/
	private String subpageId = "";
	
	/** 페이지명 **/
	private String subpageNm = "";
	
	/** 카테고리 코드 **/
	private String ctgryCode = "";
	
	/** 로그인후 사용여부 **/
	private String loginUseAt = "N";
	
	/** 답글 사영여부 **/
	private String answerUseAt = "N";
	
	/** WEBEDITOR 사용여부 **/
	private String webeditorUseAt = "N";
	
	/** 메모 사용여부  **/
	private String memoUseAt = "N";
	
	/** 아바타 사용여부 **/
	private String avataUseAt = "N";
	
	/** 추천 사용여부 **/
	private String recommendUseAt = "N";
	
	/** 제목 꾸밈 사용여부 **/
	private String titleDecoUseAt = "N";
	
	/** 리스트 사용여부 **/
	private String listUseAt = "N";
	
	/** 관련글 사용여부 **/
	private String relateUseAt = "N";
	
	/** 주윗글 사용여부 **/
	private String arndUseAt = "N";
	
	/** 게시기간 사용여부 **/
	private String ntcePdUseAt = "N";
	
	/** 게시대상 사용여부 **/
	private String ntceTrgetUseAt = "N";
	
	/** 암호 사용여부 **/
	private String passwordUseAt = "N";
	
	/** 별칭 사용여부 **/
	private String ncmUseAt = "N";
	
	/** 파일업로드 사용여부 **/
	private String fileUploadUseAt = "N";
	
	/** 앨범기능 여부  **/
	private String albumAt = "N";
	
	/** 앨범형태 구분 **/
	private String albumStleAt = "1";
	
	/** 메일발송 여부 **/
	private String emailSndngAt = "N";
	
	/** 업로드 파일갯수  **/
	private int uploadFileCo = 1;
	
	/** 페이지 리스트 갯수 **/
	private int pgeListCo = 10;
	
	/** 페이지 그룹 갯수 **/
	private int pgeGroupCo = 10;
	
	/** 공지사항사용여부  **/
	private String noticeUseAt = "N";
	
	/** 공지 갯수  **/
	private int noticeCo = 1;
	
	/** 앨범 컬럼수  **/
	private int albumColumnCo = 3;
	
	/** 앨범 줄수  **/
	private int albumLineCo = 3;
	
	/** 목록 보기 권한 **/
	private String listExAuthor = "999";
	
	/** 본물 읽기 권한 **/
	private String bdtRedngAuthor = "999";
	
	/** 본문쓰기 권한 **/
	private String bdtWriteAuthor = "999";
	
	/** 답글쓰기 권한 **/
	private String answerWriteAuthor = "999";
	
	/** 메모쓰기 권한 **/
	private String memoWriteAuthor = "999";
	
	/** 웹에디터 쓰기권한 **/
	private String webeditorWriteAuthor = "999";
	
	/** 공개방법사용여부 **/
	private String othbcMthUseAt = "N";
	
	/** RSS사용여부 **/
	private String rssUseAt = "N";
	
	/** 사용암호 **/
	private String usePassword = "";
	
	/** 게시판수정 탭구분  **/
	private String tabSe = "1";
	
	/** 분류사용여부  **/
	private String ctgryCodeUseAt = "N";
	
	/** 타이틀사용방법 **/
	private String sjUseMth = "T";
	
	/** 게시판타이틀CSS **/
	private String sjChrctrCss = "";
	
	/** 총 게시글수 **/
	private int bbsTotCnt = 0;
	
	/** 타이틀 이미지 마스크 **/
	private String titleImageMask = "";

	/** NEW 아이콘 이미지 마스크 **/
	private String newIconImageMask = "";
	
	/** COOL 아이콘 이미지 마스크 **/
	private String coolIconImageMask = "";
	
	/** HOT 아이콘 이미지 마스크 **/
	private String hotIconImageMask = "";
	
	/** 타이틀이미지확장자 **/
	private String titleImageExt = "";
	
	/** NEW 아이콘 이미지확장자 **/
	private String newIconImageExt = "";
	
	/** COOL 아이콘 이미지확장자 **/
	private String coolIconImageExt = "";
	
	/** HOT 아이콘 이미지확장자 **/
	private String hotIconImageExt = "";
	
	public String getSysSe() {
		return sysSe;
	}

	public void setSysSe(String sysSe) {
		this.sysSe = sysSe;
	}
	
	public String getTitleImageMask() {
		return titleImageMask;
	}

	public void setTitleImageMask(String titleImageMask) {
		this.titleImageMask = titleImageMask;
	}

	public String getNewIconImageMask() {
		return newIconImageMask;
	}

	public void setNewIconImageMask(String newIconImageMask) {
		this.newIconImageMask = newIconImageMask;
	}

	public String getCoolIconImageMask() {
		return coolIconImageMask;
	}

	public void setCoolIconImageMask(String coolIconImageMask) {
		this.coolIconImageMask = coolIconImageMask;
	}

	public String getHotIconImageMask() {
		return hotIconImageMask;
	}

	public void setHotIconImageMask(String hotIconImageMask) {
		this.hotIconImageMask = hotIconImageMask;
	}
	
	public String getTitleImageExt() {
		return titleImageExt;
	}

	public void setTitleImageExt(String titleImageExt) {
		this.titleImageExt = titleImageExt;
	}

	public String getNewIconImageExt() {
		return newIconImageExt;
	}

	public void setNewIconImageExt(String newIconImageExt) {
		this.newIconImageExt = newIconImageExt;
	}

	public String getCoolIconImageExt() {
		return coolIconImageExt;
	}

	public void setCoolIconImageExt(String coolIconImageExt) {
		this.coolIconImageExt = coolIconImageExt;
	}

	public String getHotIconImageExt() {
		return hotIconImageExt;
	}

	public void setHotIconImageExt(String hotIconImageExt) {
		this.hotIconImageExt = hotIconImageExt;
	}
	
	public int getBbsTotCnt() {
		return bbsTotCnt;
	}
	
	public void setBbsTotCnt(int bbsTotCnt) {
		this.bbsTotCnt = bbsTotCnt;
	}
	
	public String getSjChrctrCss() {
		return sjChrctrCss;
	}
	
	public void setSjChrctrCss(String sjChrctrCss) {
		this.sjChrctrCss = sjChrctrCss;
	}
	
	public String getSjUseMth() {
		return sjUseMth;
	}
	
	public void setSjUseMth(String sjUseMth) {
		this.sjUseMth = sjUseMth;
	}
	
	public String getCtgryCodeUseAt() {
		return ctgryCodeUseAt;
	}
	
	public void setCtgryCodeUseAt(String ctgryCodeUseAt) {
		this.ctgryCodeUseAt = ctgryCodeUseAt;
	}
	
	public String getTabSe() {
		return tabSe;
	}
	
	public void setTabSe(String tabSe) {
		this.tabSe = tabSe;
	}
	
	public String getSysNm() {
		return sysNm;
	}
	
	public void setSysNm(String sysNm) {
		this.sysNm = sysNm;
	}
	
	public String getBbsNm() {
		return bbsNm;
	}
	
	public void setBbsNm(String bbsNm) {
		this.bbsNm = bbsNm;
	}
	
	public String getBbsTitle() {
		return bbsTitle;
	}
	
	public void setBbsTitle(String bbsTitle) {
		this.bbsTitle = bbsTitle;
	}
	
	public String getBbsDc() {
		return bbsDc;
	}
	
	public void setBbsDc(String bbsDc) {
		this.bbsDc = bbsDc;
	}
	
	public String getBbsMngrId() {
		return bbsMngrId;
	}
	
	public void setBbsMngrId(String bbsMngrId) {
		this.bbsMngrId = bbsMngrId;
	}
	
	public String getBbsMngrNm() {
		return bbsMngrNm;
	}
	
	public void setBbsMngrNm(String bbsMngrNm) {
		this.bbsMngrNm = bbsMngrNm;
	}
	
	public String getBbsMngrPassword() {
		return bbsMngrPassword;
	}
	
	public void setBbsMngrPassword(String bbsMngrPassword) {
		this.bbsMngrPassword = bbsMngrPassword;
	}
	
	public String getBbsMngrEmail() {
		return bbsMngrEmail;
	}
	
	public void setBbsMngrEmail(String bbsMngrEmail) {
		this.bbsMngrEmail = bbsMngrEmail;
	}
	
	public String getSkinInfo() {
		return skinInfo;
	}
	
	public void setSkinInfo(String skinInfo) {
		this.skinInfo = skinInfo;
	}
	
	public String getTitleImage() {
		return titleImage;
	}
	
	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}
	
	public int getTableSize() {
		return tableSize;
	}
	
	public void setTableSize(int tableSize) {
		this.tableSize = tableSize;
	}
	
	public String getNewIconImage() {
		return newIconImage;
	}
	
	public void setNewIconImage(String newIconImage) {
		this.newIconImage = newIconImage;
	}
	
	public int getNewIconIndictPd() {
		return newIconIndictPd;
	}
	
	public void setNewIconIndictPd(int newIconIndictPd) {
		this.newIconIndictPd = newIconIndictPd;
	}
	
	public String getCoolIconImage() {
		return coolIconImage;
	}
	
	public void setCoolIconImage(String coolIconImage) {
		this.coolIconImage = coolIconImage;
	}
	
	public int getCoolIconIndictRdcnt() {
		return coolIconIndictRdcnt;
	}
	
	public void setCoolIconIndictRdcnt(int coolIconIndictRdcnt) {
		this.coolIconIndictRdcnt = coolIconIndictRdcnt;
	}
	
	public String getHotIconImage() {
		return hotIconImage;
	}
	
	public void setHotIconImage(String hotIconImage) {
		this.hotIconImage = hotIconImage;
	}
	
	public int getHotIconIndictRdcnt() {
		return hotIconIndictRdcnt;
	}
	
	public void setHotIconIndictRdcnt(int hotIconIndictRdcnt) {
		this.hotIconIndictRdcnt = hotIconIndictRdcnt;
	}
	
	public String getSubpageId() {
		return subpageId;
	}
	
	public void setSubpageId(String subpageId) {
		this.subpageId = subpageId;
	}
	
	public String getSubpageNm() {
		return subpageNm;
	}
	
	public void setSubpageNm(String subpageNm) {
		this.subpageNm = subpageNm;
	}
	
	public String getCtgryCode() {
		return ctgryCode;
	}
	
	public void setCtgryCode(String ctgryCode) {
		this.ctgryCode = ctgryCode;
	}
	
	public String getLoginUseAt() {
		return loginUseAt;
	}
	
	public void setLoginUseAt(String loginUseAt) {
		this.loginUseAt = loginUseAt;
	}
	
	public String getAnswerUseAt() {
		return answerUseAt;
	}
	
	public void setAnswerUseAt(String answerUseAt) {
		this.answerUseAt = answerUseAt;
	}
	
	public String getWebeditorUseAt() {
		return webeditorUseAt;
	}
	
	public void setWebeditorUseAt(String webeditorUseAt) {
		this.webeditorUseAt = webeditorUseAt;
	}
	
	public String getMemoUseAt() {
		return memoUseAt;
	}
	
	public void setMemoUseAt(String memoUseAt) {
		this.memoUseAt = memoUseAt;
	}
	
	public String getAvataUseAt() {
		return avataUseAt;
	}
	
	public void setAvataUseAt(String avataUseAt) {
		this.avataUseAt = avataUseAt;
	}
	
	public String getRecommendUseAt() {
		return recommendUseAt;
	}
	
	public void setRecommendUseAt(String recommendUseAt) {
		this.recommendUseAt = recommendUseAt;
	}
	
	public String getTitleDecoUseAt() {
		return titleDecoUseAt;
	}
	
	public void setTitleDecoUseAt(String titleDecoUseAt) {
		this.titleDecoUseAt = titleDecoUseAt;
	}
	
	public String getListUseAt() {
		return listUseAt;
	}
	
	public void setListUseAt(String listUseAt) {
		this.listUseAt = listUseAt;
	}
	
	public String getRelateUseAt() {
		return relateUseAt;
	}
	
	public void setRelateUseAt(String relateUseAt) {
		this.relateUseAt = relateUseAt;
	}
	
	public String getArndUseAt() {
		return arndUseAt;
	}
	
	public void setArndUseAt(String arndUseAt) {
		this.arndUseAt = arndUseAt;
	}
	
	public String getNtcePdUseAt() {
		return ntcePdUseAt;
	}
	
	public void setNtcePdUseAt(String ntcePdUseAt) {
		this.ntcePdUseAt = ntcePdUseAt;
	}
	
	public String getNtceTrgetUseAt() {
		return ntceTrgetUseAt;
	}
	
	public void setNtceTrgetUseAt(String ntceTrgetUseAt) {
		this.ntceTrgetUseAt = ntceTrgetUseAt;
	}
	
	public String getPasswordUseAt() {
		return passwordUseAt;
	}
	
	public void setPasswordUseAt(String passwordUseAt) {
		this.passwordUseAt = passwordUseAt;
	}
	
	public String getNcmUseAt() {
		return ncmUseAt;
	}
	
	public void setNcmUseAt(String ncmUseAt) {
		this.ncmUseAt = ncmUseAt;
	}
	
	public String getFileUploadUseAt() {
		return fileUploadUseAt;
	}
	
	public void setFileUploadUseAt(String fileUploadUseAt) {
		this.fileUploadUseAt = fileUploadUseAt;
	}
	
	public String getAlbumAt() {
		return albumAt;
	}
	
	public void setAlbumAt(String albumAt) {
		this.albumAt = albumAt;
	}
	
	public String getAlbumStleAt() {
		return albumStleAt;
	}
	
	public void setAlbumStleAt(String albumStleAt) {
		this.albumStleAt = albumStleAt;
	}
	
	public String getEmailSndngAt() {
		return emailSndngAt;
	}
	
	public void setEmailSndngAt(String emailSndngAt) {
		this.emailSndngAt = emailSndngAt;
	}
	
	public int getUploadFileCo() {
		return uploadFileCo;
	}
	
	public void setUploadFileCo(int uploadFileCo) {
		this.uploadFileCo = uploadFileCo;
	}
	
	public int getPgeListCo() {
		return pgeListCo;
	}
	
	public void setPgeListCo(int pgeListCo) {
		this.pgeListCo = pgeListCo;
	}
	
	public int getPgeGroupCo() {
		return pgeGroupCo;
	}
	
	public void setPgeGroupCo(int pgeGroupCo) {
		this.pgeGroupCo = pgeGroupCo;
	}
	
	public String getNoticeUseAt() {
		return noticeUseAt;
	}
	
	public void setNoticeUseAt(String noticeUseAt) {
		this.noticeUseAt = noticeUseAt;
	}
	
	public int getNoticeCo() {
		return noticeCo;
	}
	
	public void setNoticeCo(int noticeCo) {
		this.noticeCo = noticeCo;
	}
	
	public int getAlbumColumnCo() {
		return albumColumnCo;
	}
	
	public void setAlbumColumnCo(int albumColumnCo) {
		this.albumColumnCo = albumColumnCo;
	}
	
	public int getAlbumLineCo() {
		return albumLineCo;
	}
	
	public void setAlbumLineCo(int albumLineCo) {
		this.albumLineCo = albumLineCo;
	}
	
	public String getListExAuthor() {
		return listExAuthor;
	}
	
	public void setListExAuthor(String listExAuthor) {
		this.listExAuthor = listExAuthor;
	}
	
	public String getBdtRedngAuthor() {
		return bdtRedngAuthor;
	}
	
	public void setBdtRedngAuthor(String bdtRedngAuthor) {
		this.bdtRedngAuthor = bdtRedngAuthor;
	}
	
	public String getBdtWriteAuthor() {
		return bdtWriteAuthor;
	}
	
	public void setBdtWriteAuthor(String bdtWriteAuthor) {
		this.bdtWriteAuthor = bdtWriteAuthor;
	}
	
	public String getAnswerWriteAuthor() {
		return answerWriteAuthor;
	}
	
	public void setAnswerWriteAuthor(String answerWriteAuthor) {
		this.answerWriteAuthor = answerWriteAuthor;
	}
	
	public String getMemoWriteAuthor() {
		return memoWriteAuthor;
	}
	
	public void setMemoWriteAuthor(String memoWriteAuthor) {
		this.memoWriteAuthor = memoWriteAuthor;
	}
	
	public String getWebeditorWriteAuthor() {
		return webeditorWriteAuthor;
	}
	
	public void setWebeditorWriteAuthor(String webeditorWriteAuthor) {
		this.webeditorWriteAuthor = webeditorWriteAuthor;
	}
	
	public String getOthbcMthUseAt() {
		return othbcMthUseAt;
	}
	
	public void setOthbcMthUseAt(String othbcMthUseAt) {
		this.othbcMthUseAt = othbcMthUseAt;
	}
	
	public String getRssUseAt() {
		return rssUseAt;
	}
	
	public void setRssUseAt(String rssUseAt) {
		this.rssUseAt = rssUseAt;
	}
	
	public String getUsePassword() {
		return usePassword;
	}
	
	public void setUsePassword(String usePassword) {
		this.usePassword = usePassword;
	}
}
