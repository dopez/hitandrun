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
<%@ page import="com.bbm.sps.sample.tem.service.ValidateTemplateVO"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<title>Validate 템플릿</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<%-- Header Start ==========================================================--%>

<jsp:include page="/sps/cmm/header.do" flush="false"/> 

<link rel="stylesheet" type="text/css" href="<c:url value='/css/common/jquery/validationEngine.jquery.css'/>" media="screen" title="no title" charset="utf-8" />
<script type="text/javascript" src="<c:url value='/js/common/fms/JqGridMultiFile.js'/>" ></script>

<%-- stylesheet Start	======================================================--%>
<link rel="stylesheet" type="text/css" href="/css/common/jquery/jquery.ui.all.css" />
<link rel="stylesheet" type="text/css" href="/css/common/jquery/layout-default-latest.css" />
<link rel="stylesheet" type="text/css" href="/css/common/jqgrid/jquery-ui-1.8.2.custom.css" />
<link rel="stylesheet" type="text/css" href="/css/common/jqgrid/ui.jqgrid.css" />
<%-- stylesheet End	========================================================--%>

<%-- javascript start ==============================================--%>
<script type="text/javascript" src="/js/sps/sds/cmm/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="/js/common/jquery/jquery.layout-latest.js"></script>
<script type="text/javascript" src="/js/common/jquery/debug.js"></script>
<script type="text/javascript" src="/js/common/jquery/themeswitchertool.js"></script>	 
<script type="text/javascript" src="/js/common/jqgrid/grid.locale-en.js"></script>
<script type="text/javascript" src="/js/common/jqgrid/jquery.jqGrid.MultiHeader.min.js"></script>
<script type="text/javascript" src="/js/common/jqgrid/grid.paste.js"></script>
<script type="text/javascript" src="/js/common/jqgrid/json2.js"></script>
<script type="text/javascript" src="/js/common/jqgrid/jquery.jqGrid.init.js"></script>
<script type="text/javascript" src="/js/common/jquery/jquery.blockUI.js"></script>
<script type="text/javascript" src="/js/common/jquery/jquery.popupWindow.js"></script>
<%-- javascript end ============================================--%>

<script type="text/javaScript" language="javascript">
<!--
JQ_setValidation('validateTemplateVO');
JQ_onload(); 



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
   	JQ_request("validateTemplateVO", "<c:url value='/sample/tem/selectTemplateJqGridList.do'/>", "validateTemplateVO");
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
		JQ_request("validateTemplateVO", "<c:url value='/sample/tem/insertTemplateJqGridFile.do'/>");
	}
}


