package com.bbm.sample.dcm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import com.bbm.sample.dcm.service.DatactChangeService;
import com.bbm.sample.dcm.service.DatactChangeVO;


/**  
 * @filename DatactChangeServiceImpl.java
 * @author 조사표 설계/수집 개발팀 최종대
 * @since 2011.08.26
 * @version 1.0
 * @see
 * 
 *  * <pre> 
 *  == Modification Information == 
 *  
 *    date          author                note 
 *  -----------    -------    --------------------------- 
 *  2011.08.26      최종대      최초 생성 
 *        
 */
@Service("DatactChangeService")
public class DatactChangeServiceImpl extends AbstractServiceImpl implements DatactChangeService { 

    
    /** CommonGridDAO 서비스 호출 */ 
	@Resource(name="DatactChangeDAO")
    private DatactChangeDAO datactChangeDAO;

	/**
	 * 자료수집 미리보기의 Grid Setting
	 * @param sql_id, Map - 쿼리ID 와 검색할 조건
	 * @return Grid를 세팅 해줄 정보
	 * @exception Exception
	 */
    public List SelectGridList(String sql_id, Map map) {
    	
    	return datactChangeDAO.SelectGridList(sql_id, map);
    }
    /**
	 * 자료수집 미리보기 정보 로드
	 * @param sql_id, Map - 쿼리ID 와 검색할 조건
	 * @return Grid에 보여줄 정보
	 * @exception
	 */
    public List SelectTable(String sql_id, Map map) {
    	
    	return datactChangeDAO.SelectTable(sql_id, map);
    }
    /**
	 * 자료수집 미리보기 총 글 개수
	 * @param sql_id, Map - 쿼리ID 와 검색할 조건
	 * @return Grid에 보여줄 정보의 총 개수
	 * @exception
	 */
    public List SelectTablecnt(String sql_id, Map map) {
    	
    	return datactChangeDAO.SelectTablecnt(sql_id, map);
    }
    

}
