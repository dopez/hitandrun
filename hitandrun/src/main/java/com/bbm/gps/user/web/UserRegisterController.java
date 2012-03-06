package com.bbm.gps.user.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import com.bbm.common.cmm.EgovMessageSource;
import com.bbm.common.cmm.service.EgovCmmUseService;
import com.bbm.common.util.cas.service.EgovSessionCookieUtil;
import com.bbm.common.util.sim.service.EgovFileScrty;
import com.bbm.gps.adm.org.service.OrgManageVO;
import com.bbm.gps.adm.user.service.UserManageService;
import com.bbm.gps.adm.user.service.UserManageVO;
import com.bbm.gps.adm.user.service.ZipVO;
import com.bbm.gps.cmm.DefaultDataSet;
import com.bbm.gps.login.service.GpsSessionVO;
import com.bbm.gps.user.service.UserRegisterService;
import com.bbm.gps.user.service.UserRegisterVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/*
import com.gpki.gpkiapi.cert.X509Certificate;
import com.gpki.gpkiapi.exception.GpkiApiException;
import com.gpki.secureweb.GPKISecureWEBException;
import com.gpki.servlet.GPKIHttpServletRequest;
import com.gpki.servlet.GPKIHttpServletResponse;
*/
import com.sci.v2.comm.secu.hmac.SciHmac;

/** 
 * 회원가입 비즈니스 구현 클래스 
 * <p><b>NOTE:</b> 사용자관리 화면과 직접 연계되는 컨트롤러 클래스로 주로 JSP화면에서 호출되거나 
 * 해당 클래스 내부에서 호출되는 메소드들이 정의되어 있다
 * 해당 클래스 중에는 비즈니스 로직이 없고 단순히 다른 JSP화면으로 forword 시켜주는 메소드도 존재한다
 * DB select, DB insert, DB update, DB delete, 단순forward 등의 기능을 하기위한 메소드들의 집합
 * @author 범정부통계포털 이진우 
 * @since 2011.08.01 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.08.01     이진우       최초 생성
 *   2011.07.21     이진우       사용자유형을 권한유형으로 변경  
 * 
 * </pre> 
 */

@Controller
public class UserRegisterController {
	
	/** UserManageService 서비스 호출 */ 
	@Resource(name = "userManageService")
    private UserManageService userManageService;
	
	/** userRegisterService 서비스 호출 */ 
	@Resource(name = "userRegisterService")
    private UserRegisterService userRegisterService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
	/** EgovCmmUseService 호출 */ 
	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;
	
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
	   
    /** defaultDataSet */
    @Resource(name="defaultDataSet")
    protected DefaultDataSet defaultDataSet;
    
	/** LOG */ 
	protected Log log = LogFactory.getLog(this.getClass());
		
	/**
     * 회원 약관확인
     * @param model 화면모델
     * @return gps/user/gpsAgreementConfirm
     * @throws Exception
     */
    @RequestMapping("/gps/user/actionRegistUser.do")
    public String goRegistUser(@ModelAttribute("userRegisterVO") UserRegisterVO userRegisterVO,
    		Model model)
            throws Exception {
    	model.addAttribute("serverSe", propertiesService.getString("was.LcInfo"));
        return "gps/user/gpsAgreementConfirm";
    }
    
