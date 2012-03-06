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
	</c:if>
	//frameset controller start
	//frameset controller end
	
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
   	JQ_request("userManageVO", "<c:url value='/gps/adm/user/userAbsncePopup.do'/>", "userManageVO");
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
 function fn_regist(userId, nm){

	if (confirm(nm + "에게 권한을 인계 하시겠습니까?")) {
		 JQ_setValueObj('transferUserId', userId); 
		 JQ_setValueObj('selectedAuthorId', $("#selectedAuthorId").val()); 
		 JQ_setValueObj('approvalAt', 'N');
		 JQ_request("userManageVO", "<c:url value='/gps/adm/user/insertUserAbsnce.do'/>", "userManageVO");
	}
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
  function fn_delete(userId, nm, userAuthorId){
 	if (confirm(nm + "의 권한을 회수 하시겠습니까?")) {
 		 JQ_setValueObj('transferUserId', userId);
 		 JQ_setValueObj('userAuthorId', userAuthorId); 
 		 JQ_setValueObj('selectedAuthorId', $("#selectedAuthorId").val()); 
 		 JQ_setValueObj('approvalAt', 'Y');
 		 JQ_setValueObj('orgId', $("#orgId").val());
 		 JQ_request("userManageVO", "<c:url value='/gps/adm/user/deleteUserAbsnce.do'/>", "userManageVO");
 	}
  }
  

</script>
<!-- page contents start -->
<div class="contents_area">
	<form:form commandName="userManageVO" name="listForm" method="post">
	<form:hidden path="userId" />
	<form:hidden path="selectedAuthorId" />
	<form:hidden path="transferUserId" />
	<form:hidden path="approvalAt" />
	<form:hidden path="wrcNm" />
	<form:hidden path="orgId" />
	<form:hidden path="userAuthorId" />
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
											<form:option value="1" label="사용자ID"/>
											<form:option value="2" label="성명"/>
											<!--<form:options items="${orderByClause}" />-->
										</form:select>
									</td>
									<td>
										<form:select path="searchCondition" >
											<form:option value="" label="전체"/>
											<form:option value="1" label="사용자ID"/>
											<form:option value="2" label="성명"/>
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
							<col class="column3"/>
							<col class="column3"/>
							<col class="column3"/>
							<col class="column3"/>
						</colgroup>
						<thead>
						<tr>
							<th scope="col">번호</th>
							<th scope="col">아이디</th>
							<th scope="col">성명</th>
							<th scope="col">부서</th>
							<th scope="col">직책</th>
							<th scope="col">로그인횟수</th>
							<th scope="col">최종로그인</th>
							<th scope="col">등록일</th>
							<th scope="col">인계</th>
						</tr>
						</thead>
						<tbody>
					<c:forEach var="resultInfo" items="${resultList}" varStatus="status">
						<tr onmouseover="this.style.backgroundColor='#fafafa'" onmouseout="this.style.backgroundColor=''">
							<td class="column10"><c:out value="${paginationInfo.totalRecordCount - ((userManageVO.pageIndex - 1) * userManageVO.pageUnit + status.count) + 1}"/></td>
							<td class="column2"><c:out value="${resultInfo.userId}" /></td>
							<td class="column2"><c:out value="${resultInfo.nm}" /></td>
							<td class="column2"><c:out value="${resultInfo.deptNm}" /></td>
							<td class="column2"><c:out value="${resultInfo.rspofc}" /></td>
							<td class="column3"><c:out value="${resultInfo.loginCo}" /></td>
							<td class="column3"><c:out value="${resultInfo.lastLoginTime}" /></td>
							<td class="column3"><c:out value="${resultInfo.registDt}" /></td>
							<td class="column3">
								<c:choose>
									<c:when test="${userManageVO.approvalAt eq 'Y'}">
										<a onclick="javaScript:fn_delete('${resultInfo.userId}','${resultInfo.nm}','${resultInfo.userAuthorId}')"><c:out value="권한회수" /></a>
									</c:when>
									<c:otherwise>
										<a onclick="javaScript:fn_regist('${resultInfo.userId}','${resultInfo.nm}')"><c:out value="인계하기" /></a>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					 </c:forEach>
					<c:if test="${fn:length(resultList) eq 0}">
						<tr> 
							<td class="blank" colspan="10"><spring:message code="gps.absncenodata.msg" /></td>
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
					<td class="rbtn">
						<table class="inner_btn" align="right">
							<tr>
								<td><a onclick="window.close();" style="cursor:hand"><img src="/images/button/0205.png" alt="<spring:message code='button.close'/>" title="<spring:message code='button.close'/>"></a></td>
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