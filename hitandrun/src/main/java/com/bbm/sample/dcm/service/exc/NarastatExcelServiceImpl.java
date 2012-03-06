package com.bbm.sample.dcm.service.exc;

import egovframework.rte.fdl.cmmn.exception.BaseException;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.excel.impl.EgovExcelServiceDAO;
import egovframework.rte.fdl.string.EgovObjectUtil;
import com.bbm.common.excel.ExcelMapping;
import com.bbm.common.excel.impl.ExcelServiceImpl;
import com.bbm.sample.dcm.service.DataColctExcVO;
import com.bbm.sample.dcm.service.impl.DataColctExcDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
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
 * 엑셀파일에서 읽어온 셀단위 데이터를 처리하여 batchInsert에 list 형태로 만들어서 보낸다.
 * <p>
 * <b>NOTE:</b>
 * 
 * @filename NarastatExcelServiceImpl.java
 * @author 조사표 설계/수집 개발팀 최종대
 * @since 2011.07.22
 * @version 1.0
 * @see <pre>
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *  2011.07.22      최종대             최초 생성
 * 
 * </pre>
 */

public class NarastatExcelServiceImpl extends ExcelServiceImpl {

	/** MessageSource */
	@Resource(name = "messageSource")
	private MessageSource messageSource;

	/** templateDAO 서비스 호출 */
	@Resource(name = "dataColctExcDAO")
	private DataColctExcDAO dataColctExcDAO;

	/** Excel 입력 전자정부 DAO 선언 */
	private EgovExcelServiceDAO dao;

	/** mapping 을 위한 클래스 */
	private String mapClass;

	/** ibatis 호출 */
	private SqlMapClient sqlMapClient;

	/** 로그 처리를 위한 선언 */
	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * SQL 처리를 하기위해 IBATIS 와 연동하기 위한 sqlMapClient 정의
	 * 
	 * @param sqlMapClient
	 *            ibatis 호출 객체
	 * @return
	 * @exception Exception
	 * @see
	 */
	public void setSqlMapClient(SqlMapClient sqlMapClient) throws Exception {
		this.sqlMapClient = sqlMapClient;
		dao = new EgovExcelServiceDAO(this.sqlMapClient);

	}

	/**
	 * Excel Cell 과 VO를 mapping 구현 클래스
	 * 
	 * @param mapClass
	 * @return
	 * @exception Exception
	 * @see
	 */
	public void setMapClass(String mapClass) throws BaseException {
		this.mapClass = mapClass;
		log.debug("mapClass : " + mapClass);
	}

	/**
	 * ExcelServiceImpl 를 상속받아 확장함 엑셀 업로드시 Transaction 문제 및 Sheet 별
	 * 엑셀자료를 처리 하기 위하여 커스터 마이징 함
	 * 
	 * @param queryId
	 *            호출 쿼리ID
	 * @param fileIn
	 *            화면에서 파일 대화창으로 선택된 File 의 InputStream
	 * @param start
	 *            데이터 리드시 엑셀의 Start ROW 값
	 * @param commitCnt
	 *            입력데이터의 커밋 단위
	 * @return Integer 엑셀 입력 카운트
	 * @exception BaseException
	 * @see
	 */


