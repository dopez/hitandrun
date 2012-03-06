<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<jsp:include page="/gps/cmm/admHeader.do" flush="false"></jsp:include>
<%-- 
/** 
 * outline   : 게시판기본정보등록화면
 * filename : registerBbsInfoPopup.jsp
 * @author 통계포탈 황기연 
 * @since 2011.06.28
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == 개정이력(Modification Information) == 
 *   
 *   date        author     note 
 * ----------    -------    --------------------------- 
 * 2011.06.28     황기연           최초 생성 
 * </pre> 
 */
--%>
<script type="text/javascript">
JQ_setValidation('bbsInfoVO');
JQ_onload();

<%-- 
/************************************************************************ 
fnc name : fncPageOnload                                   
outline : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
parameter : 없음        
directions : fncPageOnload()              
since : 2011-07-05   
author : 통계포털  황기연       

    date      author             note  
 ----------   -------     ------------------- 
 2011.06.10    황기연                                          
************************************************************************/ 
--%>
function fncPageOnload(){
	//메시징처리
	<c:if test="${!empty message}">
	alert('<c:out value = "${message}"/>');
	opener.parent.rightBody.location.href="<c:url value='/gps/adm/bbs/selectBbsInfoList.do'><c:param name='dbTname' value='${bbsInfoVO.dbTname}'/></c:url>";
	self.close();
	</c:if>
	
	$('#mngr').hide();
}

<%-- 
/************************************************************************ 
fnc name : fn_insert                                 
outline : 게시판정보입력처리요청함수      
parameter : 없음        
directions : fn_insert()              
since : 2011-07-05    
author : 통계포털  황기연       

    date      author             note  
 ----------   -------     ------------------- 
 2011.06.10    황기연                                          
************************************************************************/ 
--%>
function fn_insert(){
	JQ_setProcessMsg();
	JQ_request("bbsInfoVO", "<c:url value='/gps/adm/bbs/insertBbsInfo.do'/>");
}

<%-- 
/************************************************************************ 
fnc name : fn_close                           
outline : 팝업창닫힘
parameter : 없음        
directions : fn_close()              
since : 2011-07-05    
author : 통계포털  황기연       

    date      author             note  
 ----------   -------     ------------------- 
 2011.07.05    황기연                                          
************************************************************************/ 
--%>
function fn_close(){
	window.close();
}

