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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapClient;
import egovframework.rte.psl.dataaccess.SpsAbstractDAO;
import com.bbm.sample.dcm.service.ExmnrexaminationListShVO;

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
 
@Repository("ExmnrexaminationListShDAO")
public class ExmnrexaminationListShDAO extends SpsAbstractDAO {

    public List SelectGridList(String sqlMap, Map map) {
	// return list("sampleDAO.selectSample_S", map);
    	return list(sqlMap, map);
    }
    
    public List SelectSerchList(String sqlMap, Map map) {
    	// return list("sampleDAO.selectSample_S", map);
    	return list(sqlMap, map);
    }
    
    public List SelectTable(String sqlMap, Map map) {
    	// return list("sampleDAO.selectSample_S", map);
    	return list(sqlMap, map);
    } 
    
    public List SelectTablecnt(String sqlMap, Map map) {
    	// return list("sampleDAO.selectSample_S", map);
    	return list(sqlMap, map);
    }
   	
	/**
     * 주어진 조건에 따른 공통코드를 불러온다.
     * 
     * @param vo
     * @return
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
    public Map selectCmmCodeMap(ExmnrexaminationListShVO vo) throws Exception {
    	Map codeMap  = new  LinkedHashMap();
    	ArrayList code = (ArrayList) list("ExmnrexaminationListShDAO.selectCmmCodeDetail", vo);
    	for(int tmpcnt = 0; tmpcnt < code.size(); tmpcnt++)
		{
    		ExmnrexaminationListShVO _comboVO = (ExmnrexaminationListShVO)code.get(tmpcnt);
			codeMap.put(_comboVO.getUseCodeId(), _comboVO.getCodeNm());		
		}
    	
    	return  codeMap;
    }
	
	/**
     * 주어진 조건에 따른 공통코드를 불러온다.
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<ExmnrexaminationListShVO> selectCmmCodeDetail1(ExmnrexaminationListShVO vo) throws Exception {
    	return list("ExmnrexaminationListShDAO.selectCmmCodeDetail1", vo);
    }
   

 
}
