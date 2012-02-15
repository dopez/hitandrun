<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.StringTokenizer"%>
<%@ page import="com.bbm.common.cmm.service.PrintVO"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>출력</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body topmargin="0" leftmargin="0" scroll="no">
<script src="/OZViewer/Applet/deployJava.js"></script>

<script language="JavaScript">
  deployJava.EAInstallEnabled = 'true';
  deployJava.EarlyAccessURL =  '/OZViewer/Applet/jre-6u29-windows-i586.exe';
  deployJava.returnPage = '/OZViewer/Applet/jre-6u29-windows-i586.exe';

  if( deployJava.isPluginInstalled()) {
    if( !deployJava.versionCheck('1.5.0+')) {
      location.href = '/OZViewer/Applet/jre-6u29-windows-i586.exe';
    }
  }
</script>

<script src="/OZViewer/Applet/deployOZ.js"></script>
<script language="JavaScript">

// 뷰어이벤트 선언예
function OZExportCommand_ozviewer(code, path, filename, step) {}
function OZCommand_ozviewer(code, args) {}
function OZUserActionCommand_ozviewer(type, attr) {}

// 뷰어객체 가저오는 예

function getOZApplet() {
  var oz;
	if(navigator.appName.indexOf("Microsoft") != -1){
		oz = window[OZViewerName];
	}else{
		oz = document[OZViewerName];
	}
	return oz;
}

var OZViewerName = "ozviewer"; // 뷰어 아이디

var javaVersion = '1.6.0'; // 최소 자바 버전 1.5.0 가능


var appletParameter = new Array (
	"id="+OZViewerName,
	"width=1024", 
	"height=760",
    "codebase=/OZViewer/Applet/oz/", // jar위치 상대와 URL 로 선언가능
    "ozversion=51.2011.0810.100" // 설치 뷰어 버전    
);

// 뷰어 파라미터                   
var viewerParameter = new Array (
	"connection.servlet=<%= (String)request.getAttribute("ozserverurl") %>",
<%

PrintVO printVO = (PrintVO) request.getAttribute("printVO");
String printData = printVO.getPrintData();

StringTokenizer st = new StringTokenizer( printData , "##");
String parameter = "";
while ( st.hasMoreTokens()) {
	parameter = st.nextToken();
%>
	"<%= parameter %>",
<%
}
%>
	"viewer.configmode=html",
	"connection.fetchtype=concurrent",
	"toolbar.addmemo=false", 
	"toolbar.hcdata=false",
	"viewer.isframe=false"
);
                    
OZAppletViewerStart(appletParameter, viewerParameter, javaVersion);               
</script>
</body>
</html>