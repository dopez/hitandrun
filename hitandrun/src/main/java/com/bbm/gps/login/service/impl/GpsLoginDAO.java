package com.bbm.gps.login.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.GpsAbstractDAO;
import com.bbm.gps.login.service.GpsLoginVO;

/**
 * 일반 로그인, 인증서 로그인을 처리하는 DAO 클래스
 * <p><b>NOTE:</b>
 * @author 포털통계 이관형
 * @since 2011.07.01
 * @version 1.0
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2011.07.01  이관형          최초 생성  
 *  
 *  </pre>
 */
@Repository("gpsLoginDAO")
public class GpsLoginDAO extends GpsAbstractDAO {
	
	/** log */
    protected static final Log LOG = LogFactory.getLog(GpsLoginDAO.class);
    
	/**
	 * 일반 로그인을 처리한다
	 * @param gpsLoginVO GpsLoginVO
	 * @return GpsLoginVO
	 * @exception Exception
	 */
    public GpsLoginVO actionLogin(GpsLoginVO gpsLoginVO) throws Exception {
    	return (GpsLoginVO)selectByPk("gpsLoginDAO.actionLogin", gpsLoginVO);
    }
    
    /**
	 * 인증서 정보 조회
	 * @param gpsLoginVO GpsLoginVO
	 * @return GpsLoginVO
	 * @exception Exception
	 */
    public GpsLoginVO selectGpkiUser(GpsLoginVO gpsLoginVO) throws Exception {
    	return (GpsLoginVO)selectByPk("gpsLoginDAO.selectGpkiUser", gpsLoginVO);
    }
    
	/**
	 * 일반 로그인을 처리한다
	 * @param gpsLoginVO GpsLoginVO
	 * @return GpsLoginVO
	 * @exception Exception
	 */
    public GpsLoginVO selectIdSearch(GpsLoginVO gpsLoginVO) throws Exception {
    	return (GpsLoginVO)selectByPk("gpsLoginDAO.selectIdSearch", gpsLoginVO);
    }

	/** 
	 * 비밀번호 재발급
     * @param UserManageVO 비밀번호 변경 정보를 가지고있는 VO
     * @return 
	 * @exception Exception 
     * @see TABLE NAME : TN_USER
	*/
	public int selectUserConfirm(GpsLoginVO gpsLoginVO) throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("gpsLoginDAO.selectUserConfirm", gpsLoginVO);
		
	}
	
	/** 
	 * 로그인 이력 테이블에 아이디가 존재하는지 조회
     * @param gpsLoginVO 로그인 정보를 가지고있는 VO
     * @return int
	 * @exception Exception 
     * @see TABLE NAME : TN_USER_LOGIN
	*/
	public GpsLoginVO selectUserLogin(GpsLoginVO gpsLoginVO) throws Exception {
		return (GpsLoginVO)super.selectByPk("gpsLoginDAO.selectUserLogin", gpsLoginVO);
	}
	
	/** 
	 * 로그인 이력 테이블 등록
     * @param gpsLoginVO 로그인 정보를 가지고있는 VO
     * @return int
	 * @exception Exception 
     * @see TABLE NAME : TN_USER_LOGIN
	*/
	public void insertUserLogin(GpsLoginVO gpsLoginVO) throws Exception {
		super.insert("gpsLoginDAO.insertUserLogin", gpsLoginVO);
	}
	
	/** 
	 * 로그인 이력 테이블 수정
     * @param gpsLoginVO 로그인 정보를 가지고있는 VO
     * @return int
	 * @exception Exception 
     * @see TABLE NAME : TN_USER_LOGIN
	*/
	public void updateUserLogin(GpsLoginVO gpsLoginVO) throws Exception {
		super.update("gpsLoginDAO.updateUserLogin", gpsLoginVO);
	}

}
