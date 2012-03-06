<%-- 
/** 
 * outline   : 주간일정보기 화면
 * filename : gpsWeekScheduleList.jsp
 * @author 범정부통계포털 이진우 
 * @since 2011.08.19
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.08.19     이진우           최초 생성 
 * </pre> 
 */
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="org.apache.commons.lang.math.NumberUtils"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@ page import="egovframework.rte.psl.dataaccess.util.EgovMap"%>
<%@ page import="java.util.*" %>

<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>



<%-- 공통 taglib 선언 start	=================================================--%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<%-- 공통 taglib 선언 end	=====================================================--%>

<%-- header start	==========================================================--%>
<jsp:include page="/gps/cmm/header.do"></jsp:include>
<%-- header end	==============================================================--%>
<%-- 선행 로직 start	======================================================--%>


<%-- page javascript start	==================================================--%>
<script type="text/javascript">
JQ_setValidation('userRegisterVO');
JQ_onload();
<!--
<%-- 
/************************************************************************ 
함수명 : fncPageOnload                                   
설   명 : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
인   자 : 없음        
사용법 : fncPageOnload()              
작성일 : 2011.08.01  
작성자 : 범정부통계포털 이진우  

   date        author      note  
 ----------   -------     ------------------- 
 2011.08.01	     이진우               최초생성             
************************************************************************/ 
--%>
function fncPageOnload()
{
	//frameset controller start
	//frameset controller end
	
}

<%-- 
/************************************************************************ 
함수명 : fn_search                                   
설   명 : 해당조건의 주간일정을 보여준다.       
인   자 : 없음        
사용법 : fn_search()
작성일 : 2011-08-18   
작성자 : 범정부통계포털 이진우             

  date         author            note
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
 function fn_search(){
	var month=document.searchForm.month.value;
	var year=document.searchForm.year.value;
	var weekofmonth=document.searchForm.weekofmonth.value;
	JQ_setValueObj('year', year); 
	JQ_setValueObj('month', month); 
	JQ_setValueObj('weekofmonth', weekofmonth); 
   	JQ_request("searchForm", "<c:url value='/gps/schedule/selectWeekScheduleList.do'/>", "searchForm");
}


-->
</script>
<noscript>현재 브라우저에서 자바스크립트 처리를 하지 못합니다.</noscript>
<jsp:useBean id="now" class="go.narastat.gps.adm.schedule.service.DateUtil" scope="page" />
<jsp:useBean id="dateUtil" class="go.narastat.gps.adm.schedule.service.DateUtil" scope="request" />
<div class="d_schedule">
	<div class="d_btn">
		<ul>
			<li>주간일정보기</li>
			<li><a href="/gps/schedule/selectMonthScheduleList.do">월간일정보기</a></li>
			<li><a href="/gps/schedule/searchScheduleList.do">일정검색</a></li>
		</ul>
	</div>
	<div class="d_day">
		<form name="searchForm" id="searchForm" method="post">
		<ul>
			<li>
				<select name="year" class="select wi80" title="년">
			<c:forEach begin="${dateUtil.year-5}" end="${now.year+10}" varStatus="i">
				<option value="${i.index}" <c:if test="${dateUtil.year eq i.index}">selected</c:if>>${i.index} 년</option>
			</c:forEach>
				</select>
			</li>
			<li>
				<select name="month" class="select wi60" title="월">
			<c:forEach begin="1" end="12" varStatus="i">
				<fmt:formatNumber var="item" type="number" pattern="00" value="${i.index}"/>
				<option value="${item}" <c:if test="${dateUtil.monthInt eq i.index}">selected</c:if>>${item} 월</option>
			</c:forEach>
				</select>
			</li>
			<li>
				<select name="weekofmonth" class="select wi100" title="주">
			<c:forEach varStatus="i" var="item" items="${weekList}">
				<option value="${i.count}" <c:if test="${i.count eq weekofmonth}">selected</c:if>>${fn:substring(item['0'],6,8)}일~${fn:substring(item['1'],6,8)}일 </option>
			</c:forEach>
				</select>
			</li>
			<li><img src="/images/button/0208.png" alt="<spring:message code='button.search'/>" title="<spring:message code='button.search'/>" onclick="fn_search()"></li>
			
		</ul>
		</form>
	</div>
	<div class="d_desc">
		<table class="scheduleWeek01" summary="주간일정">
   <c:forEach var="item" items="${dayList}" varStatus="i">
   		<c:if test="${i.first}"><c:set var="sc_date_t" value="${item.sc_date}"/></c:if>
		<c:if test="${i.first or item.sc_date ne sc_date_t}">
		<tr>
			<td>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" summary="<fmt:formatDate  value="${item.sc_date}" pattern="yyyy년 MM월 dd일 EEE요일"/>">
				<tr>
					<th class="f_eng12"><fmt:formatDate  value="${item.sc_date}" pattern="yyyy년 MM월 dd일 EEE요일"/></th>
				</tr>
				<tr>
					<td>
						<table class="scheduleContents">
						<thead>
						<tr>
							<th class="first">제목</th>
							<th class="wi50">구분</th>
							<th>기간</th>
							<th class="end wi100">장소</th>
						</tr>
						</thead>
						<tbody>
		</c:if>
				<c:set var="exist_sc" value="0"/>
				<c:forEach var="item2" items="${list}">
					<c:if test="${item.sc_date1 >= item2.startDe && item.sc_date1 <= item2.endDe}">
						<c:set var="exist_sc" value="1"/>
						<tr>
							<td><a href="/gps/schedule/viewSchedule.do?scSn=${item2.scSn}">${item2.subject}</a></td>
							<td>${item2.scTyNm}</td>
							<td class="f_eng12"><fmt:formatDate  value="${item2.startDt}" pattern="yyyy년 MM월 dd일 EEE요일"/> ~ <fmt:formatDate  value="${item2.endDt}" pattern="yyyy년 MM월 dd일 EEE요일"/></td>
							<td>${item2.place}</td>
						</tr>
					</c:if>
				</c:forEach>
				<c:if test="${exist_sc eq '0'}">
					<tr><td colspan="4">일정이 없습니다</td></tr>
				</c:if>
		<c:if test="${i.first or item.sc_date ne sc_date_t}">
						</tbody>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</c:if>
   </c:forEach>
		</table>
	</div>
</div>