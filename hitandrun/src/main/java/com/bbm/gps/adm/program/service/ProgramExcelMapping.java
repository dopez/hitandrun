package com.bbm.gps.adm.program.service;

import com.bbm.common.excel.ExcelMapping;
import com.bbm.common.util.ExcelUtil;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

/**
 * 프로그램관리 엑셀 Mapping
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
public class ProgramExcelMapping extends ExcelMapping  {
 
	/** 
	 * 목록형엑셀파일 맵핑 
	 * @param HSSFRow  엑셀 ROW
	 * @return  VO
	 * @exception 
	 * @see ExcelMapping
 	 */    
	@Override
	public Object mappingColumn(HSSFRow row){
		HSSFCell cell0 = row.getCell((int) 0);//시스템ID		
		HSSFCell cell1 = row.getCell((int) 1);//프로그램명
		HSSFCell cell2 = row.getCell((int) 2);//경로
    	HSSFCell cell3 = row.getCell((int) 3);//프로그램한글명
    	HSSFCell cell4 = row.getCell((int) 4);//url
    	HSSFCell cell5 = row.getCell((int) 5);//설명
    	HSSFCell cell6 = row.getCell((int) 6);//읽기
    	HSSFCell cell7 = row.getCell((int) 7);//등록
    	HSSFCell cell8 = row.getCell((int) 8);//수정
    	HSSFCell cell9 = row.getCell((int) 9);//삭제 	
    	
		ProgramManageVO vo = new ProgramManageVO();
		vo.setProgramFileNm(ExcelUtil.getValue(cell0));
		vo.setProgramKoreannm(ExcelUtil.getValue(cell1));
		vo.setUrl(ExcelUtil.getValue(cell2));
		vo.setProgramDc(ExcelUtil.getValue(cell3));
		vo.setProgramTyRead(ExcelUtil.getValue(cell4));
		vo.setProgramTyCreate(ExcelUtil.getValue(cell5));
		vo.setProgramTyUpdate(ExcelUtil.getValue(cell6));
		vo.setProgramTyDelete(ExcelUtil.getValue(cell7));
		vo.setProgramStrePath(ExcelUtil.getValue(cell8));
		vo.setSysId(ExcelUtil.getValue(cell9));
		return vo;
	}
	
	/** 
	 * Office 2007용 목록형엑셀파일 맵핑 
	 * @param HSSFRow  엑셀 ROW
	 * @return  VO
	 * @exception 
	 * @see ExcelMapping
 	 */    
	@Override
	public Object mappingColumn(XSSFRow row){
		XSSFCell cell0 = row.getCell((int) 0);//시스템ID		
		XSSFCell cell1 = row.getCell((int) 1);//프로그램명
		XSSFCell cell2 = row.getCell((int) 2);//경로
    	XSSFCell cell3 = row.getCell((int) 3);//프로그램한글명
    	XSSFCell cell4 = row.getCell((int) 4);//url
    	XSSFCell cell5 = row.getCell((int) 5);//설명
    	XSSFCell cell6 = row.getCell((int) 6);//읽기
    	XSSFCell cell7 = row.getCell((int) 7);//등록
    	XSSFCell cell8 = row.getCell((int) 8);//수정
    	XSSFCell cell9 = row.getCell((int) 9);//삭제 	
    	
		ProgramManageVO vo = new ProgramManageVO();
		vo.setProgramFileNm(ExcelUtil.getValue(cell0));
		vo.setProgramKoreannm(ExcelUtil.getValue(cell1));
		vo.setUrl(ExcelUtil.getValue(cell2));
		vo.setProgramDc(ExcelUtil.getValue(cell3));
		vo.setProgramTyRead(ExcelUtil.getValue(cell4));
		vo.setProgramTyCreate(ExcelUtil.getValue(cell5));
		vo.setProgramTyUpdate(ExcelUtil.getValue(cell6));
		vo.setProgramTyDelete(ExcelUtil.getValue(cell7));
		vo.setProgramStrePath(ExcelUtil.getValue(cell8));
		vo.setSysId(ExcelUtil.getValue(cell9));
		return vo;
	}
	
}
