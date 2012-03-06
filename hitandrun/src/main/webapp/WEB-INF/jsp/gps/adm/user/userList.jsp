<%-- 
/** 
 * outline   : 사용자관리 사용자목록 조회화면
 * filename : userList.jsp
 * @author 범정부통계포털 이관형 
 * @since 2011.06.17
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.06.17     이관형           최초 생성 
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
function fncPageOnload()
{
	<c:if test="${!empty message}">
		alert('<c:out value="${message}"/>');
		parent.leftBody.location.href = "/gps/adm/user/selectUserTypeList.do";
	</c:if>
	//frameset controller start
	//frameset controller end
	$("#selectall").click(select_all);
	
}
<%--
/************************************************************************ 
fnc name : select_all                                   
outline : 조회된 게시물에 있는 체크박스를 전체선택/전체해제 시켜주는 함수       
parameter : 없음        
directions : select_all()              
since : 2011-07-22   
author : 이진우       

    date      author             note  
 ----------   -------     ------------------- 

************************************************************************/
--%>
function select_all()
{
	var checked=$("#selectall").attr("checked");
	$("input:checkbox").each(function(){
		var subChecked=$(this).attr("checked");
		if(subChecked != checked)
			$(this).click();
	});
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
	document.listForm.pageIndex.value = pageNo;
	JQ_request("userManageVO", "<c:url value='/gps/adm/user/selectUserList.do'/>");
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
	document.listForm.pageIndex.value = 1;
 	JQ_setValueObj('pageUnit', document.listForm.pageUnit.value); 
   	JQ_request("userManageVO", "<c:url value='/gps/adm/user/selectUserList.do'/>", "userManageVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_regist                                   
설   명 : 사용자 등록 화면으로 이동하는 함수
인   자 : 없음
사용법 : fn_regist()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
 function fn_regist(){
   	JQ_request("userManageVO", "<c:url value='/gps/adm/user/registerUser.do'/>", "userManageVO");
}

<%-- 
 /************************************************************************ 
 함수명 : fn_approval                                  
 설   명 : 선택된 사용자 승인처리
 인   자 : 없음
 사용법 : fn_approval()              
 작성일 : 2011-06-22   
 작성자 : 범정부통계포털 이진우       

       date    author      note 
  ----------   -------     ------------------- 

  ************************************************************************/
  --%>
  function fn_approval(){
	  var num = 0;
		var checkVal = new Array();
		
		$("[name^=chkbox]:checked").each(function(){
			checkVal.push($(this).val());
		})
		if (checkVal.length==0){
			alert("선택된 대상이 없습니다");
			return;
		}	
		document.listForm.appUserIdList.value = checkVal;
		if (confirm("선택된 사용자를 승인하시겠습니까?")) {
		 	JQ_setValueObj('approvalAt', 'Y');
	   		JQ_request("userManageVO", "<c:url value='/gps/adm/user/approvalUser.do'/>","userManageVO");
		}
 }

<%-- 
/************************************************************************ 
함수명 : fn_modify                                   
설   명 : 사용자 수정/상세 화면으로 이동하는 함수
인   자 : 없음
사용법 : fn_modify()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_modify(userId, authorId, sysId, userAuthorId){
 	JQ_setValueObj('userId', userId);
 	JQ_setValueObj('selectedAuthorId', authorId);
 	JQ_setValueObj('sysId', sysId);
 	JQ_setValueObj('userAuthorId', userAuthorId);
   	JQ_request("userManageVO", "<c:url value='/gps/adm/user/modifyUser.do'/>", "userManageVO");
}
</script>
<!-- page contents start -->
<div class="contents_area">
	<form:form commandName="userManageVO" name="listForm" method="post">
	<form:hidden path="listType" />
	<form:hidden path="userId" />
	<form:hidden path="sysId" />
	<form:hidden path="userAuthorId" />
	<form:hidden path="authorId" />
	<form:hidden path="selectedAuthorId" />
	<form:hidden path="approvalAt" />
	<form:hidden path="appUserIdList" />
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
								<td class="title">사용자정보</td>
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
												<option value="10" <c:if test="${userManageVO.pageUnit eq '10'}">selected</c:if>>10</option>
												<option value="20" <c:if test="${userManageVO.pageUnit eq '20'}">selected</c:if>>20</option>
												<option value="30" <c:if test="${userManageVO.pageUnit eq '30'}">selected</c:if>>30</option>
												<option value="50" <c:if test="${userManageVO.pageUnit eq '50'}">selected</c:if>>50</option>
												<option value="100" <c:if test="${userManageVO.pageUnit eq '100'}">selected</c:if>>100</option>
											</select>
										</td>
										<td class="text">정렬순서</td>
										<td>
											<form:select path="orderByClause" onchange="javaScript:fn_search();" >
											    <option value="">- 선택 - </option>
												<form:option value="1" label="사용자ID"/>
												<form:option value="2" label="성명"/>
												<!--<form:options items="${orderByClause}" />-->
											</form:select>
										</td>
										<td class="text">승인여부</td>
										<td>
											<select name="approvalAtCond" onchange="javaScript:fn_search();" >
											    <option value="">전체</option>
												<option value="Y" <c:if test="${userManageVO.approvalAtCond eq 'Y'}">selected</c:if>>승인</option>
												<option value="N" <c:if test="${userManageVO.approvalAtCond eq 'N'}">selected</c:if>>미승인</option>
											</select>
										</td>
										<td>
											<form:select path="searchCondition" >
											    <option value="">- 선택 - </option>
												<form:option value="1" label="사용자ID"/>
												<form:option value="2" label="성명"/>
												<!--<form:option value="3" label="- 선택 -"/> -->
												<!--<form:options items="${searchCondition}" /> -->
											</form:select>
										</td>
										<td><input name="searchKeyword" type="text" size="35" value="${userManageVO.searchKeyword}" maxlength="35" id="searchKeyword" class="wi100"></td>
										<td><a onclick="fn_search()" style="cursor:hand"><img src="/images/button/0208.png" alt="<spring:message code='button.search'/>" title="<spring:message code='button.search'/>" ></a></td>
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
								<col class="column2"/>
								<col class="column2"/>
								<col class="column2"/>
								<col class="column2"/>
								<col class="column2"/>
								<col class="column2"/>
								<col class="column3"/>
								<col class="column3"/>
								<col class="column3"/>
								<col class="column10"/>
							</colgroup>
							<thead>
							<tr>
								<th scope="col">번호</th>
								<th scope="col">권한</th>
								<th scope="col">아이디</th>
								<th scope="col">성명</th>
								<th scope="col">소속</th>
								<th scope="col">부서</th>
								<th scope="col">직책</th>
								<th scope="col">로그인횟수</th>
								<th scope="col">최종로그인</th>
								<th scope="col">등록일</th>
								<th scope="col" class="end"><input type="checkbox" id="selectall" class="noline" title="전체선택" ></th>
							</tr>
							</thead>
							<tbody>
						<c:forEach var="resultInfo" items="${resultList}" varStatus="status">
							<tr onmouseover="this.style.backgroundColor='#fafafa'" onmouseout="this.style.backgroundColor=''">
								<td class="column10"><c:out value="${paginationInfo.totalRecordCount - ((userManageVO.pageIndex - 1) * userManageVO.pageUnit + status.count) + 1}"/></td> 
								<td class="column2"><c:out value="${resultInfo.authorNm}" /></td>
								<td class="column2"><a style="cursor:hand" onclick="javascript:fn_modify('${resultInfo.userId}','${resultInfo.authorId}','${resultInfo.sysId}','${resultInfo.userAuthorId}');" >${resultInfo.userId}</a></td>
								<td class="column2"><c:out value="${resultInfo.nm}" /></td>
								<td class="column2"><c:out value="${resultInfo.orgNm}" /></td>
								<td class="column2"><c:out value="${resultInfo.deptNm}" /></td>
								<td class="column2"><c:out value="${resultInfo.rspofc}" /></td>
								<td class="column3"><c:out value="${resultInfo.loginCo}" /></td>
								<td class="column3"><c:out value="${resultInfo.lastLoginTime}" /></td>
								<td class="column3"><c:out value="${resultInfo.registDt}" /></td>
								<td class="column10">
									<c:choose>
										<c:when test="${resultInfo.approvalAt eq 'Y'}"><img src="/images/gps/cmm/icon/o.gif"></c:when>
										<c:otherwise><input type="checkbox" name="chkbox" value="${resultInfo.userId}" class="noline"/></c:otherwise>
									</c:choose>
								</td>
							</tr>
						 </c:forEach>
						<c:if test="${fn:length(resultList) eq 0}">
							<tr> 
								<td class="blank" colspan="10"><spring:message code="common.nodata.msg" /></td>
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
									<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="link_page" />
									<input name="pageIndex" type="hidden" value="<c:out value='${userManageVO.pageIndex}'/>"/>
								</td>
							</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table class="rbuttonarea">
							<tr>
								<td class="end"><c:if test="${userManageVO.approvalAt ne 'Y'}"><a onclick="fn_approval()" style="cursor:hand"><img src="/images/button/0229.png" alt="승인" title="승인" ></a></c:if></td>
								<!--
								<c:choose>
									<c:when test="${userManageVO.se eq '001'}">
										<td>
											<img src="/images/button/0211.png" alt="<spring:message code='button.add'/>" title="<spring:message code='button.add'/>" onclick="fn_regist()">
										</td>
									</c:when>
									<c:otherwise>
										<td>
											<img src="/images/button/0211.png" alt="<spring:message code='button.add'/>" title="<spring:message code='button.add'/>" onclick="fn_regist()">
										</td>
									</c:otherwise>
								</c:choose>
								-->
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