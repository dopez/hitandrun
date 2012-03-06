<%-- 
/** 
 * outline  : 게시판DB현황
 * filename : bbsDbList.jsp
 * @author 범정부통계포털 황기연 
 * @since 2011.06.23
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.06.23     황기연             최초 생성 
 * </pre> 
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>

<%-- Header Start ==========================================================--%>
<jsp:include page="/gps/cmm/admHeader.do"></jsp:include>
<%-- Header End ==========================================================--%>

<script type="text/javascript">
<%-- 
/************************************************************************ 
fnc name : fn_registerBbsDbPopup                                 
outline : 게시판DB수정 팝업페이지 호출
parameter : 없음        
directions : fn_registerBbsDbPopup()  
since : 2011-07-04   
author : 통계포털  황기연       

    date      author             note  
 ----------   -------     ------------------- 
 2011.07.04    황기연                                          
************************************************************************/ 
--%>
function fn_registerBbsDbPopup(){
	gfn_postPopupWin("bbsDbVO","/gps/adm/bbs/registerBbsDbPopup.do","게시판DB입력페이지팝업",400,600, "yes","no",0);
}

<%-- 
/************************************************************************ 
fnc name : fn_modifyBbsDbPopup                                 
outline : 게시판DB수정페이지팝업호출
parameter : 없음        
directions : fn_modifyBbsDbPopup()  
since : 2011-07-04   
author : 통계포털  황기연       

    date      author             note  
 ----------   -------     ------------------- 
 2011.07.04    황기연                                          
************************************************************************/ 
--%>
function fn_modifyBbsDbPopup(dbTname){
	$('#dbTname').val(dbTname);
	gfn_postPopupWin("bbsDbVO","/gps/adm/bbs/modifyBbsDbPopup.do","게시판DB수정페이지팝업",400,600, "yes","no",0);
}


<%-- 
/************************************************************************ 
fnc name : fn_bbsInfoList                                 
outline : 게시판목록호출
parameter : 없음        
directions : fn_bbsInfoList()  
since : 2011-07-04   
author : 통계포털  황기연       

    date      author             note  
 ----------   -------     ------------------- 
 2011.07.04    황기연                                          
************************************************************************/ 
--%>
function fn_bbsInfoList(dbTname){
	$('#dbTname').val(dbTname);
	$('#bbsDbVO').attr('target','rightBody');
	$('#bbsDbVO').attr('action','/gps/adm/bbs/selectBbsInfoList.do').submit();
}

</script>
<!-- container start -->
<div class="ui-layout-content">
	<div class="contents_area">
		<form:form commandName="bbsDbVO" method="post">
		<form:hidden path="dbTname"/>
			<table class="managerLayer">
				<tr>
					<td class="td01"></td>
					<td class="td02"></td>
					<td class="td03"></td>
				</tr>
				<tr>
					<td class="td04"></td>
					<td class="pl5 cb vt">
						<table class = "default">
						<tr>
							<td>
								<table class="title">
								<tr>
									<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="<spring:message code="gps.titleImage"/>" title="<spring:message code="gps.titleImage"/>"/></td>
									<td class="title">게시판관리</td>
									<td class="btn"><img src="/images/button/0202.png" alt="<spring:message code='button.create'/>" title="<spring:message code='button.create'/>" style="cursor:hand;" onclick="fn_registerBbsDbPopup();return false;" onkeydown="this.onclick();return false;"/></td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table class="datalist01" summary="게시판목록">
								<colgroup>
									<col class="column2"/>
									<col class="column2"/>
								</colgroup> 
								<thead>
								<tr>
									<th scope="col">게시판DB아이디</th>
									<th scope="col" class="end">게시판DB명</th>
								</tr>
								</thead>
								<tbody>
						<c:choose>
							<c:when test="${!empty bbsDbList}">
							<c:forEach var="list" items="${bbsDbList}" varStatus="status">
								<tr>
									<td class="column2"><span style="cursor:hand;" onclick="fn_modifyBbsDbPopup('<c:out value="${list.dbTname}"/>');return false;" onkeydown="this.onclick();return false;"><c:out value="${list.dbTname}"/></span></td>
									<td class="column2"><span style="cursor:hand;" onclick="fn_bbsInfoList('<c:out value="${list.dbTname}"/>');return false;" onkeydown="this.onclick();return false;"><c:out value="${list.dbName}"/></span></td>
								</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="2" class="blank"><spring:message code="common.search.nodata.msg" /></td>
								</tr>
							</c:otherwise>
						</c:choose>
								</tbody>
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
</div>
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>