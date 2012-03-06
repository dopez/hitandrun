package com.bbm.gps.adm.image.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import com.bbm.gps.adm.image.service.ImageManageService;
import com.bbm.gps.adm.image.service.ImageManageVO;
 
/** 
 * 이미지관리에 대한 서비스 구현클래스를 정의한다
 * <p><b>NOTE:</b> 이미지관리서비스에 선언 되어있는 메소드들의 구현 클래스로 프로그램관리테이블 데이터 접근 클래스의 메소드를 호출한다
 * 메소드들 중에는 parameter를 넘기는 메소드도 있고 넘기지 않는 메소드도 존재한다
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
@Service("imageManageService")
public class ImageManageServiceImpl extends AbstractServiceImpl implements ImageManageService {

	/** imageManageDAO 서비스 호출 */ 
	@Resource(name="imageManageDAO")
    private ImageManageDAO imageManageDAO;

	/**
	 * 이미지삭제
	 * @param imageManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_IMAGE
	 */
	public void deleteImage(ImageManageVO imageManageVO) throws Exception {
		imageManageDAO.deleteImage(imageManageVO);
	}

	/**
	 * 이미지등록
	 * @param imageManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_IMAGE
	 */
	public void insertImage(ImageManageVO imageManageVO) throws Exception {
    	imageManageDAO.insertImage(imageManageVO);    	
	}

	/**
	 * 이미지수정
	 * @param imageManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_IMAGE
	 */
	public void updateImage(ImageManageVO imageManageVO) throws Exception {
    	imageManageDAO.updateImage(imageManageVO);    	
	}

	/**
	 * 이미지 상세 조회
	 * @param imageManageVO
	 * @return
	 * @throws Exception
	 * @see IMAGE_ID,IMAGE_SN,IMAGE_SE,IMAGE_NM,IMAGE_FILE_NM,IMAGE_FILE_MASK,IMAGE_FILE_SIZE,IMAGE_FILE_MIME,IMAGE_WIDTH,
	 * @see IMAGE_HEIGHT,IMAGE_REFLCT_AT,IMAGE_REFLCT_URL,SYS_ID,SYS_NM,REGISTER_IP,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID
	 * @see TABLE NAME : TN_IMAGE,TN_SYSTEM
	 */
	public ImageManageVO selectImage(ImageManageVO imageManageVO) throws Exception {
    	ImageManageVO ret = (ImageManageVO)imageManageDAO.selectImage(imageManageVO);
    	return ret;
	}
	
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
	public List selectImageList(ImageManageVO imageManageVO) throws Exception {
        return imageManageDAO.selectImageList(imageManageVO);
	}
	
	/** 
	 * 이미지 목록 총 갯수
	 * @param imageManageVO 총 갯수를 조회할 프로그램VO
	 * @return int
	 * @throws Exception 
     * @see IMAGE_ID,IMAGE_SN,IMAGE_SE,IMAGE_NM,IMAGE_FILE_NM,IMAGE_FILE_MASK,IMAGE_FILE_SIZE,IMAGE_FILE_MIME,IMAGE_WIDTH,
	 * @see IMAGE_HEIGHT,IMAGE_REFLCT_AT,IMAGE_REFLCT_URL,SYS_ID,SYS_NM,REGISTER_IP,REGIST_DT,REGISTER_ID,UPDT_DT,UPDTUSR_ID
	 * @see TABLE NAME : TN_IMAGE,TN_SYSTEM
	 */  
	public int selectImageListTotCnt(ImageManageVO imageManageVO) throws Exception {
        return imageManageDAO.selectImageListTotCnt(imageManageVO);
	}
	
	/** 
	 * 이미지 SN 생성
	 * @return int
	 * @throws Exception 
	 * @see TABLE NAME : TN_IMAGE
	 */
	public int imageSnGeneration() throws Exception {
		return imageManageDAO.imageSnGeneration();
	}
}
