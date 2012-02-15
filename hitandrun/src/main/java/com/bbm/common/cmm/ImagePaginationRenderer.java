package com.bbm.common.cmm;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

public class ImagePaginationRenderer extends AbstractPaginationRenderer {
	
	public ImagePaginationRenderer() {
		firstPageLabel    = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \"><img src=\"/images/common/icon_back_10.gif\" alt=\"처음\" title=\"처음\" class=\"firstpage\"/></a>"; 
        previousPageLabel = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \"><img src=\"/images/common/icon_back.gif\"    alt=\"이전\" title=\"이전\" class=\"previouspage\"/></a>";
        currentPageLabel  = "<font class=\"paging_strong\">{0}</font>"; 
        otherPageLabel    = "<font class=\"paging_font\"><a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \">{2}</a></font>";
        nextPageLabel     = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \"><img src=\"/images/common/icon_front.gif\"    alt=\"다음\" title=\"다음\" class=\"nextpage\"/></a>";
        lastPageLabel     = "<a href=\"?pageIndex={1}\" onclick=\"{0}({1});return false; \"><img src=\"/images/common/icon_front_10.gif\" alt=\"마지막\" title=\"마지막\" class=\"lastpage\"/></a>";

	}
}
