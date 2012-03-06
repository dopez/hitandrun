<%--
********************************************************************************
* @source      : header.jsp
* @description : 헤더  JSP - 세션/사용자/권한 처리
*                   
********************************************************************************
* DATE         AUTHOR    DESCRIPTION
*===============================================================================
* 2011-08-01   이진우           최초작성
********************************************************************************
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%-- 공통 taglib 선언 start	=================================================--%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<%-- 공통 taglib 선언 end	=====================================================--%>

<%-- header start	==========================================================--%>
<jsp:include page="/gps/cmm/left.do" flush="false"/> 
<%-- header end	==============================================================--%>
<%-- 선행 로직 start	======================================================--%>

<%-- 선행 로직 end	==========================================================--%>
<script language='javascript' src='/gpkisecureweb/var.js'></script>
<script language='javascript' src='/gpkisecureweb/GPKIFunc.js'></script>
<script language='javascript' src='/gpkisecureweb/object.js'></script>
<%-- page javascript start	==================================================--%>
<script type="text/javaScript" language="javascript">
	JQ_setValidation('userRegisterVO');
	JQ_onload();
<%-- 
/************************************************************************ 
함수명 : fncPageOnload                                   
설   명 : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
인   자 : 없음        
사용법 : fncPageOnload()              
작성일 : 2011.06.20  
작성자 : 범정부통계포털 이관형  

      date        author   note  
 ----------   -------     ------------------- 
                              
************************************************************************/ 
--%>

var type;

function fncPageOnload()
{
	//frameset controller start
	//frameset controller end
	var val=$('[name=orgChooseAt]:checked').val();
	if(val==undefined || val==''){
		$('[name=orgChooseAt]').val(['C']);
	}else{
		$('[name=orgChooseAt]').val([val]);
	}
	orgLayer();
	topStatMenuImage(0);
	subTopStatMenuImage(0);
	leftStatMenuImage(0);
	
	<c:if test="${!empty message}">
	alert('<c:out value = "${message}"/>');
	</c:if>

}

<%-- 
/************************************************************************ 
함수명 : fn_modify                               
설   명 : 사용자 항목 수정
		공통메시지를 띄워 저장여부 확인한 뒤 사용자가 확인을 하면 DB로 내용을 보내서 Update 시킴
인   자 : 없음(form 내용 modelAttribute 자동세팅)
사용법 : fn_modify()
작성일 : 2011.06.20
작성자 : 범정부통계포털 이관형       

      date        author   note  
 ----------   -------     ------------------- 
                                 
************************************************************************/
--%> 
function fn_modify(){
	if(confirm("<spring:message code='common.save.msg' />")){
		
		
		if(type=='C'){
			$("#orgWriteUseLayer").remove();
			$("#orgMultiLayer").remove();
		}else if(type=='W'){
			$("#orgChooseLayer").remove();
			$("#orgMultiLayer").remove();
		}else{
			$("#orgChooseLayer").remove();
			$("#orgWriteUseLayer").remove();
		}
		
	   	JQ_request("userRegisterVO", "<c:url value='/gps/user/updateUser.do'/>");
	}
}

function fn_modifyPw(userId){
	gfn_postPopupWin("userRegisterVO", "/gps/login/passwordReissue.do", "비밀번호 찾기", 400, 400, "yes", "no");
}

function fn_searchZip(zipSe){
	gfn_postPopupWin("userRegisterVO", "/gps/user/ZipSearchPopup.do?zipSe="+zipSe, "우편번호찾기", 500, 500, "yes", "no");
}


