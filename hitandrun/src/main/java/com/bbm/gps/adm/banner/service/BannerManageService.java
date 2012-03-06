package com.bbm.gps.adm.banner.service;

import java.util.List;
import java.util.Map;

/** 
 * 배너관리에 대한 서비스 인터페이스 클래스를 정의한다. 
 * <p><b>NOTE:</b> 해당 클래스에는 controller에서 호출하는 메소드들의 인터페이스가 정의되어 있으며 
 * 구현은 BannerManageServiceImpl에 되어있다
 * @author 범정부통계포털 이관형 
 * @since 2011.06.27 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.06.27     이관형      최초 생성 
 * 
 * </pre> 
 */

public interface BannerManageService {
    
	/**
	 * 배너목록 ID에 대한 행삭제(배너삭제) 
	 * @param bannerManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_BANNER
	 */
	public void deleteBanner(BannerManageVO bannerManageVO) throws Exception;

	/**
	 * 배너등록 처리(bannerManageVO에 담겨있는 항목을 DB에 등록) 
	 * @param bannerManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_BANNER
	 */
	public void insertBanner(BannerManageVO bannerManageVO) throws Exception;

	/**
	 * 배너수정 처리(bannerManageVO에 담겨있는 항목을 DB에 등록) 
	 * @param bannerManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_BANNER
	 */
	public void updateBanner(BannerManageVO bannerManageVO) throws Exception;

	/**
	 * 배너목록 상세
	 * @param bannerManageVO
	 * @return
	 * @throws Exception
	 * @see BANNER_SN,SYS_ID,SYS_NM,LC,ORDR,NM,ACTVTY_AT,ENDDE,LOGO_IMAGE_FILE_NM,
	 * @see LOGO_IMAGE_FILE_MASK,LOGO_IMAGE_FILE_SIZE,LOGO_IMAGE_FILE_MIME,LOGO_IMAGE_WIDTH,
	 * @see LOGO_IMAGE_HEIGHT,OVER_IMAGE_FILE_NM,OVER_IMAGE_FILE_MASK,OVER_IMAGE_FILE_SIZE,
	 * @see OVER_IMAGE_WIDTH,OVER_IMAGE_HEIGHT,OVER_IMAGE_FILE_MIME,IMAGE_ALT,URL,URL_TRGET,DC,
	 * @see TRGET,REGISTER_ID,REGISTER_IP,REGIST_DT,UPDT_DT,UPDTUSR_ID
	 * @see TABLE NAME : TN_BANNER,TN_SYSTEM
	 */
	public BannerManageVO selectBanner(BannerManageVO bannerManageVO) throws Exception;

	/**
     * 배너목록조회(bannerManageVO 검색조건에따라 배너목록을 조회) 
     * @param bannerManageVO
     * @return List
     * @throws Exception
     * @see BANNER_SN,SYS_ID,SYS_NM,LC,ORDR,NM,ACTVTY_AT,ENDDE,LOGO_IMAGE_FILE_NM,
	 * @see LOGO_IMAGE_FILE_MASK,LOGO_IMAGE_FILE_SIZE,LOGO_IMAGE_FILE_MIME,LOGO_IMAGE_WIDTH,
	 * @see LOGO_IMAGE_HEIGHT,OVER_IMAGE_FILE_NM,OVER_IMAGE_FILE_MASK,OVER_IMAGE_FILE_SIZE,
	 * @see OVER_IMAGE_WIDTH,OVER_IMAGE_HEIGHT,OVER_IMAGE_FILE_MIME,IMAGE_ALT,URL,URL_TRGET,DC,
	 * @see TRGET,REGISTER_ID,REGISTER_IP,REGIST_DT,UPDT_DT,UPDTUSR_ID
	 * @see TABLE NAME : TN_BANNER,TN_SYSTEM
     */
	@SuppressWarnings("unchecked")
	public List selectBannerList(BannerManageVO bannerManageVO) throws Exception;

	/**
     * 배너목록의 총 갯수
     * @param bannerManageVO
     * @return int
     * @throws Exception
     * @see BANNER_SN,SYS_ID,SYS_NM,LC,ORDR,NM,ACTVTY_AT,ENDDE,LOGO_IMAGE_FILE_NM,
	 * @see LOGO_IMAGE_FILE_MASK,LOGO_IMAGE_FILE_SIZE,LOGO_IMAGE_FILE_MIME,LOGO_IMAGE_WIDTH,
	 * @see LOGO_IMAGE_HEIGHT,OVER_IMAGE_FILE_NM,OVER_IMAGE_FILE_MASK,OVER_IMAGE_FILE_SIZE,
	 * @see OVER_IMAGE_WIDTH,OVER_IMAGE_HEIGHT,OVER_IMAGE_FILE_MIME,IMAGE_ALT,URL,URL_TRGET,DC,
	 * @see TRGET,REGISTER_ID,REGISTER_IP,REGIST_DT,UPDT_DT,UPDTUSR_ID
	 * @see TABLE NAME : TN_BANNER,TN_SYSTEM
     */
	public int selectBannerListTotCnt(BannerManageVO bannerManageVO) throws Exception;

	/** 
     * 배너사용여부수정
     * @param bannerManageVO 배너정보를담고있는VO
     * @throws Exception 
	 * @see TABLE NAME : TN_BANNER
     */ 
	public void updateBannerActvtyAt(BannerManageVO bannerManageVO) throws Exception;

	/**
	 * 순서변경시 배너 목록을 가져온다
	 * @param bannerManageVO
	 * @return comboMap
	 * @see TABLE NAME : TN_BANNER
	 */
	@SuppressWarnings("unchecked")
	public Map selectBannerOrderList(BannerManageVO bannerManageVO) throws Exception;

	/**
	 * 배너순서수정처리(수정할 대상 게시물ID에 해당하는 행을 업데이트)
	 * @param bannerManageVO
	 * @return comboMap
	 * @see TABLE NAME : TN_BANNER
	 */
	public void updateBannerOrder(BannerManageVO bannerManageVO) throws Exception;
	
	/**
     * 메인페이지 배너목록
     * @param bannerManageVO
     * @return
     * @throws Exception
     * @see BANNER_SN,SYS_ID,SYS_NM,LC,ORDR,NM,ACTVTY_AT,ENDDE,LOGO_IMAGE_FILE_NM,
	 * @see LOGO_IMAGE_FILE_MASK,LOGO_IMAGE_FILE_SIZE,LOGO_IMAGE_FILE_MIME,LOGO_IMAGE_WIDTH,
	 * @see LOGO_IMAGE_HEIGHT,OVER_IMAGE_FILE_NM,OVER_IMAGE_FILE_MASK,OVER_IMAGE_FILE_SIZE,
	 * @see OVER_IMAGE_WIDTH,OVER_IMAGE_HEIGHT,OVER_IMAGE_FILE_MIME,IMAGE_ALT,URL,URL_TRGET,DC,
	 * @see TRGET,REGISTER_ID,REGISTER_IP,REGIST_DT,UPDT_DT,UPDTUSR_ID
	 * @see TABLE NAME : TN_BANNER,TN_SYSTEM
     */
	@SuppressWarnings("unchecked")
	public List selectMainBannerList(BannerManageVO bannerManageVO) throws Exception;
}
