package com.bbm.gps.adm.csnst.service;

import java.util.List;


/** 
 * 만족도 조사 항목 관리 대한 서비스 인터페이스 클래스를 정의한다. 
 * <p><b>NOTE:</b> 만족도 조사 항목관리 controller에서 호출하는 메소드들의 인터페이스가 정의되어 있으며 
 * 구현은 CsnstIemManageServiceImpl에 되어있다
 * @author 포탈통계 이관형 
 * @since 2011.10.21 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.10.21     이관형      최초 생성 
 * 
 * </pre> 
 */
public interface CsnstIemManageService {

	/**
	 * 만족도 조사 항목 삭제
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_IEM_MANAGE
	 */
	void deleteCsnstIem(CsnstManageVO csnstManageVO) throws Exception;

	/**
	 * 만족도 조사 항목 등록
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_IEM_MANAGE
	 */
	void insertCsnstIem(CsnstManageVO csnstManageVO) throws Exception;

	/**
	 * 만족도 조사 항목 수정
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_IEM_MANAGE
	 */
	void updateCsnstIem(CsnstManageVO csnstManageVO) throws Exception;

	/**
	 * 만족도 조사 항목 수정
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see SYS_ID, CSNST_ID, CSNST_SN, QESITM_SN, IEM_SN, IEM_NM, REGIST_DT, REGISTER_ID, REGISTER_IP, UPDT_DT, UPDTUSR_ID, RSPNS_CNT, RSPNS_TOTAL
	 * @see TABLE NAME : TN_CSNST_IEM_MANAGE
	 */
	@SuppressWarnings("unchecked")
	List selectCsnstIem(CsnstManageVO csnstManageVO) throws Exception;

	/**
	 * 만족도 조사 항목 수정
	 * @throws Exception
	 * @see INT
	 * @see TABLE NAME : TN_CSNST_IEM_MANAGE
	 */
    int csnstIemSnGeneration() throws Exception;
    
}
