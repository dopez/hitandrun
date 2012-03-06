package com.bbm.gps.login.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import com.bbm.gps.login.service.GpsLoginService;
import com.bbm.gps.login.service.GpsLoginVO;

/**
 * 일반 로그인, 인증서 로그인을 처리하는 비즈니스 구현 클래스
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
@Service("gpsLoginService")
public class GpsLoginServiceImpl extends AbstractServiceImpl implements
        GpsLoginService {

    @Resource(name="gpsLoginDAO")
    private GpsLoginDAO gpsLoginDAO;

    /**
	 * 일반 로그인을 처리한다
	 * @param vo GpsLoginVO
	 * @return GpsLoginVO
	 * @exception Exception
	 */
    public GpsLoginVO actionLogin(GpsLoginVO vo) throws Exception {
    	GpsLoginVO loginVO = new GpsLoginVO();
    	loginVO = (GpsLoginVO)this.gpsLoginDAO.actionLogin(vo);
    	return loginVO;
    }
    
    /**
	 * 인증서 정보 조회
	 * @param gpsLoginVO GpsLoginVO
	 * @return GpsLoginVO
	 * @exception Exception
	 */
    public GpsLoginVO selectGpkiUser(GpsLoginVO gpsLoginVO) throws Exception {
    	return (GpsLoginVO)this.gpsLoginDAO.selectGpkiUser(gpsLoginVO);
    }
    
    public GpsLoginVO selectIdSearch(GpsLoginVO gpsLoginVO) throws Exception {
    	return gpsLoginDAO.selectIdSearch(gpsLoginVO);
    }
    
    public int selectUserConfirm(GpsLoginVO gpsLoginVO) throws Exception {
    	return gpsLoginDAO.selectUserConfirm(gpsLoginVO);
    }
    
    /** 
	 * 로그인 이력 테이블에 아이디가 존재하는지 조회
     * @param gpsLoginVO 로그인 정보를 가지고있는 VO
     * @return int
	 * @exception Exception 
     * @see TABLE NAME : TN_USER_LOGIN
	*/
	public GpsLoginVO selectUserLogin(GpsLoginVO gpsLoginVO) throws Exception {
		return (GpsLoginVO)this.gpsLoginDAO.selectUserLogin(gpsLoginVO);
	}
	
	/** 
	 * 로그인 이력 테이블 등록
     * @param gpsLoginVO 로그인 정보를 가지고있는 VO
     * @return int
	 * @exception Exception 
     * @see TABLE NAME : TN_USER_LOGIN
	*/
	public void insertUserLogin(GpsLoginVO gpsLoginVO) throws Exception {
		this.gpsLoginDAO.insertUserLogin(gpsLoginVO);
	}
	
	/** 
	 * 로그인 이력 테이블 수정
     * @param gpsLoginVO 로그인 정보를 가지고있는 VO
     * @return int
	 * @exception Exception 
     * @see TABLE NAME : TN_USER_LOGIN
	*/
	public void updateUserLogin(GpsLoginVO gpsLoginVO) throws Exception {
		this.gpsLoginDAO.updateUserLogin(gpsLoginVO);
	}
    
}
