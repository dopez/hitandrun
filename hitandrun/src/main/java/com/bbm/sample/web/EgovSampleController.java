/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bbm.sample.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


import com.bbm.sample.service.EgovSampleService;
import com.bbm.sample.service.SampleDefaultVO;
import com.bbm.sample.service.SampleVO;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


/**  
 * @Class Name : EgovSampleController.java
 * @Description : EgovSample Controller Class
 * @Modification Information  
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 * 
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 * 
 *  Copyright (C) by MOPAS All right reserved.
 */

@Controller
@SessionAttributes(types=SampleVO.class)
public class EgovSampleController {
	
	/** EgovSampleService */
    @Resource(name = "sampleService")
    private EgovSampleService sampleService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;


    /**
	 * 글 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 SampleDefaultVO
	 * @param model
	 * @return "/sample/egovSampleList"
	 * @exception Exception
	 */
    @RequestMapping(value="/egovSampleList.do")
    public String index(@ModelAttribute("searchVO") SampleDefaultVO searchVO, 
    		ModelMap model)
            throws Exception {
    	
    	/** EgovPropertyService.sample */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** pageing setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        List sampleList = sampleService.selectSampleList(searchVO);
        model.addAttribute("resultList", sampleList);
        
        int totCnt = sampleService.selectSampleListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "/egovframework/rte/sample/egovSampleList";
    } 
    
    
    /**
	 * 글 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 SampleDefaultVO
	 * @param model
	 * @return "/sample/egovSampleList"
	 * @exception Exception
	 */
    @RequestMapping(value="/test.do")
    public String test(@ModelAttribute("searchVO") SampleDefaultVO searchVO, 
    		ModelMap model)
            throws Exception {
    	
    	
        
        return "/sample/index";
    } 

    /**
	 * 글 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 SampleDefaultVO
	 * @param model
	 * @return "/sample/egovSampleList"
	 * @exception Exception
	 */
    @RequestMapping(value="/sample/egovSampleList2.do")
    public String selectSampleList2(@ModelAttribute("searchVO") SampleDefaultVO searchVO, 
    		ModelMap model)
            throws Exception {
    	
    	/** EgovPropertyService.sample */
    	searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	searchVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	/** pageing setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
        List sampleList = sampleService.selectSampleList(searchVO);
        model.addAttribute("resultList", sampleList);
        
        int totCnt = sampleService.selectSampleListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
        model.addAttribute("paginationInfo", paginationInfo);
        
        return "/egovframework/rte/sample/egovSampleList2";
    } 
    /**
	 * 글을 조회한다.
	 * @param sampleVO - 조회할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return @ModelAttribute("sampleVO") - 조회한 정보
	 * @exception Exception
	 */
    @RequestMapping("/sample/selectSample.do")
    public @ModelAttribute("sampleVO")
    SampleVO selectSample(
            SampleVO sampleVO,
            @ModelAttribute("searchVO") SampleDefaultVO searchVO) throws Exception {
        return sampleService.selectSample(sampleVO);
    }
		
    /**
	 * 글 등록 화면을 조회한다.
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "/sample/egovSampleRegister"
	 * @exception Exception
	 */
    @RequestMapping("/sample/addSampleView.do")
    public String addSampleView(
            @ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("sampleVO", new SampleVO());
        return "/egovframework/rte/sample/egovSampleRegister";
    }
    
    
    /**
	 * 글 등록 화면을 조회한다.
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "/sample/egovSampleRegister"
	 * @exception Exception
	 */
    @RequestMapping("/sample/addSampleView2.do")
    public String addSampleView2(
            @ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model)
            throws Exception {
        model.addAttribute("sampleVO", new SampleVO());
        return "/egovframework/rte/sample/egovSampleRegister2";
    }
    
    /**
	 * 글을 등록한다.
	 * @param sampleVO - 등록할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/sample/egovSampleList.do"
	 * @exception Exception
	 */
    @RequestMapping("/sample/addSample.do")
    public String addSample(
    		@ModelAttribute("searchVO") SampleDefaultVO searchVO,
       	 	SampleVO sampleVO,
            BindingResult bindingResult, Model model, SessionStatus status) 
    throws Exception {
    	
    	// Server-Side Validation
    	//beanValidator.validate(sampleVO, bindingResult);
    	
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("sampleVO", sampleVO);
			return "/egovframework/rte/sample/egovSampleRegister";
    	}
    	
        sampleService.insertSample(sampleVO);
        status.setComplete();
        return "forward:/sample/egovSampleList.do";
    }
    /**
	 * 글을 등록한다.
	 * @param sampleVO - 등록할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/sample/egovSampleList.do"
	 * @exception Exception
	 */
    @RequestMapping("/sample/addSample2.do")
    public String addSample2(
    		@ModelAttribute("searchVO") SampleDefaultVO searchVO,
       	 	SampleVO sampleVO,
            BindingResult bindingResult, Model model, SessionStatus status) 
    throws Exception {
    	
    	// Server-Side Validation
    	//beanValidator.validate(sampleVO, bindingResult);
    	
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("sampleVO", sampleVO);
			return "/egovframework/rte/sample/egovSampleRegister2";
    	}
    	
