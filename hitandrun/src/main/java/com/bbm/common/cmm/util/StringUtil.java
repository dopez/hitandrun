package com.bbm.common.cmm.util;

import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
 
/**
 * <pre>
 * 문자열 변환, 치환, 제거 등 문자열 처리 클래스
 * 
 * update history
 * &#064;since 2002/08/31
 * 
 * 
 *  == Modification Information) ==
 *   
 *     date         author                note
 *  -----------    --------    ---------------------------
 *   2011.11.01    황기연          이미지파일 확장자 체크 추가
 *   2011.11.07    김병욱			  XSS 방지 처리를 위한 Unscript메소드 추가
 *
 * </pre>
 */

public class StringUtil {

	/**
	 * <pre>
	 * 특정 문자를 제거한 문자열을 리턴한다.
	 * 
	 * ex. 
	 * String str = &quot;abc/defg&quot;;
	 * String character = &quot;/&quot;;
	 * return &quot;abcdefg&quot;;
	 * 
	 * String str = &quot;abc/de$fg&quot;;
	 * String character = &quot;/$&quot;;
	 * return &quot;abcdefg&quot;;
	 * 
	 * &#064;return String[]
	 * &#064;param String str
	 * &#064;param String character
	 * 
	 */
	public static String removeCharacter(String str, String character) throws Exception {

		StringTokenizer st;
		StringBuffer buf;
		String result;
		char c;

		result = str;

		for (int i = 0; i < character.length(); i++) {

			c = character.charAt(i);
			st = new StringTokenizer(result, String.valueOf(c));
			buf = new StringBuffer();

			while (st.hasMoreTokens()) {
				buf.append(st.nextToken());
			}

			result = buf.toString();
		}

		return result;
	}

	/**
	 * <pre>
	 * 입력 String을 고정된 length의 String으로 변환 한다.
	 * 고정 길이를 맞추기 위해 입력 String의 앞 또는 뒤에 &quot;0&quot; 또는 &quot; &quot;을 붙힌다.
	 * 문자열이 긴경우 substring한다.
	 * 
	 * kind의 값은 0,1,2의 값을 가진다.
	 * 
	 * ex1.
	 * int out_len=5;
	 * String str=&quot;1234567890&quot;;
	 * int kind=0;
	 * return &quot;12345     &quot;;  //kind값과 관계없이 앞에서부터 substring한다.
	 * 
	 * 
	 * ex2.
	 * int out_len=10;
	 * String str=&quot;12345&quot;;
	 * 
	 * int kind=0;
	 * return &quot;12345     &quot;;  //공백문자를 사용하여 왼쪽정렬
	 * 
	 * int kind=1;
	 * return &quot;     12345&quot;;  //공백문자를 사용하여 오른쪽정렬
	 * 
	 * int kind=2;
	 * return &quot;0000012345&quot;;  //&quot;0&quot;을 사용하여 왼쪽정렬
	 * 
	 * &#064;return String
	 * &#064;param int kind 정렬 방법
	 * &#064;param int out_len 고정 길이
	 * &#064;param String str
	 * 
	 */
	public static String fixlength(int kind, int out_len, String str) {

		if (str == null){
			str = "";
		}

		byte[] input = str.getBytes();
		byte[] temp = new byte[out_len];

		int i, j;
		int in_len = input.length;

		if (kind == 2) {
			for (i = 0; i < out_len; i++) {
				temp[i] = (byte) '0';
			}
		} else {
			for (i = 0; i < out_len; i++) {
				temp[i] = (byte) ' ';
			}
		}

		// 입력된 길이보다 해당 String이 긴 경우
		if (in_len > out_len){
			in_len = out_len;
		// String enc =cropByte( input[out_len-1] ); // byte에 대한 한글 체크
		}
		if (kind == 0){
			for (i = 0; i < in_len; i++) {
				temp[i] = input[i];
			}
		}else{
			for (i = (out_len - in_len), j = 0; i < out_len; i++, j++) {
				temp[i] = input[j];
			}
		}

		// 마지막 byte가 한글일 경우 공백으로 초기화.
		// if("KO".equals(enc)) temp[out_len-1] = (byte) ' ';

		// out_len에 해당하는 Byte가 한글일 경 우 byte가 짤리므로 공백으로 초기화 해준다.
		String output = new String(temp, 0, out_len);
		if (output.length() == 0 && out_len > 0){
			temp[out_len - 1] = (byte) ' ';
		}

		output = new String(temp, 0, out_len);

		return output;
	}

	/*
	 * 해당 byte가 한글인지 여부 체크
	 */
	public static String cropByte(byte str) {
		if ((char) str > 0x007F || (char) str > 127){
			return "KO";
		}else{
			return "EN";
		}
	}

	/**
	 * <pre>
	 * 원하는 길이의 공백문자를 리턴한다.
	 * 
	 * ex. 
	 * int out_len=4;
	 * return &quot;    &quot;;
	 * 
	 * &#064;return String
	 * &#064;param int out_len
	 * 
	 */
	public static String makeSpace(int out_len) {

		byte[] temp = new byte[out_len];

		for (int i = 0; i < out_len; i++) {
			temp[i] = (byte) ' ';
		}

		String output = new String(temp, 0, out_len);

		return output;

	}

	/**
	 * <pre>
	 * String을 int값으로 형변환한다. 
	 * 
	 * ex. 
	 * String str=&quot;12&quot;;
	 * return 12;
	 * 
	 * &#064;return int
	 * &#064;param String str
	 * 
	 */
	public static int stoi(String str) {

		if (str == null) {
			return 0;
		}

		return Integer.parseInt(str);
	}

	/**
	 * <pre>
	 * int를 String으로 형변환한다. 
	 * 
	 * ex. 
	 * int i=12;
	 * return &quot;12&quot;;
	 * 
	 * &#064;return String str
	 * &#064;param int
	 * 
	 */
	public static String itos(int ival) {
		return String.valueOf(ival);
	}

	/**
	 * <pre>
	 * 자열 뒤에 &quot; &quot;를 붙혀 고정길이 문자열을 리턴한다.
	 * 
	 * x. 
	 * tring str=&quot;abc&quot;;
	 * nt i=5;
	 * eturn &quot;abc  &quot;;
	 * 
	 * return String str
	 * param int size
	 * param String
	 * 
	 */
	public static String fillSpace(String str, int size) {

		if (str == null){
			return "";
		}
		if (str.length() >= size){
			return str;
		}

		int spaces = size - str.length();
		StringBuffer sb = new StringBuffer(spaces);

		for (int i = 0; i < spaces; i++)
			sb.append(" ");

		return str.concat(sb.toString());
	}

	/**
	 * <pre>
	 * 문자열 앞에 &quot;0&quot;를 붙혀 고정길이 문자열을 리턴한다.
	 * 
	 * ex. 
	 * String str=&quot;123&quot;;
	 * int i=5;
	 * return &quot;00123&quot;;
	 * 
	 * &#064;return String str
	 * &#064;param int size
	 * &#064;param String
	 * 
	 */
	public static String fillZero(String str, int size) {

		if (str == null){
			return "";
		}
		if (str.length() >= size){
			return str;
		}

		int zeros = size - str.length();
		StringBuffer sb = new StringBuffer(str);

		for (int i = 0; i < zeros; i++)
			sb.insert(0, '0');

		return sb.toString();
	}

	/**
	 * <pre>
	 * 입력 string에서 offset부터 len byte만큼 문자열을 추출한다. 한글은 2바이트로 계산한다.
	 * 
	 * ex. 
	 * String str=&quot;한글123&quot;;
	 * int offset=0;
	 * int len=4;
	 * return &quot;한글&quot;;
	 * 
	 * &#064;return String
	 * &#064;param int offset
	 * &#064;param int len
	 * 
	 */
	public static String hanSubstring(String str, int offset, int len) {

		String output = "";
		int length = len;

		byte[] input = str.getBytes();

		if (offset >= input.length){
			return output;
		}
		if (offset + length > input.length){
			length = input.length - offset;
		}

		output = new String(input, offset, length);

		return output;
	}

	/**
	 * <pre>
	 * 입력 string에서 offset부터 문자열을 추출한다. 한글은 2바이트로 계산한다.
	 * 
	 * ex. 
	 * String str=&quot;한글123&quot;;
	 * int offset=2;
	 * return &quot;글123&quot;;
	 * 
	 * &#064;return String
	 * &#064;param int offset
	 * 
	 */
	public static String hanSubstring(String str, int offset) {
		byte[] input = str.getBytes();
		return hanSubstring(str, offset, input.length - offset);
	}

	/**
	 * <pre>
	 * 문자열에서 from을 to로 치환한다.
	 * 
	 * ex. 
	 * String str=&quot;대한민국Seoul특별시&quot;;
	 * String from=&quot;Seoul&quot;;
	 * String from=&quot;서울&quot;;
	 * return &quot;대한민국서울특별시&quot;;
	 * 
	 * &#064;return String
	 * &#064;param String str
	 * &#064;param String from
	 * &#064;param String to
	 * 
	 */
	public static String replace(String str, String from, String to) {

		int i;
		String source = str;
		String result = "";

		do {

			i = source.indexOf(from);

			if (i >= 0) {

				result = result + source.substring(0, i) + to;
				source = source.substring(i + from.length(), source.length());
			} else {
				result = result + source;
			}

		} while (i >= 0);

		return result;
	}

	/**
	 * <pre>
	 * 입력 문자열이 null인경우 &quot;&quot;를 리턴하고 null이 아니면 입력문자열을 그대로 리턴한다.
	 * 
	 * ex. 
	 * String strData=null;
	 * return &quot;&quot;;
	 * 
	 * &#064;return String
	 * &#064;param String strData
	 * 
	 */
	public static String stringCheck(String strData) {

		if (strData == null) {
			return "";
		}

		return strData;
	}

	/**
	 * <pre>
	 * 입력 문자열을 length길이의 고정길이 문자열로 변환하여 리턴한다.
	 * type이 &quot;DIGIT&quot;인 경우 문자열 앞에 &quot;0&quot;을 붙히고
	 * 그외의 경우는 뒤에 &quot; &quot;를 붙힌다.
	 * 문자열이 length보다 긴경우 문자열을 변경없이 그대로 리턴한다.
	 * 만약, 입력 문자열이 null이면 defaultValue를 입력문자열로 사용한다.
	 * 
	 * ex. 
	 * String value=&quot;123&quot;;
	 * int length=5;
	 * String defaultValue=&quot;12345&quot;;
	 * String type=&quot;DIGIT&quot;;
	 * return &quot;00123&quot;;
	 * 
	 * &#064;return String
	 * &#064;param String value
	 * &#064;param int length
	 * &#064;param String defaultValue
	 * &#064;param String type
	 * 
	 */
	public static String convertField(String value, int length, String defaultValue, String type) throws Exception {

		String retData = null; // 리턴할 데이터

		try {

			byte[] valueByte; // 값의 바이트 배열
			int valueLength; // 값의 바이트 배열 길이
			StringBuffer dataBuf; // 디폴트문자를 가질 임시 저장소
			byte[] defaultByte; // 디폴트문자의 바이트
			String defaultStr; // 디톨트문자의 문자열

			// 필드값이 null이면 디폴트값으로 세팅
			if (value == null) {
				value = defaultValue;
			}

			// 필드값이 없다면 디폴트값으로 세팅
			if (value.equals("")) {
				value = defaultValue;
			}

			// 필드값만큼 byte 배열을 만든다.
			valueByte = value.getBytes();

			// 필드값의 길이를 구한다.
			valueLength = valueByte.length;

			// 디폴트값을 구한다. 숫자:'0', 문자:스페이스
			defaultByte = new byte[1];

			if (type.equals("DIGIT")) {
				defaultByte[0] = (byte) '0';
			} else {
				defaultByte[0] = 0x20;
			}

			defaultStr = new String(defaultByte);
			dataBuf = new StringBuffer();

			// 정해진 길이-실제길이 만큼 디폴트문자열을 만든다.
			for (int i = 0; i < length - valueLength; i++) {
				dataBuf.append(defaultStr);
			}

			if (type.equals("DIGIT")) {
				retData = dataBuf.toString() + value;
			} else {
				retData = value + dataBuf.toString();
			}

		} catch (Exception e) {
			throw new Exception("[StringUtil.convertField] 필드 변환중 에러가 발생하였습니다 " + e.toString());
		}

		return retData;

	}

