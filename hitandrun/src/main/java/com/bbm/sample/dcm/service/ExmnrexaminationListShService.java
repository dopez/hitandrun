/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bbm.sample.dcm.service;

import java.util.List;
import java.util.Map;
import javax.jws.WebService;

 
/**  
 * @Class Name : DataColctExcService.java
 * @Description : DataColctExcService 
 * @author 조사표 설계/수집 개발팀 최종대
 * @since 2011. 07.27
 * @version 1.0
 * @see
 * @Modification Information  
 * @
 * @  수정일        수정자                수정내용
 * @ ---------    ---------    -------------------------------
 * @ 2011.07.27    최종대       최초생성
 *
 */

public interface ExmnrexaminationListShService {

	List SelectGridList(String sql_id, Map map)throws Exception;
	
	List SelectSerchList(String sql_id, Map map)throws Exception;
	
	List SelectTable(String sql_id, Map map)throws Exception;
    
    List SelectTablecnt(String sql_id, Map map)throws Exception;
	
	public Map selectCmmCodeMap(ExmnrexaminationListShVO vo) throws Exception;
    
    /**
     * 공통코드를 조회한다. 
     * 
     * @param vo
     * @return List(코드)
     * @throws Exception
     */
    public List<ExmnrexaminationListShVO> selectCmmCodeDetail1(ExmnrexaminationListShVO vo) throws Exception;
		
}

