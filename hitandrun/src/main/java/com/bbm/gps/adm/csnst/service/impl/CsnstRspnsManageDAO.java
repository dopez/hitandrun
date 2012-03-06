package com.bbm.gps.adm.csnst.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.GpsAbstractDAO;
import com.bbm.gps.adm.csnst.service.CsnstRspnsVO;

/**
 * 만족도 조사 응답결과 관리 DAO CLASS
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

@Repository("csnstRspnsManageDAO")
public class CsnstRspnsManageDAO extends GpsAbstractDAO{

	/**
	 * 만족도 조사 응답결과 등록
	 * @param csnstRspnsVO CsnstRspnsVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_RSPNS
	 */
	public void insertCsnstRspns(CsnstRspnsVO csnstRspnsVO) throws Exception {
        insert("csnstRspnsManageDAO.insertCsnstRspns", csnstRspnsVO);
	}

	/**
	 * 만족도 조사 응답결과 조회
	 * @param csnstRspnsVO CsnstRspnsVO
	 * @throws Exception
	 * @see SYS_ID, CSNST_ID, CSNST_SN, QESITM_SN, IEM_SN, RSPNS_SN, REGIST_DT, REGISTER_ID
	 * @see REGISTER_IP, UPDT_DT, UPDTUSR_ID
	 * @see TABLE NAME : TN_CSNST_RSPNS
	 */
	@SuppressWarnings("unchecked")
	public List selectCsnstRspns(CsnstRspnsVO csnstRspnsVO) throws Exception {
		return list("csnstRspnsManageDAO.selectCsnstRspns", csnstRspnsVO);
	}

	/**
	 * 만족도 조사 응답결과 일련번호 생성
	 * @throws Exception
	 * @see INT
	 * @see TABLE NAME : TN_CSNST_RSPNS
	 */
    public int csnstRspnsSnGeneration() throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("csnstRspnsManageDAO.csnstRspnsSnGeneration", null);
    }

	/**
	 * 만족도 조사 응답결과 체크
	 * @param csnstRspnsVO CsnstRspnsVO
	 * @throws Exception
	 * @see INT
	 * @see TABLE NAME : TN_CSNST_RSPNS
	 */
    public int selectCsnstRspnsCheck(CsnstRspnsVO csnstRspnsVO) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("csnstRspnsManageDAO.selectCsnstRspnsCheck", csnstRspnsVO);
    }
   
}
