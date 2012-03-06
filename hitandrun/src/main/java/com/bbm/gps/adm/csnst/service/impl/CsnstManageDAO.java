package com.bbm.gps.adm.csnst.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.GpsAbstractDAO;
import com.bbm.gps.adm.csnst.service.CsnstManageVO;

/**
 * 만족도 조사 관리 DAO CLASS
 * <p><b>NOTE:</b> DB에 직접 접근하며 쿼리문에 적용할 parameter를 보내주거나 단순 쿼리 실행을 하도록 호출한다
 * select, update, delete 함수를 사용하며 쿼리아이디와 parameter를 넘긴다
 * @author 통계포털 이관형
 * @since 2011.10.21
 * @version 1.0
 * @see
 *
 * <pre>
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.10.21  이관형          최초 생성
 *
 * </pre>
 */

@Repository("csnstManageDAO")
public class CsnstManageDAO extends GpsAbstractDAO{

	/**
	 * 만족도 조사 삭제
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_MANAGE
	 */
	public void deleteCsnst(CsnstManageVO csnstManageVO) throws Exception {
		delete("csnstManageDAO.deleteCsnst", csnstManageVO);
	}

	/**
	 * 만족도 조사 등록
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_MANAGE
	 */
	public void insertCsnst(CsnstManageVO csnstManageVO) throws Exception {
        insert("csnstManageDAO.insertCsnst", csnstManageVO);
	}

	/**
	 * 만족도 조사 수정
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_MANAGE
	 */
	public void updateCsnst(CsnstManageVO csnstManageVO) throws Exception {
        update("csnstManageDAO.updateCsnst", csnstManageVO);
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
		return (CsnstManageVO)selectByPk("csnstManageDAO.selectCsnst", csnstManageVO);
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
		return (CsnstManageVO)selectByPk("csnstManageDAO.selectCsnstReview", csnstManageVO);
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
        return list("csnstManageDAO.selectCsnstList", csnstManageVO);
    }

	/**
	 * 만족도 조사 목록 조회
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see COUNT(*)
	 * @see TABLE NAME : TN_CSNST_MANAGE
	 */
    public int selectCsnstListTotCnt(CsnstManageVO csnstManageVO) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("csnstManageDAO.selectCsnstListTotCnt", csnstManageVO);
    }

	/**
	 * 만족도 조사 일력번호 생성
	 * @throws Exception
	 * @see CSNST_ID
	 * @see TABLE NAME : TN_CSNST_MANAGE
	 */
    public int csnstSnGeneration() throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("csnstManageDAO.csnstSnGeneration", null);
    }

}
