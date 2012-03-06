package com.bbm.gps.adm.user.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import com.bbm.common.cmm.EgovMessageSource;
import com.bbm.common.cmm.service.EgovCmmUseService;
import com.bbm.common.util.cas.service.EgovSessionCookieUtil;
import com.bbm.common.util.sim.service.EgovFileScrty;
import com.bbm.gps.adm.author.intergrated.service.IgrAuthorManageService;
import com.bbm.gps.adm.author.intergrated.service.IgrAuthorManageVO;
import com.bbm.gps.adm.author.intergrated.service.IgrUserAuthorManageVO;
import com.bbm.gps.adm.menu.service.SystemVO;
import com.bbm.gps.adm.org.service.OrgManageVO;
import com.bbm.gps.adm.system.service.SystemManageService;
import com.bbm.gps.adm.user.service.UamService;
import com.bbm.gps.adm.user.service.UamVO;
import com.bbm.gps.adm.user.service.UserManageService;
import com.bbm.gps.adm.user.service.UserManageVO;
import com.bbm.gps.adm.user.service.ZipVO;
import com.bbm.gps.cmm.DefaultDataSet;
import com.bbm.gps.login.service.GpsSessionVO;
import com.bbm.gps.user.service.UserRegisterService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/** 
 * 사용자관리 비즈니스 구현 클래스 
 * <p><b>NOTE:</b> 사용자관리 화면과 직접 연계되는 컨트롤러 클래스로 주로 JSP화면에서 호출되거나 
 * 해당 클래스 내부에서 호출되는 메소드들이 정의되어 있다
 * 해당 클래스 중에는 비즈니스 로직이 없고 단순히 다른 JSP화면으로 forword 시켜주는 메소드도 존재한다
 * DB select, DB insert, DB update, DB delete, 단순forward 등의 기능을 하기위한 메소드들의 집합
 * @author 범정부통계포털 이관형 
 * @since 2011.06.21 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.06.21     이관형       최초 생성
 *   2011.07.21     이진우       사용자유형을 권한유형으로 변경  
 * 
 * </pre> 
 */

@Controller
public class UserManageController {
	
	/** UserManageService 서비스 호출 */ 
	@Resource(name = "userManageService")
    private UserManageService userManageService;
	
	/** UamService 서비스 호출 */ 
	@Resource(name = "uamService")
    private UamService uamService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
	/** EgovCmmUseService 호출 */ 
	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** defaultDataSet */
    @Resource(name="defaultDataSet")
    protected DefaultDataSet defaultDataSet;

	/** IgrAuthorManageService 서비스 호출 */ 
	@Resource(name = "igrAuthorManageService")
    private IgrAuthorManageService igrAuthorManageService;

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
	/** userRegisterService 서비스 호출 */ 
	@Resource(name = "userRegisterService")
    private UserRegisterService userRegisterService;

	/** SystemManageService 서비스 호출 */ 
	@Resource(name = "systemManageService")
    private SystemManageService systemManageService;

	/** LOG */ 
	protected Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * 사용자관리메인페이지
     * @param model ModelMap
     * @return 사용자관리 프레임관리 JSP페이지 "gps/adm/user/userManageMain"
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/user/userManageMain.do")
	public String userManageMain () throws Exception {
        return "gps/adm/user/userManageMain";
	}

    /**
	 * 권한관리 메인페이지 호출
     * @return "gps/adm/user/userDefault" 사용자관리 메인페이지 URL
     * @exception Exception
     */
    @RequestMapping(value="/gps/adm/user/userDefault.do")
	public String userDefault(
				@ModelAttribute("authorManageVO") IgrAuthorManageVO authorManageVO
				) throws Exception {
        return "gps/adm/user/userDefault";
	}
    
