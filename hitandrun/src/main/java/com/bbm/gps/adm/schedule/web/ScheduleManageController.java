package com.bbm.gps.adm.schedule.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import com.bbm.common.cmm.service.EgovCmmUseService;
import com.bbm.gps.adm.schedule.service.ScheduleManageService;
import com.bbm.gps.adm.schedule.service.ScheduleManageVO;
import com.bbm.gps.cmm.DefaultDataSet;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * 일정관리 비즈니스 구현 클래스 
 * <p><b>NOTE:</b> 일정관리 화면과 직접 연계되는 컨트롤러 클래스로 주로 JSP화면에서 호출되거나 
 * 해당 클래스 내부에서 호출되는 메소드들이 정의되어 있다
 * 해당 클래스 중에는 비즈니스 로직이 없고 단순히 다른 JSP화면으로 forword 시켜주는 메소드도 존재한다
 * DB select, DB insert, DB update, DB delete, 단순forward 등의 기능을 하기위한 메소드들의 집합
 * @author 범정부통계포털 이진우 
 * @since 2011.08.03 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.08.03     이진우       최초 생성 
 * 
 * </pre> 
 */

@Controller
public class ScheduleManageController {
	
	/** ScheduleManageService 서비스 호출 */ 
	@Resource(name = "scheduleManageService")
    private ScheduleManageService scheduleManageService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
	/** EgovCmmUseService 호출 */ 
	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** defaultDataSet */
    @Resource(name="defaultDataSet")
    protected DefaultDataSet defaultDataSet;
    
	/** LOG */ 
	protected Log log = LogFactory.getLog(this.getClass());
	
    /**
     * scheduleManageVO 일정목록 조회  
     * @param scheduleManageVO
     * @param model
     * @return "gps/adm/schedule/scheduleList"
     * @throws Exception
     * @see SC_TY,SC_TY_NM,SC_SN, SUBJECT,ORG_NM, SCHEDULE_CN,START_DT,END_DT,PLACE,STAT_ID,STAT_NM,PHON_CN,FAX_PHON_CN,
     * @see UPDT_DT, REGIST_DT
     * @see TABLE NAME : TN_SCHEDULE
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/gps/adm/schedule/selectScheduleList.do")
	public String selectScheduleList (@ModelAttribute("scheduleManageVO") ScheduleManageVO scheduleManageVO
			, ModelMap model
			) throws Exception {

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(scheduleManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(scheduleManageVO.getPageUnit());
		paginationInfo.setPageSize(scheduleManageVO.getPageSize());
		
		scheduleManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		scheduleManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		scheduleManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
			
		model.addAttribute("resultList", scheduleManageService.selectScheduleList(scheduleManageVO));
		paginationInfo.setTotalRecordCount(scheduleManageService.selectScheduleListTotCnt(scheduleManageVO));
		
        
		
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("scheduleManageVO", scheduleManageVO);

        Map typeMap = cmmUseService.selectCmmCodeMap("2110101312","");
        
        model.addAttribute("typeCond", typeMap);
        
        return "gps/adm/schedule/scheduleList";
	}
     
    /**
     * 일정등록화면 페이지로 이동
     * @param scheduleManageVO
     * @param model
     * @return gps/adm/schedule/scheduleRegist
     * @throws Exception
     * @see TABLE NAME : TN_SCHEDULE
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/gps/adm/schedule/registSchedule.do")
	public String goRegistSchedule(@ModelAttribute("scheduleManageVO") ScheduleManageVO scheduleManageVO ,ModelMap model
    ) throws Exception {
    	Map typeMap = cmmUseService.selectCmmCodeMap("21107125","");
    	model.addAttribute("scTyList", typeMap);
        return "gps/adm/schedule/scheduleRegist";
    }
    
    /**
     * scheduleManageVO 일정을 수정하는 화면으로 이동
     * @param scheduleManageVO
     * @param model
     * @return "gps/adm/schedule/scheduleUpdate"
     * @throws Exception
     * @see SC_TY,SC_TY_NM,SC_SN, SUBJECT,ORG_NM, SCHEDULE_CN,START_DT,END_DT,PLACE,STAT_ID,STAT_NM,PHON_CN,FAX_PHON_CN,
     * @see UPDT_DT, REGIST_DT
     * @see TABLE NAME : TN_SCHEDULE
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/gps/adm/schedule/modifySchedule.do")
	public String goMmodifySchedule (@ModelAttribute("scheduleManageVO") ScheduleManageVO scheduleManageVO
    		, ModelMap model
    ) throws Exception {
    	
    	
		model.addAttribute("scheduleManageVO", scheduleManageService.selectSchedule(scheduleManageVO));
		
		Map typeMap = cmmUseService.selectCmmCodeMap("2110101312","");
    	model.addAttribute("scTyList", typeMap);
		
        return "gps/adm/schedule/scheduleUpdate";
    }
    
    /**
     * scheduleManageVO 에 담겨있는 항목을 DB에 insert 
     * @param scheduleManageVO
     * @param request
     * @param bindingResult
     * @param model
     * @return forward:/gps/adm/schedule/selectScheduleList.do
     * @throws Exception
     * @see TABLE NAME : TN_SCHEDULE
     */
    @RequestMapping(value="/gps/adm/schedule/insertSchedule.do")
    public String insertSchedule (
    		@ModelAttribute("scheduleManageVO") ScheduleManageVO scheduleManageVO,
    		HttpServletRequest request,
    		BindingResult bindingResult, 
    		ModelMap model
    ) throws Exception {
    	
    	//등록자 , 등록IP
    	scheduleManageVO = (ScheduleManageVO) defaultDataSet.registSet(request, scheduleManageVO);
    	
    	scheduleManageService.insertSchedule(scheduleManageVO);
    	
    	return "forward:/gps/adm/schedule/selectScheduleList.do";
    }

    /**
     * scheduleManageVO 수정할 대상 게시물ID에 해당하는 행을 업데이트 
     * @param scheduleManageVO
     * @param request
     * @param model
     * @return forward:/gps/adm/schedule/selectScheduleList.do
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/schedule/updateSchedule.do")
	public String updateSchedule (@ModelAttribute("scheduleManageVO") ScheduleManageVO scheduleManageVO
			, HttpServletRequest request
    		, ModelMap model
    ) throws Exception {

    	scheduleManageVO = (ScheduleManageVO) defaultDataSet.updateSet(request, scheduleManageVO);
		scheduleManageService.updateSchedule(scheduleManageVO);
		
        return "forward:/gps/adm/schedule/selectScheduleList.do";
		    	
    }
  
    /**
     * scheduleManageVO 키값에 대한 일정을 삭제(DB 행삭제) 
     * @param scheduleManageVO
     * @param model
     * @return forward:/gps/adm/schedule/selectScheduleList.do
     * @throws Exception
     * @see TABLE NAME : TN_SCHEDULE
     */
    @RequestMapping(value="/gps/adm/schedule/deleteSchedule.do")
	public String deleteSchedule (@ModelAttribute("scheduleManageVO")ScheduleManageVO scheduleManageVO
			, ModelMap model
			) throws Exception {
    	
    	scheduleManageService.deleteSchedule(scheduleManageVO);
    	return "forward:/gps/adm/schedule/selectScheduleList.do";
	}
    
    
	
    
}