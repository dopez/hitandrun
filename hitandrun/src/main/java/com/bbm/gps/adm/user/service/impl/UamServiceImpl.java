package com.bbm.gps.adm.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import com.bbm.gps.adm.user.service.UamService;
import com.bbm.gps.adm.user.service.UamVO;
import com.bbm.gps.adm.user.service.UserManageVO;

/** 
 * 사용자부재관리에 대한 서비스 구현클래스를 정의한다
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
 *   2011.07.21     이진우      사용자유형을 권한유형으로 변경
 * 
 * </pre> 
 */
@Service("uamService")
public class UamServiceImpl extends AbstractServiceImpl implements UamService {

	/** userManageDAO 서비스 호출 */ 
	@Resource(name="uamDAO")
    private UamDAO uamDAO;
	
	/** 
	 * 사용자유형의 사용자목록 조회  
	 * @param userManageVO  조회조건
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
	 * @see TABLE NAME : TN_USER
	 */ 
	@SuppressWarnings("unchecked")
	public List selectAbsnceUserList(UserManageVO userManageVO) throws Exception {
		return uamDAO.selectAbsnceUserList(userManageVO);
	}
	
	/** 
	 * 사용자유형의 사용자목록 총 갯수 
	 * @param userManageVO  조회조건
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
	 * @see TABLE NAME : TN_USER
	 */ 
	public int selectAbsnceUserListTotCnt(UserManageVO userManageVO) throws Exception {
		return uamDAO.selectAbsnceUserListTotCnt(userManageVO);
	}
	
	/** 
	 * 사용자유형의 사용자목록 총 갯수 
	 * @param userManageVO  조회조건
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
	 * @see TABLE NAME : TN_USER , TN_USER_AUTHOR , TN_USER_ABSNCE
	 */ 
	@SuppressWarnings("unchecked")
	public List selectTransferList(UserManageVO userManageVO) throws Exception {
		return uamDAO.selectTransferList(userManageVO);
	}
	
	/** 
	 * 사용자유형의 사용자목록 총 갯수 
	 * @param userManageVO  조회조건
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
	 * @see TABLE NAME : TN_USER , TN_USER_AUTHOR , TN_USER_ABSNCE
	 */ 
	public int selectTransferListTotCnt(UserManageVO userManageVO) throws Exception {
		return uamDAO.selectTransferListTotCnt(userManageVO);
	}
	
	/** 
	 * 사용자 부재 목록 등록
	 * @param uamVO  조회조건
	 * @return 
	 * @exception Exception 
	 * @see TABLE NAME : TN_USER_ABSNCE
	 */ 
	public void insertUserAbsnce(UamVO uamVO) throws Exception {
		uamDAO.insertUserAbsnce(uamVO);
	}
	
	/** 
	 * 사용자 부재 목록 삭제
	 * @param uamVO  조회조건
	 * @return 
	 * @exception Exception 
	 * @see TABLE NAME : TN_USER_ABSNCE
	 */ 
	public void deleteUserAbsnce(UamVO uamVO) throws Exception {
		uamDAO.deleteUserAbsnce(uamVO);
	}
}
