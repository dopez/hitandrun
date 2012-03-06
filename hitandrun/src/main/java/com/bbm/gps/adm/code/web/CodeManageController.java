package com.bbm.gps.adm.code.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import com.bbm.common.cmm.EgovMessageSource;
import com.bbm.common.cmm.service.EgovFileMngService;
import com.bbm.common.cmm.service.FileVO;
import com.bbm.common.util.ExcelUtil;
import com.bbm.common.util.StringUtil;
import com.bbm.common.util.cas.service.EgovSessionCookieUtil;
import com.bbm.gps.adm.code.service.CodeManageService;
import com.bbm.gps.adm.code.service.CodeManageVO;
import com.bbm.gps.cmm.DefaultDataSet;
import com.bbm.gps.login.service.GpsSessionVO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


/** 
 * 코드관리 비즈니스 구현 클래스 
 * <p><b>NOTE:</b> 코드관리 화면과 직접 연계되는 컨트롤러 클래스로 주로 JSP화면에서 호출되거나 
 * 해당 클래스 내부에서 호출되는 메소드들이 정의되어 있다
 * 해당 클래스 중에는 비즈니스 로직이 없고 단순히 다른 JSP화면으로 forword 시켜주는 메소드도 존재한다
 * DB select, DB insert, DB update, DB delete, 단순forward 등의 기능을 하기위한 메소드들의 집합
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
 *   2011.06.21     이관형       최초 생성 
 * 
 * </pre> 
 */
@Controller
public class CodeManageController {
	
	/** CodeManageService 서비스 호출 */ 
	@Resource(name = "codeManageService")
    private CodeManageService codeManageService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** defaultDataSet */
    @Resource(name="defaultDataSet")
    protected DefaultDataSet defaultDataSet;

    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
    
    /** messageSource */
	@Resource(name = "messageSource")
	protected MessageSource messageSource;

	/** LOG */ 
	protected Log log = LogFactory.getLog(this.getClass());

