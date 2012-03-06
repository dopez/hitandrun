<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<jsp:include page="/gps/cmm/admHeader.do" flush="false"></jsp:include>
<script type="text/javaScript" language="javascript">
	JQ_setValidation('programManageVO');
	JQ_onload();
<%-- 
/************************************************************************ 
함수명 : link_page                                   
설   명 : 페이지 이동 함수
		조회된 목록에서 다른 페이지 번호를 누르면 해당 페이지로 이동
인   자 : pageNo        
사용법 : link_page(pageNo)              
작성일 : 2011-06-17   
작성자 : 포탈 개발팀 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function link_page(pageNo){
	document.listForm.pageIndex.value = pageNo;
	JQ_request("programManageVO", "<c:url value='/gps/adm/menu/programListPopup.do'/>");
}

<%--
/************************************************************************ 
함수명 : fn_search                                   
설   명 : 조회조건에 따라 목록을 조회하는 함수
인   자 : 없음(form안에 존재하는 값)
사용법 : fn_search()              
작성일 : 2011-06-17   
작성자 : 포탈 개발팀 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_search(){
	document.listForm.pageIndex.value = 1;
   	JQ_request("programManageVO", "<c:url value='/gps/adm/menu/programListPopup.do'/>", "programManageVO");
}

<%--
/************************************************************************ 
함수명 : fn_parentUrlSet                                   
설   명 : 조회조건에 따라 목록을 조회하는 함수
인   자 : url 부모창에 setting될 프로그램url
사용법 : fn_parentUrlSet()              
작성일 : 2011-06-17   
작성자 : 포탈 개발팀 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_openerUrlSet(url,programId){
	$(opener.document).find('#menuUrl').val(url);
	$(opener.document).find('#programId').val(programId);
	window.close();
}
</script>
<!-- page contents start -->
<div class="contents_area">
	<form:form commandName="programManageVO" name="listForm" method="post">
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
								<td class="title">프로그램목록</td>
								<td class="rbtn">
									<table class="inner_btn">
									<tr>
										<td class="end"><img src="/images/button/0205.png" alt="<spring:message code='button.close'/>" title="<spring:message code='button.close'/>" onclick="window.close();"></td>
									</tr>
									</table>
								</td>
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
												<option value="10" <c:if test="${programManageVO.pageUnit == '10'}">selected</c:if>>10</option>
												<option value="20" <c:if test="${programManageVO.pageUnit == '20'}">selected</c:if>>20</option>
												<option value="30" <c:if test="${programManageVO.pageUnit == '30'}">selected</c:if>>30</option>
												<option value="50" <c:if test="${programManageVO.pageUnit == '50'}">selected</c:if>>50</option>
												<option value="100" <c:if test="${programManageVO.pageUnit == '100'}">selected</c:if>>100</option>
											</select>
										</td>
										<td>
												<select name="searchSysId" onchange="javaScript:fn_search();" >
													<option value="">전체</option>
													<c:forEach var="sysInfoList" items="${sysInfo}" varStatus="status">
													<option value="<c:out value="${sysInfoList.sysId}" />" <c:if test="${programManageVO.searchSysId == sysInfoList.sysId}">selected</c:if> ><c:out value="${sysInfoList.sysNm}" /></option>
													</c:forEach>
												</select>
										</td>						
										<td>
												<select name="searchCondition">
												<option value="">- 선택 -</option>
												<option value="1" <c:if test="${programManageVO.searchCondition == '1'}">selected</c:if>>프로그램명</option>
												<option value="2" <c:if test="${programManageVO.searchCondition == '2'}">selected</c:if>>한글명</option>
												</select>						
				
										</td>
										<td><input name="searchKeyword" type="text" size="35" value="${programManageVO.searchKeyword}"  maxlength="35" id="searchKeyword" ></td>
						    			<td><img src="/images/button/0221.png" alt="<spring:message code='button.inquire'/>" title="<spring:message code='button.inquire'/>" onclick="fn_search()"></td>
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
							<table class="datalist01" summary="프로그램목록">
							<caption>프로그램목록</caption>
							<colgroup>
								<col class="column1"/>
								<col class="column2"/>
								<col class="column2"/>
								<col class="column2"/>
								<col class="column2"/>
							</colgroup>
							<thead>
							<tr>
								<th scope="col">번호</th>
								<th scope="col">프로그램코드</th>
								<th scope="col">프로그램명</th>
								<th scope="col">프로그램한글명</th>
								<th scope="col">프로그램URL</th>
							</tr>
							</thead>
							<tbody>
						<c:forEach var="resultInfo" items="${resultList}" varStatus="status">
							<tr style="cursor:hand" onmouseover="this.style.backgroundColor='#fafafa'" onmouseout="this.style.backgroundColor=''" onclick="fn_openerUrlSet('${resultInfo.url}','${resultInfo.programId}')">
								<td class="column1"><c:out value="${paginationInfo.totalRecordCount - ((programManageVO.pageIndex - 1) * programManageVO.pageUnit + (status.count-1))}"/></td>
								<td class="column2"><c:out value="${resultInfo.programStrePath}"/></td>
								<td class="column2"><c:out value="${resultInfo.programFileNm}"/></td>
								<td class="column2"><c:out value="${resultInfo.programKoreannm}"/></td>
								<td class="column2"><c:out value="${resultInfo.url}"/></td>			
							</tr>
						 </c:forEach>
						<c:if test="${fn:length(resultList) == 0}">
								<tr> 
									<td class="blank" colspan="5"><spring:message code="common.nodata.msg" /></td>
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
									<input name="pageIndex" type="hidden" value="<c:out value='${programManageVO.pageIndex}'/>"/>
								</td>
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