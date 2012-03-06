package com.bbm.gps.adm.code.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/** 
 * 코드관리에 대한 서비스 인터페이스 클래스를 정의한다. 
 * <p><b>NOTE:</b> 해당 클래스에는 controller에서 호출하는 메소드들의 인터페이스가 정의되어 있으며 
 * 구현은 CodeManageServiceImpl에 되어있다
 * @author 범정부통계포털 이관형 
 * @since 2011.06.21 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.06.21     이관형      최초 생성 
 * 
 * </pre> 
 */

public interface CodeManageService {

	/**
     * 코드목록 조회
     * @return List
     * @throws Exception
     * @see CODE_ID,CODE_SE,UPPER_CODE_ID,CODE_TY_NO,CODE_NM,CODE_INDEX_NO,CODE_ENG_NM,CODE_ABBR_NM, 
     * @see CODE_ABBR_ENG_NM,UPPER_CODE_TY_NO,UPPER_CODE_INDEX_NO,VALID_BGNDE,VALID_ENDDE,
     * @see FNC_VAL_AT,ORG_ID,SYS_ID,USE_CODE_ID,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID, ADD_INFO, REGISTER_IP
     * @see TABLE NAME : TC_CODE
     */
	@SuppressWarnings("unchecked")
	public List selectUpperCodeList() throws Exception;

	/**
	 * 코드관리 ID에 대한 행삭제(코드삭제) 
	 * @param codeManageVO
	 * @throws Exception
	 * @see TABLE NAME : TC_CODE
	 */
	public void deleteCode(CodeManageVO codeManageVO) throws Exception;

	/**
	 * 코드등록
	 * @param codeManageVO
	 * @throws Exception
	 * @see TABLE NAME : TC_CODE
	 */
	public void insertCode(CodeManageVO codeManageVO) throws Exception;

	/**
	 * 코드수정
	 * @param codeManageVO
	 * @throws Exception
	 * @see TABLE NAME : TC_CODE
	 */
	public void updateCode(CodeManageVO codeManageVO) throws Exception;

	/**
	 * 코드 상세
	 * @param codeManageVO
	 * @return CodeManageVO
	 * @throws Exception
	 * @see CODE_ID,CODE_SE,UPPER_CODE_ID,CODE_TY_NO,CODE_NM,CODE_INDEX_NO,CODE_ENG_NM,CODE_ABBR_NM, 
     * @see CODE_ABBR_ENG_NM,UPPER_CODE_TY_NO,UPPER_CODE_INDEX_NO,VALID_BGNDE,VALID_ENDDE,
     * @see FNC_VAL_AT,ORG_ID,SYS_ID,USE_CODE_ID,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID, ADD_INFO, REGISTER_IP
     * @see TABLE NAME : TC_CODE
	 */
	public CodeManageVO selectCode(CodeManageVO codeManageVO) throws Exception;

	/**
     * 코드목록 조회
     * @param codeManageVO
     * @return List
     * @throws Exception
     * @see CODE_ID,CODE_SE,UPPER_CODE_ID,CODE_TY_NO,CODE_NM,CODE_INDEX_NO,CODE_ENG_NM,CODE_ABBR_NM, 
     * @see CODE_ABBR_ENG_NM,UPPER_CODE_TY_NO,UPPER_CODE_INDEX_NO,VALID_BGNDE,VALID_ENDDE,
     * @see FNC_VAL_AT,ORG_ID,SYS_ID,USE_CODE_ID,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID, ADD_INFO, REGISTER_IP
     * @see TABLE NAME : TC_CODE
     */
	@SuppressWarnings("unchecked")
	public List selectCodeList(CodeManageVO codeManageVO) throws Exception;

	/** 
	 * codeManageVO 코드목록 조회  
	 * @param codeManageVO  조회조건
	 * @return List 조회한 코드목록의 리스트
	 * @exception Exception 
	 * @see CODE_ID,CODE_SE,UPPER_CODE_ID,CODE_TY_NO,CODE_NM,CODE_INDEX_NO,CODE_ENG_NM,CODE_ABBR_NM, 
     * @see CODE_ABBR_ENG_NM,UPPER_CODE_TY_NO,UPPER_CODE_INDEX_NO,VALID_BGNDE,VALID_ENDDE,
     * @see FNC_VAL_AT,ORG_ID,SYS_ID,USE_CODE_ID,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID, ADD_INFO, REGISTER_IP
	 * @see TABLE NAME : TC_CODE
	 */ 
	@SuppressWarnings("unchecked")
	public Map selectGpsCodeList(CodeManageVO codeManageVO) throws Exception;
	
	/**
     * 코드목록의 총 갯수를 조회한다
     * @param codeManageVO
     * @return int
     * @throws Exception
     * @see COUNT(*) totcnt
     * @see TABLE NAME : TC_CODE 
     */
	public int selectCodeListTotCnt(CodeManageVO codeManageVO) throws Exception;

	/**
     * 코드ID를 중복 체크한다.
     * @param codeManageVO
     * @return int
     * @throws Exception
     * @see TABLE NAME : TC_CODE 
     */
	public int selectCheckUseCodeId(CodeManageVO codeManageVO) throws Exception;

	/**
     * 코드목록검색 결과에 대한 excel 파일 다운로드 
     * @param codeManageVO
     * @return List
     * @throws Exception
     * @see	코드ID,코드구분,상위코드번호,코드타입번호,한글코드명,코드색인번호,코드영문명,
     * @see 한글코드약명,영문코드약명,상위코드타입번호,상위코드색인번호,유효시작일,유효종료일, 
     * @see 함수값여부,기관번호,시스템구분,실사용ID,등록일,등록자,수정일,
     * @see 수정자,추가정보,등록자IP
     * @see TABLE NAME: TC_CODE
     */
	@SuppressWarnings("unchecked")
	public List selectExcelCodeList(CodeManageVO codeManageVO) throws Exception;

    /** 
	 * 코드정보 Excel 파일 업로드 메소드. 엑셀파일을 사용자로부터 받아 셀 안의 내용을 리스트로 만든 후 DB에 insert
	 * @param file  사용자로부터 입력받는 파일 File
	 * @param filename 파일명 
	 * @throws Exception 
	 * @see TABLE NAME : TC_CODE
	 */ 
	public void insertExcelCode(InputStream file, String excelVersion) throws Exception;
}
