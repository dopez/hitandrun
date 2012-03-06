package com.bbm.gps.adm.schedule.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;


public class DateUtil {
    Calendar  calendar = null;

    /**
     * 기본생성자 입니다.
     */
    public DateUtil() {
    	calendar = Calendar.getInstance();
    	Date trialTime = new Date();
    	calendar.setTime(trialTime);
    }

    /**
     * <pre>
     * 날짜를 설정 합니다.
     * </pre>
     * @param timeInMillis the new time in UTC milliseconds from the epoch.
     */
    public void setDate(long timeInMillis){
        calendar.setTimeInMillis(timeInMillis);
    }

    /**
     * <pre>
     * 날짜를 설정 합니다.
     * </pre>
     * @param cl 표시하고자 하는 날짜
     */
    public void setDate(Calendar cl){
        calendar = cl;
    }

    /**
     * <pre>
     * 날짜를 설정 합니다.
     * </pre>
     * @param year 년
     * @param month 월
     * @param day 일
     */
    public void setDate(int year, int month, int day){
        calendar.set(year, month - 1, day);
    }

    /**
     * <pre>
     * 년도를 얻습니다.
     * </pre>
     * @return 년도
     */
    public int getYear() {
	    return calendar.get(Calendar.YEAR);
    }

    /**
     * <pre>
     * 달을 다음의 형태로 얻습니다.
     *
     * "January", "February", "March",
     * "April", "May", "June",
     * "July","August", "September",
     * "October", "November", "December"
     * </pre>
     * @return 문자형 달
     */
    public String getMonth() {
    	int m = getMonthInt();
    	String[] months = new String [] { "January", "February", "March",
    					"April", "May", "June",
    					"July", "August", "September",
    					"October", "November", "December" };
    	if (m > 12)
    	    return "Unknown to Man";

    	return months[m - 1];
    }

    /**
     * <pre>
     * 요일을 다음의 형태로 얻습니다.
     *
     * "일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"
     * </pre>
     * @return 한글 요일
     */
    public String getDay() {
    	int x = getDayOfWeek();
    	String[] days = new String[] {"일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"};

    	if (x > 7)
    	    return "Unknown to Man";

	    return days[x - 1];
    }

    /**
     * <pre>
     * 달을 숫자의 형태로 얻습니다.
     * </pre>
     * @return 숫자형 달
     */
    public int getMonthInt() {
	    return 1 + calendar.get(Calendar.MONTH);
    }

    /**
     * <pre>
     * 날짜를 얻습니다.
     * </pre>
     * @return yyyy-mm-dd
     */
    public String getDate() {
	    return getYear() + "-" + getMonthInt() + "-" + getDayOfMonth();
    }

    /**
     * <pre>
     * 시간을 얻습니다.
     * </pre>
     * @return HH:mm:ss
     */
    public String getTime() {
	    return getHour() + ":" + getMinute() + ":" + getSecond();
    }

