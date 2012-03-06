package com.bbm.gps.adm.author.service;

import com.bbm.gps.cmm.service.GpsDefaultVO;

import java.io.Serializable;

/** 
 * 메뉴권한관리에 사용되는 value object들을 정의한다. 
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
public class MenuAuthor extends GpsDefaultVO implements Serializable {
	
	/**  */
	private int menuNo;

	/**  */
	private String authorId;

	/**  */
	private String readAuthor = "Y";

	/**  */
	private String createAuthor = "Y";

	/**  */
	private String updateAuthor = "Y";

	/**  */
	private String deleteAuthor = "Y";

	public int getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getReadAuthor() {
		return readAuthor;
	}

	public void setReadAuthor(String readAuthor) {
		this.readAuthor = readAuthor;
	}

	public String getCreateAuthor() {
		return createAuthor;
	}

	public void setCreateAuthor(String createAuthor) {
		this.createAuthor = createAuthor;
	}

	public String getUpdateAuthor() {
		return updateAuthor;
	}

	public void setUpdateAuthor(String updateAuthor) {
		this.updateAuthor = updateAuthor;
	}

	public String getDeleteAuthor() {
		return deleteAuthor;
	}

	public void setDeleteAuthor(String deleteAuthor) {
		this.deleteAuthor = deleteAuthor;
	}
	
}