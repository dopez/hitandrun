/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bbm.sample.dcm.service;


/**
 * 자료수집수행 VO
 * @filename  DataColctExcVO
 * @author 조사표 설계/수집 개발팀 최종대
 * @since 2011.07.22
 * @version 1.0
 * @see
 *
 * <pre>
 *  == Modification Information ==
 *   
 *   date         author               note
 *  -------      --------    ---------------------------
 *   2011.07.22   최종대     최초 생성
 *							1. 자료수집수행 리스트에서 사용되는 value object 정의.
 * </pre>
 */

public class DataColctExcVO extends DataColctExcDefaultVO {
	
	/** 게시판 항목 고유  ID */
	
	/** 생산ID*/
	private String prdctnId ="";
	/** 통계승인번호 */ 
	private String stateConfmNo = "";
	/** 조직코드 */ 
	private String orgnztCode = "";
	/** 조사명 */ 
	private String svyNm = "";
	/** 통계조사종류 */ 
	private String statsSvyTy = "";
	/** 자료수집 등록일시 */ 
	private String colctRegistDt = "";
	/** 자료수집 등록자ID */ 
	private String colctRegistorId = "";
	/** 자료수집 변경일시 */ 
	private String colctUpdtDt = "";
	/** 파일 첨부*/ 
	private String dataColcHasFile = "";//
	/** 자료수집 code */ 
	private String dataColcCode = "";
	/** 수록담당자 */
	private String wrterId ="";
	/** 수록일 */
	private String wrtDt ="";
	/** 파일 ID */ 
	private String dataFileid = "";//
	/** 게시판 첨부파일 ID */ 
	private String atchFileId = "";
	/** 수집테이블ID */ 
	private String colctTableId = "";
	/** 수집형태 */ 
	private String colctStle = "";//
	/** 자료수집명 */
	private String colctNm ="";//
	/** 자료수집출처 */ 
	private String colctOrigin = "";
	/** 조사회차 */ 
	private String svyOdr = "";	
	/** 양식아이디 */ 
	private String formID = "";		
	/** 자료량 */ 
	private int dtaQy = 0;
	/** 기준년도 */
	private String svyYear = "";
	/** 기준월 */ 
	private String svyMonth = "";
	/** 조사표ID */ 
	private String svyTableId = "";
	/** 설계유형 */ 
	private String designType = "";
	/** 삭제플래그 */ 
	private String delFalg  = "";
	/** 수집파일명 */ 
	private String colctFileNm  = "";
	/** 수집파일명 */ 
	private String recordId  = "";

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	/** 수집파일명 */ 
	public String getColctFileNm() {
		return colctFileNm;
	}

	public void setColctFileNm(String colctFileNm) {
		this.colctFileNm = colctFileNm;
	}

	/** 삭제플래그 */ 
	public String getDelFalg() {
		return delFalg;
	}

	public void setDelFalg(String delFalg) {
		this.delFalg = delFalg;
	}

	/** 조사표ID */
	public String getSvyTableId() {
		return svyTableId;
	}

	public void setSvyTableId(String svyTableId) {
		this.svyTableId = svyTableId;
	}
	
	/** 설계유형 */ 
	public String getDesignType() {
		return designType;
	}

	public void setDesignType(String designType) {
		this.designType = designType;
	}

	/** 수록담당자 */
	public String getWrterId() {
		return wrterId;
	}

	public void setWrterId(String wrterId) {
		this.wrterId = wrterId;
	}
	/** 수록일 */
	public String getWrtDt() {
		return wrtDt;
	}

	public void setWrtDt(String wrtDt) {
		this.wrtDt = wrtDt;
	}	
	/** 기준년도 */
	public String getSvyYear() {
		return svyYear;
	}

	public void setSvyYear(String svyYear) {
		this.svyYear = svyYear;
	}

