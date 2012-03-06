<%-- 
/** 
 * outline   : 적용페이지검색팝업
 * filename : subPageSearchPopup.jsp
 * @author 범정부통계포털 황기연 
 * @since 2011.09.08
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.09.08     황기연           최초 생성 
 * </pre> 
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>

<%-- Header Start ==========================================================--%>
<jsp:include page="/gps/cmm/admHeader.do"></jsp:include>
<%-- Header End ==========================================================--%>

<%-- javascript start ==============================================--%>
<script type="text/javaScript" language="javascript">
$(document).ready(function(){
	$("#navigation").treeview({
		collapsed:false,
		animated:"fast",
		control:"#sidetreecontrol"
	});
});

<%-- 
/************************************************************************ 
fnc name : fn_menuIdSet                          
outline : 부모창 menuId set
parameter : 없음        
directions : fn_menuIdSet()              
since : 2011-07-05    
author : 통계포털  황기연       

    date      author             note  
 ----------   -------     ------------------- 
 2011.06.10    황기연                                          
************************************************************************/ 
--%>
function fn_menuIdSet(menuId,menuNm){
	$(opener.document).find('#subpageId').val(menuId);
	$(opener.document).find('#subpageNm').val(menuNm);
	window.close();
}

function fn_close(){
	window.close();
}
</script>
<!-- page contents start -->
<div class="contents_area">
	<form:form commandName="menuManageVO" method="post">
		<table class="managerLayer">
			<tr>
				<td class="td01"></td>
				<td class="td02"></td>
				<td class="td03"></td>
			</tr>
			<tr>
				<td class="td04"></td>
				<td class="pl5 cb vt">
				<table class="default">
					<tr>
						<td>
							<c:choose>
								<c:when test="${!empty menuList}">
								<div id="sidetreecontrol"><a href="?#">
									<img src="/images/gps/adm/icon/042.gif" alt="접기" title="접기"/></a>&nbsp;<a href="?#"><img src="/images/gps/adm/icon/041.gif" alt="펼치기" title="펼치기"/></a>
								</div>
								<ul id="navigation">
									<c:forEach var="list" items="${menuList}" varStatus="status">
										<%-- <ul><li> 태그 열기--%>
										<c:out value="${list.ulOpenAt > 0?'<ul>':''}" escapeXml="false"/>
										<c:out value="<li>" escapeXml="false"/>
										<%-- 메뉴명 시작--%>
										<span class="<c:out value="${list.menuTy == 'F'?'folder':'file'}"/>">
											<c:choose>
												<c:when test="${list.menuTy == 'F'}">
													<c:out value="${list.menuNm}"/>
												</c:when>
												<c:otherwise>
													<a href="#" onclick="fn_menuIdSet('<c:out value="${list.menuId}"/>','<c:out value="${list.menuNm}"/>');return false;" onkeypress="this.onclick();"><c:out value="${list.menuNm}"/></a>
												</c:otherwise>
											</c:choose>
										</span>
										<%-- <ul><li> 태그 닫기--%>
										<c:out value="${list.leaf > 0?'</li>':''}" escapeXml="false"/>
										<c:forEach begin="1" end="${list.endTagCnt}" step="1">
											<c:out value="</ul></li>" escapeXml="false"/>
										</c:forEach>
									</c:forEach>
								</ul>
							</c:when>
							<c:otherwise>
								<div><spring:message code="common.search.nodata.msg"/></div>
							</c:otherwise>
						</c:choose>						
						</td>
					</tr>
					<tr>
					<td>
						<table class="rbuttonarea">
						<tr>
							<td class="end"><img src="/images/button/0205.png" alt="<spring:message code='button.close'/>" title="<spring:message code='button.close'/>" style="cursor:hand;" onclick="fn_close();return false;" onkeydown="this.onclick();return false;"/></td>
						</tr>
						</table>
					</td>
			</tr>
					</table>
				</td>
			<td class="td05"></td>
		</tr>
		<tr>
			<td class="td06"></td>
			<td class="td07"></td>
			<td class="td08"></td>
		</tr>
	</table>
	</form:form>
</div>
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>