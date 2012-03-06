package com.bbm.gps.adm.csnst.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import com.bbm.common.cmm.ComDefaultCodeVO;
import com.bbm.common.cmm.EgovMessageSource;
import com.bbm.common.cmm.service.EgovFileMngService;
import com.bbm.common.cmm.service.FileVO;
import com.bbm.common.cmm.service.NaraCmmService;
import com.bbm.common.util.cas.service.EgovSessionCookieUtil;
import com.bbm.gps.adm.csnst.service.CsnstIemManageService;
import com.bbm.gps.adm.csnst.service.CsnstManageService;
import com.bbm.gps.adm.csnst.service.CsnstManageVO;
import com.bbm.gps.adm.csnst.service.CsnstMenoManageService;
import com.bbm.gps.adm.csnst.service.CsnstMenoVO;
import com.bbm.gps.adm.csnst.service.CsnstQesitmManageService;
import com.bbm.gps.adm.csnst.service.CsnstRspnsManageService;
import com.bbm.gps.adm.csnst.service.CsnstRspnsVO;
import com.bbm.gps.adm.system.service.SystemManageService;
import com.bbm.gps.cmm.DefaultDataSet;
import com.bbm.gps.login.service.GpsSessionVO;

import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/** 
 * 만족도관리 비즈니스 구현 클래스 
 * <p><b>NOTE:</b> 만족도 목록, 상세정보를 조회 하며 수정,삭제 요청을 처리한다.
 * 만족도관리 화면과 직접 연계되는 컨트롤러 클래스로 주로 JSP화면에서 호출되거나 
 * 해당 클래스 내부에서 호출되는 메소드들이 정의되어 있다
 * 해당 클래스 중에는 비즈니스 로직이 없고 단순히 다른 JSP화면으로 forword 시켜주는 메소드도 존재한다
 * DB select, DB insert, DB update, DB delete, 단순redirect 등의 기능을 하기위한 메소드들의 집합
 * @author 범정부통계포털 이관형 
 * @since 2011.10.21 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.10.21     이관형       최초 생성 
 * 
 * </pre> 
 */

@Controller
public class CsnstManageController {

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
	/** CsnstManageService 서비스 호출 */ 
	@Resource(name = "csnstManageService")
    private CsnstManageService csnstManageService;

	/** csnstQesitmManageService 서비스 호출 */ 
	@Resource(name = "csnstQesitmManageService")
    private CsnstQesitmManageService csnstQesitmManageService;

	/** csnstIemManageService 서비스 호출 */ 
	@Resource(name = "csnstIemManageService")
    private CsnstIemManageService csnstIemManageService;

	/** csnstRspnsManageService 서비스 호출 */ 
	@Resource(name = "csnstRspnsManageService")
    private CsnstRspnsManageService csnstRspnsManageService;

	/** csnstMenoManageService 서비스 호출 */ 
	@Resource(name = "csnstMenoManageService")
    private CsnstMenoManageService csnstMenoManageService;
	
	/** SystemManageService 서비스 호출 */ 
	@Resource(name = "systemManageService")
    private SystemManageService systemManageService;

    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** LOG */ 
	protected Log log = LogFactory.getLog(this.getClass());
	
	/** defaultDataSet */
    @Resource(name="defaultDataSet")
    protected DefaultDataSet defaultDataSet;

	/** NaraCmmService 호출 */ 
	@Resource(name="NaraCmmService")
	private NaraCmmService naraCmmService;

	/** fileService 호출 */ 
    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;

