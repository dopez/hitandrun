<%-- 
/** 
 * outline   : 통합권한 관리 - 메뉴권한등록화면
 * filename : registerMenuAuthor.jsp
 * @author 범정부통계포털 황기연 
 * @since 2011.08.24
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.08.24     황기연           최초 생성 
 * </pre> 
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>

<%-- Header Start ==========================================================--%>
<jsp:include page="/gps/cmm/admHeader.do"></jsp:include>
<%-- Header End ==========================================================--%>
<script src="/js/sps/sds/scm/jquery-ui-1.8.12.custom.min.js"	type="text/javascript"></script> 
<script src="/js/sps/sds/scm/jquery.checkboxtree.js"			type="text/javascript"></script>
<%-- javascript start ==============================================--%>
<script type="text/javaScript" language="javascript">
JQ_onload(); 
function fncPageOnload()
{	
	$('#browser').checkboxTree({
		 collapseImage: '/images/tree/folder.gif',
	     expandImage: '/images/tree/folder-closed.gif',
	     leafImage: '/images/tree/file.gif'
	});
	<c:if test="${!empty message}">
		alert('<c:out value="${message}"/>');
		window.opener.parent.rightBody.location.href = "/gps/adm/author/intergrated/selectIamMenuList.do?authorId=" + $("#authorId").val() + "&sysId=" + $("#sysId").val() + "&authorNm=" + escape(encodeURIComponent($("#authorNm").val()));
	</c:if>
}

<%--
/************************************************************************ 
fnc name : fn_insertMenuAuthor                                   
outline : 메뉴 권한등록처리
parameter : 없음        
directions : fn_insertMenuAuthor()              
since : 2011-08-23
author : 통계포털 황기연

    date      author             note  
 ----------   -------     ------------------- 

************************************************************************/
--%>
function fn_insertMenuAuthor(){
	var checkVal = new Array();
	$("[name^=chkbox]:checked").each(function(){
		checkVal.push($(this).val());
	})	
	$('#menuNoList').val(checkVal);
	JQ_setProcessMsg();
	JQ_request("menuManageVO", "<c:url value='/gps/adm/author/intergrated/insertMenuAuthor.do'/>");
}

<%--
/************************************************************************ 
fnc name : fn_close                                   
outline : 창닫기
parameter : 없음        
directions : fn_close()              
since : 2011-08-23
author : 통계포털 황기연

    date      author             note  
 ----------   -------     ------------------- 

************************************************************************/
--%>
function fn_close(){
	window.close();
}
</script>
<!-- page contents start -->
<div class="contents_area">
	<form:form commandName="menuManageVO" method="post">
	<form:hidden path="sysId"/>
	<form:hidden path="authorId"/>
	<form:hidden path="authorNm"/>
	<form:hidden path="menuId"/>
	<form:hidden path="menuNoList"/>
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
							<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="타이틀이미지" title="타이틀이미지" /></td>
							<td class="title">메뉴 목록</td>
						</tr>
					</table>
				</td>
				<td>
					<table class="rbuttonarea">
					<tr>
						<td><img style="cursor:hand;" onclick="fn_insertMenuAuthor();" src="/images/button/0210.png" alt="<spring:message code="button.save"/>" title="<spring:message code="button.save"/>"></td>
						<td><img style="cursor:hand;" onclick="fn_close();" src="/images/button/0205.png" alt="<spring:message code="button.close"/>" title="<spring:message code="button.close"/>"></td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<c:choose>
						<c:when test="${!empty menuList}">
						<ul id="browser" class="gpstree">
							<c:forEach var="list" items="${menuList}" varStatus="status">
								<%-- <ul><li> 태그 열기--%>
								<c:out value="${list.ulOpenAt > 0?'<ul>':''}" escapeXml="false"/>
								<c:out value="<li>" escapeXml="false"/>
								<%-- 메뉴명 시작--%>
								<input type="checkbox" name="chkbox" class="noline" value="${list.menuNo}" <c:out value="${list.menuAuthorAt}"/>/>
								<c:out value="${list.menuNm}"/>
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