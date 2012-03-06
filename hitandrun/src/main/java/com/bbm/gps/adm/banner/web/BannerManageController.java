package com.bbm.gps.adm.banner.web;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import com.bbm.common.cmm.service.EgovCmmUseService;
import com.bbm.common.cmm.service.EgovFileMngService;
import com.bbm.common.cmm.service.FileVO;
import com.bbm.common.cmm.service.NaraCmmService;
import com.bbm.common.util.cas.service.EgovSessionCookieUtil;
import com.bbm.gps.adm.author.intergrated.service.IgrAuthorManageVO;
import com.bbm.gps.adm.banner.service.BannerManageService;
import com.bbm.gps.adm.banner.service.BannerManageVO;
import com.bbm.gps.adm.system.service.SystemManageService;
import com.bbm.gps.cmm.DefaultDataSet;
import com.bbm.gps.login.service.GpsSessionVO;

import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/** 
 * 배너관리 비즈니스 구현 클래스
 * <p><b>NOTE:</b> 배너관리 화면과 직접 연계되는 컨트롤러 클래스로 주로 JSP화면에서 호출되거나 
 * 해당 클래스 내부에서 호출되는 메소드들이 정의되어 있다
 * 해당 클래스 중에는 비즈니스 로직이 없고 단순히 다른 JSP화면으로 forword 시켜주는 메소드도 존재한다
 * 배너정보 조회,입력,수정,삭제 요청을 처리한다
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
 *   2011.06.27     이관형       최초 생성 
 * 
 * </pre> 
 */

@Controller
public class BannerManageController {
	
	/** BannerManageService 서비스 호출 */ 
	@Resource(name = "bannerManageService")
    private BannerManageService bannerManageService;
	
	/** multipartResolver 호출 */ 
	@Resource(name = "multipartResolver")
	CommonsMultipartResolver multipartResolver;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
	/** EgovCmmUseService 호출 */ 
	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

    /** EgovIdGnrService */
    @Resource(name = "bannerSnIdGnrService")
    private EgovIdGnrService bannerSnIdGnrService;

	/** NaraCmmService 호출 */ 
	@Resource(name="NaraCmmService")
	private NaraCmmService naraCmmService;

	/** SystemManageService 서비스 호출 */ 
	@Resource(name = "systemManageService")
    private SystemManageService systemManageService;

	/** propertiesService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
	
	/** fileService 호출 */ 
    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;

	/** defaultDataSet */
    @Resource(name="defaultDataSet")
    protected DefaultDataSet defaultDataSet;


	/** LOG */ 
	protected Log log = LogFactory.getLog(this.getClass());
	
