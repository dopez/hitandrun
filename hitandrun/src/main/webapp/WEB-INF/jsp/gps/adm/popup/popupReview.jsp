<%-- 
/** 
 * 설명   : 팝업관리 - 팝업 미리보기
 * 파일명 : popupReview.jsp
 * @author 범정부통계포털 이관형 
 * @since 2011.06.20 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.06.20     이관형           최초 생성 
 * </pre> 
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>

<%-- Header Start ==========================================================--%>
<jsp:include page="/gps/cmm/admHeader.do"></jsp:include>
<%-- Header End ==========================================================--%>

<%-- javascript start ==============================================--%>
<link rel="stylesheet" type="text/css" href="/css/common/jquery/jquery.ui.all.css" />
<link rel="stylesheet" type="text/css" href="/css/common/jquery/jquery.ui.theme.css" />

<script type="text/javaScript" language="javascript">
	JQ_setValidation('popupManageVO');
	
	JQ_onload();

<%-- 
/************************************************************************ 
함수명 : fncPageOnload                                   
설   명 : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
인   자 : 없음        
사용법 : fncPageOnload()              
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형       

  date         author            note
 ----------   -------     ------------------- 
                              
************************************************************************/ 
--%>
function fncPageOnload()
{
	//frameset controller start
	//frameset controller end
}

<%-- 
/************************************************************************ 
함수명 : popupCookieChk                                   
설   명 : 팝업 쿠키설정  
인   자 : 없음        
사용법 : popupCookieChk()              
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형       

  date         author            note
 ----------   -------     ------------------- 
                              
************************************************************************/ 
--%>
function popupCookieChk(name) {
	if ($("#popupChk").attr("checked")) {
		createCookie(name, "done", 1);
	} else {
		eraseCookie(name);
	}
}

<%-- 
/************************************************************************ 
함수명 : createCookie                                   
설   명 : 팝업 쿠키설정  
인   자 : 없음        
사용법 : createCookie()              
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형       

  date         author            note
 ----------   -------     ------------------- 
                              
************************************************************************/ 
--%>
function createCookie(name,value,days) {
	if (days) {
		var date = new Date();
		date.setTime(date.getTime()+(days*24*60*60*1000));
		var expires = "; expires="+date.toGMTString();
	} else {
		var expires = "";
	}
	document.cookie = name+"="+value+expires+"; path=/";
}

<%-- 
/************************************************************************ 
함수명 : eraseCookie                                   
설   명 : 팝업 쿠키설정  
인   자 : 없음        
사용법 : eraseCookie()              
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형       

  date         author            note
 ----------   -------     ------------------- 
                              
************************************************************************/ 
--%>
function eraseCookie(name) {
	createCookie(name,"",-1);
}
</script>
<form:form commandName="popupManageVO" name="insertForm" method="post" enctype="multipart/form-data" >

<!-- contents_area start -->
<div class="contents_area">
	<table class="managerLayer">
	<tr>
		<td class="td01"></td>
		<td class="td02"></td>
		<td class="td03"></td>
	</tr>
	<tr>
		<td class="td04"></td>
		<td class="pl5 cb vt">
			<table cellpadding="0" cellspacing="0" border="0" width="100%" height="100%">
			<tr>
				<td <c:if test="${not empty popupManageVO.atchmnflThreeMask}">style="background-image:url('<c:url value="/gps/adm/popup/getImageFile.do">
													<c:param name="atchFileId" value="${popupManageVO.atchmnflThree}"/>
													<c:param name="streFileNm" value="${popupManageVO.atchmnflThreeMask}"/>	
													</c:url>');"</c:if> valign="top">
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
						<td valign="top" <c:if test="${not empty popupManageVO.atchmnflThreeMask}">height="${popupManageVO.height}"</c:if>>
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<c:if test="${not empty popupManageVO.atchmnflTwoMask}">
							<c:choose>
								<c:when test="${not empty popupManageVO.url}">
									<c:choose>
										<c:when test="${fn:containsIgnoreCase(popupManageVO.url,'http://')}"><c:set var="url" value="${popupManageVO.url}"/></c:when>
										<c:otherwise><c:set var="url" value="http://${popupManageVO.url}"/></c:otherwise>
									</c:choose>
									<tr>
										<td><a href="${url}" target="${popupManageVO.trget}"><img src="<c:url value="/gps/adm/popup/getImageFile.do">
													<c:param name="atchFileId" value="${popupManageVO.atchmnflTwo}"/>
													<c:param name="streFileNm" value="${popupManageVO.atchmnflTwoMask}"/>	
													</c:url>" title="${popupManageVO.atchmnflTwo}" alt="${popupManageVO.atchmnflTwo}" border="0"></a></td>
									</tr>
									<tr><td class="linetd"></td></tr>
								</c:when>
								<c:otherwise>
									<tr>
										<td><img src="<c:url value="/gps/adm/popup/getImageFile.do">
													<c:param name="atchFileId" value="${popupManageVO.atchmnflOne}"/>
													<c:param name="streFileNm" value="${popupManageVO.atchmnflOneMask}"/>	
													</c:url>" title="${popupManageVO.atchmnflTwo}" alt="${popupManageVO.atchmnflTwo}"></td>
									</tr>
									<tr><td class="linetd"></td></tr>
								</c:otherwise>
							</c:choose>
						</c:if>
						<c:if test="${not empty popupManageVO.cn}">
							<tr>
								<td style="padding:3px 0 3px 3px;">
									${popupManageVO.cn}
								</td>
							</tr>
							<tr><td class="linetd"></td></tr>
						</c:if>
							</table>
						</td>
					</tr>
					</table>
				</td>
			</tr>
			<c:if test="${popupManageVO.cookieSkll eq 'Y'}">
			<tr>
				<td valign="bottom" align="right">
					<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td style="padding:2px 0 0 0;"><input type="checkbox" id="popupChk" name="popupChk" style="border:none;" onClick="popupCookieChk('pop_${popupManageVO.sysId}_${popupManageVO.popupSn}');window.close();" onkeypress="popupCookieChk('pop_${popupManageVO.sysId}_${popupManageVO.popupSn}');window.close();"></td>
						<td style="padding:5px 0 0 0;">오늘하루 보이지 않음.</td>
					</tr>
					</table>
				</td>
			</tr>
			</c:if>
			</table>
		<td class="td05"></td>
	</tr>
	<tr>
		<td class="td06"></td>
		<td class="td07"></td>
		<td class="td08"></td>
	</tr>
	</table>
</div>
<!-- container end -->
</form:form>
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>