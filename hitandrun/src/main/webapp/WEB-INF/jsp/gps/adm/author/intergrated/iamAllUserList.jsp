<%-- 
/** 
 * outline   : 통합권한 관리 - 전체 사용자 목록 조회화면
 * filename : iamAllUserList.jsp
 * @author 범정부통계포털 이관형 
 * @since 2011.08.23
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.08.23     이관형           최초 생성 
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
	JQ_setValidation('igrUserAuthorManageVO');
	JQ_onload();
	
<%-- 
/************************************************************************ 
함수명 : fncPageOnload                                   
설   명 : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)
인   자 : 없음        
사용법 : fncPageOnload()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 
 
************************************************************************/
--%>
function fncPageOnload() {
	<c:if test="${!empty message}">
		alert('<c:out value = "${message}"/>');
		window.opener.parent.rightBody.location.href = "/gps/adm/author/intergrated/selectIamUserList.do?authorId=" + $("#authorId").val() + "&sysId=" + $("#sysId").val() + "&authorNm=" + escape(encodeURIComponent($("#authorNm").val()));
	</c:if>
	//frameset controller start
	//frameset controller end
	$("#selectall").click(select_all);
}

<%--
/************************************************************************ 
함수명 : select_all                                   
설   명 : 조회된 사용자에 있는 체크박스를 전체선택/전체해제 시켜주는 함수       
인   자 : 없음        
사용법 : select_all()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

************************************************************************/
--%>
function select_all() {
	var checked=$("#selectall").attr("checked");
	$("input:checkbox").each(function(){
		var subChecked=$(this).attr("checked");
		if(subChecked != checked)
			$(this).click();
	});
}
<%-- 
/************************************************************************ 
함수명 : fn_selective_delete                                   
설   명 : 조회된 사용자에 있는 체크박스를 선택한 후 선택된 사용자에 대해 삭제를 수행하는 함수       
		삭제할 사용자에 있는 체크박스를 체크한 후 버튼을 누르면 삭제하시겠습니까 라는 확인 팝업 출력 후
		사용자가 확인을 누르면 삭제가 수행 됨
인   자 : 없음        
사용법 : fn_selective_delete()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형         

      date    author      note 
 ----------   -------     ------------------- 

************************************************************************/
--%>
function fn_selective_insert() {

	var num = 0;
	var checkVal = new Array();
	if($("[name^=chkbox]:checked").length == 0) {
		alert("<spring:message code="gps.nouser.selected" />");
		return;
	}
	$("[name^=chkbox]:checked").each(function(){
		checkVal.push($(this).val());
	})	
	document.allListForm.userIdList.value = checkVal;
	if (confirm("<spring:message code="gps.selecteduserauthorinsert.msg" />")) {
   		JQ_request("igrUserAuthorManageVO", "<c:url value='/gps/adm/author/intergrated/insertUserAuthorList.do'/>");
	}
}
<%-- 
/************************************************************************ 
함수명 : link_page                                   
설   명 : 페이지 이동 함수
		조회된 목록에서 다른 페이지 번호를 누르면 해당 페이지로 이동
인   자 : pageNo        
사용법 : link_page(pageNo)              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function link_page(pageNo){
	document.allListForm.pageIndex.value = pageNo;
	JQ_request("igrUserAuthorManageVO", "<c:url value='/gps/adm/author/intergrated/selectIamAllUserList.do'/>");
}

<%--
/************************************************************************ 
함수명 : fn_search                                   
설   명 : 조회조건에 따라 목록을 조회하는 함수
인   자 : 없음(form안에 존재하는 값)
사용법 : fn_search()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_search(){
	document.allListForm.pageIndex.value = 1;
	JQ_request("igrUserAuthorManageVO", "<c:url value='/gps/adm/author/intergrated/selectIamAllUserList.do'/>");
}

<%--
/************************************************************************ 
함수명 : fn_regist                                   
설   명 : 데이타를 저장한다.
인   자 : userId
사용법 : fn_regist()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_regist(userId) {
	if (confirm("<spring:message code="gps.userauthorinsert.msg" />")) {
	 	JQ_setValueObj('userId', userId);
		JQ_request("igrUserAuthorManageVO", "<c:url value='/gps/adm/author/intergrated/insertUserAuthor.do'/>");
	}
}
</script>
<!-- page contents start -->
<div class="contents_area">
	<form:form commandName="igrUserAuthorManageVO" name="allListForm" method="post">
	<form:hidden path="sysId" />
	<form:hidden path="authorId" />
	<form:hidden path="authorNm" />
	<form:hidden path="userId" />
	<form:hidden path="userIdList" />
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
						<td class="title">권한관리 - 사용자 권한추가</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table class="default">
					<tr>
						<td class="tl">
							<table class="option">
							<tr>
								<td class="first">출력목록수</td>
								<td>
									<select name="pageUnit" onchange="javaScript:fn_search();" >
										<option value="10" <c:if test="${igrUserAuthorManageVO.pageUnit == '10'}">selected</c:if>>10</option>
										<option value="20" <c:if test="${igrUserAuthorManageVO.pageUnit == '20'}">selected</c:if>>20</option>
										<option value="30" <c:if test="${igrUserAuthorManageVO.pageUnit == '30'}">selected</c:if>>30</option>
										<option value="50" <c:if test="${igrUserAuthorManageVO.pageUnit == '50'}">selected</c:if>>50</option>
										<option value="100" <c:if test="${igrUserAuthorManageVO.pageUnit == '100'}">selected</c:if>>100</option>
									</select>
								</td>
								<td>
										<select name="searchCondition">
										<option value="">전체</option>
										<option value="1" <c:if test="${igrUserAuthorManageVO.searchCondition == '1'}">selected</c:if>>사용자 ID</option>
										<option value="2" <c:if test="${igrUserAuthorManageVO.searchCondition == '2'}">selected</c:if>>사용자 이름</option>
										</select>						
		
								</td>
								<td><input name="searchKeyword" type="text" class="validate[optional,length[0,30]]" size="35" value="${igrUserAuthorManageVO.searchKeyword}"  maxlength="35" id="searchKeyword" ></td>
				    			<td><a onclick="javaScript:fn_search()" style="cursor:hand"><img src="/images/button/0208.png" alt="<spring:message code='button.search'/>" title="<spring:message code='button.search'/>" ></a></td>
							</tr>
							</table>
						</td>
						<td>
							<dl class="board_counter">
								<dt class="total">전체</dt>
								<dd class="total"><c:out value='${paginationInfo.totalRecordCount}'/></dd>
								<dt class="page">페이지</dt>
								<dd class="page"><c:out value='${paginationInfo.currentPageNo}'/> / <c:out value='${paginationInfo.totalPageCount }'/></dd>
							</dl>
						</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table class="datalist01" summary="사용자관리">
					<caption>사용자리스트</caption>
					<colgroup>
						<col class="column10"/>
						<col class="column10"/>
						<col class="column2"/>
						<col class="column2"/>
						<col class="column2"/>
						<col class="column2"/>
						<col class="column3"/>
					</colgroup>
					<thead>
					<tr>
						<th scope="col"><input type="checkbox" id="selectall" title="전체선택" class="noline"></th>
						<th scope="col">번호</th>
						<th scope="col">기관명</th>
						<th scope="col">부서명</th>
						<th scope="col">사용자ID</th>
						<th scope="col">사용자 이름</th>
						<th scope="col" class="end">권한등록</th>
					</tr>
					</thead>
					<tbody>
				<c:forEach var="resultInfo" items="${resultList}" varStatus="status">
					<tr onmouseover="this.style.backgroundColor='#fafafa'" onmouseout="this.style.backgroundColor=''">
						<td class="column10"><input type="checkbox" name="chkbox" value="${resultInfo.userId}" class="noline"/></td>
						<td class="column10"><c:out value="${(igrUserAuthorManageVO.pageIndex - 1) * igrUserAuthorManageVO.pageUnit + status.count}"/></td>
						<td class="column2"><c:out value="${resultInfo.orgNm}"/></td>
						<td class="column2"><c:out value="${resultInfo.deptNm}"/></td>
						<td class="column2"><c:out value="${resultInfo.userId}"/></td>
						<td class="column2"><c:out value="${resultInfo.nm}"/></td>
						<td class="column3"><a onclick="javasciprt:fn_regist('<c:out value="${resultInfo.userId}"/>');" style="cursor:hand"><img src="/images/button/0470.png" alt="권한위임" title="권한위임"/></a></td>
					</tr>
				 </c:forEach>
				<c:if test="${fn:length(resultList) == 0}">
					<tr>
						<td class="blank" colspan="5">
							<spring:message code="common.nodata.msg" />
						</td>
					</tr>
				</c:if>
					</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table class="datapaging" align="center">
					<tr>
						<td class="tc">
							<ui:pagination paginationInfo = "${paginationInfo}"	type="image" jsFunction="link_page" />
							<input name="pageIndex" type="hidden" value="<c:out value='${igrUserAuthorManageVO.pageIndex}'/>"/>
						</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table class="rbuttonarea">
					<tr>
						<td><a onclick="javaScript:fn_selective_insert();" style="cursor:hand"><img src="/images/gps/adm/author/0903.png" alt="선택 사용자 권한부여" title="선택 사용자 권한부여"/></a></td>
						<td class="end"><a onclick="window.close();" style="cursor:hand"><img src="/images/button/0205.png" alt="<spring:message code='button.close'/>" title="<spring:message code='button.close'/>"></a></td>
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