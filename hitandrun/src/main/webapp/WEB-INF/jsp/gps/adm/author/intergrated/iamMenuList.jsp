<%-- 
/** 
 * outline   : 통합권한 관리 - 권한 메뉴목록 조회화면
 * filename : iamMenuList.jsp
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>

<%-- Header Start ==========================================================--%>
<jsp:include page="/gps/cmm/admHeader.do"></jsp:include>
<%-- Header End ==========================================================--%>

<%-- javascript start ==============================================--%>
<script type="text/javaScript" language="javascript">
JQ_onload();

<%-- 
/************************************************************************ 
함수명 : fncPageOnload                                   
설   명 : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
인   자 : 없음        
사용법 : fncPageOnload()              
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형       

  date         author            note
 ----------   -------     ------------------- 
                              
************************************************************************/ 
--%>
function fncPageOnload() {
	<c:if test="${!empty message}">
		alert('<c:out value="${message}"/>');
		parent.leftBody.location.href = "/gps/adm/author/intergrated/selectIamList.do?sysId=" + $("#sysId").val() + "&authorId=" + $("#authorId").val() + "&authorNm=" + escape(encodeURIComponent($("#authorNm").val())) + "&upperMenuId=" + $("#upperMenuId").val();
	</c:if>
	//frameset controller start
	//frameset controller end
}

$(document).ready(function(){
	$("#navigation").treeview({
		collapsed:false,
		animated:"fast",
		control:"#sidetreecontrol"
	});
});

<%-- 
/************************************************************************ 
함수명 : fn_registerMenuAuthor                               
설   명 : 메뉴 연결 화면 이동 함수
		메뉴 연결 화면으로 이동한다.
인   자 : menuId,authorId
사용법 : fn_registerMenuAuthor()
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형       

      date    author            note
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
function fn_registerMenuAuthor(menuId,authorId){
	$('#menuId').val(menuId);
	$('#authorId').val(authorId);
	gfn_postPopupWin("menuManageVO", "/gps/adm/author/intergrated/registerMenuAuthor.do", "메뉴생성",700,500, "yes", 1);
}

<%-- 
/************************************************************************ 
함수명 : fn_userlist                               
설   명 : 사용자 목록 화면으로 이동 하는 함수
		사용자 목록 화면으로 이동한다.
인   자 : 없음
사용법 : fn_userlist()
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형       

      date    author            note
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
function fn_userlist() {
	JQ_request("authorManageVO", "<c:url value='/gps/adm/author/intergrated/selectIamUserList.do'/>");
}

<%-- 
/************************************************************************ 
함수명 : fn_menulist                               
설   명 : 메뉴 목록 화면으로 이동 하는 함수
		메뉴 목록 화면으로 이동한다.
인   자 : 없음
사용법 : fn_menulist()
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형       

      date    author            note
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
function fn_menulist() {
	JQ_request("authorManageVO", "<c:url value='/gps/adm/author/intergrated/selectIamMenuList.do'/>");
}

</script>
<!-- page contents start -->
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
			<form:form commandName="authorManageVO" name="tabForm" method="post">
			<tr>
				<td>
					<table class="title">
					<tr>
						<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="<spring:message code="gps.titleImage"/>" title="<spring:message code="gps.titleImage"/>"/></td>
						<td class="title"><font class="r"><c:out value="${authorManageVO.authorNm}" /></font> 권한 연결 메뉴 현황</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<form:hidden path="upperMenuId"/>
					<form:hidden path="sysId"/>
					<form:hidden path="authorId"/>
					<form:hidden path="authorNm"/>
					<div class="contents_tab clfix">
						<ul>
							<li class="on"><p><a onclick="javascript:fn_menulist();"><c:out value="${authorManageVO.authorNm}" /> 권한 연결 메뉴</a></p></li>
							<li><p><a onclick="javascript:fn_userlist();"><c:out value="${authorManageVO.authorNm}" /> 권한 연결사용자</a></p></li>
						</ul>
					</div>
				</td>
			</tr>
			</form:form>
			<tr>
				<td>
					<form:form commandName="menuManageVO" method="post">
					<form:hidden path="sysId"/>
					<form:hidden path="menuId"/>
					<form:hidden path="authorId"/>
					<form:hidden path="authorNm"/>
					<table class="default">
					<tr>
						<td class="pb5"><img style="cursor:hand;" onclick="fn_registerMenuAuthor('<c:out value="${menuManageVO.menuId}"/>','<c:out value="${menuManageVO.authorId}"/>');" src="/images/gps/adm/author/0459.png" alt="<spring:message code="gps.button.menuCreat"/>" title="<spring:message code="gps.button.menuCreat"/>"></td>
					</tr>
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
										<span class="<c:out value="${list.menuTy == 'F'?'folder':'file'}"/>"><c:out value="${list.menuNm}"/></span>
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
				</form:form>
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
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>