/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bbm.sample.dcm.service.impl;

import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import com.bbm.common.excel.ExcelService;
import com.bbm.sample.dcm.service.DataColctExcDefaultVO;
import com.bbm.sample.dcm.service.DataColctExcService;
import com.bbm.sample.dcm.service.DataColctExcVO;


/**  
 * 자료수집수행 ServiceImpl
 * @filename  DataColctExcServiceImpl.java
 * @author 조사표 설계/수집 개발팀 최종대
 * @since 2011.07.22
 * @version 1.0
 * @see
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 * @  date       author             note 
 * @ ---------   ---------   -------------------------------
 * @ 2011.07.22   최종대       최초생성
 * 							 1.Service 에 선언 되어있는 메소드들의 구현 클래스로 데이터 접근 클래스의 메소드를 호출한다.
 *							 메소드들 중에는 parameter 를 넘기는 메소드도 있고 넘기지 않는 메소드도 존재한다.
 * </pre> 
 */

@Service("dataColctExcService")
public class DataColctExcServiceImpl extends AbstractServiceImpl implements DataColctExcService {
	
	/** DataColctExcDAO 서비스 호출 */
    @Resource(name="dataColctExcDAO")
    private DataColctExcDAO dataColctExcDAO;
    
	/**  엑셀처리를 위한 Bean 에 등록된 서비스 호출 */ 
    @Resource(name = "dataExcelInsertService")
    private ExcelService dataExcelInsertService;

	/** 로그 처리를 위한 선언 */
	protected Log log = LogFactory.getLog(this.getClass());

    /**
	 * 자료수집수행 리스트 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return List 조회한 목록의 리스트
	 * @exception Exception
	 */
    public List selectDataColcExcList(DataColctExcDefaultVO searchVO) throws Exception {
        return dataColctExcDAO.selectDataColcExcList(searchVO);
    }

    /**
	 * 글 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */
    public int selectDataColctListTotCnt(DataColctExcDefaultVO searchVO) {
		return dataColctExcDAO.selectDataColctListTotCnt(searchVO);
	}
    
    
//    public String sayHi(String text) {
//    	 return "Hello " + text;
//    }
    
	/** 
     * dataColctExcVO에 담겨있는 항목을 DB에 insert 
     * @param dataColctExcVO  insert 할 항목에 대한 정보를 담고있는 VO 
     * @return 
     * @exception Exception 
     * @see 
     */
	public void insertTemplate(DataColctExcVO dataColctExcVO) throws Exception {
	
		dataColctExcDAO.insertTemplate(dataColctExcVO);    	
	}

	/** 
     * [자료수집이력관리] dataColctExcVO 자료수집 이력관리 조회에 필요한 정보 조회 
     * @param dataColctExcVO - 조회할 정보가 담긴 VO
     * @return ret 조회한 값을 ret 변수에 담아 정보를 return
     * @exception Exception 
     * @see 	
     */
	public DataColctExcVO selectDataColcExc(DataColctExcVO dataColctExcVO) throws Exception {
		DataColctExcVO ret = (DataColctExcVO)dataColctExcDAO.selectDataColcExc(dataColctExcVO);
    	return ret;
	}
	/** 
     * [자료수집이력관리] dataColctExcVO 자료수집 이력관리 정보 조회 
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return 자료소집 이력에 관한 정보
     * @exception Exception 
     * @see 	
     */
	public List	selectDataColcExcHistoryList (DataColctExcVO searchVO) throws Exception {
		  return dataColctExcDAO.selectDataColcExcHistoryList(searchVO);
	}
	
   /**
	 * 이력관리의 글 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */
    public int selectDataColctHistoryTotCnt(DataColctExcVO searchVO) {
		return dataColctExcDAO.selectDataColctHistoryTotCnt(searchVO);
    }
	/** 
     * [파일업로드] dataColctExcVO 파일업로드시 필요한 FORMID TABLEID 조회 
     * @param  dataColctExcVO - 조회할 정보가 담긴 VO
     * @return ret 조회한 값을 ret 변수에 담아 정보를 return
     * @exception Exception 
     * @see 	
     */
	public DataColctExcVO selectExcelUpload (DataColctExcVO dataColctExcVO) throws Exception {
		DataColctExcVO ret = (DataColctExcVO)dataColctExcDAO.selectExcelUpload (dataColctExcVO);
    	return ret;
	}
	
