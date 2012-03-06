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
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko">
<title>나라통계포털 관리자페이지입니다.</title>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge;">
<%-- 공통 CSS Start	========================================================--%>
<link rel="stylesheet" type="text/css" href="/css/gps/adm/default.css"></link>
<link rel="stylesheet" type="text/css" href="/css/gps/adm/table.css"></link>
<link rel="stylesheet" type="text/css" href="/css/gps/adm/layer.css"></link>
<link rel="stylesheet" type="text/css" href="/css/common/jquery/jquery.treeview.css"></link>
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
<script type="text/javascript" src="/js/common/jquery/jquery.treeview.js" ></script>
<script type="text/javascript" src="/js/common/jquery/NaraJQuery.js"></script>
<script type="text/javascript" src="/js/common/NaraCommon.js"></script>
<script type="text/javascript" src="/js/common/fms/EgovMultiFile.js"></script>
<script type="text/javascript" src="/js/gps/gpsCommon.js"></script>
<script type="text/javascript" src="/js/sps/sds/cmm/framectl.js"></script>
<%-- 공통 자바스크립트  End 	============================================--%>
<script type="text/javascript">
var x;
var y;
// 일반Window인지 Modal인지 확인 후 x/y 좌표 설정

if (window.dialogArguments) {
	x = ((gfn_replaceAll(window.dialogWidth, "px", "") - 317) / 2) + "px";
	y = ((gfn_replaceAll(window.dialogHeight, "px", "") - 74) / 2) + "px";	
} else {
	x = ((document.documentElement.clientWidth - 317) / 2) + document.documentElement.scrollLeft;
	y = ((document.documentElement.clientHeight - 74) / 2) + document.documentElement.scrollTop;
}

$(document).ajaxStart(function() {
	$.blockUI({message:'<img id="displayBox" src="/images/sps/sds/bsm/loading.gif" border="0"/>', css:{width:'317', top:y, left:x}, fadeIn:0, fadeOut:40});
});

$(document).ajaxStop(function() {
	$.unblockUI();
});
</script>
<%-- 공통 자바스크립트  End 	===============================================--%>
</head>
<body>
<%	
	response.setHeader("cache-control","no-cache"); 
	response.setHeader("expires","0"); 
	response.setHeader("pragma","no-cache");
%> 