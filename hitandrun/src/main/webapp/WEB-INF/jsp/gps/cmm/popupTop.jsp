<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
*******************************************************************************
* @source      : top.jsp
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
<jsp:include page="/gps/cmm/popupHeader.do"></jsp:include>
<script type="text/javascript">
</script>
<%-- Header Start ==========================================================  --%>
	<%-- 상단 메뉴 영역  start --%>
	<div class="popupHeader">
		<form:form commandName="menuManageVO" method="post">
		</form:form>
	</div>