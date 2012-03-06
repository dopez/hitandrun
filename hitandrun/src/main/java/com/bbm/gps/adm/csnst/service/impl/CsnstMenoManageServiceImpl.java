package com.bbm.gps.adm.csnst.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import com.bbm.gps.adm.csnst.service.CsnstMenoManageService;
import com.bbm.gps.adm.csnst.service.CsnstMenoVO;
 
/** 
 * 만족도 조사 메모 관리에 대한 서비스 구현클래스를 정의한다
 * <p><b>NOTE:</b> 만족도 조사 메모 관리서비스에 선언 되어있는 메소드들의 구현 클래스로 프로그램관리테이블 데이터 접근 클래스의 메소드를 호출한다
 * 메소드들 중에는 parameter를 넘기는 메소드도 있고 넘기지 않는 메소드도 존재한다
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
@Service("csnstMenoManageService")
public class CsnstMenoManageServiceImpl extends AbstractServiceImpl implements CsnstMenoManageService {

	/** csnstMenoManageDAO 서비스 호출 */ 
	@Resource(name="csnstMenoManageDAO")
    private CsnstMenoManageDAO csnstMenoManageDAO;

	/**
	 * 만족도 조사 메모 등록
	 * @param csnstMenoVO CsnstMenoVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_MENO
	 */
	public void insertCsnstMeno(CsnstMenoVO csnstMenoVO) throws Exception {
    	csnstMenoManageDAO.insertCsnstMeno(csnstMenoVO);    	
	}

	/**
	 * 만족도 조사 메모 목록 조회
	 * @param csnstMenoVO CsnstMenoVO
	 * @throws Exception
	 * @see SYS_ID, CSNST_ID, CSNST_SN, QESITM_SN, MEMO_SN, MEMO_WRTER_ID, MEMO_WRTER_NM
	 * @see MEMO_PASSWORD, MEMO_CN, REGIST_DT, REGISTER_ID, REGISTER_IP, UPDT_DT, UPDTUSR_ID
	 * @see TABLE NAME : TN_CSNST_MENO
	 */
	@SuppressWarnings("unchecked")
	public List selectCsnstMenoList(CsnstMenoVO csnstMenoVO) throws Exception {
    	return csnstMenoManageDAO.selectCsnstMenoList(csnstMenoVO);
	}

	/**
	 * 만족도 조사 메모 목록 갯수 조회
	 * @param csnstMenoVO CsnstMenoVO
	 * @throws Exception
	 * @see COUNT(*)
	 * @see TABLE NAME : TN_CSNST_MENO
	 */
	public int selectCsnstMenoListCnt(CsnstMenoVO csnstMenoVO) throws Exception {
		return csnstMenoManageDAO.selectCsnstMenoListCnt(csnstMenoVO);
	}

	/**
	 * 만족도 조사 메모 일련번호 생성
	 * @throws Exception
	 * @see MEMO_SN
	 * @see TABLE NAME : TN_CSNST_MENO
	 */
	public int csnstMenoSnGeneration() throws Exception {
		return csnstMenoManageDAO.csnstMenoSnGeneration();
	}

	/**
	 * 만족도 조사 메모 등록 체크
	 * @param csnstMenoVO CsnstMenoVO
	 * @throws Exception
	 * @see INT
	 * @see TABLE NAME : TN_CSNST_MENO
	 */
	public int selectCsnstMenoCheck(CsnstMenoVO csnstMenoVO) throws Exception {
		return csnstMenoManageDAO.selectCsnstMenoCheck(csnstMenoVO);
	}

	/**
	 * 만족도 조사 메모 삭제
	 * @param csnstMenoVO CsnstMenoVO
	 * @throws Exception
	 * @see INT
	 * @see TABLE NAME : TN_CSNST_MENO
	 */
	public void deleteCsnstMeno(CsnstMenoVO csnstMenoVO) throws Exception {
		csnstMenoManageDAO.deleteCsnstMeno(csnstMenoVO);
	}
	
}
