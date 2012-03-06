package com.bbm.gps.schedule.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import com.bbm.gps.schedule.service.ScheduleService;
import com.bbm.gps.schedule.service.ScheduleVO;

/** 
 * 일정관리에 대한 서비스 구현클래스를 정의한다
 * <p><b>NOTE:</b> 서비스에 선언 되어있는 메소드들의 구현 클래스로 데이터 접근 클래스의 메소드를 호출한다
 * 메소드들 중에는 parameter를 넘기는 메소드도 있고 넘기지 않는 메소드도 존재한다
 * @author 범정부통계포털 이관형 
 * @since 2011.08.18 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.08.18     이진우      최초 생성 
 * 
 * </pre> 
 */
@Service("scheduleService")
public class ScheduleServiceImpl extends AbstractServiceImpl implements ScheduleService {

	/** scheduleDAO 서비스 호출 */ 
	@Resource(name="scheduleDAO")
    private ScheduleDAO scheduleDAO;

	/** 
	 * scheduleVO 월간 일정 목록 조회  
	 * @param scheduleVO  조회조건 Vo
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
     * @see TABLE NAME : TN_SCHEDULE
	 */
	public List selectMonthScheduleList(ScheduleVO scheduleVO) throws Exception {
		// TODO Auto-generated method stub
		return scheduleDAO.selectMonthScheduleList(scheduleVO);
	}

	
	/** 
	 * scheduleVO 주간 일정 목록 조회  
	 * @param scheduleVO  조회조건 Vo
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
     * @see TABLE NAME : TN_SCHEDULE
	 */
	public List selectWeekScheduleList(ScheduleVO scheduleVO) throws Exception {
		// TODO Auto-generated method stub
		return scheduleDAO.selectWeekScheduleList(scheduleVO);
	}

	
	
	/** 
	 * scheduleVO 선택된 일정 상세조회  
	 * @param scheduleVO  조회조건
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
	 * @see TABLE NAME : TN_SCHEDULE
	 */ 
	public ScheduleVO selectSchedule(ScheduleVO scheduleVO) throws Exception {
		// TODO Auto-generated method stub
		return (ScheduleVO)scheduleDAO.selectSchedule(scheduleVO);
	}
	
	/** 
	 * scheduleVO 일정 검색 조회  
	 * @param scheduleVO  조회조건 Vo
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
     * @see TABLE NAME : 
	 */
	public List searchScheduleList(ScheduleVO scheduleVO) throws Exception {
		return scheduleDAO.searchScheduleList(scheduleVO);
	}
	
	/** 
	 * scheduleVO 일정 검색 총 갯수를 조회한다.
	 * @param  scheduleVO 총 갯수 조회조건 Vo
	 * @return int 조회한 목록의 리스트
	 * @exception Exception 
     * @see COUNT(*) totcnt 
	 */ 
	public int searchScheduleListTotCnt(ScheduleVO scheduleVO) throws Exception {
        return scheduleDAO.searchScheduleListTotCnt(scheduleVO);
	}

	
}
