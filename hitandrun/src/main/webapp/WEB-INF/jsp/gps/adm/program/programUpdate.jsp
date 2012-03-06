<%-- 
/** 
 * 설명   : 프로그램관리 - 프로그램 상세내용을 수정하는 화면
 * 파일명 : programUpdate.jsp
 * @author 범정부통계포털 이관형 
 * @since 2011.06.20
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.06.20     이관형           최초 생성 
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
	JQ_setValidation('programManageVO');
	JQ_onload();
<%-- 
/************************************************************************ 
함수명 : fncPageOnload                                   
설   명 : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
인   자 : 없음        
사용법 : fncPageOnload()              
작성일 : 2011.06.20  
작성자 : 범정부통계포털 이관형  

      date        author   note  
 ----------   -------     ------------------- 
                              
************************************************************************/ 
--%>
function fncPageOnload() {
	//frameset controller start
	
	if($("#programTyRead").val() == "Y" ) {
		$("#programTyReadChk").attr("checked",true);
	}

	if($("#programTyCreate").val() == "Y" ) {
		$("#programTyCreateChk").attr("checked",true);
	}	
	if($("#programTyUpdate").val() == "Y" ) {
		$("#programTyUpdateChk").attr("checked",true);
	}
	if($("#programTyDelete").val() == "Y" ) {
		$("#programTyDeleteChk").attr("checked",true);
	}

	//frameset controller end
}
<%-- 
/************************************************************************ 
함수명 : fn_list                                   
설   명 : "목록"버튼을 클릭했을때 프로그램 리스트로 돌아가는 화면       
인   자 : 없음        
사용법 : fn_list()
작성일 : 2011.06.20
작성자 : 범정부통계포털 이관형  

      date        author   note  
 ----------   -------     ------------------- 
                                 
************************************************************************/
--%>
 function fn_list(){
   	JQ_request("programManageVO", "<c:url value='/gps/adm/program/selectProgramList.do'/>", "programManageVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_update                               
설   명 : 프로그램 항목 수정후 재등록 하는 함수
		공통메시지를 띄워 저장여부 확인한 뒤 사용자가 확인을 하면 DB로 내용을 보내서 Update 시킴
인   자 : 없음(form 내용 modelAttribute 자동세팅)
사용법 : fn_update()
작성일 : 2011.06.20
작성자 : 범정부통계포털 이관형       

      date        author   note  
 ----------   -------     ------------------- 
                                 
************************************************************************/
--%> 
function fn_update(){
	if(confirm("<spring:message code='common.update.msg' />")){		

		if($("#programTyReadChk").attr("checked")) {
			$("#programTyRead").val("Y");
		} else {
			$("#programTyRead").val("N");
		}

		if($("#programTyCreateChk").attr("checked")) {
			$("#programTyCreate").val("Y");
		} else {
			$("#programTyCreate").val("N");
		}

		if($("#programTyUpdateChk").attr("checked")) {
			$("#programTyUpdate").val("Y");
		} else {
			$("#programTyUpdate").val("N");
		}

		if($("#programTyDeleteChk").attr("checked")) {
			$("#programTyDelete").val("Y");
		} else {
			$("#programTyDelete").val("N");
		}

		JQ_request("programManageVO", "<c:url value='/gps/adm/program/updateProgram.do'/>");
	}
}

<%-- 
/************************************************************************ 
함수명 : fn_delete                               
설   명 : 프로그램 삭제함수
		공통메시지를 띄워 저장여부 확인한 뒤 사용자가 확인을 하면 DB로 내용을 보내서 Update 시킴
인   자 : 없음(form 내용 modelAttribute 자동세팅)
사용법 : fn_delete()
작성일 : 2011.06.20
작성자 : 범정부통계포털 이관형       

      date        author   note  
 ----------   -------     ------------------- 
                                 
************************************************************************/
--%> 
function fn_delete(){
	if(confirm("<spring:message code='common.delete.msg' />")){		
		JQ_request("programManageVO", "<c:url value='/gps/adm/program/deleteProgramDetail.do'/>");
	}
}
</script>
<!-- contents_area start -->
<div class="contents_area">
	<form:form commandName="programManageVO" name="insertForm" method="post">
	<form:hidden path="programId" id="programId"/>
	<form:hidden path="programTyRead" id="programTyRead"/>
	<form:hidden path="programTyCreate" id="programTyCreate"/>	
	<form:hidden path="programTyUpdate" id="programTyUpdate"/>
	<form:hidden path="programTyDelete" id="programTyDelete"/>
	<form:hidden path="pageUnit" id="pageUnit"/>
	<form:hidden path="searchSysId" id="searchSysId"/>
	<form:hidden path="searchCondition" id="searchCondition"/>
	<form:hidden path="pageIndex" id="pageIndex"/>
	<form:hidden path="searchKeyword" id="searchKeyword"/>
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
							<td class="title">프로그램 수정</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table summary="프로그램 수정" class="write01">
						<caption>프로그램 수정</caption>
						<tr>
							<th class="subject">시스템</th>
							<td class="input">
								<select name="sysId">
									<c:forEach var="sysInfoList" items="${sysInfo}" varStatus="status">
									<option value="<c:out value="${sysInfoList.sysId}" />" <c:if test="${programManageVO.sysId == sysInfoList.sysId}">selected</c:if> ><c:out value="${sysInfoList.sysNm}" /></option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th class="subject">프로그램 파일명</th>
							<td class="input"><form:input path="programFileNm" size="50" cssClass="wi200 validate[required,length[0,50]] text-input" maxlength="50" /></td>
						</tr>
						<tr>
							<th class="subject">프로그램 저장경로</th>
							<td class="input"><form:input path="programStrePath" size="50" cssClass="wi200 validate[required,length[0,60]] text-input" maxlength="50" /></td>
						</tr>
						<tr>
							<th class="subject">프로그램 한글명</th>
							<td class="input"><form:input path="programKoreannm" size="60" cssClass="wi200 validate[required,custom[onlyKor],length[0,60]] text-input" maxlength="60" /></td>
						</tr>
						<tr>
							<th class="subject">프로그램 유형</th>
							<td>
								<table class="inside02">
								<tr>
									<td class="input"><input type="checkbox" path="programTyReadChk" class="noline validate[minCheckbox[1]] checkbox" id="programTyReadChk" /></td>
									<td>읽기</td>
									<td class="input"><input type="checkbox" path="programTyCreateChk" class="noline validate[minCheckbox[1]] checkbox" id="programTyCreateChk" /></td>
									<td>등록</td>
									<td class="input"><input type="checkbox" path="programTyUpdateChk" class="noline validate[minCheckbox[1]] checkbox" id="programTyUpdateChk" /></td>
									<td>수정</td>
									<td class="input"><input type="checkbox" path="programTyDeleteChk" class="noline validate[minCheckbox[1]] checkbox" id="programTyDeleteChk" /></td>
									<td>삭제</td>
								</tr>
								</table>
							</td>
						</tr>					
						<tr>
							<th class="subject">프로그램 설명</th>
							<td class="input"><form:input path="programDc" size="60" cssClass="wi500 validate[optional,length[6,300]] text-input" /></td>
						</tr>
						<tr>
							<th class="subject">URL</th>
							<td class="input"><form:input path="url" size="60" cssClass="wi500 validate[required,length[0,60]] text-input" maxlength="60" /></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="rbuttonarea">
						<tr>
							<td><a onclick = "fn_update();" style="cursor:hand"><img src="/images/button/0210.png" alt="<spring:message code="button.save" />" title="<spring:message code="button.save" />"></a></td>
							<td><a onclick="fn_delete();" style="cursor:hand"><img src="/images/button/0204.png" alt="<spring:message code="button.delete" />" title="<spring:message code="button.delete" />"></a></td>
							<td class="end"><a onclick="fn_list();" style="cursor:hand"><img src="/images/button/0203.png" alt="<spring:message code="button.list" />" title="<spring:message code="button.list" />" ></a></td>
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