	/** 기준월 */ 
	public String getSvyMonth() {
		return svyMonth;
	}
	public void setSvyMonth(String svyMonth) {
		this.svyMonth = svyMonth;
	}

	
	/** 로그저장을 위한 엑셀인설트 카운트 */ 
	public int getDtaQy() {
		return dtaQy;
	}
	public void setDtaQy(int dtaQy) {
		this.dtaQy = dtaQy;
	}
	/** 통계승인번호 */
	public String getStateConfmNo() {
		return stateConfmNo;
	}
	public void setStateConfmNo(String stateConfmNo) {
		this.stateConfmNo = stateConfmNo;
	}
	/** 조직코드 */ 
	public String getOrgnztCode() {
		return orgnztCode;
	}
	public void setOrgnztCode(String orgnztCode) {
		this.orgnztCode = orgnztCode;
	}
	/** 조사명 */
	public String getSvyNm() {
		return svyNm;
	}
	public void setSvyNm(String svyNm) {
		this.svyNm = svyNm;
	}
	/** 최초등록자 */ 
	public String getColctRegistorId() {
		return colctRegistorId;
	}
	public void setColctRegistorId(String colctRegistorId) {
		this.colctRegistorId = colctRegistorId;
	}
	/** 자료수집 등록일시 */
	public String getColctRegistDt() {
		return colctRegistDt;
	}
	public void setColctRegistDt(String colctRegistDt) {
		this.colctRegistDt = colctRegistDt;
	}
	/** 자료수집 변경일시 */ 
	public String getColctUpdtDt() {
		return colctUpdtDt;
	}
	public void setColctUpdtDt(String colctUpdtDt) {
		this.colctUpdtDt = colctUpdtDt;
	}
	/** 파일 첨부*/ 
	public String getDataColcHasFile() {
		return dataColcHasFile;
	}
	public void setDataColcHasFile(String dataColcHasFile) {
		this.dataColcHasFile = dataColcHasFile;
	}
	/** 통계조사종류 */ 
	public String getStatsSvyTy() {
		return statsSvyTy;
	}
	public void setStatsSvyTy(String statsSvyTy) {
		this.statsSvyTy = statsSvyTy;
	}
	
	/** 자료수집명 */
	public String getColctNm() {
		return colctNm;
	}

	public void setColctNm(String colctNm) {
		this.colctNm = colctNm;
	}
	
	/** 자료수집 code */ 
	public String getDataColcCode() {
		return dataColcCode;
	}
	public void setDataColcCode(String dataColcCode) {
		this.dataColcCode = dataColcCode;
	}
	/** 파일 ID */ 
	public String getDataFileid() {
		return dataFileid;
	}
	public void setDataFileid(String dataFileid) {
		this.dataFileid = dataFileid;
	}
	/** 게시판 첨부파일 ID */ 
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	/** 양식아이디 */ 
	public String getFormID() {
		return formID;
	}
	public void setFormID(String formID) {
		this.formID = formID;
	}
	/** 생산ID*/
	public String getPrdctnId() {
		return prdctnId;
	}
	public void setPrdctnId(String prdctnId) {
		this.prdctnId = prdctnId;
	}

	/** 게시판 분류 */ 
    private String bunryu  = "";
    
    public String getBunryu() {
		return bunryu;
	}
	public void setBunryu(String bunryu) {
		this.bunryu = bunryu;
	}
	/** 검색조건 관련 */ 
    private String fromDate = "";

	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	private String toDate = "";

	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	/** 수집테이블 ID */
	public String getColctTableId() {
		return colctTableId;
	}
	public void setColctTableId(String colctTableId) {
		this.colctTableId = colctTableId;
	}
	
	/** 수집형태 */
	public String getColctStle() {
		return colctStle;
	}
	public void setColctStle(String colctStle) {
		this.colctStle = colctStle;
	}
	/** 자료수집출처 */
	public String getColctOrigin() {
		return colctOrigin;
	}
	public void setColctOrigin(String colctOrigin) {
		this.colctOrigin = colctOrigin;
	}
	/** 생산회차 */
	public String getSvyOdr() {
		return svyOdr;
	}
	public void setSvyOdr(String svyOdr) {
		this.svyOdr = svyOdr;
	}
	
}
