<%-- 
/** 
 * 설명   : 사용자 상세내용을 수정하는 화면
 * 파일명 : userUpdate.jsp
 * @author 범정부통계포털 이관형 
 * @since 2011.06.20
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.06.20     이관형           최초 생성 
 * </pre> 
 */
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp"%>

<%-- Header Start ==========================================================--%>
<jsp:include page="/gps/cmm/admHeader.do"></jsp:include>
<%-- Header End ==========================================================--%>

<%-- javascript start ==============================================--%>
<script type="text/javaScript" language="javascript">
	JQ_setValidation('userManageVO');
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
		
		JQ_setValueObj('selectedAuthorId', $("#selectedAuthorId").val());
		JQ_setValueObj('authorId', $("#authorId").val());
		JQ_setValueObj('userAuthorId', $("#userAuthorId").val());



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


		
		JQ_request("userManageVO", "<c:url value='/gps/adm/user/updateUser.do'/>");
	}
}

function fn_modifyPw(userId){
	gfn_postPopupWin("userManageVO", "/gps/adm/user/chgPwPopup.do", "비밀번호변경", 180, 500, "yes", 1);
}

function fn_searchZip(zipSe){
	gfn_postPopupWin("userManageVO", "/gps/adm/user/ZipSearchPopup.do?zipSe="+zipSe, "우편번호찾기", 180, 500, "yes", 1);
}

function fn_userAbsnce_layer(){

	var userAbsnceFlg = $("#approvalAt option:selected").val();
	if (userAbsnceFlg == 'N') {
		document.getElementById('userAbsceLayer').style.display = "block";
		document.getElementById('userAuthorLayer').style.display = "none";
	} else {
		document.getElementById('userAbsceLayer').style.display = "none";
		document.getElementById('userAuthorLayer').style.display = "block";
	}

}

