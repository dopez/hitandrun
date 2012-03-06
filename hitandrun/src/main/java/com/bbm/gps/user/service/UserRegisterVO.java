package com.bbm.gps.user.service;

import com.bbm.gps.adm.user.service.UserManage;

import java.io.Serializable;

/** 
 * 사용자관리에 사용되는 value object들을 정의한다. 
 * <p><b>NOTE:</b>
 * @author 범정부통계포털 이관형 
 * @since 2011.06.17 
 * @version 1.0 
 * @see 
 * 
 * </pre>  
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 */
@SuppressWarnings("serial")
public class UserRegisterVO extends UserManage implements Serializable {
	
    /** 기관 리스트 */
	private String orgList = "";

    /** 권한 ID */
    private String authorId;
    
    /** 사용자 이미지 삭제 플레그 */
	private boolean userImgDelFlg;

    private String userId;

    /** 승인여부 검색조건 */
    private String approvalAtCond;
    
    /** 승인처리시 사용자id 리스트 */
    private String appUserIdList = "";
    
    public String getAppUserIdList() {
		return appUserIdList;
	}

	public void setAppUserIdList(String appUserIdList) {
		this.appUserIdList = appUserIdList;
	}

	public String getOrgList() {
		return orgList;
	}

	public void setOrgList(String orgList) {
		this.orgList = orgList;
	}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getApprovalAtCond() {
		return approvalAtCond;
	}

	public void setApprovalAtCond(String approvalAtCond) {
		this.approvalAtCond = approvalAtCond;
	}

	public boolean isUserImgDelFlg() {
		return userImgDelFlg;
	}

	public void setUserImgDelFlg(boolean userImgDelFlg) {
		this.userImgDelFlg = userImgDelFlg;
	}

}