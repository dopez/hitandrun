package com.bbm.gps.adm.author.service;

import com.bbm.gps.cmm.service.GpsDefaultVO;

import java.io.Serializable;





/** 
 * 사용자권한관리에 사용되는 value object들을 정의한다. 
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
public class UserAuthor extends GpsDefaultVO implements Serializable {

	/** 사용자권한ID */
	private String userAuthorId="";
	
	/** 권한ID */
	private String authorId="";
	
	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getUserAuthorId() {
		return userAuthorId;
	}

	public void setUserAuthorId(String userAuthorId) {
		this.userAuthorId = userAuthorId;
	}
	
	
}