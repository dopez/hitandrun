package com.bbm.gps.schedule.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import com.bbm.common.cmm.service.EgovCmmUseService;
import com.bbm.gps.adm.schedule.service.DateUtil;
import com.bbm.gps.schedule.service.ScheduleService;
import com.bbm.gps.schedule.service.ScheduleVO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * 일정관리 비즈니스 구현 클래스 
 * <p><b>NOTE:</b> 사용자관리 화면과 직접 연계되는 컨트롤러 클래스로 주로 JSP화면에서 호출되거나 
 * 해당 클래스 내부에서 호출되는 메소드들이 정의되어 있다
 * 해당 클래스 중에는 비즈니스 로직이 없고 단순히 다른 JSP화면으로 forword 시켜주는 메소드도 존재한다
 * DB select, DB insert, DB update, DB delete, 단순forward 등의 기능을 하기위한 메소드들의 집합
 * @author 범정부통계포털 이진우 
 * @since 2011.08.18 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.08.18     이진우       최초 생성 
 * 
 * </pre> 
 */

@Controller
public class ScheduleController {
	
	/** ScheduleService 서비스 호출 */ 
	@Resource(name = "scheduleService")
    private ScheduleService scheduleService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
	/** EgovCmmUseService 호출 */ 
	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;
    
	/** LOG */ 
	protected Log log = LogFactory.getLog(this.getClass());
	
	
	/** 
	 * scheduleVO 월별 일정목록 조회  
     * @param scheduleVO 월별 일정목록을 조회할 정보를 가지고있는 VO
     * @param model ModelMap
	 * @return 목록화면 JSP로 이동 ("gps/schedule/gpsMonthScheduleList")
	 * @exception Exception 
     * @see TABLE NAME : TN_SCHEDULE
	*/ 
    @RequestMapping(value="/gps/schedule/selectMonthScheduleList.do")
	public String selectMonthScheduleList (@ModelAttribute("scheduleVO") ScheduleVO scheduleVO
			, ModelMap model
			) throws Exception {
    	
    	if(scheduleVO.getYear()==null || scheduleVO.getYear().equals("")){
    		DateUtil dateUtil=new DateUtil();
    		scheduleVO.setSearchMm(dateUtil.getYear()+dateUtil.getMonthDigit());
    	}
    	
    	model.addAttribute("resultList", scheduleService.selectMonthScheduleList(scheduleVO));
		
        return "gps/schedule/gpsMonthScheduleList";
	}
    