    /** 
     * userManageVO 에 담겨있는 항목을 DB에 insert 
     * @param userManageVO  insert할 항목에 대한 정보를 담고있는 VO 
     * @param bindingResult BindingResult
     * @param model ModelMap
     * @return 리스트 화면으로 forward ("forward:/gps/adm/user/selectUserList.do")
     * @exception Exception 
     * @see TABLE NAME : TN_USER
    */ 
    @RequestMapping(value="/gps/adm/user/insertAdmin.do")
    public String insertAdmin (
    		@ModelAttribute("userManageVO") UserManageVO userManageVO,
    		BindingResult bindingResult, 
    		ModelMap model
    ) throws Exception {

    	// 1. 입력한 비밀번호를 암호화한다.
    	userManageVO.setPassword(EgovFileScrty.encryptPassword(userManageVO.getPassword()));
    	// 사용자 정보 암호화
    	userManageVO.setAddrCn(EgovFileScrty.encode(userManageVO.getAddrCn()));
    	userManageVO.setAddrDtlCn(EgovFileScrty.encode(userManageVO.getAddrDtlCn()));
    	userManageVO.setPhonCn(EgovFileScrty.encode(userManageVO.getPhonCn()));
    	userManageVO.setMoblphonCn(EgovFileScrty.encode(userManageVO.getMoblphonCn()));
    	userManageVO.setEmail(EgovFileScrty.encode(userManageVO.getEmail()+"@"+userManageVO.getEmailCoNm()));
    	userManageService.insertUser(userManageVO);
    	
    	return "forward:/gps/adm/user/selectUserList.do";
    }
    
    /**
	 * 사용자유형 목록
	 * @param codeManageVO 화면의 사용자코드 정보
     * @param model ModelMap
     * @return 사용자화면 JSP로 이동 "gps/adm/user/userTypeList"
     * @throws Exception
     * @see ROWID, AUTHOR_ID, SYS_ID,SYS_NM,
	 * @see	AUTHOR_CODE, AUTHOR_NM, AUTHOR_DC, REGIST_DT,USER_CNT
     * @see TABLE NAME : TN_USER
     */
    @RequestMapping(value="/gps/adm/user/selectUserTypeList.do")
	public String userTypeList (@ModelAttribute("systemManageVO") SystemVO systemManageVO
			, HttpServletRequest request
			, ModelMap model
			) throws Exception {
    	
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	
    	systemManageVO.setUserId(gpsSessionVO.getUsrId());
    	model.addAttribute("userCodeList", userManageService.selectUserTypeList(systemManageVO));
    	model.addAttribute("sysInfo", systemManageService.selectSysInfoList(gpsSessionVO.getUsrId()));

    	model.addAttribute("systemManageVO", systemManageVO);
    	model.addAttribute("allUserCnt", userManageService.selectUserTypeListTotCnt());
        return "gps/adm/user/userTypeList";
	}

    /** 
     * 관리자정보 수정
     * @param codeManageVO  업데이트 항목들에 대한 정보를 가지고있는 VO
     * @param model ModelMap
     * @return 업데이트 한 후 업데이트된 해당 게시물의 결과를 보여주는 상세화면 조회 메서드로 forward
     * @return ("forward:/gps/adm/user/selectUserList.do") 
     * @exception Exception 
     * @see TABLE NAME : TN_USER 
    */ 
    @RequestMapping(value="/gps/adm/user/updateAdmin.do")
	public String updateAdmin (@ModelAttribute("userManageVO") UserManageVO userManageVO
			, ModelMap model
			, HttpServletRequest request
    ) throws Exception {
    	
    	userManageVO = (UserManageVO) defaultDataSet.updateSet(request, userManageVO);
		userManageService.updateAdmin(userManageVO);

        return "forward:/gps/adm/user/selectUserList.do";
		    	
    }
    
