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
<jsp:include page="/sps/cmm/header.do" flush="false"/> 
<%-- Header Start ==========================================================--%>

<script type="text/javascript" src="/js/sps/sds/cmm/framectl.js"></script>

<script type="text/javascript">
	function actionLogout(){
		location.href="<c:url value='/gps/login/devActionLogout.do'/>";
	}
</script>
<%-- 자바 스크립트  End  ===================================================--%>

<form:form commandName="loginVO" name="loginForm"  method="post">
<%-- 상단 메뉴 영역  Start =================================================--%>
	<div class="ui-layout-north">
		<div class="top_color"></div>
		<div class="top_infor">
			<ul>
				<li><a href="#" onclick="actionLogout()"><img src="/images/gps/adm/icon/043.gif" alt="로그아웃"  title="로그아웃"></a>
				<c:out value="${gpsSessionVO.usrNm}"/></li>
			</ul>
		</div>		
	</div>
<%-- 상단 메뉴 영역  End ===================================================--%>
</form:form>

<%-- Footer Start =========================================================--%>
<jsp:include page="/sps/cmm/bottom.do" flush="false"/>
<%-- Footer End ===========================================================--%>