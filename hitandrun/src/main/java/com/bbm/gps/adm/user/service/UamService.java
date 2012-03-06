package com.bbm.gps.adm.user.service;

import java.util.List;

/** 
 * 사용자관리에 대한 서비스 인터페이스 클래스를 정의한다. 
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
public interface UamService {

	@SuppressWarnings("unchecked")
	List selectAbsnceUserList(UserManageVO userManageVO) throws Exception;
	
	int selectAbsnceUserListTotCnt(UserManageVO userManageVO) throws Exception;

	@SuppressWarnings("unchecked")
	List selectTransferList(UserManageVO userManageVO) throws Exception;

	int selectTransferListTotCnt(UserManageVO userManageVO) throws Exception;
	
	public void insertUserAbsnce(UamVO uamVO) throws Exception;
	
	public void deleteUserAbsnce(UamVO uamVO) throws Exception;
}
