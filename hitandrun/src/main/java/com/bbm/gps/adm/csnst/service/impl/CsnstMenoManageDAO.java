package com.bbm.gps.adm.csnst.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.GpsAbstractDAO;
import com.bbm.gps.adm.csnst.service.CsnstMenoVO;

/**
 * 만족도 조사 메모 관리 DAO CLASS
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

@Repository("csnstMenoManageDAO")
public class CsnstMenoManageDAO extends GpsAbstractDAO{

	/**
	 * 만족도 조사 메모 등록
	 * @param csnstMenoVO CsnstMenoVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_MENO
	 */
	public void insertCsnstMeno(CsnstMenoVO csnstMenoVO) throws Exception {
        insert("csnstMenoManageDAO.insertCsnstMeno", csnstMenoVO);
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
		return list("csnstMenoManageDAO.selectCsnstMenoList", csnstMenoVO);
	}

	/**
	 * 만족도 조사 메모 목록 갯수 조회
	 * @param csnstMenoVO CsnstMenoVO
	 * @throws Exception
	 * @see COUNT(*)
	 * @see TABLE NAME : TN_CSNST_MENO
	 */
	public int selectCsnstMenoListCnt(CsnstMenoVO csnstMenoVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("csnstMenoManageDAO.selectCsnstMenoListCnt", csnstMenoVO);
	}

	/**
	 * 만족도 조사 메모 일련번호 생성
	 * @throws Exception
	 * @see MEMO_SN
	 * @see TABLE NAME : TN_CSNST_MENO
	 */
    public int csnstMenoSnGeneration() throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("csnstMenoManageDAO.csnstMemoSnGeneration", null);
    }

	/**
	 * 만족도 조사 메모 등록 체크
	 * @param csnstMenoVO CsnstMenoVO
	 * @throws Exception
	 * @see INT
	 * @see TABLE NAME : TN_CSNST_MENO
	 */
    public int selectCsnstMenoCheck(CsnstMenoVO csnstMenoVO) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("csnstMenoManageDAO.selectCsnstMenoCheck", csnstMenoVO);
    }

	/**
	 * 만족도 조사 메모 삭제
	 * @param csnstMenoVO CsnstMenoVO
	 * @throws Exception
	 * @see INT
	 * @see TABLE NAME : TN_CSNST_MENO
	 */
	public void deleteCsnstMeno(CsnstMenoVO csnstMenoVO) throws Exception {
		delete("csnstMenoManageDAO.deleteCsnstMeno", csnstMenoVO);
	}

}
