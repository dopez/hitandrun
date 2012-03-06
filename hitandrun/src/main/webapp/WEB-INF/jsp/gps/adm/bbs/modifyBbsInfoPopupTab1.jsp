<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<jsp:include page="/gps/cmm/admHeader.do" flush="false"></jsp:include>
<%-- 
/** 
 * outline   : 게시판생성정보수정화면 TAB1
 * filename : modifyBbsInfoPopupTab1.jsp
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
	opener.location.href="<c:url value='/gps/adm/bbs/selectBbsInfoList.do'><c:param name='dbTname' value='${bbsInfoVO.dbTname}'/></c:url>";
		<c:if test="${popupCloseAt == 'Y'}">
			window.self.close();
		</c:if>
	</c:if>
	$('#mngr').hide();

}

<%-- 
/************************************************************************ 
fnc name : fn_update                               
outline : 게시판DB수정처리
parameter : 없음        
directions : fn_update()              
since : 2011-07-05    
author : 통계포털  황기연       

    date      author             note  
 ----------   -------     ------------------- 
 2011.06.10    황기연                                          
************************************************************************/ 
--%>
function fn_update(){
	JQ_setProcessMsg();
	JQ_request("bbsInfoVO", "<c:url value='/gps/adm/bbs/updateBbsInfo.do'/>");
}

<%-- 
/************************************************************************ 
fnc name : fn_delete                                 
outline : 게시판DB삭제    
parameter : 없음        
directions : fn_delete()              
since : 2011-07-05    
author : 통계포털  황기연       

    date      author             note  
 ----------   -------     ------------------- 
 2011.06.10    황기연                                          
************************************************************************/ 
--%>
function fn_delete(){
	if (confirm("<spring:message code="gps.bbsInfo.delete.msg" />")){
	JQ_setProcessMsg();
	JQ_request("bbsInfoVO", "<c:url value='/gps/adm/bbs/deleteBbsInfo.do'/>","bbsInfoVO");
	}
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
		$('#bbsNm').val("<c:out value="${bbsInfoVO.bbsNm}"/>");
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
		$('#bbsMngrNm').val("<c:out value="${bbsInfoVO.bbsMngrNm}"/>");
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

<%-- 
/************************************************************************ 
fnc name : fn_modifyBbsInfoTab                                   
outline :  탭이동
parameter : val
directions : fn_modifyBbsInfoTab()              
since : 2011-05-10   
author : 통계포털 황기연

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_modifyBbsInfoPopupTab(tabSe){
	$('#tabSe').val(tabSe);
	JQ_setProcessMsg();
	JQ_request("bbsInfoVO","<c:url value='/gps/adm/bbs/modifyBbsInfoPopupTab.do'/>","bbsInfoVO");
}
</script>
<div class="contents_area">
	<form:form commandName="userManageVO">
		<form:hidden path="nm"/>
	</form:form>
	<form:form commandName="bbsInfoVO" method="post" enctype="multipart/form-data">
	<form:hidden path="dbTname"/>
	<form:hidden path="bbsId"/>
	<form:hidden path="tabSe"/>
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
									<td class="title"><c:out value="${bbsInfoVO.bbsNm}"/> 게시판생성정보관리</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table class="subtitle">
								<tr>
									<td class="icon"><img src="/images/gps/adm/icon/030.gif" alt="<spring:message code="gps.titleImage"/>" title="<spring:message code="gps.titleImage"/>"/></td>
									<td class="title"><c:out value="${bbsInfoVO.bbsNm}"/> 게시판정보</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<div class="contents_tab clfix">
								<ul>
									<li class="on"><p><a>생성정보</a></p></li>
									<li><p><a onclick="fn_modifyBbsInfoPopupTab('2');return false;" onkeydown="this.onclick();return false;">기본정보</a></p></li>
									<li><p><a onclick="fn_modifyBbsInfoPopupTab('3');return false;" onkeydown="this.onclick();return false;">확장기능</a></p></li>
									<li><p><a onclick="fn_modifyBbsInfoPopupTab('4');return false;" onkeydown="this.onclick();return false;">접근권한</a></p></li>
									<li><p><a onclick="fn_modifyBbsInfoPopupTab('5');return false;" onkeydown="this.onclick();return false;">디자인기능</a></p></li>
								</ul>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<table summary="게시판생성정보관리" class="write01">
								<caption>게시판생성정보관리</caption>
									<tr>
										<th class="reqsubject">게시판ID</th>
										<td class="text"><c:out value="${bbsInfoVO.bbsId}"/></td>
									</tr>
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
									<td><img src="/images/button/0209.png" alt="<spring:message code='button.update'/>" title="<spring:message code='button.update'/>" style="cursor:hand;" onclick="fn_update();return false;" onkeydown="this.onclick();return false;"/></td>
									<td><img src="/images/button/0204.png" alt="<spring:message code='button.delete'/>" title="<spring:message code='button.delete'/>" style="cursor:hand;" onclick="fn_delete();return false;" onkeydown="this.onclick();return false;"></td>
									<td class="end"><img src="/images/button/0205.png" alt="<spring:message code='button.close'/>" title="<spring:message code='button.close'/>" style="cursor:hand;" onclick="fn_close();return false;" onkeydown="this.onclick();return false;"/></td>
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
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>