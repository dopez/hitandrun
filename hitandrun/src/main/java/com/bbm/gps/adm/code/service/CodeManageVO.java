package com.bbm.gps.adm.code.service;

import java.io.Serializable;


/** 
 * 코드관리에 사용되는 value object들을 정의한다. 
 * <p><b>NOTE:</b>
 * @author 범정부통계포털 이관형 
 * @since 2011.06.17 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.06.17     이관형      최초 생성 
 * 
 * </pre> 
 */
@SuppressWarnings("serial")
public class CodeManageVO extends CodeManage implements Serializable {

	/** 코드ID리스트 */
	private String codeIdList;
	
	/** 상위코드명(JSP페이지사용) */
	private String upperCodeNm;

	public String getCodeIdList() {
		return codeIdList;
	}

	public void setCodeIdList(String codeIdList) {
		this.codeIdList = codeIdList;
	}

	public String getUpperCodeNm() {
		return upperCodeNm;
	}

	public void setUpperCodeNm(String upperCodeNm) {
		this.upperCodeNm = upperCodeNm;
	}

}
