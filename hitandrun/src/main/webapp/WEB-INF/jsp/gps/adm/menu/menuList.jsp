<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<jsp:include page="/gps/cmm/admHeader.do"></jsp:include>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#navigation").treeview({
			collapsed:false,
			animated:"fast",
			control:"#sidetreecontrol"
		});
	});
	

	//시스템메뉴 등록  화면 호출
	function fn_registerSysMenu(sysSe){
		parent.rightBody.location.href = "/gps/adm/menu/registerSysMenu.do?sysSe="+sysSe+"&rootSysId=<c:out value='${rootSysId}'/>";
	}

	//서브메뉴 수정 화면 호출
	function fn_modifySubMenu(menuId){
		parent.rightBody.location.href = "/gps/adm/menu/modifySubMenu.do?menuId="+menuId;
	}
	</script>
<!-- container start -->
<div class="contents_area">
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
					<table class="title">
					<tr>
						<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="<spring:message code="gps.titleImage"/>" title="<spring:message code="gps.titleImage"/>"/></td>
						<td class="title">메뉴관리</td>
						<td class="rbtn">
							<table class="inner_btn">
							<tr>
								<td><img src="/images/button/0522.png" style="cursor:hand;" alt="<spring:message code="gps.button.systemAdd"/>" title="<spring:message code="gps.button.systemAdd"/>" onclick="fn_registerSysMenu('sys');"/></td>
								<td class="end"><img src="/images/button/0506.png" style="cursor:hand;" alt="<spring:message code="gps.button.siteAdd"/>" title="<spring:message code="gps.button.siteAdd"/>" onclick="fn_registerSysMenu('site');"/></td>
							</tr>
							</table>
						</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table class="default">
					<tr>
						<td>
							<c:choose>
								<c:when test="${!empty menuList}">
								<div id="sidetreecontrol"><a href="?#"><img src="/images/gps/adm/icon/042.gif" alt="접기" title="접기"/></a>&nbsp;<a href="?#"><img src="/images/gps/adm/icon/041.gif" alt="펼치기" title="펼치기"/></a></div>
								<ul id="navigation">
									<c:forEach var="list" items="${menuList}" varStatus="status">
										<%-- <ul><li> 태그 열기--%>
										<c:out value="${list.ulOpenAt > 0 ? '<ul>' : ''}" escapeXml="false"/>
										<c:out value="<li>" escapeXml="false"/>
										<%-- 메뉴명 시작--%>
										<c:choose>
											<c:when test="${list.menuId == '00' || list.menuId eq list.rootSysId}">
												<span class="<c:out value="${list.menuTy == 'A' || list.menuTy == 'F' ? 'folder' : 'file'}"/>"><c:out value="${list.menuNm}"/></span>
											</c:when>
											<c:otherwise>
												<a href="<c:url value="/gps/adm/menu/${!empty list.upperSysId ? 'modifySysMenu.do' : 'modifySubMenu.do'}">
													<c:param name="${!empty list.upperSysId ? 'sysId' : 'menuId'}" value="${list.menuId}"/>
													<c:param name="sysSe" value="${list.sysSe}"/>
													<c:param name="rootSysId" value="${list.rootSysId}"/>
												</c:url>" target="rightBody">
												<span class="<c:out value="${list.menuTy == 'A' || list.menuTy == 'F' ? 'folder' : 'file'}"/>"><c:out value="${list.menuNm}"/></span>
												</a>
											</c:otherwise>
										</c:choose>
										<c:if test="${list.useAt == 'N'}">
										<span><img src="/images/gps/adm/icon/027.gif" alt="<spring:message code="gps.menuUseAt"/>" title="<spring:message code="gps.menuUseAt"/>"/></span>
										</c:if>
										<%-- <ul><li> 태그 닫기--%>
										<c:out value="${list.leaf > 0 ? '</li>' : ''}" escapeXml="false"/>
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
</div>
<!-- container end -->
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>