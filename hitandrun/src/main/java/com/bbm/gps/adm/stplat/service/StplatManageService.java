package com.bbm.gps.adm.stplat.service;

import java.util.List;

/** 
 * 약관관리에 대한 서비스 인터페이스 클래스를 정의한다. 
 * <p><b>NOTE:</b> 해당 클래스에는 controller에서 호출하는 메소드들의 인터페이스가 정의되어 있으며 
 * 구현은 StplatManageServiceImpl에 되어있다
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

public interface StplatManageService {
    
	/**
     * 약관목록조회(stplatManageVO 검색조건에따라 약관목록을 조회)  
     * @param stplatManageVO
     * @return List 조회한 약관목록의 리스트
     * @throws Exception
     * @see stplatSe,stplatSeNm,stplatId,sysId,sysNm,stplatNm,stplatCn,stplatUseSe
     * @see TABLE NAME : TN_STPLAT
     */
	@SuppressWarnings("unchecked")
	public List selectStplatList(StplatManageVO stplatManageVO) throws Exception;

	/**
     * 약관목록의 총 갯수
     * @param stplatManageVO
     * @return int 조회한 목록의 리스트
     * @throws Exception
     * @see COUNT(*) totcnt 약관목록 총 갯수
     * @see TABLE NAME : TN_STPLAT
     */
	public int selectStplatListTotCnt(StplatManageVO stplatManageVO) throws Exception;
	
	/**
	 * 약관등록 처리(stplatManageVO에 담겨있는 항목을 DB에 등록) 
	 * @param stplatManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_STPLAT
	 */
	public void insertStplat(StplatManageVO stplatManageVO) throws Exception;
	
	/**
	 * 약관목록수정처리
	 * @param stplatManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_STPLAT
	 */
	public void updateStplat(StplatManageVO stplatManageVO) throws Exception;
	
	/**
	 * 약관목록 상세
	 * @param stplatManageVO
	 * @return StplatManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_STPLAT
	 */
	public StplatManageVO selectStplat(StplatManageVO stplatManageVO) throws Exception;
	
	/**
     * 약관사용여부수정
     * @param stplatManageVO
     * @throws Exception
     * @see TABLE NAME : TN_STPLAT
     */
	public void updateStplatActvtyAt(StplatManageVO stplatManageVO) throws Exception;
	
	/**
	 * 약관목록 ID에 대한 행삭제(약관삭제) 
	 * @param stplatManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_STPLAT
	 */
	public void deleteStplat(StplatManageVO stplatManageVO) throws Exception;


}
