package com.bbm.gps.adm.image.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import com.bbm.common.cmm.service.EgovCmmUseService;
import com.bbm.common.cmm.service.EgovFileMngService;
import com.bbm.common.cmm.service.FileVO;
import com.bbm.common.cmm.service.NaraCmmService;
import com.bbm.common.util.cas.service.EgovSessionCookieUtil;
import com.bbm.gps.adm.image.service.ImageManageService;
import com.bbm.gps.adm.image.service.ImageManageVO;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/** 
 * 이미지관리 비즈니스 구현 클래스 
 * <p><b>NOTE:</b> 이미지 목록, 상세정보를 조회 하며 수정,삭제 요청을 처리한다.
 * 프로그램관리 화면과 직접 연계되는 컨트롤러 클래스로 주로 JSP화면에서 호출되거나 
 * 해당 클래스 내부에서 호출되는 메소드들이 정의되어 있다
 * 해당 클래스 중에는 비즈니스 로직이 없고 단순히 다른 JSP화면으로 forword 시켜주는 메소드도 존재한다
 * DB select, DB insert, DB update, DB delete, 단순redirect 등의 기능을 하기위한 메소드들의 집합
 * @author 범정부통계포털 이관형 
 * @since 2011.10.10 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.10.10     이관형       최초 생성 
 * 
 * </pre> 
 */

@Controller
public class ImageManageController {
	
