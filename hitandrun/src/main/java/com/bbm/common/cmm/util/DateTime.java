package com.bbm.common.cmm.util;


import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
 
import org.joda.time.format.DateTimeFormat;

public class DateTime {
	/**
	 * 달을 int로 반환
	 */
	public String sep = "";
	public String dateSep = ".";
	public String hourSep = ":";
	
//	private static DateTimeFormatter jodaFormatter = DateTimeFormat.forPattern("yyyyMMdd").withLocale(Locale.KOREA);	
//	private static DateTimeFormatter jodaYearFormatter = DateTimeFormat.forPattern("yyyy").withLocale(Locale.KOREA);	
//	private static DateTimeFormatter jodaMonthFormatter = DateTimeFormat.forPattern("MM").withLocale(Locale.KOREA);	
//	private static DateTimeFormatter jodaDayFormatter = DateTimeFormat.forPattern("dd").withLocale(Locale.KOREA);	
	/**
	 * DateTime 생성자 주석. Don't let anyone instantiate this class
	 */
	private DateTime() {
		super();
	}
	
	/**
	 * <p>@설명 : DateNum 값에 따른 날짜 포멧으로 현재 Date return.</p>
	 * 
	 * <blockquote>
	 * 
	 * <p>예) nowDate(0) = "yyyyMMddHHmmss" </p>
	 * <p>예) nowDate(1) = "yyyy-MM-dd HH:mm:ss" </p>
	 * <p>예) nowDate(2) = "yyyy/MM/dd HH:mm:ss" </p>
	 * <p>예) nowDate(3) = "yyyyMMdd" </p>
	 * <p>예) nowDate(4) = "yyyy-MM-dd" </p>
	 * <p>예) nowDate(5) = "yyyy" </p>
	 * <p>예) nowDate(6) = "MM" </p>
	 * <p>예) nowDate(7) = "dd" </p>
	 * <p>예) nowDate(8) = "HH" </p>
	 * <p>예) nowDate(9) = "mm" </p>
	 * <p>예) nowDate(10) = "HH:mm" </p>
	 * <p>예) nowDate(11) = "HH:mm:ss" </p>
     * <p>예) nowDate(12) = "yyyyMMddHHmmssSS" </p>	 
     * <p>예) nowDate(13) = "yyyyMMddHHmmssSSS" </p>
     * <p>예) nowDate(14) = "HHmmss" </p>   
     * <p>예) nowDate(15) = "HHmmssS" </p>
	 * </blockquote>
	 * @param DateNum - 날짜 포멧을 구분하는 구분자
	 * @return String - 구분자에 따른 날짜 포멧으로 현재 Date return
	 * 
	 */
	public static String nowDate(int DateNum)
	{
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		
		String[] dateType = {
				"yyyyMMddHHmmss",
				"yyyy-MM-dd HH:mm:ss",
				"yyyy/MM/dd HH:mm:ss",
				"yyyyMMdd",
				"yyyy-MM-dd",
				"yyyy",
				"MM",
				"dd",
				"HH",
				"mm",
				"HH:mm",
				"HH:mm:ss",
				"yyyyMMddHHmmssSS",
				"yyyyMMddHHmmssSSS",
				"HHmmss",
				"HHmmssSS" };
		
		return DateTimeFormat.forPattern(dateType[DateNum]).withLocale(Locale.KOREA).print(date.getTime());
	}
	
	/**
	 * <p>@설명 :	 * yyyyMMddHHmmss --> 날짜의 포멧을 바꾼다. </p>
	 * 
	 * <blockquote>
	 * <p>cutString(yyyyMMddHHmmss,-)--> yyyy-MM-dd [HH:mm:ss]</p>
	 * <p>cutString(yyyyMMddHHmmss,:)--> yyyy:MM:dd [HH:mm:ss]</p>
	 * <p>cutString(yyyyMMddHHmmss,/)--> yyyy/MM/dd [HH:mm:ss]</p>
	 * 
	 * </blockquote>
	 * 
	 * @param dateFormat - yyyyMMddHHmmss
	 * @param char_ - 포멧 Character
	 * 
	 * @return String return String
	 * 
	 */
	public static String dateFormat(String dateFormat, char char_) {
		String dateFormat_ ="";
		
		if(dateFormat.length()==14){
			dateFormat_ = dateFormat.substring(0,4)+char_
						+ dateFormat.substring(4,6)+char_
						+ dateFormat.substring(6,8)
						+ "["+dateFormat.substring(8,10)+":"
						+ dateFormat.substring(10,12)+":"
						+ dateFormat.substring(12,14)+"]";
		}else if(dateFormat.length()==12){
			dateFormat_ = dateFormat.substring(0,4)+char_
			+ dateFormat.substring(4,6)+char_
			+ dateFormat.substring(6,8)
			+ "["+dateFormat.substring(8,10)+":"
			+ dateFormat.substring(10,12)+"]";
			
		}else{
			return dateFormat;
		}
		return dateFormat_;

	}

	/**
	 * <p>@설명 : 
	 * 달력을 생성시 년,월을 파라메터로 받아 String[42]의 배열에 날짜를 담는다.
	 * </p>
	 * 
	 * @param year - 년도
	 * @param month - 월
	 *  
	 * @return String[] - String[42]의 배열에 날짜를 만들어 넣는다.
	 * 
	 */
	public static String[] DateArray(int year, int month)
	{
		Calendar ytable = Calendar.getInstance();
		String[] arrayDate = new String[42];
		ytable.set(year, month - 1, 1);
		int sindex = ytable.get(Calendar.DAY_OF_WEEK) - 1;
		int lday = ytable.getActualMaximum(Calendar.DAY_OF_MONTH);
		int sdate = 1;
		for (int i = sindex; i < lday + sindex; i++)
		{
			arrayDate[i] = Integer.toString(sdate++);
		}
		return arrayDate;
	}

	
	public static String getDisDate(String s, String format) throws java.lang.Exception {
		// 날짜 검사
		java.util.Date targetDate = null;

		try {
			targetDate = check(s, "yyyyMMdd");
		} catch (java.text.ParseException pe) {
			return "";
		}

		return DateTimeFormat.forPattern(format).withLocale(Locale.KOREA).print(targetDate.getTime());
	}
	
