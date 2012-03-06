package com.bbm.gps.cmm.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import com.bbm.common.cmm.EgovMessageSource;
import com.bbm.common.util.cas.service.EgovSessionCookieUtil;
import com.bbm.gps.adm.author.intergrated.service.IgrAuthorManageService;
import com.bbm.gps.adm.author.intergrated.service.IgrAuthorManageVO;
import com.bbm.gps.adm.menu.service.MenuManageService;
import com.bbm.gps.adm.menu.service.MenuManageVO;
import com.bbm.gps.adm.user.service.UserManageService;
import com.bbm.gps.adm.user.service.UserManageVO;
import com.bbm.gps.login.service.GpsLoginVO;
import com.bbm.gps.login.service.GpsSessionVO;
import com.bbm.gps.user.service.UserRegisterVO;


import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * 공통페이지 비즈니스 구현 클래스
 * <p><b>NOTE:</b>포털공통페이지 화면과 직접 연계되는 컨트롤러 클래스로 주로 JSP화면에서 호출되거나 
 * 해당 클래스 내부에서 호출되는 메소드들이 정의되어 있다
 * 해당 클래스 중에는 비즈니스 로직이 없고 단순히 다른 JSP화면으로 forword 시켜주는 메소드도 존재한다
 * @author 통계포털 황기연
 * @since 2011.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.06.01  황기연          최초 생성
 *
 * </pre>
 */

@Controller
public class CmmController {
	/** log */
	protected Logger logger = Logger.getLogger(this.getClass());
	
	/** menuService */
	@Resource(name="menuManageService")
	protected MenuManageService menuManageService;
	
	/** propertiesService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
	
    /** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    

	
	/** EgovLoginService */
	@Resource(name = "userManageService")
	private UserManageService userManageService;

	/** IgrAuthorManageService 서비스 호출 */ 
	@Resource(name = "igrAuthorManageService")
    private IgrAuthorManageService igrAuthorManageService;
	
	/**
	 * 포털관리자 index 페이지
     * @return returnUrl 페이지정보
     * @exception Exception
     */
    @RequestMapping(value="/gps/adm/index.do")
    public String admIndex(
    		@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
    		HttpServletRequest request,
    		ModelMap model) throws Exception{
    	return "gps/adm/index";
    }
    
	/**
	 * 관리자 TOP 페이지
     * @param model
     * @return "gps/cmm/admTop"
     * @exception Exception
     */
    @RequestMapping(value="/gps/cmm/admTop.do")
	public String admTop(
			@ModelAttribute("gpsLoginVO") GpsLoginVO gpsLoginVO,
			@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
			HttpServletRequest request,
    		ModelMap model)
            throws Exception{
    	gpsLoginVO.setReturnUrl("/gps/adm/index.do");
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	if(gpsSessionVO!=null){
    		// 2011-11-10 로그인 사용자 권한 레벨 체크 START
    		IgrAuthorManageVO authorManageVO = new IgrAuthorManageVO();
    		authorManageVO.setUserId(gpsSessionVO.getUsrId());
    		if (igrAuthorManageService.selectAuthorLevel(authorManageVO) > 10000) {
        		// 상단메뉴정보
        		model.addAttribute("menuList",(List<MenuManageVO>)this.menuManageService.selectTopMenuList(gpsSessionVO));
    		} else {
        		// 상단메뉴정보
        		model.addAttribute("menuList",(List<MenuManageVO>)this.menuManageService.selectTopMenuListAdm(gpsSessionVO));
    		}
        	// 2011-11-10 로그인 사용자 권한 레벨 체크 END
    		
    	}
        return "gps/cmm/admTop";
	}
    
    /**
	 * 관리자 HEADER 페이지
     * @param model
     * @return "gps/cmm/admHeader"
     * @exception Exception
     */
    @RequestMapping(value="/gps/cmm/admHeader.do")
	public String admHeader(
			HttpServletRequest request,
    		ModelMap model)
            throws Exception{
    	//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	model.addAttribute("gpsSessionVO", gpsSessionVO);
    	model.addAttribute("returnUrl", "/gps/adm/index.do");
        return "gps/cmm/admHeader";
	}
    
