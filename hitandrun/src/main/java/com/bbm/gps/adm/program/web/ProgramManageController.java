package com.bbm.gps.adm.program.web;

import egovframework.rte.fdl.idgnr.impl.EgovUUIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import com.bbm.common.cmm.EgovMessageSource;
import com.bbm.common.cmm.service.EgovFileMngService;
import com.bbm.common.cmm.service.FileVO;
import com.bbm.common.util.ExcelUtil;
import com.bbm.common.util.StringUtil;
import com.bbm.common.util.cas.service.EgovSessionCookieUtil;
import com.bbm.gps.adm.program.service.ProgramManageService;
import com.bbm.gps.adm.program.service.ProgramManageVO;
import com.bbm.gps.adm.system.service.SystemManageService;
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

/** 
 * 프로그램관리 비즈니스 구현 클래스 
 * <p><b>NOTE:</b> 프로그램 목록, 상세정보를 조회 하며 수정,삭제 요청을 처리한다.
 * 프로그램관리 화면과 직접 연계되는 컨트롤러 클래스로 주로 JSP화면에서 호출되거나 
 * 해당 클래스 내부에서 호출되는 메소드들이 정의되어 있다
 * 해당 클래스 중에는 비즈니스 로직이 없고 단순히 다른 JSP화면으로 forword 시켜주는 메소드도 존재한다
 * DB select, DB insert, DB update, DB delete, 단순redirect 등의 기능을 하기위한 메소드들의 집합
 * @author 범정부통계포털 이관형 
 * @since 2011.06.21 
 * @version 1.0 
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
public class ProgramManageController {
	
	/** ProgramManageService 서비스 호출 */ 
	@Resource(name = "programManageService")
    private ProgramManageService programManageService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    /** EgovIdGnrService */
    @Resource(name = "programIdGnrService")
    private EgovUUIdGnrService idgenService;
    
	/** SystemManageService 서비스 호출 */ 
	@Resource(name = "systemManageService")
    private SystemManageService systemManageService;

	/** defaultDataSet */
    @Resource(name="defaultDataSet")
    protected DefaultDataSet defaultDataSet;

    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
    
    /** massage source */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	/** LOG */ 
	protected Log log = LogFactory.getLog(this.getClass());
	
    /**
     * 프로그램목록 조회 
     * @param programManageVO 검색조건
     * @param model ModelMap
     * @return 목록화면 JSP로 이동 ("gps/adm/program/programList") 
     * @throws Exception
     * @see TABLE NAME :
     */
    /**
     * 프로그램목록 조회 
     * @param programManageVO
     * @param request
     * @param model
     * @return "gps/adm/program/programList"
     * @throws Exception
     * @see PROGRAM_ID, PROGRAM_FILE_NM, PROGRAM_STRE_PATH, PROGRAM_KOREANNM, URL, PROGRAM_DC,
	 * @see PROGRAM_TY_READ, PROGRAM_TY_CREATE, PROGRAM_TY_UPDATE, PROGRAM_TY_DELETE,SYS_ID,SYS_NM,
	 * @see REGISTER_IP,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID
	 * @see TABLE NAME : TN_PROGRAM,TN_SYSTEM
	 */
    @RequestMapping(value="/gps/adm/program/selectProgramList.do")
	public String selectProgramList (@ModelAttribute("programManageVO") ProgramManageVO programManageVO
			, HttpServletRequest request
			, ModelMap model
			) throws Exception {

    	/** pageing */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(programManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(programManageVO.getPageUnit());
		paginationInfo.setPageSize(programManageVO.getPageSize());
		
		programManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		programManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		programManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		// 권한설정
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	programManageVO.setUserId(gpsSessionVO.getUsrId());
		
        model.addAttribute("resultList", programManageService.selectProgramList(programManageVO));
        
		paginationInfo.setTotalRecordCount(programManageService.selectProgramListTotCnt(programManageVO));
        model.addAttribute("paginationInfo", paginationInfo);
        model.addAttribute("programManageVO", programManageVO);
        
        //model.addAttribute("searchCondition", egovCmmUseService.selectCmmCode("21107211",""));
        // System 유형
    	model.addAttribute("sysInfo", systemManageService.selectSysInfoList(gpsSessionVO.getUsrId()));
    	
        return "gps/adm/program/programList";
	}
    
    /**
     * 프로그램 다중 삭제
     * @param programManageVO
     * @param model
     * @return "forward:/gps/adm/program/selectProgramList.do"
     * @throws Exception
     * @see TABLE NAME : TN_PROGRAM
     */
    @RequestMapping(value="/gps/adm/progaram/deleteProgramList.do")
	public String deleteProgramList (@ModelAttribute("programManageVO")ProgramManageVO programManageVO
			, ModelMap model
			) throws Exception {
    	StringTokenizer programIdList = new StringTokenizer(programManageVO.getProgramIdList(), ",");
		while(programIdList.hasMoreTokens() )
		{
			programManageVO.setProgramId(programIdList.nextToken());
			programManageService.deleteProgram(programManageVO);
		}	    	

    	return "forward:/gps/adm/program/selectProgramList.do";
	}
    
    /**
     * 프로그램을 삭제
     * @param programManageVO
     * @param model
     * @return forward:/gps/adm/program/selectProgramList.do"
     * @throws Exception
     * @see TABLE NAME : TN_PROGRAM
     */
    @RequestMapping(value="/gps/adm/program/deleteProgramDetail.do")
	public String deleteProgramDetail (@ModelAttribute("programManageVO")ProgramManageVO programManageVO
			, ModelMap model
			) throws Exception {
    	programManageService.deleteProgram(programManageVO);
    	return "forward:/gps/adm/program/selectProgramList.do";
	}

    /**
     * 프로그램 등록화면
     * @param programManageVO
     * @param request
     * @param model
     * @return "gps/adm/program/programRegist"
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/program/registerProgram.do")
	public String registerProgram(@ModelAttribute("programManageVO")ProgramManageVO programManageVO
			, HttpServletRequest request
			,ModelMap model
    ) throws Exception {
        // System 유형
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	model.addAttribute("sysInfo", systemManageService.selectSysInfoList(gpsSessionVO.getUsrId()));
        model.addAttribute("programManageVO", programManageVO);
        return "gps/adm/program/programRegist";
    }

    /**
     * 프로그램 등록
     * @param programManageVO
     * @param model
     * @param request
     * @return "forward:/gps/adm/program/selectProgramList.do"
     * @throws Exception
     * @see TABLE NAME : TN_PROGRAM
     */
    @RequestMapping(value="/gps/adm/program/insertProgram.do")
    public String insertProgram (
    		@ModelAttribute("programManageVO") ProgramManageVO programManageVO,
    		ModelMap model,
            HttpServletRequest request
    ) throws Exception {
    	String address = request.getHeader("HTTP_X_FORWARED_FOR");
    	if(address == null || address.length() == 0 || address.toLowerCase().equals("unknown")) {
    		address = request.getHeader("REMOTE_ADDR");
    	}
    	if(address == null || address.length() == 0 || address.toLowerCase().equals("unknown")) {
    		address = request.getRemoteAddr();
    	}
    	
    	idgenService.setAddress(address);
    	String strNo = idgenService.getNextStringId();
    	programManageVO.setProgramId(strNo);
		//등록자 정보
    	// 포털 사용자 로그인 취득
    	programManageVO = (ProgramManageVO) defaultDataSet.registSet(request, programManageVO);
		
    	programManageService.insertProgram(programManageVO);
        model.addAttribute("programManageVO", programManageVO);
    	return "forward:/gps/adm/program/selectProgramList.do";
    }

    /**
     * 프로그램정보를 Select하여 게시물을 수정하는 화면으로 이동 
     * @param programManageVO
     * @param request
     * @param model
     * @return "gps/adm/program/programUpdate"
     * @throws Exception
     */
    @RequestMapping(value="/gps/adm/program/modifyProgram.do")
	public String modifyProgram (@ModelAttribute("programManageVO") ProgramManageVO programManageVO
			, HttpServletRequest request
    		, ModelMap model
    ) throws Exception {
    	
		ProgramManageVO resultVO = programManageService.selectProgram(programManageVO);
		resultVO.setPageIndex(programManageVO.getPageIndex());
		resultVO.setPageUnit(programManageVO.getPageUnit());
		resultVO.setSearchCondition(programManageVO.getSearchCondition());
		resultVO.setSearchKeyword(programManageVO.getSearchKeyword());
		resultVO.setSearchSysId(programManageVO.getSearchSysId());
		
		model.addAttribute("programManageVO", resultVO);
        // System 유형
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	model.addAttribute("sysInfo", systemManageService.selectSysInfoList(gpsSessionVO.getUsrId()));
    	
        return "gps/adm/program/programUpdate";
    }

    /**
     * 프로그램 수정
     * @param programManageVO
     * @param request
     * @param model
     * @return "forward:/gps/adm/program/selectProgramList.do"
     * @throws Exception
     * @see TABLE NAME : TN_PROGRAM
     */
    @RequestMapping(value="/gps/adm/program/updateProgram.do")
	public String updateProgram (@ModelAttribute("programManageVO") ProgramManageVO programManageVO
			, HttpServletRequest request
    		, ModelMap model
    ) throws Exception {

		//수정자자 정보
    	programManageVO = (ProgramManageVO) defaultDataSet.updateSet(request, programManageVO);
    	
		programManageService.updateProgram(programManageVO);
        model.addAttribute("programManageVO", programManageVO);
		
        return "forward:/gps/adm/program/selectProgramList.do";
		    	
    }

	/**
	 * 프로그램정보Excel 업로드 화면호출
	 * @param programManageVO
	 * @param model
	 * @return "gps/adm/program/programExcelRegist"
	 * @throws Exception
	 */
	@RequestMapping(value="/gps/adm/program/registerProgramExcelUpload.do")
 	public String registerProgramExcelUpload ( @ModelAttribute("programManageVO") ProgramManageVO programManageVO
 			, ModelMap model
 			) throws Exception {
		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId("FILE_000000000011846");
		fileVO.setFileSn("0");
		
		fileVO  = fileService.selectFileInf(fileVO);
		model.addAttribute("fileVO", fileVO);
		
        model.addAttribute("programManageVO", programManageVO);
		
		return "gps/adm/program/programExcelRegist";
	}

	/**
	 * 프로그램정보Excel 파일 업로드 메소드. 엑셀파일을 사용자로부터 받아 셀 안의 내용을 리스트로 만든 후 DB에 insert
	 * @param programManageVO
	 * @param request
	 * @param commandMap
	 * @param model
	 * @return "forward:/gps/adm/program/selectProgramList.do"
	 * @throws Exception
	 * @see TABLE NAME : TN_PROGRAM
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/gps/adm/program/insertProgramExcelUpload.do")
	public String insertProgramExcelUpload(@ModelAttribute("programManageVO") ProgramManageVO programManageVO			
			, final HttpServletRequest request
			, Map commandMap
			, Model model) throws Exception {
		
    	//address
		String address = request.getHeader("HTTP_X_FORWARED_FOR");
    	if(address == null || address.length() == 0 || address.toLowerCase().equals("unknown")) {
    		address = request.getHeader("REMOTE_ADDR");
    	}
    	if(address == null || address.length() == 0 || address.toLowerCase().equals("unknown")) {
    		address = request.getRemoteAddr();
    	}
    	    	
    	final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		final Map<String, MultipartFile> files =  multiRequest.getFileMap();
		
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file = null;

		try {
			while (itr.hasNext()) {
				Entry<String, MultipartFile> entry = itr.next();
				
				file = entry.getValue();
				if (!"".equals(file.getOriginalFilename())) {
					if (StringUtil.checkExcelFileExt(file.getOriginalFilename())) {
						model.addAttribute("uploadFailMsg",this.egovMessageSource.getMessage("fail.common.filenameext"));
						return "forward:/gps/adm/program/registerProgramExcelUpload.do";
					} else {
						programManageService.insertExcelProgram(file.getInputStream(), address, ExcelUtil.getExcelVersion(file));
					}
				}
			}
		} catch (Exception e){
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
        model.addAttribute("programManageVO", programManageVO);
		
		return "forward:/gps/adm/program/selectProgramList.do";
	}

    /**
     * 프로그램목록검색 결과에 대한 excel 파일 다운로드 
     * @param programManageVO
     * @param request
     * @param model
     * @return ModelAndView
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value="/gps/adm/program/selectProgramExcelDownload.do")
	public ModelAndView selectProgramExcelDownload (@ModelAttribute("programManageVO") ProgramManageVO programManageVO
			, HttpServletRequest request
			, ModelMap model
			) throws Exception {
    	
    	// Excel 기능 공통
    	Map<String, String> ExcelMap = new HashMap<String, String>();
    	ExcelMap.put("fileName", "program_manage.xls");    	
    	ExcelMap.put("title", "프로그램관리");
    	ExcelMap.put("sheetName", "프로그램관리");

		// 권한설정
    	GpsSessionVO gpsSessionVO = (GpsSessionVO) EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	if(gpsSessionVO!=null){
    		programManageVO.setUserId(gpsSessionVO.getUsrId());
    	}
    	List programList =  programManageService.selectExcelProgramList(programManageVO);
    	programList.add(ExcelMap);
		return new ModelAndView("ComExcelView", "ExcelList", programList);
	}
}