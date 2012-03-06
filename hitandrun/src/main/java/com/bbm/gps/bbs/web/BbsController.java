package com.bbm.gps.bbs.web;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import com.bbm.common.cmm.EgovMessageSource;
import com.bbm.common.util.cas.service.EgovSessionCookieUtil;
import com.bbm.common.util.sim.service.EgovFileScrty;
import com.bbm.gps.adm.bbs.service.BbsInfoVO;
import com.bbm.gps.adm.bbs.service.BbsManageService;
import com.bbm.gps.bbs.service.BbsMemoVO;
import com.bbm.gps.bbs.service.BbsSearchVO;
import com.bbm.gps.bbs.service.BbsService;
import com.bbm.gps.bbs.service.BbsVO;
import com.bbm.gps.cmm.DefaultDataSet;
import com.bbm.gps.cmm.service.GpsCmmService;
import com.bbm.gps.login.service.GpsLoginVO;
import com.bbm.gps.login.service.GpsSessionVO;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/** 
 * 게시판 비즈니스 구현 클래스 
 * <p><b>NOTE:</b>게시글 화면과 직접 연계되는 컨트롤러 클래스로 주로 JSP화면에서 호출되거나 
 * 해당 클래스 내부에서 호출되는 메소드들이 정의되어 있다
 * 해당 클래스 중에는 비즈니스 로직이 없고 단순히 다른 JSP화면으로 forword 시켜주는 메소드도 존재한다
 * 게시글정보 조회,입력,수정,삭제 요청을 처리한다
 * @author 포탈통계 황기연 
 * @since 2011.09.14 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.09.14      황기연       최초 생성 
 * 
 * </pre> 
 */

@Controller
public class BbsController {
	/** BbsManageService 서비스 호출 */ 
	@Resource(name = "bbsManageService")
    private BbsManageService bbsManageService;
	
	/** BbsManageService 서비스 호출 */ 
	@Resource(name = "bbsService")
    private BbsService bbsService;
    
	/** massage source */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
	
	/** defaultDataSet */
    @Resource(name="defaultDataSet")
    protected DefaultDataSet defaultDataSet;
    
    /** gpsCmmService 호출 */ 
	@Resource(name="gpsCmmService")
	private GpsCmmService gpsCmmService;
	
