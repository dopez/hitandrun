package com.bbm.gps.schedule.service;
import java.util.List;


/** 
 * 일정관리에 대한 서비스 인터페이스 클래스를 정의한다. 
 * <p><b>NOTE:</b> 해당 클래스에는 controller에서 호출하는 메소드들의 인터페이스가 정의되어 있으며 
 * 구현은 UserManageServiceImpl에 되어있다
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
 *   2011.08.03     이진우      최초 생성 
 *   
 * </pre> 
 */
public interface ScheduleService {

	
	/** 
	 * scheduleVO 월별 일정목록 목록 조회  
	 * @param scheduleVO  조회조건 Vo
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
     * @see TABLE NAME : TN_SCHEDULE 
	 */
	List selectMonthScheduleList(ScheduleVO scheduleVO) throws Exception;
	
	 
    /** 
	 * scheduleVO 주간 일정목록 목록 조회  
	 * @param scheduleVO  조회조건 Vo
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
     * @see TABLE NAME : TN_SCHEDULE 
	 */
	List selectWeekScheduleList(ScheduleVO scheduleVO) throws Exception;
	
	 
	
    /** 
	 * scheduleVO 선택된 일정 상세조회  
	 * @param scheduleVO  조회조건
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
	 * @see TABLE NAME : TN_SCHEDULE
	 */ 
	ScheduleVO selectSchedule(ScheduleVO scheduleVO) throws Exception;
	
	/** 
	 * scheduleVO 일정검색 목록 조회  
	 * @param scheduleVO  조회조건 Vo
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
     * @see TABLE NAME : TN_SCHEDULE 
	 */
	List searchScheduleList(ScheduleVO scheduleVO) throws Exception;
	
	 /** 
	 * scheduleVO 일정검색 목록 조회 총 갯수를 조회한다.
	 * @param  scheduleVO 총 갯수 조회조건 Vo
	 * @return int 조회한 목록의 리스트
	 * @exception Exception 
     * @see COUNT(*) totcnt 
	 */ 
    int searchScheduleListTotCnt(ScheduleVO scheduleVO) throws Exception;
	
}
