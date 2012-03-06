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

<%-- page javascript start	==================================================--%>
<script type="text/javascript">
var isIdChk = false;
JQ_setValidation('userRegisterVO');
JQ_onload();
<!--
<%-- 
/************************************************************************ 
함수명 : fncPageOnload                                   
설   명 : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
인   자 : 없음        
사용법 : fncPageOnload()              
작성일 : 2011.08.01  
작성자 : 범정부통계포털 이진우  

   date        author      note  
 ----------   -------     ------------------- 
 2011.08.01	     이진우               최초생성             
************************************************************************/ 
--%>
var type;
function fncPageOnload(){
	<c:if test="${!empty message}">
		alert('<c:out value = "${message}"/>');
	</c:if>
	$('[name=orgChooseAt]').val(['C']);
	orgLayer();
	topStatMenuImage(0);
	subTopStatMenuImage(0);
	leftStatMenuImage(0);
}
<%-- 
/************************************************************************ 
함수명 : fn_insertUser                               
설   명 : 사용자 항목 수정
		공통메시지를 띄워 저장여부 확인한 뒤 사용자가 확인을 하면 DB로 내용을 보내서 Update 시킴
인   자 : 없음(form 내용 modelAttribute 자동세팅)
사용법 : fn_insertUser()
작성일 : 2011.08.01
작성자 : 범정부통계포털 이진우       

   date        author      note  
 ----------   -------     ------------------- 
 2011.08.01	     이진우               최초생성                    
************************************************************************/
--%> 
function fn_insertUser(){
	if(isIdChk){
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
			
		JQ_request("userRegisterVO", "<c:url value='/gps/user/insertUser.do'/>");
	}else{
		alert('아이디 중복확인이 되지 않았습니다.');
		return false;
	}
}

