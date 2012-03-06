<%-- 
/** 
 * 설명   : 코드관리 - 코드엑셀등록화면
 * 파일명 : codeExcelRegist.jsp
 * @author 범정부통계포털 이관형
 * @since 2011.07.27 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == 개정이력(Modification Information) == 
 *   
 *   수정일               수정자                               수정내용 
 * ----------    -------    --------------------------- 
 * 2011.07.27     이관형           최초 생성 
 * </pre> 
 */
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp"%>
<%-- Header Start ==========================================================--%>
<jsp:include page="/gps/cmm/admHeader.do"></jsp:include>
<%-- Header End ==========================================================--%>
<%-- 선행 로직 start	======================================================--%>
<%-- 선행 로직 end	==========================================================--%>

<%-- javascript start ==============================================--%>
<script type="text/javaScript" language="javascript">
	JQ_onload();
<%-- 
/************************************************************************ 
함수명 : fncPageOnload                                   
설   명 : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
인   자 : 없음        
사용법 : fncPageOnload()              
작성일 : 2011-07-27
작성자 : 범정부통계포털 이관형

      수정일              수정자                        수정내용 
 ----------   -------     ------------------- 
 2011.05.10    김일수              프레임 처리 추가                                       
************************************************************************/ 
--%>
function fncPageOnload()
{
	//frameset controller start
	//frameset controller end
	if ('<c:out value="${message}"/>' != '' && '<c:out value="${message}"/>' != null) {
		opener.parent.subCodeFrame.location.href = "/gps/adm/code/selectCodeList.do?upperCodeId=" + document.uploadForm.upperCodeId.value;
		alert('<c:out value="${message}"/>');
		window.close();
	}

	<c:if test="${!empty uploadFailMsg}">
		alert("<c:out value="${uploadFailMsg}"/>");
	</c:if>
}

<%-- 
/************************************************************************ 
함수명 : fn_egov_regist_exceltemplate                               
설   명 : 엑셀파일을 첨부하여 DB에 저장하는함수
인   자 : 없음
사용법 : fn_egov_regist_exceltemplate()
		엑셀파일을 첨부하였는지 여부를 검사하는 로직이 들어있다.
작성일 : 2011-07-27
작성자 : 범정부통계포털 이관형

      수정일              수정자                        수정내용 
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
function fn_egov_regist_excelcode(){
	
	var varForm				 = document.all["uploadForm"];
	
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
	
	varForm.target = "_self";
	varForm.action = "/gps/adm/code/insertCodeExcelUpload.do";
	varForm.submit();
}
</script>
<a name="noscript" id="noscript">
	<noscript class="noScriptTitle"><spring:message code='fail.common.nojavscript'/></noscript>
</a>
<!-- page title / frameset controller end -->
<div class="contents_area">
	<form:form action="" commandName="codeManageVO" name="uploadForm"  method="post" enctype="multipart/form-data" >
	<form:hidden path="upperCodeId" />
	<table class="default">
	<!-- contents_area start -->
	<tr>
		<td>
			<table class="title">
			<tr>
				<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="코드 엑셀등록" title="코드 엑셀등록"/></td>
				<td class="title">코드 엑셀등록</td>
			</tr>
			</table>
		</td>
	</tr>
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
			<table summary="엑셀파일 등록" class="write01">
			<caption>엑셀파일 등록</caption>
			<tr>
				<th class="subject">엑셀파일</th>
				<td class="input"><input name="fileNm" type="file" id="fileNm"/></td>
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
							<c:param name="param_atchFileId" value="FILE_000000000011151" />				
						</c:import>
					</c:when>
					<c:otherwise>
						<c:out value="샘플양식이 존재하지 않습니다." />
					</c:otherwise>
				</c:choose>
				</td>
				<td><img src="/images/button/0210.png" alt="<spring:message code='button.save'/>" title="<spring:message code='button.save'/>" style="cursor: pointer;" onclick="fn_egov_regist_excelcode();"></td>
				<td class="end"><img src="/images/button/0203.png" alt="<spring:message code='button.reset'/>" title="<spring:message code='button.reset'/>" style="cursor: pointer;" onclick="window.close();"></td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
	</form:form>
</div>
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>