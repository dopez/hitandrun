package com.bbm.gps.adm.image.service;

import java.util.List;

/** 
 * 이미지관리에 대한 서비스 인터페이스 클래스를 정의한다. 
 * <p><b>NOTE:</b> 이미지관리 controller에서 호출하는 메소드들의 인터페이스가 정의되어 있으며 
 * 구현은 ImageManageServiceImpl에 되어있다
 * @author 포탈통계 이관형 
 * @since 2011.10.10 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.10.10     이관형      최초 생성 
 * 
 * </pre> 
 */
public interface ImageManageService {

	/**
	 * 이미지삭제
	 * @param imageManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_IMAGE
	 */
	void deleteImage(ImageManageVO imageManageVO) throws Exception;

	/**
	 * 이미지등록
	 * @param imageManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_IMAGE
	 */
	void insertImage(ImageManageVO imageManageVO) throws Exception;

	/**
	 * 이미지수정
	 * @param imageManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_IMAGE
	 */
	void updateImage(ImageManageVO imageManageVO) throws Exception;

	/**
	 * 이미지 상세 조회
	 * @param imageManageVO
	 * @return
	 * @throws Exception
	 * @see IMAGE_ID,IMAGE_SN,IMAGE_SE,IMAGE_NM,IMAGE_FILE_NM,IMAGE_FILE_MASK,IMAGE_FILE_SIZE,IMAGE_FILE_MIME,IMAGE_WIDTH,
	 * @see IMAGE_HEIGHT,IMAGE_REFLCT_AT,IMAGE_REFLCT_URL,SYS_ID,SYS_NM,REGISTER_IP,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID
	 * @see TABLE NAME : TN_IMAGE,TN_SYSTEM
	 */
	ImageManageVO selectImage(ImageManageVO imageManageVO) throws Exception;
	
	/**
     * 이미지 목록 조회
     * @param imageManageVO
     * @return List
     * @throws Exception
     * @see IMAGE_ID,IMAGE_SN,IMAGE_SE,IMAGE_NM,IMAGE_FILE_NM,IMAGE_FILE_MASK,IMAGE_FILE_SIZE,IMAGE_FILE_MIME,IMAGE_WIDTH,
	 * @see IMAGE_HEIGHT,IMAGE_REFLCT_AT,IMAGE_REFLCT_URL,SYS_ID,SYS_NM,REGISTER_IP,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID
	 * @see TABLE NAME : TN_IMAGE,TN_SYSTEM
     */
	@SuppressWarnings("unchecked")
	List selectImageList(ImageManageVO imageManageVO) throws Exception;
	
	/** 
	 * 이미지 목록 총 갯수
	 * @param imageManageVO 총 갯수를 조회할 프로그램VO
	 * @return int
	 * @throws Exception 
     * @see IMAGE_ID,IMAGE_SN,IMAGE_SE,IMAGE_NM,IMAGE_FILE_NM,IMAGE_FILE_MASK,IMAGE_FILE_SIZE,IMAGE_FILE_MIME,IMAGE_WIDTH,
	 * @see IMAGE_HEIGHT,IMAGE_REFLCT_AT,IMAGE_REFLCT_URL,SYS_ID,SYS_NM,REGISTER_IP,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID
	 * @see TABLE NAME : TN_IMAGE,TN_SYSTEM
	 */  
    int selectImageListTotCnt(ImageManageVO imageManageVO) throws Exception;
    
    /** 
	 * 이미지 SN 생성
	 * @return int
	 * @throws Exception 
	 * @see TABLE NAME : TN_IMAGE
	 */
    int imageSnGeneration() throws Exception;
}
