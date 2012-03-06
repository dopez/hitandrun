package com.bbm.gps.adm.image.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.GpsAbstractDAO;
import com.bbm.gps.adm.image.service.ImageManageVO;

/** 
 * 이미지관리에 대한 데이터 접근 클래스를 정의한다
 * <p><b>NOTE:</b> 넘어온 요청에 대해 DB작업을 수행하는 메소드들의 집합
 * DB에 직접 접근하며 쿼리문에 적용할 parameter를 보내주거나 단순 쿼리 실행을 하도록 호출한다
 * select, update, delete insert 함수를 사용하며 쿼리아이디와 parameter를 넘긴다
 * @author 포탈통계 이관형 
 * @since 2011.06.17 
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
@Repository("imageManageDAO")
public class ImageManageDAO extends GpsAbstractDAO {

	/**
	 * 이미지삭제
	 * @param imageManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_IMAGE
	 */
	public void deleteImage(ImageManageVO imageManageVO) throws Exception {
		delete("ImageManageDAO.deleteImage", imageManageVO);
	}

	/**
	 * 이미지등록
	 * @param imageManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_IMAGE
	 */
	public void insertImage(ImageManageVO imageManageVO) throws Exception {
        insert("ImageManageDAO.insertImage", imageManageVO);
	}

	/**
	 * 이미지수정
	 * @param imageManageVO
	 * @throws Exception
	 * @see TABLE NAME : TN_IMAGE
	 */
	public void updateImage(ImageManageVO imageManageVO) throws Exception {
        update("ImageManageDAO.updateImage", imageManageVO);
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
		return (ImageManageVO)selectByPk("ImageManageDAO.selectImage", imageManageVO);
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
        return list("ImageManageDAO.selectImageList", imageManageVO);
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
        return (Integer)getSqlMapClientTemplate().queryForObject("ImageManageDAO.selectImageListTotCnt", imageManageVO);
    }
    
    /** 
	 * 이미지 SN 생성
	 * @return int
	 * @throws Exception 
	 * @see TABLE NAME : TN_IMAGE
	 */
    public int imageSnGeneration() throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("ImageManageDAO.imageSnGeneration", null);
    }

}
