package com.bbm.gps.adm.code.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.GpsAbstractDAO;
import com.bbm.gps.adm.code.service.CodeManageVO;

/** 
 * 코드관리에 대한 데이터 접근 클래스를 정의한다
 * <p><b>NOTE:</b> 넘어온 요청에 대해 DB작업을 수행하는 메소드들의 집합
 * DB에 직접 접근하며 쿼리문에 적용할 parameter를 보내주거나 단순 쿼리 실행을 하도록 호출한다
 * select, update, delete 함수를 사용하며 쿼리아이디와 parameter를 넘긴다
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
 *   2011.05.15     이관형      최초 생성 
 * 
 * </pre> 
 */
@Repository("codeManageDAO")
public class CodeManageDAO extends GpsAbstractDAO {

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
	public List selectUpperCodeList() throws Exception {
        return list("CodeManageDAO.selectUpperCodeList", null);
    }

	/**
	 * 코드관리 ID에 대한 행삭제(코드삭제) 
	 * @param codeManageVO
	 * @throws Exception
	 * @see TABLE NAME : TC_CODE
	 */
	public void deleteCode(CodeManageVO codeManageVO) throws Exception {
		delete("CodeManageDAO.deleteCode", codeManageVO);
	}

	/**
	 * 코드등록
	 * @param codeManageVO
	 * @throws Exception
	 * @see TABLE NAME : TC_CODE
	 */
	public void insertCode(CodeManageVO codeManageVO) throws Exception {
        insert("CodeManageDAO.insertCode", codeManageVO);
	}

	/**
	 * 코드수정
	 * @param codeManageVO
	 * @throws Exception
	 * @see TABLE NAME : TC_CODE
	 */
	public void updateCode(CodeManageVO codeManageVO) throws Exception {
        update("CodeManageDAO.updateCode", codeManageVO);
	}

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
	public CodeManageVO selectCode(CodeManageVO codeManageVO) throws Exception {
		return (CodeManageVO)selectByPk("CodeManageDAO.selectCode", codeManageVO);
	}

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
	public List selectCodeList(CodeManageVO codeManageVO) throws Exception {
        return list("CodeManageDAO.selectCodeList", codeManageVO);
    }

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
	public Map selectGpsCodeList(CodeManageVO codeManageVO) throws Exception {
    	Map codeMap  = new  LinkedHashMap();
    	ArrayList sysCodeList = (ArrayList) list("CodeManageDAO.selectGpsCodeList_sys", codeManageVO);
    	ArrayList cmmCodeList = (ArrayList) list("CodeManageDAO.selectGpsCodeList_cmm", codeManageVO);
    	
    	for(int i = 0; i < sysCodeList.size(); i++)
		{
    		CodeManageVO _comboVO = (CodeManageVO)sysCodeList.get(i);
			codeMap.put(_comboVO.getCodeId(), _comboVO.getCodeNm());
		}
    	for(int i = 0; i < cmmCodeList.size(); i++)
		{
    		CodeManageVO _comboVO = (CodeManageVO)cmmCodeList.get(i);
			codeMap.put(_comboVO.getCodeId(), _comboVO.getCodeNm());
		}
    	
    	return  codeMap;
    }

    /**
     * 코드목록의 총 갯수를 조회한다
     * @param codeManageVO
     * @return int
     * @throws Exception
     * @see COUNT(*) totcnt
     * @see TABLE NAME : TC_CODE 
     */
    public int selectCodeListTotCnt(CodeManageVO codeManageVO) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("CodeManageDAO.selectCodeListTotCnt", codeManageVO);
    }

    /**
     * 코드ID를 중복 체크한다.
     * @param codeManageVO
     * @return int
     * @throws Exception
     * @see TABLE NAME : TC_CODE 
     */
    public int selectCheckUseCodeId(CodeManageVO codeManageVO) throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("CodeManageDAO.selectCheckUseCodeId", codeManageVO);
    }

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
	public List selectExcelCodeList(CodeManageVO codeManageVO) throws Exception {
        return list("CodeManageDAO.selectExcelList", codeManageVO);
    }
}