    /**
     * bannerManageVO 배너목록 조회  
     * @param bannerManageVO
     * @param request
     * @param model
     * @return "gps/adm/banner/bannerList"
     * @throws Exception
     * @see BANNER_SN,SYS_ID,SYS_NM,LC,ORDR,NM,ACTVTY_AT,ENDDE,LOGO_IMAGE_FILE_NM,
	 * @see LOGO_IMAGE_FILE_MASK,LOGO_IMAGE_FILE_SIZE,LOGO_IMAGE_FILE_MIME,LOGO_IMAGE_WIDTH,
	 * @see LOGO_IMAGE_HEIGHT,OVER_IMAGE_FILE_NM,OVER_IMAGE_FILE_MASK,OVER_IMAGE_FILE_SIZE,
	 * @see OVER_IMAGE_WIDTH,OVER_IMAGE_HEIGHT,OVER_IMAGE_FILE_MIME,IMAGE_ALT,URL,URL_TRGET,DC,
	 * @see TRGET,REGISTER_ID,REGISTER_IP,REGIST_DT,UPDT_DT,UPDTUSR_ID
	 * @see TABLE NAME : TN_BANNER,TN_SYSTEM
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/gps/adm/banner/selectBannerList.do")
	public String selectBannerList (@ModelAttribute("bannerManageVO") BannerManageVO bannerManageVO
			, HttpServletRequest request
			, ModelMap model
			) throws Exception {

    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	
    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(bannerManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(bannerManageVO.getPageUnit());
		paginationInfo.setPageSize(bannerManageVO.getPageSize());
		
		bannerManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		bannerManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		bannerManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		// 권한설정
		bannerManageVO.setUserId(gpsSessionVO.getUsrId());
        List bannerList = bannerManageService.selectBannerList(bannerManageVO);
        model.addAttribute("resultList", bannerList);
        
        int totCnt = bannerManageService.selectBannerListTotCnt(bannerManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("bannerManageVO", bannerManageVO);
        
        //시스템코드
    	IgrAuthorManageVO IgrAuthorManageVO=new IgrAuthorManageVO();
        IgrAuthorManageVO.setUserId(gpsSessionVO.getUsrId());
    	model.addAttribute("sysInfo", systemManageService.selectSysInfoList(gpsSessionVO.getUsrId()));

    	model.addAttribute("WebImagePath", this.propertyService.getString("WebImagePath"));
        model.addAttribute("searchCondition", cmmUseService.selectCmmCodeMap("COM036",""));
        
        return "gps/adm/banner/bannerList";
	}

    /**
     * 배너 다중 삭제 (배너테이블로부터 선택된 배너다중삭제)
     * @param bannerManageVO
     * @param model
     * @return "forward:/gps/adm/banner/selectBannerList.do"
     * @throws Exception
     * @see TABLE NAME : TN_BANNER
     */
    @RequestMapping(value="/gps/adm/progaram/deleteBannerList.do")
	public String deleteBannerList (@ModelAttribute("bannerManageVO")BannerManageVO bannerManageVO
			, ModelMap model
			) throws Exception {
    	
    	StringTokenizer bannerSnList = new StringTokenizer(bannerManageVO.getBannerSnList(), ",");
		while(bannerSnList.hasMoreTokens() )
		{
			String tmp = (String)bannerSnList.nextToken();
			StringTokenizer bannerSntm = new StringTokenizer(tmp, "^");
			
			bannerManageVO.setSysId((String)bannerSntm.nextToken());
			bannerManageVO.setBannerSn((String)bannerSntm.nextToken());
			bannerManageService.deleteBanner(bannerManageVO);
		}	    	

    	return "forward:/gps/adm/banner/selectBannerList.do";
	}
    
    /**
     * @param bannerManageVO
     * @param model
     * @return "forward:/gps/adm/banner/selectBannerList.do"
     * @throws Exception
     * @see TABLE NAME : TN_BANNER
     */
    @RequestMapping(value="/gps/adm/banner/deleteBanner.do")
	public String deleteBanner (@ModelAttribute("bannerManageVO")BannerManageVO bannerManageVO
			, ModelMap model) throws Exception {

    	bannerManageService.deleteBanner(bannerManageVO);
    	return "forward:/gps/adm/banner/selectBannerList.do";
	}

