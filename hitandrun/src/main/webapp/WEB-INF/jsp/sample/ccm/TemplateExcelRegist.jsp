<%-- 
/** 
 * 설명   : 공통 탬플릿 게시판 상세내용을 수정하는 화면
 * 파일명 : TemplateExcelRegist.jsp
 * @author 기술지원 김일수 
 * @since 2011.05.10 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == 개정이력(Modification Information) == 
 *   
 *   수정일               수정자                               수정내용 
 * ----------    -------    --------------------------- 
 * 2011.05.10     김일수           최초 생성 
 * </pre> 
 */
 --%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<title>목록형 템플릿 엑셀파일 등록</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">

<jsp:include page="/sps/cmm/header.do" flush="false"/> 

<link rel="stylesheet" type="text/css" href="<c:url value='/css/common/jquery/validationEngine.jquery.css'/>" media="screen" title="no title" charset="utf-8" />

<script type="text/javascript" src="<c:url value='/js/common/fms/EgovMultiFile.js'/>" ></script>
<script type="text/javaScript" language="javascript">
JQ_onload(); 
<!--
<%-- 
/************************************************************************ 
함수명 : fncPageOnload                                   
설   명 : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
인   자 : 없음        
사용법 : fncPageOnload()              
작성일 : 2011-05-10   
작성자 : 공통서비스 개발팀 김일수       

      수정일              수정자                        수정내용 
 ----------   -------     ------------------- 
 2011.05.10    김일수              프레임 처리 추가                                       
************************************************************************/ 
--%>
function fncPageOnload()
{
	//frameset controller start
	parent.frmtop.setFrameCtl();
	//frameset controller end
}

<%-- 
/************************************************************************ 
함수명 : fn_egov_list_zip                                   
설   명 : "목록"버튼을 클릭했을때 게시물 리스트로 돌아가는 화면       
인   자 : 없음        
사용법 : fn_egov_list_zip()
작성일 : 2011-05-10
작성자 : 공통서비스 개발팀 김일수       

      수정일              수정자                        수정내용 
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
function fn_egov_list_zip(){
	location.href = "/sample/tem/selectTemplateList.do";
}

<%-- 
/************************************************************************ 
함수명 : fn_egov_regist_exceltemplate                               
설   명 : 엑셀파일을 첨부하여 DB에 저장하는함수
인   자 : 없음
사용법 : fn_egov_regist_exceltemplate()
		엑셀파일을 첨부하였는지 여부를 검사하는 로직이 들어있다.
작성일 : 2011-05-10
작성자 : 공통서비스 개발팀 김일수       

      수정일              수정자                        수정내용 
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
function fn_egov_regist_exceltemplate(){
	var varForm				 = document.all["uploadForm"];

	// 파일 확장명 확인
	var arrExt      = "xls";
	var objInput    = varForm.elements["fileNm"];
	var strFilePath = objInput.value;
	var arrTmp      = strFilePath.split(".");
	var strExt      = arrTmp[arrTmp.length-1].toLowerCase();

	if ("xls" != strExt && "xlsx" != strExt) {
		alert("<spring:message code='fail.common.excelupload'/>");
		abort;
	} 
	
	varForm.action           = "/sample/tem/insertTemplateExcelMapUpload.do";
	varForm.submit();

}
-->
</script>
</head>
<a name="noscript" id="noscript">
<noscript class="noScriptTitle"><spring:message code='fail.common.nojavscript'/></noscript>
</a>
<body>


<!-- page contents start -->
<div class="center_contents_area">
    <!-- page title / frameset controller start -->
    <div class="page_subject">
        <div class="page_title">목록형 템플릿 엑셀파일 등록</div>
        <div class="frmctl" id="frmctl"></div>
    </div>
    <!-- page title / frameset controller end -->


<!-- 엑셀 등록 메시지  -->
${sResult}


	<!-- 상단타이틀 -->
	<form action="" name="uploadForm"  method="post" enctype="multipart/form-data" >
		
		<!-- 줄간격조정  -->
		<table width="700" cellspacing="0" cellpadding="0" border="0">
		<tr>
			<td height="3px"></td>
		</tr>
		</table>

		<!-- 등록  폼 영역  -->
		<table width="700" border="0" cellpadding="0" cellspacing="1" class="table-register" summary="템플릿 엑셀파일을 첨부할 수 있는 등록 테이블이다.">
		
		  <tr>
		    <th width="20%" height="23" class="required_text" scope="row" nowrap ><label for="fileNm"><spring:message code='common.template.templateExcelFile'/></label></th>
		  	<td><input name="fileNm" type="file" id="fileNm"/></td>
		  </tr>
		</table>
		<table width="700" border="0" cellspacing="0" cellpadding="0">
		  <tr> 
		    <td height="10"></td>
		  </tr>
		</table>

		<!-- 줄간격조정  -->
		<table width="700" cellspacing="0" cellpadding="0" border="0">
		<tr>
			<td height="3px"></td>
		</tr>
		</table>
		<!-- 목록/저장버튼  -->
		<table border="0" cellspacing="0" cellpadding="0" align="center">
		<tr> 
		  
		 <td><img src="/images/button/0210.png" alt="<spring:message code='button.save'/>" title="<spring:message code='button.save'/>" style="cursor:hand" onclick = "fn_egov_regist_exceltemplate();"></td>
		  <td width="10"></td>
		  <td><img src="/images/button/0201.png" alt="<spring:message code='button.list'/>" title="<spring:message code='button.list'/>" style="cursor:hand" onclick = "fn_egov_list_zip();"></td>
		  
		</tr>
		</table>
	
		<input name="cmd" id="cmd" type="hidden" value="ExcelUpload"/>
	</form>
</DIV>
</body>
</html>