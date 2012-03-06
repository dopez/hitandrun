package com.bbm.gps.adm.csnst.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import com.bbm.gps.adm.csnst.service.CsnstManageService;
import com.bbm.gps.adm.csnst.service.CsnstManageVO;
 
/** 
 * 만족도 조사 관리 대한 서비스 구현클래스를 정의한다
 * <p><b>NOTE:</b> 만족도 조사 관리서비스에 선언 되어있는 메소드들의 구현 클래스로 프로그램관리테이블 데이터 접근 클래스의 메소드를 호출한다
 * 메소드들 중에는 parameter를 넘기는 메소드도 있고 넘기지 않는 메소드도 존재한다
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
@Service("csnstManageService")
public class CsnstManageServiceImpl extends AbstractServiceImpl implements CsnstManageService {

	/** csnstManageDAO 서비스 호출 */ 
	@Resource(name="csnstManageDAO")
    private CsnstManageDAO csnstManageDAO;

	/**
	 * 만족도 조사 삭제
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_MANAGE
	 */
	public void deleteCsnst(CsnstManageVO csnstManageVO) throws Exception {
		csnstManageDAO.deleteCsnst(csnstManageVO);
	}

	/**
	 * 만족도 조사 등록
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_MANAGE
	 */
	public void insertCsnst(CsnstManageVO csnstManageVO) throws Exception {
    	csnstManageDAO.insertCsnst(csnstManageVO);    	
	}

	/**
	 * 만족도 조사 수정
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_MANAGE
	 */
	public void updateCsnst(CsnstManageVO csnstManageVO) throws Exception {
    	csnstManageDAO.updateCsnst(csnstManageVO);    	
	}

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
	public CsnstManageVO selectCsnst(CsnstManageVO csnstManageVO) throws Exception {
    	CsnstManageVO ret = (CsnstManageVO)csnstManageDAO.selectCsnst(csnstManageVO);
    	return ret;
	}

	/**
	 * 만족도 조사 미리보기 조회
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see CSNST_ID, CSNST_SN, SYS_ID, SYS_NM, CSNST_SE, CSNST_NM, CSNST_DN, CSNST_USE_AT, VALID_BGNDE, VALID_ENDDE
	 * @see CSNST_OTHBCSE, CSNST_PASSWORD, CSNST_DPLCT_PERM_SE, CSNST_MEMO_USE_AT, CSNST_MEMO_WEBEDIT_SE, CSNST_MEMO_AUTHOR_SE, CSNST_FILE_NM
	 * @see CSNST_FILE_MASK, CSNST_FILE_SIZE, CSNST_FILE_MIME, CSNST_FILE_DC, QESITM_SN, QESITM_QESTN_NM, QESITM_TY
	 * @see TABLE NAME : TN_CSNST_MANAGE
	 */
	public CsnstManageVO selectCsnstReview(CsnstManageVO csnstManageVO) throws Exception {
    	CsnstManageVO ret = (CsnstManageVO)csnstManageDAO.selectCsnstReview(csnstManageVO);
    	return ret;
	}

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
	public List selectCsnstList(CsnstManageVO csnstManageVO) throws Exception {
        return csnstManageDAO.selectCsnstList(csnstManageVO);
	}

	/**
	 * 만족도 조사 목록 조회
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see COUNT(*)
	 * @see TABLE NAME : TN_CSNST_MANAGE
	 */
	public int selectCsnstListTotCnt(CsnstManageVO csnstManageVO) throws Exception {
        return csnstManageDAO.selectCsnstListTotCnt(csnstManageVO);
	}

	/**
	 * 만족도 조사 일력번호 생성
	 * @throws Exception
	 * @see CSNST_ID
	 * @see TABLE NAME : TN_CSNST_MANAGE
	 */
	public int csnstSnGeneration() throws Exception {
		return csnstManageDAO.csnstSnGeneration();
	}
}
