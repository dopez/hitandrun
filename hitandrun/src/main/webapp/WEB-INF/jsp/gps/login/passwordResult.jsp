<%
 /**
  * @Class Name : EgovIdPasswordResult.jsp
  * @Description : 아이디/비밀번호 찾기 결과화면
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.03.17    박지욱          최초 생성
  *
  *  @author 공통서비스 개발팀 박지욱
  *  @since 2009.03.17
  *  @version 1.0
  *  @see
  *  
  */
%>
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
/* ********************************************************
 * 뒤로 처리 함수
 ******************************************************** */
function fnBack(){
	document.backForm.action = "<c:url value='/gps/login/passwordReissue.do'/>";
	document.backForm.submit();
}
</script>
</head>
<body>
<div class="popupContents">
	<div class="popupHeader">
		<h3></h3>
		<p class="close_btn"><a href="javascript:window.close();"><img src="/images/gps/cmm/icon/x.gif" alt="닫기"></a></p>
	</div>
	
	<h3><img src="/images/gps/title/009.gif" alt="비밀번호 재발급 완료" title="비밀번호 재발급 완료"></h3>
	<form name="backForm"/>
	<div class="popupCn">
		<div class="popupResult">
			<table class="searchResult" summary="${gpsLoginVO.nm}님의 비밀번호 재발급 완료">
			<caption>${gpsLoginVO.nm}님의 비밀번호 재발급 완료</caption>
			<tr>
				<td class="resultKey"><c:out value="${gpsLoginVO.nm}"/></td>
				<td>님의 비밀번호가 재발급 되었습니다 .</td>
			</tr>
			</table>
		</div>
		<div class="popupFooter">
			<table class="fooerBtn">
			<tr>
				<td align="center">
					<table class="rbuttonarea">
					<tr>
						<td><a onclick="fnBack()"><img src="/images/button/0223.png" alt="뒤로" title="뒤로"/></a></td>
						<td><a onclick="window.close();"><img src="/images/button/0205.png" alt="<spring:message code='button.close'/>" title="<spring:message code='button.close'/>"/></a></td>
					</tr>
					</table>
				</td>
			</tr>
			</table>
		</div>
	</div>
	</form>
</div>
<div class="popupBottom"></div>
</body>
</html>