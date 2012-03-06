package com.bbm.gps.adm.menu.service.impl;

import egovframework.rte.fdl.property.EgovPropertyService;
import com.bbm.common.cmm.EgovMessageSource;
import com.bbm.common.cmm.service.NaraCmmService;
import com.bbm.common.util.fcc.service.EgovStringUtil;
import com.bbm.gps.adm.author.intergrated.service.IgrAuthorManageVO;
import com.bbm.gps.adm.author.intergrated.service.impl.IgrAuthorManageDAO;
import com.bbm.gps.adm.menu.service.MenuManageService;
import com.bbm.gps.adm.menu.service.MenuManageVO;
import com.bbm.gps.adm.menu.service.SystemVO;
import com.bbm.gps.cmm.service.GpsCmmService;
import com.bbm.gps.login.service.GpsSessionVO;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 메뉴관리에 대한 서비스 구현클래스를 정의한다
 * <p><b>NOTE:</b> 메뉴관리에 대한 서비스 구현클래스를 정의한다
 * 서비스에 선언 되어있는 메소드들의 구현 클래스로 데이터 접근 클래스의 메소드를 호출한다
 * 메소드들 중에는 parameter를 넘기는 메소드도 있고 넘기지 않는 메소드도 존재한다
 * @author 이관/포털 황기연
 * @since 2011.06.07
 * @version 1.0
 * @see
 *
 * <pre>
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.06.07  황기연          최초 생성
 *
 * </pre>
 */

@Service("menuManageService")
public class MenuManageServiceImpl implements MenuManageService{
	
	/** propertiesService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
    
    /** NaraCmmService 호출 */ 
	@Resource(name="NaraCmmService")
	private NaraCmmService naraCmmService;
	
	/** gpsCmmService 호출 */ 
	@Resource(name="gpsCmmService")
	private GpsCmmService gpsCmmService;
	
	/** menuManageDAO */
    @Resource(name = "menuManageDAO")
    protected MenuManageDAO menuManageDAO;
    
