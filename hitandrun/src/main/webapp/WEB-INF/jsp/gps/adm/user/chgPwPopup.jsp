<%-- 
/** 
 * outline  : 사용자관리 비밀번호 변경 팝업
 * filename : chgPwPopup.jsp
 * @author 범정부통계포털 이진우 
 * @since 2011.07.26
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.07.26     이진우           최초 생성 
 * </pre> 
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<%-- Header Start ==========================================================--%>
<jsp:include page="/gps/cmm/admHeader.do"></jsp:include>
<%-- Header End ==========================================================--%>

<%-- javascript start ==============================================--%>
<script type="text/javaScript" language="JavaScript">
	JQ_setValidation('userManageVO');
	JQ_onload();
	
function fncPageOnload() {
	//frameset controller start
	//frameset controller end
}
<%-- 
/************************************************************************ 
함수명 : fn_modify                               
설   명 : 선택된 사용자의 비밀번호 변경
인   자 : 없음
사용법 : fn_modifyPw()
작성일 : 2011.07.27
작성자 : 범정부통계포털 이진우       

      date        author   note  
 ----------   -------     ------------------- 
                                 
************************************************************************/
--%> 
function fn_modifyPw(){
	if(confirm("<spring:message code='common.update.msg' />")){		
		JQ_request("userManageVO", "<c:url value='/gps/adm/user/updatePwPopup.do'/>");
	}
	//window.close();
}
</script>
<div class="contents_area">
	<form:form commandName="userManageVO" name="insertForm" id="userManageVO" method="post">
  	<input name="userId" type="hidden" value="${userManageVO.userId}"/>
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
							<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="타이틀이미지" title="타이틀이미지" /></td>
							<td class="title">비밀번호 변경</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table summary="비밀번호 변경" class="write01">
						<caption>비밀번호 변경</caption>
						<tr>
							<th class="subject">아이디</th>
							<td class="input"><c:out value="${userManageVO.userId}" /></td>
						</tr>
						<tr>
							<th class="subject">비밀번호 변경</th>
							<td class="input"><form:password path="password" id="password" title="비밀번호" cssClass="validate[required,length[5,12]] text-input wi150"/></td>
						</tr>
						<tr>
							<th class="subject">비밀번호 확인</th>
							<td class="input"><input type="password" id="password2" title="비밀번호 확인" class="validate[required,confirm[password]] text-input wi150"/></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="rbuttonarea">
						<tr>
							<td><img src="/images/button/0210.png" alt="<spring:message code="button.save" />" title="<spring:message code="button.save" />" onclick = "fn_modifyPw();"></td>
							<td class="end"><img src="/images/button/0205.png" alt="닫기" title="닫기" onclick="window.close();"></td>
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
	</form:form>
</div>