	/** 
	 * 사용자목록 조회  
     * @param userManageVO  사용자 항목들에 조회할 정보를 가지고있는 VO
     * @param model ModelMap
	 * @return 목록화면 JSP로 이동 ("gps/adm/user/userList")
	 * @exception Exception 
     * @see T.USER_ID, T.PASSWORD, T.NM, T.SE, T.GRAD, T.PSITN, T.PHOTO_CPCTY, T.ZIP, T.ADDR_CN,T.ADDR_DTL_CN, T.PHON_CN, T.MOBLPHON_CN, 
	 * @see EMAIL,EMAIL_CO,T.HTTP_CN, T.WRC_NM,T.DEPT_NM, T.RSPOFC, T.WRC_PHON_CN,T.WRC_ZIP, T.WRC_ADDR_CN, T.WRC_ADDR_DTL_CN, 
	 * @see T.PHOTO_OTHBC_AT, T.HTTP_OTHBC_AT, T.EMAIL_OTHBC_AT,T.MOBLPHON_OTHBC_AT, T.WRC_OTHBC_AT, T.SMS_RECPTN_AT,T.NEWS_RECPTN_AT, NVL(T.LOGIN_CO,0) as LOGIN_CO, T.LAST_LOGIN_TIME, 
	 * @see T.REGIST_DT, T.UPDT_DT, T.ENG_NM,T.WRC_FAX_CN, T.PHOTO_FILE_NM, T.PHOTO_FILE_MASK,T.APPROVAL_AT, T.ADDR_OTHBC_AT, T.PHON_OTHBC_AT, 
	 * @see T.WRC_PHON_OTHBC_AT, T.WRC_FAX_OTHBC_AT, T.WRC_ADDR_OTHBC_AT,T.REGISTER_ID, T.REGISTER_IP, T.UPDTUSR_ID, T.ORG_KD, T.ORG_DEPT_ID, T.ORG_ID, T.ORG_CHOOSE_AT,
	 * @see T.TRNSFER_INFO, T.OFFI_NM,PW_CHANGE_TIME
     * @see TABLE NAME : TN_USER
	*/
	@RequestMapping(value="/gps/adm/user/selectUserList.do")
	public String selectUserList (@ModelAttribute("userManageVO") UserManageVO userManageVO
			, ModelMap model
			) throws Exception {

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(userManageVO.getPageUnit());
		paginationInfo.setPageSize(userManageVO.getPageSize());
		
		userManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		userManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		// 전체 사용자 조회일 경우
		if (userManageVO.getListType().equals("all")) {
			userManageVO.setAuthorId("");
			userManageVO.setSysId("");
		}
		
		if(userManageVO.getAuthorId()==null || userManageVO.equals("")){
			model.addAttribute("resultList", userManageService.selectAllUserList(userManageVO));
			paginationInfo.setTotalRecordCount(userManageService.selectAllUserListTotCnt(userManageVO));
		}else{
			model.addAttribute("resultList", userManageService.selectUserList(userManageVO));
			paginationInfo.setTotalRecordCount(userManageService.selectUserListTotCnt(userManageVO));
		}
		
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("userManageVO", userManageVO);
        
        return "gps/adm/user/userList";
	}
    
    
    /** 
	 * 사용자리스트에서 선택된 미승인 사용자의 승인처리 
	 * @param userManageVO
	 * @return "forward:/gps/adm/user/selectUserList.do"
	 * @exception Exception 
	 * @see TABLE NAME : TN_USER 
	*/ 
    @RequestMapping(value="/gps/adm/user/approvalUser.do")
	public String approvalUser (@ModelAttribute("userManageVO") UserManageVO userManageVO
			,ModelMap model
			) throws Exception {
    	
    	StringTokenizer tkUserList = new StringTokenizer(userManageVO.getAppUserIdList(), ",");
		while(tkUserList.hasMoreTokens() )
		{
			userManageVO.setUserId(tkUserList.nextToken());
			userManageService.appUserId(userManageVO);
		}
		
    	return "forward:/gps/adm/user/selectUserList.do";
	}
    
    
    