        sampleService.insertSample(sampleVO);
        status.setComplete();
        return "forward:/sample/egovSampleList2.do";
    }
    /**
	 * 글 수정화면을 조회한다.
	 * @param id - 수정할 글 id
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "/sample/egovSampleRegister"
	 * @exception Exception
	 */
    @RequestMapping("/sample/updateSampleView.do")
    public String updateSampleView(
            @RequestParam("selectedId") String id ,
            @ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model)
            throws Exception {
        SampleVO sampleVO = new SampleVO();
        sampleVO.setId(id);
        // 변수명은 CoC 에 따라 sampleVO
        model.addAttribute(selectSample(sampleVO, searchVO));
        return "/egovframework/rte/sample/egovSampleRegister";
    }

    /**
	 * 글 수정화면을 조회한다.
	 * @param id - 수정할 글 id
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "/sample/egovSampleRegister"
	 * @exception Exception
	 */
    @RequestMapping("/sample/updateSampleView2.do")
    public String updateSampleView2(
            @RequestParam("selectedId") String id ,
            @ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model)
            throws Exception {
        SampleVO sampleVO = new SampleVO();
        sampleVO.setId(id);
        // 변수명은 CoC 에 따라 sampleVO
        model.addAttribute(selectSample(sampleVO, searchVO));
        return "/egovframework/rte/sample/egovSampleRegister2";
    }
    
    /**
	 * 글을 수정한다.
	 * @param sampleVO - 수정할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/sample/egovSampleList.do"
	 * @exception Exception
	 */
    @RequestMapping("/sample/updateSample.do")
    public String updateSample(
            @ModelAttribute("searchVO") SampleDefaultVO searchVO, 
            SampleVO sampleVO, 
            BindingResult bindingResult, Model model, SessionStatus status)
            throws Exception {

    	//beanValidator.validate(sampleVO, bindingResult);
    	
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("sampleVO", sampleVO);
			return "/egovframework/rte/sample/sample/egovSampleRegister";
    	}
    	
        sampleService.updateSample(sampleVO);
        status.setComplete();
        return "forward:/sample/egovSampleList.do";
    }
    
    /**
	 * 글을 수정한다.
	 * @param sampleVO - 수정할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/sample/egovSampleList2.do"
	 * @exception Exception
	 */
    @RequestMapping("/sample/updateSample2.do")
    public String updateSample2(
            @ModelAttribute("searchVO") SampleDefaultVO searchVO, 
            SampleVO sampleVO, 
            BindingResult bindingResult, Model model, SessionStatus status)
            throws Exception {

    	//beanValidator.validate(sampleVO, bindingResult);
    	
    	if (bindingResult.hasErrors()) {
    		model.addAttribute("sampleVO", sampleVO);
			return "/egovframework/rte/sample/sample/egovSampleRegister2";
    	}
    	
        sampleService.updateSample(sampleVO);
        status.setComplete();
        return "forward:/sample/egovSampleList2.do";
    }
    
    /**
	 * 글을 삭제한다.
	 * @param sampleVO - 삭제할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/sample/egovSampleList.do"
	 * @exception Exception
	 */
    @RequestMapping("/sample/deleteSample.do")
    public String deleteSample(
            SampleVO sampleVO,
            @ModelAttribute("searchVO") SampleDefaultVO searchVO, SessionStatus status)
            throws Exception {
        sampleService.deleteSample(sampleVO);
        status.setComplete();
        return "forward:/sample/egovSampleList.do";
    }
    
    /**
	 * 글을 삭제한다.
	 * @param sampleVO - 삭제할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/sample/egovSampleList.do"
	 * @exception Exception
	 */
    @RequestMapping("/sample/deleteSample2.do")
    public String deleteSample2(
            SampleVO sampleVO,
            @ModelAttribute("searchVO") SampleDefaultVO searchVO, SessionStatus status)
            throws Exception {
        sampleService.deleteSample(sampleVO);
        status.setComplete();
        return "forward:/sample/egovSampleList2.do";
    }
    
    @RequestMapping(value = "/sample/index.do")
    public String spsindex() throws Exception
    {
        return "sample/index";
    }
    
    @RequestMapping(value = "/sample/cmm/header.do")
    public String spsheader() throws Exception
    {
        return "sample/cmm/header";
    }
    
    @RequestMapping(value = "/sample/cmm/top.do")
    public String spstop() throws Exception
    {
        return "sample/cmm/top";
    }
    
    @RequestMapping(value = "/sample/cmm/left.do")
    public String spsleft() throws Exception
    {
        return "sample/cmm/left";
    }
    
    @RequestMapping(value = "/sample/cmm/footer.do")
    public String spsfooter() throws Exception
    {
        return "sample/cmm/footer";
    }
    
    @RequestMapping(value = "/sample/cmm/bottom.do")
    public String spsbottom() throws Exception
    {
        return "sample/cmm/bottom";
    }

}
