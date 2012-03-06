package com.bbm.gps.adm.csnst.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import com.bbm.gps.adm.csnst.service.CsnstIemManageService;
import com.bbm.gps.adm.csnst.service.CsnstManageVO;
 
/** 
 * 만족도 조사 항목 관리 대한 서비스 구현클래스를 정의한다
 * <p><b>NOTE:</b> 만족도 조사 항목 관리서비스에 선언 되어있는 메소드들의 구현 클래스로 프로그램관리테이블 데이터 접근 클래스의 메소드를 호출한다
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
@Service("csnstIemManageService")
public class CsnstIemManageServiceImpl extends AbstractServiceImpl implements CsnstIemManageService {

	/** csnstIemManageDAO 서비스 호출 */ 
	@Resource(name="csnstIemManageDAO")
    private CsnstIemManageDAO csnstIemManageDAO;

	/**
	 * 만족도 조사 항목 삭제
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_IEM_MANAGE
	 */
	public void deleteCsnstIem(CsnstManageVO csnstManageVO) throws Exception {
		csnstIemManageDAO.deleteCsnstIem(csnstManageVO);
	}

	/**
	 * 만족도 조사 항목 등록
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_IEM_MANAGE
	 */
	public void insertCsnstIem(CsnstManageVO csnstManageVO) throws Exception {
    	csnstIemManageDAO.insertCsnstIem(csnstManageVO);    	
	}

	/**
	 * 만족도 조사 항목 수정
	 * @param csnstManageVO CsnstManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_IEM_MANAGE
	 */
	public void updateCsnstIem(CsnstManageVO csnstManageVO) throws Exception {
    	csnstIemManageDAO.updateCsnstIem(csnstManageVO);    	
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
    	return csnstIemManageDAO.selectCsnstIem(csnstManageVO);
	}

	/**
	 * 만족도 조사 항목 수정
	 * @throws Exception
	 * @see INT
	 * @see TABLE NAME : TN_CSNST_IEM_MANAGE
	 */
	public int csnstIemSnGeneration() throws Exception {
		return csnstIemManageDAO.csnstIemSnGeneration();
	}
}
