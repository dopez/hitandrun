<%-- 
/** 
 * 설명   : 통합권한 관리 - 권한 조회/수정화면
 * 파일명 : iamUpdate.jsp
 * @author 범정부통계포털 이관형 
 * @since 2011.08.18 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.08.18     이관형           최초 생성 
 * </pre> 
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>

<%-- Header Start ==========================================================--%>
<jsp:include page="/gps/cmm/admHeader.do"></jsp:include>
<%-- Header End ==========================================================--%>

<%-- 선행 로직 start	======================================================--%>
<script type="text/javaScript" language="javascript">
</script>
<%-- 선행 로직 end	==========================================================--%>

<%-- javascript start ==============================================--%>
<script type="text/javaScript" language="javascript">
JQ_setValidation('authorManageVO');

<%-- 
/************************************************************************ 
fnc name : fn_list                                   
outline :  권한 목록 화면 으로 이동한다.
parameter : 없음
directions : fn_list()              
since : 2011-08-18   
author : 범정부통계포털 이관형       

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_list() {
	JQ_request("authorManageVO", "<c:url value='/gps/adm/author/intergrated/selectIamMenuList.do'/>");
}

<%-- 
/************************************************************************ 
fnc name : fn_update                                   
outline :  권한정보를 수정한다.
parameter : 없음
directions : fn_update()              
since : 2011-08-18   
author : 범정부통계포털 이관형       

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_update() {

	if(confirm("<spring:message code='common.update.msg' />")){
		JQ_request("authorManageVO", "<c:url value='/gps/adm/author/intergrated/updateIam.do'/>");
	}
}

<%-- 
/************************************************************************ 
fnc name : fn_delete                                   
outline :  권한정보를 삭제한다.
parameter : 없음
directions : fn_delete()              
since : 2011-08-18   
author : 범정부통계포털 이관형       

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_delete() {

	if(confirm("<spring:message code='common.delete.msg' />")){
		JQ_request("authorManageVO", "<c:url value='/gps/adm/author/intergrated/deleteIam.do'/>");
	}
}

</script>
<%-- javascript end ============================================--%>
<!-- contents_area start -->
<div class="contents_area">
	<form:form commandName="authorManageVO" name="insertForm" method="post">
	<form:hidden path="upperMenuId"/>
	<form:hidden path="sysId"/>
	<form:hidden path="authorId"/>
	<form:hidden path="authorNm"/>
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
						<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="타이틀이미지" title="타이틀이미지"/></td>
						<td class="title">권한수정</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table summary="권한등록" class="write01">
					<caption>권한등록</caption>
					<tr>
						<th class="subject">시스템명</th>
						<td class="text"><c:out value="${authorManageVO.sysNm}" /></td>
					</tr>
					<c:if test="${null != authorManageVO.upperAuthorNm}">
						<tr>
							<th class="subject">상위권한명</th>
							<td class="text"><c:out value="${authorManageVO.upperAuthorNm}" /></td>
						</tr>
					</c:if>
					<tr>
						<th class="subject">권한명</th>
						<td class="text"><c:out value="${authorManageVO.authorNm}" /></td>
					</tr>
					<tr>
						<th class="subject">권한 설명</th>
						<td class="input"><form:textarea path="authorDc" rows="5" cols="65" cssClass="validate[optional,length[0,255]]" /></td>
					</tr>
					<tr>
						<th class="subject">최초 작성자</th>
						<td class="text"><c:out value="${authorManageVO.registerId}" /></td>
					</tr>
					<tr>
						<th class="subject">최초 작성일</th>
						<td class="text"><c:out value="${authorManageVO.registDt}" /></td>
					</tr>
				<c:if test="${null != authorManageVO.updtDt}">
					<tr>
						<th class="subject">최종 수정일</th>
						<td class="text"><c:out value="${authorManageVO.updtDt}" /></td>
					</tr>
				</c:if>
				<c:if test="${null != authorManageVO.updtusrId}">
					<tr>
						<th class="subject">최종 수정자</th>
						<td class="text"><c:out value="${authorManageVO.updtusrId}" /></td>
					</tr>
				</c:if>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table class="rbuttonarea">
					<tr>
						<td><a onclick="javaScript:fn_update();" style="cursor:hand"><img src="/images/button/0209.png" alt="<spring:message code="button.update" />" title="<spring:message code="button.update" />" style="cursor:pointer;" ></a></td>
						<td><a onclick="javaScript:fn_list();" style="cursor:hand"><img src="/images/button/0203.png" alt="<spring:message code="button.reset" />" title="<spring:message code="button.reset" />" style="cursor:pointer;" ></a></td>
						<td><a onclick="javaScript:fn_delete();" style="cursor:hand"><img src="/images/button/0204.png" alt="<spring:message code="button.delete" />" title="<spring:message code="button.delete" />" style="cursor:pointer;" ></a></td>			
					</tr>
					</table>
				</td>
			</tr>
			</table>
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