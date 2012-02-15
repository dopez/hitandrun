package com.bbm.common.cmm.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
 

/**
 * <pre>
 *
 * 숫자, 금액 형식 포맷 및 환률 관련 처리를 하는 클래스
 *
 * update history
 * @since 2003/01/01
 * </pre>
 */
public class NumberUtil {
               
    public static final int WON = 1;
    public static final int DOLLAR = 2;
    
	/**
     * <pre>
	 * 숫자형 String을 원화 금액 포맷으로 변환한다.
	 *
	 * ex.
	 * NumberUtil.money("6480000") --> 6,480,000
	 *
	 * @param String money 금액
	 * @return String 포맷된 금액 문자열
	 */
    public static String money(String money) throws Exception {
        
        try {                      
            return money(money, WON);
        } catch(NumberFormatException nfe) {
			throw new Exception("[NumberUtil.money] 금액포맷으로 변환하는중 에러가 발생하였습니다. 숫자(" + money +")가 아닙니다. "+nfe.toString());
		}
    }
	
	/**
     * <pre>
	 * double형을 원화 금액 포맷으로 변환한다.
	 *
	 * ex.
	 * NumberUtil.money(6480000) --> 6,480,000
	 *
	 * @param double money 금액
	 * @return String 포맷된 금액 문자열
	 */
	public static String money(double money) throws Exception {
        			
		try {				
            return money(money, WON);	
		} catch(Exception e) {
			throw new Exception("[NumberUtil.money] 금액포맷으로 변환하는중 에러가 발생하였습니다. "+e.toString());
		}
	}	
	
	/**
     * <pre>
	 * 숫자형 String을 언어구분에 따라 금액포맷으로 변환한다.
	 * 언어구분 lang은 NumberUtil.WON 또는 NumberUtil.DOLLAR 두가지를 사용할 수 있다.
	 *
	 * ex.
	 * NumberUtil.money("6480000.50", NumberUtil.DOLLAR) --> 6,480,000.50
	 *
	 * @param String money 금액
	 * @param int lang 언어구분
	 * @return String 포맷된 금액 문자열
	 */
    public static String money(String money, int lang) throws Exception {
        
        money = money.trim();
          
        if (money == null) {
			return "0";
		}
			
		if (money.equals("")) {
			return "0";
		}
		
		double dataMoney = 0.0;	
		
        try {                      
            //dataMoney = Double.parseDouble(money);
            BigDecimal bd = new BigDecimal(money);		    
		    dataMoney = bd.doubleValue();
        } catch(NumberFormatException nfe) {
			throw new Exception("[NumberUtil.money] 금액포맷으로 변환하는중 에러가 발생하였습니다. 숫자(" + money +")가 아닙니다 "+nfe.toString());
		}
		
        return money(dataMoney, lang);	
		
    }  
	
	/**
     * <pre>
	 *
     * double형을 언어구분에 따라 금액포맷으로 변환한다.
	 * 언어구분 lang은 NumberUtil.WON 또는 NumberUtil.DOLLAR 두가지를 사용할 수 있다.
	 *
	 * ex.
	 * NumberUtil.money(6480000.50, NumberUtil.DOLLAR) --> 6,480,000.50
	 *
	 * @param double money 금액
	 * @param int lang 언어구분
	 * @return String 포맷된 금액 문자열
	 */
	public static String money(double money, int lang) throws Exception {
        
		try {
            
			String language = null;
			String country = null;
			Locale locale = null;
			DecimalFormat f = null;
            
			switch (lang) {
                
			    case WON:
			        language = "ko";
			        country = "KR";
			        break;
			    case DOLLAR:
			        language = "en";
			        country = "US";
			    default:
			}

			locale = new Locale(language, country);
            
			f = (DecimalFormat)NumberFormat.getCurrencyInstance(locale); 
			f.setPositivePrefix("");
			f.setNegativePrefix("-");
            					
			return f.format(money);
            		
		} catch(Exception e) {
			throw new Exception("[NumberUtil.money] 금액포맷으로 변환하는중 에러가 발생하였습니다. "+e.toString());
		}
        
	}	
	
	/** 
     * <pre>
	 * double형을 ","가 없는 숫자 문자열 포맷으로 변환한다.
	 *
	 *
	 * @param double digit 금액
	 * @return String 포맷된 금액 문자열
	 */
    public static String digit(double digit) throws Exception {
                
		String s = money(digit);		
        s = StringUtil.removeCharacter(s, ",");
        		
        return s;			
    }  	
	
	/**
     * <pre>
	 * 숫자 문자열 앞의 "0"을 제거한다. removeZero(String s,"01")과 동일하다.
     *
	 * @param String s
	 * @param String gubun
	 * @return String 
	 */
	public static String removeZero(String s) {
		return removeZero(s, "01");
	}

