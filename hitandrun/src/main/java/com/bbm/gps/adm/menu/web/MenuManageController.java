package com.bbm.gps.adm.menu.web;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bbm.common.cmm.EgovMessageSource;
import com.bbm.common.cmm.service.EgovCmmUseService;
import com.bbm.common.util.StringUtil;
import com.bbm.common.util.cas.service.EgovSessionCookieUtil;
import com.bbm.gps.adm.author.intergrated.service.IgrAuthorManageService;
import com.bbm.gps.adm.author.intergrated.service.IgrAuthorManageVO;
import com.bbm.gps.adm.bbs.service.BbsInfoVO;
import com.bbm.gps.adm.bbs.service.BbsManageService;
import com.bbm.gps.adm.bbs.service.BbsSearchVO;
import com.bbm.gps.adm.csnst.service.CsnstManageService;
import com.bbm.gps.adm.csnst.service.CsnstManageVO;
import com.bbm.gps.adm.menu.service.MenuManageService;
import com.bbm.gps.adm.menu.service.MenuManageVO;
import com.bbm.gps.adm.menu.service.SystemVO;
import com.bbm.gps.adm.program.service.ProgramManageService;
import com.bbm.gps.adm.program.service.ProgramManageVO;
import com.bbm.gps.adm.system.service.SystemManageService;
import com.bbm.gps.cmm.DefaultDataSet;
import com.bbm.gps.login.service.GpsSessionVO;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 메뉴관리 비즈니스 구현 클래스
 * <p><b>NOTE:</b>메뉴관리 화면과 직접 연계되는 컨트롤러 클래스로 주로 JSP화면에서 호출되거나 
 * 해당 클래스 내부에서 호출되는 메소드들이 정의되어 있다
 * 해당 클래스 중에는 비즈니스 로직이 없고 단순히 다른 JSP화면으로 forword 시켜주는 메소드도 존재한다
 * @author 통계포털 황기연
 * @since 2011.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 *  == Modification Information) ==
 *   
 *     date         author                note
 *  -----------    --------    ---------------------------
 *   2011.06.01  황기연          최초 생성
 *
 * </pre>
 */

@Controller
public class MenuManageController{
	
	/** LOG */ 
	protected Logger logger = Logger.getLogger(this.getClass());
	
	/** menuManageService */
    @Resource(name="menuManageService")
    protected MenuManageService menuManageService;
    
    /** ProgramManageService 서비스 호출 */ 
	@Resource(name = "programManageService")
    private ProgramManageService programManageService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    /** massage source */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
	/** BbsManageService 서비스 호출 */ 
	@Resource(name = "bbsManageService")
    private BbsManageService bbsManageService;

	/** CsnstManageService 서비스 호출 */ 
	@Resource(name = "csnstManageService")
    private CsnstManageService csnstManageService;

	/** SystemManageService 서비스 호출 */ 
	@Resource(name = "systemManageService")
    private SystemManageService systemManageService;
	
	/** defaultDataSet */
    @Resource(name="defaultDataSet")
    protected DefaultDataSet defaultDataSet;
    
    /** EgovCmmUseService 호출 */ 
	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;
	
	/** IgrAuthorManageService 서비스 호출 */ 
	@Resource(name = "igrAuthorManageService")
    private IgrAuthorManageService igrAuthorManageService;

    Logger log = Logger.getLogger(this.getClass());

    /**
     * 메뉴관리 메인페이지 호출
     * @param menuManageVO
     * @return "gps/adm/menu/menuManageMain" 메뉴관리 메인페이지 URL
     * @throws Exception
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/menu/selectMenuManageMain.do")
	public String selectMenuManageMain(
				@ModelAttribute("menuManageVO") MenuManageVO menuManageVO
				) throws Exception {
        return "gps/adm/menu/menuManageMain";
	}

    /**
     * 메뉴목록 조회
     * @param menuManageVO
     * @param request
     * @param model
     * @return "gps/adm/menu/menuList" 메뉴목록 URL
     * @throws Exception
     * @see rootSysId,upperSysId,menuId,menuNm,menuTy,sysSe,useAt,leaf,ulOpenAt,endTagCnt
     * @see TABLE NAME : TN_MENU
     */
    @RequestMapping(value="/gps/adm/menu/selectMenuList.do")
	public String selectMenuList( 
			@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
			HttpServletRequest request,
			ModelMap model
			)throws Exception {
    	String rootSysId = "";
    	List<MenuManageVO> menuList = new ArrayList<MenuManageVO>();
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	menuManageVO.setUsrId(gpsSessionVO.getUsrId());
    	
    	menuList = (List<MenuManageVO>)this.menuManageService.selectMenuList(menuManageVO);
    	
    	if(menuList.size() > 0){
    		for (int i=0;i<1;i++) {
    			rootSysId = menuList.get(i).getRootSysId();
			}
    	}
    	// system root id
    	model.addAttribute("rootSysId", rootSysId);
    	// 메뉴목록
    	model.addAttribute("menuList",(List<MenuManageVO>)this.menuManageService.selectMenuList(menuManageVO));
        return "gps/adm/menu/menuList";
	}

