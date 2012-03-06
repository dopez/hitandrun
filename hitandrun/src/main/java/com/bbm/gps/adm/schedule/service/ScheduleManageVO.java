package com.bbm.gps.adm.schedule.service;

import com.bbm.gps.cmm.service.GpsDefaultVO;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ScheduleManageVO extends GpsDefaultVO implements Serializable {
	/** 일정구분 **/
	private String scTy;
	/** 일정구분명 **/
	private String scTyNm;
	/** 일련번호 **/
	private int scSn;
	/** 제목 **/
	private String subject;
	/** 주관기관 **/
	private String orgNm;
	/** 내용 **/
	private String scheduleCn;
	/** 시작일 **/
	private String startDt;
	/** 종료일 **/
	private String endDt;
	/** 장소 **/
	private String place;
	/** 전화번호 **/
	private String phonCn;
	/** 팩스번호 **/
	private String faxPhonCn;
	/** 등록자ID **/
	private String registerId;
	/** 수정자ID **/
	private String updtusrId;
	/** 수정일 **/
	private String updtDt;
	/** 등록일 **/
	private String registDt;
	/** 등록자 IP **/
	private String registerIp;
	/** 조사ID **/
	private String statId;
	/** 조사명 **/
	private String statNm;
	/** 시작일 **/
	private String startDe;
	/** 종료일 **/
	private String endDe;
	/** 시작시간 **/
	private String scStartHh;
	/** 시작분 **/
	private String scStartMm;
	/** 종료시간 **/
	private String scEndHh;
	/** 종료분 **/
	private String scEndMm;
	/**  **/
	private String typeCond;
	
	
	public int getScSn() {
		return scSn;
	}
	public void setScSn(int scSn) {
		this.scSn = scSn;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getOrgNm() {
		return orgNm;
	}
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}
	
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	
	
	public String getScheduleCn() {
		return scheduleCn;
	}
	public void setScheduleCn(String scheduleCn) {
		this.scheduleCn = scheduleCn;
	}
	public String getRegisterId() {
		return registerId;
	}
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}
	
	public String getUpdtDt() {
		return updtDt;
	}
	public void setUpdtDt(String updtDt) {
		this.updtDt = updtDt;
	}
	public String getRegistDt() {
		return registDt;
	}
	public void setRegistDt(String registDt) {
		this.registDt = registDt;
	}
	public String getTypeCond() {
		return typeCond;
	}
	public void setTypeCond(String typeCond) {
		this.typeCond = typeCond;
	}
	public String getStatId() {
		return statId;
	}
	public void setStatId(String statId) {
		this.statId = statId;
	}
	public String getStatNm() {
		return statNm;
	}
	public void setStatNm(String statNm) {
		this.statNm = statNm;
	}
	public String getScStartHh() {
		return scStartHh;
	}
	public void setScStartHh(String scStartHh) {
		this.scStartHh = scStartHh;
	}
	public String getScStartMm() {
		return scStartMm;
	}
	public void setScStartMm(String scStartMm) {
		this.scStartMm = scStartMm;
	}
	public String getScEndHh() {
		return scEndHh;
	}
	public void setScEndHh(String scEndHh) {
		this.scEndHh = scEndHh;
	}
	public String getScEndMm() {
		return scEndMm;
	}
	public void setScEndMm(String scEndMm) {
		this.scEndMm = scEndMm;
	}	
	
	public String getStartDe() {
		return startDe;
	}
	public void setStartDe(String startDe) {
		this.startDe = startDe;
	}
	public String getEndDe() {
		return endDe;
	}
	public void setEndDe(String endDe) {
		this.endDe = endDe;
	}
	public String getScTy() {
		return scTy;
	}
	public void setScTy(String scTy) {
		this.scTy = scTy;
	}
	public String getPhonCn() {
		return phonCn;
	}
	public void setPhonCn(String phonCn) {
		this.phonCn = phonCn;
	}
	public String getFaxPhonCn() {
		return faxPhonCn;
	}
	public void setFaxPhonCn(String faxPhonCn) {
		this.faxPhonCn = faxPhonCn;
	}
	public String getUpdtusrId() {
		return updtusrId;
	}
	public void setUpdtusrId(String updtusrId) {
		this.updtusrId = updtusrId;
	}
	public String getRegisterIp() {
		return registerIp;
	}
	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}
	public String getScTyNm() {
		return scTyNm;
	}
	public void setScTyNm(String scTyNm) {
		this.scTyNm = scTyNm;
	}
	
	

	
}
