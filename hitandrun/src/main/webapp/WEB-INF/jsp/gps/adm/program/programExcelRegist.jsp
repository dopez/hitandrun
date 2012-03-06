<%-- 
/** 
 * outline   : 프로그램관리 - 프로그램정보를 엑셀업로드로 등록한다.
 * filename : programExcelRegist.jsp
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
JQ_onload(); 
<%-- 
/************************************************************************ 
함수명 : fncPageOnload                                   
설   명 : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
인   자 : 없음        
사용법 : fncPageOnload()              
작성일 : 2011-05-10   
작성자 : 범정부통계포털 이관형

      date      author    note
 ----------   -------     ------------------- 
                       
************************************************************************/ 
--%>
function fncPageOnload()
{	
	<c:if test="${!empty uploadFailMsg}">
	alert("<c:out value="${uploadFailMsg}"/>");
	</c:if>
	//frameset controller start
	//frameset controller end
}

<%-- 
/************************************************************************ 
함수명 : fn_list                                   
설   명 : "목록"버튼을 클릭했을때 프로그램 리스트로 돌아가는 화면       
인   자 : 없음        
사용법 : fn_list()
작성일 : 2011-05-10
작성자 : 범정부통계포털 이관형

      date      author    note
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
function fn_list(){
   	JQ_request("programManageVO", "<c:url value='/gps/adm/program/selectProgramList.do'/>", "programManageVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_egov_regist_excelprogram                               
설   명 : 엑셀파일을 첨부하여 DB에 저장하는함수
인   자 : 없음
사용법 : fn_egov_regist_excelprogram()
		엑셀파일을 첨부하였는지 여부를 검사하는 로직이 들어있다.
작성일 : 2011-05-10
작성자 : 범정부통계포털 이관형

      date      author    note
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
function fn_egov_regist_excelprogram(){
	var varForm				 = document.all["updateForm"];
	
	// 파일 확장명 확인
	var arrExt      = "xls";
	var objInput    = varForm.elements["fileNm"];
	var strFilePath = objInput.value;
	var arrTmp      = strFilePath.split(".");
	var strExt      = arrTmp[arrTmp.length-1].toLowerCase();

	if (arrExt != strExt) {
		alert("<spring:message code='fail.common.excelupload'/>");
		abort;
	} 
	
	varForm.action           = "/gps/adm/program/insertProgramExcelUpload.do";
	varForm.submit();

}
</script>
<a name="noscript" id="noscript">
<noscript class="noScriptTitle"><spring:message code='fail.common.nojavscript'/></noscript>
</a>
<!-- 엑셀 등록 메시지  -->
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
	</form:form>

	<form action="" name="updateForm"  method="post" enctype="multipart/form-data" >
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
						<!-- 엑셀 등록 메시지  -->
						<table class="datamessage">
						<tr>
							<td>${sResult}</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="title">
						<tr>
							<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="타이틀이미지" title="타이틀이미지"/></td>
							<td class="title">프로그램 관리</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table summary="엑셀파일 등록" class="write01">
						<caption>엑셀파일 등록</caption>
						<tr>
							<th width="20%" height="23" class="required_text" scope="row"><label for="fileNm">프로그램정보  엑셀파일</label></th>
							<td><input name="fileNm" type="file" id="fileNm"/></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="rbuttonarea">
						<tr>
							<td>
							<c:choose>
								<c:when test="${fileVO != null}">
									<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="UTF-8">
										<c:param name="param_atchFileId" value="FILE_000000000011846" />				
									</c:import>
								</c:when>
								<c:otherwise>
									<c:out value="샘플양식이 존재하지 않습니다." />
								</c:otherwise>
							</c:choose>
							</td>
							<td><a onclick = "fn_egov_regist_excelprogram();" style="cursor:pointer;"><img src="/images/button/0210.png" alt="<spring:message code='button.save'/>" title="<spring:message code='button.save'/>"></a></td>
							<td class="end"><a onclick="fn_list();" style="cursor:pointer;"><img src="/images/button/0201.png" alt="<spring:message code='button.list'/>" title="<spring:message code='button.list'/>"></a></td>
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
	</form>
</div>
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>