<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="egovframework.rte.fdl.cmmn.exception.BaseException"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>▒▒▒  범정부 통계시스템   ▒▒▒</title>

<link href="<c:url value='/css/sps/dms/default.css' />" rel="stylesheet" type="text/css" />

<script language="javascript">
function fncGoAfterErrorPage(){
    history.back(-2);
}
</script>

<script language="javascript">

<%

Exception e = (Exception) request.getAttribute("exception");
String errDesc = "좀 더 자세한 사항은 관리자에게 문의해 주시기 바랍니다.";

if(e instanceof BaseException) {
   		BaseException baseEx = (BaseException)e;
   		errDesc = baseEx.getMessage();

}
%>	
 		
</script>


</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td align="center" valign="top"><br />
    <br />
    <br />
    <table width="600" border="0" cellpadding="0" cellspacing="0" background="/images/egovframework/cmmn/blue_bg.jpg">
      <tr>
        <td align="center"><table width="100%" border="0" cellspacing="9" cellpadding="0">
          <tr>
            <td bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td align="left"><img src="/images/egovframework/cmmn/er_logo.gif"  alt="통계청로그" title="통계청로그" /></td>
              </tr>
              <tr>
                <td><br />
                  <br /></td>
              </tr>

              <tr>
                <td align="center"><table width="520" border="0" cellspacing="2" cellpadding="2">
                  <tr>
                    <td width="74" rowspan="2" align="center"><img src="/images/egovframework/cmmn/danger.jpg" width="74" height="74" alt="위럼" title="위럼" /></td>
                    <td width="399" align="left" class="lt_text2">HTTP 404 Error</td>
                  </tr>
                  <tr>
                    <td align="left" valign="top" class="lt_text5"><%= errDesc  %></td>
                  </tr>
                </table>
                  <table width="500" border="0" cellspacing="2" cellpadding="2">
                                  </table></td>
              </tr>
              <tr>
                <td><br />
                  <br /></td>
              </tr>
              <tr>
                <td align="center"><a onclick="javascript:fncGoAfterErrorPage();" style="cursor: pointer;"><img src="/images/egovframework/cmmn/go_history.jpg" width="90" height="29" border="0" alt="이전페이지" title="이전페이지" /></a></td>
              </tr>
            </table>
              <br /></td>
          </tr>
        </table></td>
      </tr>
    </table>
    </td>
  </tr>
</table>
</body>
</html>
