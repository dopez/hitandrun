package com.bbm.sample.dcm.service;

import com.bbm.common.excel.ExcelMapping;
import com.bbm.common.util.ExcelUtil;

import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

/**
 * 자료수집수행  Mapping class
 * @filename  DataColctExcelMapping.java
 * @author 조사표 설계/수집 개발팀 최종대
 * @since 2011.07.22
 * @version 1.0
 * @see
 *
 * <pre>
 *  == Modification Information == 
 *   
 *    date          author                note 
 *  -------        --------    ---------------------------
 *   2011.07.22     최종대          최초 생성
 *
 * </pre>
 */
public class DataColctExcelMapping extends ExcelMapping  {
 
	/** 
	 * 목록형엑셀파일 맵핑 
	 * @param HSSFRow  엑셀 ROW
	 * @return  VO
	 * @exception 
	 * @see ExcelMapping
 	 */    
	@SuppressWarnings("deprecation")
	@Override
	public Object mappingColumn(HSSFRow row){
		long cellCnt = row.getPhysicalNumberOfCells();
		HashMap hMap = new HashMap();
		
		for (int i=0; i <cellCnt; i++){				
			HSSFCell cell = row.getCell((short) i);
			 hMap.put(i,  ExcelUtil.getValue(cell));
		}					
		return hMap;
	}
	
	/** 
	 * Office 2007 목록형엑셀파일 맵핑 
	 * @param HSSFRow  엑셀 ROW
	 * @return  VO
	 * @exception 
	 * @see ExcelMapping
 	 */    
	@SuppressWarnings("deprecation")
	@Override
	public Object mappingColumn(XSSFRow row){
		long cellCnt = row.getPhysicalNumberOfCells();
		HashMap hMap = new HashMap();
		
		for (int i=0; i <cellCnt; i++){				
			 XSSFCell cell = row.getCell((short) i);
			 hMap.put(i,  ExcelUtil.getValue(cell));
		}					
		return hMap;
	}

}
