<%-- 
/** 
 * 설명   : 코드관리 - 코드ID 체크화면
 * 파일명 : codeIdCheck.jsp
 * @author 범정부통계포털 이관형 
 * @since 2011.06.23 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == 개정이력(Modification Information) == 
 *   
 *   date          author        note
 * ----------    -------    --------------------------- 
 * 2011.06.23     이관형           최초 생성 
 * </pre> 
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp"%>

<%-- Header Start ==========================================================--%>
<jsp:include page="/gps/cmm/admHeader.do"></jsp:include>
<%@ page import="go.narastat.gps.adm.code.service.CodeManageVO"%>
<%-- Header End ==========================================================--%>

<%-- javascript start ==============================================--%>
<script type="text/javaScript" language="javascript">
	JQ_setValidation('codeManageVO');
	JQ_onload();
<%-- 
/************************************************************************ 
함수명 : fncPageOnload                                   
설   명 : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
인   자 : 없음        
사용법 : fncPageOnload()              
작성일 : 2011-06-20   
작성자 : 통계포탈 이관형       

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
함수명 : fn_check                                   
설   명 : 코드ID 중복체크       
인   자 : 없음        
사용법 :  function fn_check()
작성일 : 2011-07-25   
작성자 : 통계포탈 이관형           

  date         author            note
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
 function fn_check(){
   	JQ_request("codeManageVO", "<c:url value='/gps/adm/code/checkUseCodeId.do'/>");
}

 <%-- 
 /************************************************************************ 
 함수명 : confirm                                   
 설   명 : 코드ID를 반영한다.       
 인   자 : 없음        
 사용법 :  confirm()
 작성일 : 2011-07-25   
 작성자 : 통계포탈 이관형           

   date         author            note
  ----------   -------     ------------------- 
                                  
 ************************************************************************/ 
 --%>
  function confirm(){
	  opener.document.insertForm.useCodeId.value = document.checkForm.useCodeId.value;
	  window.close();
 }
</script>
<%-- javascript end ============================================--%>
<form:form commandName="codeManageVO" name="checkForm" method="post">
<form:hidden path="upperCodeId" />
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
			<table class="default">
			<tr>
				<td>
					<table class="title">
					<tr>
						<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="코드ID 중복체크" title="코드ID 중복체크" onclick="fn_check();" /></td>
						<td class="title">코드ID 중복체크</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table summary="코드ID 중복체크" class="write01">
					<caption>코드ID 중복체크</caption>
					<tr>
						<th class="subject">코드ID</th>
						<td class="input"><input type="text" value="${codeManageVO.useCodeId}" class="wi100 validate[required,length[1,5],custom[onlyEngNumber]] text-input" id="useCodeId" name="useCodeId" size="50" maxlength="50" <c:if test="${codeManageVO.useCodeId ne null}">readonly="readonly"</c:if> /></td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table class="datamessage">
					<tr>
						<td><c:out value="${message}" /></td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table class="rbuttonarea">
					<tr>
						<td><c:if test="${codeManageVO.useCodeId eq null}"><img src="/images/button/0701.png" alt="코드ID 중복 확인" title="코드ID 중복 확인" onclick="fn_check();" style="cursor: pointer;"></c:if><c:if test="${codeManageVO.useCodeId ne null}"><img src="/images/button/0206.png" alt="<spring:message code="button.confirm" />" title="<spring:message code="button.confirm" />" onclick="confirm();" style="cursor:pointer;"><img src="/images/button/0223.png" alt="뒤로" title="뒤로" onclick="history.back(-1);" style="cursor:pointer;"></c:if></td>
						<td class="end"><img src="/images/button/0203.png" alt="<spring:message code="button.list" />" title="<spring:message code="button.list" />" onclick="javascript:window.close();" style="cursor: pointer;"></td>
					</tr>
					</table>
				</td>
			</tr>
			</table>
	</td>
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