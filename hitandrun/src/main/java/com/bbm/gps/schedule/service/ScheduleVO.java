package com.bbm.gps.schedule.service;

import java.io.Serializable;

import com.bbm.gps.adm.schedule.service.ScheduleManageVO;

@SuppressWarnings("serial")
public class ScheduleVO extends ScheduleManageVO implements Serializable{
	
	String year	= "";
	String month = "";
	String day = "";
	String searchMm = "";
	
	String weekofmonth ="";
	
	String startWeek = "";
	String endWeek = "";
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getSearchMm() {
		return searchMm;
	}
	public void setSearchMm(String searchMm) {
		this.searchMm = searchMm;
	}
	public String getWeekofmonth() {
		return weekofmonth;
	}
	public void setWeekofmonth(String weekofmonth) {
		this.weekofmonth = weekofmonth;
	}
	public String getStartWeek() {
		return startWeek;
	}
	public void setStartWeek(String startWeek) {
		this.startWeek = startWeek;
	}
	public String getEndWeek() {
		return endWeek;
	}
	public void setEndWeek(String endWeek) {
		this.endWeek = endWeek;
	}
	
	
	

}