    /**
     * 회원가입 인증 종류 선택화면 호출
     * @param model 화면모델
     * @return gps/user/gpsRealNameConfirm
     * @throws Exception
     */
	@RequestMapping("/gps/user/actionAgreementConfirm.do")
    public String goRealNameConfirm(
    		@ModelAttribute("userRegisterVO") UserRegisterVO userRegisterVO,
    		HttpServletRequest request,
    		HttpServletResponse response,
    		@RequestParam(value="msg", required=false) String msg,
    		Model model)
            throws Exception {
		//내부망
		/*
		if(propertiesService.getString("was.LcInfo").equals("naraint")||propertiesService.getString("was.LcInfo").equals("naradev")){
			GPKIHttpServletResponse gpkiresponse = null;
		    GPKIHttpServletRequest gpkirequest = null;
		    try{
		        gpkiresponse = new GPKIHttpServletResponse(response);
		        gpkirequest = new GPKIHttpServletRequest(request);
		        if(gpkirequest!=null){
		        gpkiresponse.setRequest(gpkirequest);
		        	model.addAttribute("challenge", gpkiresponse.getChallenge());
		        }
		    }catch(Exception e){
		    	if(log.isErrorEnabled()){
		    		log.error(e);
		    	}
		    }
		}
		*/
		//외부망
		if(propertiesService.getString("was.LcInfo").equals("naraext")){
	        Calendar today = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
	        String day = sdf.format(today.getTime());
	
	        Random ran = new Random();
	        int numLength = 6;
	        String randomStr = "";
	
	        for (int i = 0; i < numLength; i++) {
	            randomStr += ran.nextInt(10);
	        }
	
			//reqNum은 최대 30byte 까지 사용 가능
	        String reqNum = day + randomStr;
			
	        Cookie cookie = new Cookie("reqNum", reqNum);
	        cookie.setMaxAge(1800);  // <== 필요시 설정(초단위로 설정됩니다)
	        response.addCookie(cookie);
	        
	        String id = this.propertiesService.getString("GPS.id");//회원사아이디
	        String srvNo = this.propertiesService.getString("GPS.srvNo");//서비스번호
	        String exVar    = this.propertiesService.getString("GPS.exVar");// 복호화용 임시필드
	      	//01. 암호화 모듈 선언
	    	com.sci.v2.comm.secu.SciSecuManager seed  = new com.sci.v2.comm.secu.SciSecuManager();
			
	    	//02. 1차 암호화
	    	String encStr = "";
	    	String reqInfo      = id+"/"+srvNo+"/"+reqNum+"/"+exVar;  // 데이터 암호화
	    	encStr              = seed.getEnc(reqInfo, "");
	
	    	//03. 위변조 검증 값 생성
	    	String hmacMsg = SciHmac.HMacEncript(encStr);
	    	
	    	//03. 2차 암호화
	    	reqInfo  = seed.getEnc(encStr + "/" + hmacMsg + "/" + "0000000000000000", "");  //2차암호화
	    	model.addAttribute("reqInfo", reqInfo);
	    	model.addAttribute("retUrl", "22http://"+request.getServerName()+"/gps/user/vnamePopup.do?menuId="+userRegisterVO.getMenuId()+"&leftMenuId="+userRegisterVO.getLeftMenuId());
		}
		if(msg!=null){
    		model.addAttribute("message","유효한 인증서가 아닙니다.");
    	}
    	model.addAttribute("serverSe",propertiesService.getString("was.LcInfo"));
        return "gps/user/gpsRealNameConfirm";
    }
    
    /**
     * 실명인증 리턴화면
     * @param model 화면모델
     * @return gps/user/vnamePopup
     * @throws Exception
     */
    @RequestMapping("/gps/user/vnamePopup.do")
    public String vnamePopup(
    		HttpServletRequest request,
    		HttpServletResponse response,
    		@ModelAttribute("userRegisterVO") UserRegisterVO userRegisterVO,
    		Model model)
            throws Exception {
    	response.setHeader("Cache-Control","no-cache");     
    	response.setHeader("Pragma","no-cache");
        return "gps/user/vnamePopup";
    }
    
    /**
     * GPIN 인증결과 리턴화면
     * @return gps/user/gpsRegistUserForm
     * @throws Exception
     */
    @RequestMapping("/gps/user/gpinServiceResponse.do")
    public String GpinServiceResponse(
    		HttpServletRequest request,
    		HttpSession session,
    		@ModelAttribute("userRegisterVO") UserRegisterVO userRegisterVO,
    		Model model
    		)throws Exception {
    	if(session.getAttribute("realName")!=null){
    		userRegisterVO.setMenuId((String)session.getAttribute("menuId"));
        	userRegisterVO.setLeftMenuId((String)session.getAttribute("leftMenuId"));
        	userRegisterVO.setGpin((String)session.getAttribute("dupInfo"));
        	userRegisterVO.setNm((String)session.getAttribute("realName"));
        	if(this.userRegisterService.duplicateJoinChk(userRegisterVO) > 0){
        		model.addAttribute("message", "이미 가입된 회원 입니다.");
        		return "forward:/gps/login/gpsLoginUsr.do";
        	}else{
	    		model.addAttribute("message", "인증 처리 되었습니다");
	    		model.addAttribute("emailMap", cmmUseService.selectEmailMap("21101304",""));
	            model.addAttribute("orgKdMap", cmmUseService.selectCmmCodeIdMap("21101110",""));
	    		return "gps/user/gpsRegistUserForm";
        	}
    	}else{
    		model.addAttribute("message", "인증에 실패 하였습니다.");
    		return "forward:/gps/user/actionAgreementConfirm.do";
    	}
    }
    
