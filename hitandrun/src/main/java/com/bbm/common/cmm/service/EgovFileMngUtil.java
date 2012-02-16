package com.bbm.common.cmm.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.bbm.common.cmm.util.StringUtil;
import com.bbm.common.cmm.util.fcc.service.EgovStringUtil;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name  : EgovFileMngUtil.java
 * @Description : 메시지 처리 관련 유틸리티
 * @Modification Information
 * 
 *     수정일         수정자                   수정내용
 *     -------          --------        ---------------------------
 *   2009.02.13       이삼섭                  최초 생성
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 02. 13
 * @version 1.0
 * @see 
 * 
 */
@Component("EgovFileMngUtil")
public class EgovFileMngUtil {

    public static final int BUFF_SIZE = 2048;

    /** messageSource */
	@Resource(name = "messageSource")
	protected MessageSource messageSource;
	
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    

    @Resource(name = "egovFileIdGnrService")
    private EgovIdGnrService idgenService;

    Logger log = Logger.getLogger(this.getClass());

    
    /**
     * 첨부파일에 대한 목록 정보를 취득한다.
     * 
     * @param files
     * @return
     * @throws Exception
     */
    public List<FileVO> parseFileInf(Map<String, MultipartFile> files, String KeyStr, int fileKeyParam, String atchFileId, String storePath) throws Exception {
       return parseFileInf( files, KeyStr, fileKeyParam, atchFileId, storePath, ""); 
    }	 
    /**
     * 첨부파일에 대한 목록 정보를 취득한다.
     * 
     * @param files
     * @return
     * @throws Exception
     */
    public List<FileVO> parseFileInf(Map<String, MultipartFile> files, String KeyStr, int fileKeyParam, String atchFileId, String storePath, String creatorId) throws Exception {
	int fileKey = fileKeyParam;
	
	String storePathString = "";
	String atchFileIdString = "";

	if ("".equals(storePath) || storePath == null) {
	    storePathString = propertiesService.getString("Globals.fileStorePath")+File.separator+propertiesService.getString("was.LcInfo");
	} else {
	    storePathString = storePath;
	}

	if ("".equals(atchFileId) || atchFileId == null) {
	    atchFileIdString = idgenService.getNextStringId();
	} else {
	    atchFileIdString = atchFileId;
	}

	File saveFolder = new File(storePathString);
	
	if (!saveFolder.exists() || saveFolder.isFile()) {
	    saveFolder.mkdirs();
	}

	Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
	MultipartFile file;
	String filePath = "";
	List<FileVO> result  = new ArrayList<FileVO>();
	FileVO fvo;

	while (itr.hasNext()) {
	    Entry<String, MultipartFile> entry = itr.next();

	    file = entry.getValue();
	    String orginFileName = file.getOriginalFilename();
	    
	    //--------------------------------------
	    // 원 파일명이 없는 경우 처리
	    // (첨부가 되지 않은 input file type)
	    //--------------------------------------
	    if ("".equals(orginFileName)) {
		continue;
	    }
	    if( StringUtil.checkFileExt(orginFileName)) {	    	
	    	throw new EgovBizException(messageSource, "fail.common.filenameext", new String[] {"BizException"} , null );
	    }
	    
	    long fileSize = file.getSize();
		if(Long.parseLong(propertiesService.getString("COMMON.file.uploadSize"))< fileSize) {   	
	    	throw new EgovBizException(messageSource, "error.file.uploadsize", new String[] {"BizException", propertiesService.getString("COMMON.file.uploadSize")} , null );
	    }

	    ////------------------------------------

	    int index = orginFileName.lastIndexOf(".");
	    //String fileName = orginFileName.substring(0, index);
	    String fileExt = orginFileName.substring(index + 1);
	    String newName = KeyStr + EgovStringUtil.getTimeStamp() + fileKey;
	    long _size = file.getSize();

	    if (!"".equals(orginFileName)) {
		filePath = storePathString + File.separator + newName;
		file.transferTo(new File(filePath));
	    }
	    fvo = new FileVO();
   	    fvo.setAtchFileKey(entry.getKey());
	    fvo.setFileExtsn(fileExt);
	    fvo.setFileStreCours(storePathString);
	    fvo.setFileMg(Long.toString(_size));
	    fvo.setOrignlFileNm(orginFileName);
	    fvo.setStreFileNm(newName);
	    fvo.setAtchFileId(atchFileIdString);
	    fvo.setFileSn(String.valueOf(fileKey));
	    fvo.setCreatorId(creatorId);    

	    //writeFile(file, newName, storePathString);
	    result.add(fvo);
	    
	    fileKey++;
	}

	return result;
    }

    /**
     * 첨부파일에 대한 목록 정보를 취득한다.
     * 
     * @param files
     * @return
     * @throws Exception
     */
    public List<FileVO> parseFileInf( MultipartFile file, String KeyStr, int fileKeyParam, String atchFileId, String storePath) throws Exception {
	  return parseFileInf(file, KeyStr, fileKeyParam, atchFileId, storePath, "" ); 
    }	
    
