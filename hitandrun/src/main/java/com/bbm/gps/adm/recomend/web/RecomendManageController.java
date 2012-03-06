package com.bbm.gps.adm.recomend.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import com.bbm.common.cmm.service.EgovCmmUseService;
import com.bbm.common.cmm.service.EgovFileMngService;
import com.bbm.common.cmm.service.FileVO;
import com.bbm.common.cmm.service.NaraCmmService;
import com.bbm.common.util.cas.service.EgovSessionCookieUtil;
import com.bbm.gps.adm.recomend.service.RecomendManageService;
import com.bbm.gps.adm.recomend.service.RecomendManageVO;
import com.bbm.gps.adm.system.service.SystemManageService;
import com.bbm.gps.cmm.DefaultDataSet;
import com.bbm.gps.login.service.GpsSessionVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/** 
 * 추천사이트관리 비즈니스 구현 클래스 
 * <p><b>NOTE:</b> 추천사이트 목록, 상세정보를 조회 하며 수정,삭제 요청을 처리한다.
 * 프로그램관리 화면과 직접 연계되는 컨트롤러 클래스로 주로 JSP화면에서 호출되거나 
 * 해당 클래스 내부에서 호출되는 메소드들이 정의되어 있다
 * 해당 클래스 중에는 비즈니스 로직이 없고 단순히 다른 JSP화면으로 forword 시켜주는 메소드도 존재한다
 * DB select, DB insert, DB update, DB delete, 단순redirect 등의 기능을 하기위한 메소드들의 집합
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
 *   2011.10.07     이관형       최초 생성 
 * 
 * </pre> 
 */

@Controller
public class RecomendManageController {
	
	/** RecomendManageService 서비스 호출 */ 
	@Resource(name = "recomendManageService")
    private RecomendManageService recomendManageService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** SystemManageService 서비스 호출 */ 
	@Resource(name = "systemManageService")
    private SystemManageService systemManageService;

	/** defaultDataSet */
    @Resource(name="defaultDataSet")
    protected DefaultDataSet defaultDataSet;

	/** NaraCmmService 호출 */ 
	@Resource(name="NaraCmmService")
	private NaraCmmService naraCmmService;

	/** fileService 호출 */ 
    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;

	/** EgovCmmUseService 호출 */ 
	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** LOG */ 
	protected Log log = LogFactory.getLog(this.getClass());
	
