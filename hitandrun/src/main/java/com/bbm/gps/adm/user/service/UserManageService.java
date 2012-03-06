package com.bbm.gps.adm.user.service;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import com.bbm.gps.adm.menu.service.SystemVO;
import com.bbm.gps.adm.org.service.OrgManageVO;

import java.util.List;
import java.util.Map;

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
public interface UserManageService {
	
	/** 
	 * @param AuthorManageVO 권한정보VO
	 * 사용자유형(권한)에 대하여 목록 조회
	 * @return List 조회한 사용자유형(권한)목록의 리스트
	 * @exception Exception 
	 * @see TABLE NAME : TN_AUTHOR
	 */ 
	@SuppressWarnings("unchecked")
    List selectUserTypeList(SystemVO systemManageVO) throws Exception;

    int selectUserTypeListTotCnt() throws Exception;
    
	/** 
     * 관리자정보 수정
     * @param userManageVO  관리자정보 업데이트 항목들에 대한 정보를 가지고있는 VO
     * @exception Exception 
     * @see TABLE NAME : 
     */ 
	void updateAdmin(UserManageVO userManageVO) throws Exception;

	/** 
     * 관리자정보 삭제
     * @param userManageVO  관리자정보 삭제 항목들에 대한 정보를 가지고있는 VO
     * @exception Exception 
     * @see TABLE NAME : 
     */ 
	void deleteUser(UserManageVO userManageVO) throws Exception;

	/** 
     * userManageVO VO에 담겨있는 항목을 DB에 insert 
     * @param userManageVO  insert할 항목에 대한 사용자 정보를 담고있는 VO 
     * @exception Exception 
     * @see TABLE NAME : 
     */
	void insertUser(UserManageVO userManageVO) throws Exception;

	/** 
     * userManageVO 수정할 대상 게시물ID에 해당하는 행을 업데이트 
     * @param userManageVO  업데이트 항목들에 대한 정보를 가지고있는 VO
     * @exception Exception 
     * @see TABLE NAME : 
     */ 
	void updateUser(UserManageVO userManageVO) throws Exception;

	/** 
     * userManageVO 게시물에 대한 상세화면을 조회 
     * @return UserManageVO 조회해온 상세항목에 대한 정보를 담고있는 VO 
     * @exception Exception 
     * @see TABLE NAME : 
     */
	UserManageVO selectUser(UserManageVO userManageVO) throws Exception;

	/** 
	 * userManageVO 모든사용자 목록 조회  
	 * @param userManageVO  조회조건 Vo
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
     * @see TABLE NAME : 
	 */
	@SuppressWarnings("unchecked")
	List selectAllUserList(UserManageVO orgManageVO) throws Exception;
	
	/** 
	 * userManageVO 선택된 유형의 사용자 목록 조회  
	 * @param userManageVO  조회조건 Vo
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
     * @see TABLE NAME : 
	 */
	@SuppressWarnings("unchecked")
	List selectUserList(UserManageVO orgManageVO) throws Exception;
	
	/** 
	 * userManageVO 모든사용자 목록 조회 총 갯수를 조회한다.
	 * @param  userManageVO 총 갯수 조회조건 Vo
	 * @return int 조회한 목록의 리스트
	 * @exception Exception 
     * @see COUNT(*) totcnt 
	 */ 
    int selectAllUserListTotCnt(UserManageVO orgManageVO) throws Exception;
    
    /** 
	 * userManageVO 선택된 유형의 사용자 목록 조회 총 갯수를 조회한다.
	 * @param  userManageVO 총 갯수 조회조건 Vo
	 * @return int 조회한 목록의 리스트
	 * @exception Exception 
     * @see COUNT(*) totcnt 
	 */ 
    int selectUserListTotCnt(UserManageVO orgManageVO) throws Exception;

	/** 
	 * 기관목록을 조회한다.
	 * @param  whereMap 검색조건
	 * @return Map 조회한 기관목록의 리스트
	 * @exception Exception 
     * @see TABLE NAME : 
	 */ 
	@SuppressWarnings("unchecked")
    Map selectOrgMap(Map orgManageVO) throws Exception;

    /** 
	 * 사용자리스트에서 선택된 미승인 사용자의 승인처리 
	 * @param appUserIdList 승인할 사용자ID  리스트 
	 * @return 
	 * @exception Exception 
	 * @see TABLE NAME : TN_USER 
	*/ 
	void appUserId(UserManageVO vo) throws Exception ;
	
	/** 
	 * 우편번호 조회
     * @param ZipVO 우편번호 정보를 가지고있는 VO
     * @return 우편번호 리스트
	 * @exception Exception 
     * @see TABLE NAME : TN_ZIP
	*/ 
	@SuppressWarnings("unchecked")
	List selectZipList(ZipVO searchVO) throws Exception ;

	/** 
	 * 우편번호 목록 총 갯수
     * @param ZipVO 우편번호 정보를 가지고있는 VO
     * @return 우편번호 총 갯수
	 * @exception Exception 
     * @see TABLE NAME : TN_ZIP
	*/
	int selectZipListTotCnt(ZipVO searchVO) throws Exception ;

	/** 
	 * 비밀번호 변경
     * @param UserManageVO 비밀번호 변경 정보를 가지고있는 VO
     * @return 
	 * @exception Exception 
     * @see TABLE NAME : TN_USER
	*/
	void updatePassword(UserManageVO userManageVO) throws Exception ;
	
	public List<EgovMap> selectDeptCombo(OrgManageVO orgManageVO) throws Exception;

}
