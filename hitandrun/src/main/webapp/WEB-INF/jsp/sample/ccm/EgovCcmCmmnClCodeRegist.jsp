<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="validator"
	uri="http://www.springmodules.org/tags/commons-validator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<title>공통분류코드 등록</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="/css/sample/ccm/com.css">
<link type="text/css" rel="stylesheet" href="/css/sample/ccm/style.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/common/jquery/validationEngine.jquery.css'/>" media="screen" title="no title" charset="utf-8" />
<link href="<c:url value='/css/sample/ccm/button.css' />"
	rel="stylesheet" type="text/css">
	
<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery-1.5.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery.datepick.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery.form.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery.validationEngine-en.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery.validationEngine.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/jquery/NaraJQuery.js'/>"></script>
<script type="text/javaScript" language="javascript">
JQ_setValidation('cmmnClCode');
<!--
/* ********************************************************
 * 목록 으로 가기
 *********************************************************/
function fn_egov_list_CmmnClCode(){
	location.href = "/sample/ccm/ccc/EgovCcmCmmnClCodeList.do";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
 function fn_egov_regist_CmmnClCode(form){
	if(confirm("<spring:message code="common.save.msg" />")){
		
		JQ_request("cmmnClCode","<c:url value='/sample/ccm/ccc/EgovCcmCmmnClCodeRegist.do'/>");
		
	}
}
-->
</script>
</head>

<body>
<a name="noscript" id="noscript">
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부
기능을 사용하실 수 없습니다.</noscript>
</a>
<DIV id="content" style="width: 712px"><!-- 상단타이틀 --> <form:form
	commandName="cmmnClCode" name="cmmnClCode" method="post">
	<!-- ----------------- 상단 타이틀  영역 -->
	<table width="700" cellpadding="8" class="table-search" border="0">
		<tr>
			<td width="100%" class="title_left">
			<h1 class="title_left"><img
				src="/images/sample/ccm/icon/tit_icon.gif" width="16" height="16"
				hspace="3" style="vertical-align: middle" alt="제목">&nbsp;공통분류코드
			등록</h1>
			</td>
		</tr>
	</table>
	<!-- 줄간격조정  -->
	<table width="700" cellspacing="0" cellpadding="0" border="0">
		<tr>
			<td height="3px"></td>
		</tr>
	</table>

	<!-- 등록  폼 영역  -->
	<table width="700" border="0" cellpadding="0" cellspacing="1"
		class="table-register"
		summary="분류코드, 분류코드명, 분류코드설명, 사용여부를 입력하는 공통분류코드 등록 테이블이다.">
		<CAPTION style="display: none;">공통분류코드 등록</CAPTION>
		<tr>
			<th width="20%" height="23" class="required_text" scope="row" nowrap><label
				for="clCode">분류코드</label><img
				src="/images/sample/ccm/icon/required.gif" alt="필수" width="15"
				height="15"></th>
			<td width="80%" nowrap colspan="3">
			   <form:input path="clCode" cssClass="select_t validate[required,length[1,3]]"  size="5" maxlength="5" />			   			   
			</td>
		</tr>
		<tr>
			<th width="20%" height="23" class="required_text" scope="row" nowrap><label
				for="clCodeNm">분류코드명</label><img
				src="/images/sample/ccm/icon/required.gif" alt="필수" width="15"
				height="15"></th>
			<td width="80%" nowrap>
			<form:input path="clCodeNm" cssClass="select_t validate[required,length[1,60]]"  size="60" maxlength="60" />
			 </td>
		</tr>
		<tr>
			<th height="23" class="required_text" scope="row"><label
				for="clCodeDc">분류코드설명</label><img
				src="/images/sample/ccm/icon/required.gif" alt="필수" width="15"
				height="15"></th>
			<td>
			<textarea value="ced@hotmail.com" class="validate[required,length[6,300]] text-input" name="clCodeDc" id="clCodeDc" /> </textarea>
			 </td>
		</tr>
		<tr>
			<th width="20%" height="23" class="required_text" scope="row" nowrap><label
				for="useAt">사용여부</label><img
				src="/images/sample/ccm/icon/required.gif" alt="필수" width="15"
				height="15"></th>
			<td width="30%" nowrap class="title_left" colspan="3">
			    <select name="useAt" id="useAt"   class="validate[required]" >
					<option value="Y" label="Yes" />
					<option value="N" label="No" />
			    </select></td>
			   
		</tr>
	</table>
	<table width="700" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="10">
			</td>
		</tr>
	</table>		
	<!-- 목록/저장버튼  -->
	<table border="0" cellspacing="0" cellpadding="0" align="center">
		<tr>
			<td><img src="/images/sample/ccm/btn/bu2_left.gif" alt="목록"
				width="8" height="20"></td>
			<td
				style="background-image:URL(<c:url value='/images/sample/ccm/btn/bu2_bg.gif'/>);"
				class="text_left" nowrap><a href="#noscript"
				onclick="fn_egov_list_CmmnClCode(); return false;">목록</a></td>
			<td><img src="/images/sample/ccm/btn/bu2_right.gif" alt="목록"
				width="8" height="20"></td>
			<td width="10"></td>
			
			<td><span class="button"><input type="submit" value="저장"
				onclick="fn_egov_regist_CmmnClCode(document.cmmnClCode); return false;"></span></td>
		</tr>
	</table>
	

	<!-- 줄간격조정  -->
	<table width="700" cellspacing="0" cellpadding="0" border="0">
		<tr>
			<td height="3px"></td>
		</tr>
	</table>
	

	<input name="cmd" type="hidden" value="<c:out value='save'/>" />
</form:form></DIV>
</body>
</html>