    /**
	 * 관리자 LEFT 페이지
     * @param model
     * @return "gps/cmm/admLeft"
     * @exception Exception
     */
    @RequestMapping(value="/gps/cmm/admLeft.do")
	public String admLeft( 
			@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
			HttpServletRequest request,
    		ModelMap model)
            throws Exception{
    	//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	if(gpsSessionVO!=null){
    	menuManageVO.setUsrId(gpsSessionVO.getUsrId());
    	//LEFT 메뉴정보
    	model.addAttribute("menuList",(List<MenuManageVO>)this.menuManageService.selectLeftMenuList(menuManageVO));
    	}
        return "gps/cmm/admLeft";
	}
    
    /**
	 * 관리자 BOTTOM 페이지
     * @param model
     * @return "gps/cmm/admBottom"
     * @exception Exception
     */
    @RequestMapping(value="/gps/cmm/admBottom.do")
	public String admBottom( 
    		ModelMap model)
            throws Exception{ 
        return "gps/cmm/admBottom";
	}
    
    /**
	 * 관리자 FOOTER 페이지
     * @param model
     * @return "gps/cmm/admFooter"
     * @exception Exception
     */
    @RequestMapping(value="/gps/cmm/admFooter.do")
	public String admFooter( 
    		ModelMap model)
            throws Exception{ 
        return "gps/cmm/admFooter";
	}
    
    /**
	 * 포털 사용자 index 페이지
     * @return "gps/index"
     * @exception Exception
     */
    @RequestMapping(value="/gps/index.do")
    public String index(
    		@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
    		@ModelAttribute("gpsLoginVO") GpsLoginVO gpsLoginVO,
    		@RequestParam(value="msg", required=false) String msg,
    		@RequestParam(value="failCnt", required=false) Integer failCnt,
    		HttpServletRequest request,
    		ModelMap model
    		) throws Exception{
    		String pw = "";
    		//아이디저장 쿠키처리
    		String userId = EgovSessionCookieUtil.getCookie(request,"userId");
    		if(userId != null){
    			gpsLoginVO.setUserId(userId);
    			gpsLoginVO.setSaveId("Y");
    		}
    		
    		//세션정보
        	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
        	//메타시스템 index 페이지 파라미터 처리
        	if(gpsSessionVO!=null){
        		if(gpsSessionVO.getUsrId()!=null && !gpsSessionVO.getUsrId().isEmpty()){
	        		UserManageVO userManageVO = new UserManageVO();
	        		userManageVO.setUserId(gpsSessionVO.getUsrId());
	        		userManageVO = (UserManageVO)this.userManageService.selectUser(userManageVO);
	        		pw = userManageVO.getPassword();
        		}
        	}
    	
    		//menuSkin
    		menuManageVO.setMenuSkin("index");
    		//returnUrl
    		gpsLoginVO.setReturnUrl("/gps/index.do");
    			
    		//처리 메세지
    		String message = "";
    		if(msg != null){
    			if(msg.equals("2")){//로그인실패
    				message = this.egovMessageSource.getMessage("gps.fail.login");
    			}else if(msg.equals("3")){//로그아웃
    				message = this.egovMessageSource.getMessage("gps.success.logout");
    			}else if(msg.equals("4")){//로그인제한
    				message = this.egovMessageSource.getMessage("gps.login.limit.excess");
    			}else{
    				message = "";
    			}
    		}
    		model.addAttribute("menuManageVO",menuManageVO);
    		model.addAttribute("message",message);
    		model.addAttribute("pw", pw);
    		model.addAttribute("serverSe",propertiesService.getString("was.LcInfo"));
        	return  "gps/index";
    }
    
    /**
	 * 사용자 TOP 페이지
     * @param model
     * @return "gps/cmm/top"
     * @exception Exception
     */
    @RequestMapping(value="/gps/cmm/top.do")
	public String top( 
			@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
			HttpServletRequest request,
    		ModelMap model)
            throws Exception{
		
    	//상단메뉴정보
    	menuManageVO.setUpperMenuId(this.propertyService.getString("GPS.sysId"));
    	List<MenuManageVO> menuList = this.menuManageService.selectUserPageTopMenuList(menuManageVO);
    	for (MenuManageVO manageVO : menuList) {
    		if (manageVO.getMenuLv() == 2) {
		    	if (null == manageVO.getTopImageMask()) {
		    		menuManageVO.setMenuImgFlg(false);
		    	}
    		}
    	}

    	model.addAttribute("WebImagePath", this.propertyService.getString("WebImagePath"));
		model.addAttribute("topMenuList",menuList);
        return "gps/cmm/top";
	}
    
    /**
	 * 사용자 HEADER 페이지
     * @param model
     * @return "gps/cmm/header"
     * @exception Exception
     */
    @RequestMapping(value="/gps/cmm/header.do")
	public String header(
			@ModelAttribute("userManageVO") UserManageVO userManageVO,
			HttpServletRequest request,
    		ModelMap model)
            throws Exception{
    	//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	if(gpsSessionVO!=null){
	    	if(gpsSessionVO.getUsrId()!=null && !gpsSessionVO.getUsrId().isEmpty()){
	    		userManageVO.setUserId(gpsSessionVO.getUsrId());
	    		userManageVO = (UserManageVO)this.userManageService.selectUser(userManageVO);
			}
    	}
    	model.addAttribute("gpsSessionVO", gpsSessionVO);
    	model.addAttribute("userManageVO", userManageVO);
    	model.addAttribute("serverSe",propertiesService.getString("was.LcInfo"));
        return "gps/cmm/header";
	}
    
    /**
	 * 사용자 LEFT 페이지
     * @param model
     * @return "gps/cmm/left"
     * @exception Exception
     */
    @RequestMapping(value="/gps/cmm/left.do")
	public String left( 
			@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
    		ModelMap model)
            throws Exception{ 
    	// 네비게이션 정보
    	if (!menuManageVO.getMenuSkin().equals("index")) {
			model.addAttribute("naviList", this.menuManageService.selectNavi(menuManageVO));
			List<MenuManageVO> leftMenuList = (List<MenuManageVO>)this.menuManageService.selectUserPageLeftMenuList(menuManageVO);
			MenuManageVO menuVO = new MenuManageVO();
			if (!menuManageVO.getMenuId().isEmpty()) {
				menuVO = menuManageService.selectMenu(menuManageVO);
			}
	    	for (MenuManageVO manageVO : leftMenuList) {
			    if (null == manageVO.getLeftImageMask()) {
			    	menuVO.setMenuImgFlg(false);
			    }
	    	}
	    	menuVO.setLeftMenuId(menuManageVO.getLeftMenuId());
	
	    	model.addAttribute("menuManageVO", menuVO);
	    	model.addAttribute("WebImagePath", this.propertyService.getString("WebImagePath"));
	    	//LEFT 메뉴정보
	    	model.addAttribute("leftMenuList",leftMenuList);
    	}
        return "gps/cmm/left";
	}
    
    /**
	 * 사용자 FOOTER 페이지
     * @param model
     * @return "gps/cmm/footer"
     * @exception Exception
     */
    @RequestMapping(value="/gps/cmm/footer.do")
	public String footer( 
			@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
    		ModelMap model)throws Exception{
		if (!menuManageVO.getMenuId().isEmpty()) {
			model.addAttribute("menuManageVO", menuManageService.selectMenu(menuManageVO));
		}
    	model.addAttribute("WebImagePath", this.propertyService.getString("WebImagePath"));
        return "gps/cmm/footer";
	}
    
    /**
	 * 팝업 TOP 페이지
     * @param model
     * @return "gps/cmm/popupTop"
     * @exception Exception
     */
    @RequestMapping(value="/gps/cmm/popupTop.do")
	public String popupTop()throws Exception{
        return "gps/cmm/popupTop";
	}
    
    /**
	 * 팝업 HEADER 페이지
     * @param model
     * @return "gps/cmm/header"
     * @exception Exception
     */
    @RequestMapping(value="/gps/cmm/popupHeader.do")
	public String popupHeader()throws Exception{
        return "gps/cmm/popupHeader";
	}
    
    /**
	 * 팝업 LEFT 페이지
     * @param model
     * @return "gps/cmm/left"
     * @exception Exception
     */
    @RequestMapping(value="/gps/cmm/popupLeft.do")
	public String popupLeft()throws Exception{ 
        return "gps/cmm/popupLeft";
	}
    
    /**
	 * 팝업 FOOTER 페이지
     * @param model
     * @return "gps/cmm/popupFooter"
     * @exception Exception
     */
    @RequestMapping(value="/gps/cmm/popupFooter.do")
	public String popupFooter()throws Exception{
        return "gps/cmm/popupFooter";
	}
    
