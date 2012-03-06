<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<jsp:include page="/gps/cmm/admHeader.do" flush="false"></jsp:include>
<%-- 
/** 
 * outline   : 게시판목록
 * filename : bbsInfoList.jsp
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

}

<%-- 
/************************************************************************ 
함수명 : link_page                                   
설   명 : 페이지 이동 함수
		조회된 목록에서 다른 페이지 번호를 누르면 해당 페이지로 이동
인   자 : pageNo        
사용법 : link_page(pageNo)              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 황기연       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function link_page(pageNo){
	$('#pageIndex').val(pageNo);
	JQ_request("bbsSearchVO", "<c:url value='/gps/adm/bbs/selectBbsInfoList.do'/>","bbsSearchVO");
}

<%--
/************************************************************************ 
함수명 : fn_search                                   
설   명 : 조회조건에 따라 목록을 조회하는 함수
인   자 : 없음(form안에 존재하는 값)
사용법 : fn_search()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 황기연       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_search(){
	$('#pageIndex').val(1);
   	JQ_request("bbsSearchVO", "<c:url value='/gps/adm/bbs/selectBbsInfoList.do'/>", "bbsSearchVO");
}

<%--
/************************************************************************ 
함수명 : fn_modifyBbsInfoPopupTab1                               
설   명 : 게시판기본정보수정팝업호출
인   자 : 없음(form안에 존재하는 값)
사용법 : fn_modifyBbsInfoPopupTab1()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 황기연       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_modifyBbsInfoPopupTab(bbsId){
	$('#bbsId').val(bbsId);
	gfn_postPopupWin("bbsInfoVO","<c:url value='/gps/adm/bbs/modifyBbsInfoPopupTab.do'/>","게시판기본정보수정",500,600, "yes","no",0);
}
<%-- 
/************************************************************************ 
fnc name : fn_registerPopup                          
outline : 게시판 추가화면호출
parameter : 없음        
directions : fn_registerPopup()              
since : 2011-07-05    
author : 통계포털  황기연       

    date      author             note  
 ----------   -------     ------------------- 
 2011.06.10    황기연                                          
************************************************************************/ 
--%>
function fn_registerPopup(){
	gfn_postPopupWin("bbsSearchVO","<c:url value='/gps/adm/bbs/registerBbsInfoPopup.do'/>","게시판생성",400,600, "no","no",0);
}

