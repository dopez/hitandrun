package com.bbm.gps.adm.popup.web;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import com.bbm.common.cmm.service.EgovCmmUseService;
import com.bbm.common.cmm.service.FileVO;
import com.bbm.common.login.service.SessionVO;
import com.bbm.common.util.cas.service.EgovSessionCookieUtil;
import com.bbm.common.util.fcc.service.EgovStringUtil;
import com.bbm.gps.adm.author.intergrated.service.IgrAuthorManageService;
import com.bbm.gps.adm.author.intergrated.service.IgrAuthorManageVO;
import com.bbm.gps.adm.popup.service.PopupManageService;
import com.bbm.gps.adm.popup.service.PopupManageVO;
import com.bbm.gps.cmm.DefaultDataSet;
import com.bbm.gps.cmm.service.GpsCmmService;
import com.bbm.gps.login.service.GpsSessionVO;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 팝업관리 비즈니스 구현 클래스
 * <p>
 * <b>NOTE:</b> 팝업관리 화면과 직접 연계되는 컨트롤러 클래스로 주로 JSP화면에서 호출되거나 해당 클래스 내부에서 호출되는
 * 메소드들이 정의되어 있다 해당 클래스 중에는 비즈니스 로직이 없고 단순히 다른 JSP화면으로 forword 시켜주는 메소드도 존재한다
 * 팝업정보 조회,입력,수정,삭제 요청을 처리한다
 * 
 * @author 포탈통계 이관형
 * @since 2011.06.27
 * @version 1.0
 * @see 
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
public class PopupManageController {

	/** PopupManageService 서비스 호출 */
	@Resource(name = "popupManageService")
	private PopupManageService popupManageService;

	/** multipartResolver 호출 */
	@Resource(name = "multipartResolver")
	CommonsMultipartResolver multipartResolver;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** EgovCmmUseService 호출 */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** EgovIdGnrService */
	@Resource(name = "popupSnIdGnrService")
	private EgovIdGnrService popupSnIdGnrService;

	/** GpsCmmService 호출 */
	@Resource(name = "gpsCmmService")
	private GpsCmmService gpsCmmService;

	/** defaultDataSet */
    @Resource(name="defaultDataSet")
    protected DefaultDataSet defaultDataSet;
	
	/** IgrAuthorManageService 서비스 호출 */ 
	@Resource(name = "igrAuthorManageService")
    private IgrAuthorManageService igrAuthorManageService;
	
