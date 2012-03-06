<%-- 
/** 
 * outline   : 일정검색 화면
 * filename : gpsSearchScheduleList.jsp
 * @author 범정부통계포털 이진우 
 * @since 2011.08.22
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.08.22     이진우           최초 생성 
 * </pre> 
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>

<%-- Header Start ==========================================================--%>
<jsp:include page="/gps/cmm/header.do"></jsp:include>
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
function fncPageOnload()
{
	SetDatePicker("startDe");
	SetDatePicker("endDe");
	
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
	JQ_request("scheduleVO", "<c:url value='/gps/schedule/searchScheduleList.do'/>");
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
 	JQ_setValueObj('pageUnit', 10); 
   	JQ_request("scheduleVO", "<c:url value='/gps/schedule/searchScheduleList.do'/>", "scheduleVO");
}

function fn_view(scSn){
 	JQ_setValueObj('scSn', scSn); 
   	JQ_request("scheduleVO", "<c:url value='/gps/schedule/viewSchedule.do'/>", "scheduleVO");
}
-->
</script>

<!-- ui-layout-center start  -->
<div class="ui-layout-content">
	<!-- page contents start -->
	<div class="d_btn">
		<ul>
			<li><a href="/gps/schedule/selectWeekScheduleList.do">주간일정보기</a></li>
			<li><a href="/gps/schedule/selectMonthScheduleList.do">월간일정보기</a></li>
			<li>일정일정검색</li>
		</ul>
	</div>
	<div class="contents_area">
		<form:form commandName="scheduleVO" name="listForm" method="post">
		<form:hidden path="scSn"/>
		<form:hidden path="pageUnit"/>
			<table class="default">
				<tr>
					<td>
						<table class="title">
							<tr>
								<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="타이틀이미지" title="타이틀이미지"/></td>
								<td class="title">일정검색</td>
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
									<td >일정구분</td>
									<td>
										<form:select path="typeCond">
											<form:option value="" label="- 선택 -"/>
											<form:options items="${typeCond}" />
										</form:select>
									</td>
									<td class="first">일정명</td>
									<td>
										<form:input path="subject" id="subject" cssClass="text-input wi150"/>
									</td>
									<td>일정장소</td>
									<td>
										<form:input path="place" id="place" cssClass="text-input wi150"/>
									</td>
									<td>일정기간</td>
									<td>
										<form:input id="startDe" path="startDe" cssClass="text-input wi100" /> ~ 
										<form:input id="endDe" path="endDe" cssClass="text-input wi100"/>
									</td>
									<td><img src="/images/button/0208.png" alt="<spring:message code='button.search'/>" title="<spring:message code='button.search'/>" onclick="fn_search()"></td>
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
							<th scope="col">NO</th>
							<th scope="col">제목</th>
							<th scope="col">구분</th>
							<th scope="col">장소</th>
							<th scope="col">일정</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach var="resultInfo" items="${resultList}" varStatus="status">
								<tr onmouseover="this.style.backgroundColor='#fafafa'" onmouseout="this.style.backgroundColor=''">
									<td class="tc"><c:out value="${(scheduleVO.pageIndex-1) * scheduleVO.pageUnit + status.count}"/></td> 
									<td class="tc" >
									<a href="#LINK" onclick="javascript:fn_view('${resultInfo.scSn}');" >${resultInfo.subject}</a>
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
								<input name="pageIndex" type="hidden" value="<c:out value='${scheduleVO.pageIndex}'/>"/>
							</td>
						</tr>
						</table>
					</td>
				</tr>
				
			</table>
		</form:form>
	</div>
</div>
