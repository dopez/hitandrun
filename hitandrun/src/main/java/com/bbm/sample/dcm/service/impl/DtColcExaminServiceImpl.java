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
import com.bbm.sample.dcm.service.DtColcExaminService;
import com.bbm.sample.dcm.service.DtColcExaminVO;


/**  
 * @Project Name : 행정자료 통합관리시스템 구축 프로젝트
 * @Class Name : IblLoginServiceImpl.java
 * @Description : 
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2011. 6. 22.	 CoolLBK              최초생성
 * 
 * @author : CoolLBK
 * @since  : 2011. 6. 22.
 * @version 1.0
 * 
 *  Copyright (C) by Ucore All right reserved.
 */
@Service("DtColcExaminService")
public class DtColcExaminServiceImpl extends AbstractServiceImpl implements DtColcExaminService { 

    
    /** CommonGridDAO 서비스 호출 */ 
	@Resource(name="DtColcExaminDAO")
    private DtColcExaminDAO dtColcExaminDAO;

   /**
	 * 자료수집 현황 Grid Setting
	 * @param sql_id, Map map - 검색할 SQL ID 와 검색조건 의 값 
	 * @return  Grid 셋팅할 정보
	 * @exception
	 */
    public List SelectGridList(String sql_id, Map map) {
    	
    	return dtColcExaminDAO.SelectGridList(sql_id, map);
    }
   /**
	 * 자료수집 현황 Grid 로드
	 * @param sql_id, Map map - 검색할 SQL ID 와 검색조건 의 값 
	 * @return  Grid 보여줄 정보
	 * @exception
	 */
    public List SelectTable(String sql_id, Map map) {
    	
    	return dtColcExaminDAO.SelectTable(sql_id, map);
    }
   /**
	 * 자료수집 현황 Grid 정보의 총 개수
	 * @param sql_id, Map map - 검색할 SQL ID 와 검색조건 의 값 
	 * @return  Grid 보여줄 정보 총 개수
	 * @exception
	 */
    public List SelectTablecnt(String sql_id, Map map) {
    	
    	return dtColcExaminDAO.SelectTablecnt(sql_id, map);
    }
   /**
	 * 자료수집 현황 Grid 로드시 필요한 정보 검색
	 * @param sql_id, Map map - 검색할 SQL ID 와 검색조건 의 값 
	 * @return  Grid 로드시 필요한 정보 검색
	 * @exception
	 */
    public List SelectTableJosopyo(String sql_id, Map map) {
    	
    	return dtColcExaminDAO.SelectTableJosopyo(sql_id, map);
    }
   /**
	 * 
	 * @param  
	 * @return 
	 * @exception
	 */
    public List SelectSerchList(String sql_id, Map map) {
    	
    	return dtColcExaminDAO.SelectSerchList(sql_id, map);
    }
   /**
	 * 
	 * @param  
	 * @return 
	 * @exception
	 */
	public List selecthistoryList(DtColcExaminVO searchVO) throws Exception {
		return dtColcExaminDAO.selecthistoryList(searchVO);
	}
   /**
	 * 
	 * @param  
	 * @return 
	 * @exception
	 */
	public int selecthistoryTotCnt(DtColcExaminVO searchVO) {
		return dtColcExaminDAO.selecthistoryTotCnt(searchVO);
	}
   /**
	 * 검색조건에 입력된 값의 검색정보
	 * @param codeId, orderType
	 * @return  Grid 의 검색조건에 입력한 정보로 검색한 값
	 * @exception
	 */	
	 public Map selectCmmCodeMap(String codeId, String orderType) throws Exception {
		 DtColcExaminVO vo = new DtColcExaminVO();
    	vo.setUpperCodeId( codeId ); // 상위코드ID 
    	vo.setOrderType(orderType);  
    	return dtColcExaminDAO.selectCmmCodeMap(vo);
    }
	 
	 /**
     * 공통코드를 조회한다.
     * 
     * @param vo
     * @return
     * @throws Exception
     */
	 public List<DtColcExaminVO> selectCmmCodeDetail1(DtColcExaminVO vo) throws Exception {
		return dtColcExaminDAO.selectCmmCodeDetail1(vo);
	 }
	 
	 /**
     * 공통코드를 조회한다.
     * 
     * @param vo
     * @return
     * @throws Exception
     */
	 public List<DtColcExaminVO> selectCmmCodeDetail2(DtColcExaminVO vo) throws Exception {
		return dtColcExaminDAO.selectCmmCodeDetail2(vo);
	 }

}
