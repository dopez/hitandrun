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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="go.narastat.common.util.StringUtil"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<%
String zipSe=request.getParameter("zipSe");
%>
<%-- Header Start ==========================================================--%>

<%-- 공통 CSS Start	========================================================--%>
<link rel="stylesheet" type="text/css" href="/css/gps/adm/default.css"></link>
<link rel="stylesheet" type="text/css" href="/css/gps/adm/table.css"></link>
<link rel="stylesheet" type="text/css" href="/css/gps/adm/layer.css"></link>
<link rel="stylesheet" type="text/css" href="/css/common/jquery/jquery.ui.all.css" />
<link rel="stylesheet" type="text/css" href="/css/common/jquery/jquery.ui.theme.css" />
<link rel="stylesheet" type="text/css" href="/css/common/jquery/validationEngine.jquery.css" media="screen" title="no title" charset="utf-8"/>
<%-- 공통 CSS End	========================================================--%>

<%-- 공통 자바스크립트  Start ==============================================--%>
<script type="text/javascript" src="/js/common/jquery/jquery-latest.js"></script>
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
	document.listForm.action = "<c:url value='/gps/adm/user/ZipSearchList.do'/>";
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
		$("#userManageVO input[id=zip]", window.opener.document).val(vZip);
		$("#userManageVO input[id=addrCn]", window.opener.document).val(sAddr);
	}else if(zipSe=='2'){
		$("#userManageVO input[id=wrcZip]", window.opener.document).val(vZip);
		$("#userManageVO input[id=wrcAddrCn]", window.opener.document).val(sAddr);
	}
	window.close();
}	
//-->
</script>

<div class="ui-layout-content">
	<!-- page contents start -->
	<div class="contents_area">
		<form name="listForm" action="<c:url value='/gps/adm/user/ZipSearchList.do'/>" onsubmit="fn_searchZip();" method="post">
   		<input name="searchCondition" type="hidden" size="35" value="4" /> 
   		<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}' escapeXml="true"/>"/>
   		<input name="zipSe" type="hidden" value="<%=StringUtil.unScript(zipSe)%>"/>
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
										<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="타이틀이미지" title="타이틀이미지" /></td>
										<td class="title">우편번호찾기</td>
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
												<td class="first">동명을 입력하시오.</td>
												<td>
													<input name="searchKeyword" type="text" size="20" value="${searchVO.searchKeyword}"  maxlength="20" title="동명"/>
												</td>
												<td>
													<img src="/images/button/0221.png" alt="조회" title="조회" onclick="fn_searchZip()">
												</td>
											</tr>
											</table>
										</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr><td class="ht2"></td></tr>
							<tr>
								<td>
								<table class="datalist01" summary="우편번호리스트">
								<caption>우편번호리스트</caption>
									<colgroup>
										<col class="column1"/>
										<col class="column3"/>
									</colgroup>
									<thead>
									<tr>
										<th scope="col">우편번호</th>
										<th scope="col">주소</th>
									</tr>
									</thead>
									<tbody>
									<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
										<tr style="cursor:pointer;cursor:hand;" onclick="javascript:fn_egov_return_Zip( '${resultInfo.zip}', '${resultInfo.ctprvnNm} ${resultInfo.signguNm} ${resultInfo.emdNm} ${resultInfo.liBuldNm}');">
										<!--<tr onmouseover="this.style.backgroundColor='#fafafa'" onmouseout="this.style.backgroundColor=''">-->
											<td class="column1"><c:out value='${fn:substring(resultInfo.zip, 0,3)}'/>-<c:out value='${fn:substring(resultInfo.zip, 3,6)}'/></td>
											<td class="column2">${resultInfo.ctprvnNm} ${resultInfo.signguNm} ${resultInfo.emdNm} ${resultInfo.liBuldNm} ${resultInfo.lnbrDongHo}</td>
										</tr>
										<c:set var="totalCount" value="${totalCount + resultInfo.userCnt}"/>
									</c:forEach>
										
									<c:if test="${fn:length(resultList) == 0}">
										<tr> 
											<td class="lt_text3" colspan="2">
												<spring:message code="common.nodata.msg" />
											</td>
										</tr>
									</c:if>
									<c:if test="${fn:length(resultList) != 0}">
										<tr>
										<td colspan="2">
										<div align="center">
											<div>
												<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_egov_pageview"/>
											</div>
										</div>
										</td>
										</tr>
									</c:if>
									</tbody>
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
		</form>
	</div>
</div>
