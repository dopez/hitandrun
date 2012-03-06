package com.bbm.gps.adm.author.intergrated.service;

import com.bbm.gps.adm.menu.service.MenuManageVO;

import java.util.List;
import java.util.Map;

/** 
 * 권한관리에 대한 서비스 인터페이스 클래스를 정의한다. 
 * <p><b>NOTE:</b> 해당 클래스에는 controller에서 호출하는 메소드들의 인터페이스가 정의되어 있으며 
 * 구현은 IntergratedAuthorManageServiceImpl에 되어있다
 * @author 통계포털 이관형 
 * @since 2011.08.16 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    -------    --------------------------- 
 *   2011.08.16     이관형      최초 생성 
 * 
 * </pre> 
 */

public interface IgrAuthorManageService {

    /**
	 * 권한정보목록을 조회한다.
	 * @param igrAuthorManageVO IgrAuthorManageVO
	 * @return 권한 목록 List<IgrAuthorManageVO>
	 * @exception Exception
     * @see LEVEL, SYS_ID, SYS_NM, AUTHOR_ID, AUTHOR_NM, UPPER_AUTHOR_ID
	 * @see TABLE NAME : TN_AUTHOR
	 */
	public List<IgrAuthorManageVO> selectIamList(IgrAuthorManageVO authorManageVO) throws Exception;

	/** 
     * 권한정보에 대한 상세화면을 조회한다.
	 * @param igrAuthorManageVO IgrAuthorManageVO
	 * @return 권한정보 IgrAuthorManageVO
	 * @exception Exception
     * @see AUTHOR_ID, SYS_ID, SYS_NM, AUTHOR_CODE, AUTHOR_NM, AUTHOR_DC, REGIST_DT, REGISTER_ID
     * @see REGISTER_IP, UPDT_DT, UPDTUSR_ID, UPPER_AUTHOR_NM
	 * @see TABLE NAME : TN_AUTHOR
     */ 
	public IgrAuthorManageVO selectIam(IgrAuthorManageVO igrAuthorManageVO) throws Exception;

	/** 
     * 권한정보를 등록한다.
	 * @param igrAuthorManageVO IgrAuthorManageVO
	 * @exception Exception
     * @see AUTHOR_ID, SYS_ID, AUTHOR_CODE, AUTHOR_NM, AUTHOR_DC, REGIST_DT, REGISTER_ID, REGISTER_IP
     * @see UPDT_DT, UPDTUSR_ID, UPPER_AUTHOR_ID
	 * @see TABLE NAME : TN_AUTHOR
     */ 
	public void insertIam(IgrAuthorManageVO authorManageVO) throws Exception;

	/** 
     * 권한정보를 삭제한다.
	 * @param igrAuthorManageVO IgrAuthorManageVO
	 * @exception Exception
     * @see AUTHOR_ID
	 * @see TABLE NAME : TN_AUTHOR
     */ 
	public void deleteAuthor(IgrAuthorManageVO igrAuthorManageVO) throws Exception;

	/** 
     * 권한정보를 변경한다.
	 * @param igrAuthorManageVO IgrAuthorManageVO
	 * @exception Exception
     * @see AUTHOR_ID
	 * @see TABLE NAME : TN_AUTHOR
     */ 
	public void updateAuthor(IgrAuthorManageVO igrAuthorManageVO) throws Exception;

	/** 
     * 콤보박스로 시스템목록을 조회한다.
	 * @param igrAuthorManageVO IgrAuthorManageVO
	 * @return 시스템목록 Map
	 * @exception Exception
     * @see SYS_ID, SYS_NM
	 * @see TABLE NAME : TN_AUTHOR
     */ 
	@SuppressWarnings("unchecked")
	public Map selectSysComboMap(IgrAuthorManageVO authorManageVO) throws Exception;

	/** 
     * 콤보박스로 시스템에 해당하는 권한목록을 조회한다.
	 * @param igrAuthorManageVO IgrAuthorManageVO
	 * @return 권한목록 List<AuthorManageVO>
	 * @exception Exception
     * @see AUTHOR_ID, AUTHOR_NM
	 * @see TABLE NAME : TN_AUTHOR, TN_SYSTEM
     */ 
	@SuppressWarnings("unchecked")
	public List selectAuthorCombo(IgrAuthorManageVO authorManageVO) throws Exception;

	/** 
     * 권한아이디를 생성한다.
	 * @param igrAuthorManageVO IgrAuthorManageVO
	 * @return 권한아이디 String
	 * @exception Exception
     * @see AUTHOR_ID
	 * @see TABLE NAME : TN_AUTHOR
     */ 
	public String authorIdGeneration(IgrAuthorManageVO authorManageVO) throws Exception;
	
	/** 
     * 사용자의권한정보를 등록한다.
	 * @param igrUserAuthorManageVO IgrUserAuthorManageVO
	 * @exception Exception
     * @see USER_ID, AUTHOR_ID, USER_AUTHOR_ID, REGIST_DT, REGISTER_ID, REGISTER_IP, UPDT_DT, UPDTUSR_ID
	 * @see TABLE NAME : TN_USER_AUTHOR
     */ 
	public void insertUserAuthor(IgrUserAuthorManageVO igrUserAuthorManageVO) throws Exception;

