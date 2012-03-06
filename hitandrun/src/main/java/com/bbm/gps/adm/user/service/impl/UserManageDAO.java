package com.bbm.gps.adm.user.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.GpsAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import com.bbm.gps.adm.menu.service.SystemVO;
import com.bbm.gps.adm.org.service.OrgManageVO;
import com.bbm.gps.adm.user.service.UserManageVO;
import com.bbm.gps.adm.user.service.ZipVO;

/** 
 * 사용자관리에 대한 데이터 접근 클래스를 정의한다
 * <p><b>NOTE:</b> 넘어온 요청에 대해 DB작업을 수행하는 메소드들의 집합
 * DB에 직접 접근하며 쿼리문에 적용할 parameter를 보내주거나 단순 쿼리 실행을 하도록 호출한다
 * select, update, delete 함수를 사용하며 쿼리아이디와 parameter를 넘긴다
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
 *   2011.05.15     이관형      최초 생성 
 *   2011.07.21     이진우      사용자유형을 권한유형으로 변경
 * 
 * </pre> 
 */
@Repository("userManageDAO")
public class UserManageDAO extends GpsAbstractDAO {
	
	/** 
	 * @param AuthorManageVO 권한정보VO
	 * 사용자유형(권한)에 대하여 목록 조회
	 * @return List 조회한 사용자유형(권한)목록의 리스트
	 * @exception Exception 
	 * @see TABLE NAME : TN_AUTHOR
	 */ 
    @SuppressWarnings("unchecked")
	public List selectUserTypeList(SystemVO systemManageVO) throws Exception {
        return list("UserManageDAO.selectUserTypeList", systemManageVO);
    }

