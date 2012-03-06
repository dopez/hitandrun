<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%-- 공통 taglib 선언 start	=================================================--%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<%-- 공통 taglib 선언 end	=====================================================--%>
<%!
    String getSession(HttpSession session, String attrName)
    {
        return session.getAttribute(attrName) != null ? (String)session.getAttribute(attrName) : "";
    }
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta content="text/html; charset=utf-8" http-equiv="content-type" />
    <title>GPIN SP - SAMPLE - 사용자 본인인증 결과</title>
</head>
<body>
<%
    /**
     * Sample-AuthRequest 를 통한 사용자인증 완료후 session에 저장된 사용자정보를 가져오는 페이지입니다.
     * Sample-AuthRequest에서 리턴페이지로 지정을 해주어야 연결되며 보여지는 항목의 상세한 내용은 가이드를참조하시기바랍니다.
     */
    // 인증 수신시 요청처와 동일한 위치인지를 session에 저장한 요청자 IP와 비교합니다.
	if (request.getRemoteAddr().equals(session.getAttribute("gpinUserIP")))
	{
%>
    <table>
        <tr>
            <td>중복확인코드(dupInfo)</td>
            <td><%= getSession(session, "dupInfo") %></td>
        </tr>
        <tr>
            <td>개인식별번호(virtualNo)</td>
            <td><%= getSession(session, "virtualNo") %></td>
        </tr>
        <tr>
            <td>이름(realName)</td>
            <td><%= getSession(session, "realName") %></td>
        </tr>
        <tr>
            <td>성별(sex)</td>
            <td><%= getSession(session, "sex") %></td>
        </tr>
        <tr>
            <td>나이(age)</td>
            <td><%= getSession(session, "age") %></td>
        </tr>
        <tr>
            <td>생년월일(birthDate)</td>
            <td><%= getSession(session, "birthDate") %></td>
        </tr>
        <tr>
            <td>국적(nationalInfo)</td>
            <td><%= getSession(session, "nationalInfo") %></td>
        </tr>
        <tr>
            <td>본인인증방법(authInfo)</td>
            <td><%= getSession(session, "authInfo") %></td>
        </tr>
    </table>
<%
	}
	else
	{
%>
		<table>
		<tr><td>세션값을 받지 못했습니다.</td></tr>
		</table>
<%
	}
	%>


    <br />
    <a href="javascript:history.back(-2)">Go Back</a>
    <br />
    <a href="Sample-SessionClear.jsp">Session Clear</a>
</body>
</html>
