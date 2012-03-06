<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<jsp:include page="/gps/cmm/admHeader.do" flush="false"></jsp:include>
<%-- 
/** 
 * outline   : 게시판관리 게시판목록 조회화면
 * filename : bbsList.jsp
 * @author 통계포탈 이관형 
 * @since 2011.06.28
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == 개정이력(Modification Information) == 
 *   
 *   date        author     note 
 * ----------    -------    --------------------------- 
 * 2011.06.28     이관형           최초 생성 
 * </pre> 
 */
--%>
<script type="text/javaScript" language="javascript">
<!--

JQ_setValidation('searchVO');
JQ_onload();

<%-- 
/************************************************************************ 
함수명 : fncPageOnload                                   
설   명 : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
인   자 : 없음        
사용법 : fncPageOnload()              
작성일 : 2011-06-17   
작성자 : 포탈 개발팀 이관형       

      date    author      note 
 ----------   -------     ------------------- 
 
************************************************************************/
--%>
function fncPageOnload()
{
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
작성자 : 포탈 개발팀 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function link_page(pageNo){
	document.listForm.pageIndex.value = pageNo;
	JQ_request("searchVO", "<c:url value='/gps/adm/bbs/selectBbsList.do'/>");
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
   	JQ_request("searchVO", "<c:url value='/gps/adm/bbs/selectBbsList.do'/>", "searchVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_regist                                   
설   명 : 게시물 등록 화면으로 이동하는 함수
인   자 : 없음
사용법 : fn_regist()              
작성일 : 2011-06-17   
작성자 : 포탈 개발팀 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
 function fn_regist(){
   	JQ_request("searchVO", "<c:url value='/gps/adm/bbs/registerBbs.do'/>", "searchVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_detail                                   
설   명 : 게시물 상세 화면으로 이동하는 함수
인   자 : 없음
사용법 : fn_detail()              
작성일 : 2011-06-17   
작성자 : 포탈 개발팀 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_detail(bbsId){
 	JQ_setValueObj('bbsId', bbsId); 
   	JQ_request("searchVO", "<c:url value='/gps/adm/bbs/selectBbsDetail.do'/>", "searchVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_excel_download                                   
설   명 : 현재 조회되어있는 게시물 목록을 엑셀파일형식으로 다운받는 함수
인   자 : 없음
사용법 : fn_excel_download()              
작성일 : 2011-06-17   
작성자 : 포탈 개발팀 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_excel_download(){
	JQ_request("searchVO", "<c:url value='/gps/adm/bbs/selectBbsExcelDownload.do'/>", "searchVO");
}

-->
</script>

<!-- ui-layout-center start  -->
<div class="ui-layout-content">
	<!-- page contents start -->
	<div class="contents_area">
		<form:form commandName="searchVO" name="listForm" method="post">
		<form:hidden path="bbsId" />
		<form:hidden path="jssfcCode" />
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
										<td class="title">게시판정보</td>
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
												<select>
													<option value="10">10</option>
													<option value="20">20</option>
													<option value="30">30</option>
													<option value="50">50</option>
													<option value="100">100</option>
												</select>
											</td>
											<td>정렬순서</td>
											<td>
												<form:select path="orderByClause" onchange="javaScript:fn_search();" >
													<form:option value="" label="- 선택 -"/>
													<form:options items="${orderByClause}" />
												</form:select>
											</td>
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
						<tr><td class="ht2"></td></tr>
						<tr>
							<td>
								<table class="datalist01" summary="게시판관리">
								<caption>게시판리스트</caption>
								<colgroup>
									<col class="column1"/>
									<col class="column3"/>
									<col class="column3"/>
									<col class="column3"/>
									<col class="column3"/>
									<col class="column3"/>
									<col class="column3"/>
									<col class="column3"/>
									<col class="column3"/>
									<col class="column1"/>
								</colgroup>
								<thead>
								<tr>
									<th scope="col">번호</th>
									<th scope="col">아이디</th>
									<th scope="col">성명</th>
									<th scope="col">소속</th>
									<th scope="col">유형</th>
									<th scope="col">이메일</th>
									<th scope="col">로그인횟수</th>
									<th scope="col">최종로그인</th>
									<th scope="col">등록일</th>
									<th scope="col">승인여부</th>
								</tr>
								</thead>
								<tbody>
									<c:forEach var="resultInfo" items="${resultList}" varStatus="status">
										<tr onmouseover="this.style.backgroundColor='#fafafa'" onmouseout="this.style.backgroundColor=''">
											<td class="tc"><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td> 
											<td class="tc" onclick="javascript:fn_detail('${resultInfo.bbsId}');" >${resultInfo.bbsId}</td>
											<td class="tc"><c:out value="${resultInfo.nm}" /></td>
											<td class="tc"><c:out value="${resultInfo.psitn}" /></td>
											<td class="tc"><c:out value="${resultInfo.grad}" /></td>
											<td class="tc"><c:out value="${resultInfo.email}" /></td>
											<td class="tc"><c:out value="${resultInfo.loginCo}" /></td>
											<td class="tc"><c:out value="${resultInfo.lastLoginTime}" /></td>
											<td class="tc"><c:out value="${resultInfo.registDt}" /></td>
											<td class="tc"><img src="/images/common/o.gif"/></td>
										</tr>
									 </c:forEach>
									<c:if test="${fn:length(resultList) == 0}">
										<tr> 
											<td class="lt_text3" colspan="4">
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
										<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
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
											<td>
												<form:select path="searchCondition" >
													<form:option value="" label="- 선택 -"/>
													<form:options items="${searchCondition}" />
												</form:select>
											</td>
											<td><input name="searchKeyword" type="text" size="35" value="${searchVO.searchKeyword}"  maxlength="35" id="searchKeyword" ></td>
											<td><img src="/images/button/0208.png" alt="<spring:message code='button.search'/>" title="<spring:message code='button.search'/>" onclick="fn_search()"></td>
										</tr>
										</table>
									</td>
									<td class="rbtn">
										<table class="inner_btn" align="right">
											<tr>
												<td><img src="/images/button/0211.png" alt="<spring:message code='button.add'/>" title="<spring:message code='button.add'/>" style="cursor:hand;" onclick="fn_regist();return false;" onkeydown="this.onclick();return false;"></td>
												<td><img src="/images/button/0428.png" alt="<spring:message code="button.selectedToDelete" />" title="<spring:message code="button.selectedToDelete" />" style="cursor:hand;" onclick="fn_selective_delete();return false;" onkeydown="this.onclick();return false;"></td>
												<td><img src="/images/button/0613.png" alt="<spring:message code="button.exceldownload" />" title="<spring:message code="button.exceldownload" />" style="cursor:hand;" onclick="fn_excel_download();return false;" onkeydown="this.onclick();return false;"></td>
											</tr>
										</table>
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
</div>
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>