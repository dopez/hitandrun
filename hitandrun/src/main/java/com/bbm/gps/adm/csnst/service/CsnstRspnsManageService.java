package com.bbm.gps.adm.csnst.service;

import java.util.List;


/** 
 * 만족도 조사 응답결과 관리에 대한 서비스 인터페이스 클래스를 정의한다. 
 * <p><b>NOTE:</b> 만족도 조사 응답결과 관리 controller에서 호출하는 메소드들의 인터페이스가 정의되어 있으며 
 * 구현은 CsnstRspnsManageServiceImpl에 되어있다
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
public interface CsnstRspnsManageService {

	/**
	 * 만족도 조사 응답결과 등록
	 * @param csnstRspnsVO CsnstRspnsVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_RSPNS
	 */
	void insertCsnstRspns(CsnstRspnsVO csnstRspnsVO) throws Exception;

	/**
	 * 만족도 조사 응답결과 조회
	 * @param csnstRspnsVO CsnstRspnsVO
	 * @throws Exception
	 * @see SYS_ID, CSNST_ID, CSNST_SN, QESITM_SN, IEM_SN, RSPNS_SN, REGIST_DT, REGISTER_ID
	 * @see REGISTER_IP, UPDT_DT, UPDTUSR_ID
	 * @see TABLE NAME : TN_CSNST_RSPNS
	 */
	@SuppressWarnings("unchecked")
	List selectCsnstRspns(CsnstRspnsVO csnstRspnsVO) throws Exception;

	/**
	 * 만족도 조사 응답결과 일련번호 생성
	 * @throws Exception
	 * @see INT
	 * @see TABLE NAME : TN_CSNST_RSPNS
	 */
    int csnstRspnsSnGeneration() throws Exception;

	/**
	 * 만족도 조사 응답결과 체크
	 * @param csnstRspnsVO CsnstRspnsVO
	 * @throws Exception
	 * @see INT
	 * @see TABLE NAME : TN_CSNST_RSPNS
	 */
    int selectCsnstRspnsCheck(CsnstRspnsVO csnstRspnsVO) throws Exception;
    
}
