package com.bbm.gps.adm.bbs.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import com.bbm.common.cmm.EgovMessageSource;
import com.bbm.common.cmm.service.EgovCmmUseService;
import com.bbm.common.util.cas.service.EgovSessionCookieUtil;
import com.bbm.common.util.sim.service.EgovFileScrty;
import com.bbm.gps.adm.bbs.service.BbsDbVO;
import com.bbm.gps.adm.bbs.service.BbsInfoVO;
import com.bbm.gps.adm.bbs.service.BbsManageService;
import com.bbm.gps.adm.bbs.service.BbsSearchVO;
import com.bbm.gps.adm.menu.service.MenuManageService;
import com.bbm.gps.adm.menu.service.MenuManageVO;
import com.bbm.gps.adm.menu.service.SystemVO;
import com.bbm.gps.adm.user.service.UserManageVO;
import com.bbm.gps.cmm.DefaultDataSet;
import com.bbm.gps.login.service.GpsSessionVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

/** 
 * 게시판관리 비즈니스 구현 클래스 
 * <p><b>NOTE:</b>게시판관리 화면과 직접 연계되는 컨트롤러 클래스로 주로 JSP화면에서 호출되거나 
 * 해당 클래스 내부에서 호출되는 메소드들이 정의되어 있다
 * 해당 클래스 중에는 비즈니스 로직이 없고 단순히 다른 JSP화면으로 forword 시켜주는 메소드도 존재한다
 * 게시판정보 조회,입력,수정,삭제 요청을 처리한다
 * @author 포탈통계 황기연 
 * @since 2011.07.05 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.07.05     황기연       최초 생성 
 * 
 * </pre> 
 */

@Controller
public class BbsManageController {
	
	/** BbsManageService 서비스 호출 */ 
	@Resource(name = "bbsManageService")
    private BbsManageService bbsManageService;
    
	/** massage source */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
	
	/** defaultDataSet */
    @Resource(name="defaultDataSet")
    protected DefaultDataSet defaultDataSet;
    
    /** EgovCmmUseService 호출 */ 
	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;
	
	/** menuManageService */
    @Resource(name="menuManageService")
    protected MenuManageService menuManageService;
    
	/** LOG */ 
	protected Log log = LogFactory.getLog(this.getClass());
	
	/** propertiesService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
	
	/**
	 * 게시판관리 메인 페이지
	 * @return "gps/adm/bbs/bbsManageMain"
	 * @throws Exception
	 */
	@RequestMapping(value="/gps/adm/bbs/selectBbsManageMain.do")
	public String selectBbsManageMain()throws Exception{
		return "gps/adm/bbs/bbsManageMain";
	}
	
	/**
	 * 게시판목록기본페이지
	 * @return "gps/adm/bbs/bbsDefault"
	 * @throws Exception
	 */
	@RequestMapping(value="/gps/adm/bbs/selectBbsDefault.do")
	public String selectBbsDefault()throws Exception{
		return "gps/adm/bbs/bbsDefault";
	}
	
	/**
	 * 게시판 DB목록 조회
	 * @param bbsDbVO
	 * @param model
	 * @return "gps/adm/bbs/bbsDbList"
	 * @throws Exception
	 * @see DB_TNAME, DB_NAME,DB_COMMENT,DB_MNGR_NM,DB_MNGR_ID,DB_MNGR_PASSWORD,REGISTER_ID,REGISTER_IP,REGIST_DT,UPDT_DT
	 * @see TABLE NAME : TN_BBS_DB
	 */
	@RequestMapping(value="/gps/adm/bbs/selectBbsDbList.do")
	public String selectBbsDbList(
			@ModelAttribute("bbsDbVO")BbsDbVO bbsDbVO,
			ModelMap model
			)throws Exception{
		model.addAttribute("bbsDbList",(List<BbsDbVO>)this.bbsManageService.selectBbsDbList(bbsDbVO));
		return "gps/adm/bbs/bbsDbList";
	}
	
