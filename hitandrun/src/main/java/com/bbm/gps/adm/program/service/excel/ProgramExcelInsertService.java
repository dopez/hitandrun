package com.bbm.gps.adm.program.service.excel;

import egovframework.rte.fdl.cmmn.exception.BaseException;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.excel.impl.EgovExcelServiceDAO;
import egovframework.rte.fdl.idgnr.impl.EgovUUIdGnrService;
import egovframework.rte.fdl.string.EgovObjectUtil;
import com.bbm.common.excel.ExcelMapping;
import com.bbm.common.excel.impl.ExcelServiceImpl;
import com.bbm.gps.adm.program.service.ProgramManageVO;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.MessageSource;

import com.ibatis.sqlmap.client.SqlMapClient;


/** 
 * 엑셀파일에서 읽어온 셀단위 데이터를 처리하여 batchInsert에 list형태로 만들어서 보낸다
 * <p><b>NOTE:</b> 
 * @author 공표/이관 염우섭 
 * @since 2011.07.18 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *  2011.07.18      염우섭               최초 생성
 * 
 * </pre> 
 */


public class ProgramExcelInsertService extends ExcelServiceImpl{
	
    /** EgovIdGnrService */
    @Resource(name = "programIdGnrService")
    private EgovUUIdGnrService idgenService;
	
	/** MessageSource */
	@Resource(name ="messageSource")
	private MessageSource messageSource;
	

	/** Excel입력 전자정부 DAO 선언 */
	private EgovExcelServiceDAO dao;
	
	/** mapping 을 위한 클래스 */
	private String mapClass;
	
	/** ibatis 호출 */
	private SqlMapClient sqlMapClient;
	
	/** 로그 처리를 위한 선언 */
	protected Log log = LogFactory.getLog(this.getClass());
	
	
	
	 /** 
	 * SQL 처리를 하기위해 IBATIS 와 연동하기 위한 sqlMapClient 정의
	 * @param sqlMapClient  ibatis 호출 객체
	 * @throws Exception 
	 * @see 
	 */ 	
	public void setSqlMapClient(SqlMapClient sqlMapClient) throws Exception {
		this.sqlMapClient = sqlMapClient;
		dao = new EgovExcelServiceDAO(this.sqlMapClient);
		//dao = EgovExcelServiceDAO.getInstance();
	}
	


