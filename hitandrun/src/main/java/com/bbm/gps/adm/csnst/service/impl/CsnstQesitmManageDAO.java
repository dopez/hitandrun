package com.bbm.gps.adm.csnst.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.GpsAbstractDAO;
import com.bbm.gps.adm.csnst.service.CsnstManageVO;

/**
 * 만족도 조사 문항 관리 DAO CLASS
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

@Repository("csnstQesitmManageDAO")
public class CsnstQesitmManageDAO extends GpsAbstractDAO{

	/**
	 * 만족도 조사 문항 삭제
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_QESITM_MANAGE
	 */
	public void deleteCsnstQesitm(CsnstManageVO csnstManageVO) throws Exception {
		delete("csnstQesitmManageDAO.deleteCsnstQesitm", csnstManageVO);
	}

	/**
	 * 만족도 조사 문항 등록
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_QESITM_MANAGE
	 */
	public void insertCsnstQesitm(CsnstManageVO csnstManageVO) throws Exception {
        insert("csnstQesitmManageDAO.insertCsnstQesitm", csnstManageVO);
	}

	/**
	 * 만족도 조사 문항 수정
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_QESITM_MANAGE
	 */
	public void updateCsnstQesitm(CsnstManageVO csnstManageVO) throws Exception {
        update("csnstQesitmManageDAO.updateCsnstQesitm", csnstManageVO);
	}

	/**
	 * 만족도 조사 문항 수정
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see SYS_ID, CSNST_ID, CSNST_SN, QESITM_SN, QESITM_QESTN_NM, QESITM_TY, QESITM_QESTN_CO
	 * @see REGIST_DT, REGISTER_ID, REGISTER_IP, UPDT_DT, UPDTUSR_ID
	 * @see TABLE NAME : TN_CSNST_QESITM_MANAGE
	 */
	public CsnstManageVO selectCsnstQesitm(CsnstManageVO csnstManageVO) throws Exception {
		return (CsnstManageVO)selectByPk("csnstQesitmManageDAO.selectCsnstQesitm", csnstManageVO);
	}

	/**
	 * 만족도 조사 문항 일련번호 생성
	 * @throws Exception
	 * @see INT
	 * @see TABLE NAME : TN_CSNST_QESITM_MANAGE
	 */
    public int csnstQesitmSnGeneration() throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("csnstQesitmManageDAO.csnstQesitmSnGeneration", null);
    }
    
}
