<%--
 /**
  * @Class Name  : ZipSearchList.jsp
  * @Description : ZipSearchList 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2011.08.01   이진우              최초 생성
  *
  *  @author 범정부통계포털 이진우 
  *  @since 2011.08.01
  *  @version 1.0
  *  @see
  *  
  */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="go.narastat.common.util.StringUtil"%>
<%-- 공통 taglib 선언 Start	================================================--%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<%-- 공통 taglib 선언 End	====================================================--%>
<%
String zipSe = request.getParameter("zipSe");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko">
<title>나라통계포털-우편번호찾기</title>
<head>
<%-- 공통 CSS Start	========================================================--%>
<link rel="stylesheet" type="text/css" href="/css/gps/cmm/default.css"></link>
<link rel="stylesheet" type="text/css" href="/css/gps/cmm/table.css"></link>
<link rel="stylesheet" type="text/css" href="/css/gps/cmm/layer.css"></link>
<link rel="stylesheet" type="text/css" href="/css/common/jquery/validationEngine.jquery.css" media="screen" title="no title" charset="utf-8"/>
<%-- 공통 CSS End	========================================================--%>

<%-- 공통 자바스크립트  Start ==============================================--%>
<script type="text/javascript" src="/js/common/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/common/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/js/common/jquery/jquery.ui.core.js"></script>
<script type="text/javascript" src="/js/common/jquery/jquery.ui.widget.js"></script>
<script type="text/javascript" src="/js/common/jquery/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/js/common/jquery/jquery.validationEngine-ko.js"></script>
<script type="text/javascript" src="/js/common/jquery/jquery.validationEngine.js"></script>
<script type="text/javascript" src="/js/common/jquery/jquery.popupWindow.js"></script>
<script type="text/javascript" src="/js/common/jquery/NaraJQuery.js"></script>
<script type="text/javascript" src="/js/common/NaraCommon.js"></script>
<script type="text/javascript" src="/js/common/fms/EgovMultiFile.js"></script>
<script type="text/javascript" src="/js/gps/gpsCommon.js"></script>
<%-- 공통 자바스크립트  End 	============================================--%>
<%-- Header End ==========================================================--%>

<%-- javascript start ==============================================--%>
<script type="text/javaScript" language="JavaScript">
<!--
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_pageview(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/gps/user/ZipSearchList.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 조회 처리 
 ******************************************************** */
function fn_searchZip(){
	document.listForm.pageIndex.value = 1;
   	document.listForm.submit();
}
/* ********************************************************
 * 결과 우편번호,주소 반환 
 ******************************************************** */
function fn_egov_return_Zip(zip,addr){
	var zipSe=<%=StringUtil.unScript(zipSe)%>;
	
	var vZip     = zip.substring(0,3)+"-"+zip.substring(3,6);
	var sAddr    = addr.replace("/^\s+|\s+$/g","");

	if(zipSe=='1'){
		$("#userRegisterVO input[id=zip]", window.opener.document).val(vZip);
		$("#userRegisterVO input[id=addrCn]", window.opener.document).val(sAddr);
	}else if(zipSe=='2'){
		$("#userRegisterVO input[id=wrcZip]", window.opener.document).val(vZip);
		$("#userRegisterVO input[id=wrcAdres1]", window.opener.document).val(sAddr);
	}
	window.close();
}	
//-->
</script>
</head>
<body>
<div class="popupContents">
	<div class="popupHeader">
		<h3></h3>
		<p class="close_btn"><a href="javascript:window.close();"><img src="/images/gps/cmm/icon/x.gif" onclick="javascript:fnClose();" onkeypress="javascript:fnClose();" alt="닫기" /></a></p>
	</div>
	
	<h3><img src="/images/gps/title/003.gif" alt="우편번호검색" title="우편번호검색"/></h3>
	<!-- page contents start -->
	<form name="listForm" action="<c:url value='/gps/user/ZipSearchList.do'/>" onsubmit="fn_searchZip();" method="post">
 	<input name="searchCondition" type="hidden" size="35" value="4"/> 
 	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
 	<input name="zipSe" type="hidden" value="<%=StringUtil.unScript(zipSe)%>"/>
	<div class="popupCn">
		<div class="popupInnerCn">
			<p class="popupRule">찾으시는 주소의 읍,면 또는 동 이름을 입력하세요</p>
			<fieldset>
			<legend>우편번호찾기 찾기</legend>
			<div class="popupSearch">
				<ul>
					<li class="text"> 읍,면 또는 동 이름</li>
					<li><input name="searchKeyword" type="text" value="${searchVO.searchKeyword}" maxlength="20" title="동명"/></li>
					<li><img src="/images/button/0221.png" alt="조회" title="조회" onclick="fn_searchZip()" class="noline course"/></li>
				</ul>
			</div>		
			</fieldset>
		</div>
		<div class="popupResult">
			<table class="searchResult" summary="우편번호리스트">
			<caption>우편번호리스트</caption>
			<thead>
			<tr>
				<th scope="col">우편번호</th>
				<th scope="col" class="end">주소</th>
			</tr>
			</thead>
			<tbody>
		<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
			<tr style="cursor:pointer;" onclick="javascript:fn_egov_return_Zip( '${resultInfo.zip}', '${resultInfo.ctprvnNm} ${resultInfo.signguNm} ${resultInfo.emdNm} ${resultInfo.liBuldNm}');">
				<td class="column1"><c:out value='${fn:substring(resultInfo.zip, 0,3)}'/>-<c:out value='${fn:substring(resultInfo.zip, 3,6)}'/></td>
				<td class="column2">${resultInfo.ctprvnNm} ${resultInfo.signguNm} ${resultInfo.emdNm} ${resultInfo.liBuldNm} ${resultInfo.lnbrDongHo}</td>
			</tr>
			<c:set var="totalCount" value="${totalCount + resultInfo.userCnt}"/>
		</c:forEach>
		<c:if test="${fn:length(resultList) == 0}">
			<tr> 
				<td class="blank" colspan="2">
					<spring:message code="common.nodata.msg" />
				</td>
			</tr>
		</c:if>
		<c:if test="${fn:length(resultList) != 0}">
			<tr>
				<td colspan="2" align="center">
					<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_pageview"/>
				</td>
			</tr>
		</c:if>
			</tbody>
			</table>
		</div>
	</div>
	</form>
	<div class="popupFooter">
		<div class="popupButton">
			<a href="javascript:window.close();"><img src="/images/button/0205.png" alt="닫기" title="닫기"/></a>
		</div>
	</div>
</div>
<div class="popupBottom"></div>
</body>
</html>