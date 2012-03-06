<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>나라통계포털 관리자페이지입니다.</title>
</head>
<frameset cols="250,*" border="0" title="게시판관리">
		<frame src="/gps/adm/bbs/selectBbsDbList.do" name="leftBody" title="게시판DB목록" scrolling="auto" border="0" frameborder="0" framespacing="no" marginheight="0" marginheight="0">
		<frame src="/gps/adm/bbs/selectBbsDefault.do" name="rightBody" title="게시판목록" scrolling="auto" border="0" frameborder="0" framespacing="no" marginheight="0" marginheight="0">
</frameset>
<noframes>
	<body>
		<ul>
			<li><a href="/gps/bbs/bbs/selectBbsDbList.do">시스템게시판목록</a></li>
			<li><a href="/gps/bbs/bbs/selectBbsDefault.do">게시판목록</a></li>
		</ul>
	</body>
</noframes>
</html>