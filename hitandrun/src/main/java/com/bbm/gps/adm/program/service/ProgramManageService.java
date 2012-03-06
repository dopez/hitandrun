package com.bbm.gps.adm.program.service;

import java.io.InputStream;
import java.util.List;

/** 
 * 프로그램관리에 대한 서비스 인터페이스 클래스를 정의한다. 
 * <p><b>NOTE:</b> 프로그램관리 controller에서 호출하는 메소드들의 인터페이스가 정의되어 있으며 
 * 구현은 ProgramManageServiceImpl에 되어있다
 * @author 포탈통계 이관형 
 * @since 2011.06.17 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.06.17     이관형      최초 생성 
 * 
 * </pre> 
 */
public interface ProgramManageService {

	/**
	 * 프로그램삭제
	 * @param programManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_PROGRAM
	 */
	void deleteProgram(ProgramManageVO programManageVO) throws Exception;

	/**
	 * 프로그램 등록
	 * @param programManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_PROGRAM
	 */
	void insertProgram(ProgramManageVO programManageVO) throws Exception;

	/**
	 * 프로그램 수정
	 * @param programManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_PROGRAM
	 */
	void updateProgram(ProgramManageVO programManageVO) throws Exception;

	/**
	 * 프로그램정보 상제정보 조회
	 * @param programManageVO
	 * @return ProgramManageVO
	 * @throws Exception
	 * @see PROGRAM_ID, PROGRAM_FILE_NM, PROGRAM_STRE_PATH, PROGRAM_KOREANNM, URL, PROGRAM_DC,
	 * @see PROGRAM_TY_READ, PROGRAM_TY_CREATE, PROGRAM_TY_UPDATE, PROGRAM_TY_DELETE,SYS_ID,SYS_NM,
	 * @see REGISTER_IP,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID
	 * @see TABLE NAME : TN_PROGRAM,TN_SYSTEM
	 */
	ProgramManageVO selectProgram(ProgramManageVO programManageVO) throws Exception;
	
	/**
     * 프로그램목록 조회 
     * @param programManageVO
     * @return List
     * @throws Exception
     * @see PROGRAM_ID, PROGRAM_FILE_NM, PROGRAM_STRE_PATH, PROGRAM_KOREANNM, URL, PROGRAM_DC,
	 * @see PROGRAM_TY_READ, PROGRAM_TY_CREATE, PROGRAM_TY_UPDATE, PROGRAM_TY_DELETE,SYS_ID,SYS_NM,
	 * @see REGISTER_IP,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID
	 * @see TABLE NAME : TN_PROGRAM,TN_SYSTEM
     */
	@SuppressWarnings("unchecked")
	List selectProgramList(ProgramManageVO programManageVO) throws Exception;
	
	/**
     * 프로그램목록의 총 갯수를 조회한다.
     * @param programManageVO
     * @return int
     * @throws Exception
     * @see totcnt
     * @see TABLE NAME : TN_PROGRAM,TN_SYSTEM
     */
    int selectProgramListTotCnt(ProgramManageVO programManageVO) throws Exception;
	
    /**
     * 프로그램목록검색 결과에 대한 excel 파일 다운로드
     * @param programManageVO
     * @return List
     * @throws Exception
     * @see 시스템명,프로그램코드,프로그램명,프로그램한글명,프로그램URL,읽기,등록,수정,삭제
     * @see TABLE NAME : TN_PROGRAM
     */
    @SuppressWarnings("unchecked")
	List selectExcelProgramList(ProgramManageVO programManageVO) throws Exception;
    
    /** 
	 * 프로그램정보 Excel 파일 업로드 메소드. 엑셀파일을 사용자로부터 받아 셀 안의 내용을 
	 * 리스트로 만든 후 DB에 insert
	 * @param file  사용자로부터 입력받는 파일 File
	 * @exception Exception 
	 */ 
    void insertExcelProgram(InputStream file, String addr, String excelVersion ) throws Exception;
}