    /** 
     * userManageVO 키값에 대한 게시물을 삭제(DB 행삭제) 
     * @param userManageVO  삭제 항목에 대한 구분자 
     * @param model ModelMap
     * @return 리스트 화면으로 forward ("forward:/gps/adm/user/selectUserList.do")
     * @exception Exception 
     * @see TABLE NAME : TN_USER
    */ 
    @RequestMapping(value="/gps/adm/user/deleteUserDetail.do")
	public String deleteUserDetail (
			@ModelAttribute("userManageVO")UserManageVO userManageVO,
			ModelMap model
			) throws Exception {
    	
    	userManageService.deleteUser(userManageVO);
    	return "forward:/gps/adm/user/selectUserList.do";
	}

    /** 
     * 사용자 등록화면 페이지로 이동
     * @param userManageVO  등록화면에 필요한 사용자 정보
     * @param model ModelMap
     * @return 게시물 등록 JSP로 이동 ("gps/adm/user/userRegist")
     * @exception Exception 
    */ 
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/gps/adm/user/registerUser.do")
	public String registerUser(@ModelAttribute("userManageVO") UserManageVO userManageVO ,ModelMap model
    ) throws Exception {
        // 기관목록 출력 조건
    	Map whereMap = new HashMap();
        
        model.addAttribute("orgList", userManageService.selectOrgMap(whereMap));
        
        return "gps/adm/user/userRegist";
    }
    
    /** 
     * userManageVO 에 담겨있는 항목을 DB에 insert 
     * @param userManageVO  insert할 항목에 대한 정보를 담고있는 VO 
     * @param bindingResult BindingResult
     * @param model ModelMap
     * @return 리스트 화면으로 forward ("forward:/gps/adm/user/selectUserList.do")
     * @exception Exception 
     * @see TABLE NAME : TN_USER
    */ 
    @RequestMapping(value="/gps/adm/user/insertUser.do")
    public String insertUser (
    		@ModelAttribute("userManageVO") UserManageVO userManageVO,
    		HttpServletRequest request,
    		BindingResult bindingResult, 
    		ModelMap model
    ) throws Exception {

    	// 1. 입력한 비밀번호를 암호화한다.
    	userManageVO.setPassword(EgovFileScrty.encryptPassword(userManageVO.getPassword()));
    	// 사용자 정보 암호화
    	userManageVO.setAddrCn(EgovFileScrty.encode(userManageVO.getAddrCn()));
    	userManageVO.setAddrDtlCn(EgovFileScrty.encode(userManageVO.getAddrDtlCn()));
    	userManageVO.setPhonCn(EgovFileScrty.encode(userManageVO.getPhonCn()));
    	userManageVO.setMoblphonCn(EgovFileScrty.encode(userManageVO.getMoblphonCn()));
    	userManageVO.setEmail(EgovFileScrty.encode(userManageVO.getEmail()+"@"+userManageVO.getEmailCoNm()));
    	
    	userManageVO = (UserManageVO) defaultDataSet.registSet(request, userManageVO);
    	userManageService.insertUser(userManageVO);
    	
    	return "forward:/gps/adm/user/selectUserList.do";
    }
    