	/** propertiesService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
	
    
	/** LOG */ 
	protected Log log = LogFactory.getLog(this.getClass());
	
	
	/** 
	 * 메인페이지 게시글 목록 조회
	 * @param bbsSearchVO 검색조건을 담고있는 VO
	 * @param bbsInfoVO 게시판정보를 담고있는 VO
	 * @param model ModelMap
	 * @return 게시판 skin 페이지
	 * @exception Exception 
	 * @see TABLE NAME : 
 	 */ 
	@RequestMapping(value="/gps/bbs/selectBbsIndexList.do")
	public String selectBbsIndexList(
			@ModelAttribute("bbsSearchVO")BbsSearchVO bbsSearchVO,
			@ModelAttribute("bbsInfoVO")BbsInfoVO bbsInfoVO,
			@ModelAttribute("bbsVO")BbsVO bbsVO,
			ModelMap model
			)throws Exception{
		bbsSearchVO.setBbsId(this.bbsService.bbsIdReplace(bbsSearchVO.getBbsId()));//특수문자제거
		bbsInfoVO = (BbsInfoVO)this.bbsManageService.selectBbsInfo(bbsInfoVO);
    	model.addAttribute("bbsInfoVO",bbsInfoVO);
		model.addAttribute("bbsList",(List<BbsVO>)this.bbsService.selectBbsIndexList(bbsSearchVO));
		return "gps/bbs/skin/"+bbsInfoVO.getSkinInfo()+"/bbsIndexList";
	}
	
	
	/** 
	 * 게시글 목록 조회
	 * @param bbsSearchVO 검색조건을 담고있는 VO
	 * @param bbsInfoVO 게시판정보를 담고있는 VO
	 * @param model ModelMap
	 * @return 게시판 skin 페이지
	 * @exception Exception 
	 * @see TABLE NAME : 
 	 */ 
	@RequestMapping(value="/gps/bbs/selectBbsList.do")
	public String selectBbsList(
			@ModelAttribute("bbsSearchVO")BbsSearchVO bbsSearchVO,
			@ModelAttribute("bbsInfoVO")BbsInfoVO bbsInfoVO,
			@ModelAttribute("gpsLoginVO") GpsLoginVO gpsLoginVO,
			@RequestParam(value="msg", required=false) Integer msg,
			HttpServletRequest request,
			ModelMap model
			)throws Exception{
		bbsSearchVO.setBbsId(this.bbsService.bbsIdReplace(bbsSearchVO.getBbsId()));//특수문자제거
		bbsInfoVO.setBbsId(this.bbsService.bbsIdReplace(bbsInfoVO.getBbsId()));//특수문자제거
		gpsLoginVO.setBbsId(this.bbsService.bbsIdReplace(gpsLoginVO.getBbsId()));//특수문자제거
		String returnUrl="";
		int recordCountPerPage = 0;
		boolean writeButtonUseAt = true;
		//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	//게시판 설정정보
    	bbsInfoVO = (BbsInfoVO)this.bbsManageService.selectBbsInfo(bbsInfoVO);
    	
    	//공개방법별  게시물 공개 처리(001-로그인 후,002-비밀번호확인후)
    	if(bbsInfoVO!=null){
    		if(bbsInfoVO.getOthbcMthUseAt().equals("Y")){
    			if(bbsInfoVO.getListExAuthor().contains("001")){
    				if(gpsSessionVO == null){
    					gpsLoginVO.setReturnUrl("/gps/bbs/selectBbsList.do");
    					model.addAttribute("message", this.egovMessageSource.getMessage("gps.afterLogin.useEnable"));
    					return "gps/login/gpsLoginUsr";
    				}
    			}
    			if(bbsInfoVO.getListExAuthor().contains("002")){
    				if(!bbsSearchVO.getAuthorPassword().equals(bbsInfoVO.getUsePassword())){
	    				bbsSearchVO.setReturnUrl("/gps/bbs/selectBbsList.do");
	    				model.addAttribute("message",!bbsSearchVO.getAuthorPassword().equals("")?this.egovMessageSource.getMessage("gps.fail.password"):"");
	            		return "gps/bbs/skin/"+bbsInfoVO.getSkinInfo()+"/bbsPassword";
    				}
            	}
    			//쓰기버튼숨김처리
    			if(bbsInfoVO.getLoginUseAt().equals("Y")&& bbsInfoVO.getBdtWriteAuthor().contains("001")){
        			if(gpsSessionVO == null){
        				writeButtonUseAt = false;
        			}
        		}
        	}
    	}
    	
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(bbsSearchVO.getPageIndex());
		
		
		//일반/앨범형 페이지 setting 
		if(bbsInfoVO.getAlbumAt().equals("Y")){
			if(bbsInfoVO.getAlbumStleAt().equals("2")){
				recordCountPerPage = bbsInfoVO.getPgeListCo();
			}else{
				recordCountPerPage = bbsInfoVO.getAlbumColumnCo()*bbsInfoVO.getAlbumLineCo();
			}
		}else{
			recordCountPerPage = bbsInfoVO.getPgeListCo();
		}
		
		paginationInfo.setRecordCountPerPage(recordCountPerPage);
		paginationInfo.setPageSize(bbsInfoVO.getPgeGroupCo());
		
		bbsSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		bbsSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		bbsSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		//총갯수
		paginationInfo.setTotalRecordCount(this.bbsService.selectBbsListTotCnt(bbsSearchVO));
		//목록
		model.addAttribute("bbsList",(List<BbsVO>)this.bbsService.selectBbsList(bbsSearchVO));
		
		//검색조건
		model.addAttribute("bbsSearchVO",bbsSearchVO);
		//게시판 설정정보
		model.addAttribute("bbsInfoVO",bbsInfoVO);
		//페이징정보
		model.addAttribute("paginationInfo", paginationInfo);
		//이미지경로
		model.addAttribute("WebImagePath", this.propertyService.getString("WebImagePath"));
		
		//처리 메세지
		String message = "";
		if(msg != null){
			if(msg == 4){//등록
				message = this.egovMessageSource.getMessage("success.common.insert");	
			}else if(msg == 5){//수정
				message = this.egovMessageSource.getMessage("success.common.update");
			}else if(msg == 6){//삭제
				message = this.egovMessageSource.getMessage("success.common.delete");
			}
		}
		model.addAttribute("message", message);
		model.addAttribute("writeButtonUseAt", writeButtonUseAt);
		returnUrl += "gps/bbs/skin/"+bbsInfoVO.getSkinInfo()+"/bbs";
		if(bbsInfoVO.getAlbumAt().equals("Y")){
			if(bbsInfoVO.getAlbumStleAt().equals("1")){
				returnUrl += "BasicAlbum";
			}else if(bbsInfoVO.getAlbumStleAt().equals("2")) {
				returnUrl += "ExtAlbum";
			}else{
				returnUrl += "";
			}
		}
		returnUrl += "List";
		return returnUrl;
	}
	
