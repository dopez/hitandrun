package com.bbm.gps.user.service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import com.bbm.gps.adm.org.service.OrgManageVO;

import java.util.List;



/** 
 * 회원가입에 대한 서비스 인터페이스 클래스를 정의한다. 
 * <p><b>NOTE:</b> 해당 클래스에는 controller에서 호출하는 메소드들의 인터페이스가 정의되어 있으며 
 * 구현은 UserRegisterServiceImpl에 되어있다
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
public interface UserRegisterService {

	/** 
     * userRegisterVO VO에 담겨있는 항목을 DB에 insert 
     * @param userRegisterVO  insert할 항목에 대한 사용자 정보를 담고있는 VO 
     * @exception Exception 
     * @see TABLE NAME : TN_USER
     */
	void insertUser(UserRegisterVO userRegisterVO) throws Exception;

	
	/**
	 * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
	 * @param checkId 중복여부 확인대상 아이디
	 * @return 사용가능여부(아이디 사용회수 int)
	 * @throws Exception
	 */
	public int checkIdDplct(String checkId) throws Exception;
	
	public List<EgovMap> selectOrgCombo(OrgManageVO orgManageVO) throws Exception;
	
	public List<EgovMap> selectDeptCombo(OrgManageVO orgManageVO) throws Exception;

	/** 
     * userManageVO 수정할 대상 게시물ID에 해당하는 행을 업데이트 
     * @param userManageVO  업데이트 항목들에 대한 정보를 가지고있는 VO
     * @exception Exception 
     * @see TABLE NAME : 
     */ 
	void updateUser(UserRegisterVO userRegisterVO) throws Exception;
	
	/** 
	 * 중복가입체크
     * @param userRegisterVO 
     * @exception Exception 
	 * @see TABLE NAME : TN_USER
     */
	public int duplicateJoinChk(UserRegisterVO userRegisterVO) throws Exception;
	
	/** 
     * 인증서 등록 처리
     * @param userManageVO  업데이트 항목들에 대한 정보를 가지고있는 VO
     * @exception Exception 
	 * @see TABLE NAME : 
     */
	public void updateGpki(UserRegisterVO userRegisterVO) throws Exception;
}
