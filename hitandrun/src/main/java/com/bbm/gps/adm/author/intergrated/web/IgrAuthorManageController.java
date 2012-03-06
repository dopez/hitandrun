package com.bbm.gps.adm.author.intergrated.web;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import com.bbm.common.cmm.EgovMessageSource;
import com.bbm.common.util.cas.service.EgovSessionCookieUtil;
import com.bbm.gps.adm.author.intergrated.service.IgrAuthorManageService;
import com.bbm.gps.adm.author.intergrated.service.IgrAuthorManageVO;
import com.bbm.gps.adm.author.intergrated.service.IgrUserAuthorManageVO;
import com.bbm.gps.adm.menu.service.MenuManageService;
import com.bbm.gps.adm.menu.service.MenuManageVO;
import com.bbm.gps.cmm.DefaultDataSet;
import com.bbm.gps.login.service.GpsSessionVO;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/** 
 * 권한관리 비즈니스 구현 클래스
 * <p><b>NOTE:</b> 권한관리 화면과 직접 연계되는 컨트롤러 클래스로 주로 JSP화면에서 호출되거나 
 * 해당 클래스 내부에서 호출되는 메소드들이 정의되어 있다
 * 해당 클래스 중에는 비즈니스 로직이 없고 단순히 다른 JSP화면으로 forword 시켜주는 메소드도 존재한다
 * 권한정보 조회,입력,수정,삭제 요청을 처리한다
 * @author 범정부통계포털 이관형 
 * @since 2011.06.27 
 * @version 1.0 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.06.27     이관형       최초 생성 
 * 
 * </pre> 
 */

@Controller
public class IgrAuthorManageController {

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
	/** IgrAuthorManageService 서비스 호출 */ 
	@Resource(name = "igrAuthorManageService")
    private IgrAuthorManageService igrAuthorManageService;
	
	/** menuManageService */
    @Resource(name="menuManageService")
    protected MenuManageService menuManageService;
    
	/** defaultDataSet */
    @Resource(name="defaultDataSet")
    protected DefaultDataSet defaultDataSet;
    
	/** LOG */ 
	protected Log log = LogFactory.getLog(this.getClass());

    /**
	 * 권한관리 메인페이지 호출
     * @param authorManageVO IgrAuthorManageVO
     * @return "/gps/adm/author/intergrated/selectIamManageMain.do" 권한관리 메인페이지 URL
     * @exception Exception
     */
    @RequestMapping(value="/gps/adm/author/intergrated/selectIamManageMain.do")
	public String iamManageMain(
				@ModelAttribute("authorManageVO") IgrAuthorManageVO authorManageVO
				, ModelMap model
				) throws Exception {
    	model.addAttribute("authorManageVO", authorManageVO);
        return "gps/adm/author/intergrated/iamManageMain";
	}

    /**
	 * 권한관리 기본페이지 호출
     * @param authorManageVO IgrAuthorManageVO
     * @return "gps/adm/author/intergrated/iamDefaultList" 메뉴관리 기본페이지 URL
     * @exception Exception
     */
    @RequestMapping(value="/gps/adm/author/intergrated/selectIamDefault.do")
	public String iamDefault(
				@ModelAttribute("authorManageVO") IgrAuthorManageVO authorManageVO
				) throws Exception {
        return "gps/adm/author/intergrated/iamDefaultList";
	}
    
    /**
	 * 권한정보목록을 조회한다.
     * @param authorManageVO IgrAuthorManageVO
     * @param request HttpServletRequest
     * @param model ModelMap
     * @return "gps/adm/author/intergrated/iamList" 권한목록페이지
     * @throws Exception
     * @see LEVEL, SYS_ID, SYS_NM, AUTHOR_ID, AUTHOR_NM, UPPER_AUTHOR_ID
	 * @see TABLE NAME : TN_AUTHOR
     */
    @RequestMapping(value="/gps/adm/author/intergrated/selectIamList.do")
	public String iamList( 
			@ModelAttribute("authorManageVO") IgrAuthorManageVO authorManageVO,
			HttpServletRequest request,
			ModelMap model
			)throws Exception {
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	authorManageVO.setUserId(gpsSessionVO.getUsrId());
    	
    	// 20111108 - 권한에 기관ID 묶음 START
    	authorManageVO.setUserId(gpsSessionVO.getUsrId());
    	
		if (authorManageVO.getSysId().isEmpty() || authorManageVO.getSysId() == null) {
			authorManageVO.setSysId(authorManageVO.getUpperMenuId());
		}
    	List<IgrAuthorManageVO> authorList = (List<IgrAuthorManageVO>) this.igrAuthorManageService.selectIamList(authorManageVO);
    	// 20111108 - 권한에 기관ID 묶음 END
    	

    	// 메뉴목록
    	model.addAttribute("authorList", authorList);

	    // ** 디테일 구현부분 :(저장후 리스트화면에 저장된 권한의 영역(이름)을 표시하기위함) START
    	if (null != authorManageVO.getAuthorNm()){
	    	authorManageVO.setAuthorNm(URLDecoder.decode(authorManageVO.getAuthorNm(),"UTF-8"));
    	}
	    // ** 디테일 구현부분 END
    	
        return "gps/adm/author/intergrated/iamList";
	}
    
