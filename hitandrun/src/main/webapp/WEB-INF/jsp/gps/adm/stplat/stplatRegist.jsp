<%-- 
/** 
 * outline   : 약관관리  등록화면
 * filename : stplatRegist.jsp
 * @author 범정부통계포털 이관형 
 * @since 2011.06.17
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.06.17     이관형           최초 생성 
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

JQ_setValidation('stplatManageVO');
JQ_onload();

<%-- 
/************************************************************************ 
함수명 : fn_list                                   
설   명 : "목록"버튼을 클릭했을때 배너 리스트로 돌아가는 화면       
인   자 : 없음        
사용법 : fn_list()
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형           

  date         author            note
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
 function fn_list(){
   	JQ_request("stplatManageVO", "<c:url value='/gps/adm/stplat/selectStplatList.do'/>", "stplatManageVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_regist                               
설   명 : 입력된 약관 정보를 등록 하는 함수
		공통메시지를 띄워 저장여부 확인한 뒤 사용자가 확인을 하면 DB로 내용을 보내서 insert 시킴
인   자 : 없음(form 내용 modelAttribute 자동세팅)
사용법 : fn_regist()
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형       

      date    author            note
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
function fn_regist(){
	if(confirm("<spring:message code='common.save.msg' />")){
		JQ_request("stplatManageVO", "<c:url value='/gps/adm/stplat/insertStplat.do'/>");
	}
}

</script>
<!-- page contents start -->
<div class="contents_area">
	<form:form commandName="stplatManageVO" name="insertForm" method="post">
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
							<td class="title">약관관리</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table summary="약관등록" class="write01">
						<caption>약관등록</caption>
						<tr>
							<th class="subject">시스템</th>
							<td class="input">
								<form:select path="sysId" cssClass="validate[required]" >
									<form:option value="" label="선택"/>
									<form:options items="${sysId}" />
								</form:select>
							</td>
						</tr>
						<tr>
							<th class="reqsubject">약관구분</th>
							<td class="input">
								<form:select path="stplatSe" cssClass="validate[required]" >
									<form:option value="" label="선택"/>
									<form:options items="${stplatSeList}" />
								</form:select>
							</td>
						</tr>
						<tr>
							<th class="reqsubject">약관명</th>
							<td class="input"><form:input path="stplatNm" size="50" cssClass="wi200 validate[required,length[0,50]] text-input" maxlength="50" /></td>
						</tr>
						<tr>
							<th class="reqsubject">약관내용</th>
							<td class="input"><form:textarea path="stplatCn" cssClass="wi500 ht500 validate[optional,length[1,4000]]"/></td>
						</tr>
						<tr>
							<th class="subject">약관 사용여부</th>
							<td>
								<table class="inside02">
								<tr>
									<td class="input"><form:radiobutton path="stplatUseSe" value="Y" cssClass="noline"/></td>
									<td class="text">사용</td>
									<td class="input"><form:radiobutton path="stplatUseSe" value="N" cssClass="noline"/></td>
									<td class="text">사용안함</td>
								</tr>
								</table>
							</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="rbuttonarea">
						<tr>
							<td><a onclick="fn_regist();"><img src="/images/button/0210.png" alt="<spring:message code="button.save" />" title="<spring:message code="button.save" />"></a></td>
							<td class="end"><a onclick = "history.back(-1);"><img src="/images/button/0203.png" alt="<spring:message code="button.reset" />" title="<spring:message code="button.reset" />"></a></td>
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