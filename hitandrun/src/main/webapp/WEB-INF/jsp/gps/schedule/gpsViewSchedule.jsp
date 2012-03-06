<%-- 
/** 
 * 설명   : 일정상세보기
 * 파일명 : gpsViewSchedule.jsp
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

<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>

<%-- Header Start ==========================================================--%>
<jsp:include page="/gps/cmm/header.do"></jsp:include>
<%-- Header End ==========================================================--%>

<%-- javascript start ==============================================--%>
<script type="text/javaScript" language="javascript">
<!--
JQ_setValidation('scheduleVO');
JQ_onload();

<!--


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
   	JQ_request("scheduleVO", "<c:url value='/gps/adm/schedule/selectScheduleList.do'/>", "scheduleVO");
}


 function fncPageOnload()
 {
 	fn_selType();
 }

function fn_selType(){
	var selVal='${scheduleVO.scTy}'

	if(selVal=='01'){
		$("#trOrg").hide();
		$("#trPlace").hide();
		$("#trTelNo").hide();
		$("#trFaxNo").hide();

		$("#sc_start_hh").hide();
		$("#sc_start_mm").hide();
		$("#sc_end_hh").hide();
		$("#sc_end_mm").hide();

		
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
		$("#trStat").hide();
	}else{
		$("#trStat").show();
		$("#trPlace").hide();
		
	}
}




-->
</script>
<div class="ui-layout-content">

	<form:form commandName="scheduleVO" name="scheduleVO" id="scheduleVO" method="post">
	<form:hidden path="startDt" id="startDt"/>
	<form:hidden path="endDt" id="endDt"/>
	<form:hidden path="scSn"/>
	<!-- contents_area start -->
	<div class="contents_area">
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
								${scheduleVO.scTyNm }
							</td>
						</tr>
						<tr>
							<th class="subject">제목</th>
							<td class="input">
								${scheduleVO.subject }
							</td>
						</tr>
						<tr>
							<th class="subject">일정</th>
							<td class="input">
								${scheduleVO.startDt} ~ ${scheduleVO.endDt} 
							</td>
						</tr>
						
						
						<tr id="trStat">
							<th class="subject">조사명</th>
							<td class="input">
								${scheduleVO.statNm }
							</td>
						</tr>
						<tr>
							<th class="subject">내용</th>
							<td class="input">
								${scheduleVO.scheduleCn }
							</td>
						</tr>
						
						<tr id="trOrg">
							<th class="subject">주관기관</th>
							<td class="input">
								${scheduleVO.orgNm }
							</td>
						</tr>
						<tr id="trPlace">
							<th class="subject">장소</th>
							<td class="input">
								${scheduleVO.place }
							</td>
						</tr>
						<tr id="trTelNo">
							<th class="subject">전화번호</th>
							<td class="input">
							${scheduleVO.phonCn }
							</td>
						</tr>
						<tr id="trFaxNo">
							<th class="subject">팩스번호</th>
							<td class="input">
							${scheduleVO.faxPhonCn }
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table class="rbuttonarea">
						<tr>
						<td><img src="/images/button/0203.png" alt="<spring:message code="button.list" />" title="<spring:message code="button.list" />" onclick = "history.back(-1);"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<!-- container end -->
	</form:form>
</div>
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>