	/** 
	 * 게시글 등록화면
	 * @param bbsSearchVO 검색조건을 담고있는 VO
	 * @param bbsInfoVO 게시판정보를 담고있는 VO
	 * @param model ModelMap
	 * @return 게시판 skin 페이지
	 * @exception Exception 
	 * @see TABLE NAME : 
 	 */ 
	@RequestMapping(value="/gps/bbs/registerBbs.do")
	public String registerBbs(
			@ModelAttribute("bbsSearchVO")BbsSearchVO bbsSearchVO,
			@ModelAttribute("bbsInfoVO")BbsInfoVO bbsInfoVO,
			@ModelAttribute("bbsVO")BbsVO bbsVO,
			@ModelAttribute("gpsLoginVO") GpsLoginVO gpsLoginVO,
			HttpServletRequest request,
			ModelMap model
			)throws Exception{
		
		//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	if(gpsSessionVO!=null){
	    	bbsVO.setWrterId(gpsSessionVO.getUsrId());
	    	bbsVO.setWrterNm(gpsSessionVO.getUsrNm());
    	}
    	//게시판 설정정보
    	bbsInfoVO = (BbsInfoVO)this.bbsManageService.selectBbsInfo(bbsInfoVO);
		
    	//공개방법별  게시물 공개 처리(001-로그인 후,002-비밀번호확인후)
    	if(bbsInfoVO!=null){
    		if(bbsInfoVO.getOthbcMthUseAt().equals("Y")){
    			if(bbsInfoVO.getListExAuthor().contains("001")){
    				if(gpsSessionVO == null){
    					gpsLoginVO.setReturnUrl("/gps/bbs/registerBbs.do");
    					model.addAttribute("message", this.egovMessageSource.getMessage("gps.afterLogin.useEnable"));
    					return "gps/login/gpsLoginUsr";
    				}
    			}
    			if(bbsInfoVO.getListExAuthor().contains("002")){
    				if(!bbsSearchVO.getAuthorPassword().equals(bbsInfoVO.getUsePassword())){
	    				bbsSearchVO.setReturnUrl("/gps/bbs/registerBbs.do");
	    				model.addAttribute("message",!bbsSearchVO.getAuthorPassword().equals("")?this.egovMessageSource.getMessage("gps.fail.password"):"");
	            		return "gps/bbs/skin/"+bbsInfoVO.getSkinInfo()+"/bbsPassword";
    				}
            	}
        	}
    	}
    	