<%-- 
/************************************************************************ 
fnc name : fn_codesearch                                   
outline :  동적으로 콤보박스의 내용을 변경해주는 함수 
parameter : 없음
directions : fn_codesearch()              
since : 2011-08-18   
author : 범정부통계포털 이관형       

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>

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




//기관정보 입력 방법
function orgLayer(){
	var val=$('[name=orgChooseAt]:checked').val();
	if(val=='C'){
		type=val;
		$("#orgChooseLayer").show();
		$("#orgWriteUseLayer").hide();
		$("#orgMultiLayer").hide();
	}else if(val=='W'){
		type=val;
		$("#orgChooseLayer").hide();
		$("#orgWriteUseLayer").show();
		$("#orgMultiLayer").hide();
	}else{
		type=val;
		$("#orgChooseLayer").hide();
		$("#orgWriteUseLayer").hide();
		$("#orgMultiLayer").show();
	}
}



<%-- 
/************************************************************************ 
fnc name : fn_codesearch                                   
outline :  동적으로 콤보박스의 내용을 변경해주는 함수 
parameter : 없음
directions : fn_codesearch()              
since : 2011-08-18   
author : 범정부통계포털 이관형       

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_orgSearch() {

	if( document.getElementById("orgKd"+type).selectedIndex == 0){
		 $("#ordId"+type).html("");
		return;
	}
	
	var orgKd = $("#orgKd"+type+" option:selected").val();

	var options = {         success     :fn_org_success,
			                 error       :fn_error,
			                 url         :'/gps/user/orgComboList.do?'+'selectOrgKd='+orgKd,
			                 contentType :'application/x-www-form-urlencoded; charset=UTF-8',
			                 type        :'post',
			                 dataType    :'json'
			               };

	JQ_setProcessMsg();
	JQ_requestAjax('userRegisterVO', options);    		 
	
}


function fn_deptSearch() {

	if( document.getElementById("orgId"+type).selectedIndex == 0){
		 $("#orgDeptId").html("");
		return;
	}
	
	var orgId = $("#orgId"+type+" option:selected").val();

	var options = {         success     :fn_dept_success,
			                 error       :fn_error,
			                 url         :'/gps/user/deptComboList.do?'+'selectOrgId='+orgId,
			                 contentType :'application/x-www-form-urlencoded; charset=UTF-8',
			                 type        :'post',
			                 dataType    :'json'
			               };

	JQ_setProcessMsg();
	JQ_requestAjax('userRegisterVO', options);    		 
	
}


function fn_org_success(response, status) 
{	
	
	var data = response.result ;
	$("#orgId"+type).html("");
	if( status == 'success' ) {
		JQ_setProcessMsg_delete();
		for(var index=0 ; index < data.length + 1 ; index++) {
				if (0 == index) {
					$("#orgId"+type).get(0).options[index] = new Option("선택하세요", "");
				} else {
					$("#orgId"+type).get(0).options[index] = new Option(data[index-1].orgNm, data[index-1].orgId);
				}
		}
	}
}


function fn_dept_success(response, status) 
{	
	var data = response.result ;
	$("#orgDeptId").html("");
	if( status == 'success' ) {
		JQ_setProcessMsg_delete();
		for(var index=0 ; index < data.length + 1 ; index++) {
				if (0 == index) {
					$("#orgDeptId").get(0).options[index] = new Option("선택하세요", "");
				} else {
					$("#orgDeptId").get(0).options[index] = new Option(data[index-1].orgNm, data[index-1].orgId);
				}
		}
	}
}



function fn_chgEmail(){
	var val=$("#emailCo").val();
	$("#emailCoNm").val(val);
}


<%-- 
/************************************************************************ 
fnc name : fn_gpkiRegist                     
outline : 인증서등록
parameter : 없음        
directions : fn_gpkiRegist              
since : 2011-08-01   
author : 통계포털  황기연       

    date      author             note  
 ----------   -------     ------------------- 
 2011.10.04    황기연                       최초 생성                 
************************************************************************/ 
--%>
function fn_updateGpki(){
	Login(popForm);
}



</script>
<!-- contents_area start -->
<div class="contents_area">


<form name="popForm" method="post" action="/gps/user/updateGpki.do">
<input type="hidden" name="challenge" value="<c:out value="${challenge}" escapeXml="true"/>"/>
<input type="hidden" name="returnUrl" value="<c:out value="${userRegisterVO.returnUrl}" escapeXml="true"/>"/>
<input type="hidden" name="menuId" value="<c:out value="${userRegisterVO.menuId}" escapeXml="true"/>"/>
<input type="hidden" name="leftMenuId" value="<c:out value="${userRegisterVO.leftMenuId}" escapeXml="true"/>"/>
<input type="hidden" name="bbsId" value="<c:out value="${userRegisterVO.bbsId}" escapeXml="true"/>"/>
<input type="hidden" name="bbsSn" value="<c:out value="${userRegisterVO.bbsSn}" escapeXml="true"/>"/>
</form>
<form:form commandName="userRegisterVO" name="userRegisterVO" id="userRegisterVO" method="post">
<form:hidden path="selectedAuthorId"/>
<form:hidden path="userAuthorId"/>
<form:hidden path="menuId"/>
<form:hidden path="leftMenuId"/>