	/** LOG */
	protected Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * popupManageVO 팝업목록 조회
	 * @param popupManageVO
	 * @param request
	 * @param model
	 * @return "gps/adm/popup/popupList"
	 * @throws Exception
	 * @see POPUP_SN,SYS_ID,ORDR,SJ,HTML_AT,WIDTH,HEIGHT,LC_TOP,LC_LEFT,LC_SCROLL,ACTVTY_BGNDE, 
     * @see ACTVTY_ENDDE,ACTVTY_AT,COOKIE_SKLL,ATCHMNFL_ONE,ATCHMNFL_ONE_MASK,ATCHMNFL_ONE_SIZE, 
     * @see ATCHMNFL_ONE_MIME,ATCHMNFL_TWO,ATCHMNFL_TWO_MASK,ATCHMNFL_TWO_SIZE,ATCHMNFL_TWO_MIME, 
     * @see ATCHMNFL_THREE,ATCHMNFL_THREE_MASK,ATCHMNFL_THREE_SIZE,ATCHMNFL_THREE_MIME, TRGET, URL, 
     * @see URL_TRGET,REGISTER_IP,REGIST_DT,UPDT_DT,REGISTER_ID,POPUP_TY, UPDTUSR_ID, CN
     * @see TABLE NAME : TN_POPUP,TN_SYSTEM
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/gps/adm/popup/selectPopupList.do")
	public String selectPopupList(
			@ModelAttribute("popupManageVO") PopupManageVO popupManageVO,
			HttpServletRequest request,
			ModelMap model) throws Exception {
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	IgrAuthorManageVO authorManageVO = new IgrAuthorManageVO();
    	authorManageVO.setUserId(gpsSessionVO.getUsrId());
        model.addAttribute("searchSysId", igrAuthorManageService.selectSysComboMap(authorManageVO));
        
		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(popupManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(popupManageVO.getPageUnit());
		paginationInfo.setPageSize(popupManageVO.getPageSize());

		popupManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		popupManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		popupManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		// 권한설정
		popupManageVO.setUserId(gpsSessionVO.getUsrId());
		
		List popupList = popupManageService.selectPopupList(popupManageVO);
		model.addAttribute("resultList", popupList);

		int totCnt = popupManageService.selectPopupListTotCnt(popupManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		model.addAttribute("popupManageVO", popupManageVO);

		return "gps/adm/popup/popupList";
	}

	/**
	 * 팝업 다중 삭제
	 * @param popupManageVO
	 * @param model
	 * @return "forward:/gps/adm/popup/selectPopupList.do"
	 * @throws Exception
	 * @see TABLE NAME : TN_POPUP
	 */
	@RequestMapping(value = "/gps/adm/progaram/deletePopupList.do")
	public String deletePopupList(
			@ModelAttribute("popupManageVO") PopupManageVO popupManageVO,
			ModelMap model) throws Exception {

		StringTokenizer popupSnList = new StringTokenizer(popupManageVO.getPopupSnList(), ",");
		while (popupSnList.hasMoreTokens()) {
			String tmp = (String)popupSnList.nextToken();
			StringTokenizer popupSntm = new StringTokenizer(tmp, "^");
			
			popupManageVO.setSysId((String)popupSntm.nextToken());
			popupManageVO.setPopupSn(Integer.parseInt(popupSntm.nextToken()));
			
			popupManageService.deletePopup(popupManageVO);
		}
		model.addAttribute("popupManageVO", popupManageVO);

		return "forward:/gps/adm/popup/selectPopupList.do";
	}

	/**
	 * popupManageVO 키값에 대한 게시물을 삭제(DB 행삭제)
	 * @param popupManageVO
	 * @param model
	 * @return
	 * @throws Exception
	 * @see TABLE NAME : TN_POPUP
	 */
	@RequestMapping(value = "/gps/adm/popup/deletePopup.do")
	public String deletePopup(
			@ModelAttribute("popupManageVO") PopupManageVO popupManageVO,
			ModelMap model) throws Exception {

		popupManageService.deletePopup(popupManageVO);
		model.addAttribute("popupManageVO", popupManageVO);
		
		return "forward:/gps/adm/popup/selectPopupList.do";
	}

	/**
	 * 팝업등록화면 호출
	 * @param popupManageVO
	 * @param request
	 * @param model
	 * @return "gps/adm/popup/popupRegist"
	 * @throws Exception
	 */
	@RequestMapping(value = "/gps/adm/popup/registerPopup.do")
	public String registerPopup(
			@ModelAttribute("popupManageVO") PopupManageVO popupManageVO,
			HttpServletRequest request,
			ModelMap model) throws Exception {
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	IgrAuthorManageVO authorManageVO = new IgrAuthorManageVO();
    	authorManageVO.setUserId(gpsSessionVO.getUsrId());
        model.addAttribute("sysId", igrAuthorManageService.selectSysComboMap(authorManageVO));

		model.addAttribute("searchCondition", cmmUseService.selectCmmCodeMap("COM036", ""));
		model.addAttribute("lcList", cmmUseService.selectCmmCodeMap("LC01", ""));
		model.addAttribute("targetList", cmmUseService.selectCmmCodeMap("TG01", ""));
		model.addAttribute("popupManageVO", popupManageVO);
		
		return "gps/adm/popup/popupRegist";
	}

	/**
	 * 팝업등록 처리 popupManageVO 에 담겨있는 항목을 DB에 insert
	 * @param multiRequest
	 * @param request
	 * @param popupManageVO
	 * @param bindingResult
	 * @param model
	 * @return "forward:/gps/adm/popup/selectPopupList.do"
	 * @throws Exception
	 * @see TABLE NAME : TN_POPUP
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/gps/adm/popup/insertPopup.do")
	public String insertPopup(final MultipartHttpServletRequest multiRequest,
			HttpServletRequest request,
			@ModelAttribute("popupManageVO") PopupManageVO popupManageVO,
			BindingResult bindingResult, ModelMap model) throws Exception {
    	
		List<FileVO> resultList = gpsCmmService.insertAtchFile(multiRequest.getFileMap(), "POPUP_", "", "");

		for (int i=0; i < resultList.size(); i++ ) {
				FileVO fileVO = (FileVO)resultList.get(i);
			if (fileVO.getAtchFileKey().equals("atchmnflOneFile")) {
				String atchFileId = resultList.get(i).getAtchFileId();
				
				popupManageVO.setAtchmnflOne(atchFileId);
				popupManageVO.setAtchmnflOneMask(fileVO.getStreFileNm());
				popupManageVO.setAtchmnflOneSize(Integer.parseInt(fileVO.getFileMg()));
				popupManageVO.setAtchmnflOneSize(Integer.parseInt(fileVO.getFileMg()));
			} else if (fileVO.getAtchFileKey().equals("atchmnflTwoFile")) {
				String atchFileId = resultList.get(i).getAtchFileId();
				
				popupManageVO.setAtchmnflTwo(atchFileId);
				popupManageVO.setAtchmnflTwoMask(fileVO.getStreFileNm());
				popupManageVO.setAtchmnflTwoSize(Integer.parseInt(fileVO.getFileMg()));
			} else if (fileVO.getAtchFileKey().equals("atchmnflThreeFile")) {
				String atchFileId = resultList.get(i).getAtchFileId();
				
				popupManageVO.setAtchmnflThree(atchFileId);
				popupManageVO.setAtchmnflThreeMask(fileVO.getStreFileNm());
				popupManageVO.setAtchmnflThreeSize(Integer.parseInt(fileVO.getFileMg()));
			}

		}

		popupManageVO.setPopupSn(Integer.parseInt(popupSnIdGnrService.getNextStringId()));
		
		popupManageVO = (PopupManageVO) defaultDataSet.registSet(request, popupManageVO);
		popupManageService.insertPopup(popupManageVO);
		model.addAttribute("popupManageVO", popupManageVO);

		return "forward:/gps/adm/popup/selectPopupList.do";
	}
	/**
	 * 팝업목록 수정화면 호출
	 * @param popupManageVO
	 * @param request
	 * @param model
	 * @return 
	 * @throws Exception
	 * @see POPUP_SN,SYS_ID,ORDR,SJ,HTML_AT,WIDTH,HEIGHT,LC_TOP,LC_LEFT,LC_SCROLL,ACTVTY_BGNDE, 
     * @see ACTVTY_ENDDE,ACTVTY_AT,COOKIE_SKLL,ATCHMNFL_ONE,ATCHMNFL_ONE_MASK,ATCHMNFL_ONE_SIZE, 
     * @see ATCHMNFL_ONE_MIME,ATCHMNFL_TWO,ATCHMNFL_TWO_MASK,ATCHMNFL_TWO_SIZE,ATCHMNFL_TWO_MIME, 
     * @see ATCHMNFL_THREE,ATCHMNFL_THREE_MASK,ATCHMNFL_THREE_SIZE,ATCHMNFL_THREE_MIME, TRGET, URL, 
     * @see URL_TRGET,REGISTER_IP,REGIST_DT,UPDT_DT,REGISTER_ID,POPUP_TY, UPDTUSR_ID, CN
     * @see TABLE NAME : TN_POPUP
	 */
	@RequestMapping(value = "/gps/adm/popup/modifyPopup.do")
	public String modifyPopup(
			@ModelAttribute("popupManageVO") PopupManageVO popupManageVO,
			HttpServletRequest request,
			ModelMap model) throws Exception {

    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	IgrAuthorManageVO authorManageVO = new IgrAuthorManageVO();
    	authorManageVO.setUserId(gpsSessionVO.getUsrId());
        model.addAttribute("sysId", igrAuthorManageService.selectSysComboMap(authorManageVO));
        PopupManageVO resultVo = popupManageService.selectPopup(popupManageVO);
        resultVo.setPageIndex(popupManageVO.getPageIndex());
        resultVo.setPageUnit(popupManageVO.getPageUnit());
        resultVo.setSearchCondition(popupManageVO.getSearchCondition());
        resultVo.setSearchKeyword(popupManageVO.getSearchKeyword());
        resultVo.setSearchSysId(popupManageVO.getSearchSysId());		
        resultVo.setSearchActvtyAt(popupManageVO.getSearchActvtyAt());		
        
        model.addAttribute("popupManageVO", resultVo);
        
		model.addAttribute("searchCondition", cmmUseService.selectCmmCodeMap("COM036", ""));
		model.addAttribute("lcList", cmmUseService.selectCmmCodeMap("LC01", ""));
		model.addAttribute("targetList", cmmUseService.selectCmmCodeMap("TG01", ""));

		return "gps/adm/popup/popupUpdate";
	}

	/**
	 * @param popupManageVO
	 * @param request
	 * @param model
	 * @return "gps/adm/popup/popupReview"
	 * @throws Exception
	 * @see POPUP_SN,SYS_ID,ORDR,SJ,HTML_AT,WIDTH,HEIGHT,LC_TOP,LC_LEFT,LC_SCROLL,ACTVTY_BGNDE, 
     * @see ACTVTY_ENDDE,ACTVTY_AT,COOKIE_SKLL,ATCHMNFL_ONE,ATCHMNFL_ONE_MASK,ATCHMNFL_ONE_SIZE, 
     * @see ATCHMNFL_ONE_MIME,ATCHMNFL_TWO,ATCHMNFL_TWO_MASK,ATCHMNFL_TWO_SIZE,ATCHMNFL_TWO_MIME, 
     * @see ATCHMNFL_THREE,ATCHMNFL_THREE_MASK,ATCHMNFL_THREE_SIZE,ATCHMNFL_THREE_MIME, TRGET, URL, 
     * @see URL_TRGET,REGISTER_IP,REGIST_DT,UPDT_DT,REGISTER_ID,POPUP_TY, UPDTUSR_ID, CN
     * @see TABLE NAME : TN_POPUP
	 */
	@RequestMapping(value = "/gps/adm/popup/popupReview.do")
	public String popupReview(
			@ModelAttribute("popupManageVO") PopupManageVO popupManageVO,
			HttpServletRequest request,
			ModelMap model) throws Exception {

        PopupManageVO resultVo = popupManageService.selectPopup(popupManageVO);
        
        model.addAttribute("popupManageVO", resultVo);
        
		return "gps/adm/popup/popupReview";
	}	
	
	/**
	 * 팝업목록 수정 처리
	 * @param multiRequest
	 * @param request
	 * @param popupManageVO
	 * @param bindingResult
	 * @param model
	 * @return "forward:/gps/adm/popup/selectPopupList.do"
	 * @throws Exception
	 * @see TABLE NAME : TN_POPUP
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/gps/adm/popup/updatePopup.do")
	public String updatePopup(final MultipartHttpServletRequest multiRequest,
			HttpServletRequest request,
			@ModelAttribute("popupManageVO") PopupManageVO popupManageVO,
			BindingResult bindingResult, ModelMap model) throws Exception {
		
		
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(multiRequest, "gpsSessionVO");
    	popupManageVO.setUpdtusrId(gpsSessionVO.getUsrId());
		Map<String, MultipartFile> fileMap = multiRequest.getFileMap();
		
		// 메인 이미지파일 삭제를 체크했을 경우
		if (null != fileMap.get("atchmnflOneFile")) {
			popupManageVO.setAtchmnflOneMask("");
			popupManageVO.setAtchmnflOneSize(0);
		}
		// 이미지파일 삭제를 체크했을 경우
		if (null != fileMap.get("atchmnflTwoFile")) {
			popupManageVO.setAtchmnflTwoMask("");
			popupManageVO.setAtchmnflTwoSize(0);
		}
		// 배경 이미지파일 삭제를 체크했을 경우
		if (null != fileMap.get("atchmnflThreeFile")) {
			popupManageVO.setAtchmnflThreeMask("");
			popupManageVO.setAtchmnflThreeSize(0);

		}

		if (!fileMap.isEmpty()){
			List<FileVO> resultList = gpsCmmService.updateAtchFile(fileMap, "POPUP_", popupManageVO.getAtchmnflOne(), "");
	
			for (int i=0; i < resultList.size(); i++ ) {
					FileVO fileVO = (FileVO)resultList.get(i);
				if (fileVO.getAtchFileKey().equals("atchmnflOneFile")) {
					String atchFileId = resultList.get(i).getAtchFileId();
					
					popupManageVO.setAtchmnflOne(atchFileId);
					popupManageVO.setAtchmnflOneMask(fileVO.getStreFileNm());
					popupManageVO.setAtchmnflOneSize(Integer.parseInt(fileVO.getFileMg()));
					popupManageVO.setAtchmnflOneSize(Integer.parseInt(fileVO.getFileMg()));
				} else if (fileVO.getAtchFileKey().equals("atchmnflTwoFile")) {
					String atchFileId = resultList.get(i).getAtchFileId();
					
					popupManageVO.setAtchmnflTwo(atchFileId);
					popupManageVO.setAtchmnflTwoMask(fileVO.getStreFileNm());
					popupManageVO.setAtchmnflTwoSize(Integer.parseInt(fileVO.getFileMg()));
				} else if (fileVO.getAtchFileKey().equals("atchmnflThreeFile")) {
					String atchFileId = resultList.get(i).getAtchFileId();
					
					popupManageVO.setAtchmnflThree(atchFileId);
					popupManageVO.setAtchmnflThreeMask(fileVO.getStreFileNm());
					popupManageVO.setAtchmnflThreeSize(Integer.parseInt(fileVO.getFileMg()));
				}
			}
		}

		popupManageVO = (PopupManageVO) defaultDataSet.updateSet(request, popupManageVO);
		popupManageService.updatePopup(popupManageVO);
		model.addAttribute("popupManageVO", popupManageVO);

		return "forward:/gps/adm/popup/selectPopupList.do";

	}

	/**
	 * 팝업순서변경화면호출
	 * @param popupManageVO
	 * @param model
	 * @return 팝업순서변경화면호출
	 * @throws Exception
	 */
	@RequestMapping(value = "/gps/adm/popup/popupOrderBy.do")
	public String popupOrderBy(
			@ModelAttribute("popupManageVO") PopupManageVO popupManageVO,
			ModelMap model) throws Exception {

		return "gps/adm/popup/popupOrderBy";
	}

	/**
	 * 팝업순서변경처리
	 * @param popupManageVO
	 * @param model
	 * @return "gps/adm/popup/popupOrderBy"
	 * @throws Exception
	 * @see TABLE NAME : TN_POPUP
	 */
	@RequestMapping(value = "/gps/adm/popup/updatePopupOrderBy.do")
	public String updatePopupOrderBy(
			@ModelAttribute("popupManageVO") PopupManageVO popupManageVO,
			ModelMap model) throws Exception {

		return "gps/adm/popup/popupOrderBy";
	}
	
	/**
	 * 팝업사용여부변경
	 * @param popupManageVO
	 * @param model
	 * @return "forward:gps/adm/popup/selectPopupList.do"
	 * @throws Exception
	 * @see TABLE NAME : TN_POPUP
	 */
	@RequestMapping(value = "/gps/adm/popup/updatePopupUseAt.do")
	public String updatePopupActvtyAt(
			@ModelAttribute("popupManageVO") PopupManageVO popupManageVO,
			ModelMap model) throws Exception {

		return "forward:gps/adm/popup/selectPopupList.do";
	}

	/**
	 * 첨부된 이미지에 대한 미리보기 기능을 제공한다.
	 * @param sessionVO
	 * @param commandMap
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/gps/adm/popup/getImageFile.do")
	public void getImageInf(SessionVO sessionVO,Map<String, Object> commandMap, HttpServletResponse response)
			throws Exception {

		FileVO vo = new FileVO();
		Iterator<Entry<String, Object>> it = commandMap.entrySet().iterator();
		while (it.hasNext()) {
			
			Entry<String, Object> entry = it.next();
			String paramKey = entry.getKey();

			// FileVO로부터Parameter키값 추출
			if (!paramKey.isEmpty()) {
				if (FileVO.class.getField(paramKey).getName().equals(paramKey)) {
					FileVO.class.getField(paramKey).set(vo, String.valueOf(entry.getValue()));
				}
			}
		}

		FileVO fvo = gpsCmmService.selectFileInf(vo);

		File file = new File(fvo.getFileStreCours(), fvo.getStreFileNm());			
		
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream in = new BufferedInputStream(fis);
		ByteArrayOutputStream bStream = new ByteArrayOutputStream();

		try {
			int imgByte;
			while ((imgByte = in.read()) != -1) {
				bStream.write(imgByte);
			}
		} catch (Exception e) {
			if(log.isErrorEnabled()){
				log.error(e);
			}
		} finally {
			if (null != bStream) {
				bStream.close();
			}
			if (null != in) {
				in.close();
			}
			if (null != fis) {
				fis.close();
			}
		}

		String type = "";
		if (fvo.getFileExtsn() != null && !"".equals(fvo.getFileExtsn())) {
			if ("jpg".equals(EgovStringUtil.lowerCase(fvo.getFileExtsn()))) {
				type = "image/jpeg";
			} else {
				type = "image/" + EgovStringUtil.lowerCase(fvo.getFileExtsn());
			}
			type = "image/" + EgovStringUtil.lowerCase(fvo.getFileExtsn());

		} else {
			log.debug("Image fileType is null.");
		}

		response.setHeader("Content-Type", type.replaceAll("\r", "").replaceAll("\n", ""));
		response.setContentLength(bStream.size());

		bStream.writeTo(response.getOutputStream());

		response.getOutputStream().flush();
		response.getOutputStream().close();

	}
	
}