	public Integer uploadExcel(String queryId, InputStream fileIn, int start,
			long commitCnt, String tableName, String excelVersion) throws BaseException, Exception {
		Integer rowsAffected = 0;
		
		try {
			
			if("2007".equals( excelVersion )) {
				return uploadExcelXlsx( queryId, fileIn,  start,
						commitCnt, tableName);
			}

			String[] items = queryId.split("/");
			
			int insertrow = 0;
			String insertname = ""; // excel 파일 항목명
			String insertitem = ""; // excel 파일 항목 내용
			String insertid = ""; // excel 파일 항목 내용
			HashMap param = new HashMap();
			DataColctExcVO vo = new DataColctExcVO();
			
			if (tableName.startsWith("\'")) {
				tableName = tableName.substring(1, tableName.length());
			}
		
			if (tableName.endsWith("\'")) {
				tableName = tableName.substring(1, tableName.length());
			}
			param.put("TABLE_NAME", tableName);
			int recordId = dataColctExcDAO.selectExcelMaxCount(param);
		
			// Sheet 별 입력을 위한 Sheet 입력이 아닌 엑셀의 Workbook 을 인자로 받는다.
			HSSFWorkbook wb = loadWorkbook(fileIn);

			// 해당 WorkBook 안의 Sheet 갯수 확인
			int sheetCnt = wb.getNumberOfSheets();
			HashMap hMap = new HashMap();

			String cellName = "";
			
			for (int k = 0; k < sheetCnt; ++k) {
			
				insertname = "";

				// 각 Sheet 별 데이터를 추출한다.
				HSSFSheet sheet = wb.getSheetAt(k);
				log.debug("Excel InsertService SheetName : "
						+ wb.getSheetName(k));

				// 시트 안의 내용을 읽어 행값을 저장
				long rowCnt = sheet.getPhysicalNumberOfRows();
				
				long cnt = (commitCnt == 0) ? rowCnt : commitCnt;

				long startTime = System.currentTimeMillis();

				for(int idx = start, i = start; idx < rowCnt; idx = i) {
					List<Object> list = new ArrayList<Object>();
			
					log.debug("before Runtime.getRuntime().freeMemory() : "
							+ Runtime.getRuntime().freeMemory());
					// 업로드할 엑셀 로우의 값을 생성하는 부분 호출
					ExcelMapping mapping = (ExcelMapping) EgovObjectUtil
							.instantiate(mapClass);
					int count = 0;
							
					for (i = idx; i <= rowCnt && i <= (cnt + idx); i++) {
						insertitem = "";
						insertid = "";

						HSSFRow row = sheet.getRow(i);
						HSSFRow cellRow = sheet.getRow(3);
						HashMap hMapCell = new HashMap();
						// 게시판 ID 입력을 위한 IdgenSerivce 추후 ORACLE SEQUENCE 로 대체
						for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
						
							// col의 값을 현재 담겨진 값 만큼 돌면서 cellName 의 담는다
							cellName = cellRow.getCell(j).getStringCellValue();
							hMap = (HashMap) mapping.mappingColumn(row);

							// 각각의 cellName을 하나씩 가져와서 +',' 콤마로 구분 짖는다
							if (i == 4) {
								insertname += cellName + " ,";
							}
							insertitem += "'" + hMap.get(j) + "' ,";

							if (!hMap.isEmpty()) {
								hMapCell.put(cellName, hMap.get(j));
								hMapCell.put("tableName", tableName);
							} else {
								continue;
							}

						}
						if (i == 4) {
							insertname = insertname.substring(0, insertname
									.length() - 1);
						}

						insertid += "'" + items[1] + "',";
						insertid += "'" + items[2] + "',";
						insertid += "'" + items[7] + "',";
						insertid += "'" + ++recordId + "'";

						insertitem = insertitem.substring(0, insertitem
								.length() - 1);
								
						hMapCell.put("insertname", insertname);
						hMapCell.put("insertitem", insertitem);
						hMapCell.put("tableName", tableName);
						hMapCell.put("insertid", insertid);
						hMapCell.put("writer", "'"+items[5]+"'");

						list.add(hMapCell);
					}
				try{
					rowsAffected += dao.batchInsert(items[0], list);
				} catch (Exception e) {
					throw new EgovBizException(messageSource
							, "fail.common.exceldataerror"
							, new String[] {"BizException"}, null );	
				}
				}
				
				insertrow += rowCnt;
				log.debug("batchInsert time is "
						+ (System.currentTimeMillis() - startTime));
			}
			
			// 엑셀 파일 업로드가 끝나고 난 뒤 남은 이력을 저장하기 위해서 호출하는 부분
			
			vo.setDtaQy(insertrow - 3); // 자료량
			vo.setPrdctnId(items[1]); // 생산 ID
			vo.setSvyOdr(items[2]); // 생산회차
			vo.setFormID(items[4]); // 양식 ID
			vo.setColctStle(items[3]); // 수집형태
			vo.setWrterId(items[5]); // 수록담당자
			vo.setColctFileNm(items[6]);// 수록파일명
			dataColctExcDAO.insertexeclrow(vo);
			
		
		} catch (NullPointerException ne) {
			throw new EgovBizException(messageSource,
					"fail.common.noexceldata!!!!!!!!!!!", ne);
		}

