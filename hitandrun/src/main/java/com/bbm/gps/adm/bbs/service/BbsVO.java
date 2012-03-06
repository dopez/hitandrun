package com.bbm.gps.adm.bbs.service;

import java.io.Serializable;

/**
 * <p><b>NOTE:</b>게시판기본정보VO
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
	private String co = "";
	
	/** 게시물 HTML 여부 Y/N **/
	private String htmlAt = "N";
	
	/** 공지여부 Y/N **/
	private String noticeAt = "N";
	
	/** 게시물 추천수 **/
	private int recommendCo = 0;
	
	/** 게시물 메모 수 **/
	private int memoCo = 0;
	
	/** 게시물 첨부 파일 수 **/
	private int atchmnflCo = 0;
	
	/** 게시 시작일 **/
	private String bgnde = "";
	
	/** 게시 종료일 **/
	private String endde = "";
	
	/** 게시 대상 **/
	private String trget = "";
	
	/** 게시 반복 **/
	private String reptit = "";
	
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
	public String getCo() {
		return co;
	}
	public void setCo(String co) {
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
	public int getMemoCo() {
		return memoCo;
	}
	public void setMemoCo(int memoCo) {
		this.memoCo = memoCo;
	}
	public int getAtchmnflCo() {
		return atchmnflCo;
	}
	public void setAtchmnflCo(int atchmnflCo) {
		this.atchmnflCo = atchmnflCo;
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
}
