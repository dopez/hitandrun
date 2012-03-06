package com.bbm.gps.adm.program.service;

import com.bbm.gps.cmm.service.GpsDefaultVO;

import java.io.Serializable;

/** 
 * 프로그램관리에 사용되는 value object들을 정의한다. 
 * <p><b>NOTE:</b>
 * @author 포탈통계 이관형 
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
public class ProgramManageVO extends GpsDefaultVO implements Serializable {

    /** 프로그램 파일명 리스트 */
	private String programIdList;

    /** 프로그램 ID */
    private String programId;	
	
	/** 프로그램 파일명 */
    private String programFileNm;

    /** 프로그램 저장경로 */
    private String programStrePath;

    /** 프로그램 한글명 */
    private String programKoreannm;

    /** URL */
    private String url;

    /** 프로그램 설명 */
    private String programDc;

    /** 프로그램유형:읽기기능(Y:N)*/
    private String programTyRead;
    
    /** 프로그램유형:신규생성기능(Y:N)*/
    private String programTyCreate;
    
    /** 프로그램유형:수정기능(Y:N)*/
    private String programTyUpdate;
    
    /** 프로그램유형:삭제기능(Y:N)*/
    private String programTyDelete;
    
	/** 시스템명 */
    private String sysNm;
    
    public String getProgramIdList() {
		return programIdList;
	}

	public void setProgramIdList(String programIdList) {
		this.programIdList = programIdList;
	}

    public String getProgramFileNm() {
        return programFileNm;
    }

    public void setProgramFileNm(String programFileNm) {
        this.programFileNm = programFileNm;
    }

    public String getProgramStrePath() {
        return programStrePath;
    }

    public void setProgramStrePath(String programStrePath) {
        this.programStrePath = programStrePath;
    }

    public String getProgramKoreannm() {
        return programKoreannm;
    }

    public void setProgramKoreannm(String programKoreannm) {
        this.programKoreannm = programKoreannm;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProgramDc() {
        return programDc;
    }

    public void setProgramDc(String programDc) {
        this.programDc = programDc;
    }

    public String getProgramTyRead() {
		return programTyRead;
	}

    public void setProgramTyRead(String programTyRead) {
		this.programTyRead = programTyRead;
	}

    public String getProgramTyCreate() {
		return programTyCreate;
	}

    public void setProgramTyCreate(String programTyCreate) {
		this.programTyCreate = programTyCreate;
	}

    public String getProgramTyUpdate() {
		return programTyUpdate;
	}

    public void setProgramTyUpdate(String programTyUpdate) {
		this.programTyUpdate = programTyUpdate;
	}

    public String getProgramTyDelete() {
		return programTyDelete;
	}

    public void setProgramTyDelete(String programTyDelete) {
		this.programTyDelete = programTyDelete;
	}

    public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}    

	public String getSysNm() {
		return sysNm;
	}

	public void setSysNm(String sysNm) {
		this.sysNm = sysNm;
	}
}