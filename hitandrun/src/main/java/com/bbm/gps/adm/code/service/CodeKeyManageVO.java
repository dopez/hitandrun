package com.bbm.gps.adm.code.service;

import com.bbm.gps.cmm.service.GpsDefaultVO;

import java.io.Serializable;

/**
 * 코드관리 모델 클래스
 * @author 범정부통계포털 이관형 
 * @since 2011.06.21
 * @version 1.0
 * @see
 *
 * <pre>
 *  == Modification Information) == 
 *   
 *     date         author                note 
 *  -----------    --------    --------------------------- 
 *   2011.06.21  이관형          최초 생성
 *
 * </pre>
 */
@SuppressWarnings("serial")
public class CodeKeyManageVO extends GpsDefaultVO implements Serializable {

	/** 코드ID */
    private String codeId;

    /** 코드구분 */
    private String codeSe;

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getCodeSe() {
        return codeSe;
    }

    public void setCodeSe(String codeSe) {
        this.codeSe = codeSe;
    }
}
