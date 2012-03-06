<%-- 
/** 
 * outline   : 회원가입 아이디중복 체크 팝업
 * filename : gpsUserIdCheck.jsp
 * @author 범정부통계포털 이진우 
 * @since 2011.08.02
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.08.02     이진우           최초 생성 
 * </pre> 
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%-- 공통 taglib 선언 Start	================================================--%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<%-- 공통 taglib 선언 End	====================================================--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko">
<title>나라통계포털-아이디중복확인</title>
<head>
<%-- 공통 CSS Start	=====================================================--%>
<link rel="stylesheet" type="text/css" href="/css/gps/cmm/default.css"></link>
<link rel="stylesheet" type="text/css" href="/css/gps/cmm/table.css"></link>
<link rel="stylesheet" type="text/css" href="/css/gps/cmm/layer.css"></link>
<link rel="stylesheet" type="text/css" href="/css/common/jquery/validationEngine.jquery.css" media="screen" title="no title" charset="utf-8"/>
<%-- 공통 CSS End	=====================================================--%>

<%-- 공통 자바스크립트  Start ==============================================--%>
<script type="text/javascript" src="/js/common/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/common/jquery/jquery.form.js"></script>
<script type="text/javascript" src="/js/common/jquery/jquery.ui.core.js"></script>
<script type="text/javascript" src="/js/common/jquery/jquery.ui.widget.js"></script>
<script type="text/javascript" src="/js/common/jquery/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/js/common/jquery/jquery.validationEngine-ko.js"></script>
<script type="text/javascript" src="/js/common/jquery/jquery.validationEngine.js"></script>
<script type="text/javascript" src="/js/common/jquery/NaraJQuery.js"></script>
<script type="text/javascript" src="/js/common/NaraCommon.js"></script>
<%-- 공통 자바스크립트  End 	========================================--%>
<%-- javascript start ==============================================--%>
<script type="text/javaScript" language="JavaScript">
function fnCheckId(){
	if(fnCheckNotKorean($('#checkId').val())){
	   	JQ_request("userRegisterVO", "<c:url value='/gps/user/checkUserIdConfirm.do'/>");
    }else{
    	alert("한글은 사용할 수 없습니다.");
        return;
    }
}

function fnReturnId(){
    if ($('#usedCnt').val()== 0){
        $(opener.document).find('#userId').val($('#checkId').val());
        window.close();
    }else if ($('#usedCnt').val() == 1){
        alert("이미사용중인 아이디입니다.");
        return;
    }else{
    	alert("먼저 중복확인을 실행하십시오");
        return;
    }
}
function fnClose(){
    window.close();
}

function fnCheckNotKorean(koreanStr){                  
    for(var i=0;i<koreanStr.length;i++){
        var koreanChar = koreanStr.charCodeAt(i);
        if( !( 0xAC00 <= koreanChar && koreanChar <= 0xD7A3 ) && !( 0x3131 <= koreanChar && koreanChar <= 0x318E ) ) { 
        }else{
            //hangul finding....
            return false;
        }
    }
    return true;
}
</script>
</head>
<body>
<div class="popupContents">
	<div class="popupHeader">
		<h3></h3>
		<p class="close_btn"><a href="#"><img src="/images/gps/cmm/icon/x.gif" onclick="javascript:fnClose();" onkeypress="javascript:fnClose();" alt="닫기"></a></p>
	</div>
	
	<h3><img src="/images/gps/title/007.gif" alt="아이디중복확인" title="아이디중복확인"></h3>
	
	<!-- page contents start -->
	<form:form commandName="userRegisterVO" method="post">
	<input type="hidden" id="usedCnt" name="usedCnt" value="<c:out value="${usedCnt}"/>" />
	<div class="popupCn">
		<div class="popupInnerCn">
	   		<p class="popupRule">사용하시려는 아이디(ID)를 입력하시고 중복여부를 확인하세요.</p>
	   		<fieldset>
			<legend>아이디비밀번호 찾기</legend>
			<div class="popupSearch">
				<ul>
					<li class="text">사용할아이디</li>
					<li><input type="text" id="checkId" name="checkId" title="아이디를 입력하세요" class="validate[required,length[5,12],custom[OnlyKor]]" value="<c:out value="${checkId}"/>" maxlength="12" tabindex="1"/></li>
					<li><img src="/images/button/0408.png" title="중복확인" alt="중복확인" style="cursor:hand;" onclick="fnCheckId();return false;" onkeypress="this.onclick();return false;" class="noline course"/></li>
				</ul>
			</div>		
			</fieldset>
		<c:if test="${empty checkId}">
			<p class="popupRule">검색창에 검색어를 입력해 주세요</p>
		</c:if>
		</div>
	<c:if test="${not empty checkId}">
		<div class="popupResult">
	<c:choose>
		<c:when test="${usedCnt eq -1}">
			<table class="idChkResult" summary="<c:out value="${checkId}"/> 의 검색결과입니다.">
			<caption><c:out value="${checkId}"/> 검색결과 입니다.</caption>
			<tr>
				<td>입력하신 아이디 </td>
				<td class="idResult">${checkId}</td>
				<td> (은)는 중복됩니다. 다시 검색하세요.</td>
			</tr>
			</table>
		</c:when>
		<c:when test="${usedCnt eq 0}">
			<table class="idChkResult" summary="<c:out value="${checkId}"/> 의 검색결과입니다.">
			<caption><c:out value="${checkId}"/> 검색결과 입니다.</caption>
			<tr>
				<td>입력하신 아이디 </td>
				<td class="idResult"><a href="javascript:fnReturnId()"><font color="green"><b><c:out value="${checkId}" escapeXml="true"/></b></font></a></td>
				<td> (은)는 사용가능한 아이디입니다.</td>
			</tr>
			</table>
		</c:when>
		<c:otherwise>
			<table class="idChkResult" summary="<c:out value="${checkId}"/> 의 검색결과입니다.">
			<caption><c:out value="${checkId}"/> 검색결과 입니다.</caption>
			<tr>
				<td>입력하신 아이디 </td>
				<td class="idResult"><font color="green"><b><c:out value="${checkId}" escapeXml="true"/></b></font></td>
				<td> (은)는 사용할수 없는 아이디입니다.</td>
			</tr>
			</table>
		</c:otherwise>
	</c:choose>
		</div>
	</c:if>
	</div>
	</form:form>
	<div class="popupFooter">
		<div class="popupButton">
			<a href="javascript:window.close();"><img src="/images/button/0205.png" alt="닫기" title="닫기" ></a>
		</div>
	</div>
</div>
<div class="popupBottom"></div>
</body>
</html>