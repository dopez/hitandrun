package com.bbm.gps.user.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.GpsAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import com.bbm.gps.adm.org.service.OrgManageVO;
import com.bbm.gps.user.service.UserRegisterVO;

/** 
 * 회원가입에 대한 데이터 접근 클래스를 정의한다
 * <p><b>NOTE:</b> 넘어온 요청에 대해 DB작업을 수행하는 메소드들의 집합
 * DB에 직접 접근하며 쿼리문에 적용할 parameter를 보내주거나 단순 쿼리 실행을 하도록 호출한다
 * select, update, delete 함수를 사용하며 쿼리아이디와 parameter를 넘긴다
 * @author 범정부통계포털 이관형 
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
@Repository("userRegisterDAO")
public class UserRegisterDAO extends GpsAbstractDAO {

	
	/** 
     * userRegisterVO VO에 담겨있는 항목을 DB에 insert 
     * @param userRegisterVO  insert할 항목에 대한 사용자 정보를 담고있는 VO 
     * @exception Exception 
     * @see TABLE NAME : TN_USER
     */
	public void insertUser(UserRegisterVO userRegisterVO) {
		 insert("UserManageDAO.insertUser", userRegisterVO);
	}
	
	
	/**
     * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
     * @param checkId 중복체크대상 아이디
     * @return int 사용가능여부(아이디 사용회수 )
     */
    public int checkIdDplct(String checkId){
        return (Integer)getSqlMapClientTemplate().queryForObject("userManageDAO.checkIdDplct_S", checkId);
    }
	
    @SuppressWarnings("unchecked")
    public List<EgovMap> selectOrgCombo(OrgManageVO orgManageVO) throws Exception {
    	return list("UserManageDAO.selectOrgList", orgManageVO);
    }
    
    @SuppressWarnings("unchecked")
    public List<EgovMap> selectDeptCombo(OrgManageVO orgManageVO) throws Exception {
    	return list("UserManageDAO.selectDeptList", orgManageVO);
    }
    
    /** 
     * userManageVO 수정할 대상 게시물ID에 해당하는 행을 업데이트 
     * @param userManageVO  업데이트 항목들에 대한 정보를 가지고있는 VO
     * @exception Exception 
	 * @see TABLE NAME : 
     */
	public void updateUser(UserRegisterVO userRegisterVO) throws Exception {
        update("UserManageDAO.updateUserInfo", userRegisterVO);
	}
	
	/** 
	 * 중복가입체크
     * @param userRegisterVO 
     * @exception Exception 
	 * @see TABLE NAME : TN_USER
     */
	public int duplicateJoinChk(UserRegisterVO userRegisterVO) throws Exception {
        return (Integer)super.selectByPk("UserManageDAO.duplicateJoinChk", userRegisterVO);
	}
	
	/** 
     * 인증서 등록 처리
     * @param userManageVO  업데이트 항목들에 대한 정보를 가지고있는 VO
     * @exception Exception 
	 * @see TABLE NAME : 
     */
	public void updateGpki(UserRegisterVO userRegisterVO) throws Exception {
        super.update("UserManageDAO.updateGpki", userRegisterVO);
	}
}
