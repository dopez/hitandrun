<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<c:choose>
<c:when test="${empty gpsSessionVO}">
	<script type="text/javascript">
		alert("로그아웃 되었습니다.");
		window.opener.location.href="/index_test.do";
		window.self.close();
	</script>
</c:when>
<c:otherwise>
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge;">
<title>:::::나라통계포털:::::</title>
<frameset id="frmset" rows="70,*,70" border="0" frameborder="0" framespacing="no" marginheight="0" marginheight="0">
	<frame name="frmtop" src="/gps/cmm/admTop.do?menuId=${menuManageVO.menuId}" scrolling="no" framespacing="0" frameborder="1" noresize>
		<frameset id="bodySet" cols="200,*" frameborder="0" framespacing="no" marginheight="0" marginheight="0">
		    <frame name="frmleft" src="/gps/cmm/admLeft.do?menuId=${menuManageVO.menuId}" scrolling="no">
		    <frame name="frmbody" src="" border="0" frameborder="0" framespacing="no" marginheight="0" marginheight="0">
		    <frame name="frmright" src="" border="0" frameborder="0" framespacing="no" marginheight="0" marginheight="0">
		</frameset>
	<frame name="footer" src="/gps/cmm/admFooter.do" scrolling="no" framespacing="0" frameborder="0" noresize>
</frameset>
	<noframes>
		<body>
			<ul>
				<li><a href="">상단 대메뉴</a></li>
				<li><a href="">좌측 서브메뉴</a></li>
				<li><a href="">메인 콘텐츠</a></li>
			</ul>
		</body>
	</noframes>
</html>
</c:otherwise>
</c:choose>