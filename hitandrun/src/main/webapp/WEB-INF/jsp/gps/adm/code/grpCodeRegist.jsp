<%-- 
/** 
 * 설명   : 코드관리 시스템코드 등록화면
 * 파일명 : grpCodeRegist.jsp
 * @author 범정부통계포털 이관형 
 * @since 2011.06.23 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.06.23     이관형           최초 생성 
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
	JQ_setValidation('codeManageVO');
	
	JQ_setCalendar('validBgnde','validEndde','from');
	JQ_setCalendar('validEndde','validBgnde','to');
	JQ_onload();
<%-- 
/************************************************************************ 
함수명 : fncPageOnload                                   
설   명 : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
인   자 : 없음        
사용법 : fncPageOnload()              
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형       

  date         author            note
 ----------   -------     ------------------- 
                              
************************************************************************/ 
--%>
function fncPageOnload()
{
	<c:if test="${!empty message}">
		parent.codeFrame.location.href = "/gps/adm/code/selectGrpCodeList.do?upperCodeId=" + document.insertForm.upperCodeId.value;
		parent.subCodeFrame.location.href = "/gps/adm/code/selectCodeList.do?upperCodeId=" + document.insertForm.codeId.value;
	</c:if>
}

<%-- 
/************************************************************************ 
함수명 : fn_regist                               
설   명 : 코드 항목 수정후 재등록 하는 함수
		공통메시지를 띄워 저장여부 확인한 뒤 사용자가 확인을 하면 DB로 내용을 보내서 Update 시킴
인   자 : 없음(form 내용 modelAttribute 자동세팅)
사용법 : fn_regist()
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형       

      date    author            note
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
function fn_regist(){
	if(confirm("<spring:message code='common.save.msg' />")){
		document.insertForm.target = "_self";
		JQ_request("codeManageVO", "<c:url value='/gps/adm/code/insertGrpCode.do'/>");
	}
}

function fn_codeNameChk() {
	var ptn = /[^A-z|0-9|가-힣ㄱ-ㅎ]/g;
	if($('#useCodeId').val().match(ptn)){
		$('#useCodeId').val("");
		$('#useCodeId').focus();
		$('#useCodeId').html("공백이나 특수문자는 입력이 불가능 합니다.");
	}else{
		var options = {
								 success     :fn_codeNmSuccess,
					             error       :fn_error,
				                 url         :'/gps/adm/code/checkUseCodeId.do',			                 
				                 contentType :'application/x-www-form-urlencoded; charset=UTF-8',
				                 type        :'post',
				                 dataType    :'json'
				               };   
	     JQ_requestAjax('codeManageVO',options);
	}
}

<%-- 
/************************************************************************ 
fnc name : fn_dbNmSuccess                                   
outline :  관리자목록호출 성공
parameter : response json객체 
directions : fn_dbNmSuccess()              
since : 2011-05-10   
author : 통계포털 황기연

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_codeNmSuccess(response) 
{	
	var message = response.message;
	var checkAt = response.checkAt;
	
	if(checkAt){
		$('#useCodeId').val("");
		$('#useCodeId').focus();
	}
	$('#chkMessage').html(message);
}

<%-- 
/************************************************************************ 
fnc name : fn_error                                   
outline :  ajax 호출에 실패했을떄 호출되는 함수  
parameter : error json객체 
directions : fn_error()              
since : 2011-05-10   
author : 기술지원 김일수       

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_error( error ) {
	if( error.statusText == "error" ) {
	   alert( "<spring:message code="fail.common.msg" />" );
	}
}

</script>
<%-- javascript end ============================================--%>
<form:form commandName="codeManageVO" name="insertForm" method="post">
<form:hidden path="codeId" />
<form:hidden path="upperCodeId" />
<!-- contents_area start -->
<div class="contents_area">
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
						<td class="title"><c:out value="${codeManageVO.upperCodeNm}" />추가</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table summary="코드  등록" class="write01">
					<caption>코드 등록</caption>
					<tr>
						<th class="reqsubject">코드</th>
						<td>
							<table class="inside02">
							<tr>
								<td class="input">
									<form:input path="useCodeId" id="useCodeId" size="50" cssClass="wi100 validate[required,length[1,5],custom[onlyEngNumber]] text-input" maxlength="50" onchange="fn_codeNameChk();" />
								</td>
								<td class="img"><div id="chkMessage"></div></td>
							</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th class="reqsubject">한글코드명</th>
						<td class="input"><form:input path="codeNm" id="codeNm" size="50" cssClass="validate[required,length[1,100],custom[onlyKorEng]] text-input; wi150;" maxlength="100" /></td>
					</tr>
					<tr>
						<th class="subject">한글코드단축명</th>
						<td class="input"><form:input path="codeAbbrNm" size="50" cssClass="validate[optional,custom[onlyKorEng],length[0,100]; wi150;" maxlength="60" /></td>
					</tr>
					<tr>
						<th class="subject">영문코드명</th>
						<td class="input"><form:input path="codeEngNm" size="50" cssClass="validate[optional,custom[onlyLetter],length[0,100]; wi150;" maxlength="100" /></td>
					</tr>
					<tr>
						<th class="subject">영문코드단축명</th>
						<td class="input"><form:input path="codeAbbrEngNm" size="50" cssClass="validate[optional,custom[onlyLetter],length[0,100]; wi150;" maxlength="60" /></td>
					</tr>
					<tr>
						<th class="subject">유효 시작일</th>
						<td class="no_padding">
							<table class="inside02">
							<tr>
								<td class="input">
									<form:input path="validBgnde"  size="10" cssStyle="vertical-align:middle" cssClass="input_t input_nara_date" onfocus="gfn_focus(this);" onblur="gfn_focusnot(this);"/>
								</td>
							</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th class="subject">유효 종료일</th>
						<td class="no_padding">
							<table class="inside02">
							<tr>
								<td class="input">
									<form:input path="validEndde"  size="10" cssStyle="vertical-align:middle" cssClass="input_t input_nara_date" onfocus="gfn_focus(this);" onblur="gfn_focusnot(this);"/>
								</td>
							</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th class="subject">사용여부</th>
						<td>
							<table class="inside02">
							<tr>
								<td class="input"><form:radiobutton path="fncValAt" id="fncValAtY" value="Y" cssClass="noline"/></td>
								<td><label for="fncValAtY" title="사용">사용</label></td>
								<td class="input"><form:radiobutton path="fncValAt" id="fncValAtN" value="N" cssClass="noline"/></td>
								<td><label for="fncValAtN" title="사용안함">사용안함</label></td>
							</tr>
							</table>
						</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table class="rbuttonarea">
					<tr>
						<td><a onclick="javascript:fn_regist();" style="cursor:pointer;"><img src="/images/button/0210.png" alt="<spring:message code="button.create" />" title="<spring:message code="button.create" />" ></a></td>
						<td class="end"><a onclick="history.back(-1);" style="cursor:pointer;"><img src="/images/button/0203.png" alt="<spring:message code="button.list" />" title="<spring:message code="button.list" />"></a></td>
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
</div>
<!-- container end -->
</form:form>
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>