    /**
     * 메뉴상세 기본페이지(메뉴가 선택되지 않았을 때 기본페이지)
     * @return "gps/adm/menu/menuDefault"
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/menu/selectMenuDefault.do")
	public String selectMenuDefault() throws Exception {
        return "gps/adm/menu/menuDefault";
	}
    
    /**
	 * 시스템입력화면호출
	 * @param systemVO 시스템정보를 가지고있는VO
     * @param model ModelMap
     * @return "gps/adm/menu/registerSysMenu"
     * @throws Exception
     * @see userCodeId,codeNm
     * @see TABLE NAME : TC_CODE
     */
	@RequestMapping(value="/gps/adm/menu/registerSysMenu.do")
	public String registerSysMenu (
			@ModelAttribute("systemVO") SystemVO systemVO,
			HttpServletRequest request,
			ModelMap model)
    		throws Exception{
		model.addAttribute("prdctnList", this.menuManageService.selectPrdctnList(systemVO));
		model.addAttribute("sysMenuList", cmmUseService.selectCmmCode("21101314",""));	//시스템관리메뉴
		model.addAttribute("cntntsMenuList", cmmUseService.selectCmmCode("21101315",""));	//컨텐츠관리메뉴
        return "gps/adm/menu/registerSysMenu";
	}
    