    /** massage source */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name="igrAuthorManageDAO")
    private IgrAuthorManageDAO igrAuthorManageDAO;
    
    /**
	 * 페이지Navigation
	 * @param menuManageVO
	 * @return List
	 * @throws Exception
	 * @see menuLevel,menuNm,menuId,upperMenuId,menuUrl
	 * @see TABLE NAME : TN_MENU
	 */
    public List<MenuManageVO> selectNavi(MenuManageVO menuManageVO)throws Exception {
    	return this.menuManageDAO.selectNavi(menuManageVO);
    }

    /**
	 * 포털 사용자페이지 top 메뉴
	 * @param menuManageVO
	 * @return List<MenuManageVO> 메뉴목록
	 * @throws Exception
	 * @see menuLv,menuId,upperMenuId,menuNo,menuOrdr,menuNm,menuUrl,menuTy,ulOpenAt,endTagCnt,leftImageMask,leftImageCm,
	 * @see leftImageMime,leftImageMouseoverNm,leftImageMouseoverMask,leftImageMouseoverCm,leftImageMouseoverMime,bbsId
	 * @see TABLE NAME : TN_SYSTEM,TN_MENU
	 */
	public List<MenuManageVO> selectUserPageLeftMenuList(MenuManageVO menuManageVO)throws Exception {
		return (List<MenuManageVO>)this.menuManageDAO.selectUserPageLeftMenuList(menuManageVO);
	}
    
	/**
	 * 포털 사용자페이지 top 메뉴
	 * @param menuManageVO
	 * @return List<MenuManageVO> 메뉴목록
	 * @throws Exception
	 * @see menuAuthorAt,menuLv,menuNo,menuId,menuOrdr,upperMenuId,menuNm,menuTy,menuUrl,topImageNm,topImageMask,topImageCm,
	 * @see topImageMime,topImageMouseoverNm,topImageMouseoverMask,topImageMouseoverCm,topImageMouseoverMime,bbsId,as leaf
	 * @see TABLE NAME : TN_SYSTEM,TN_MENU
	 */
	public List<MenuManageVO> selectUserPageTopMenuList(MenuManageVO menuManageVO)throws Exception {
		return (List<MenuManageVO>)this.menuManageDAO.selectUserPageTopMenuList(menuManageVO);
	}
    
	/**
	 * 시스템(사이트)목록상세조회
	 * @param systemVO
	 * @return SystemVO
	 * @throws Exception
	 * @see sysId,upperSysId,orgId,prdctnId,svyOdr,sysNm,sysAbrv,sysEngNm,userManageUseAt,authorManageUseAt,menuManageUseAt,
	 * @see codeManageUseAt,programManageUseAt,mainImageManageUseAt,loginImageManageUseAt,boardManageUseAt,recomendSiteManageUseAt,
	 * @see stplatManageUseAt,snstUseAt,bannerManageUseAt,qestnarUseAt,schdulManageUseAt,eventManageUseAt,conectStatsUseAt,popupManageUseAt,
	 * @see useAt,bgnde,endde,sysRm,sysSe,registerId,registerIp,registDt,updtDt,deleteAt,updtusrId
	 * @see TABLE NAME : TN_SYSTEM
	 */
	public SystemVO selectSystem(SystemVO systemVO)throws Exception {
		 return (SystemVO)this.menuManageDAO.selectSystem(systemVO);
	}
	
	/**
	 * 조사목록
	 * @return List
	 * @throws Exception
	 * @see prdctnId,svyOdr,svyNm
	 * @see TABLE NAME : TN_PRDCTN_ODR
	 */
	public List<SystemVO> selectPrdctnList(SystemVO systemVO)throws Exception {
		return (List<SystemVO>)this.menuManageDAO.selectPrdctnList(systemVO);
	}
	
	/**
	 * 시스템(사이트)등록
	 * @param systemVO
	 * @throws Exception
	 * @see TABLE NAME : TN_SYSTEM
	 */
	public void insertSystem(SystemVO systemVO,IgrAuthorManageVO igrAuthorManageVO)throws Exception {
		int sysNo = 0;
		String sysId = "";
		sysNo = this.menuManageDAO.insertSystem(systemVO);
		if(sysNo > 0){
			systemVO.setSysNo(sysNo);
			sysId = this.insertManageMenu(systemVO);
			//시스템 생성후 권한생성
	    	if(!sysId.isEmpty()){
	    		igrAuthorManageVO.setAuthorId(sysId.substring(2,sysId.length()));//권한ID
	    		igrAuthorManageVO.setAuthorNm(systemVO.getSysNm());
	    		igrAuthorManageVO.setAuthorDc(systemVO.getSysNm());
	    		igrAuthorManageVO.setSysId(sysId);
	    		igrAuthorManageVO.setAuthorCode(sysId.substring(2,sysId.length()));//권한코드
	    		this.igrAuthorManageDAO.insertIam(igrAuthorManageVO);
	    	}
		}
	}
	
	/**
	 * 시스템(사이트)수정
	 * @param  SystemVO 메뉴VO
     * @throws Exception
	 * @see TABLE NAME : TN_SYSTEM
	 */
	public void updateSystem(SystemVO systemVO)throws Exception {
		int result = 0;
		result = (Integer)this.menuManageDAO.updateSystem(systemVO);
		if(result > 0 && !systemVO.getSysId().isEmpty()){
			
			 //시스템하위메뉴삭제
			 MenuManageVO menuManageVO = new MenuManageVO();
			 menuManageVO.setUpperMenuId(systemVO.getSysId());
			 result = (Integer)this.menuManageDAO.deleteSystemWithMenu(menuManageVO);
			
			 if(result > 0){
				 menuManageVO.setUpperMenuId(systemVO.getSysId());
				 this.menuManageDAO.deleteSystemWithManageMenuAuthor(menuManageVO);//관리메뉴 권한 정보 삭제
			 }
			 
			 //삭제후 기본 관리 메뉴 재생성
			 this.insertManageMenu(systemVO);
		 }
	}
	
	/**
	 * 시스템(사이트)삭제 여부 변경
	 * @param systemVO
	 * @return
	 * @throws Exception
	 * @see TABLE NAME : TN_SYSTEM 
	 */
	public void updateDeleteAtSystem(SystemVO systemVO)throws Exception {
		 int result = 0;
		 //시스템삭제
		 result = (Integer)this.menuManageDAO.updateDeleteAtSystem(systemVO);
		 if(result > 0){
			 //시스템하위메뉴삭제
			 MenuManageVO menuManageVO = new MenuManageVO();
			 menuManageVO.setUpperMenuId(systemVO.getSysId());
			 result = (Integer)this.menuManageDAO.updateDeleteAtSystemWithMenu(menuManageVO);
			 if(result > 0){
				 menuManageVO.setMenuId(systemVO.getSysId());
				 this.menuManageDAO.deleteSystemWithAuthor(menuManageVO);//권한삭제
				 this.menuManageDAO.deleteSystemWithMenuAuthor(menuManageVO);//메뉴권한정보삭제
			 }
		 }
	}
	
	/**
	 * 메뉴 목록
	 * @param menuManageVO
	 * @return List 메뉴목록
	 * @throws Exception
	 * @see rootSysId,upperSysId,menuId,menuNm,menuTy,sysSe,useAt,leaf,ulOpenAt,endTagCnt
	 * @see TABLE NAME : TN_MENU
	 */
	public List<MenuManageVO> selectMenuList(MenuManageVO menuManageVO)throws Exception {
		return (List<MenuManageVO>)this.menuManageDAO.selectMenuList(menuManageVO);
	}
	
	/**
	 * TOP페이지메뉴목록
	 * @param gpsSessionVO
	 * @return
	 * @throws Exception
	 * @see menuId,menuNm
	 * @see TABLE NAME : TN_SYSTEM,TN_MENU,TN_USER_AUTHOR,TN_MENU_AUTHOR,TN_AUTHOR
	 */
	public List<MenuManageVO> selectTopMenuListAdm(GpsSessionVO gpsSessionVO)throws Exception {
		return (List<MenuManageVO>)this.menuManageDAO.selectTopMenuListAdm(gpsSessionVO);
	}
	
	
	/**
	 * TOP페이지메뉴목록
	 * @param gpsSessionVO
	 * @return
	 * @throws Exception
	 * @see menuId,menuNm
	 * @see TABLE NAME : TN_SYSTEM,TN_MENU,TN_USER_AUTHOR,TN_MENU_AUTHOR,TN_AUTHOR
	 */
	public List<MenuManageVO> selectTopMenuList(GpsSessionVO gpsSessionVO)throws Exception {
		return (List<MenuManageVO>)this.menuManageDAO.selectTopMenuList(gpsSessionVO);
	}
	
	/**
	 * LEFT페이지메뉴목록
	 * @param menuManageVO
	 * @return List
	 * @throws Exception
	 * @see menuId,upperMenuId,menuNo,menuOrdr,menuNm,menuUrl,menuTy,sysUseTy,bbsId,ulOpenAt,endTagCnt
	 * @see TABLE NAME : TN_SYSTEM,TN_MENU
	 */
	public List<MenuManageVO> selectLeftMenuList(MenuManageVO menuManageVO)throws Exception {
		return (List<MenuManageVO>)this.menuManageDAO.selectLeftMenuList(menuManageVO);
	}
	
	/**
	 * 메뉴 상세 조회
	 * @param menuManageVO
	 * @return MenuManageVO
	 * @throws Exception
	 * @see menuId,upperMenuId,menuNo,psitnCode,menuCode,menuOrdr,sysUseTy,menuNm,menuAbrv,menuEngNm,menuTy,
	 * @see menuSeCode,menuSkin,menuSkinButton,menuUrl,menuUrlTarget,useAt,topMenuUseAt,leftMenuUseAt,
	 * @see bottomMenuUseAt,sitemapUseAt,topImageNm,topImageMask,topImageCm,topImageMime,topImageSize,
	 * @see topImageMouseoverNm,topImageMouseoverMask,topImageMouseoverCm,topImageMouseoverMime,
	 * @see topImageMouseoverSize,leftImageNm,leftImageMask,leftImageCm,leftImageMime,leftImageSize,
	 * @see bottomImageNm,bottomImageMask,bottomImageCm,bottomImageMime,bottomImageSize,bottomImageMouseoverNm,
	 * @see bottomImageMouseoverMask,bottomImageMouseoverCm,bottomImageMouseoverMime,bottomImageMouseoverSize,
	 * @see sitemapImageNm,sitemapImageMask,sitemapImageCm,sitemapImageMime,sitemapImageSize,sitemapImageMouseoverNm,
	 * @see sitemapImageMouseoverMask,sitemapImageMouseoverCm,sitemapImageMouseoverMime,sitemapImageMouseoverSize,
	 * @see titleImageNm,titleImageMask,titleImageMime,titleImageSize,menuRm,programId,snstUseAt,
	 * @see attachmentFileId,bbsId,bbsNm,popupWidth,popupHeight,popupTop,popupLeft,registerId,registerIp,
	 * @see registDt,updtDt,deleteAt,updtusrId,csnstId
	 * @see TABLE NAME : TN_MENU
	 */
	public MenuManageVO selectMenu(MenuManageVO menuManageVO)throws Exception {
		MenuManageVO resultVO = new MenuManageVO();
		resultVO = (MenuManageVO)this.menuManageDAO.selectMenu(menuManageVO);
		resultVO.setFileListCnt(this.gpsCmmService.fileListCnt(resultVO.getAttachmentFileId()));//첨부파일 갯수
		return resultVO;
	}

	/**
	 * 메뉴 등록 처리
	 * @param menuManageVO
	 * @return
	 * @throws Exception
	 * @see TABLE NAME : TN_MENU
	 */
	@SuppressWarnings("unchecked")
	public void insertMenu(MenuManageVO menuManageVO,MultipartHttpServletRequest multiRequest)throws Exception {
		Map<String, MultipartFile> fileMap = new HashMap<String, MultipartFile>();
		int menuNo = 0;
		//메뉴이미지 정보 업로드 처리
		menuManageVO = (MenuManageVO)this.setImgFile(menuManageVO, multiRequest);
		
		//첨부파일 업로드처리
		String _atchFileId = "";
    	final Map<String, MultipartFile> files = multiRequest.getFileMap();
    	Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
	    
    	while (itr.hasNext()) {
    	    Entry<String, MultipartFile> entry = itr.next();
    	    if( entry.getKey().indexOf("file_") > -1  ) {
    	    	fileMap.put( entry.getKey(), entry.getValue() );
    	    }
    	}
    	
    	//첨부파일쪽만 업로드
    	if(!fileMap.isEmpty()){
    		_atchFileId = this.naraCmmService.insertAttachFile(multiRequest, fileMap, this.getClass().getPackage().toString(), "",  "");	
	        if( !"".equals( _atchFileId ) ) {
	    		menuManageVO.setAttachmentFileId(_atchFileId);
    		}
    	}
    	menuNo = this.menuManageDAO.insertMenu(menuManageVO);
    	if(menuNo > 0){
    		//권한등록
    		menuManageVO.setMenuNo(menuNo);
    		this.menuManageDAO.insertMenuAuthor(menuManageVO);
    		
    		//메뉴형태가 게시판이면 게시판관리 테이블에 메뉴ID등록
    		if(menuManageVO.getMenuTy()!= null && menuManageVO.getMenuTy().equals("B")){
    			//등록된메뉴ID검색
    			menuManageVO.setMenuId((String)this.menuManageDAO.selectMenuId(menuManageVO));
    			//게시판관리 메뉴 ID 등록
    			this.menuManageDAO.updateBbsSubpageId(menuManageVO);
    		}
    		
    	}
	}

	/**
	 * 메뉴 수정 처리
	 * @param menuManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_MENU
	 */
	@SuppressWarnings("unchecked")
	public void updateMenu(MenuManageVO menuManageVO,MultipartHttpServletRequest multiRequest)throws Exception {
		Map<String, MultipartFile> fileMap = new HashMap<String, MultipartFile>();
		
		//메뉴이미지 정보 업로드 처리
		menuManageVO = this.setImgFile(menuManageVO, multiRequest);
		
		
		//첨부파일 업로드처리
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
	    
    	while (itr.hasNext()) {
    	    Entry<String, MultipartFile> entry = itr.next();
    	    if( entry.getKey().indexOf("file_") > -1  ) {
    	    	fileMap.put( entry.getKey(), entry.getValue() );
    	    }
    	}
    	String _atchFileId = menuManageVO.getAttachmentFileId(); 
    	//첨부파일쪽만 업로드
    	if(!fileMap.isEmpty()){
    		if( "".equals(_atchFileId)) {
    			_atchFileId = this.naraCmmService.insertAttachFile(multiRequest, fileMap, this.getClass().getPackage().toString(), "",  "");				
    			if( !"".equals(_atchFileId )) {    		
    	    		menuManageVO.setAttachmentFileId(_atchFileId);
        		}	
    		} else {    
    			this.naraCmmService.updateAtchFile(multiRequest, fileMap, this.getClass().getPackage().toString(),  _atchFileId, "");   	
    		}
    	}
		
		this.menuManageDAO.updateMenu(menuManageVO);
		if(menuManageVO.getMenuTy()!=null && menuManageVO.getMenuTy().equals("B")){
			this.menuManageDAO.updateBbsSubpageId(menuManageVO);
		}
	}
	
	/**
	 * 메뉴 삭제 처리
	 * @param menuManageVO
	 * @return int
	 * @throws Exception
	 * @see TABLE NAME : TN_MENU
	 */
	public void deleteMenu(MenuManageVO menuManageVO)throws Exception {
		//메뉴삭제
		if(this.menuManageDAO.deleteMenu(menuManageVO) > 0){
			//메뉴권한삭제
			this.menuManageDAO.deleteMenuWithMenuAuthor(menuManageVO);
		}
	}
	
	/**
	 * 메뉴이미지삭제처리
	 * @param menuManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_MENU
	 */
	public void deleteImgFile(MenuManageVO menuManageVO)throws Exception {
		this.menuManageDAO.deleteImgFile(menuManageVO);
	}
	
	
	/**
	 * 업로드된 메뉴이미지 MenuManageVO에 setting
	 * @param menuManageVO
	 * @param multiRequest MultipartHttpServletRequest
	 * @return MenuManageVO 메뉴이미지를 담고있는 VO
     * @throws Exception
     */
	private MenuManageVO setImgFile(MenuManageVO menuManageVO,MultipartHttpServletRequest multiRequest)throws Exception {
		String filePath = "";
		String storePath = propertyService.getString("GPS.fileStorePath");
			for (int i = 1; i <= 9; i++) {
				MultipartFile file = multiRequest.getFile("menuImg_"+Integer.toString(i));
				// 첨부파일이 존재하면 업로드 처리 
				if(file != null) {
					File saveFolder = new File(storePath);
					
					if (!saveFolder.exists() || saveFolder.isFile()) {
					    saveFolder.mkdirs();
					}
					String orginFileName = file.getOriginalFilename();
					int index = orginFileName.lastIndexOf(".");
				    String fileExt = orginFileName.substring(index + 1);
				    String newName = EgovStringUtil.getTimeStamp()+"_"+Integer.toString(i);
				    int _size = (int)file.getSize();
				    
					if( !"".equals(orginFileName)) {
						filePath = storePath + File.separator + newName;
						file.transferTo(new File(filePath));
						
						switch (i) {
						case 1:	// TOP메뉴오버로드전이미지정보
							menuManageVO.setTopImageNm(orginFileName);
							menuManageVO.setTopImageMask(newName);
							menuManageVO.setTopImageSize(_size);
							menuManageVO.setTopImageMime(fileExt);
							break;
						case 2:	// TOP메뉴오버로드후이미지정보
							menuManageVO.setTopImageMouseoverNm(orginFileName);
							menuManageVO.setTopImageMouseoverMask(newName);
							menuManageVO.setTopImageMouseoverSize(_size);
							menuManageVO.setTopImageMouseoverMime(fileExt);
							break;
						case 3:	// LEFT메뉴오버로드전이미지정보
							menuManageVO.setLeftImageNm(orginFileName);
							menuManageVO.setLeftImageMask(newName);
							menuManageVO.setLeftImageSize(_size);
							menuManageVO.setLeftImageMime(fileExt);
							break;
						case 4:	// LEFT메뉴오버로드후이미지정보
							menuManageVO.setLeftImageMouseoverNm(orginFileName);
							menuManageVO.setLeftImageMouseoverMask(newName);
							menuManageVO.setLeftImageMouseoverSize(_size);
							menuManageVO.setLeftImageMouseoverMime(fileExt);
							break;
						case 5:	// BOTTOM메뉴오버로드전이미지정보
							menuManageVO.setBottomImageNm(orginFileName);
							menuManageVO.setBottomImageMask(newName);
							menuManageVO.setBottomImageSize(_size);
							menuManageVO.setBottomImageMime(fileExt);
							break;
						case 6:	// BOTTOM메뉴오버로드후이미지정보
							menuManageVO.setBottomImageMouseoverNm(orginFileName);
							menuManageVO.setBottomImageMouseoverMask(newName);
							menuManageVO.setBottomImageMouseoverSize(_size);
							menuManageVO.setBottomImageMouseoverMime(fileExt);
							break;
						case 7:	// SITEMAP오버로드전이미지정보
							menuManageVO.setSitemapImageMouseoverNm(orginFileName);
							menuManageVO.setSitemapImageMouseoverMask(newName);
							menuManageVO.setSitemapImageMouseoverSize(_size);
							menuManageVO.setSitemapImageMouseoverMime(fileExt);
							break;
						case 8:	// SITEMAP오버로드후이미지정보
							menuManageVO.setSitemapImageMouseoverNm(orginFileName);
							menuManageVO.setSitemapImageMouseoverMask(newName);
							menuManageVO.setSitemapImageMouseoverSize(_size);
							menuManageVO.setSitemapImageMouseoverMime(fileExt);
							break;
						case 9:	// 타이틀이미지정보
							menuManageVO.setTitleImageNm(orginFileName);
							menuManageVO.setTitleImageMask(newName);
							menuManageVO.setTitleImageSize(_size);
							menuManageVO.setTitleImageMime(fileExt);
							break;
						default:
							break;
						}
					}
		    	}
			}
		return menuManageVO;
	}
	
	
	/**
	 * 시스템생성시 기본 메뉴생성
	 * @param systemVO
	 * @param List<MenuManageVO> menuNoList
	 * @return sysId
     * @throws Exception
     * @see TABLE NAME : TN_SYSTEM,TN_MENU
     */
	public String insertManageMenu(SystemVO systemVO)throws Exception{
		String sysId = "";									  //시스템ID
		int systemManageFolderNo = 0;						  //시스템(사이트)관리 폴더 메뉴NO
		String systemManageFolderMenuId = "";				  //시스템(사이트)관리 폴더 메뉴ID
		int contentManageFolderNo = 0;						  //컨텐츠관리 폴더 메뉴NO
		String contentManageFolderMenuId = "";				  //컨텐츠관리 폴더 메뉴ID
		int contentFolderNo = 0;						  	  //컨텐츠관리 메뉴NO
		int menuNo = 0;						  				  //메뉴NO
		int menuOrdr = 1;
		List<Integer> menuNoList = new ArrayList<Integer>();  //권한등록할  menu_no list
		MenuManageVO menuManageVO = new MenuManageVO();
		menuManageVO.setSysUseTy("Y");		//시스템사용타입
		menuManageVO.setMenuUrlTarget("1");	//메뉴URL타겟
		menuManageVO.setUseAt("Y");			//메뉴사용여부
		//시스템 생성 sysNo list 에 추가
		menuNoList.add(systemVO.getSysNo());
		
		SystemVO resultVO = new SystemVO();
		resultVO = (SystemVO)this.menuManageDAO.selectSystemInfo(systemVO);
		if(resultVO !=null && !resultVO.getSysId().equals("")){
			sysId = resultVO.getSysId();
			//시스템등록후 시스템(사이트)관리 폴더 등록처리
			menuManageVO.setUpperMenuId(sysId);
			menuManageVO.setMenuOrdr(menuOrdr++);
			menuManageVO.setMenuNm(this.egovMessageSource.getMessage("gps.menu."+(systemVO.getSysSe().equals("sys")?"system":"site")+"Manage"));
			//시스템(사이트)관리 폴더 등록
			systemManageFolderNo = (Integer)this.menuManageDAO.insertMenu(menuManageVO);
			
			menuManageVO.setMenuOrdr(menuOrdr++);
			menuManageVO.setMenuNm(this.egovMessageSource.getMessage("gps.menu.contentManage"));
			//컨텐츠관리 폴더 등록
			contentManageFolderNo = (Integer)this.menuManageDAO.insertMenu(menuManageVO);
			
			if(systemVO.getSysId()==null || systemVO.getSysId().isEmpty()){		//시스템수정일때는 생성안함
				//컨텐츠 폴더 생성
				menuManageVO.setMenuNm(resultVO.getSysNm());
				menuManageVO.setMenuOrdr(menuOrdr++);
				menuManageVO.setSysUseTy("N");
				contentFolderNo = (Integer)this.menuManageDAO.insertMenu(menuManageVO);
				menuNoList.add(contentFolderNo);
			}
			//시스템관리메뉴생성
			if(systemManageFolderNo > 0){
				menuOrdr = 1;
				//시스템(사이트)관리 menuNo list에 추가
				menuNoList.add(systemManageFolderNo);
				
				//입력된 메뉴ID 조회
	    		menuManageVO.setMenuNo(systemManageFolderNo);
	    		menuManageVO.setSysUseTy("Y");
	    		systemManageFolderMenuId = (String)this.menuManageDAO.selectMenuId(menuManageVO);
	    		menuManageVO.setUpperMenuId(systemManageFolderMenuId);
	    		menuManageVO.setMenuTy("H");
	    		
	    		
	    		//코드관리
	    		if(systemVO.getCodeManageUseAt().equals("Y")){
		    		menuManageVO.setMenuOrdr(menuOrdr++);
		    		menuManageVO.setMenuNm(this.egovMessageSource.getMessage("gps.menu.codeManage"));//링크메뉴명
		    		menuManageVO.setMenuUrl(this.egovMessageSource.getMessage("gps.menu.codeManage.url"));//메뉴URL
		    		menuNo = (Integer)this.menuManageDAO.insertMenu(menuManageVO);
		    		menuNoList.add(menuNo);
	    		}
	    		//사용자관리
	    		if(systemVO.getUserManageUseAt().equals("Y")){
		    		menuManageVO.setMenuOrdr(menuOrdr++);
		    		menuManageVO.setMenuNm(this.egovMessageSource.getMessage("gps.menu.userManage"));//링크메뉴명
		    		menuManageVO.setMenuUrl(this.egovMessageSource.getMessage("gps.menu.userManage.url"));//메뉴URL
		    		menuNo = (Integer)this.menuManageDAO.insertMenu(menuManageVO);
		    		menuNoList.add(menuNo);
	    		}
	    		
	    		//권한관리
	    		if(systemVO.getAuthorManageUseAt().equals("Y")){
		    		menuManageVO.setMenuOrdr(menuOrdr++);
		    		menuManageVO.setMenuNm(this.egovMessageSource.getMessage("gps.menu.unityAuthorManage"));//링크메뉴명
		    		menuManageVO.setMenuUrl(this.egovMessageSource.getMessage("gps.menu.unityAuthorManage.url"));//메뉴URL
		    		menuNo = (Integer)this.menuManageDAO.insertMenu(menuManageVO);
		    		menuNoList.add(menuNo);
	    		}
	    		
	    		//메뉴관리
	    		if(systemVO.getMenuManageUseAt().equals("Y")){
		    		menuManageVO.setMenuOrdr(menuOrdr++);
		    		menuManageVO.setMenuNm(this.egovMessageSource.getMessage("gps.menu.menuManage"));//링크메뉴명
		    		menuManageVO.setMenuUrl(this.egovMessageSource.getMessage("gps.menu.menuManage.url"));//메뉴URL
		    		menuNo = (Integer)this.menuManageDAO.insertMenu(menuManageVO);
		    		menuNoList.add(menuNo);
	    		}
	    		//프로그램관리
	    		if(systemVO.getProgramManageUseAt().equals("Y")){
		    		menuManageVO.setMenuOrdr(menuOrdr++);
		    		menuManageVO.setMenuNm(this.egovMessageSource.getMessage("gps.menu.programManage"));//링크메뉴명
		    		menuManageVO.setMenuUrl(this.egovMessageSource.getMessage("gps.menu.programManage.url"));//메뉴URL
		    		menuNo = (Integer)this.menuManageDAO.insertMenu(menuManageVO);
		    		menuNoList.add(menuNo);
	    		}
	    		
	    	}
			
			//컨텐츠관리메뉴생성
			if(contentManageFolderNo > 0){
				menuOrdr = 1;
				//컨텐츠관리 menuNo list에 추가
				menuNoList.add(contentManageFolderNo);
				//입력된 메뉴ID 조회
	    		menuManageVO.setMenuNo(contentManageFolderNo);
	    		contentManageFolderMenuId = (String)this.menuManageDAO.selectMenuId(menuManageVO);
	    		menuManageVO.setUpperMenuId(contentManageFolderMenuId);
	    		menuManageVO.setMenuTy("H");
	    		
	    		//메인이미지관리
	    		if(systemVO.getMainImageManageUseAt().equals("Y")){
		    		menuManageVO.setMenuOrdr(menuOrdr++);
		    		menuManageVO.setMenuNm(this.egovMessageSource.getMessage("gps.menu.mainImageManage"));//링크메뉴명
		    		menuManageVO.setMenuUrl(this.egovMessageSource.getMessage("gps.menu.mainImageManage.url"));//메뉴URL
		    		menuNo = (Integer)this.menuManageDAO.insertMenu(menuManageVO);
		    		menuNoList.add(menuNo);
	    		}
	    		
	    		
	    		//로그인이미지관리
	    		if(systemVO.getLoginImageManageUseAt().equals("Y")){
		    		menuManageVO.setMenuOrdr(menuOrdr++);
		    		menuManageVO.setMenuNm(this.egovMessageSource.getMessage("gps.menu.loginImageManage"));//링크메뉴명
		    		menuManageVO.setMenuUrl(this.egovMessageSource.getMessage("gps.menu.loginImageManage.url"));//메뉴URL
		    		menuNo = (Integer)this.menuManageDAO.insertMenu(menuManageVO);
		    		menuNoList.add(menuNo);
	    		}
	    		
	    		//게시판관리
	    		if(systemVO.getBoardManageUseAt().equals("Y")){
		    		menuManageVO.setMenuOrdr(menuOrdr++);
		    		menuManageVO.setMenuNm(this.egovMessageSource.getMessage("gps.menu.boardManage"));//링크메뉴명
		    		menuManageVO.setMenuUrl(this.egovMessageSource.getMessage("gps.menu.boardManage.url"));//메뉴URL
		    		menuNo = (Integer)this.menuManageDAO.insertMenu(menuManageVO);
		    		menuNoList.add(menuNo);
	    		}
	    		
	    		
	    		//추천사이트관리
	    		if(systemVO.getRecomendSiteManageUseAt().equals("Y")){
		    		menuManageVO.setMenuOrdr(menuOrdr++);
		    		menuManageVO.setMenuNm(this.egovMessageSource.getMessage("gps.menu.recomendSiteManage"));//링크메뉴명
		    		menuManageVO.setMenuUrl(this.egovMessageSource.getMessage("gps.menu.recomendSiteManage.url"));//메뉴URL
		    		menuNo = (Integer)this.menuManageDAO.insertMenu(menuManageVO);
		    		menuNoList.add(menuNo);
	    		}
	    		
	    		
	    		//약관관리
	    		if(systemVO.getStplatManageUseAt().equals("Y")){
		    		menuManageVO.setMenuOrdr(menuOrdr++);
		    		menuManageVO.setMenuNm(this.egovMessageSource.getMessage("gps.menu.stplatManage"));//링크메뉴명
		    		menuManageVO.setMenuUrl(this.egovMessageSource.getMessage("gps.menu.stplatManage.url"));//메뉴URL
		    		menuNo = (Integer)this.menuManageDAO.insertMenu(menuManageVO);
		    		menuNoList.add(menuNo);
	    		}
	    		
	    		
	    		//만족도조사
	    		if(systemVO.getSnstUseAt().equals("Y")){
		    		menuManageVO.setMenuOrdr(menuOrdr++);
		    		menuManageVO.setMenuNm(this.egovMessageSource.getMessage("gps.menu.snst"));//링크메뉴명
		    		menuManageVO.setMenuUrl(this.egovMessageSource.getMessage("gps.menu.snst.url"));//메뉴URL
		    		menuNo = (Integer)this.menuManageDAO.insertMenu(menuManageVO);
		    		menuNoList.add(menuNo);
	    		}
	    		
	    		
	    		//배너관리
	    		if(systemVO.getBannerManageUseAt().equals("Y")){
		    		menuManageVO.setMenuOrdr(menuOrdr++);
		    		menuManageVO.setMenuNm(this.egovMessageSource.getMessage("gps.menu.bannerManage"));//링크메뉴명
		    		menuManageVO.setMenuUrl(this.egovMessageSource.getMessage("gps.menu.bannerManage.url"));//메뉴URL
		    		menuNo = (Integer)this.menuManageDAO.insertMenu(menuManageVO);
		    		menuNoList.add(menuNo);
	    		}
	    		
	    		
	    		//설문조사
	    		if(systemVO.getQestnarUseAt().equals("Y")){
		    		menuManageVO.setMenuOrdr(menuOrdr++);
		    		menuManageVO.setMenuNm(this.egovMessageSource.getMessage("gps.menu.qestnar"));//링크메뉴명
		    		menuManageVO.setMenuUrl(this.egovMessageSource.getMessage("gps.menu.qestnar.url"));//메뉴URL
		    		menuNo = (Integer)this.menuManageDAO.insertMenu(menuManageVO);
		    		menuNoList.add(menuNo);
	    		}
	    		
	    		
	    		//스케줄관리
	    		if(systemVO.getSchdulManageUseAt().equals("Y")){
		    		menuManageVO.setMenuOrdr(menuOrdr++);
		    		menuManageVO.setMenuNm(this.egovMessageSource.getMessage("gps.menu.schdulManage"));//링크메뉴명
		    		menuManageVO.setMenuUrl(this.egovMessageSource.getMessage("gps.menu.schdulManage.url"));//메뉴URL
		    		menuNo = (Integer)this.menuManageDAO.insertMenu(menuManageVO);
		    		menuNoList.add(menuNo);
	    		}
	    		
	    		
	    		//이벤트관리
	    		if(systemVO. getEventManageUseAt().equals("Y")){
		    		menuManageVO.setMenuOrdr(menuOrdr++);
		    		menuManageVO.setMenuNm(this.egovMessageSource.getMessage("gps.menu.eventManage"));//링크메뉴명
		    		menuManageVO.setMenuUrl(this.egovMessageSource.getMessage("gps.menu.eventManage.url"));//메뉴URL
		    		menuNo = (Integer)this.menuManageDAO.insertMenu(menuManageVO);
		    		menuNoList.add(menuNo);
	    		}
	    		
	    		
	    		//접속통계
	    		if(systemVO. getConectStatsUseAt().equals("Y")){
		    		menuManageVO.setMenuOrdr(menuOrdr++);
		    		menuManageVO.setMenuNm(this.egovMessageSource.getMessage("gps.menu.conectStats"));//링크메뉴명
		    		menuManageVO.setMenuUrl(this.egovMessageSource.getMessage("gps.menu.conectStats.url"));//메뉴URL
		    		menuNo = (Integer)this.menuManageDAO.insertMenu(menuManageVO);
		    		menuNoList.add(menuNo);
	    		}
	    		
	    		
	    		//팝업관리
	    		if(systemVO. getPopupManageUseAt().equals("Y")){
		    		menuManageVO.setMenuOrdr(menuOrdr++);
		    		menuManageVO.setMenuNm(this.egovMessageSource.getMessage("gps.menu.popupManage"));//링크메뉴명
		    		menuManageVO.setMenuUrl(this.egovMessageSource.getMessage("gps.menu.popupManage.url"));//메뉴URL
		    		menuNo = (Integer)this.menuManageDAO.insertMenu(menuManageVO);
		    		menuNoList.add(menuNo);
	    		}

			}
			this.insertMenuAuthor(systemVO.getUsrId(), menuNoList);		//메뉴권한등록
			
			//사용여부변경시 하위메뉴 사용여부 변경
			menuManageVO.setUseAt(systemVO.getUseAt());
			menuManageVO.setUpperMenuId(resultVO.getSysId());
			this.menuManageDAO.updateUseAtSystemWithMenu(menuManageVO);
			
		}
		return sysId;
	}
	
	
	/**
	 * 입력된메뉴 권한등록처리
	 * @param usrId
	 * @param List<MenuManageVO> menuNoList
     * @throws Exception
     * @see TABLE NAME : TN_MENU_AUTHOR 
     */
	public void insertMenuAuthor(String usrId , List<Integer> menuNoList)throws Exception{
		if(menuNoList.size() > 0){
			for(int i = 0;i < menuNoList.size();i++){
				MenuManageVO menuManageVO = new MenuManageVO();
				menuManageVO.setMenuNo(menuNoList.get(i));
				menuManageVO.setUsrId(usrId);
				this.menuManageDAO.insertMenuAuthor(menuManageVO);
			}
		}
	}
	
	/**
	 * 권한별메뉴목록
	 * @param menuManageVO
	 * @return List
	 * @throws Exception
	 * @see menuLv,menuNo,menuNm,menuTy,leaf,ulOpenAt,endTagCnt,menuAuthorAt
	 * @see TABLE NAME : TN_MENU,TN_MENU_AUTHOR
	 */
	public List<MenuManageVO> selectAuthorMenuList(MenuManageVO menuManageVO)throws Exception {
		return (List<MenuManageVO>)this.menuManageDAO.selectAuthorMenuList(menuManageVO);
	}
	
	
	/**
	 * 게시판관리 적용페이지 메뉴목록
	 * @param menuManageVO
	 * @return
	 * @throws Exception
	 * @see menuLv,menuId,menuNm,menuTy,leaf,ulOpenAt,endTagCnt
	 * @see TABLE NAME : TN_SYSTEM,TN_MENU
	 */
	public List<MenuManageVO> selectSubpageMenuList(MenuManageVO menuManageVO)throws Exception {
		return (List<MenuManageVO>)this.menuManageDAO.selectSubpageMenuList(menuManageVO);
	}
	
	/**
	 * 메뉴형태가 게시판일때 다른메뉴에서 사용중인지 검색
	 * @param menuManageVO
	 * @return MenuManageVO
	 * @throws Exception
	 * @see bbsId
	 * @see TABLE NAME : TN_MENU
	 */
	public MenuManageVO selectSameBbsId(MenuManageVO menuManageVO)throws Exception {
		return (MenuManageVO)this.menuManageDAO.selectSameBbsId(menuManageVO);
	}
	
	/**
	 * 메뉴 수정 처리
	 * @param menuManageVO 메뉴VO
     * @throws Exception
     * @see TABLE NAME : TN_MENU
     */
	public void registCsnstId(MenuManageVO menuManageVO)throws Exception {
		this.menuManageDAO.registCsnstId(menuManageVO);
	}
	
	/**
	 * 사이트맵목록
	 * @param menuManageVO
	 * @return List
	 * @throws Exception
	 * @see nextMenuLv,menuLv,leaf,menuId,upperMenuId,menuNm,menuUrl,menuTy,sitemapImageMask,sitemapImageCm,
	 * @see sitemapImageMime,sitemapImageSize,sitemapImageMouseOverMask,sitemapImageMouseOverCm,sitemapImageMouseOverMime,
	 * @see sitemapImageMouseOverSize,bbsId
	 * @see TABLE NAME : TN_MENU
	 */
	public List<MenuManageVO> selectSitemap(MenuManageVO menuManageVO)throws Exception {
		return (List<MenuManageVO>)this.menuManageDAO.selectSitemap(menuManageVO);
	}
}
