package com.bbm.gps.banner.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import com.bbm.common.cmm.EgovMessageSource;
import com.bbm.gps.adm.banner.service.BannerManageService;
import com.bbm.gps.adm.banner.service.BannerManageVO;
import com.bbm.gps.cmm.DefaultDataSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * 만족도관리 비즈니스 구현 클래스 
 * <p><b>NOTE:</b> 만족도 목록, 상세정보를 조회 하며 수정,삭제 요청을 처리한다.
 * 만족도관리 화면과 직접 연계되는 컨트롤러 클래스로 주로 JSP화면에서 호출되거나 
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
 *   2011.06.21     이관형       최초 생성 
 * 
 * </pre> 
 */

@Controller
public class BannerController {

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	/** BannerManageService 서비스 호출 */ 
	@Resource(name = "bannerManageService")
    private BannerManageService bannerManageService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** LOG */ 
	protected Log log = LogFactory.getLog(this.getClass());
	
	/** defaultDataSet */
    @Resource(name="defaultDataSet")
    protected DefaultDataSet defaultDataSet;

    /**
     * 배너
     * @param csnstIemManageVO 업데이트항목에 대한 만족도 정보를 가지고있는VO
     * @param model ModelMap
     * @return ("redirect:/gps/adm/csnstIem/selectCsnstIemList.do") 
     * @throws Exception
	 * @see TABLE NAME : 
     */
    @RequestMapping(value="/gps/banner/bannerMainList.do")
	public String selectBannerList (@ModelAttribute("bannerManageVO") BannerManageVO bannerManageVO
			, HttpServletRequest request
    		, ModelMap model
    ) throws Exception {

    	bannerManageVO.setSysId("0010001");
		model.addAttribute("bannerList", bannerManageService.selectMainBannerList(bannerManageVO));
		
        return "gps/banner/bannerTicker";
		    	
    }

}