    /**
     * <pre>
     * Calendar.DAY_OF_MONTH 값을 얻습니다.
     * </pre>
     * @return Calendar.DAY_OF_MONTH
     */
    public int getDayOfMonth() {
	    return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * <pre>
     * Calendar.DAY_OF_YEAR 값을 얻습니다.
     * </pre>
     * @return Calendar.DAY_OF_YEAR
     */
    public int getDayOfYear() {
	    return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * <pre>
     * Calendar.WEEK_OF_YEAR 값을 얻습니다.
     * </pre>
     * @return Calendar.WEEK_OF_YEAR
     */
    public int getWeekOfYear() {
	    return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * <pre>
     * Calendar.WEEK_OF_MONTH 값을 얻습니다.
     * </pre>
     * @return Calendar.WEEK_OF_MONTH
     */
    public int getWeekOfMonth() {
	    return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * <pre>
     * Calendar.DAY_OF_WEEK 값을 얻습니다.
     * </pre>
     * @return Calendar.DAY_OF_WEEK
     */
    public int getDayOfWeek() {
	    return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * <pre>
     * Calendar.HOUR_OF_DAY 값을 얻습니다.
     * </pre>
     * @return Calendar.HOUR_OF_DAY
     */
    public int getHour() {
	    return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * <pre>
     * Calendar.MINUTE 값을 얻습니다.
     * </pre>
     * @return Calendar.MINUTE
     */
    public int getMinute() {
	    return calendar.get(Calendar.MINUTE);
    }

    /**
     * <pre>
     * Calendar.SECOND 값을 얻습니다.
     * </pre>
     * @return Calendar.SECOND
     */
    public int getSecond() {
	    return calendar.get(Calendar.SECOND);
    }

    /**
     * <pre>
     * Calendar.ERA 값을 얻습니다.
     * </pre>
     * @return Calendar.ERA
     */
    public int getEra() {
	    return calendar.get(Calendar.ERA);
    }

    /**
     * <pre>
     * Calendar.AM_PM 값을 얻습니다.
     * </pre>
     * @return Calendar.AM_PM
     */
    public int getAMPM() {
	    return calendar.get(Calendar.AM_PM);
    }

    /**
     * 해당 월의 마지막 날을 구한다.
     * @return Calendar.DAY_OF_MONTH
     */
    public int getEndDay(){
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 회계년도를 얻습니다.
     * @return 회계년도
     */
    public int getAccountYear(){
        if (getMonthInt() < 3){
            return getYear() - 1;
        }else{
            return getYear();
        }
    }

    /**
     * <pre>
     * 회계년도를 얻습니다.
     * </pre>
     * @param year 년도
     * @param month 달
     * @return 회계년도
     */
    public int getMoneyYear(int year, int month){
        if (month < 3){
            return year - 1;
        }else{
            return year;
        }
    }

    /**
     * 주어진 월의 마지막 날을 구함.
     * @param year 년도
     * @param month 달
     * @return 마지막 날
     */
    public int getAccountDay(String year, String month){
        int yer = Integer.parseInt(year);
        int mon = Integer.parseInt(month);

        if (mon != 2) {
            if (mon == 4 || mon == 6 || mon == 9 || mon == 11){
                return 30;
            }else{
                return 31;
            }
        } else {
            int day_num =0;
            // 2월인 경우 윤년 체크
            if ((yer%4) != 0) day_num = 28;
            else if ((yer%100) != 0) day_num = 29;
            else if ((yer%400) != 0) day_num = 28;
            else day_num = 29;

            return day_num;
        }
    }

    /**
     * 8자리로 된 날자를 지정된 포맷으로 변환
     * @param date
     * @return String
     */
    public String getStringToDate(String date) {
        return getStringToDate(date, "년월일");
    }

    /**
     * 8자리로 된 날자를 지정된 포맷으로 변환
     * @param date      변환하기 위한 날자 8자리로 된 날자(yyyyMMdd)
     * @param param     지정된 포맷
     * @return String
     */
    public String getStringToDate(String date, String param) {
        if (StringUtils.isEmpty(date) || date.length() != 8) return "";

        String format = param;
        if (StringUtils.isEmpty(format)) {
            format = "년월일";
        }

        StringBuffer sb = new StringBuffer("");

        String year = StringUtils.substring(date, 0, 4);
        String month = StringUtils.substring(date, 4, 6);
        String day = StringUtils.substring(date, 6, 8);

        if (format.length() == 3) {
            sb.append(year).append(format.substring(0, 1))
                    .append(month).append(format.substring(1, 2))
                    .append(day).append(format.substring(2, 3));
        } else {
            sb.append(year).append(format).append(month).append(format).append(day);
        }
        return sb.toString();
    }

    /**
     * 현재 년도를 mode에 따라 &lt;option&gt;문으로 만들어 return
     * @param mode  year  년도 option 문
     *              month 월 opiton 문
     *              day   일 option 문
     * @return String
     */
    public String getDateOptions(String mode) {
        int year = this.getYear();
        int month = this.getMonthInt();
        int day = this.getDayOfMonth();
        int endOfMonth = this.getEndDay();

        if (StringUtils.equals(mode, "year")) {
            return this.getYearOptions(year);
        } else if (StringUtils.equals(mode, "month")) {
            return this.getMonthOptions(month);
        } else if (StringUtils.equals(mode, "day")) {
            return this.getDayOptions(endOfMonth, day);
        } else {
            return "";
        }
    }

    /**
     * 타임스탬프를 mode 에 따라  &lt;option&gt;문으로 만들어 return
     * @param timestamp
     * @param mode  year  : 년도 option 문
     *              month : 월 opiton 문
     *              day   : 일 option 문
     * @return String
     */
    public String getDateOptions(Timestamp timestamp, String mode) {
        return this.getDateOptions (getDateString(timestamp, "yyyyMMdd"), mode);
    }

    /**
     * 특정  년도를 mode에 따라 &lt;option&gt;문으로 만들어 return
     * @param date  특정 날자  : yyyyMMdd 형식 이어야 함
     * @param mode  year  년도 option 문
     *              month 월 opiton 문
     *              day   일 option 문
     * @return String
     */
    public String getDateOptions (String date, String mode) {
        if (date == null || date.length() != 8) return "";

        int year = Integer.parseInt(StringUtils.substring(date, 0, 4));
        int month = Integer.parseInt(StringUtils.substring(date, 4, 6));
        int day = Integer.parseInt(StringUtils.substring(date, 6, 8));

        if (StringUtils.equals(mode, "year")) {
            return this.getYearOptions(year);
        } else if (StringUtils.equals(mode, "month")) {
            return this.getMonthOptions(month);
        } else if (StringUtils.equals(mode, "day")) {
            return this.getDayOptions(year, month, day);
        } else {
            return "";
        }
    }

    /**
     * 현재년도를 &lt;option&gt;문으로 만들어 return
     * @return String
     */
    public String getYearOptions() {
        return this.getYearOptions(0, 3, this.getYear());
    }

    /**
     * 현재년도를 &lt;option&gt;문으로 만들어 return
     * @param minValue  현재년도 기준으로 -값, 현재 년도부터 시작일 경우  0 이다
     * @param maxValue  현재년도 기준으로 + 값
     * @return String
     */
    public String getYearOptions(int minValue, int maxValue) {
        return this.getYearOptions(minValue, maxValue, this.getYear());
    }

    /**
     * 특정년도를 &lt;option&gt;문으로 만들어 return
     * @param year  특정 년도
     * @return String
     */
    public String getYearOptions(int year) {
        return this.getYearOptions(0, 3, year);
    }

    /**
     * 특정 년도를 &lt;option&gt;문으로 만들어 return
     * @param timestamp 특정 년도
     * @return String
     */
    public String getYearOptions(Timestamp timestamp) {
        if (timestamp == null) {
            timestamp = new Timestamp(System.currentTimeMillis());
        }
        String year = getDateString(timestamp, "yyyy");
        return getYearOptions(NumberUtils.toInt(year));
    }

    /**
     * 특정년도를 &lt;option&gt;문으로 만들어 return
     * @param minValue  특정년도 기준으로 - 값, 현재 년도부터 시작일 경우  0 이다
     * @param maxValue  특정년도 기준으로 + 값
     * @param selectedYear  선택되는 년도
     * @return  &lt;options&gt;
     */
    public String getYearOptions(int minValue, int maxValue, int selectedYear) {
        if (selectedYear == 0) selectedYear = this.getYear();

        StringBuffer yearOptions = new StringBuffer("");

        int minYear = minValue + this.getYear();
        int maxYear = maxValue + this.getYear();

        for (int i=minYear; i <= maxYear; i++) {
            yearOptions.append("<option value='").append(String.valueOf(i)).append("'");
            if (i == selectedYear) {
                yearOptions.append(" selected");
            }
            yearOptions.append(">").append(String.valueOf(i)).append(" 년").append("</option>");
            yearOptions.append("\n");
        }
        return yearOptions.toString();
    }

    /**
     * 현재 월을  &lt;option&gt;문으로 만들어 return
     * @return String
     */
    public String getMonthOptions() {
        return getMonthOptions(this.getMonthInt());
    }

    /**
     * 특정 월을  &lt;option&gt;문으로 만들어 return
     * @param timestamp
     * @return String
     */
    public String getMonthOptions(Timestamp timestamp) {
        if (timestamp == null) {
            timestamp = new Timestamp(System.currentTimeMillis());
        }
        String month = getDateString(timestamp, "MM");
        return getMonthOptions(NumberUtils.toInt(month));
    }

    /**
     * 특정 월을  &lt;option&gt;문으로 만들어 return
     * @param selectedMonth  선택되는 월
     * @return String
     */
    public String getMonthOptions(int selectedMonth) {
        if (selectedMonth == 0) selectedMonth = this.getMonthInt();

        StringBuffer monthOptions = new StringBuffer("");
        for (int i=1; i <= 12; i++) {
            monthOptions.append("<option value='").append(StringUtils.leftPad(String.valueOf(i), 2, "0")).append("'");
            if (i == selectedMonth) {
                monthOptions.append(" selected");
            }
            monthOptions.append(">").append(StringUtils.leftPad(String.valueOf(i), 2, "0")).append(" 월").append("</option>");
            monthOptions.append("\n");
        }
        return monthOptions.toString();
    }

    /**
     * 현재 일을  &lt;option&gt;문으로 만들어 return
     * @param selectedDay  선택되는 일
     * @return String
     */
    public String getDayOptions(int selectedDay) {
        int endOfMonth = this.getEndDay();
        return getDayOptions(endOfMonth, selectedDay);
    }

    /**
     * 특정 일을 &lt;option&gt;문으로 만들어 return
     * @param timestamp
     * @return String
     */
    public String getDayOptions(Timestamp timestamp) {
        if (timestamp == null) {
            timestamp = new Timestamp(System.currentTimeMillis());
        }
        String year = getDateString(timestamp, "yyyy");
        String month = getDateString(timestamp, "MM");
        String day = getDateString(timestamp, "dd");
        return getDayOptions(
                NumberUtils.toInt(year),
                NumberUtils.toInt(month),
                NumberUtils.toInt(day)
        );
    }

    /**
     * 특정 일을  &lt;option&gt;문으로 만들어 return
     * @param year  틀정일이 속한 년도
     * @param month 특정일이 속한 월
     * @param selectedDay  선택되는 일
     * @return String
     */
    public String getDayOptions(int year, int month, int selectedDay) {
        if (year == 0) year = this.getYear();
        if (month == 0) month = this.getMonthInt();
        if (selectedDay == 0) selectedDay = this.getDayOfMonth();

        // 해당월의 마지막일
        int endOfMonth = this.getAccountDay(String.valueOf(year), String.valueOf(month));
        return getDayOptions(endOfMonth, selectedDay);
    }

    /**
     * 특정 일을  &lt;option&gt;문으로 만들어 return
     * @param endOfMonth    특정일이 속한 마지막  일
     * @param selectedDay  선택되는 일
     * @return String
     */
    public String getDayOptions(int endOfMonth, int selectedDay) {
        if (selectedDay == 0) selectedDay = this.getDayOfMonth();

        StringBuffer dayOptions = new StringBuffer("");
        for (int i=1; i <= endOfMonth; i++) {
            dayOptions.append("<option value='").append(StringUtils.leftPad(String.valueOf(i), 2, "0")).append("'");
            if (i == selectedDay) {
                dayOptions.append(" selected");
            }
            dayOptions.append(">").append(StringUtils.leftPad(String.valueOf(i), 2, "0")).append(" 일").append("</option>");
            dayOptions.append("\n");
        }
        return dayOptions.toString();
    }

    /**
     * 월을 2자리 String으로 만들어 return
     * @return 2자리 월
     */
    public String getMonthDigit() {
        return StringUtils.leftPad(String.valueOf(this.getMonthInt()), 2, "0");
    }

    /**
     * 일을 2자리 String으로 만들어 return
     * @return 2자리 일
     */
    public String getDayDigit() {
        return StringUtils.leftPad(String.valueOf(this.getDayOfMonth()), 2, "0");
    }

    /**
     * 날자를 8자리 String 으로 만들어 return
     * @return 8자리 날자
     */
    public String getDateOct() {
        return this.getYear() + this.getMonthDigit() + this.getDayDigit();
    }

    /**
     * 날자를 8자리 String 으로 만들어 return
     * @return 8자리 날자
     */
    public boolean getFromTo(String sdate, String edate) {
    	boolean rslt = false;
    	String today = getDateOct();

    	if(sdate==null || sdate.equals(""))	return rslt;
    	if(edate==null || edate.equals(""))	return rslt;

    	if(sdate.length()==10) sdate = sdate.substring(0,4)+sdate.substring(5,7)+sdate.substring(8);
    	if(edate.length()==10) edate = edate.substring(0,4)+edate.substring(5,7)+edate.substring(8);

    	if(Integer.parseInt(sdate) <= Integer.parseInt(today)){
        	if(Integer.parseInt(today) <= Integer.parseInt(edate)){
        		rslt = true;
        	}
    	}

    	return rslt;
    }
    
    
    /**
     * Timstamp를 yyyy-mm-dd 형식으로 return
     * @param ts Timestamp형 날짜
     * @return yyyy-mm-dd
     */
    public static String getDateString(Timestamp ts){
        return getDateString(ts, "yyyy-MM-dd");
    }

    /**
     * Timestamp를 지정된 날짜 형식으로 return
     * @param ts Timestamp형 날짜
     * @param format 지정할 포맷
     * @return 지정된 날자 형식 String
     */
    public static String getDateString(Timestamp ts, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.KOREA);
        return sdf.format(ts);
    }
}