		log.debug("uploadExcel result count is " + rowsAffected);

		return rowsAffected;
	}

	
	/**
	 * ExcelServiceImpl 를 상속받아 확장함 엑셀 업로드시 Transaction 문제 및 Sheet 별
	 * 엑셀자료를 처리 하기 위하여 커스터 마이징 함
	 * 
	 * @param queryId
	 *            호출 쿼리ID
	 * @param fileIn
	 *            화면에서 파일 대화창으로 선택된 File 의 InputStream
	 * @param start
	 *            데이터 리드시 엑셀의 Start ROW 값
	 * @param commitCnt
	 *            입력데이터의 커밋 단위
	 * @return Integer 엑셀 입력 카운트
	 * @exception BaseException
	 * @see
	 */


	public Integer uploadExcelXlsx(String queryId, InputStream fileIn, int start,
			long commitCnt, String tableName) throws BaseException, Exception {
		Integer rowsAffected = 0;
		
		try {
			
			String[] items = queryId.split("/");
			
			int insertrow = 0;
			String insertname = ""; // excel 파일 항목명
			String insertitem = ""; // excel 파일 항목 내용
			String insertid = ""; // excel 파일 항목 내용
			HashMap param = new HashMap();
			DataColctExcVO vo = new DataColctExcVO();
			
			if (tableName.startsWith("\'")) {
				tableName = tableName.substring(1, tableName.length());
			}
		
			if (tableName.endsWith("\'")) {
				tableName = tableName.substring(1, tableName.length());
			}
			param.put("TABLE_NAME", tableName);
			int recordId = dataColctExcDAO.selectExcelMaxCount(param);
		
			// Sheet 별 입력을 위한 Sheet 입력이 아닌 엑셀의 Workbook 을 인자로 받는다.
			XSSFWorkbook wb = new XSSFWorkbook(fileIn);

			// 해당 WorkBook 안의 Sheet 갯수 확인
			int sheetCnt = wb.getNumberOfSheets();
			HashMap hMap = new HashMap();

			String cellName = "";
			
			for (int k = 0; k < sheetCnt; ++k) {
			
				insertname = "";

				// 각 Sheet 별 데이터를 추출한다.
				XSSFSheet sheet = wb.getSheetAt(k);
				log.debug("Excel InsertService SheetName : "
						+ wb.getSheetName(k));

				// 시트 안의 내용을 읽어 행값을 저장
				long rowCnt = sheet.getPhysicalNumberOfRows();
				
				long cnt = (commitCnt == 0) ? rowCnt : commitCnt;

				long startTime = System.currentTimeMillis();

				for(int idx = start, i = start; idx < rowCnt; idx = i) {
					List<Object> list = new ArrayList<Object>();
			
					log.debug("before Runtime.getRuntime().freeMemory() : "
							+ Runtime.getRuntime().freeMemory());
					// 업로드할 엑셀 로우의 값을 생성하는 부분 호출
					ExcelMapping mapping = (ExcelMapping) EgovObjectUtil
							.instantiate(mapClass);
					int count = 0;
							
					for (i = idx; i <= rowCnt && i <= (cnt + idx); i++) {
						insertitem = "";
						insertid = "";

						XSSFRow row = sheet.getRow(i);
						XSSFRow cellRow = sheet.getRow(3);
						HashMap hMapCell = new HashMap();
						// 게시판 ID 입력을 위한 IdgenSerivce 추후 ORACLE SEQUENCE 로 대체
						for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
						
							// col의 값을 현재 담겨진 값 만큼 돌면서 cellName 의 담는다
							cellName = cellRow.getCell(j).getStringCellValue();
							hMap = (HashMap) mapping.mappingColumn(row);

							// 각각의 cellName을 하나씩 가져와서 +',' 콤마로 구분 짖는다
							if (i == 4) {
								insertname += cellName + " ,";
							}
							insertitem += "'" + hMap.get(j) + "' ,";

							if (!hMap.isEmpty()) {
								hMapCell.put(cellName, hMap.get(j));
								hMapCell.put("tableName", tableName);
							} else {
								continue;
							}

						}
						if (i == 4) {
							insertname = insertname.substring(0, insertname
									.length() - 1);
						}

						insertid += "'" + items[1] + "',";
						insertid += "'" + items[2] + "',";
						insertid += "'" + items[7] + "',";
						insertid += "'" + ++recordId + "'";

						insertitem = insertitem.substring(0, insertitem
								.length() - 1);
								
						hMapCell.put("insertname", insertname);
						hMapCell.put("insertitem", insertitem);
						hMapCell.put("tableName", tableName);
						hMapCell.put("insertid", insertid);
						hMapCell.put("writer", "'"+items[5]+"'");

						list.add(hMapCell);
					}
				try{
					rowsAffected += dao.batchInsert(items[0], list);
				} catch (Exception e) {
					throw new EgovBizException(messageSource
							, "fail.common.exceldataerror"
							, new String[] {"BizException"}, null );	
				}
				}
				
				insertrow += rowCnt;
				log.debug("batchInsert time is "
						+ (System.currentTimeMillis() - startTime));
			}
			
			// 엑셀 파일 업로드가 끝나고 난 뒤 남은 이력을 저장하기 위해서 호출하는 부분
			
			vo.setDtaQy(insertrow - 3); // 자료량
			vo.setPrdctnId(items[1]); // 생산 ID
			vo.setSvyOdr(items[2]); // 생산회차
			vo.setFormID(items[4]); // 양식 ID
			vo.setColctStle(items[3]); // 수집형태
			vo.setWrterId(items[5]); // 수록담당자
			vo.setColctFileNm(items[6]);// 수록파일명
			dataColctExcDAO.insertexeclrow(vo);
			
		
		} catch (NullPointerException ne) {
			throw new EgovBizException(messageSource,
					"fail.common.noexceldata!!!!!!!!!!!", ne);
		}

		log.debug("uploadExcel result count is " + rowsAffected);

		return rowsAffected;
	}


	/**
	 * 전자정부 제공 EgovExcelServiceImpl 를 상속받아 확장함 CSV 업로드시 Transaction 문제 및 Sheet 별
	 * CSV자료를 처리 하기 위하여 커스터 마이징 함
	 * 
	 * @param queryId
	 *            호출 쿼리ID
	 * @param fileIn
	 *            화면에서 파일 대화창으로 선택된 File 의 InputStream
	 * @param start
	 *            데이터 리드시CSV의 Start ROW 값
	 * @param commitCnt
	 *            입력데이터의 커밋 단위
	 * @return Integer CSV 입력 카운트
	 * @exception BaseException
	 * @see
	 */
	public Integer uploadExcelCsv(String queryId, InputStream fileIn,
			int start, long commitCnt, String tableName) throws BaseException,
			Exception {

		Integer rowsAffected = 0;
		int count = 0;
		int insertrow = 0;
		String insertname = "";
		String insertitem = "";
		String insertid = "";
		String[] items = queryId.split("/");
		String encoding = checkencoding(fileIn);
		HashMap param = new HashMap();
		DataColctExcVO vo = new DataColctExcVO();
		
		if (tableName.startsWith("\'")) {
			tableName = tableName.substring(1, tableName.length());
		}
	
		if (tableName.endsWith("\'")) {
			tableName = tableName.substring(1, tableName.length());
		}
		param.put("TABLE_NAME", tableName);
		int recordId = dataColctExcDAO.selectExcelMaxCount(param);
		
		HashMap hMap = new HashMap();
		try {

			HashMap hMapCell = new HashMap();
			InputStreamReader filein = new InputStreamReader(fileIn, encoding);
			BufferedReader reader = new BufferedReader(filein);
			String line = null;
			ExcelMapping mapping = (ExcelMapping) EgovObjectUtil
					.instantiate(mapClass);

			List<Object> list = new ArrayList<Object>();
			DataColctExcVO dataColctExcVO = new DataColctExcVO();
			while ((line = reader.readLine()) != null) {

				count++;
				// count 가 1일 경우 substring 을 사용하여 1번째 문자 부터 line 의 길이 까지 추출한다

				if (count == 3) {
					if (line.startsWith("\ufeff")) {
						line = line.substring(1, line.length());
					}
				}
				// line 에서 처음 시작하는 문자열이 " 일 경우 "를 제외한 문자열을 가져온다.
				if (line.startsWith("\"")) {
					line = line.substring(1, line.length());
				}
				// line 에서 마지막 문자열이 " 일 경우 "를 제외한 문자열을 가져온다.
				if (line.endsWith("\"")) {
					line = line.substring(0, line.length() - 1);
				}
				// CSV 첫 행의 파일을 읽는다
				if (count == 3) {
					insertname = line;
					// CSV 첫 행을 제외한 파일을 읽는다
				} else if (count >= 4) {
					insertitem = "'" + line.replace(",", "' , '") + "'";
					insertid = "'" + items[1] + "'," + "'" + items[2] + "',"+ "'"+ items[7] +"'," + "'" + ++recordId + "'";
					
			}
				if (count >= 4) {

					// 셀안에 tableName, insertname , insertitem 값을 입력한다.
					hMapCell.put("tableName", tableName);
					hMapCell.put("insertname", insertname);
					hMapCell.put("insertitem", insertitem);
					hMapCell.put("insertid", insertid);
					hMapCell.put("writer", "'"+items[5]+"'");

					list.add(hMapCell);
					try{
						rowsAffected += dao.batchInsert(items[0], list);
					} catch (Exception e) {
						throw new EgovBizException(messageSource
								, "fail.common.exceldataerror"
								, new String[] {"BizException"}, null );	
					}
					insertrow = count;
				}
				insertrow = count;
			}

			// CSV 업로드가 끝난 후 DataColctExcVO 를 이용하여 자료수집 이력관리에 insert 한다.
			
			vo.setDtaQy(insertrow - 3); // 자료량
			vo.setPrdctnId(items[1]); // 생산 ID
			vo.setSvyOdr(items[2]); // 생산회차
			vo.setFormID(items[4]); // 양식 ID
			vo.setColctStle(items[3]); // 수집형태
			vo.setWrterId(items[5]); // 수록담당자
			vo.setColctFileNm(items[6]);// 수록파일명
			dataColctExcDAO.insertexeclrow(vo);

		} catch (NullPointerException ne) {
			throw new EgovBizException(messageSource,
					"fail.common.noexceldata!!!!!!!!!!!", ne);
		}
		log.debug("uploadExcel result count is " + rowsAffected);

		return rowsAffected;
	}

	// CSV 파일의 inputStream 을 사용하여 인코딩 을 확인 후 리턴한다
	public  String checkencoding(InputStream stream) throws	Exception {
		String encoding = "";
		try {
			byte[] BOM = new byte[4];
			stream.read(BOM, 0, 4);

			if ((BOM[0] & 0xFF) == 0xEF && (BOM[1] & 0xFF) == 0xBB
					&& (BOM[2] & 0xFF) == 0xBF)
				encoding = "UTF-8";
			else if ((BOM[0] & 0xFF) == 0xFE && (BOM[1] & 0xFF) == 0xFF)
				encoding = "UTF-16BE";
			else if ((BOM[0] & 0xFF) == 0xFF && (BOM[1] & 0xFF) == 0xFE)
				encoding = "UTF-16LE";
			else if ((BOM[0] & 0xFF) == 0x00 && (BOM[1] & 0xFF) == 0x00
					&& (BOM[0] & 0xFF) == 0xFE && (BOM[1] & 0xFF) == 0xFF)
				encoding = "UTF-32BE";
			else if ((BOM[0] & 0xFF) == 0xFF && (BOM[1] & 0xFF) == 0xFE
					&& (BOM[0] & 0xFF) == 0x00 && (BOM[1] & 0xFF) == 0x00)
				encoding = "UTF-32LE";
			else
				encoding = "EUC-KR";

			stream.reset();
			stream.close();
		}catch (IOException e) {
			 log.error(this.getClass().getName() + " JAVA-SCRIPTExec() " + " IO Exception 발생");
		}
		return encoding;
	}

}