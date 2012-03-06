<%-- 
/** 
 * outline   : 코드관리 - 사용자 유형 목록 화면과 사용자 목록 화면을 구성해주는 프레임 화면
 * filename : codeManageMain.jsp
 * @author 범정부통계포털 이관형 
 * @since 2011.06.17
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.06.17     이관형           최초 생성 
 * </pre> 
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>나라통계포털 관리자페이지입니다.</title>
</head>
<frameset cols="350,*" border="0">
	<frame src="/gps/adm/code/selectGrpCodeList.do" name="codeFrame" title="분류코드" scrolling="auto"border="0" frameborder="0" framespacing="no" marginheight="0" marginheight="0">
	<frame src="/gps/adm/code/selectCodeList.do" name="subCodeFrame" title="소코드" scrolling="auto" border="0" frameborder="0" framespacing="no" marginheight="0" marginheight="0">
</frameset>
<noframes>
	<body>
		<ul>
			<li><a href="">분류코드</a></li>
			<li><a href="">소코드</a></li>
		</ul>
	</body>
</noframes>
</html>