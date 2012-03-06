package com.bbm.gps.adm.csnst.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.GpsAbstractDAO;
import com.bbm.gps.adm.csnst.service.CsnstManageVO;

/**
 * 만족도 조사 항목 관리 DAO CLASS
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

@Repository("csnstIemManageDAO")
public class CsnstIemManageDAO extends GpsAbstractDAO{

	/**
	 * 만족도 조사 항목 삭제
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_IEM_MANAGE
	 */
	public void deleteCsnstIem(CsnstManageVO csnstManageVO) throws Exception {
		delete("csnstIemManageDAO.deleteCsnstIem", csnstManageVO);
	}

	/**
	 * 만족도 조사 항목 등록
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_IEM_MANAGE
	 */
	public void insertCsnstIem(CsnstManageVO csnstManageVO) throws Exception {
        insert("csnstIemManageDAO.insertCsnstIem", csnstManageVO);
	}

	/**
	 * 만족도 조사 항목 수정
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_IEM_MANAGE
	 */
	public void updateCsnstIem(CsnstManageVO csnstManageVO) throws Exception {
        update("csnstIemManageDAO.updateCsnstIem", csnstManageVO);
	}

	/**
	 * 만족도 조사 항목 수정
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see SYS_ID, CSNST_ID, CSNST_SN, QESITM_SN, IEM_SN, IEM_NM, REGIST_DT, REGISTER_ID, REGISTER_IP, UPDT_DT, UPDTUSR_ID, RSPNS_CNT, RSPNS_TOTAL
	 * @see TABLE NAME : TN_CSNST_IEM_MANAGE
	 */
	@SuppressWarnings("unchecked")
	public List selectCsnstIem(CsnstManageVO csnstManageVO) throws Exception {
		return list("csnstIemManageDAO.selectCsnstIem", csnstManageVO);
	}

	/**
	 * 만족도 조사 항목 수정
	 * @throws Exception
	 * @see INT
	 * @see TABLE NAME : TN_CSNST_IEM_MANAGE
	 */
    public int csnstIemSnGeneration() throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("csnstIemManageDAO.csnstIemSnGeneration", null);
    }
    
}
