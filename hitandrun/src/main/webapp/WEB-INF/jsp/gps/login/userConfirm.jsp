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

//메시징처리
<c:if test="${!empty message}">
	alert('<c:out value = "${message}"/>');
</c:if>
	function fn_idsearch() {
		if (document.userConfirmForm.password.value == document.userConfirmForm.passwordConfirm.value) {
		   	JQ_request("gpsLoginVO", "<c:url value='/gps/login/passwdReissue.do'/>");
		} else {
			alert('입력하신 비밀번호가 일치하지 않습니다.');
		}
	}

</script>
</head>
<body>
<div class="popupContents">
	<div class="popupHeader">
		<h3></h3>
		<p class="close_btn"><a href="#LINK" onclick="window.close();return false;" onkeypress="this.onclick();return false;"><img src="/images/gps/cmm/icon/x.gif" title="닫기" alt="닫기"/></a></p>
	</div>
	
	<h3><img src="/images/gps/title/006.gif" alt="비밀번호재발급" title="비밀번호재발급"/></h3>
	<form:form commandName="gpsLoginVO" name="userConfirmForm"  method="post">
	<form:hidden path="nm"/>
	<form:hidden path="userId"/>
	<div class="popupCn">
		<div class="popupInnerCn">
			<p class="popupRule">5~12자의 <font class="fb f_red">영문 소문자, 숫자와 특수기호</font> 사용 가능.</p>
			<div class="popupPwConfirm">
				<table class="pwConfirm" summary="아이디/비밀번호 검색">
				<tr>
					<td class="text">신규 비밀번호</td>
					<td><input type="password" id="password" name="password" class="validate[required,length[5,12]] text-input wi150"/></td>
				</tr>
				<tr>
					<td class="text">신규  비밀번호 확인</td>
					<td><input type="password" id="passwordConfirm" name="passwordConfirm" class="validate[required,confirm[password]] text-input wi150"/></td>
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
						<td><a onclick="fn_idsearch()"><img src="/images/gps/contents/login/bt_pw_off.gif" alt="비밀번호 재발급" title="비밀번호 재발급"/></a></td>
						<td><a onclick="window.close();"><img src="/images/button/0205.png" alt="<spring:message code='button.close'/>" title="<spring:message code='button.close'/>"/></a></td>
					</tr>
					</table>
				</td>
			</tr>
			</table>
		</div>
	</div>
    </form:form>
</div>
<div class="popupBottom"></div>
</body>
</html>