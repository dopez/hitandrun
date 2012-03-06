<%-- 
/** 
 * outline   : 통합권한 관리 - 권한목록 기본화면
 * filename : iamDefaultList.jsp
 * @author 범정부통계포털 이관형 
 * @since 2011.08.23
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.08.23     이관형           최초 생성 
 * </pre> 
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<jsp:include page="/gps/cmm/admHeader.do"></jsp:include>

<%-- javascript start ==============================================--%>
<script type="text/javaScript" language="javascript">
JQ_onload();

<%-- 
/************************************************************************ 
함수명 : fncPageOnload                                   
설   명 : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)
인   자 : 없음        
사용법 : fncPageOnload()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 
 
************************************************************************/
--%>
function fncPageOnload() {
	// 권한을 삭제하였을 경우
	<c:if test="${!empty message}">
		parent.leftBody.location.href = "/gps/adm/author/intergrated/selectIamList.do?sysId=" + $("#sysId").val() + "&upperMenuId=" + $("#upperMenuId").val();
	</c:if>
	//frameset controller start
	//frameset controller end
}
</script>
<!-- container start -->
<div class="contents_area">
	<form:form commandName="authorManageVO" name="defaultForm" method="post">
	<form:hidden path="upperMenuId"/>
	<form:hidden path="authorId"/>
	<form:hidden path="sysId"/>
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
						<td class="title">
			<c:choose>
				<c:when test="${!empty message}">
						<c:out value="${message}"/>
				</c:when>
				<c:otherwise>
					<c:out value="권한을 선택하여 주십시오."></c:out>
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
	</form:form>
</div>
<!-- container end -->
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>