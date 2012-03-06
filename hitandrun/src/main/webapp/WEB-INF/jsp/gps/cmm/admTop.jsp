<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
*******************************************************************************
* @source      : header.jsp
* @description : 헤더  JSP - 세션/사용자/권한 처리
*                   
*******************************************************************************
* DATE         AUTHOR    DESCRIPTION
*==============================================================================
* 2011-04-18   이진우           최초작성
*******************************************************************************
--%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<%-- Header Start ==========================================================--%>
<jsp:include page="/gps/cmm/admHeader.do"></jsp:include>
<%-- Header Start ==========================================================--%>
<script type="text/javascript">
	function actionLogout(){
		window.parent.location.href="<c:url value='/gps/login/actionLogout.do'/>?returnUrl=<c:out value="${gpsLoginVO.returnUrl}"/>";
	}
	
	function fn_index(menuId){
		window.parent.location.href="<c:url value='/gps/adm/index.do'/>?menuId="+menuId;
	}
</script>
<%-- 자바 스크립트  End  ===================================================--%>

<form:form commandName="gpsLoginVO" method="post">
<form:hidden path="returnUrl"/>
<%-- 상단 메뉴 영역  Start =================================================--%>
	<div class="ui-layout-north">
		<div class="topContents">
			<div class="top_infor">
				<ul>
					<li><c:out value="${gpsSessionVO.usrNm}"/>님</li>
				</ul>
			</div>
			<div class="top_btn">
				<ul>
					<li><img src="/images/gps/adm/head/top_icon_logout_on.gif" alt="<spring:message code="gps.login.logout"/>" title="<spring:message code="gps.login.logout"/>" style="cursor:hand;" onclick="actionLogout()"></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="tab clfix">
		<ul>
	<c:forEach var="list" items="${menuList}" varStatus="status">
    		<li <c:out value="${menuManageVO.menuId == list.menuId?'class=\"on\"':''}" escapeXml="false"/>><p><a href="#" onclick="fn_index('<c:out value="${list.menuId}"/>'); return false;" onkeydown="this.onclick(); return false;"><c:out value="${list.menuNm}"/></a></p></li>
	</c:forEach>
		</ul>
	</div>
<%-- 상단 메뉴 영역  End ===================================================--%>
</form:form>
<%-- Footer Start ==========================================================--%>
<%@include file="/WEB-INF/jsp/gps/cmm/admBottom.jsp" %>
<%-- Footer End ============================================================--%>