	/**
	 * <pre>
	 * 입력 문자열을 length길이의 고정길이 문자열로 변환하여 리턴한다.
	 * type이 &quot;DIGIT&quot;인 경우 문자열 앞에 &quot;0&quot;을 붙히고
	 * 그외의 경우는 뒤에 &quot; &quot;를 붙힌다.
	 * 문자열이 length보다 긴경우 문자열을 변경없이 그대로 리턴한다.
	 * 만약, 입력 문자열이 null이면 defaultValue를 입력문자열로 사용한다.
	 * 
	 * ex. 
	 * String value=&quot;123&quot;;
	 * int length=5;
	 * String defaultValue=&quot;12345&quot;;
	 * String type=&quot;DIGIT&quot;;
	 * return &quot;00123&quot;;
	 * 
	 * &#064;return String
	 * &#064;param String value
	 * &#064;param int length
	 * &#064;param String defaultValue
	 * &#064;param String type
	 * 
	 */
	public static String convertErrorField(String value, int length, String defaultValue, String type) {

		String retData = null; // 리턴할 데이터

		try {

			byte[] valueByte; // 값의 바이트 배열
			int valueLength; // 값의 바이트 배열 길이
			StringBuffer dataBuf; // 디폴트문자를 가질 임시 저장소
			byte[] defaultByte; // 디폴트문자의 바이트
			String defaultStr; // 디톨트문자의 문자열

			// 필드값이 null이면 디폴트값으로 세팅
			if (value == null) {
				value = defaultValue;
			}

			// 필드값이 없다면 디폴트값으로 세팅
			if (value.equals("")) {
				value = defaultValue;
			}

			// 필드값만큼 byte 배열을 만든다.
			valueByte = value.getBytes();

			// 필드값의 길이를 구한다.
			valueLength = valueByte.length;

			// 디폴트값을 구한다. 숫자:'0', 문자:스페이스
			defaultByte = new byte[1];

			if (type.equals("DIGIT")) {
				defaultByte[0] = (byte) '0';
			} else {
				defaultByte[0] = 0x20;
			}

			defaultStr = new String(defaultByte);
			dataBuf = new StringBuffer();

			// 정해진 길이-실제길이 만큼 디폴트문자열을 만든다.
			for (int i = 0; i < length - valueLength; i++) {
				dataBuf.append(defaultStr);
			}

			if (type.equals("DIGIT")) {
				retData = dataBuf.toString() + value;
			} else {
				retData = value + dataBuf.toString();
			}

		} catch (Exception e) {
			System.err.println("StringUtil convertErrorField() Exception Occured");
		}

		return retData;
	}

	/**
	 * <pre>
	 * 입력 문자열 끝부분의 공백문자 &quot; &quot;를 제거한다.
	 * 
	 * ex. 
	 * String str=&quot; 123 0   &quot;;
	 * return &quot; 123 0&quot;;
	 * 
	 * &#064;return String
	 * &#064;param String str
	 * 
	 */
	public static String removeLastSpace(String str) {

		if (str == null || str.equals("")){
			return str;
		}

		int i;
		int len = str.length();

		for (i = 0; i < len; i++) {
			if (str.charAt(len - i - 1) != ' '){
				break;
			}
		}

		return str.substring(0, len - i);
	}

	/**
	 * <pre>
	 * String values에서 strKey 를 strVal로 대체한다.
	 * 
	 * &#064;return String
	 * &#064;param String str
	 * 
	 */
	public static String getStringReplace(String strKey, String values, String strVal) {

		StringTokenizer st = null;
		StringBuffer buff = new StringBuffer();

		st = new StringTokenizer(values, ",");

		if (st.countTokens() < 7){
			return values;
		}

		String weekday = st.nextToken().trim();
		String saturday = st.nextToken().trim();
		String sunday = st.nextToken().trim();
		String holiday = st.nextToken().trim();
		String block = st.nextToken().trim();
		String errorcode = st.nextToken().trim();
		String servicetime = st.nextToken().trim();

		if ("weekday".equals(strKey)){
			weekday = strVal;
		}
		if ("saturday".equals(strKey)){
			saturday = strVal;
		}
		if ("sunday".equals(strKey)){
			sunday = strVal;
		}
		if ("holiday".equals(strKey)){
			holiday = strVal;
		}
		if ("block".equals(strKey)){
			block = strVal;
		}
		if ("errorcode".equals(strKey)){
			errorcode = strVal;
		}
		if ("servicetime".equals(strKey)){
			servicetime = strVal;
		}

		buff.append(weekday);
		buff.append(",");
		buff.append(saturday);
		buff.append(",");
		buff.append(sunday);
		buff.append(",");
		buff.append(holiday);
		buff.append(",");
		buff.append(block);
		buff.append(",");
		buff.append(errorcode);
		buff.append(",");
		buff.append(servicetime);

		return buff.toString();

	}

	/**
	 * <pre>
	 * \&lt;/free&gt; 태그 다음에 시간과,SessionId를 append한다
	 * 
	 * return String
	 * param String str
	 * 
	 */
	public static String insertSessId(String strRe, String SessId) {

		int len = 0;
		int index = 0;

		String delim = ";";
		String str1 = null;
		String str2 = null;
		StringBuffer buff = new StringBuffer();

		try {

			String date = DateTime.getDate();
			len = strRe.length();
			index = strRe.lastIndexOf("</free>");

			if (index != -1) {

				str1 = strRe.substring(0, index);
				str2 = strRe.substring(index, len);
			}

			strRe = buff.append(str1).append(SessId).append(delim).append(date).append(delim).append(str2).toString();

		} catch (NullPointerException e) {
			System.err.println("StringUtil insertSessId() NullPointerException Occured");
		}

		return strRe;

	}

	/**
	 * <pre>
	 * 원하는 길이만큼 문자열에 앞에 &quot;0&quot;을 붙여준다.
	 * 
	 * ex. 
	 * int size=4;
	 * return &quot;0000a&quot;;
	 * 
	 * &#064;return String
	 * &#064;param String str
	 * &#064;param int size
	 * 
	 */
	public static String makeZero(String str, int size) {

		if (str == null){
			return "";
		}
		if (size == 0){
			return str;
		}

		int zeros = size;
		StringBuffer sb = new StringBuffer(str);

		for (int i = 0; i < zeros; i++)
			sb.insert(0, '0');

		return sb.toString();
	}

	/**
	 * string이 비었는지 검사.
	 * 
	 * @original Determine whether a (trimmed) string is empty
	 * 
	 * <tag>param foo The text to check. <tag>return Whether empty.
	 * 
	 * @param foo
	 *            체크할 문자열.
	 * @return 비었는지에 대한 검사 결과.
	 */
	public static final boolean isEmpty(String foo) {
		return (foo == null);
	}

	/**
	 * 반자 -> 전자로 변환하는 메소드
	 * 
	 * @param sStr
	 *            전자로 변환할 문자열
	 * @return 전자로 변환된 문자열
	 */
	public static String toDByte(String sStr) {

		char[] cArray = sStr.toCharArray();
		int iCnt = cArray.length;

		for (int i = 0; i < iCnt; i++) {
			int iCode = cArray[i];

			if (iCode == 32) {
				cArray[i] = (char) 12288; // SPACE 코드..
			} else if (iCode > 32 && iCode <= 126) {
				cArray[i] = (char) (iCode + 65248); // 영숫자, 특수문자
			}
		}

		return new String(cArray);
	}

	/**
	 * 전자 -> 반자로 변환하는 메소드
	 * 
	 * @param dStr
	 *            반자로 변환할 문자열
	 * @return 반자로 변환된 문자열
	 */
	public static String toSByte(String dStr) {

		char[] cArray = dStr.toCharArray();
		int iCnt = cArray.length;

		for (int i = 0; i < iCnt; i++) {
			int iCode = cArray[i];

			if (iCode == 12288) {
				cArray[i] = (char) 32; // SPACE 코드
			} else if (iCode > 65280 && iCode <= 65374) {
				cArray[i] = (char) (iCode - 65248); // 영숫자,특수문자
			}
		}

		return new String(cArray);
	}

	/**
	 * 문자열 좌측의 공백을 제거하는 메소드
	 * 
	 * @param str
	 *            공백을 제거할 문자열
	 * @return 좌측의 공백이 제거된 문자열
	 */
	public static String leftTrim(String str) {
		int len = str.length();
		int idx = 0;

		while (idx < len && str.charAt(idx) <= ' '){
			idx++;
		}
		
		return str.substring(idx, len);
	}

	/**
	 * 문자열 우측의 공백을 제거하는 메소드
	 * 
	 * @param str
	 *            공백을 제거할 문자열
	 * @return 우측의 공백이 제거된 문자열
	 */
	public static String rightTrim(String str) {
		int len = str.length();

		while (0 < len && str.charAt(len - 1) <= ' '){
			len--;
		}
		return str.substring(0, len);
	}

	/**
	 * 영숫자 문자열의 byte 계산 메소드
	 * 
	 * @param str
	 *            길이를 계산 할 영숫자 문자열
	 * @return str의 byte수
	 */
	public static int byteLength(String str) {
		if (str == null){
			return -1;
		}

		return str.getBytes().length;
	}

	/**
	 * 한영혼합 문자열의 byte 계산 메소드 전자변환 및 쏘시를 포함해서 계산
	 * 
	 * @param str
	 *            길이를 계산할 한영혼합 문자열
	 * @return str의 byte 수
	 */
	public static int byteLengthDB(String str) {
		if (str == null){
			return -1;
		}

		str = toDByte(str);

		int stemp = 0;
		int stemp2 = 0;
		int count = 0;

		for (int i = 0; i < str.getBytes().length; i++) {
			if (str.getBytes()[i] >= 0){
				stemp = 0;
			}else{
				stemp = 1;
			}
			if (stemp != stemp2){
				count += 2;
			}else{
				count += 1;
			}
			stemp2 = stemp;
			if (i + 1 == str.getBytes().length && stemp2 == 1){
				count += 1;
			}
		}

		return count;

	}

	/**
	 * 영숫자 문자열의 byte 제한 메소드
	 * 
	 * @param str
	 *            길이제한을 할 영숫자 문자열
	 * @param len
	 *            길이제한을 할 byte 수
	 * @return 지정된 byte 만큼의 문자열
	 */
	public static String byteValue(String str, int len) {
		if (str == null){
			return "";
		}

		String value = null;
		int count = 0;

		for (int y = 0; y < str.length(); y++) {
			count = 0;
			value = str.substring(0, str.length() - y);
			count = byteLength(value);

			if (count <= len){
				break; // 길이제한
			}
		}

		return value;
	}

	/**
	 * 한영혼합 문자열의 byte 제한 메소드 전자변환 및 쏘시를 포함해서 입력가능한 값을 리턴
	 * 
	 * @param str
	 *            길이제한을 할 영숫자 문자열
	 * @param len
	 *            길이제한을 할 byte 수
	 * @return 지정된 byte 만큼의 문자열
	 */
	public static String byteValueDB(String str, int len) {
		if (str == null){
			return "";
		}

		str = toDByte(str);

		String value = null;
		int stemp = 0;
		int stemp2 = 0;
		int count = 0;

		for (int y = 0; y < str.length(); y++) {
			count = 0;
			value = str.substring(0, str.length() - y);

			for (int i = 0; i < value.getBytes().length; i++) {
				if (value.getBytes()[i] >= 0){
					stemp = 0;
				}else{
					stemp = 1;
				}

				if (stemp != stemp2){
					count += 2;
				}else{
					count += 1;
				}

				stemp2 = stemp;

				if (i + 1 == value.getBytes().length && stemp2 == 1){
					count += 1;
				}
			}

			if (count <= len){
				break; // 길이제한
			}
		}

		return value;
	}

