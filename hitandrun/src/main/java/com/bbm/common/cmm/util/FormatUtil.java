package com.bbm.common.cmm.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * <pre>
 * 전화번호, 카드번호, 주민등록번호, 계좌번호, 가상계좌번호, CMS코드 포맷 변환
 * 
 * update history 
 * &#064;since 2003/01/01
 * 
 */
public class FormatUtil {

	/**
	 * <pre>
	 *   
	 * 2자리의 번호를 전화번호format으로 return
	 * 
	 * x.
	 * 00201234567 -&gt; 02-123-4567
	 * 
	 * &#064;return java.lang.String
	 * &#064;param telNum 전화번호12자리
	 * 
	 */
	public static String parseTelNum(String telNum) {

		if (telNum == null){
			return telNum;
		}

		String tel, tel2, tel3, pNum;

		tel = telNum.substring(0, 4).trim();
		if (tel.length() == 4) {
			tel = tel.substring(1);
			if (tel.charAt(0) == '0' && tel.charAt(1) == '0'){
				tel = tel.substring(1);
			}
		}
		tel2 = telNum.substring(4, 8);
		if (tel2.charAt(0) == '0'){
			tel2 = tel2.substring(1);
		}
		tel3 = telNum.substring(8);

		pNum = tel.trim() + "-" + tel2.trim() + "-" + tel3.trim();
		return pNum;
	}

	/**
	 * <pre>
	 *   
	 * 전화번호를 &quot;-&quot;없는 12자리의 번호로 return
	 * 
	 * ex.
	 * 02-123-4567 -&gt; 000201234567
	 * 
	 * &#064;return java.lang.String 
	 * &#064;param telstr
	 * 
	 */
	public static String telNum(String telstr) {

		int i = telstr.indexOf("-");
		int j = telstr.lastIndexOf("-");
		if (i == -1 || j == -1 || i == j){
			return telstr;
		}

		String tel1 = StringUtil.fillZero(telstr.substring(0, i), 4);
		String tel2 = StringUtil.fillZero(telstr.substring(i + 1, j), 4);
		String tel3 = StringUtil.fillZero(telstr.substring(j + 1), 4);

		return tel1 + tel2 + tel3;
	}

	/**
	 * <pre>
	 * 카드유효기간 포맷으로변환
	 * 
	 * ex.
	 * 999999 --&gt; 9999-99
	 * 
	 * &#064;param 카드유효기간문자열     String
	 * &#064;return 포맷된 카드유효기간 문자열
	 * 
	 */
	public static String yuhyogigan(String term) throws Exception {

		if (term == null){
			return "";
		}

		term = term.trim();

		if (term.equals("")){
			return "";
		}

		if (term.length() != 6){
			return "";
		}

		StringBuffer strBuff = null;

		String yuhy_head = term.substring(0, 4);
		String yuhy_end = term.substring(4, 6);

		strBuff = new StringBuffer(yuhy_head);

		return strBuff.append("년").append(yuhy_end).append("월").toString();
	}

	/**
	 * <pre>
	 * 가상계좌번호가 14자리인지 확인한다.
	 * 14자리인경우 virtualAccount를 그대로 리턴하고 아닌경우 CommonUtilException을 발생시킨다.
	 * 
	 * &#064;param 가상계좌번호문자열     String
	 * &#064;return 가상계좌번호 문자열
	 * 
	 */
	public static String virtualAccount(String virtualAccount) throws Exception {

		if (virtualAccount.length() != 14) {

			throw new Exception("[FormatUtil.virtualAccount] 가상계좌번호(" + virtualAccount + ")가 14자리가 아닙니다");

		}

		return virtualAccount;
	}

	/**
	 * <pre>
	 * 
	 * CMS코드가 14자리인지 확인한다.
	 * <br>
	 * 14자리인경우 cms를 그대로 리턴하고 아닌경우 CommonUtilException을 발생시킨다.
	 * 
	 * &#064;param CMS코드     String
	 * &#064;return CMS코드
	 * 
	 */
	public static String cms(String cms) throws Exception {

		if (cms.length() != 14) {
			throw new Exception("[FormatUtil.cms] CMS코드(" + cms + ")가 14자리가 아닙니다");
		}
		return cms;
	}

	/**
	 * <pre>
	 * 계좌번호의 앞자리 0을 제거하여 리턴한다.
	 * 
	 * &#064;return java.lang.String
	 * &#064;param str 계좌번호
	 * 
	 */
	public static String trim0AcctNo(String str) {

		String sCode = "";
		String strAccno = str.trim();

		if (strAccno.length() < 11){
			return strAccno;
		}

		if (strAccno.substring(0, 3).equals("000")){
			sCode = str.substring(3);
		}else if (strAccno.substring(0, 2).equals("00")){
			sCode = strAccno.substring(2);
		}else{
			sCode = strAccno;
		}

		return sCode;
	}

	/**
	 * 개월수 앞자리 0을제거...
	 * 
	 * @return java.lang.String
	 * @param str
	 *            개월수번호
	 */
	public static String trim0Month(String str) {

		String sCode = "";
		String strAccno = str.trim();

		if (strAccno.substring(0, 2).equals("00")){
			sCode = str.substring(2);
		}else if (strAccno.substring(0, 1).equals("0")){
			sCode = strAccno.substring(1);
		}else{
			sCode = strAccno;
		}

		return sCode;
	}

	/**
	 * <pre>
	 *   
	 * 계좌번호의 체계에 따라 특정위치에 '-'를 첨가한다. 
	 * 
	 * &#064;return java.lang.String
	 * &#064;param str 계좌번호
	 * 
	 */
	public static String accountNo(String str) {

		String temp = null;

		if (str.length() == 15) {
			return acountNoFA(str);
		}

		str = trim0AcctNo(str);
		int len = str.length();

		switch (len) {
		case 9:
			temp = "00" + str.substring(0, 1) + "-" + str.substring(1, 3) + "-" + str.substring(3, 9);
			break;
		case 10:
			temp = "0" + str.substring(0, 2) + "-" + str.substring(2, 4) + "-" + str.substring(4, 10);
			break;
		case 11:
			temp = str.substring(0, 3) + "-" + str.substring(3, 5) + "-" + str.substring(5, 11);
			break;
		case 12:
			temp = str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 12);
			break;
		case 14:
			temp = str.substring(0, 6) + "-" + str.substring(6, 8) + "-" + str.substring(8, 14);
			break;
		default:
			temp = str;
			break;
		}

