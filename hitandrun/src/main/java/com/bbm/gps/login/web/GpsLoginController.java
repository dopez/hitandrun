package com.bbm.gps.login.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import com.bbm.common.cmm.EgovMessageSource;
import com.bbm.common.login.service.EgovLoginPolicyService;
import com.bbm.common.util.cas.service.EgovSessionCookieUtil;
import com.bbm.common.util.sim.service.EgovFileScrty;
import com.bbm.gps.adm.menu.service.MenuManageService;
import com.bbm.gps.adm.menu.service.MenuManageVO;
import com.bbm.gps.adm.user.service.UserManageService;
import com.bbm.gps.login.service.GpsLoginService;
import com.bbm.gps.login.service.GpsLoginVO;
import com.bbm.gps.login.service.GpsSessionVO;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*import com.gpki.gpkiapi.cert.X509Certificate;
import com.gpki.gpkiapi.exception.GpkiApiException;
import com.gpki.secureweb.GPKISecureWEBException;
import com.gpki.servlet.GPKIHttpServletRequest;
import com.gpki.servlet.GPKIHttpServletResponse;*/
import com.nanum.xf.xfutil.XFCrypt;

/**
 * 일반 로그인, 인증서 로그인을 처리하는 컨트롤러 클래스
 * <p><b>NOTE:</b>
 * @author 포털통계 이관형
 * @since 2011.07.01
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2011.07.01  이관형          최초 생성
 * 
 * </pre>
 */
@Controller
public class GpsLoginController {

	/** EgovLoginService */
	@Resource(name = "gpsLoginService")
	private GpsLoginService gpsLoginService;

	/** EgovLoginService */
	@Resource(name = "userManageService")
	private UserManageService userManageService;

	/** EgovLoginPolicyService */
	@Resource(name = "egovLoginPolicyService")
	EgovLoginPolicyService egovLoginPolicyService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** menuService */
	@Resource(name="menuManageService")
	protected MenuManageService menuManageService;
	
