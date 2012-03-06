package com.bbm.gps.adm.csnst.service;

import java.util.List;


/** 
 * 만족도 조사 메모 관리에 대한 서비스 인터페이스 클래스를 정의한다. 
 * <p><b>NOTE:</b> 만족도 조사 메모 관리 controller에서 호출하는 메소드들의 인터페이스가 정의되어 있으며 
 * 구현은 CsnstMenoManageServiceImpl에 되어있다
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
public interface CsnstMenoManageService {

	/**
	 * 만족도 조사 메모 등록
	 * @param csnstMenoVO CsnstMenoVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_MENO
	 */
	void insertCsnstMeno(CsnstMenoVO csnstMenoVO) throws Exception;

	/**
	 * 만족도 조사 메모 목록 조회
	 * @param csnstMenoVO CsnstMenoVO
	 * @throws Exception
	 * @see SYS_ID, CSNST_ID, CSNST_SN, QESITM_SN, MEMO_SN, MEMO_WRTER_ID, MEMO_WRTER_NM
	 * @see MEMO_PASSWORD, MEMO_CN, REGIST_DT, REGISTER_ID, REGISTER_IP, UPDT_DT, UPDTUSR_ID
	 * @see TABLE NAME : TN_CSNST_MENO
	 */
	@SuppressWarnings("unchecked")
	List selectCsnstMenoList(CsnstMenoVO csnstMenoVO) throws Exception;

	/**
	 * 만족도 조사 메모 목록 갯수 조회
	 * @param csnstMenoVO CsnstMenoVO
	 * @throws Exception
	 * @see COUNT(*)
	 * @see TABLE NAME : TN_CSNST_MENO
	 */
	int selectCsnstMenoListCnt(CsnstMenoVO csnstMenoVO) throws Exception;

	/**
	 * 만족도 조사 메모 일련번호 생성
	 * @throws Exception
	 * @see MEMO_SN
	 * @see TABLE NAME : TN_CSNST_MENO
	 */
    int csnstMenoSnGeneration() throws Exception;

	/**
	 * 만족도 조사 메모 등록 체크
	 * @param csnstMenoVO CsnstMenoVO
	 * @throws Exception
	 * @see INT
	 * @see TABLE NAME : TN_CSNST_MENO
	 */
    int selectCsnstMenoCheck(CsnstMenoVO csnstMenoVO) throws Exception;

	/**
	 * 만족도 조사 메모 삭제
	 * @param csnstMenoVO CsnstMenoVO
	 * @throws Exception
	 * @see INT
	 * @see TABLE NAME : TN_CSNST_MENO
	 */
    void deleteCsnstMeno(CsnstMenoVO csnstMenoVO) throws Exception;
}