    /**
	 * 시스템수정화면호출
     * @param menuManageVO 메뉴정보를 가지고있는VO
     * @param model ModelMap
     * @return "gps/adm/menu/modifySysMenu"
     * @throws Exception
     * @see sysId,upperSysId,orgId,prdctnId,svyOdr,sysNm,sysAbrv,sysEngNm,userManageUseAt,authorManageUseAt,menuManageUseAt,
     * @see codeManageUseAt,programManageUseAt,mainImageManageUseAt,loginImageManageUseAt,boardManageUseAt,
     * @see recomendSiteManageUseAt,stplatManageUseAt,snstUseAt,bannerManageUseAt,qestnarUseAt,schdulManageUseAt,
     * @see eventManageUseAt,conectStatsUseAt,popupManageUseAt,useAt,bgnde,endde,sysRm,sysSe,registerId,registerIp,
     * @see registDt,updtDt,deleteAt,updtusrId
     * @see userCodeId,codeNm
     * @see TABLE NAME : TN_SYSTEM,TC_CODE
     */
    @RequestMapping(value="/gps/adm/menu/modifySysMenu.do")
	public String modifySysMenu(
			@ModelAttribute("systemVO") SystemVO systemVO,
			ModelMap model
			)throws Exception{
    	String rootSysId = systemVO.getRootSysId();
    	systemVO = (SystemVO)this.menuManageService.selectSystem(systemVO);
    	systemVO.setRootSysId(rootSysId);
    	model.addAttribute("systemVO", systemVO);
    	model.addAttribute("sysMenuList", cmmUseService.selectCmmCodeMap("21101314",""));	//시스템관리메뉴
		model.addAttribute("cntntsMenuList", cmmUseService.selectCmmCodeMap("21101315",""));	//컨텐츠관리메뉴
        return "gps/adm/menu/modifySysMenu";
	}
    
    
    /**
	 * 서브메뉴등록화면호출
     * @param menuManageVO 메뉴정보를 가지고있는VO
     * @param model ModelMap
     * @return "gps/adm/menu/registerSubMenu"
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/menu/registerSubMenu.do")
	public String registerSubMenu(
			@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
			ModelMap model
			) throws Exception {
    	String upperMenuId = menuManageVO.getMenuId();
    	String sysUseTy = menuManageVO.getSysUseTy();
    	MenuManageVO resultVO = new MenuManageVO();
    	// 메뉴번호를 메뉴상위번호로 변경(상위메뉴 부모키(UPPER_NO)로변경)
    	resultVO.setUpperMenuId(upperMenuId);
    	// 시스템관리메뉴구분값
    	resultVO.setSysUseTy(sysUseTy);
    	model.addAttribute("menuManageVO", resultVO);
        return "gps/adm/menu/registerSubMenu";
	}
    
    /**
	 * 서브메뉴수정화면호출
     * @param menuManageVO 메뉴정보를 가지고있는VO
     * @param model ModelMap
     * @return "gps/adm/menu/modifySubMenu"
     * @throws Exception
     * @see menuId,upperMenuId,menuNo,psitnCode,menuCode,menuOrdr,sysUseTy,menuNm,menuAbrv,menuEngNm,menuTy,menuSeCode,
     * @see menuSkin,menuSkinButton,menuUrl,menuUrlTarget,useAt,topMenuUseAt,leftMenuUseAt,bottomMenuUseAt,sitemapUseAt,
     * @see topImageNm,topImageMask,topImageCm,topImageMime,topImageSize,topImageMouseoverNm,topImageMouseoverMask,topImageMouseoverCm,
     * @see topImageMouseoverMime,topImageMouseoverSize,leftImageNm,leftImageMask,leftImageCm,leftImageMime,leftImageSize,
     * @see leftImageMouseoverNm,leftImageMouseoverMask,leftImageMouseoverCm,leftImageMouseoverMime,leftImageMouseoverSize,bottomImageNm,
     * @see bottomImageMask,bottomImageCm,bottomImageMime,bottomImageSize,bottomImageMouseoverNm,bottomImageMouseoverMask,bottomImageMouseoverCm,
     * @see bottomImageMouseoverMime,bottomImageMouseoverSize,sitemapImageNm,sitemapImageMask,sitemapImageCm,sitemapImageMime,
     * @see sitemapImageSize,sitemapImageMouseoverNm,sitemapImageMouseoverMask,sitemapImageMouseoverCm,sitemapImageMouseoverMime,
     * @see sitemapImageMouseoverSize,titleImageNm,titleImageMask,titleImageMime,titleImageSize,menuRm,programId,snstUseAt,
     * @see attachmentFileId,bbsId,bbsNm,popupWidth,popupHeight,popupTop,popupLeft,registerId,registerIp,registDt,
     * @see updtDt,deleteAt,updtusrId,csnstId
     * @see TABLE NAME : TN_MENU
     */
    @RequestMapping(value="/gps/adm/menu/modifySubMenu.do")
	public String modifySubMenu(
			@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
			ModelMap model
			) throws Exception {
    	
    	// 수정해야할 메뉴정보를 ModelMap에 추가
    	MenuManageVO menuVO = (MenuManageVO)this.menuManageService.selectMenu(menuManageVO);
    	menuVO.setRootSysId(menuManageVO.getRootSysId());
    	model.addAttribute("menuManageVO", menuVO);
    	model.addAttribute("WebImagePath",propertiesService.getString("WebImagePath"));
        return "gps/adm/menu/modifySubMenu";
	}
    
