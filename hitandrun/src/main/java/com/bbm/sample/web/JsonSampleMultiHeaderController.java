package com.bbm.sample.web;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.rte.fdl.property.EgovPropertyService;

@Controller
public class JsonSampleMultiHeaderController {

	protected Log log = LogFactory.getLog(this.getClass());


	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;


	@RequestMapping(value = "/goSampleJqgridMultiHeader.do")
	public String goSampleJqgrid() throws Exception {

		return "sample/json/sample_jqgrid_multiheader";
	}

	

}