	/** propertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
	/** LOG */ 
	protected Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 로그인 화면 호출
	 * @param gpsLoginVO - 로그인후 이동할 URL이 담긴 GpsLoginVO
	 * @param model ModelMap
	 * @return 로그인 페이지 "gps/login/gpsLoginUsr"
	 * @exception Exception
	 */
	@RequestMapping(value = "/gps/login/gpsLoginUsr.do")
	public String loginUsrView(
			@ModelAttribute("gpsLoginVO") GpsLoginVO gpsLoginVO,
			@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
			@RequestParam(value="msg", required=false) String msg,
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model
			) throws Exception {
		boolean gpkiLogin = false;
		//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	if(gpsSessionVO!=null){
    		return "redirect:/gps/index.do?msg="+msg;
    	}else{
    		//내부망
    		if(propertiesService.getString("was.LcInfo").equals("naraint")||propertiesService.getString("was.LcInfo").equals("naradev")){
    			/*GPKIHttpServletResponse gpkiresponse = null;
    		    GPKIHttpServletRequest gpkirequest = null;
    		    try{
    		        gpkiresponse = new GPKIHttpServletResponse(response);
    		        gpkirequest = new GPKIHttpServletRequest(request);
    		        gpkiresponse.setRequest(gpkirequest);
    		        model.addAttribute("challenge", gpkiresponse.getChallenge());
    		        if(msg!=null){
    		    		model.addAttribute("message","유효한 인증서가 아닙니다.");
    		    	}
    		    }catch(Exception e){
    		    	if(logger.isErrorEnabled()){
    		    		logger.error(e);
    		    	}
    		    }*/
    		    gpkiLogin = true;
    		}
            
    		
    		//아이디저장 쿠키처리
    		String userId = EgovSessionCookieUtil.getCookie(request,"userId");
    		if(userId != null){
    			gpsLoginVO.setUserId(userId);
    			gpsLoginVO.setSaveId("Y");
    		}
    		
    		//returnUrl
    		gpsLoginVO.setReturnUrl("/gps/login/gpsLoginUsr.do");
    		//처리 메세지
    		String message = "";
    		if(msg != null){
    			if(msg.equals("0")){
    				message = "이미 등록 되어있습니다.";
    			}else if(msg.equals("1")){//로그인실패
    				message = "유효한 인증서가 아닙니다.";
    			}else if(msg.equals("2")){//로그인실패
    				message = this.egovMessageSource.getMessage("gps.fail.login");
    			}else if(msg.equals("4")){//로그인제한
    				message = this.egovMessageSource.getMessage("gps.login.limit.excess");
    			}else if(msg.equals("5")){//미승인 회원
    				message = this.egovMessageSource.getMessage("gps.fail.approval");
    			}
    		}
			model.addAttribute("navi", this.menuManageService.selectNavi(menuManageVO));
			model.addAttribute("message",message);
			model.addAttribute("gpkiLogin", gpkiLogin);
    		return "gps/login/gpsLoginUsr";
    	}
	}

	/**
	 * 일반 로그인을 처리한다
	 * @param gpsLoginVO - 로그인후 이동할 URL이 담긴 GpsLoginVO
	 * @param request HttpServletRequest
	 * @param model ModelMap
	 * @return 로그인결과(세션정보) "gps/login/gpsLoginUsr"
	 * @exception Exception
	 */
	@RequestMapping(value = "/gps/login/actionLogin.do")
	public String actionLogin(
			@ModelAttribute("gpsLoginVO") GpsLoginVO gpsLoginVO,
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model) throws Exception {
		String returnUrl = gpsLoginVO.getReturnUrl();
		String msg = "";	//메시지
		String password = "";
		String gpsPassword = "";
		String ubisPassword = "";
		String param = "?menuId="+(gpsLoginVO.getMenuId()!=null?gpsLoginVO.getMenuId():"")+"&leftMenuId="+(gpsLoginVO.getLeftMenuId()!=null?gpsLoginVO.getLeftMenuId():"");
		if(!gpsLoginVO.getBbsId().isEmpty()){
			param += "&bbsId="+gpsLoginVO.getBbsId();
		}
		if(gpsLoginVO.getBbsSn() > 0){
			param += "&bbsSn="+gpsLoginVO.getBbsSn();
		}
		gpsPassword = gpsLoginVO.getLoginSe()!=null&&gpsLoginVO.getLoginSe().equals("gpki")?gpsLoginVO.getPassword():EgovFileScrty.encryptPassword(gpsLoginVO.getPassword());//일반비밀번호
		ubisPassword = gpsLoginVO.getLoginSe()!=null&&gpsLoginVO.getLoginSe().equals("gpki")?gpsLoginVO.getPassword():XFCrypt.crypt(gpsLoginVO.getPassword());//UBIS 비밀번호
		boolean loginAt = false;
		boolean loginLock = false;
		//로그인 이력 조회
		GpsLoginVO userLoginVO = new GpsLoginVO();
		userLoginVO = (GpsLoginVO)this.gpsLoginService.selectUserLogin(gpsLoginVO);
		if(userLoginVO!=null){
			if(userLoginVO.getLoginLockTy()!=null && !userLoginVO.getLoginLockTy().isEmpty()){
				if(userLoginVO.getLoginLockTy().equals("Y")){
					loginLock = true;
					msg = "4";
				}
			}
		}
		// 1. 일반 로그인 처리
		GpsLoginVO resultVO = new GpsLoginVO();
		resultVO = (GpsLoginVO)this.gpsLoginService.actionLogin(gpsLoginVO);
		if(resultVO!=null){
			if(resultVO.getTrnsferInfo()!=null && !resultVO.getTrnsferInfo().isEmpty()){//회원구분확인
				password = resultVO.getTrnsferInfo().equals("U")?ubisPassword:gpsPassword;
			}else{
				password = gpsPassword;
			}
			if(resultVO.getPassword().equals(password)){//비밀번호확인
				if(resultVO.getApprovalAt()!=null && resultVO.getApprovalAt().equals("Y")){//승인여부확인
					loginAt = true;
				}else{
					msg = "5";
				}
			}else{
				msg = "2";
			}
			
		}else{
			msg = "2";
		}
		// TODO 개발서버 테스트를 위한  패스워드 미검증 추가
		if(resultVO!=null && "naradev".equals(propertiesService.getString("was.LcInfo"))){
			loginAt = true;
			msg = "1";
		}
		if (loginAt) {//로그인성공처리
			if(!loginLock){
				//세션 생성
				GpsSessionVO gpsSessionVO = new GpsSessionVO();
				gpsSessionVO.setOrgId(resultVO.getOrgId());//기관코드
				gpsSessionVO.setOrgNm(resultVO.getOrgNm());//기관명
				gpsSessionVO.setUsrId(resultVO.getUserId());//사용자ID
				gpsSessionVO.setUsrNm(resultVO.getNm());//사용자명
				gpsSessionVO.setSysUserAt(resultVO.getSysUserAt());//시스템사용자여부
				gpsSessionVO.setTrnsferInfo(resultVO.getTrnsferInfo()!=null?resultVO.getTrnsferInfo():"K");//회원구분
				gpsSessionVO.setAuthorIdArray(resultVO.getAuthorIdArray());//권한 ID 배열
				EgovSessionCookieUtil.setSessionAttribute(request,"gpsSessionVO",gpsSessionVO);	
				
				//아이디저장 쿠키처리
				Cookie cookie = new Cookie("userId",gpsLoginVO.getUserId().replaceAll("\r", "").replaceAll("\n", ""));
				cookie.setPath("/");
				if(gpsLoginVO.getSaveId()!=null && gpsLoginVO.getSaveId().equals("Y")){
					cookie.setMaxAge(3600*24*7);//유효기간 7 일
					cookie.setSecure(true);//httponly 처리
					cookie.setVersion(0);
				}else{
					cookie.setMaxAge(0);
				}
				response.addCookie(cookie);
				
				//로그인 실패횟수 초기화
				gpsLoginVO.setLoginFailrCo(0);
				
				//로그인 횟수 증가
				gpsLoginVO.setLoginCo(userLoginVO != null?userLoginVO.getLoginCo()+1:1);
			}
		}else {
			if(resultVO!=null && !loginLock){
				gpsLoginVO.setLoginFailrCo(userLoginVO != null?userLoginVO.getLoginFailrCo()+1:1);
				gpsLoginVO.setLoginLockTy(gpsLoginVO.getLoginFailrCo() > 4?"Y":"N");
				//로그인 횟수
				gpsLoginVO.setLoginCo(userLoginVO != null?userLoginVO.getLoginCo():0);
			}
		}
		if(resultVO!=null && !loginLock){
			//로그인 이력 저장
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
			gpsLoginVO.setLoginLastIp(clientIP);
			if(userLoginVO != null){
				this.gpsLoginService.updateUserLogin(gpsLoginVO);
			}else{
				this.gpsLoginService.insertUserLogin(gpsLoginVO);
			}
		}
		
		param += "&msg="+msg;
		return "redirect:"+returnUrl+param;
	}
	
	/**
	 * gpki로그인처리
	 * @param gpsLoginVO - 로그인후 이동할 URL이 담긴 GpsLoginVO
	 * @param model ModelMap
	 * @return 로그인처리 "gps/login/crtfctLogin"
	 * @exception Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/gps/login/gpkiLogin.do")
	public String gpkiLogin(
			@ModelAttribute("gpsLoginVO") GpsLoginVO gpsLoginVO,
			HttpServletRequest request,
    		HttpServletResponse response,
			ModelMap model
			) throws Exception {
		String menuId = gpsLoginVO.getMenuId();
		String leftMenuId = gpsLoginVO.getLeftMenuId();
		String bbsId = gpsLoginVO.getBbsId();
		int bbsSn = gpsLoginVO.getBbsSn();
		String returnUrl="redirect:/gps/login/gpsLoginUsr.do?msg=1&menuId="+menuId+"&leftMenuId="+leftMenuId+"&bbsId="+bbsId+"&bbsSn="+bbsSn;
		//내부망일때만 처리
    	
		/*
		if(propertiesService.getString("was.LcInfo").equals("naraint")||propertiesService.getString("was.LcInfo").equals("naradev")){
			GPKIHttpServletResponse gpkiresponse = null;
		    GPKIHttpServletRequest gpkirequest = null;
			try{
		        gpkiresponse =new GPKIHttpServletResponse(response);
		        gpkirequest = new GPKIHttpServletRequest(request);
		        if(gpkirequest!=null){
			        gpkiresponse.setRequest(gpkirequest);
			        X509Certificate cert = null; 
			        byte[] signData = null;
			        byte[] privatekey_random = null;
			        String signType = "";
			        String queryString = "";
			        cert = gpkirequest.getSignerCert(); 
			        String subDN = cert.getSubjectDN();
			        java.math.BigInteger b = cert.getSerialNumber();
			        b.toString();
			        int message_type =  gpkirequest.getRequestMessageType();
			        if( message_type == gpkirequest.ENCRYPTED_SIGNDATA || message_type == gpkirequest.LOGIN_ENVELOP_SIGN_DATA ||
			            message_type == gpkirequest.ENVELOP_SIGNDATA || message_type == gpkirequest.SIGNED_DATA){
			            signData = gpkirequest.getSignedData();
						// random획득
			            privatekey_random   = gpkirequest.getSignerRValue();
			
			            signType = gpkirequest.getSignType();
			        }
		            queryString = gpkirequest.getQueryString();
			        if(subDN!=null && !subDN.isEmpty()){
			        	gpsLoginVO.setGpki(subDN);
			        	GpsLoginVO vo = new GpsLoginVO();
			        	vo = (GpsLoginVO)this.gpsLoginService.selectGpkiUser(gpsLoginVO);
			        	if(vo != null){
			        		queryString+="&userId="+vo.getUserId()+"&password="+vo.getPassword()+"&loginSe=gpki";
							returnUrl = "redirect:/gps/login/actionLogin.do?"+queryString;
			        	}
			        }
		        }
			}catch(GPKISecureWEBException e){
				if(logger.isErrorEnabled()){
					logger.error("GPKISecureWEBException:"+e);
				}
		    }catch(GpkiApiException e){
		    	if(logger.isErrorEnabled()){
					logger.error("GpkiApiException:"+e);
				}
		    }catch (Exception e) {
		    	if(logger.isErrorEnabled()){
					logger.error("Exception:"+e);
				}
			}
		}
		*/
    	return returnUrl;
	}
	
	/**
	 * 로그아웃처리
	 * @param request 
	 * @param model ModelMap
	 * @return 로그아웃처리 "forward:/gps/login/gpsLoginUsr.do"
	 * @exception Exception
	 */
	@RequestMapping(value = "/gps/login/actionLogout.do")
	public String actionLogout(
			@ModelAttribute("gpsLoginVO") GpsLoginVO gpsLoginVO,
			HttpServletRequest request,
			ModelMap model
			) throws Exception {
			EgovSessionCookieUtil.invalidateSession(request);
			String returnUrl = gpsLoginVO.getReturnUrl();
			if (returnUrl.isEmpty()) {
				return "redirect:"+ "/gps/index.do";
			}
			return "redirect:"+returnUrl;
	}
	
	
	/**
	 * 아이디/비밀번호찾기
	 * @param gpsLoginVO - 로그인후 이동할 URL이 담긴 GpsLoginVO
	 * @param model ModelMap
	 * @return 아이디/비밀번호찾기 페이지 "gps/login/idPwdSearch"
	 * @exception Exception
	 */
	@RequestMapping(value = "/gps/login/idSearch.do")
	public String idSearch(
			@ModelAttribute("gpsLoginVO") GpsLoginVO gpsLoginVO,
			ModelMap model
			) throws Exception {
		return "gps/login/idSearch";
	}

	/**
	 * 아이디/비밀번호찾기
	 * @param gpsLoginVO - 로그인후 이동할 URL이 담긴 GpsLoginVO
	 * @param model ModelMap
	 * @return 아이디/비밀번호찾기 페이지 "gps/login/idPwdSearch"
	 * @exception Exception
	 */
	@RequestMapping(value = "/gps/login/selectIdSearch.do")
	public String selectIdSearch(
			@ModelAttribute("gpsLoginVO") GpsLoginVO gpsLoginVO,
			ModelMap model
			) throws Exception {
    	if (null == gpsLoginVO.getTrnsferInfo() || !gpsLoginVO.getTrnsferInfo().equals("U")) {
    		String email = gpsLoginVO.getEmail();
    		gpsLoginVO.setEmail(String.valueOf(new StringBuilder().append(EgovFileScrty.encode(email.substring(0, email.indexOf("@")))).append(email.substring(email.indexOf("@"), email.length()))));
    	}
		GpsLoginVO rstGpsLoginVO = gpsLoginService.selectIdSearch(gpsLoginVO);
		if (null == rstGpsLoginVO) {
			model.addAttribute("message", egovMessageSource.getMessage("gps.idsearch.fail"));
		} else {
			gpsLoginVO.setUserId(rstGpsLoginVO.getUserId());
		}
	   	if (null == gpsLoginVO.getTrnsferInfo() || !gpsLoginVO.getTrnsferInfo().equals("U")) {
    		String email = gpsLoginVO.getEmail();
    		gpsLoginVO.setEmail(String.valueOf(new StringBuilder().append(EgovFileScrty.decode(email.substring(0, email.indexOf("@")))).append(email.substring(email.indexOf("@"), email.length()))));
    	}
		model.addAttribute("gpsLoginVO", gpsLoginVO);
		
		return "gps/login/idSearch";
	}

	/**
	 * 아이디/비밀번호찾기
	 * @param gpsLoginVO - 로그인후 이동할 URL이 담긴 GpsLoginVO
	 * @param model ModelMap
	 * @return 아이디/비밀번호찾기 페이지 "gps/login/idPwdSearch"
	 * @exception Exception
	 */
	@RequestMapping(value = "/gps/login/passwordReissue.do")
	public String passwordReissue(
			@ModelAttribute("gpsLoginVO") GpsLoginVO gpsLoginVO,
			ModelMap model
			) throws Exception {
		return "gps/login/passwordReissue";
	}

	/**
	 * 아이디/비밀번호찾기
	 * @param gpsLoginVO - 로그인후 이동할 URL이 담긴 GpsLoginVO
	 * @param model ModelMap
	 * @return 아이디/비밀번호찾기 페이지 "gps/login/idPwdSearch"
	 * @exception Exception
	 */
	@RequestMapping(value = "/gps/login/userConfirm.do")
	public String userConfirm(
			@ModelAttribute("gpsLoginVO") GpsLoginVO gpsLoginVO,
			ModelMap model
			) throws Exception {
		
		String rst;
		
    	if (null == gpsLoginVO.getTrnsferInfo() || !gpsLoginVO.getTrnsferInfo().equals("U")) {
    		String email = gpsLoginVO.getEmail();
    		gpsLoginVO.setEmail(String.valueOf(new StringBuilder().append(EgovFileScrty.encode(email.substring(0, email.indexOf("@")))).append(email.substring(email.indexOf("@"), email.length()))));
    	}
    	
		if (gpsLoginService.selectUserConfirm(gpsLoginVO) > 0){
			model.addAttribute("message", egovMessageSource.getMessage("gps.userconfirm.success"));
			rst = "gps/login/userConfirm";
		} else {
			model.addAttribute("message", egovMessageSource.getMessage("gps.userconfirm.fail"));
			rst = "gps/login/passwordReissue";
		}

    	if (null == gpsLoginVO.getTrnsferInfo() || !gpsLoginVO.getTrnsferInfo().equals("U")) {
    		String email = gpsLoginVO.getEmail();
    		gpsLoginVO.setEmail(String.valueOf(new StringBuilder().append(EgovFileScrty.decode(email.substring(0, email.indexOf("@")))).append(email.substring(email.indexOf("@"), email.length()))));
    	}
    	
		model.addAttribute("gpsLoginVO", gpsLoginVO);
		
		return rst;
	}

	/**
	 * 아이디/비밀번호찾기
	 * @param gpsLoginVO - 로그인후 이동할 URL이 담긴 GpsLoginVO
	 * @param model ModelMap
	 * @return 아이디/비밀번호찾기 페이지 "gps/login/idPwdSearch"
	 * @exception Exception
	 */
	@RequestMapping(value = "/gps/login/passwdReissue.do")
	public String passwdReissue(
			@ModelAttribute("gpsLoginVO") GpsLoginVO gpsLoginVO,
			ModelMap model
			) throws Exception {

    	// 1. 입력한 비밀번호를 암호화한다.
		gpsLoginVO.setPassword(EgovFileScrty.encryptPassword(gpsLoginVO.getPassword()));
		userManageService.updatePassword(gpsLoginVO);
		
		model.addAttribute("gpsLoginVO", gpsLoginVO);
		
		return "gps/login/passwordResult";
	}
}