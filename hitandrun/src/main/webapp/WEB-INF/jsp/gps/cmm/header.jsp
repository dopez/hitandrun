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
<title>::::::::::::::::통계발전의새싹 나라통계포털에 오신것을 환영합니다.::::::::::::::::</title>
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
<script type="text/javascript">
<%-- 
/************************************************************************ 
fnc name : actionLogout                               
outline : 로그아웃처리함수
parameter : 없음        
directions : actionLogout()              
since : 2011-06-10   
author : 통계포털  황기연       

    date      author             note  
 ----------   -------     ------------------- 
 2011.06.10    황기연                       최초 생성                 
************************************************************************/ 
--%>
	function actionLogout(){
		if (confirm("<spring:message code="gps.logout.confirm.msg"/>")) {
			parent.location.href="<c:url value='/gps/login/actionLogout.do'/>";
		}
	}
</script>
</head>
<body>
<!-- quick_navigation start -->
<div id="quick_navigation">
<h2>메뉴 바로가기</h2>
	<ul>
		<li><a href="#header">메인 메뉴</a></li>
		<li><a href="#container">본문 내용</a></li>
		<li><a href="#footer">서비스안내</a></li>
	</ul>
</div>
<!-- quick_navigation end -->
<div id="wrap">
	<!-- header start -->
	<div id="top_infor">
		<div class="topContent">
	<c:choose>
		<c:when test="${empty gpsSessionVO}">
			<div class="account">
				<c:set var="loginUrl" value="${serverSe == 'naraint' || serverSe == 'naradev'?'/gps/cmm/gpkiInstall.do?se=login':'/gps/login/gpsLoginUsr.do?menuId=0010001100122123&leftMenuId=0010001100122'}"/>
				<ul>
					<li><a href="/"><img src="/images/gps/menu/top/top_icon_home_off.gif" onmouseover="overImg(this);" onFocus="overImg(this);" onmouseout="outImg(this);" onBlur="outImg(this);" alt="홈" title="홈"/></a></li>
					<li class="login"><a href="<c:url value="${loginUrl}"/>"><span><spring:message code="gps.login.login"/></span></a></li>
					<li><a href="/gps/user/actionRegistUser.do?menuId=0010001100122125&leftMenuId=0010001100122"><img src="/images/gps/menu/top/top_icon_member_off.gif" onmouseover="overImg(this);" onFocus="overImg(this);" onmouseout="outImg(this);" onBlur="outImg(this);" alt="회원가입" title="회원가입"/></a></li>
					<li class="end"><a href="/gps/contents/guidance/sitemap.do?menuId=0010001100126131&leftMenuId=0010001100126"><img src="/images/gps/menu/top/top_icon_site_off.gif" onmouseover="overImg(this);" onFocus="overImg(this);" onmouseout="outImg(this);" onBlur="outImg(this);" alt="사이트맵" title="사이트맵"/></a></li>
				</ul>
			</div>
		</c:when>
		<c:otherwise>
		<c:if test="${menuManageVO.menuSkin ne 'index'}">
			<div class="sysMenu">
				<ul>
					<c:if test="${gpsSessionVO.trnsferInfo == 'U'}">
						<li><a href="/pms/index.do" target="_blank"><span>UBIS</span></a></li>
					</c:if>
					<li><a href="/pms/index.do" target="_blank"><span>통계정책</span></a></li>
					<li><a href="/sps/index.do" target="_blank"><span>통계생산</span></a></li>
					<c:choose>
						<c:when test="${serverSe == 'naraext'}">
							<c:set var="url" value="www.narastat.kr"/>
						</c:when>
						<c:when test="${serverSe == 'naraint'}">
							<c:set var="url" value="10.184.70.80"/>
						</c:when>
						<c:otherwise>
							<c:set var="url" value="10.184.70.83"/>
						</c:otherwise>
					</c:choose>
					<li><a href="http://${url}/meta/loginMeta_ins.jsp?txtID=<c:out value="${userManageVO.userId}"/>&PW=<c:out value="${userManageVO.password}"/>" target="_blank"><span>통계메타</span></a></li>
				</ul>
			</div>
		</c:if>
			<div class="account">
				<ul>
				<c:if test="${!empty gpsSessionVO}">
					<li class="name"><c:out value="${gpsSessionVO.usrNm}"/>님 </li>
					<li class="orgnm">(<c:out value="${gpsSessionVO.orgNm}"/>)</li>
				</c:if>
					<li><a href="/"><img src="/images/gps/menu/top/top_icon_home_off.gif" onmouseover="overImg(this);" onFocus="overImg(this);" onmouseout="outImg(this);" onBlur="outImg(this);" alt="홈" title="홈"/></a></li>
					<li class="logout"><a href="#LINK" onclick="actionLogout();return false;" onkeypress="this.onclick();return false;"><span><spring:message code="gps.login.logout"/></span></a></li>
					<c:if test="${gpsSessionVO.trnsferInfo ne 'U'}">
						<c:set var="userUpdateUrl" value="${serverSe == 'naraint' || serverSe == 'naradev'?'/gps/cmm/gpkiInstall.do?se=userUpdate&menuId=0010001100122125&leftMenuId=0010001100122':'/gps/user/modifyUser.do?menuId=0010001100122125&leftMenuId=0010001100122'}"/>
							<li><a href="<c:url value="${userUpdateUrl}"/>"><img src="/images/gps/menu/top/top_icon_member_on.gif" onmouseover="overImg(this);" onFocus="overImg(this);" onmouseout="outImg(this);" onBlur="outImg(this);" alt="회원정보변경" title="회원정보변경"/></a></li>
					</c:if>
					<li <c:if test="${gpsSessionVO.sysUserAt ne 'Y' or empty gpsSessionVO}">class="end"</c:if>><a href="/gps/contents/guidance/sitemap.do?menuId=0010001100126131&leftMenuId=0010001100126"><img src="/images/gps/menu/top/top_icon_site_off.gif" onmouseover="overImg(this);" onFocus="overImg(this);" onmouseout="outImg(this);" onBlur="outImg(this);" alt="사이트맵" title="사이트맵"/></a></li>
				<c:if test="${gpsSessionVO.sysUserAt eq 'Y'}">
					<li class="end"><a href="/gps/adm/index.do" target="_blank"><img src="/images/gps/menu/top/top_icon_system_on.gif" onmouseover="overImg(this);" onFocus="overImg(this);" onmouseout="outImg(this);" onBlur="outImg(this);" alt="시스템관리" title="시스템관리"/></a></li>
				</c:if>					
				</ul>
			</div>
		</c:otherwise>
	</c:choose>
		</div>
	</div>