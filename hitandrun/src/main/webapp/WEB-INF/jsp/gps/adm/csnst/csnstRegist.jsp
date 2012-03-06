<%-- 
/** 
 * 설명   : 만족도관리 - 만족도조사 등록화면
 * 파일명 : csnstRegist.jsp
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
<link rel="stylesheet" type="text/css" href="/css/common/jquery/jquery.ui.all.css" />
<link rel="stylesheet" type="text/css" href="/css/common/jquery/jquery.ui.theme.css" />

<%-- javascript start ==============================================--%>
<script type="text/javaScript" language="javascript">
	JQ_setValidation('csnstManageVO');
	
	JQ_setCalendar('validBgnde');
	JQ_setCalendar('validEndde');
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
	// 초기UI설정
	$('[name=csnstUseAt]').val(['Y']);
	$('[name=csnstSe]').val(['N']);
	$('[name=csnstMemoUseAt]').val(['Y']);
	$('[name=csnstOthbcse]').val(['999']);
}

<%-- 
/************************************************************************ 
함수명 : fn_list                                   
설   명 : "목록"버튼을 클릭했을때 프로그램 리스트로 돌아가는 화면       
인   자 : 없음        
사용법 : fn_list()
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형           

  date         author            note
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
 function fn_list(){
   	JQ_request("csnstManageVO", "<c:url value='/gps/adm/csnst/selectCsnstList.do'/>", "csnstManageVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_regist                               
설   명 : 만족도조사 항목 수정후 재등록 하는 함수
		공통메시지를 띄워 저장여부 확인한 뒤 사용자가 확인을 하면 DB로 내용을 보내서 Update 시킴
인   자 : 없음(form 내용 modelAttribute 자동세팅)
사용법 : fn_regist()
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형       

      date    author            note
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
function fn_regist(){
	if(confirm("<spring:message code='common.save.msg' />")){
		if( JQ_dayBetweenValidate( 'validBgnde', 'validEndde' ) ) return;
		
		JQ_request("csnstManageVO", "<c:url value='/gps/adm/csnst/insertCsnst.do'/>");
	}
}

<%-- 
/************************************************************************ 
함수명 : fn_usedate                                   
설   명 : 만족도조사 기간 입력항목 조작 함수
인   자 : 없음
사용법 : fn_usedate()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_usedate() {
	if (document.insertForm.usedateChk.checked) {
		document.insertForm.validBgnde.disabled = true;
		document.insertForm.validEndde.disabled = true;
		document.getElementById('layer_validBgnde').style.display = 'none';
		document.getElementById('layer_validEndde').style.display = 'none';
	} else {
		document.insertForm.validBgnde.disabled = false;
		document.insertForm.validEndde.disabled = false;
		document.getElementById('layer_validBgnde').style.display = 'block';
		document.getElementById('layer_validEndde').style.display = 'block';
	}
}

<%-- 
/************************************************************************ 
함수명 : fn_usedate                                   
설   명 : 만족도조사 비밀번호 조작 함수
인   자 : 없음
사용법 : fn_usedate()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function layerCsnstOthbcse(csnstOthbcse) {
	if (csnstOthbcse == "002") {
		document.getElementById('layer_password').style.display = 'block';
	} else {
		document.getElementById('layer_password').style.display = 'none';
		document.insertForm.csnstPassword.value = '';
	}
}

</script>
<form:form commandName="csnstManageVO" name="insertForm" method="post" enctype="multipart/form-data">
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
						<td class="title">만족도 조사등록</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table summary="만족도 조사등록" class="write01">
					<caption>만족도 조사등록</caption>
					<tr>
						<th class="subject">시스템</th>
						<td class="input">
							<select name="sysId">
								<c:forEach var="sysInfoList" items="${sysInfo}" varStatus="status">
								<option value="<c:out value="${sysInfoList.sysId}" />" ><c:out value="${sysInfoList.sysNm}" /></option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th class="reqsubject">만족도 조사</th>
						<td class="input"><form:input path="csnstNm" size="50" cssClass="wi200 validate[required,length[0,50]] text-input" maxlength="50"/></td>
					</tr>
					<tr>
						<th class="reqsubject">사용여부</th>
						<td>
							<table class="inside02">
							<tr>
								<td class="input"><form:radiobutton id="csnstUseAtY" cssClass="validate[required] radio" value="Y" path="csnstUseAt"/></td>
								<td class="text">사용</td>
								<td class="input"><form:radiobutton id="csnstUseAtN" cssClass="validate[required] radio" value="N" path="csnstUseAt"/></td>
								<td class="text">사용안함</td>
							</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th class="subject">기간</th>
						<td>
							<table class="inside01">
							<tr>
								<th class="subject">기간없음</th>
								<td>
									<table class="inside02">
									<tr>
										<td class="input"><input type="checkbox" id="usedateChk" value="Y" class="noline" onclick="fn_usedate();"></td>
										<td class="text">기간없이 설정</td>
									</tr>
									</table>
								</td>
							</tr>
							<tr id="layer_validBgnde">
								<th class="subject">시작일</th>
								<td class="input"><form:input path="validBgnde" id="validBgnde" size="10" cssClass="input_t input_nara_date" onfocus="gfn_focus(this);" onblur="gfn_focusnot(this);"/></td>
							</tr>
							<tr id="layer_validEndde">
								<th class="bottom_noline">종료일</th>
								<td class="bottom_noline"><form:input path="validEndde" id="validEndde" size="10" cssClass="input_t input_nara_date" onfocus="gfn_focus(this);" onblur="gfn_focusnot(this);"/></td>
							</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th class="subject">내용</th>
						<td class="input"><form:textarea path="csnstDn" rows="5" cols="65" /></td>
					</tr>
					<tr>
						<th class="subject">항목타입선택</th>
						<td>
							<table class="inside02">
							<tr>
								<td class="input"><form:radiobutton id="csnstSeN" value="N" path="csnstSe" onclick="document.getElementById('layer_file').style.display = 'none';"/></td>
								<td class="text">텍스트사용</td>
								<td class="input"><form:radiobutton id="csnstSeY" value="Y" path="csnstSe" onclick="document.getElementById('layer_file').style.display = 'block';"/></td>
								<td class="text">파일사용</td>
							</tr>
							</table>
						</td>
					</tr>
					<tr id="layer_file" style="display:none">
						<th class="subject">첨부파일</th>
						<td class="input"><input type="file" name="csnstFile" maxlength="200" class="wi400"></td>
					</tr>
					<%-- 
					<tr>
						<th class="subject">메모사용 허용여부</th>
						<td>
							<table class="inside02">
							<tr>
								<td class="input"><form:radiobutton value="Y" path="csnstMemoUseAt"/></td>
								<td class="text">메모 사용</td>
								<td class="input"><form:radiobutton value="N" path="csnstMemoUseAt" /></td>
								<td class="text">메모 사용안함</td>
							</tr>
							</table>
						</td>
					</tr>
					--%>
					<tr>
						<th class="subject">공개수준</th>
						<td>
							<table class="inside02">
								<c:forEach var="csnstOthbcseItem" varStatus="i" items="${csnstOthbcseList}">
									<td class="input"><input type="radio" class="noline" name="csnstOthbcse" value="${csnstOthbcseItem.useCodeId}" ></td>
									<%-- 비밀번호 입력레이어 필요시 <td class="input"><input type="radio" class="noline" name="csnstOthbcse" value="${csnstOthbcseItem.useCodeId}" onclick="layerCsnstOthbcse(this.value);"></td> --%>
									<td class="text">${csnstOthbcseItem.codeNm}</td>
								</c:forEach>
								<c:if test="${fn:length(csnstOthbcseList) == 0}">
									<tr> 
										<td class="lt_text3" colspan="10"><spring:message code="gps.nodata.csnstcsnstOthbcse" /></td>
									</tr>   	          				 			   
								</c:if>
							</table>
						</td>
					</tr>
					<%-- 
					<tr id="layer_password" <c:if test="${csnstManageVO.csnstOthbcse ne '001'}">style="display:none"</c:if><c:if test="${csnstManageVO.csnstOthbcse eq '001'}">style="display:block"</c:if>>
						<th class="subject">비밀번호</th>
						<td>
							<table class="inside02">
							<tr>
								<td class="input"><form:password path="csnstPassword" cssClass="text" maxlength="10" /></td>
							</tr>
							</table>
						</td>
					</tr>
					--%>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table class="rbuttonarea">
					<tr>
						<td><img src="/images/button/0210.png" alt="<spring:message code="button.create" />" title="<spring:message code="button.create" />" onclick="fn_regist();" style="cursor:hand"></td>
						<td class="end"><img src="/images/button/0203.png" alt="<spring:message code="button.reset" />" title="<spring:message code="button.reset" />" onclick="fn_list();" style="cursor:hand"></td>
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