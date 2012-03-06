package com.bbm.sample.dcm.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapClient;
import egovframework.rte.psl.dataaccess.SpsAbstractDAO;
import com.bbm.sample.dcm.service.DatactChangeVO;

/**
 * @filename DatactChangeDAO.java
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

@Repository("DatactChangeDAO")
public class DatactChangeDAO extends SpsAbstractDAO {

    public List SelectGridList(String sqlMap, Map map) {

    	return list(sqlMap, map);
    }
    
    public List SelectTable(String sqlMap, Map map) {

    	return list(sqlMap, map);
    } 
    
    public List SelectTablecnt(String sqlMap, Map map) {
 
    	return list(sqlMap, map);
    }
    
 
 
}
