package com.bbm.gps.adm.csnst.service;

import java.util.List;

/** 
 * 만족도 조사 관리에 대한 서비스 인터페이스 클래스를 정의한다. 
 * <p><b>NOTE:</b> 만족도 조사 관리 controller에서 호출하는 메소드들의 인터페이스가 정의되어 있으며 
 * 구현은 CsnstManageServiceImpl에 되어있다
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
public interface CsnstManageService {

	/**
	 * 만족도 조사 삭제
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_MANAGE
	 */
	void deleteCsnst(CsnstManageVO csnstManageVO) throws Exception;

	/**
	 * 만족도 조사 등록
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_MANAGE
	 */
	void insertCsnst(CsnstManageVO csnstManageVO) throws Exception;

	/**
	 * 만족도 조사 수정
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_MANAGE
	 */
	void updateCsnst(CsnstManageVO csnstManageVO) throws Exception;

	/**
	 * 만족도 조사 조회
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see CSNST_ID, CSNST_SN, SYS_ID, SYS_NM, CSNST_SE, CSNST_NM, CSNST_DN, CSNST_USE_AT
	 * @see VALID_BGNDE, VALID_ENDDE, CSNST_OTHBCSE, CSNST_PASSWORD, CSNST_DPLCT_PERM_SE
	 * @see CSNST_MEMO_USE_AT, CSNST_MEMO_WEBEDIT_SE, CSNST_MEMO_AUTHOR_SE, CSNST_FILE_NM
	 * @see CSNST_FILE_MASK, CSNST_FILE_SIZE, CSNST_FILE_MIME, CSNST_FILE_DC, REGIST_DT
	 * @see REGISTER_ID, REGISTER_IP, UPDT_DT, UPDTUSR_ID,  QESITM_SN
	 * @see TABLE NAME : TN_CSNST_MANAGE
	 */
	CsnstManageVO selectCsnst(CsnstManageVO csnstManageVO) throws Exception;

	/**
	 * 만족도 조사 미리보기 조회
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see CSNST_ID, CSNST_SN, SYS_ID, SYS_NM, CSNST_SE, CSNST_NM, CSNST_DN, CSNST_USE_AT, VALID_BGNDE, VALID_ENDDE
	 * @see CSNST_OTHBCSE, CSNST_PASSWORD, CSNST_DPLCT_PERM_SE, CSNST_MEMO_USE_AT, CSNST_MEMO_WEBEDIT_SE, CSNST_MEMO_AUTHOR_SE, CSNST_FILE_NM
	 * @see CSNST_FILE_MASK, CSNST_FILE_SIZE, CSNST_FILE_MIME, CSNST_FILE_DC, QESITM_SN, QESITM_QESTN_NM, QESITM_TY
	 * @see TABLE NAME : TN_CSNST_MANAGE
	 */
	CsnstManageVO selectCsnstReview(CsnstManageVO csnstManageVO) throws Exception;

	/**
	 * 만족도 조사 목록 조회
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see CSNST_ID, CSNST_SN, SYS_ID, SYS_NM, CSNST_SE, CSNST_NM, CSNST_DN, CSNST_USE_AT
	 * @see VALID_BGNDE, VALID_ENDDE, CSNST_OTHBCSE, CSNST_PASSWORD, CSNST_DPLCT_PERM_SE
	 * @see CSNST_MEMO_USE_AT, CSNST_MEMO_WEBEDIT_SE, CSNST_MEMO_AUTHOR_SE, CSNST_FILE_NM
	 * @see CSNST_FILE_MASK, CSNST_FILE_SIZE, CSNST_FILE_MIME, CSNST_FILE_DC, REGIST_DT
	 * @see REGISTER_ID, REGISTER_IP, UPDT_DT, UPDTUSR_ID,  QESITM_SN
	 * @see TABLE NAME : TN_CSNST_MANAGE
	 */
	@SuppressWarnings("unchecked")
	List selectCsnstList(CsnstManageVO csnstManageVO) throws Exception;

	/**
	 * 만족도 조사 목록 조회
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see COUNT(*)
	 * @see TABLE NAME : TN_CSNST_MANAGE
	 */
    int selectCsnstListTotCnt(CsnstManageVO csnstManageVO) throws Exception;

	/**
	 * 만족도 조사 일력번호 생성
	 * @throws Exception
	 * @see CSNST_ID
	 * @see TABLE NAME : TN_CSNST_MANAGE
	 */
    int csnstSnGeneration() throws Exception;
    
}
