<%-- 
/** 
 * outline   : 약관관리 약관목록 조회화면
 * filename : stplatList.jsp
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

JQ_setValidation('stplatManageVO');
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
function fncPageOnload(){
	//frameset controller start
	//frameset controller end
	$("#selectall").click(select_all);
}

<%--
/************************************************************************ 
함수명 : select_all                                   
설   명 : 조회된 약관에 있는 체크박스를 전체선택/전체해제 시켜주는 함수       
인   자 : 없음        
사용법 : select_all()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

************************************************************************/
--%>
function select_all(){
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
	JQ_request("stplatManageVO", "<c:url value='/gps/adm/stplat/selectStplatList.do'/>");
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
   	JQ_request("stplatManageVO", "<c:url value='/gps/adm/stplat/selectStplatList.do'/>", "stplatManageVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_regist                                   
설   명 : 약관 등록 화면으로 이동하는 함수
인   자 : 없음
사용법 : fn_regist()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
 function fn_regist(){
   	JQ_request("stplatManageVO", "<c:url value='/gps/adm/stplat/registerStplat.do'/>", "stplatManageVO");
}

 <%-- 
 /************************************************************************ 
 함수명 : fn_modify                                   
 설   명 : 팝업 상세 화면으로 이동하는 함수
 인   자 : 없음
 사용법 : fn_modify()              
 작성일 : 2011-06-17   
 작성자 : 범정부통계포털 이관형       

       date    author      note 
  ----------   -------     ------------------- 

  ************************************************************************/
  --%>
 function fn_modify(stplatId, sysId){
  	JQ_setValueObj('stplatId', stplatId); 
  	JQ_setValueObj('sysId', sysId); 
   	JQ_request("stplatManageVO", "<c:url value='/gps/adm/stplat/modifyStplat.do'/>", "stplatManageVO");
 }
</script>
<!-- page contents start -->
<div class="contents_area">
	<form:form commandName="stplatManageVO" name="listForm" method="post">
	<form:hidden path="sysId" />
	<form:hidden path="stplatId" />
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
							<td class="title">약관관리</td>
							<td class="rbtn">
								<table class="inner_btn" align="right">
								<tr>
									<td class="end"><a onclick="fn_regist()"><img src="/images/button/0202.png" alt="<spring:message code='button.create'/>" title="<spring:message code='button.create'/>"></a></td>
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
											<option value="10" <c:if test="${stplatManageVO.pageUnit eq '10'}">selected</c:if>>10</option>
											<option value="20" <c:if test="${stplatManageVO.pageUnit eq '20'}">selected</c:if>>20</option>
											<option value="30" <c:if test="${stplatManageVO.pageUnit eq '30'}">selected</c:if>>30</option>
											<option value="50" <c:if test="${stplatManageVO.pageUnit eq '50'}">selected</c:if>>50</option>
											<option value="100" <c:if test="${stplatManageVO.pageUnit eq '100'}">selected</c:if>>100</option>
										</select>
									</td>
									<td>
										<form:select path="sysIdSearch" onchange="javaScript:fn_search();">
										<form:option value="" label="- 선택 -"/>
										<form:options items="${sysId}"/>
										</form:select>
									</td>
									<td>
										<form:select path="actvtyAtSearch" onchange="javaScript:fn_search();">
										<form:option value="" label="- 선택 -"/>
										<form:option value="Y" label="사용"/>
										<form:option value="N" label="사용안함"/>
										</form:select>
									</td>
									<td>
										<form:select path="searchCondition" >
										<form:option value="" label="- 선택 -"/>
										<form:option value="1" label="약관명"/>
										<form:option value="2" label="약관내용"/>
										</form:select>
									</td>
									<td><input name="searchKeyword" type="text" size="35" value="${stplatManageVO.searchKeyword}"  maxlength="35" id="searchKeyword" ></td>
									<td><a onclick="fn_search()"><img src="/images/button/0221.png" alt="<spring:message code='button.inquire'/>" title="<spring:message code='button.inquire'/>"></a></td>
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
						<table class="datalist01" summary="약관관리">
						<caption>약관리스트</caption>
						<colgroup>
							<col class="column10" />
							<col class="column2" />
							<col class="column3" />
							<col class="column2" />
							<col class="column3" />
							<col class="column3" />
						</colgroup> 
						<thead>
						<tr>
							<th scope="col">번호</th>
							<th scope="col">시스템명</th>
							<th scope="col">구분</th>
							<th scope="col">약관명</th>
							<th scope="col">사용여부</th>
							<th scope="col" class="end">등록</th>
						</tr>
						</thead>
						<tbody>
					<c:forEach var="resultInfo" items="${resultList}" varStatus="status">
						<tr onmouseover="this.style.backgroundColor='#fafafa'" onmouseout="this.style.backgroundColor=''">
							<td class="column10"><c:out value="${paginationInfo.totalRecordCount - ((stplatManageVO.pageIndex - 1) * stplatManageVO.pageSize + (status.count-1))}"/></td>
							<td class="column2"><c:out value="${resultInfo.sysNm}"/></td>
							<td class="column3"><c:out value="${resultInfo.stplatSeNm}"/></td>
							<td class="column2"><a onclick="javascript:fn_modify('${resultInfo.stplatId}','${resultInfo.sysId}');"><c:out value="${resultInfo.stplatNm}"/></a></td>
							<td class="column3"><c:if test="${resultInfo.stplatUseSe eq 'Y'}"><img src="/images/gps/cmm/icon/o.gif"></c:if><c:if test="${resultInfo.stplatUseSe eq 'N'}"><img src="/images/gps/cmm/icon/x.gif"></c:if></td>
							<td class="column3"><c:out value="${resultInfo.registerId}"/></td>
						</tr>
					</c:forEach>
					<c:if test="${fn:length(resultList) eq 0}">
						<tr> 
							<td class="blank" colspan="6">
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
								<input name="pageIndex" type="hidden" value="<c:out value='${stplatManageVO.pageIndex}'/>"/>
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