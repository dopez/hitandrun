package com.bbm.sample.dcm.service;

import java.util.List;
import java.util.Map;
import javax.jws.WebService;

/**
 * @Project Name : 행정자료 통합관리시스템 구축 프로젝트
 * @Class Name : IblLoginService.java
 * @Description : @ @ 수정일 수정자 수정내용 @ --------- ---------
 *              ------------------------------- @ 2011. 6. 22. CoolLBK 최초생성
 * 
 * @author : CoolLBK
 * @since : 2011. 6. 22.
 * @version 1.0
 *  
 *          Copyright (C) by Ucore All right reserved.
 */

public interface DtColcErExaminService {

    List SelectGridList(String sql_id, Map map)throws Exception;
    
    List SelectTable(String sql_id, Map map)throws Exception;
    
    List SelectTablecnt(String sql_id, Map map)throws Exception;
    
    List SelectTableJosopyo(String sql_id, Map map)throws Exception;
    
    List SelectSerchList(String sql_id, Map map)throws Exception;
    
    List selecthistoryList(DtColcErExaminVO searchVO) throws Exception;
    
    int selecthistoryTotCnt(DtColcErExaminVO searchVO);
    
    public Map selectCmmCodeMap(String codeId , String orderType ) throws Exception;
    
    /**
     * 공통코드를 조회한다. 
     * 
     * @param vo
     * @return List(코드)
     * @throws Exception
     */
    public List<DtColcErExaminVO> selectCmmCodeDetail1(DtColcErExaminVO vo) throws Exception;
    
    /**
     * 공통코드를 조회한다. 
     * 
     * @param vo
     * @return List(코드)
     * @throws Exception
     */
    public List<DtColcErExaminVO> selectCmmCodeDetail2(DtColcErExaminVO vo) throws Exception;
}