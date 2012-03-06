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

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bbm.sample.dcm.service.DataColctExcDefaultVO;
import com.bbm.sample.dcm.service.DataColctExcVO;

import egovframework.rte.psl.dataaccess.SpsAbstractDAO;

/**  
 * 자료수집수행 DAO
 * @filename  DataColctExcDAO.java
 * @author 조사표 설계/수집 개발팀 최종대
 * @since 2011.07.22
 * @version 1.0
 * @see
 * 
 *  * <pre> 
 *  == Modification Information == 
 *  
 *    date          author                note 
 *  -----------    -------    --------------------------- 
 *  2011.07.18      최종대      최초 생성 
 *            				 1. 자료수집수행 리스트에 대한 데이터 접근 클래스를 정의한다. 
 *                            DB에 접근하여 쿼리문에 적용되는 parameter 값을 전달하거나 단순 쿼리실행 하도록 호출한다.          
 * </pre> 
 */
 
@Repository("dataColctExcDAO")
public class DataColctExcDAO extends SpsAbstractDAO {   
	
	/**
	 * 자료수집수행 리스트를 조회한다.
	 * @param searchKeyword  조회조건 검색어
	 * @return List 조회한 목록의 리스트
	 * @exception Exception
	 * @see 
	 */
    public List selectDataColcExcList(DataColctExcDefaultVO searchVO) throws Exception {
        return list("dataColctExcDAO.selectTemplateList", searchVO);
    }

    /**
	 * 자료수집수행 리스트 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return int 조회한 목록의 리스트
	 * @exception
	 * @see COUNT(*) totcnt 
	 */
    public int selectDataColctListTotCnt(DataColctExcDefaultVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("dataColctExcDAO.selectTemplateListTotCnt", searchVO);
    } 
	/** 
     * dataColctExcVO VO에 담겨있는 항목을 DB에 insert 
     * @param dataColctExcVO  insert 할 항목에 대한 정보를 담고있는 VO 
     * @return 
     * @exception Exception 
     * @see 
     * @see 
     */
	public void insertTemplate(DataColctExcVO dataColctExcVO) throws Exception {
        insert("dataColctExcDAO.insertTemplate", dataColctExcVO);
	}

	/** 
	 * dataColctExcVO 자료수집수행 리스트 목록 조회  
	 * @param searchKeyword  조회조건 검색어 
	 * @param fromDate  조회할 대상의 등록일 기간에 대한 시작일 구분자 
	 * @param toDate  조회할 대상의 등록일 기간에 대한 종료일 구분자 
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
     * @see 
     * @see 
	 */ 
	public DataColctExcVO selectDataColcExc(DataColctExcVO dataColctExcVO) throws Exception {
		return (DataColctExcVO)selectByPk("dataColctExcDAO.selectTemplate", dataColctExcVO);		
	}
	
	/** 
	 * dataColctExcVO 파일업로드에 필요한 데이타 조회
	 * @param dataColctExcVO FORMID , TABLEID 값에대한 정보
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
     * @see 
     * @see 
	 */ 
	public DataColctExcVO selectExcelUpload(DataColctExcVO dataColctExcVO) throws Exception {
		return (DataColctExcVO)selectByPk("dataColctExcDAO.selectExcelUpload", dataColctExcVO);		
	}
	
	/** 
     * DataColctExcVO searchVO에 담겨있는 항목을 이력관리 항목으로 리턴한다.  
     * @param searchVO  insert 된 항목에 대한 정보를 담고있는 VO 
     * @return 변경된 list 항목
     * @exception Exception 
     * @see 
     * @see 
     */
	public List selectDataColcExcHistoryList (DataColctExcVO searchVO) throws Exception {
		  return list("dataColctExcDAO.selectHistoryManage", searchVO);
	}

	/** 
	 * vo 엑셀파일 DB업로드에 대한 이력저장(엑셀업로드 이력저장) 
	 * @param vo 추가 리스트에 대한 정보를 담고있는 VO 
	 * @return  
	 * @exception Exception 
	 * @see 
 	 */
	public void insertexeclrow(DataColctExcVO vo) throws Exception {
        insert("dataColctExcDAO.insertexeclrow", vo);  
	}
	
	/** 
	 * dataColctExcVO 파일다운로드에 필요한 데이타 조회
	 * @param dataColctExcVO colctNm , colctOrign 값에대한 정보
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
     * @see 
     * @see 
	 */ 
	public DataColctExcVO selectDownloadData(DataColctExcVO dataColctExcVO) throws Exception {
		return (DataColctExcVO)selectByPk("dataColctExcDAO.selectDownloadData", dataColctExcVO);		
	}
	
	
   /**
	 * 자료수집수행 리스트 목록 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return int 조회한 목록의 리스트
	 * @exception
	 * @see COUNT(*) totcnt 
	 */
    public int selectDataColctHistoryTotCnt(DataColctExcVO searchVO) {
        return (Integer)getSqlMapClientTemplate().queryForObject("dataColctExcDAO.selectDataColctHistoryTotCnt", searchVO);
    }



    
    public int selectExcelMaxCount(Map map) {
    	 
    	   return (Integer)getSqlMapClientTemplate().queryForObject("dataColctExcDAO.selectExcelMaxCount", map);
    }
	
}