    /**
     * <p>@설명 :
     * dataFormat에 따른 날짜 포멧으로 입력 날짜로 부터 datesize 이전 또는 이후의 날짜를 반환한다.
     * (입력 포맷과 다른 형태로 출력 가능)
     * </p> 
     * <blockquote>
     * <p>예) reDateFormat(nowDate(0), "DATE","yyyyMMddHHmmss", 0) 
     *          : 오늘  날짜를 "yyyyMMddHHmmss"  형태로 반환</p> 
     * <p>예) reDateFormat(nowDate(0), "DATE","yyyyMMddHHmmss", +1) 
     *          : 다음날의 날짜를 "yyyyMMddHHmmss"  형태로 반환</p>
     * <p>예) reDateFormat("20071231", "DATE","yyyyMMdd", "yyyy-MM-dd HH:mm:ss",  -2) 
     *          : 2007년 12월 31일 이틀전의 날짜를 "yyyy-MM-dd HH:mm:ss"  형태로 반환</p>
     * </blockquote>
     * 
     * @param inDate - 입력일자
     * @param dateFlag - 날짜 포멧을 구분하는 구분자
     * @param dateFormat - 날짜 포멧을 구분하는 구분자
     * @param datesize - 날짜 포멧을 구분하는 구분자
     * @return String - 구분자에 따른 날짜 포멧으로 현재 Date return
     */ 
    public static String reDateFormat(String inDate, String dateFlag, String reDateFormat, int datesize)
    {
        Calendar c = Calendar.getInstance();
        Date date = null;
        String reDate = null;
        try {
            if(inDate == null || "".equals(inDate)){
                reDate = nowDate(3);
            }else{
                reDate = inDate;
            }
            
            date = (new SimpleDateFormat("yyyyMMdd", Locale.KOREA)).parse(reDate);
            
            c.setTime(date);  
            //DateTimeFormat.forPattern("yyyyMMdd").withLocale(Locale.KOREA).parseDateTime(reDate);
            //c.set(Integer.parseInt(reDate.substring(0, 4)), Integer.parseInt(reDate.substring(4, 6)), Integer.parseInt(reDate.substring(6, 8)));
            
            if(dateFlag.equals("YEAR")){
                c.add(Calendar.YEAR, datesize);
            }else if(dateFlag.equals("MONTH")){
                c.add(Calendar.MONTH, datesize);
            }else if(dateFlag.equals("HOUR")){
                c.add(Calendar.HOUR, datesize);
            }else if(dateFlag.equals("MINUTE")){
                c.add(Calendar.MINUTE , datesize);
            }else if(dateFlag.equals("DATE")){
                c.add(Calendar.DATE , datesize);
            }
        } catch(Exception e) {
        	System.err.println("DateTime reDateFormat() Exception Occured");
            return null;
        }
        
        return DateTimeFormat.forPattern(reDateFormat).withLocale(Locale.KOREA).print(c.getTime().getTime());
    }
	    
    /** 
     * <p>@설명 : 현재일자의 문자열로부터 일수를 더한 일자의 문자열을 반환한다.</p>
     * 
	 * <blockquote>
	 * <p>예) reDateFormat("DATE","yyyyMMddHHmmss", 0) 
	 * 			: 오늘 날짜를 "yyyyMMddHHmmss"  형태로 반환</p> 
	 * <p>예) reDateFormat("DATE","yyyyMMddHHmmss", +1) 
	 * 			: 오늘 날짜를 다음날 "yyyyMMdd"  형태로 반환</p>
	 * <p>예) reDateFormat("DATE","yyyyMMdd", -2) 
	 * 			: 오늘 날짜를 이틀전의 날짜로 "yyyyMMdd"  형태로 반환</p>
	 * </blockquote>
	 *  
     * @param dateFlag - 가감할 날짜 영역 선택(년/월/일/시/분/날짜)
     * @param dateFormat - 날짜 포멧을 구분하는 구분자
     * @param datesize - 가감할 날짜
     * @return datesize를 가감한 날짜의 문자열을 반환, 에러인 경우 return null
     */    
    public static String reDateFormat(String dateFlag, String dateFormat, int datesize) {
        return reDateFormat(nowDate(3), dateFlag, dateFormat, datesize);
    }
    
	/**
	 * 경과 월수 구하기 날짜의 형식(Format) 없음.
	 * return add month to date strings
	 * @param String   date string
	 * @param int 더할 월수
	 * @return int 날짜 형식이 맞고, 존재하는 날짜일 때 월수 더하기 형식이 잘못 되었거나 존재하지 않는 날짜:
	 *         java.text.ParseException 발생
	 */
	public static String addMonths(String s, int month) throws Exception {
		return reDateFormat(s, "MONTH", "yyyyMMdd", month);
	}    

	/**
	 * 현재 시스템의 기준일자 -1 일을 리턴하는 메소드
	 * @return 시스템 기준일
	 */
	public static String getPrevDateString() {
		return DateTime.reDateFormat("DATE", "yyyy-MM-dd", -1);
	}	
	
	/**
	 * <p>@설명 : 오늘의 요일 가져오기</p>
	 * 
     * @return String - 오늘의 요일 return
	 * 
	 */		
	public static String nowDayOfWeek() {

	    GregorianCalendar cal = new GregorianCalendar();
	    int day = cal.get(Calendar.DAY_OF_WEEK);	    
	    String[] days = {"일","월","화","수","목","금","토"};
	    return days[day-1];
	  }