	/**
	 * 게시판 DB목록 입력페이지호출
	 * @param bbsDbVO
	 * @param userManageVO
	 * @param model
	 * @return "gps/adm/bbs/registerBbsDbPopup"
	 * @throws Exception
	 */
	@RequestMapping(value="/gps/adm/bbs/registerBbsDbPopup.do")
	public String registerBbsDbPopup(
			@ModelAttribute("bbsDbVO")BbsDbVO bbsDbVO,
			@ModelAttribute("userManageVO")UserManageVO userManageVO,
			ModelMap model
			)throws Exception{
		return "gps/adm/bbs/registerBbsDbPopup";
	}

	/**
	 * 관리자 검색
	 * @param userManageVO
	 * @param response
	 * @param model
	 * @return "jsonView"
	 * @throws Exception
	 * @see	userId,nm,email
	 * @see TABLE NAME : TN_USER
	 */
	@RequestMapping(value="/gps/adm/bbs/selectMngrList.do")
	public String selectMngrList(
			@ModelAttribute("userManageVO")UserManageVO userManageVO,
			HttpServletResponse response,
			ModelMap model
			)throws Exception{
		model.addAttribute("mngrList", this.bbsManageService.selectMngrList(userManageVO));
		return "jsonView";
	}
	
	/**
	 * 게시판 DB명 중복검사
	 * @param bbsDbVO
	 * @param response
	 * @param model
	 * @return "jsonView"
	 * @throws Exception
	 * @see COUNT(DB_NAME)
	 * @see TABLE NAME : TN_BBS_DB
	 */
	@RequestMapping(value="/gps/adm/bbs/dbNameDuplicateAt.do")
	public String dbNameDuplicateAt(
			@ModelAttribute("bbsDbVO")BbsDbVO bbsDbVO,
			HttpServletResponse response,
			ModelMap model
			)throws Exception{
		boolean duplicateAt = false;
		//게시판DB명중복검사
		if(!this.bbsManageService.dbNameDuplicateAt(bbsDbVO)){
			//중복메시지
			model.addAttribute("message",this.egovMessageSource.getMessage("gps.inUseName.msg"));
			duplicateAt = true;
		}else{
			model.addAttribute("message",this.egovMessageSource.getMessage("gps.useName.msg"));
		}
		model.addAttribute("duplicateAt",duplicateAt);
		return "jsonView";
	}
	
	/**
	 * 게시판 DB목록 등록
	 * @param bbsDbVO
	 * @param userManageVO
	 * @param request
	 * @param status
	 * @param model
	 * @return "gps/adm/bbs/registerBbsDbPopup"
	 * @throws Exception
	 * @see TABLE NAME : TN_BBS_DB
	 */
	@RequestMapping(value="/gps/adm/bbs/insertBbsDb.do")
	public String insertBbsDb(
			@ModelAttribute("bbsDbVO")BbsDbVO bbsDbVO,
			@ModelAttribute("userManageVO")UserManageVO userManageVO,
			HttpServletRequest request,
			SessionStatus status,
			ModelMap model
			)throws Exception{
			
			bbsDbVO = (BbsDbVO)this.defaultDataSet.registSet(request, bbsDbVO);
			bbsDbVO.setDbMngrPassword(EgovFileScrty.encryptPassword(bbsDbVO.getDbMngrPassword()));
			//게시판DB입력처리
			this.bbsManageService.insertBbsDb(bbsDbVO);
			status.setComplete();
			//입력처리메시지
			model.addAttribute("message",this.egovMessageSource.getMessage("success.common.insert"));
		return "gps/adm/bbs/registerBbsDbPopup";
	}

	/**
	 * 게시판 DB목록 수정페이지호출
	 * @param bbsDbVO
	 * @param userManageVO
	 * @param model
	 * @return "gps/adm/bbs/modifyBbsDbPopup"
	 * @throws Exception
	 */
	@RequestMapping(value="/gps/adm/bbs/modifyBbsDbPopup.do")
	public String modifyBbsDbPopup(
			@ModelAttribute("bbsDbVO")BbsDbVO bbsDbVO,
			@ModelAttribute("userManageVO")UserManageVO userManageVO,
			ModelMap model)throws Exception{
		model.addAttribute("bbsDbVO", (BbsDbVO)this.bbsManageService.selectBbsDb(bbsDbVO));
		return "gps/adm/bbs/modifyBbsDbPopup";
	}
	
