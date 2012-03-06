<%-- 
/** 
 * outline   : 월간일정 화면
 * filename : gpsMonthScheduleList.jsp
 * @author 범정부통계포털 이진우 
 * @since 2011.08.18
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.08.18     이진우           최초 생성 
 * </pre> 
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ page import="org.apache.commons.lang.math.NumberUtils"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@ page import="egovframework.rte.psl.dataaccess.util.EgovMap"%>
<%@ page import="java.math.*"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="go.narastat.common.util.StringUtil"%>



<%-- 공통 taglib 선언 start	=================================================--%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<%-- 공통 taglib 선언 end	=====================================================--%>

<%-- header start	==========================================================--%>
<jsp:include page="/gps/cmm/header.do"></jsp:include>
<%-- header end	==============================================================--%>
<%-- 선행 로직 start	======================================================--%>
<jsp:useBean id="now" class="go.narastat.gps.adm.schedule.service.DateUtil" scope="page" />
<jsp:useBean id="dateUtil" class="go.narastat.gps.adm.schedule.service.DateUtil" scope="page" />


<%
	List monthList=(List)request.getAttribute("resultList");	
	
	int year = NumberUtils.toInt(request.getParameter("year"), dateUtil.getYear());
	int month = NumberUtils.toInt(request.getParameter("month"), dateUtil.getMonthInt());
	int day = NumberUtils.toInt(request.getParameter("day"), dateUtil.getDayOfMonth());

	dateUtil.setDate(year, month, 1);

	String strYear = StringUtils.leftPad(String.valueOf(dateUtil.getYear()), 2, "0");
	String strMonth = StringUtils.leftPad(String.valueOf(dateUtil.getMonthInt()), 2, "0");
	String strDay = StringUtils.leftPad(String.valueOf(dateUtil.getDayOfMonth()), 2, "0") ;

	int fweek = 0;  // 첫주의 일수
	int rweek = 0;  // 남은 주
	int acday = 0;  // 그달의 일수

	acday = dateUtil.getEndDay();
	fweek = 8 - dateUtil.getDayOfWeek();
	rweek = (int)Math.ceil((acday - fweek)/7.0);

	Calendar calendar = Calendar.getInstance();
	calendar.set(year, month-1, day);

	int cntSchedule = 0; // 일정수

%>

<%-- 선행 로직 end	==========================================================--%>



<%-- page javascript start	==================================================--%>
<script type="text/javascript">
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
설   명 : 해당조건의 월간일정을 보여준다.       
인   자 : 없음        
사용법 : fn_search()
작성일 : 2011-08-18   
작성자 : 범정부통계포털 이진우             

  date         author            note
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
 function fn_search(){
	var searchMm=document.searchForm.selYear.value+document.searchForm.selMonth.value;
	
    JQ_setValueObj('searchMm', searchMm); 
   	JQ_request("searchForm", "<c:url value='/gps/schedule/selectMonthScheduleList.do'/>", "searchForm");
}


-->
</script>
<noscript>현재 브라우저에서 자바스크립트 처리를 하지 못합니다.</noscript>
<div class="d_schedule">
	<div class="d_btn">
		<form name="chMonth" method="get">
		<input type="hidden" name="year" value="<%= StringUtil.unScript(year) %>">
		<input type="hidden" name="month" value="<%= StringUtil.unScript(month) %>">
		<input type="hidden" name="day" value="<%= StringUtil.unScript(day) %>">
		<ul>
			<li><a href="/gps/schedule/selectWeekScheduleList.do">주간일정보기</a></li>
			<li>월간일정보기</li>
			<li><a href="/gps/schedule/searchScheduleList.do">일정검색</a></li>
		</ul>
		</form>
	</div>
	<div class="d_day">
		<form name="searchForm" id="searchForm" method="post">
		<input type="hidden" name="searchMm" id="searchMm">
		<ul>
			<li>
				<select name="year" id="selYear" class="select wi60" title="년">
			<c:forEach begin="${dateUtil.year-5}" end="${now.year+10}" varStatus="i">
				<option value="${i.index}" <c:if test="${dateUtil.year eq i.index}">selected</c:if>>${i.index} 년</option>
			</c:forEach>
				</select>
			</li>
			<li>
				<select name="month" id="selMonth" class="select wi60" title="월">
				<option value="">월선택</option>
			<c:forEach begin="1" end="12" varStatus="i">
				<fmt:formatNumber var="item" type="number" pattern="00" value="${i.index}"/>
				<option value="${item}" <c:if test="${dateUtil.monthInt eq i.index}">selected</c:if>>${item} 월</option>
			</c:forEach>
				</select>
			</li>
			<li><img src="/images/button/0208.png" alt="<spring:message code='button.search'/>" title="<spring:message code='button.search'/>" onclick="fn_search()"></li>
			
		</ul>
		</form>
	</div>
	<div class="d_desc">
		<table width="679" border="0" cellspacing="0" cellpadding="0" summary="<%=strYear%>년 <%=strMonth%>월 ">
		<caption></caption>
		<tr>
			<td>
				<table class="scheduleCalendar01">
				<tr>
					<th><font color="#DE443F">SUN</font></th>
					<th>MON</th>
					<th>TUE</th>
					<th>WED</th>
					<th>THU</th>
					<th>FRI</th>
					<th><font color="#3F93C5">SAT</font></th>
				</tr>
				</table>
			</td>
		</tr>
		<tr><td class="ht2"></td></tr>
		<tr>
			<td>
				<table class="scheduleCalendar02">
				<tr>
			<%
				int fday = 0;
				StringBuffer sbDay = new StringBuffer();
				
			// 첫주
				for (int i=1; i <= 7; i++) {
				if(i >= dateUtil.getDayOfWeek()){
					sbDay.setLength(0);
					cntSchedule = 0;
					fday++;
					ArrayList scList=new ArrayList();

					String strfday = StringUtils.leftPad(String.valueOf(fday), 2, "0");

					//ScheduleFactory scFactory = ScheduleFactory.getInstance();

					
					int curDay=Integer.parseInt(strYear+strMonth+strfday);
					for(int k=0;k<monthList.size();k++){
						EgovMap vo=(EgovMap)monthList.get(k);
						int stDay=Integer.parseInt((String)vo.get("startDe"));
						int endDay=Integer.parseInt((String)vo.get("endDe"));
						
						if(curDay>=stDay && curDay<=endDay){
							cntSchedule++;
							scList.add(vo);
						}
						
					}
					
					
					if (i == 1) { // 일요일
						sbDay.append("<font style='font-size:11px; font-family:Arial, sans-serif; color:#DE443F;'>").append(String.valueOf(fday)).append("</font>");
					} else if (i == 7) { // 토요일
						sbDay.append("<font style='font-size:11px; font-family:Arial, sans-serif; color:#3F93C5;'>").append(String.valueOf(fday)).append("</font>");
					} else {
						sbDay.append("<font style='font:11px arial'>").append(String.valueOf(fday)).append("</font>");
					}
			%>
					<td class="day vt">
						<table border="0" cellpadding="0" cellspacing="0" width="95" height="90">
						<tr>
							<td height="17" class="vt">
								<table class="scheduleCalendar03">
								<tr>
									<td class="tc"><%= sbDay.toString() %></td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="73" width="94" class="vt pt2">
								<table border="0" cellpadding="0" cellspacing="0" width="94">
								<tr>
									<td class="pl2">
										<table border="0" cellpadding="0" cellspacing="0">
									<%
										if (cntSchedule == 0) {
									%>
										<tr>
											<td></td>
											<td class="pt3"></td>
											<td></td>
										</tr>
									<%
										} else {
									%>
										<tr>
											<td class="vt">
												<table border="0" cellpadding="0" cellspacing="0" width="100%">
												<c:set var="titleLimit" value="6"/>
											<%

												//ScheduleFactory scheFac = ScheduleFactory.getInstance();
												if (scList != null) {
													pageContext.setAttribute("list", scList);
												}
											%>
												<tr>
													<td>
														<table border="0" cellpadding="0" cellspacing="0" width="100%">
													<c:forEach var="item" items="${list}" varStatus="i">
														<tr>
															<td>
															<a href="/gps/schedule/viewSchedule.do?scSn=${item.scSn}">[${item.scTyNm}]
														<c:choose>
															<c:when test="${fn:length(item.subject) > titleLimit}"><c:out value="${fn:substring(item.subject,0,titleLimit)}..." escapeXml="false"/></c:when>
															<c:otherwise><c:out value="${item.subject}" escapeXml="false"/></c:otherwise>
														</c:choose>
															</a>
															</td>
														</tr>
													</c:forEach>
														</table>
													</td>
												</tr>
												</table>
											</td>
										</tr>
									<%
										}
									%>
										</table>
									</td>
								</tr>
								</table>
							</td>
						</tr>
						</table>
					</td>
			<%
				} else {
			%>
					<td class="day vt">
						<table border="0" cellpadding="0" cellspacing="0" width="95" height="90">
						<tr>
							<td height="17" class="vt">
								<table class="scheduleCalendar03">
								<tr>
									<td></td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="73" width="94" class="vt pt2">
								<table border="0" cellpadding="0" cellspacing="0" width="94">
								<tr>
									<td></td>
								</tr>
								</table>
							</td>
						</tr>
						</table>
					</td>
			<%
				}
			}
			%>
				</tr>
			<%
			fday = fweek;
			// 나머지 주
			for(int j=0; j < rweek; j++) {
			%>
				<tr>
			<%
				for(int i=1; i <= 7; i++) {
					if(fday < acday) {
					sbDay.setLength(0);
					fday++;
					ArrayList scList2=new ArrayList();

					String strfday = StringUtils.leftPad(String.valueOf(fday), 2, "0");

					int curDay=Integer.parseInt(strYear+strMonth+strfday);
					for(int k=0;k<monthList.size();k++){
						EgovMap vo=(EgovMap)monthList.get(k);
						int stDay=Integer.parseInt((String)vo.get("startDe"));
						int endDay=Integer.parseInt((String)vo.get("endDe"));
						
						if(curDay>=stDay && curDay<=endDay){
							cntSchedule++;
							scList2.add(vo);
						}
						
					}
					
					
					if (i == 1) { // 일요일
						sbDay.append("<font style='font-size:11px; font-family:Arial, sans-serif; color:#DE443F;'>").append(String.valueOf(fday)).append("</font>");
					} else if (i == 7) { // 토요일
						sbDay.append("<font style='font-size:11px; font-family:Arial, sans-serif; color:#3F93C5;'>").append(String.valueOf(fday)).append("</font>");
					} else {
						sbDay.append("<font style='font:11px arial'>").append(String.valueOf(fday)).append("</font>");
					}
			%>
					<td class="day vt">
						<table border="0" cellpadding="0" cellspacing="0" width="95" height="90">
						<tr>
							<td height="17" class="vt">
								<table class="scheduleCalendar03">
								<tr>
									<td align="center"><%= sbDay.toString() %></td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="73" width="94" class="vt pt2">
								<table border="0" cellpadding="0" cellspacing="0" width="94">
								<tr>
									<td class="pl2">
										<table border="0" cellpadding="0" cellspacing="0" width="100%">
									<%
										if (cntSchedule == 0) {
									%>
										<tr>
											<td></td>
											<td class="pt3"></td>
											<td></td>
										</tr>
									<%
										} else {
									%>
										<tr>
											<td class="vt">
												<table border="0" cellpadding="0" cellspacing="0" width="100%">
											<c:set var="titleLimit" value="6"/>
											<%
												if (scList2 != null) {
													pageContext.setAttribute("list", scList2);
												}
											%>
												<tr>
													<td>
														<table border="0" cellpadding="0" cellspacing="0" width="100%">
													<c:forEach var="item" items="${list}" varStatus="i">
														<tr>
															<td>
															<a href="/gps/schedule/viewSchedule.do?scSn=${item.scSn}">[${item.scTyNm}]
														<c:choose>
															<c:when test="${fn:length(item.subject) > titleLimit}"><c:out value="${fn:substring(item.subject,0,titleLimit)}..." escapeXml="false"/></c:when>
															<c:otherwise><c:out value="${item.subject}" escapeXml="false"/></c:otherwise>
														</c:choose>
															</a>
															</td>
														</tr>
													</c:forEach>
														</table>
													</td>
												</tr>
												</table>
											</td>
										</tr>
									<%
										}
									%>
										</table>
									</td>
								</tr>
								</table>
							</td>
						</tr>
						</table>
					</td>
			<%
				} else {
			%>
					<td class="day vt">
						<table border="0" cellpadding="0" cellspacing="0" width="95" height="90">
						<tr>
							<td height="17" class="vt">
								<table class="scheduleCalendar03">
								<tr>
									<td></td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="73" width="94" class="vt pt2">
								<table border="0" cellpadding="0" cellspacing="0" width="94">
								<tr>
									<td class="pl2"></td>
								</tr>
								</table>
							</td>
						</tr>
						</table>
					</td>
			<%
				}
			}
			%>
				</tr>
			<%
			}
			%>
				</table>
			</td>
		</tr>
		</table>
	</div>
</div>