    /**
	 * 조사목록
	 * @param userManageVO
	 * @param response
	 * @param model
	 * @return "jsonView"
	 * @throws Exception
	 * @see	userId,nm,email
	 * @see TABLE NAME : TN_USER
	 */
	@RequestMapping(value="/gps/adm/menu/selectPrdctnList.do")
	public ModelAndView selectMngrList(
			@ModelAttribute("systemVO")SystemVO systemVO,
			HttpServletResponse response
			)throws Exception{
		ModelAndView model = new ModelAndView();
		List<SystemVO> prdctnList = (List<SystemVO>)this.menuManageService.selectPrdctnList(systemVO);
		if(prdctnList.size() > 0){
			for (SystemVO vo : prdctnList) {
				model.addObject("svyOdr",vo.getSvyOdr());
			}
		}
		model.setViewName("jsonView");
	    return model;
	}
    
    
    /**
	 * 시스템등록
     * @param systemVO 메뉴정보를 가지고있는VO
     * @param model ModelMap
     * @param request HttpServletRequest
     * @param bindingResult BindingResult
     * @param status SessionStatus
     * @return "gps/adm/menu/menuDefault"
     * @throws Exception
     * @see TABLE NAME : TN_SYSTEM
     */
	@RequestMapping(value="/gps/adm/menu/insertSystem.do")
	public String insertSystem(
			@ModelAttribute("systemVO") SystemVO systemVO,
			@ModelAttribute("igrAuthorManageVO") IgrAuthorManageVO igrAuthorManageVO,
			ModelMap model, 
			SessionStatus status,
			HttpServletRequest request
			) throws Exception {
		GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
		
		if(gpsSessionVO!=null){
			systemVO = (SystemVO)this.defaultDataSet.registSet(request, systemVO);
	    	// 권한 등록을 위한 사용자ID systemVO에 setting 
	    	systemVO.setUsrId(gpsSessionVO.getUsrId());
		}
    	// 시스템등록시 상위메뉴ID를 root sysId 로 setting
    	systemVO.setUpperSysId(systemVO.getRootSysId());
    	
    	igrAuthorManageVO = (IgrAuthorManageVO) defaultDataSet.registSet(request, igrAuthorManageVO);
    	
		// 시스템 등록처리
    	this.menuManageService.insertSystem(systemVO,igrAuthorManageVO);
    	//메타시스템 권한정보 동기화
		this.igrAuthorManageService.updateUmmAuthor();
    	status.setComplete();
    	
    	//메뉴등록처리메시지 ModelMap 에추가
    	model.addAttribute("message",this.egovMessageSource.getMessage("success.common.insert"));
        return "gps/adm/menu/menuDefault";
	}
	
	/**
	 * 시스템수정
     * @param request HttpServletRequest
     * @param systemVO 메뉴정보를 가지고있는VO
     * @param bindingResult BindingResult
     * @param model ModelMap
     * @param status SessionStatus
     * @return "gps/adm/menu/modifySysMenu"
     * @throws Exception
     * @see TABLE NAME : TN_SYSTEM
     */
    @RequestMapping(value="/gps/adm/menu/updateSystem.do")
	public String updateSystem(
			HttpServletRequest request,
			@ModelAttribute("systemVO") SystemVO systemVO,
			ModelMap model, 
			SessionStatus status
			) throws Exception {
    	
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	if(gpsSessionVO!=null){
    		systemVO = (SystemVO)this.defaultDataSet.updateSet(request, systemVO);
    		// 권한 등록을 위한 사용자ID systemVO에 setting 
        	systemVO.setUsrId(gpsSessionVO.getUsrId());
    	}
    	
    	//시스템 수정처리
    	this.menuManageService.updateSystem(systemVO);
    	//메타시스템 권한정보 동기화
		this.igrAuthorManageService.updateUmmAuthor();
    	status.setComplete();
    	
    	// 메뉴수정처리메시지 ModelMap 에추가
    	model.addAttribute("message",this.egovMessageSource.getMessage("success.common.update"));
    	return "forward:/gps/adm/menu/modifySysMenu.do";
	}
    
    /**
	 *시스템삭제
     * @param systemVO 메뉴정보를 가지고있는VO
     * @param model ModelMap
     * @return "gps/adm/menu/menuDefault"
     * @throws Exception
     * @see TABLE NAME : TN_SYSTEM
     */
    @RequestMapping(value="/gps/adm/menu/deleteSystem.do")
	public String deleteSystem(
			@ModelAttribute("systemVO") SystemVO systemVO,
			ModelMap model
			) throws Exception {
    	
    	// 시스템삭제처리
    	this.menuManageService.updateDeleteAtSystem(systemVO);
    	//메타시스템 권한정보 동기화
		this.igrAuthorManageService.updateUmmAuthor();
    	// 메뉴삭제처리메시지 ModelMap 에추가
    	model.addAttribute("message",this.egovMessageSource.getMessage("success.common.delete"));
        return "gps/adm/menu/menuDefault";
	}
    