		//게시판 설정정보
		model.addAttribute("bbsInfoVO",bbsInfoVO);
		//세션정보
		model.addAttribute("gpsSessionVO",gpsSessionVO);
		//공지사항작성가능여부
		model.addAttribute("noticeWriteAt",(String)this.bbsService.noticeWriteAt(bbsVO));
		return "gps/bbs/skin/"+bbsInfoVO.getSkinInfo()+"/registerBbs";
	}
	
	/** 
	 * 게시글 등록 처리
	 * @param bbsSearchVO 검색조건을 담고있는 VO
	 * @param bbsInfoVO 게시판정보를 담고있는 VO
	 * @param model ModelMap
	 * @return 게시판 skin 페이지
	 * @exception Exception 
	 * @see TABLE NAME : 
 	 */ 
	@RequestMapping(value="/gps/bbs/insertBbs.do")
	public String insertBbs(
			@ModelAttribute("bbsVO")BbsVO bbsVO,
			final MultipartHttpServletRequest multiRequest,
			HttpServletRequest request,
			SessionStatus status,
			ModelMap model
			)throws Exception{
		bbsVO.setBbsId(this.bbsService.bbsIdReplace(bbsVO.getBbsId()));//특수문자제거
		//검색조건
		String bbsId = bbsVO.getBbsId();
		String menuId = bbsVO.getMenuId();
		String leftMenuId = bbsVO.getLeftMenuId();
		bbsVO = (BbsVO)this.defaultDataSet.registSet(request, bbsVO);
    	this.bbsService.insertBbs(bbsVO,multiRequest);
    	status.setComplete();

    	return "redirect:/gps/bbs/selectBbsList.do?msg=4&bbsId="+bbsId+"&menuId="+menuId+"&leftMenuId="+leftMenuId;
	}
	
	
	/** 
	 * 게시글 상세보기 화면
	 * @param bbsSearchVO 검색조건을 담고있는 VO
	 * @param bbsInfoVO 게시판정보를 담고있는 VO
	 * @param model ModelMap
	 * @return 게시판 skin 페이지
	 * @exception Exception 
	 * @see TABLE NAME : 
 	 */ 
	@RequestMapping(value="/gps/bbs/selectBbsDetail.do")
	public String selectBbsDetail(
			@ModelAttribute("bbsSearchVO")BbsSearchVO bbsSearchVO,
			@ModelAttribute("bbsInfoVO")BbsInfoVO bbsInfoVO,
			@ModelAttribute("bbsVO")BbsVO bbsVO,
			@ModelAttribute("gpsLoginVO")GpsLoginVO gpsLoginVO,
			@ModelAttribute("bbsMemoVO")BbsMemoVO bbsMemoVO,
			@RequestParam(value="msg", required=false) Integer msg,
			HttpServletRequest request,
			ModelMap model
			)throws Exception{
		bbsSearchVO.setBbsId(this.bbsService.bbsIdReplace(bbsSearchVO.getBbsId()));//특수문자제거
		bbsVO.setBbsId(this.bbsService.bbsIdReplace(bbsVO.getBbsId()));//특수문자제거
		boolean writeButtonUseAt = true;
		boolean answerButtonUseAt = true;
		//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	if(gpsSessionVO!=null){
    		bbsMemoVO.setMemoWrterId(gpsSessionVO.getUsrId());
    		bbsMemoVO.setMemoWrterNm(gpsSessionVO.getUsrNm());
    	}
    	//게시판 설정정보
    	bbsInfoVO = (BbsInfoVO)this.bbsManageService.selectBbsInfo(bbsInfoVO);
    	
    	//공개방법별  게시물 공개 처리(001-로그인 후,002-비밀번호확인후)
    	if(bbsInfoVO!=null){
    		if(bbsInfoVO.getOthbcMthUseAt().equals("Y")){
    			if(bbsInfoVO.getBdtRedngAuthor().contains("001")){
    				if(gpsSessionVO == null){
    					gpsLoginVO.setReturnUrl("/gps/bbs/selectBbsDetail.do");
    					model.addAttribute("message", this.egovMessageSource.getMessage("gps.afterLogin.useEnable"));
    					return "gps/login/gpsLoginUsr";
    				}
    			}
    			if(bbsInfoVO.getBdtRedngAuthor().contains("002")){
    				if(!bbsSearchVO.getAuthorPassword().equals(bbsInfoVO.getUsePassword())){
	    				bbsSearchVO.setReturnUrl("/gps/bbs/selectBbsDetail.do");
	    				model.addAttribute("message",!bbsSearchVO.getAuthorPassword().equals("")?this.egovMessageSource.getMessage("gps.fail.password"):"");
	            		return "gps/bbs/skin/"+bbsInfoVO.getSkinInfo()+"/bbsPassword";
    				}
    			}
    			//수정버튼숨김처리
    			if(bbsInfoVO.getLoginUseAt().equals("Y")&& bbsInfoVO.getBdtWriteAuthor().contains("001")){
        			if(gpsSessionVO == null){
        				writeButtonUseAt = false;
        			}
        		}
    			//답변버튼숨김처리
    			if(bbsInfoVO.getLoginUseAt().equals("Y")&& bbsInfoVO.getAnswerWriteAuthor().contains("001")){
        			if(gpsSessionVO == null){
        				answerButtonUseAt = false;
        			}
        		}
        	}
    	}
    	
    	
    	//게시글 조회수 증가
    	this.bbsService.updateCo(bbsSearchVO);
    	
    	//게시글 상세
    	bbsVO = (BbsVO)this.bbsService.selectBbs(bbsVO);
    	if(bbsVO!=null){
    		bbsVO.setCn(bbsVO.getCn()!=null?(bbsVO.getCn().replaceAll("\n","<br>")):"");
    	}
    	
		//게시판 설정정보
		model.addAttribute("bbsInfoVO",bbsInfoVO);
		model.addAttribute("bbsVO",bbsVO);
		//메모목록
		model.addAttribute("bbsMemoList", (List<BbsMemoVO>)this.bbsService.selectBbsMemoList(bbsSearchVO));
		
		//처리 메세지
		String message = "";
		if(msg != null){
			if(msg == 4){//등록
				message = this.egovMessageSource.getMessage("success.common.insert");	
			}else if(msg == 5){//수정
				message = this.egovMessageSource.getMessage("success.common.update");
			}else if(msg == 6){//삭제
				message = this.egovMessageSource.getMessage("success.common.delete");
			}else if(msg == 7){//비밀번호 불일치
				message = this.egovMessageSource.getMessage("gps.fail.password");
			}
		}	
		model.addAttribute("message", message);
		//쓰기 버튼 사용여부
		model.addAttribute("writeButtonUseAt", writeButtonUseAt);
		//답글버튼 사용여부
		model.addAttribute("answerButtonUseAt", answerButtonUseAt);
		//이미지경로
		model.addAttribute("WebImagePath", this.propertyService.getString("WebImagePath"));
		return "gps/bbs/skin/"+bbsInfoVO.getSkinInfo()+"/bbsDetail";
	}
	
	/** 
	 * 게시글 수정화면
	 * @param bbsSearchVO 검색조건을 담고있는 VO
	 * @param bbsInfoVO 게시판정보를 담고있는 VO
	 * @param bbsVO 게시글를 담고있는 VO
	 * @param model ModelMap
	 * @return 게시글 수정 페이지
	 * @exception Exception 
	 * @see TABLE NAME : 
 	 */ 
	@RequestMapping(value="/gps/bbs/modifyBbs.do")
	public String modifyBbs(
			@ModelAttribute("bbsSearchVO")BbsSearchVO bbsSearchVO,
			@ModelAttribute("bbsInfoVO")BbsInfoVO bbsInfoVO,
			@ModelAttribute("bbsVO")BbsVO bbsVO,
			@ModelAttribute("gpsLoginVO")GpsLoginVO gpsLoginVO,
			HttpServletRequest request,
			ModelMap model
			)throws Exception{
		bbsSearchVO.setBbsId(this.bbsService.bbsIdReplace(bbsSearchVO.getBbsId()));//특수문자제거
		boolean answerButtonUseAt = true;
		//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
    	if(gpsSessionVO!=null){
	    	bbsVO.setWrterId(gpsSessionVO.getUsrId());
	    	bbsVO.setWrterNm(gpsSessionVO.getUsrNm());
    	}
    	//게시판 설정정보
    	bbsInfoVO = (BbsInfoVO)this.bbsManageService.selectBbsInfo(bbsInfoVO);
    	
    	//공개방법별  게시물 공개 처리(001-로그인 후,002-비밀번호확인후)
    	if(bbsInfoVO!=null){
    		if(bbsInfoVO.getOthbcMthUseAt().equals("Y")){
    			//답변버튼숨김처리
    			if(bbsInfoVO.getLoginUseAt().equals("Y")&& bbsInfoVO.getAnswerWriteAuthor().contains("001")){
        			if(gpsSessionVO == null){
        				answerButtonUseAt = false;
        			}
        		}
        	}
    	}
    	
    	//게시글 정보
    	bbsVO = (BbsVO)this.bbsService.selectBbs(bbsSearchVO);
    	
    	//첨부파일 갯수
    	bbsVO.setFileListCnt(this.gpsCmmService.fileListCnt(bbsVO.getAtchmnflId()));
		
		model.addAttribute("bbsInfoVO",bbsInfoVO);
		model.addAttribute("bbsVO",bbsVO);
		model.addAttribute("gpsSessionVO",gpsSessionVO);
		//공지사항작성가능여부(게시판설정값)
		model.addAttribute("noticeWriteAt",(String)this.bbsService.noticeWriteAt(bbsVO));
		//답글버튼 사용여부
		model.addAttribute("answerButtonUseAt", answerButtonUseAt);
		return "gps/bbs/skin/"+bbsInfoVO.getSkinInfo()+"/modifyBbs";
	}
	
	/** 
	 * 게시글 수정 처리
	 * @param bbsSearchVO 검색조건을 담고있는 VO
	 * @param bbsInfoVO 게시판정보를 담고있는 VO
	 * @param model ModelMap
	 * @return 게시글 상세 페이지
	 * @exception Exception 
	 * @see TABLE NAME : 
 	 */ 
	@RequestMapping(value="/gps/bbs/updateBbs.do")
	public String updateBbs(
			@ModelAttribute("bbsVO")BbsVO bbsVO,
			final MultipartHttpServletRequest multiRequest,
			HttpServletRequest request,
			SessionStatus status,
			ModelMap model
			)throws Exception{
		bbsVO.setBbsId(this.bbsService.bbsIdReplace(bbsVO.getBbsId()));//특수문자제거
		//검색조건
		String bbsId = bbsVO.getBbsId();
		int bbsSn = bbsVO.getBbsSn();
		String menuId = bbsVO.getMenuId();
		String leftMenuId = bbsVO.getLeftMenuId();
		
		//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
		
    	
    	BbsVO vo = new BbsVO();
    	vo = (BbsVO)this.bbsService.selectBbs(bbsVO);
    	
    	bbsVO.setPassword(EgovFileScrty.encryptPassword(bbsVO.getPassword()));
    	
    	//비밀번호확인
    	if(vo!=null && vo.getPassword()!=null){
    		if(gpsSessionVO!=null){
    			if(!gpsSessionVO.getUsrId().equals(vo.getRegisterId())){
    				if(!vo.getPassword().equals(bbsVO.getPassword())){
    					model.addAttribute("message",this.egovMessageSource.getMessage("gps.fail.password"));
    					return "forward:/gps/bbs/modifyBbs.do";
    				}
    			}
    		}else{
    			if(!vo.getPassword().equals(bbsVO.getPassword())){
    				model.addAttribute("message",this.egovMessageSource.getMessage("gps.fail.password"));
					return "forward:/gps/bbs/modifyBbs.do";
				}
    		}
    	}
		
		bbsVO = (BbsVO)this.defaultDataSet.updateSet(request, bbsVO);
		
		//게시글 수정
    	this.bbsService.updateBbs(bbsVO,multiRequest);
    	status.setComplete();
		
		return "redirect:/gps/bbs/selectBbsDetail.do?msg=5&bbsId="+bbsId+"&bbsSn="+bbsSn+"&menuId="+menuId+"&leftMenuId="+leftMenuId;
	}
	
	
	/** 
	 * 게시글 삭제 처리
	 * @param bbsSearchVO 검색조건을 담고있는 VO
	 * @param bbsVO 게시글정보를 담고있는 VO
	 * @param model ModelMap
	 * @return 게시판 목록 페이지
	 * @exception Exception 
	 * @see TABLE NAME : 
 	 */ 
	@RequestMapping(value="/gps/bbs/deleteBbs.do")
	public String deleteBbs(
			@ModelAttribute("bbsVO")BbsVO bbsVO,
			HttpServletRequest request,
			ModelMap model
			)throws Exception{
		bbsVO.setBbsId(this.bbsService.bbsIdReplace(bbsVO.getBbsId()));//특수문자제거
		//검색조건
		String bbsId = bbsVO.getBbsId();
		String menuId = bbsVO.getMenuId();
		String leftMenuId = bbsVO.getLeftMenuId();
		
		//세션정보
    	GpsSessionVO gpsSessionVO = (GpsSessionVO)EgovSessionCookieUtil.getSessionAttribute(request, "gpsSessionVO");
		
    	
    	BbsVO vo = new BbsVO();
    	vo = (BbsVO)this.bbsService.selectBbs(bbsVO);
    	bbsVO.setPassword(EgovFileScrty.encryptPassword(bbsVO.getPassword()));
    	//비밀번호확인
    	if(vo!=null && vo.getPassword()!=null){
    		if(gpsSessionVO!=null){
    			if(!gpsSessionVO.getUsrId().equals(vo.getRegisterId())){
    				if(!vo.getPassword().equals(bbsVO.getPassword())){
    					model.addAttribute("message",this.egovMessageSource.getMessage("gps.fail.password"));
    					return "forward:/gps/bbs/modifyBbs.do";
    				}
    			}
    		}else{
    			if(!vo.getPassword().equals(bbsVO.getPassword())){
    				model.addAttribute("message",this.egovMessageSource.getMessage("gps.fail.password"));
					return "forward:/gps/bbs/modifyBbs.do";
				}
    		}
    	}
		
		
		//삭제처리
    	this.bbsService.deleteBbs(bbsVO);

		return "redirect:/gps/bbs/selectBbsList.do?msg=6&bbsId="+bbsId+"&menuId="+menuId+"&leftMenuId="+leftMenuId;
	}
	
	/** 
	 * 메모 등록 처리
	 * @param bbsMemoVO 메모정보를 담고있는 VO
	 * @param model ModelMap
	 * @return 게시글 상세 페이지
	 * @exception Exception 
	 * @see TABLE NAME : 
 	 */ 
	@RequestMapping(value="/gps/bbs/insertBbsMemo.do")
	public String insertBbsMemo(
			@ModelAttribute("bbsMemoVO")BbsMemoVO bbsMemoVO,
			HttpServletRequest request,
			SessionStatus status,
			ModelMap model
			)throws Exception{
		bbsMemoVO.setBbsId(this.bbsService.bbsIdReplace(bbsMemoVO.getBbsId()));//특수문자제거
		//검색조건
		String bbsId = bbsMemoVO.getBbsId();
		int bbsSn = bbsMemoVO.getBbsSn();
		String menuId = bbsMemoVO.getMenuId();
		String leftMenuId = bbsMemoVO.getLeftMenuId();
		
		bbsMemoVO = (BbsMemoVO)this.defaultDataSet.registSet(request, bbsMemoVO);
		
		//등록처리
    	this.bbsService.insertBbsMemo(bbsMemoVO);
    	status.setComplete();
    	return "redirect:/gps/bbs/selectBbsDetail.do?msg=4&bbsId="+bbsId+"&bbsSn="+bbsSn+"&menuId="+menuId+"&leftMenuId="+leftMenuId;
	}
	
	/** 
	 * 메모 삭제 처리
	 * @param bbsMemoVO 메모정보를 담고있는 VO
	 * @param model ModelMap
	 * @return 게시글 상세 페이지
	 * @exception Exception 
	 * @see TABLE NAME : 
 	 */ 
	@RequestMapping(value="/gps/bbs/deleteBbsMemo.do")
	public String deleteBbsMemo(
			@ModelAttribute("bbsMemoVO")BbsMemoVO bbsMemoVO,
			ModelMap model
			)throws Exception{
		bbsMemoVO.setBbsId(this.bbsService.bbsIdReplace(bbsMemoVO.getBbsId()));//특수문자제거
		//검색조건
		String bbsId = bbsMemoVO.getBbsId();
		int bbsSn = bbsMemoVO.getBbsSn();
		String menuId = bbsMemoVO.getMenuId();
		String leftMenuId = bbsMemoVO.getLeftMenuId();
		int msg=0;
    	
    	if(this.bbsService.memoPasswordConfirm(bbsMemoVO)){
    		//삭제처리
        	this.bbsService.deleteBbsMemo(bbsMemoVO);
        	msg = 6;
    	}else{
    		msg = 7;
    	}
    	return "redirect:/gps/bbs/selectBbsDetail.do?msg="+msg+"&bbsId="+bbsId+"&bbsSn="+bbsSn+"&menuId="+menuId+"&leftMenuId="+leftMenuId;
	}
}
