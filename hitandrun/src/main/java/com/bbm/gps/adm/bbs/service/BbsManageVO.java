package com.bbm.gps.adm.bbs.service;

import com.bbm.gps.cmm.service.GpsDefaultVO;

import java.io.Serializable;

/**
 * 게시판관리 모델 클래스
 * @author 포탈통계 이관형
 * @since 2011.06.21
 * @version 1.0
 * @see
 *
 * <pre>
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.06.21  이관형          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class BbsManageVO extends GpsDefaultVO implements Serializable {
	/** 게시판 DB 접두어 **/
	private String  dbTname = "";
	/** 게시판ID **/
	private String  bbsId = "";
	/** 시스템ID **/
	private String  sysId = "";
	/** 게시판 이름 **/
	private String  bbsNm = "";
	/** 게시판 타이틀 **/
	private String  bbsTitle = "";
	/** 게시판 설명  **/
	private String  bbsDc = "";
	/** 게시판 관리자 ID **/
	private String  bbsMngrId = "";
	/** 게시판 관리자 이름 **/
	private String  bbsMngrNm = "";
	/** 게시판 관리자 암호 **/
	private String  bbsMngrPassword = "";
	/** 게시판 관리자/대표 이메일 **/
	private String  bbsMngrEmail = "";
	/** 스킨 정보 **/
	private String  skinInfo = "";
	/** 게시판 타이틀 이미지 **/
	private String  titleImage = "";
	/** 테이블 사이즈  **/
	private int  tableSize = 700;
	/** NEW 아이콘 이미지 **/
	private String  newIconImage = "";
	/** NEW 아이콘 표시 기간  **/
	private int  newIconIndictPd = 0;
	/** COOL 아이콘 이미지 **/
	private String  coolIconImage = "";
	/** COOL 아이콘 표시 조회수 **/
	private int  coolIconIndictRdcnt = 0;
	/** HOT 아이콘 이미지  **/
	private String  hotIconImage = "";
	/** HOT 아이콘 표시 조회수  **/
	private int  hotIconIndictRdcnt = 0;
	/** 페이지ID **/
	private String  subpageId = "";
	/** 카테고리 코드 **/
	private String  ctgryCode = "";
	/** 로그인후 사용여부 **/
	private String  loginUseAt = "Y";
	/** 답글 사영여부 **/
	private String  answerUseAt = "N";
	/** WEBEDITOR 사용여부 **/
	private String  webeditorUseAt = "N";
	/** 메모 사용여부  **/
	private String  memoUseAt = "N";
	/** 아바타 사용여부 **/
	private String  avataUseAt = "N";
	/** 추천 사용여부 **/
	private String  recommendUseAt = "N";
	/** 제목 꾸밈 사용여부 **/
	private String  titleDecoUseAt = "N";
	/** 리스트 사용여부 **/
	private String  listUseAt = "N";
	/** 관련글 사용여부 **/
	private String  relateUseAt = "N";
	/** 주윗글 사용여부 **/
	private String  arndUseAt = "N";
	/** 게시기간 사용여부 **/
	private String  ntcePdUseAt = "N";
	/** 게시대상 사용여부 **/
	private String  ntceTrgetUseAt = "N";
	/** 암호 사용여부 **/
	private String  passwordUseAt = "N";
	/** 별칭 사용여부 **/
	private String  ncmUseAt = "N";
	/** 파일업로드 사용여부 **/
	private String  fileUploadUseAt = "N";
	/** 앨범기능 여부  **/
	private String  albumAt = "N";
	/** 앨범형탱 여부 **/
	private String  albumStleAt = "N";
	/** 메일발송 여부 **/
	private String  emailSndngAt = "N";
	/** 업로드 파일갯수  **/
	private int  uploadFileCo = 0;
	/** 페이지 리스트 갯수 **/
	private int  pgeListCo = 0;
	/** 페이지 그룹 갯수 **/
	private int  pgeGroupCo = 0;
	/** 공지 갯수  **/
	private int  noticeCo = 0;
	/** 앨범 컬럼수  **/
	private int  albumColumnCo = 0;
	/** 앨범 줄수  **/
	private int  albumLineCo = 0;
	/** 목록 보기 권한 **/
	private String  listExAuthor = "";
	/** 본물 읽기 권한 **/
	private String  bdtRedngAuthor = "";
	/** 본문쓰기 권한 **/
	private String  bdtWriteAuthor = "";
	/** 답글쓰기 권한 **/
	private String  answerWriteAuthor = "";
	/** 메모쓰기 권한 **/
	private String  memoWriteAuthor = "";
	/** 웹에디터 쓰기권한 **/
	private String  webeditorWriteAuthor = "";
	
	
	public String getDbTname() {
		return dbTname;
	}
	public void setDbTname(String dbTname) {
		this.dbTname = dbTname;
	}
	public String getBbsId() {
		return bbsId;
	}
	public void setBbsId(String bbsId) {
		this.bbsId = bbsId;
	}
	public String getSysId() {
		return sysId;
	}
	public void setSysId(String sysId) {
		this.sysId = sysId;
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

}
