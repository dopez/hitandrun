package com.bbm.gps.adm.user.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import com.bbm.gps.adm.menu.service.SystemVO;
import com.bbm.gps.adm.org.service.OrgManageVO;
import com.bbm.gps.adm.user.service.UserManageService;
import com.bbm.gps.adm.user.service.UserManageVO;
import com.bbm.gps.adm.user.service.ZipVO;
import com.bbm.gps.login.service.GpsLoginVO;
import com.bbm.gps.login.service.impl.GpsLoginDAO;

/** 
 * 사용자관리에 대한 서비스 구현클래스를 정의한다
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
@Service("userManageService")
public class UserManageServiceImpl extends AbstractServiceImpl implements UserManageService {

	/** userManageDAO 서비스 호출 */ 
	@Resource(name="userManageDAO")
    private UserManageDAO userManageDAO;
	
	/** userManageDAO 서비스 호출 */ 
	@Resource(name="gpsLoginDAO")
    private GpsLoginDAO gpsLoginDAO;
	
	/** 
	 * @param AuthorManageVO 권한정보VO
	 * 사용자유형(권한)에 대하여 목록 조회
	 * @return List 조회한 사용자유형(권한)목록의 리스트
	 * @exception Exception 
	 * @see TABLE NAME : TN_AUTHOR
	 */ 
	@SuppressWarnings("unchecked")
	public List selectUserTypeList(SystemVO systemManageVO) throws Exception {
		return userManageDAO.selectUserTypeList(systemManageVO);
	}
	
	public int selectUserTypeListTotCnt() throws Exception {
		return userManageDAO.selectUserTypeListTotCnt();
	}

	/** 
     * userManageVO VO에 담겨있는 항목을 DB에 update 
     * @param userManageVO  update 항목에 대한 사용자 정보를 담고있는 VO 
     * @exception Exception 
     * @see TABLE NAME : 
     */
	public void updateAdmin(UserManageVO userManageVO) throws Exception {
		userManageDAO.updateAdmin(userManageVO);    	
	}

	/** 
     * 관리자정보 삭제
     * @param userManageVO  관리자정보 삭제 항목들에 대한 정보를 가지고있는 VO
     * @exception Exception 
     * @see TABLE NAME : 
     */ 
	public void deleteUser(UserManageVO userManageVO) throws Exception {
		userManageDAO.deleteUser(userManageVO);
	}

	/** 
     * userManageVO VO에 담겨있는 항목을 DB에 insert 
     * @param userManageVO  insert할 항목에 대한 사용자 정보를 담고있는 VO 
     * @exception Exception 
     * @see TABLE NAME : TN_USER
     */
	public void insertUser(UserManageVO userManageVO) throws Exception {
    	userManageDAO.insertUser(userManageVO);    	
	}

	/** 
     * userManageVO 수정할 대상 게시물ID에 해당하는 행을 업데이트 
     * @param userManageVO  업데이트 항목들에 대한 정보를 가지고있는 VO
     * @exception Exception 
     * @see TABLE NAME : TN_USER
     */ 
	public void updateUser(UserManageVO userManageVO) throws Exception {
    	userManageDAO.updateUser(userManageVO);    	
	}

	/** 
     * userManageVO 게시물에 대한 상세화면을 조회 
     * @return UserManageVO 조회해온 상세항목에 대한 정보를 담고있는 VO 
     * @exception Exception 
     * @see TABLE NAME : TN_USER
     */
	public UserManageVO selectUser(UserManageVO userManageVO) throws Exception {
    	return (UserManageVO)userManageDAO.selectUser(userManageVO);
	}

	/** 
	 * userManageVO 모든사용자 조회
	 * @param userManageVO  조회조건 Vo
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
     * @see TABLE NAME : TN_USER
	 */
	@SuppressWarnings("unchecked")
	public List selectAllUserList(UserManageVO userManageVO) throws Exception {
		return userManageDAO.selectAllUserList(userManageVO);
	}
	
	/** 
	 * userManageVO 선택된 유형의 사용자 조회  
	 * @param userManageVO  조회조건 Vo
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
     * @see TABLE NAME : 
	 */
	@SuppressWarnings("unchecked")
	public List selectUserList(UserManageVO userManageVO) throws Exception {
		return userManageDAO.selectUserList(userManageVO);
	}
	
	/** 
	 * userManageVO 모든사용자 조회 총 갯수를 조회한다.
	 * @param  userManageVO 총 갯수 조회조건 Vo
	 * @return int 조회한 목록의 리스트
	 * @exception Exception 
     * @see COUNT(*) totcnt 
	 */ 
	public int selectAllUserListTotCnt(UserManageVO userManageVO) throws Exception {
        return userManageDAO.selectAllUserListTotCnt(userManageVO);
	}
	
	/** 
	 * userManageVO 선택된 유형의 사용자 조회   총 갯수를 조회한다.
	 * @param  userManageVO 총 갯수 조회조건 Vo
	 * @return int 조회한 목록의 리스트
	 * @exception Exception 
     * @see COUNT(*) totcnt 
	 */ 
	public int selectUserListTotCnt(UserManageVO userManageVO) throws Exception {
        return userManageDAO.selectUserListTotCnt(userManageVO);
	}

	/** 
	 * 기관목록을 조회한다.
	 * @param  whereMap 검색조건
	 * @return Map 조회한 기관목록의 리스트
	 * @exception Exception 
     * @see TABLE NAME : 
	 */ 
	@SuppressWarnings("unchecked")
	public Map selectOrgMap(Map whereMap) throws Exception {
		return userManageDAO.selectOrgMap(whereMap);
	}

	/** 
	 * 사용자리스트에서 선택된 미승인 사용자의 승인처리 
	 * @param appUserIdList 승인할 사용자ID  리스트 
	 * @return 
	 * @exception Exception 
	 * @see TABLE NAME : TN_USER 
	*/ 
	public void appUserId(UserManageVO vo) throws Exception  {
		userManageDAO.appUserId(vo);
	}

	/** 
	 * 우편번호 조회
     * @param ZipVO 우편번호 정보를 가지고있는 VO
     * @return 우편번호 리스트
	 * @exception Exception 
     * @see TABLE NAME : TN_ZIP
	*/
	@SuppressWarnings("unchecked")
	public List selectZipList(ZipVO searchVO) throws Exception  {
		return userManageDAO.selectZipList(searchVO);
	}

	/** 
	 * 우편번호 목록 총 갯수
     * @param ZipVO 우편번호 정보를 가지고있는 VO
     * @return 우편번호 총 갯수
	 * @exception Exception 
     * @see TABLE NAME : TN_ZIP
	*/
	public int selectZipListTotCnt(ZipVO searchVO) throws Exception  {
		return userManageDAO.selectZipListTotCnt(searchVO);
	}

	/** 
	 * 비밀번호 변경
     * @param UserManageVO 비밀번호 변경 정보를 가지고있는 VO
     * @return 
	 * @exception Exception 
     * @see TABLE NAME : TN_USER
	*/
	public void updatePassword(UserManageVO userManageVO) throws Exception  {
		if(userManageDAO.updatePassword(userManageVO) > 0){
			//비밀번호 변경시 로그인 이력정보 초기화
			GpsLoginVO gpsLoginVO = new GpsLoginVO();
			gpsLoginVO.setUserId(userManageVO.getUserId());
			gpsLoginVO.setLoginCo(0);
			gpsLoginVO.setLoginFailrCo(0);
			gpsLoginVO.setLoginLockTy("N");
			this.gpsLoginDAO.updateUserLogin(gpsLoginVO);
		}
	}
	
	/** 
	 * 주어진 조건에 따른 공통코드를 불러온다.
     * @param orgManageVO 
     * @return List
	 * @exception Exception 
     * @see TABLE NAME : TN_USER
	*/
	public List<EgovMap> selectDeptCombo(OrgManageVO orgManageVO) throws Exception {
		return userManageDAO.selectDeptCombo(orgManageVO);
	}
}
