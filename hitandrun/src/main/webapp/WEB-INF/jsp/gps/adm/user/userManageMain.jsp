<%-- 
/** 
 * outline   : 사용자관리 사용자 유형 목록 화면과 사용자 목록 화면을 구성해주는 프레임 화면
 * filename : userManageMain.jsp
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
 * 2011.07.21     이진우           좌측 프레임 사이즈 조절 250 -> 300
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
<frameset cols="300,*" border="0" title="사용자관리">
		<frame src="/gps/adm/user/selectUserTypeList.do" name="leftBody" title="사용자관리" scrolling="auto" border="0" frameborder="0" framespacing="no" marginheight="0" marginheight="0">
		<frame src="/gps/adm/user/userDefault.do" name="rightBody" title="사용자유형별목록" scrolling="auto" border="0" frameborder="0" framespacing="no" marginheight="0" marginheight="0">
</frameset>
<noframes>
	<body>
		<ul>
			<li><a href="">사용자유형목록</a></li>
			<li><a href="">사용자유형별목록</a></li>
		</ul>
	</body>
</noframes>
</html>