<%-- 
/************************************************************************ 
fnc name : fn_del                               
outline : 첨부파일 리스트에서 삭제하는 함수 
parameter : 없음
directions : fn_del()
since : 2011-05-10
author : 기술지원 김일수       

    date      author             note  
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
function fn_del() {
	var ab  = $("#addFile").jqGrid('getGridParam','selarrrow');
	if( ab.length < 1 ) 
	{
		alert("선택된 자료가 없습니다");
		return;
	}	
	
	for(var i = ab.length-1; i >= 0; i--){

		 var rowdata = $("#addFile").getRowData(ab[i]);	

		 alert( rowdata.id  );
		 
		 $("#" + rowdata.id ).remove();
         $("#addFile").jqGrid('delRowData',(ab[i]));
         
         
	}
}

function fn_click() {

	document.insertForm.file.click();
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
        <div class="page_title">Validate 템플릿</div>
        <div class="frmctl" id="frmctl"></div>
    </div>
    <!-- page title / frameset controller end -->

    <div class="center_inner_contents"  id="bassContents">
	<form:form commandName="validateTemplateVO" name="insertForm" method="post" enctype="multipart/form-data" >
	
		<table class="default">
			<tr>
					<td align="center">
						<table class="rbuttonarea">
							<tr>
								<td><img src="/images/button/0210.png" alt="<spring:message code="button.save" />" title="<spring:message code="button.save" />" style="cursor:hand" onclick = "fn_regist();"></td>
							</tr>
						</table>
					</td>
			</tr>
			<tr>
			<td height="50" >
			</td>
			</tr>		
			<tr>
						<td height="10">
case.1:필수입력사항, 숫자만 입력가능, 길이제한(0-3까지)
class="validate[length[0,3]] text-input"
<input value="22"  class="validate[required,custom[onlyNumber],length[0,3]] text-input" type="text" name="age"  id="age" />

<br><br>
case.2:필수입력사항, 전화번호형식으로만 입력가능, 길이제한없음 
validate[required,custom[telephone]] text-input
<input value="1111111111"  class="validate[required,custom[telephone]] text-input" type="text" name="telephone"  id="telephone" />
<br><br>
case.3:필수입력사항, 최소6자리, 최대11자리로 길이재한 				
validate[required,length[6,11]] text-input
<input value="karnius"  class="validate[required,length[6,11]] text-input" type="password" name="password"  id="password" />
	<br><br>				
case.4:필수입력사항, passwd1필드와 값이 일치하는지 체크					
validate[required,confirm[passwd1]] text-input
<input value="karnius"  class="validate[required,length[6,11]] text-input" type="password" name="passwd1"  id="idpw1" />
<input value="karnius"  class="validate[required,confirm[idpw1]] text-input" type="password" name="passwd2"  id="idpw2" />
<br><br>
case.5:필수입력사항, 이메일형식으로 입력
validate[required,custom[email]] text-input
<input value="ced@hotmail.com"  class="validate[required,custom[email]] text-input" type="text" name="email" id="em1"  />
<br><br>
case.6:필수입력사항, 입력한 이메일 이중입력시 확인 
validate[required,confirm[email]] text-input
<input value="ced@hotmail.com" class="validate[required,confirm[em1]] text-input" type="text" name="email2"  id="em2" />
<br><br>
case.7:필수입력사항, 길이제한, textarea형식에 사용권장
validate[required,length[6,300]] text-input
<textarea class="validate[required,length[6,300]] text-input" name="comments" id="comments" />ced@hotmail.com</textarea>
<br><br>
case.8:라디오버턴에 사용됨, name이 같은 라디오 버턴이 n개 있을경우  최소 하나의 라디오 버턴이 선택되게함 
validate[required] radio
<input class="validate[required] radio" type="radio" name="rdo1"  id="radio1"  value="5">
<input class="validate[required] radio" type="radio" name="rdo1"  id="radio2"  value="5">
<input class="validate[required] radio" type="radio" name="rdo1"  id="radio3"  value="5">
<br><br>
case.9:체크박스에 사용됨, name이 같은 체크박스가 n개 있을경우 최소 2개의 체크박스가 체크되게함
validate[minCheckbox[2]] checkbo
<input class="validate[minCheckbox[2]] checkbox" type="checkbox" name="chk1" id="maxcheck2"  value="3"/>				
<input class="validate[minCheckbox[2]] checkbox" type="checkbox" name="chk1" id="maxcheck3"  value="9"/>
<input class="validate[minCheckbox[2]] checkbox" type="checkbox" name="chk1" id="maxcheck3"  value="9"/>
<br><br>
case.10:콤보박스에 사용됨, 최소 하나의 값이 선택되게 함
validate[required]
<select name="sport" id="sport"  class="validate[required]"  id="sport"  >
					<option value="">선택하세요</option>
					<option value="option1">선택1</option>
					<option value="option2">선택2</option>
					<option value="option3">선택3</option>
				</select>
<br><br>
case.11:필수입력사항, 숫자만 입력가능, 3자리로 길이제한
validate[required,custom[onlyNumber],length[0,3]] text-input
<input value="22"  class="validate[required,custom[onlyNumber],length[0,3]] text-input" type="text" name="age"  id="age" />
<br><br>
case.12:필수입력사항, 전화번호 형식만 입력가능 
validate[required,custom[telephone]] text-input
<input value="010-727-7322"  class="validate[required,custom[telephone]] text-input" type="text" name="telephone"  id="telephone" />
<br><br>
case.13:필수체크, 체크박스, 약관에 동의합니다 스타일의 체크시 사용권장
validate[required] checkbox
<input class="validate[required] checkbox" type="checkbox"  id="agree"  name="agree"/>
<br><br>


case.14:필수입력사항, 특수문자사용불가, 20자리 길이제한, onblur()시 ajax로 controller호출, 사용하실분은 공통파트로 연락주셈,정리중
validate[required,custom[noSpecialCaracters],length[0,20],ajax[ajaxName]]
<br><br>
case.15:필수입력사항, 날짜형식체크
<input value="1111-11-11"  class="validate[required,custom[date]] text-input" type="text" name="date"  id="date" />
<br><br>
case 16:선택입력사항, 입력없는 공백일시 공백으로도 조회가능, 
									입력시 뒤에 따라오는 validate rule(숫자만입력가능, 길이6자리제한)을 체크,
validate[optional,custom[onlyNumber],length[0,6]]
<input type="text" id="schUserId" name="schUserId" size="6" maxlength="6" class="validate[optional,custom[onlyNumber],length[0,6]] text-input" style="ime-mode: disabled"  onkeydown="javascript:if(event.keyCode == 13) {fncSearch(document.listForm);}">
<br><br>
case.17:한글만입력
validate[optional,custom[onlyKor]]
<input type="text" id="schUserId1" name="schUserId1" size="6" maxlength="6" class="validate[optional,custom[onlyKor]] text-input" style="ime-mode: disabled"  onkeydown="javascript:if(event.keyCode == 13) {fncSearch(document.listForm);}">
<br><br>
case.18:한글,영문만 가능
validate[optional,custom[onlyKorEng]]
<input type="text" id="schUserId2" name="schUserId2" size="6" maxlength="6" class="validate[optional,custom[onlyKorEng]] text-input" style="ime-mode: disabled"  onkeydown="javascript:if(event.keyCode == 13) {fncSearch(document.listForm);}">
<br><br>

case.19: url형식( 영문자 대문,소문, 숫자, / . & = )
validate[optional,custom[url]]
<input type="text" id="schUserId3" name="schUserId3" size="20" maxlength="20" class="validate[optional,custom[url]] text-input"  onkeydown="javascript:if(event.keyCode == 13) {fncSearch(document.listForm);}">
<br><br>

case.21:필수입력사항, 영문자만 입력가능, 길이제한 100자리, testFunc라는 사용자정의 함수 호출, boolean 결과에 따라 메세지 출력
<input id="tmp1" name="tmp1" type="text" class="validate[required,custom[onlyLetter],length[0,100],funcCall[testFunc]] text-input" >
<script language="javascript">
function testFunc(){
			if($("#tmp1").val() =="a" ){
				return "";
			}else if($("#tmp1").val() =="b" ) {
				return "오류1";
			}else if($("#tmp1").val() =="c" ) {
				return "오류2";
			}
		}
</script>		
<br><br>

<!--
case.19:제목을 입력하세요
cssClass="validate[required,custom[reqSubject]] text-input"
<input type="text" id="schUserId3" name="schUserId3" size="6" maxlength="6" class="validate[required,custom[reqSubject]] text-input" style="ime-mode: disabled"  onkeydown="javascript:if(event.keyCode == 13) {fncSearch(document.listForm);}">
<br><br>
case.20:내용을 입력하세요
cssClass="validate[required,custom[reqContents]] text-input"
<input type="text" id="schUserId4" name="schUserId4" size="6" maxlength="6" class="validate[required,custom[reqContents]] text-input" style="ime-mode: disabled"  onkeydown="javascript:if(event.keyCode == 13) {fncSearch(document.listForm);}">
<br><br>

 
 -->

			</td>
		</tr>			
		<tr>
				<td align="center">
					<table class="rbuttonarea">
						<tr>
							<td><img src="/images/button/0210.png" alt="<spring:message code="button.save" />" title="<spring:message code="button.save" />" style="cursor:hand" onclick = "fn_regist();"></td>
						</tr>
					</table>
				</td>
		</tr>
	</table>
	<input name="cmd" type="hidden" value="<c:out value='save'/>" />
	</form:form>
	</div>
</div>
</div>
</body>
</html>