    /**
     * 추천사이트목록 조회
     * @param recomendManageVO
     * @param request
     * @param model
     * @return "gps/adm/recomend/recomendList"
     * @throws Exception
     * @see IMAGE_ID,IMAGE_SN,IMAGE_SE,IMAGE_NM,IMAGE_FILE_NM,IMAGE_FILE_MASK,IMAGE_FILE_SIZE,IMAGE_FILE_MIME,IMAGE_WIDTH,
	 * @see IMAGE_HEIGHT,IMAGE_REFLCT_AT,IMAGE_REFLCT_URL,SYS_ID,SYS_NM,REGISTER_IP,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID
	 * @see TABLE NAME : TN_IMAGE,TN_SYSTEM
     */
    @RequestMapping(value="/gps/adm/recomend/selectRecomendList.do")
	public String selectRecomendList (@ModelAttribute("recomendManageVO") RecomendManageVO recomendManageVO
			, HttpServletRequest request
			, ModelMap model
			) throws Exception {

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(recomendManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(recomendManageVO.getPageUnit());
		paginationInfo.setPageSize(recomendManageVO.getPageSize());
		
		recomendManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		recomendManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		recomendManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		// 권한설정
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	recomendManageVO.setUserId(gpsSessionVO.getUsrId());
		
        model.addAttribute("resultList", recomendManageService.selectRecomendList(recomendManageVO));
        
		paginationInfo.setTotalRecordCount(recomendManageService.selectRecomendListTotCnt(recomendManageVO));
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("recomendManageVO", recomendManageVO);
        
        //model.addAttribute("searchCondition", egovCmmUseService.selectCmmCode("21107211",""));
        // System 유형
    	model.addAttribute("sysInfo", systemManageService.selectSysInfoList(gpsSessionVO.getUsrId()));
    	
        return "gps/adm/recomend/recomendList";
	}
    
    /**
     * 추천사이트 삭제
     * @param recomendManageVO
     * @param model
     * @return "forward:/gps/adm/recomend/selectRecomendList.do"
     * @throws Exception
     * @see TABLE NAME : TN_IMAGE
     */
    @RequestMapping(value="/gps/adm/recomend/deleteRecomendDetail.do")
	public String deleteRecomendDetail (@ModelAttribute("recomendManageVO")RecomendManageVO recomendManageVO
			, ModelMap model
			) throws Exception {
    	recomendManageService.deleteRecomend(recomendManageVO);
    	return "forward:/gps/adm/recomend/selectRecomendList.do";
	}

    /**
     * 추천사이트 등록화면
     * @param recomendManageVO
     * @param request
     * @param model
     * @return "gps/adm/recomend/recomendRegist"
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/recomend/registerRecomend.do")
	public String registerRecomend(@ModelAttribute("recomendManageVO")RecomendManageVO recomendManageVO
			, HttpServletRequest request
			,ModelMap model
    ) throws Exception {
        // System 유형
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	model.addAttribute("sysInfo", systemManageService.selectSysInfoList(gpsSessionVO.getUsrId()));
        model.addAttribute("recomendManageVO", recomendManageVO);
        model.addAttribute("recomendSeInfo", cmmUseService.selectCmmCodeMap("21101313",""));
        return "gps/adm/recomend/recomendRegist";
    }

    /**
     * 추천사이트 등록
     * @param recomendManageVO
     * @param model
     * @param request
     * @param multiRequest
     * @return "forward:/gps/adm/recomend/selectRecomendList.do"
     * @throws Exception
     * @see TABLE NAME : TN_IMAGE
     */
    @RequestMapping(value="/gps/adm/recomend/insertRecomend.do")
    public String insertRecomend (
    		@ModelAttribute("recomendManageVO") RecomendManageVO recomendManageVO,
    		ModelMap model,
            HttpServletRequest request
    		, final MultipartHttpServletRequest multiRequest
    ) throws Exception {
    	
    	recomendManageVO = (RecomendManageVO) defaultDataSet.registSet(request, recomendManageVO);
    	
    	recomendManageVO.setSiteSn(String.valueOf(recomendManageService.siteSnGeneration()));
    	recomendManageVO.setSiteId(recomendManageVO.getSysId().concat(recomendManageVO.getSiteSn()));
    	
    	MultipartFile file = multiRequest.getFile("recomendSiteFile");
    	String _atchFileId = naraCmmService.insertAtchFile(multiRequest, file,  "RECOMEND_",  "", propertiesService.getString("GPS.recomendPath"));
    	if( !"".equals( _atchFileId )) {
    		// 업로드 파일정보 취득후 추천사이트 VO에 설정
    		FileVO fv = new FileVO();
    		fv.setAtchFileId(_atchFileId);
    		fv.setFileSn("0");
    		fv = fileService.selectFileInf(fv);
    		recomendManageVO.setSiteImageNm(fv.getOrignlFileNm());
    		recomendManageVO.setSiteMageMask(fv.getStreFileNm());
    		recomendManageVO.setSiteImageMime(fv.getFileExtsn());
    		recomendManageVO.setSiteImageSize(fv.getFileMg());
    	}
		
    	recomendManageService.insertRecomend(recomendManageVO);
        model.addAttribute("recomendManageVO", recomendManageVO);
    	return "forward:/gps/adm/recomend/selectRecomendList.do";
    }

    /**
     * 추천사이트수정화면호출
     * @param recomendManageVO
     * @param request
     * @param model
     * @return "gps/adm/recomend/recomendUpdate"
     * @throws Exception
     * @see IMAGE_ID,IMAGE_SN,IMAGE_SE,IMAGE_NM,IMAGE_FILE_NM,IMAGE_FILE_MASK,IMAGE_FILE_SIZE,IMAGE_FILE_MIME,IMAGE_WIDTH,
	 * @see IMAGE_HEIGHT,IMAGE_REFLCT_AT,IMAGE_REFLCT_URL,SYS_ID,SYS_NM,REGISTER_IP,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID
	 * @see TABLE NAME : TN_IMAGE,TN_SYSTEM
     */
    @RequestMapping(value="/gps/adm/recomend/modifyRecomend.do")
	public String modifyRecomend (@ModelAttribute("recomendManageVO") RecomendManageVO recomendManageVO
			, HttpServletRequest request
    		, ModelMap model
    ) throws Exception {
    	
		RecomendManageVO resultVO = recomendManageService.selectRecomend(recomendManageVO);
		resultVO.setPageIndex(recomendManageVO.getPageIndex());
		resultVO.setPageUnit(recomendManageVO.getPageUnit());
		resultVO.setSearchCondition(recomendManageVO.getSearchCondition());
		resultVO.setSearchKeyword(recomendManageVO.getSearchKeyword());
		resultVO.setSearchSysId(recomendManageVO.getSearchSysId());
		
		model.addAttribute("recomendManageVO", resultVO);
        // System 유형
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	model.addAttribute("sysInfo", systemManageService.selectSysInfoList(gpsSessionVO.getUsrId()));
    	model.addAttribute("WebRecomendPath", this.propertiesService.getString("WebRecomendPath"));
    	
        return "gps/adm/recomend/recomendUpdate";
    }

    /**
     * 추천사이트 수정
     * @param recomendManageVO
     * @param request
     * @param model
     * @param multiRequest
     * @return "forward:/gps/adm/recomend/selectRecomendList.do"
     * @throws Exception
     * @see TABLE NAME : TN_IMAGE
     */
    @RequestMapping(value="/gps/adm/recomend/updateRecomend.do")
	public String updateRecomend (@ModelAttribute("recomendManageVO") RecomendManageVO recomendManageVO
			, HttpServletRequest request
    		, ModelMap model
    		, final MultipartHttpServletRequest multiRequest
    ) throws Exception {

		//수정자자 정보
    	recomendManageVO = (RecomendManageVO) defaultDataSet.updateSet(request, recomendManageVO);

    	if (multiRequest.getFile("recomendSiteFile").getSize() > 0) {
    		FileVO fileVO = new FileVO();
    		fileVO.setAtchFileId(recomendManageVO.getSiteImageNm());
    		
    		fileService.deleteAllFileInf(fileVO);
    		
    		// 새로운 파일 등록
        	MultipartFile file = multiRequest.getFile("recomendSiteFile");

        	String _atchFileId = naraCmmService.insertAtchFile(multiRequest, file,  "RECOMEND_",  "", propertiesService.getString("GPS.recomendPath"));
        	
        	if( !"".equals(_atchFileId )) {
        		// 업로드 파일정보 취득후 추천사이트 VO에 설정
        		FileVO fv = new FileVO();
        		fv.setAtchFileId(_atchFileId);
        		fv.setFileSn("0");
        		fv = fileService.selectFileInf(fv);
        		recomendManageVO.setSiteImageNm(fv.getOrignlFileNm());
        		recomendManageVO.setSiteMageMask(fv.getStreFileNm());
        		recomendManageVO.setSiteImageMime(fv.getFileExtsn());
        		recomendManageVO.setSiteImageSize(fv.getFileMg());
        	}
        	
    	}

		recomendManageService.updateRecomend(recomendManageVO);
        model.addAttribute("recomendManageVO", recomendManageVO);
		
        return "forward:/gps/adm/recomend/selectRecomendList.do";
		    	
    }
    
}