	/** ImageManageService 서비스 호출 */ 
	@Resource(name = "imageManageService")
    private ImageManageService imageManageService;
	
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
     * 이미지목록 조회
     * @param imageManageVO
     * @param request
     * @param model
     * @return "gps/adm/image/imageList"
     * @throws Exception
     * @see IMAGE_ID,IMAGE_SN,IMAGE_SE,IMAGE_NM,IMAGE_FILE_NM,IMAGE_FILE_MASK,IMAGE_FILE_SIZE,IMAGE_FILE_MIME,IMAGE_WIDTH,
	 * @see IMAGE_HEIGHT,IMAGE_REFLCT_AT,IMAGE_REFLCT_URL,SYS_ID,SYS_NM,REGISTER_IP,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID
	 * @see TABLE NAME : TN_IMAGE,TN_SYSTEM
     */
    @RequestMapping(value="/gps/adm/image/selectImageList.do")
	public String selectImageList (@ModelAttribute("imageManageVO") ImageManageVO imageManageVO
			, HttpServletRequest request
			, ModelMap model
			) throws Exception {

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(imageManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(imageManageVO.getPageUnit());
		paginationInfo.setPageSize(imageManageVO.getPageSize());
		
		imageManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		imageManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		imageManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		// 권한설정
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	imageManageVO.setUserId(gpsSessionVO.getUsrId());
		
        model.addAttribute("resultList", imageManageService.selectImageList(imageManageVO));
        
		paginationInfo.setTotalRecordCount(imageManageService.selectImageListTotCnt(imageManageVO));
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("imageManageVO", imageManageVO);
        
        //model.addAttribute("searchCondition", egovCmmUseService.selectCmmCode("21107211",""));
        // System 유형
    	model.addAttribute("sysInfo", systemManageService.selectSysInfoList(gpsSessionVO.getUsrId()));
    	
        return "gps/adm/image/imageList";
	}
    
    /**
     * 이미지 다중 삭제
     * @param imageManageVO
     * @param model
     * @return "forward:/gps/adm/image/selectImageList.do"
     * @throws Exception
     * @see TABLE NAME : TN_IMAGE
     */
    @RequestMapping(value="/gps/adm/progaram/deleteImageList.do")
	public String deleteImageList (@ModelAttribute("imageManageVO")ImageManageVO imageManageVO
			, ModelMap model
			) throws Exception {
    	StringTokenizer imageIdList = new StringTokenizer(imageManageVO.getImageIdList(), ",");
		while(imageIdList.hasMoreTokens() )
		{
			imageManageVO.setImageId(imageIdList.nextToken());
			imageManageService.deleteImage(imageManageVO);
		}	    	

    	return "forward:/gps/adm/image/selectImageList.do";
	}

    /**
     * 이미지 삭제
     * @param imageManageVO
     * @param model
     * @return "forward:/gps/adm/image/selectImageList.do"
     * @throws Exception
     * @see TABLE NAME : TN_IMAGE
     */
    @RequestMapping(value="/gps/adm/image/deleteImageDetail.do")
	public String deleteImageDetail (@ModelAttribute("imageManageVO")ImageManageVO imageManageVO
			, ModelMap model
			) throws Exception {
    	imageManageService.deleteImage(imageManageVO);
    	return "forward:/gps/adm/image/selectImageList.do";
	}

    /**
     * 이미지 등록화면
     * @param imageManageVO
     * @param request
     * @param model
     * @return "gps/adm/image/imageRegist"
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/image/registerImage.do")
	public String registerImage(@ModelAttribute("imageManageVO")ImageManageVO imageManageVO
			, HttpServletRequest request
			,ModelMap model
    ) throws Exception {
        // System 유형
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	model.addAttribute("sysInfo", systemManageService.selectSysInfoList(gpsSessionVO.getUsrId()));
        model.addAttribute("imageManageVO", imageManageVO);
        model.addAttribute("imageSeInfo", cmmUseService.selectCmmCodeMap("21101313",""));
        return "gps/adm/image/imageRegist";
    }

    /**
     * 이미지 등록
     * @param imageManageVO
     * @param model
     * @param request
     * @param multiRequest
     * @return "forward:/gps/adm/image/selectImageList.do"
     * @throws Exception
     * @see TABLE NAME : TN_IMAGE
     */
    @RequestMapping(value="/gps/adm/image/insertImage.do")
    public String insertImage (
    		@ModelAttribute("imageManageVO") ImageManageVO imageManageVO,
    		ModelMap model,
            HttpServletRequest request
    		, final MultipartHttpServletRequest multiRequest
    ) throws Exception {
    	
    	imageManageVO.setImageSn(imageManageService.imageSnGeneration());
    	if (imageManageVO.getImageSe().equals("001")) {
    		imageManageVO.setImageId(imageManageVO.getSysId());
    	} else {
    		imageManageVO.setImageId(imageManageVO.getSysId().concat(String.valueOf(imageManageVO.getImageSn())));
    	}

    	imageManageVO = (ImageManageVO) defaultDataSet.registSet(request, imageManageVO);

    	MultipartFile file = multiRequest.getFile("imageFile");
    	String _atchFileId = naraCmmService.insertAtchFile(multiRequest, file,  "IMAGE_",  "", propertiesService.getString("GPS.imagePath"));
    	if( !"".equals( _atchFileId )) {
    		// 업로드 파일정보 취득후 이미지 VO에 설정
    		FileVO fv = new FileVO();
    		fv.setAtchFileId(_atchFileId);
    		fv.setFileSn("0");
    		fv = fileService.selectFileInf(fv);
    		imageManageVO.setImageFileNm(fv.getOrignlFileNm());
    		imageManageVO.setImageFileMask(fv.getStreFileNm());
    		imageManageVO.setImageFileMime(fv.getFileExtsn());
    		imageManageVO.setImageFileSize(fv.getFileMg());
    	}
		
    	imageManageService.insertImage(imageManageVO);
        model.addAttribute("imageManageVO", imageManageVO);
    	return "forward:/gps/adm/image/selectImageList.do";
    }

    /**
     * 이미지수정화면호출
     * @param imageManageVO
     * @param request
     * @param model
     * @return "gps/adm/image/imageUpdate"
     * @throws Exception
     * @see IMAGE_ID,IMAGE_SN,IMAGE_SE,IMAGE_NM,IMAGE_FILE_NM,IMAGE_FILE_MASK,IMAGE_FILE_SIZE,IMAGE_FILE_MIME,IMAGE_WIDTH,
	 * @see IMAGE_HEIGHT,IMAGE_REFLCT_AT,IMAGE_REFLCT_URL,SYS_ID,SYS_NM,REGISTER_IP,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID
	 * @see TABLE NAME : TN_IMAGE,TN_SYSTEM
     */
    @RequestMapping(value="/gps/adm/image/modifyImage.do")
	public String modifyImage (@ModelAttribute("imageManageVO") ImageManageVO imageManageVO
			, HttpServletRequest request
    		, ModelMap model
    ) throws Exception {
    	
		ImageManageVO resultVO = imageManageService.selectImage(imageManageVO);
		resultVO.setPageIndex(imageManageVO.getPageIndex());
		resultVO.setPageUnit(imageManageVO.getPageUnit());
		resultVO.setSearchCondition(imageManageVO.getSearchCondition());
		resultVO.setSearchKeyword(imageManageVO.getSearchKeyword());
		resultVO.setSearchSysId(imageManageVO.getSearchSysId());
		
		model.addAttribute("imageManageVO", resultVO);
        // System 유형
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	model.addAttribute("sysInfo", systemManageService.selectSysInfoList(gpsSessionVO.getUsrId()));
        model.addAttribute("imageSeInfo", cmmUseService.selectCmmCodeMap("21101313",""));
    	model.addAttribute("WebImagePath", this.propertiesService.getString("WebImagePath"));
    	
        return "gps/adm/image/imageUpdate";
    }

    /**
     * 이미지 수정
     * @param imageManageVO
     * @param request
     * @param model
     * @param multiRequest
     * @return "forward:/gps/adm/image/selectImageList.do"
     * @throws Exception
     * @see TABLE NAME : TN_IMAGE
     */
    @RequestMapping(value="/gps/adm/image/updateImage.do")
	public String updateImage (@ModelAttribute("imageManageVO") ImageManageVO imageManageVO
			, HttpServletRequest request
    		, ModelMap model
    		, final MultipartHttpServletRequest multiRequest
    ) throws Exception {

		//수정자자 정보
    	imageManageVO = (ImageManageVO) defaultDataSet.updateSet(request, imageManageVO);

    	if (multiRequest.getFile("imageFile").getSize() > 0) {
    		FileVO fileVO = new FileVO();
    		fileVO.setAtchFileId(imageManageVO.getImageFileNm());
    		
    		fileService.deleteAllFileInf(fileVO);
    		
    		// 새로운 파일 등록
        	MultipartFile file = multiRequest.getFile("imageFile");

        	String _atchFileId = naraCmmService.insertAtchFile(multiRequest, file,  "IMAGE_",  "", propertiesService.getString("GPS.imagePath"));
        	
        	if( !"".equals(_atchFileId )) {
        		// 업로드 파일정보 취득후 이미지 VO에 설정
        		FileVO fv = new FileVO();
        		fv.setAtchFileId(_atchFileId);
        		fv.setFileSn("0");
        		fv = fileService.selectFileInf(fv);
        		imageManageVO.setImageFileNm(fv.getOrignlFileNm());
        		imageManageVO.setImageFileMask(fv.getStreFileNm());
        		imageManageVO.setImageFileMime(fv.getFileExtsn());
        		imageManageVO.setImageFileSize(fv.getFileMg());
        	}
        	
    	}

		imageManageService.updateImage(imageManageVO);
        model.addAttribute("imageManageVO", imageManageVO);
		
        return "forward:/gps/adm/image/selectImageList.do";
		    	
    }
    
}