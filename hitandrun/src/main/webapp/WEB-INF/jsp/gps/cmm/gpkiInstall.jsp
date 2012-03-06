<%--
********************************************************************************
* @source      : gpkiInstall.jsp
* @description : 인증서 모듈설치 페이지
*                   
********************************************************************************
* DATE         AUTHOR    DESCRIPTION
*===============================================================================
* 2011-11-23   황기연           최초작성
********************************************************************************
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko">
<head>
<script type='text/javascript' src="/gpkisecureweb/var.js"></script>
<script type='text/javascript' src="/gpkisecureweb/install.js"></script>
<style type="text/css">    
   .main
   {
   	COLOR: #535353;
    FONT-FAMILY: "굴림", "Arial", "seoul";
   	FONT-SIZE: 9pt;
   	LINE-HEIGHT: 15px;
   	letter-spacing : -0.9px;
   	list-style-type : decimal;
   	font-variant : normal;
   }
</style>
<script type='text/javascript'>
document.write(Object_GPKIInstaller);
</script>
<script type='text/javascript'>
if(HaveObject()){
	if(BrowserVersionCheck())
	{
		if(VersionCheckAndDownload()){
			location.href = "<c:url value="${returnUrl}"/>";
		}
	}
}
</script>		
</head>
<!--보안프로그램 처리 시작-->
<body bgcolor="white">
<table width="404" border="3" cellspacing="0" cellpadding="0" align="center" bordercolor="#337DD8">
<tr><td>
<table border="1" cellspacing="0" cellpadding="0" align="center" bordercolor="#A6C7EF">
<tr><td>
		<table border="0" cellspacing="0" cellpadding="0" align="center">
		<tr>
	<!--	    <td><center><img src="image/logo.gif" alt="" border="0"></center></td> -->
		</tr>
		<tr>
		    <td align="center"><br/><img src="/gpkisecureweb/image/dot_line.gif" alt="" width="360" height="10" border="0"/></td>
		</tr>
		<tr>
		    <td align="center" class="main"><br/><b> GPKI 인증관리센터입니다..</b></td>
		</tr>
		<tr>
		    <td align="center" class="main"><br/>
			<b>[ 프로그램 설치시 주의사항 ]</b><br/><br/>
			</td>
		</tr>	
		<tr><td class="main">
			&nbsp; 1. 처음 접속하시는 분은 <b>[보안경고]</b>창이 표시되면 반드시 <b>[예(Y)]</b>를<br/>
			&nbsp; &nbsp; &nbsp;누르셔서 프로그램을 설치하셔야 합니다.<br/>
			&nbsp; &nbsp; &nbsp;*보안에 필요한 프로그램 설치이므로 [아니오(N)]를 선택하시면 <br/>
			&nbsp; &nbsp; &nbsp;서비스를 이용하실 수 없습니다.<br/><br/>
			&nbsp; 2. 보안프로그램 설치중 오류가 발생하거나 장시간 화면이 정지해 <br/>
			&nbsp; &nbsp; &nbsp;있을 경우, 아래의 [보안프로그램 내려받기]를 눌러 다운받으신<br/>
			&nbsp; &nbsp; &nbsp;프로그램 실행 하여 보안프로그램을 수동설치하십시오.<br/><br/>
			&nbsp; 3. 설치예상시간 <br/>
			&nbsp; &nbsp; &nbsp;- 초고속 인터넷 사용자 : 약 30초<br/>
			&nbsp; &nbsp; &nbsp;- 전화모뎀 사용자 : 약 2~3분<br/>
			<br/>
		</td></tr>
		<tr><td align="center">	  
		<a href='/gpkisecureweb/setup/install_off_v1.0.4.9.exe'><img src="/gpkisecureweb/image/security_down.gif" width="136" height="29" alt="" border="0"/></a>
		</td>
		</tr>
</table>
</td></tr></table>		
</td></tr></table>
</body>
</html>