    /** 
	 * userManageVO 전체 사용자 조회 총 갯수를 조회한다.
	 * @param  userManageVO
	 * @return int 조회한 목록의 리스트
	 * @exception Exception 
     * @see COUNT(*) totcnt 
	 */
    public int selectUserTypeListTotCnt() throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("UserManageDAO.selectUserTypeCnt", null);
    }
    
	/** 
     * userManageVO 수정할 대상 게시물ID에 해당하는 행을 업데이트 
     * @param userManageVO  업데이트 항목들에 대한 정보를 가지고있는 VO
     * @exception Exception 
	 * @see TABLE NAME : 
     */
	public void updateAdmin(UserManageVO userManageVO) throws Exception {
        update("UserManageDAO.updateUser", userManageVO);
	}

	/** 
	 * userManageVO 게시물 ID에 대한 행삭제(게시물삭제) 
	 * @param userManageVO  삭제 항목에 대한 구분자 
	 * @exception Exception 
	 * @see TABLE NAME : 
 	 */ 
	public void deleteUser(UserManageVO userManageVO) throws Exception {
		delete("UserManageDAO.deleteUser", userManageVO);
	}

	/** 
     * userManageVO VO에 담겨있는 항목을 DB에 insert 
     * @param userManageVO  insert할 항목에 대한 정보를 담고있는 VO 
     * @exception Exception 
	 * @see TABLE NAME : 
     */
	public void insertUser(UserManageVO userManageVO) throws Exception {
        insert("UserManageDAO.insertUser", userManageVO);
	}
	
	/** 
     * userManageVO 수정할 대상 게시물ID에 해당하는 행을 업데이트 
     * @param userManageVO  업데이트 항목들에 대한 정보를 가지고있는 VO
     * @exception Exception 
	 * @see TABLE NAME : 
     */
	public void updateUser(UserManageVO userManageVO) throws Exception {
        update("UserManageDAO.updateUser", userManageVO);
	}

	/** 
	 * userManageVO 게시판목록 조회  
	 * @param userManageVO  조회조건
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
	 * @see TABLE NAME : 
	 */ 
	public UserManageVO selectUser(UserManageVO userManageVO) throws Exception {
		return (UserManageVO)selectByPk("UserManageDAO.selectUser", userManageVO);
	}

	/** 
	 * userManageVO 전체 사용자 조회  
	 * @param userManageVO  조회조건
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
	 * @see TABLE NAME : 
	 */ 
    @SuppressWarnings("unchecked")
	public List selectAllUserList(UserManageVO userManageVO) throws Exception {
        return list("UserManageDAO.selectAllUserList", userManageVO);
    }
    
    /** 
	 * userManageVO 선택된 사용자유형의 사용자목록 조회  
	 * @param userManageVO  조회조건
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
	 * @see TABLE NAME : 
	 */ 
    @SuppressWarnings("unchecked")
	public List selectUserList(UserManageVO userManageVO) throws Exception {
        return list("UserManageDAO.selectUserList", userManageVO);
    }
    
    /** 
	 * userManageVO 전체 사용자 조회 총 갯수를 조회한다.
	 * @param  userManageVO
	 * @return int 조회한 목록의 리스트
	 * @exception Exception 
     * @see COUNT(*) totcnt 
	 */
    public int selectAllUserListTotCnt(UserManageVO userManageVO) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("UserManageDAO.selectAllUserListTotCnt", userManageVO);
    }
    
    /** 
	 * userManageVO 선택된 사용자유형의 사용자목록 총 갯수를 조회한다.
	 * @param  userManageVO
	 * @return int 조회한 목록의 리스트
	 * @exception Exception 
     * @see COUNT(*) totcnt 
	 */
    public int selectUserListTotCnt(UserManageVO userManageVO) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("UserManageDAO.selectUserListTotCnt", userManageVO);
    }

    /**
     * 주어진 조건에 따른 공통코드를 불러온다.
     * 
     * @param whereMap 검색조건맵
     * @return orgMap 기관정보
     * @exception Exception
     * @see COUNT(*) totcnt 
     */
    @SuppressWarnings("unchecked")
	public Map selectOrgMap(Map whereMap) throws Exception {
    	// 조건이 필요할경우 orgManageVO을 맵으로 변경후 파라미터 요청
    	Map orgMap  = new  LinkedHashMap();
    	ArrayList orgList = (ArrayList) list("UserManageDAO.selectOrgList", whereMap);
    	for(int tmpcnt = 0; tmpcnt < orgList.size(); tmpcnt++)
		{
    		OrgManageVO _orgVO = (OrgManageVO)orgList.get(tmpcnt);
    		orgMap.put(_orgVO.getOrgId(), _orgVO.getOrgNm());		
		}
    	
    	return  orgMap;
    }

    /** 
	 * 사용자리스트에서 선택된 미승인 사용자의 승인처리 
	 * @param appUserIdList 승인할 사용자ID  리스트 
	 * @return 
	 * @exception Exception 
	 * @see TABLE NAME : TN_USER 
	*/ 
	public void appUserId(UserManageVO vo) {
		update("UserManageDAO.appUserId", vo);
	}

	/** 
	 * 우편번호 조회
     * @param ZipVO 우편번호 정보를 가지고있는 VO
     * @return 우편번호 리스트
	 * @exception Exception 
     * @see TABLE NAME : TN_ZIP
	*/
	@SuppressWarnings("unchecked")
	public List selectZipList(ZipVO searchVO) {
		return list("UserManageDAO.selectZipList", searchVO);
		
	}

	/** 
	 * 우편번호 목록 총 갯수
     * @param ZipVO 우편번호 정보를 가지고있는 VO
     * @return 우편번호 총 갯수
	 * @exception Exception 
     * @see TABLE NAME : TN_ZIP
	*/
	public int selectZipListTotCnt(ZipVO searchVO) {
		return (Integer)getSqlMapClientTemplate().queryForObject("UserManageDAO.selectZipListTotCnt", searchVO);
		
	}

	/** 
	 * 비밀번호 변경
     * @param UserManageVO 비밀번호 변경 정보를 가지고있는 VO
     * @return 
	 * @exception Exception 
     * @see TABLE NAME : TN_USER
	*/
	public int updatePassword(UserManageVO userManageVO) {
		return (Integer)update("UserManageDAO.updatePassword", userManageVO);
		
	}

    /**
     * 주어진 조건에 따른 공통코드를 불러온다.
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<EgovMap> selectDeptCombo(OrgManageVO orgManageVO) throws Exception {
    	return list("UserManageDAO.selectDeptList", orgManageVO);
    }

}