	/** 
	 * userManageVO 게시물ID에 해당하는 정보를 Select하여 게시물을 수정하는 화면으로 이동 
	 * @param userManageVO  수정 대상 항목에 대한 구분자 
     * @param model ModelMap
	 * @return 수정화면 JSP로 이동 ("gps/adm/user/userUpdate") 
	 * @exception Exception 
     * @see TABLE NAME : TN_USER
	*/ 
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/gps/adm/user/modifyUser.do")
	public String modifyUser (@ModelAttribute("userManageVO") UserManageVO userManageVO
			, HttpServletRequest request
    		, ModelMap model
    ) throws Exception {
    	
    	UserManageVO userVO = new UserManageVO();
    	userVO = userManageService.selectUser(userManageVO);
    	// : : : 목록에서 선택된 사용자정보 오브젝트 교체 : List화면에서 Update화면에필요한 파라미터값들을 아래에 세팅 : : :
    	userVO.setSysId(userManageVO.getSysId());
    	userVO.setSelectedAuthorId(userManageVO.getSelectedAuthorId());
    	userVO.setAuthorId(userManageVO.getSelectedAuthorId());
    	userVO.setUserAuthorId(userManageVO.getUserAuthorId());
    	userVO.setEmailCoNm(userVO.getEmailCo());
    	userVO.setPageIndex(userManageVO.getPageIndex());
    	userVO.setPageUnit(userManageVO.getPageUnit());
    	userVO.setSearchCondition(userManageVO.getSearchCondition());
    	userVO.setSearchKeyword(userManageVO.getSearchKeyword());
    	userVO.setOrderByClause(userManageVO.getOrderByClause());
    	userVO.setApprovalAtCond(userManageVO.getApprovalAtCond());
    	userVO.setListType(userManageVO.getListType());
    	
    	if (null == userVO.getTrnsferInfo() || !userVO.getTrnsferInfo().equals("U")) {
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
    	}
    	
    	// 사용자 부재정보에 기관정보 설정
    	UserManageVO userAbsnceVO = new UserManageVO();
    	userAbsnceVO.setOrgId(userVO.getOrgId());

        model.addAttribute("userAbsnceVO",userAbsnceVO);
        model.addAttribute("userManageVO",userVO);
        
        model.addAttribute("appList", cmmUseService.selectCmmCodeMap("21101309",""));

        // 권한 정보 취득
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
        IgrAuthorManageVO igrAuthorManageVO = new IgrAuthorManageVO();
        igrAuthorManageVO.setUserId(gpsSessionVO.getUsrId());
        model.addAttribute("sysId", igrAuthorManageService.selectSysComboMap(igrAuthorManageVO));
        
    	IgrAuthorManageVO authorManageVO = new IgrAuthorManageVO();
    	authorManageVO.setSysId(userVO.getSysId());
    	authorManageVO.setUserId(gpsSessionVO.getUsrId());
    	List authorList = (ArrayList) igrAuthorManageService.selectAuthorCombo(authorManageVO);

    	Map authorMap  = new  LinkedHashMap();
    	for(int tmpcnt = 0; tmpcnt < authorList.size(); tmpcnt++)
		{
    		EgovMap _egovMap = (EgovMap)authorList.get(tmpcnt);
    		authorMap.put(_egovMap.get("authorId"), _egovMap.get("authorNm"));
		}
	    model.addAttribute("authorId", authorMap);
	    model.addAttribute("emailMap", cmmUseService.selectEmailMap("21101304",""));
	    
        model.addAttribute("orgKdMap", cmmUseService.selectCmmCodeIdMap("21101110",""));
        
        
        // 조직, 부서찾기 콤보
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

        return "gps/adm/user/userUpdate";
    }
    