<table summary="사용자정보수정" class="write01">
<caption>사용자정보수정</caption>
<tr>
	<th class="reqsubject">아이디</th>
	<td>
		<table class="inside02">
		<tr>
			<td class="input">
			<form:input path="userId" id="userId" cssClass="wi100" title="사용자ID" readonly="true"/>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr>
	<th class="reqsubject">비밀번호 변경</th>
	<td>
		<table class="inside02">
		<tr>
			<td class="input">
			<a onclick="fn_modifyPw('<c:out value="${userRegisterVO.userId}" />');" style="cursor: hand"><img src="/images/button/0623.png"
						alt="비밀번호변경" title="비밀번호변경"></a></td>
		</tr>
		</table>
	</td>
</tr>
<tr>
	<th class="reqsubject">이름</th>
	<td class="input">
	<form:input path="nm" id="nm" title="이름" cssClass="validate[required,custom[onlyKorEng],length[0,20]] text-input wi100" />
	</td>
</tr>
<tr>
	<th class="reqsubject">EMAIL</th>
	<td>
		<table class="inside02">
		<tr>
			<td class="input">
			<form:input path="email" id="email" cssClass="validate[required,length[0,20]] text-input wi100" title="EMAIL" />
			</td>
			<td class="input">
			@<form:input path="emailCoNm" id="emailCoNm" cssClass="wi100 validate[required,length[0,20]] text-input" title="이메일" />
			</td>
			<td class="input">
				<form:select path="emailCo" onchange="fn_chgEmail();" >
					<form:option value="" label="직접입력"/>
					<form:options items="${emailMap}"/>
				</form:select>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr>
	<th class="subject">전화번호</th>
	<td>
		<table class="inside02">
		<tr>
			<td class="input">
			<form:input path="phonCn" id="phonCn" title="전화번호" cssClass="validate[optional, custom[telephone], length[1,13]] text-input wi100"/>
			</td>
			<td></td>
		</tr>
		</table>
	</td>
</tr>
<tr>
	<th class="subject">휴대전화</th>
	<td class="input">
	<form:input path="moblphonCn" id="moblphonCn" title="휴대전화" cssClass="validate[optional, custom[telephone],length[1,13]]] text-input wi100"/>
	</td>
</tr>
<tr>
	<th class="subject">주소</th>
	<td>
		<table class="inside01">
		<tr>
			<th class="subject">우편번호</th>
			<td>
				<table class="inner01">
				<tr>
					<td class="input_bottom_noline">
					<form:input path="zip" id="zip" title="우편번호" cssClass="wi100"/>
					</td>
					<td class="input_bottom_noline">
					<img src="/images/button/0601.png" alt="우편번호찾기" title="우편번호찾기" id="btnSearchZip" onclick="fn_searchZip(1);">
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<th class="subject">주소</th>
			<td class="input">
			<form:input path="addrCn" id="addrCn" title="주소" cssClass="validate[optional, length[0,50]] text-input wi300"/>
			</td>
		</tr>
		<tr>
			<th class="bottom_noline">상세주소</th>
			<td class="input_bottom_noline">
			<form:input path="addrDtlCn" id="addrDtlCn" title="상세주소" cssClass="validate[optional, length[0,50]] text-input wi300"/>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr>
	<th class="subject">기관선택방법</th>
	<td>
		<table class="inside02">
		<tr>
			<td class="input"><form:radiobutton path="orgChooseAt" id="orgChooseAtC" value="C" onclick="orgLayer()" onkeypress="orgLayer()" label="선택입력"/></td>
			<td class="input"><form:radiobutton path="orgChooseAt" id="orgChooseAtW" value="W" onclick="orgLayer()" onkeypress="orgLayer()" label="직접입력" /></td>
			<td class="input"><form:radiobutton path="orgChooseAt" id="orgChooseAtM" value="M" onclick="orgLayer()" onkeypress="orgLayer()" label="부서만 직접 입력" /></td>
		</tr>
		</table>
	</td>
