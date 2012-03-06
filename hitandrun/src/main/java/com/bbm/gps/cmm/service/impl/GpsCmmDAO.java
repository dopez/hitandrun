package com.bbm.gps.cmm.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.GpsAbstractDAO;
import com.bbm.common.cmm.service.FileVO;

/**
 * @Class Name : GpsFileMngDAO.java
 * @Description : 파일정보 관리를 위한 데이터 처리 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2011. 7. 18.     이관형    최초생성
 *
 * @author 통계포털 이관형
 * @since 2011. 7. 18.
 * @version
 * @see
 *
 */
@Repository("GpsCmmDAO")
public class GpsCmmDAO extends GpsAbstractDAO {

    Logger log = Logger.getLogger(this.getClass());

    /**
     * 파일에 대한 상세정보를 조회한다.
     * 
     * @param fvo
     * @return
     * @throws Exception
     */
    public FileVO selectFileInf(FileVO fvo) throws Exception {
    	return (FileVO)selectByPk("GpsCmmDAO.selectFileInf", fvo);
    }

}
