package com.bbm.gps.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import com.bbm.gps.adm.org.service.OrgManageVO;
import com.bbm.gps.user.service.UserRegisterService;
import com.bbm.gps.user.service.UserRegisterVO;

/** 
 * 회원가입에 대한 서비스 구현클래스를 정의한다
 * <p><b>NOTE:</b> 서비스에 선언 되어있는 메소드들의 구현 클래스로 데이터 접근 클래스의 메소드를 호출한다
 * 메소드들 중에는 parameter를 넘기는 메소드도 있고 넘기지 않는 메소드도 존재한다
 * @author 범정부통계포털 이진우 
 * @since 2011.08.01 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.08.01     이진우      최초 생성 
 * 
 * </pre> 
 */
@Service("userRegisterService")
public class UserRegisterServiceImpl extends AbstractServiceImpl implements UserRegisterService {

	
	/** userRegisterDAO 서비스 호출 */ 
	@Resource(name="userRegisterDAO")
    private UserRegisterDAO userRegisterDAO;
	
	/** 
     * userRegisterVO VO에 담겨있는 항목을 DB에 insert 
     * @param userRegisterVO  insert할 항목에 대한 사용자 정보를 담고있는 VO 
     * @exception Exception 
     * @see TABLE NAME : TN_USER
     */
	public void insertUser(UserRegisterVO userRegisterVO) throws Exception {
		// TODO Auto-generated method stub
		userRegisterDAO.insertUser(userRegisterVO);   
		
	}

	
	/**
	 * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
	 * @param checkId 중복여부 확인대상 아이디
	 * @return 사용가능여부(아이디 사용회수 int)
	 * @throws Exception
	 */
	public int checkIdDplct(String checkId) {
		return userRegisterDAO.checkIdDplct(checkId);
	}
	
	public List<EgovMap> selectOrgCombo(OrgManageVO orgManageVO) throws Exception {
		return userRegisterDAO.selectOrgCombo(orgManageVO);
	}
	
	public List<EgovMap> selectDeptCombo(OrgManageVO orgManageVO) throws Exception {
		return userRegisterDAO.selectDeptCombo(orgManageVO);
	}
	
	/** 
     * userManageVO 수정할 대상 게시물ID에 해당하는 행을 업데이트 
     * @param userManageVO  업데이트 항목들에 대한 정보를 가지고있는 VO
     * @exception Exception 
     * @see TABLE NAME : 
     */ 
	public void updateUser(UserRegisterVO userRegisterVO) throws Exception {
		userRegisterDAO.updateUser(userRegisterVO);    	
	}
	
	/** 
	 * 중복가입체크
     * @param userRegisterVO 
     * @exception Exception 
	 * @see TABLE NAME : TN_USER
     */
	public int duplicateJoinChk(UserRegisterVO userRegisterVO) throws Exception {
        return (Integer)this.userRegisterDAO.duplicateJoinChk(userRegisterVO);
	}
	
	/** 
     * 인증서 등록 처리
     * @param userManageVO  업데이트 항목들에 대한 정보를 가지고있는 VO
     * @exception Exception 
	 * @see TABLE NAME : 
     */
	public void updateGpki(UserRegisterVO userRegisterVO) throws Exception {
		this.userRegisterDAO.updateGpki(userRegisterVO);
	}

}