    /**
	 * 권한정보에 대한 상세화면을 조회한다.
     * @param authorManageVO IgrAuthorManageVO
     * @param model ModelMap
     * @return "gps/adm/author/intergrated/iamUpdate" 권한정보 조회/수정페이지
     * @throws Exception
     * @see AUTHOR_ID, SYS_ID, SYS_NM, AUTHOR_CODE, AUTHOR_NM, AUTHOR_DC, REGIST_DT, REGISTER_ID
     * @see REGISTER_IP, UPDT_DT, UPDTUSR_ID, UPPER_AUTHOR_NM
	 * @see TABLE NAME : TN_AUTHOR
     */
    @RequestMapping(value="/gps/adm/author/intergrated/modifyIam.do")
	public String modifyIam (
			@ModelAttribute("authorManageVO") IgrAuthorManageVO authorManageVO,
    		ModelMap model
    ) throws Exception {

    	String tempUpperMenuId = authorManageVO.getUpperMenuId();
    	authorManageVO = igrAuthorManageService.selectIam(authorManageVO);
    	authorManageVO.setUpperMenuId(tempUpperMenuId);

		model.addAttribute("authorManageVO", authorManageVO);
        return "gps/adm/author/intergrated/iamUpdate";
    }

    /**
     * 권한정보를 변경한다.
     * @param authorManageVO IgrAuthorManageVO
     * @param request HttpServletRequest
     * @param model ModelMap
     * @return "forward:/gps/adm/author/intergrated/selectIamMenuList.do" 메뉴권한리스트페이지 URL
     * @throws Exception
     * @see AUTHOR_ID
	 * @see TABLE NAME : TN_AUTHOR
     */
    @RequestMapping(value="/gps/adm/author/intergrated/updateIam.do")
	public String updateIam (
			@ModelAttribute("authorManageVO") IgrAuthorManageVO authorManageVO,
			HttpServletRequest request,
    		ModelMap model
    ) throws Exception {
    	
    	authorManageVO = (IgrAuthorManageVO) defaultDataSet.updateSet(request, authorManageVO);
    	igrAuthorManageService.updateAuthor(authorManageVO);
    	//메타시스템 권한정보 동기화
		igrAuthorManageService.updateUmmAuthor();
	    model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
        return "forward:/gps/adm/author/intergrated/selectIamMenuList.do";
    }

