package com.bbm.gps.adm.recomend.service;

import java.util.List;

/** 
 * 추천사이트관리에 대한 서비스 인터페이스 클래스를 정의한다. 
 * <p><b>NOTE:</b> 추천사이트관리 controller에서 호출하는 메소드들의 인터페이스가 정의되어 있으며 
 * 구현은 RecomendManageServiceImpl에 되어있다
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
 *   2011.06.17     이관형      최초 생성 
 * 
 * </pre> 
 */
public interface RecomendManageService {

	/**
	 * 추천사이트삭제
	 * @param recomendManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_IMAGE
	 */
	void deleteRecomend(RecomendManageVO recomendManageVO) throws Exception;

	/**
	 * 추천사이트등록
	 * @param recomendManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_IMAGE
	 */
	void insertRecomend(RecomendManageVO recomendManageVO) throws Exception;

	/**
	 * 추천사이트수정
	 * @param recomendManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_IMAGE
	 */
	void updateRecomend(RecomendManageVO recomendManageVO) throws Exception;

	/**
	 * 추천사이트 상세 조회
	 * @param recomendManageVO
	 * @return
	 * @throws Exception
	 * @see IMAGE_ID,IMAGE_SN,IMAGE_SE,IMAGE_NM,IMAGE_FILE_NM,IMAGE_FILE_MASK,IMAGE_FILE_SIZE,IMAGE_FILE_MIME,IMAGE_WIDTH,
	 * @see IMAGE_HEIGHT,IMAGE_REFLCT_AT,IMAGE_REFLCT_URL,SYS_ID,SYS_NM,REGISTER_IP,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID
	 * @see TABLE NAME : TN_IMAGE,TN_SYSTEM
	 */
	RecomendManageVO selectRecomend(RecomendManageVO recomendManageVO) throws Exception;
	
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
	List selectRecomendList(RecomendManageVO recomendManageVO) throws Exception;
	
	/** 
	 * 추천사이트 목록 총 갯수
	 * @param recomendManageVO 총 갯수를 조회할 프로그램VO
	 * @return int
	 * @throws Exception 
     * @see IMAGE_ID,IMAGE_SN,IMAGE_SE,IMAGE_NM,IMAGE_FILE_NM,IMAGE_FILE_MASK,IMAGE_FILE_SIZE,IMAGE_FILE_MIME,IMAGE_WIDTH,
	 * @see IMAGE_HEIGHT,IMAGE_REFLCT_AT,IMAGE_REFLCT_URL,SYS_ID,SYS_NM,REGISTER_IP,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID
	 * @see TABLE NAME : TN_IMAGE,TN_SYSTEM
	 */  
    int selectRecomendListTotCnt(RecomendManageVO recomendManageVO) throws Exception;
    
    /** 
	 * 추천사이트 SN 생성
	 * @return int
	 * @throws Exception 
	 * @see TABLE NAME : TN_IMAGE
	 */
    int siteSnGeneration() throws Exception;
}
