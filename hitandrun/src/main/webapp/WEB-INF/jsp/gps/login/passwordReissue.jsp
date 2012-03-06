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
<title>나라통계포털-비밀번호 재발급</title>
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

function fn_passwordReissue() {
   	JQ_request("gpsLoginVO", "<c:url value='/gps/login/userConfirm.do'/>");
}
</script>
</head>
<body>
<div class="popupContents">
	<div class="popupHeader">
		<h3></h3>
		<p class="close_btn"><a href="#LINK" onclick="window.close();return false;" onkeypress="this.onclick();return false;"><img src="/images/gps/cmm/icon/x.gif" alt="닫기" title="닫기"/></a></p>
	</div>
	
	<h3><img src="/images/gps/title/002.gif" alt="비밀번호재발급" title="비밀번호재발급"/></h3>
	<!--비밀번호찾기 테이블 시작-->
	<form:form commandName="gpsLoginVO" name="passwordReissueForm" method="post">
	<input type="submit" class="hidden" />
	<div class="popupCn">
		<div class="popupInnerCn">
			<p class="popupRule">회원가입시 등록한 아이디, 성명, 이메일을 입력하세요.</p>
			<div class="popupPwsearch">
				<table class="pwSearch" summary="비밀번호변경">
				<caption>비밀번호변경</caption>
				<tr>
					<td class="text">아이디</td>
					<td><form:input cssClass="validate[required text-input" id="userId" title="아이디" path="userId" maxlength="15" /></td>
					<td />
				</tr>
				<tr>
					<td class="text">성명</td>
					<td><form:input cssClass="validate[optional,custom[onlyKorEng]],length[0,50] text-input" id="nm" path="nm" maxlength="20" title="성명" /></td>
					<td />
				</tr>
				<tr>
					<td class="text">이메일</td>
					<td><form:input cssClass="validate[required,custom[email]] text-input" id="email" path="email" title="이메일" maxlength="30" /></td>
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
						<td><a onclick="fn_passwordReissue()"><img src="/images/gps/contents/login/bt_pw_on.gif" alt="비밀번호찾기" title="비밀번호찾기" /></a></td>
						<td><a href="#LINK" onclick="window.close();return false;" onkeypress="this.onclick();return false;"><img src="/images/gps/contents/member/bt_cancel_off.gif" alt="취소" title="취소" /></a></td>
					</tr>
					</table>
				</td>
			</tr>
			</table>
		</div>
	</div>
	</form:form>
	<!--비밀번호찾기 테이블 끝-->
</div>
<div class="popupBottom"></div>
</body>
</html>