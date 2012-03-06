package com.bbm.gps.cmm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import com.bbm.common.cmm.service.EgovFileMngService;
import com.bbm.common.cmm.service.EgovFileMngUtil;
import com.bbm.common.cmm.service.FileVO;
import com.bbm.gps.cmm.service.GpsCmmService;

/**
 * @Class Name : NaraCmmServiceImpl.java
 * @Description : 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기위한 서비스 구현 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2011. 7. 18.     이관형
 *	  2011. 7. 25.     황기연	     fileListCnt(파일ID 의 총파일 갯수) 추가
 *
 * @author 범정부통계포털 이관형
 * @since 2011. 7. 18.
 * @version
 * @see
 *
 */
@Service("gpsCmmService")
public class GpsCmmServiceImpl extends AbstractServiceImpl implements GpsCmmService {

	/** 첨부파일 관련 EgovFileMngUtil 호출 */	 
	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

    /** 첨부파일 관련 EgovFileMngService 호출 */ 
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;	

	/** GpsCmmDAO 서비스 호출 */ 
	@Resource(name="GpsCmmDAO")
    private GpsCmmDAO gpsCmmDAO;
    
    /**
     * 첨부파일에 대한 목록 정보를 취득한다.
     * 그리고 첨부파일을 저장한다. 
     * 
     * @param files
     * @param keyStr
     * @param fileKeyParam
     * @param atchFileId
     * @param storePath
     * @return
     * @throws Exception
     */
    public List<FileVO> insertAtchFile(Map<String, MultipartFile> fileList, String KeyStr, String atchFileId, String storePath) throws Exception {
        List<FileVO> _result = null;
        int fileKeyParam =0 ;
    	if(!fileList.isEmpty()){
    	
    		_result     = fileUtil.parseFileInf(fileList, KeyStr, fileKeyParam, atchFileId, storePath );
    		if( _result.size() > 0) {
    	
    			fileMngService.insertFileInfs(_result);	//파일이 생성되고 나면 생성된 첨부파일에 대한 정보를 리턴한다.
    		}
    	}

    	return _result;
    }
    
    /**
     * 첨부파일에 대한 목록 정보를 취득한다.
     * 그리고 첨부파일을 저장한다. 
     * 
     * @param files
     * @param keyStr
     * @param atchFileId
     * @param storePath
     * @return
     * @throws Exception
     */
    public List<FileVO> updateAtchFile(Map<String, MultipartFile> files, String KeyStr,  String atchFileId, String storePath) throws Exception {
        List<FileVO> _result = null;
        
        FileVO fvo = new FileVO();
        fvo.setAtchFileId(atchFileId);
		int _cnt = fileMngService.getMaxFileSN(fvo);
		
    	if(!files.isEmpty()){
    		_result     = fileUtil.parseFileInf(files, KeyStr, _cnt, atchFileId, storePath);
    		fileMngService.updateFileInfs(_result);	
    	}
    	return _result;

    }

    /**
     * 파일에 대한 상세정보를 조회한다.
     * 
     * @param FileVO fvo
     * @return FileVO fileVO
     * @throws Exception
     */
    public FileVO selectFileInf(FileVO fvo) throws Exception {
    	return gpsCmmDAO.selectFileInf(fvo);
    }
    
    
    /**
	 * 첨부파일 ID의 첨부파일 총갯수
	 * @param attchFileId
	 * @return int 첨부파일 총갯수
	 * @exception Exception
	 */
    public String fileListCnt(String attchFileId) throws Exception {
		List<FileVO> fileList = new ArrayList<FileVO>();
		if(attchFileId != null && !attchFileId.equals("")){
	    	FileVO fileVO = new FileVO();
	    	fileVO.setAtchFileId(attchFileId);
			fileList = fileMngService.selectFileInfs(fileVO);//파일정보 가져오기
		}
		int cnt = fileList.size();
		return Integer.toString(cnt);
	}

}	
