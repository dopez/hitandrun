<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%-- 공통 taglib 선언 start	=================================================--%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<%-- 공통 taglib 선언 end	=====================================================--%>

<c:choose>
<c:when test="${userRegisterVO.scriptGb eq 'Y'}">
	<script language="JavaScript">
		window.opener.location.href='<c:url value="/gps/user/actionRegistUserForm.do"><c:param name="retInfo" value="${userRegisterVO.retInfo}"/><c:param name="menuId" value="${userRegisterVO.menuId}"/><c:param name="leftMenuId" value="${userRegisterVO.leftMenuId}"/></c:url>';
		self.close();
	</script>
</c:when>
<c:otherwise>
	<c:redirect url="/gps/user/actionRegistUserForm.do">
		<c:param name="retInfo" value="${userRegisterVO.retInfo}"/>
		<c:param name="menuId" value="${userRegisterVO.menuId}"/>
		<c:param name="leftMenuId" value="${userRegisterVO.leftMenuId}"/>
	</c:redirect>
</c:otherwise>
</c:choose>