	/**
     * <pre>
	 * 숫자 문자열 앞의 "0"을 제거한다.
	 * 단, gubun이 "01"이 아니면 아래와 같이 변환한다.
	 * "01" -> "01", "012" -> "012", "0123" -> "123", "01234" -> "1234" ...
	 * "0" -> "0", "00" -> "00", "001" -> "001", "0012" -> "012", "00123" -> "123" ...
     *
	 * @param String s
	 * @param String gubun
	 * @return String 
	 */
	public static String removeZero(String s,String gubun) {
        
		StringBuffer temp = new StringBuffer(s.trim());
        
		int len = 0;
        
		//if(gubun.equals("01")) len = temp.length();
        
		if( gubun.equals("01") || gubun.equals("02") ) {len = temp.length();
		}else{ len = temp.length()-3;}

		for(int i=0;i<len;i++) {
			if(temp.charAt(i) == '0') {
				if(i == (len-1)) {return temp.substring(i+1);}
				continue;
			}
			temp = temp.delete(0,i);
			break;
		}
        
        /***********************************************/
		// .00 일경우 => 0.00으로
		/***********************************************/
		s= temp.toString();
		if( s.indexOf(".") == 0)   {temp.insert(0,"0");}
        /***********************************************/

		return temp.toString();
        
	}


	/**<pre>
	 *숫자값을 원화로 바꾼어 리턴한다.
	 *
	 * @param String s
	 * @param String gubun
	 * @return String 
	 */
	public static String getWon(String ss) {
        
		if(ss == null) {return "";}

		StringBuffer temp = new StringBuffer(removeZero(ss,"01"));
        
		int token = temp.length()%3;
        
		if(temp.toString().equals("")) {return "0";}
        
		if(token == 0) {token=3;}
		
		for(int i=token;i<temp.length();i=i+4) {
			temp.insert(i,",");
		}
        
		return temp.toString();

	}
	
	/**
     * <pre>
	 * 달러와 원화값을 숫자로 바꾸어 리턴한다.
	 *
	 * @param String ss
	 * @return String 
	 */	
	public static String getNum(String ss) {
        
		StringBuffer temp = new StringBuffer(ss.trim());
        
		if(wonCheck(temp.toString())) {
             
            temp.insert(temp.length(),"00");
            
        } else {
            
			int dot = temp.toString().indexOf(".");
              
			if(dot == temp.length()-2){ temp.insert(temp.length(),"0");}
            
			temp.delete(dot,dot+1);
            
		}
        
		int len = 17-temp.length();
        
		for(int i=0;i<len;i++) temp.insert(0,"0");
		
		return temp.toString();
	}
    
    /*########################################################################33*/
    
    /**<pre>
	 *달러와 원화값을 숫자로 바꾸어 리턴한다.
	 *
	 * @param String ss
	 * @return String 
	 */	
    public static String getNum(String s, int i) {
        
        StringBuffer stringbuffer = new StringBuffer(s.trim());
        
        for(int j = 0; j < stringbuffer.length(); j++)
            if(stringbuffer.charAt(j) == '.' || stringbuffer.charAt(j) == ','){
                stringbuffer.deleteCharAt(j);
            }

        return getFillZero(stringbuffer.toString(), i);
    }
    
    public static String getFillZero(String s, int i) {
        
        StringBuffer stringbuffer = new StringBuffer(s.trim());
        
        int j = i - stringbuffer.length();
        
        for(int k = 0; k < j; k++)
            stringbuffer.insert(0, "0");

        return stringbuffer.toString();
    }
    
/*########################################################################33*/


	/**
     * <pre>
	 * 달러와 원화값을 숫자로 바꾸어 리턴한다.(s1=KRW)
	 *
	 * @param String ss
	 * @return String 
	 */	
	public static String getWD(String s,String s1) {
        
        String s2 =null;
        
		if(s1.equals("KRW")){
            s2 = getWon(s);
		}else{
            s2 = getDollar(s);
		}
            
		return s2;
	}
	
	/**
     * <pre>
	 * 숫자값을 달러로 바꾸어 리턴한다.
	 *
	 * @param String ss
	 * @return String 
	 */	
	public static String getDollar(String ss) {
        
		if(ss == null || ss.trim().equals("")) {return "";}
        
		boolean wc = wonCheck(ss);
		StringBuffer temp = null;
        
		try {
            
            temp = new StringBuffer(removeZero(ss,"02"));

			if( temp.length() == 1) {
                temp.insert(0,"0");
			}
                
		} catch(Exception e) {
            
			System.err.println("Exception Occured");
			return "**False Data**";
            
		}
        
		if(wc) {
			if( temp.length() == 0){
               temp.append("0.00");
			}else{
			   temp.insert(temp.length()-2,".");
			}
		}
        
		int token = (temp.length()-3)%3;  //","가 시작될 위치.
		if(token == 0) {token=3;}
		
		if( temp.toString().indexOf(".") == 0)   {temp.insert(0,"0");}
		
		for(int i=token;i<temp.length()-3;i=i+4) {
			temp.insert(i,",");
		}
        
		return temp.toString();
				
	}
	
