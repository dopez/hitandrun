package com.bbm.sample.dcm.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapClient;
import egovframework.rte.psl.dataaccess.SpsAbstractDAO;
import com.bbm.sample.dcm.service.DtColcExaminVO;
import com.bbm.sample.ccm.cde.service.CmmnDetailCode;
/**
 * @Project Name : 행정자료 통합관리시스템 구축 프로젝트
 * @Class Name : BlAbstractDAO.java
 * @Description : @ @ 수정일 수정자 수정내용 @ --------- ---------
 *              ------------------------------- @ 2011. 6. 22. CoolLBK 최초생성
 * 
 * @author : CoolLBK
 * @since : 2011. 6. 22.
 * @version 1.0
 * 
 *          Copyright (C) by Ucore All right reserved.
 */

@Repository("DtColcExaminDAO")
public class DtColcExaminDAO extends SpsAbstractDAO {

    public List SelectGridList(String sqlMap, Map map) {
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
    
    public List SelectTableJosopyo(String sqlMap, Map map) {
    	// return list("sampleDAO.selectSample_S", map);
    	return list(sqlMap, map);
    }
    
    public List SelectSerchList(String sqlMap, Map map) {
    	// return list("sampleDAO.selectSample_S", map);
    	return list(sqlMap, map);
    }
    
    public List selecthistoryList(DtColcExaminVO searchVO)
	  throws Exception{
		return list("DtColcExaminDAO.selecthistoryList", searchVO);
		
	}
	
	public int selecthistoryTotCnt(DtColcExaminVO searchVO){
		return (Integer)getSqlMapClientTemplate().queryForObject("DtColcExaminDAO.selecthistoryTotCnt", searchVO);
	}
	
	
	 /**
     * 주어진 조건에 따른 공통코드를 불러온다.
     * 
     * @param vo
     * @return
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
    public Map selectCmmCodeMap(DtColcExaminVO vo) throws Exception {
    	Map codeMap  = new  LinkedHashMap();
    	ArrayList code = (ArrayList) list("DtColcExaminDAO.selectCmmCodeDetail", vo);
    	for(int tmpcnt = 0; tmpcnt < code.size(); tmpcnt++)
		{
    		DtColcExaminVO _comboVO = (DtColcExaminVO)code.get(tmpcnt);
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
    public List<DtColcExaminVO> selectCmmCodeDetail1(DtColcExaminVO vo) throws Exception {
	return list("DtColcExaminDAO.selectCmmCodeDetail1", vo);
    }

    /**
     * 주어진 조건에 따른 공통코드를 불러온다.
     * 
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<DtColcExaminVO> selectCmmCodeDetail2(DtColcExaminVO vo) throws Exception {
	return list("DtColcExaminDAO.selectCmmCodeDetail2", vo);
    }
}

