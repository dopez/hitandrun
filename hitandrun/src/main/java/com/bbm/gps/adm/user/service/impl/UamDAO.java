package com.bbm.gps.adm.user.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.GpsAbstractDAO;
import com.bbm.gps.adm.user.service.UamVO;
import com.bbm.gps.adm.user.service.UserManageVO;

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
 * 
 * </pre> 
 */
@Repository("uamDAO")
public class UamDAO extends GpsAbstractDAO {
	
    /** 
	 * userManageVO 선택된 사용자유형의 사용자목록 조회  
	 * @param userManageVO  조회조건
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
	 * @see TABLE NAME : TN_USER
	 */ 
    @SuppressWarnings("unchecked")
	public List selectAbsnceUserList(UserManageVO userManageVO) throws Exception {
        return list("uamDAO.selectAbsnceUserList", userManageVO);
    }

    /** 
	 * userManageVO 전체 사용자 조회 총 갯수를 조회한다.
	 * @param  userManageVO
	 * @return int 조회한 목록의 리스트
	 * @exception Exception 
     * @see TABLE NAME : TN_USER
	 */
    public int selectAbsnceUserListTotCnt(UserManageVO userManageVO) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("uamDAO.selectAbsnceUserListTotCnt", userManageVO);
    }

    /** 
	 * userManageVO 선택된 사용자유형의 사용자목록 조회  
	 * @param userManageVO  조회조건
	 * @return List 조회한 목록의 리스트
	 * @exception Exception
	 * @see TABLE NAME : TN_USER , TN_USER_AUTHOR , TN_USER_ABSNCE 
	 */ 
    @SuppressWarnings("unchecked")
	public List selectTransferList(UserManageVO userManageVO) throws Exception {
        return list("uamDAO.selectTransferList", userManageVO);
    }

    /** 
	 * userManageVO 전체 사용자 조회 총 갯수를 조회한다.
	 * @param  userManageVO
	 * @return int 조회한 목록의 리스트 총 갯수
	 * @exception Exception 
     * @see TABLE NAME : TN_USER , TN_USER_AUTHOR , TN_USER_ABSNCE 
	 */
    public int selectTransferListTotCnt(UserManageVO userManageVO) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("uamDAO.selectTransferListTotCnt", userManageVO);
    }

	/** 
     * userManageVO VO에 담겨있는 항목을 DB에 insert 
     * @param userManageVO  insert할 항목에 대한 정보를 담고있는 VO 
     * @exception Exception 
	 * @see TABLE NAME : TN_USER_ABSNCE
     */
	public void insertUserAbsnce(UamVO uamVO) throws Exception {
        insert("uamDAO.insertUserAbsnce", uamVO);
	}

	/** 
	 * userManageVO 게시물 ID에 대한 행삭제(게시물삭제) 
	 * @param userManageVO  삭제 항목에 대한 구분자 
	 * @exception Exception 
	 * @see TABLE NAME : TN_USER_ABSNCE
 	 */ 
	public void deleteUserAbsnce(UamVO uamVO) throws Exception {
		delete("uamDAO.deleteUserAbsnce", uamVO);
	}
    
}
