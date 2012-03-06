package com.bbm.gps.csnst.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import com.bbm.common.cmm.EgovMessageSource;
import com.bbm.gps.adm.csnst.service.CsnstIemManageService;
import com.bbm.gps.adm.csnst.service.CsnstManageService;
import com.bbm.gps.adm.csnst.service.CsnstManageVO;
import com.bbm.gps.adm.csnst.service.CsnstMenoManageService;
import com.bbm.gps.adm.csnst.service.CsnstMenoVO;
import com.bbm.gps.adm.csnst.service.CsnstRspnsManageService;
import com.bbm.gps.adm.csnst.service.CsnstRspnsVO;
import com.bbm.gps.cmm.DefaultDataSet;

import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class SatisfactionController {

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
	/** CsnstManageService 서비스 호출 */ 
	@Resource(name = "csnstManageService")
    private CsnstManageService csnstManageService;

	/** csnstIemManageService 서비스 호출 */ 
	@Resource(name = "csnstIemManageService")
    private CsnstIemManageService csnstIemManageService;

	/** csnstRspnsManageService 서비스 호출 */ 
	@Resource(name = "csnstRspnsManageService")
    private CsnstRspnsManageService csnstRspnsManageService;

	/** csnstMenoManageService 서비스 호출 */ 
	@Resource(name = "csnstMenoManageService")
    private CsnstMenoManageService csnstMenoManageService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	/** LOG */ 
	protected Log log = LogFactory.getLog(this.getClass());
	
	/** defaultDataSet */
    @Resource(name="defaultDataSet")
    protected DefaultDataSet defaultDataSet;

    /**
     * 만족도 조사 미리보기
     * @param csnstIemManageVO 업데이트항목에 대한 만족도 정보를 가지고있는VO
     * @param model ModelMap
     * @return ("redirect:/gps/adm/csnstIem/selectCsnstIemList.do") 
     * @throws Exception
	 * @see TABLE NAME : 
     */
    @RequestMapping(value="/gps/csnst/satisfactionReview.do")
	public String selectCsnstReview (@ModelAttribute("csnstManageVO") CsnstManageVO csnstManageVO
			, @ModelAttribute("csnstRspnsVO") CsnstRspnsVO csnstRspnsVO
			, @ModelAttribute("csnstMenoVO") CsnstMenoVO csnstMenoVO
			, HttpServletRequest request
    		, ModelMap model
    ) throws Exception {

    	CsnstManageVO reviewVO = csnstManageService.selectCsnstReview(csnstManageVO);
    	
	    model.addAttribute("reviewVO", reviewVO);
		model.addAttribute("iemNmList", csnstIemManageService.selectCsnstIem(csnstManageVO));
		
        return "gps/csnst/satisfactionReview";
		    	
    }

    /**
     * 만족도 응답
     * @param csnstIemManageVO 업데이트항목에 대한 만족도 정보를 가지고있는VO
     * @param model ModelMap
     * @return ("redirect:/gps/adm/csnstIem/selectCsnstIemList.do") 
     * @throws Exception
	 * @see TABLE NAME : 
     */
    @RequestMapping(value="/gps/csnst/insertSatisfactionRspns.do")
	public String insertCsnstRspns (@ModelAttribute("csnstManageVO") CsnstManageVO csnstManageVO
			, @ModelAttribute("csnstRspnsVO") CsnstRspnsVO csnstRspnsVO
			, @ModelAttribute("csnstMenoVO") CsnstMenoVO csnstMenoVO
    		, HttpServletResponse response
			, HttpServletRequest request
			, ModelMap model
    ) throws Exception {
    	
    	// 1. 선택된 만족도 조사 항목 체크(비체크시 반환).
    	if (!csnstRspnsVO.getIemSn().equals("0")) {
	    	csnstRspnsVO = (CsnstRspnsVO) defaultDataSet.registSet(request, csnstRspnsVO);
	    	
	    	// 2.로그인 사용자인지 일반사용자인지를 설정하여 만족도조사를 체크(1=로그인사용자,2=일반사용자).
	    	if (!csnstRspnsVO.getRegisterId().isEmpty()) {
	    		csnstRspnsVO.setSearchCondition("1");
	    	} else {
	    		csnstRspnsVO.setSearchCondition("2");
	    	}
	    	
	    	// 3. 만족도 조사 중복 체크
	    	if (csnstRspnsManageService.selectCsnstRspnsCheck(csnstRspnsVO) > 0){
	    		// 3-1. 중복된 만족도 조사
	    	    model.addAttribute("message", egovMessageSource.getMessage("gps.csnstrsnpns.multi"));
	    	} else {
	    		// 3-2. 만족도 조사 반영
	
	    		// 주관식(체크박스)일경우 다중 저장
		    	StringTokenizer iemSn = new StringTokenizer(csnstRspnsVO.getIemSn(), ",");
		    	
				while (iemSn.hasMoreTokens()) {
			    	csnstRspnsVO.setRspnsSn(String.valueOf(csnstRspnsManageService.csnstRspnsSnGeneration()));
			    	csnstRspnsVO.setIemSn(iemSn.nextToken());
				    csnstRspnsManageService.insertCsnstRspns(csnstRspnsVO);
				}
				// 4. 메모입력 여부
				if (!csnstRspnsVO.getMemoCn().isEmpty()) {
					// 메모입력 값이 있을경우 메모 등록
					csnstMenoVO = (CsnstMenoVO) defaultDataSet.registSet(request, csnstMenoVO);
					csnstMenoVO.setMemoSn(String.valueOf(csnstMenoManageService.csnstMenoSnGeneration()));
					// 만족도조사 참여 화면에서 입력받은 메모값  설정
					csnstMenoVO.setMemoCn(csnstRspnsVO.getMemoCn());
					// 5. 메모 저장
					csnstMenoManageService.insertCsnstMeno(csnstMenoVO);
				}
				
			    model.addAttribute("message", egovMessageSource.getMessage("gps.csnstrsnpns.success"));
	    	}
    	} else {
    		// 1-1. 만족도 조사 비체크
		    model.addAttribute("message", egovMessageSource.getMessage("gps.satisfaction.noselected"));
    	}

		return "jsonView";
        
    }

}