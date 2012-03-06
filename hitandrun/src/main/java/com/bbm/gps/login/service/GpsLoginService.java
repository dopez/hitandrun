package com.bbm.gps.login.service;

/**
 * 일반 로그인, 인증서 로그인을 처리하는 비즈니스 인터페이스 클래스
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
public interface GpsLoginService {
	
	/**
	 * 일반 로그인을 처리한다
	 * @param gpsLoginVO GpsLoginVO
	 * @return GpsLoginVO
	 * @exception Exception
	 */
    GpsLoginVO actionLogin(GpsLoginVO gpsLoginVO) throws Exception;
    
    /**
	 * 인증서 정보 조회
	 * @param gpsLoginVO GpsLoginVO
	 * @return GpsLoginVO
	 * @exception Exception
	 */
    public GpsLoginVO selectGpkiUser(GpsLoginVO gpsLoginVO) throws Exception;
    
    GpsLoginVO selectIdSearch(GpsLoginVO gpsLoginVO) throws Exception;
    
    int selectUserConfirm(GpsLoginVO gpsLoginVO) throws Exception;
    
    /** 
	 * 로그인 이력 테이블에 아이디가 존재하는지 조회
     * @param gpsLoginVO 로그인 정보를 가지고있는 VO
     * @return int
	 * @exception Exception 
     * @see TABLE NAME : TN_USER_LOGIN
	*/
	public GpsLoginVO selectUserLogin(GpsLoginVO gpsLoginVO) throws Exception;
	
	/** 
	 * 로그인 이력 테이블 등록
     * @param gpsLoginVO 로그인 정보를 가지고있는 VO
     * @return int
	 * @exception Exception 
     * @see TABLE NAME : TN_USER_LOGIN
	*/
	public void insertUserLogin(GpsLoginVO gpsLoginVO) throws Exception;
	
	/** 
	 * 로그인 이력 테이블 수정
     * @param gpsLoginVO 로그인 정보를 가지고있는 VO
     * @return int
	 * @exception Exception 
     * @see TABLE NAME : TN_USER_LOGIN
	*/
	public void updateUserLogin(GpsLoginVO gpsLoginVO) throws Exception;
    
}