</tr>
<tr id="orgChooseLayer">
	<th class="subject">기관선택</th>
	<td>
		<table class="inside01">
		<tr>
			<th class="subject">기관유형</th>
			<td>
				<table class="inner01">
				<tr>
					<td class="input_bottom_noline">
						<form:select path="orgKd" id="orgKdC" onchange="javascript:fn_orgSearch(); return false;">
						<form:option value="- 선택 -"/>
						<form:options items="${orgKdMap}"/>
						</form:select>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<th class="subject">기관명</th>
			<td class="input">
				<form:select path="orgId" id="orgIdC" onchange="javascript:fn_deptSearch(); return false;">
				<form:options items="${orgIdMap}"/>
				</form:select>
			</td>
		</tr>
		<tr>
			<th class="bottom_noline">부서선택</th>
			<td class="input_bottom_noline">
				<form:select path="orgDeptId" id="orgDeptId">
				<form:options items="${deptIdMap}"/>
				</form:select>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr id="orgWriteUseLayer">
	<th class="subject">기관선택</th>
	<td>
		<table class="inside01">
		<tr>
			<th class="subject">기관명</th>
			<td class="input">
			<form:input path="wrcNm" id="wrcNm" title="직장명" cssClass="wi300"/>
			</td>
		</tr>
		<tr>
			<th class="subject">국명</th>
			<td class="input">
			<form:input path="deptNm" id="deptNm" title="국명" cssClass="wi300"/>
			</td>
		</tr>
		<tr>
			<th class="bottom_noline">부서명</th>
			<td class="input_bottom_noline">
			<form:input path="offiNm" id="offiNm" title="부서명" cssClass="wi300"/>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr id="orgMultiLayer">
	<th class="subject">기관선택</th>
	<td>
		<table class="inside01">
		<tr>
			<th class="subject">기관유형</th>
			<td>
				<table class="inner01">
				<tr>
					<td class="input_bottom_noline">
						<form:select path="orgKd" id="orgKdM" onchange="javascript:fn_orgSearch(); return false;">
						<form:option value="- 선택 -"/>
						<form:options items="${orgKdMap}"/>
						</form:select>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<th class="subject">기관명</th>
			<td class="input">
				<form:select path="orgId" id="orgIdM">
				<form:options items="${orgIdMap}"/>
				</form:select>
			</td>
		</tr>
		<tr>
			<th class="subject">국명</th>
			<td class="input">
			<form:input path="deptNm" id="deptNm" title="국명" cssClass="wi300"/>
			</td>
		</tr>
		<tr>
			<th class="bottom_noline">부서명</th>
			<td class="input_bottom_noline">
			<form:input path="offiNm" id="offiNm" title="부서명" cssClass="wi300"/>
			</td>
		</tr>
		</table>
	</td>
</tr>

<tr>
<td colspan="2" align="center">
	<table class="rbuttonarea">
		<tr>
			<c:if test="${gpkiRegist}">
			<td><a onclick="fn_updateGpki();" style="cursor:hand"><img src="/images/button/0533.png" alt="<spring:message code="gps.button.gpkiRegist" />" title="<spring:message code="gps.button.gpkiRegist" />"></a></td>
			</c:if>
			<td><a onclick="fn_modify();" style="cursor:hand"><img src="/images/button/0210.png" alt="<spring:message code="button.save" />" title="<spring:message code="button.save" />"></a></td>
			<td class="end"><a href="/" style="cursor:hand"><img src="/images/button/0203.png" alt="<spring:message code="button.list" />" title="<spring:message code="button.list" />"></a></td>
		</tr>
	</table>
</td>
</tr>
</table>
</form:form>
</div>
<jsp:include page="/gps/cmm/footer.do" flush="false"/>