	/**
	 * 현재 시각을 패턴으로 요청한 정보에 맞게 리턴한다.
	 *
	 * @param java.lang.String pattern  "yyyy, MM, dd, HH, mm, ss and more"
	 * @return formatted string representation of current day and time with your pattern.
	 */
	public static String getStringByPattern(String pattern) {
		return DateTimeFormat.forPattern(pattern).withLocale(Locale.KOREA).print(new java.util.Date().getTime());
	}	
	
	/**
	 * 현재년도를 반환
	 * For example, int year = DateUtil.getYear();
	 *
	 * @return current year.
	 */
	public static String getYear() {
		return getStringByPattern("yyyy");
	}
	
	/**
	 * 현재년도를 반환
	 * For example, int year = DateUtil.getYear();
	 *
	 * @return current year.
	 */
	public static String getCurrYear() {
		return getStringByPattern("yyyy");
	}	
	
	/**
	 * 현재월을 반환
	 * For example, int month = DateUtil.getMonth();
	 *
	 * @return current month.
	 */
	public static String getMonth() {
		return getStringByPattern("MM");
	}
	
	/**
	 * 현재날짜정보 
	 * For example, int day = DateUtil.getDay();
	 *
	 * @return current day.
	 */
	public static String getDay() {
		return getStringByPattern("dd");
	}

	
	/**
	 * 년월일로 파일저장 경로 추출
	 * For example, int day = DateUtil.getFileSaveDir();
	 *
	 * @return current day.
	 */
	public static String getFileSaveDir() {
		StringBuffer sb = new StringBuffer();
		sb.append(getYear()).append("/");
		//sb.append(File.separator);
		sb.append(getMonth()).append("/");
		//sb.append(File.separator);
		sb.append(getDay()).append("/");
		//sb.append(File.separator);
		
		return sb.toString();
	}
	