	public static boolean wonCheck(String s) {
        
		if(s.indexOf(".") == -1) {return true;}
        
		return false;
	}
		
	/**
     * <pre>
	 * 숫자값을 환가로율로 바꾸어 리턴한다.
	 *
	 * @param String ss
	 * @param int num
	 * @return String 
	 */	
	public static String getExchange(String ss, int num) {
        
		String s = removeZero(ss,"02");
		StringBuffer temp;
        
		if(num == 2) {
            
			if(s.equals("0")) {return "0.00";}
            
			temp = new StringBuffer(s);
			
            return temp.insert(temp.length()-2,".")+"";
            
		} else if(num == 4) {
            
			if(s.equals("0")) {return "0.0000";}
            
			temp = new StringBuffer(s);
            
			int len = temp.length();
            
			if(len <= 4) {
				for(int i=0;i<(5-len);i++) temp.insert(0,"0");
			}
            
			return temp.insert(temp.length()-4,".")+"";
            
		}
        
		return "false";
        
	}

	/**
     * <pre>
	 * 통화코드목록을 리턴한다.
	 *
	 * @param String ss
	 * @param int num
	 * @return String 
	 */	
	public static String[] getCurrency() {
        
		String[] currency = {"USD","KRW","JPY","GBP","DEM","CAD","FRF","ITL","CHF","HKD","SEK",
				     "AUD","DKK","BEF","ATS","NOK","NLG","SAR","KWD","BHD","AED",
				     "SGD","MYR","NZD","ESP","FIM","THB","IDR","EUR","CNY"
				    };	

		                     
        return currency;
        
	}

	/**
     * <pre>
	 * 국가명을 리턴한다.
	 *
	 * @param String ss
	 * @param int num
	 * @return String 
	 */	
	public static String getCurrency(String s) {
        
		String kor_name = null;
		
		     if(s.equals("USD")) {kor_name = "미국 	";  //달러
		}else if(s.equals("KRW")) {kor_name = "한국 	";  //원         
		}else if(s.equals("JPY")) {kor_name = "일본 	";  //엔      
		}else if(s.equals("GBP")) {kor_name = "영국 	";  //파운드  
		}else if(s.equals("DEM")) {kor_name = "독일 	";  //마르크  
		}else if(s.equals("CAD")) {kor_name = "캐나다 	";  //달러    
		}else if(s.equals("FRF")) {kor_name = "프랑스 	";  //프랑    
		}else if(s.equals("ITL")) {kor_name = "이태리 	";  //리라    
		}else if(s.equals("CHF")) {kor_name = "스위스 	";  //프랑    
		}else if(s.equals("HKD")) {kor_name = "홍콩 	";  //달러    
		}else if(s.equals("SEK")) {kor_name = "스웨덴 	";  //크로네  
		}else if(s.equals("AUD")) {kor_name = "호주 	";  //달러    
		}else if(s.equals("DKK")) {kor_name = "덴마크 	";  //크로네  
		}else if(s.equals("BEF")) {kor_name = "벨기에 	";  //프랑    
		}else if(s.equals("ATS")) {kor_name = "오스트리아 	";  //쉴링  	
		}else if(s.equals("NOK")) {kor_name = "노르웨이 	";  //크로네  
		}else if(s.equals("NLG")) {kor_name = "네덜란드 	";  //길더    
		}else if(s.equals("SAR")) {kor_name = "사우디 	";  //리알    
		}else if(s.equals("KWD")) {kor_name = "쿠웨이트 	";  //디나르  
		}else if(s.equals("BHD")) {kor_name = "바레인 	";  //디나르  
		}else if(s.equals("AED")) {kor_name = "아랍토호국 	";  //디히람	
		}else if(s.equals("SGD")) {kor_name = "싱가포르 	";  //달러    
		}else if(s.equals("MYR")) {kor_name = "말레이지아 	";  //링기트	
		}else if(s.equals("NZD")) {kor_name = "뉴질랜드 	";  //달러    
		}else if(s.equals("ESP")) {kor_name = "스페인 	";  //페세타  
		}else if(s.equals("FIM")) {kor_name = "핀란드 	";  //마르카  
		}else if(s.equals("THB")) {kor_name = "태국 	";  //바트    
		}else if(s.equals("IDR")) {kor_name = "인도 	";  //루피아  
		}else if(s.equals("EUR")) {kor_name = "유로통화 	";  //유러 	
		}else if(s.equals("CNY")) {kor_name = "중국 	";  //원화    
		}else{ kor_name = "등록되지 않은 통화입니다.";}
        		
		return kor_name.trim();		
	}	


