<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="egovframework.rte.fdl.cmmn.exception.BaseException"%>
<%@ page import="egovframework.rte.fdl.cmmn.exception.EgovBizException"%>
<%@ page import="com.bbm.common.exception.AuthorizationException"%>
<%@ page import="net.sf.json.JSONObject"%>
<%

Exception e = (Exception) request.getAttribute("exception");
String errDesc = "좀 더 자세한 사항은 관리자에게 문의해 주시기 바랍니다."; // 에러메시지 
String sbErrDesc = ""; //상세 에러메시지
String errCode = ""; // 에러코드
String errType = ""; //에러 타입
String servicename = ""; //서비스명
String methodname = ""; // 메소드명
String classname = ""; //에러클래스명

if(e instanceof AuthorizationException ) {
	AuthorizationException bizex = (AuthorizationException)e;
    errDesc = bizex.getMessage();
    String [] bizStr = (String [] ) bizex.getMessageParameters();
    String url = "";
    String alertAt = ""; // alert표시여부 
	
	if( bizStr != null ) {
	    if( bizStr.length > 0 ) {
			url = bizStr[0];
		}
	    if( bizStr.length > 1 ) {
			alertAt = bizStr[1];
		}
		
	}

%>
    <script language="javascript">
    	alert( '<%=  errDesc %>' );
<%    	
        if( "Alert".equals( alertAt ) )  {
%>
    	  if( window.name == "iExecFrame" ) {   		
          }
    	  else {
    		history.back(-2);
    	  }
<%
        } else {
%>    	
          top.location.href = "<%= url %>";
<%
        }
%>
    </script>
<%
    return;
} else if(e instanceof EgovBizException) {
	    EgovBizException bizex = (EgovBizException)e;
	    errDesc = bizex.getMessage();		
		String [] bizStr = (String [] ) bizex.getMessageParameters();
		
		if( bizStr != null && bizStr.length > 0 ) {
			errType     = bizStr[0];
			if( bizStr.length > 1 ) errCode     = bizStr[1];
			if( bizStr.length > 2 ) sbErrDesc   = bizStr[2];
			if( bizStr.length > 3 ) servicename = bizStr[3];
			if( bizStr.length > 4 ) methodname  = bizStr[4];
			if( bizStr.length > 5 ) classname   = bizStr[5];
		}

} else if(e instanceof BaseException) {
   		BaseException baseEx = (BaseException)e;
   		errDesc = baseEx.getMessage();		
		String [] bizStr = (String [] ) baseEx.getMessageParameters();		
		if( bizStr != null & bizStr.length > 0 ) {
			errType     = bizStr[0];
			errCode     = bizStr[1];
			errDesc     = bizStr[2];
			servicename = bizStr[3];
			methodname  = bizStr[4];
			classname   = bizStr[5];
		}
} else if(e instanceof Exception) {
	errDesc = e.getMessage();
}
%>
<%
  if( request.getHeader("accept").contains("application/json") ) {
	  
      response.setContentType("application/json;charset=utf-8");

//      JSONObject jSonObject = new JSONObject();
//      jSonObject.put("errType",     errType );
//      jSonObject.put("errCode",     errCode );
//      jSonObject.put("errDesc",     errDesc );
//      jSonObject.put("servicename", servicename );
//      jSonObject.put("methodname",  methodname );
//      jSonObject.put("classname",   classname );
//      out.println( "error" + jSonObject.toString() );

      out.println("처리중에 에러가 발생했습니다.");

      out.println("에러메시지  :  "+ errDesc );
      out.println("에러타입     :  "+ errType );
      out.println("에러코드     :  "+ errCode );      
      out.println("서비스이름 :  "+ servicename );
      out.println("메소드이름 :  "+ methodname );
      out.println("클래스이름 :  "+ classname );

      return;
  }
%>
<%
 if( "BizException".equals( errType ) ){
%>
    <script language="javascript">    
    	alert( '<%=  errDesc %>' );
    	if( window.name == "iExecFrame" ) {}
    	else {
    		history.back(-2);
    	}
    </script>
<%
    return;
 } else if( "AltException".equals( errType ) ){
%>
    <script language="javascript">
    
    	alert( '<%=  errDesc %>' );
    </script>
<%
    return;
 } 
 
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>▒▒▒  범정부 통계시스템   ▒▒▒</title>

<link href="<c:url value='/css/sps/cmm/default.css' />" rel="stylesheet" type="text/css" />

<script language="javascript">
function fncGoAfterErrorPage(){
    history.back(-2);
}
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
                <td align="left"><img src="/images/egovframework/cmmn/er_logo.gif" alt="통계청로그"   title="통계청로그" /></td>
              </tr>
              <tr>
                <td><br />
                  <br /></td>
              </tr>

              <tr>
                <td align="center"><table width="520" border="0" cellspacing="2" cellpadding="2">
                  <tr>
                    <td width="74" rowspan="5" align="center"><img src="/images/egovframework/cmmn/danger.jpg" width="74" height="74" alt="위험"  title="위험"/></td>
                    <td  align="left" width="60">메시지</td>
                    <td align="left" valign="top" class="lt_text5"><%= errDesc  %></td>
                  </tr>
                  <tr>
                    <td  align="left" >에러 코드</td>
                    <td width="399" align="left" class="lt_text2"><%= errCode %></td>
                  </tr>
                   <tr>
                    <td  align="left" >에러Class</td>
                    <td align="left" valign="top" class="lt_text5"><%= classname  %></td>
                  </tr>
                  
                  <tr>
                    <td  align="left" >서비스명</td>
                    <td align="left" valign="top" class="lt_text5"><%= servicename  %></td>
                  </tr>
                   <tr>
                    <td  align="left" >기능</td>
                    <td align="left" valign="top" class="lt_text5"><%= methodname  %></td>
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
                <td align="center"><a onclick="javascript:fncGoAfterErrorPage();" style="cursor: pointer;"><img src="/images/egovframework/cmmn/go_history.jpg" width="90" height="29" border="0" alt="이전페이지"  title="이전페이지" /></a></td>
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