function fn_userAbsnce(){
    window.open("/gps/adm/user/userAbsncePopup.do?userId="+ $("#userId").val() +  "&orgId=" + $("#orgId").val() + "&approvalAt=" + $("#approvalAt option:selected").val(), "test", "toolbar=no,derectories=no,status=no,menubar=no,width="+800+",height="+500+",resizable=no");
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
function fn_syssearch() {

	if( document.getElementById("sysId").selectedIndex == 0){
		 $("#authorId").html("");
		return;
	}
	
	var sysId = $("#sysId option:selected").val();

//  아래 예제에서는 searchVO를 이용하지는 않았지만 	
//  searchVO 폼데이타전송을 하면서 ajax함수를 이용한 방법입니다.
//  jquery.form.js에 ajaxsubmit()함수를 호출합니다.
	var options = {         success     :fn_author_success,
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
function fn_author_success(response, status) 
{	
	var data = response.result ;
	$("#authorId").html("");
	if( status == 'success' ) {
		JQ_setProcessMsg_delete();
		for(var index=0 ; index < data.length + 1 ; index++) {
				if (0 == index) {
					$("#authorId").get(0).options[index] = new Option("권한 선택 안함", "");
				} else {
					$("#authorId").get(0).options[index] = new Option(data[index-1].authorNm, data[index-1].authorId);
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
		 $("#orgId"+type).html("");
		 $("#orgDeptId").html("");
		 
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

function fn_list(){
	JQ_request("userManageVO", "<c:url value='/gps/adm/user/selectUserList.do'/>", "userManageVO");
}

</script>
<!-- contents_area start -->
<form:form commandName="userAbsnceVO" name="userAbsnceVO" id="userAbsnceVO" method="post">
<form:hidden path="orgId"/>
</form:form>

<form:form commandName="userManageVO" name="userManageVO" id="userManageVO" method="post">
<form:hidden path="approvalAtCond"/>
<form:hidden path="orderByClause"/>	
<form:hidden path="pageUnit"/>
<form:hidden path="searchCondition"/>
<form:hidden path="pageIndex"/>
<form:hidden path="searchKeyword"/>
<form:hidden path="trnsferInfo"/>

<form:hidden path="listType"/>
<form:hidden path="selectedAuthorId"/>
<form:hidden path="userAuthorId"/>
<c:if test="${userManageVO.authorId eq '10000'}">
<form:hidden path="authorId"/>
</c:if>
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
								<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="사용자 수정" title="사용자 수정"/></td>
								<td class="title">사용자 수정</td>
							</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table summary="사용자관리" class="write01">
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
											<a onclick="fn_modifyPw('<c:out value="${userManageVO.userId}" />');" style="cursor: hand"><img src="/images/button/0623.png"
														alt="비밀번호변경" title="비밀번호변경"></a></td>
										</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="reqsubject">이름</th>
									<td class="input">
									<form:input path="nm" id="nm" title="이름" cssClass="validate[required] text-input wi100" />
									</td>
								</tr>
								<tr>
									<th class="reqsubject">EMAIL</th>
									<td>
										<table class="inside02">
										<tr>
											<td class="input">
											<form:input path="email" id="email" cssClass="validate[required] text-input wi100" title="EMAIL" />
											</td>
											<td class="input">
											@<form:input path="emailCoNm" id="emailCoNm" cssClass="wi100 validate[required] text-input" title="이메일" />
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
													<img src="/images/button/0601.png"  title="우편번호찾기" id="btnSearchZip" onclick="fn_searchZip(1);">
													</td>
												</tr>
												</table>
											</td>
										</tr>
										<tr>
											<th class="subject">주소</th>
											<td class="input">
											<form:input path="addrCn" id="addrCn" title="주소" cssClass="wi300"/>
											</td>
										</tr>
										<tr>
											<th class="bottom_noline">상세주소</th>
											<td class="input_bottom_noline">
											<form:input path="addrDtlCn" id="addrDtlCn" title="상세주소" cssClass="wi300"/>
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
											<td class="input"><form:input path="deptNm" id="deptNm" title="국명" cssClass="wi300"/></td>
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
											<td class="input"><form:input path="deptNm" id="deptNm" title="국명" cssClass="wi300"/></td>
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
								<c:if test="${userManageVO.authorId ne '10000'}">
									<tr>
										<th class="subject">시스템</th>
										<td class="option">
										   <form:select path="sysId" onchange="javascript:fn_syssearch(); return false;">
									                      <form:option value="" label="선택"/>
									                      <form:options items="${sysId}" />
									                    </form:select>
										</td>
									</tr>
								</c:if>
								<c:if test="${userManageVO.authorId ne '10000'}">
									<tr>
										<th class="subject">권한</th>
										<td class="option">
											<form:select path="authorId">
												<form:option value="" label="시스템을 선택해주세요."/>
												<form:options items="${authorId}" />
											 </form:select>
										</td>
									</tr>
								</c:if>
								<tr>
									<th class="subject">사용자상태</th>
									<td class="input"><form:select path="approvalAt" onchange="javaScript:fn_userAbsnce_layer()">
										<form:options items="${appList}" />
									</form:select>
									</td>
								</tr>
								<tr id="userAbsceLayer" style="display:none;">
									<th class="subject">사용자 연계</th>
									<td class="input"><a onclick="javaScript:fn_userAbsnce()">권한 인계</a></td>
								</tr>
								<tr id="userAuthorLayer" style="display:none;">
									<th class="subject">권한 회수</th>
									<td class="input"><a onclick="javaScript:fn_userAbsnce()">권한 회수</a></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table class="rbuttonarea">
							<tr>
								<c:if test="${userManageVO.trnsferInfo ne 'U'}">
									<td><a onclick="fn_modify();" style="cursor: hand"><img src="/images/button/0210.png" alt="<spring:message code="button.save" />" title="<spring:message code="button.save" />"></a></td>
								</c:if>
								<td class="end"><a onclick="fn_list();" style="cursor: hand"><img src="/images/button/0203.png" alt="<spring:message code="button.reset" />" title="<spring:message code="button.list" />"></a></td>
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
</form:form>
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>