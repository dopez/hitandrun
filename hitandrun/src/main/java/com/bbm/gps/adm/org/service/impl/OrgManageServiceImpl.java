package com.bbm.gps.adm.org.service.impl;

import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import com.bbm.common.excel.ExcelService;
import com.bbm.gps.adm.org.service.OrgManageService;
import com.bbm.gps.adm.org.service.OrgManageVO;

/** 
 * 기관관리에 대한 서비스 구현클래스를 정의한다
 * <p><b>NOTE:</b> 서비스에 선언 되어있는 메소드들의 구현 클래스로 데이터 접근 클래스의 메소드를 호출한다
 * 메소드들 중에는 parameter를 넘기는 메소드도 있고 넘기지 않는 메소드도 존재한다
 * @author 범정부통계포털 이관형 
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
@Service("orgManageService")
public class OrgManageServiceImpl extends AbstractServiceImpl implements OrgManageService {

	/** orgDAO 서비스 호출 */ 
	@Resource(name="orgManageDAO")
    private OrgManageDAO orgManageDAO;
    
	/**  엑셀처리를 위한 Bean에 등록된 서비스 호출 */ 
    @Resource(name = "orgExcelInsertService")
    private ExcelService orgExcelInsertService;

	/** 
	 * 기관삭제
	 * @param orgManageVO  삭제 항목에 대한 구분자 
	 * @exception Exception 
	 * @see    
	 * @see TABLE NAME :
 	 */ 
	public void deleteOrg(OrgManageVO orgManageVO) throws Exception {
		orgManageDAO.deleteOrg(orgManageVO);
	}

	/** 
     * 기관 등록
     * @param orgManageVO insert할 항목에 대한 정보를 담고있는 기관VO
     * @exception Exception 
	 * @see    
	 * @see TABLE NAME :
     */ 
	public void insertOrg(OrgManageVO orgManageVO) throws Exception {
    	orgManageDAO.insertOrg(orgManageVO);    	
	}

	/** 
     * 기관 수정
     * @param orgManageVO 업데이트항목에 대한 기관 정보를 가지고있는VO
     * @exception Exception 
	 * @see    
	 * @see TABLE NAME :
     */ 
	public void updateOrg(OrgManageVO orgManageVO) throws Exception {
    	orgManageDAO.updateOrg(orgManageVO);    	
	}

	/** 
     * 기관정보 출력
     * @return OrgManageVO 상세화면 출력 정보
     * @exception Exception 
	 * @see    
	 * @see TABLE NAME :
     */ 
	public OrgManageVO selectOrg(OrgManageVO orgManageVO) throws Exception {
    	return (OrgManageVO)orgManageDAO.selectOrg(orgManageVO);
	}

	/** 
	 * 기관목록 조회  
	 * @param orgManageVO 검색조건
	 * @return List 조회한 기관목록
	 * @exception Exception 
	 * @see    
	 * @see TABLE NAME :
	 */ 
	@SuppressWarnings("unchecked")
	public List selectOrgList(OrgManageVO orgManageVO) throws Exception {
        return orgManageDAO.selectOrgList(orgManageVO);
	}
	
	/** 
	 * 기관목록의 총 갯수를 조회한다.
	 * @param  orgManageVO
	 * @return int 기관목록의 리스트
	 * @exception Exception 
     * @see COUNT(*) totcnt 
	 * @see TABLE NAME :
	 */ 
	public int selectOrgListTotCnt(OrgManageVO orgManageVO) throws Exception {
        return orgManageDAO.selectOrgListTotCnt(orgManageVO);
	}

    /** 
     * 기관목록검색 결과에 대한 excel 파일 다운로드 
     * @param orgManageVO
     * @return List 기관목록 정보
     * @exception Exception 
	 * @see 
	 * @see TABLE NAME :
     */ 
	@SuppressWarnings("unchecked")
	public List selectExcelOrgList(OrgManageVO orgManageVO) throws Exception {

		return orgManageDAO.selectExcelOrgList(orgManageVO);
	}

    /** 
	 * 기관정보 Excel 파일 업로드 메소드. 엑셀파일을 사용자로부터 받아 셀 안의 내용을 
	 * 리스트로 만든 후 DB에 insert
	 * @param file  사용자로부터 입력받는 파일 File
	 * @exception Exception 
	 * @see 
	 * @see TABLE NAME :
	 */ 
	public void insertExcelOrg(InputStream file, String excelVersion) throws Exception {
		orgExcelInsertService.uploadExcel("OrgManageDAO.insertExcelUpload", file, 1, (long) 5000, excelVersion); 
	}
}
