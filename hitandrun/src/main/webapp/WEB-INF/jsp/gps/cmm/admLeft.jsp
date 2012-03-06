<%--
**********************************************************************************
* @source      : admLeft.jsp
* @description : 포털관리자LEFT
*                   
**********************************************************************************
* DATE         AUTHOR    DESCRIPTION
*=================================================================================
* 2011-04-18   이진우           최초작성
**********************************************************************************
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<%-- Header Start =============================================================--%>
<jsp:include page="/gps/cmm/admHeader.do" flush="false"></jsp:include>

<script type="text/javascript">

	$(document).ready(function(){
		$("#navigation").treeview({
			collapsed:false,
			animated:"fast"
		});
	});

</script>
<%-- 자바 스크립트  End  =======================================================--%>
	
<%-- 좌측 메뉴 영역  Start =====================================================--%>
<div class="ui-layout-west">
<c:if test="${fn:length(menuList) > 0}">
	<table class="managerLayer">
	<tr>
		<td class="td01"></td>
		<td class="td02"></td>
		<td class="td03"></td>
	</tr>
	<tr>
		<td class="td04"></td>
		<td class="pl5 cb vt">
</c:if>		
			<ul id="navigation">
				<c:forEach var="list" items="${menuList}" varStatus="status">
					<%-- <ul><li>태그 열기--%>
					<c:out value="${list.ulOpenAt > 0?'<ul>':''}" escapeXml="false"/>
					<c:out value="<li>" escapeXml="false"/>
					<%-- 메뉴명 시작--%>
					<c:choose>
						<c:when test="${list.menuTy == 'A' || list.menuTy == 'F'}">
							<span class="folder"><c:out value="${list.menuNm}"/></span>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${!empty list.menuUrl}">
									<a href="<c:url value="${list.menuUrl}">
												<c:choose>
													<c:when test="${list.sysUseTy eq 'Y'}">
														<c:param name="upperMenuId" value="${list.upperMenuId}"/>
													</c:when>
													<c:otherwise>
														<c:param name="menuId" value="${list.menuId}"/>
														<c:param name="leftMenuId" value="${list.upperMenuId}"/>
														<c:param name="bbsId" value="${list.bbsId}"/>
													</c:otherwise>
												</c:choose>
											</c:url>" target="frmbody">
									<span class="file"><c:out value="${list.menuNm}"/></span>
									</a>
								</c:when>
								<c:otherwise>
									<a href="javascript:alert('메뉴 중비중입니다.');"><span class="file"><c:out value="${list.menuNm}"/></span></a>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
					<%-- <ul><li> 태그 닫기--%>
					<c:out value="${list.leaf > 0?'</li>':''}" escapeXml="false"/>
					<c:forEach begin="1" end="${list.endTagCnt}" step="1">
						<c:out value="</ul></li>" escapeXml="false"/>
					</c:forEach>
				</c:forEach>
			</ul>
<c:if test="${fn:length(menuList) > 0}">
		</td>
		<td class="td05"></td>
	</tr>
	<tr>
		<td class="td06"></td>
		<td class="td07"></td>
		<td class="td08"></td>
	</tr>
	</table>
</c:if>
</div>
<%-- 좌측 메뉴 영역 End =====================================================--%>

<%-- Footer Start ===========================================================--%>
<jsp:include page="/gps/cmm/admBottom.do" flush="false"></jsp:include>
<%-- Footer End =============================================================--%>