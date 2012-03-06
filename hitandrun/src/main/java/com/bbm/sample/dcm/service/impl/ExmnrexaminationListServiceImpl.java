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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import com.bbm.sample.dcm.service.ExmnrexaminationListService;
import com.bbm.sample.dcm.service.ExmnrexaminationListVO;

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

@Service("ExmnrexaminationListService")
public class ExmnrexaminationListServiceImpl extends AbstractServiceImpl implements ExmnrexaminationListService { 

    
    /** CommonGridDAO 서비스 호출 */ 
	@Resource(name="ExmnrexaminationListDAO")
    private ExmnrexaminationListDAO exmnrexaminationListDAO;

	/**
	 * 조사원 조사입력 Grid Setting
	 * @param sql_id,map - 검색할 SQL ID 와 검색조건 의 값 
	 * @return  Grid 셋팅할 정보
	 * @exception 
	 */
    public List SelectGridList(String sql_id, Map map) {
    	
    	return exmnrexaminationListDAO.SelectGridList(sql_id, map);
    }
	/**
	 * 조사원 조사입력 Grid Setting
	 * @param sql_id,map - 검색할 SQL ID 와 검색조건 의 값 
	 * @return  Grid 셋팅할 정보
	 * @exception 
	 */
    public List SelectSerchList(String sql_id, Map map) {
    	
    	return exmnrexaminationListDAO.SelectSerchList(sql_id, map);
    }
	/**
	 * 조사원 조사입력 Grid 로드
	 * @param sql_id,map - 검색할 SQL ID 와 검색조건 의 값 
	 * @return  Grid 보여줄 정보
	 * @exception Exception
	 */
    public List SelectTable(String sql_id, Map map) {
    	
    	return exmnrexaminationListDAO.SelectTable(sql_id, map);
    }
	/**
	 * 조사원 조사입력 Grid 로그시 정보 총 개수
	 * @param sql_id,map - 검색할 SQL ID 와 검색조건 의 값 
	 * @return  Grid 보여줄 정보 총 개수
	 * @exception 
	 */
    public List SelectTablecnt(String sql_id, Map map) {
    	
    	return exmnrexaminationListDAO.SelectTablecnt(sql_id, map);
    }
   /**
	 * 검색조건에 입력된 값의 검색정보
	 * @param codeId, orderType
	 * @return  Grid 의 검색조건에 입력한 정보로 검색한 값
	 * @exception
	 */
	public Map selectCmmCodeMap(ExmnrexaminationListVO vo) throws Exception {

		return exmnrexaminationListDAO.selectCmmCodeMap(vo);
    }
	 
	 /**
     * 공통코드를 조회한다.
     * 
     * @param vo
     * @return
     * @throws Exception
     */
	 public List<ExmnrexaminationListVO> selectCmmCodeDetail1(ExmnrexaminationListVO vo) throws Exception {
		return exmnrexaminationListDAO.selectCmmCodeDetail1(vo);
	 }


}
