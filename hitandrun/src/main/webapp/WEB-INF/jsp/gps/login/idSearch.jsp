<%--
********************************************************************************
* @source      : header.jsp
* @description : 헤더  JSP - 세션/사용자/권한 처리
*                   
********************************************************************************
* DATE         AUTHOR    DESCRIPTION
*===============================================================================
* 2011-04-18   이진우           최초작성
********************************************************************************
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%-- 공통 taglib 선언 Start	================================================--%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<%-- 공통 taglib 선언 End	====================================================--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko">
<title>나라통계포털-아이디찾기</title>
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
<%-- Header End ========================================================--%>
<%-- page javascript start	============================================--%>
<script type="text/javascript" language="javascript">
	JQ_setValidation('gpsLoginVO');
	
	<c:if test="${!empty message}">
		alert('<c:out value = "${message}"/>');
	</c:if>

	function fn_idsearch() {
	   	JQ_request("gpsLoginVO", "<c:url value='/gps/login/selectIdSearch.do'/>");
	}

	function fn_passwordReissue(){
	   	JQ_request("gpsLoginVO", "<c:url value='/gps/login/passwordReissue.do'/>");
	}
</script>
</head>
<body>

<!--아이디찾기 테이블 시작-->
<form:form commandName="gpsLoginVO" name="idSearchForm" method="post">
<input type="submit" class="hidden"/>
<div class="popupContents">
	<div class="popupHeader">
		<!-- <h3>아이디찾기</h3> -->
		<p class="close_btn"><a href="#LINK" onclick="window.close();return false;" onkeypress="this.onclick();return false;"><img src="/images/gps/cmm/icon/x.gif" alt="닫기" /></a></p>
	</div>
	<c:choose>
		<c:when test="${!empty gpsLoginVO.userId}">
			<h3><img src="/images/gps/title/008.gif" alt="아이디찾기 결과" title="아이디찾기 결과" /></h3>
		</c:when>
		<c:otherwise>
			<h3><img src="/images/gps/title/001.gif" alt="아이디찾기" title="아이디찾기"/></h3>
		</c:otherwise>
	</c:choose>
	

	<div class="popupCn">
		<div class="popupInnerCn">
			<p class="popupRule">회원가입시 성명, 이메일을 입력하세요.</p>
			<div class="popupIdSearch">
				<table class="idSearch" summary="아이디찾기">
				<caption>아이디찾기</caption>
				<tr>
					<td class="text">성명</td>
					<td><form:input cssClass="validate[optional,custom[onlyKorEng]],length[0,50] text-input" id="nm" path="nm" maxlength="20" title="성명" tabindex="1"/></td>
				</tr>
				<tr>
					<td class="text">이메일</td>
					<td><form:input cssClass="validate[required,custom[email]] text-input" id="email" path="email" maxlength="30" title="email" tabindex="2"/></td>
				</tr>
				</table>
			</div>
		</div>
		<div class="popupFooter">
			<table class="fooerBtn">
			<tr>
				<td align="center">
					<table class="cbuttonarea">
					<tr>
						<td><a href="#LINK" onclick="fn_idsearch();return false;" tabindex="3"><img src="/images/gps/contents/login/bt_id_on.gif" alt="아이디찾기" title="아이디찾기"/></a></td>
						<td><a href="#LINK" onclick="window.close();return false;" onkeypress="this.onclick();return false;"><img src="/images/gps/contents/member/bt_cancel_off.gif" alt="취소" title="취소" /></a></td>
					</tr>
					</table>
				</td>
			</tr>
			</table>
		</div>
		<c:if test="${!empty gpsLoginVO.userId}">
			<div class="popupResult">
				<table class="searchResult" summary="<c:out value="${gpsLoginVO.userId}"/> 의 검색결과입니다.">
				<caption><c:out value="${gpsLoginVO.userId}"/> 검색결과 입니다.</caption>
				<tr>
					<td class="resultKey"><c:out value="${gpsLoginVO.nm}"/></td>
					<td>님의 ID는</td>
					<td class="resultKey"><c:out value="${gpsLoginVO.userId}"/></td>
					<td>입니다.</td>
				</tr>
				</table>
			</div>
		</c:if>
	</div>
	<!--아이디찾기 테이블 끝-->
</div>
<div class="popupBottom"></div>
</form:form>
</body>
</html>