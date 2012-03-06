package com.bbm.gps.cmm.service;

import com.bbm.common.cmm.service.FileVO;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기 위한 서비스 인터페이스 
 * @author 범정부통계포털 이관형
 * @since 2011.07.18
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.07.18  이관형          최초 생성
 *
 * </pre>
 */
public interface GpsCmmService {
	
    /**
     * 첨부파일에 대한 목록 정보를 취득한다.
     * 그리고 첨부파일을 저장한다. 
     * @param files
     * @param keyStr
     * @param fileKeyParam
     * @param atchFileId
     * @param storePath
     * @return List<FileVO>
     * @throws Exception
     */
    public List<FileVO> insertAtchFile(Map<String, MultipartFile> files, String KeyStr, String atchFileId, String storePath) throws Exception;     
    
    /**
     * 첨부파일에 대한 목록 정보를 취득한다.
     * 그리고 첨부파일을 저장한다. 
     * @param files
     * @param keyStr
     * @param atchFileId
     * @param storePath
     * @return List<FileVO>
     * @throws Exception
     */
    public List<FileVO> updateAtchFile(Map<String, MultipartFile> files, String KeyStr,  String atchFileId, String storePath) throws Exception;

    /**
     * 파일에 대한 상세정보를 조회한다.
     * 
     * @param FileVO fvo
     * @return FileVO fileVO
     * @throws Exception
     */
    public FileVO selectFileInf(FileVO fvo) throws Exception;
    
    
    /**
	 * 첨부파일 총갯수
	 * @param attchFileId
	 * @return int 첨부파일 총갯수
	 * @exception Exception
	 */
    public String fileListCnt(String attchFileId) throws Exception;

}
