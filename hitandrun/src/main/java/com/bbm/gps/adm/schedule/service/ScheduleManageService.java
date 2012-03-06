package com.bbm.gps.adm.schedule.service;

import java.util.List;


/** 
 * 일정관리에 대한 서비스 인터페이스 클래스를 정의한다. 
 * <p><b>NOTE:</b> 해당 클래스에는 controller에서 호출하는 메소드들의 인터페이스가 정의되어 있으며 
 * 구현은 ScheduleManageServiceImpl에 되어있다
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
public interface ScheduleManageService {

	/**
     * scheduleManageVO 일정목록 조회  
     * @param scheduleManageVO
     * @return List
     * @throws Exception
     * @see SC_TY,SC_TY_NM,SC_SN, SUBJECT,ORG_NM, SCHEDULE_CN,START_DT,END_DT,PLACE,STAT_ID,STAT_NM,PHON_CN,FAX_PHON_CN,
     * @see UPDT_DT, REGIST_DT
     * @see TABLE NAME : TN_SCHEDULE
     */
	@SuppressWarnings("unchecked")
	List selectScheduleList(ScheduleManageVO scheduleManageVO) throws Exception;
	
	/**
     * scheduleManageVO 일정목록 총 갯수를 조회한다.
     * @param scheduleManageVO
     * @return int
     * @throws Exception
     * @see totcnt 
     * @see TABLE NAME : TN_SCHEDULE
     */
    int selectScheduleListTotCnt(ScheduleManageVO scheduleManageVO) throws Exception;
	
    /**
	 * scheduleManageVO 선택된 일정 상세조회  
	 * @param scheduleManageVO
	 * @return ScheduleManageVO
	 * @throws Exception
	 * @see SC_TY,SC_TY_NM,SC_SN, SUBJECT,ORG_NM, SCHEDULE_CN,START_DT,END_DT,PLACE,STAT_ID,STAT_NM,PHON_CN,FAX_PHON_CN,
     * @see UPDT_DT, REGIST_DT
	 * @see TABLE NAME : TN_SCHEDULE
	 */ 
	ScheduleManageVO selectSchedule(ScheduleManageVO scheduleManageVO) throws Exception;
	
	
	/**
	 * scheduleManageVO 선택된 일정 삭제 
	 * @param scheduleManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_SCHEDULE
	 */
	void deleteSchedule(ScheduleManageVO scheduleManageVO) throws Exception;

	/**
	 * scheduleManageVO VO에 담겨있는 항목을 DB에 insert 
	 * @param scheduleManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_SCHEDULE
	 */
	void insertSchedule(ScheduleManageVO scheduleManageVO) throws Exception;

	/**
	 * scheduleManageVO 수정할 대상 일정을 업데이트 
	 * @param scheduleManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_SCHEDULE
	 */
	void updateSchedule(ScheduleManageVO scheduleManageVO) throws Exception;

	
	
}
