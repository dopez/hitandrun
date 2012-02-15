<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : egovSampleList2.jsp
  * @Description : Sample List 화면
  * @Modification Information
  * 
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2009.02.01            최초 생성
  *
  * author 실행환경 개발팀
  * since 2009.02.01
  *  
  * Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<!--
****************************************************************************************************
* @source      : template.jsp
* @description : 샘플  JSP
*                   
****************************************************************************************************
* DATE         AUTHOR    DESCRIPTION
*===================================================================================================
* 2011-04-18   이진우           최초작성
****************************************************************************************************
-->
<!-- import 선언 Start ==========================================================================--> 


<!-- import 선언 End==========================================================================--> 
<!-- 선행 로직  Start ==================================================================================--> 
<%


%>
<!-- 선행 로직  End ==================================================================================--> 


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- Header INCLUDE Start=========================================================================--> 
<%@include file="/include/common/header.jsp" %> 
<!-- Header INCLUDE End=========================================================================--> 
<!-- CSS Start ======================================================================================-->

<!-- CSS End ======================================================================================-->
<!-- 자바 스크립트  Start ==================================================================================-->
 <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/sample.css'/>"/>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
/* 글 수정 화면 function */
function fn_egov_select(id) {
	document.listForm.selectedId.value = id;
   	document.listForm.action = "<c:url value='/sample/updateSampleView2.do'/>";
   	document.listForm.submit();		
}

/* 글 등록 화면 function */
function fn_egov_addView() {
   	document.listForm.action = "<c:url value='/sample/addSampleView2.do'/>";
   	document.listForm.submit();		
}

/* 글 목록 화면 function */
function fn_egov_selectList() {
	document.listForm.action = "<c:url value='/sample/egovSampleList2.do'/>";
   	document.listForm.submit();
}

/* pagination 페이지 링크 function */
function fn_egov_link_page(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/sample/egovSampleList2.do'/>";
   	document.listForm.submit();
}

-->
</script>
<!-- 자바 스크립트  End ==================================================================================-->
</head>
<body>
<div id="optional-container">
	<!-- 상단 메인 메뉴 영역  Start ==================================================================================-->
		<jsp:include page="/include/top.jsp" />
	<!-- 상단 메인 메뉴 영역  End ==================================================================================-->
	<!-- 좌측 메뉴 영역  Start ==================================================================================-->
		<jsp:include page="/include/left.jsp" />
	<!-- 좌측 메뉴 영역   End==================================================================================-->
	<div id="contents_div" class="ui-layout-center">
	<!-- 컨텐츠 영역 Start  ==================================================================================-->
	
		
		
		
	<form:form commandName="searchVO" name="listForm" method="post">
<input type="hidden" name="selectedId" />
<div id="content_pop">
	<!-- 타이틀 -->
	<div id="title">
		<ul>
			<li><img src="<c:url value='/images/egovframework/rte/title_dot.gif'/>"> 카테고리 목록 </li>
		</ul>
	</div>
	<!-- // 타이틀 -->
	<div id="search">
		<ul>
		<li>
			<form:select path="searchCondition" cssClass="use">
				<form:option value="1" label="Name" />
				<form:option value="0" label="ID" />
			</form:select>
		</li>
		<li><form:input path="searchKeyword" cssClass="text"/></li>
		<li><span class="btn_blue_l"><a href="javascript:fn_egov_selectList();"><spring:message code="button.search" /></a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;"></span></li></ul>		
	</div>
	<!-- List -->
	<div id="table">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<colgroup>
				<col width="40">				
				<col width="100">
				<col width="150">
				<col width="80">
				<col width="">
				<col width="60">
			</colgroup>		  
			<tr>
				<th align="center">No</th>
				<th align="center">카테고리ID</th>
				<th align="center">카테고리명</th>
				<th align="center">사용여부</th>
				<th align="center">Description</th>
				<th align="center">등록자</th>
			</tr>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			<tr>
				<td align="center" class="listtd"><c:out value="${status.count}"/></td>
				<td align="center" class="listtd"><a href="javascript:fn_egov_select('<c:out value="${result.id}"/>')"><c:out value="${result.id}"/></a></td>
				<td align="left" class="listtd"><c:out value="${result.name}"/>&nbsp;</td>
				<td align="center" class="listtd"><c:out value="${result.useYn}"/>&nbsp;</td>
				<td align="center" class="listtd"><c:out value="${result.description}"/>&nbsp;</td>
				<td align="center" class="listtd"><c:out value="${result.regUser}"/>&nbsp;</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<!-- /List -->
	<div id="paging">
		<ui:pagination paginationInfo = "${paginationInfo}"
				   type="image"
				   jsFunction="fn_egov_link_page"
				   />
		<form:hidden path="pageIndex" />
	</div>
	<div id="sysbtn1">
		<ul>
		<div id="sysbtn"><ul>
		<li><span class="btn_blue_l"><a href="javascript:fn_egov_addView();">등록</a><img src="<c:url value='/images/egovframework/rte/btn_bg_r.gif'/>" style="margin-left:6px;"></span></li></ul>
		</div>
		</ul>
	</div>
</div>
</form:form>
		
		
	
	<!-- 컨텐츠 영역 End  ==================================================================================-->
	</div>
	<!-- Footer 영역   ==================================================================================-->
		<jsp:include page="/include/footer.jsp" />
	<!-- Footer 영역   ==================================================================================-->
</div>
</body>
</html>