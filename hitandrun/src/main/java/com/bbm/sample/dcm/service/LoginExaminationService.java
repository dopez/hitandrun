package com.bbm.sample.dcm.service;

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
public interface LoginExaminationService {
	
	/**
	 * 일반 로그인을 처리한다
	 * @param gpsLoginVO GpsLoginVO
	 * @return GpsLoginVO
	 * @exception Exception
	 */
	LoginExaminationLoginVO actionLogin(LoginExaminationLoginVO interLoginVO) throws Exception;
    
}