	/**
	 * 게시판 DB목록 수정
	 * @param bbsDbVO
	 * @param userManageVO
	 * @param status
	 * @param request
	 * @param model
	 * @return "gps/adm/bbs/modifyBbsDbPopup"
	 * @throws Exception
	 * @see TABLE NAME : TN_BBS_INFO
	 */
	@RequestMapping(value="/gps/adm/bbs/updateBbsDb.do")
	public String updateBbsDb(
			@ModelAttribute("bbsDbVO")BbsDbVO bbsDbVO,
			@ModelAttribute("userManageVO")UserManageVO userManageVO,
			SessionStatus status,
			HttpServletRequest request,
			ModelMap model
		)throws Exception{
		// 수정처리
		bbsDbVO = (BbsDbVO)this.defaultDataSet.updateSet(request, bbsDbVO);
		this.bbsManageService.updateBbsDb(bbsDbVO);
		status.setComplete();
		//수정처리메시지
		model.addAttribute("message",this.egovMessageSource.getMessage("success.common.update"));
		return "gps/adm/bbs/modifyBbsDbPopup";
	}

	/**
	 * 게시판 DB목록 삭제
	 * @param bbsDbVO
	 * @param userManageVO
	 * @param model
	 * @return "gps/adm/bbs/modifyBbsDbPopup"
	 * @throws Exception
	 * @see TABLE NAME : TN_BBS_INFO
	 */
	@RequestMapping(value="/gps/adm/bbs/deleteBbsDb.do")
	public String deleteBbsDb(
			@ModelAttribute("bbsDbVO")BbsDbVO bbsDbVO,
			@ModelAttribute("userManageVO")UserManageVO userManageVO,
			ModelMap model
			)throws Exception{
		//삭제처리
		this.bbsManageService.deleteBbsDb(bbsDbVO);
		//삭제처리메시지
		model.addAttribute("message",this.egovMessageSource.getMessage("success.common.delete"));
		return "gps/adm/bbs/modifyBbsDbPopup";
	}

	/**
	 * 게시판 목록 조회
	 * @param bbsSearchVO
	 * @param bbsInfoVO
	 * @param bbsDbVO
	 * @param request
	 * @param model
	 * @return "gps/adm/bbs/bbsInfoList"
	 * @throws Exception
	 * @see BBS_ID,  DB_TNAME,  BBS_NM,  BBS_TITLE,  BBS_DC,  BBS_MNGR_NM,  SKIN_INFO,  TITLE_IMAGE,  
	 * @see TABLE_SIZE,  NEW_ICON_IMAGE,  NEW_ICON_INDICT_PD,  COOL_ICON_IMAGE,  COOL_ICON_INDICT_RDCNT,  
	 * @see HOT_ICON_IMAGE,  HOT_ICON_INDICT_RDCNT,  SUBPAGE_ID,  CTGRY_CODE,  LOGIN_USE_AT,  ANSWER_USE_AT,  
	 * @see WEBEDITOR_USE_AT,  MEMO_USE_AT,  AVATA_USE_AT,  RECOMMEND_USE_AT,  TITLE_DECO_USE_AT,  LIST_USE_AT,  
	 * @see RELATE_USE_AT,  ARND_USE_AT,  NTCE_PD_USE_AT,  NTCE_TRGET_USE_AT,  PASSWORD_USE_AT,  NCM_USE_AT,  
	 * @see FILE_UPLOAD_USE_AT,  ALBUM_AT,  ALBUM_STLE_AT,  EMAIL_SNDNG_AT,  UPLOAD_FILE_CO,  PGE_LIST_CO,  
	 * @see PGE_GROUP_CO,  NOTICE_CO,  ALBUM_COLUMN_CO,  ALBUM_LINE_CO,  LIST_EX_AUTHOR,  BDT_REDNG_AUTHOR,  BDT_WRITE_AUTHOR,  ANSWER_WRITE_AUTHOR,  
	 * @see MEMO_WRITE_AUTHOR,  WEBEDITOR_WRITE_AUTHOR,  REGISTER_IP,  REGIST_DT,  REGISTER_ID,  UPDT_DT
	 * @see TABLE NAME : TN_BBS_INFO
	 */
	@RequestMapping(value="/gps/adm/bbs/selectBbsInfoList.do")
	public String selectBbsInfoList(
			@ModelAttribute("bbsSearchVO")BbsSearchVO bbsSearchVO,
			@ModelAttribute("bbsInfoVO")BbsInfoVO bbsInfoVO,
			@ModelAttribute("bbsDbVO")BbsDbVO bbsDbVO,
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
			//게시판 DB정보
			model.addAttribute("bbsDbVO",(BbsDbVO)this.bbsManageService.selectBbsDb(bbsDbVO));
			model.addAttribute("paginationInfo", paginationInfo);
    	}
    	