<%-- 
/************************************************************************ 
fnc name : fnIdCheck                                   
outline :  아이디중복체크
parameter : 없음
directions : fnIdCheck()              
since : 2011-11-25   
author : 범정부통계포털 황기연       

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fnIdCheck(){
	var ptn = /[^A-z|0-9|_]/g;
	if($('#userId').val() == ''){
		alert('아이디를 입력하세요');
		$('#userId').focus();
	}else{
		if($('#userId').val().match(ptn)){
			alert("공백이나 '_' 이외의 특수문자는 입력이 불가능 합니다.");
			$('#userId').focus();
			$('#userId').val('');
		}else{
			var options = {
									 success     :fn_idSearch_success,
						             error       :fn_error,
					                 url         :'/gps/user/checkUserId.do',			                 
					                 contentType :'application/x-www-form-urlencoded; charset=UTF-8',
					                 type        :'post',
					                 dataType    :'json'
					               };   
		     JQ_requestAjax('userRegisterVO',options);
		}
	}
}

<%-- 
/************************************************************************ 
fnc name : fn_idSearch_success                                   
outline :  아이디중복체크 결과
parameter : 없음
directions : fn_idSearch_success()              
since : 2011-11-25   
author : 범정부통계포털 황기연       

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_idSearch_success(response, status){	
	var data = response.result ;
	if( status == 'success' ){
		if(!data){
			alert('사용 가능한 아이디 입니다.');
			isIdChk = true;
		}else{
			alert('사용 불가능한 아이디 입니다.');
			$("#userId").focus();
			$("#userId").val('');
		}
	}
}

function fn_searchZip(zipSe){
	popup("/gps/user/ZipSearchPopup.do?zipSe="+zipSe, "우편번호찾기", 500, 450, "yes", "no", 0);
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

	var options = {success:fn_org_success,
			       error:fn_error,
			       url:'/gps/user/orgComboList.do?'+'selectOrgKd='+orgKd,
			       contentType:'application/x-www-form-urlencoded; charset=UTF-8',
			       type:'post',
			       dataType:'json'
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

	var options = {success:fn_dept_success,
			       error:fn_error,
			       url:'/gps/user/deptComboList.do?'+'selectOrgId='+orgId,
			       contentType:'application/x-www-form-urlencoded; charset=UTF-8',
			       type:'post',
			       dataType:'json'
			       };

	JQ_setProcessMsg();
	JQ_requestAjax('userRegisterVO', options);    		 
	
}

function fn_org_success(response, status){	
	
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

function fn_dept_success(response, status){	
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

function fn_error( error ) {
	if( error.statusText == "error" ) {
	   alert( "<spring:message code="fail.common.msg" />" );
	}
}

function fn_chgEmail(){
	var val=$("#emailCo").val();
	$("#emailCoNm").val(val);
}

function fn_idCheck(){
	isIdChk = false;
}
-->
</script>
<noscript>현재 브라우저에서 자바스크립트 처리를 하지 못합니다.</noscript>
<%-- page javascript end	==================================================--%>
<!-- subpagebody_contents start -->
<div id="subpagebody_contents">
	<!-- contents start -->
	<div class="agreementprocess">
		<div class="agreementflow">
			<ul>
				<li><img src="/images/gps/contents/member/member_im_01_off.gif" alt="약관동의" title="약관동의"></li>
				<li><img src="/images/gps/contents/member/member_im_02_off.gif" alt="본인확인" title="본인확인"></li>
				<li><img src="/images/gps/contents/member/member_im_03_on.gif" alt="회원정보입력" title="회원정보입력"></li>
				<li><img src="/images/gps/contents/member/member_im_04_off.gif" alt="회원가입완료" title="회원가입완료"></li>
			</ul>
		</div>
	</div>
	<!-- content start -->
	<div class="registuserForm">
		<form:form commandName="userRegisterVO" method="post">
		<form:hidden path="menuId"/>
		<form:hidden path="leftMenuId"/>
		<form:hidden path="ipin"/>
		<form:hidden path="gpin"/>
		<form:hidden path="gpki"/>
		<table summary="사용자등록" class="default">
		<caption>사용자등록</caption>
		<tr>
			<td>
				<table summary="사용자등록" class="write01">
				<caption>사용자등록</caption>
				<tr>
					<th class="reqsubject">아이디</th>
					<td>
						<table class="inside02">
						<tr>
							<td class="input"><form:input path="userId" cssClass="validate[required,custom[LowerEngNumber],length[5,12]] wi100" title="사용자ID" onchange="fn_idCheck();"/></td>
							<td class="btn"><a href="#LINK" onclick="fnIdCheck();return false;" onkeypress="this.onclick();return false;" class="course"><img src="/images/button/0701.png" alt="아이디중복확인" title="아이디중복확인" id="btnCheckUser" ></a></td>
							<td class="textcation">5~12자의 <font class="fb f_red">영문 소문자, 숫자와 특수기호( _ )</font>만 사용 가능.</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<th class="reqsubject">비밀번호</th>
					<td>
						<table class="inside02">
						<tr>
							<td class="input"><form:password path="password" id="password" title="비밀번호" cssClass="validate[required,length[9,12]] text-input wi150"/></td>
							<td class="textcation">9~12자의 <font class="fb f_red">영문 소문자, 숫자와 특수기호</font> 사용 가능.</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<th class="reqsubject">비밀번호 확인</th>
					<td>
						<table class="inside02">
						<tr>
							<td class="input"><input type="password" id="password2" title="비밀번호 확인" class="validate[required,confirm[password],length[9,12]] text-input wi150"/></td>
							<td class="textcation">비밀번호를 한번 더 입력하세요</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<th class="reqsubject">이름</th>
					<td class="input"><form:input path="nm" id="nm" title="이름" cssClass="validate[required] text-input wi100" readonly="true"/></td>
				</tr>
				<tr>
					<th class="reqsubject">EMAIL</th>
					<td>
						<table class="inside02">
						<tr>
							<td class="input"><form:input path="email" id="email" cssClass="validate[required] text-input wi100" title="EMAIL" /></td>
							<td class="text">@</td>
							<td class="input"><form:input path="emailCoNm" id="emailCoNm" cssClass="wi100 validate[required] text-input" title="이메일" /></td>
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
					<td class="input"><form:input path="phonCn" id="phonCn" title="전화번호" cssClass="validate[optional, custom[telephone], length[1,13]] text-input wi100"/></td>
				</tr>
				<tr>
					<th class="subject">휴대전화</th>
					<td class="input"><form:input path="moblphonCn" id="moblphonCn" title="휴대전화" cssClass="validate[optional, custom[telephone],length[1,13]]] text-input wi100"/></td>
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
									<td class="input_bottom_noline"><form:input path="zip" id="zip" title="우편번호" cssClass="wi100"/></td>
									<td class="input_bottom_noline"><img src="/images/button/0601.png" alt="우편번호찾기" title="우편번호찾기" id="btnSearchZip" onclick="fn_searchZip(1);" class="course"></td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th class="subject">주소</th>
							<td class="input"><form:input path="addrCn" id="addrCn" title="주소" cssClass="wi300"/></td>
						</tr>
						<tr>
							<th class="bottom_noline">상세주소</th>
							<td class="input_bottom_noline"><form:input path="addrDtlCn" id="addrDtlCn" title="상세주소" cssClass="wi300"/></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<th class="subject">기관선택방법</th>
					<td>
						<table class="inside02">
						<tr>
							<td class="input"><form:radiobutton path="orgChooseAt" id="orgChooseAtC" value="C" onclick="orgLayer()" onkeypress="orgLayer()"/></td>
							<td class="text">선택입력</td>
							<td class="input"><form:radiobutton path="orgChooseAt" id="orgChooseAtW" value="W" onclick="orgLayer()" onkeypress="orgLayer()"/></td>
							<td class="text">직접입력</td>
							<td class="input"><form:radiobutton path="orgChooseAt" id="orgChooseAtM" value="M" onclick="orgLayer()" onkeypress="orgLayer()" /></td>
							<td class="text">부서만 직접 입력</td>
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
							<td class="input"><form:select path="orgId" id="orgIdC" onchange="javascript:fn_deptSearch(); return false;"></form:select></td>
						</tr>
						<tr>
							<th class="bottom_noline">부서선택</th>
							<td class="input_bottom_noline"><form:select path="orgDeptId" id="orgDeptId"></form:select></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr id="orgWriteUseLayer">
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
							<td class="input"><form:input path="wrcNm" id="wrcNm" title="직장명" cssClass="wi300"/></td>
						</tr>
						<tr>
							<th class="subject">국명</th>
							<td class="input"><form:input path="deptNm" id="deptNm" title="부서명" cssClass="wi300"/></td>
						</tr>
						<tr>
							<th class="bottom_noline">부서명</th>
							<td class="input_bottom_noline"><form:input path="offiNm" id="offiNm" title="국명" cssClass="wi300"/></td>
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
							<td class="input"><form:select path="orgId" id="orgIdM"></form:select></td>
						</tr>
						<tr>
							<th class="subject">국명</th>
							<td class="input"><form:input path="deptNm" id="deptNm" title="부서명" cssClass="wi300"/></td>
						</tr>
						<tr>
							<th class="bottom_noline">부서명</th>
							<td class="input_bottom_noline"><form:input path="offiNm" id="offiNm" title="국명" cssClass="wi300"/></td>
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
					<td><img src="/images/gps/contents/member/bt_member_on.gif" alt="<spring:message code="button.save" />" title="<spring:message code="button.save" />" onclick="fn_insertUser();" class="course"></td>
					<td><a href="/"><img src="/images/gps/contents/member/bt_cancel_off.gif" alt="<spring:message code="button.list" />" title="<spring:message code="button.list" />" class="course"></a></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
		</form:form>
	</div>	
	<!-- contents_area end -->
</div>
<jsp:include page="/gps/cmm/footer.do" flush="false"/>