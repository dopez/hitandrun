package com.bbm.common.cmm.service;

import java.io.Serializable;

/**
 * @Class Name : PrintVO.java
 * @Description : 출력을 위한 VO 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2011. 8. 19.     송인겸
 *
 * @author 기술지원팀 송인겸 
 * @since 2011. 8. 19.
 * @version
 * @see
 *
 */
@SuppressWarnings("serial")
public class PrintVO implements Serializable {

    /**
     * 첨부파일 Key
     */
    public String printData = "";

	public String getPrintData() {
		return printData;
	}

	public void setPrintData(String printData) {
		this.printData = printData;
	}
    	
}
