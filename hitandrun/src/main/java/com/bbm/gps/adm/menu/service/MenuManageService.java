package com.bbm.gps.adm.menu.service;

import com.bbm.gps.adm.author.intergrated.service.IgrAuthorManageVO;
import com.bbm.gps.login.service.GpsSessionVO;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 메뉴관리에 대한 서비스 인터페이스 클래스를 정의한다. 
 * <p><b>NOTE:</b>해당 클래스에는 controller에서 호출하는 메소드들의 인터페이스가 정의되어 있으며 
 * 구현은 MenuManageServiceImpl에 되어있다
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

public interface MenuManageService {
	
	/**
	 * 페이지Navigation
	 * @param menuManageVO 메뉴VO
     * @throws Exception
     */
    public List<MenuManageVO> selectNavi(MenuManageVO menuManageVO)throws Exception;

	/**
	 * 포털 사용자페이지 top 메뉴
	 * @param menuManageVO
	 * @return List<MenuManageVO> 메뉴목록
     * @throws Exception
     */
	public List<MenuManageVO> selectUserPageLeftMenuList(MenuManageVO menuManageVO)throws Exception;
	
	/**
	 * 포털 사용자페이지 top 메뉴
	 * @param menuManageVO
	 * @return List<MenuManageVO> 메뉴목록
     * @throws Exception
     */
	public List<MenuManageVO> selectUserPageTopMenuList(MenuManageVO menuManageVO)throws Exception;
	
	
	/**
	 * 시스템(사이트)목록상세조회
	 * @param  SystemVO 메뉴VO
	 * @return SystemVO
     * @throws Exception
     */
	public SystemVO selectSystem(SystemVO systemVO)throws Exception;
	
	/**
	 * 조사목록
	 * @return List
	 * @throws Exception
	 * @see prdctnId,svyOdr,svyNm
	 * @see TABLE NAME : TN_PRDCTN_ODR
	 */
	public List<SystemVO> selectPrdctnList(SystemVO systemVO)throws Exception;
	
	/**
	 * 시스템(사이트)등록
	 * @param  SystemVO 메뉴VO
     * @throws Exception
     */
	public void insertSystem(SystemVO systemVO,IgrAuthorManageVO igrAuthorManageVO)throws Exception;
	
	/**
	 * 시스템(사이트)수정
	 * @param  SystemVO 메뉴VO
     * @throws Exception
     */
	public void updateSystem(SystemVO systemVO)throws Exception;
	
	/**
	 * 시스템(사이트)삭제여부 변경 처리
	 * @param  SystemVO 메뉴VO
     * @throws Exception
     */
	public void updateDeleteAtSystem(SystemVO systemVO)throws Exception;
	
	/**
	 * 메뉴목록
	 * @param menuManageVO 메뉴목록을 조회할 조건
	 * @return List<MenuManageVO> 메뉴목록
     * @throws Exception
     */
	public List<MenuManageVO> selectMenuList(MenuManageVO menuManageVO)throws Exception;
	
	/**
	 * TOP페이지메뉴목록
	 * @param menuManageVO 메뉴VO
	 * @return List<MenuManageVO> 메뉴목록
     * @throws Exception
     */
	public List<MenuManageVO> selectTopMenuListAdm(GpsSessionVO gpsSessionVO)throws Exception;
	
	
	/**
	 * TOP페이지메뉴목록
	 * @param menuManageVO 메뉴VO
	 * @return List<MenuManageVO> 메뉴목록
     * @throws Exception
     */
	public List<MenuManageVO> selectTopMenuList(GpsSessionVO gpsSessionVO)throws Exception;
	
	/**
	 * LEFT페이지메뉴목록
	 * @param menuManageVO 메뉴VO
	 * @return List<MenuManageVO> 메뉴목록
     * @throws Exception
     */
	public List<MenuManageVO> selectLeftMenuList(MenuManageVO menuManageVO)throws Exception;
	
	/**
	 * 메뉴 상세 조회
	 * @param menuManageVO 메뉴VO
	 * @return MenuManageVO 메뉴 상세정보
     * @throws Exception
     */
	public MenuManageVO selectMenu(MenuManageVO menuManageVO)throws Exception;
	
	/**
	 * 메뉴 등록 처리
	 * @param menuManageVO 메뉴VO
	 * @param multiRequest 첨부파일
     * @throws Exception
     */
	public void insertMenu(MenuManageVO menuManageVO,MultipartHttpServletRequest multiRequest)throws Exception;
	
	/**
	 * 메뉴 수정 처리
	 * @param menuManageVO 메뉴VO
	 * @param multiRequest 첨부파일
     * @throws Exception
     */
	public void updateMenu(MenuManageVO menuManageVO,MultipartHttpServletRequest multiRequest)throws Exception;
	
	/**
	 * 메뉴 삭제 처리
	 * @param menuManageVO 메뉴VO
     * @throws Exception
     */
	public void deleteMenu(MenuManageVO menuManageVO)throws Exception;
	
	/** 
	 * 메뉴이미지삭제처리
	 * @param menuManageVO 메뉴VO
	 * @throws Exception
	 */ 
	public void deleteImgFile(MenuManageVO menuManageVO)throws Exception;
	
	
	/**
	 * 권한별메뉴목록
	 * @param menuManageVO 메뉴VO
     * @throws Exception
     */
	public List<MenuManageVO> selectAuthorMenuList(MenuManageVO menuManageVO)throws Exception;
	
	/**
	 * 게시판관리 적용페이지 메뉴목록
	 * @param menuManageVO 메뉴VO
     * @throws Exception
     */
	public List<MenuManageVO> selectSubpageMenuList(MenuManageVO menuManageVO)throws Exception;
	
	/**
	 * 메뉴형태가 게시판일때 다른메뉴에서 사용중인지 검색
	 * @param menuManageVO 메뉴VO
     * @throws Exception
     */
	public MenuManageVO selectSameBbsId(MenuManageVO menuManageVO)throws Exception;
	
	/**
	 * 메뉴수정
	 * @param menuManageVO 메뉴VO
     * @throws Exception
     */
	public void registCsnstId(MenuManageVO menuManageVO)throws Exception;
	
	/**
	 * 사이트맵목록
	 * @param menuManageVO 메뉴VO
	 * @return List
     * @throws Exception
     */
	public List<MenuManageVO> selectSitemap(MenuManageVO menuManageVO)throws Exception;
}
