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
package com.bbm.sample.dcm.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

 
/**  
 * @Class Name : DataColctExcService.java
 * @Description : DataColctExcService 
 * @author 조사표 설계/수집 개발팀 최종대
 * @since 2011. 07.27
 * @version 1.0
 * @see
 * @Modification Information  
 * @
 * @  수정일        수정자                수정내용
 * @ ---------    ---------    -------------------------------
 * @ 2011.07.27    최종대       최초생성
 *
 */

public interface DataColctExcService {

    /**
	 * 글 목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
  
	List selectDataColcExcList(DataColctExcDefaultVO searchVO) throws Exception;

        
    /**
	 * 글 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */
    int selectDataColctListTotCnt(DataColctExcDefaultVO searchVO);
    

	
	/**[자료수집이력관리] 
     * dataColctExcVO 게시물에 대한 상세화면을 조회 
     * @param  parmID
     * @return DataColctExcVO 조회해온 상세항목에 대한 정보를 담고있는 VO 
     * @exception Exception 
     * @see 
     */ 
	DataColctExcVO selectDataColcExc(DataColctExcVO dataColctExcVO) throws Exception;
	

	List selectDataColcExcHistoryList(DataColctExcVO searchVO) throws Exception;
	
	/**[파일업로드에 필요한 양식 ID 테이블 ID 조회] 
     * dataColctExcVO 파일업로드에 필요한 양식 ID 테이블 ID 조회
     * @param  dataColctExcVO
     * @return DataColctExcVO 조회해온 상세항목에 대한 정보를 담고있는 dataColctExcVO 
     * @exception Exception 
     * @see 
     */ 
	DataColctExcVO selectExcelUpload (DataColctExcVO dataColctExcVO) throws Exception;
	
    /**[수집자료업로드] 
	 * dataColctExcVO xls 파일 업로드 메소드. 엑셀파일을 사용자로부터 받아 셀 안의 내용을 
	 * 리스트로 만든 후 DB에 insert
	 * @param file  사용자로부터 입력받는 EXCEL 파일 
	 * @return 
	 * @exception Exception 
	 * @see
	 */ 
    void insertExcelUpload(InputStream file ,DataColctExcVO vo, String excelVersion ) throws Exception;
    
    /**[수집자료업로드] 
	 * dataColctExcVO CSV 파일 업로드 메소드. 엑셀파일을 사용자로부터 받아 셀 안의 내용을 
	 * 리스트로 만든 후 DB에 insert
	 * @param file 사용자로부터 입력받는 CSV 파일 
	 * @return 
	 * @exception Exception 
	 * @see 
	 */ 
    void insertExcelUploadCsv(InputStream file ,DataColctExcVO vo ) throws Exception;
    

	/** 
     * dataColctExcVO VO에 담겨있는 항목을 DB에 insert 
     * @param dataColctExcVO  insert 할 항목에 대한 정보를 담고있는 VO 
     * @return 
     * @exception Exception 
     * @see 
     */ 
	void insertTemplate(DataColctExcVO dataColctExcVO) throws Exception;
	
	
	/**[파일다운로드에  필요한 정보 조회] 
     * dataColctExcVO 파일다운로드에 필요한 sheetName title 정보 조회
     * @param  dataColctExcVO
     * @return DataColctExcVO 조회해온 상세항목에 대한 정보를 담고있는 dataColctExcVO 
     * @exception Exception 
     * @see 
     */ 
	DataColctExcVO selectDownloadData (DataColctExcVO dataColctExcVO) throws Exception;
    
	
	/**
	 * 글 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 글 총 갯수
	 * @exception
	 */
    int selectDataColctHistoryTotCnt(DataColctExcVO searchVO);	
    

    
    
}

