package com.bbm.gps.cmm.service.impl;

import egovframework.rte.psl.dataaccess.GpsAbstractDAO;
import com.bbm.common.cmm.service.FileVO;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

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
@Repository("GpsFileManageDAO")
public class GpsFileManageDAO extends GpsAbstractDAO {

    Logger log = Logger.getLogger(this.getClass());

    /**
     * 파일에 대한 상세정보를 조회한다.
     * 
     * @param fvo
     * @return
     * @throws Exception
     */
    public FileVO selectFileInfStre(FileVO fvo) throws Exception {
    	return (FileVO)selectByPk("GpsFileManageDAO.selectFileInfStre", fvo);
    }

}