	/** 
	 * [수집자료업로드] xls 파일 업로드 메소드. 엑셀파일을 사용자로부터 받아 셀 안의 내용을 
	 * 리스트로 만든 후 DB에 insert
	 * @param file  사용자로부터 입력받는 Excel File
	 * @param dataColcExcNo, examinqy 로그 적재를 위한 구분자 
	 * @param tableid 엑셀파일 적재를 위한 테이블 구분자 
	 * @return 
	 * @exception Exception 
	 * @see stateConfmNo, svyOdr 이력조회를 위한 구분자 
	 * @see 
	 */
	public void insertExcelUpload(InputStream file ,DataColctExcVO vo, String excelVersion ) throws Exception {
		String prdctnId = vo.getPrdctnId(); 		//생산 ID
		String svyOdr = vo.getSvyOdr(); 	 		//생산회차
		String colctTableId = vo.getColctTableId(); //수집테이블 ID
		String colctStle = vo.getColctStle();   	//수집형태 
		String formid = vo.getFormID();			 	//양식 ID	
		String wrterId  = vo.getWrterId(); 	   		//수록담당자 
		String colctFileNm = vo.getColctFileNm();   //수집파일명  
		String designType = vo.getDesignType();     //DesignType
			
		String id = prdctnId +"/" +svyOdr+"/"+ colctStle +"/" + formid +"/" + wrterId + "/" + colctFileNm +"/" +designType ;
		
		dataExcelInsertService.uploadExcel("dataColctExcDAO.insertExcelUpload/"+id, file, 4, (long) 5000, colctTableId, excelVersion); 	
		
	}
	

	/** 
	 * [수집자료업로드] CSV 파일 업로드 메소드. CSV 파일을 사용자로부터 받아 셀 안의 내용을 
	 * 리스트로 만든 후 DB에 insert
	 * @param file  사용자로부터 입력받는 CSV File
	 * @param dataColcExcNo, examinqy 로그 적재를 위한 구분자 
	 * @param tableid 엑셀파일 적재를 위한 테이블 구분자 
	 * @return 
	 * @exception Exception 
	 * @see stateConfmNo, svyOdr 이력조회를 위한 구분자 
	 * @see 
	 */
	public void insertExcelUploadCsv(InputStream file ,DataColctExcVO vo) throws Exception {
		String prdctnId = vo.getPrdctnId(); 		//생산 ID
		String svyOdr = vo.getSvyOdr(); 	 		//생산회차
		String colctTableId = vo.getColctTableId(); //수집테이블 ID
		String colctStle = vo.getColctStle();   	//수집형태 
		String formid = vo.getFormID();			 	//양식 ID	
		String wrterId  = vo.getWrterId(); 	   		//수록담당자 
		String colctFileNm = vo.getColctFileNm();   //수집파일명  
		String designType = vo.getDesignType();     //DesignType
		

		String id = prdctnId +"/" +svyOdr+"/"+ colctStle +"/" + formid +"/" + wrterId + "/" + colctFileNm +"/" +designType ; 
		dataExcelInsertService.uploadExcelCsv("dataColctExcDAO.insertExcelUpload/"+id, file,0, (long) 5000, colctTableId); 	
	}
	
	/** 
     * [파일업로드] dataColctExcVO 파일업로드시 필요한 FORMID TABLEID 조회 
     * @param 
     * @return ret 조회한 값을 ret 변수에 담아 정보를 return
     * @exception Exception 
     * @see 	
     */
	public DataColctExcVO selectDownloadData (DataColctExcVO dataColctExcVO) throws Exception {
		DataColctExcVO ret = (DataColctExcVO)dataColctExcDAO.selectDownloadData (dataColctExcVO);
    	return ret;
	}

}