    /**
     * 권한정보를 삭제한다.
     * @param authorManageVO IgrAuthorManageVO
     * @param model ModelMap
     * @return "forward:/gps/adm/author/intergrated/selectIamDefault.do" 권한 기본페이지 URL
     * @throws Exception
     * @see AUTHOR_ID
	 * @see TABLE NAME : TN_AUTHOR
     */
    @RequestMapping(value="/gps/adm/author/intergrated/deleteIam.do")
	public String deleteIam (
			@ModelAttribute("authorManageVO") IgrAuthorManageVO authorManageVO,
    		ModelMap model
    ) throws Exception {

		// 권한 삭제
    	igrAuthorManageService.deleteAuthor(authorManageVO);
    	IgrUserAuthorManageVO igrUserAuthorManageVO = new IgrUserAuthorManageVO();
    	igrUserAuthorManageVO.setAuthorId(authorManageVO.getAuthorId());
    	// 권한 사용자 삭제
    	igrAuthorManageService.deleteUserAuthor(igrUserAuthorManageVO);
    	//메타시스템 권한정보 동기화
		igrAuthorManageService.updateUmmAuthor();
	    model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));

        return "forward:/gps/adm/author/intergrated/selectIamDefault.do";
    }

    /**
     * 권한등록 페이지로 이동한다.
     * @param authorManageVO IgrAuthorManageVO
     * @param request request
     * @param model model
     * @return "gps/adm/author/intergrated/iamRegist" 권한 등록페이지
     * @throws Exception
     * @see SYS_ID, SYS_NM
	 * @see TABLE NAME : TN_AUTHOR
     */
    @RequestMapping(value="/gps/adm/author/intergrated/registerIam.do")
	public String iamRegister(@ModelAttribute("authorManageVO") IgrAuthorManageVO authorManageVO,
			HttpServletRequest request,
			ModelMap model
			)throws Exception {
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	authorManageVO.setUserId(gpsSessionVO.getUsrId());

        model.addAttribute("sysId", igrAuthorManageService.selectSysComboMap(authorManageVO));

        return "gps/adm/author/intergrated/iamRegist";
	}

    /**
     * 권한등록 처리
     * @param authorManageVO IgrAuthorManageVO
     * @param request HttpServletRequest
     * @param status SessionStatus
     * @param model ModelMap
     * @return "forward:/gps/adm/author/intergrated/selectIamMenuList.do" 권한메뉴리스트페이지 URL
     * @throws Exception
     * @see AUTHOR_ID, SYS_ID, AUTHOR_CODE, AUTHOR_NM, AUTHOR_DC, REGIST_DT, REGISTER_ID, REGISTER_IP
     * @see UPDT_DT, UPDTUSR_ID, UPPER_AUTHOR_ID
	 * @see TABLE NAME : TN_AUTHOR
     */
    @RequestMapping(value="/gps/adm/author/intergrated/iamInsert.do")
    public String iamInsert(@ModelAttribute("authorManageVO") IgrAuthorManageVO authorManageVO, 
								HttpServletRequest request,
    		                    SessionStatus status, 
    		                    ModelMap model) throws Exception {
    	
		// 상위코드를 선택하지 않았을 경우(시스템만 선택하였을 경우), 상위 권한 생성
    	if (authorManageVO.getUpperAuthorId().isEmpty()) {

    		authorManageVO.setSearchCondition("1");
        	String authorId = igrAuthorManageService.authorIdGeneration(authorManageVO);
        	
    		if (authorId.equals("0")) {
    			// 첫 권한생성 패턴은 없을것이지만, SystemException이 발생할것을 대비해 초기데이타 설정
    			authorManageVO.setAuthorId("10000");
    		} else {
    			// 같은 레벨의 권한ID MAX값
        		authorManageVO.setAuthorId(authorId);
    		}

    	// 상위권한를 선택하였을 경우, 선택한 권한은 상위 권한으로 간주하고 선택된 권한의 하위 권한을 생성
    	} else {
    		
    		authorManageVO.setSearchCondition("2");
    		String authorId = igrAuthorManageService.authorIdGeneration(authorManageVO);
        	
    		if (authorId.equals("0")) {
    			// 새로운 하위 권한 생성시 상위권한 + [001]의 초기값 부여
    			authorManageVO.setAuthorId(authorManageVO.getUpperAuthorId().concat("001"));
    		} else {
    			// 같은 레벨의 권한이 존재할경우  기존의 하위권한ID의MAX값
    			authorManageVO.setAuthorId(authorId);
    		}
    	}
    	authorManageVO = (IgrAuthorManageVO) defaultDataSet.registSet(request, authorManageVO);
    	authorManageVO.setAuthorCode(authorManageVO.getAuthorId());
    	igrAuthorManageService.insertIam(authorManageVO);
    	//메타시스템 권한정보 동기화
		igrAuthorManageService.updateUmmAuthor();
	    status.setComplete();
	    
	    model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
	    
	    // ** 디테일 구현부분 :(저장후 리스트화면에 저장된 권한의 영역을 표시하기위함) START
	    model.addAttribute("authorManageVO", authorManageVO);
	    request.setAttribute("authorId", authorManageVO.getAuthorId());
	    // ** 디테일 구현부문 END
	    
	    return "forward:/gps/adm/author/intergrated/selectIamMenuList.do";

    }
    
    /**
     * 메뉴권한 리스트화면을 표시한다.
     * @param authorManageVO IgrAuthorManageVO
     * @param menuManageVO MenuManageVO
     * @param request HttpServletRequest
     * @param model ModelMap
     * @return "gps/adm/author/intergrated/iamMenuList" 메뉴 권한목록 페이지
     * @throws Exception
     * @see menuLv, menuNo, menuNm, menuTy, leaf, ulOpenAt, endTagCnt, menuAuthorAt
     * @see TABLE NAME : TN_MENU, TN_MENU_AUTHOR
     */
    @RequestMapping(value="/gps/adm/author/intergrated/selectIamMenuList.do")
	public String iamMenuList( 
			@ModelAttribute("authorManageVO") IgrAuthorManageVO authorManageVO,
			@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
			HttpServletRequest request,
			ModelMap model
			)throws Exception {

	    // ** 디테일 구현부분 :(저장후 리스트화면에 저장된 권한의 영역(이름)을 표시하기위함) START
    	if (null != authorManageVO.getAuthorNm()){
	    	authorManageVO.setAuthorNm(URLDecoder.decode(authorManageVO.getAuthorNm(),"UTF-8"));
    	}
	    // ** 디테일 구현부분 END
    	
	    // ** 디테일 구현부분:(저장후 리스트화면에 저장된 권한의 영역(진한글)을 표시하기위함) START
    	if (null != request.getAttribute("authorId")) {
    		authorManageVO.setAuthorId(String.valueOf(request.getAttribute("authorId")));
    	}
	    // ** 디테일 구현부문 END
    	
    	menuManageVO.setAuthorId(authorManageVO.getAuthorId());
    	menuManageVO.setMenuId(authorManageVO.getSysId());
    	//권한메뉴 여부
    	menuManageVO.setAuthorMenuAt("Y");
    	// 메뉴목록
    	model.addAttribute("menuList",(List<MenuManageVO>)this.menuManageService.selectAuthorMenuList(menuManageVO));
        return "gps/adm/author/intergrated/iamMenuList";
	}

    /**
	 * 메뉴권한등록 화면호출
     * @param menuManageVO MenuManageVO
     * @param request HttpServletRequest
     * @param model ModelMap
     * @return "gps/adm/author/intergrated/registerMenuAuthor" 메뉴 권한등록 화면
     * @throws Exception
     * @see menuLv, menuNo, menuNm, menuTy, leaf, ulOpenAt, endTagCnt, menuAuthorAt
     * @see TABLE NAME : TN_MENU, TN_MENU_AUTHOR
     */
    @RequestMapping(value="/gps/adm/author/intergrated/registerMenuAuthor.do")
	public String registerMenuAuthor(
			@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
			HttpServletRequest request,
			ModelMap model
			)throws Exception {
    	
	    // ** 디테일 구현부분 :(저장후 리스트화면에 저장된 권한의 영역(이름)을 표시하기위함) START
    	if (null != menuManageVO.getAuthorNm()){
    		menuManageVO.setAuthorNm(URLDecoder.decode(menuManageVO.getAuthorNm(),"UTF-8"));
    	}
	    // ** 디테일 구현부분 END
    	
    	//권한메뉴 여부
    	menuManageVO.setAuthorMenuAt("N");
    	// 메뉴목록
    	model.addAttribute("menuList",(List<MenuManageVO>)this.menuManageService.selectAuthorMenuList(menuManageVO));
        return "gps/adm/author/intergrated/registerMenuAuthor";
	}
    
    /**
	 * 메뉴권한등록처리
     * @param menuManageVO
     * @param request
     * @param ststus
     * @param model
     * @return "forward:/gps/adm/author/intergrated/registerMenuAuthor.do" 메뉴등록화면페이지 URL
     * @throws Exception
     * @see MENU_NO, AUTHOR_ID
	 * @see TABLE NAME : TN_MENU_AUTHOR
     */
    @RequestMapping(value="/gps/adm/author/intergrated/insertMenuAuthor.do")
	public String insertMenuAuthor(
			@ModelAttribute("menuManageVO") MenuManageVO menuManageVO,
			HttpServletRequest request,
			SessionStatus ststus,
			ModelMap model
			)throws Exception {
    	// 메뉴권한등록처리
    	menuManageVO = (MenuManageVO)defaultDataSet.registSet(request, menuManageVO);
    	this.igrAuthorManageService.insertMenuAuthor(menuManageVO);
    	//메타시스템 권한정보 동기화
		igrAuthorManageService.updateUmmAuthor();
    	ststus.setComplete();
    	model.addAttribute("message",egovMessageSource.getMessage("success.common.insert"));
        return "forward:/gps/adm/author/intergrated/registerMenuAuthor.do";
	}

    /**
	 * 사용자권한등록 처리
     * @param igrUserAuthorManageVO
     * @param request HttpServletRequest
     * @param ststus SessionStatus
     * @param model ModelMap
     * @return "forward:/gps/adm/author/intergrated/selectIamAllUserList.do" 사용자 목록 페이지 URL
     * @throws Exception
     * @see USER_ID, AUTHOR_ID, USER_AUTHOR_ID, REGIST_DT, REGISTER_ID, REGISTER_IP, UPDT_DT, UPDTUSR_ID
	 * @see TABLE NAME : TN_USER_AUTHOR
     */
    @RequestMapping(value="/gps/adm/author/intergrated/insertUserAuthor.do")
	public String insertUserAuthor(
			@ModelAttribute("igrUserAuthorManageVO") IgrUserAuthorManageVO igrUserAuthorManageVO,
			HttpServletRequest request,
			SessionStatus ststus,
			ModelMap model
			)throws Exception {
    	
    	igrUserAuthorManageVO = (IgrUserAuthorManageVO)defaultDataSet.registSet(request, igrUserAuthorManageVO);
    	igrUserAuthorManageVO.setUserAuthorId(Integer.toString(igrAuthorManageService.userAuthorIdGeneration(igrUserAuthorManageVO)));
    	
    	// 메뉴권한등록처리
    	igrAuthorManageService.insertUserAuthor(igrUserAuthorManageVO);
    	//메타시스템 권한정보 동기화
		igrAuthorManageService.updateUmmAuthor();
    	ststus.setComplete();
    	model.addAttribute("message",egovMessageSource.getMessage("success.common.insert"));
        return "forward:/gps/adm/author/intergrated/selectIamAllUserList.do";
	}
    
    /**
	 * 사용자권한 다중 등록 처리
     * @param igrUserAuthorManageVO IgrUserAuthorManageVO
     * @param request HttpServletRequest
     * @param ststus SessionStatus
     * @param model ModelMap
     * @return "forward:/gps/adm/author/intergrated/selectIamAllUserList.do" 사용자 목록 페이지 URL
     * @throws Exception
     * @see USER_ID, AUTHOR_ID, USER_AUTHOR_ID, REGIST_DT, REGISTER_ID, REGISTER_IP, UPDT_DT, UPDTUSR_ID
	 * @see TABLE NAME : TN_USER_AUTHOR
     */
    @RequestMapping(value="/gps/adm/author/intergrated/insertUserAuthorList.do")
	public String insertUserAuthorList (
			@ModelAttribute("igrUserAuthorManageVO") IgrUserAuthorManageVO igrUserAuthorManageVO,
			HttpServletRequest request,
			SessionStatus ststus,
			ModelMap model
			) throws Exception {
    	
    	StringTokenizer userIdList = new StringTokenizer(igrUserAuthorManageVO.getUserIdList(), ",");
    	igrUserAuthorManageVO = (IgrUserAuthorManageVO)defaultDataSet.registSet(request, igrUserAuthorManageVO);
    	
		while(userIdList.hasMoreTokens() )
		{
			igrUserAuthorManageVO.setUserId(userIdList.nextToken());
	    	igrUserAuthorManageVO.setUserAuthorId(Integer.toString(igrAuthorManageService.userAuthorIdGeneration(igrUserAuthorManageVO)));
			igrAuthorManageService.insertUserAuthor(igrUserAuthorManageVO);
		}	    	
		//메타시스템 권한정보 동기화
		igrAuthorManageService.updateUmmAuthor();
    	ststus.setComplete();
    	model.addAttribute("message",egovMessageSource.getMessage("success.common.insert"));
    	
    	return "forward:/gps/adm/author/intergrated/selectIamAllUserList.do";
	}

    /**
     * 사용자의권한정보를 삭제한다.
     * @param igrUserAuthorManageVO IgrUserAuthorManageVO
     * @param request HttpServletRequest
     * @param ststus SessionStatus
     * @param model ModelMap
     * @return "forward:/gps/adm/author/intergrated/selectIamAllUserList.do" 사용자 목록 페이지 URL
     * @throws Exception
     * @see AUTHOR_ID
	 * @see TABLE NAME : TN_USER_AUTHOR
     */
    @RequestMapping(value="/gps/adm/author/intergrated/deleteUserAuthor.do")
	public String deleteUserAuthor(
			@ModelAttribute("igrUserAuthorManageVO") IgrUserAuthorManageVO igrUserAuthorManageVO,
			HttpServletRequest request,
			SessionStatus ststus,
			ModelMap model
			)throws Exception {
    	
    	// 사용자권한 삭제처리
    	igrAuthorManageService.deleteUserAuthor(igrUserAuthorManageVO);
    	//메타시스템 권한정보 동기화
		igrAuthorManageService.updateUmmAuthor();
    	ststus.setComplete();
    	model.addAttribute("message",egovMessageSource.getMessage("success.common.delete"));
        return "forward:/gps/adm/author/intergrated/selectIamUserList.do";
	}

    /**
     * 사용자의권한정보를 삭제한다.
     * @param igrUserAuthorManageVO
     * @param request
     * @param ststus
     * @param model
     * @return "forward:/gps/adm/author/intergrated/selectIamAllUserList.do" 사용자 목록 페이지 URL
     * @throws Exception
     * @see AUTHOR_ID
	 * @see TABLE NAME : TN_USER_AUTHOR
     */
    @RequestMapping(value="/gps/adm/author/intergrated/deleteUserAuthorList.do")
	public String deleteUserAuthorList (
			@ModelAttribute("igrUserAuthorManageVO") IgrUserAuthorManageVO igrUserAuthorManageVO,
			HttpServletRequest request,
			SessionStatus ststus,
			ModelMap model
			) throws Exception {
    	
    	StringTokenizer userAuthorIdList = new StringTokenizer(igrUserAuthorManageVO.getUserAuthorIdList(), ",");
		while(userAuthorIdList.hasMoreTokens() )
		{
			igrUserAuthorManageVO.setUserAuthorId(userAuthorIdList.nextToken());
			igrAuthorManageService.deleteUserAuthor(igrUserAuthorManageVO);
		}	   
		//메타시스템 권한정보 동기화
		igrAuthorManageService.updateUmmAuthor();
    	ststus.setComplete();
    	model.addAttribute("message",egovMessageSource.getMessage("success.common.delete"));
    	return "forward:/gps/adm/author/intergrated/selectIamUserList.do";
	}
    
    /**
     * 사용자의권한 목록을 조회한다.
     * @param authorManageVO IgrAuthorManageVO
     * @param igrUserAuthorManageVO IgrAuthorManageVO
     * @param request HttpServletRequest
     * @param model ModelMap
     * @return "gps/adm/author/intergrated/iamUserList" 사용자 권한 목록 페이지
     * @throws Exception
     * @see USER_ID, NM, USER_AUTHOR_ID, AUTHOR_ID, AUTHOR_KIND
	 * @see TABLE NAME : TN_USER_AUTHOR
     */
    @RequestMapping(value="/gps/adm/author/intergrated/selectIamUserList.do")
	public String iamUserList( 
			@ModelAttribute("authorManageVO") IgrAuthorManageVO authorManageVO,
			@ModelAttribute("igrUserAuthorManageVO") IgrUserAuthorManageVO igrUserAuthorManageVO,
			HttpServletRequest request,
			ModelMap model
			)throws Exception {

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(igrUserAuthorManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(igrUserAuthorManageVO.getPageUnit());
		paginationInfo.setPageSize(igrUserAuthorManageVO.getPageSize());
		
		igrUserAuthorManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		igrUserAuthorManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		igrUserAuthorManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		log.debug("getAuthorId:" + igrUserAuthorManageVO.getAuthorId());

	    // ** 디테일 구현부분 :(저장후 리스트화면에 저장된 권한의 영역(이름)을 표시하기위함) START
    	if (null != authorManageVO.getAuthorNm()){
	    	authorManageVO.setAuthorNm(URLDecoder.decode(authorManageVO.getAuthorNm(),"UTF-8"));
    	}
	    // ** 디테일 구현부분 END
    	
    	// 20111108 - 권한에 기관ID 묶음 START
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	authorManageVO.setUserId(gpsSessionVO.getUsrId());
    	int authorLevel = igrAuthorManageService.selectAuthorLevel(authorManageVO);
    	
    	if (authorLevel > 10000) {
    		igrUserAuthorManageVO.setAuthorLevel("2");
    		igrUserAuthorManageVO.setOrgId(gpsSessionVO.getOrgId());
    	}
    	// 20111108 - 권한에 기관ID 묶음 END
    	
        model.addAttribute("resultList", igrAuthorManageService.selectUserAuthorList(igrUserAuthorManageVO));
        
		paginationInfo.setTotalRecordCount(igrAuthorManageService.selectUserAuthorListTotCnt(igrUserAuthorManageVO));
		
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("igrUserAuthorManageVO", igrUserAuthorManageVO);
        
    	// 메뉴목록
        return "gps/adm/author/intergrated/iamUserList";
	}

    /**
	 * 전체 사용자 목록을 조회한다.
     * @param igrUserAuthorManageVO IgrUserAuthorManageVO
     * @param request HttpServletRequest
     * @param model ModelMap
     * @return "gps/adm/author/intergrated/iamAllUserList" 전체 사용자 목록 페이지
     * @throws Exception
     * @see USER_ID, NM
	 * @see TABLE NAME : TN_USER
     */
    @RequestMapping(value="/gps/adm/author/intergrated/selectIamAllUserList.do")
	public String iamAllUserList( 
			@ModelAttribute("igrUserAuthorManageVO") IgrUserAuthorManageVO igrUserAuthorManageVO,
			HttpServletRequest request,
			ModelMap model
			)throws Exception {

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(igrUserAuthorManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(igrUserAuthorManageVO.getPageUnit());
		paginationInfo.setPageSize(igrUserAuthorManageVO.getPageSize());
		
		igrUserAuthorManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		igrUserAuthorManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		igrUserAuthorManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		log.debug("getAuthorId:" + igrUserAuthorManageVO.getAuthorId());
    	
    	// 20111108 - 권한에 기관ID 묶음 START
		IgrAuthorManageVO authorManageVO = new IgrAuthorManageVO();
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	authorManageVO.setUserId(gpsSessionVO.getUsrId());
    	int authorLevel = igrAuthorManageService.selectAuthorLevel(authorManageVO);
    	
    	if (authorLevel > 10000) {
    		igrUserAuthorManageVO.setAuthorLevel("2");
    		igrUserAuthorManageVO.setOrgId(gpsSessionVO.getOrgId());
    	}
    	// 20111108 - 권한에 기관ID 묶음 END
    	
        model.addAttribute("resultList", igrAuthorManageService.selectAllUserAuthorList(igrUserAuthorManageVO));
        
		paginationInfo.setTotalRecordCount(igrAuthorManageService.selectAllUserAuthorListTotCnt(igrUserAuthorManageVO));
		
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("igrUserAuthorManageVO", igrUserAuthorManageVO);
        
    	// 메뉴목록
        return "gps/adm/author/intergrated/iamAllUserList";
	}
    
    /**
     * 콤보박스로 시스템에 해당하는 권한목록을 조회한다.
     * @param response HttpServletResponse
     * @param request HttpServletRequest
     * @param commandMap Map
     * @return model ModelAndView
     * @throws Exception
     * @see AUTHOR_ID, AUTHOR_NM
	 * @see TABLE NAME : TN_AUTHOR, TN_SYSTEM
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/gps/adm/author/intergrated/authorComboList.do")
    public ModelAndView authorComboList(
    		HttpServletResponse response,
			HttpServletRequest request,
            Map commandMap) throws Exception {

    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	ModelAndView model = new ModelAndView();
    	String selectSysId = commandMap.get("selectSysId") == null ? "" : (String) commandMap.get("selectSysId");
    	
    	IgrAuthorManageVO authorManageVO = new IgrAuthorManageVO();
    	authorManageVO.setSysId(selectSysId);
    	authorManageVO.setUserId(gpsSessionVO.getUsrId());
    	
	    model.addObject("result", (List) igrAuthorManageService.selectAuthorCombo(authorManageVO));

	    model.setViewName("jsonView");
	    return model;
	}

    /**
     * 권한명 체크 화면으로 이동한다.
     * @param request HttpServletRequest
     * @param authorManageVO IgrAuthorManageVO
     * @param model ModelMap
     * @return "gps/adm/author/intergrated/iamNmCheck" 권한명 체크화면
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/author/intergrated/iamNmCheck.do")
	public String iamNmCheck (HttpServletRequest request,
			@ModelAttribute("authorManageVO") IgrAuthorManageVO authorManageVO,
			ModelMap model
    ) throws Exception {
    	
    	if (null != authorManageVO.getAuthorNm()) {
    		authorManageVO.setAuthorNm(null);
    	}
		
        return "gps/adm/author/intergrated/iamNmCheck";
        
    }

    /**
	 * 권한명을 중복 체크한다.
     * @param request HttpServletRequest
     * @param authorManageVO IgrAuthorManageVO
     * @param model ModelMap
     * @return "gps/adm/author/intergrated/iamNmCheck" 권한명 체크화면
     * @throws Exception
     * @see COUNT(*) totcnt 
	 * @see TABLE NAME : TN_AUTHOR
     */
    @RequestMapping(value="/gps/adm/author/intergrated/selectIamNm.do")
	public String selectCheckAuthorNm (HttpServletRequest request,
			@ModelAttribute("authorManageVO") IgrAuthorManageVO authorManageVO,
			ModelMap model
    ) throws Exception {

		if (0 < igrAuthorManageService.selectCheckAuthorNm(authorManageVO)) {
			authorManageVO.setAuthorNm(null);
			model.addAttribute("message", egovMessageSource.getMessage("gps.checkauthornmduplicate.msg"));
		} else {
			model.addAttribute("message", egovMessageSource.getMessage("gps.checkauthornmsuccess.msg"));
		}
		
        return "gps/adm/author/intergrated/iamNmCheck";
    }
    
    /**
     * 전체사용자 화면으로 이동한다.
     * @param igrUserAuthorManageVO IgrUserAuthorManageVO
     * @param model ModelMap
     * @return "gps/adm/author/intergrated/iamUserAllList" 전체사용자리스트화면
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/author/intergrated/addAuthorList.do")
	public String addAuthorList (@ModelAttribute("igrUserAuthorManageVO")IgrUserAuthorManageVO igrUserAuthorManageVO
			, ModelMap model
			) throws Exception {
    	StringTokenizer authorIdList = new StringTokenizer(igrUserAuthorManageVO.getAuthorId(), ",");
		while(authorIdList.hasMoreTokens() )
		{
			igrUserAuthorManageVO.setAuthorId(authorIdList.nextToken());
		}

		model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
		
    	return "gps/adm/author/intergrated/iamUserAllList";
	}

    /**
     * 사용자 권한 목록 화면으로 이동한다.
     * @param igrUserAuthorManageVO IgrUserAuthorManageVO
     * @param model ModelMap
     * @return "gps/adm/author/intergrated/iamUserList" 사용자권한 목록 화면
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/author/intergrated/cancelAuthorList.do")
	public String cancelAuthorList (@ModelAttribute("igrUserAuthorManageVO")IgrUserAuthorManageVO igrUserAuthorManageVO
			, ModelMap model
			) throws Exception {
    	StringTokenizer authorIdList = new StringTokenizer(igrUserAuthorManageVO.getAuthorId(), ",");
		while(authorIdList.hasMoreTokens())
		{
			igrUserAuthorManageVO.setAuthorId(authorIdList.nextToken());
			// igrAuthorManageService.cancelAuthorList(igrUserAuthorManageVO);
		}	    	

		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		
    	return "gps/adm/author/intergrated/iamUserList";
	}
    
    /**
     * 전체사용자 화면으로 이동한다.
     * @param igrUserAuthorManageVO IgrUserAuthorManageVO
     * @param model ModelMap
     * @return "gps/adm/author/intergrated/iamUserAllList" 전체사용자리스트화면
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/author/intergrated/addAuthor.do")
	public String addAuthor(@ModelAttribute("igrUserAuthorManageVO")IgrUserAuthorManageVO igrUserAuthorManageVO
			, ModelMap model
			) throws Exception {

		// igrAuthorManageService.addAuthorList(igrUserAuthorManageVO);    	

		model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
		
    	return "gps/adm/author/intergrated/iamUserAllList";
	}
    
    /**
     * 사용자 권한 목록 화면으로 이동한다.
     * @param igrUserAuthorManageVO IgrUserAuthorManageVO
     * @param model ModelMap
     * @return "gps/adm/author/intergrated/iamUserList" 사용자권한 목록 화면
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/author/intergrated/cancelAuthor.do")
	public String cancelAuthor (@ModelAttribute("igrUserAuthorManageVO")IgrUserAuthorManageVO igrUserAuthorManageVO
			, ModelMap model
			) throws Exception {
    	
		// igrAuthorManageService.cancelAuthorList(igrUserAuthorManageVO);

		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		
    	return "gps/adm/author/intergrated/iamUserList";
	}
    
}