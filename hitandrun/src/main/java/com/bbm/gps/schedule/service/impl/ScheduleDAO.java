package com.bbm.gps.schedule.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.GpsAbstractDAO;
import com.bbm.gps.schedule.service.ScheduleVO;

/** 
 * 일정관리에 대한 데이터 접근 클래스를 정의한다
 * <p><b>NOTE:</b> 넘어온 요청에 대해 DB작업을 수행하는 메소드들의 집합
 * DB에 직접 접근하며 쿼리문에 적용할 parameter를 보내주거나 단순 쿼리 실행을 하도록 호출한다
 * select, update, delete 함수를 사용하며 쿼리아이디와 parameter를 넘긴다
 * @author 범정부통계포털 이관형 
 * @since 2011.08.04 
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
@Repository("scheduleDAO")
public class ScheduleDAO extends GpsAbstractDAO {
	
	/** 
	 * scheduleVO 월간 일정목록 조회  
	 * @param scheduleVO  조회조건
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
	 * @see TABLE NAME : TN_SCHEDULE
	 */ 
    public List selectMonthScheduleList(ScheduleVO scheduleVO) throws Exception {
        return list("ScheduleDAO.selectMonthScheduleList", scheduleVO);
    }
    
    
    
    /** 
	 * scheduleVO 주간 일정목록 조회  
	 * @param scheduleVO  조회조건
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
	 * @see TABLE NAME : TN_SCHEDULE
	 */ 
    public List selectWeekScheduleList(ScheduleVO scheduleVO) throws Exception {
        return list("ScheduleDAO.selectWeekScheduleList", scheduleVO);
    }
    
    
    
    /** 
	 * scheduleVO 선택된 일정 상세조회  
	 * @param scheduleVO  조회조건
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
	 * @see TABLE NAME : TN_SCHEDULE
	 */ 
	public ScheduleVO selectSchedule(ScheduleVO scheduleVO) throws Exception {
		return (ScheduleVO)selectByPk("ScheduleDAO.selectSchedule", scheduleVO);
	}
	
	/** 
	 * scheduleVO 일정 조회  
	 * @param scheduleVO  조회조건
	 * @return List 조회한 목록의 리스트
	 * @exception Exception 
	 * @see TABLE NAME : TN_SCHEDULE
	 */ 
    public List searchScheduleList(ScheduleVO scheduleVO) throws Exception {
        return list("ScheduleDAO.searchScheduleList", scheduleVO);
    }
    
    /** 
	 * scheduleVO 일정목록 총 갯수를 조회한다.
	 * @param  scheduleVO
	 * @return int 조회한 목록의 리스트
	 * @exception Exception 
     * @see COUNT(*) totcnt 
	 */
    public int searchScheduleListTotCnt(ScheduleVO scheduleVO) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("ScheduleDAO.searchScheduleListTotCnt", scheduleVO);
    }
    
}
