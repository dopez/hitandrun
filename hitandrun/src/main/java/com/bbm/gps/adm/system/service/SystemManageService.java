package com.bbm.gps.adm.system.service;
//import com.bbm.sps.web.SpsSessionVO;

import java.util.List;


/** 
 * 시스템정보에 대한 서비스 인터페이스 클래스를 정의한다. 
 * <p><b>NOTE:</b> 해당 클래스에는 controller에서 호출하는 메소드들의 인터페이스가 정의되어 있으며 
 * 구현은 UserManageServiceImpl에 되어있다
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
 *   2011.07.21     이진우      사용자유형을 권한유형으로 변경
 * 
 * </pre> 
 */
public interface SystemManageService {
	
	/**
     * 시스템정보에 대하여 목록 조회(검색조건)  
     * @param userId
     * @return List
     * @throws Exception
     * @see SYS_ID,SYS_NM
     * @see TABLE NAME : TN_SYSTEM
     */
	@SuppressWarnings("unchecked")
	public List selectSysInfoList(String userId) throws Exception;
	
	/**
     * 시스템정보에 대하여 목록 조회(검색조건)  
     * @param spsSessionVO
     * @return String
     * @throws Exception
     * @see TABLE NAME : TN_SYSTEM
     */
	//public String selectSysId(SpsSessionVO spsSessionVO) throws Exception;
}
