<%--
**********************************************************************************
* @source      : header.jsp
* @description : 헤더  JSP - 세션/사용자/권한 처리
*                   
**********************************************************************************
* DATE         AUTHOR    DESCRIPTION
*=================================================================================
* 2011-04-18   이진우           최초작성
**********************************************************************************
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<%-- Header Start ==========================================================--%>
<jsp:include page="/sps/cmm/header.do" flush="false"/> 
<%-- Header Start ==========================================================--%>

<!-- import 선언 Start ==========================================================================--> 

<!-- import 선언 End==========================================================================--> 



<!-- 선행 로직  Start ==================================================================================--> 
<%
	
%>
<!-- 선행 로직  End ==================================================================================--> 

<%-- 자바 스크립트  Start  ================================================--%>
<!-- frame 제어관련 스크립트 -->
<script type="text/javascript" src="/js/sps/sds/cmm/framectl.js"></script>
<%-- 자바 스크립트  End  ===================================================--%>




<link rel="stylesheet" href="/css/common/jqgrid/ui.jqgrid.css" type="text/css" >
	
	<style type="text/css">
	/* Using an 'optional-container' instead of 'body', so need body to have a 'height' */
	html, body {
		width:		100%;
		height:		100%;
		padding:	0;
		margin:		0;
		overflow:	hidden !important;
	}
	#optional-container {
		width:			100%;
		height:			100%;
		margin-top:		0;
		margin-left:	0;
	}
	.ui-layout-center { overflow: hidden; }
	</style>
<!-- 공통 CSS End ======================================================================================-->

<!--  자바 스크립트  Start ==================================================================================--> 
<script type="text/javaScript" language="javascript">
function actionLogin() {

    if (document.loginForm.userId.value =="") {
        alert("아이디를 입력하세요");
    } else if (document.loginForm.password.value =="") {
        alert("비밀번호를 입력하세요");
    } else {
        document.loginForm.action="<c:url value='/gps/login/actionLogin.do'/>";
        document.loginForm.submit();
    }
}
</script>
<!-- 자바 스크립트  End ==================================================================================-->


<!-- 상단 메뉴 영역  End ==================================================================================-->

<!--
<button onClick="removeUITheme()">Remove Theme</button> &nbsp; &nbsp;
<button onClick="myLayout.resizeAll(); myLayout.sizeContent('center');">Resize Content</button>
-->


 <!--일반로그인  시작-->
 <form:form commandName="loginVO" name="loginForm"  method="post">
 
<div class="ui-layout-north" style="text-align: center;">
	<div id="login" align="left">
	아이디:<input name="userId" type="text" value="" size="15" maxlength="20">&nbsp;
	비밀번호:<input name="password" type="password" value="" size="15" maxlength="20">&nbsp;
	<button onClick="actionLogin()">로그인</button>
	<input name="devLoginAt" type="hidden" value="Y" >&nbsp;
	
	</div>		
</div>

</form:form>
 <!--일반로그인 끝-->
<!-- 상단 메뉴 영역  End ==================================================================================-->
<%-- Footer Start =========================================================--%>
<jsp:include page="/sps/cmm/bottom.do" flush="false"/>
<%-- Footer End ===========================================================--%>
		
	