	public static long parseLong( String str ) {
        
		if (str.charAt(0)=='+') {
			return Long.parseLong( str.trim().substring(1) );
		}
            
		return Long.parseLong( str.trim() );
	}

	public static int parseInt( String str ) {
		return Integer.parseInt( str.trim() );
	}

	public static String formedNumber( int number ) {
		return formedNumber( String.valueOf( number ) );
	}
	
	public static String formedNumber( long number ) {
		return formedNumber( String.valueOf( number ) );
	}
	
	public static String formedNumber( String number ) {
        
		boolean plusFlag = false; 

		if (number.charAt(0)=='+') {
	 		plusFlag = true; 
			number = number.substring(1);
		}

		StringBuffer sb = new StringBuffer( number );
        
		int startLength = number.length();
		int commaOffset = startLength - 3;

		while( commaOffset > 0 ) {
			sb.insert( commaOffset, ',' );
			commaOffset-=3;					
		}

		return plusFlag ? sb.insert( 0, '+' ).toString() : sb.toString();
	}

	public static String formedNumber( Object obj ) {
		if(obj instanceof BigDecimal) {
			return formedNumber( String.valueOf( obj ) );
		} 
		
		return formedNumber( (String)obj );
	}

	/**  
	 * number formating, 숫자에 ','를 첨가한다. 
	 * @return java.lang.String
	 * @param str 숫자String
	 */
	public static String moneyStr(String str) {
		return moneyStr(str, 0);
	}

	/**  
	 * number formating, 소숫점이하 자리수
	 * @return java.lang.String
	 * @param str 숫자String
	 */
	public static String moneyStr(String str, int dpoint) {
        
	    String dformat = null;
        
		switch (dpoint) {
            
			case 0:
			    dformat = "###,###,###.###";
			    break;	
			case 1:
			    dformat = "###,###,###,##0.0";
			    break;			
			case 2:
			    dformat = "###,###,###,##0.00";
			    break;
			case 3:
			    dformat = "###,###,###,##0.000";
			    break;	    
			case 4:
			    dformat = "###,###,###,##0.0000";
			    break;	    
			case 5:
			    dformat = "###,###,###,##0.00000";
			    break;
			default:
	    }
	    
		String temp = null;

		if (str == null || str.trim().equals("")) {
			temp = "0";
        } else {
			double change = Double.valueOf(str);
			DecimalFormat decimal = new DecimalFormat(dformat);
			temp = decimal.format(change);
		}

		return temp;
	}

	/**  
	 * 5자리의 이율을 ##.###로 return
	 * @return java.lang.String 3.500
	 * @param str 이율5자리
	 */
	public static String interate( String str ){

		String str1,str2;

		str1 = str.substring(0,2);
		str2 = str.substring(2,5);

		return moneyStr(str1) + "." + str2;

	}

	/**  
	 * number formating, 숫자에 ','를 첨가한다. 
	 * @return java.lang.String
	 * @param d double
	 */
	public static String moneyStr(double d) {

		String temp = null;

		if (d == 0) {
			temp = "0";
        } else {
			DecimalFormat decimal = new DecimalFormat("###,###,###.###");
			temp = decimal.format(d);
		}

		return temp;
	}

    /**
     * 금액문자열을 금액표시타입으로 변환한다. <BR>
     * (예) 12345678 --> 12,345,678            <BR>
     * @param    moneyString 금액문자열.        
     * @param    delimeter   금액표시구분자.   
     * @return   변경된 금액 문자열.           
     */
    public static String makeMoneyType(String moneyString, String delimeter) {
        
        int len = moneyString.length();
        
        String temp=null;
        String money=null;
        String ret="";
        
        for(int x=len; x > 0; x -= 3) {
            
            if ((x-3) <= 0) {
                temp = moneyString.substring(0,x);   
	            money = temp;
	            ret = money + ret;
            } else {
                temp = moneyString.substring(x-3,x);
	            money = delimeter+temp;
	            ret = money + ret;                
            }
            
        }
        
        if( (ret.charAt(0) == '+') || (ret.charAt(0) == '-') ) {
	        	
   			if( ret.charAt(1) == delimeter.charAt(0)) {
   			     	
        		String tempMoneyStr = null;
        		tempMoneyStr = ret.substring(0,1) + ret.substring(2, ret.length());
        		
        		ret = null;	
  				ret = tempMoneyStr;
  			}
  		}
        
        return ret;
    }

}