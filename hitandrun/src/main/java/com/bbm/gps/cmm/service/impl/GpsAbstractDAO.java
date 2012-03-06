package com.bbm.gps.cmm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ibatis.sqlmap.client.SqlMapClient;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

/**
 * 포털 sqlMapClient 지정 CLASS
 * <p><b>NOTE:</b>
 * @author 통계포털 황기연
 * @since 2011.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.06.01  황기연          최초 생성
 *
 * </pre>
 */

public class GpsAbstractDAO extends EgovAbstractDAO {
	
	@Resource(name = "sqlMapClient")
	public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
		super.setSuperSqlMapClient(sqlMapClient);
	}
	/*
	@Resource(name = "sqlMapClient")
	public void setSuperSqlMapClient(SqlMapClient sqlMapClient) {
		super.setSuperSqlMapClient(sqlMapClient);
	}
	/*
	
	/**
	 * 페이징처리 list
     * @param model
     * @return list 페이징처리된 목록
     */
	@SuppressWarnings("unchecked")
	@Override
	public List listWithPaging(String queryId, Object parameterObject,int firstIndex, int recordCountPerPage){
		return super.getSqlMapClientTemplate().queryForList(queryId, parameterObject, firstIndex, recordCountPerPage);
	}
}