		return temp;

	}

	/**
	 * <pre>
	 *   
	 * 계좌번호의 체계에 따라 특정위치에 '-'를 첨가하며, 외환 계좌일경우 사용한다.
	 * 외환계좌의 포맷은 3-3-6, 4-3-6, 6-3-6 이다.
	 * 
	 * &#064;return java.lang.String
	 * &#064;param str 계좌번호
	 * 
	 */
	public static String acountNoFA(String str) {

		String temp = null;
		str = trim0AcctNo(str);
		int len = str.length();

		switch (len) {

		case 10:
			temp = "00" + str.substring(0, 1) + "-" + str.substring(1, 4) + "-" + str.substring(4, 10);
			break;
		case 11:
			temp = "0" + str.substring(0, 2) + "-" + str.substring(2, 5) + "-" + str.substring(5, 11);
			break;
		case 12:
			temp = str.substring(0, 3) + "-" + str.substring(3, 6) + "-" + str.substring(6, 12);
			break;
		case 13:
			temp = str.substring(0, 4) + "-" + str.substring(4, 7) + "-" + str.substring(7, 13);
			break;
		case 15:
			temp = str.substring(0, 6) + "-" + str.substring(6, 9) + "-" + str.substring(9, 15);
			break;
		default:
			temp = str;
			break;

		}

		return temp;
	}

	/**
	 * <pre>
	 *   
	 * 계좌번호의 체계에 따라 특정위치에 '-'를 첨가한다.[정보통신부 전용] 
	 * 추가:2003/03/07 Jung Tae Won
	 * 
	 * &#064;return java.lang.String
	 * &#064;param str 계좌번호
	 * 
	 */
	public static String accountNoMic(String str) {

		String temp = null;
		int len = str.length();

		switch (len) {

		case 14:
			temp = str.substring(0, 6) + "-" + str.substring(6, 8) + "-" + str.substring(8, 14);
			break;
		default:
			temp = str;
			break;
		}

		return temp;
	}

	
	/**
	 * <pre>
	 *   
	 * 수입신고번호 5-2-7
     * @param str
	 * @return temp
	 */
	public static String impDclNo(String str) {
		String temp = null;
		int len = str.length();

		if (len != 14){
			return str;
		}

		temp = str.substring(0, 5) + "-" + str.substring(5, 7) + "-" + str.substring(7, 14);

		return temp;
	}
	
	/**
	 * <pre>
	 *   
	 * 수출신고번호 3-2-2-8
     * @param str
	 * @return temp
	 */
	public static String expDclNo(String str) {
		String temp = null;
		int len = str.length();
		if (len != 15){
			return str;
		}

		temp = str.substring(0, 3) + "-" + str.substring(3, 5) + "-" + str.substring(5, 7) + "-" + str.substring(7, 15);

		return temp;
	}
	
	/**
	 * <pre>
	 *   
	 * 카드번호에 '-'를 첨가한다.  
	 * 
	 * ex.
	 * &quot;1111222233334444&quot; -&gt; &quot;1111-2222-3333-4444&quot;
	 * 
	 * &#064;return java.lang.String
	 * &#064;param str 카드번호
	 * 
	 */
	public static String cardNo(String str) {

		String temp = null;
		int len = str.length();

		if (len != 16){
			return str;
		}

		temp = str.substring(0, 4) + "-" + str.substring(4, 8) + "-" + str.substring(8, 12) + "-" + str.substring(12, 16);

		return temp;
	}

	/**
	 * <pre>
	 *   
	 * 주민등록번호 또는 사업자번호에 '-'를 첨가한다. 주민 번호 뒷자리는 &quot;*&quot;로 표기
	 * 
	 * ex.
	 * &quot;7012341234567&quot; -&gt; &quot;701234-******&quot;
	 * &quot;1112233333&quot; -&gt; &quot;111-22-33333&quot;
	 * 
	 * &#064;return java.lang.String
	 * &#064;param str 숫자String
	 * 
	 */
	public static String regnNo2(String str) {

		String temp = null;
		int len = str.length();

		if ((len != 13) && (len != 10)){
			return str;
		}

		// 사업자번호
		if (len == 10){
			temp = str.substring(0, 3) + "-" + str.substring(3, 5) + "-" + str.substring(5, 10);
		}

		// 주민등록번호
		if (len == 13) {
			if (str.charAt(6) == '0') {
				temp = str.substring(1, 4) + "-" + str.substring(4, 6) + "-" + str.substring(8, 13);
			}
			else {
				temp = str.substring(0, 6) + "-" + "*******";
				// temp = str.substring(0,6) + "-" + str.substring(6,13);// 기존거
			}
		}

		return temp;
	}

	/**
	 * <pre>
	 *   
	 * 주민등록번호 또는 사업자번호에 '-'를 첨가한다. 
	 * 
	 * ex.
	 * &quot;7012341234567&quot; -&gt; &quot;701234-123456&quot;
	 * &quot;1112233333&quot; -&gt; &quot;111-22-33333&quot;
	 * 
	 * &#064;return java.lang.String
	 * &#064;param str 숫자String
	 * 
	 */
	public static String regnNo(String str) {
		String temp = null;
		int len = str.length();
		if (len != 13 && len != 10){
			return str;
		}
		if (len == 10){
			temp = str.substring(0, 3) + "-" + str.substring(3, 5) + "-" + str.substring(5, 10);
		}
		if (len == 13){
			if (str.charAt(6) == '0'){
				temp = str.substring(1, 4) + "-" + str.substring(4, 6) + "-" + str.substring(8, 13);
			}else{
				temp = str.substring(0, 6) + "-" + str.substring(6, 13);
			}
		}
		return temp;
	}
	
	
	/**
	 * <pre>
	 *   
	 * 주민등록번호를 '-*******'로 표시한다. 
	 * 
	 * ex.
	 * &quot;7012341234567&quot; -&gt; &quot;701234-*******&quot;
	 * 
	 * 
	 * &#064;return java.lang.String
	 * &#064;param str 숫자String
	 * 
	 */
	public static String regnNoPass(String str) {
		String temp = null;
		int len = str.length();
		if (len != 13 && len != 10){
			return str;
		}
		
		if (len == 13){
			temp = str.substring(0, 6) + "-*******";
		}
		return temp;
	}

	/**
	 * <pre>
	 *   
	 * 우편번호에 '-'를 첨가한다. 
	 * 
	 * ex.
	 * &quot;404180&quot; -&gt; &quot;404-180&quot;
	 * 
	 * &#064;return java.lang.String
	 * &#064;param str 우편번호String
	 * 
	 */
	public static String postNo(String str) {
		if (str == null){
			return "";
		}else{
			return str.substring(0, 3) + "-" + str.substring(3, 6);
		}
	}

	/**
	 * <pre>
	 *   
	 * 숫자 문자열을 계좌번호 형식으로 변환한다.
	 * 
	 * ex.
	 * &quot;1111122233333&quot; -&gt; &quot;11111-222-33333&quot;
	 * 
	 * &#064;return java.lang.String
	 * &#064;param str 숫자String
	 * 
	 */
	public static String getAccount(String s) {

		if (s == null){
			return "";
		}
		// if(s.length() < 9) return ;

		StringBuffer temp = new StringBuffer(s.trim());

		temp.insert(5, "-");
		temp.insert(9, "-");

		return temp.toString();
	}

	/**
	 * <pre>
	 *   
	 * 13자리 숫자 문자열을 사업자 번호 형식으로 변환한다.
	 * 
	 * 2009.09.23 황병준 - 10자리인 경우에도 변환할 수 있도록 변경
	 * 
	 * ex.
	 * &quot;0123450067890&quot; -&gt; &quot;123-45-67890&quot;
	 * 
	 * &#064;return java.lang.String
	 * &#064;param str 숫자String
	 * 
	 */
	public static String getWorkNo(String s) {
		String str = s.trim();
		StringBuffer temp = new StringBuffer(s);

		if (str.length() == 13) {
			if (temp.charAt(0) != '0'){
				return temp.toString();
			}

			temp.deleteCharAt(0); // '0'값을 제거한다.
			temp.deleteCharAt(5); // '0'값을 제거한다.
			temp.deleteCharAt(5); // '0'값을 제거한다.

			temp.insert(3, "-");
			temp.insert(6, "-");
		}
		else if (str.length() == 10) {
			temp.insert(3, "-");
			temp.insert(6, "-");
		}

		return temp.toString();

	}

	/**
	 * <pre>
	 *   
	 * 계좌번호 또는 카드번호 문자열에서 숫자만을 추출하여 리턴한다.
	 * 
	 * ex.
	 * &quot;012345-00-678900&quot; -&gt; &quot;01234500678900&quot;
	 * 
	 * &#064;return java.lang.String
	 * &#064;param str 숫자String
	 * 
	 */
	public static String makeDigit(String str) {

		int n = str.length();
		String temp = "";

		for (int i = 0; i < n; i++) {

			if (isDigitChar(str.charAt(i))){
				temp += str.charAt(i);
			}
		}

		return temp;
	}

	/**
	 * <pre>
	 *   
	 * 입력 char가 숫자형인지 아닌지를 리턴. &quot;0&quot;&tilde;&quot;9&quot;이면 true, 아니면 false.
	 * 
	 * &#064;return java.lang.String
	 * &#064;param str 숫자String
	 * 
	 */
	public static boolean isDigitChar(char c) {

		char x = '0';
		char y = '9';

		if (c < x || c > y){
			return false;
		}

		return true;
	}

	/**
	 * <pre>
	 *   
	 * 입력한 일련의 숫자형 문자열을 전화번호 형태로 리턴.
	 * 9자리 이상의 모든 전화번호를 변환가능.
	 * 
	 * ex.
	 * &quot;028891900&quot; -&gt; &quot;02-889-1900&quot;
	 * &quot;0298891900&quot; -&gt; &quot;02-9889-1900&quot;
	 * &quot;05298891900&quot; -&gt; &quot;052-9889-1900&quot;
	 * &quot;0119891900&quot; -&gt; &quot;011-989-1900&quot;
	 * 
	 * &#064;return java.lang.String
	 * &#064;param str 숫자String
	 * 
	 */
	public static String makePhoneNumFormat(String str) {
		str = str.trim();
		StringBuffer rtnStr = null;

		try {
			rtnStr = new StringBuffer();

			if (str == null){
				return "";
			}

			// 1588-0000 과 같은 번호의 처리
			if(str.length() == 8) {
				rtnStr.append(str.substring(0, 4));
				rtnStr.append("-");
				rtnStr.append(str.substring(4));
				return rtnStr.toString();
			}
			
			if (str.substring(0, 2).equals("02")) {
				rtnStr.append(str.substring(0, 2));
				str = str.substring(2);
			}
			else {
				rtnStr.append(str.substring(0, 3));
				str = str.substring(3);
			}

			rtnStr.append("-");

			if (str.length() == 7) {
				rtnStr.append(str.substring(0, 3));
				rtnStr.append("-");
				rtnStr.append(str.substring(3));
			}
			else {
				rtnStr.append(str.substring(0, 4));
				rtnStr.append("-");
				rtnStr.append(str.substring(4));
			}

		}
		catch (Exception e) {
			return "";
		}
		return rtnStr.toString();
	}

	/**
	 * <pre>
	 *   
	 * 13자리 숫자 문자열을 축협 계좌번호 형식(3-2-5-3)으로 변환한다.
	 * 
	 * ex.
	 * &quot;1112233333444&quot; -&gt; &quot;111-22-33333-444&quot;
	 * 
	 * &#064;return java.lang.String
	 * &#064;param str 숫자String
	 * 
	 */
	public static String getCAccount(String s) {

		if (s == null || s.length() != 13){
			return s;
		}

		StringBuffer temp = new StringBuffer(s.trim());

		temp.insert(3, "-");
		temp.insert(6, "-");
		temp.insert(12, "-");

		return temp.toString();
	}

	/**
	 * 타행 계좌번호를 Length를 16자리 고정 시킨다.
	 * 
	 * @return java.lang.String
	 * @param Gja(계좌
	 *            번호) java.lang.String
	 */
	public static String GjaNbrFormat(String Gja) {

		String GjaNbr = Gja;
		if (GjaNbr.length() <= 0){
			return "000000000000000";
		}
		int len = GjaNbr.length();

		switch (len) {

		case 16:
			break;
		case 15:
			GjaNbr = GjaNbr + " ";
			break;
		case 14:
			GjaNbr = GjaNbr + "  ";
			break;
		case 13:
			GjaNbr = GjaNbr + "   ";
			break;
		case 12:
			GjaNbr = GjaNbr + "    ";
			break;
		case 11:
			GjaNbr = GjaNbr + "     ";
			break;
		case 10:
			GjaNbr = GjaNbr + "      ";
			break;
		case 9:
			GjaNbr = GjaNbr + "       ";
			break;

		default:
			GjaNbr = "";
			break;
		}

		return GjaNbr;
	}

	/**
	 * 농협 계좌번호를 Length를 16자리 고정 시킨다.
	 * 
	 * @return java.lang.String
	 * @param Gja(계좌
	 *            번호) java.lang.String
	 */
	public static String NacfGjaNbrFormat(String Gja) {

		String GjaNbr = Gja;
		if (GjaNbr.length() <= 0){
			return "000000000000000";
		}
		int len = GjaNbr.length();

		switch (len) {

		case 16:
			break;
		case 15:
			GjaNbr = GjaNbr + " ";
			break;
		case 14:
			GjaNbr = GjaNbr + "  ";
			break;
		case 13:
			GjaNbr = "0" + GjaNbr + "  ";
			break;
		case 12:
			GjaNbr = "00" + GjaNbr + "  ";
			break;
		case 11:
			GjaNbr = "000" + GjaNbr + "  ";
			break;
		case 10:
			GjaNbr = "0000" + GjaNbr + "  ";
			break;
		case 9:
			GjaNbr = "00000" + GjaNbr + "  ";
			break;
		default:
			GjaNbr = "";
			break;
		}

		return GjaNbr;

	}

	/**
	 * 
	 * 전문입력양식에 맞게 데이터를 Length만큼 고정시킨다.
	 * 
	 * @return java.lang.String
	 * 
	 */
	public static String DataFormat(String str, int len) {

		StringBuffer temp = new StringBuffer();

		String kk = str;

		byte data[] = kk.getBytes();
		int datalen = data.length;

		if (datalen < len) { // 입력값이 정상입력시

			temp.append(kk);

			for (int i = 0; i < len - datalen; i++) {
				temp.append(" ");
			}

		}else{ // 입력 데이터길이가 넘을때
			temp.append(kk.substring(0, len));
		}

		return temp.toString();

	}

	/**
	 * <code>WON</code> : "원" 식별자
	 */
	public static final int WON = 1;
	/**
	 * <code>DOLLAR</code> : "달러" 식별자
	 */
	public static final int DOLLAR = 2;

	/**
	 * String 인자값을 int 형으로 형변환 시킨다. (Exception 발생 시 0 리턴)
	 * 
	 * @param arg
	 *            원문 문자열
	 * 
	 * @return int
	 */
	public static int parseInt(final String arg) {
		try {
			return Integer.parseInt(arg.trim());
		}
		catch (NumberFormatException nfe) {
			return 0;
		}
		catch (NullPointerException ne) {
			return 0;
		}
	}

	/**
	 * String 인자값을 long 형으로 형변환 시킨다. (Exception 발생 시 0 리턴)
	 * 
	 * @param arg
	 *            원문 문자열
	 * 
	 * @return long
	 */
	public static long parseLong(final String arg) {
		try {
			return Long.parseLong(arg.trim());
		}
		catch (NumberFormatException nfe) {
			return 0;
		}
		catch (NullPointerException ne) {
			return 0;
		}
	}

	/**
	 * String 인자값을 double형으로 형변환 시킨다. (Exception 발생 시 0 리턴)
	 * 
	 * @param arg
	 *            원문 문자열
	 * 
	 * @return double
	 */
	public static double parseDouble(final String arg) {
		try {
			return Double.parseDouble(arg.trim());
		}
		catch (NumberFormatException nfe) {
			return 0;
		}
		catch (NullPointerException ne) {
			return 0;
		}
	}

	/**
	 * 원화금액 포맷을 입력받아 숫자형 String 으로 변환한다. ( "6,480,000" --> "6480000" )
	 * 
	 * @param value
	 *            format 될 문자로 바꿀 숫자
	 * 
	 * @return format된 문자 열
	 */
	public static String money2num(String value) {
		try {
			return NumberFormat.getInstance().parse(value).toString();
		}
		catch (Exception e) {
			return "0";
		}
	}

	/**
	 * 숫자형 String을 원화 금액 포맷으로 변환한다. ( "6480000" --> "6,480,000" )
	 * 
	 * @param money
	 *            금액 문자열
	 * 
	 * @return 포맷된 금액 문자열
	 * @throws Exception
	 */
	public static String money(String money) throws Exception {
		try {
			return money(money, WON);
		}
		catch (NumberFormatException nfe) {
			throw new Exception("[CMM242_01.money] 금액포맷으로 변환하는중 에러가 발생하였습니다. 숫자(" + money + ")가 아닙니다. " + nfe.toString());
		}
	}

	/**
	 * double 형을 원화 금액 포맷으로 변환한다. ( 6480000 --> "6,480,000" )
	 * 
	 * @param money
	 *            금액
	 * 
	 * @return 포맷된 금액 문자열
	 * @throws Exception
	 */
	public static String money(double money) throws Exception {
		try {
			return money(money, WON);
		}
		catch (Exception e) {
			throw new Exception("[CMM242_01.money] 금액포맷으로 변환하는중 에러가 발생하였습니다. " + e.toString());
		}
	}

	/**
	 * int 형을 원화 금액 포맷으로 변환한다.
	 * 
	 * @param money
	 *            금액
	 * 
	 * @return 포맷된 금액 문자열
	 * @throws Exception
	 */
	public static String money(int money) throws Exception {
		try {
			return money((double) money, WON);
		}
		catch (Exception e) {
			throw new Exception("[CMM242_01.money] 금액포맷으로 변환하는중 에러가 발생하였습니다. " + e.toString());
		}
	}

	/**
	 * long 형을 원화 금액 포맷으로 변환한다.
	 * 
	 * @param money
	 *            금액
	 * 
	 * @return 포맷된 금액 문자열
	 * @throws Exception
	 */
	public static String money(long money) throws Exception {
		try {
			return money((double) money, WON);
		}
		catch (Exception e) {
			throw new Exception("[CMM242_01.money] 금액포맷으로 변환하는중 에러가 발생하였습니다. " + e.toString());
		}
	}

	/**
	 * 숫자형 String을 언어구분에 따라 금액포맷으로 변환한다. 언어구분 lang은 CMM242_01.WON 또는
	 * CMM242_01.DOLLAR 두가지를 사용할 수 있다. ( ex. CMM242_01.money("6480000.50",
	 * CMM242_01.DOLLAR) --> "6,480,000.50" )
	 * 
	 * @param money
	 *            금액 문자열
	 * @param lang
	 *            언어구분
	 * 
	 * @return 포맷된 금액 문자열
	 * @throws Exception
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
			BigDecimal bd = new BigDecimal(money);
			dataMoney = bd.doubleValue();
		}
		catch (NumberFormatException nfe) {
			throw new Exception("[CMM242_01.money] 금액포맷으로 변환하는중 에러가 발생하였습니다. 숫자(" + money + ")가 아닙니다 " + nfe.toString());
		}

		return money(dataMoney, lang);
	}

	/**
	 * double형을 언어구분에 따라 금액포맷으로 변환한다. 언어구분 lang은 CMM242_01.WON 또는
	 * CMM242_01.DOLLAR 두가지를 사용할 수 있다. ( ex. CMM242_01.money(6480000.50,
	 * CMM242_01.DOLLAR) --> "6,480,000.50" )
	 * 
	 * @param money
	 *            금액
	 * @param lang
	 *            언어구분
	 * 
	 * @return 포맷된 금액 문자열
	 * @throws Exception
	 */
	public static String money(double money, int lang) throws Exception {
		String language = null;
		String country = null;
		Locale locale = null;
		DecimalFormat f = null;

		try {
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
			f = (DecimalFormat) NumberFormat.getCurrencyInstance(locale);
			f.setPositivePrefix("");
			f.setNegativePrefix("-");
			return f.format(money);
		}
		catch (Exception e) {
			throw new Exception("[CMM242_01.money] 금액포맷으로 변환하는중 에러가 발생하였습니다. " + e.toString());
		}
	}

	/**
	 * 입력된 숫자문자열을 금액표시 형태로 포맷팅 한다. (금액 계산값 표현용, + 기호포함됨) ( ex. "+1234567" -->
	 * "+1,234,567", "1234567" --> "1,234,567" )
	 * 
	 * @param number
	 *            숫자 문자열
	 * 
	 * @return 포맷된 금액 문자열
	 * @throws Exception
	 */
	public static String getFormedNumber(String number) throws Exception {
		String signChar = "";
		String temp = null;

		if (number.charAt(0) == '+' || number.charAt(0) == '-') {
			signChar = number.substring(0, 1);
			number = number.substring(1);
		}

		try {
			temp = money(number);
		}
		catch (Exception e) {
			throw new Exception("[CMM242_01.getFormedNumber] 금액포맷으로 변환하는중 에러가 발생하였습니다. " + e.toString());
		}

		StringBuffer sb = new StringBuffer(signChar);
		sb.append(temp);

		return sb.toString();
	}

	/**
	 * 입력된 숫자문자열을 금액표시 형태로 포맷팅 한다. (소숫점 자리 지정)
	 * 
	 * @param str
	 *            숫자 문자열
	 * @param dpoint
	 *            소숫점이하 자리수 ( 허용범위 : 0 ~ 5 )
	 * 
	 * @return 포맷된 금액 문자열
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
			dformat = "###,###,###.###";
			break;
		}

		String temp = null;

		if (str == null || str.trim().equals("")) {
			temp = "0";
		}
		else {
			double change = Double.valueOf(str);
			DecimalFormat decimal = new DecimalFormat(dformat);
			temp = decimal.format(change);
		}

		return temp;
	}

	/**
	 * <pre>
	 * 숫자 자릿수 맞추어 출력 하기
	 * 
	 *  0 : 숫자
	 *  # : 숫자 , 0은 생략
	 *  . : 소수 구분자
	 *  - : 음수
	 *  , : 그룹 구분자
	 *  % : 입력수에 100을 곱해 % 표시한다.
	 *  ' : 패턴 사용안하기 ex) &quot;##'%'&quot; -&gt;  20 입력시 20% 표시
	 * </pre>
	 * 
	 * @param patten
	 *            적용 패턴
	 * @param value
	 *            포맷팅 할 숫자형 문자열
	 * 
	 * @return String
	 */
	public static String getNumberFormat(String patten, String value) {

		String rtn;
		rtn = "";

		if (value == null || value.equals("")) {
			return rtn;
		}

		double number = Double.parseDouble(value);
		DecimalFormat format = new DecimalFormat(patten);
		return format.format(number);
	}

	/**
	 * 입력된 숫자(double)를 지정된 소숫점 아래 자리까지 반올림하여 String으로 리턴한다. 결과가 Engineering 단위로
	 * 나오는 경우 일반 형식으로 바꾼다.
	 * 
	 * @param number
	 *            입력 값
	 * @param digit
	 *            소수점 아래 자리수
	 * 
	 * @return String
	 */
	public static String getRoundNumber(double number, int digit) {

		if (digit == 0) {
			return Long.toString(Double.doubleToLongBits(number));
		}

		double tempDigit = 1;

		for (int i = 0; i < digit; i++) {
			tempDigit = tempDigit * 10;
		}

		String tempResult = Double.toString((double) (Math.round(number * tempDigit)) / tempDigit);
		String result = "";
		int index = tempResult.indexOf("E");

		if (index != -1) {

			int index2 = tempResult.indexOf(".");
			int lowerLength = tempResult.substring(index2 + 1, index).length();
			int zeroNum = Integer.parseInt(tempResult.substring(index + 1, tempResult.length()));

			if (lowerLength <= zeroNum) {

				result = tempResult.substring(0, index2) + tempResult.substring(index2 + 1, index);

				for (int j = 0; j < (zeroNum - lowerLength); j++) {
					result = result + "0";
				}

				result = result + ".0";

			}
			else {
				result = tempResult.substring(0, index2) + tempResult.substring(index2 + 1, index2 + zeroNum + 1) + "." + tempResult.substring(index2 + zeroNum + 1, index);
			}

		}
		else {

			result = tempResult;
		}

		return result;
	}

	private static String defaultDelemiter = "\n\r";

	/**
	 * java.math.BigDecimal value을 String으로 변환한다.
	 * 
	 * @param value
	 *            BigDecimal 값
	 * 
	 * @return String
	 */
	public static String getNullToEmpty(java.math.BigDecimal value) {
		String rtn;

		rtn = "";

		if (value == null) {
			return rtn;
		}
		else {
			return value.toString();
		}
	}

	/**
	 * String null 을 "" String으로 변환한다.
	 * 
	 * @param value
	 *            String 문자열
	 * 
	 * @return String
	 */
	public static String getNullToEmpty(String value) {
		String rtn;

		rtn = "";

		if (value == null) {
			return rtn;
		}
		else {
			return value;
		}
	}

	/**
	 * String null 이나 "" 을 defaultValue 로 변환한다.
	 * 
	 * @param value
	 *            String 문자열
	 * @param defaultValue
	 *            default 값
	 * 
	 * @return String
	 */
	public static String getNullToEmpty(String value, String defaultValue) {
		if (value == null || value.trim().equals("")) {
			return defaultValue;
		}
		else {
			return value;
		}
	}

	/**
	 * format형태의 String에서 문자만을 추출하여 리턴한다.
	 * getRawText("019-337-2412","???-???-????") --->> 0193372412로 출력
	 * 
	 * @param text
	 *            포맷팅 대상 문자열
	 * @param format
	 *            포맷팅 문자열
	 * 
	 * @return String
	 */
	public static String getRawText(String text, String format) {
		int start, i, j, tCount, fCount;
		String rtn;

		tCount = text.length();
		fCount = format.length();

		rtn = "";
		if (text.equals("")) {
			return rtn;
		}
		if (text.equals("&nbsp;")) {
			return "&nbsp;";
		}

		start = 0;
		for (i = 0; i < tCount; ++i) {
			for (j = start; j < fCount; ++j) {
				if (format.substring(j, j + 1).equals("?")) {
					rtn = rtn + text.substring(i, i + 1);
					start = start + 1;
					break;
				}
				else {
					start = start + 1;
					break;
				}
			}
		}
		return rtn;
	}

	/**
	 * String 을 format형태로 포맷팅하여 리턴한다.
	 * getFormatedText("0193372412","???-???-????") --->> 019-337-2412로 출력
	 * 
	 * @param text
	 *            포맷팅 대상 문자열
	 * @param format
	 *            포맷 문자열
	 * 
	 * @return String
	 */
	public static String getFormatedText(String text, String format) {
		String rtn;
		int start, i, j, len;
		int tCount, fCount;

		tCount = text.length();
		fCount = format.length();

		rtn = "";

		if (text.equals("")){
			return rtn;
		}
		if (text.equals("&nbsp;")){
			return "&nbsp;";
		}
		// text가 01252412 이고 format 이 ????-???? 이면 0125-2412로 출력
		// text에서 -를 제거한다.
		for (i = 0; i < tCount; ++i) {
			if (!text.substring(i, i + 1).equals("-")){
				rtn = rtn + text.substring(i, i + 1);
			}
		}

		text = rtn;
		tCount = text.length();

		// 포멧에서 ?의 count
		len = 0;
		for (j = 0; j < fCount; ++j)
			if (format.substring(j, j + 1).equals("?")){
				++len;
			}

		// text의 길이가 len보다 작으면 앞에 0를 붙인다.
		if (tCount < len) {
			for (i = 0; i < (len - tCount); ++i)
				text = '0' + text;
			tCount = len;
		}

		rtn = "";
		start = 0;
		for (i = 0; i < tCount; ++i) {
			for (j = start; j < fCount; ++j) {
				if (format.substring(j, j + 1).equals("?")) {
					rtn = rtn + text.substring(i, i + 1);
					start = start + 1;
					break;
				}
				else {
					rtn = rtn + format.substring(j, j + 1);
					start = start + 1;
				}
			}
		}
		return rtn + format.substring(start);
	}

	/**
	 * 답장 작성 시 수신 편지의 본문 앞에 ">" 표시를 붙인다.
	 * 
	 * @param str
	 *            원문 문자열
	 * 
	 * @return String
	 */
	public static String getReplyContent(String str) {
		return replace(str, "\n", "\n>");
	}

	/**
	 * 입력된 문자에 있는 공백을 모두 제거한 문자열을 리턴한다.
	 * 
	 * @param value
	 *            원문 문자열
	 * 
	 * @return String
	 */
	public static String getNoneSpaceString(String value) {
		if (value != null && !value.equals("")) {

			value = value.trim();

			while (value.indexOf(" ") != -1) {
				int index = value.indexOf(" ");
				value = value.substring(0, index) + value.substring(index + 1, value.length());
			}

		}

		return value;
	}

	/**
	 * 문자열 중에서 특정 문자열을 대소문자 구별하여 변경한다.
	 * 
	 * @param target
	 *            바꾸려는 문자열을 가진 원본
	 * @param old
	 *            찾을 문자열
	 * @param newer
	 *            바꿔줄 문자열
	 * 
	 * @return String
	 */
	public static String replace(String target, String old, String newer) {

		int e = 0, s = 0;

		StringBuffer result = new StringBuffer();

		e = target.indexOf(old, s);
		while (e >= 0) {
			result.append(target.substring(s, e));
			result.append(newer);
			s = e + old.length();
		}

		result.append(target.substring(s));

		return result.toString();
	}

	/**
	 * 특정 구분자로 구분되어온 data 를 구분자를 뺀 문자로만 이루어진 data 로 리턴
	 * 
	 * @param str
	 *            구분자로 구분된 data
	 * @param deli
	 *            구분자
	 * 
	 * @return String
	 * @throws Exception
	 */
	public static String removeDelimiter(String str, String deli) throws Exception {

		String sData = ""; // 파싱한 데이타

		str = strTrim(str);

		// null 이거나 "" 이면 "" 를 리턴한다.
		if (str == null || str.equals("")) {
			return sData;
		}

		StringTokenizer st = new StringTokenizer(str, deli);

		while (st.hasMoreTokens()) {
			sData += st.nextToken();
		}

		return sData;
	}

	/**
	 * 스트링 문자열의 공백 제거(Null 인경우 공백 리턴)
	 * 
	 * @param str
	 *            Trim 처리할 String
	 * 
	 * @return String
	 * @throws Exception
	 */
	public static String strTrim(String str) throws Exception {
		if (str != null && !str.equals("")) {
			str = str.trim();
		}
		else {
			str = "";
		}
		return str;
	}

	/**
	 * 오브젝트(스트링 타입)의 공백 제거
	 * 
	 * @param obj
	 *            Trim 처리할 Object (스트링타입)
	 * 
	 * @return String
	 * @throws Exception
	 */
	public static String strTrim(Object obj) throws Exception {
		if (obj != null) {
			return ((String) obj).trim();
		}
		else {
			return "";
		}
	}

	/**
	 * 스트링 문자열의 비교 (앞뒤 공백은 자동으로 제거한 후 비교됨)
	 * 
	 * @param str
	 *            비교 문자열 1
	 * @param cstr
	 *            비교 문자열 2
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean equals(String str, String cstr) throws Exception {
		try {
			if( str != null ) { 
				return str.trim().equals(cstr.trim());
			} else {
				if( cstr == null || "".equals(cstr.trim()) ) {
				    return true;
				} else {
					return false;
				}
			}
		}
		catch (Exception ex) {
			return false;
		}
	}

	/**
	 * <pre>
	 * HTML과 관련하여 일부 특수문자를 반환한다.
	 * 
	 * &amp; ---&gt;&gt; &amp;amp;
	 * &lt; ---&gt;&gt; &amp;lt;
	 * &gt; ---&gt;&gt; &amp;gt;
	 * &amp;acute ---&gt;&gt; &amp;acute;
	 * &quot; ---&gt;&gt; &amp;quot;
	 * &amp;brvbar ---&gt;&gt; &amp;brvbar;
	 * \n\r ---&gt;&gt;
	 * <BR>
	 * </pre>
	 * 
	 * @param str
	 *            원문 문자열
	 * 
	 * @return String
	 */
	public static String getSpecialCharacters(String str) {
		str = replace(str, "&", "&amp;");
		str = replace(str, "<", "&lt;");
		str = replace(str, ">", "&gt;");
		str = replace(str, "'", "&acute;");
		str = replace(str, "\"", "&quot;");
		str = replace(str, "|", "&brvbar;");

		str = replace(str, "\n", "<BR>");
		str = replace(str, "\r", "");

		return str;
	}

	/**
	 * <pre>
	 * HTML과 관련하여 일부 특수문자를 변환한다. (Reverse)
	 * 
	 * &amp;amp;---&gt;&gt; &amp;
	 * &amp;lt;---&gt;&gt; &lt;
	 * &amp;gt;---&gt;&gt; &gt;
	 * &amp;acute;---&gt;&gt; &amp;acute
	 * &amp;quot;---&gt;&gt; &quot;
	 * &amp;brvbar;---&gt;&gt; &amp;brvbar
	 * </pre>
	 * 
	 * @param str
	 *            원문 문자열
	 * 
	 * @return String
	 */
	public static String getReverseSpecialCharacters(String str) {
		str = replace(str, "&amp;", "&");
		str = replace(str, "&lt;", "<");
		str = replace(str, "&gt;", ">");
		str = replace(str, "&acute;", "'");
		str = replace(str, "&quot;", "\"");
		str = replace(str, "&brvbar;", "|");
		return str;
	}

	/**
	 * 입력된 문자에 있는 스페셜 캐릭터를 삭제하고 리턴한다.
	 * 
	 * @param str
	 *            원문 문자열
	 * 
	 * @return String
	 */
	public static String getSpecialCharDelete(String str) {
		str = replace(str, "/", "");
		str = replace(str, "<", "");
		str = replace(str, ">", "");
		str = replace(str, "'", "");
		str = replace(str, "\"", "");
		str = replace(str, "|", "");
		str = replace(str, "?", "");
		str = replace(str, "*", "");
		str = replace(str, "\"", "");
		str = replace(str, ";", "");
		str = replace(str, ".", "");

		return str;
	}

	/**
	 * 문자열 형태의 주민등록번호를 포맷한다. ( '8010102006214' ==> '801010-2006214' )
	 * 
	 * @param ssn
	 *            주민등록번호
	 * 
	 * @return String
	 * @throws Exception
	 */
	public static String stringToSsn(String ssn) throws Exception {
		String convert = "";

		if (ssn == null || strTrim(ssn).equals("") || ssn.length() != 13) {
			return ssn;
		}

		convert = ssn.substring(0, 6) + "-" + ssn.substring(6, 13);

		return convert;
	}

	/**
	 * 공백값이 넘어온 경우 스페이스로 치환한다
	 * 
	 * @param str
	 *            원문 문자열
	 * 
	 * @return String
	 * @throws Exception
	 */
	public static String getBlank2Space(String str) throws Exception {
		String strReturn = "";

		if (str == null || strTrim(str).equals("")) {
			strReturn = " ";
		}
		else {
			strReturn = str;
		}

		return strReturn;
	}

	/**
	 * Object 형을 String형으로 치환한다.
	 * 
	 * @param obj
	 *            String 형으로 바꿔야 할 Object
	 * 
	 * @return String
	 */
	public static String ObjToString(Object obj) {
		try {
			return ((String) obj).trim();
		}
		catch (Exception ex) {
			return "";
		}
	}

	/**
	 * ORACLE 의 LPAD 와 같은 기능의 Padding 함수
	 * 
	 * @param str
	 *            원문 문자열
	 * @param length
	 *            포맷팅할 길이
	 * @param paddingStr
	 *            포맷팅 문자열
	 * 
	 * @return String
	 * @throws Exception
	 */
	public static String LPAD(String str, int length, String paddingStr) throws Exception {
		int endLength = length - str.getBytes().length;
		if (length > str.getBytes().length) {
			for (int i = 0; i < endLength; i++) {
				str = paddingStr + str.substring(0);
			}
		}
		return str;
	}

	/**
	 * RPAD 기능의 Padding 함수 ( LPAD 와 반대 )
	 * 
	 * @param str
	 *            원문 문자열
	 * @param length
	 *            포맷팅할 길이
	 * @param paddingStr
	 *            포맷팅 문자열
	 * 
	 * @return String
	 * @throws Exception
	 */
	public static String RPAD(String str, int length, String paddingStr) throws Exception {

		StringBuffer strbuf = new StringBuffer(str);
		int endLength = length - str.getBytes().length;

		if (length > str.getBytes().length) {

			for (int i = 0; i < endLength; i++) {
				strbuf.append(paddingStr);
			}

		}

		return strbuf.toString();
	}

	/**
	 * HTML 로 Double Quotation Mark 를 출력할 수 있도록 문자열 변환
	 * 
	 * @param str
	 *            원문 문자열
	 * 
	 * @return String
	 * @throws Exception
	 */
	public static String quotationMark(String str) throws Exception {

		return replace(str, "\"", "&#34;");
	}

	/**
	 * HTML 로 Single Quotation Mark 를 출력할 수 있도록 문자열 변환
	 * 
	 * @param str
	 *            원문 문자열
	 * 
	 * @return String
	 * @throws Exception
	 */
	public static String singleQuotationMark(String str) throws Exception {

		return replace(str, "\'", "\\\'");
	}

	/**
	 * 입력 문자열이 null이나 "" 인 경우 %를 리턴한다. 그렇지 않을 경우 문자 그대로를 리턴한다.
	 * 
	 * @param strData
	 *            원문 문자열
	 * 
	 * @return String
	 */
	public static String getPercentCheck(String strData) {
		if (strData == null || strData.equals("")) {
			strData = "%";
		}
		return strData;
	}

	/**
	 * 입력 문자열이 null이나 "" 인 경우 %를 리턴한다. 그렇지 않을 경우 문자열 앞뒤에 %를 붙여 리턴한다.
	 * 
	 * @param strData
	 *            원문 문자열
	 * 
	 * @return String
	 */
	public static String getPercentLikeCheck(String strData) {
		if (strData == null || strData.equals("")) {
			strData = "%";
		}
		return "%" + strData + "%";
	}

	/**
	 * SQL 문에서 LIKE 연산에 사용될 문자열에 들어있는 '%' 와 '_' 를 Escaping 한다.
	 * 
	 * @param strData
	 *            Like 연산 대상 문자열
	 * 
	 * @return String
	 */
	public static String getLikeCharEsc(String strData) {
		String result = strData;
		result = replace(result, "%", "\\%");
		result = replace(result, "_", "\\_");
		return result;

	}

	/**
	 * 문자열 바이트수만큼 끊어주고, 생략표시하기 (2byte 문자지원)
	 * 
	 * @param str
	 *            원문 문자열
	 * @param len
	 *            바이트수
	 * @param trail
	 *            생략표시 문자열. (예) "..."
	 * 
	 * @return String
	 */
	public static String cropByte(String str, int len, String trail) {
		if (str == null) {
			return "";
		}
		String tmp = str;
		int slen = 0, blen = 0;
		char c;
		try {
			if (tmp.getBytes("MS949").length > len) {
				while (blen + 1 < len) {
					c = tmp.charAt(slen);
					blen++;
					slen++;
					if (c > 127) {
						blen++; // 2-byte character..
					}
				}
				tmp = tmp.substring(0, slen) + trail;
			}
		}
		catch (java.io.UnsupportedEncodingException e) {
			System.err.println("FormatUtil cropByte() java.io.UnsupportedEncodingException Occured");
		}
		return tmp;
	}

	/**
	 * 문자열 자리수만큼 끊어주기
	 * 
	 * @param str
	 *            원문 문자열
	 * @param len
	 *            자리수
	 * 
	 * @return String
	 */
	public static String crop(String str, int len) {
		return cropByte(str, len, "");
	}

	// *****************************************************************
	// 여기서부터는 배열관련된 함수들
	// *****************************************************************

	/**
	 * Text 유형의 String문자열을 기본 구분자(\n\r)로 분리한 String 배열을 리턴한다.
	 * 
	 * @param text
	 *            Text 유형의 String
	 * 
	 * @return String[]
	 */
	public static String[] getArrayFromDivStringForText(final String text) {
		return getArrayFromDivString(text, defaultDelemiter);
	}

	/**
	 * Text 유형의 String문자열을 "," 로 분리한 String 배열을 리턴한다. (CSV 유형)
	 * 
	 * @param text
	 *            Text 유형의 String
	 * 
	 * @return String[]
	 */
	public static String[] getArrayFromDivString(final String text) {
		return getArrayFromDivString(text, ",");
	}

	/**
	 * Text 유형의 String 문자열을 지정된 구분자로 분리한 String 배열을 리턴한다.
	 * 
	 * @param text
	 *            Text 유형의 String
	 * @param deli
	 *            구분자
	 * 
	 * @return String[]
	 */
	public static String[] getArrayFromDivString(final String text, final String deli) {
		String strTemp = text;
		StringTokenizer st = new StringTokenizer(strTemp, deli);
		String[] arrayStr = new String[st.countTokens()];
		for (int i = 0; st.hasMoreTokens(); i++) {
			arrayStr[i] = st.nextToken();
		}
		return arrayStr;
	}

	/**
	 * String 배열의 내용을 구분자로 구분된 문자열로 리턴한다.
	 * 
	 * @param values
	 *            문자열 배열
	 * @param deli
	 *            구분자
	 * 
	 * @return String
	 */
	public static String getStringFromArray(String[] values, String deli) {
		StringBuffer str = new StringBuffer();

		for (int i = 0; i < values.length; i++) {
			str.append(getNullToEmpty(values[i]));
			if (i < values.length - 1) {
				str.append(deli).append(" ");
			}
		}

		return str.toString();
	}

	/**
	 * String 배열의 내용을 "," 로 구분된 문자열로 리턴한다. (CSV 유형)
	 * 
	 * @param values
	 *            문자열 배열
	 * 
	 * @return String
	 */
	public static String getStringFromArray(String[] values) {
		return getStringFromArray(values, ",");
	}

	/**
	 * String 배열의 내용을 "," 로 구분된 문자열로 리턴한다. (DB용) 각 요소는 ''로 쌓여 있다.
	 * 
	 * @param values
	 *            문자열 배열
	 * 
	 * @return String
	 */
	public static String getStringFromArrayForDB(String[] values) {
		StringBuffer str = new StringBuffer();

		for (int i = 0; i < values.length; i++) {
			str.append("'" + getNullToEmpty(values[i]) + "'");
			if (i < values.length - 1) {
				str.append(", ");
			}
		}

		return str.toString();
	}

	/**
	 * int 배열에서 해당 값의 인덱스를 구한다.
	 * 
	 * @param key
	 *            검색 대상 int 배열
	 * @param value
	 *            비교 값
	 * 
	 * @return 인덱스 (없는 경우 -1)
	 */
	public static int getArrayIndex(int[] key, int value) {
		int index = 0;
		boolean isExist = false;

		for (int i = 0; i < key.length; i++) {
			if (key[i] == value) {
				isExist = true;
				index = i;
			}
		}

		if (!isExist) {
			return -1;
		}

		return index;
	}

	/**
	 * String 배열에서 해당 값의 인덱스를 구한다.
	 * 
	 * @param key
	 *            검색 대상 String 배열
	 * @param value
	 *            비교 값
	 * 
	 * @return 인덱스 (없는 경우 -1)
	 */
	public static int getArrayIndex(String[] key, String value) {
		int index = 0;
		boolean isExist = false;

		for (int i = 0; i < key.length; i++) {
			if (key[i].equals(value)) {
				isExist = true;
				index = i;
			}
		}

		if (!isExist) {
			return -1;
		}

		return index;
	}

	/**
	 * "1,2,5 ... " 형태의 문자열을 int 배열로 변환한다.
	 * 
	 * @param str
	 *            CSV 형태의 숫자 문자열
	 * 
	 * @return int 배열
	 */
	public static int[] str2Aint(String str) {
		int aInfo[] = null;
		int count = 0;
		StringTokenizer st = null;
		String stTmp = null;

		st = new StringTokenizer(str, ",");
		while (st.hasMoreTokens()) {
			stTmp = st.nextToken().trim();
			count++;
		}

		aInfo = new int[count];

		count = 0;
		st = new StringTokenizer(str, ",");
		while (st.hasMoreTokens()) {
			stTmp = st.nextToken().trim();
			aInfo[count] = Integer.parseInt(stTmp);
			count++;
		}

		return aInfo;
	}

	/**
	 * "1,2,5 ... " 형태의 문자열을 int 배열로 변환 후 배열의 모든 값을 더한다.
	 * 
	 * @param str
	 *            CSV 형태의 숫자 문자열
	 * 
	 * @return int
	 */
	public static int str2Sum(String str) {
		int tLen = 0;
		int len = 0;
		StringTokenizer st = null;
		String stTmp = null;

		st = new StringTokenizer(str, ",");

		while (st.hasMoreTokens()) {
			stTmp = st.nextToken().trim();
			len = Integer.parseInt(stTmp);
			tLen += len;
		}

		return tLen;
	}

	/**
	 * int 배열의 모든 값을 더한다.
	 * 
	 * @param key
	 *            합계 대상 int 배열
	 * 
	 * @return int
	 */
	public static int arraySum(int[] key) {
		int nSum = 0;

		for (int i = 0; i < key.length; i++) {
			nSum += key[i];
		}
		return nSum;
	}

	/**
	 * 확장자에 따른 첨부파일 아이콘 이미지 HTML 리턴
	 * 
	 * @param filenm
	 *            파일명
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	public static String getFileIcon(String filenm) throws Exception {

		StringBuffer sbIcon = new StringBuffer();
		String file_nm = strTrim(filenm);

		if (file_nm.substring(file_nm.lastIndexOf(".") + 1).equalsIgnoreCase("doc")) {
			sbIcon.append("<img src='/images/cm/fileicon/doc.gif' alt='" + file_nm + "'>");
		}
		else if (file_nm.substring(file_nm.lastIndexOf(".") + 1).equalsIgnoreCase("xls")) {
			sbIcon.append("<img src='/images/cm/fileicon/xls.gif' alt='" + file_nm + "'>");
		}
		else if (file_nm.substring(file_nm.lastIndexOf(".") + 1).equalsIgnoreCase("jpg")) {
			sbIcon.append("<img src='/images/cm/fileicon/jpg.gif' alt='" + file_nm + "'>");
		}
		else if (file_nm.substring(file_nm.lastIndexOf(".") + 1).equalsIgnoreCase("pdf")) {
			sbIcon.append("<img src='/images/cm/fileicon/pdf.gif' alt='" + file_nm + "'>");
		}
		else if (file_nm.substring(file_nm.lastIndexOf(".") + 1).equalsIgnoreCase("ppt")) {
			sbIcon.append("<img src='/images/cm/fileicon/ppt.gif' alt='" + file_nm + "'>");
		}
		else if (file_nm.substring(file_nm.lastIndexOf(".") + 1).equalsIgnoreCase("txt")) {
			sbIcon.append("<img src='/images/cm/fileicon/txt.gif' alt='" + file_nm + "'>");
		}
		else if (file_nm.substring(file_nm.lastIndexOf(".") + 1).equalsIgnoreCase("hwp")) {
			sbIcon.append("<img src='/images/cm/fileicon/hwp.gif' alt='" + file_nm + "'>");
		}
		else if (file_nm.substring(file_nm.lastIndexOf(".") + 1).equalsIgnoreCase("zip")) {
			sbIcon.append("<img src='/images/cm/fileicon/zip.gif' alt='" + file_nm + "'>");
		}
		else if (file_nm.substring(file_nm.lastIndexOf(".") + 1).equalsIgnoreCase("bmp")) {
			sbIcon.append("<img src='/images/cm/fileicon/bmp.gif' alt='" + file_nm + "'>");
		}
		else if (file_nm.substring(file_nm.lastIndexOf(".") + 1).equalsIgnoreCase("gif")) {
			sbIcon.append("<img src='/images/cm/fileicon/gif.gif' alt='" + file_nm + "'>");
		}
		else {
			sbIcon.append("<img src='/images/cm/fileicon/etc.gif' alt='" + file_nm + "'>");
		}

		return sbIcon.toString();
	}
	
	//8분기 3년 타이틀
	public static HashMap<String, String> getTitle() {
		Map<String, String> title = new HashMap<String, String>();
		title.put("quarter1", DateTime.getYearQuarter(0));
		title.put("quarter2", DateTime.getYearQuarter(-3));
		title.put("quarter3", DateTime.getYearQuarter(-6));
		title.put("quarter4", DateTime.getYearQuarter(-9));
		title.put("quarter5", DateTime.getYearQuarter(-12));
		title.put("quarter6", DateTime.getYearQuarter(-15));
		title.put("quarter7", DateTime.getYearQuarter(-18));
		title.put("quarter8", DateTime.getYearQuarter(-21));
		title.put("quarter9", DateTime.getYearQuarter(-24));
		title.put("cYear1", DateTime.nowDate(5));
		title.put("cYear2", DateTime.getLastYear());
		title.put("cYear3", DateTime.getYearBeforeLast());
		title.put("cYear4", DateTime.getYearBeforeAfterCalc(-3));
		return (HashMap<String, String>) title;
	}
	//8개월 타이틀
	public static HashMap<String, String> getMonthTitle() {
		Map<String, String> titleMonth = new HashMap<String, String>();
		titleMonth.put("month1", DateTime.getMonthLastEight()[0]);
		titleMonth.put("month2", DateTime.getMonthLastEight()[1]);
		titleMonth.put("month3", DateTime.getMonthLastEight()[2]);
		titleMonth.put("month4", DateTime.getMonthLastEight()[3]);
		titleMonth.put("month5", DateTime.getMonthLastEight()[4]);
		titleMonth.put("month6", DateTime.getMonthLastEight()[5]);
		titleMonth.put("month7", DateTime.getMonthLastEight()[6]);
		titleMonth.put("month8", DateTime.getMonthLastEight()[7]);
		
		return (HashMap<String, String>) titleMonth;
	}
	
	//4분기
	public static HashMap<String, String[]> getFullYearQuarters() {
		Map<String, String[]> yearQuarter = new HashMap<String, String[]>();
		yearQuarter.put("yearQuarter1", DateTime.getFullYearQuarter(-9));
		yearQuarter.put("yearQuarter2", DateTime.getFullYearQuarter(-6));
		yearQuarter.put("yearQuarter3", DateTime.getFullYearQuarter(-3));
		yearQuarter.put("yearQuarter4", DateTime.getFullYearQuarter(0));
		return (HashMap<String, String[]>) yearQuarter;
	}
	
	//8개월
	public static HashMap<String, String[]> getMonthYear() {
		Map<String, String[]> monthYear = new HashMap<String, String[]>();
		monthYear.put("yearMonth1", DateTime.getFullYearMonth(-7));
		monthYear.put("yearMonth2", DateTime.getFullYearMonth(-6));
		monthYear.put("yearMonth3", DateTime.getFullYearMonth(-5));
		monthYear.put("yearMonth4", DateTime.getFullYearMonth(-4));
		monthYear.put("yearMonth5", DateTime.getFullYearMonth(-3));
		monthYear.put("yearMonth6", DateTime.getFullYearMonth(-2));
		monthYear.put("yearMonth7", DateTime.getFullYearMonth(-1));
		monthYear.put("yearMonth8", DateTime.getFullYearMonth(0));
		
		return (HashMap<String, String[]>) monthYear;
	}
	
	// 주요거래처 타이틀
	public static HashMap<String, String> getMainClientTitle(String ditcCd) {
		Map<String, String> title = new HashMap<String, String>();
		title.put("name1", "부호");
		if ("P04".equals(ditcCd)) {
			title.put("name2", "해외거래처명");
		} else if ("P09".equals(ditcCd)) {
			title.put("name2", "장치장명");
		} else {
			title.put("name2", "상호명");
		}
		title.put("name3", "사업자번호");
		title.put("name4", "대표자");
		title.put("name5", "주소");
		title.put("name6", "이용건수");
		title.put("name7", "최초거래일<br />최종거래일");
		return (HashMap<String, String>) title;
	}

	// 6분기단위  타이틀
	public static HashMap<String, String> getTitleBunGi6() {
		Map<String, String> title = new HashMap<String, String>();
		title.put("quarter1", DateTime.getYearQuarter(-15));
		title.put("quarter2", DateTime.getYearQuarter(-12));
		title.put("quarter3", DateTime.getYearQuarter(-9));
		title.put("quarter4", DateTime.getYearQuarter(-6));
		title.put("quarter5", DateTime.getYearQuarter(-3));
		title.put("quarter6", DateTime.getYearQuarter(0));
		return (HashMap<String, String>) title;
	}
	
	/*
	 * Json->HashMap 변환
	 */
	public static HashMap getHashMapToJson(Object obj)
	{
		Map map = new HashMap();
		map.put("dataList", java.util.HashMap.class);
		JSONObject jsonObject = JSONObject.fromObject(obj);
		HashMap model = (HashMap)JSONObject.toBean(jsonObject, java.util.HashMap.class, map);
		return model;
	}
	
	/*
	 * JsonArray->List(HashMap) 변환
	 */
	public static List getListToJsonArray(JSONArray arr)
	{
		List list = new ArrayList();
		for( int i = 0; i < arr.size(); i++ )
		{
			Map map = new HashMap();
			map.put("dataList", java.util.HashMap.class);
			JSONObject jsonObject = JSONObject.fromObject(arr.get(i));
			HashMap model = (HashMap)JSONObject.toBean(jsonObject, java.util.HashMap.class, map);
			list.add(model);
		}
		
		return list;
	}

}
