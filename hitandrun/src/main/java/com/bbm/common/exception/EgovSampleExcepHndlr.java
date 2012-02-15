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
package com.bbm.common.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.cmmn.exception.handler.ExceptionHandler;

/**  
 * @Class Name : EgovSampleExcepHndlr.java
 * @Description : EgovSampleExcepHndlr Class
 * @Modification Information  
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 * 
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 * 
 *  Copyright (C) by MOPAS All right reserved.
 */
public class EgovSampleExcepHndlr implements ExceptionHandler {

    protected Log log = LogFactory.getLog(this.getClass());
    
    /**
    * @param ex
    * @param packageName
    * @see 개발프레임웍크 실행환경 개발팀
    */
    public void occur(Exception ex, String packageName) {

		log.debug(" EgovServiceExceptionHandler run..............." + packageName);
       
		try {
			String method_name = "";
			String service_name = "";
			
			int methodpos =  packageName.lastIndexOf("."); // 뒤로 부터 .의 위치
			if( methodpos > 0 ) {
				method_name = packageName.substring( methodpos + 1 ); //메소드 명
				String preStr = packageName.substring( 0, methodpos );
			
				int servicepos =  preStr.lastIndexOf("."); // 뒤로 부터 .의 위치
				if( servicepos > 0 ) {
					String tempStr = preStr.substring( servicepos + 1 );					
					servicepos =  tempStr.lastIndexOf("Impl"); // 뒤로 부터 Impl의 위치
					service_name = tempStr.substring( 0, servicepos );
				}
				
			}
			
			
			if( ex instanceof EgovBizException ) {
				EgovBizException bizex = (EgovBizException)ex;
				String [] messageParameters = new String[3];

				String classname = ex.getClass().toString();
				// 구분 , 에러코드, 에러메시지, 서비스명, 메소드명 
				String [] messages = new String[] {"BizException", "BizException", bizex.getMessage(), service_name, method_name, classname };  
				
				bizex.setMessageParameters(messages);				
				
			} 
			
						
			
			log.debug(" EgovServiceExceptionHandler try ");
		} catch (Exception e) {
			System.err.println("EgovSampleExcepHndlr occur() Exception Occured");
		}
    }

}
