<%-- 
/** 
 * outline   : 일정관리 일정목록 조회화면
 * filename : scheduleList.jsp
 * @author 범정부통계포털 이진우 
 * @since 2011.08.04
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.08.04     이진우           최초 생성 
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
<!--
JQ_onload();
<%-- 
/************************************************************************ 
함수명 : fncPageOnload                                   
설   명 : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
인   자 : 없음        
사용법 : fncPageOnload()              
작성일 : 2011-08-04   
작성자 : 범정부통계포털 이진우       

      date    author      note 
 ----------   -------     ------------------- 
 
************************************************************************/
--%>
function fncPageOnload(){
	//frameset controller start
	//frameset controller end
}
<%-- 
/************************************************************************ 
함수명 : link_page                                   
설   명 : 페이지 이동 함수
		조회된 목록에서 다른 페이지 번호를 누르면 해당 페이지로 이동
인   자 : pageNo        
사용법 : link_page(pageNo)              
작성일 : 2011-08-04   
작성자 : 범정부통계포털 이진우       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function link_page(pageNo){
	document.listForm.pageIndex.value = pageNo;
	JQ_request("scheduleManageVO", "<c:url value='/gps/adm/schedule/selectScheduleList.do'/>");
}

<%--
/************************************************************************ 
함수명 : fn_search                                   
설   명 : 조회조건에 따라 목록을 조회하는 함수
인   자 : 없음(form안에 존재하는 값)
사용법 : fn_search()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이진우       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_search(){
	document.listForm.pageIndex.value = 1;
 	JQ_setValueObj('pageUnit', document.listForm.pageUnit.value); 
   	JQ_request("scheduleManageVO", "<c:url value='/gps/adm/schedule/selectScheduleList.do'/>", "scheduleManageVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_regist                                   
설   명 : 일정 등록 화면으로 이동하는 함수
인   자 : 없음
사용법 : fn_regist()              
작성일 : 2011-08-04   
작성자 : 범정부통계포털 이진우       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
 function fn_regist(){
   	JQ_request("scheduleManageVO", "<c:url value='/gps/adm/schedule/registSchedule.do'/>", "scheduleManageVO");
}
<%-- 
/************************************************************************ 
함수명 : fn_modify                                   
설   명 : 일정 수정/상세 화면으로 이동하는 함수
인   자 : 없음
사용법 : fn_modify()              
작성일 : 2011-08-04   
작성자 : 범정부통계포털 이진우       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_modify(scSn){
 	JQ_setValueObj('scSn', scSn); 
   	JQ_request("scheduleManageVO", "<c:url value='/gps/adm/schedule/modifySchedule.do'/>", "scheduleManageVO");
}
-->
</script>
<!-- page contents start -->
<div class="contents_area">
	<form:form commandName="scheduleManageVO" name="listForm" method="post">
	<form:hidden path="scSn"/>
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
							<td class="title">일정관리</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="default">
						<tr>
							<td class="tl">
								<table class="option">
								<tr>
									<td class="first">출력목록수</td>
									<td class="input">
										<form:select path="pageUnit" id="pageUnit" onchange="javaScript:fn_search();" >
											<form:option value="10" label="10"/>
											<form:option value="20" label="20" /> 
											<form:option value="30" label="30" /> 
											<form:option value="50" label="50" /> 
											<form:option value="100" label="100" /> 
										</form:select>
									</td>
									<td class="text">정렬순서</td>
									<td>
										<form:select path="orderByClause" onchange="javaScript:fn_search();" >
											<form:option value="" label="- 선택 -"/>
											<form:option value="1" label="일정명" /> 
											<form:option value="2" label="등록일" /> 
										</form:select>
									</td>
									<td class="text">일정구분</td>
									<td class="input">
										<form:select path="typeCond" onchange="javaScript:fn_search();" >
											<form:option value="" label="- 선택 -"/>
											<form:options items="${typeCond}" />
										</form:select>
									</td>
									<td class="input">
										<form:select path="searchCondition" >
											<form:option value="" label="- 선택 -"/>
											<form:option value="1" label="일정명" />
											<form:option value="2" label="장소" />
										</form:select>
									</td>
									<td class="input"><form:input path="searchKeyword" id="searchKeyword" cssClass="wi200"/></td>
									<td class="input"><img src="/images/button/0208.png" alt="<spring:message code='button.search'/>" title="<spring:message code='button.search'/>" onclick="fn_search()"></td>
								</tr>
								</table>
							</td>
							<td>
								<dl class="board_counter">
									<dt class="total">전체</dt>
									<dd class="total"><c:out value='${paginationInfo.totalRecordCount}'/></dd>
									<dt class="page">페이지</dt>
									<dd class="page"><c:out value='${paginationInfo.currentPageNo}'/> / <c:out value='${paginationInfo.totalPageCount }'/></dd>
								</dl>
							</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr><td class="ht2"></td></tr>
				<tr>
					<td>
						<table class="datalist01" summary="일정관리">
						<caption>일정리스트</caption>
						<colgroup>
							<col class="column1"/>
							<col class="column3"/>
							<col class="column3"/>
							<col class="column3"/>
							<col class="column3"/>
						</colgroup>
						<thead>
						<tr>
							<th scope="col">번호</th>
							<th scope="col">제목</th>
							<th scope="col">구분</th>
							<th scope="col">장소</th>
							<th scope="col">일정</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach var="resultInfo" items="${resultList}" varStatus="status">
								<tr onmouseover="this.style.backgroundColor='#fafafa'" onmouseout="this.style.backgroundColor=''">
									<td class="tc"><c:out value="${(scheduleManageVO.pageIndex-1) * scheduleManageVO.pageUnit + status.count}"/></td> 
									<td class="tc" >
									<a href="#LINK" onclick="javascript:fn_modify('${resultInfo.scSn}');" >${resultInfo.subject}</a>
									</td>
									<td class="tc">
										<c:out value="${resultInfo.scTyNm}" />
										<c:if test="${resultInfo.scTy=='02' }">
											(<c:out value="${resultInfo.statNm}" />)
										</c:if>
									</td>
									<td class="tc" >${resultInfo.place}</td>
									<td class="tc"><c:out value="${resultInfo.startDt}" /> ~ <c:out value="${resultInfo.endDt}" /></td>
								</tr>
							 </c:forEach>
							<c:if test="${fn:length(resultList) == 0}">
								<tr> 
									<td class="lt_text3" colspan="10">
										<spring:message code="common.nodata.msg" />
									</td>
								</tr>   	          				 			   
							</c:if>
						</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="datapaging" align="center">
						<tr>
							<td class="tc">
								<ui:pagination paginationInfo = "${paginationInfo}"	type="image" jsFunction="link_page" />
								<input name="pageIndex" type="hidden" value="<c:out value='${scheduleManageVO.pageIndex}'/>"/>
							</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="default">
						<tr>
							<td class="tl">
								<table class="option">
								<tr>
									
								</tr>
								</table>
							</td>
							<td class="rbtn">
								<table class="inner_btn" align="right">
									<tr>
										<td>
											<img src="/images/button/0202.png" alt="<spring:message code='button.create'/>" title="<spring:message code='button.create'/>" onclick="fn_regist()">
										</td>
									</tr>
								</table>
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
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>