/******************************************************************/
/* 나라통계포털 컨텐츠 Controller                                   */
/******************************************************************/
	/**
	 * 나라통계소개
     * @param model
     * @return "gps/contents/nara/introduction"
     * @exception Exception
     */
    @RequestMapping(value="/gps/contents/nara/introduction.do")
	public String introduction(
			HttpServletRequest request,
    		ModelMap model)
            throws Exception{
    	//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	model.addAttribute("gpsSessionVO", gpsSessionVO);
        return "gps/contents/nara/introduction";
	}
    
    /**
	 * 통계설명자료
     * @param model
     * @return "gps/contents/nara/explanation"
     * @exception Exception
     */
    @RequestMapping(value="/gps/contents/nara/explanation.do")
	public String explanation(
			HttpServletRequest request,
    		ModelMap model)
            throws Exception{
    	//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	model.addAttribute("gpsSessionVO", gpsSessionVO);
        return "gps/contents/nara/explanation";
	}

    
    /**
	 * 나라통계조사 > 조사홈페이지
     * @param model
     * @return "gps/contents/survey/svyhomepage"
     * @exception Exception
     */
    @RequestMapping(value="/gps/svy/intnetSvyContList.do")
	public String svyhomepage(
			HttpServletRequest request,
    		ModelMap model)
            throws Exception{
    	//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	model.addAttribute("gpsSessionVO", gpsSessionVO);
        return "gps/svy/intnetSvyContList";
	}
    
    /**
	 * 나라통계조사 > 인터넷조사
     * @param model
     * @return "gps/contents/survey/internet"
     * @exception Exception
     */
    @RequestMapping(value="/gps/contents/survey/internet.do")
	public String internet(
			HttpServletRequest request,
    		ModelMap model)
            throws Exception{
    	//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	model.addAttribute("gpsSessionVO", gpsSessionVO);
        return "gps/contents/survey/internet";
	}
    
    /**
	 * 나라통계조사 > 월별조사
     * @param model
     * @return "gps/contents/survey/monthsvy"
     * @exception Exception
     */
    @RequestMapping(value="/gps/contents/survey/monthsvy.do")
	public String monthsvy(
			HttpServletRequest request,
    		ModelMap model)
            throws Exception{
    	//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	model.addAttribute("gpsSessionVO", gpsSessionVO);
        return "gps/contents/survey/monthsvy";
	}
    
    /**
	 * 나라통계조사 > 기간별조사
     * @param model
     * @return "gps/contents/survey/orgsvy"
     * @exception Exception
     */
    @RequestMapping(value="/gps/contents/survey/orgsvy.do")
	public String orgsvy(
			HttpServletRequest request,
    		ModelMap model)
            throws Exception{
    	//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	model.addAttribute("gpsSessionVO", gpsSessionVO);
        return "gps/contents/survey/orgsvy";
	}
    
    /**
	 * 알기쉬운나라통계 > 조사통계
     * @param model
     * @return "gps/contents/study/surveystat"
     * @exception Exception
     */
    @RequestMapping(value="/gps/contents/study/surveystat.do")
	public String surveystat(
			HttpServletRequest request,
    		ModelMap model)
            throws Exception{
    	//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	model.addAttribute("gpsSessionVO", gpsSessionVO);
        return "gps/contents/study/surveystat";
	}
    
    /**
	 * 알기쉬운나라통계 > 보고통계
     * @param model
     * @return "gps/contents/study/reportstat"
     * @exception Exception
     */
    @RequestMapping(value="/gps/contents/study/reportstat.do")
	public String reportstat(
			HttpServletRequest request,
    		ModelMap model)
            throws Exception{
    	//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	model.addAttribute("gpsSessionVO", gpsSessionVO);
        return "gps/contents/study/reportstat";
	}
    
    /**
	 * 알기쉬운나라통계 > 가공통계
     * @param model
     * @return "gps/contents/study/processiongstat"
     * @exception Exception
     */
    @RequestMapping(value="/gps/contents/study/processiongstat.do")
	public String processiongstat(
			HttpServletRequest request,
    		ModelMap model)
            throws Exception{
    	//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	model.addAttribute("gpsSessionVO", gpsSessionVO);
        return "gps/contents/study/processiongstat";
	}
    
    @RequestMapping(value="/gps/contents/study/ksbpm.do")
	public String ksbpm(
			HttpServletRequest request,
    		ModelMap model)
            throws Exception{
    	//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	model.addAttribute("gpsSessionVO", gpsSessionVO);
        return "gps/contents/study/ksbpm";
	}
    
    /**
	 * 이용안내 > 서비스안내
     * @param model
     * @return "gps/contents/guidance/service"
     * @exception Exception
     */
    @RequestMapping(value="/gps/contents/guidance/service.do")
	public String service(
			HttpServletRequest request,
    		ModelMap model)
            throws Exception{
    	//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	model.addAttribute("gpsSessionVO", gpsSessionVO);
        return "gps/contents/guidance/service";
	}
    
    /**
	 * 이용안내 > 이용약관
     * @param model
     * @return "gps/contents/guidance/agreement"
     * @exception Exception
     */
    @RequestMapping(value="/gps/contents/guidance/agreement.do")
	public String agreement(
			HttpServletRequest request,
    		ModelMap model)
            throws Exception{
    	//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	model.addAttribute("gpsSessionVO", gpsSessionVO);
        return "gps/contents/guidance/agreement";
	}
    
    /**
	 * 이용안내 > 개인정보보호정책
     * @param model
     * @return "gps/contents/guidance/person"
     * @exception Exception
     */
    @RequestMapping(value="/gps/contents/guidance/person.do")
	public String person(
			HttpServletRequest request,
    		ModelMap model)
            throws Exception{
    	//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	model.addAttribute("gpsSessionVO", gpsSessionVO);
        return "gps/contents/guidance/person";
	}
    
    /**
	 * 이용안내 > 이메일무단수집거부
     * @param model
     * @return "gps/contents/guidance/email"
     * @exception Exception
     */
    @RequestMapping(value="/gps/contents/guidance/email.do")
	public String email(
			HttpServletRequest request,
    		ModelMap model)
            throws Exception{
    	//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	model.addAttribute("gpsSessionVO", gpsSessionVO);
        return "gps/contents/guidance/email";
	}
    
    /**
	 * 이용안내 > 사이트맵
     * @param model
     * @return "gps/contents/guidance/sitemap"
     * @exception Exception
     */
    @RequestMapping(value="/gps/contents/guidance/sitemap.do")
	public String sitemap(
			HttpServletRequest request,
			@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
    		ModelMap model)
            throws Exception{
    	//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	if(gpsSessionVO!=null){
    		menuManageVO.setUserId(gpsSessionVO.getUsrId());
    	}
    	List<MenuManageVO> sitemapList = (List<MenuManageVO>)this.menuManageService.selectSitemap(menuManageVO);
    	model.addAttribute("gpsSessionVO", gpsSessionVO);
    	model.addAttribute("sitemapList", sitemapList);
    	model.addAttribute("WebImagePath", this.propertyService.getString("WebImagePath"));
        return "gps/contents/guidance/sitemap";
	}
    
    
    /**
	 * 인증서 모듈 설치화면 이동
	 * @return gps/login/gpkiInstall
	 * @exception Exception
	 */
	@RequestMapping(value = "/gps/cmm/gpkiInstall.do")
	public String gpkiInstall(
			@ModelAttribute("userRegisterVO") UserRegisterVO userRegisterVO,
			@RequestParam(value="se", required=false) String se,
			Model model
			)throws Exception {
		String returnUrl = "";
		if(se!=null){
			if(se.equals("userRegist")){
				returnUrl = "/gps/user/actionAgreementConfirm.do?menuId="+userRegisterVO.getMenuId()+"&leftMenuId="+userRegisterVO.getLeftMenuId();
			}else if(se.equals("userUpdate")){
				returnUrl = "/gps/user/modifyUser.do?menuId="+userRegisterVO.getMenuId()+"&leftMenuId="+userRegisterVO.getLeftMenuId();
			}else if(se.equals("login")){
				returnUrl = "/gps/login/gpsLoginUsr.do?menuId=0010001100122123&leftMenuId=0010001100122";
			}else{
				returnUrl = "/";
			}
		}
		model.addAttribute("returnUrl", returnUrl);
		return "gps/cmm/gpkiInstall";
	}
    
    /******************************************************************/
    /* 나라통계포털 Indext Controller                                  */
    /******************************************************************/
    

    
}
