<%-- 
/** 
 * 설명   : 일정관리 수정화면
 * 파일명 : scheduleUpdate.jsp
 * @author 범정부통계포털 이진우 
 * @since 2011.08.10 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.08.05     이진우           최초 생성 
 * </pre> 
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<jsp:useBean id="dateUtil_now" class="go.narastat.gps.adm.schedule.service.DateUtil" scope="page" />
<jsp:useBean id="dateUtil_start" class="go.narastat.gps.adm.schedule.service.DateUtil" scope="page" />
<jsp:useBean id="dateUtil_end" class="go.narastat.gps.adm.schedule.service.DateUtil" scope="page" />
<jsp:useBean id="scheduleManageVO" class="go.narastat.gps.adm.schedule.service.ScheduleManageVO" scope="request" />
<%

int sc_start_hh = 0;
int sc_start_mm = 0;
int sc_end_hh = 0;
int sc_end_mm = 0;

SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");

if (scheduleManageVO.getStartDt() != null) {
	formatter.parse(scheduleManageVO.getStartDt());
	Calendar calStart=formatter.getCalendar();
	dateUtil_start.setDate(calStart);
	
	sc_start_hh=dateUtil_start.getHour();
	sc_start_mm=dateUtil_start.getMinute();
	
}
if (scheduleManageVO.getEndDt() != null) {
	formatter.parse(scheduleManageVO.getEndDt());
	Calendar calEnd=formatter.getCalendar();
	dateUtil_end.setDate(calEnd);
	
	sc_end_hh=dateUtil_end.getHour();
	sc_end_mm=dateUtil_end.getMinute();
}

%>
<%-- Header Start ==========================================================--%>
<jsp:include page="/gps/cmm/admHeader.do"></jsp:include>
<%-- Header End ==========================================================--%>
<%-- javascript start ==============================================--%>
<script type="text/javaScript" language="javascript">
<!--
JQ_setValidation('scheduleManageVO');
JQ_onload();

<!--
<%-- 
/************************************************************************ 
함수명 : fncPageOnload                                   
설   명 : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
인   자 : 없음        
사용법 : fncPageOnload()              
작성일 : 2011-08-05   
작성자 : 범정부통계포털 이진우       

  date         author            note
 ----------   -------     ------------------- 
                              
************************************************************************/ 
--%>
function fncPageOnload(){
	SetDatePicker("startDe");
	SetDatePicker("endDe");
	
	fn_selType();

	popupWin("selStatImg", "/sps/dms/popupStatInfo.do", "조사찾기", 338, 600, "no", "no", 0);
}