<%-- 
/************************************************************************ 
함수명 : fn_excel_download                                   
설   명 : 현재 조회되어있는 게시판 목록을 엑셀파일형식으로 다운받는 함수
인   자 : 없음
사용법 : fn_excel_download()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 황기연       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_excel_download(){
	JQ_request("bbsSearchVO", "<c:url value='/gps/adm/bbs/selectBbsInfoExcelList.do'/>", "bbsSearchVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_subpage                                   
설   명 : 게시판이적용되어있는페이지호출
인   자 : 없음
사용법 : fn_subpage()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 황기연       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_subpage(menuId,leftMenuId,bbsId){
	if(menuId != ""){
		$('#menuId').val(menuId);
		$('#leftMenuId').val(leftMenuId);
		$('#bbsId').val(bbsId);
		gfn_postPopupWin("bbsInfoVO","<c:url value='/gps/bbs/selectBbsList.do'/>","게시판적용페이지",768,1024, "yes","yes",0);
	}else{
		alert("적용된 페이지가 없습니다");
	}
}
</script>
	<!-- ui-layout-center start  -->
<div class="ui-layout-content">
	<!-- page contents start -->
	<div class="contents_area">
		<form:form commandName="bbsInfoVO" method="post">
		<form:hidden path="dbTname"/>
		<form:hidden path="bbsId"/>
		<form:hidden path="sysId"/>
		<form:hidden path="subpageId"/>
		<form:hidden path="menuId"/>
		<form:hidden path="leftMenuId"/>
		</form:form>
		<form:form commandName="bbsSearchVO" method="post">
		<form:hidden path="dbTname"/>
		<form:hidden path="pageIndex"/>
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
											<td class="title"><c:out value="${bbsDbVO.dbName}"/> DB 게시판 목록</td>
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
													<form:select path="pageUnit" onchange="fn_search();return false;">
														<form:option value="10">10</form:option>
														<form:option value="20">20</form:option>
														<form:option value="30">30</form:option>
														<form:option value="50">50</form:option>
														<form:option value="100">100</form:option>
													</form:select>
												</td>
												<c:if test="${fn:length(systemList) > 1}">
								    			<td>
													<form:select path="searchSysId" onchange="fn_search();return false;">
														<form:option value="">- 선택 -</form:option>
														<c:forEach var="systemList" items="${systemList}" varStatus="status">
															<form:option value="${systemList.sysId}"><c:out value="${systemList.sysNm}"/></form:option>
														</c:forEach>
													</form:select>
												</td>
												</c:if>			
												<td>
													<form:select path="searchCondition">
														<form:option value="">- 선택 -</form:option>
														<form:option value="1">게시판아이디</form:option>
														<form:option value="2">게시판명</form:option>
													</form:select>
												</td>
												<td>
													<form:input path="searchKeyword" cssClass="wi100" maxlength="35"/>
												</td>
								    			<td><img src="/images/button/0208.png" alt="<spring:message code='button.search'/>" title="<spring:message code='button.search'/>" style="cursor:hand;" onclick="fn_search()"></td>
								    			<td><a href="<c:url value="/gps/adm/bbs/selectBbsInfoList.do"><c:param name="dbTname" value="${bbsSearchVO.dbTname}"/></c:url>"><img src="/images/button/0318.png" alt="<spring:message code='button.initialization'/>" title="<spring:message code='button.initialization'/>"></a></td>
								    			<td><img src="/images/button/0613.png" alt="<spring:message code="button.exceldownload" />" title="<spring:message code="button.exceldownload" />" style="cursor:hand;" onclick="fn_excel_download();return false;" onkeydown="this.onclick();return false;"></td>
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
									<table class="datalist01" summary="게시판관리">
									<caption>게시판리스트</caption>
									<colgroup>
										<col class="column1" />
										<col class="column3" />
										<col class="column3" />
										<col class="column3" />
										<col class="column3" />
										<col class="column3" />
										<col class="column3" />
										<col class="column3" />
									</colgroup>
									<thead>
									<tr>
										<th scope="col">번호</th>
										<th scope="col">구분</th>
										<th scope="col">게시판아이디</th>
										<th scope="col">게시판명</th>
										<th scope="col">게시판관리자</th>
										<th scope="col">게시글수</th>
										<th scope="col">등록일</th>
										<th scope="col" class="end">작성자</th>
									</tr>
									</thead>
									<tbody>
										<c:forEach var="list" items="${bbsInfoList}" varStatus="status">
											<tr>
												<td class="column1"><c:out value="${list.rowNo}"/></td> 
												<td class="column3"><c:out value="${list.sysNm}"/></td>
												<td class="column3">
													<a href="<c:url value='/gps/adm/bbs/modifyBbsInfoPopupTab.do'><c:param name="bbsId" value="${list.bbsId}"/></c:url>" target="_blank" onclick="fn_modifyBbsInfoPopupTab('<c:out value="${list.bbsId}"/>');return false;" onkeydown="this.onclick();return false;">
														<c:out value="${list.bbsId}"/>
													</a>
												</td>
												<td class="column3">
													<a href="<c:url value="/gps/bbs/selectBbsList.do">
															<c:param name="menuId" value="${list.menuId}"/>
															<c:param name="leftMenuId" value="${list.leftMenuId}"/>
															<c:param name="bbsId" value="${list.bbsId}"/>
															</c:url>" 
														target="_blank" 
														onclick="fn_subpage('<c:out value="${list.menuId}"/>','<c:out value="${list.leftMenuId}"/>','<c:out value="${list.bbsId}"/>');return false;" 
														onkeypress="this.onclick();return false;">
														<c:out value="${list.bbsNm}"/>
													</a>
												</td> 
												<td class="column3"><c:out value="${list.bbsMngrNm}"/></td>
												<td class="column3"><c:out value="${list.bbsTotCnt}"/></td>
												<td class="column3"><c:out value="${list.registDt}" /></td>
												<td class="column3"><c:out value="${list.registerId}" /></td>
											</tr>
										 </c:forEach>
										<c:if test="${fn:length(bbsInfoList) == 0}">
											<tr> 
												<td align="center" colspan="8">
													<spring:message code="common.search.nodata.msg" />
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
										</td>
									</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table class="rbuttonarea">
									<tr>
										<td class="rbtn">
											<table class="inner_btn" align="right">
												<tr>
													<td><img src="/images/button/0202.png" alt="<spring:message code='button.create'/>" title="<spring:message code='button.create'/>" style="cursor:hand;" onclick="fn_registerPopup();return false;" onkeydown="this.onclick();return false;"></td>
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
<!-- ui-layout-center end  -->
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>