    /**
     * 코드관리메인페이지
     * @param model
     * @return "gps/adm/code/codeManageMain"
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/code/codeManageMain.do")
	public String codeManageMain ( 
			ModelMap model
			) throws Exception {
        return "gps/adm/code/codeManageMain";
	}

    /**
     * 시스템코드 삭제(DB 행삭제) 
     * @param codeManageVO
     * @param model
     * @return "gps/adm/code/grpCodeUpdate"
     * @throws Exception
     * @see TABLE NAME : TC_CODE
     */
    @RequestMapping(value="/gps/adm/code/deleteGrpCode.do")
	public String deleteGrpCode (@ModelAttribute("codeManageVO")CodeManageVO codeManageVO
			, ModelMap model
			) throws Exception {
    	
    	codeManageService.deleteCode(codeManageVO);
    	// TODO 상위코드 삭제시 상위코드에 종속되 있던 하위코드는 삭제 하지 않음.
		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));
		model.addAttribute("codeManageVO", codeManageVO);

    	return "gps/adm/code/grpCodeUpdate";
	}

    /**
     * 상위코드목록
     * @param codeManageVO
     * @param model
     * @return "gps/adm/code/grpCodeList"
     * @throws Exception
     * @see CODE_ID,CODE_SE,UPPER_CODE_ID,CODE_TY_NO,CODE_NM,CODE_INDEX_NO,CODE_ENG_NM,CODE_ABBR_NM, 
     * @see CODE_ABBR_ENG_NM,UPPER_CODE_TY_NO,UPPER_CODE_INDEX_NO,VALID_BGNDE,VALID_ENDDE,
     * @see FNC_VAL_AT,ORG_ID,SYS_ID,USE_CODE_ID,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID, ADD_INFO, REGISTER_IP
     * @see TABLE NAME : TC_CODE
     */
    @RequestMapping(value="/gps/adm/code/selectGrpCodeList.do")
	public String selectGrpCodeList (@ModelAttribute("codeManageVO") CodeManageVO codeManageVO
			, HttpServletRequest request
			, ModelMap model
			) throws Exception {

    	// 상위코드가 NULL인 코드
    	codeManageVO.setSearchCondition("1");
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	codeManageVO.setUserId(gpsSessionVO.getUsrId());
		model.addAttribute("upperCodeList", codeManageService.selectGpsCodeList(codeManageVO));

		// 왼쪽UI
        model.addAttribute("codeList", codeManageService.selectCodeList(codeManageVO));

        return "gps/adm/code/grpCodeList";
	}

    /**
     * 상위코드 등록 페이지로 이동
     * @param codeManageVO
     * @param model
     * @return "gps/adm/code/grpCodeRegist"
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/code/registerGrpCode.do")
	public String registerGrpCode(@ModelAttribute("codeManageVO") CodeManageVO codeManageVO
			, ModelMap model
    ) throws Exception {
        
    	CodeManageVO upperCodeNmVo = new CodeManageVO();
    	upperCodeNmVo.setCodeId(codeManageVO.getUpperCodeId());
    	upperCodeNmVo = codeManageService.selectCode(upperCodeNmVo);
    	if (null != upperCodeNmVo) {
        	codeManageVO.setUpperCodeNm(upperCodeNmVo.getCodeNm());
    	}
    	
        return "gps/adm/code/grpCodeRegist";
    }

    /**
     * 상위코드 등록
     * @param request
     * @param codeManageVO
     * @param model
     * @return "gps/adm/code/grpCodeRegist"
     * @throws Exception
     * @see TABLE NAME : TC_CODE
     */
    @RequestMapping(value="/gps/adm/code/insertGrpCode.do")
    public String insertGrpCode (HttpServletRequest request,
    		@ModelAttribute("codeManageVO") CodeManageVO codeManageVO,
    		ModelMap model
    ) throws Exception {

    	// 정상적인 상위코드인지를 체크
    	if (null != codeManageVO.getUpperCodeId()) { 
    		// 분류에 설정할 상위코드에 대한 정보 취득
    		CodeManageVO upperCodeParameterVO = new CodeManageVO();
    		upperCodeParameterVO.setCodeId(codeManageVO.getUpperCodeId());
    		CodeManageVO upperCodeVO = codeManageService.selectCode(upperCodeParameterVO);
    		
    		// 코드 구분 - 분류코드:사용코드
    		codeManageVO.setCodeSe(codeManageVO.getUseCodeId());
    		// 시스템 구분
    		codeManageVO.setSysId(upperCodeVO.getSysId());
	    	// 상위코드색인번호 - 상위코드의상위코드색인번호
	    	codeManageVO.setUpperCodeIndexNo(upperCodeVO.getCodeIndexNo());
	    	// 상위 코드타입번호  - 분류코드:10
	    	codeManageVO.setUpperCodeTyNo("10");
	    	// 코드색인번호 - 분류코드:사용코드
	    	codeManageVO.setCodeIndexNo(codeManageVO.getUseCodeId());
	    	// 코드타입번호분류코드:21
	    	codeManageVO.setCodeTyNo("21");
	    	// 상위코드 - 상위코드
	    	codeManageVO.setUpperCodeId(upperCodeVO.getCodeId());
    		// 코드ID - (CODE_TY_NO +  부모CODE_INDEX_NO + USE_CODE_ID
	    	codeManageVO.setCodeId(codeManageVO.getCodeTyNo().concat(codeManageVO.getUpperCodeIndexNo().concat(codeManageVO.getUseCodeId())));
	    	// 기관번호
        	// 포털 사용자 취득
        	GpsSessionVO sessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
	    	codeManageVO.setOrgId(sessionVO.getOrgId());

	    	// [사용자 등록 정보]
	    	codeManageVO = (CodeManageVO) defaultDataSet.registSet(request, codeManageVO);

	    	if (null !=codeManageVO.getValidBgnde()) {
	        	codeManageVO.setValidBgnde(codeManageVO.getValidBgnde().replaceAll("-", ""));
	    	}
	    	if (null != codeManageVO.getValidEndde()) {

	        	codeManageVO.setValidEndde(codeManageVO.getValidEndde().replaceAll("-", ""));
	    	}
	    	codeManageService.insertCode(codeManageVO);

			model.addAttribute("codeManageVO", codeManageVO);
			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			
    	} else {
    		log.debug("상위코드 [ " + codeManageVO.getUpperCodeId() + " ]는 잘못된 코드입니다.");
    	}
    	return "gps/adm/code/grpCodeRegist";
    }

    /**
     * 상위코드 수정 화면으로 이동
     * @param codeManageVO
     * @param model
     * @return "gps/adm/code/grpCodeUpdate"
     * @throws Exception
     * @see CODE_ID,CODE_SE,UPPER_CODE_ID,CODE_TY_NO,CODE_NM,CODE_INDEX_NO,CODE_ENG_NM,CODE_ABBR_NM, 
     * @see CODE_ABBR_ENG_NM,UPPER_CODE_TY_NO,UPPER_CODE_INDEX_NO,VALID_BGNDE,VALID_ENDDE,
     * @see FNC_VAL_AT,ORG_ID,SYS_ID,USE_CODE_ID,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID, ADD_INFO, REGISTER_IP
     * @see TABLE NAME : TC_CODE
     */
    @RequestMapping(value="/gps/adm/code/modifyGrpCode.do")
	public String modifyGrpCode (@ModelAttribute("codeManageVO") CodeManageVO codeManageVO
    		, ModelMap model
    ) throws Exception {

    	codeManageVO = codeManageService.selectCode(codeManageVO);
        
    	CodeManageVO upperCodeNmVo = new CodeManageVO();
    	upperCodeNmVo.setCodeId(codeManageVO.getUpperCodeId());
    	upperCodeNmVo = codeManageService.selectCode(upperCodeNmVo);
    	if (null != upperCodeNmVo) {
        	codeManageVO.setUpperCodeNm(upperCodeNmVo.getCodeNm());
    	}
    	
		model.addAttribute("codeManageVO", codeManageVO);
        return "gps/adm/code/grpCodeUpdate";
    }

    /**
     * 상위코드 수정
     * @param request
     * @param codeManageVO
     * @param model
     * @return "gps/adm/code/grpCodeUpdate"
     * @throws Exception
     * @see TABLE NAME : TC_CODE
     */
    @RequestMapping(value="/gps/adm/code/updateGrpCode.do")
	public String updateGrpCode (HttpServletRequest request,
			@ModelAttribute("codeManageVO") CodeManageVO codeManageVO,
			ModelMap model
    ) throws Exception {

    	// TODO 상위코드 업데이트시 상위코드에 종속 되있던 하위코드는 삭제 하지 않음.
    	codeManageVO.setValidBgnde(codeManageVO.getValidBgnde().replaceAll("-", ""));
    	codeManageVO.setValidEndde(codeManageVO.getValidEndde().replaceAll("-", ""));
    	
    	codeManageVO = (CodeManageVO) defaultDataSet.updateSet(request, codeManageVO);
    	
		codeManageService.updateCode(codeManageVO);

		model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
		
        return "gps/adm/code/grpCodeUpdate";
		    	
    }

    /**
     * 화면으로부터선택된 코드를 삭제
     * @param codeManageVO
     * @param model
     * @return "forward:/gps/adm/code/selectCodeList.do"
     * @throws Exception
     * @see TABLE NAME : TC_CODE
     */
    @RequestMapping(value="/gps/adm/code/deleteCodeList.do")
	public String deleteCodeList (@ModelAttribute("codeManageVO")CodeManageVO codeManageVO
			, ModelMap model
			) throws Exception {
    	
    	StringTokenizer codeIdList = new StringTokenizer(codeManageVO.getCodeIdList(), ",");
		while(codeIdList.hasMoreTokens() )
		{
			codeManageVO.setCodeId(codeIdList.nextToken());

			codeManageService.deleteCode(codeManageVO);
		}
		
    	return "forward:/gps/adm/code/selectCodeList.do";
	}
    
    /**
     * 코드삭제(DB 행삭제) 
     * @param codeManageVO
     * @param model
     * @return "forward:/gps/adm/code/selectCodeList.do"
     * @throws Exception
     * @see TABLE NAME :TC_CODE
     */
    @RequestMapping(value="/gps/adm/code/deleteCode.do")
	public String deleteCode (@ModelAttribute("codeManageVO")CodeManageVO codeManageVO
			, ModelMap model
			) throws Exception {
    	
    	codeManageService.deleteCode(codeManageVO);
    	return "forward:/gps/adm/code/selectCodeList.do";
	}

    /**
     * 코드등록 화면으로 이동
     * @param codeManageVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/code/registerCode.do")
	public String registerCode(
			@ModelAttribute("codeManageVO") CodeManageVO codeManageVO
			, ModelMap model
    ) throws Exception {
        
    	CodeManageVO upperCodeNmVo = new CodeManageVO();
    	upperCodeNmVo.setCodeId(codeManageVO.getUpperCodeId());
    	upperCodeNmVo = codeManageService.selectCode(upperCodeNmVo);
    	if (null != upperCodeNmVo) {
        	codeManageVO.setUpperCodeNm(upperCodeNmVo.getCodeNm());
    	}
    	

        return "gps/adm/code/codeRegist";
    }
    
    /**
     * 코드 등록
     * @param request
     * @param codeManageVO
     * @param model
     * @return "forward:/gps/adm/code/selectCodeList.do"
     * @throws Exception
     * @see TABLE NAME : TC_CODE
     */
    @RequestMapping(value="/gps/adm/code/insertCode.do")
    public String insertCode (HttpServletRequest request,
    		@ModelAttribute("codeManageVO") CodeManageVO codeManageVO,
    		ModelMap model
    ) throws Exception {

    	// 정상적인 상위코드인지를 체크
    	if (null != codeManageVO.getUpperCodeId()) { 
    		// 분류에 설정할 상위코드에 대한 정보 취득
    		CodeManageVO upperCodeParameterVO = new CodeManageVO();
    		upperCodeParameterVO.setCodeId(codeManageVO.getUpperCodeId());
    		CodeManageVO upperCodeVO = codeManageService.selectCode(upperCodeParameterVO);
    		
    		// 코드 구분 - 하위코드:분류코드의 사용코드
    		codeManageVO.setCodeSe(upperCodeVO.getUseCodeId());
    		// 시스템 구분
    		codeManageVO.setSysId(upperCodeVO.getSysId());
	    	// 상위코드색인번호 - 상위코드의상위코드색인번호
	    	codeManageVO.setUpperCodeIndexNo(upperCodeVO.getCodeIndexNo());
	    	// 상위 코드타입번호  - 하위코드:분류코드타입번호
	    	codeManageVO.setUpperCodeTyNo(upperCodeVO.getCodeTyNo());
	    	// 코드색인번호 - 하위코드:분류코드사용코드
	    	codeManageVO.setCodeIndexNo(upperCodeVO.getUseCodeId());
	    	// 코드타입번호분류코드:12
	    	codeManageVO.setCodeTyNo("12");
	    	// 상위코드 - 상위코드
	    	codeManageVO.setUpperCodeId(upperCodeVO.getCodeId());
    		// 코드ID - (CODE_TY_NO +  부모CODE_INDEX_NO + USE_CODE_ID
	    	codeManageVO.setCodeId(codeManageVO.getCodeTyNo().concat(codeManageVO.getUpperCodeIndexNo().concat(codeManageVO.getUseCodeId())));
	    	// 기관번호
        	// 포털 사용자 취득
        	GpsSessionVO sessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
	    	codeManageVO.setOrgId(sessionVO.getOrgId());

	    	// [사용자 등록 정보]
	    	codeManageVO = (CodeManageVO) defaultDataSet.registSet(request, codeManageVO);

	    	if (null !=codeManageVO.getValidBgnde()) {
	        	codeManageVO.setValidBgnde(codeManageVO.getValidBgnde().replaceAll("-", ""));
	    	}
	    	if (null != codeManageVO.getValidEndde()) {

	        	codeManageVO.setValidEndde(codeManageVO.getValidEndde().replaceAll("-", ""));
	    	}
	    	codeManageService.insertCode(codeManageVO);

			model.addAttribute("message", egovMessageSource.getMessage("success.common.insert"));
			
    	} else {
    		log.debug("상위코드 [ " + codeManageVO.getUpperCodeId() + " ]는 잘못된 코드입니다.");
    	}
    	return "forward:/gps/adm/code/selectCodeList.do";
    }
    
    /**
     * 코드목록 조회  
     * @param codeManageVO
     * @param model
     * @return "gps/adm/code/codeList"
     * @throws Exception
     * @see CODE_ID,CODE_SE,UPPER_CODE_ID,CODE_TY_NO,CODE_NM,CODE_INDEX_NO,CODE_ENG_NM,CODE_ABBR_NM, 
     * @see CODE_ABBR_ENG_NM,UPPER_CODE_TY_NO,UPPER_CODE_INDEX_NO,VALID_BGNDE,VALID_ENDDE,
     * @see FNC_VAL_AT,ORG_ID,SYS_ID,USE_CODE_ID,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID, ADD_INFO, REGISTER_IP
     * @see TABLE NAME : TC_CODE
     */
    @RequestMapping(value="/gps/adm/code/selectCodeList.do")
	public String selectCodeList (@ModelAttribute("codeManageVO") CodeManageVO codeManageVO
			, HttpServletRequest request
			, ModelMap model
			) throws Exception {
    	
    	if (null != codeManageVO.getUpperCodeId()) {

        	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
        	codeManageVO.setUserId(gpsSessionVO.getUsrId());
            model.addAttribute("resultList", codeManageService.selectCodeList(codeManageVO));
            
        	CodeManageVO upperCodeNmVo = new CodeManageVO();
        	upperCodeNmVo.setCodeId(codeManageVO.getUpperCodeId());
        	upperCodeNmVo = codeManageService.selectCode(upperCodeNmVo);
        	if (null != upperCodeNmVo) {
            	codeManageVO.setUpperCodeNm(upperCodeNmVo.getCodeNm());
        	}
    	}
        return "gps/adm/code/codeList";
	}

    /**
     * 코드 수정하는 화면으로 이동 
     * @param codeManageVO
     * @param model
     * @return "gps/adm/code/codeUpdate"
     * @throws Exception
     * @see CODE_ID,CODE_SE,UPPER_CODE_ID,CODE_TY_NO,CODE_NM,CODE_INDEX_NO,CODE_ENG_NM,CODE_ABBR_NM, 
     * @see CODE_ABBR_ENG_NM,UPPER_CODE_TY_NO,UPPER_CODE_INDEX_NO,VALID_BGNDE,VALID_ENDDE,
     * @see FNC_VAL_AT,ORG_ID,SYS_ID,USE_CODE_ID,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID, ADD_INFO, REGISTER_IP
     * @see TABLE NAME : TC_CODE
     */
    @RequestMapping(value="/gps/adm/code/modifyCode.do")
	public String modifyCode (@ModelAttribute("codeManageVO") CodeManageVO codeManageVO
    		, ModelMap model
    ) throws Exception {

    	codeManageVO = codeManageService.selectCode(codeManageVO);
        
    	CodeManageVO upperCodeNmVo = new CodeManageVO();
    	upperCodeNmVo.setCodeId(codeManageVO.getUpperCodeId());
    	upperCodeNmVo = codeManageService.selectCode(upperCodeNmVo);
    	if (null != upperCodeNmVo) {
        	codeManageVO.setUpperCodeNm(upperCodeNmVo.getCodeNm());
    	}
    	
		model.addAttribute("codeManageVO", codeManageVO);
        return "gps/adm/code/codeUpdate";
    }

    /**
     * 코드수정
     * @param request
     * @param codeManageVO
     * @param model
     * @return "forward:/gps/adm/code/selectCodeList.do"
     * @throws Exception
     * @see TABLE NAME : TC_CODE
     */
    @RequestMapping(value="/gps/adm/code/updateCode.do")
	public String updateCode (HttpServletRequest request,
			@ModelAttribute("codeManageVO") CodeManageVO codeManageVO,
			ModelMap model
    ) throws Exception {
    	
    	// [사용자 수정 정보]
    	codeManageVO = (CodeManageVO) defaultDataSet.registSet(request, codeManageVO);
		codeManageService.updateCode(codeManageVO);

		model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));
		
        return "forward:/gps/adm/code/selectCodeList.do";
		    	
    }

    /**
     * 코드아이디중복체크처리
     * @param request
     * @param codeManageVO
     * @param model
     * @return "gps/adm/code/codeIDCheck"
     * @throws Exception
     * @see TABLE NAME : TC_CODE
     */
    @RequestMapping(value="/gps/adm/code/checkUseCodeId.do")
	public String selectCheckUseCodeId (
			HttpServletResponse response,
			@ModelAttribute("codeManageVO") CodeManageVO codeManageVO,
			ModelMap model
    ) throws Exception {

		boolean checkAt = false;
		if(0 < codeManageService.selectCheckUseCodeId(codeManageVO)){
			//중복메시지
			model.addAttribute("message", egovMessageSource.getMessage("gps.checkduplicate.msg"));
			checkAt = true;
		}else{
			model.addAttribute("message", egovMessageSource.getMessage("gps.checksuccess.msg"));
		}
		model.addAttribute("checkAt",checkAt);
		return "jsonView";
		    	
    }
    
    /**
     * 코드목록검색 결과에 대한 excel 파일 다운로드 
     * @param codeManageVO
     * @param model
     * @return ModelAndView
     * @throws Exception
     * @see	코드ID,코드구분,상위코드번호,코드타입번호,한글코드명,코드색인번호,코드영문명,
     * @see 한글코드약명,영문코드약명,상위코드타입번호,상위코드색인번호,유효시작일,유효종료일, 
     * @see 함수값여부,기관번호,시스템구분,실사용ID,등록일,등록자,수정일,
     * @see 수정자,추가정보,등록자IP
     * @see TABLE NAME: TC_CODE
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/gps/adm/code/selectCodeExcelDownload.do")
	public ModelAndView selectCodeExcelDownload (@ModelAttribute("codeManageVO") CodeManageVO codeManageVO
			, ModelMap model
			) throws Exception {
    	
    	// Excel 기능 공통
    	Map<String, String> ExcelMap = new HashMap<String, String>();
    	CodeManageVO codeVO = new CodeManageVO();
    	codeVO.setCodeId(codeManageVO.getUpperCodeId());
    	codeVO = codeManageService.selectCode(codeVO);
    	ExcelMap.put("fileName", "code_manage.xls");    	
    	ExcelMap.put("title", "코드 관리_".concat(codeVO.getCodeNm()));
    	ExcelMap.put("sheetName", "코드 관리_".concat(codeVO.getCodeNm()));
    	
    	List codeList =  codeManageService.selectExcelCodeList(codeManageVO);
    	codeList.add(ExcelMap);
		
		return new ModelAndView("ComExcelView", "ExcelList", codeList);
    	
	}

	/**
	 * codeVO Excel 업로드 화면호출
	 * @param codeManageVO
	 * @param model
	 * @return "gps/adm/code/codeExcelRegist"
	 * @throws Exception
	 */
	@RequestMapping(value="/gps/adm/code/registerCodeExcelUpload.do")
 	public String registerCodeExcelUpload (@ModelAttribute("codeManageVO") CodeManageVO codeManageVO
 			, ModelMap model
 			) throws Exception {
		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId("FILE_000000000011151");
		fileVO.setFileSn("0");
		
		fileVO  = fileService.selectFileInf(fileVO);
		model.addAttribute("fileVO", fileVO);
		
		return "gps/adm/code/codeExcelRegist";
	}    

	/** codeVO Excel 파일 업로드 메소드. 엑셀파일을 사용자로부터 받아 셀 안의 내용을 리스트로 만든 후 DB에 insert     
	 * @param codeManageVO
	 * @param request
	 * @param model
	 * @return "gps/adm/code/codeExcelRegist"
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/gps/adm/code/insertCodeExcelUpload.do")
	public String insertCodeExcelUpload(@ModelAttribute("codeManageVO") CodeManageVO codeManageVO	
			, final HttpServletRequest request
			, Model model) throws Exception {

    	final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		final Map<String, MultipartFile> files = multiRequest.getFileMap();

		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file = null;

		try {
			while (itr.hasNext()) {
				Entry<String, MultipartFile> entry = itr.next();

				file = entry.getValue();
				if (!"".equals(file.getOriginalFilename())) {
					if (StringUtil.checkExcelFileExt(file.getOriginalFilename())) {
						model.addAttribute("uploadFailMsg",this.egovMessageSource.getMessage("fail.common.filenameext"));
						return "forward:/gps/adm/code/registerCodeExcelUpload.do";
					} else {
						codeManageService.insertExcelCode(file.getInputStream(), ExcelUtil.getExcelVersion(file));
					}
				}
			}
		} catch (Exception e) {
			if(log.isErrorEnabled()){
				log.error(e);
			}
		}finally{
        	try{ if( file.getInputStream() != null ) file.getInputStream().close(); }catch(IOException ioe){
        		if(log.isErrorEnabled()){
    				log.error(ioe);
    			}
        	}
        }
		model.addAttribute("message", egovMessageSource.getMessage("gps.uploadsuccess.msg"));
		return "gps/adm/code/codeExcelRegist";
	}
    
}