    /**
     * 배너등록화면 호출
     * @param bannerManageVO
     * @param request
     * @param model
     * @return "gps/adm/banner/bannerRegist"
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/banner/registerBanner.do")
	public String registerBanner(@ModelAttribute("bannerManageVO") BannerManageVO bannerManageVO
								, HttpServletRequest request
								,ModelMap model) throws Exception {
    	
    	//시스템코드
    	IgrAuthorManageVO IgrAuthorManageVO=new IgrAuthorManageVO();
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
        IgrAuthorManageVO.setUserId(gpsSessionVO.getUsrId());
    	model.addAttribute("sysInfo", systemManageService.selectSysInfoList(gpsSessionVO.getUsrId()));
        
        model.addAttribute("lcList", cmmUseService.selectCmmCodeMap("LC01",""));	//위치
        model.addAttribute("targetList", cmmUseService.selectCmmCodeMap("TG01",""));	//타겟
        return "gps/adm/banner/bannerRegist";
    }

    /**
     * 배너등록 처리
     * @param multiRequest
     * @param bannerManageVO
     * @param bindingResult
     * @param model
     * @return
     * @throws Exception
     * @see TABLE NAME : TN_BANNER
     */
    @RequestMapping(value="/gps/adm/banner/insertBanner.do")
    public String insertBanner (HttpServletRequest request,
    		final MultipartHttpServletRequest multiRequest,
    		@ModelAttribute("bannerManageVO") BannerManageVO bannerManageVO,
    		BindingResult bindingResult, 
    		ModelMap model
    ) throws Exception {
    	
    	MultipartFile file = multiRequest.getFile("logoImageFile");
    	String _atchFileId = naraCmmService.insertAtchFile(multiRequest, file,  "BANNER_",  "", propertiesService.getString("GPS.bannerPath"));
    	
    	if( !"".equals( _atchFileId )) {
    		// 업로드 파일정보 취득후 배너 VO에 설정
    		FileVO fv = new FileVO();
    		fv.setAtchFileId(_atchFileId);
    		fv.setFileSn("0");
    		fv = fileService.selectFileInf(fv);
    		bannerManageVO.setLogoImageFileMask(fv.getStreFileNm());
    		bannerManageVO.setLogoImageFileNm(fv.getOrignlFileNm());
    		bannerManageVO.setLogoImageFileMime(fv.getFileExtsn());
    		bannerManageVO.setLogoImageFileSize(fv.getFileMg());
    	}
    	
    	bannerManageVO.setBannerSn(bannerSnIdGnrService.getNextStringId());
    	
    	
    	//등록자 , 등록IP
    	bannerManageVO = (BannerManageVO) defaultDataSet.registSet(request, bannerManageVO);
    	
    	bannerManageService.insertBanner(bannerManageVO);
    	
    	return "forward:/gps/adm/banner/selectBannerList.do";
    }
    
    /**
     * 배너목록 수정화면 호출
     * @param bannerManageVO
     * @param request
     * @param model
     * @return "gps/adm/banner/bannerUpdate"
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/banner/modifyBanner.do")
	public String modifyBanner (@ModelAttribute("bannerManageVO") BannerManageVO bannerManageVO
			, HttpServletRequest request
    		, ModelMap model
    ) throws Exception {
    	BannerManageVO resultVO = bannerManageService.selectBanner(bannerManageVO);
		model.addAttribute("bannerManageVO", resultVO);

		resultVO.setPageIndex(bannerManageVO.getPageIndex());
		resultVO.setPageUnit(bannerManageVO.getPageUnit());
		resultVO.setSearchCondition(bannerManageVO.getSearchCondition());
		resultVO.setSearchKeyword(bannerManageVO.getSearchKeyword());
		resultVO.setSearchSysId(bannerManageVO.getSearchSysId());
		resultVO.setActvtyAtSearch(bannerManageVO.getActvtyAtSearch());
		
        //시스템코드
        IgrAuthorManageVO IgrAuthorManageVO=new IgrAuthorManageVO();
        GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
        IgrAuthorManageVO.setUserId(gpsSessionVO.getUsrId());
    	model.addAttribute("sysInfo", systemManageService.selectSysInfoList(gpsSessionVO.getUsrId()));

    	model.addAttribute("WebImagePath", this.propertyService.getString("WebImagePath"));
        
        return "gps/adm/banner/bannerUpdate";
    }

    /**
     * 배너목록 수정 처리
     * @param multiRequest
     * @param bannerManageVO
     * @param bindingResult
     * @param model
     * @return "forward:/gps/adm/banner/selectBannerList.do"
     * @throws Exception
     * @see TABLE NAME : TN_BANNER
     */
    @RequestMapping(value="/gps/adm/banner/updateBanner.do")
	public String updateBanner (HttpServletRequest request,
			final MultipartHttpServletRequest multiRequest,
    		@ModelAttribute("bannerManageVO") BannerManageVO bannerManageVO,
    		BindingResult bindingResult, 
    		ModelMap model
    ) throws Exception {

    	if (bannerManageVO.isBannerImgDelFlg()) {
    		FileVO fileVO = new FileVO();
    		fileVO.setAtchFileId(bannerManageVO.getLogoImageFileNm());
    		
    		fileService.deleteAllFileInf(fileVO);
    		
    		// 새로운 파일 등록
        	MultipartFile file = multiRequest.getFile("logoImageFile");

        	String _atchFileId = naraCmmService.insertAtchFile(multiRequest, file,  "BANNER_",  "", propertiesService.getString("GPS.bannerPath"));
        	
        	if( !"".equals( _atchFileId )) {
        		// 업로드 파일정보 취득후 배너 VO에 설정
        		FileVO fv = new FileVO();
        		fv.setAtchFileId(_atchFileId);
        		fv.setFileSn("0");
        		fv = fileService.selectFileInf(fv);
        		bannerManageVO.setLogoImageFileMask(fv.getStreFileNm());
        		bannerManageVO.setLogoImageFileNm(fv.getOrignlFileNm());
        		bannerManageVO.setLogoImageFileMime(fv.getFileExtsn());
        		bannerManageVO.setLogoImageFileSize(fv.getFileMg());
        	}
        	
    	}
    	
    	//수정자 
    	bannerManageVO = (BannerManageVO) defaultDataSet.updateSet(request, bannerManageVO);
		bannerManageService.updateBanner(bannerManageVO);

        return "forward:/gps/adm/banner/selectBannerList.do";
		    	
    }