    /** 
     * userManageVO 수정할 대상 게시물ID에 해당하는 행을 업데이트 
     * @param userManageVO  업데이트 항목들에 대한 정보를 가지고있는 VO
     * @param model ModelMap
     * @return 업데이트 한 후 업데이트된 해당 게시물의 결과를 보여주는 상세화면 조회 메서드로 forward
     * @return ("forward:/gps/adm/user/selectUserList.do") 
     * @exception Exception 
     * @see TABLE NAME : TN_USER
    */ 
    @RequestMapping(value="/gps/adm/user/updateUser.do")
	public String updateUser (@ModelAttribute("userManageVO") UserManageVO userManageVO
			, HttpServletRequest request
    		, ModelMap model
    ) throws Exception {

    	// 1.사용자 정보 편집
    	userManageVO = (UserManageVO) defaultDataSet.updateSet(request, userManageVO);
    		
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	userManageVO.setUpdtusrId(gpsSessionVO.getUsrId());
    	// 사용자 정보 복호화
    	userManageVO.setUpdtusrId(gpsSessionVO.getUsrId());
    	userManageVO.setAddrCn(EgovFileScrty.encode(userManageVO.getAddrCn()));
    	userManageVO.setAddrDtlCn(EgovFileScrty.encode(userManageVO.getAddrDtlCn()));
    	userManageVO.setPhonCn(EgovFileScrty.encode(userManageVO.getPhonCn()));
    	userManageVO.setMoblphonCn(EgovFileScrty.encode(userManageVO.getMoblphonCn()));
    	userManageVO.setEmail(EgovFileScrty.encode(userManageVO.getEmail()).concat("@"+userManageVO.getEmailCoNm()));
    	
    	// 1.사용자 정보 수정
		userManageService.updateUser(userManageVO);

		if (!userManageVO.getAuthorId().isEmpty()) {

			IgrUserAuthorManageVO igrUserAuthorManageVO = new IgrUserAuthorManageVO();
			
			igrUserAuthorManageVO.setUserId(userManageVO.getUserId());
			igrUserAuthorManageVO.setAuthorId(userManageVO.getAuthorId());
			igrUserAuthorManageVO.setUserAuthorId(userManageVO.getUserAuthorId());
			
			// 2.사용자권한 UPDATE
			if (igrUserAuthorManageVO.getUserAuthorId().isEmpty()) {
				igrUserAuthorManageVO.setUserAuthorId(Integer.toString(igrAuthorManageService.userAuthorIdGeneration(igrUserAuthorManageVO)));
				igrAuthorManageService.insertUserAuthor(igrUserAuthorManageVO);
			} else {
				igrAuthorManageService.updateUserAuthor(igrUserAuthorManageVO);
			}
		}

		model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
        return "forward:/gps/adm/user/selectUserList.do";
		    	
    }
    
    /** 
     * 우편번호 찾기 화면호출
     * @param model ModelMap
     * @return 우편번호 찾기 화면으로 이동
     * @exception Exception 
     * @see TABLE NAME : TN_ZIP 
    */ 
	@RequestMapping(value="/gps/adm/user/ZipSearchPopup.do")
 	public String callZipSearchPopup (ModelMap model) throws Exception {
		return "gps/adm/user/ZipSearchList";
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
	@RequestMapping(value="/gps/adm/user/ZipSearchList.do")
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
        
        return "gps/adm/user/ZipSearchList";
	}
    
    
    /** 
     * 비밀번호 변경 화면호출
     * @param model ModelMap
     * @return 비밀번호 변경 화면으로 이동
     * @exception Exception 
     * @see TABLE NAME : TN_USER
    */ 
	@RequestMapping(value="/gps/adm/user/chgPwPopup.do")
 	public String callChgPwPopup (@ModelAttribute("userManageVO") UserManageVO userManageVO
    		,ModelMap model
 			) throws Exception {
		return "gps/adm/user/chgPwPopup";
	}
	
	
	/** 
     * userManageVO 해당 사용자의 비밀번호 변경 
     * @param userManageVO  변경 비밀번호 정보를 가지고있는 VO
     * @param model ModelMap
     * @return 
     * @return ("gps/adm/user/chgPwPopup") 
     * @exception Exception 
     * @see TABLE NAME : TN_USER
    */ 
    @RequestMapping(value="/gps/adm/user/updatePwPopup.do")
	public String updatePw (@ModelAttribute("userManageVO") UserManageVO userManageVO
    		, ModelMap model
    ) throws Exception {

    	// 1. 입력한 비밀번호를 암호화한다.
    	userManageVO.setPassword(EgovFileScrty.encryptPassword(userManageVO.getPassword()));
		userManageService.updatePassword(userManageVO);
		
        return "gps/adm/user/chgPwPopupOk";
		    	
    }

