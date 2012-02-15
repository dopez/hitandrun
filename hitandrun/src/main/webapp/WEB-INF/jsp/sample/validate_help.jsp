<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<%-- Header Start ==========================================================--%>
<jsp:include page="/sps/cmm/header.do" flush="false"/> 
<%-- Header Start ==========================================================--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<title>공통분류코드 등록</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="/css/sample/ccm/com.css">
<link type="text/css" rel="stylesheet" href="/css/sample/ccm/style.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jquery/validationEngine.jquery.css'/>" media="screen" title="no title" charset="utf-8" />

	
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
	

	<!-- 등록  폼 영역  -->	
	<table width="700" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="10">
			
4. options for IRM validation
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
<input value="karnius"  class="validate[required,confirm[passwd1]] text-input" type="password" name="passwd2"  id="idpw2" />
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
<textarea value="ced@hotmail.com" class="validate[required,length[6,300]] text-input" name="comments" id="comments" /> </textarea>
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
case.14:필수입력사항, 영문자만 입력가능, 길이제한 100자리, testFunc라는 사용자정의 함수 호출, boolean 결과에 따라 메세지 출력
validate[required,custom[onlyLetter],length[0,100],funcCall[testFunc]] text-input
<script language="javascript">
function testFunc(){
			if($("#tmp1").val() =="" || $("#tmp2").val() == ""){
				return true;
			}else{
				return false;
			}
		}
</script>		
<input value=""  class="validate[required,custom[onlyLetter],length[0,100],funcCall[testFunc]] text-input" type="text" id="lastname" name="lastname"  />
<br><br>
case.15:필수입력사항, 특수문자사용불가, 20자리 길이제한, onblur()시 ajax로 controller호출, 사용하실분은 공통파트로 연락주셈,정리중
validate[required,custom[noSpecialCaracters],length[0,20],ajax[ajaxName]]
<br><br>
case.16:필수입력사항, 날짜형식체크
<input value="1111-11-11"  class="validate[required,custom[date]] text-input" type="text" name="date"  id="date" />
<br><br>
case 17:선택입력사항, 입력없는 공백일시 공백으로도 조회가능, 
									입력시 뒤에 따라오는 validate rule(숫자만입력가능, 길이6자리제한)을 체크,
validate[optional,custom[onlyNumber],length[0,6]]
<input type="text" id="schUserId" name="schUserId" size="6" maxlength="6" class="validate[optional,custom[onlyNumber],length[0,6]] text-input" style="ime-mode: disabled"  onkeydown="javascript:if(event.keyCode == 13) {fncSearch(document.listForm);}">
<br><br>
case.18:한글만입력
validate[optional,custom[OnlyKor]]
<input type="text" id="schUserId" name="schUserId" size="6" maxlength="6" class="validate[optional,custom[OnlyKor]] text-input" style="ime-mode: disabled"  onkeydown="javascript:if(event.keyCode == 13) {fncSearch(document.listForm);}">
<br><br>
case.19:한글,영문만 가능
validate[optional,custom[OnlyKorEng]]
<input type="text" id="schUserId" name="schUserId" size="6" maxlength="6" class="validate[optional,custom[OnlyKorEng]] text-input" style="ime-mode: disabled"  onkeydown="javascript:if(event.keyCode == 13) {fncSearch(document.listForm);}">
<br><br>
case.20:제목을 입력하세요
cssClass="validate[required,custom[reqSubject]] text-input"
<input type="text" id="schUserId" name="schUserId" size="6" maxlength="6" class="validate[required,custom[reqSubject]] text-input" style="ime-mode: disabled"  onkeydown="javascript:if(event.keyCode == 13) {fncSearch(document.listForm);}">
<br><br>
case.21:내용을 입력하세요
cssClass="validate[required,custom[reqContents]] text-input"
<input type="text" id="schUserId" name="schUserId" size="6" maxlength="6" class="validate[required,custom[reqContents]] text-input" style="ime-mode: disabled"  onkeydown="javascript:if(event.keyCode == 13) {fncSearch(document.listForm);}">
<br><br>
			</td>
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