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
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ page import="com.bbm.common.cmm.service.PrintVO"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<title>템플릿게시판 등록화면</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<%-- Header Start ==========================================================--%>

<jsp:include page="/sps/cmm/header.do" flush="false"/> 


<link rel="stylesheet" type="text/css" href="<c:url value='/css/common/jquery/validationEngine.jquery.css'/>" media="screen" title="no title" charset="utf-8" />

<script type="text/javascript" src="<c:url value='/js/common/fms/EgovMultiFile.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery.popupWindow.js'/>" ></script>


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
		JQ_request("templateVO", "<c:url value='/sample/tem/insertTemplateFile.do'/>");
	}
}

<%-- 
/************************************************************************ 
fnc name : fn_print                               
outline : 출력하는 공통함수 
parameter : 없음
directions : fn_print()
since : 2011-05-10
author : 기술지원 김일수       

    date      author             note  
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>

function fn_print(){
	                   
	var param = new Array (
	  "connection.reportname=/TEST.ozr",
	  "connection.pcount=1",   
	  "connection.args1=P_TITLE=UDS_TEST",
	  "odi.odinames=CMM_OD",  
	  "odi.CMM_OD.pcount=1",   
	  "odi.CMM_OD.args1=P_PARAM=ksd"
	);
	
	gfn_printPopupWin( "PrintVO", param, "출력", 768, 1024, "no", "no");
	
}

<%-- 
/************************************************************************ 
fnc name : fn_print2                               
outline : iframe에 출력하는 공통 출력함수 
parameter : 없음
directions : fn_print2()
since : 2011-05-10
author : 기술지원 김일수       

    date      author             note  
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>


function fn_print2(){
	                   
	var param = new Array (
	  "connection.reportname=/TEST.ozr",
	  "connection.pcount=2",   
	  "connection.args1=username=사용자1",
	  "connection.args2=imgurl=http://oztn.net/kb/img/common/main/bottomLogo_n.gif",
	  "odi.odinames=패러미터라벨",  
	  "odi.패러미터라벨.pcount=2",   
	  "odi.패러미터라벨.args1=FromDate=2010-02-01",
	  "odi.패러미터라벨.args2=ToDate=2010-05-31"
	);
	
	gfn_printWin( "PrintVO", param, "iprint" );
	
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
        <div class="page_title">템플릿 게시판 등록1</div>
        <div class="frmctl" id="frmctl"></div>
    </div>
    <!-- page title / frameset controller end -->

	<form:form commandName="templateVO" name="insertForm" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />
		<table class="default">
			
			<tr>
				<td>
					<table summary="summary" class="write01">
						<tr>
							<th class="subject"><spring:message code='common.template.seCode' /></th>
							<td class="input">
								<form:select path="templateSeCode" cssClass="validate[required]">
									<form:option value="" label="-선택-" />
									<form:option value="001" label="분류1" />
									<form:option value="002" label="분류2" />
									<form:option value="003" label="분류3" />
								</form:select>
							</td>
						</tr>
						<tr>
							<th class="subject"><spring:message code='common.template.title' /></th>
							<td class="input">
							<form:input path="templateTitle" cssClass="validate[required,length[1,255]]" size="60" />
							</td>
						</tr>						
						<tr>
							<th class="subject"><spring:message code='common.template.title'/></th>
							<td class="input">
							<form:textarea path="templateContent" cssClass="validate[required,length[1,255]]" rows="5" cols="65" />
							</td>
						</tr>
						<tr>
							<th class="subject" ><spring:message code='common.template.addFile'/>1</th>
							<td><input name="file_1" id="file_1" type="file" /></td>
						</tr>
						<tr>
							<th class="subject" ><spring:message code='common.template.addFile'/>2</th>
							<td><input name="file_2" id="file_2" type="file" /></td>
						</tr>						
					</table>					
				</td>
			</tr>
			<tr>
				<td align="center">
					<table class="rbuttonarea">
						<tr>
							<td><img src="/images/button/0210.png" alt="<spring:message code="button.save" />" title="<spring:message code="button.save" />" style="cursor:hand" onclick = "fn_regist();"></td>
							
							<td><img src="/images/button/0201.png" alt="<spring:message code="button.list" />" title="<spring:message code="button.list" />" style="cursor:hand" onclick = "fn_list();"></td>
							
							<td><img src="/images/button/0216.png" alt="<spring:message code="button.print" />" 
							 title="<spring:message code="button.print" />" style="cursor:hand" onclick = "fn_print();"></td>
							
							<td><img src="/images/button/0216.png" alt="<spring:message code="button.print" />" 
							title="<spring:message code="button.print" />" style="cursor:hand" onclick = "fn_print2();"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	<input name="cmd" type="hidden" value="<c:out value='save'/>" />
	</form:form>
	<iframe name="iprint" src="" style="width:100%; height:340px;" frameborder=0></iframe>
	<form:form commandName="PrintVO" name="PrintVO" method="post"  >
	    <input name="printData" type="hidden" />
	</form:form>
</div>
</body>
</html>