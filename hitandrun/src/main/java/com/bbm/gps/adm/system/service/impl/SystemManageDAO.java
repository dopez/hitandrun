package com.bbm.gps.adm.system.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.GpsAbstractDAO;
//import com.bbm.sps.web.SpsSessionVO;

/** 
 * 시스템정보 대한 데이터 접근 클래스를 정의한다
 * <p><b>NOTE:</b> 넘어온 요청에 대해 DB작업을 수행하는 메소드들의 집합
 * DB에 직접 접근하며 쿼리문에 적용할 parameter를 보내주거나 단순 쿼리 실행을 하도록 호출한다
 * select, update, delete 함수를 사용하며 쿼리아이디와 parameter를 넘긴다
 * @author 범정부통계포털 이관형 
 * @since 2011.06.17 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.05.15     이관형      최초 생성 
 *   2011.07.21     이진우      사용자유형을 권한유형으로 변경
 * 
 * </pre> 
 */
@Repository("systemManageDAO")
public class SystemManageDAO extends GpsAbstractDAO {

    /**
     * 시스템정보에 대하여 목록 조회(검색조건)  
     * @param userId
     * @return List
     * @throws Exception
     * @see SYS_ID,SYS_NM
     * @see TABLE NAME : TN_SYSTEM
     */
    @SuppressWarnings("unchecked")
	public List selectSysInfoList(String userId) throws Exception {
        return list("systemManageDAO.selectSysInfoList", userId);
    }
    
    /**
     * 시스템정보에 대하여 목록 조회(검색조건)  
     * @param spsSessionVO
     * @return String
     * @throws Exception
     * @see TABLE NAME : TN_SYSTEM
     */
    
    /*public String selectSysId(SpsSessionVO spsSessionVO) throws Exception {
    	return (String)getSqlMapClientTemplate().queryForObject("systemManageDAO.selectSysId", spsSessionVO);
    }*/
    
}
