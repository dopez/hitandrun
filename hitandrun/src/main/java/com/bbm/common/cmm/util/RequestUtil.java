package com.bbm.common.cmm.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 
 * <pre>
 * HttpServlet Request 를 Map으로 변경
 * 
 * since 2011/06/13 
 * SW Lee
 * 
 */
public class RequestUtil {
	
	/**
	 * <pre>
	 * HttpServletRequest를 HashMap으로 변경
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String,Object> requestParamMap(HttpServletRequest request) throws Exception {

		  HashMap map = new HashMap();
		  
		  Enumeration Enumeration = request.getParameterNames();
		  while (Enumeration.hasMoreElements()) {
			  String key = (String) Enumeration.nextElement();
		      String[] value = request.getParameterValues(key);
		      
		      
		      if (value.length == 1) {
		          map.put(key, request.getParameter(key) == null ? "" : request.getParameter(key));
		      } else if (value.length > 1) {
		    	  String valStr = "";
		          Vector vector = new Vector();
		          for (int i = 0; i < value.length; i++) {
		        	  vector.add(vector.size(), value[i] == null ? "" : value[i]);
		        	  valStr += value[i]+" , ";
		          }
		          map.put(key, vector);	          
		      }
		  }
		  
		  return map;
		}
	
	/**
	 * <pre>
	 * HttpServletRequest를 EgovMap 변경
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static EgovMap RequestParamEgovMap(HttpServletRequest request) throws Exception {

		EgovMap map = new EgovMap();
		  
		  Enumeration<Object> Enumeration = request.getParameterNames();
		  
		  while (Enumeration.hasMoreElements()) {
			  String key = (String) Enumeration.nextElement();
		      String[] value = request.getParameterValues(key);
		      if (value.length == 1) {
		          map.put(key, request.getParameter(key) ==null ? "" : request.getParameter(key)   );
		          
		      } else if (value.length > 1) {
		          Vector vector = new Vector();
		          for (int i = 0; i < value.length; i++) {
		        	  vector.add(vector.size(), value[i] == null ? "" : value[i] );
		          }
		          map.put(key, vector);	          
		      }
		  }
		  return map;
		}	
	@SuppressWarnings("unchecked")
	public static ArrayList getRequestValuesToArray(Object param) {
		
		ArrayList alResult = new ArrayList();
		String strValue = "";
		
		if(param == null) {
			return null;
		}
		
		strValue = param.toString().replace("[", "");
		strValue = strValue.replace("]", "");
		
		if(strValue.indexOf(",") > -1)
		{
			/*			
			StringTokenizer st = new StringTokenizer(strValue, ", ");
			while ( st.hasMoreTokens()) {
				if("".equals(st.nextToken()))
				{
					alResult.add("");
				}
				else
				{
					alResult.add(st.nextToken().trim());
				}				
			}			
		*/	
			String[] arrValue = strValue.split(",");
			
			for(int i=0; i < arrValue.length; i++)
			{
				if("".equals(arrValue[i]))
				{
					alResult.add("");
				}
				else
				{
					alResult.add(arrValue[i].replaceAll("@##@", ",").trim());
				}
			}
		
			
		}
		else
		{
			alResult.add(strValue);
		}
		
		if(right(strValue.trim(), 1).equals(","))
		{
			alResult.add("");
		}
		
		return alResult;
	}	
	
	@SuppressWarnings("unchecked")
	public static String right(String strValue, int intRightLength) {
		int length = strValue.length();
		
		if(length > intRightLength)
		{
			strValue = strValue.substring((length - intRightLength));
		}
			
		return strValue;
	}
	
	/**
	 * 프로그램 URL를 리턴한다. 
	 * @param HttpServletRequest 
	 * @return url 프로그램URL
	 * @exception Exception
	 * @see 
	 */	
	public static String selectProgramInfo( HttpServletRequest request ) throws Exception {
		
		String programControl = request.getRequestURI().substring(request.getContextPath().length()+1, request.getRequestURI().length());
		programControl = StringUtil.cutString(programControl, programControl.indexOf("?"));
		programControl = StringUtil.cutString(programControl, programControl.indexOf("."));

		programControl  = "/" + StringUtil.null2str(programControl);
		
		return programControl;
	}
}