    /**
     * gpki인증
     * @param model 화면모델
     * @return gps/user/gpsRealNameConfirm
     * @throws Exception,GpkiApiException 
     * @throws Exception
     */
	@SuppressWarnings("static-access")
	@RequestMapping("/gps/user/gpsGpkiConfirm.do")
    public String gpkiConfirm(
    		@ModelAttribute("userRegisterVO") UserRegisterVO userRegisterVO,
    		HttpServletRequest request,
    		HttpServletResponse response,
    		Model model
    	)throws Exception{
		String[] nm = null;
		String[] queryStr = null;
		String returnUrl = "redirect:/gps/user/actionAgreementConfirm.do?msg=1&menuId="+userRegisterVO.getMenuId()+"&leftMenuId="+userRegisterVO.getLeftMenuId();
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
			        	userRegisterVO.setGpki(subDN);
			        	if(this.userRegisterService.duplicateJoinChk(userRegisterVO) > 0){
			        		return "redirect:/gps/login/gpsLoginUsr.do?"+queryString+"&msg=0";
			        	}else{
				        	try {
				        		//한글이름만 자르기
								nm = subDN.split("[,]");
								userRegisterVO.setNm(nm[0].replaceAll("[^\uAC00-\uD7AF\u1100-\u11FF\u3130-\u318F]+", ""));
								//메뉴ID자르기
								if(queryString!=null && !queryString.isEmpty()){
									queryStr = queryString.split("[&]");
					        		userRegisterVO.setMenuId(queryStr[1].split("[=]")[1]);
					        		userRegisterVO.setLeftMenuId(queryStr[2].split("[=]")[1]);
								}
							} catch (IndexOutOfBoundsException e) {
								if(log.isErrorEnabled()){
									log.error(e);
								}
							}
			        	}
			        	model.addAttribute("emailMap", cmmUseService.selectEmailMap("21101304",""));
				        model.addAttribute("orgKdMap", cmmUseService.selectCmmCodeIdMap("21101110",""));
			        	model.addAttribute("message", "인증 처리 되었습니다");
			        	
			        }
			        returnUrl =  "gps/user/gpsRegistUserForm";
		        }
		    }catch(GPKISecureWEBException e){
				if(log.isErrorEnabled()){
					log.error("GPKISecureWEBException:"+e);
				}
		    }catch(GpkiApiException e){
		    	if(log.isErrorEnabled()){
		    		log.error("GpkiApiException:"+e);
				}
		    }catch (Exception e) {
		    	if(log.isErrorEnabled()){
		    		log.error("Exception:"+e);
				}
			}
    	}
    	*/
    	return returnUrl;
    }
    
    /**
     * 실명인증후 회원가입 입력 화면 이동
     * @param model 화면모델
     * @return gps/user/gpsRegistUserForm
     * @throws Exception
     */
    @SuppressWarnings("unused")
    @RequestMapping("/gps/user/actionRegistUserForm.do")
    public String goRegistUserForm(
    		@ModelAttribute("userRegisterVO") UserRegisterVO userRegisterVO,
    		HttpServletRequest request,
    		Model model)
            throws Exception {
    	// 변수 -------------------------------------------------------------------------------------------------------------
        String retInfo = userRegisterVO.getRetInfo().trim(); // 결과정보    
        String vDiscrNo = ""; // 가상식별번호
        String name = "";  // 성명
        String result = ""; // 실명확인 결과값
        String discrHash = ""; // 중복가입확인정보
    	String ciscrHash = ""; // CI연계값
		String ciVersion = ""; // CI버전	
    	String encPara = "";
    	String encMsg = "";
    	String returnUrl = "";
    	boolean isConfirm = false;
        //-----------------------------------------------------------------------------------------------------------------
        
        //쿠키값 가져 오기
        Cookie[] cookies = request.getCookies();
        String cookiename = "";
        String cookiereqNum = "";
    	if(cookies!=null){
    		for (int i = 0; i < cookies.length; i++){
    			Cookie c = cookies[i];
    			cookiename = c.getName();
    			cookiereqNum = c.getValue();
    			if(cookiename.compareTo("reqNum")==0){
    				break;
    			}
    			cookiereqNum = null;
    		}
    	}
        try {
			// Parameter 수신 --------------------------------------------------------------------
			retInfo = userRegisterVO.getRetInfo().trim();
			// 1. 암호화 모듈 (jar) Loading
			com.sci.v2.comm.secu.SciSecuManager sciSecuMg = new com.sci.v2.comm.secu.SciSecuManager();
			//쿠키에서 생성한 값을 Key로 생성 한다.
			retInfo = sciSecuMg.getDec(retInfo, cookiereqNum);
			// 2.1차 파싱---------------------------------------------------------------
			int inf1 = retInfo.indexOf("/", 0);
			int inf2 = retInfo.indexOf("/", inf1 + 1);
			encPara = retInfo.substring(0, inf1); //암호화된 통합 파라미터
			encMsg = retInfo.substring(inf1 + 1, inf2); //암호화된 통합 파라미터의 Hash값
			String encMsg2 = sciSecuMg.getMsg(encPara);
			// 3.위/변조 검증 ---------------------------------------------------------------
			if (!encMsg2.equals(encMsg)){
				model.addAttribute("message", "비정상적인 접근입니다.");
			}else{
				// 복호화 및 위/변조 검증 ---------------------------------------------------------------
				retInfo = sciSecuMg.getDec(encPara, cookiereqNum);
				int info1 = retInfo.indexOf("/", 0);
				int info2 = retInfo.indexOf("/", info1 + 1);
				int info3 = retInfo.indexOf("/", info2 + 1);
				int info4 = retInfo.indexOf("/", info3 + 1);
				int info5 = retInfo.indexOf("/", info4 + 1);
				int info6 = retInfo.indexOf("/", info5 + 1);
				int info7 = retInfo.indexOf("/",info6+1);
				
				vDiscrNo   = retInfo.substring(info1+1,info2);
				name = retInfo.substring(info2 + 1, info3);
				result = retInfo.substring(info3 + 1, info4);
				discrHash = retInfo.substring(info4 + 1, info5);
				ciscrHash = retInfo.substring(info5 + 1, info6);
				ciVersion  = retInfo.substring(info6+1,info7);
				discrHash = sciSecuMg.getDec(discrHash, cookiereqNum); //암호화된 중복가입확인정보 한번더 복호화
				ciscrHash = sciSecuMg.getDec(ciscrHash, cookiereqNum); //암호화된 CI연계값 한번더 복호화
				
				if(result.equals("1")){
					userRegisterVO.setIpin(vDiscrNo);
					if(this.userRegisterService.duplicateJoinChk(userRegisterVO) > 0){
		        		return "redirect:/gps/login/gpsLoginUsr.do?msg=0&menuId=0010001100122123&leftMenuId=0010001100122";
					}else{
						userRegisterVO.setNm(name);
						isConfirm = true;
						model.addAttribute("message","실명인증 되었습니다.");
						model.addAttribute("emailMap", cmmUseService.selectEmailMap("21101304",""));
				        model.addAttribute("orgKdMap", cmmUseService.selectCmmCodeIdMap("21101110",""));
					}
				}else if(result.equals("2") || result.equals("4")){
					model.addAttribute("message","주민등록번호 오류입니다.");
				}else if(result.equals("3")){
					model.addAttribute("message","실명확인 결과가 없습니다.");
				}else{
					model.addAttribute("message","실명인증 시스템 에러입니다.");
				}
			}
		} catch (Exception e) {
			if(log.isErrorEnabled()){
				log.error(e);
			}
		}
		if(isConfirm){
			returnUrl = "gps/user/gpsRegistUserForm";
		}else{
			returnUrl = "forward:/gps/user/actionAgreementConfirm.do";
		}
		return returnUrl;
    }
    
    /** 
     * userRegistVO 에 담겨있는 항목을 DB에 insert 
     * @param userRegistVO  insert할 항목에 대한 정보를 담고있는 VO 
     * @param bindingResult BindingResult
     * @param model ModelMap
     * @return gps/user/gpsRegistUserForm
     * @exception Exception 
     * @see TABLE NAME : 
    */ 
    @RequestMapping(value="/gps/user/insertUser.do")
    public String insertUser (
    		@ModelAttribute("userRegisterVO") UserRegisterVO userRegisterVO,
    		HttpServletRequest request,
    		ModelMap model
    ) throws Exception {

    	// 1. 입력한 비밀번호를 암호화한다.
    	userRegisterVO = (UserRegisterVO)this.defaultDataSet.registSet(request, userRegisterVO);
    	userRegisterVO.setPassword(EgovFileScrty.encryptPassword(userRegisterVO.getPassword()));
    	userRegisterVO.setAddrCn(EgovFileScrty.encode(userRegisterVO.getAddrCn()));
    	userRegisterVO.setAddrDtlCn(EgovFileScrty.encode(userRegisterVO.getAddrDtlCn()));
    	userRegisterVO.setPhonCn(EgovFileScrty.encode(userRegisterVO.getPhonCn()));
    	userRegisterVO.setMoblphonCn(EgovFileScrty.encode(userRegisterVO.getMoblphonCn()));
    	userRegisterVO.setEmail(EgovFileScrty.encode(userRegisterVO.getEmail()).concat("@"+userRegisterVO.getEmailCoNm()));
    	userRegisterVO.setTrnsferInfo("K");
    	userRegisterService.insertUser(userRegisterVO);
    	
    	return "forward:/gps/login/gpsLoginUsr.do";
    }
    
    
    /**
     * 사용자아이디의 중복확인화면 이동
     * @param model 
     * @return gps/user/gpsUserIdCheck
     * @throws Exception
     */
    @RequestMapping(value="/gps/user/actionCheckId.do")
    public String goCheckId(ModelMap model)
            throws Exception {
        model.addAttribute("checkId", "");
        model.addAttribute("usedCnt", "-1");
        return "gps/user/gpsUserIdCheck";
    }
    
    /**
     * 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
     * @param commandMap 파라메터전달용 commandMap
     * @param model 화면모델
     * @return gps/user/gpsUserIdCheck
     * @throws Exception
     */
    @RequestMapping(value="/gps/user/checkUserIdConfirm.do")
    public String checkIdDplct(
    		Map<String, Object> commandMap,
            ModelMap model
            )throws Exception {
        
    	String checkId = (String)commandMap.get("checkId");
    	checkId =  new String(checkId.getBytes("ISO-8859-1"), "UTF-8");
        
    	if (checkId==null || checkId.equals("")) return "forward:/gps/user/actionCheckId.do";
        
        int usedCnt = userRegisterService.checkIdDplct(checkId);
        model.addAttribute("usedCnt", usedCnt);
        model.addAttribute("checkId", checkId);
        
        return "gps/user/gpsUserIdCheck";
    }
    
    /**
     * 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
     * @param commandMap 파라메터전달용 commandMap
     * @param model 화면모델
     * @return gps/user/gpsUserIdCheck
     * @throws Exception
     */
    @RequestMapping(value="/gps/user/checkUserId.do")
    public ModelAndView checkUserId(
    		@ModelAttribute("userRegisterVO") UserRegisterVO userRegisterVO,
    		HttpServletResponse response,
			HttpServletRequest request,
			ModelAndView model
            )throws Exception {
    	boolean result = false;
    	int usedCnt = 0;
    	usedCnt = userRegisterService.checkIdDplct(userRegisterVO.getUserId());
    	if(usedCnt > 0){
    		result = true;
    	}
	    model.addObject("result",result);
	    model.setViewName("jsonView");
	    return model;
    }
    
    
    
    /** 
     * 우편번호 찾기 화면호출
     * @param model ModelMap
     * @return 우편번호 찾기 화면으로 이동
     * @exception Exception 
     * @see TABLE NAME : TN_ZIP 
    */ 
	@RequestMapping(value="/gps/user/ZipSearchPopup.do")
 	public String callZipSearchPopup (ModelMap model
 			) throws Exception {
		return "gps/user/gpsZipSearchList";
	}    
    
	/** 
	 * 우편번호 조회
     * @param ZipVO 우편번호 정보를 가지고있는 VO
     * @param model ModelMap
	 * @return 목록화면 JSP로 이동 
	 * @exception Exception 
     * @see TABLE NAME : TN_ZIP
	*/ 
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/gps/user/ZipSearchList.do")
	public String selectZipSearchList (@ModelAttribute("searchVO") ZipVO searchVO
			, ModelMap model
			) throws Exception {
    	/** EgovPropertyService.sample */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        List zipList = userManageService.selectZipList(searchVO);
        model.addAttribute("resultList", zipList);
        
        int totCnt = userManageService.selectZipListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "gps/user/gpsZipSearchList";
	}
    
    
    /**
     * 조직목록를 조회한다. 
     * @param commandMap - 요청 codeid 
     * @param model - Spring 제공하는 ModelMap
     * @return String -리턴 URL
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/gps/user/orgComboList.do")
    public ModelAndView orgComboList(
    		HttpServletResponse response,
			HttpServletRequest request,
            Map commandMap) throws Exception {

    	ModelAndView model = new ModelAndView();
    	String selectOrgKd = commandMap.get("selectOrgKd") == null ? "" : (String) commandMap.get("selectOrgKd");
    	
    	OrgManageVO orgManageVO = new OrgManageVO();
    	orgManageVO.setOrgKd(selectOrgKd);
    	List orgList = (ArrayList) userRegisterService.selectOrgCombo(orgManageVO);
    	
	    model.addObject("result", orgList);

	    model.setViewName("jsonView");
	    return model;
	}
    
    
    /**
     * 부서목록를 조회한다. 
     * @param commandMap - 요청 codeid 
     * @param model - Spring 제공하는 ModelMap
     * @return String -리턴 URL
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/gps/user/deptComboList.do")
    public ModelAndView deptComboList(
    		HttpServletResponse response,
			HttpServletRequest request,
            Map commandMap) throws Exception {

    	ModelAndView model = new ModelAndView();
    	String selectOrgId = commandMap.get("selectOrgId") == null ? "" : (String) commandMap.get("selectOrgId");
    	
    	OrgManageVO orgManageVO = new OrgManageVO();
    	orgManageVO.setUpOrgId(selectOrgId);
    	List deptList = (ArrayList) userRegisterService.selectDeptCombo(orgManageVO);
    	
	    model.addObject("result", deptList);

	    model.setViewName("jsonView");
	    return model;
	}
    
    
    
    
    /** 
	 * userRegisterVO 게시물ID에 해당하는 정보를 Select하여 게시물을 수정하는 화면으로 이동 
	 * @param userRegisterVO  수정 대상 항목에 대한 구분자 
     * @param model ModelMap
	 * @return 수정화면 JSP로 이동 ("gps/user/userUpdate") 
	 * @exception Exception 
     * @see TABLE NAME : 
	*/ 
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/gps/user/modifyUser.do")
	public String modifyUser (
			@ModelAttribute("userRegisterVO") UserRegisterVO userRegisterVO,
			HttpServletRequest request,
    		HttpServletResponse response,
    		@RequestParam(value="msg", required=false) String msg,
    		ModelMap model
    ) throws Exception {
    	boolean gpkiRegist = false;
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	if(gpsSessionVO!=null){
	    	UserManageVO userVO = new UserManageVO();
	    	userVO.setUserId(gpsSessionVO.getUsrId());
	    	userVO = userManageService.selectUser(userVO);
	    	if(userVO!=null){
		    	// 사용자 정보 복호화
		    	if (null != userVO.getAddrCn()) {
		        	userVO.setAddrCn(EgovFileScrty.decode(userVO.getAddrCn()));
		    	}
		    	if (null != userVO.getAddrDtlCn()) {
		    		userVO.setAddrDtlCn(EgovFileScrty.decode(userVO.getAddrDtlCn()));
		    	}
		    	if (null != userVO.getPhonCn()) {
		    		userVO.setPhonCn(EgovFileScrty.decode(userVO.getPhonCn()));
		    	}
		    	if (null != userVO.getMoblphonCn()) {
		    		userVO.setMoblphonCn(EgovFileScrty.decode(userVO.getMoblphonCn()));
		    	}
		    	if (null != userVO.getEmail()) {
		    		userVO.setEmail(EgovFileScrty.decode(userVO.getEmail()));
		    	}
		    	if (null != userVO.getEmailCo()) {
		    		userVO.setEmailCoNm(userVO.getEmailCo());
		    	}
		    	
		    	userVO.setMenuId(userRegisterVO.getMenuId());
		    	userVO.setLeftMenuId(userRegisterVO.getLeftMenuId());
		        model.addAttribute("userRegisterVO",userVO);
			    model.addAttribute("emailMap", cmmUseService.selectEmailMap("21101304",""));
		        model.addAttribute("orgKdMap", cmmUseService.selectCmmCodeIdMap("21101110",""));
		        
		        //조직, 부서찾기 콤보
		        if(userVO.getOrgChooseAt()!=null && !userVO.getOrgChooseAt().equals("W")){
		        	OrgManageVO orgVO = new OrgManageVO();
		        	orgVO.setOrgKd(userVO.getOrgKd());
		        	List orgList = (ArrayList) userRegisterService.selectOrgCombo(orgVO);
		        	Map orgMap  = new  LinkedHashMap();
		        	for(int tmpcnt = 0; tmpcnt < orgList.size(); tmpcnt++)
		    		{
		        		EgovMap _egovMap = (EgovMap)orgList.get(tmpcnt);
		        		orgMap.put(_egovMap.get("orgId"), _egovMap.get("orgNm"));
		    		}        	
		        	model.addAttribute("orgIdMap",orgMap);
		        	if(userVO.getOrgChooseAt().equals("C")){
			        	OrgManageVO deptVO = new OrgManageVO();
			        	deptVO.setUpOrgId(userVO.getOrgId());
			            List deptList = (ArrayList) userRegisterService.selectDeptCombo(deptVO);
			           	Map deptMap  = new  LinkedHashMap();
			           	for(int tmpcnt = 0; tmpcnt < deptList.size(); tmpcnt++)
			       		{
			           		EgovMap _egovMap = (EgovMap)deptList.get(tmpcnt);
			           		deptMap.put(_egovMap.get("orgId"), _egovMap.get("orgNm"));
			       		}        	
			           	model.addAttribute("deptIdMap",deptMap);
		        	}     	
		        }
		      //내부망
		        /*
				if(propertiesService.getString("was.LcInfo").equals("naraint") || propertiesService.getString("was.LcInfo").equals("naradev")){
					GPKIHttpServletResponse gpkiresponse = null;
				    GPKIHttpServletRequest gpkirequest = null;
				    try{
				        gpkiresponse = new GPKIHttpServletResponse(response);
				        gpkirequest = new GPKIHttpServletRequest(request);
				        if(gpkirequest!=null){
				        gpkiresponse.setRequest(gpkirequest);
				        model.addAttribute("challenge", gpkiresponse.getChallenge());
				        }
				    }catch(Exception e){
				    	if(log.isErrorEnabled()){
				    		log.error(e);
				    	}
				    }
				    if(userVO!=null){
			        	if(userVO.getGpki() == null || userVO.getGpki().isEmpty()){
			        		gpkiRegist = true;
			        	}
			        }
				}
		       */
		        
		        if(msg!=null){
		        	if(msg.equals("1")){
		        		model.addAttribute("message","유효한 인증서가 아닙니다.");
		        	}else if(msg.equals("2")){
		        		model.addAttribute("message","인증서가 등록되었습니다.");
		        	}
		    	}
		        model.addAttribute("gpkiRegist", gpkiRegist);
		        return "gps/user/userUpdate";
	    	}else{
	    		model.addAttribute("message", "회원정보가 없습니다");
	    		return "forward:/gps/index.do";
	    	}
    	}else{
    		model.addAttribute("message", "회원정보가 없습니다");
    		return "forward:/gps/index.do";
    	}
    }
    
    /**
     * gpki인증
     * @param model 화면모델
     * @return gps/user/gpsRealNameConfirm
     * @throws Exception,GpkiApiException 
     * @throws Exception
     */
	@SuppressWarnings("static-access")
	@RequestMapping("/gps/user/updateGpki.do")
    public String updateGpki(
    		@ModelAttribute("userRegisterVO") UserRegisterVO userRegisterVO,
    		HttpServletRequest request,
    		HttpServletResponse response,
    		Model model
    	)throws Exception{
		String returnUrl="redirect:/gps/user/modifyUser.do?menuId="+userRegisterVO.getMenuId()+"&leftMenuId="+userRegisterVO.getLeftMenuId();
		String msg="1";
		//내부망일때만 처리
		/*
    	if(propertiesService.getString("was.LcInfo").equals("naraint") || propertiesService.getString("was.LcInfo").equals("naradev")){
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
			        	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
			        	if(gpsSessionVO!=null){
			        		userRegisterVO.setUserId(gpsSessionVO.getUsrId());
				        	userRegisterVO.setGpki(subDN);
				        	this.userRegisterService.updateGpki(userRegisterVO);
				        	msg = "2";
			        	}
			        }
		        }
		    }catch(GPKISecureWEBException e){
				if(log.isErrorEnabled()){
					log.error("GPKISecureWEBException:"+e);
				}
		    }catch(GpkiApiException e){
		    	if(log.isErrorEnabled()){
		    		log.error("GpkiApiException:"+e);
				}
		    }catch (Exception e) {
		    	if(log.isErrorEnabled()){
		    		log.error("Exception:"+e);
				}
			}
    	}
    	*/
    	return returnUrl+"&msg="+msg;
    }
    
    /** 
     * userRegisterVO 수정할 대상 게시물ID에 해당하는 행을 업데이트 
     * @param userRegisterVO  업데이트 항목들에 대한 정보를 가지고있는 VO
     * @param model ModelMap
     * @return 업데이트 한 후 업데이트된 해당 게시물의 결과를 보여주는 상세화면 조회 메서드로 forward
     * @return ("forward:/gps/user/selectUserList.do") 
     * @exception Exception 
     * @see TABLE NAME : 
    */ 
    @RequestMapping(value="/gps/user/updateUser.do")
	public String updateUser (@ModelAttribute("userRegisterVO") UserRegisterVO userRegisterVO
			, HttpServletRequest request
    		, ModelMap model
    ) throws Exception {

    	// 1.사용자 정보 편집
    	userRegisterVO = (UserRegisterVO) defaultDataSet.updateSet(request, userRegisterVO);
    	
    	// 사용자 정보 복호화
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	userRegisterVO.setUpdtusrId(gpsSessionVO.getUsrId());
    	userRegisterVO.setAddrCn(EgovFileScrty.encode(userRegisterVO.getAddrCn()));
    	userRegisterVO.setAddrDtlCn(EgovFileScrty.encode(userRegisterVO.getAddrDtlCn()));
    	userRegisterVO.setPhonCn(EgovFileScrty.encode(userRegisterVO.getPhonCn()));
    	userRegisterVO.setMoblphonCn(EgovFileScrty.encode(userRegisterVO.getMoblphonCn()));
    	userRegisterVO.setEmail(EgovFileScrty.encode(userRegisterVO.getEmail()).concat("@"+userRegisterVO.getEmailCoNm()));
    	
    	// 1.사용자 정보 수정
    	userRegisterService.updateUser(userRegisterVO);

		model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
		
		return "forward:/gps/user/modifyUser.do"; 	
    }
}
