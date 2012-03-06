package com.bbm.gps.adm.bbs.service;

import com.bbm.gps.cmm.service.GpsDefaultVO;

import java.io.Serializable;

/**
 * <p><b>NOTE:</b>게시판파일VO
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
public class BbsFileVO extends GpsDefaultVO implements Serializable {
	/** 게시판ID **/
	private String bbsId = "";
	/** 게시물 일련번호  **/
	private int bbsSn = 0;
	/** 게시물 파일 일련번호 **/
	private int fileSn = 0;
	/** 게시물 파일명 **/
	private String fileNm = "";
	/** 게시물 파일 마스크명 **/
	private String fileMask = "";
	/** 게시물 파일 마임타임 **/
	private String fileMime = "";
	/** 게시물 파일 크기 **/
	private int fileSize = 0;
	/** 게시물 파일 다운로드 수 **/
	private int fileDwldCo = 0;
	
	public String getBbsId() {
		return bbsId;
	}
	public void setBbsId(String bbsId) {
		this.bbsId = bbsId;
	}
	public int getBbsSn() {
		return bbsSn;
	}
	public void setBbsSn(int bbsSn) {
		this.bbsSn = bbsSn;
	}
	public int getFileSn() {
		return fileSn;
	}
	public void setFileSn(int fileSn) {
		this.fileSn = fileSn;
	}
	public String getFileNm() {
		return fileNm;
	}
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	public String getFileMask() {
		return fileMask;
	}
	public void setFileMask(String fileMask) {
		this.fileMask = fileMask;
	}
	public String getFileMime() {
		return fileMime;
	}
	public void setFileMime(String fileMime) {
		this.fileMime = fileMime;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public int getFileDwldCo() {
		return fileDwldCo;
	}
	public void setFileDwldCo(int fileDwldCo) {
		this.fileDwldCo = fileDwldCo;
	}
}