		return "gps/adm/bbs/bbsInfoList";
	}

    /**
     * 게시판 목록에 대한 excel 파일 다운로드 
     * @param bbsSearchVO
     * @return ModelAndView
     * @throws Exception
     * @see BBS_ID,  DB_TNAME,  BBS_NM,  BBS_TITLE,  BBS_DC,  BBS_MNGR_NM,  SKIN_INFO,  TITLE_IMAGE,  
	 * @see TABLE_SIZE,  NEW_ICON_IMAGE,  NEW_ICON_INDICT_PD,  COOL_ICON_IMAGE,  COOL_ICON_INDICT_RDCNT,  
	 * @see HOT_ICON_IMAGE,  HOT_ICON_INDICT_RDCNT,  SUBPAGE_ID,  CTGRY_CODE,  LOGIN_USE_AT,  ANSWER_USE_AT,  
	 * @see WEBEDITOR_USE_AT,  MEMO_USE_AT,  AVATA_USE_AT,  RECOMMEND_USE_AT,  TITLE_DECO_USE_AT,  LIST_USE_AT,  
	 * @see RELATE_USE_AT,  ARND_USE_AT,  NTCE_PD_USE_AT,  NTCE_TRGET_USE_AT,  PASSWORD_USE_AT,  NCM_USE_AT,  
	 * @see FILE_UPLOAD_USE_AT,  ALBUM_AT,  ALBUM_STLE_AT,  EMAIL_SNDNG_AT,  UPLOAD_FILE_CO,  PGE_LIST_CO,  
	 * @see PGE_GROUP_CO,  NOTICE_CO,  ALBUM_COLUMN_CO,  ALBUM_LINE_CO,  LIST_EX_AUTHOR,  BDT_REDNG_AUTHOR,  BDT_WRITE_AUTHOR,  ANSWER_WRITE_AUTHOR,  
	 * @see MEMO_WRITE_AUTHOR,  WEBEDITOR_WRITE_AUTHOR,  REGISTER_IP,  REGIST_DT,  REGISTER_ID,  UPDT_DT
	 * @see TABLE NAME : TN_BBS_INFO
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/gps/adm/bbs/selectBbsInfoExcelList.do")
	public ModelAndView selectBbsInfoExcelList (
			@ModelAttribute("bbsSearchVO")BbsSearchVO bbsSearchVO
			) throws Exception {
    	
    	// Excel 기능 공통
    	Map<String, String> ExcelMap = new HashMap<String, String>();
    	ExcelMap.put("fileName", "bbsInfoList.xls");    	
    	ExcelMap.put("title", "게시판관리");
    	ExcelMap.put("sheetName", "게시판관리");
    	
    	List bbsInfoList = this.bbsManageService.selectBbsInfoExcelList(bbsSearchVO);
    	bbsInfoList.add(ExcelMap);
		
		return new ModelAndView("ComExcelView", "ExcelList", bbsInfoList);
    	
	}
	
	/**
	 * 게시판 등록 페이지 호출
	 * @param bbsInfoVO
	 * @param bbsSearchVO
	 * @param userManageVO
	 * @param request
	 * @param model
	 * @return "gps/adm/bbs/registerBbsInfoPopup"
	 * @throws Exception
	 */
	@RequestMapping(value="/gps/adm/bbs/registerBbsInfoPopup.do")
	public String registerBbsInfoPopup(
			@ModelAttribute("bbsInfoVO") BbsInfoVO bbsInfoVO,
			@ModelAttribute("bbsSearchVO") BbsSearchVO bbsSearchVO,
			@ModelAttribute("userManageVO")UserManageVO userManageVO,
			HttpServletRequest request,
			ModelMap model
			)throws Exception{
		//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	if(gpsSessionVO!=null){
    		bbsSearchVO.setUsrId(gpsSessionVO.getUsrId());
    		//시스템목록
			List<SystemVO> systemList = (List<SystemVO>)this.bbsManageService.selectSystemList(bbsSearchVO);
			model.addAttribute("systemList", systemList);
    	}
		return "gps/adm/bbs/registerBbsInfoPopup";
	}

	/**
	 * 게시판명 중복검사
	 * @param bbsInfoVO
	 * @param response
	 * @param model
	 * @return "jsonView"
	 * @throws Exception
	 * @see COUNT(BBS_NM)
	 * @see TABLE NAME : TN_BBS_INFO
	 */
	@RequestMapping(value="/gps/adm/bbs/bbsNameDuplicateAt.do")
	public String bbsNameDuplicateAt(
			@ModelAttribute("bbsInfoVO")BbsInfoVO bbsInfoVO,
			HttpServletResponse response,
			ModelMap model
			)throws Exception{
		boolean duplicateAt = false;
		//게시판DB명중복검사
		if(!this.bbsManageService.bbsNameDuplicateAt(bbsInfoVO)){
			//중복메시지
			model.addAttribute("message",this.egovMessageSource.getMessage("gps.inUseName.msg"));
			duplicateAt = true;
		}else{
			model.addAttribute("message",this.egovMessageSource.getMessage("gps.useName.msg"));
		}
		model.addAttribute("duplicateAt",duplicateAt);
		return "jsonView";
	}

	/**
	 * 게시판 기본정보 등록 처리
	 * @param bbsInfoVO
	 * @param userManageVO
	 * @param status
	 * @param request
	 * @param model
	 * @return "gps/adm/bbs/registerBbsInfoPopup"
	 * @throws Exception
	 * @see TABLE NAME : TN_BBS_INFO
	 */
	@RequestMapping(value="/gps/adm/bbs/insertBbsInfo.do")
	public String insertBbsInfo(
			@ModelAttribute("bbsInfoVO") BbsInfoVO bbsInfoVO,
			@ModelAttribute("userManageVO")UserManageVO userManageVO,
			SessionStatus status,
			HttpServletRequest request,
			ModelMap model
			)throws Exception{
		
		bbsInfoVO = (BbsInfoVO)this.defaultDataSet.registSet(request, bbsInfoVO);
		bbsInfoVO.setBbsMngrPassword(EgovFileScrty.encryptPassword(bbsInfoVO.getBbsMngrPassword()));
		bbsInfoVO.setBbsMngrEmail(EgovFileScrty.encode(bbsInfoVO.getBbsMngrEmail()));
		//게시판 기본정보 등록 처리
		this.bbsManageService.insertBbsInfo(bbsInfoVO);
		status.setComplete();
		//등록처리메시지
		model.addAttribute("message",this.egovMessageSource.getMessage("success.common.insert"));
		model.addAttribute("bbsInfoVO", bbsInfoVO);
		model.addAttribute("popupCloseAt","Y");
		return "gps/adm/bbs/registerBbsInfoPopup";
	}
	
	/**
	 * 게시판 기본정보수정 페이지호출
	 * @param bbsSearchVO
	 * @param bbsInfoVO
	 * @param userManageVO
	 * @param request
	 * @param model
	 * @return "gps/adm/bbs/modifyBbsInfoPopupTab/탭구분값"
	 * @throws Exception
	 * @see BBS_ID,  DB_TNAME,  BBS_NM,  BBS_TITLE,  BBS_DC,  BBS_MNGR_NM,  SKIN_INFO,  TITLE_IMAGE,  
	 * @see TABLE_SIZE,  NEW_ICON_IMAGE,  NEW_ICON_INDICT_PD,  COOL_ICON_IMAGE,  COOL_ICON_INDICT_RDCNT,  
	 * @see HOT_ICON_IMAGE,  HOT_ICON_INDICT_RDCNT,  SUBPAGE_ID,  CTGRY_CODE,  LOGIN_USE_AT,  ANSWER_USE_AT,  
	 * @see WEBEDITOR_USE_AT,  MEMO_USE_AT,  AVATA_USE_AT,  RECOMMEND_USE_AT,  TITLE_DECO_USE_AT,  LIST_USE_AT,  
	 * @see RELATE_USE_AT,  ARND_USE_AT,  NTCE_PD_USE_AT,  NTCE_TRGET_USE_AT,  PASSWORD_USE_AT,  NCM_USE_AT,  
	 * @see FILE_UPLOAD_USE_AT,  ALBUM_AT,  ALBUM_STLE_AT,  EMAIL_SNDNG_AT,  UPLOAD_FILE_CO,  PGE_LIST_CO,  
	 * @see PGE_GROUP_CO,  NOTICE_CO,  ALBUM_COLUMN_CO,  ALBUM_LINE_CO,  LIST_EX_AUTHOR,  BDT_REDNG_AUTHOR,  BDT_WRITE_AUTHOR,  ANSWER_WRITE_AUTHOR,  
	 * @see MEMO_WRITE_AUTHOR,  WEBEDITOR_WRITE_AUTHOR,  REGISTER_IP,  REGIST_DT,  REGISTER_ID,  UPDT_DT
	 * @see TABLE NAME : TN_BBS_INFO
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/gps/adm/bbs/modifyBbsInfoPopupTab.do")
	public String modifyBbsInfoPopup(
			@ModelAttribute("bbsSearchVO")BbsSearchVO bbsSearchVO,
			@ModelAttribute("bbsInfoVO")BbsInfoVO bbsInfoVO,
			@ModelAttribute("userManageVO")UserManageVO userManageVO,
			HttpServletRequest request,
			ModelMap model)throws Exception{
		String dbTname = bbsInfoVO.getDbTname();
		String tabSe = bbsInfoVO.getTabSe();
		
		bbsInfoVO = (BbsInfoVO)this.bbsManageService.selectBbsInfo(bbsInfoVO);
		if(bbsInfoVO!=null){
			bbsInfoVO.setBbsMngrEmail(EgovFileScrty.decode(bbsInfoVO.getBbsMngrEmail()));
		}
		//게시판db명
		bbsInfoVO.setDbTname(dbTname);
		//탭구분
		bbsInfoVO.setTabSe(tabSe);
		
		//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	if(gpsSessionVO!=null){
    		bbsSearchVO.setUsrId(gpsSessionVO.getUsrId());
    		//시스템목록
			List<SystemVO> systemList = (List<SystemVO>)this.bbsManageService.selectSystemList(bbsSearchVO);
			model.addAttribute("systemList", systemList);
    	}
    	
    	//분류코드목록
    	Map<String, String> ctgryCodeMap = (Map<String, String>)cmmUseService.selectCmmCodeMap("21101305","");
    	
    	//공개방법목록
    	Map<String, String> othbcMthMap = (Map<String, String>)cmmUseService.selectCmmCodeMap("21101301","");
    	
    	//앨범형태목록
    	Map<String, String> albumSeMap = (Map<String, String>)cmmUseService.selectCmmCodeMap("2110101311","");
    	
    	//게시판스킨
    	List<String> bbsSkinList = (List<String>)this.bbsManageService.bbsSkinList();
    	
    	model.addAttribute("bbsInfoVO",bbsInfoVO );
    	model.addAttribute("ctgryCodeMap", ctgryCodeMap);
    	model.addAttribute("othbcMthMap", othbcMthMap);
    	model.addAttribute("albumSeMap", albumSeMap);
    	model.addAttribute("bbsSkinList", bbsSkinList);
    	//이미지경로
		model.addAttribute("WebImagePath", this.propertyService.getString("WebImagePath"));
		return "gps/adm/bbs/modifyBbsInfoPopupTab"+bbsInfoVO.getTabSe();
	}

	/**
	 * 게시판 기본 정보 수정
	 * @param bbsInfoVO
	 * @param multiRequest
	 * @param request
	 * @param status
	 * @param model
	 * @return "forward:/gps/adm/bbs/modifyBbsInfoPopupTab.do"
	 * @throws Exception
	 * @see TABLE NAME : TN_BBS_INFO
	 */
	@RequestMapping(value="/gps/adm/bbs/updateBbsInfo.do")
	public String updateBbsInfo(
			@ModelAttribute("bbsInfoVO")BbsInfoVO bbsInfoVO,
			final MultipartHttpServletRequest multiRequest,
			HttpServletRequest request,
			SessionStatus status,
			ModelMap model
		)throws Exception{
		// 게시판 기본정보 수정처리
		bbsInfoVO = (BbsInfoVO)this.defaultDataSet.updateSet(request, bbsInfoVO);
		bbsInfoVO.setBbsMngrEmail(EgovFileScrty.encode(bbsInfoVO.getBbsMngrEmail()));
		this.bbsManageService.updateBbsInfo(bbsInfoVO,multiRequest);
		status.setComplete();
		model.addAttribute("message",this.egovMessageSource.getMessage("success.common.update"));
		model.addAttribute("popupCloseAt","N");
		return "forward:/gps/adm/bbs/modifyBbsInfoPopupTab.do";
	}

	/**
	 * 게시판 삭제
	 * @param bbsInfoVO
	 * @param userManageVO
	 * @param model
	 * @return
	 * @throws Exception
	 * @see TABLE NAME : TN_BBS_INFO
	 */
	@RequestMapping(value="/gps/adm/bbs/deleteBbsInfo.do")
	public String deleteBbsInfo(
			@ModelAttribute("bbsInfoVO")BbsInfoVO bbsInfoVO,
			@ModelAttribute("userManageVO")UserManageVO userManageVO,
			ModelMap model
			)throws Exception{
		//삭제처리
		this.bbsManageService.deleteBbsInfo(bbsInfoVO);
		//삭제처리메시지
		model.addAttribute("message",this.egovMessageSource.getMessage("success.common.delete"));
		model.addAttribute("popupCloseAt","Y");
		return "gps/adm/bbs/modifyBbsInfoPopupTab"+bbsInfoVO.getTabSe();
	}
	
	/**
	 * 적용페이지검색팝업
	 * @param bbsInfoVO
	 * @param menuManageVO
	 * @param request
	 * @param status
	 * @param model
	 * @return "gps/adm/bbs/subPageSearchPopup"
	 * @throws Exception
	 * @see menuLv,menuId,menuNm,menuTy,leaf,ulOpenAt,endTagCnt
	 * @see TABLE NAME : TN_SYSTEM,TN_MENU
	 */
	@RequestMapping(value="/gps/adm/bbs/subPageSearchPopup.do")
	public String subPageSearchPopup(
			@ModelAttribute("bbsInfoVO")BbsInfoVO bbsInfoVO,
			@ModelAttribute("menuManageVO")MenuManageVO menuManageVO,
			HttpServletRequest request,
			SessionStatus status,
			ModelMap model
		)throws Exception{
		menuManageVO.setMenuId(bbsInfoVO.getSysId());
		List<MenuManageVO> menuList = (List<MenuManageVO>)this.menuManageService.selectSubpageMenuList(menuManageVO);
		model.addAttribute("menuList", menuList);
		return "gps/adm/bbs/subPageSearchPopup";
	}
}