    /**
	 * 메뉴등록
     * @param menuManageVO 메뉴정보를 가지고있는VO
     * @param model ModelMap
     * @param request HttpServletRequest
     * @param bindingResult BindingResult
     * @param status SessionStatus
     * @return "gps/adm/menu/menuDefault"
     * @throws Exception
     * @see TABLE NAME : TN_MENU
     */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/gps/adm/menu/insertMenu.do")
	public String insertMenu(
			final MultipartHttpServletRequest multiRequest,
			@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
			ModelMap model, 
			SessionStatus status,
			HttpServletRequest request
			) throws Exception {
		//첨부파일 확장자검사
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file = null;

		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();
		
			file = entry.getValue();
			if (!"".equals(file.getOriginalFilename())) {
				if( entry.getKey().indexOf("file_") > -1  ) {//첨부파일 확장자 검사
					if(StringUtil.checkFileExt(file.getOriginalFilename())){
						model.addAttribute("message",this.egovMessageSource.getMessage("fail.common.filenameext"));
						return "gps/adm/menu/registerSubMenu";
					}
				}else{										//메뉴 이미지파일 확장자 검사
					if(!StringUtil.checkImgFileExt(file.getOriginalFilename())){
						model.addAttribute("message",this.egovMessageSource.getMessage("fail.common.filenameext"));
						return "gps/adm/menu/registerSubMenu";
					}
				}
			}
		}
		/*
		if(menuManageVO.getMenuTy().equals("B")){
			MenuManageVO vo = new MenuManageVO();
			vo = (MenuManageVO)this.menuManageService.selectSameBbsId(menuManageVO);
			if(vo != null){
				model.addAttribute("message",vo.getBbsNm()+" 게시판은 "+vo.getMenuNm()+" 메뉴에서 이미 사용중입니다.");
				return "gps/adm/menu/registerSubMenu";
			}
		}
		*/
		menuManageVO = (MenuManageVO)this.defaultDataSet.registSet(request, menuManageVO);
		
		GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	if(gpsSessionVO!=null){
    		menuManageVO.setUsrId(gpsSessionVO.getUsrId());
    	}
		
    	
    	
		// 메뉴등록처리
    	this.menuManageService.insertMenu(menuManageVO,multiRequest);
    	//메타시스템 권한정보 동기화
		this.igrAuthorManageService.updateUmmAuthor();
    	status.setComplete();
    	
