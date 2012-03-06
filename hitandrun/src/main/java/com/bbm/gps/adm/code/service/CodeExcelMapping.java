package com.bbm.gps.adm.code.service;

import com.bbm.common.excel.ExcelMapping;
import com.bbm.common.util.ExcelUtil;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

/**
 * 코드관리 엑셀 Mapping
 * @author 포탈통계 이관형
 * @since 2011.06.21
 * @version 1.0
 * @see
 *
 * <pre>
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.06.21  이관형          최초 생성
 *
 * </pre>
 */
public class CodeExcelMapping extends ExcelMapping  {

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
		HSSFCell cell0 = row.getCell((int) 0);
    	HSSFCell cell1 = row.getCell((int) 1);	
    	HSSFCell cell2 = row.getCell((int) 2);		
    	HSSFCell cell3 = row.getCell((int) 3);		
    	HSSFCell cell4 = row.getCell((int) 4);		
    	HSSFCell cell5 = row.getCell((short) 5);		
    	HSSFCell cell6 = row.getCell((int) 6);		
    	HSSFCell cell7 = row.getCell((short) 7);		
    	HSSFCell cell8 = row.getCell((short) 8);		
    	HSSFCell cell9 = row.getCell((short) 9);		
    	HSSFCell cell10 = row.getCell((int) 10);		
    	HSSFCell cell11 = row.getCell((int) 11);		
    	HSSFCell cell12 = row.getCell((short) 12);		
    	HSSFCell cell13 = row.getCell((short) 13);		
    	HSSFCell cell14 = row.getCell((int) 14);		
    	HSSFCell cell15 = row.getCell((int) 15);			
    	HSSFCell cell16 = row.getCell((short) 16);			
    	HSSFCell cell18 = row.getCell((short) 18);			
    	HSSFCell cell21 = row.getCell((short) 21);		

    	CodeManageVO vo = new CodeManageVO();
		vo.setCodeId(ExcelUtil.getValue(cell0));
		vo.setCodeSe(ExcelUtil.getValue(cell1));
		vo.setUpperCodeId(ExcelUtil.getValue(cell2));
		vo.setCodeTyNo(ExcelUtil.getValue(cell3));
		vo.setCodeNm(ExcelUtil.getValue(cell4));
		vo.setCodeIndexNo(ExcelUtil.getValue(cell5));
		vo.setCodeEngNm(ExcelUtil.getValue(cell6));
		vo.setCodeAbbrNm(ExcelUtil.getValue(cell7));
		vo.setCodeAbbrEngNm(ExcelUtil.getValue(cell8));
		vo.setUpperCodeTyNo(ExcelUtil.getValue(cell9));
		vo.setUpperCodeIndexNo(ExcelUtil.getValue(cell10));
		vo.setValidBgnde(ExcelUtil.getValue(cell11));
		vo.setValidEndde(ExcelUtil.getValue(cell12));
		vo.setFncValAt(ExcelUtil.getValue(cell13));
		vo.setOrgId(ExcelUtil.getValue(cell14));
		vo.setSysId(ExcelUtil.getValue(cell15));
		vo.setUseCodeId(ExcelUtil.getValue(cell16));
		vo.setRegisterId(ExcelUtil.getValue(cell18));
		vo.setAddInfo(ExcelUtil.getValue(cell21));
		
		return vo;
	}
	
	/** 
	 * 2007 목록형엑셀파일 맵핑 
	 * @param HSSFRow  엑셀 ROW
	 * @return  VO
	 * @exception 
	 * @see ExcelMapping
 	 */    
	@SuppressWarnings("deprecation")
	@Override
	public Object mappingColumn(XSSFRow row){
		XSSFCell cell0 = row.getCell((int) 0);
    	XSSFCell cell1 = row.getCell((int) 1);	
    	XSSFCell cell2 = row.getCell((int) 2);		
    	XSSFCell cell3 = row.getCell((int) 3);		
    	XSSFCell cell4 = row.getCell((int) 4);		
    	XSSFCell cell5 = row.getCell((short) 5);		
    	XSSFCell cell6 = row.getCell((int) 6);		
    	XSSFCell cell7 = row.getCell((short) 7);		
    	XSSFCell cell8 = row.getCell((short) 8);		
    	XSSFCell cell9 = row.getCell((short) 9);		
    	XSSFCell cell10 = row.getCell((int) 10);		
    	XSSFCell cell11 = row.getCell((int) 11);		
    	XSSFCell cell12 = row.getCell((short) 12);		
    	XSSFCell cell13 = row.getCell((short) 13);		
    	XSSFCell cell14 = row.getCell((int) 14);		
    	XSSFCell cell15 = row.getCell((int) 15);			
    	XSSFCell cell16 = row.getCell((short) 16);			
    	XSSFCell cell18 = row.getCell((short) 18);			
    	XSSFCell cell21 = row.getCell((short) 21);		

    	CodeManageVO vo = new CodeManageVO();
		vo.setCodeId(ExcelUtil.getValue(cell0));
		vo.setCodeSe(ExcelUtil.getValue(cell1));
		vo.setUpperCodeId(ExcelUtil.getValue(cell2));
		vo.setCodeTyNo(ExcelUtil.getValue(cell3));
		vo.setCodeNm(ExcelUtil.getValue(cell4));
		vo.setCodeIndexNo(ExcelUtil.getValue(cell5));
		vo.setCodeEngNm(ExcelUtil.getValue(cell6));
		vo.setCodeAbbrNm(ExcelUtil.getValue(cell7));
		vo.setCodeAbbrEngNm(ExcelUtil.getValue(cell8));
		vo.setUpperCodeTyNo(ExcelUtil.getValue(cell9));
		vo.setUpperCodeIndexNo(ExcelUtil.getValue(cell10));
		vo.setValidBgnde(ExcelUtil.getValue(cell11));
		vo.setValidEndde(ExcelUtil.getValue(cell12));
		vo.setFncValAt(ExcelUtil.getValue(cell13));
		vo.setOrgId(ExcelUtil.getValue(cell14));
		vo.setSysId(ExcelUtil.getValue(cell15));
		vo.setUseCodeId(ExcelUtil.getValue(cell16));
		vo.setRegisterId(ExcelUtil.getValue(cell18));
		vo.setAddInfo(ExcelUtil.getValue(cell21));
		
		return vo;
	}
}