    /**
     * 권한목록를 조회한다. 
     * @param commandMap
     * @return jsonView
     * @throws Exception
     * @see ORG_ID,ORG_NM
     * @see TABLE NAME : TN_ORG
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/gps/adm/user/deptComboList.do")
    public ModelAndView deptComboList(
    		HttpServletResponse response,
			HttpServletRequest request,
            Map commandMap) throws Exception {

    	@SuppressWarnings("unused")
		GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	ModelAndView model = new ModelAndView();
    	String selectOrgId = commandMap.get("selectOrgId") == null ? "" : (String) commandMap.get("selectOrgId");
    	
    	OrgManageVO orgManageVO = new OrgManageVO();
    	orgManageVO.setUpOrgId(selectOrgId);
    	List deptList = (ArrayList) userManageService.selectDeptCombo(orgManageVO);
    	
	    model.addObject("result", deptList);

	    model.setViewName("jsonView");
	    return model;
	}

	/** 
	 * 사용자목록 조회  
     * @param userManageVO  사용자 항목들에 조회할 정보를 가지고있는 VO
     * @param model ModelMap
	 * @return 목록화면 JSP로 이동 ("gps/adm/user/userAbsnceList")
	 * @exception Exception 
	 * @see USER_ID, A.NM, A.SE, A.GRAD, A.PSITN, A.PHOTO_CPCTY, A.ZIP, A.ADDR_CN, A.ADDR_DTL_CN, A.PHON_CN,
	 * @see	MOBLPHON_CN, A.EMAIL, A.HTTP_CN, A.WRC_NM, A.DEPT_NM, A.RSPOFC, A.WRC_PHON_CN, A.WRC_ZIP, A.WRC_ADDR_CN,
	 * @see	WRC_ADDR_DTL_CN, A.PHOTO_OTHBC_AT, A.HTTP_OTHBC_AT, A.EMAIL_OTHBC_AT, A.MOBLPHON_OTHBC_AT,
	 * @see	WRC_OTHBC_AT, A.SMS_RECPTN_AT, A.NEWS_RECPTN_AT, A.LOGIN_CO, A.LAST_LOGIN_TIME, A.REGIST_DT, A.UPDT_DT,
	 * @see	ENG_NM, A.WRC_FAX_CN, A.PHOTO_FILE_NM, A.PHOTO_FILE_MASK, A.APPROVAL_AT, A.ADDR_OTHBC_AT, A.PHON_OTHBC_AT,
	 * @see	WRC_PHON_OTHBC_AT, A.WRC_FAX_OTHBC_AT, A.WRC_ADDR_OTHBC_AT, A.REGISTER_ID, A.REGISTER_IP, A.UPDTUSR_ID
     * @see TABLE NAME : TN_USER
	*/ 
	@RequestMapping(value="/gps/adm/user/userAbsncePopup.do")
	public String userAbsncePopup (@ModelAttribute("userManageVO") UserManageVO userManageVO,
			HttpServletRequest request,
			ModelMap model
			) throws Exception {

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(userManageVO.getPageUnit());
		paginationInfo.setPageSize(userManageVO.getPageSize());
		
		userManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		userManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
    	if (userManageVO.getApprovalAt().equals("N")) {
	        model.addAttribute("resultList", uamService.selectAbsnceUserList(userManageVO));
			paginationInfo.setTotalRecordCount(uamService.selectAbsnceUserListTotCnt(userManageVO));
    	} else {
	        model.addAttribute("resultList", uamService.selectTransferList(userManageVO));
			paginationInfo.setTotalRecordCount(uamService.selectTransferListTotCnt(userManageVO));
    	}
		
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("userManageVO", userManageVO);
        
		return "gps/adm/user/userAbsnceList";
	}