    /**
     * 배너순서변경화면호출
     * @param bannerManageVO
     * @param request
     * @param model
     * @return "gps/adm/banner/bannerOrderBy"
     * @throws Exception
     * @see TABLE NAME : TN_BANNER
     */
    @RequestMapping(value="/gps/adm/banner/bannerOrderBy.do")
	public String bannerOrderBy (@ModelAttribute("bannerManageVO") BannerManageVO bannerManageVO
			, HttpServletRequest request
    		, ModelMap model
    ) throws Exception {
    	
    	//시스템코드
        IgrAuthorManageVO IgrAuthorManageVO=new IgrAuthorManageVO();
        GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
        IgrAuthorManageVO.setUserId(gpsSessionVO.getUsrId());
    	model.addAttribute("sysInfo", systemManageService.selectSysInfoList(gpsSessionVO.getUsrId()));
        
    	model.addAttribute("bannerMap",bannerManageService.selectBannerOrderList(bannerManageVO));
    	
        return "gps/adm/banner/bannerOrderBy";
    }

    /**
     * 배너순서변경처리
     * @param bannerManageVO
     * @param model
     * @return
     * @throws Exception
     * @see TABLE NAME : TN_BANNER
     */
    @RequestMapping(value="/gps/adm/banner/updateBannerOrderBy.do")
	public String updateBannerOrderBy (@ModelAttribute("bannerManageVO") BannerManageVO bannerManageVO
    		, ModelMap model
    ) throws Exception {
    	
    	
		StringTokenizer bannerSntm = new StringTokenizer(bannerManageVO.getBannerSn(), "^");
		
		bannerManageVO.setSysId((String)bannerSntm.nextToken());
		bannerManageVO.setBannerSn((String)bannerSntm.nextToken());
		
		bannerManageService.updateBannerOrder(bannerManageVO);
    	
        return "gps/adm/banner/bnnerOrderBy";
    }

    /**
     * 배너사용여부변경
     * @param bannerManageVO
     * @param model
     * @return "forward:gps/adm/banner/selectBannerList.do"
     * @throws Exception
     * @see TABLE NAME : TN_BANNER
     */
    @RequestMapping(value="/gps/adm/banner/updateBannerUseAt.do")
	public String updateBannerActvtyAt (@ModelAttribute("bannerManageVO") BannerManageVO bannerManageVO
    		, ModelMap model
    ) throws Exception {
    	
        return "forward:gps/adm/banner/selectBannerList.do";    	
    }
}