    /**
     * 첨부파일에 대한 목록 정보를 취득한다.
     * 
     * @param files
     * @return
     * @throws Exception
     */
    public List<FileVO> parseFileInf( MultipartFile file, String KeyStr, int fileKeyParam, String atchFileId, String storePath, String creatorId ) throws Exception {
	int fileKey = fileKeyParam;
	
	String storePathString = "";
	String atchFileIdString = "";

	if ("".equals(storePath) || storePath == null) {
	    storePathString = propertiesService.getString("Globals.fileStorePath");
	} else {
	    storePathString = storePath;
	}

	if ("".equals(atchFileId) || atchFileId == null) {
	    atchFileIdString = idgenService.getNextStringId();
	} else {
	    atchFileIdString = atchFileId;
	}

	File saveFolder = new File(storePathString);
	
	if (!saveFolder.exists() || saveFolder.isFile()) {
	    saveFolder.mkdirs();
	}
	
	String filePath = "";
	List<FileVO> result  = new ArrayList<FileVO>();
	FileVO fvo;

    String orginFileName = file.getOriginalFilename();
    
    //--------------------------------------
    // 원 파일명이 없는 경우 처리
    // (첨부가 되지 않은 input file type)
    //--------------------------------------
    if ("".equals(orginFileName)) {
    	 return result;
    }
    if( StringUtil.checkFileExt(orginFileName)) {	    	
    	throw new EgovBizException(messageSource, "fail.common.filenameext", new String[] {"BizException"} , null );
    }

    ////------------------------------------

    int index = orginFileName.lastIndexOf(".");
    //String fileName = orginFileName.substring(0, index);
    String fileExt = orginFileName.substring(index + 1);
    String newName = KeyStr + EgovStringUtil.getTimeStamp() + fileKey;
    long _size = file.getSize();

    if (!"".equals(orginFileName)) {
	filePath = storePathString + File.separator + newName;
	file.transferTo(new File(filePath));
    }
    fvo = new FileVO();
    fvo.setFileExtsn(fileExt);
    fvo.setFileStreCours(storePathString);
    fvo.setFileMg(Long.toString(_size));
    fvo.setOrignlFileNm(orginFileName);
    fvo.setStreFileNm(newName);
    fvo.setAtchFileId(atchFileIdString);
    
    fvo.setFileSn(String.valueOf(fileKey));
    
    fvo.setCreatorId(creatorId);
    
    
    //writeFile(file, newName, storePathString);
    result.add(fvo);
    
    fileKey++;

    return result;
    }
    
    /**
     * 첨부파일을 서버에 저장한다.
     * 
     * @param file
     * @param newName
     * @param stordFilePath
     * @throws Exception
     */
    protected void writeUploadedFile(MultipartFile file, String newName, String stordFilePath) throws Exception {
	InputStream stream = null;
	OutputStream bos = null;
	
	try {
	    stream = file.getInputStream();
	    File cFile = new File(stordFilePath);

	    if (!cFile.isDirectory()) {
		boolean _flag = cFile.mkdir();
		if (!_flag) {
		    throw new IOException("Directory creation Failed ");
		}
	    }

	    bos = new FileOutputStream(stordFilePath + File.separator + newName);

	    int bytesRead = 0;
	    byte[] buffer = new byte[BUFF_SIZE];

	    while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {
		bos.write(buffer, 0, bytesRead);
	    }
	} catch (FileNotFoundException fnfe) {
		log.error("EgovFileMngUtil writeUploadedFile() FileNotFoundException Occured");
	} catch (IOException ioe) {
		log.error("EgovFileMngUtil writeUploadedFile() IOException Occured");
	} catch (Exception e) {
		log.error("EgovFileMngUtil writeUploadedFile() Exception Occured");
	} finally {
	    if (bos != null) {
		try {
		    bos.close();
		} catch (Exception ignore) {
		    log.debug("IGNORED: " + ignore.getMessage());
		}
	    }
	    if (stream != null) {
		try {
		    stream.close();
		} catch (Exception ignore) {
		    log.debug("IGNORED: " + ignore.getMessage());
		}
	    }
	}
    }	

    /**
     * 파일을 실제 물리적인 경로에 생성한다.
     * 
     * @param file
     * @param newName
     * @param stordFilePath
     * @throws Exception
     */
    protected static void writeFile(MultipartFile file, String newName, String stordFilePath) throws Exception {
	InputStream stream = null;
	OutputStream bos = null;
	
	try {
	    stream = file.getInputStream();
	    File cFile = new File(stordFilePath);

	    if (!cFile.isDirectory())
		cFile.mkdir();

	    bos = new FileOutputStream(stordFilePath + File.separator + newName);

	    int bytesRead = 0;
	    byte[] buffer = new byte[BUFF_SIZE];

	    while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {
		bos.write(buffer, 0, bytesRead);
	    }
	} catch (FileNotFoundException fnfe) {
		Logger.getLogger(EgovFileMngUtil.class).debug("EgovFileMngUtil writeFile() FileNotFoundException Occured");
	} catch (IOException ioe) {
		Logger.getLogger(EgovFileMngUtil.class).debug("EgovFileMngUtil writeFile() IOException Occured");
	} catch (Exception e) {
		Logger.getLogger(EgovFileMngUtil.class).debug("EgovFileMngUtil writeFile() Exception Occured");
	} finally {
	    if (bos != null) {
		try {
		    bos.close();
		} catch (Exception ignore) {
		    Logger.getLogger(EgovFileMngUtil.class).debug("IGNORED: " + ignore.getMessage());
		}
	    }
	    if (stream != null) {
		try {
		    stream.close();
		} catch (Exception ignore) {
		    Logger.getLogger(EgovFileMngUtil.class).debug("IGNORED: " + ignore.getMessage());
		}
	    }
	}
    }

   
}
