<%-- 
/** 
 * outline : 공통 탬플릿 게시판 상세화면
 * filename : TemplateDetail.jsp
 * @author 기술지원 김일수 
 * @since 2011.05.10 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.05.10     김일수           최초 생성 
 * </pre> 
 */
--%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<title>템플릿게시판 조회화면(상세)</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">

<jsp:include page="/sps/cmm/header.do" flush="false"/> 

<link rel="stylesheet" type="text/css" href="/css/sps/cmm/default.css"></link>
<link rel="stylesheet" type="text/css" href="/css/sps/cmm/table.css"></link>
<link rel="stylesheet" type="text/css" href="/css/sps/cmm/layer.css"></link>

<script type="text/javascript" src="<c:url value='/js/common/fms/EgovMultiFile.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/common/jquery/validationEngine.jquery.css'/>" media="screen" title="no title" charset="utf-8" />
<link href="<c:url value='/css/sample/ccm/button.css' />" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery-1.5.2.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery.ui.core.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery.ui.datepicker.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery.form.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery.validationEngine-en.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery.validationEngine.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/jquery/NaraJQuery.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/NaraCommon.js'/>"></script>
<script type="text/javaScript" language="javascript">
JQ_setValidation('templateVO');
JQ_onload();
<!--
<%-- 
/************************************************************************ 
fnc name : fncPageOnload                                   
outline : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
parameter : 없음        
directions : fncPageOnload()              
since : 2011-05-10   
author : 기술지원 김일수       

    date      author             note  
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
fnc name : fn_list                                   
outline : "목록"버튼을 클릭했을때 게시물 리스트로 돌아가는 화면       
parameter : 없음        
directions : fn_list()
since : 2011-05-10
author : 기술지원 김일수       

    date      author             note  
 ----------   -------     ------------------- 
                                 
************************************************************************/
--%>
function fn_list(){
	JQ_request("templateVO", "<c:url value='/sample/tem/selectTemplateList.do'/>", "templateVO");
}

<%-- 
/************************************************************************ 
fnc name : fn_modify                                   
outline : "수정"버튼을 클릭했을때 게시물 수정 화면으로 이동하는 함수
		서버단으로 이동하여 게시물의 키값으로 게시물 정보를 다시 조회해옴
parameter : 없음(form에 정의되어있는 인자)
directions : fn_modify()
since : 2011-05-10
author : 기술지원 김일수       

    date      author             note  
 ----------   -------     ------------------- 
                                 
************************************************************************/
--%>
function fn_modify(){

	JQ_request("templateVO", "<c:url value='/sample/tem/modifyTemplate.do'/>", "templateVO");
}

<%-- 
/************************************************************************ 
fnc name : fn_delete                                   
outline : "삭제"버튼을 클릭했을때 해당 키값에 대한 게시물을 삭제하는 함수
		게시물에대한 정보를 삭제한 후 컨트롤러를 통해 게시물 목록으로 이동한다
parameter : 없음(form에 정의되어있는 인자)
directions : fn_delete()
since : 2011-05-10
author : 기술지원 김일수       

    date      author             note  
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>

function fn_delete(){
	if (confirm("<spring:message code="common.delete.msg" />")) {
		JQ_request("templateVO", "<c:url value='/sample/tem/deleteTemplateDetail.do'/>", "templateVO");
	}
}
-->
</script>
</head>
<body>
<div class="ui-layout-content">
<!-- page contents start -->
<div class="center_contents_area">
    <!-- page title / frameset controller start -->
    <div class="page_subject">
        <div class="page_title">템플릿 게시판 상세조회</div>
        <div class="frmctl" id="frmctl"></div>
    </div>
    <!-- page title / frameset controller end -->    
<form:form commandName="templateVO" name="selectForm" method="post">
<form:hidden path="templateId"/>
<form:hidden path="pageIndex"/>
<form:hidden path="fromDate"/>
<form:hidden path="toDate"/>

<table class="default" >
<tr>
	<td>
		<table class="read01">
		<tr>
			<th class="subject"><spring:message code='common.template.listID'/></th>
			<td>${result.templateId}</td>
		</tr>
		<tr>
			<th class="subject"><spring:message code='common.template.regUser'/></th>
			<td>${result.templateRegister}</td>
		</tr>
		<tr>
			<th class="subject"><spring:message code='common.template.regDate'/></th>
			<td>${result.templateRegistDe}</td>
		</tr>
		<tr>
			<th class="subject"><spring:message code='common.template.updateDate'/></th>
			<td>${result.templateUpdtDe}</td>
		</tr>
		<tr>
			<th class="subject"><spring:message code='common.template.seCode'/></th>
			<td>${result.templateSeCode}</td>
		</tr>
		<tr>
			<th class="subject"><spring:message code='common.template.title'/></th>
			<td>${result.templateTitle}</td>
		</tr>
		<tr>
			<th class="subject"><spring:message code='common.template.content'/></th>
			<td>${result.templateContent}</td>
		</tr>
		<c:if test="${result.templateHasfile != 'N'}">
		<tr>
			<th class="subject"><spring:message code='common.template.hasFile'/></th>
			<td>			
			<c:import url="/cmm/fms/selectFileInfs.do">
				<c:param name="param_atchFileId" value="${result.templateFileid}" />				
			</c:import>
			</td>
		</tr>
		<tr>
			<th class="subject"><spring:message code='common.template.hasFile'/></th>
			<td>			
			<c:import url="/cmm/fms/selectFileInfs.do">
				<c:param name="param_atchFileId" value="${result.templateFileid2}" />				
			</c:import>
			</td>
		</tr>
		</c:if>
		</table>
	</td>
</tr>
<tr>
	<td align="center">
		<table class="rbuttonarea">
		<tr>
			
			<td><img src="/images/button/0209.png" alt="<spring:message code='button.update'/>" title="<spring:message code='button.update'/>" style="cursor:hand" onclick = "fn_modify();"></td>
			<td><img src="/images/button/0204.png" alt="<spring:message code='button.delete'/>" title="<spring:message code='button.delete'/>" style="cursor:hand" onclick = "fn_delete();"></td>
			<td><img src="/images/button/0201.png" alt="<spring:message code='button.list'/>" title="<spring:message code='button.list'/>"  style="cursor:hand" onclick = "fn_list();"></td>
		</tr>
		</table>
	</td>
</tr>
</table>
</form:form>
</div>
</div>
</body>
</html>
