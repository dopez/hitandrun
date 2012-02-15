<%-- 
/** 
 * outline   : 공통 탬플릿 게시판 등록화면
 * filename : TemplateRegist.jsp
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
<%@ taglib prefix="validator"
	uri="http://www.springmodules.org/tags/commons-validator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<title>템플릿게시판 등록화면</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<%-- Header Start ==========================================================--%>

<jsp:include page="/sps/cmm/header.do" flush="false" />

<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/common/jquery/validationEngine.jquery.css'/>"
	media="screen" title="no title" charset="utf-8" />

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
fnc name : fn_regist                               
outline : 게시물 항목 수정후 재등록 하는 함수
		공통메시지를 띄워 저장여부 확인한 뒤 사용자가 확인을 하면 DB로 내용을 보내서 Update 시킴
parameter : 없음(form 내용 modelAttribute 자동세팅)
directions : fn_regist()
since : 2011-05-10
author : 기술지원 김일수       

    date      author             note  
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
function fn_regist(){
	if(confirm("<spring:message code='common.save.msg' />")){
		JQ_request("templateVO", "<c:url value='/sample/tem/insertTemplate.do'/>");
	}
}
var countFile = 0;

//파일 행 추가
function fn_FileAdd() 
{
	countFile = countFile + 1;
	var addStr01 = "<td width='50%'><input type='file' name='file" + countFile + "'" + " class='input_t'  style='width:350px; height:19px'></td>"; 		
 	var addStr02 = "<td width='50%'><input type='checkbox' name='checkListFile' >삭제</td><input type='hidden' name='checkListFileCheck' value='N'>";
 	
 	var table = document.getElementById("fileAdd");
 	var newRow = table.insertRow();
 	var newCell01 = newRow.insertCell();	newCell01.className="input_t";
 	var newCell02 = newRow.insertCell();	newCell02.className="table_cont"; 
 	newCell01.innerHTML = addStr01;
 	newCell02.innerHTML = addStr02;
 	//countFile++;
}

//파일 행 삭제
function fn_FileDel() 
{
	//var obj = document.saveForm;
	var chkCnt = fn_CheckedCnt('checkListFile');
		
	var table = document.getElementById("fileAdd");
	
	var obj = document.getElementsByName('checkListFile');
	
	for (var j=0; j<chkCnt; j++)
	{
		for(var i=0;i<obj.length;i++)
		{
			if(obj[i].checked == true)
			{
				table.deleteRow(i);
				countFile--;
				break;
			}	
		}
	}
}

function fn_CheckedCnt(objName)
{
	var obj = document.getElementsByName(objName);
	var returnValue = 0;
	for(var i=0;i<obj.length;i++){
		if(obj[i].checked == true){
			returnValue = returnValue + 1;
		}
	}
	return returnValue;
} 


-->
</script>
</head>
<body>
<div class="ui-layout-content"><!-- page contents start -->
<div class="center_contents_area"><!-- page title / frameset controller start -->
<div class="page_subject">
<div class="page_title">템플릿 게시판 등록</div>
<div class="frmctl" id="frmctl"></div>
</div>
<!-- page title / frameset controller end --> <form:form
	commandName="templateVO" name="insertForm" method="post"
	enctype="multipart/form-data">
	<input type="hidden" name="posblAtchFileNumber"
		id="posblAtchFileNumber" value="3" />
	<table class="default">

		<tr>
			<td>
			<table summary="summary" class="write01">
				<tr>
					<th class="subject"><spring:message code='common.template.seCode'/></th>
					<td class="input"><form:select path="templateSeCode"
						cssClass="validate[required]">
						<form:option value="" label="-선택-" />
						<form:option value="001" label="분류1" />
						<form:option value="002" label="분류2" />
						<form:option value="003" label="분류3" />
					</form:select></td>
				</tr>
				<tr>
					<th class="subject"><spring:message code='common.template.title'/></th>
					<td class="input"><form:input path="templateTitle"
						cssClass="validate[required,length[1,255]]" size="60" /></td>
				</tr>
				<tr>
					<th class="subject"><spring:message code='common.template.content'/></th>
					<td class="input"><form:textarea path="templateContent"
						cssClass="validate[required,length[1,255]]" rows="5" cols="65" />
					</td>
				</tr>
				<tr>
					<th class="subject" rowspan="2"><spring:message code='common.template.addFile'/></th>
					<td>
					<table class="rbuttonarea">		
					  <tr>
					    <td class="rbuttonarea">
					    	<a href="javascript:fn_FileAdd();"><img src="<c:url value='/images/button/0211.png'/>" style="cursor:hand" alt="<spring:message code='button.addFileRow'/>" /></a> 
					    	<a href="javascript:fn_FileDel();"><img src="<c:url value='/images/button/0204.png'/>" style="cursor:hand" alt="<spring:message code='button.deleteFileRow'/>" /></a>
					    </td>
					  </tr>
					</table>
					<table  border="1" class="default">
						<tr>
							<td style="text-align:center;font-weight:bold; color:#66a3d3; " width="80%"><spring:message code='common.template.hasFile'/></td>							
							<td style="text-align:center;font-weight:bold; color:#66a3d3; " width="20%"><spring:message code='common.template.delete'/></td>
						</tr>
					</table>					
					<table id="fileAdd"	class="default">
					</table>

					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td align="center">
			<table class="rbuttonarea">
				<tr>
					<td><img src="/images/button/0210.png"
						alt="<spring:message code="button.save" />"
						title="<spring:message code="button.save" />"
						onclick="fn_regist();"></td>
					<td><img src="/images/button/0201.png"
						alt="<spring:message code="button.list" />"
						title="<spring:message code="button.list" />" onclick="fn_list();"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<input name="cmd" type="hidden" value="<c:out value='save'/>" />
</form:form></div>
</body>
</html>