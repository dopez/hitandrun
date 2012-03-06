package com.bbm.gps.adm.menu.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.GpsAbstractDAO;
import com.bbm.gps.adm.menu.service.MenuManageVO;
import com.bbm.gps.adm.menu.service.SystemVO;
import com.bbm.gps.cmm.service.GpsDefaultVO;
import com.bbm.gps.login.service.GpsSessionVO;

/**
 * 메뉴관리 DAO CLASS
 * <p><b>NOTE:</b> DB에 직접 접근하며 쿼리문에 적용할 parameter를 보내주거나 단순 쿼리 실행을 하도록 호출한다
 * select, update, delete 함수를 사용하며 쿼리아이디와 parameter를 넘긴다
 * @author 통계포털 황기연
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

@Repository("menuManageDAO")
public class MenuManageDAO extends GpsAbstractDAO{

	/**
	 * 페이지Navigation
	 * @param menuManageVO
	 * @return List
	 * @throws Exception
	 * @see menuLevel,menuNm,menuId,upperMenuId,menuUrl
	 * @see TABLE NAME : TN_MENU
	 */
	@SuppressWarnings("unchecked")
	public List<MenuManageVO> selectNavi(MenuManageVO menuManageVO)throws Exception {
		return (List<MenuManageVO>)super.list("menuManageDAO.selectNavi",menuManageVO);
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
	@SuppressWarnings("unchecked")
	public List<MenuManageVO> selectUserPageLeftMenuList(MenuManageVO menuManageVO)throws Exception {
		return (List<MenuManageVO>)super.list("menuManageDAO.selectUserPageLeftMenuList",menuManageVO);
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
	@SuppressWarnings("unchecked")
	public List<MenuManageVO> selectUserPageTopMenuList(MenuManageVO menuManageVO)throws Exception {
		return (List<MenuManageVO>)super.list("menuManageDAO.selectUserPageTopMenuList",menuManageVO);
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
		 return (SystemVO)super.selectByPk("menuManageDAO.selectSystem",systemVO);
	}
	
	/**
	 * 조사목록
	 * @return List
	 * @throws Exception
	 * @see prdctnId,svyOdr,svyNm
	 * @see TABLE NAME : TN_PRDCTN_ODR
	 */
	@SuppressWarnings("unchecked")
	public List<SystemVO> selectPrdctnList(SystemVO systemVO)throws Exception {
		return (List<SystemVO>)super.list("menuManageDAO.selectPrdctnList",systemVO);
	}
	
	/**
	 * 시스템(사이트)등록
	 * @param systemVO
	 * @return
	 * @throws Exception
	 * @see TABLE NAME : TN_SYSTEM
	 */
	public int insertSystem(SystemVO systemVO)throws Exception {
		return (Integer)super.insert("menuManageDAO.insertSystem",systemVO);
	}
	
	/**
	 * 시스템(사이트)수정
	 * @param systemVO
	 * @return
	 * @throws Exception
	 * @see TABLE NAME : TN_SYSTEM
	 */
	public int updateSystem(SystemVO systemVO)throws Exception {
		return (Integer)super.update("menuManageDAO.updateSystem",systemVO);
	}
	
	/**
	 * 등록된 시스템 정보
	 * @param SystemVO 시스템메뉴VO
	 * @return
	 * @throws Exception
	 * @see sysId,sysNm
	 * @see TABLE NAME : TN_SYSTEM
	 */
	public SystemVO selectSystemInfo(SystemVO systemVO)throws Exception {
		 return (SystemVO)super.selectByPk("menuManageDAO.selectSystemInfo",systemVO);
	}
	
	/**
	 * 시스템(사이트)삭제 여부 변경
	 * @param systemVO
	 * @return
	 * @throws Exception
	 * @see TABLE NAME : TN_SYSTEM 
	 */
	public int updateDeleteAtSystem(SystemVO systemVO)throws Exception {
		 return (Integer)super.update("menuManageDAO.updateDeleteAtSystem",systemVO);
	}
	
	/**
	 * 시스템(사이트)삭제시 하위메뉴 삭제 여부 변경
	 * @param menuManageVO
	 * @return
	 * @throws Exception
	 * @see TABLE NAME : TN_MENU
	 */
	public int updateDeleteAtSystemWithMenu(MenuManageVO menuManageVO)throws Exception {
		 return (Integer)super.update("menuManageDAO.updateDeleteAtSystemWithMenu",menuManageVO);
	}

	/**
	 * 시스템(사이트)삭제처리
	 * @param systemVO
	 * @return
	 * @throws Exception
	 * @see TABLE NAME :  TN_SYSTEM
	 */
	public int deleteSystem(SystemVO systemVO)throws Exception {
		 return (Integer)super.delete("menuManageDAO.deleteSystem",systemVO);
	}
	
	/**
	 * 시스템(사이트)삭제시 하위메뉴 삭제처리
	 * @param menuManageVO
	 * @return
	 * @throws Exception
	 * @see TABLE NAME : TN_MENU
	 */
	public int deleteSystemWithMenu(MenuManageVO menuManageVO)throws Exception {
		 return (Integer)super.delete("menuManageDAO.deleteSystemWithMenu",menuManageVO);
	}
	
	/**
	 * 시스템(사이트)삭제시 권한삭제
	 * @param menuManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_AUTHOR
	 */
	public void deleteSystemWithAuthor(MenuManageVO menuManageVO)throws Exception {
		 super.delete("menuManageDAO.deleteSystemWithAuthor",menuManageVO);
	}
	
	/**
	 * 시스템(사이트)수정시 관리메뉴 권한만 삭제
	 * @param menuManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_MENU_AUTHOR
	 */
	public void deleteSystemWithManageMenuAuthor(MenuManageVO menuManageVO)throws Exception {
		 super.delete("menuManageDAO.deleteSystemWithManageMenuAuthor",menuManageVO);
	}
	
	/**
	 * 시스템(사이트)삭제시 메뉴권한삭제
	 * @param menuManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_MENU_AUTHOR
	 */
	public void deleteSystemWithMenuAuthor(MenuManageVO menuManageVO)throws Exception {
		 super.delete("menuManageDAO.deleteSystemWithMenuAuthor",menuManageVO);
	}

	/**
	 * 시스템 사용여부 변경시 하위메뉴 사용여부 변경
	 * @param menuManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_MENU
	 */
	public void updateUseAtSystemWithMenu(MenuManageVO menuManageVO)throws Exception {
		super.update("menuManageDAO.updateUseAtSystemWithMenu",menuManageVO);
	}

	/**
	 * 메뉴 목록
	 * @param menuManageVO
	 * @return List 메뉴목록
	 * @throws Exception
	 * @see rootSysId,upperSysId,menuId,menuNm,menuTy,sysSe,useAt,leaf,ulOpenAt,endTagCnt
	 * @see TABLE NAME : TN_MENU
	 */
	@SuppressWarnings("unchecked")
	public List<MenuManageVO> selectMenuList(MenuManageVO menuManageVO)throws Exception {
		return (List<MenuManageVO>)super.list("menuManageDAO.selectMenuList",menuManageVO);
	}

	/**
	 * TOP페이지메뉴목록
	 * @param gpsSessionVO
	 * @return
	 * @throws Exception
	 * @see menuId,menuNm
	 * @see TABLE NAME : TN_SYSTEM,TN_MENU,TN_USER_AUTHOR,TN_MENU_AUTHOR,TN_AUTHOR
	 */
	@SuppressWarnings("unchecked")
	public List<MenuManageVO> selectTopMenuListAdm(GpsSessionVO gpsSessionVO)throws Exception {
		return (List<MenuManageVO>)super.list("menuManageDAO.selectTopMenuListAdm",gpsSessionVO);
	}
	

	/**
	 * TOP페이지메뉴목록
	 * @param gpsSessionVO
	 * @return
	 * @throws Exception
	 * @see menuId,menuNm
	 * @see TABLE NAME : TN_SYSTEM,TN_MENU,TN_USER_AUTHOR,TN_MENU_AUTHOR,TN_AUTHOR
	 */
	@SuppressWarnings("unchecked")
	public List<MenuManageVO> selectTopMenuList(GpsSessionVO gpsSessionVO)throws Exception {
		return (List<MenuManageVO>)super.list("menuManageDAO.selectTopMenuList",gpsSessionVO);
	}
	
	/**
	 * LEFT페이지메뉴목록
	 * @param menuManageVO
	 * @return List
	 * @throws Exception
	 * @see menuId,upperMenuId,menuNo,menuOrdr,menuNm,menuUrl,menuTy,sysUseTy,bbsId,ulOpenAt,endTagCnt
	 * @see TABLE NAME : TN_SYSTEM,TN_MENU
	 */
	@SuppressWarnings("unchecked")
	public List<MenuManageVO> selectLeftMenuList(MenuManageVO menuManageVO)throws Exception {
		return (List<MenuManageVO>)super.list("menuManageDAO.selectLeftMenuList",menuManageVO);
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
		return (MenuManageVO)super.selectByPk("menuManageDAO.selectMenu",menuManageVO);
	}

	/**
	 * 메뉴 등록 처리
	 * @param menuManageVO
	 * @return
	 * @throws Exception
	 * @see TABLE NAME : TN_MENU
	 */
	public int insertMenu(MenuManageVO menuManageVO)throws Exception {
		return (Integer)super.insert("menuManageDAO.insertMenu",menuManageVO);
	}
	
	/**
	 * 메뉴 등록 처리 후 MENU_ID 값
	 * @param menuManageVO
	 * @return String menuId
	 * @throws Exception
	 * @see TABLE NAME : TN_MENU
	 */
	public String selectMenuId(MenuManageVO menuManageVO)throws Exception {
		return (String)super.selectByPk("menuManageDAO.selectMenuId",menuManageVO);
	}
	
	/**
	 * 메뉴 등록 후 메뉴 권한등록 처리
	 * @param gpsDefaultVO
	 * @throws Exception
	 * @see TABLE NAME : TN_MENU_AUTHOR 
	 */
	public void insertMenuAuthor(GpsDefaultVO gpsDefaultVO)throws Exception {
		super.insert("menuManageDAO.insertMenuAuthor",gpsDefaultVO);
	}
	
	/**
	 * 메뉴 수정 처리
	 * @param menuManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_MENU
	 */
	public void updateMenu(MenuManageVO menuManageVO)throws Exception {
		super.update("menuManageDAO.updateMenu",menuManageVO);
	}
	
	/**
	 * 상위메뉴 전체사용여부 변경시 하위메뉴 사용여부변경
	 * @param menuManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_MENU
	 */
	public void updateMenuUseAt(MenuManageVO menuManageVO)throws Exception {
		super.update("menuManageDAO.updateMenuUseAt",menuManageVO);
	}
	
	/**
	 * 메뉴 삭제 처리
	 * @param menuManageVO
	 * @return int
	 * @throws Exception
	 * @see TABLE NAME : TN_MENU
	 */
	public int deleteMenu(MenuManageVO menuManageVO)throws Exception {
		return (Integer)super.update("menuManageDAO.deleteMenu",menuManageVO);
	}
	
	/**
	 * 메뉴 삭제시 메뉴권한 삭제 처리
	 * @param menuManageVO
	 * @return
	 * @throws Exception
	 * @see TABLE NAME : TN_MENU_AUTHOR
	 */
	public int deleteMenuWithMenuAuthor(MenuManageVO menuManageVO)throws Exception {
		return (Integer)super.delete("menuManageDAO.deleteMenuWithMenuAuthor",menuManageVO);
	}
	
	/**
	 * 메뉴이미지삭제처리
	 * @param menuManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_MENU
	 */
	public void deleteImgFile(MenuManageVO menuManageVO)throws Exception {
		super.update("menuManageDAO.deleteImgFile",menuManageVO);
	}
	
	/**
	 * 권한별메뉴목록
	 * @param menuManageVO
	 * @return List
	 * @throws Exception
	 * @see menuLv,menuNo,menuNm,menuTy,leaf,ulOpenAt,endTagCnt,menuAuthorAt
	 * @see TABLE NAME : TN_MENU,TN_MENU_AUTHOR
	 */
	@SuppressWarnings("unchecked")
	public List<MenuManageVO> selectAuthorMenuList(MenuManageVO menuManageVO)throws Exception {
		return (List<MenuManageVO>)super.list("menuManageDAO.selectAuthorMenuList",menuManageVO);
	}
	
	/**
	 * 게시판관리 적용페이지 메뉴목록
	 * @param menuManageVO
	 * @return
	 * @throws Exception
	 * @see menuLv,menuId,menuNm,menuTy,leaf,ulOpenAt,endTagCnt
	 * @see TABLE NAME : TN_SYSTEM,TN_MENU
	 */
	@SuppressWarnings("unchecked")
	public List<MenuManageVO> selectSubpageMenuList(MenuManageVO menuManageVO)throws Exception {
		return (List<MenuManageVO>)super.list("menuManageDAO.selectSubpageMenuList",menuManageVO);
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
		return (MenuManageVO)super.selectByPk("menuManageDAO.selectSameBbsId",menuManageVO);
	}
	
	/**
	 * 메뉴형태가 게시판이면 해당게시판의 적용페이지 ID 수정
	 * @param menuManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_BBS
	 */
	public void updateBbsSubpageId(MenuManageVO menuManageVO)throws Exception {
		super.update("menuManageDAO.updateBbsSubpageId",menuManageVO);
	}

	/**
	 * 메뉴 수정 처리
	 * @param menuManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_MENU
	 */
	public void registCsnstId(MenuManageVO menuManageVO)throws Exception {
		super.update("menuManageDAO.registCsnstId",menuManageVO);
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
	@SuppressWarnings("unchecked")
	public List<MenuManageVO> selectSitemap(MenuManageVO menuManageVO)throws Exception {
		return (List<MenuManageVO>)super.list("menuManageDAO.selectSitemap",menuManageVO);
	}
	
}
