package com.bbm.gps.adm.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import com.bbm.gps.adm.system.service.SystemManageService;
//import com.bbm.sps.web.SpsSessionVO;

/** 
 * 시스템정보에 대한 서비스 구현클래스를 정의한다
 * <p><b>NOTE:</b> 서비스에 선언 되어있는 메소드들의 구현 클래스로 데이터 접근 클래스의 메소드를 호출한다
 * 메소드들 중에는 parameter를 넘기는 메소드도 있고 넘기지 않는 메소드도 존재한다
 * @author 범정부통계포털 이관형 
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
@Service("systemManageService")
public class SystemManageServiceImpl extends AbstractServiceImpl implements SystemManageService {

	/** userManageDAO 서비스 호출 */ 
	@Resource(name="systemManageDAO")
    private SystemManageDAO systemManageDAO;
	
	/**
     * 시스템정보에 대하여 목록 조회(검색조건)  
     * @param userId
     * @return List
     * @throws Exception
     * @see SYS_ID,SYS_NM
     * @see TABLE NAME : TN_SYSTEM
     */
	@SuppressWarnings("unchecked")
	public List selectSysInfoList(String userId) throws Exception {
		return systemManageDAO.selectSysInfoList(userId);
	}
	
	/**
     * 시스템정보에 대하여 목록 조회(검색조건)  
     * @param spsSessionVO
     * @return String
     * @throws Exception
     * @see TABLE NAME : TN_SYSTEM
     */
	/*public String selectSysId(SpsSessionVO spsSessionVO) throws Exception {
		return systemManageDAO.selectSysId(spsSessionVO);
	}*/
}
