<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>나라통계포털 관리자페이지입니다.</title>
</head>
<frameset cols="300,*" border="0" title="권한관리">
		<frame src="/gps/adm/author/intergrated/selectIamList.do?upperMenuId=${authorManageVO.upperMenuId}" name="leftBody" title="권한목록" scrolling="auto" border="0" frameborder="0" framespacing="no" marginheight="0" marginheight="0">
		<frame src="/gps/adm/author/intergrated/selectIamDefault.do" name="rightBody" title="권한상세" scrolling="auto" border="0" frameborder="0" framespacing="no" marginheight="0" marginheight="0">
</frameset>
<noframes>
	<body>
		<ul>
			<li><a href="">권한트리</a></li>
			<li><a href="">권한상세</a></li>
		</ul>
	</body>
</noframes>
</html>