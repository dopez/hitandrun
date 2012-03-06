package com.bbm.gps.adm.stplat.web;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import com.bbm.common.cmm.EgovMessageSource;
import com.bbm.common.cmm.service.EgovCmmUseService;
import com.bbm.common.util.cas.service.EgovSessionCookieUtil;
import com.bbm.gps.adm.author.intergrated.service.IgrAuthorManageService;
import com.bbm.gps.adm.author.intergrated.service.IgrAuthorManageVO;
import com.bbm.gps.adm.stplat.service.StplatManageService;
import com.bbm.gps.adm.stplat.service.StplatManageVO;
import com.bbm.gps.cmm.DefaultDataSet;
import com.bbm.gps.login.service.GpsSessionVO;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * 약관관리 비즈니스 구현 클래스
 * <p><b>NOTE:</b> 약관관리 화면과 직접 연계되는 컨트롤러 클래스로 주로 JSP화면에서 호출되거나 
 * 해당 클래스 내부에서 호출되는 메소드들이 정의되어 있다
 * 해당 클래스 중에는 비즈니스 로직이 없고 단순히 다른 JSP화면으로 forword 시켜주는 메소드도 존재한다
 * 약관정보 조회,입력,수정,삭제 요청을 처리한다
 * @author 범정부통계포털 이관형 
 * @since 2011.06.27 
 * @version 1.0 
 * @see 
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
public class StplatManageController {
	
	/** StplatManageService 서비스 호출 */ 
	@Resource(name = "stplatManageService")
    private StplatManageService stplatManageService;
	
	/** IgrAuthorManageService 서비스 호출 */ 
	@Resource(name = "igrAuthorManageService")
    private IgrAuthorManageService igrAuthorManageService;

	/** massage source */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
	/** EgovCmmUseService 호출 */ 
	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;
	
	/** EgovIdGnrService */
    @Resource(name = "stplatIdGnrService")
    private EgovIdGnrService stplatIdGnrService;
	
	/** defaultDataSet */
    @Resource(name="defaultDataSet")
    protected DefaultDataSet defaultDataSet;

	/** LOG */ 
	protected Log log = LogFactory.getLog(this.getClass());
	
	/**
     * 약관목록조회(stplatManageVO 검색조건에따라 약관목록을 조회)  
     * @param stplatManageVO
     * @return "gps/adm/stplat/stplatList"
     * @throws Exception
     * @see stplatSe,stplatSeNm,stplatId,sysId,sysNm,stplatNm,stplatCn,stplatUseSe
     * @see TABLE NAME : TN_STPLAT
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/gps/adm/stplat/selectStplatList.do")
	public String selectStplatList (@ModelAttribute("stplatManageVO") StplatManageVO stplatManageVO
			, HttpServletRequest request
			, ModelMap model
			) throws Exception {

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(stplatManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(stplatManageVO.getPageUnit());
		paginationInfo.setPageSize(stplatManageVO.getPageSize());
		
		stplatManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		stplatManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		stplatManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        List stplatList = stplatManageService.selectStplatList(stplatManageVO);
        model.addAttribute("resultList", stplatList);
        
        int totCnt = stplatManageService.selectStplatListTotCnt(stplatManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("stplatManageVO", stplatManageVO);
        
        //시스템코드
        GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	IgrAuthorManageVO authorManageVO = new IgrAuthorManageVO();
    	authorManageVO.setUserId(gpsSessionVO.getUsrId());
        model.addAttribute("sysId", igrAuthorManageService.selectSysComboMap(authorManageVO));
        
        model.addAttribute("searchCondition", cmmUseService.selectCmmCodeMap("COM036",""));
        
        return "gps/adm/stplat/stplatList";
	}
    
    /** 
     * 약관등록화면 호출
     * @param stplatManageVO 약관관리VO
     * @param model ModelMap
     * @return "gps/adm/stplat/stplatRegist"
     * @throws Exception 
     * @see TABLE NAME : 
    */ 
    @RequestMapping(value="/gps/adm/stplat/registerStplat.do")
	public String registerStplat(@ModelAttribute("stplatManageVO") StplatManageVO stplatManageVO
								,HttpServletRequest request
								,ModelMap model) throws Exception {
    	
    	//시스템코드
        GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	IgrAuthorManageVO authorManageVO = new IgrAuthorManageVO();
    	authorManageVO.setUserId(gpsSessionVO.getUsrId());
        model.addAttribute("sysId", igrAuthorManageService.selectSysComboMap(authorManageVO));
        
        //약관구분
        model.addAttribute("stplatSeList", cmmUseService.selectCmmCodeMap("21101308",""));
        return "gps/adm/stplat/stplatRegist";
    }
    
