<%-- 
/** 
 * outline   : 만족도관리 만족도목록 조회화면
 * filename : csnstList.jsp
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
	JQ_setValidation('csnstManageVO');
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
	</c:if>
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
	JQ_request("csnstManageVO", "<c:url value='/gps/adm/menu/selectCsnstList.do'/>");
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
   	JQ_request("csnstManageVO", "<c:url value='/gps/adm/menu/selectCsnstList.do'/>", "csnstManageVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_regist                                   
설   명 : 만족도 등록 화면으로 이동하는 함수
인   자 : 없음
사용법 : fn_regist()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
 function fn_regist(csnstId){
	 JQ_setValueObj('csnstId', csnstId); 
	 JQ_request("csnstManageVO", "<c:url value='/gps/adm/menu/registCsnstId.do'/>", "csnstManageVO");
}

</script>
<!-- page contents start -->
<div class="contents_area">
	<form:form commandName="csnstManageVO" name="listForm" method="post">
	<form:hidden path="sysId"/>
	<form:hidden path="menuId"/>
	<form:hidden path="csnstId"/>
	<form:hidden path="csnstSn"/>
	<form:hidden path="qesitmSn"/>
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
							<td class="title">만족도관리</td>
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
											<option value="10" <c:if test="${csnstManageVO.pageUnit == '10'}">selected</c:if>>10</option>
											<option value="20" <c:if test="${csnstManageVO.pageUnit == '20'}">selected</c:if>>20</option>
											<option value="30" <c:if test="${csnstManageVO.pageUnit == '30'}">selected</c:if>>30</option>
											<option value="50" <c:if test="${csnstManageVO.pageUnit == '50'}">selected</c:if>>50</option>
											<option value="100" <c:if test="${csnstManageVO.pageUnit == '100'}">selected</c:if>>100</option>
										</select>
									</td>
									<td>
										<select name="searchSysId" onchange="javaScript:fn_search();" >
											<option value="">전체</option>
											<c:forEach var="sysInfoList" items="${sysInfo}" varStatus="status">
											<option value="<c:out value="${sysInfoList.sysId}" />" <c:if test="${csnstManageVO.searchSysId == sysInfoList.sysId}">selected</c:if> ><c:out value="${sysInfoList.sysNm}" /></option>
											</c:forEach>
										</select>
									</td>
									<td>
										<select name="searchCondition">
										<option value="">- 선택 -</option>
										<option value="1" <c:if test="${csnstManageVO.searchCondition == '1'}">selected</c:if>>만족도 조사명</option>
										<option value="2" <c:if test="${csnstManageVO.searchCondition == '2'}">selected</c:if>>만족도 조사내용</option>
										</select>						
									</td>
									<td><input name="searchKeyword" type="text" size="35" value="${csnstManageVO.searchKeyword}"  maxlength="35" id="searchKeyword" ></td>
					    			<td><img src="/images/button/0208.png" alt="<spring:message code='button.search'/>" title="<spring:message code='button.search'/>" onclick="fn_search()" style="cursor:hand"></td>
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
						<table class="datalist01" summary="만족도 조사목록">
						<caption>만족도 조사목록</caption>
						<colgroup>
							<col class="column10" />
							<col class="column3" />
							<col class="column3" />
							<col class="column2" />
							<col class="column3" />
							<col class="column3" />
							<col class="column3" />
							<col class="column3" />
						</colgroup>
						<thead>
						<tr>
							<th scope="col">번호</th>
							<th scope="col">사용등록</th>
							<th scope="col">시스템명</th>
							<th scope="col">만족도 조사 제목</th>
							<th scope="col">첨부</th>
							<th scope="col">사용여부</th>
							<th scope="col">시작일</th>
							<th scope="col" class="end">종료일</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach var="resultInfo" items="${resultList}" varStatus="status">
								<tr onmouseover="this.style.backgroundColor='#fafafa'" onmouseout="this.style.backgroundColor=''">
									<td class="column10"><c:out value="${paginationInfo.totalRecordCount - ((csnstManageVO.pageIndex - 1) * csnstManageVO.pageSize + (status.count-1))}"/></td>
									<td class="column3">
									<c:choose>
										<c:when test="${resultInfo.csnstId == csnstManageVO.csnstId}">
											[현재등록]
										</c:when>
										<c:otherwise>
											<a onclick="fn_regist(${resultInfo.csnstId});" style="cursor:hand"><img src="/images/button/0202.png" alt="<spring:message code='button.add'/>" title="<spring:message code='button.add'/>" ></a>
										</c:otherwise>
									</c:choose>
									</td>
									<td class="column3"><c:out value="${resultInfo.sysNm}"/></td>
									<td class="column2"><c:out value="${resultInfo.csnstNm}"/></td>
									<td class="column3">
										<c:import url="/cmm/fms/selectFileInfs.do">
											<c:param name="param_atchFileId" value="${resultInfo.csnstFileNm}" />
										</c:import>
									</td>
									<td class="column3"><c:if test="${resultInfo.csnstUseAt eq 'Y'}"><img src="/images/gps/cmm/icon/o.gif"></c:if><c:if test="${resultInfo.csnstUseAt eq 'N'}"><img src="/images/gps/cmm/icon/x.gif"></c:if></td>
									<td class="column3"><c:out value="${resultInfo.validBgnde}" /></td>
									<td class="column3"><c:out value="${resultInfo.validEndde}" /></td>
								</tr>
							 </c:forEach>
							<c:if test="${fn:length(resultList) == 0}">
								<tr>
									<td class="blank" colspan="10">
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
								<input name="pageIndex" type="hidden" value="<c:out value='${csnstManageVO.pageIndex}'/>"/>
							</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="rbuttonarea">
						<tr>
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