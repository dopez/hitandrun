package com.bbm.gps.adm.author.service;

import com.bbm.gps.cmm.service.GpsDefaultVO;

import java.io.Serializable;


/** 
 * 권한관리에 사용되는 value object들을 정의한다. 
 * <p><b>NOTE:</b>
 * @author 포탈통계 이진우 
 * @since 2011.06.17 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.06.17     이진우      최초 생성 
 * 
 * </pre> 
 */
@SuppressWarnings("serial")
public class AuthorManage extends GpsDefaultVO implements Serializable {

    private String authorId;
    
    private String authorCode;
    
    private String authorNm;

    private String authorDc;

    private String upperAuthorId;

    public String getAuthorCode() {
        return authorCode;
    }

    public void setAuthorCode(String authorCode) {
        this.authorCode = authorCode;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
    public String getAuthorNm() {
        return authorNm;
    }

    public void setAuthorNm(String authorNm) {
        this.authorNm = authorNm;
    }
    
    public String getAuthorDc() {
        return authorDc;
    }

    public void setAuthorDc(String authorDc) {
        this.authorDc = authorDc;
    }

	public String getUpperAuthorId() {
		return upperAuthorId;
	}

	public void setUpperAuthorId(String upperAuthorId) {
		this.upperAuthorId = upperAuthorId;
	}

}
