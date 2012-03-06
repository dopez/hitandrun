package com.bbm.gps.adm.org.service;



import java.io.InputStream;
import java.util.List;

/** 
 * 기관관리에 대한 서비스 인터페이스 클래스를 정의한다. 
 * <p><b>NOTE:</b> 기관관리 controller에서 호출하는 메소드들의 인터페이스가 정의되어 있으며 
 * 구현은 OrgManageServiceImpl에 되어있다
 * @author 범정부통계포털 이관형 
 * @since 2011.06.27 
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

public interface OrgManageService {

	/** 
	 * 기관삭제
	 * @param orgManageVO  삭제 항목에 대한 구분자 
	 * @exception Exception 
	 * @see    
	 * @see TABLE NAME :
 	 */ 
	void deleteOrg(OrgManageVO orgVO) throws Exception;

	/** 
     * 기관 등록
     * @param orgManageVO insert할 항목에 대한 정보를 담고있는 기관VO
     * @exception Exception 
	 * @see    
	 * @see TABLE NAME :
     */ 
	void insertOrg(OrgManageVO orgVO) throws Exception;

	/** 
     * 기관 수정
     * @param orgManageVO 업데이트항목에 대한 기관 정보를 가지고있는VO
     * @exception Exception 
	 * @see    
	 * @see TABLE NAME :
     */ 
	void updateOrg(OrgManageVO orgVO) throws Exception;

	/** 
     * 기관정보 출력
     * @return OrgManageVO 상세화면 출력 정보
     * @exception Exception 
	 * @see    
	 * @see TABLE NAME :
     */ 
	OrgManageVO selectOrg(OrgManageVO orgVO) throws Exception;

	/** 
	 * 기관목록 조회  
	 * @param orgManageVO 검색조건
	 * @return List 조회한 기관목록
	 * @exception Exception 
	 * @see    
	 * @see TABLE NAME :
	 */ 
	@SuppressWarnings("unchecked")
	List selectOrgList(OrgManageVO orgManageVO) throws Exception;
	
	/** 
	 * 기관목록의 총 갯수를 조회한다.
	 * @param  
	 * @return int 기관목록의 리스트
	 * @exception Exception 
     * @see COUNT(*) totcnt 
	 * @see TABLE NAME :
	 */ 
    int selectOrgListTotCnt(OrgManageVO orgManageVO) throws Exception;

    /** 
     * 기관목록검색 결과에 대한 excel 파일 다운로드 
     * @param 
     * @return List 기관목록 정보
     * @exception Exception 
	 * @see 
	 * @see TABLE NAME :
     */ 
	@SuppressWarnings("unchecked")
    List selectExcelOrgList(OrgManageVO orgManageVO) throws Exception;
    
    /** 
	 * 기관정보 Excel 파일 업로드 메소드. 엑셀파일을 사용자로부터 받아 셀 안의 내용을 
	 * 리스트로 만든 후 DB에 insert
	 * @param file  사용자로부터 입력받는 파일 File
	 * @param filename = 
	 * @exception Exception 
	 * @see 
	 * @see TABLE NAME :
	 */ 
    void insertExcelOrg(InputStream file, String excelVersion) throws Exception;
}
