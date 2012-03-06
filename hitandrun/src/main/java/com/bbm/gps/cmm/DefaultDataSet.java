package com.bbm.gps.cmm;

import com.bbm.common.util.cas.service.EgovSessionCookieUtil;
import com.bbm.gps.cmm.service.GpsDefaultVO;
import com.bbm.gps.login.service.GpsSessionVO;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

/**
 * 포털 기본데이타 설정 클래스
 * <p><b>NOTE:</b>포털 기본데이타 설정을 하며, 컨트롤러 클래스에서 해당VO객체가 캐스팅 하여 사용 할수 있다. 
 * 해당 클래스 내부에서 호출되는 메소드들이 정의되어 있다
 * @author 통계포털 이관형
 * @since 2011.07.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.07.01  이관형          최초 생성
 *
 * </pre>
 */
@Component("defaultDataSet")
public class DefaultDataSet {
	
	/**
	 * 등록VO에 세션사용자의 등록자ID와 IP를 설정 한다.
	 * @param request HttpServletRequest
	 * @param gpsDefaultVO GpsDefaultVO
	 * @return gpsDefaultVO
	 * @throws Exception
	 */
	public GpsDefaultVO registSet(HttpServletRequest request, GpsDefaultVO gpsDefaultVO) throws Exception {
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	if(gpsSessionVO!=null){
    		// 1.등록자ID
			gpsDefaultVO.setRegisterId(gpsSessionVO.getUsrId());
    	}
		// 2.등록자IP
        String clientIP = "";
        clientIP = request.getHeader("Proxy-Client-Ip");
        if(clientIP == null){
            clientIP = request.getHeader("WL-Proxy-Client-IP");
            if(clientIP == null){
                clientIP = request.getHeader("X-Forwared-For");
                if(clientIP == null){
                    clientIP = request.getRemoteAddr();
                }
            }
        }
		gpsDefaultVO.setRegisterIp(clientIP);
		return gpsDefaultVO;
	}

	/**
	 * 수정VO에 세션사용자의 수정자ID와 IP를 설정 한다.
	 * @param request HttpServletRequest
	 * @param gpsDefaultVO GpsDefaultVO
	 * @return gpsDefaultVO
	 * @throws Exception
	 */
	public GpsDefaultVO updateSet(HttpServletRequest request, GpsDefaultVO gpsDefaultVO) throws Exception {
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	if(gpsSessionVO!=null){
    		// 1.수정자ID
    		gpsDefaultVO.setUpdtusrId(gpsSessionVO.getUsrId());
    	}
		// 2.등록자IP에 최종 수정자IP설정
        String clientIP = "";
        clientIP = request.getHeader("Proxy-Client-Ip");
        if(clientIP == null){
            clientIP = request.getHeader("WL-Proxy-Client-IP");
            if(clientIP == null){
                clientIP = request.getHeader("X-Forwared-For");
                if(clientIP == null){
                    clientIP = request.getRemoteAddr();
                }
            }
        }
		gpsDefaultVO.setRegisterIp(clientIP);
		return gpsDefaultVO;
	}
}
