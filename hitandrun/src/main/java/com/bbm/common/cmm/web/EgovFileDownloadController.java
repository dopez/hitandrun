package com.bbm.common.cmm.web;

import com.bbm.common.cmm.EgovMessageSource;
import com.bbm.common.cmm.service.EgovFileMngService;
import com.bbm.common.cmm.service.FileVO;
import com.bbm.common.cmm.util.StringUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 파일 다운로드를 위한 컨트롤러 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2011.06.25
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2011.6.25  김일수          최초 생성
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 * </pre>
 */
@Controller
public class EgovFileDownloadController {
    
	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
	
    @Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
    
    Logger log = Logger.getLogger(this.getClass());
    
    /**
     * 브라우저 구분 얻기.
     * 
     * @param request
     * @return
     */
    private String getBrowser(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        if (header.indexOf("MSIE") > -1) {
            return "MSIE";
        } else if (header.indexOf("Chrome") > -1) {
            return "Chrome";
        } else if (header.indexOf("Opera") > -1) {
            return "Opera";
        }
        return "Firefox";
    }
    
    /**
     * Disposition 지정하기.
     * 
     * @param filename
     * @param request
     * @param response
     * @throws Exception
     */
    private void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
	String browser = getBrowser(request);
	
	String dispositionPrefix = "attachment; filename=";
	String encodedFilename = null;
	
	if (browser.equals("MSIE")) {
	    encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
	} else if (browser.equals("Firefox")) {
	    encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
	} else if (browser.equals("Opera")) {
	    encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
	} else if (browser.equals("Chrome")) {
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < filename.length(); i++) {
		char c = filename.charAt(i);
		if (c > '~') {
		    sb.append(URLEncoder.encode("" + c, "UTF-8"));
		} else {
		    sb.append(c);
		}
	    }
	    encodedFilename = sb.toString();
	} else {
	    //throw new RuntimeException("Not supported browser");
	    throw new IOException("Not supported browser");
	}
	
	//보안취약성 조치 코드 추가
	dispositionPrefix = dispositionPrefix.replaceAll("\r", "").replaceAll("\n", "");
	encodedFilename = encodedFilename.replaceAll("\r", "").replaceAll("\n", "");
	
	response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

	if ("Opera".equals(browser)){
	    response.setContentType("application/octet-stream;charset=UTF-8");
	}
    }

    /**
     * 첨부파일로 등록된 파일에 대하여 다운로드를 제공한다.
     * 
     * @param commandMap
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/cmm/fms/FileDown.do")    
    public void cvplFileDownload(Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	String atchFileId = (String)commandMap.get("atchFileId");
	    String fileSn = (String)commandMap.get("fileSn");

//	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

//	if (isAuthenticated) {

	    FileVO fileVO = new FileVO();
	    fileVO.setAtchFileId(atchFileId);
	    fileVO.setFileSn(fileSn);
	    FileVO fvo = fileService.selectFileInf(fileVO);
	    File uFile = new File(fvo.getFileStreCours() + File.separator + fvo.getStreFileNm());
	    int fSize = (int)uFile.length();

	    if (fSize > 0) {
		String mimetype = "application/x-msdownload";

		//response.setBufferSize(fSize);	// OutOfMemeory 발생
		response.setContentType(mimetype);
		//response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fvo.getOrignlFileNm(), "utf-8") + "\"");
		setDisposition(fvo.getOrignlFileNm(), request, response);
		response.setContentLength(fSize);

		/*
		 * FileCopyUtils.copy(in, response.getOutputStream());
		 * in.close(); 
		 * response.getOutputStream().flush();
		 * response.getOutputStream().close();
		 */
		BufferedInputStream in = null;
		BufferedOutputStream out = null;

		try {
		    in = new BufferedInputStream(new FileInputStream(uFile));
		    out = new BufferedOutputStream(response.getOutputStream());

		    FileCopyUtils.copy(in, out);
		    out.flush();
		} catch (Exception ex) {
		    //ex.printStackTrace();
		    // 다음 Exception 무시 처리
		    // Connection reset by peer: socket write error
		    log.debug("IGNORED: " + ex.getMessage());
		} finally {
		    if (in != null) {
			try {
			    in.close();
			} catch (Exception ignore) {
			    // no-op
			    log.debug("IGNORED: " + ignore.getMessage());
			}
		    }
		    if (out != null) {
			try {
			    out.close();
			} catch (Exception ignore) {
			    // no-op
			    log.debug("IGNORED: " + ignore.getMessage());
			}
		    }
		}

	    } else {
		
			response.setContentType("text/html;charset=utf-8");
	
			PrintWriter output = response.getWriter();
	
			output.println("<script type='text/javascript' >");
			output.println("alert('"+ egovMessageSource.getMessage("fail.common.noatchfile")+"');");			
			output.println("</script>");
	
	
			output.flush();
			output.close();

	    }
	}

    /**
	 * DB Table에 등록된 첨부파일에 대하여 다운로드를 제공한다.
	 * 
	 * @param commandMap
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/cmm/fms/FileDownLoad.do")
	public void FileDownLoad(Map<String, Object> commandMap,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String atchFileId = (String) commandMap.get("atchFileId");
		String fileSn = (String) commandMap.get("fileSn");

		FileVO fileVO = new FileVO();
		fileVO.setAtchFileId(atchFileId);
		fileVO.setFileSn(fileSn);
		FileVO fvo = fileService.selectFileTableInf(fileVO);
		
	    byte [] b = fvo.getFileCn();
	    int fSize = b.length;
	    
		if (fSize > 0) {
			String mimetype = "application/x-msdownload";
		
			response.setContentType(mimetype);
			setDisposition(fvo.getOrignlFileNm(), request, response);
			response.setContentLength(fSize);
			BufferedOutputStream out = null;

			try {

				out = new BufferedOutputStream(response.getOutputStream());

				out.write( b );
				out.flush();
			} catch (Exception ex) {
				log.debug("IGNORED: " + ex.getMessage());
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (Exception ignore) {
						log.debug("IGNORED: " + ignore.getMessage());
					}
				}
			}

		} else {
			response.setContentType("application/x-msdownload");

			PrintWriter printwriter = response.getWriter();
         	printwriter.println("<html>");
			printwriter.println("<br><br><br><h2>Could not get file name:<br>"
     		+ StringUtil.unScript(fvo.getOrignlFileNm()) + "</h2>");
			printwriter
					.println("<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
            printwriter.println("<br><br><br>&copy; webAccess");
            printwriter.println("</html>");
			printwriter.flush();
            printwriter.close();
		}
	}

}
