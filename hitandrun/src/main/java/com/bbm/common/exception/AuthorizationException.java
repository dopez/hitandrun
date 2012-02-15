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

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.context.MessageSource;

import egovframework.rte.fdl.cmmn.exception.BaseException;

/**  
 * @Class Name : AuthorizationException.java
 * @Description : AuthorizationException Class
 * @Modification Information  
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2011.08.01              최초생성
 * 
 * @author 기술지원팀
 * @since 2011. 08.01
 * @version 1.0
 * @see
 * 
 *  Copyright (C) by MOPAS All right reserved.
 */
public class AuthorizationException extends BaseException {

		private static final long serialVersionUID = 1L;

		/**
		 * AuthorizationException 생성자
		 */
		public AuthorizationException() {
			this("AuthorizationException without message", null, null);
		}

		/**
		 * AuthorizationException 생성자
		 * @param defaultMessage 메세지 지정
		 */
		public AuthorizationException(String defaultMessage) {
			this(defaultMessage, null, null);
		}
		/**
		 * AuthorizationException 생성자
		 * @param defaultMessage 메세지 지정
		 * @param wrappedException 발생한 Exception 내포함
		 */
		public AuthorizationException(String defaultMessage, Exception wrappedException) {
			this(defaultMessage, null, wrappedException);
		}

		/**
		 * AuthorizationException 생성자
		 * @param defaultMessage 메세지 지정(변수지정)
		 * @param messageParameters 치환될 메세지 리스트
		 * @param wrappedException 발생한 Exception 내포함.
		 */
		public AuthorizationException(String defaultMessage, Object[] messageParameters, Exception wrappedException) {
			String userMessage = defaultMessage;
			if (messageParameters != null) {
				userMessage = MessageFormat.format(defaultMessage, messageParameters);
			}
			this.message = userMessage;
			this.wrappedException = wrappedException;
		}
		/**
		 * AuthorizationException 생성자
		 * @param messageSource 메세지 리소스
		 * @param messageKey 메세지키값
		 */
		public AuthorizationException(MessageSource messageSource, String messageKey) {
			this(messageSource, messageKey, null, null, Locale.getDefault(), null);
		}
		/**
		 * AuthorizationException 생성자
		 * @param messageSource 메세지 리소스
		 * @param messageKey 메세지키값
		 * @param wrappedException 발생한 Exception 내포함.
		 */
		public AuthorizationException(MessageSource messageSource, String messageKey, Exception wrappedException) {
			this(messageSource, messageKey, null, null, Locale.getDefault(), wrappedException);
		}

		public AuthorizationException(MessageSource messageSource, String messageKey, Locale locale, Exception wrappedException) {
			this(messageSource, messageKey, null, null, locale, wrappedException);
		}

		public AuthorizationException(MessageSource messageSource, String messageKey, Object[] messageParameters, Locale locale,
		        Exception wrappedException) {
			this(messageSource, messageKey, messageParameters, null, locale, wrappedException);
		}

		public AuthorizationException(MessageSource messageSource, String messageKey, Object[] messageParameters,
		        Exception wrappedException) {
			this(messageSource, messageKey, messageParameters, null, Locale.getDefault(), wrappedException);
		}

		public AuthorizationException(MessageSource messageSource, String messageKey, Object[] messageParameters,
		        String defaultMessage, Exception wrappedException) {
			this(messageSource, messageKey, messageParameters, defaultMessage, Locale.getDefault(), wrappedException);
		}

		public AuthorizationException(MessageSource messageSource, String messageKey, Object[] messageParameters,
		        String defaultMessage, Locale locale, Exception wrappedException) {
			this.messageKey = messageKey;
			this.messageParameters = messageParameters;
			this.message = messageSource.getMessage(messageKey, messageParameters, defaultMessage, locale);
			this.wrappedException = wrappedException;
		}

	}
