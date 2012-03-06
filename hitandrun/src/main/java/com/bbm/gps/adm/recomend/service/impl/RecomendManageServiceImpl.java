package com.bbm.gps.adm.recomend.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import com.bbm.gps.adm.recomend.service.RecomendManageService;
import com.bbm.gps.adm.recomend.service.RecomendManageVO;
 
/** 
 * 추천사이트관리에 대한 서비스 구현클래스를 정의한다
 * <p><b>NOTE:</b> 추천사이트관리서비스에 선언 되어있는 메소드들의 구현 클래스로 프로그램관리테이블 데이터 접근 클래스의 메소드를 호출한다
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
@Service("recomendManageService")
public class RecomendManageServiceImpl extends AbstractServiceImpl implements RecomendManageService {

	/** recomendManageDAO 서비스 호출 */ 
	@Resource(name="recomendManageDAO")
    private RecomendManageDAO recomendManageDAO;

	/**
	 * 추천사이트삭제
	 * @param recomendManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_IMAGE
	 */
	public void deleteRecomend(RecomendManageVO recomendManageVO) throws Exception {
		recomendManageDAO.deleteRecomend(recomendManageVO);
	}

	/**
	 * 추천사이트등록
	 * @param recomendManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_IMAGE
	 */
	public void insertRecomend(RecomendManageVO recomendManageVO) throws Exception {
    	recomendManageDAO.insertRecomend(recomendManageVO);    	
	}

	/**
	 * 추천사이트수정
	 * @param recomendManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_IMAGE
	 */
	public void updateRecomend(RecomendManageVO recomendManageVO) throws Exception {
    	recomendManageDAO.updateRecomend(recomendManageVO);    	
	}

	/**
	 * 추천사이트 상세 조회
	 * @param recomendManageVO
	 * @return
	 * @throws Exception
	 * @see IMAGE_ID,IMAGE_SN,IMAGE_SE,IMAGE_NM,IMAGE_FILE_NM,IMAGE_FILE_MASK,IMAGE_FILE_SIZE,IMAGE_FILE_MIME,IMAGE_WIDTH,
	 * @see IMAGE_HEIGHT,IMAGE_REFLCT_AT,IMAGE_REFLCT_URL,SYS_ID,SYS_NM,REGISTER_IP,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID
	 * @see TABLE NAME : TN_IMAGE,TN_SYSTEM
	 */
	public RecomendManageVO selectRecomend(RecomendManageVO recomendManageVO) throws Exception {
    	RecomendManageVO ret = (RecomendManageVO)recomendManageDAO.selectRecomend(recomendManageVO);
    	return ret;
	}
	
	/**
     * 추천사이트 목록 조회
     * @param recomendManageVO
     * @return List
     * @throws Exception
     * @see IMAGE_ID,IMAGE_SN,IMAGE_SE,IMAGE_NM,IMAGE_FILE_NM,IMAGE_FILE_MASK,IMAGE_FILE_SIZE,IMAGE_FILE_MIME,IMAGE_WIDTH,
	 * @see IMAGE_HEIGHT,IMAGE_REFLCT_AT,IMAGE_REFLCT_URL,SYS_ID,SYS_NM,REGISTER_IP,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID
	 * @see TABLE NAME : TN_IMAGE,TN_SYSTEM
     */
	@SuppressWarnings("unchecked")
	public List selectRecomendList(RecomendManageVO recomendManageVO) throws Exception {
        return recomendManageDAO.selectRecomendList(recomendManageVO);
	}
	
	/** 
	 * 추천사이트 목록 총 갯수
	 * @param recomendManageVO 총 갯수를 조회할 프로그램VO
	 * @return int
	 * @throws Exception 
     * @see IMAGE_ID,IMAGE_SN,IMAGE_SE,IMAGE_NM,IMAGE_FILE_NM,IMAGE_FILE_MASK,IMAGE_FILE_SIZE,IMAGE_FILE_MIME,IMAGE_WIDTH,
	 * @see IMAGE_HEIGHT,IMAGE_REFLCT_AT,IMAGE_REFLCT_URL,SYS_ID,SYS_NM,REGISTER_IP,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID
	 * @see TABLE NAME : TN_IMAGE,TN_SYSTEM
	 */  
	public int selectRecomendListTotCnt(RecomendManageVO recomendManageVO) throws Exception {
        return recomendManageDAO.selectRecomendListTotCnt(recomendManageVO);
	}
	
	/** 
	 * 추천사이트 SN 생성
	 * @return int
	 * @throws Exception 
	 * @see TABLE NAME : TN_IMAGE
	 */
	public int siteSnGeneration() throws Exception {
		return recomendManageDAO.siteSnGeneration();
	}
}