	/**
	 * 해당월의 마지막 날짜를 가져온다.
	 * @param dateStr :  입력날짜
	 * @param dateType : 날짜형태
	 * @return String 일자(YYYYMMDD, DD 형태)
	 */
	public static String getLastDay(String dateStr, String dateFormat) {	    
		Calendar calendar = Calendar.getInstance();
	    String lastDay = null;
	    String returnDate = null;	    
	    
	    try {
	      if (dateStr != null && (dateStr.length() == 6 || dateStr.length() == 8)) {
	        int inYear = Integer.parseInt(dateStr.substring(0, 4));
	        int inMonth = Integer.parseInt(dateStr.substring(4, 6)) - 1;
	        
	        if (inMonth < 0 || inMonth > 11) {
	          lastDay = "";
	        }
	        
		    calendar = new java.util.GregorianCalendar(inYear, inMonth, 1);
		    lastDay = String.valueOf(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
	      }
	    } catch (Exception e) {
	    	lastDay = "";
	    }
	    if ("YYYYMMDD".equals(dateFormat)) {
	      returnDate = dateStr.substring(0, 6) + lastDay;
	    } else {
	      returnDate = lastDay;
	    }
	    return returnDate;
	  }
	
	/**
	 * 해당월의 마지막 일자를 가져온다.
	 * @param dateStr :  입력날짜
	 * @param dateType : 날짜형태
	 * @return String 일자
	 */	
	public static String getLastDay(String dateStr) {	    
		return getLastDay(dateStr, "");
	}
	
	/**
	 * 해당 년, 월의 마지막 일자를 구하는 Method.
	 * 
	 * @param year
	 * @param month
	 * @return 마지막 일자
	 */
	public static int getLastDate(String dateStr) {
		return Integer.parseInt(getLastDay(dateStr, ""));
	}
	
	/**
	 * 해당 년, 월의 마지막 일자를 구하는 Method.
	 * 
	 * @param year
	 * @param month
	 * @return 마지막 일자
	 */
	public static int getNowLastDate() {
		return getLastDate(nowDate(3));
	}	
	
	/**
	 * 해당 년도의 전년도를 리턴
	 * 
	 * @return lastYear
	 */
	public static String getLastYear() {
		Calendar cal = Calendar.getInstance();
		return String.valueOf(cal.get(Calendar.YEAR) - 1);
	}
	
	/**
	 * 해당 년도의 전전년도를 리턴
	 * 
	 * @return yearBeforeLast
	 */
	public static String getYearBeforeLast() {
		Calendar cal = Calendar.getInstance();
		return String.valueOf(cal.get(Calendar.YEAR) - 2);
	}
	
	/**
	 * 해당 년도의 입력받은 년도를 계산하여  리턴
	 * 
	 * @return getYearBeforeLastCalc
	 */
	public static String getYearBeforeAfterCalc(int year) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, year);
		return String.valueOf(cal.get(Calendar.YEAR));
	}	
		
    /**
     * <p>@설명 : 두 날짜 사이의 일수 차이를 구한다 (fromDate, toDate)</p>
     * @param fromDate, toDate
     * @return fromDate, toDate간 차이 일수를 반환
     */
    public static int getDayBetween(Date fromDate, Date toDate) {
        Calendar fromCal = Calendar.getInstance();
        Calendar toCal = Calendar.getInstance();
        // Calendar tmpCal = Calendar.getInstance();

        fromCal.setTime(fromDate);
        toCal.setTime(toDate);

        //int nFromYear = fromCal.get(Calendar.YEAR);
        //int nToYear = toCal.get(Calendar.YEAR);
        
        //시분초까지 계산하므로, 일자로 계산하는 경우,
        //fromDate와 toDate의 hhMMdd가 '000000' 이어야 오차가 없다
        long nFromDate = fromCal.getTime().getTime();
        long nToDate = toCal.getTime().getTime();
        
        //이전 년도에대한 일자를 계산 (윤년 포함)
        /*int nCheckDate = 0;
        for (int i=nFromYear ;i<nToYear;i++) {
            tmpCal.set(i, Calendar.DECEMBER, 31);
            nCheckDate += tmpCal.get(Calendar.DAY_OF_YEAR);
        }
        */
        return (int)((nToDate - nFromDate)/(24*60*60*1000));
    }  

    /**
     * <p>@설명 : 두 날짜 사이의 일수 차이를 구한다 (fromDateStr, toDateStr)</p>
     * @param fromDateStr, toDateStr
     * @return fromDateStr, toDateStr간 차이 일수를 반환
     */
    public static int getDayBetween(String fromDateStr, String toDateStr) throws ParseException {
        Date fromDate = getStrToDate( fromDateStr, "yyyyMMdd" );
        Date toDate   = getStrToDate( toDateStr, "yyyyMMdd" );
        
        return getDayBetween(fromDate, toDate);
    }


   /**
    * 문자를 날짜 타입으로 리턴한다.
    * @param String dateString
    * @return java.util.Date
    */
  public static Date getStrToDate(String s, String format) throws ParseException
  {
     if ( s == null ){
        throw new ParseException("date string to check is null", 0);
     }
     if ( format == null ) {
        format = "yyyyMMdd";
     }

     SimpleDateFormat formatter = new SimpleDateFormat(format, java.util.Locale.KOREA);
     java.util.Date date = null;

     try {
        date = formatter.parse(s);
     }
     catch(ParseException e) {
        throw new ParseException(" wrong date:\"" + s + "\" with format \"" + format + "\"", 0);
     }

     if ( ! formatter.format(date).equals(s) ) {
        throw new ParseException("Out of bound date:\"" + s + "\" with format \"" + format + "\"", 0);
     }

     return date;
  }
 
	/**
	 * 현재의 날짜를 기준으로 YYYYMMDDHHMMSS 형태로 변환
	 * 
	 * @return YYYYMMDD
	 *  
	 */
	public static String getDate() {
		return DateTimeFormat.forPattern("yyyyMMddHHmmss").withLocale(Locale.KOREA).print(new java.util.Date().getTime());
	}
	
	/**
	 * 날자에 해당하는 요일을 구하는 메소드
	 * 
	 * @param date
	 *            요일을 구하고자 하는 날자(yyyy-MM-dd)
	 * @return 해당 날자에 대한 요일(한글약어)
	 */
	public static String getDate(String date) {
		return getDate(date, 1);
	}
	
	/**
	 * 날자에 해당하는 요일을 구하는 메소드
	 * 
	 * @param date
	 *            요일을 구하고자 하는 날자(yyyy-MM-dd)
	 * @param type
	 *            요일에 대한 type (1:한글약어, 2:한글, 3:영문약어, 4:영문)
	 * @return 해당 날자에 대한 요일을 type에 따른 명칭으로 return
	 */
	public static String getDate(String date, int type) {
		if (date.trim() == null) {
			return "";
		}
		else if (date.trim().length() < 8) {
			return date.trim();
		}

		date = StringUtil.removeCharAll(date);

		date = date.trim().substring(0, 8);

		String[] week = null;

		DateFormatSymbols symbol = new DateFormatSymbols(Locale.KOREA);

		int orgYear = Integer.parseInt(date.substring(0, 4));
		int orgMon = Integer.parseInt(date.substring(4, 6)) - 1;
		int orgDay = Integer.parseInt(date.substring(6));

		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(orgYear, orgMon, orgDay);

		if (type == 1){
			week = new String[] { "", "일", "월", "화", "수", "목", "금", "토" };
		}
		else if (type == 2) {
			week = new String[] { "", "일요일", "월요일", "화요일", "수요일", "목요일", "금요일",	"토요일" };
		}
		else if (type == 3) {
			week = new String[] { "", "SUN", "MON", "TUE", "WED", "THU", "FRI",	"SAT" };
		}
		else {
			week = new String[] { "", "SUNDAY", "MONDAY", "TUESDAY","WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY" };
		}

		symbol.setShortWeekdays(week);
		SimpleDateFormat sdf = new SimpleDateFormat("E", symbol); // 요일표시

		return sdf.format(cal.getTime());
	}	  	
  
	
	/**
	 * 8자리 연월일 문자열을 받아서 유효한 날짜인지 검증
	 * 
	 * @param date
	 *            String 형태의 검증할 날짜
	 * @return 유효한 주민등록번호인지 여부 true/false
	 */
	public static boolean isValidDate(String date) {
		try {
			if (date.length() != 8) { // 8자리 연월일만 받음
				return false;
			}

			if (StringUtil.getInteger(date) == 0) { // 8자리 숫자가 아니면 false
				return false;
			}

			if (Integer.parseInt(date.substring(0, 4)) < 1900
					|| Integer.parseInt(date.substring(0, 4)) > 9999) {
				return false;
			}
			if (Integer.parseInt(date.substring(4, 6)) < 1
					|| Integer.parseInt(date.substring(4, 6)) > 12) {
				return false;
			}
			if (Integer.parseInt(date.substring(6, 8)) < 1
					|| Integer.parseInt(date.substring(6, 8)) > Integer.parseInt(getLastDay(date)) ) {
				return false;
			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	
	

	/**
	 * 원하는 년도와 분기를 구한다.
	 * 
	 * @param month
	 * 
	 * @return year, quarter
	 */
	public static String getYearQuarter(int month) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, month);
		String quarter = "";
		String year = String.valueOf(cal.get(Calendar.YEAR)).substring(2, 4);
		if((cal.get(Calendar.MONTH) / 3 + 1) < 1) {
			quarter = String.valueOf((cal.get(Calendar.MONTH) / 3 + 1) + 4);
		} else if((cal.get(Calendar.MONTH) / 3 + 1) > 4) {
			quarter = String.valueOf((cal.get(Calendar.MONTH) / 3 + 1) - 4);
		} else {
			quarter = String.valueOf(cal.get(Calendar.MONTH) / 3 + 1);
		}
		return year + "/0" + quarter;
	}
	
	/**
	 * 원하는 년도와 월을 구한다.
	 * 
	 * @param month
	 * 
	 * @return year, quarter
	 */
	public static String getYearMonth(int month) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, month);
		String m = String.valueOf((cal.get(Calendar.MONTH))+1);
		String year = String.valueOf(cal.get(Calendar.YEAR));
		
		if(m.length() == 1) {
			return year + "/0" + m;
		}
		return year +"/"+ m;
	}
	
	/**
	 * 원하는 년도와 분기를 구하여 배열로 리턴
	 * 
	 * @param month
	 * 
	 * @return 배열 year, quarter
	 */
	public static String[] getFullYearQuarter(int month) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, month);
		String[] retStr = new String[2];
		retStr[0] = String.valueOf(cal.get(Calendar.YEAR));
		if((cal.get(Calendar.MONTH) / 3 + 1) < 1) {
			retStr[1] = String.valueOf((cal.get(Calendar.MONTH) / 3 + 1) + 4);
		} else if((cal.get(Calendar.MONTH) / 3 + 1) > 4) {
			retStr[1] = String.valueOf((cal.get(Calendar.MONTH) / 3 + 1) - 4);
		} else {
			retStr[1] = String.valueOf(cal.get(Calendar.MONTH) / 3 + 1);
		}
		return retStr;
	}

	/**
	 * 원하는 분기를 구한다.
	 * 
	 * @param month
	 * 
	 * @return year, quarter
	 */
	public static String getQuarter(int month) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, month);
		String quarter = "";
		if((cal.get(Calendar.MONTH) / 3 + 1) < 1) {
			quarter = String.valueOf((cal.get(Calendar.MONTH) / 3 + 1) + 4);
		} else if((cal.get(Calendar.MONTH) / 3 + 1) > 4) {
			quarter = String.valueOf((cal.get(Calendar.MONTH) / 3 + 1) - 4);
		} else {
			quarter = String.valueOf((cal.get(Calendar.MONTH) / 3 + 1));
		}
		return quarter;
	}
	
	/**
	 * 원하는 분기에 해당하는 년도를 구한다.
	 * @param month
	 * @return year, quarter
	 */
	public static String getYearOfQuarter(int month) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, month);
		return String.valueOf(cal.get(Calendar.YEAR));
	}

	/**
	 * 최근 8개월 나타냄
	 * @param month
	 * @return year, quarter
	 */
	public static String[] getMonthLastEight() {
		Calendar cal = Calendar.getInstance();
		String year = "";
		String month = "";
		String[] retTitle = new String[8];
		for (int i = -7; i < 1; i++) {
			cal.add(Calendar.MONTH, i);
			year = String.valueOf(cal.get(Calendar.YEAR)).substring(2, 4);
			month = String.valueOf(cal.get(Calendar.MONTH)+1);
			retTitle[i+7] = year + "/"+ (Integer.parseInt(month) < 10 ? "0" + month : month);
			cal.add(Calendar.MONTH, -i);
		}
		return retTitle;
	}
	
	/**
	 * 최근 8개월의 년도와 달을 가져옴 
	 * @param month
	 * @return 배열 year, month
	 */
	public static String[] getFullYearMonth(int month) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, month);
		String[] retStr = new String[2];
		retStr[0] = String.valueOf(cal.get(Calendar.YEAR));
		for (int i = -7; i < 1; i++) {
			cal.add(Calendar.MONTH, i);
			retStr[0] = String.valueOf(cal.get(Calendar.YEAR));
			retStr[1] = String.valueOf(cal.get(Calendar.MONTH)+1);
			cal.add(Calendar.MONTH, -i);
		}
		return retStr;
	}
	
	/**
	 * 현재 일자를 지정된 형식으로 int형으로 변환
	 * For example, String time = DateTime.getFormatString("yyyy-MM-ddHH:mm:ss");
	 * @param java.lang.String
	 *            pattern "yyyy, MM, dd, HH, mm, ss and more"
	 * @return formatted string representation of current day and time with your
	 *         pattern.
	 */
	public static int getNumberByPattern(String pattern) {
		return Integer.parseInt(DateTimeFormat.forPattern(pattern).withLocale(Locale.KOREA).print(new java.util.Date().getTime()));
	}
	
	/**
	 * 현재 일자 구하기
	 * 
	 * @return formatted string representation of current day with "yyyy.MM.dd".
	 */
	public static String getDateString() {
		return nowDate(3);
	}

	/**
	 * <pre>
	 * 
	 *  날짜형(yyyy?MM?dd) 데이터의 delimiter 제거
	 *  &quot;2003/09/09&quot; ==&gt; &quot;20030909&quot;
	 *  &quot;2004.10.10&quot; ==&gt; &quot;20041010&quot;
	 *  
	 * </pre>
	 * 
	 * @param str
	 *            날짜포맷의 문자열("yyyy?MM?dd")
	 * @param delim
	 *            구분자
	 * 
	 * @return 포맷된 결과 문자열 ("yyyyMMdd")
	 * @throws Exception
	 */
	public static String delimCutDate(String str, String delim)
			throws Exception {
  
		str = FormatUtil.strTrim(str);

		if (str.indexOf(delim) == -1) {
			return str;
		}

		if (str.length() != 10) {
			str = FormatUtil.replace(str, delim, "");
			return str;
		}

		if (String.valueOf(str.charAt(4)).equals(delim)
				&& String.valueOf(str.charAt(7)).equals(delim)) {
			str = str.substring(0, 4) + str.substring(5, 7)
					+ str.substring(8, 10);
		}

		return str;
	}

	
	/**
	 * 날자 데이터를 yyyy-MM-dd 형식으로 전환하는 메소드
	 * 
	 * @param dfDate
	 * @return
	 */
	public static String formDate(Date dfDate) {
		return DateTimeFormat.forPattern("yyyy-MM-dd").withLocale(Locale.KOREA).print(dfDate.getTime());
	}
	
	/**
	 * 날자 데이터를 yyyy-MM-dd 형식으로 전환하는 메소드
	 * 
	 * @param date
	 *            변환하고자 하는 날자 (yyyyMMdd)
	 * @return 변환된 날자. 예) 20001212 인 경우 2000-12-12 로 리턴
	 */
	public static String formDate(String date) {
		return formDate(date, "-");
	}
	
	/**
	 * 날자 데이터를 yyyy/MM/dd 형식으로 전환하는 메소드
	 * 
	 * @param date
	 *            변환하고자 하는 날자 (yyyyMMdd)
	 * @param delimiter
	 *            년,월,일을 구분하고자 하는 구분자
	 * @return 구분자로 구분된 날자
	 */
	public static String formDate(String date, String delimiter) {
		if (date == null){
			return "";
		}
		else if (date.trim().length() < 8){
			return date.trim();
		}

		String result = null;
		date = date.trim().substring(0, 8);

		if (date.equals("00010101")){
			result = "";
		}
		else if (date.equals("00000000")) {
			result = "";
		}
		else {
			result = date.substring(0, 4) + delimiter + date.substring(4, 6)+ delimiter + date.substring(6);
		}

		return result;
	}	
	
	

	/**
	 * 주어진 년,월,일이 해당월의 몇번째 주인지 알려준다.
	 * 해당 월의 첫번째 월요일이 첫번째 주가 된다.
	 * 만약 해당 일이 첫번째 월요일보다 앞에 있으면 그 전 달의 마지막째 주가 리턴 된다. 
	 * 
	 * @param year
	 *				년
	 * @param month
	 *				월
	 * @param date
	 *				일
	 * @return 해당 년,월과 해당월의 몇번째 주
	 * 
	 * @author 김정환
	 */
	public static final int[] getYearMonthDateOfWeek(int year,int month,int date) {
		int[] result = new int[3];
		Calendar calendar = new GregorianCalendar(year,month-1,date);

		int day = calendar.get(Calendar.DAY_OF_WEEK);
		int week = calendar.get(Calendar.WEEK_OF_MONTH);

		if (day == 1 || week == 1) {
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-1);
		}

		calendar.get(Calendar.DATE);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		int weekDay = calendar.get(Calendar.DATE);

		result[0] = calendar.get(Calendar.YEAR);
		result[1] = calendar.get(Calendar.MONTH) + 1;

		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int count = 0;
		for(int i=0;i<maxDay;i++) {
			calendar.set(Calendar.DATE, (i+1));
			if(calendar.get(Calendar.DAY_OF_WEEK)==2) {
				count++;
				if(weekDay == (i+1)) {
					result[2] = count;
				}
			}
		}
		return result;
	}	
	
	

	/**
	 * 주어진 년,월의 해당주차의 시작일자와 마지막일자를 반환한다.
	 * 
	 * @param year
	 *            년
	 * @param month
	 *            월
	 * @param week
	 *            시작일자와 마지막일자를 찾고자 하는 주
	 * @return 시작일자, 마지막일자
	 * 
	 * @author 김정환
	 */
	public static final String[] getStartEndOfWeeks2(int year,int month,int week) {
		String[] result = new String[2];

		Calendar calendar = new GregorianCalendar(year,month-1,1);

		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int count = 0;
		int firstDay = 0;
		for(int i=1;i<maxDay;i++) {
			calendar.set(Calendar.DATE, i);
			if(calendar.get(Calendar.DAY_OF_WEEK)==2) {
				count++;
				if(count == week) {
					firstDay = i;
				}else if(count < week){
					firstDay = maxDay;
				}
			}
		}
		calendar.set(Calendar.DATE, firstDay);
		calendar.get(Calendar.DATE);

		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		result[0] = getDate(calendar);

		calendar.add(Calendar.DATE, 6);
		result[1] = getDate(calendar);

		return result;
	}
	
	/**
	 * 입력된 Calendar의 날짜를 'YYYYMMDD' 형태의 String으로 반환하는 Method.
	 * 
	 * @param calendar
	 * @return 날짜 String 'YYYYMMDD'
	 */
	public static final String getDate(Calendar calendar) {
		String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
		if (month.length() == 1){
			month = "0" + month;
		}
		String date = Integer.toString(calendar.get(Calendar.DATE));
		if (date.length() == 1){
			date = "0" + date;
		}
		return calendar.get(Calendar.YEAR) + month + date;
	}
	
	
	/**
	 * 년,월,일에 대한 증가값을 넣어 특정일을 구하는 메소드(제거 함수)
	 * 
	 * @param date
	 *            기준일자
	 * @param addYear
	 *            기준일자에 더할 년수
	 * @param addMonth
	 *            기준일자에 더할 월수
	 * @param addDay
	 *            기준일자에 더할 일수
	 * @return 기준일자에 년,월,일을 각각 더한 일자.
	 */
	public static String getAddDate(String date, int addYear, int addMonth,
			int addDay) {
		if (date == null) {
			return "";
		}
		else if (date.trim().length() < 8) {
			return date.trim();
		}

		date = StringUtil.removeCharAll(date);

		int orgYear = Integer.parseInt(date.substring(0, 4));
		int orgMonth = Integer.parseInt(date.substring(4, 6));
		int orgDay = Integer.parseInt(date.substring(6, 8));

		Calendar cal = Calendar.getInstance();

		cal.set(orgYear, orgMonth - 1, orgDay);

		cal.add(Calendar.YEAR, addYear);
		cal.add(Calendar.MONTH, addMonth);
		cal.add(Calendar.DATE, addDay);

		String rDate = String.valueOf(cal.get(Calendar.YEAR));

		if (cal.get(Calendar.MONTH) + 1 < 10) {
			rDate = rDate + "0" + (cal.get(Calendar.MONTH) + 1);
		} else {
			rDate = rDate + (cal.get(Calendar.MONTH) + 1);
		}

		if (cal.get(Calendar.DAY_OF_MONTH) < 10) {
			rDate = rDate + "0" + cal.get(Calendar.DAY_OF_MONTH);
		} else {
			rDate = rDate + cal.get(Calendar.DAY_OF_MONTH);
		}

		return rDate;
	}	
	
	

	/**
	 * 년월일과 원하는 int를 입력받아 결과 날짜를 return(제거 함수)
	 * 
	 * @param date
	 *            기준일자
	 * @param cc
	 *            추가할 일수
	 * @return 기준일자에 cc가 추가된 일자
	 */
	public static String getAddDate(String date, int cc) {
		String yyyymmdd = StringUtil.removeCharAll(date);

		String wantday = "";

		DecimalFormat M = new java.text.DecimalFormat("00");
		int yyyy = Integer.parseInt(yyyymmdd.trim().substring(0, 4));
		int mm = Integer.parseInt(yyyymmdd.trim().substring(4, 6));
		int dd = Integer.parseInt(yyyymmdd.trim().substring(6, 8));

		Calendar c = Calendar.getInstance();
		c.set(yyyy, mm - 1, dd);

		c.add(Calendar.DATE, cc);

		wantday = (c.get(Calendar.YEAR) + ""
				+ M.format(c.get(Calendar.MONTH) + 1) + "" + M.format(c
				.get(Calendar.DATE)));

		return wantday;
	}	
	
	/**
	 * 주어진 년의 전달을 리턴
	 * 
	 * @param year
	 *				년
	 * @param month
	 *				월
	 * @param date
	 *				일
	 * @return 해당 년의 전달
	 * 
	 * @author 김정환
	 */
	public static final int[] getYearMonth(int year,int month,int date) {
		int[] result = new int[2];
		Calendar calendar = new GregorianCalendar(year,month-1,date);

		result[0] = calendar.get(Calendar.YEAR);
		result[1] = calendar.get(Calendar.MONTH)+1;

		return result;
	}	
	
	
	/**
	 * 기간 구하기 날짜의 형식(Format) 없음.
	 * 
	 * return days between two date strings with default defined
	 * format.("yyyyMMdd")
	 * 
	 * @param String
	 *            from date string
	 * @param String
	 *            to date string
	 * @return int 날짜 형식이 맞고, 존재하는 날짜일 때 2개 일자 사이의 나이 리턴 형식이 잘못 되었거나 존재하지 않는 날짜:
	 *         java.text.ParseException 발생
	 */
	public static int daysBetween(String from, String to) throws java.text.ParseException {
		return daysBetween(from, to, "yyyyMMdd");
	}	
	
	/**
	 * 일자 사이의 기간 구하기
	 * 
	 * return days between two date strings with user defined format.
	 * 
	 * @param String
	 *            from date string
	 * @param String
	 *            to date string
	 * @return int 날짜 형식이 맞고, 존재하는 날짜일 때 2개 일자 사이의 일자 리턴 형식이 잘못 되었거나 존재하지 않는 날짜:
	 *         java.text.ParseException 발생
	 */
	public static int daysBetween(String from, String to, String format) throws java.text.ParseException {
		 
		// 날짜 검사
		java.util.Date d1 = check(from, format);
		java.util.Date d2 = check(to, format);

		long duration = d2.getTime() - d1.getTime();

		return (int) (duration / (1000 * 60 * 60 * 24));
	}	
	
	/**
	 * 일자 검사
	 * 
	 * check date string validation with an user defined format.
	 * 
	 * @param s
	 *            date string you want to check.
	 * @param format
	 *            string representation of the date format. For example,
	 *            "yyyy-MM-dd".
	 * @return date java.util.Date
	 */
	public static Date check(String s, String format)	throws java.text.ParseException {
		// 파라메터 검사
		if (s == null) {
			throw new java.text.ParseException("date string to check is null",0);
		}
		if (format == null) {
			throw new java.text.ParseException("format string to check date is null", 0);
		}

		// 날짜 형식 지정
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);

		// 검사
		java.util.Date date = null;

		try {
			date = formatter.parse(s);
		} catch (java.text.ParseException e) {
			throw new java.text.ParseException(" wrong date:\"" + s + "\" with format \"" + format + "\"", 0);
		}

		// 날짜 포멧이 틀린 경우
		if (!formatter.format(date).equals(s)) {
			throw new java.text.ParseException("Out of bound date:\"" + s + "\" with format \"" + format + "\"", 0);
		}

		// 리턴
		return date;
	}
	
	
	/**
	 * <pre>
	 * 
	 *       영문달을 숫자로 변환한다
	 *  
	 * </pre>
	 * 
	 * @param String
	 *            strSource 영문월 String ex)jan - january(1월)
	 * 
	 * @return 소스 String의 영문월 포맷을 숫자형으로 변경한 String ex)'01'
	 */
	public static String convertEngToMM(String strSource) {
		String upstrSource = StringUtil.null2space(strSource).toUpperCase();
		
		if (upstrSource.equals("JAN")) {
			return "01";
		}else if (upstrSource.equals("FEB")) {
			return "02";
		}else if (upstrSource.equals("MAR")) {
			return "03";
		}else if (upstrSource.equals("APR")) {
			return "04";
		}else if (upstrSource.equals("MAY")) {
			return "05";
		}else if (upstrSource.equals("JUN")) {
			return "06";
		}else if (upstrSource.equals("JUL")) {
			return "07";
		}else if (upstrSource.equals("AUG")) {
			return "08";
		}else if (upstrSource.equals("SEP")) {
			return "09";
		}else if (upstrSource.equals("OCT")) {
			return "10";
		}else if (upstrSource.equals("NOV")) {
			return "11";
		}else if (upstrSource.equals("DEC")) {
			return "12";
		}else{
			return "00";
		}
	}	
	
	
	/**
	 * 년월일 사이에 '-'를 첨가한다. "yyyymmdd" -> "yyyy-mm-dd"
	 * 
	 * @return java.lang.String
	 * @param str
	 *            날짜(yyyymmdd)
	 */
	public static String dashDate(String str) {

		String temp = null;
		if (str == null){
			str = "";
		}

		int len = str.length();

		if (len != 8 || str.equals("        ")){
			return str;
		}
		if ((str.equals("00000000")) || (str.equals("       0"))){
			return "";
		}
		temp = str.substring(0, 4) + "-" + str.substring(4, 6) + "-"+ str.substring(6, 8);

		return temp;
	}	
	
	
	/**
	 * 년월을 한글로 표시한다 "yyyymm" -> "yyyy년 mm월"
	 * 
	 * @return java.lang.String
	 * @param str
	 *            날짜(yyyymm)
	 */
	public static String hanYM3(String str) {
		
		String temp = null;
		if (str == null){
			return "";
		}
		int len = str.length();
		
		if (len == 4){
			return str + "년";
		}
		if (len != 6) {
			return str;
		}
		if ((str.equals("000000")) || (str.equals("     0"))) {
			return "";
		}
		temp = str.substring(0, 4) + "년 "+ Integer.parseInt(str.substring(4, 6)) + "월";
		
		return temp;
	}
	

	/**
	 * <pre>
	 * 
	 *  입력된 날짜(&quot;yyyyMMdd&quot;) 문자열을 한글로 표시한다.
	 *  &quot;yyyyMMdd&quot; --&gt; &quot;yyyy년 MM월 dd일&quot;
	 *  
	 * </pre>
	 * 
	 * @param str
	 *            날짜 ("yyyyMMdd" 포맷)
	 * 
	 * @return 날짜 문자열 ("yyyy년 MM월 dd일")
	 */
	public static String hanDate(String str) {

		String temp = null;
		int len = 0;

		if (str == null) {
			return "";
		}

		len = str.length();

		if (len != 8) {
			return str;
		}

		temp = str.substring(0, 4) + "년 "
				+ Integer.parseInt(str.substring(4, 6)) + "월 "
				+ Integer.parseInt(str.substring(6, 8)) + "일";

		return temp;
	}

	
	
	/**
	 * 년월일을 한글로 표시한다 "yyyymmdd" -> "yyyy년 mm월 dd일"
	 * 
	 * @return java.lang.String
	 * @param str
	 *            날짜(yyyymmdd)
	 */
	public static String hanDate3(String str) {

		String temp = null;
		if (str == null){
			return "";
		}
		int len = str.length();

		if (len != 8){
			return str;
		}
		if ((str.equals("00000000")) || (str.equals("       0"))){
			return "";
		}
		temp = str.substring(0, 4) + "년 "
				+ Integer.parseInt(str.substring(4, 6)) + "월 "
				+ Integer.parseInt(str.substring(6, 8)) + "일";

		return temp;
	}	
	
	/**
	 * 년월을 한글로 표시한다 "yyyymm" -> "yyyy년 mm월"
	 * 
	 * @return java.lang.String
	 * @param str
	 *            날짜(yyyymm)
	 */
	public static String hanYM2(String str) {

		String temp = null;
		if (str == null){
			return "";
		}
		int len = str.length();

		if (len != 6){
			return str;
		}
		if ((str.equals("000000")) || (str.equals("     0"))){
			return "";
		}
		temp = str.substring(0, 4) + "년 "+ Integer.parseInt(str.substring(4, 6)) + "월";

		return temp;
	}	
	
	/**
	 * <pre>
	 * 
	 *  시간(&quot;HHmmss&quot; 또는 &quot;HHmm&quot;) 문자열을 포맷한다.
	 *  ( ex. &quot;134050&quot; ==&gt; &quot;13:40:50&quot;, &quot;1340&quot; ==&gt; &quot;13:40&quot; )
	 *  
	 * </pre>
	 * 
	 * @param time
	 *            시간 문자열 ("HHmmss" 또는 "HHmm")
	 * 
	 * @return 시간 문자열 ("HH:mm:ss" 또는 "HH:mm")
	 * @throws Exception
	 */
	public static String stringToTime(String time) throws Exception {
		String convert;

		if (time == null || FormatUtil.strTrim(time).equals("")
				|| (time.length() != 4 && time.length() != 6)) {
			return time;
		}

		time = FormatUtil.strTrim(time);

		if (time.length() == 4) {
			convert = time.substring(0, 2) + ":" + time.substring(2, 4);
		} else {
			convert = time.substring(0, 2) + ":" + time.substring(2, 4) + ":"
					+ time.substring(4, 6);
		}

		return convert;
	}	
	
	/**
	 * 지정한 구분자로 포맷된 시스템 날짜를 구한다. ("yyyy?MM?dd" 형태)
	 * 
	 * @param deli
	 *            구분자
	 * 
	 * @return "yyyy?MM?dd" 형식의 시스템 날짜
	 * @throws Exception
	 */
	public static String currentDateFmt(String deli) throws Exception {
		return formDate(nowDate(3), deli);
	}	
}