package com.bbm.gps.adm.program.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.GpsAbstractDAO;
import com.bbm.gps.adm.program.service.ProgramManageVO;

/** 
 * 프로그램관리에 대한 데이터 접근 클래스를 정의한다
 * <p><b>NOTE:</b> 넘어온 요청에 대해 DB작업을 수행하는 메소드들의 집합
 * DB에 직접 접근하며 쿼리문에 적용할 parameter를 보내주거나 단순 쿼리 실행을 하도록 호출한다
 * select, update, delete insert 함수를 사용하며 쿼리아이디와 parameter를 넘긴다
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
 *   2011.06.28     이관형      최초 생성 
 * 
 * </pre> 
 */
@Repository("programManageDAO")
public class ProgramManageDAO extends GpsAbstractDAO {

	/**
	 * 프로그램삭제
	 * @param programManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_PROGRAM
	 */
	public void deleteProgram(ProgramManageVO programManageVO) throws Exception {
		delete("ProgramManageDAO.deleteProgram", programManageVO);
	}

	/**
	 * 프로그램 등록
	 * @param programManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_PROGRAM
	 */
	public void insertProgram(ProgramManageVO programManageVO) throws Exception {
        insert("ProgramManageDAO.insertProgram", programManageVO);
	}

	/**
	 * 프로그램 수정
	 * @param programManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_PROGRAM
	 */
	public void updateProgram(ProgramManageVO programManageVO) throws Exception {
        update("ProgramManageDAO.updateProgram", programManageVO);
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
		return (ProgramManageVO)selectByPk("ProgramManageDAO.selectProgram", programManageVO);
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
        return list("ProgramManageDAO.selectProgramList", programManageVO);
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
        return (Integer)getSqlMapClientTemplate().queryForObject("ProgramManageDAO.selectProgramListTotCnt", programManageVO);
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
        return list("ProgramManageDAO.selectExcelList", programManageVO);
    }
}