    /**
     * 만족도목록 조회 
     * @param csnstManageVO CsnstManageVO
     * @param model ModelMap
     * @param request HttpServletRequest
     * @return "gps/adm/csnst/csnstList" 만족도 조사 목록 화면
     * @throws Exception
	 * @see CSNST_ID, CSNST_SN, SYS_ID, SYS_NM, CSNST_SE, CSNST_NM, CSNST_DN, CSNST_USE_AT
	 * @see VALID_BGNDE, VALID_ENDDE, CSNST_OTHBCSE, CSNST_PASSWORD, CSNST_DPLCT_PERM_SE
	 * @see CSNST_MEMO_USE_AT, CSNST_MEMO_WEBEDIT_SE, CSNST_MEMO_AUTHOR_SE, CSNST_FILE_NM
	 * @see CSNST_FILE_MASK, CSNST_FILE_SIZE, CSNST_FILE_MIME, CSNST_FILE_DC, REGIST_DT
	 * @see REGISTER_ID, REGISTER_IP, UPDT_DT, UPDTUSR_ID,  QESITM_SN
	 * @see TABLE NAME : TN_CSNST_MANAGE
     */
    @RequestMapping(value="/gps/adm/csnst/selectCsnstList.do")
	public String selectCsnstList (@ModelAttribute("csnstManageVO") CsnstManageVO csnstManageVO
			, ModelMap model
			, HttpServletRequest request
			) throws Exception {

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(csnstManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(csnstManageVO.getPageUnit());
		paginationInfo.setPageSize(csnstManageVO.getPageSize());
		
		csnstManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		csnstManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		csnstManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		// 만족도 조사 권한에 필요한 사용자ID설정
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
		csnstManageVO.setUserId(gpsSessionVO.getUsrId());

		paginationInfo.setTotalRecordCount(csnstManageService.selectCsnstListTotCnt(csnstManageVO));
        model.addAttribute("resultList", csnstManageService.selectCsnstList(csnstManageVO));
		
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("csnstManageVO", csnstManageVO);
        
        // System 유형
    	model.addAttribute("sysInfo", systemManageService.selectSysInfoList(gpsSessionVO.getUsrId()));
    	
        return "gps/adm/csnst/csnstList";
	}
    
    /**
     * 만족도조사 삭제
     * @param csnstManageVO CsnstManageVO
     * @param model ModelMap
     * @return "forward:/gps/adm/csnst/selectCsnstList.do" 만족도 조사 목록 URL
     * @throws Exception
	 * @see TABLE NAME : TN_CSNST_MANAGE
     */
    @RequestMapping(value="/gps/adm/csnst/deleteCsnst.do")
	public String deleteCsnst (@ModelAttribute("csnstManageVO")CsnstManageVO csnstManageVO
			, ModelMap model
			) throws Exception {
    	// 1. 만족도 조사 항목 삭제
    	csnstIemManageService.deleteCsnstIem(csnstManageVO);
    	// 2. 만족도 조사 문항 삭제
    	csnstQesitmManageService.deleteCsnstQesitm(csnstManageVO);
    	// 3. 만족도 조사 삭제
    	csnstManageService.deleteCsnst(csnstManageVO);
    	return "forward:/gps/adm/csnst/selectCsnstList.do";
	}

    /**
     * 만족도 조사 문항 삭제
     * @param csnstManageVO CsnstManageVO
     * @param model ModelMap
     * @return "forward:/gps/adm/csnst/modifyCsnst.do" 만족도 조사 수정 화면 URL
     * @throws Exception
	 * @see TABLE NAME : TN_CSNST_IEM_MANAGE, TN_CSNST_QESITM_MANAGE
     */
    @RequestMapping(value="/gps/adm/csnst/deleteCsnstQesitm.do")
	public String deleteCsnstQesitm (@ModelAttribute("csnstManageVO")CsnstManageVO csnstManageVO
			, ModelMap model
			) throws Exception {
    	// 1. 만족도 조사 항목 삭제
    	csnstIemManageService.deleteCsnstIem(csnstManageVO);
    	// 2. 만족도 조사 문항 삭제
    	csnstQesitmManageService.deleteCsnstQesitm(csnstManageVO);
    	// GET FORWARD
    	return "forward:/gps/adm/csnst/modifyCsnst.do?msg=20&csnstId=" + csnstManageVO.getCsnstId() + "&csnstSn=" + csnstManageVO.getCsnstSn();
	}

    /**
     * 만족도 조사 항목 삭제
     * @param csnstManageVO CsnstManageVO
     * @param model ModelMap
     * @return "forward:/gps/adm/csnst/modifyCsnst.do" 만족도 조사 수정 화면 URL
     * @throws Exception
	 * @see TABLE NAME : TN_CSNST_IEM_MANAGE
     */
    @RequestMapping(value="/gps/adm/csnst/deleteCsnstIem.do")
	public String deleteCsnstIem (@ModelAttribute("csnstManageVO")CsnstManageVO csnstManageVO
			, ModelMap model
			) throws Exception {
    	// 1. 만족도 조사 항목 삭제
    	csnstIemManageService.deleteCsnstIem(csnstManageVO);

    	// GET FORWARD
    	return "forward:/gps/adm/csnst/modifyCsnst.do?msg=30&csnstId=" + csnstManageVO.getCsnstId() + "&csnstSn=" + csnstManageVO.getCsnstSn();
	}

    /**
     * 만족도 등록화면
     * @param csnstManageVO CsnstManageVO
     * @param request HttpServletRequest
     * @param model ModelMap
     * @return "gps/adm/csnst/csnstRegist" 만족도 조사 등록 화면
     * @throws Exception
     * @see SYS_ID,SYS_NM
     * @see TABLE NAME : TN_SYSTEM
     */
    @RequestMapping(value="/gps/adm/csnst/registerCsnst.do")
	public String registerCsnst(@ModelAttribute("csnstManageVO")CsnstManageVO csnstManageVO
            , HttpServletRequest request
			, ModelMap model
    ) throws Exception {

    	// 등록자 기본정보 설정
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	
        // 세선 사용자의 SYSTEM 목록 취득
    	model.addAttribute("sysInfo", systemManageService.selectSysInfoList(gpsSessionVO.getUsrId()));
    	
    	// 만족도 조사 공개수준 목록을 코드테이블에서 취득한다. (공개수준:로그인후,암호입력후,전체)
    	ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
    	codeVO.setUpperCodeId("21101301");
    	model.addAttribute("csnstOthbcseList", naraCmmService.selectCmmCodeDetail(codeVO));
        return "gps/adm/csnst/csnstRegist";
    }

    /**
     * 만족도 조사 등록 처리
     * @param multiRequest MultipartHttpServletRequest
     * @param csnstManageVO CsnstManageVO
     * @param model ModelMap
     * @param request HttpServletRequest
     * @return "forward:/gps/adm/csnst/modifyCsnst.do" 만족도 조사 수정 화면 URL
     * @throws Exception
	 * @see TABLE NAME : TN_CSNST_MANAGE
     */
    @RequestMapping(value="/gps/adm/csnst/insertCsnst.do")
    public String insertCsnst (
    		final MultipartHttpServletRequest multiRequest,
    		@ModelAttribute("csnstManageVO") CsnstManageVO csnstManageVO,
    		ModelMap model,
            HttpServletRequest request
    ) throws Exception {
		// 등록자 기본정보 설정
    	csnstManageVO = (CsnstManageVO) defaultDataSet.registSet(request, csnstManageVO);
    	// 만족도 조사 일련번호 생성
    	csnstManageVO.setCsnstSn(String.valueOf(csnstManageService.csnstSnGeneration()));
    	// 만족도 조사 ID생성 (시스템구분 + 만족도 조사 일련번호)
    	csnstManageVO.setCsnstId(csnstManageVO.getSysId().concat(csnstManageVO.getCsnstSn()));

    	String _atchFileId = naraCmmService.insertAtchFile(multiRequest, multiRequest.getFile("csnstFile"),  "CSNST_",  "", "");
    	
    	if(!_atchFileId.isEmpty()){
	        csnstManageVO.setCsnstFileNm(_atchFileId);
    	}
		
    	csnstManageService.insertCsnst(csnstManageVO);
		
    	return "forward:/gps/adm/csnst/modifyCsnst.do?msg=1&csnstId=" + csnstManageVO.getCsnstId() + "&csnstSn=" + csnstManageVO.getCsnstSn();
    }

    /**
     * 만족도 문항 등록 처리
     * @param qesitmManageVO CsnstManageVO
     * @param model ModelMap
     * @param request HttpServletRequest
     * @return "forward:/gps/adm/csnst/modifyCsnst.do" 만족도 조사 수정 화면 URL
     * @throws Exception
	 * @see TABLE NAME : TN_CSNST_QESITM_MANAGE
     */
    @RequestMapping(value="/gps/adm/csnst/insertCsnstQesitm.do")
    public String insertCsnstQesitm (
    		@ModelAttribute("qesitmManageVO") CsnstManageVO qesitmManageVO,
    		ModelMap model,
            HttpServletRequest request
    ) throws Exception {
		// 등록자 기본정보 설정
    	qesitmManageVO = (CsnstManageVO) defaultDataSet.registSet(request, qesitmManageVO);
    	// 만족도 조사 문항 일련번호 생성
    	qesitmManageVO.setQesitmSn(String.valueOf(csnstQesitmManageService.csnstQesitmSnGeneration()));

    	csnstQesitmManageService.insertCsnstQesitm(qesitmManageVO);
    	
    	return "forward:/gps/adm/csnst/modifyCsnst.do?msg=2&csnstId=" + qesitmManageVO.getCsnstId() + "&csnstSn=" + qesitmManageVO.getCsnstSn();
    }

    /**
     * 만족도 항목 등록 처리
     * @param iemManageVO CsnstManageVO
     * @param model ModelMap
     * @param request HttpServletRequest
     * @return "forward:/gps/adm/csnst/modifyCsnst.do" 만족도 조사 수정 화면 URL
     * @throws Exception
	 * @see TABLE NAME : TN_CSNST_IEM_MANAGE
     */
    @RequestMapping(value="/gps/adm/csnst/insertCsnstIem.do")
    public String insertCsnstIem (
    		@ModelAttribute("iemManageVO") CsnstManageVO iemManageVO,
    		ModelMap model,
            HttpServletRequest request
    ) throws Exception {
		// 등록자 기본정보 설정
    	iemManageVO = (CsnstManageVO) defaultDataSet.registSet(request, iemManageVO);

    	StringTokenizer iemNm = new StringTokenizer(iemManageVO.getIemNm(), ",");
		while (iemNm.hasMoreTokens()) {
	    	// 만족도 조사 항목 일련번호 생성
			iemManageVO.setIemSn(String.valueOf(csnstIemManageService.csnstIemSnGeneration()));
			iemManageVO.setIemNm(iemNm.nextToken());
			csnstIemManageService.insertCsnstIem(iemManageVO);
		}

		// GET FORWARD
    	return "forward:/gps/adm/csnst/modifyCsnst.do?msg=3&csnstId=" + iemManageVO.getCsnstId() + "&csnstSn=" + iemManageVO.getCsnstSn();
    }

    /**
     * 만족도정보를 Select하여 게시물을 수정하는 화면으로 이동 
     * @param csnstManageVO CsnstManageVO
     * @param msg Integer
     * @param model ModelMap
     * @return "gps/adm/csnst/csnstUpdate" 만족도 조사 수정 화
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/csnst/modifyCsnst.do")
	public String modifyCsnst (@ModelAttribute("csnstManageVO") CsnstManageVO csnstManageVO
			, @RequestParam(value="msg", required=false) Integer msg
    		, ModelMap model
    ) throws Exception {
    	
    	// 1.만족도 조사 정보 취득
		CsnstManageVO resultVO = csnstManageService.selectCsnst(csnstManageVO);
		resultVO.setPageIndex(csnstManageVO.getPageIndex());
		resultVO.setPageUnit(csnstManageVO.getPageUnit());
		resultVO.setSearchCondition(csnstManageVO.getSearchCondition());
		resultVO.setSearchKeyword(csnstManageVO.getSearchKeyword());
		resultVO.setSearchSysId(csnstManageVO.getSearchSysId());
		
		// 2.조회할 만족도 조사 문항이 등록되어 있을 경우
		if (!resultVO.getQesitmSn().equals("0")) {
			csnstManageVO.setQesitmSn(resultVO.getQesitmSn());
			
			// 만족도 조사 문항 정보 설정
			CsnstManageVO csnstQesitm = new CsnstManageVO();
			csnstQesitm = csnstQesitmManageService.selectCsnstQesitm(csnstManageVO);
			resultVO.setQesitmQestnNm(csnstQesitm.getQesitmQestnNm());
			resultVO.setQesitmQestnCo(csnstQesitm.getQesitmQestnCo());
			resultVO.setQesitmTy(csnstQesitm.getQesitmTy());

			// 만족도 조사 항목 취득
			model.addAttribute("iemNmList", csnstIemManageService.selectCsnstIem(csnstManageVO));
		}
		// 3.만족도 조사 정보 VO JSP에서 FORM의VO로 사용
		model.addAttribute("csnstManageVO", resultVO);
		// 4.만족도 조사 문항 정보 VO JSP에서 FORM의VO로 사용
		model.addAttribute("qesitmManageVO", resultVO);
		// 5.만족도 조사 항목 정보 VO JSP에서 FORM의VO로 사용
		model.addAttribute("iemManageVO", resultVO);

    	// 만족도 조사 공개수준 목록을 코드테이블에서 취득한다. (공개수준:로그인후,암호입력후,전체)
    	ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
    	codeVO.setUpperCodeId("21101301");
    	model.addAttribute("csnstOthbcseList", naraCmmService.selectCmmCodeDetail(codeVO));
    	
    	// 6.msg 설정
    	if (null != msg) {
	    	if (msg == 1) {
	    	    model.addAttribute("message", egovMessageSource.getMessage("gps.csnstinsert.success"));
	    	} else if(msg == 2) {
	    	    model.addAttribute("message", egovMessageSource.getMessage("gps.csnstqesitminsert.success"));
	    	} else if (msg == 3){
	    	    model.addAttribute("message", egovMessageSource.getMessage("gps.csnstieminsert.success"));
	    	} else if(msg == 20) {
	    	    model.addAttribute("message", egovMessageSource.getMessage("gps.csnstqesitmdelete.success"));
	    	} else if (msg == 30){
	    	    model.addAttribute("message", egovMessageSource.getMessage("gps.csnstiemdelete.success"));
	    	}  else if (msg == 33){
	    	    model.addAttribute("message", egovMessageSource.getMessage("gps.csnstiemdeletedetail.success"));
	    	} 
    	}
        return "gps/adm/csnst/csnstUpdate";
    }

    /**
     * 만족도 수정 처리
     * @param csnstManageVO CsnstManageVO
     * @param multiRequest MultipartHttpServletRequest
     * @param request HttpServletRequest
     * @param model ModelMap
     * @return "forward:/gps/adm/csnst/modifyCsnst.do" 만족도 조사 수정 화면 URL
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/csnst/updateCsnst.do")
	public String updateCsnst (@ModelAttribute("csnstManageVO") CsnstManageVO csnstManageVO
    		, final MultipartHttpServletRequest multiRequest
			, HttpServletRequest request
    		, ModelMap model
    ) throws Exception {

		//수정자 정보
    	csnstManageVO = (CsnstManageVO) defaultDataSet.updateSet(request, csnstManageVO);
    	
    	// 기존파일ID 취득
    	String _oldFileId = csnstManageVO.getCsnstFileNm();

    	// 새로운 파일ID 취득(파일 업로드여부는 공통클래스 내부에서 판단함.)
    	String _atchFileId = naraCmmService.insertAtchFile(multiRequest, multiRequest.getFile("csnstFile"),  "CSNST_",  "", "");
    	
    	if(!_atchFileId.isEmpty() || csnstManageVO.isFileDeleteFlg()){
			// 업데이트가아니라 기존파일 삭제! 공통클래스에서 업데이트 처리가 좀 웃김... 그래서 새로등록후 삭제(기존파일 삭제처리도 안되어 있으므로..)
			// naraCmmService.updateAttachFile();
			FileVO fileVO = new FileVO();
			fileVO.setAtchFileId(_oldFileId);
    		fileService.deleteAllFileInf(fileVO);    			
    		
	        csnstManageVO.setCsnstFileNm(_atchFileId);
    	}

    	
	    model.addAttribute("message", egovMessageSource.getMessage("gps.csnstupdate.success"));
    	
		csnstManageService.updateCsnst(csnstManageVO);
        model.addAttribute("csnstManageVO", csnstManageVO);
		
        return "forward:/gps/adm/csnst/modifyCsnst.do";
		    	
    }

    /**
     * 만족도 조사 문항 수정 처리
     * @param csnstManageVO CsnstManageVO
     * @param request HttpServletRequest
     * @param model ModelMap
     * @return "forward:/gps/adm/csnst/modifyCsnst.do" 만족도 조사 수정 화면 URL
     * @throws Exception
	 * @see TABLE NAME : TN_CSNST_QESITM_MANAGE
     */
    @RequestMapping(value="/gps/adm/csnst/updateCsnstQesitm.do")
	public String updateQesitmCsnst (@ModelAttribute("qesitmManageVO") CsnstManageVO csnstManageVO
			, HttpServletRequest request
    		, ModelMap model
    ) throws Exception {
    	
		// 수정자 정보
    	csnstManageVO = (CsnstManageVO) defaultDataSet.updateSet(request, csnstManageVO);
    	
		csnstQesitmManageService.updateCsnstQesitm(csnstManageVO);

	    model.addAttribute("message", egovMessageSource.getMessage("gps.csnstqesitmupdate.success"));
	    
        return "forward:/gps/adm/csnst/modifyCsnst.do";
		    	
    }
    
    /**
     * 만족도 항목 수정 처리
     * @param csnstManageVO CsnstManageVO
     * @param request HttpServletRequest
     * @param model ModelMap
     * @return "forward:/gps/adm/csnst/modifyCsnst.do" 만족도 조사 수정 화면 URL
     * @throws Exception
	 * @see TABLE NAME : TN_CSNST_IEM_MANAGE
     */
    @RequestMapping(value="/gps/adm/csnst/updateCsnstIem.do")
	public String updateIemCsnst (@ModelAttribute("iemManageVO") CsnstManageVO csnstManageVO
			, HttpServletRequest request
    		, ModelMap model
    ) throws Exception {

		//수정자 정보
    	csnstManageVO = (CsnstManageVO) defaultDataSet.updateSet(request, csnstManageVO);
    	StringTokenizer iemSn = new StringTokenizer(csnstManageVO.getIemSn(), ",");
    	StringTokenizer iemNm = new StringTokenizer(csnstManageVO.getIemNm(), ",");
    	
		while (iemNm.hasMoreTokens()) {
			csnstManageVO.setIemSn(iemSn.nextToken());
			csnstManageVO.setIemNm(iemNm.nextToken());
			csnstIemManageService.updateCsnstIem(csnstManageVO);
		}

	    model.addAttribute("message", egovMessageSource.getMessage("gps.csnstiemupdate.success"));
	    
        return "forward:/gps/adm/csnst/modifyCsnst.do";
		    	
    }

    /**
     * 만족도 조사 미리보기
     * @param csnstManageVO CsnstManageVO
     * @param csnstRspnsVO CsnstRspnsVO
     * @param csnstMenoVO CsnstMenoVO
     * @param request HttpServletRequest
     * @param model ModelMap
     * @return "gps/adm/csnst/csnstReview" 만족도 조사 미리보기 화면
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/csnst/csnstReview.do")
	public String selectCsnstReview (@ModelAttribute("csnstManageVO") CsnstManageVO csnstManageVO
			, @ModelAttribute("csnstRspnsVO") CsnstRspnsVO csnstRspnsVO
			, @ModelAttribute("csnstMenoVO") CsnstMenoVO csnstMenoVO
			, HttpServletRequest request
    		, ModelMap model
    ) throws Exception {

    	CsnstManageVO reviewVO = csnstManageService.selectCsnstReview(csnstManageVO);
    	
	    model.addAttribute("reviewVO", reviewVO);
		model.addAttribute("iemNmList", csnstIemManageService.selectCsnstIem(csnstManageVO));
		
        return "gps/adm/csnst/csnstReview";
		    	
    }

    /**
     * 만족도 응답
     * @param csnstManageVO CsnstManageVO
     * @param csnstRspnsVO CsnstRspnsVO
     * @param csnstMenoVO CsnstMenoVO
     * @param request HttpServletRequest
     * @param model ModelMap
     * @return "gps/adm/csnst/csnstReview" 만족도 조사 결과 반영
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/csnst/insertCsnstRspns.do")
	public String insertCsnstRspns (@ModelAttribute("csnstManageVO") CsnstManageVO csnstManageVO
			, @ModelAttribute("csnstRspnsVO") CsnstRspnsVO csnstRspnsVO
			, @ModelAttribute("csnstMenoVO") CsnstMenoVO csnstMenoVO
			, HttpServletRequest request
    		, ModelMap model
    ) throws Exception {
    	
    	csnstRspnsVO = (CsnstRspnsVO) defaultDataSet.registSet(request, csnstRspnsVO);

    	// 만족도 조사 참여자 인지를 체크하기 위하여 searchCondition의 비교값 설정
    	if (!csnstRspnsVO.getRegisterId().isEmpty()) {
    		csnstRspnsVO.setSearchCondition("1");
    	} else {
    		csnstRspnsVO.setSearchCondition("2");
    	}
    	
    	// 1. 만족도 조사 중복 체크
    	if (csnstRspnsManageService.selectCsnstRspnsCheck(csnstRspnsVO) > 0){
    		// 2-1. 중복된 만족도 조사
    	    model.addAttribute("message", egovMessageSource.getMessage("gps.csnstrsnpns.multi"));
    	} else {
    		// 2-2. 만족도 조사 반영

    		// 주관식(체크박스)일경우 다중 저장
	    	StringTokenizer iemSn = new StringTokenizer(csnstRspnsVO.getIemSn(), ",");
	    	
			while (iemSn.hasMoreTokens()) {
		    	csnstRspnsVO.setRspnsSn(String.valueOf(csnstRspnsManageService.csnstRspnsSnGeneration()));
		    	csnstRspnsVO.setIemSn(iemSn.nextToken());
			    csnstRspnsManageService.insertCsnstRspns(csnstRspnsVO);
			}
			// 3. 메모입력 여부
			if (!csnstRspnsVO.getMemoCn().isEmpty()) {
				// 메모입력 값이 있을경우 메모 등록
				csnstMenoVO = (CsnstMenoVO) defaultDataSet.registSet(request, csnstMenoVO);
				csnstMenoVO.setMemoSn(String.valueOf(csnstMenoManageService.csnstMenoSnGeneration()));
				// 만족도조사 참여 화면에서 입력받은 메모값  설정
				csnstMenoVO.setMemoCn(csnstRspnsVO.getMemoCn());
				// 4. 메모 저장
				csnstMenoManageService.insertCsnstMeno(csnstMenoVO);
			}
			
		    model.addAttribute("message", egovMessageSource.getMessage("gps.csnstrsnpns.success"));
    	}
	    
    	CsnstManageVO reviewVO = csnstManageService.selectCsnstReview(csnstManageVO);
	    model.addAttribute("reviewVO", reviewVO);
		model.addAttribute("iemNmList", csnstIemManageService.selectCsnstIem(csnstManageVO));
	    
        return "gps/adm/csnst/csnstReview";
        
    }

    /**
     * 만족도 조사 응답 결과 보기
     * @param csnstManageVO CsnstManageVO
     * @param csnstMenoVO CsnstMenoVO
     * @param request HttpServletRequest
     * @param model ModelMap
     * @return "gps/adm/csnst/csnstRspns"
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/csnst/csnstRspnsResult.do")
	public String selectCsnstRspnsResult (@ModelAttribute("csnstManageVO") CsnstManageVO csnstManageVO
			, @ModelAttribute("csnstMenoVO") CsnstMenoVO csnstMenoVO
			, HttpServletRequest request
    		, ModelMap model
    ) throws Exception {
    	
    	CsnstManageVO rsnpnsResult = csnstManageService.selectCsnstReview(csnstManageVO);
    	
    	// 만족도 조사 질문 취득
	    model.addAttribute("rsnpnsResultVO", rsnpnsResult);
	    
	    // 만족도 조사 질문에 대한 결과 취득
		model.addAttribute("iemNmList", csnstIemManageService.selectCsnstIem(csnstManageVO));
		
		// 만족도 조사 메모 취득 조건값 설정;
		csnstMenoVO.setCsnstId(csnstMenoVO.getCsnstId());
		csnstMenoVO.setCsnstSn(csnstMenoVO.getCsnstSn());
		csnstMenoVO.setQesitmSn(csnstMenoVO.getQesitmSn());
		
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(csnstMenoVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(csnstMenoVO.getPageUnit());
		paginationInfo.setPageSize(csnstMenoVO.getPageSize());
		
		csnstMenoVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		csnstMenoVO.setLastIndex(paginationInfo.getLastRecordIndex());
		csnstMenoVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		// 만족도 조사 메모 취득
		model.addAttribute("memoList", csnstMenoManageService.selectCsnstMenoList(csnstMenoVO));
		
		paginationInfo.setTotalRecordCount(csnstMenoManageService.selectCsnstMenoListCnt(csnstMenoVO));
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("csnstMenoVO", csnstMenoVO);
        
		
        return "gps/adm/csnst/csnstRspns";
		    	
    }
    
    /**
     * 메모 다중 삭제
     * @param csnstMenoVO CsnstMenoVO
     * @param model ModelMap
     * @return "forward:/gps/adm/csnst/csnstRspnsResult.do" 만족도 조사 결과 화면 URL
     * @throws Exception
	 * @see INT
	 * @see TABLE NAME : TN_CSNST_MENO
     */
    @RequestMapping(value="/gps/adm/csnst/deleteCsnstMemoList.do")
	public String deleteCsnstMenoList (@ModelAttribute("csnstMenoVO") CsnstMenoVO csnstMenoVO
			, ModelMap model
			) throws Exception {
    	StringTokenizer memoSnList = new StringTokenizer(csnstMenoVO.getMemoSnList(), ",");
		while(memoSnList.hasMoreTokens() )
		{
			csnstMenoVO.setMemoSn(memoSnList.nextToken());
			csnstMenoManageService.deleteCsnstMeno(csnstMenoVO);
		}	    	

    	return "forward:/gps/adm/csnst/csnstRspnsResult.do";
	}

}