	/**
	 * 문자열에서 특정문자를 제거하는 메소드
	 * 
	 * @param str
	 *            원본 문자열
	 * @param ch
	 *            제거할 문자
	 * @return 특정 문자가 제거된 문자열
	 */
	public static String removeChar(String str, char ch) {
		if (str == null){
			return "";
		}
		String value = "";

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ch){
				continue;
			}
			value = value + str.charAt(i);
		}

		return value;
	}

	/**
	 * 문자열에서 '/', '-', ':', ',', '.', '%'의 char문자와 앞뒤 공백을 제거하는 메소드
	 * 
	 * @param str
	 *            원본 문자열
	 * @return '/', '-', ':', ',', '.', '%'의 char문자 및 앞뒤 공백을 제거한 문자열
	 */
	public static String removeCharAll(String str) {
		String value = null;
		value = removeChar(str, '/'); // '/' 제거
		value = removeChar(value, '-'); // '-' 제거
		value = removeChar(value, ':'); // ':' 제거
		value = removeChar(value, ','); // ',' 제거
		value = removeChar(value, '.'); // '.' 제거
		value = removeChar(value, '%'); // '%' 제거
		value = value.trim(); // 문자열 앞뒤 공백제거

		return value;
	}

	/**
	 * 문자열 중에서 특정 문자를 찾아 다른 문자로 바꾸는 메소드
	 * 
	 * @param String
	 *            str 원본 문자열
	 * @param String
	 *            target 찾을 문자열
	 * @param String
	 *            replace 변경할 문자열
	 * @return String value 변경된 전체 문자열
	 */
	public static String replace1(String str, String target, String replace) {
		if (str == null){
			return "";
		}

		int targetLen = target.length();

		StringBuffer value = new StringBuffer();
		int i = 0;
		int j = 0;

		while (j > -1) {
			j = str.indexOf(target, i);

			if (j > -1) {
				value.append(str.substring(i, j)).append(replace);
				i = j + targetLen;
			}
		}

		value.append(str.substring(i, str.length()));

		return value.toString();
	}

	/**
	 * 지정된 length만큼 문자열 앞부분을 분리하는 메소드 지정된 length만큼 분리 후 뒤쪽에 ... 이 붙어 리턴됨
	 * 
	 * @param str
	 *            원본 문자열
	 * @return 지정된 크기(length)만큼 앞부분이 분리된 문자열
	 */
	public static String splitHead(String str, int size) {
		if (str == null){
			return "";
		}

		if (str.length() > size){
			str = str.substring(0, size) + "...";
		}

		return str;
	}

	/**
	 * 지정된 length만큼 문자열 뒷부분을 분리하는 메소드 지정된 length만큼 분리 후 앞쪽에 ... 이 붙어 리턴됨
	 * 
	 * @param str
	 *            원본 문자열
	 * @return 지정된 크기(length)만큼 뒷부분이 분리된 문자열
	 */
	public static String splitTail(String str, int size) {
		if (str == null){
			return "";
		}

		if (str.length() > size){
			str = "..." + str.substring(str.length() - size);
		}

		return str;
	}

	/**
	 * 문자열의 왼쪽에 지정한 문자를 지정된 길이에 모자란 만큼 Add하는 메소드
	 * 
	 * @param str
	 *            원본 문자열
	 * @param add
	 *            원본 문자열이 지정된 length 보다 적은 경우 length 만큼 채워질 문자
	 * @param len
	 *            전체 길이
	 * @return 전체 길이만큼 지정문자가 채워진 문자열. 단 원본 문자열의 길이가 전체 지정한 길이보다 긴 경우에는 원본 문자열이
	 *         return 됨
	 */
	public static String leftPad(String str, char add, int len) {
		if (str == null){
			return "";
		}
		for (int i = str.length(); i < len; i++) {
			str = add + str;
		}

		return str;
	}

	/**
	 * 문자열의 오른쪽에 지정한 문자를 지정된 길이에 모자란 만큼 Add하는 메소드
	 * 
	 * @param str
	 *            원본 문자열
	 * @param add
	 *            원본 문자열이 지정된 length 보다 적은 경우 length 만큼 채워질 문자
	 * @param len
	 *            전체 길이
	 * @return 전체 길이만큼 지정문자가 채워진 문자열. 단 원본 문자열의 길이가 전체 지정한 길이보다 긴 경우에는 원본 문자열이
	 *         return 됨
	 */
	public static String rightPad(String str, char add, int len) {
		if (str == null){
			return "";
		}
		for (int i = str.length(); i < len; i++) {
			str = str + add;
		}

		return str;
	}

	/**
	 * 문자열의 왼쪽에 지정한 문자를 지정된 byte에 모자란 만큼 Add하는 메소드
	 * 
	 * @param str
	 *            원본 문자열
	 * @param add
	 *            원본 문자열이 지정된 byte보다 적은 경우 byte 만큼 채워질 문자
	 * @param blen
	 *            전체 byte
	 * @return 전체 byte만큼 지정문자가 채워진 문자열. 단 원본 문자열의 byte가 전체 지정한 byte보다 긴 경우에는 원본
	 *         문자열이 return 됨
	 */
	public static String leftPadB(String str, char add, int blen) {
		if (str == null){
			return "";
		}
		for (int i = str.getBytes().length; i < blen; i++) {
			str = add + str;
		}

		return str;
	}

	/**
	 * 문자열의 오른쪽에 지정한 문자를 지정된 byte에 모자란 만큼 Add하는 메소드
	 * 
	 * @param str
	 *            원본 문자열
	 * @param add
	 *            원본 문자열이 지정된 byte보다 적은 경우 byte 만큼 채워질 문자
	 * @param blen
	 *            전체 byte
	 * @return 전체 byte만큼 지정문자가 채워진 문자열. 단 원본 문자열의 byte가 전체 지정한 byte보다 긴 경우에는 원본
	 *         문자열이 return 됨
	 */
	public static String rightPadB(String str, char add, int blen) {
		if (str == null){
			return "";
		}
		for (int i = str.getBytes().length; i < blen; i++) {
			str = str + add;
		}

		return str;
	}

	/**
	 * 문자열을 숫자형 포맷과, 소수점을 붙여 변환하는 메소드 예) formCurrency("1234567890") ->
	 * 1,234,567,890 formCurrency("1234567890.12") -> 1,234,567,890.12
	 * 
	 * @param sNumber
	 *            숫자형 포맷을 실시할 문자열
	 * @return 포맷이 반영된 문자열
	 */
	public static String formCurrency(String sNumber) {
		NumberFormat nf = NumberFormat.getInstance();
		String leftStr = "";
		String rightStr = "";
		int iPos = -1;

		sNumber = removeChar(sNumber, ','); // "," 제거 02.03.12 엄완 수정

		// 소수점 자리수보다 문자열 길이가 작은경우
		if (sNumber.length() == 0){
			return "";
		}

		iPos = sNumber.indexOf(".");

		if (iPos == -1) { // 소수점이 없음
			leftStr = sNumber;
		} else { // 소수점이 있음
			leftStr = sNumber.substring(0, iPos);
			rightStr = sNumber.substring(iPos + 1);
		}

		sNumber = removeCharAll(sNumber); // character문자와 공백를 제거

		// 소수점 앞자리 숫자 Format처리
		leftStr = nf.format(Long.parseLong(leftStr));

		sNumber = leftStr;
		if (rightStr.length() > 0){
			sNumber = sNumber + ".";
		}
		sNumber = sNumber + rightStr;

		return sNumber;
	}

	/**
	 * 문자열을 숫자형 포맷과, 소수점을 붙여 변환하는 메소드 예) formCurrency("1234567890", 4, false) ->
	 * 1234567890.0000 formCurrency("12345678.90", 4, true) -> 12,345,678.9000
	 * 
	 * @param sNumber
	 *            숫자형 포맷을 실시할 문자열
	 * @param iDecCnt
	 *            소수점 이하 자리수
	 * @param bFormat
	 *            3자리마다 ,를 표시할 것인지의 여부
	 * @return 포맷이 반영된 문자열
	 */
	public static String formCurrency(String sNumber, int iDecCnt, boolean bFormat) {
		NumberFormat nf = NumberFormat.getInstance();
		String leftStr = "";
		String rightStr = "";
		int iPos = -1;

		sNumber = removeChar(sNumber, ','); // "," 제거 02.03.12 엄완 수정

		// 소수점 자리수보다 문자열 길이가 작은경우
		if (sNumber.length() == 0){
			return "";
		}

		// 소수점 처리능력 20자리로 제한
		if (iDecCnt > 20){
			return "";
		}

		iPos = sNumber.indexOf(".");

		if (iPos == -1) { // 소수점이 없음
			leftStr = sNumber;
		} else { // 소수점이 있음
			leftStr = sNumber.substring(0, iPos);
			rightStr = sNumber.substring(iPos + 1);

			if (rightStr.length() > iDecCnt){
				rightStr = rightStr.substring(0, iDecCnt);
			}
		}

		sNumber = removeCharAll(sNumber); // character문자와 공백를 제거

		// 소수점 이하 자리수에 부족한 만큼 0을 채운다.
		for (int i = rightStr.length(); i < iDecCnt; i++)
			rightStr += "0";

		// 소수점 앞자리 숫자 Format처리
		if (bFormat){
			leftStr = nf.format(Long.parseLong(leftStr));
		}else{
			leftStr = Long.toString(Long.parseLong(leftStr));
		}

		sNumber = leftStr;
		if (rightStr.length() > 0){
			sNumber = sNumber + ".";
		}
		sNumber = sNumber + rightStr;

		return sNumber;
	}

	/**
	 * 문자열을 숫자형 포맷과, 소수점을 붙여 변환하는 메소드 예) formCurrencyIn("1234567890", 4, false)
	 * ==> "123456.7890" 리턴 formCurrencyIn("12345678.90", 4, true) ==>
	 * "123,456.7890" 리턴
	 * 
	 * @param sNumber
	 *            숫자형 포맷을 실시할 문자열
	 * @param iDecCnt
	 *            소수점 이하 자리수
	 * @param bFormat
	 *            3자리마다 ,를 표시할 것인지의 여부
	 * @return 포맷이 반영된 문자열
	 */
	public static String formCurrencyIn(String sNumber, int iDecCnt, boolean bFormat) {
		NumberFormat nf = NumberFormat.getInstance();
		String sRetValue = "";

		// character문자와 공백를 제거
		sNumber = removeCharAll(sNumber);

		// 소수점 자리수보다 문자열 길이가 작은경우
		if (sNumber.length() == 0){
			return "";
		}

		// 소수점 처리능력 20자리로 제한
		if (iDecCnt > 20){
			return "";
		}

		// 소수점 지정 자리수보다 문자열의 길이가 작을때 0.??? 처리
		if (sNumber.length() <= iDecCnt) {
			sNumber = "00000000000000000000" + sNumber;
			sRetValue = "0." + sNumber.substring(sNumber.length() - iDecCnt, sNumber.length());
			return sRetValue;
		}

		// 소수점 이하자리 처리하여 보관
		if (iDecCnt > 0) {
			sRetValue = "." + sNumber.substring(sNumber.length() - iDecCnt, sNumber.length());
		}

		// 소수점 이하 자리 문자열 삭제
		sNumber = sNumber.substring(0, sNumber.length() - iDecCnt);

		// 앞자리의 0000 제거
		sNumber = Long.toString(Long.parseLong(sNumber));

		// 소수점 앞자리 숫자 Format처리
		if (bFormat){
			sNumber = nf.format(Long.parseLong(sNumber));
		}else{
			sNumber = Long.toString(Long.parseLong(sNumber));
		}

		sRetValue = sNumber + sRetValue;

		return sRetValue;
	}

	/**
	 * 금액을 입력받아 한글금액으로 변경하는 메소드
	 * 
	 * @param 변환을
	 *            실시할 문자열
	 * @param 변환type
	 *            true : 칠원, false : 7원
	 * @return 변환된 문자열
	 */
	public static String numToAmtHan(String sNumber, boolean type) {
		String[] han = new String[] { "천", "백", "십", "조", "천", "백", "십", "억", "천", "백", "십", "만", "천", "백", "십", "" };
		boolean value = false;

		sNumber = leftPad(sNumber, '0', 16);
		char[] cNumber = sNumber.toCharArray();

		StringBuffer won = new StringBuffer();

		for (int i = 0; i < cNumber.length; i++) {
			if (cNumber[i] != '0') {
				if (type){
					won.append(numToHan(cNumber[i])).append(han[i]);
				}else{
					won.append(cNumber[i]).append(han[i]);
				}

				if (i == 3 || i == 7 || i == 11){
					value = false;
				}else{
					value = true;
				}
			} else {
				if (value && (i == 3 || i == 7 || i == 11 || i == 15)) {
					won.append(han[i]);
					value = false;
				}
			}

		}

		if (won.length() == 0){
			won.append("0원");
		}else{
			won.append("원");
		}

		return won.toString();
	}

	/**
	 * 0 ~ 9 사이의 숫자를 한글로 변환 음수나 10이상의 수가 들어오면 빈문자열을 return 함 예) 1 -> "일"
	 * 
	 * @param i
	 *            변환할 숫자
	 * @return 한글로 변환된 결과
	 */
	public static String numToHan(int i) {
		if (i < 0 || i >= 10){
			return "";
		}
		String s = null;
		if (i == 0){
			s = "영";
		}else if (i == 1){
			s = "일";
		}else if (i == 2){
			s = "이";
		}else if (i == 3){
			s = "삼";
		}else if (i == 4){
			s = "사";
		}else if (i == 5){
			s = "오";
		}else if (i == 6){
			s = "육";
		}else if (i == 7){
			s = "칠";
		}else if (i == 8){
			s = "팔";
		}else if (i == 9){
			s = "구";
		}
		return s;
	}

	/**
	 * 0 ~ 9 사이의 숫자형 char를 한글로 변환 예) '1' -> "일"
	 * 
	 * @param c
	 *            숫자형 char
	 * @return 한글로 변환된 결과
	 */
	public static String numToHan(char c) {
		return numToHan(String.valueOf(c));
	}

	/**
	 * 0 ~ 9 사이의 숫자 String을 한글로 변환 예) "1" -> "일"
	 * 
	 * @param s
	 *            변환할 문자열
	 * @return 한글로 변환된 결과
	 */
	public static String numToHan(String s) {
		return numToHan(getInteger(s));
	}

	/**
	 * 금액을 입력받아 한자금액으로 변경하는 메소드
	 * 
	 * @param 변환을
	 *            실시할 문자열
	 * @param 변환type
	 *            true : 七원, false : 7원
	 * @return 변환된 문자열
	 */
	public static String numToAmtHanja(String sNumber, boolean type) {
		String[] han = new String[] { "阡", "百", "拾", "兆", "阡", "百", "拾", "億", "阡", "百", "拾", "萬", "阡", "百", "拾", "" };
		boolean value = false;

		sNumber = leftPad(sNumber, '0', 16);
		char[] cNumber = sNumber.toCharArray();

		StringBuffer won = new StringBuffer();

		for (int i = 0; i < cNumber.length; i++) {
			if (cNumber[i] != '0') {
				if (type){
					won.append(numToHanja(cNumber[i])).append(han[i]);
				}else{
					won.append(cNumber[i]).append(han[i]);
				}

				if (i == 3 || i == 7 || i == 11){
					value = false;
				}else{
					value = true;
				}
			} else {
				if (value && (i == 3 || i == 7 || i == 11 || i == 15)) {
					won.append(han[i]);
					value = false;
				}
			}

		}

		if (won.length() == 0){
			won.append("零원");
		}else{
			won.append("원");
		}

		return won.toString();
	}

	/**
	 * 0 ~ 9 사이의 숫자를 한자로 변환 음수나 10이상의 수가 들어오면 빈문자열을 return 함 예) 1 -> "壹"
	 * 
	 * @param i
	 *            변환할 숫자
	 * @return 한자로 변환된 결과
	 */
	public static String numToHanja(int i) {
		if (i < 0 || i >= 10){
			return "";
		}
		String s = null;
		if (i == 0){
			s = "零";
		}else if (i == 1){
			s = "壹";
		}else if (i == 2){
			s = "貳";
		}else if (i == 3){
			s = "參";
		}else if (i == 4){
			s = "四";
		}else if (i == 5){
			s = "五";
		}else if (i == 6){
			s = "六";
		}else if (i == 7){
			s = "七";
		}else if (i == 8){
			s = "八";
		}else if (i == 9){
			s = "九";
		}
		return s;
	}

	/**
	 * 0 ~ 9 사이의 숫자형 char를 한자로 변환 예) '1' -> "일"
	 * 
	 * @param c
	 *            숫자형 char
	 * @return 한자로 변환된 결과
	 */
	public static String numToHanja(char c) {
		return numToHanja(String.valueOf(c));
	}

	/**
	 * 0 ~ 9 사이의 숫자 String을 한자로 변환 예) "1" -> "壹"
	 * 
	 * @param s
	 *            변환할 문자열
	 * @return 한자로 변환된 결과
	 */
	public static String numToHanja(String s) {
		return numToHanja(getInteger(s));
	}

	/**
	 * String을 숫자로 변환 예) "1" -> 1
	 * 
	 * @param s
	 *            변환할 String
	 * @return 변환한 숫자, 숫자가 아닌 값인 경우 0을 return
	 */
	public static int getInteger(String s) {
		int i = 0;
		try {
			i = Integer.parseInt(s.trim());
		} catch (NumberFormatException numberformatexception) {
			i = 0;
		}
		return i;
	}

	/**
	 * 주민등록번호를 받아서 유효한 주민등록번호인지 확인
	 * 
	 * @param resNo
	 *            String 형태의 주민등록번호
	 * @return 유효한 주민등록번호인지 여부 true/false
	 */
	public static boolean isResNo(String resNo) {
		int sum;
		String checkNumber = "234567892345"; // 주민등록번호 검사용 값
		String birthDate = "";

		sum = 0;
		try {
			if (resNo != null) {
				if (resNo.length() == 13) { // 주민등록번호 자리수가 맞는가를 확인한다.
					if (Integer.parseInt(resNo.substring(6, 7)) < 1 || Integer.parseInt(resNo.substring(6, 7)) > 4) { // 주민등록번호의
																														// 뒷 부분
																														// 첫
																														// 자리가
																														// 형식에
																														// 맞는지
																														// 확인한다.
						return false;
					} else {
						switch (Integer.parseInt(resNo.substring(6, 7))) {
						case 1: {
							birthDate = "19" + resNo.substring(0, 6);
							break;
						}
						case 2: {
							birthDate = "19" + resNo.substring(0, 6);
							break;
						}
						case 3: {
							birthDate = "20" + resNo.substring(0, 6);
							break;
						}
						case 4: {
							birthDate = "20" + resNo.substring(0, 6);
							break;
						}
						default:
						}

						if (DateTime.isValidDate(birthDate)) { // 주민등록번호 앞 자리가
																// 유효한 날짜인가 점검

							for (int i = 1; i < 13; i++) { // 주민등록번호 끝 자리 점검 식
								sum = sum + Integer.parseInt(resNo.substring(i - 1, i)) * Integer.parseInt(checkNumber.substring(i - 1, i));
							}

							sum = 11 - (sum % 11);
							if (Integer.parseInt(resNo.substring(12, 13)) == (sum % 10)) {
								return true;
							} else {
								return false;
							}
						} else {
							return false;
						}
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception e) { // 숫자가 아닌 값이 넘어 왔을 경우에는 exception에서 false 처리가
								// 된다.
			return false;
		}
	}

	/**
	 * 사업자등록번호를 받아서 유효한 사업자등록번호인지 확인
	 * 
	 * @param bizNo
	 *            String 형태의 사업자등록번호
	 * @return 유효한 사업자등록번호인지 여부 true/false
	 */
	public static boolean isBizNo(String bizNo) {
		int sum;
		String checkNumber = "137137135"; // 사업자등록번호 검사용 값

		sum = 0;
		try {
			if (bizNo != null) {
				if (bizNo.length() == 10) { // 사업자등록번호 자리수가 맞는가를 확인한다.
					for (int i = 1; i < 10; i++) { // 사업자등록번호 끝 자리 점검 식
						sum = sum + Integer.parseInt(bizNo.substring(i - 1, i)) * Integer.parseInt(checkNumber.substring(i - 1, i));
					}

					sum = sum + ((Integer.parseInt(bizNo.substring(8, 9)) * 5) / 10);
					sum = (sum - (sum % 1)) % 10;
					if (sum != 0) {
						sum = 10 - sum;
					}
					if (Integer.parseInt(bizNo.substring(9, 10)) == sum) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception e) { // 숫자가 아닌 값이 넘어 왔을 경우에는 exception에서 false 처리가
								// 된다.
			return false;
		}
	}

	/**
	 * null string을 ""로 변환한다. 또한 null이 아닐 경우 출력값에 대해 String.trim()을 수행한다.
	 * 
	 * @original Deal with null strings converting them to "" instead. It also
	 *           invokes String.trim() on the output.
	 * 
	 * <tag>param foo String <tag>return String
	 * 
	 * @param foo
	 *            String.
	 * @return String.
	 */
	public static final String makeString(String foo) {
		return (foo == null ? "" : foo.trim());
	}

	/**
	 * 지정된 string이 <code>null</code>도 아니고 빈(empty) String도 아닌 지 검사한다.
	 * 
	 * @original Validates that the supplied string is neither <code>null</code>
	 *           nor the empty string.
	 * 
	 * @param foo
	 *            체크할 문자열.
	 * @return 유효성 검사 결과.
	 */
	public static final boolean isValid(String foo) {
		return (foo != null && foo.length() > 0);
	}

	/**
	 * string이 비었는지 검사.
	 * 
	 * @original Determine whether a (trimmed) string is empty
	 * 
	 * <tag>param foo The text to check. <tag>return Whether empty.
	 * 
	 * @param foo
	 *            체크할 문자열.
	 * @return 비었는지에 대한 검사 결과.
	 */
	public static final boolean isEmpty2(String foo) {
		return (foo == null );
	}
	

	/**
	 * 두 string을 비교하여 값이 같으면 <code>true</code>를 리턴한다.
	 * 
	 * @original Compares two Strings, returns true if their values are the
	 *           same.
	 * 
	 * <tag>param s1 The first string. <tag>param s2 The second string.
	 * <tag>return True if the values of both strings are the same.
	 * 
	 * @param s1
	 *            첫번째 문자열.
	 * @param s2
	 *            두번째 문자열.
	 * @return 두 문자열의 값이 같으면 True.
	 */
	public static boolean equals(String s1, String s2) {
		if ( s1 == null ) s1 = "";
		if ( s2 == null ) s2 = "";
		return s1.trim().equals(s2.trim());
	}

	public static final int PPKEY_CLASSNAME = 0;
	public static final int PPKEY_ID = 1;
	public static final int PPKEY_PROPERTY = 2;

	/**
	 * "substring[substring]substring" 형태의 string을 받아 세 substring으로 나누어 리턴한다.
	 * 
	 * @original Takes a String of the form substring[substring]subtring and
	 *           returns the 3 substrings
	 * 
	 * <tag>return a three element String array
	 * 
	 * @return 세 substring에 대한 배열.
	 */
	public static String[] parseObjectKey(String s) {
		String[] p = new String[3];
		StringTokenizer st = new StringTokenizer(s, "[]");
		int count = st.countTokens();
		if (count > 1) {
			p[0] = st.nextToken();
			p[1] = st.nextToken();
			if (count == 3) {
				p[2] = st.nextToken();
			}
		}
		return p;
	}

	/**
	 * 문자열의 Underscore를 제거하고 각 Sub의 첫 글자를 대문자로 교체한다.
	 * 예를 들어 foo_bar는 FooBar가 된다.
	 * 들어온 문자열이 모두 대문자일 경우 소문자로 변환하고 로직을 수행한다.
	 * 또한, 제일 첫번째 문자는 대문자로 변경하지 않는다.
	 * 
	 * @original Remove Underscores from a string and replaces first Letters
	 *           with Capitals. foo_bar becomes FooBar
	 */
	public static String removeUnderScores(String data) {
		int i = 1;
		String temp = null;
		StringBuffer out = new StringBuffer();
		temp = data.toLowerCase();

		StringTokenizer st = new StringTokenizer(temp, "_");
		while (st.hasMoreTokens()) {
			String element = (String) st.nextElement();
			out.append((i == 1) ? element : firstLetterCaps(element));
			i++;
		}
		return out.toString();
	}

	/**
	 * 첫번째 글자를 대문자로 교체하고 나머지는 이전과 같이 둔다.
	 * 
	 * @original Makes the first letter caps and leaves the rest as is.
	 * 
	 * @param data
	 *            교체할 문자열.
	 */
	public static String firstLetterCaps(String data) {
		StringBuffer sbuf = new StringBuffer(data.length());
		sbuf.append(data.substring(0, 1).toUpperCase()).append(data.substring(1));
		return sbuf.toString();
	}

	/**
	 * Splits the provided CSV text into a list.
	 * 
	 * @param text
	 *            The CSV list of values to split apart.
	 * @param separator
	 *            The separator character.
	 * @return The list of values.
	 */
	public static String[] split(String text, String separator) {
		StringTokenizer st = new StringTokenizer(text, separator);
		String[] values = new String[st.countTokens()];
		int pos = 0;
		while (st.hasMoreTokens()) {
			values[pos++] = st.nextToken();
		}
		return values;
	}

	/**
	 * Joins the elements of the provided array into a single string containing
	 * a list of CSV elements.
	 * 
	 * @param list
	 *            The list of values to join together.
	 * @param separator
	 *            The separator character.
	 * @return The CSV text.
	 */
	public static String join(String[] list, String separator) {
		StringBuffer csv = new StringBuffer();
		for (int i = 0; i < list.length; i++) {
			if (i > 0) {
				csv.append(separator);
			}
			csv.append(list[i]);
		}
		return csv.toString();
	}

	/**
	 * Takes a block of text which might have long lines in it and wraps the
	 * long lines based on the supplied wrapColumn parameter. It was initially
	 * implemented for use by VelocityEmail. If there are tabs in inString, you
	 * are going to get results that are a bit strange, since tabs are a single
	 * character but are displayed as 4 or 8 spaces. Remove the tabs.
	 * 
	 * @param inString
	 *            Text which is in need of word-wrapping.
	 * @param newline
	 *            The characters that define a newline.
	 * @param wrapColumn
	 *            The column to wrap the words at.
	 * @return The text with all the long lines word-wrapped.
	 */

	public static String wrapText(String inString, String newline, int wrapColumn) {
		StringTokenizer lineTokenizer = new StringTokenizer(inString, newline, true);
		StringBuffer stringBuffer = new StringBuffer();

		while (lineTokenizer.hasMoreTokens()) {
			try {
				String nextLine = lineTokenizer.nextToken();

				if (nextLine.length() > wrapColumn) {
					// This line is long enough to be wrapped.
					nextLine = wrapLine(nextLine, newline, wrapColumn);
				}

				stringBuffer.append(nextLine);
			} catch (NoSuchElementException nsee) {
				// thrown by nextToken(), but I don't know why it would
				break;
			}
		}

		return stringBuffer.toString();
	}

	/**
	 * Wraps a single line of text. Called by wrapText(). I can't think of any
	 * good reason for exposing this to the public, since wrapText should always
	 * be used AFAIK.
	 * 
	 * @param line
	 *            A line which is in need of word-wrapping.
	 * @param newline
	 *            The characters that define a newline.
	 * @param wrapColumn
	 *            The column to wrap the words at.
	 * @return A line with newlines inserted.
	 */

	protected static String wrapLine(String line, String newline, int wrapColumn) {
		StringBuffer wrappedLine = new StringBuffer();

		while (line.length() > wrapColumn) {
			int spaceToWrapAt = line.lastIndexOf(' ', wrapColumn);

			if (spaceToWrapAt >= 0) {
				wrappedLine.append(line.substring(0, spaceToWrapAt));
				wrappedLine.append(newline);
				line = line.substring(spaceToWrapAt + 1);
			}

			// This must be a really long word or URL. Pass it
			// through unchanged even though it's longer than the
			// wrapColumn would allow. This behavior could be
			// dependent on a parameter for those situations when
			// someone wants long words broken at line length.
			else {
				spaceToWrapAt = line.indexOf(' ', wrapColumn);

				if (spaceToWrapAt >= 0) {
					wrappedLine.append(line.substring(0, spaceToWrapAt));
					wrappedLine.append(newline);
					line = line.substring(spaceToWrapAt + 1);
				} else {
					wrappedLine.append(line);
					line = "";
				}
			}
		}

		// Whatever is left in line is short enough to just pass through,
		// just like a small small kidney stone
		wrappedLine.append(line);

		return wrappedLine.toString();
	}

	/**
	 * 문자열 내의 한 substring을 다른 substring으로 교환한다.
	 * 
	 * @original Replaces one substring with another substring within a string.
	 *           <tag>param str The string within which to perform the replace.
	 *           <tag>param strOld The substring to be replaced. <tag>param
	 *           strNew The substring to replace with. <tag>return A string
	 *           where an old substring has been replaced with a new one. If the
	 *           old substring was not found within the string, the original
	 *           string will be replaced.
	 * 
	 * @param str
	 *            교환할 substring을 갖는 문자열.
	 * @param strOld
	 *            교환될 substring.
	 * @param strNew
	 *            교환할 substring.
	 * @return 새로운 substring으로 교환된 문자열. 기존의 substring이 str에서 찾을 수 없는 경우, 기존의
	 *         문자열이 대체된다.
	 */
	public static final String replace4(String str, String strOld, String strNew) {
		return replace(new StringBuffer(), str, strOld, strNew).toString();
	}

	/**
	 * 문자열 내의 한 substring을 다른 substring으로 교환한다.
	 * 
	 * @original Replaces one substring with another substring within a string.
	 *           <tag>param sb The stringbuffer where the resulting string is
	 *           appended. <tag>param str The string within which to perform the
	 *           replace. <tag>param strOld The substring to be replaced.
	 *           <tag>param strNew The substring to replace with. <tag>return A
	 *           string where an old substring has been replaced with a new one.
	 *           If the old substring was not found within the string, the
	 *           original string will be replaced.
	 * 
	 * @param sb
	 *            결과 문자열이 저장될 stringbuffer.
	 * @param str
	 *            교환할 substring을 갖는 문자열.
	 * @param strOld
	 *            교환될 substring.
	 * @param strNew
	 *            교환할 substring.
	 * @return 새로운 substring으로 교환된 문자열. 기존의 substring이 str에서 찾을 수 없는 경우, 기존의
	 *         문자열이 대체된다.
	 */
	public static final StringBuffer replace(StringBuffer sb, String str, String strOld, String strNew) {
		int iIndex = str.indexOf(strOld, 0);
		if (iIndex == -1) {
			sb.append(str);
			return sb;
		}
		int nLenOld = strOld.length();
		int iLast = 0;
		while (iIndex != -1) {
			sb.append(str.substring(iLast, iIndex)).append(strNew);
			iLast = iIndex + nLenOld;
			iIndex = str.indexOf(strOld, iLast);
		}
		return sb.append(str.substring(iLast));
	}

	/**
	 * 문자열 내의 한 substring을 다른 substring으로 교환한다.
	 * 
	 * @original Replaces one substring with another substring within a string.
	 *           <tag>param sb The stringbuffer on which to perform the replace.
	 *           <tag>param sOld The substring to be replaced. <tag>param sNew
	 *           The substring to replace with. <tag>return The passed
	 *           stringbuffer where the old substring has been replaced with the
	 *           new one. If the old substring was not found within the
	 *           stringbuffer, the original stringbuffer will be returned.
	 * 
	 * @param sb
	 *            교환할 substring을 갖는 문자열 버퍼.
	 * @param strOld
	 *            교환될 substring.
	 * @param strNew
	 *            교환할 substring.
	 * @return 새로운 substring으로 교환된 문자열. 기존의 substring이 str에서 찾을 수 없는 경우, 기존의 문자열
	 *         버퍼가 리턴된다.
	 */
	public static final StringBuffer replace(StringBuffer sb, String sOld, String sNew) {
		/**
		 * * This code works. * It differs from the other replace by that it
		 * uses the built-in replace * method in the stringbuffer class.
		 * However, it runs a little bit slower * than I hoped it should. When
		 * run with the following parameters: * sb:
		 * "1234567890123456789012345678901234567890" * sOld: "123" * sNew:
		 * "abcd" * 1.000.000 times, it runs in 4236 milliseconds, while the
		 * other runs in * 3575 milliseconds. *
		 */
		String s = sb.toString();
		int nLast = 0;
		int nIndex = s.indexOf(sOld, nLast);
		if (nIndex == -1) {
			return sb;
		}
		int nOld = sOld.length();
		int nNew = sNew.length();
		int nOffset = 0;
		int nDiff = nNew - nOld;
		int nLen = 0;
		if (nDiff == 0) {
			while (nIndex != -1) {
				sb.replace(nIndex, nIndex + nOld, sNew);
				nLast = nIndex + nOld;
				nIndex = s.indexOf(sOld, nLast);
			}
		} else {
			while (nIndex != -1) {
				nLen = nIndex + nOffset;
				sb.replace(nLen, nLen + nOld, sNew);
				nLast = nIndex + nOld;
				nIndex = s.indexOf(sOld, nLast);
				nOffset += nDiff;
			}
		}
		return sb;
	}

	/***************************************************************************
	 * 한글인지 아닌지 검사 하는 함수.
	 * 
	 * @param uni20
	 *            원본 문자열
	 * @return 한글일 경우 true
	 **************************************************************************/
	public static boolean checkHan(String uni20) {
		boolean result = false;

		if (uni20 == null){
			return result;
		}

		int len = uni20.length();
		char c;
		for (int i = 0; i < len; i++) {
			c = uni20.charAt(i);
			if (c < 0xac00 || 0xd7a3 < c) {
				result = false;
			} else {
				result = true;
				break;
			}
		}
		return result;
	}

	/***************************************************************************
	 * 문자열 치환 함수.
	 * 
	 * <pre>
	 * String source = &quot;abcdefghabcdefgh&quot;;
	 * String subject = &quot;cd&quot;;
	 * String object = &quot;1234&quot;;
	 * String rst = strUtil.replace(source, subject, object);
	 * //rst 는 &quot;ab1234efghab1234efgh&quot; 가 된다.
	 * </pre>
	 * 
	 * @param source
	 *            원본 문자열
	 * @param subject
	 *            바뀔 문자열
	 * @param object
	 *            바꿀 문자열
	 * @return 바뀐 문자열
	 **************************************************************************/
	public static String replace3(String source, String subject, String object) {
		StringBuffer rtnStr = new StringBuffer();
		String preStr = "";
		String nextStr = source;
		while (source.indexOf(subject) >= 0) {
			preStr = source.substring(0, source.indexOf(subject));
			nextStr = source.substring(source.indexOf(subject) + subject.length(), source.length());
			source = nextStr;
			rtnStr.append(preStr).append(object);
		}
		rtnStr.append(nextStr);
		return rtnStr.toString();
	}

	public static String replaceOnce(String source, String subject, String object) {
		StringBuffer rtnStr = new StringBuffer();
		String preStr = "";
		String nextStr = source;
		if (source.indexOf(subject) >= 0) {
			preStr = source.substring(0, source.indexOf(subject));
			nextStr = source.substring(source.indexOf(subject) + subject.length(), source.length());
			rtnStr.append(preStr).append(object).append(nextStr);
			return rtnStr.toString();
		} else {
			return source;
		}
	}

	// 바꾸고자 하는 문자열이 순차적이지 않을 때 사용
	public static String replaceChar(String source, String subject, String object) {
		StringBuffer rtnStr = new StringBuffer();
		String preStr = "";
		String nextStr = source;
		char chA;

		for (int i = 0; i < subject.length(); i++) {
			chA = subject.charAt(i);

			if (source.indexOf(chA) >= 0) {
				preStr = source.substring(0, source.indexOf(chA));
				nextStr = source.substring(source.indexOf(chA) + 1, source.length());
				source = rtnStr.append(preStr).append(object).append(nextStr).toString();
			}
		}
		return source;
	}

	/***************************************************************************
	 * 문자열의 CharSet을 unicode(8859_1) 로 바꾸는 함수.
	 * 
	 * <pre>
	 * String source = &quot;가나다라마바사아&quot;;
	 * String rst = strUtil.str2uni(src);
	 * </pre>
	 * 
	 * @param source
	 *            원본 문자열
	 * @return 유니코드로 변환된 문자열
	 **************************************************************************/
	public static String toUnicode(String source) {
		String ret = null;
		if (source == null){
			return null;
		}
		try {
			ret = new String(source.getBytes(), "8859_1");
		} catch (UnsupportedEncodingException e) {
			ret = null;
		}
		return ret;
	}

	/***************************************************************************
	 * 문자열의 CharSet을 EUC-KR(KSC5601) 로 바꾸는 함수.
	 * 
	 * <pre>
	 * String source = &quot;가나다라마바사아&quot;;
	 * String rst = strUtil.str2uni(src);
	 * </pre>
	 * 
	 * @param source
	 *            원본 문자열
	 * @return 유니코드로 변환된 문자열
	 **************************************************************************/
	public static String toEuckr(String source) {
		String ret = null;
		if (source == null){
			return null;
		}
		try {
			ret = new String(source.getBytes(), "KSC5601");
		} catch (UnsupportedEncodingException e) {
			ret = null;
		}
		return ret;
	}

	public static String toEuckr2(String source) {
		String ret = null;
		if (source == null){
			return null;
		}
		try {
			ret = new String(source.getBytes("8859_1"), "KSC5601");
		} catch (UnsupportedEncodingException e) {
			ret = null;
		}
		return ret;
	}

	/***************************************************************************
	 * 문자열을 원하는 형태의 CharSet으로 바꾸는 함수.
	 * 
	 * <pre>
	 * String source = &quot;가나다라마바사아&quot;;
	 * String rst = strUtil.Str2charset(src, &quot;ksc5601&quot;);
	 * </pre>
	 * 
	 * @param source
	 *            원본 문자열
	 * @param charset
	 *            CharSet
	 * @return 지정 charset으로 변환된 문자열
	 **************************************************************************/
	public static String toCharset(String source, String charset) {
		String ret = null;
		if (source == null){
			return null;
		}
		try {
			ret = new String(source.getBytes("8859_1"), charset);
		} catch (UnsupportedEncodingException e) {
			ret = null;
		}
		return ret;
	}

	/***************************************************************************
	 * DB에 넣기 위해 single quotation 입력을 처리하는 메서드
	 * 
	 * @param source
	 *            원본 문자열
	 * @return single quotation 처리된 문자열
	 **************************************************************************/
	public static String procQuotation(String source) {
		String ret = null;
		if (source == null){
			return null;
		}
		ret = replace2(source, "'", "''");
		return ret;
	}

	/***************************************************************************
	 * single quotation 입력을 처리하고 앞뒤로 single quotation을 추가하는 메서드
	 * 
	 * @param source
	 *            원본 문자열
	 * @return single quotation 처리된 문자열
	 **************************************************************************/
	public static String autoQuotation(String source) {
		return "'" + procQuotation(source) + "'";
	}

	/***************************************************************************
	 * 문자열이 null인지 확인하고 null인 경우 지정된 문자열로 바꾸는 함수.
	 * 
	 * <pre>
	 * String id1 = strUtil.isNull(request.getParameter(&quot;id1&quot;),&quot;&quot;);
	 * 서블릿 요청 파라메터 id1에 대한 값이 null이면 &quot;&quot; 로 바꾼다.
	 * </pre>
	 * 
	 * @param source
	 *            원본 문자열
	 * @param value
	 *            null일경우 바뀔 문자열
	 * @return 문자열
	 **************************************************************************/
	public static String isNull(String source, String value) {
		String retVal;
		if (source == null || source.length() < 1) {
			retVal = value;
		} else {
			retVal = source;
		}
		retVal = replace2(retVal, "'", "`"); // add by khko 20030908 '를 `로
												// 바꾼다
		return retVal;
	}

	/***************************************************************************
	 * 문자열이 null인지 확인하고 null인 경우 지정된 문자열로 바꾸는 함수. ',' 문자가 있는경우 제거한다.
	 * 
	 * <pre>
	 * String id1 = strUtil.isNull(request.getParameter(&quot;id1&quot;),&quot;&quot;);
	 * 서블릿 요청 파라메터 id1에 대한 값이 null이면 &quot;&quot; 로 바꾼다.
	 * </pre>
	 * 
	 * @param source
	 *            원본 문자열
	 * @param value
	 *            null일경우 바뀔 문자열
	 * @return 문자열
	 **************************************************************************/
	// add by khko 20030916 ','를 제거한다
	public static String isNull3(String source, String value) {
		String retVal;
		if (source == null || source.length() < 1) {
			retVal = value;
		} else {
			retVal = source;
		}
		retVal = replace2(retVal, ",", "");
		return retVal;
	}

	public static String isNull(String[] source, String value) {
		String retVal;
		if (source == null) {
			retVal = value;
		} else {
			retVal = source[0];
		}
		return retVal;
	}

	/***************************************************************************
	 * 문자열을 int형으로 변환하고, null인지 확인하여 null인 경우 지정된 int로 바꾸는 함수.
	 * 
	 * <pre>
	 * int id1 = strUtil.isNull(request.getParameter(&quot;id1&quot;),0);
	 * 서블릿 요청 파라메터 id1에 대한 값이 null이면 0으로 바꾼다.
	 * null이 아니면 int형으로 변환하고, 변환 불가능하면 지정값 0으로 되돌린다.
	 * </pre>
	 * 
	 * @param source
	 *            원본 문자열
	 * @param val
	 *            null일경우 바뀔 문자열
	 * @return 정수
	 **************************************************************************/
	public static int isNull(String source, int value) {
		int retVal;
		try {
			if (source == null) {
				retVal = value;
			} else {
				retVal = Integer.parseInt(source);
			}
		} catch (Exception e) {
			retVal = value;
		}
		return retVal;
	}

	public static String isNull2(String source, String value) {
		String str;
		char ch;
		String Result = "";
		int i;
		if (source == null) {
			str = value;
		} else {
			str = source;
		}
		try {
			for (i = 0; i < str.length(); i++) {
				ch = str.charAt(i);
				if (ch == '"') {
					Result += "\"";
				} else if (ch == '\'') {
					Result += "\"";
				} else {
					Result += ch;
				}
			}
			return Result;
		} catch (Exception e) {
			System.err.println("StringUtil isNull2() Exception Occured");
			return Result;
		}
	}

	/***************************************************************************
	 * 문자열을 지정한 분리자에 의해 배열로 리턴하는 함수.
	 * 
	 * <pre>
	 * String[] rst = strUtil.split(&quot;2002-01-20&quot;,&quot;-&quot;);
	 * 결과
	 * rst.length = 3
	 * rst[0] = 2002, rst[1] = 01, rst[2] = 20
	 * 
	 * String[] rst = strUtil.split(&quot;20020120&quot;,&quot;-&quot;);
	 * 결과
	 * rst.length = 1
	 * rst[0] = 20020120
	 * 
	 * 
	 * </pre>
	 * 
	 * @param source
	 *            원본 문자열
	 * @param separator
	 *            분리자
	 * @return 분리자로 나뉘어진 문자열배열
	 **************************************************************************/
	public static String[] split3(String source, String separator) throws NullPointerException {
		String[] rtnVal = null;
		int cnt = 1;

		int index = source.indexOf(separator);
		int index0 = 0;
		while (index >= 0) {
			cnt++;
			index = source.indexOf(separator, index + 1);
		}
		rtnVal = new String[cnt];
		cnt = 0;
		index = source.indexOf(separator);
		while (index >= 0) {
			rtnVal[cnt] = source.substring(index0, index);
			index0 = index + 1;
			index = source.indexOf(separator, index + 1);
			cnt++;
		}
		rtnVal[cnt] = source.substring(index0);
		return rtnVal;
	}

	/***************************************************************************
	 * 문자열을 지정한 분리자에 의해 지정된 길이의 배열로 리턴하는 함수.
	 * 
	 * <pre>
	 * String[] rst = strUtil.split(&quot;2002-01-20&quot;,&quot;-&quot;,4);
	 * 결과
	 * rst.length = 4
	 * rst[0] = &quot;2002&quot;, rst[1] = &quot;01&quot;, rst[2] = &quot;20&quot;, rst[3] = &quot;&quot;
	 * 
	 * String[] rst = strUtil.split(&quot;20020120&quot;,&quot;-&quot;,4);
	 * 결과
	 * rst.length = 4
	 * rst[0] = &quot;20020120&quot;, rst[1] = &quot;&quot;, rst[2] = &quot;&quot;, rst[3] = &quot;&quot;
	 * 
	 * 
	 * </pre>
	 * 
	 * @param source
	 *            원본 문자열
	 * @param separator
	 *            분리자
	 * @param arraylength
	 *            배열 길이
	 * @return 분리자로 나뉘어진 문자열배열
	 **************************************************************************/
	public static String[] split(String source, String separator, int arraylength) throws NullPointerException {
		String[] rtnVal = new String[arraylength];

		int cnt = 0;
		int index0 = 0;
		int index = source.indexOf(separator);
		while (index >= 0 && cnt < (arraylength - 1)) {
			rtnVal[cnt] = source.substring(index0, index);
			index0 = index + 1;
			index = source.indexOf(separator, index + 1);
			cnt++;
		}
		rtnVal[cnt] = source.substring(index0);
		if (cnt < (arraylength - 1)) {
			for (int i = cnt + 1; i < arraylength; i++) {
				rtnVal[i] = "";
			}
		}
		return rtnVal;
	}

	/***************************************************************************
	 * 문자열배열을 지정한 분리자에 의해 합한 문자열로 리턴하는 함수.
	 * 
	 * <pre>
	 * test[0] = &quot;2000&quot;, test[1] = &quot;01', test[2] = '02'
	 * String rst = strUtil.patch(request.getParameterValues(&quot;test&quot;),&quot;-&quot;);
	 * 결과
	 * rst = 2002-01-02
	 * </pre>
	 * 
	 * @param source
	 *            원본 문자열 배열
	 * @param separator
	 *            분리자
	 * @return 분리자 합친 문자열
	 **************************************************************************/
	public static String patch(String[] source, String separator) throws NullPointerException {
		return patch(source, separator, false);
	}

	/***************************************************************************
	 * 문자열배열을 지정한 분리자에 의해 합한 문자열로 리턴하는 함수.
	 * 
	 * <pre>
	 * test[0] = &quot;2000&quot;, test[1] = &quot;&quot;, test[2] = &quot;02&quot;, test[3] = &quot;00&quot;
	 * String rst = strUtil.patch(request.getParameterValues(&quot;test&quot;),&quot;-&quot;,true);
	 * 결과
	 * isblankinsert 가 ture 시 rst = &quot;2002-02-00&quot;
	 * isblankinsert 가 false 시 rst = &quot;2002--02-00&quot;
	 * </pre>
	 * 
	 * @param source
	 *            원본 문자열 배열
	 * @param separator
	 *            분리자
	 * @param isblankinsert
	 *            빈값에서 분리자를 표시할 지 여부
	 * @return 분리자 합친 문자열
	 **************************************************************************/
	public static String patch(String[] source, String separator, boolean isblankinsert) throws NullPointerException {
		StringBuffer rtnVal = new StringBuffer();
		int cnt = 0;
		if (source != null) {
			for (int i = 0; i < source.length; i++) {
				if (!isblankinsert) {
					if (cnt != 0){
						rtnVal.append(separator);
					}
					rtnVal.append(isNull(source[i], ""));
					cnt++;
				} else {
					if (isNull(source[i], "").length() > 0) {
						if (cnt != 0){
							rtnVal.append(separator);
						}
						rtnVal.append(source[i]);
						cnt++;
					}
				}
			}
		}
		return rtnVal.toString();
	}

	/***************************************************************************
	 * 문자열이 지정한 길이를 초과했을때 지정한길이에다가 해당 문자열을 붙여주는것
	 * 
	 * <pre>
	 * test[0] = &quot;2000&quot;, test[1] = &quot;&quot;, test[2] = &quot;02&quot;, test[3] = &quot;00&quot;
	 * String rst = strUtil.cutString(request.getParameterValues(&quot;aaaaaaabbbbbbb&quot;,&quot;..&quot;,5);
	 * 결과
	 * aaaaa..
	 * </pre>
	 * 
	 * @param source
	 *            원본 문자열 배열
	 * @param output
	 *            더할문자열
	 * @param slength
	 *            지정길이
	 * @return 지정길이로 잘라서 더할분자열 합친 문자열
	 **************************************************************************/

	public static String cutNAppendString(String source, String output, int slength) {
		String rtnVal = null;
		if (source != null) {
			if (source.length() > slength) {
				rtnVal = source.substring(0, slength) + output;
			} else {
				rtnVal = source;
			}
		}
		return rtnVal;
	}

	/***************************************************************************
	 * 문자열이 지정한 길이를 초과했을때 해당 문자열을 삭제하는 메서드
	 * 
	 * <pre>
	 * test[0] = &quot;2000&quot;, test[1] = &quot;&quot;, test[2] = &quot;02&quot;, test[3] = &quot;00&quot;
	 * String rst = strUtil.cutString(request.getParameterValues(&quot;aaaaaaabbbbbbb&quot;,&quot;..&quot;,5);
	 * 결과
	 * aaaaa..
	 * </pre>
	 * 
	 * @param source
	 *            원본 문자열 배열
	 * @param output
	 *            더할문자열
	 * @param slength
	 *            지정길이
	 * @return 지정길이로 잘라서 더할분자열 합친 문자열
	 **************************************************************************/

	public static String cutString(String source, int slength) {
		String rtnVal = null;
		if (source != null) {
			if (slength > 0 && source.length() > slength) {
				rtnVal = source.substring(0, slength);
			} else{
				rtnVal = source;
			}
		}
		return rtnVal;
	}

	/**
	 * 주어진 길이보다 길이가 작은 문자열을 앞에 0을 붙여 패딩한다 <BR>
	 * 
	 * @param str
	 *            문자열
	 * @param len
	 *            길이
	 * @return 앞에 '0'으로 패딩된 문자열을 리턴한다. 단, 주어진 길이보다 크거나 같으면 원본문자열을 그대로 리턴한다
	 */

	public static String paddingZero(String str, int len) {
		int strLen = str.length();
		int cab = 0;
		String tmp = "";
		if (strLen >= len){
			return str;
		}else{
			cab = len - strLen;
		}

		for (int ii = 0; ii < cab; ii++) {
			tmp = "0" + tmp;
		}

		return tmp + str;
	}

	/**
	 * 첫문자를 대문자로 바꿈 <BR>
	 * 
	 * @param str
	 *            입력문자열
	 * @return 변환된 문자열을 리턴한다
	 */

	public static String initCap(String str) {
		// A: 65, a: 97
		// 소문자일 경우 대문자로 변경
		String second = str.substring(1);
		char h = str.charAt(0);
		if (h >= 'a' && h <= 'z') {
			String first = String.valueOf((char) (h - 32));
			return first + second;
		} else{
			return str;
		}
	}

	/**
	 * 원본문자열(str)에서 캐릭터(ch)를 찾아 제거한다 <BR>
	 * 
	 * @param str
	 *            입력문자열
	 * @param 제거할
	 *            문자
	 * @return 변환된 문자열을 리턴한다
	 */

	public static String removeChar2(String str, char ch) {
		return removeChar(str, String.valueOf(ch));
	}

	/**
	 * 원본문자열(str)에서 문자열(ch)을 찾아 제거한다 <BR>
	 * 
	 * @param str
	 *            입력문자열
	 * @param 제거할
	 *            문자열
	 * @return 변환된 문자열을 리턴한다
	 */

	public static String removeChar(String str, String ch) {
		StringBuffer buff = new StringBuffer();
		StringTokenizer token = new StringTokenizer(str, ch);
		while (token.hasMoreTokens()) {
			buff.append(token.nextToken());
		}

		return buff.toString();
	}

	/**
	 * 문자열을 정수로 변환한다 <br>
	 * 
	 * @param str
	 *            입력문자열
	 * @return 입력문자열이 NULL 일 경우에는 0, 그외는 변환된 정수를 리턴한다.
	 */

	public static int str2int(String str) {
		if (str == null || "".equals(str)){
			return 0;
		}else{
			return Integer.parseInt(str);
		}
	}

	/**
	 * 문자열을 값이 큰 정수로 변환한다 <br>
	 * 
	 * @param str
	 *            입력문자열
	 * @return 입력문자열이 NULL 일 경우에는 0, 그외는 변환된 정수를 리턴한다.
	 */

	public static long str2long(String str) {
		if (str == null || "".equals(str)){
			return 0;
		}else{
			return Long.parseLong(str);
		}
	}

	/**
	 * 문자열을 실수로 변환한다 <br>
	 * 
	 * @param str
	 *            입력문자열
	 * @return 입력문자열이 NULL 일 경우에는 0, 그외는 변환된 실수를 리턴한다.
	 */

	public static double str2float(String str) {
		if (str == null || "".equals(str)){
			return 0;
		}else{
			return Float.parseFloat(str);
		}
	}

	/**
	 * 문자열을 값이 큰 실수로 변환한다 <br>
	 * 
	 * @param str
	 *            입력문자열
	 * @return 입력문자열이 NULL 일 경우에는 0, 그외는 변환된 실수를 리턴한다.
	 */

	public static double str2double(String str) {
		if (str == null || "".equals(str)){
			return 0;
		}else{
			return Double.parseDouble(str);
		}
	}

	/**
	 * 정수를 문자열로 변환한다 <br>
	 * 
	 * @param i
	 *            입력정수
	 * @return 변환된 문자열을 리턴한다.
	 */

	public static String int2Str(int iVal) {
		return String.valueOf(iVal);
	}

	/**
	 * 값이 큰 정수를 문자열로 변환한다 <br>
	 * 
	 * @param i
	 *            입력정수
	 * @return 변환된 문자열을 리턴한다.
	 */

	public static String long2Str(long iVal) {
		return String.valueOf(iVal);
	}

	/**
	 * 실수를 문자열로 변환한다 <br>
	 * 
	 * @param i
	 *            입력실수
	 * @return 변환된 문자열을 리턴한다.
	 */

	public static String float2Str(long iVal) {
		return String.valueOf(iVal);
	}

	/**
	 * 값이 큰 실수를 문자열로 변환한다 <br>
	 * 
	 * @param i
	 *            입력실수
	 * @return 변환된 문자열을 리턴한다.
	 */

	public static String double2Str(double iVal) {
		return String.valueOf(iVal);
	}

	/**
	 * 널인 문자열을 스페이스("")로 치환한다 <BR>
	 * 단, 좌우 공백이 있는 문자열은 trim 한다 <br>
	 * 
	 * @param s
	 *            입력문자열
	 * @return 치환된 문자열
	 */

	public static String null2space(String s) {
		if (s == null || s.length() == 0){
			return "";
		}else{
			return s.trim();
		}
	}

	/**
	 * 널인 문자열을 스페이스("")로 치환한다 <BR>
	 * 단, 좌우 공백이 있는 문자열은 trim 한다 <br>
	 * 
	 * @param s
	 *            입력문자열
	 * @return 치환된 문자열
	 */

	public static String null2str(String s) {
		if (s == null || s.length() == 0){
			return "";
		}else{
			return s.trim();
		}
	}
	
	/**
	 * 널인 문자열을 스페이스("")로 치환한다 <BR>
	 * 단, 좌우 공백이 있는 문자열은 trim 한다 <br>
	 * 
	 * @param s
	 *            입력문자열
	 * @return 치환된 문자열
	 */
	
	public static String null2str2(String s) {
		if (s == "null" || s.length() == 0){
			return "";
		}else{
			return s.trim();
		}
	}

	/**
	 * 널이거나 빈 문자열을 "&nbsp;"로 변환한다 <BR>
	 * 단, 좌우 공백이 있는 문자열은 trim 한다 <br>
	 * 
	 * @param s
	 *            입력문자열
	 * @return 치환된 문자열
	 */

	public static String null2nbsp(String org) {
		if (org == null){
			return "&nbsp;";
		}else{
			return org.trim();
		}
	}

	/**
	 * 널이거나 빈 문자열을 원하는 스트링으로 변환한다 <BR>
	 * 단, 좌우 공백이 있는 문자열은 trim 한다 <br>
	 * 
	 * @param s
	 *            입력문자열
	 * @return 치환된 문자열
	 */

	public static String null2str(String org, String converted) {
		if (org == null){
			return converted;
		}else{
			return org.trim();
		}
	}

	/**
	 * 문자열이 널이거나 빈 공백문자열인지 CHECK 한다 <br>
	 * 
	 * @param s
	 *            입력문자열
	 * @return 널이거나 공백일 경우 true, 아닐경우 false 를 리턴한다
	 */

	public static boolean isNull(String str) {
		if (str == null){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 문자열에서 주어진 separator 로 쪼개어 문자배열을 생성한다 <br>
	 * 
	 * @param str
	 *            원본문자열
	 * @param separator
	 *            원하는 token 문자열
	 * @return 스트링배열
	 */

	public static String[] split2(String str, String separator) {
		StringTokenizer st = new StringTokenizer(str, separator);
		String[] values = new String[st.countTokens()];
		int pos = 0;
		while (st.hasMoreTokens()) {
			values[pos++] = st.nextToken();
		}

		return values;
	}

	public static String[] getParser(String str, String sep) {
		int count = 0;
		int index = -1;
		int index2 = 0;

		if (str == null){
			return null;
		}

		do {
			++count;
			++index;
			index = str.indexOf(sep, index);
		} while (index != -1);
		// 마지막에 구분자가 있는지 check
		if (isNull(str.substring(index2))) {
			count--;
		}
		String[] substr = new String[count];
		index = 0;
		int endIndex = 0;
		for (int i = 0; i < (count); i++) {
			endIndex = str.indexOf(sep, index);
			if (endIndex == -1) {
				substr[i] = str.substring(index);
			} else {
				substr[i] = str.substring(index, endIndex);
			}
			index = endIndex + 1;
		}
		return substr;
	}

	/**
	 * 문자열 배열을 주어진 separator 로 연결하여 문자열을 생성한다 <br>
	 * 
	 * @param list
	 *            스트링배열
	 * @param separator
	 *            원하는 token 문자열
	 * @return 합쳐진 문자열을 리턴한다
	 */

	public static String join2(String list[], String separator) {
		StringBuffer csv = new StringBuffer();
		for (int i = 0; i < list.length; i++) {
			if (i > 0){
				csv.append(separator);
			}
			csv.append(list[i]);
		}
		return csv.toString();
	}
	
	/**
	 * 원본문자열에서 뒤로부터 주어진 문자열(ch)을 찾아 제거한다 <br>
	 * 
	 * @param str
	 *            원본문자열
	 * @param ch
	 *            제거할 문자열
	 * @return 제거된문자열을 리턴한다
	 * @exception 파싱에러시
	 *                발생
	 */

	public static String removeLastChar(String str, String ch) throws Exception {
		int pos = str.lastIndexOf(ch);
		if (pos == -1){
			return str;
		}else{
			return str.substring(0, pos) + str.substring(pos + 1);
		}
	}

	/**
	 * 문자열중 특정문자를 치환한다
	 * 
	 * @param str
	 *            대상문자열
	 * @param src
	 *            치환당할 문자
	 * @param tgt
	 *            치환할 문자
	 * @return 완성된 문자열
	 */
	public static String replace2(String str, String src, String tgt) {
		String buf = "";
		String ch = null;

		if (str == null || str.length() == 0){
			return "";
		}

		int len = src.length();
		for (int i = 0; i < str.length(); i++) {

			ch = str.substring(i, i + len);
			if (ch.equals(src)){
				buf = buf + tgt;
			}else{
				buf = buf + ch;
			}
		}
		return buf;
	}

	/**
	 * 문자열중 특정문자열을 치환한다
	 * 
	 * @param str
	 *            대상문자열
	 * @param src
	 *            치환당할 문자열
	 * @param tgt
	 *            치환할 문자열
	 * @return 완성된 문자열
	 */
	public static String replaceString(String str, String src, String tgt) {
		StringBuffer ret = new StringBuffer();

		if (str == null){
			return null;
		}
		if (str.equals("")){
			return "";
		}

		int start = 0;
		int found = str.indexOf(src);
		while (found >= 0) {
			if (found > start){
				ret.append(str.substring(start, found));
			}

			if (tgt != null){
				ret.append(tgt);
			}

			start = found + src.length();
			found = str.indexOf(src, start);
		}

		if (str.length() > start){
			ret.append(str.substring(start, str.length()));
		}

		return ret.toString();
	}
	/**
	 * 문자열중 특정문자열을 치환한다
	 * 
	 * @param str
	 *            대상문자열
	 * @return 완성된 문자열
	 */
	public static String replaceMoniteringTitle(String str) {
		StringBuffer ret = new StringBuffer();
		String title = "";

		if (str == null){
			return null;
		}
		if (str.equals("")){
			return "";
		}

		int start = 0;
		int x = 0; 
		int found = str.indexOf("▲");
		if(found < 0){
			found = str.indexOf("▼");
		}
		if(found < 0){
			ret.append(str.substring(start, str.length()));
			return ret.toString();
		}else{
			if (found > start){
				ret.append(str.substring(start, found));
			}
			start = found;
		}
		
		for(int i=start;i<str.length();i++) {
			x= str.charAt(i);
			if (x >= 48 && x <= 57){
				if (i > start){
					ret.append(str.substring(start, i));
				}
				int j=1;
				while(true){
					x= str.charAt(i+j);
					if (!(x >= 48 && x <= 57)){
						break;
					}
					j++;
				}
				String temp = str.substring(i, i+j);
				String temp2= NumberUtil.formedNumber(temp);

				ret.append(temp2);
				
				start = i + j;
				i = start;
			}
		}

		if (str.length() > start){
			ret.append(str.substring(start, str.length()));
		}
		
		title = StringUtil.replaceString(ret.toString(),"▲","<font color='red'>▲</font>");
		title = StringUtil.replaceString(title,"▼","<font color='blue'>▼</font>");

		return title;
	}

	/**
	 * 입력받은 String을 원하는 길이만큼 원하는 문자로 오른쪽을 채워주는 함수
	 * 
	 * @param calendar
	 *            입력받은 String
	 * @return 지정된 문자로 채워진 String
	 */
	public static String rpad(String str, int len, char pad) {
		String result = str;
		int templen = len - result.getBytes().length;

		for (int i = 0; i < templen; i++) {
			result = result + pad;
		}

		return result;
	}

	/**
	 * 입력받은 String을 원하는 길이만큼 원하는 문자로 왼쪽을 채워주는 함수
	 * 
	 * @param calendar
	 *            입력받은 String
	 * @return 지정된 문자로 채워진 String
	 */
	public static String lpad(String str, int len, char pad) {
		String result = str;
		int templen = len - result.getBytes().length;

		for (int i = 0; i < templen; i++)
			result = pad + result;

		return result;
	}

	/**
	 * 문자가 길경우에 특정 바이트 단위 길이로 자른다. (by ssoon 2005.03.28)
	 * 
	 * @param str
	 *            문자열
	 * @param byteSize
	 *            남길 문자열의 길이
	 * @return string 자르고 남은 문자열
	 * @throws Exception
	 */
	public static String getStrCut(String str, int byteSize) throws Exception {
		return getStrCut(str, byteSize, "...");
	}

	/**
	 * 문자가 길경우에 특정 바이트 단위 길이로 자른다. (by ssoon 2005.03.28)
	 * 
	 * @param str
	 *            문자열
	 * @param byteSize
	 *            남길 문자열의 길이
	 * @param str2
	 *            남길 문자열 뒤에 적어줄 문자열
	 * @return string 자르고 남은 문자열
	 * @throws Exception
	 */
	public static String getStrCut(String str, int byteSize, String str2) throws Exception {
		int rSize = 0;
		int len = 0;

		if (str.getBytes().length > byteSize) {
			for (; rSize < str.length(); rSize++) {
				if (str.charAt(rSize) > 0x007F){
					len += 2;
				}else{
					len++;
				}
				if (len > byteSize){
					break;
				}
			}
			str = str.substring(0, rSize) + str2;
		}
		return str;
	}

	/**
	 * formatMoney(by ssoon 2005.03.28)
	 * 
	 * @param str
	 * @return String
	 */
	public static String formatMoney(String str) {
		double iAmount = new Double(str).doubleValue();
		java.text.DecimalFormat df = new java.text.DecimalFormat("###,###,###,###,###,###,###");
		return df.format(iAmount);
	}

	/**
	 * 대상 str가 null이거나 ""인경우 경우 "&nbsp;"을 return(by ssoon 2005.03.28)
	 * 
	 * @param str
	 *            대상 스트링
	 */
	public static String strToNbsp(String str) throws Exception {
		if (str == null || null2space(str).equals("")){
			return "&nbsp;";
		}else{
			return str;
		}
	}

	/**
	 * html --> text 로 변환
	 * 
	 * @param strString
	 *            데이터베이스에 있는 데이터 문자열이다.
	 * @return 바뀌어진 값을 넘겨준다.
	 */
	public static String changeHtmlToText(String strString) {
		String strNew = "";

		StringBuffer strTxt = new StringBuffer("");
		char chrBuff;
		int len = 0;
		int i = 0;

		len = strString.length();

		for (i = 0; i < len; i++) {
			chrBuff = (char) strString.charAt(i);
			switch (chrBuff) {
			case '<':
				strTxt.append("&lt");
				break;
			case '>':
				strTxt.append("&gt");
				break;
			case 10:
				strTxt.append("<br>");
				break;
			case 13:
				// strTxt.append("<br>");
				break;
			case ' ':
				strTxt.append(" ");
				break;
			default:
				strTxt.append(chrBuff);
			}// switch
		}// for

		strNew = strTxt.toString();

		return strNew;
	}

	public static String toHtmlString(String str) {
		if (str == null){
			return "";
		}

		String tempStr = replace(str, "\n", "<br>");

		tempStr = StringUtil.replaceString(tempStr, "<a href", "<ahref");
		tempStr = StringUtil.replace(tempStr, " ", "&nbsp;");
		tempStr = StringUtil.replaceString(tempStr, "<ahref", "<a href");
		return tempStr;
	}

	public static String toSsnStr(String ssnStr) {
		if (ssnStr == null) {
			return "";
		} else if (ssnStr.length() != 13) {
			return ssnStr;
		} else {
			return ssnStr.substring(0, 6) + "-" + ssnStr.substring(6, 13);
		}
	}

	public static boolean isStringBdl(String tmpStr)
	{
	    try{
	        Double.parseDouble(tmpStr);
	        return true;
	    }
	    catch(NumberFormatException e)
	    {
	        return false;
	    }
	}
	
	/**
	 * 특수문자 제거
	 * 
	 * @param strString
	 * @return 바뀌어진 값을 넘겨준다.
	 */
	public static String removeSpecialChar(String str) {
		String value = null;
		value = removeChar(str, '/'); // '/' 제거
		value = removeChar(value, '-'); // '-' 제거
		value = removeChar(value, ':'); // ':' 제거
		value = removeChar(value, ','); // ',' 제거
		//value = removeChar(value, '.'); // '.' 제거
		value = removeChar(value, '%'); // '%' 제거
		value = removeChar(value, '{'); // '{' 제거
		value = removeChar(value, '}'); // '}' 제거
		value = removeChar(value, '['); // '[' 제거
		value = removeChar(value, ']'); // ']' 제거
		value = removeChar(value, '*'); // '*' 제거
		value = removeChar(value, '~'); // '~' 제거
		value = removeChar(value, '`'); // '`' 제거
		value = removeChar(value, '!'); // '!' 제거
		value = removeChar(value, '^'); // '^' 제거
		value = removeChar(value, '+'); // '+' 제거
		value = removeChar(value, '<'); // '<' 제거
		value = removeChar(value, '>'); // '>' 제거
		value = removeChar(value, '@'); // '@' 제거
		value = removeChar(value, '\\'); // '\' 제거
		value = removeChar(value, '#'); // '#' 제거
		value = removeChar(value, '$'); // '$' 제거
		value = removeChar(value, '&'); // '&' 제거
		value = removeChar(value, '\''); // ''' 제거
		value = removeChar(value, '"'); // '"' 제거
		value = removeChar(value, '('); // '(' 제거
		value = removeChar(value, ')'); // ')' 제거
		value = removeChar(value, '='); // '=' 제거
		value = value.trim(); // 문자열 앞뒤 공백제거

		return value;
	}
	
    /**
     * 배열to스트링 변환하기
     * 
     * 
     * Example) String[] strarr = {"A","B","C"} ==> String str = "A, B, C" 
     * @param form
     * @return String
     * @throws Exception
     */
	public static String atos(String[] str) throws Exception {
		String result = "";
	  	
		try {
			for(int i=0; i<str.length; i++) result += str[i] + ", ";
		} catch (Exception e) {
			System.err.println("StringUtil atos() Exception Occured");
		}

		return result;
	}
	
	
    /**
     * 업무코드 찾기 ( 패키지명에서 3번째 )
     * 
     * 
 
     * @param package name
     * @return String
     * @throws Exception
     */
	public static String getJobCode(String packageName) throws Exception {
		String result = "";
		StringTokenizer st = new StringTokenizer(packageName, ".");
		if ( st.hasMoreTokens()) {
			st.nextToken();
			st.nextToken();
			result = st.nextToken();
		}
		return result;
	}
	
	
	/**
     * null치환
     * 
     * @param Object 
     * @return String
     * @throws Exception
     */
	public static Object nulltospace(Object obj)
    {
        if(obj == null) return "";
        else return obj;
    }

	/**
     * 파일의 확장자를 체크하여 허용여부를 리턴한다.
     * 
     * @param 파일이름  
     * @return 확장자 성공여부 
     * @throws Exception
     */
	public static boolean checkFileExt(String filename)
    {
		if( filename == null) return true;
		String name = filename.toLowerCase();
		
		if( name.endsWith(".exe") ||
		    name.endsWith(".asp") ||
		    name.endsWith(".cmd") ||
		    name.endsWith(".class") ||
		    name.endsWith(".html") ||
		    name.endsWith(".js") ||
		    name.endsWith(".jsp") ||
		    name.endsWith(".php") ||
		    name.endsWith(".sh") ||
		    name.endsWith(".csh") ) {
			return true;
		} else {
			return false;
		}		
				
    }

	/**
     * 엑셀 파일의 확장자를 체크하여 허용여부를 리턴한다.
     *  
     * @param 엑셀 파일이름  
     * @return 확장자 성공여부 
     * @throws Exception
     */
	public static boolean checkExcelFileExt(String filename)
    {
		if( filename == null) return true;
		String name = filename.toLowerCase();
		
		if( name.endsWith(".xls") ||
		    name.endsWith(".xlsx")   ) {
			return false;
		} else {
			return true;
		}		
				
    }
	
	/**
     * 이미지 파일의 확장자를 체크하여 허용여부를 리턴한다.
     *  
     * @param 이미지 파일이름  
     * @return 확장자 성공여부 
     * @throws Exception
     */
	public static boolean checkImgFileExt(String filename)
    {
		if( filename == null) return true;
		String name = filename.toLowerCase();
		
		if( name.endsWith(".jpg") || 
			name.endsWith(".gif") || 
			name.endsWith(".png") || 
			name.endsWith(".bmp")   ) {
			return true;
		} else {
			return false;
		}		
				
    }
	
	/**
	 * XSS 방지 처리를 위해, C Tag Lib를 사용하지 못하는 필드의 경우, 서버쪽에서 Unscript 처리를 해주기 위한 메소드
	 *  
	 * @param inputString 입력문자열
	 * @return String Script 문제를 대체한 결과 문자열
	 * @throws Exception
	 */
	public static String unScript(String data) {
        if (data == null || data.trim().equals("")) {
            return "";
        }
        
        String ret = data;
        
        ret = ret.replaceAll("<(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;script");
        ret = ret.replaceAll("</(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;/script");
        
        ret = ret.replaceAll("<(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;object");
        ret = ret.replaceAll("</(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;/object");
        
        ret = ret.replaceAll("<(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;applet");
        ret = ret.replaceAll("</(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;/applet");
        
        ret = ret.replaceAll("<(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
        ret = ret.replaceAll("</(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
        
        ret = ret.replaceAll("<(F|f)(O|o)(R|r)(M|m)", "&lt;form");
        ret = ret.replaceAll("</(F|f)(O|o)(R|r)(M|m)", "&lt;form");
        
        // added
        ret = ret.replaceAll("(?i)<script.*?>.*?</script.*?>", "");
        ret = ret.replaceAll("(?i)<.*?JAVA-SCRIPT:.*?>.*?</.*?>", "");
        ret = ret.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");

        return ret;
    }	
	
}