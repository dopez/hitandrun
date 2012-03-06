package com.bbm.gps.adm.csnst.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import com.bbm.gps.adm.csnst.service.CsnstRspnsManageService;
import com.bbm.gps.adm.csnst.service.CsnstRspnsVO;
 
/** 
 * 만족도 조사 응답결과 관리에 대한 서비스 구현클래스를 정의한다
 * <p><b>NOTE:</b> 만족도 조사 응답결과관리서비스에 선언 되어있는 메소드들의 구현 클래스로 프로그램관리테이블 데이터 접근 클래스의 메소드를 호출한다
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
@Service("csnstRspnsManageService")
public class CsnstRspnsManageServiceImpl extends AbstractServiceImpl implements CsnstRspnsManageService {

	/** csnstRspnsManageDAO 서비스 호출 */ 
	@Resource(name="csnstRspnsManageDAO")
    private CsnstRspnsManageDAO csnstRspnsManageDAO;

	/**
	 * 만족도 조사 응답결과 등록
	 * @param csnstRspnsVO CsnstRspnsVO
	 * @throws Exception
	 * @see TABLE NAME : TN_CSNST_RSPNS
	 */
	public void insertCsnstRspns(CsnstRspnsVO csnstRspnsVO) throws Exception {
    	csnstRspnsManageDAO.insertCsnstRspns(csnstRspnsVO);    	
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
    	return csnstRspnsManageDAO.selectCsnstRspns(csnstRspnsVO);
	}

	/**
	 * 만족도 조사 응답결과 일련번호 생성
	 * @throws Exception
	 * @see INT
	 * @see TABLE NAME : TN_CSNST_RSPNS
	 */
	public int csnstRspnsSnGeneration() throws Exception {
		return csnstRspnsManageDAO.csnstRspnsSnGeneration();
	}

	/**
	 * 만족도 조사 응답결과 체크
	 * @param csnstRspnsVO CsnstRspnsVO
	 * @throws Exception
	 * @see INT
	 * @see TABLE NAME : TN_CSNST_RSPNS
	 */
	public int selectCsnstRspnsCheck(CsnstRspnsVO csnstRspnsVO) throws Exception {
		return csnstRspnsManageDAO.selectCsnstRspnsCheck(csnstRspnsVO);
	}
	
}