    /** 
	 * scheduleVO 주별 일정목록 조회  
     * @param scheduleVO 주별 일정목록을 조회할 정보를 가지고있는 VO
     * @param model ModelMap
	 * @return 목록화면 JSP로 이동 ("gps/schedule/gpsWeekScheduleList")
	 * @exception Exception 
     * @see TABLE NAME : TN_SCHEDULE
	*/ 
    @SuppressWarnings({ "unchecked", "deprecation" })
	@RequestMapping(value="/gps/schedule/selectWeekScheduleList.do")
	public String selectWeekScheduleList (@ModelAttribute("scheduleVO") ScheduleVO scheduleVO
			, ModelMap model
			) throws Exception {
    	
    	DateUtil dateUtil=new DateUtil();
    	
    	int year = NumberUtils.toInt(scheduleVO.getYear(), dateUtil.getYear());
    	int month = NumberUtils.toInt(scheduleVO.getMonth(), dateUtil.getMonthInt());
    	int weekofmonth = NumberUtils.toInt(scheduleVO.getWeekofmonth(), dateUtil.getWeekOfMonth());
    	
    	//pageContext.setAttribute("weekofmonth", new Integer(weekofmonth));
    	model.addAttribute("weekofmonth", new Integer(weekofmonth));

    	dateUtil.setDate(year, month, 1);

    	List weekList = new ArrayList();

    	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd",Locale.KOREA);
    	Calendar calen = Calendar.getInstance();

    	calen.set(dateUtil.getYear(), dateUtil.getMonthInt() - 1, 1);
    	int endDay = dateUtil.getEndDay();

    	String fromDate = formatter.format(calen.getTime());

    	calen.set(Calendar.WEEK_OF_MONTH, 1);
    	calen.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
    	String toDate = formatter.format(calen.getTime());

    	weekList.add(new String[]{fromDate, toDate});

    	// 마지막 주차
    	Calendar endCal = Calendar.getInstance();
    	endCal.set(dateUtil.getYear(), dateUtil.getMonthInt() - 1, endDay);

    	for (int i = 1; i < endCal.get(Calendar.WEEK_OF_MONTH); i++) {
    		calen.add(Calendar.DAY_OF_MONTH, 7);

    		calen.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    		fromDate = formatter.format(calen.getTime());

    		calen.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
    		if (month != calen.get(Calendar.MONTH) + 1) {
    			calen.set(dateUtil.getYear(), dateUtil.getMonthInt() - 1, endDay);
    		} else {
    			calen.set(Calendar.DAY_OF_MONTH, (int) (calen.get(Calendar.DATE)));
    		}
    		toDate = formatter.format(calen.getTime());

    		weekList.add(new String[]{fromDate, toDate});
    	}

    	String[] searchWeek = (String[]) weekList.get(0);

    	String startWeek = searchWeek[0];
    	String endWeek = searchWeek[1];
    	try {
    		searchWeek = (String[]) weekList.get(weekofmonth - 1);

    		startWeek = searchWeek[0];
    		endWeek = searchWeek[1];
    	} catch (IndexOutOfBoundsException ie) {
    		if(log.isErrorEnabled()){
    			log.error("IndexOutOfBoundsException:"+ie);
    		}
    	}
    	//pageContext.setAttribute("weekList", weekList);
    	model.addAttribute("weekList", weekList);
    	
    	List dayList = new ArrayList();
    	for (int i=Integer.parseInt(startWeek); i <= Integer.parseInt(endWeek); i++) {
    		String tmpDay = String.valueOf(i);
    		Map map_t = new java.util.HashMap();
    		map_t.put("sc_date1", tmpDay);
    		map_t.put("sc_date", new java.sql.Timestamp(
    				Integer.parseInt(tmpDay.substring(0,4)) - 1900,
    				Integer.parseInt(tmpDay.substring(4,6))-1,
    				Integer.parseInt(tmpDay.substring(6)), 0, 0, 0, 0));

    		dayList.add(map_t);
    	}
    	model.addAttribute("dateUtil",dateUtil);
    	model.addAttribute("dayList", dayList);
    	//pageContext.setAttribute("dayList", dayList);
    	
    	scheduleVO.setStartWeek(startWeek);
    	scheduleVO.setEndWeek(endWeek);
    	
    	model.addAttribute("list", scheduleService.selectWeekScheduleList(scheduleVO));
    	
		
        return "gps/schedule/gpsWeekScheduleList";
	}
    
    
    /** 
	 * scheduleVO 일정을 수정하는 화면으로 이동 
	 * @param scheduleVO  수정 대상 일정정보
     * @param model ModelMap
	 * @return 일정보기 화면 JSP로 이동 ("gps/schedule/gpsViewSchedule") 
	 * @exception Exception 
     * @see TABLE NAME : TN_SCHEDULE
	*/ 
    @RequestMapping(value="/gps/schedule/viewSchedule.do")
	public String goMmodifySchedule (@ModelAttribute("scheduleVO") ScheduleVO scheduleVO
    		, ModelMap model
    ) throws Exception {
    	
    	
		model.addAttribute("scheduleVO", scheduleService.selectSchedule(scheduleVO));
		
        return "gps/schedule/gpsViewSchedule";
    }
    
    
    
    
    
    /** 
	 * scheduleVO 일정 검색  
     * @param scheduleVO  일정목록을 검색할 정보를 가지고있는 VO
     * @param model ModelMap
	 * @return 목록화면 JSP로 이동 ("gps/schedule/gps/gpsSearchScheduleList")
	 * @exception Exception 
     * @see TABLE NAME : TN_SCHEDULE
	*/ 
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/gps/schedule/searchScheduleList.do")
	public String searchScheduleList (@ModelAttribute("scheduleVO") ScheduleVO scheduleVO
			, ModelMap model
			) throws Exception {

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(scheduleVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(scheduleVO.getPageUnit());
		paginationInfo.setPageSize(scheduleVO.getPageSize());
		
		scheduleVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		scheduleVO.setLastIndex(paginationInfo.getLastRecordIndex());
		scheduleVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
			
		model.addAttribute("resultList", scheduleService.searchScheduleList(scheduleVO));
		paginationInfo.setTotalRecordCount(scheduleService.searchScheduleListTotCnt(scheduleVO));
		
        
		
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("scheduleVO", scheduleVO);
        
        Map typeMap = cmmUseService.selectCmmCodeMap("2110101312","");
        
        model.addAttribute("typeCond", typeMap);
        
        return "gps/schedule/gpsSearchScheduleList";
	}
    
}