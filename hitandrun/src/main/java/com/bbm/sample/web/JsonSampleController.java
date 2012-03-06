package com.bbm.sample.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import com.bbm.sample.ccm.cde.service.CmmnDetailCode;
import com.bbm.sample.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import com.bbm.sample.service.SampleJqgridVO;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JsonSampleController {

	protected Log log = LogFactory.getLog(this.getClass());

	@Resource(name = "CmmnDetailCodeManageService")
	private EgovCcmCmmnDetailCodeManageService cmmnDetailCodeManageService;

	//@Resource(name = "CmmnClCodeManageService")
	//private EgovCcmCmmnClCodeManageService cmmnClCodeManageService;

	//@Resource(name = "CmmnCodeManageService")
	//private EgovCcmCmmnCodeManageService cmmnCodeManageService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	//@Autowired
	//private DefaultBeanValidator beanValidator;

	@RequestMapping(value = "/goSampleJqgrid.do")
	public String goSampleJqgrid() throws Exception {

		return "sample/json/sample_jqgrid";
	}

	/**
	 * 공통상세코드 목록을 조회한다.
	 * 
	 * @param loginVO
	 * @param searchVO
	 * @param model
	 * @return "sample/ccm/EgovCcmCmmnDetailCodeList"
	 * @throws Exception
	 */
	@RequestMapping(value = "/jqgrid_list.do")
	public ModelAndView selectCmmnDetailCodeList2(
			@ModelAttribute("searchVO") SampleJqgridVO searchVO)
			throws Exception {

		ModelAndView model = new ModelAndView();

		/** EgovPropertyService.sample */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		//searchVO.setPageSize(propertiesService.getInt("pageSize"));
		
		
		log.debug("pageSize................:"+searchVO.getPageSize());
		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getRecordCountPerPage());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List CmmnCodeList = cmmnDetailCodeManageService
				.selectCmmnDetailCodeList(searchVO);
		model.addObject("resultList", CmmnCodeList);

		int totCnt = cmmnDetailCodeManageService
				.selectCmmnDetailCodeListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addObject("paginationInfo", paginationInfo);
		model.setViewName("jsonView");
		return model;
	}

	/**
	 * 공통상세코드를 수정한다.
	 * 
	 * @param loginVO
	 * @param cmmnDetailCode
	 * @param bindingResult
	 * @param commandMap
	 * @param model
	 * @return "sample/ccm/EgovCcmCmmnDetailCodeModify"
	 * @throws Exception
	 */
	@RequestMapping(value = "/jqgrid_update.do")
	public void updateCmmnDetailCode3( 	HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "json") String jsonString) throws Exception {

		
		log.debug("getJsonString................" + StringEscapeUtils.unescapeHtml(jsonString));
		log.debug("getJsonString................"
				+ request.getParameter("json"));

		JSONArray arr = JSONArray.fromObject(StringEscapeUtils.unescapeHtml(jsonString));

		List cmmnDetailCodes =  JSONArray.toList(arr,CmmnDetailCode.class);
		log.debug("arr2................"+ cmmnDetailCodes.size());
		
		for (int i = 0; i < cmmnDetailCodes.size(); i++) {
			CmmnDetailCode cmmnDetailCode = (CmmnDetailCode)cmmnDetailCodes.get(i);
			log.debug("cmmnDetailCode................"+ cmmnDetailCode.getCode());

		}
		 cmmnDetailCodeManageService.updateCmmnDetailCodes(cmmnDetailCodes);

	}

}
