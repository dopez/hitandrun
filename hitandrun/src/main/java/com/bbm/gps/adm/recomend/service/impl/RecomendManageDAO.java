package com.bbm.gps.adm.recomend.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.GpsAbstractDAO;
import com.bbm.gps.adm.recomend.service.RecomendManageVO;

/** 
 * 추천사이트관리에 대한 데이터 접근 클래스를 정의한다
 * <p><b>NOTE:</b> 넘어온 요청에 대해 DB작업을 수행하는 메소드들의 집합
 * DB에 직접 접근하며 쿼리문에 적용할 parameter를 보내주거나 단순 쿼리 실행을 하도록 호출한다
 * select, update, delete insert 함수를 사용하며 쿼리아이디와 parameter를 넘긴다
 * @author 포탈통계 이관형 
 * @since 2011.06.17 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.06.28     이관형      최초 생성 
 * 
 * </pre> 
 */
@Repository("recomendManageDAO")
public class RecomendManageDAO extends GpsAbstractDAO {

	/**
	 * 추천사이트삭제
	 * @param recomendManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_IMAGE
	 */
	public void deleteRecomend(RecomendManageVO recomendManageVO) throws Exception {
		delete("RecomendManageDAO.deleteRecomend", recomendManageVO);
	}

	/**
	 * 추천사이트등록
	 * @param recomendManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_IMAGE
	 */
	public void insertRecomend(RecomendManageVO recomendManageVO) throws Exception {
        insert("RecomendManageDAO.insertRecomend", recomendManageVO);
	}

	/**
	 * 추천사이트수정
	 * @param recomendManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_IMAGE
	 */
	public void updateRecomend(RecomendManageVO recomendManageVO) throws Exception {
        update("RecomendManageDAO.updateRecomend", recomendManageVO);
	}

	/**
	 * 추천사이트 상세 조회
	 * @param recomendManageVO
	 * @return
	 * @throws Exception
	 * @see IMAGE_ID,IMAGE_SN,IMAGE_SE,IMAGE_NM,IMAGE_FILE_NM,IMAGE_FILE_MASK,IMAGE_FILE_SIZE,IMAGE_FILE_MIME,IMAGE_WIDTH,
	 * @see IMAGE_HEIGHT,IMAGE_REFLCT_AT,IMAGE_REFLCT_URL,SYS_ID,SYS_NM,REGISTER_IP,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID
	 * @see TABLE NAME : TN_IMAGE,TN_SYSTEM
	 */
	public RecomendManageVO selectRecomend(RecomendManageVO recomendManageVO) throws Exception {
		return (RecomendManageVO)selectByPk("RecomendManageDAO.selectRecomend", recomendManageVO);
	}

    /**
     * 추천사이트 목록 조회
     * @param recomendManageVO
     * @return List
     * @throws Exception
     * @see IMAGE_ID,IMAGE_SN,IMAGE_SE,IMAGE_NM,IMAGE_FILE_NM,IMAGE_FILE_MASK,IMAGE_FILE_SIZE,IMAGE_FILE_MIME,IMAGE_WIDTH,
	 * @see IMAGE_HEIGHT,IMAGE_REFLCT_AT,IMAGE_REFLCT_URL,SYS_ID,SYS_NM,REGISTER_IP,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID
	 * @see TABLE NAME : TN_IMAGE,TN_SYSTEM
     */
    @SuppressWarnings("unchecked")
	public List selectRecomendList(RecomendManageVO recomendManageVO) throws Exception {
        return list("RecomendManageDAO.selectRecomendList", recomendManageVO);
    }
	
	/** 
	 * 추천사이트 목록 총 갯수
	 * @param recomendManageVO 총 갯수를 조회할 프로그램VO
	 * @return int
	 * @throws Exception 
     * @see IMAGE_ID,IMAGE_SN,IMAGE_SE,IMAGE_NM,IMAGE_FILE_NM,IMAGE_FILE_MASK,IMAGE_FILE_SIZE,IMAGE_FILE_MIME,IMAGE_WIDTH,
	 * @see IMAGE_HEIGHT,IMAGE_REFLCT_AT,IMAGE_REFLCT_URL,SYS_ID,SYS_NM,REGISTER_IP,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID
	 * @see TABLE NAME : TN_IMAGE,TN_SYSTEM
	 */  
    public int selectRecomendListTotCnt(RecomendManageVO recomendManageVO) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("RecomendManageDAO.selectRecomendListTotCnt", recomendManageVO);
    }
    
    /** 
	 * 추천사이트 SN 생성
	 * @return int
	 * @throws Exception 
	 * @see TABLE NAME : TN_IMAGE
	 */
    public int siteSnGeneration() throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("RecomendManageDAO.siteSnGeneration", null);
    }

}
