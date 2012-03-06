package com.bbm.sample.dcm.service;

public class DataColcExcHistoryVO  extends DataColctExcVO {
	/** 게시판 항목 고유  ID */ 
	private String dataColcExcNo = "";	
	
    /** 게시판 등록자 */ 
    private String register = "";
	
    /** 게시판 수정시간 */ 
    private String registerDate= "";
    
    /** 게시판 첨부파일  ID */ 
    private String atchFileId = "";
    
    /** 게시판 첨부파일 ID */ 
    private String dataFileid = "";
    
    /** 게시판 파일 첨부 여부 */ 
    private String dataHasFile = "";
    
    private String  instt  ="";
    
    private String statsName  ="";
    
    private String examinQy ="";
    
    private String colctForm  ="";
    
    private String uploadcount ="";
    
	/** 통계기관ID */ 	
	private int rowcount = 0;

    private String tableId ="";
    
	public String getDataColcExcNo() {
		return dataColcExcNo;
	}

	public void setDataColcExcNo(String dataColcExcNo) {
		this.dataColcExcNo = dataColcExcNo;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}


	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getAtchFileId() {
		return atchFileId;
	}

	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	public String getDataFileid() {
		return dataFileid;
	}

	public void setDataFileid(String dataFileid) {
		this.dataFileid = dataFileid;
	}

	public String getDataHasFile() {
		return dataHasFile;
	}

	public void setDataHasFile(String dataHasFile) {
		this.dataHasFile = dataHasFile;
	}

	public String getInstt() {
		return instt;
	}

	public void setInstt(String instt) {
		this.instt = instt;
	}

	public String getStatsName() {
		return statsName;
	}

	public void setStatsName(String statsName) {
		this.statsName = statsName;
	}

	
	public String getColctForm() {
		return colctForm;
	}

	public void setColctForm(String colctForm) {
		this.colctForm = colctForm;
	}

	public String getUploadcount() {
		return uploadcount;
	}

	public void setUploadcount(String uploadcount) {
		this.uploadcount = uploadcount;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getExaminQy() {
		return examinQy;
	}

	public void setExaminQy(String examinQy) {
		this.examinQy = examinQy;
	}

	public int getRowcount() {
		return rowcount;
	}

	public void setRowcount(int rowcount) {
		this.rowcount = rowcount;
	}
	
    
     
}
