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
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<%-- 공통 taglib 선언 end	=====================================================--%>

<%-- header start	==========================================================--%>
<jsp:include page="/gps/cmm/left.do" flush="false"/> 
<%-- header end	==============================================================--%>
<%-- 선행 로직 start	======================================================--%>
<script language='javascript' src='/gpkisecureweb/var.js'></script>
<script language='javascript' src='/gpkisecureweb/GPKIFunc.js'></script>
<script language='javascript' src='/gpkisecureweb/object.js'></script>
<%-- page javascript start	==================================================--%>
<script type="text/javascript" language="javascript">
	JQ_setValidation('gpsLoginVO');
	JQ_onload();
	
	function fncPageOnload() {
		<c:if test="${!empty message}">
			alert('<c:out value = "${message}"/>');
		</c:if>
		topStatMenuImage(0);
		subTopStatMenuImage(0);
		leftStatMenuImage(0);
	}
	
	<%-- 
	/************************************************************************ 
	fnc name : actionLogin                                   
	outline : 로그인처리 함수      
	parameter : 없음        
	directions : actionLogin()              
	since : 2011-06-10   
	author : 통계포털  황기연       
	
	    date      author             note  
	 ----------   -------     ------------------- 
	 2011.06.10    황기연                       최초 생성                 
	************************************************************************/ 
	--%>
	function fn_login() {
		JQ_request("gpsLoginVO", "<c:url value='/gps/login/actionLogin.do'/>");
	}

	<%-- 
	/************************************************************************ 
	fnc name : fn_gpkiLogin                     
	outline : 인증서로그인
	parameter : 없음        
	directions : fn_gpkiLogin              
	since : 2011-08-01   
	author : 통계포털  황기연       

	    date      author             note  
	 ----------   -------     ------------------- 
	 2011.10.04    황기연                       최초 생성                 
	************************************************************************/ 
	--%>
	function fn_gpkiLogin(){
		<c:if test="${gpkiLogin}">
		Login(popForm);
		</c:if>
	}
	
	
</script>
<noscript>현재 브라우저에서 자바스크립트 처리를 하지 못합니다.</noscript>
<%-- page javascript end	==================================================--%>
<!-- subpagebody_contents start -->
<div id="subpagebody_contents">
	<!-- contents start -->
	<form name="popForm" method="post" action="/gps/login/gpkiLogin.do">
	<input type="submit" class="hidden" />
	<input type="hidden" name="challenge" value="<c:out value="${challenge}" escapeXml="true"/>"/>
	<input type="hidden" name="returnUrl" value="<c:out value="${gpsLoginVO.returnUrl}" escapeXml="true"/>"/>
	<input type="hidden" name="menuId" value="<c:out value="${gpsLoginVO.menuId}" escapeXml="true"/>"/>
	<input type="hidden" name="leftMenuId" value="<c:out value="${gpsLoginVO.leftMenuId}" escapeXml="true"/>"/>
	<input type="hidden" name="bbsId" value="<c:out value="${gpsLoginVO.bbsId}" escapeXml="true"/>"/>
	<input type="hidden" name="bbsSn" value="<c:out value="${gpsLoginVO.bbsSn}" escapeXml="true"/>"/>
	</form>
	<form:form commandName="gpsLoginVO" method="post">
	<input type="submit" class="hidden" />
	<form:hidden path="returnUrl"/>
	<form:hidden path="bbsId"/>
	<form:hidden path="bbsSn"/>
	<form:hidden path="menuId"/>
	<form:hidden path="leftMenuId"/>
	<div id="login_contents">
		<!-- login contetns start -->
		<div class="login">
			<fieldset>
			<legend>로그인</legend>
			<dl>
				<dt>로그인정보입력</dt>
				<dd><form:input path="userId" maxlength="25" tabindex="1" cssClass="validate[required,length[1,20]]" title="아이디 입력"/></dd>
				<dd><form:password path="password" maxlength="20" tabindex="2" cssClass="validate[required,length[1,20]]" title="비밀번호 입력"/></dd>
				<dd class="check">
					<form:checkbox path="saveId" value="Y" title="아이디저장" tabindex="5"/><label for="saveId" id="saveId">아이디 저장</label>
				</dd>
				<dd class="btn">
					<a href="#LINK" onclick="fn_login();return false;" onkeypress="this.onclick();return false;" tabindex="3"><img src="/images/gps/contents/login/bt_login_off.gif" alt="로그인버튼" title="로그인버튼"/></a>
				</dd>
			<c:if test="${gpkiLogin}">
				<dd class="gpkibtn">
					<a href="#LINK" onclick="fn_gpkiLogin();return false;" onkeypress="this.onclick();return false;" tabindex="4"><img src="/images/gps/contents/login/bt_login2_off.gif" alt="인증서로그인버튼" title="인증서로그인버튼"/></a>
				</dd>
			</c:if>
			</dl>
			</fieldset>
		</div>
		<!-- login contetns end -->
		
	</div>
	<div class="loginbtn">
		<a href="<c:url value='/gps/user/actionRegistUser.do?menuId=0010001100122125&leftMenuId=0010001100122'/>"><img src="/images/gps/contents/login/bt_member_on.gif"alt="회원가입" title="회원가입"></a>
		<a href="<c:url value="/gps/login/idSearch.do"/>" target="_blank" onclick="window.open(this.href,'idSearch','width=400,height=400');return false;" onkeypress="this.onclick();return false;"><img src="/images/gps/contents/login/bt_id_off.gif" alt="아이디찾기" title="아이디찾기" class="course"/></a>
		<a href="<c:url value="/gps/login/passwordReissue.do"/>" target="_blank" onclick="window.open(this.href,'idSearch','width=400,height=400');return false;" onkeypress="this.onclick();return false;"><img src="/images/gps/contents/login/bt_pw_off.gif" alt="비밀번호재발급" title="비밀번호재발급" class="course"/></a>
	</div>
	</form:form>
	<!-- contents end -->
</div>
<!-- login_contents contetns end -->
<jsp:include page="/gps/cmm/footer.do" flush="false"/>
