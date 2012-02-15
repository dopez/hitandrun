package com.bbm.common.cmm.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

public class SelectedAnnotationHandlerMapping extends
		DefaultAnnotationHandlerMapping {

	/** 포함 URL **/
	private List<String> urls;

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	/** ignore URL **/
	private List<String> ignoreUrls;

	public void setIgnoreUrls(List<String> ignoreUrls) {
		this.ignoreUrls = ignoreUrls;
	}

	public String[] getFiltered(String strs[]) {
		if (strs == null) {
			return null;
		}
		List<String> tmpList = new ArrayList<String>();
		int i = strs.length;
		boolean bIgnoreUrl = false;
		
		for (int j = 0; j < i; j++) {
			String str = strs[j];
			bIgnoreUrl = false;

			// ignoreUrl에 포함되어 있으면 interceptor를 안탐.
			for (String ignoreUrl : ignoreUrls) {
				Pattern pattern = Pattern.compile(ignoreUrl, Pattern.CASE_INSENSITIVE);
		        if (pattern.matcher(str).find(0) ) {
		        	bIgnoreUrl = true;
		        }
			}
			
			
			// ignoreUrl이 아니면 
			if( ! bIgnoreUrl ) {
				for (String url : urls) {
					Pattern pattern = Pattern
							.compile(url, Pattern.CASE_INSENSITIVE);
					if (pattern.matcher(str).find(0) ) {
						tmpList.add(str);
					}
				}
			}
		}
		return (String[]) tmpList.toArray(new String[tmpList.size()]);
	}

	protected String[] determineUrlsForHandler(String s) {
		return getFiltered(super.determineUrlsForHandler(s));
	}
}
