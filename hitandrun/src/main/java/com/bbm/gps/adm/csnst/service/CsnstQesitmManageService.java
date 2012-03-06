package com.bbm.gps.adm.csnst.service;



/** 
 * 만족도 조사 문항관리에 대한 서비스 인터페이스 클래스를 정의한다. 
 * <p><b>NOTE:</b> 만족도 조사 문항관리 controller에서 호출하는 메소드들의 인터페이스가 정의되어 있으며 
 * 구현은 CsnstQesitmManageServiceImpl에 되어있다
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
public interface CsnstQesitmManageService {

	/**
	 * 만족도 조사 문항 삭제
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_QESITM_MANAGE
	 */
	void deleteCsnstQesitm(CsnstManageVO csnstManageVO) throws Exception;

	/**
	 * 만족도 조사 문항 등록
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_QESITM_MANAGE
	 */
	void insertCsnstQesitm(CsnstManageVO csnstManageVO) throws Exception;

	/**
	 * 만족도 조사 문항 수정
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_QESITM_MANAGE
	 */
	void updateCsnstQesitm(CsnstManageVO csnstManageVO) throws Exception;

	/**
	 * 만족도 조사 문항 수정
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see SYS_ID, CSNST_ID, CSNST_SN, QESITM_SN, QESITM_QESTN_NM, QESITM_TY, QESITM_QESTN_CO
	 * @see REGIST_DT, REGISTER_ID, REGISTER_IP, UPDT_DT, UPDTUSR_ID
	 * @see TABLE NAME : TN_CSNST_QESITM_MANAGE
	 */
	CsnstManageVO selectCsnstQesitm(CsnstManageVO csnstManageVO) throws Exception;

	/**
	 * 만족도 조사 문항 일련번호 생성
	 * @throws Exception
	 * @see INT
	 * @see TABLE NAME : TN_CSNST_QESITM_MANAGE
	 */
    int csnstQesitmSnGeneration() throws Exception;
    
}