<%-- 
/************************************************************************ 
함수명 : fn_list                                   
설   명 : "목록"버튼을 클릭했을때 사용자 리스트로 돌아가는 화면       
인   자 : 없음        
사용법 : fn_list()
작성일 : 2011-08-05   
작성자 : 범정부통계포털 이진우             

  date         author            note
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
 function fn_list(){
   	JQ_request("scheduleManageVO", "<c:url value='/gps/adm/schedule/selectScheduleList.do'/>", "scheduleManageVO");
}
<%-- 
/************************************************************************ 
함수명 : fn_modify                               
설   명 : 일정 수정
인   자 : 없음(form 내용 modelAttribute 자동세팅)
사용법 : fn_modify()
작성일 : 2011-08-05   
작성자 : 범정부통계포털 이진우         

      date    author            note
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
function fn_modify(){

	var selVal=$("#scTy").val();
	
	var startYyyymmdd=$("#startDe").val().replace(/-/gi, "");
	var startHh=$("#sc_start_hh").val();
	var startMm=$("#sc_start_mm").val();

	var endYyyymmdd=$("#endDe").val().replace(/-/gi, "");
	var endHh=$("#sc_end_hh").val();
	var endMm=$("#sc_end_mm").val();


	if(selVal=='01'){
		$("#startDt").val(startYyyymmdd+'0000');
		$("#endDt").val(endYyyymmdd+'2359');
	}else{
		$("#startDt").val(startYyyymmdd+startHh+startMm);
		$("#endDt").val(endYyyymmdd+endHh+endMm);
	}

	if(confirm("<spring:message code='common.save.msg' />")){
		JQ_request("scheduleManageVO", "<c:url value='/gps/adm/schedule/updateSchedule.do'/>");
	}
	//opener.parent.leftBody.location.reload(true);
	
}

function fn_delete(){
	if(confirm("<spring:message code='common.delete.msg' />")){
		JQ_request("scheduleManageVO", "<c:url value='/gps/adm/schedule/deleteSchedule.do'/>");
	}
	opener.parent.leftBody.location.reload(true);
}

function fn_selType(){
	var selVal=$("#scTy").val();

	if(selVal=='01'){
		$("#trOrg").hide();
		$("#trPlace").hide();
		$("#trTelNo").hide();
		$("#trFaxNo").hide();

		$("#sc_start_hh").hide();
		$("#sc_start_mm").hide();
		$("#sc_end_hh").hide();
		$("#sc_end_mm").hide();

		$("#orgNm").val("");
		$("#place").val("");
		$("#phonCn").val("");
		$("#faxPhonCn").val("");
	}else{
		$("#trOrg").show();
		$("#trPlace").show();
		$("#trTelNo").show();
		$("#trFaxNo").show();

		$("#sc_start_hh").show();
		$("#sc_start_mm").show();
		$("#sc_end_hh").show();
		$("#sc_end_mm").show();
	}
	
	if(selVal!='02'){
		$("#statId").val("");
		$("#statNm").val("");
		$("#trStat").hide();
	}else{
		$("#trStat").show();
		$("#trPlace").hide();
		$("#place").val("");
	}
}

function chkCal(){

	if ($("#startDt").val() > $("#endDt").val()) {
		return "일정 시작일은 종료일 이전이어야 합니다.";
	}else{
		return "";
	}
}
-->
</script>
<!-- contents_area start -->
<div class="contents_area">
	<form:form commandName="scheduleManageVO" name="scheduleManageVO" id="scheduleManageVO" method="post">
	<form:hidden path="startDt" id="startDt"/>
	<form:hidden path="endDt" id="endDt"/>
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
						<table summary="사용자등록" class="write01">
						<caption>일정등록</caption>
						<tr>
							<th class="subject">일정구분</th>
							<td class="input">
								<form:select path="scTy" id="scTy" onchange="fn_selType();" cssClass="validate[required] ">
									<form:option value="" label="- 선택 -"/>
									<form:options items="${scTyList}" />
								</form:select>
							</td>
						</tr>
						<tr>
							<th class="subject">제목</th>
							<td class="input"><form:input path="subject" id="subject" title="제목" cssClass="validate[required] text-input wi300"/></td>
						</tr>
						<tr>
							<th class="subject">일정</th>
							<td>
								<table class="inside01">
								<tr>
									<th class="subject">시작일</th>
									<td>
										<table class="inner01">
										<tr>
											<td class="input_bottom_noline">
											<form:input id="startDe" path="startDe" cssClass="validate[required,funcCall[chkCal]] text-input wi100" />
											</td>
											<td class="input_bottom_noline">
												<select name="sc_start_hh" id="sc_start_hh" class="select wi60">
											<%
												for (int i=0; i < 24; i++) {
													pageContext.setAttribute("hhmm", StringUtils.leftPad(String.valueOf(i),2,"0"));
													if (i == sc_start_hh) {
														pageContext.setAttribute("hhmm_selected", "selected");
													} else {
														pageContext.setAttribute("hhmm_selected", "");
													}
											%>
													<option value="${hhmm}" ${hhmm_selected}>${hhmm} 시</option>
											<%
												}
											%>
												</select>
											</td>
											<td class="input_bottom_noline">
												<select name="sc_start_mm" id="sc_start_mm" class="select wi60">
											<%
												for (int i=0; i < 60; i+=10) {
													pageContext.setAttribute("hhmm", StringUtils.leftPad(String.valueOf(i),2,"0"));
													if (i == sc_start_mm) {
														pageContext.setAttribute("hhmm_selected", "selected");
													} else {
														pageContext.setAttribute("hhmm_selected", "");
													}
											%>
													<option value="${hhmm}" ${hhmm_selected}>${hhmm} 분</option>
											<%
												}
											%>
												</select>
											</td>
										</tr>
										</table>	
									</td>
								</tr>
								<tr>
									<th class="bottom_noline">종료일</th>
									<td class="bottom_noline">
										<table class="inner01">
										<tr>
											<td class="input_bottom_noline"><form:input id="endDe" path="endDe" cssClass="validate[required] text-input wi100"/></td>
											<td class="input_bottom_noline">
												<select name="sc_end_hh" id="sc_end_hh" class="select wi60">
											<%
												for (int i=0; i < 24; i++) {
													pageContext.setAttribute("hhmm", StringUtils.leftPad(String.valueOf(i),2,"0"));
													if (i == sc_end_hh) {
														pageContext.setAttribute("hhmm_selected", "selected");
													} else {
														pageContext.setAttribute("hhmm_selected", "");
													}
											%>
													<option value="${hhmm}" ${hhmm_selected}>${hhmm} 시</option>
											<%
												}
											%>
												</select>
											</td>
											<td class="input_bottom_noline">
												<select name="sc_end_mm" id="sc_end_mm" class="select wi60">
											<%
												for (int i=0; i < 60; i+=10) {
													pageContext.setAttribute("hhmm", StringUtils.leftPad(String.valueOf(i),2,"0"));
													if (i == sc_end_mm) {
														pageContext.setAttribute("hhmm_selected", "selected");
													} else {
														pageContext.setAttribute("hhmm_selected", "");
													}
											%>
													<option value="${hhmm}" ${hhmm_selected}>${hhmm} 분</option>
											<%
												}
											%>
												</select>
											</td>
										</tr>
										</table>
									</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr id="trStat">
							<th class="subject">조사명</th>
							<td class="input">
								<table class="inside02">
								<tr>
									<td class="input">
										<form:hidden path="statId" id="statId"/>
										<form:input path="statNm" id="statNm" title="조사명" cssClass="wi200" readonly="true"/>
									</td>
									<td  class="img"><a id="selStatImg"><img src="/images/button/0207.png" alt="찾기" title="찾기"></a></td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th class="subject">내용</th>
							<td class="input"><form:textarea path="scheduleCn" id="scheduleCn" cols="50" cssClass="validate[required] text-input"/></td>
						</tr>
						
						<tr id="trOrg">
							<th class="subject">주관기관</th>
							<td class="input"><form:input path="orgNm" id="orgNm" title="주관기관" cssClass="wi200"/></td>
						</tr>
						<tr id="trPlace">
							<th class="subject">장소</th>
							<td class="input"><form:input path="place" id="place" title="장소" cssClass="wi200"/></td>
						</tr>
						<tr id="trTelNo">
							<th class="subject">전화번호</th>
							<td class="input"><form:input path="phonCn" id="phonCn" title="전화번호" cssClass="text-input wi200"/></td>
						</tr>
						<tr id="trFaxNo">
							<th class="subject">팩스번호</th>
							<td class="input"><form:input path="faxPhonCn" id="faxPhonCn" title="팩스번호" cssClass="text-input wi200"/></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="rbuttonarea">
						<tr>
							<td><img src="/images/button/0210.png" alt="<spring:message code="button.save" />" title="<spring:message code="button.save" />" onclick = "fn_modify();"></td>
							<td><img src="/images/button/0204.png" alt="<spring:message code="button.delete" />" title="<spring:message code="button.delete" />" onclick = "fn_delete();"></td>
							<td><img src="/images/button/0203.png" alt="<spring:message code="button.list" />" title="<spring:message code="button.list" />" onclick = "fn_list();"></td>
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