	/**
	 * Excel Cell과 VO를 mapping 구현 클래스
	 * @param mapClass
	 * @throws Exception
	 * @see 
	 */
	public void setMapClass(String mapClass) throws BaseException {
		this.mapClass = mapClass;
		log.debug("mapClass : " + mapClass);
		
	}
	
	
	/**
	 * ExcelServiceImpl 를 상속받아 확장함
	 * 엑셀 업로드시 Transaction 문제 및 Sheet 별 엑셀자료를 처리 하기 위하여
	 * 커스터 마이징 함
	 * @param queryId 에 호출쿼리 ID, tongid, josaid, talbeid를 받아서와 "/"구분자를 제외한 배열로 받아 처리함
	 * @param fileIn 화면에서 파일 대화창으로 선택된 File 의 InputStream 
	 * @param start 데이터 리드시 엑셀의 Start ROW 값 
	 * @param commitCnt 입력데이터의 커밋 단위
	 * @return Integer 엑셀 입력 카운트
	 * throws BaseException,Exception
	 */
public Integer uploadExcel(String queryId, InputStream fileIn, int start, long commitCnt, String address, String excelVersion ) throws BaseException,  Exception{
		
		Integer rowsAffected = 0;
		
		try {
			
			 
			if( "2007".equals(excelVersion))  {
				return uploadExcelXlsx( queryId, fileIn, start, commitCnt, address  );				
			}
			
			int insertrow = 0;
			String[] items = queryId.split("/");
			
			// Sheet 별 입력을 위한 Sheet의 입력이 아닌 엑셀의 Workbook 을 인자로 받는다.		      
			HSSFWorkbook wb = loadWorkbook(fileIn);
			// 해당 WorkBook 안의 Sheet 갯수 확인	  
			int sheetCnt = wb.getNumberOfSheets();
			for (int k=0; k< sheetCnt; ++k){
				// 각 Sheet 별 데이터를 추출한다. 
				HSSFSheet sheet = wb.getSheetAt(k);
				log.debug("Excel InsertService SheetName : "+wb.getSheetName(k));
				long rowCnt = sheet.getPhysicalNumberOfRows();
				long cnt = (commitCnt == 0)?rowCnt:commitCnt;
		
				log.debug("Runtime.getRuntime().totalMemory() : " + Runtime.getRuntime().totalMemory());
				log.debug("Runtime.getRuntime().freeMemory() : " + Runtime.getRuntime().freeMemory());
		
				long startTime = System.currentTimeMillis();
	
				for (int idx = start, i = start; idx < rowCnt; idx = i) {
					List<Object> list = new ArrayList<Object>();
		
					log.debug("before Runtime.getRuntime().freeMemory() : " + Runtime.getRuntime().freeMemory());
		        	ExcelMapping mapping = (ExcelMapping) EgovObjectUtil.instantiate(mapClass);
		
					for (i = idx; i < rowCnt && i < (cnt + idx); i++) {
						HSSFRow row = sheet.getRow(i);
				    	idgenService.setAddress(address);						
						String programId = idgenService.getNextStringId(); 
						ProgramManageVO programManageVO = (ProgramManageVO)mapping.mappingColumn(row);
			    		// 빈공백의 셀 값도 입력되는것을 방지하기 위한 부분
		    			programManageVO.setProgramId(programId);
		    			
			            list.add(programManageVO);

			    		
			        }	
			        rowsAffected += dao.batchInsert(items[0], list);
	
					log.debug("after Runtime.getRuntime().freeMemory() : " + Runtime.getRuntime().freeMemory());		
			        log.debug("\n\n\n" + rowsAffected);
	        
				}
				
				insertrow += rowCnt;
				
				log.debug("batchInsert time is " + (System.currentTimeMillis() - startTime));
			}
			
		
		} catch (NullPointerException ne) {
			throw new EgovBizException(messageSource, "fail.common.noexceldata", ne);
		} catch (Exception e) {
			log.error("Exception Occured");
		}
				
		log.debug("uploadExcel result count is " + rowsAffected);

        return rowsAffected;
	}

/**
 * ExcelServiceImpl 를 상속받아 확장함
 * 엑셀 업로드시 Transaction 문제 및 Sheet 별 엑셀자료를 처리 하기 위하여
 * 커스터 마이징 함
 * @param queryId 에 호출쿼리 ID, tongid, josaid, talbeid를 받아서와 "/"구분자를 제외한 배열로 받아 처리함
 * @param fileIn 화면에서 파일 대화창으로 선택된 File 의 InputStream 
 * @param start 데이터 리드시 엑셀의 Start ROW 값 
 * @param commitCnt 입력데이터의 커밋 단위
 * @return Integer 엑셀 입력 카운트
 * throws BaseException,Exception
 */
public Integer uploadExcelXlsx(String queryId, InputStream fileIn, int start, long commitCnt, String address ) throws BaseException,  Exception{
	
	Integer rowsAffected = 0;
	
	try {

		int insertrow = 0;
		String[] items = queryId.split("/");
		
		// Sheet 별 입력을 위한 Sheet의 입력이 아닌 엑셀의 Workbook 을 인자로 받는다.		      
		XSSFWorkbook wb = new XSSFWorkbook(fileIn);
		// 해당 WorkBook 안의 Sheet 갯수 확인	  
		int sheetCnt = wb.getNumberOfSheets();
		for (int k=0; k< sheetCnt; ++k){
			// 각 Sheet 별 데이터를 추출한다. 
			XSSFSheet sheet = wb.getSheetAt(k);
			log.debug("Excel InsertService SheetName : "+wb.getSheetName(k));
			long rowCnt = sheet.getPhysicalNumberOfRows();
			long cnt = (commitCnt == 0)?rowCnt:commitCnt;
	
			log.debug("Runtime.getRuntime().totalMemory() : " + Runtime.getRuntime().totalMemory());
			log.debug("Runtime.getRuntime().freeMemory() : " + Runtime.getRuntime().freeMemory());
	
			long startTime = System.currentTimeMillis();

			for (int idx = start, i = start; idx < rowCnt; idx = i) {
				List<Object> list = new ArrayList<Object>();
	
				log.debug("before Runtime.getRuntime().freeMemory() : " + Runtime.getRuntime().freeMemory());
	        	ExcelMapping mapping = (ExcelMapping) EgovObjectUtil.instantiate(mapClass);
	
				for (i = idx; i < rowCnt && i < (cnt + idx); i++) {
					XSSFRow row = sheet.getRow(i);
			    	idgenService.setAddress(address);						
					String programId = idgenService.getNextStringId(); 
					ProgramManageVO programManageVO = (ProgramManageVO)mapping.mappingColumn(row);
		    		// 빈공백의 셀 값도 입력되는것을 방지하기 위한 부분
	    			programManageVO.setProgramId(programId);
	    			
		            list.add(programManageVO);

		    		
		        }	
		        rowsAffected += dao.batchInsert(items[0], list);

				log.debug("after Runtime.getRuntime().freeMemory() : " + Runtime.getRuntime().freeMemory());		
		        log.debug("\n\n\n" + rowsAffected);
        
			}
			
			insertrow += rowCnt;
			
			log.debug("batchInsert time is " + (System.currentTimeMillis() - startTime));
		}
		
	
	} catch (NullPointerException ne) {
		throw new EgovBizException(messageSource, "fail.common.noexceldata", ne);
	} catch (Exception e) {
		log.error("Exception Occured");
	}
			
	log.debug("uploadExcel result count is " + rowsAffected);

    return rowsAffected;
}


}