package com.bbm.sample.dcm.service;

import java.util.List;
import java.util.Map;
import javax.jws.WebService;

/**  
 * @Class Name : DatactChangeService.java
 * @Description : 
 * @author : 조사표 설계/수집 개발팀 최종대
 * @since  : 2011. 08. 26
 * @version 1.0
 * @
 * @  수정일      		수정자           수정내용
 * @ ---------   	   ---------  	----------------------------------
 * @ 2011. 08. 26.	 	최종대            최초생성
 * 
 * 
 */

public interface DatactChangeService {

    List SelectGridList(String sql_id, Map map)throws Exception;
    
    List SelectTable(String sql_id, Map map)throws Exception;
    
    List SelectTablecnt(String sql_id, Map map)throws Exception;
       
} 