    	//메뉴등록처리메시지 ModelMap 에추가
    	model.addAttribute("message",this.egovMessageSource.getMessage("success.common.insert"));
        return "gps/adm/menu/menuDefault";
	}
    
    /**
	 * 메뉴수정
     * @param multiRequest MultipartHttpServletRequest
     * @param request HttpServletRequest
     * @param menuManageVO 메뉴정보를 가지고있는VO
     * @param status SessionStatus
     * @return "gps/adm/menu/modifySubMenu"
     * @throws Exception
     * @see TABLE NAME : TN_MENU
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/gps/adm/menu/updateMenu.do")
	public String updateMenu(
			final MultipartHttpServletRequest multiRequest,
			HttpServletRequest request,
			@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
			ModelMap model, 
			SessionStatus status
			) throws Exception {
    	
    	//첨부파일 확장자검사
    	final Map<String, MultipartFile> files = multiRequest.getFileMap();
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file = null;
		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();
		
			file = entry.getValue();
			if (!"".equals(file.getOriginalFilename())) {
				if( entry.getKey().indexOf("file_") > -1  ) {//첨부파일 확장자 검사
					if(StringUtil.checkFileExt(file.getOriginalFilename())){
						model.addAttribute("message",this.egovMessageSource.getMessage("fail.common.filenameext"));
						return "gps/adm/menu/modifySubMenu";
					}
				}else{										//메뉴 이미지파일 확장자 검사
					if(!StringUtil.checkImgFileExt(file.getOriginalFilename())){
						model.addAttribute("message",this.egovMessageSource.getMessage("fail.common.filenameext"));
						return "gps/adm/menu/modifySubMenu";
					}
				}
			}
		}
		/*
    	if(menuManageVO.getMenuTy().equals("B")){
			MenuManageVO vo = new MenuManageVO();
			vo = (MenuManageVO)this.menuManageService.selectSameBbsId(menuManageVO);
			if(vo != null){
				if(!vo.getMenuId().equals(menuManageVO.getMenuId())){
				model.addAttribute("message",vo.getBbsNm()+" 게시판은 "+vo.getMenuNm()+" 메뉴에서 이미 사용중입니다.");
				return "gps/adm/menu/modifySubMenu";
				}
			}
		}
    	*/
    	menuManageVO = (MenuManageVO)this.defaultDataSet.updateSet(request, menuManageVO);
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	if(gpsSessionVO!=null){
    		menuManageVO.setUsrId(gpsSessionVO.getUsrId());
    	}
    	// 메뉴수정처리
    	this.menuManageService.updateMenu(menuManageVO,multiRequest);
    	//메타시스템 권한정보 동기화
		this.igrAuthorManageService.updateUmmAuthor();
    	status.setComplete();
    	
    	// 메뉴수정처리메시지 ModelMap 에추가
    	model.addAttribute("message",this.egovMessageSource.getMessage("success.common.update"));
    	return "forward:/gps/adm/menu/modifySubMenu.do";
	}
    
    /**
	 * 메뉴삭제
     * @param menuManageVO 메뉴정보를 가지고있는VO
     * @param model ModelMap
     * @return "gps/adm/menu/menuDefault"
     * @throws Exception
     * @see TABLE NAME : TN_MENU
     */
    @RequestMapping(value="/gps/adm/menu/deleteMenu.do")
	public String deleteMenu(
			@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
			ModelMap model
			) throws Exception {
    	
    	// 메뉴삭제처리
    	this.menuManageService.deleteMenu(menuManageVO);
    	//메타시스템 권한정보 동기화
		this.igrAuthorManageService.updateUmmAuthor();
    	// 메뉴삭제처리메시지 ModelMap 에추가
    	model.addAttribute("message",this.egovMessageSource.getMessage("success.common.delete"));
        return "gps/adm/menu/menuDefault";
	}
    
    /**
	 * 이미지삭제처리
     * @param menuManageVO 메뉴정보를 가지고있는VO
     * @param model ModelMap
     * @return "forward:/gps/adm/menu/modifySubMenu.do"
     * @throws Exception
     * @see TABLE NAME : TN_MENU
     */
    @RequestMapping(value="/gps/adm/menu/deleteImgFile.do")
	public String deleteImgFile(
			@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
			ModelMap model
			) throws Exception {
    	
    	if(menuManageVO.getDeleteImgSe() > 0){
    	// 메뉴테이블 이미지파일 정보 삭제
    	this.menuManageService.deleteImgFile(menuManageVO);
    	}
        return "forward:/gps/adm/menu/modifySubMenu.do";
	}
    
    
    /**
	 * 프로그램 목록 조회 팝업
     * @param programManageVO 조회할 프로그램정보를 가지고있는VO
     * @return "gps/adm/menu/programListPopup"
     * @throws Exception
     * @see PROGRAM_ID, PROGRAM_FILE_NM, PROGRAM_STRE_PATH, PROGRAM_KOREANNM, URL, PROGRAM_DC, PROGRAM_TY_READ, PROGRAM_TY_CREATE,
     * @see PROGRAM_TY_UPDATE, PROGRAM_TY_DELETE, SYS_ID,SYS_NM,REGISTER_IP, REGIST_DT, REGISTER_ID, UPDT_DT, UPDTUSR_ID
     * @see TABLE NAME : TN_PROGRAM,TN_SYSTEM
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/gps/adm/menu/programListPopup.do")
	public String programListPopup(
    	@ModelAttribute("programManageVO") ProgramManageVO programManageVO
        , HttpServletRequest request
		, ModelMap model
		) throws Exception {
	
		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(programManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(programManageVO.getPageUnit());
		paginationInfo.setPageSize(programManageVO.getPageSize());
		
		programManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		programManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		programManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
	    List<ProgramManageVO> programList = (List<ProgramManageVO>)this.programManageService.selectProgramList(programManageVO);
	    model.addAttribute("resultList", programList);
	    
	    int totCnt = programManageService.selectProgramListTotCnt(programManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
	    model.addAttribute("paginationInfo", paginationInfo);
	    model.addAttribute("programManageVO", programManageVO);

    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
        // System 유형
    	model.addAttribute("sysInfo",systemManageService.selectSysInfoList(gpsSessionVO.getUsrId()));
        return "gps/adm/menu/programListPopup";
	}
    
    /**
	 * 게시판 목록 조회 팝업
     * @return "gps/adm/menu/boardListPopup"
     * @throws Exception
     * @see sysId,sysNm
     * @see TABLE NAME : TN_SYSTEM ,TN_AUTHOR ,TN_USER_AUTHOR 
     */
	@RequestMapping(value="/gps/adm/menu/boardListPopup.do")
	public String boardListPopup(
			@ModelAttribute("bbsSearchVO")BbsSearchVO bbsSearchVO,
			HttpServletRequest request,
			ModelMap model
			)throws Exception{
		//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(bbsSearchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(bbsSearchVO.getPageUnit());
		paginationInfo.setPageSize(bbsSearchVO.getPageSize());
		
		bbsSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		bbsSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		bbsSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

    	if(gpsSessionVO!=null){
    		bbsSearchVO.setUsrId(gpsSessionVO.getUsrId());
    		//시스템목록
			List<SystemVO> systemList = (List<SystemVO>)this.bbsManageService.selectSystemList(bbsSearchVO);
			model.addAttribute("systemList", systemList);
    	
			//총갯수
			paginationInfo.setTotalRecordCount(this.bbsManageService.selectBbsInfoListTotCnt(bbsSearchVO));
			//목록
			model.addAttribute("bbsInfoList",(List<BbsInfoVO>)this.bbsManageService.selectBbsInfoList(bbsSearchVO));
			model.addAttribute("paginationInfo", paginationInfo);
    	}
    	
		return "gps/adm/menu/boardListPopup";
	}
	
    /**
     * 만족도목록 조회 
     * @param csnstManageVO 검색조건
     * @param model ModelMap
     * @return 목록화면 JSP로 이동 ("gps/adm/menu/csnstList") 
     * @throws Exception
     * @see CSNST_ID, CSNST_SN, SYS_ID, SYS_NM, CSNST_SE, CSNST_NM, CSNST_DN, CSNST_USE_AT, VALID_BGNDE,
     * @see VALID_ENDDE, CSNST_OTHBCSE, CSNST_PASSWORD, CSNST_DPLCT_PERM_SE, CSNST_MEMO_USE_AT, CSNST_MEMO_WEBEDIT_SE,
     * @see CSNST_MEMO_AUTHOR_SE, CSNST_FILE_NM, CSNST_FILE_MASK, CSNST_FILE_SIZE, CSNST_FILE_MIME, CSNST_FILE_DC,
     * @see REGIST_DT, REGISTER_ID, REGISTER_IP, UPDT_DT, UPDTUSR_ID, QESITM_SN
     * @see TABLE NAME : TN_SYSTEM,TN_CSNST_MANAGE
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/gps/adm/menu/selectCsnstList.do")
	public String selectCsnstList (@ModelAttribute("csnstManageVO") CsnstManageVO csnstManageVO
			, ModelMap model
			, HttpServletRequest request
			) throws Exception {

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(csnstManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(csnstManageVO.getPageUnit());
		paginationInfo.setPageSize(csnstManageVO.getPageSize());
		
		csnstManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		csnstManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		csnstManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		// 권한설정
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
		csnstManageVO.setUserId(gpsSessionVO.getUsrId());
		
		List list = csnstManageService.selectCsnstList(csnstManageVO);
        model.addAttribute("resultList", list);
        
		paginationInfo.setTotalRecordCount(list.size());
		
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("csnstManageVO", csnstManageVO);
        
        // System 유형
    	model.addAttribute("sysInfo",systemManageService.selectSysInfoList(gpsSessionVO.getUsrId()));
    	
        return "gps/adm/menu/csnstListPopup";
	}

    /**
     * 만족도조사 등록/변경 
     * @param request
     * @param menuManageVO
     * @param model
     * @param status
     * @return 목록화면 JSP로 이동 ("forward:/gps/adm/menu/selectCsnstList.do") 
     * @throws Exception
     * @see TABLE NAME : TN_CSNST_MANAGE
     */
    @RequestMapping(value="/gps/adm/menu/registCsnstId.do")
	public String registCsnstId(
			HttpServletRequest request,
			@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
			ModelMap model, 
			SessionStatus status
			) throws Exception {
    	
    	// 메뉴수정처리
    	this.menuManageService.registCsnstId(menuManageVO);
    	
    	status.setComplete();
    	
    	// 메뉴수정처리메시지 ModelMap 에추가
    	model.addAttribute("message",this.egovMessageSource.getMessage("gps.menucsnstid.regist"));
    	return "forward:/gps/adm/menu/selectCsnstList.do";
	}
    
}
