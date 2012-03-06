<%-- 
/** 
 * 설명   : 통합권한 관리 - 권한명체크화면
 * 파일명 : iamNmCheck.jsp
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
<%-- Header End ==========================================================--%>

<%-- javascript start ==============================================--%>
<script type="text/javaScript" language="javascript">
	JQ_setValidation('authorManageVO');
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
function fncPageOnload(){
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
   	JQ_request("authorManageVO", "<c:url value='/gps/adm/author/intergrated/selectIamNm.do'/>");
}

 <%-- 
 /************************************************************************ 
 함수명 : fn_back                                   
 설   명 : 코드ID 중복체크       
 인   자 : 없음        
 사용법 :  function fn_check()
 작성일 : 2011-07-25   
 작성자 : 통계포탈 이관형           

   date         author            note
  ----------   -------     ------------------- 
                                  
 ************************************************************************/ 
 --%>
  function fn_back(){
    	JQ_request("authorManageVO", "<c:url value='/gps/adm/author/intergrated/iamNmCheck.do'/>");
 }

 <%-- 
 /************************************************************************ 
 함수명 : confirm                                   
 설   명 : 코드ID 반영함수
 인   자 : 없음        
 사용법 :  confirm()
 작성일 : 2011-07-25   
 작성자 : 통계포탈 이관형           

   date         author            note
  ----------   -------     ------------------- 
                                  
 ************************************************************************/ 
 --%>
  function confirm(){
	  opener.document.insertForm.authorNm.value = $("#authorNm").val();
	  window.close();
 }
</script>
<%-- javascript end ============================================--%>
<form:form commandName="authorManageVO" name="checkForm" method="post">
	<form:hidden path="sysId"/>
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
								<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="권한명 중복체크" title="권한명 중복체크" onclick="fn_check();" /></td>
								<td class="title">권한명 중복체크</td>
							</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table summary="권한명 중복체크" class="write01">
							<caption>권한명 중복체크</caption>
							<tr>
								<th class="subject">권한명</th>
								<td class="input"><input type="text" value="${authorManageVO.authorNm}" class="wi200 validate[required,length[0,100]] text-input" id="authorNm" name="authorNm" size="100" maxlength="100" <c:if test="${authorManageVO.authorNm ne null}">readonly="readonly"</c:if> /></td>
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
								<c:if test="${authorManageVO.authorNm eq null}">
									<td><img src="/images/button/0408.png" alt="중복 확인" title="중복 확인" onclick="fn_check();" style="cursor: pointer;"></td>
								</c:if>
								<c:if test="${authorManageVO.authorNm ne null}">
									<td><img src="/images/button/0206.png" alt="<spring:message code="button.confirm" />" title="<spring:message code="button.confirm" />" onclick="confirm();" style="cursor:pointer;"></td>
									<td><img src="/images/button/0223.png" alt="뒤로" title="뒤로" onclick="fn_back();" style="cursor:pointer;"></td>
								</c:if>
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