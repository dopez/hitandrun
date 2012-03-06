<%-- 
/** 
 * 설명   : 통합권한 관리 - 권한 등록화면
 * 파일명 : iamRegist.jsp
 * @author 범정부통계포털 이관형 
 * @since 2011.08.18 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.08.18     이관형           최초 생성 
 * </pre> 
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>

<%-- Header Start ==========================================================--%>
<jsp:include page="/gps/cmm/admHeader.do"></jsp:include>
<%-- Header End ==========================================================--%>

<script type="text/javascript" src="/js/common/jqgrid/json2.js"></script>

<%-- javascript start ==============================================--%>
<script type="text/javaScript" language="javascript">
JQ_setValidation('authorManageVO');
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
	fn_syssearch();
}

<%-- 
/************************************************************************ 
함수명 : fn_authorList                                   
설   명 : 권한 목록 화면으로 이동한다.
인   자 : 없음        
사용법 : fn_authorList()              
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형       

  date         author            note
 ----------   -------     ------------------- 
                              
************************************************************************/ 
--%>
function fn_authorList() {
	JQ_request("authorManageVO", "<c:url value='/gps/adm/author/intergrated/iamList.do'/>");
}

<%-- 
/************************************************************************ 
함수명 : fn_insert                                   
설   명 : 권한을 등록한다.
인   자 : 없음        
사용법 : fn_insert()              
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형       

  date         author            note
 ----------   -------     ------------------- 
                              
************************************************************************/ 
--%>
function fn_insert() {

    if(confirm("저장 하시겠습니까?")){
    	document.insertForm.target = "_self";
		JQ_request("authorManageVO", "<c:url value='/gps/adm/author/intergrated/iamInsert.do'/>");
    }
}

<%-- 
/************************************************************************ 
함수명 : fn_check                                   
설   명 : 권한을명을 중복체크 한다.
인   자 : 없음        
사용법 : fn_check()              
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형       

  date         author            note
 ----------   -------     ------------------- 
                              
************************************************************************/ 
--%>
function fn_check(){
	gfn_postPopupWin("authorManageVO", "/gps/adm/author/intergrated/iamNmCheck.do", "권한관리 권한명중복확인", 200, 500, "no", "no");
}

<%-- 
/************************************************************************ 
fnc name : fn_syssearch                                   
outline :  동적으로 콤보박스의 내용을 변경해주는 함수 
parameter : 없음
directions : fn_syssearch()              
since : 2011-08-18   
author : 범정부통계포털 이관형       

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_syssearch() {

	document.insertForm.authorNm.value = '';

	if( document.getElementById("sysId").selectedIndex == 0){
		 $("#upperAuthorId").html("");
		return;
	}
	
	var sysId = $("#sysId option:selected").val();

//  아래 예제에서는 searchVO를 이용하지는 않았지만 	
//  searchVO 폼데이타전송을 하면서 ajax함수를 이용한 방법입니다.
//  jquery.form.js에 ajaxsubmit()함수를 호출합니다.
	var options = {         success     :fn_success,
			                 error       :fn_error,
			                 url         :'/gps/adm/author/intergrated/authorComboList.do?'+'selectSysId='+sysId,
			                 contentType :'application/x-www-form-urlencoded; charset=UTF-8',
			                 type        :'post',
			                 dataType    :'json'
			               };

	JQ_setProcessMsg();
	JQ_requestAjax('authorManageVO', options);    		 
	
}

<%-- 
/************************************************************************ 
fnc name : fn_success                                   
outline :  ajax 호출에 성공했을떄 호출뙤는 함수  
parameter : response json객체 
            status 성공여부 
directions : fn_success()              
since : 2011-08-18   
author : 범정부통계포털 이관형       

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_success(response, status) 
{	
	var data = response.result ;
	$("#upperAuthorId").html("");
	if( status == 'success' ) {
		JQ_setProcessMsg_delete();
		for(var index=0 ; index < data.length + 1 ; index++) {
				if (0 == index) {
					$("#upperAuthorId").get(0).options[index] = new Option("- 권한 선택 안함 -", "");
				} else {
					$("#upperAuthorId").get(0).options[index] = new Option(data[index-1].authorNm, data[index-1].authorId);
				}
		}
	}
}

<%-- 
/************************************************************************ 
fnc name : fn_error                                   
outline :  ajax 호출에 실패했을떄 호출뙤는 함수  
parameter : error json객체 
directions : fn_error()              
since : 2011-08-18   
author : 범정부통계포털 이관형       

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_error( error ) {
	if( error.statusText == "error" ) {
	   alert( "<spring:message code="fail.common.msg" />" );
	}
}

<%-- 
/************************************************************************ 
fnc name : fn_reset                                   
outline :  권한명 초기화
directions : fn_reset()              
since : 2011-08-18   
author : 범정부통계포털 이관형       

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_reset() {
	document.insertForm.authorNm.value = '';
}

</script>
<%-- javascript end ============================================--%>
<!-- contents_area start -->
<div class="contents_area">
	<form:form commandName="authorManageVO" name="insertForm" method="post">
	<form:hidden path="upperMenuId"/>
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
							<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="타이틀이미지" title="타이틀이미지" /></td>
							<td class="title">권한추가</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table summary="권한등록" class="write01">
					<caption>권한등록</caption>
					<tr>
						<th class="reqsubject">시스템</th>
						<td class="input">
							<form:select path="sysId" onchange="javascript:fn_syssearch(); return false;" cssClass="validate[required]">
								<form:option value="" label="선택" />
								<form:options items="${sysId}" />
							</form:select>
						</td>
					</tr>
					<tr>
						<th class="subject">권한</th>
						<td class="input">
							<form:select path="upperAuthorId" onchange="javascript:fn_reset(); return false;">
								<form:option value="" label="- 권한 선택 안함 -" />
								<form:options items="${upperAuthorId}" />
							</form:select>
						</td>
					</tr>
					<tr>
						<th class="reqsubject">권한명</th>
						<td>
							<table class="inside02">
							<tr>
								<td class="input"><input type="text" id="authorNm" name="authorNm" size="100" class="wi200 validate[required,length[0,100]] text-input" maxlength="100" readonly="readonly" /></td>
								<td class="img"><img src="/images/button/0408.png" alt="중복확인" title="중복확인" onclick="fn_check();" style="cursor: pointer;"></td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<th class="subject">권한 설명</th>
						<td class="input"><form:textarea path="authorDc" rows="5"
							cols="65" cssClass="validate[optional,length[0,255]]" /></td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table class="rbuttonarea">
					<tr>
						<td><a onclick="javascript:fn_insert()" style="cursor: pointer;"><img src="/images/button/0210.png" alt="<spring:message code='button.create'/>" title="<spring:message code='button.create'/>" /></a></td>
						<td class="end"><a onclick="history.back(-1);" style="cursor: pointer;"><img src="/images/button/0203.png" alt="<spring:message code='button.reset'/>" title="<spring:message code='button.reset'/>"/></a></td>
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
	</form:form>
</div>
<!-- container end -->
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>