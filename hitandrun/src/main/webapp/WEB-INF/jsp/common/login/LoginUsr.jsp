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

<%-- 공통 taglib 선언 start	=================================================--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%-- 공통 taglib 선언 end	=====================================================--%>

<%-- header start	==========================================================--%>
<jsp:include page="/gps/cmm/left.do" flush="false"/> 
<%-- header end	==============================================================--%>

<%-- import 선언 start	======================================================--%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="java.util.*" %>
<%-- import 선언 end	======================================================--%>

<%-- 선행 로직 start	======================================================--%>
<c:if test="${!empty message}">
	<script type="text/javascript">
		alert('<c:out value="${message}"/>');
	</script>
</c:if>
<%-- 선행 로직 end	==========================================================--%>

<%-- page javascript start	==================================================--%>
<script type="text/javascript">
	function actionLogin() {
	    if (document.loginForm.id.value =="") {
	        alert("아이디를 입력하세요");
	    } else if (document.loginForm.password.value =="") {
	        alert("비밀번호를 입력하세요");
	    } else {
	        document.loginForm.action="<c:url value='/common/login/actionLogin.do'/>";
	        document.loginForm.submit();
	    }
	}
</script>
<noscript>현재 브라우저에서 자바스크립트 처리를 하지 못합니다.</noscript>
<%-- page javascript end	==================================================--%>
<div class="ui-layout-content">
	<!-- contents_area start -->
	<div class="center_contents_area">
		<!--일반로그인  시작-->
		<!-- contents page_title start -->
		<div class="page_title">
			<div class="location">
				<ul>
					<li class="home"><a href="#"><img src="/images/common/location_home.gif" alt="처음으로" title="처음으로"></a></li>
					<li class="path"><a href="#" title="처음으로">로그인</a></li>
					<li class="path">로그인</li>
				</ul>
			</div>		
			<span>로그인</span>
		</div>
		<!-- contents page_title end -->

		<!-- login_contents contetns start -->
		<form:form commandName="loginVO" name="loginForm"  method="post">
		<input type="hidden" name="message" value="${message}">
		<div id="login_contents">
			<!-- login contetns start -->
			<div class="login">
				<div class="text_info">
					<span>아이디와 비밀번호를 입력하세요.</span>
				</div>

				<fieldset>
				<legend>dd</legend>
				<dl>
					<dt>aa</dt>
					<dd><input type="text" id="id" name="id" maxlength="25" tabindex="1" title="아이디 입력" /></dd>
					<dd><input type="password" id="password" name="password" maxlength="20" tabindex="3" title="비밀번호 입력" /></dd>
					<dd class="check">
						<input type="checkbox" id="saveid" name="saveid" /><label for="saveid" id="" tabindex="4">아이디 저장</label>
					</dd>
					<dd class="btn">
						<input onclick="actionLogin();" type="submit" tabindex="6" title="로그인버튼" value="로그인"/>
					</dd>
				</dl>
				</fieldset>
			</div>
			<!-- login contetns end -->
		</div>
		</form:form>
		<!-- login_contents contetns end -->
	</div>
	<!-- contents_area end -->
</div>
<jsp:include page="/gps/cmm/footer.do" flush="false"/>