    /**
	 * 권한등록처리
     * @param userManageVO 사용자 정보를 담고있는VO
     * @return "forward:/gps/adm/user/userAbsncePopup.do"
     * @exception Exception
     * @see TABLE NAME : 
     */
    @RequestMapping(value="/gps/adm/user/insertUserAbsnce.do")
	public String insertUserAuthor(
			@ModelAttribute("userManageVO") UserManageVO userManageVO,
			HttpServletRequest request,
			SessionStatus ststus,
			ModelMap model
			)throws Exception {
    	
    	// 1.사용자권한 정보 편집
    	IgrUserAuthorManageVO igrUserAuthorManageVO = new IgrUserAuthorManageVO();
    	igrUserAuthorManageVO = (IgrUserAuthorManageVO)defaultDataSet.registSet(request, igrUserAuthorManageVO);
    	
    	igrUserAuthorManageVO.setUserId(userManageVO.getTransferUserId());
    	igrUserAuthorManageVO.setAuthorId(userManageVO.getSelectedAuthorId());
    	// 사용자 권한 ID생성
    	String userAuthorId = Integer.toString(igrAuthorManageService.userAuthorIdGeneration(igrUserAuthorManageVO));
    	igrUserAuthorManageVO.setUserAuthorId(userAuthorId);
    	
    	// 1.사용자권한등록처리
    	igrAuthorManageService.insertUserAuthor(igrUserAuthorManageVO);
    	
    	// 2.사용자 부재정보 편집
    	UamVO uamVO = new UamVO();
    	uamVO = (UamVO)defaultDataSet.registSet(request, uamVO);
    	
    	uamVO.setAbsnceUserId(userManageVO.getUserId());
    	uamVO.setTransferUserId(userManageVO.getTransferUserId());
    	uamVO.setAuthorId(userManageVO.getSelectedAuthorId());
    	uamVO.setUserAuthorId(userAuthorId);
    	// 2.사용자 부재정보 등록처리
    	uamService.insertUserAbsnce(uamVO);
    	
    	// 3.사용자 정보사용여부 : N
		userManageService.appUserId(userManageVO);
    	
    	ststus.setComplete();
    	model.addAttribute("message",egovMessageSource.getMessage("gps.insertUserAbsnce.msg"));
        return "forward:/gps/adm/user/userAbsncePopup.do";
	}

    /**
	 * 메뉴권한등록처리
     * @param menuManageVO 메뉴정보를 담고있는VO
     * @param model ModelMap
     * @return "gps/adm/author/intergrated/registerMenuAuthor"
     * @exception Exception
     * @see TABLE NAME : 
     */
    @RequestMapping(value="/gps/adm/user/deleteUserAbsnce.do")
	public String deleteUserAuthor(
			@ModelAttribute("userManageVO") UserManageVO userManageVO,
			HttpServletRequest request,
			SessionStatus ststus,
			ModelMap model
			)throws Exception {
    	
    	// 1.사용자권한 정보 편집
    	IgrUserAuthorManageVO igrUserAuthorManageVO = new IgrUserAuthorManageVO();
    	igrUserAuthorManageVO = (IgrUserAuthorManageVO)defaultDataSet.registSet(request, igrUserAuthorManageVO);
    	igrUserAuthorManageVO.setUserId(userManageVO.getTransferUserId());
    	igrUserAuthorManageVO.setUserAuthorId(userManageVO.getUserAuthorId());
    	igrUserAuthorManageVO.setAuthorId(userManageVO.getSelectedAuthorId());
    	
    	// 1.사용자권한삭제처리
    	igrAuthorManageService.deleteUserAuthor(igrUserAuthorManageVO);

    	// 2.사용자 부재정보 편집
    	UamVO uamVO = new UamVO();
    	uamVO = (UamVO)defaultDataSet.registSet(request, uamVO);
    	
    	uamVO.setAbsnceUserId(userManageVO.getUserId());
    	uamVO.setTransferUserId(userManageVO.getTransferUserId());
    	uamVO.setUserAuthorId(userManageVO.getUserAuthorId());
    	// 2.사용자 부재정보 삭제처리
    	uamService.deleteUserAbsnce(uamVO);
    	
    	// 3.사용자 정보 사용여부 : Y
		userManageService.appUserId(userManageVO);
    	
    	ststus.setComplete();
    	model.addAttribute("message",egovMessageSource.getMessage("gps.deleteUserAbsnce.msg"));
        return "forward:/gps/adm/user/userAbsncePopup.do";
	}

}