	/** 
     * 사용자의권한정보를 삭제한다.
	 * @param igrUserAuthorManageVO IgrUserAuthorManageVO
	 * @exception Exception
     * @see AUTHOR_ID
	 * @see TABLE NAME : TN_USER_AUTHOR
     */ 
	public void deleteUserAuthor(IgrUserAuthorManageVO igrUserAuthorManageVO) throws Exception;

	/** 
     * 사용자의권한 목록을 조회한다.
	 * @param igrUserAuthorManageVO IgrUserAuthorManageVO
	 * @return 사용자권한목록 List
	 * @exception Exception
     * @see USER_ID, NM, USER_AUTHOR_ID, AUTHOR_ID, AUTHOR_KIND
	 * @see TABLE NAME : TN_USER_AUTHOR
     */ 
	@SuppressWarnings("unchecked")
	public List selectUserAuthorList(IgrUserAuthorManageVO igrUserAuthorManageVO) throws Exception;

    /** 
	 * 사용자의권한 목록의 총 갯수를 조회한다.
	 * @param igrUserAuthorManageVO IgrUserAuthorManageVO
	 * @return 조회한 목록의 총 갯수 int
	 * @exception Exception 
     * @see COUNT(*) totcnt 
	 * @see TABLE NAME : TN_USER_AUTHOR
	 */
	public int selectUserAuthorListTotCnt(IgrUserAuthorManageVO igrUserAuthorManageVO) throws Exception;

	/** 
     * 사용자의권한아이디를 생성한다.
	 * @param igrUserAuthorManageVO IgrUserAuthorManageVO
	 * @return 사용자권한아이디 int
	 * @exception Exception
     * @see USER_AUTHOR_ID
	 * @see TABLE NAME : TN_USER_AUTHOR
     */ 
	public int userAuthorIdGeneration(IgrUserAuthorManageVO authorManageVO) throws Exception;

	/** 
	 * 전체 사용자 목록을 조회한다.
	 * @param igrUserAuthorManageVO IgrUserAuthorManageVO
	 * @return 조회한 목록의 리스트 List
	 * @exception Exception 
     * @see USER_ID, NM
	 * @see TABLE NAME : TN_USER
	 */ 
	@SuppressWarnings("unchecked")
	public List selectAllUserAuthorList(IgrUserAuthorManageVO igrUserAuthorManageVO) throws Exception;

    /** 
	 * 전체사용자 목록의 총 갯수를 조회한다.
	 * @param igrUserAuthorManageVO IgrUserAuthorManageVO
	 * @return 조회한 목록의 총 갯수 int
	 * @exception Exception 
     * @see COUNT(*) totcnt 
	 * @see TABLE NAME : TN_USER
	 */
	public int selectAllUserAuthorListTotCnt(IgrUserAuthorManageVO igrUserAuthorManageVO) throws Exception;

	public int selectAuthorLevel(IgrAuthorManageVO igrAuthorManageVO) throws Exception;
	
	/** 
	 * 권한명을 중복 체크한다.
	 * @param igrAuthorManageVO IgrAuthorManageVO
	 * @return 체크결과 int
	 * @exception Exception 
     * @see COUNT(*) totcnt 
	 * @see TABLE NAME : TN_AUTHOR
	 */ 
	public int selectCheckAuthorNm(IgrAuthorManageVO authorManageVO) throws Exception;

	/** 
	 * 메뉴권한을 등록한다.
	 * @param MenuManageVO menuManageVO
	 * @exception Exception 
     * @see MENU_NO, AUTHOR_ID
	 * @see TABLE NAME : TN_MENU_AUTHOR
	 */ 
    public void insertMenuAuthor(MenuManageVO menuManageVO) throws Exception;

	/** 
	 * 사용자권한의 사용자권한아이디를 체크한다.
	 * @param IgrUserAuthorManageVO igrUserAuthorManageVO
	 * @return 체크결과 int
	 * @exception Exception 
     * @see COUNT(USER_AUTHOR_ID)
	 * @see TABLE NAME : TN_USER_AUTHOR
	 */ 
    public int selectUserAuthorIdChk(IgrUserAuthorManageVO igrAuthorManageVO) throws Exception;

	/** 
     * 사용자권한의 권한을 변경한다.
	 * @param IgrUserAuthorManageVO igrUserAuthorManageVO
     * @exception Exception 
     * @see AUTHOR_ID
	 * @see TABLE NAME : TN_USER_AUTHOR
     */
    public void updateUserAuthor(IgrUserAuthorManageVO igrUserAuthorManageVO) throws Exception;
    
    /** 
     * 권한변경시 메타시스템 권한 동기화
     * @exception Exception 
     */
	public void updateUmmAuthor() throws Exception;
}