<%-- 
/************************************************************************ 
fnc name : 게시판명 중복검사             
outline :  게시판명 중복검사
parameter : 없음
directions : fn_mngrSearch()              
since : 2011-05-10   
author : 통계포털 황기연       

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_bbsNameDuplicateAt() {
	var ptn = /[^A-z|0-9|가-힣ㄱ-ㅎ]/g;
	if($('#bbsNm').val().match(ptn)){
		$('#bbsNm').val("");
		$('#bbsNm').focus();
		$('#duplicateMessage').html("공백이나 특수문자는 입력이 불가능 합니다.");
	}else{
		var options = { 
								 success     :fn_bbsNmSuccess,
					             error       :fn_error,
				                 url         :'/gps/adm/bbs/bbsNameDuplicateAt.do',			                 
				                 contentType :'application/x-www-form-urlencoded; charset=UTF-8',
				                 type        :'post',
				                 dataType    :'json'
				               };   
	     JQ_requestAjax('bbsInfoVO',options);
	}  
}

<%-- 
/************************************************************************ 
fnc name : fn_mngrSearch                               
outline :  관리자검색
parameter : 없음
directions : fn_mngrSearch()              
since : 2011-05-10   
author : 통계포털 황기연       

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_mngrSearch() {
	var ptn = /[^A-z|0-9|가-힣ㄱ-ㅎ]/g;
	if($('#bbsMngrNm').val().match(ptn)){
		$('#bbsMngrNm').val("");
		$('#bbsMngrNm').focus();
		$('#mngr').show();
		$('#mngr').html("<li>공백이나 특수문자는 입력이 불가능 합니다.</li>");
	}else{
		var nm = $('#nm').val($('#bbsMngrNm').val());
		var options = { 
								 success     :fn_bbsMngrSuccess,
					             error       :fn_error,
				                 url         :'/gps/adm/bbs/selectMngrList.do',			                 
				                 contentType :'application/x-www-form-urlencoded; charset=UTF-8',
				                 type        :'post',
				                 dataType    :'json'
				               };   
	     JQ_requestAjax('userManageVO',options);
	}
}

<%-- 
/************************************************************************ 
fnc name : fn_bbsNmSuccess                                   
outline :  게시판명 검색
parameter : response json객체 
directions : fn_bbsNmSuccess()              
since : 2011-05-10   
author : 통계포털 황기연

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_bbsNmSuccess(response) 
{	
	var message = response.message;
	var duplicateAt = response.duplicateAt;
	
	if(duplicateAt){
		$('#bbsNm').val("");
		$('#bbsNm').focus();
	}
	$('#duplicateMessage').html(message);
}

<%-- 
/************************************************************************ 
fnc name : fn_bbsMngrSuccess                                   
outline :  관리자목록호출 성공
parameter : response json객체 
directions : fn_bbsMngrSuccess()              
since : 2011-05-10   
author : 통계포털 황기연

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_bbsMngrSuccess(response) 
{	
	var data = response.mngrList;
	$('#bbsMngrNm').val("");
	$('#bbsMngrNm').focus();
	var htmlList="";
	$('#mngr').show();
	if(data.length > 0){
		htmlList += "<li><font color='red'>- 관리자 목록 선택 -</font></li>";
		for(var i=0;i<data.length;i++){
			htmlList += "<li style=\"cursor:hand;\" onclick=\"fn_mngrValueSet('"+data[i].nm+"','"+data[i].userId+"','"+data[i].email+"');\">"+data[i].nm+" [ "+data[i].userId+" ]</li>";
		}
	}else{
		htmlList +="<li>검색결과가없습니다.</li>";
	}
	$('#mngr').html(htmlList);
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

<%-- 
/************************************************************************ 
fnc name : fn_mngrSet                                   
outline :  관리자ID,이름 setting
parameter : val
directions : fn_mngrSet()              
since : 2011-05-10   
author : 통계포털 황기연

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_mngrValueSet(nm,userId,email) {
	$('#bbsMngrNm').val(nm);
	$('#bbsMngrId').val(userId);
	$('#bbsMngrEmail').val(email);
	$('#mngr').html("");
	$('#mngr').hide();
}

</script>

<!-- ui-layout-center start  -->
<div class="ui-layout-center">
	<!-- contents_area start -->
	<div class="contents_area">
		<form:form commandName="userManageVO">
			<form:hidden path="nm"/>
		</form:form>
		<form:form commandName="bbsInfoVO" method="post">
		<form:hidden path="dbTname"/>
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
										<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="<spring:message code="gps.titleImage"/>" title="<spring:message code="gps.titleImage"/>"/></td>
										<td class="title">게시판정보등록</td>
									</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table summary="게시판정보등록" class="write01">
									<caption>게시판정보등록</caption>
										<tr>
											<th class="reqsubject">시스템구분</th>
											<td class="input">
												<form:select path="sysId">
													<c:forEach var="systemList" items="${systemList}" varStatus="status">
													<form:option value="${systemList.sysId}"><c:out value="${systemList.sysNm}"/></form:option>
													</c:forEach>
												</form:select>
											</td>
										</tr>
										<tr>
											<th class="reqsubject">게시판명</th>
											<td class="input">
												<form:input path="bbsNm" maxlength="50" cssClass="validate[required,length[1,50]]" onchange="fn_bbsNameDuplicateAt();"/>
												<div id="duplicateMessage"></div>
											</td>
										</tr>
										<tr>
											<th class="reqsubject">게시판관리자</th>
											<td class="input">
												<form:input path="bbsMngrNm" cssClass="validate[required,length[1,20]]" onchange="fn_mngrSearch();"/>
												<form:hidden path="bbsMngrId"/>
											    <div id="mngr" style="overflow:auto;width:300px;height:50px;">
												</div>
											</td>
										</tr>
										<tr>
											<th class="reqsubject">관리자비밀번호</th>
											<td class="input"><form:password path="bbsMngrPassword" cssClass="validate[required,length[1,50]]"/></td>
										</tr>
										<tr>
											<th class="subject">게시판관리자이메일</th>
											<td class="input"><form:input path="bbsMngrEmail" cssClass="validate[optional,length[1,100],custom[email]]"/></td>
										</tr>
										<tr>
											<th class="subject">설명</th>
											<td class="input"><form:textarea path="bbsDc" cssClass="wi200 ht40 validate[optional,length[1,1000]]"/></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table class="rbuttonarea">
										<tr>
											<td><img src="/images/button/0202.png" alt="<spring:message code='button.create'/>" title="<spring:message code='button.create'/>" style="cursor:hand;" onclick="fn_insert();return false;" onkeydown="this.onclick();return false;"/></td>
											<td class="end"><img src="/images/button/0205.png" alt="<spring:message code='button.close'/>" title="<spring:message code='button.close'/>" style="cursor:hand;" onclick="window.close();"/></td>
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
</div>
<!-- ui-layout-center end  -->
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>