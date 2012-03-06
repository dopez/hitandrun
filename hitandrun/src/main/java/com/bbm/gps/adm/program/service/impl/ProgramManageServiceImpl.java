package com.bbm.gps.adm.program.service.impl;

import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import com.bbm.common.excel.ExcelService;
import com.bbm.gps.adm.program.service.ProgramManageService;
import com.bbm.gps.adm.program.service.ProgramManageVO;
 
/** 
 * 프로그램관리에 대한 서비스 구현클래스를 정의한다
 * <p><b>NOTE:</b> 프로그램관리서비스에 선언 되어있는 메소드들의 구현 클래스로 프로그램관리테이블 데이터 접근 클래스의 메소드를 호출한다
 * 메소드들 중에는 parameter를 넘기는 메소드도 있고 넘기지 않는 메소드도 존재한다
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
@Service("programManageService")
public class ProgramManageServiceImpl extends AbstractServiceImpl implements ProgramManageService {

	/** programManageDAO 서비스 호출 */ 
	@Resource(name="programManageDAO")
    private ProgramManageDAO programManageDAO;

	/**  엑셀처리를 위한 Bean에 등록된 서비스 호출 */ 
    @Resource(name = "programExcelInsertService")
    private ExcelService programExcelInsertService;

    /**
	 * 프로그램삭제
	 * @param programManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_PROGRAM
	 */
	public void deleteProgram(ProgramManageVO programManageVO) throws Exception {
		programManageDAO.deleteProgram(programManageVO);
	}

	/**
	 * 프로그램 등록
	 * @param programManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_PROGRAM
	 */
	public void insertProgram(ProgramManageVO programManageVO) throws Exception {
    	programManageDAO.insertProgram(programManageVO);    	
	}

	/**
	 * 프로그램 수정
	 * @param programManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_PROGRAM
	 */
	public void updateProgram(ProgramManageVO programManageVO) throws Exception {
    	programManageDAO.updateProgram(programManageVO);    	
	}

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
	public ProgramManageVO selectProgram(ProgramManageVO programManageVO) throws Exception {
    	ProgramManageVO ret = (ProgramManageVO)programManageDAO.selectProgram(programManageVO);
    	return ret;
	}
	
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
	public List selectProgramList(ProgramManageVO programManageVO) throws Exception {
        return programManageDAO.selectProgramList(programManageVO);
	}
	
	/**
     * 프로그램목록의 총 갯수를 조회한다.
     * @param programManageVO
     * @return int
     * @throws Exception
     * @see totcnt
     * @see TABLE NAME : TN_PROGRAM,TN_SYSTEM
     */
	public int selectProgramListTotCnt(ProgramManageVO programManageVO) throws Exception {
        return programManageDAO.selectProgramListTotCnt(programManageVO);
	}
	
	/**
     * 프로그램목록검색 결과에 대한 excel 파일 다운로드
     * @param programManageVO
     * @return List
     * @throws Exception
     * @see 시스템명,프로그램코드,프로그램명,프로그램한글명,프로그램URL,읽기,등록,수정,삭제
     * @see TABLE NAME : TN_PROGRAM
     */
	@SuppressWarnings("unchecked")
	public List selectExcelProgramList(ProgramManageVO programManageVO) throws Exception {

		return programManageDAO.selectExcelProgramList(programManageVO);
	}

	/** 
	 * 프로그램정보 Excel 파일 업로드 메소드. 엑셀파일을 사용자로부터 받아 셀 안의 내용을 
	 * 리스트로 만든 후 DB에 insert
	 * @param file  사용자로부터 입력받는 파일 File
	 * @exception Exception 
	 */ 
	public void insertExcelProgram(InputStream file, String address, String excelVersion) throws Exception {
		programExcelInsertService.uploadExcel("ProgramManageDAO.insertExcelUpload", file, 1, (long) 5000, address, excelVersion);
	}
}
