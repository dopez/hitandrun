package com.bbm.gps.bbs.service;

import java.io.Serializable;

/**
 * <p><b>NOTE:</b>게시판VO
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
 *   2011.07.04     황기연          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class BbsVO extends BbsSearchVO implements Serializable {
	
	/** 게시물 level **/
	private int bbsLevel = 0;

	/** 게시물 그룹번호 **/
	private int relate = 0;
	
	/** 게시물 계층번호 **/
	private int sclsrtNo = 0;
	
	/** 게시판 들여쓰기 **/
	private int indwrtng = 0;
	
	/** 게시물 제목 **/
	private String sj = "";
	
	/** 게시물 제목 색 **/
	private String sjColor = "";
	
	/** 게시물 미리보기 **/
	private String preview = "";
	
	/** 게시물 내용 **/
	private String cn ;
	
	/** 작성자 ID **/
	private String wrterId = "";
	
	/** 작성자명 **/
	private String wrterNm = "";
	
	/** 작성자 유형 **/
	private String wrterTy = "";
	
	/** 작성자 이메일 **/
	private String email = "";
	
	/** 작성자 홈페이지 **/
	private String hmpg = "";
	
	/** 게시물 비밀번호 **/
	private String password = "";
	
	/** 게시물 조회수 **/
	private int co = 0;
	
	/** 게시물 HTML 여부 Y/N **/
	private String htmlAt = "N";
	
	/** 공지여부 Y/N **/
	private String noticeAt = "N";
	
	/** 게시물 추천수 **/
	private int recommendCo = 0;
	
	/** 게시물 이미지 파일 ID **/
	private String imageFileId = "";
	
	/** 게시물 첨부 파일 ID **/
	private String atchmnflId = "";
	
	/** 게시 시작일 **/
	private String bgnde = "";
	
	/** 게시 종료일 **/
	private String endde = "";
	
	/** 게시 대상 **/
	private String trget = "";
	
	/** 게시 반복 **/
	private String reptit = "";
	
	/** 게시물 분류 코드 **/
	private String ctgryCode = "";
	
	/** 게시물 공개 여부 **/
	private String othbcMthUseAt = "N";
	
	/** 이전글 게시물 번호 **/
	private int beforeBbsSn = 0;

	/** 다음글 게시물 번호 **/
	private int nextBbsSn = 0;
	
	/** 이전글 제목 **/
	private String beforeSj = "";

	/** 다음글 제목 **/
	private String nextSj = "";
	
	/** 이전글 이미지파일 ID **/
	private String beforeImageFileId = "";

	/** 다음글 이미지파일 ID **/
	private String nextImageFileId = "";
	
	/** 게시글목록 new 아이콘 여부 **/
	private String newIconUseAt = "";
	
	/** 게시글목록 hot 아이콘 여부 **/
	private String hotIconUseAt = "";
	
	/** 게시글목록 cool 아이콘 여부 **/
	private String coolIconUseAt = "";
	
	/** 앨범이미지 마스크 **/
	private String imageMask = "";
	
	/** 앨범이미지 확장자 **/
	private String imageExt = "";
	
	/** 다음글 앨범이미지 마스크 **/
	private String nextImageMask = "";
	
	/** 다음글 앨범이미지 확장자 **/
	private String nextImageExt = "";
	
	/** 이전글 앨범이미지 마스크 **/
	private String beforeImageMask = "";
	
	/** 이전글 앨범이미지 확장자 **/
	private String beforeImageExt = "";
	
	public String getNextImageMask() {
		return nextImageMask;
	}

	public void setNextImageMask(String nextImageMask) {
		this.nextImageMask = nextImageMask;
	}

	public String getNextImageExt() {
		return nextImageExt;
	}

	public void setNextImageExt(String nextImageExt) {
		this.nextImageExt = nextImageExt;
	}

	public String getBeforeImageMask() {
		return beforeImageMask;
	}

	public void setBeforeImageMask(String beforeImageMask) {
		this.beforeImageMask = beforeImageMask;
	}

	public String getBeforeImageExt() {
		return beforeImageExt;
	}

	public void setBeforeImageExt(String beforeImageExt) {
		this.beforeImageExt = beforeImageExt;
	}
	
	public String getImageMask() {
		return imageMask;
	}

	public void setImageMask(String imageMask) {
		this.imageMask = imageMask;
	}

	public String getImageExt() {
		return imageExt;
	}

	public void setImageExt(String imageExt) {
		this.imageExt = imageExt;
	}
	
	public int getBbsLevel() {
		return bbsLevel;
	}

	public void setBbsLevel(int bbsLevel) {
		this.bbsLevel = bbsLevel;
	}
	
	public int getRelate() {
		return relate;
	}

	public void setRelate(int relate) {
		this.relate = relate;
	}

	public int getSclsrtNo() {
		return sclsrtNo;
	}

	public void setSclsrtNo(int sclsrtNo) {
		this.sclsrtNo = sclsrtNo;
	}

	public int getIndwrtng() {
		return indwrtng;
	}

	public void setIndwrtng(int indwrtng) {
		this.indwrtng = indwrtng;
	}

	public String getSj() {
		return sj;
	}

	public void setSj(String sj) {
		this.sj = sj;
	}

	public String getSjColor() {
		return sjColor;
	}

	public void setSjColor(String sjColor) {
		this.sjColor = sjColor;
	}

	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getWrterId() {
		return wrterId;
	}

	public void setWrterId(String wrterId) {
		this.wrterId = wrterId;
	}

	public String getWrterNm() {
		return wrterNm;
	}

	public void setWrterNm(String wrterNm) {
		this.wrterNm = wrterNm;
	}

	public String getWrterTy() {
		return wrterTy;
	}

	public void setWrterTy(String wrterTy) {
		this.wrterTy = wrterTy;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHmpg() {
		return hmpg;
	}

	public void setHmpg(String hmpg) {
		this.hmpg = hmpg;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCo() {
		return co;
	}

	public void setCo(int co) {
		this.co = co;
	}

	public String getHtmlAt() {
		return htmlAt;
	}

	public void setHtmlAt(String htmlAt) {
		this.htmlAt = htmlAt;
	}

	public String getNoticeAt() {
		return noticeAt;
	}

	public void setNoticeAt(String noticeAt) {
		this.noticeAt = noticeAt;
	}

	public int getRecommendCo() {
		return recommendCo;
	}

	public void setRecommendCo(int recommendCo) {
		this.recommendCo = recommendCo;
	}

	public String getImageFileId() {
		return imageFileId;
	}

	public void setImageFileId(String imageFileId) {
		this.imageFileId = imageFileId;
	}

	public String getAtchmnflId() {
		return atchmnflId;
	}

	public void setAtchmnflId(String atchmnflId) {
		this.atchmnflId = atchmnflId;
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

	public String getTrget() {
		return trget;
	}

	public void setTrget(String trget) {
		this.trget = trget;
	}

	public String getReptit() {
		return reptit;
	}

	public void setReptit(String reptit) {
		this.reptit = reptit;
	}

	public String getCtgryCode() {
		return ctgryCode;
	}

	public void setCtgryCode(String ctgryCode) {
		this.ctgryCode = ctgryCode;
	}

	public String getOthbcMthUseAt() {
		return othbcMthUseAt;
	}

	public void setOthbcMthUseAt(String othbcMthUseAt) {
		this.othbcMthUseAt = othbcMthUseAt;
	}

	public int getBeforeBbsSn() {
		return beforeBbsSn;
	}

	public void setBeforeBbsSn(int beforeBbsSn) {
		this.beforeBbsSn = beforeBbsSn;
	}

	public int getNextBbsSn() {
		return nextBbsSn;
	}

	public void setNextBbsSn(int nextBbsSn) {
		this.nextBbsSn = nextBbsSn;
	}

	public String getBeforeSj() {
		return beforeSj;
	}

	public void setBeforeSj(String beforeSj) {
		this.beforeSj = beforeSj;
	}

	public String getNextSj() {
		return nextSj;
	}

	public void setNextSj(String nextSj) {
		this.nextSj = nextSj;
	}

	public String getNewIconUseAt() {
		return newIconUseAt;
	}

	public void setNewIconUseAt(String newIconUseAt) {
		this.newIconUseAt = newIconUseAt;
	}

	public String getHotIconUseAt() {
		return hotIconUseAt;
	}

	public void setHotIconUseAt(String hotIconUseAt) {
		this.hotIconUseAt = hotIconUseAt;
	}

	public String getCoolIconUseAt() {
		return coolIconUseAt;
	}

	public void setCoolIconUseAt(String coolIconUseAt) {
		this.coolIconUseAt = coolIconUseAt;
	}
	
	public String getBeforeImageFileId() {
		return beforeImageFileId;
	}

	public void setBeforeImageFileId(String beforeImageFileId) {
		this.beforeImageFileId = beforeImageFileId;
	}

	public String getNextImageFileId() {
		return nextImageFileId;
	}

	public void setNextImageFileId(String nextImageFileId) {
		this.nextImageFileId = nextImageFileId;
	}
}
