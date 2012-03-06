<%-- 
/** 
 * 설명   : 코드관리 - 코드 상세내용을 수정하는 화면
 * 파일명 : codeUpdate.jsp
 * @author 범정부통계포털 이관형 
 * @since 2011.06.23
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.06.23     이관형           최초 생성 
 * </pre> 
 */
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>

<%-- Header Start ==========================================================--%>
<jsp:include page="/gps/cmm/admHeader.do"></jsp:include>
<%-- Header End ==========================================================--%>

<%-- javascript start ==============================================--%>
<script type="text/javaScript" language="javascript">
	JQ_setValidation('codeManageVO');
	
	JQ_setCalendar('validBgnde','validEndde','from');
	JQ_setCalendar('validEndde','validBgnde','to');
	JQ_onload();
<%-- 
/************************************************************************ 
함수명 : fncPageOnload                                   
설   명 : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
인   자 : 없음        
사용법 : fncPageOnload()              
작성일 : 2011.06.23  
작성자 : 범정부통계포털 이관형  

      date        author   note  
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
 함수명 : fn_perfectdelete                               
 설   명 : 코드를 완전삭제
 		코드테이블로부터 코드를 완전 삭제한다.
 인   자 : 없음(form 내용 modelAttribute 자동세팅)
 사용법 : fn_perfectdelete()
 작성일 : 2011.06.23
 작성자 : 범정부통계포털 이관형       

       date        author   note  
  ----------   -------     ------------------- 
                                  
 ************************************************************************/
 --%> 
 function fn_delete(){
 	if(confirm("<spring:message code='common.perfectdelete.msg' />")){		
 		JQ_request("codeManageVO", "<c:url value='/gps/adm/code/deleteCode.do'/>");
 	}
 }

<%-- 
/************************************************************************ 
함수명 : fn_modify                               
설   명 : 코드테이블의 행을 수정
		코드테이블의 행을 수정한다.
인   자 : 없음(form 내용 modelAttribute 자동세팅)
사용법 : fn_modify()
작성일 : 2011.06.23
작성자 : 범정부통계포털 이관형       

      date        author   note  
 ----------   -------     ------------------- 
                                 
************************************************************************/
--%> 
function fn_modify(){

	if(confirm("<spring:message code='common.update.msg' />")){
		JQ_request("codeManageVO", "<c:url value='/gps/adm/code/updateCode.do'/>");
	}
}
</script>
<%-- javascript end ============================================--%>
<form:form commandName="codeManageVO" name="updateForm" method="post">
<form:hidden path="codeId" />
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
						<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="타이틀이미지" title="타이틀이미지"/></td>
						<td class="title"><c:out value="${codeManageVO.upperCodeNm}" />수정/삭제</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table summary="코드  수정" class="write01">
					<caption>코드 수정</caption>
					<tr>
						<th class = "subject">코드</th>
						<td>
							<table class="inside02">
							<tr>
								<td class="input"><c:out value="${codeManageVO.useCodeId}" /></td>
							</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th class="reqsubject">한글코드명</th>
						<td class="input"><form:input path="codeNm" id="codeNm" size="50" cssClass="validate[required,length[1,100],custom[onlyKorEng]] text-input; wi100;" maxlength="100" /></td>
					</tr>
					<tr>
						<th class="subject">한글코드단축명</th>
						<td class="input"><form:input path="codeAbbrNm" size="50" cssClass="validate[optional,custom[onlyKorEng],length[0,100]; wi100;" maxlength="60" /></td>
					</tr>
					<tr>
						<th class="subject">영문코드명</th>
						<td class="input"><form:input path="codeEngNm" size="50" cssClass="validate[optional,custom[onlyLetter],length[0,100]; wi100;" maxlength="100" /></td>
					</tr>
					<tr>
						<th class="subject">영문코드단축명</th>
						<td class="input"><form:input path="codeAbbrEngNm" size="50" cssClass="validate[optional,custom[onlyLetter],length[0,100]; wi100;" maxlength="60" /></td>
					</tr>
					<tr>
						<th class="subject">유효 시작일</th>
						<td class="no_padding">
							<table class="inside02">
							<tr>
								<td class="input">
									<form:input path="validBgnde"  size="10" cssStyle="vertical-align:middle" cssClass="input_t input_nara_date" onfocus="gfn_focus(this);" onblur="gfn_focusnot(this);"/>
								</td>
							</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th class="subject">유효 종료일</th>
						<td class="no_padding">
							<table class="inside02">
							<tr>
								<td class="input">
									<form:input path="validEndde"  size="10" cssStyle="vertical-align:middle" cssClass="input_t input_nara_date" onfocus="gfn_focus(this);" onblur="gfn_focusnot(this);"/>
								</td>
							</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th class="subject">사용여부</th>
						<td>
							<table class="inside02">
							<tr>
								<td class="input"><form:radiobutton path="fncValAt" id="fncValAtY" value="Y" cssClass="noline"/></td>
								<td><label for="fncValAtY" title="사용">사용</label></td>
								<td class="input"><form:radiobutton path="fncValAt" id="fncValAtN" value="N" cssClass="noline"/></td>
								<td><label for="fncValAtN" title="사용안함">사용안함</label></td>
							</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th class="subject">작성자</th>
						<td class="input"><c:out value="${codeManageVO.registerId}"/></td>
					</tr>
					<tr>
						<th class="subject">작성일</th>
						<td class="input"><c:out value="${codeManageVO.registDt}"/></td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table class="rbuttonarea">
					<tr>
						<td><img src="/images/button/0210.png" alt="<spring:message code="button.save" />" title="<spring:message code="button.save" />" onclick = "fn_modify();" style="cursor:pointer;" ></td>
						<td><img src="/images/button/0204.png" alt="<spring:message code="button.delete" />" title="<spring:message code="button.delete" />" onclick = "fn_delete();" style="cursor:pointer;" ></td>
						<td class="end"><img src="/images/button/0203.png" alt="<spring:message code="button.reset" />" title="<spring:message code="button.reset" />" onclick = "history.back(-1);" style="cursor:pointer;" ></td>
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