    /**
	 * 약관등록 처리(stplatManageVO에 담겨있는 항목을 DB에 등록) 
	 * @param stplatManageVO
	 * @return "forward:/gps/adm/stplat/selectStplatList.do"
	 * @throws Exception
	 * @see TABLE NAME : TN_STPLAT
	 */
    @RequestMapping(value="/gps/adm/stplat/insertStplat.do")
    public String insertStplat (
    		@ModelAttribute("stplatManageVO") StplatManageVO stplatManageVO,
    		HttpServletRequest request,
    		ModelMap model
    ) throws Exception {
    	
    	stplatManageVO.setStplatId(stplatIdGnrService.getNextStringId());
    	
    	stplatManageVO = (StplatManageVO)this.defaultDataSet.registSet(request, stplatManageVO);
    	
    	stplatManageService.insertStplat(stplatManageVO);
    	
    	return "forward:/gps/adm/stplat/selectStplatList.do";
    }
    
    /** 
	 * 약관목록 수정화면 호출
	 * @param stplatManageVO  수정 약관에 대한 정보
     * @param model ModelMap
	 * @return "gps/adm/stplat/stplatUpdate"
	 * @throws Exception 
	*/ 
    @RequestMapping(value="/gps/adm/stplat/modifyStplat.do")
	public String modifyStplat (@ModelAttribute("stplatManageVO") StplatManageVO stplatManageVO
			, HttpServletRequest request
    		, ModelMap model
    ) throws Exception {
    	StplatManageVO resultVO = stplatManageService.selectStplat(stplatManageVO);
		model.addAttribute("stplatManageVO", resultVO);
		
        // 시스템코드
		GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
		
		IgrAuthorManageVO authorManageVO = new IgrAuthorManageVO();
    	authorManageVO.setUserId(gpsSessionVO.getUsrId());
    	
        model.addAttribute("sysId", igrAuthorManageService.selectSysComboMap(authorManageVO));
        
        // 검색조건
        resultVO.setPageIndex(stplatManageVO.getPageIndex());
        resultVO.setPageUnit(stplatManageVO.getPageUnit());
        resultVO.setSysIdSearch(stplatManageVO.getSysIdSearch());
        resultVO.setActvtyAtSearch(stplatManageVO.getActvtyAtSearch());
        resultVO.setSearchCondition(stplatManageVO.getSearchCondition());
        resultVO.setSearchKeyword(stplatManageVO.getSearchKeyword());
        
        // 약관구분
        model.addAttribute("stplatSeList", cmmUseService.selectCmmCodeMap("21101308",""));
        
        return "gps/adm/stplat/stplatUpdate";
    }
    
    /**
     * 약관목록 수정 처리
     * @param stplatManageVO
     * @param request
     * @param model
     * @return "redirect:/gps/adm/stplat/selectStplatList.do"
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/stplat/updateStplat.do")
	public String updateStplat (
    		@ModelAttribute("stplatManageVO") StplatManageVO stplatManageVO,
    		HttpServletRequest request,
    		ModelMap model
    ) throws Exception {
    	
    	stplatManageVO = (StplatManageVO)this.defaultDataSet.updateSet(request, stplatManageVO);
    	
		stplatManageService.updateStplat(stplatManageVO);
		model.addAttribute("stplatManageVO",stplatManageVO);
		
		//수정처리메시지
		model.addAttribute("message",this.egovMessageSource.getMessage("success.common.update"));

        return "forward:/gps/adm/stplat/selectStplatList.do";
    }

    /**
     * 약관사용여부변경
     * @param stplatManageVO
     * @param model
     * @return "gps/adm/stplat/selectStplatList.do"
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/stplat/updateStplatUseAt.do")
	public String updateStplatActvtyAt (@ModelAttribute("stplatManageVO") StplatManageVO stplatManageVO
    		, ModelMap model
    ) throws Exception {
    	
        return "redirect:gps/adm/stplat/selectStplatList.do";    	
    }
    
    /**
     * 약관을 삭제
     * @param stplatManageVO
     * @param model
     * @return "forward:/gps/adm/stplat/selectStplatList.do"
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/stplat/deleteStplat.do")
	public String deleteStplat (@ModelAttribute("stplatManageVO")StplatManageVO stplatManageVO
			, ModelMap model) throws Exception {

    	stplatManageService.deleteStplat(stplatManageVO);
    	model.addAttribute("stplatManageVO", stplatManageVO);
    	
    	//삭제처리메시지
		model.addAttribute("message",this.egovMessageSource.getMessage("success.common.delete"));
    	
    	return "forward:/gps/adm/stplat/selectStplatList.do";
	}
}