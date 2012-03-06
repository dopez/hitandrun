<%-- 
/** 
 * 설명   : 만족도관리 - 만족도 조사 수정화면
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
	JQ_setValidation('qesitmManageVO');
	JQ_setValidation('iemManageVO');

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
function fncPageOnload() {
	// 탭UI
	document.getElementById('li_csnst').className = 'on';

	fn_layer_view(1);
	
	// 기간설정UI
	if ($("#validBgnde").val() == '' && $("#validEndde").val() == '')
	{
		document.csnstUpdateForm.usedateChk.checked = true;
		document.getElementById('layer_validBgnde').style.display = 'none';
		document.getElementById('layer_validEndde').style.display = 'none';
	}
	
	// 암호입력UI
	// if ("${csnstManageVO.csnstOthbcse}" == "002") {
	// 	document.getElementById('layer_password').style.display = 'block';
		
	// } else {
	// 	document.getElementById('layer_password').style.display = 'none';
	// }

	if ("${csnstManageVO.csnstSe}" == "N") {
		document.getElementById('layer_csnstFile').style.display = 'none';
	} else {
		if ('${csnstManageVO.csnstFileNm}' == '') {
			document.getElementById('layer_file').style.display = 'block';
		}
	}

	<c:if test="${!empty message}">
		if ('<c:out value = "${message}"/>' == '<spring:message code="gps.csnstinsert.success"/>') {
			fn_layer_view(1);
			if (confirm('<c:out value = "${message}"/>')) {
				fn_layer_view(2);
			}
		} else if ('<c:out value = "${message}"/>' == '<spring:message code="gps.csnstqesitminsert.success"/>') {
			fn_layer_view(2);
			if (confirm('<c:out value = "${message}"/>')) {
				fn_layer_view(3);
			}
		} else if ('<c:out value = "${message}"/>' == '<spring:message code="gps.csnstieminsert.success"/>') {
			fn_layer_view(3);
			if (confirm('<c:out value = "${message}"/>')) {
			   	JQ_request("csnstManageVO", "<c:url value='/gps/adm/csnst/selectCsnstList.do'/>", "csnstManageVO");
			}
		} else if ('<c:out value = "${message}"/>' == '<spring:message code="gps.csnstupdate.success"/>') {
			fn_layer_view(1);
			if (confirm('<c:out value = "${message}"/>')) {
			   	JQ_request("csnstManageVO", "<c:url value='/gps/adm/csnst/selectCsnstList.do'/>", "csnstManageVO");
			}
		} else if ('<c:out value = "${message}"/>' == '<spring:message code="gps.csnstqesitmupdate.success"/>') {
			fn_layer_view(2);
			if (confirm('<c:out value = "${message}"/>')) {
			   	JQ_request("csnstManageVO", "<c:url value='/gps/adm/csnst/selectCsnstList.do'/>", "csnstManageVO");
			}
		} else if ('<c:out value = "${message}"/>' == '<spring:message code="gps.csnstiemupdate.success"/>') {
			fn_layer_view(3);
			if (confirm('<c:out value = "${message}"/>')) {
			   	JQ_request("csnstManageVO", "<c:url value='/gps/adm/csnst/selectCsnstList.do'/>", "csnstManageVO");
			}
		} else if ('<c:out value = "${message}"/>' == '<spring:message code="gps.csnstqesitmdelete.success"/>') {
			fn_layer_view(1);
			if (confirm('<c:out value = "${message}"/>')) {
			   	JQ_request("csnstManageVO", "<c:url value='/gps/adm/csnst/selectCsnstList.do'/>", "csnstManageVO");
			}
		} else if ('<c:out value = "${message}"/>' == '<spring:message code="gps.csnstiemdelete.success"/>') {
			fn_layer_view(2);
			if (confirm('<c:out value = "${message}"/>')) {
			   	JQ_request("csnstManageVO", "<c:url value='/gps/adm/csnst/selectCsnstList.do'/>", "csnstManageVO");
			}
		} else if ('<c:out value = "${message}"/>' == '<spring:message code="gps.csnstiemdeletedetail.success"/>') {
			fn_layer_view(3);
		}
	</c:if>
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
	 JQ_request("csnstManageVO", "<c:url value='/gps/adm/csnst/selectCsnstList.do'/>");

}

 <%-- 
 /************************************************************************ 
 함수명 : fn_update                               
 설   명 : 만족도조사 항목 수정후 재등록 하는 함수
 		공통메시지를 띄워 저장여부 확인한 뒤 사용자가 확인을 하면 DB로 내용을 보내서 Update 시킴
 인   자 : 없음(form 내용 modelAttribute 자동세팅)
 사용법 : fn_update()
 작성일 : 2011.06.20
 작성자 : 범정부통계포털 이관형       

       date        author   note  
  ----------   -------     ------------------- 
                                  
 ************************************************************************/
 --%> 
 function fn_update(view){
 	if(confirm("<spring:message code='common.update.msg' />")){
		 if( JQ_dayBetweenValidate( 'validBgnde', 'validEndde' ) ) return;
		 if (view == 1) {
	 		if ('${csnstManageVO.csnstFileNm}' != '') {
	 	 		JQ_setValueObj('fileDeleteFlg', document.csnstUpdateForm.delFlg.checked);
	 		}
	 		JQ_request("csnstManageVO", "<c:url value='/gps/adm/csnst/updateCsnst.do'/>");
	 	 } else if (view == 2) {
		 		JQ_request("qesitmManageVO", "<c:url value='/gps/adm/csnst/updateCsnstQesitm.do'/>");
	 	 } else if (view == 3) {
		 		JQ_request("iemManageVO", "<c:url value='/gps/adm/csnst/updateCsnstIem.do'/>");
	 	 }
 	}
 }

 <%-- 
 /************************************************************************ 
 함수명 : fn_regist                               
 설   명 : 등록
 인   자 : 없음(form 내용 modelAttribute 자동세팅)
 사용법 : fn_update()
 작성일 : 2011.06.20
 작성자 : 범정부통계포털 이관형       

       date        author   note  
  ----------   -------     ------------------- 
                                  
 ************************************************************************/
 --%> 
 function fn_regist(view) {
	 if (view == 2) {
		 if ($("#qesitmQestnCo").val() <= 1) {
			 alert('만족도 조사항목의 갯수는 두개 이상이어야 합니다.');
		 } else {
			JQ_request("qesitmManageVO", "<c:url value='/gps/adm/csnst/insertCsnstQesitm.do'/>");
		 }
	 } else if (view == 3) {
		 JQ_request("iemManageVO", "<c:url value='/gps/adm/csnst/insertCsnstIem.do'/>");
	 }
 }

 <%-- 
 /************************************************************************ 
 함수명 : fn_delete                               
 설   명 : 삭제
 인   자 : 없음(form 내용 modelAttribute 자동세팅)
 사용법 : fn_delete()
 작성일 : 2011.06.20
 작성자 : 범정부통계포털 이관형       

       date        author   note  
  ----------   -------     ------------------- 
                                  
 ************************************************************************/
 --%> 
 function fn_delete(view){
 	if(confirm("<spring:message code='common.delete.msg' />")){
 		if (view == 1) {
	 		JQ_request("csnstManageVO", "<c:url value='/gps/adm/csnst/deleteCsnst.do'/>");
 		} else if(view == 2) {
	 		JQ_request("qesitmManageVO", "<c:url value='/gps/adm/csnst/deleteCsnstQesitm.do'/>");
 		} else if(view == 3) {
	 		JQ_request("iemManageVO", "<c:url value='/gps/adm/csnst/deleteCsnstIem.do'/>");
 		}
 	}
 }

<%-- 
/************************************************************************ 
함수명 : fn_layer_view                               
설   명 : 레이어조작
인   자 : 없음(form 내용 modelAttribute 자동세팅)
사용법 : fn_layer_view()
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형       

      date    author            note
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
function fn_layer_view(view){
	if (view == 1) {
		document.getElementById('layer_csnst').style.display = 'block';
		document.getElementById('li_csnst').className = 'on';
		document.getElementById('layer_qesitm').style.display = 'none';
		document.getElementById('li_qesitm').className = '';
		document.getElementById('layer_iem').style.display = 'none';
		if ($("#li_iem").length > 0) {
			document.getElementById('li_iem').className = '';
		}
		
	} else if(view == 2) {
		document.getElementById('layer_csnst').style.display = 'none';
		document.getElementById('li_csnst').className = '';
		document.getElementById('layer_qesitm').style.display = 'block';
		document.getElementById('li_qesitm').className = 'on';
		document.getElementById('layer_iem').style.display = 'none';
		if ($("#li_iem").length > 0) {
			document.getElementById('li_iem').className = '';
		}
	} else if(view == 3) {
		document.getElementById('layer_csnst').style.display = 'none';
		document.getElementById('li_csnst').className = '';
		document.getElementById('layer_qesitm').style.display = 'none';
		document.getElementById('li_qesitm').className = '';
		document.getElementById('layer_iem').style.display = 'block';
		document.getElementById('li_iem').className = 'on';
	}
}

<%-- 
/************************************************************************ 
함수명 : fn_layer_view                               
설   명 : 레이어조작
인   자 : 없음(form 내용 modelAttribute 자동세팅)
사용법 : fn_layer_view()
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형       

      date    author            note
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
function fn_usedate() {
	if (document.csnstUpdateForm.usedateChk.checked) {
		document.csnstUpdateForm.validBgnde.disabled = true;
		document.csnstUpdateForm.validEndde.disabled = true;
		document.getElementById('layer_validBgnde').style.display = 'none';
		document.getElementById('layer_validEndde').style.display = 'none';
	} else {
		document.csnstUpdateForm.validBgnde.disabled = false;
		document.csnstUpdateForm.validEndde.disabled = false;
		document.getElementById('layer_validBgnde').style.display = 'block';
		document.getElementById('layer_validEndde').style.display = 'block';
	}
}

<%-- 
/************************************************************************ 
함수명 : fn_layer_view                               
설   명 : 레이어조작
인   자 : 없음(form 내용 modelAttribute 자동세팅)
사용법 : fn_layer_view()
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형       

      date    author            note
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
function layerCsnstOthbcse(csnstOthbcse) {
	if (csnstOthbcse == "002") {
		document.getElementById('layer_password').style.display = 'block';
	} else {
		document.getElementById('layer_password').style.display = 'none';
	}
}

<%-- 
/************************************************************************ 
함수명 : fn_layer_view                               
설   명 : 레이어조작
인   자 : 없음(form 내용 modelAttribute 자동세팅)
사용법 : fn_layer_view()
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형       

      date    author            note
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
function fileChf(val) {
	if (val =='Y') {
		if ('${csnstManageVO.csnstFileNm}' != '') {
			document.getElementById('layer_csnstFile').style.display = 'block';
			if (document.csnstUpdateForm.delFlg.checked) {
				document.getElementById('layer_file').style.display = 'block';
			}
		} else {
			document.getElementById('layer_file').style.display = 'block';
		}
	} else {
		document.getElementById('layer_file').style.display = 'none';
		if ('${csnstManageVO.csnstFileNm}' != '') {
			document.getElementById('layer_csnstFile').style.display = 'none';
		}
	}
}
</script>

<!-- contents_area start -->
<div class="contents_area">
		<form:form commandName="csnstManageVO" name="csnstUpdateForm" method="post" enctype="multipart/form-data">
		<!-- 만족도 조사ID -->
		<form:hidden path="sysId"/>
		<form:hidden path="csnstId"/>
		<form:hidden path="csnstSn"/>
		<form:hidden path="qesitmSn"/>
		<form:hidden path="csnstFileNm"/>
		<form:hidden path="fileDeleteFlg"/>
		<form:hidden path="pageUnit" id="pageUnit"/>
		<form:hidden path="searchCondition" id="searchCondition"/>
		<form:hidden path="pageIndex" id="pageIndex"/>
		<form:hidden path="searchKeyword" id="searchKeyword"/>
		<form:hidden path="searchSysId"/>
		<div class="contents_tab clfix">
			<ul>
				<li id="li_csnst"><p><a onclick="fn_layer_view(1);">만족도 조사수정</a></p></li>
			<c:choose>
			<c:when test="${csnstManageVO.qesitmSn == 0}">
				<li id="li_qesitm"><p><a onclick="fn_layer_view(2);">만족도 조사 문항 등록</a></p></li>
			</c:when>
			<c:otherwise>
				<li id="li_qesitm"><p><a onclick="fn_layer_view(2);">만족도 조사 문항 수정</a></p></li>
			</c:otherwise>
			</c:choose>
			<c:if test="${csnstManageVO.qesitmSn != 0}">
				<c:choose>
				<c:when test="${fn:length(iemNmList) == 0}">
					<li id="li_iem"><p><a onclick="fn_layer_view(3);">만족도 조사 항목 등록</a></p></li>
				</c:when>
				<c:otherwise>
					<li id="li_iem"><p><a onclick="fn_layer_view(3);">만족도 조사 항목 수정</a></p></li>
				</c:otherwise>
				</c:choose>
			</c:if>
			</ul>
		</div>
		<div id="layer_csnst">
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
								<td class="title">만족도 조사수정</td>
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
									<c:out value="${csnstManageVO.sysNm}"/>
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
										<td class="input"><form:radiobutton value="Y" path="csnstUseAt"/></td>
										<td class="text">사용</td>
										<td class="input"><form:radiobutton value="N" path="csnstUseAt"/></td>
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
										<td class="input"><form:radiobutton value="N" path="csnstSe" onclick="fileChf(this.value);"/></td>
										<td class="text">텍스트사용</td>
										<td class="input"><form:radiobutton value="Y" path="csnstSe" onclick="fileChf(this.value);"/></td>
										<td class="text">파일사용</td>
									</tr>
									</table>
								</td>
							</tr>
							<c:choose>
								<c:when test="${csnstManageVO.csnstFileNm != null}">
								<tr id="layer_csnstFile">
									<th class="subject">첨부파일</th>
									<td class="input">
										<div id="egovComFileList"></div>
										<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="UTF-8">
											<c:param name="param_atchFileId" value="${csnstManageVO.csnstFileNm}" />
										</c:import>
										<input type="checkbox" id="delFlg" class="noline" onclick="if(this.checked){document.getElementById('layer_file').style.display = 'block';}else{document.getElementById('layer_file').style.display = 'none';}">
										<font color="red"><c:out value="첨부파일 삭제" /></font>
									</td>
								</tr>
								</c:when>
								<c:otherwise>
								<tr id="layer_csnstFile">
									<th class="subject">첨부파일</th>
									<td class="input">
										<c:out value="첨부파일 없음"></c:out>
									</td>
								</tr>
								</c:otherwise>
							</c:choose>
							<tr id="layer_file" style="display:none">
								<th class="subject">파일첨부</th>
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
											<td class="input"><input type="radio" class="noline" name="csnstOthbcse" value="${csnstOthbcseItem.useCodeId}" <c:if test="${csnstManageVO.csnstOthbcse eq csnstOthbcseItem.useCodeId or empty csnstManageVO.csnstOthbcse}">checked</c:if>></td>
											<%-- 비밀번호 입력레이어 필요시 <input type="radio" class="noline" name="csnstOthbcse" value="${csnstOthbcseItem.useCodeId}" onclick="layerCsnstOthbcse(this.value);" <c:if test="${csnstManageVO.csnstOthbcse eq csnstOthbcseItem.useCodeId or empty csnstManageVO.csnstOthbcse}">checked</c:if>>--%>
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
										<td class="input"><form:password path="csnstPassword" cssClass="text" maxlength="10" showPassword="true"/></td>
									</tr>
									</table>
								</td>
							</tr>
					--%>
							<c:if test="${csnstManageVO.qesitmSn == 0}">
								<tr>
								<th class="subject">만족도 조사 문항 등록 여부</th>
									<td>
										<table class="inside02">
										<tr>
											<td class="input"><font color="red">만족도 조사 문항이 등록되어있지 않습니다. 문항을 등록해주세요</font></td>
										</tr>
										</table>
									</td>
								</tr>
							</c:if>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table class="rbuttonarea">
							<tr>
								<td><img src="/images/button/0210.png" alt="<spring:message code="button.save" />" title="<spring:message code="button.save" />" onclick = "fn_update(1);" style="cursor:hand"></td>
								<td><img src="/images/button/0204.png" alt="<spring:message code="button.delete" />" title="<spring:message code="button.delete" />" onclick = "fn_delete(1);" style="cursor:hand"></td>
								<td class="end"><img src="/images/button/0203.png" alt="<spring:message code="button.reset" />" title="<spring:message code="button.reset" />" onclick = "fn_list();" style="cursor:hand"></td>
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
		</form:form>
		<form:form commandName="qesitmManageVO" name="quesitmForm" method="post" enctype="multipart/form-data">
		<!-- 만족도 조사ID -->
		<form:hidden path="csnstId"/>
		<form:hidden path="sysId"/>
		<form:hidden path="csnstSn"/>
		<form:hidden path="qesitmSn"/>
		<form:hidden path="pageUnit" id="pageUnit"/>
		<form:hidden path="searchCondition" id="searchCondition"/>
		<form:hidden path="pageIndex" id="pageIndex"/>
		<form:hidden path="searchKeyword" id="searchKeyword"/>
		<form:hidden path="searchSysId"/>
		<div id="layer_qesitm" <c:if test="${qesitmManageVO.qesitmSn == 0}">style="display:none"</c:if>>
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
								<td class="title">
								<c:choose>
								<c:when test="${qesitmManageVO.qesitmSn == 0}">
									<c:out value="만족도 조사문항 등록"/>
								</c:when>
								<c:otherwise>
									<c:out value="만족도 조사문항 수정"/>
								</c:otherwise>
								</c:choose>
								</td>
							</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table summary="만족도 조사항목 등록" class="write01">
							<caption>만족도 조사항목 등록</caption>
							<tr>
								<th class="reqsubject">만족도 조사 문항명</th>
								<td class="input"><form:input id="qesitmQestnNm" path="qesitmQestnNm" size="50" maxlength="50" cssClass="validate[required,length[0,50]] text-input; wi200;"/></td>
							</tr>
							<tr>
								<th class="reqsubject">만죽도 조사 문항 유형</th>
								<td>
									<table class="inside02">
									<tr>
										<td class="input"><form:radiobutton id="qesitmTy_radio" cssClass="validate[required] radio" value="radio" path="qesitmTy" /></td>
										<td class="text">단일선택</td>
										<td class="input"><form:radiobutton id="qesitmTy_checkbox" cssClass="validate[required] radio" value="checkbox" path="qesitmTy" /></td>
										<td class="text">다중선택</td>
									</tr>
									</table>
								</td>
							</tr>
							<tr>
								<th class="reqsubject">만족도 조사 항목 갯수</th>
								<td class="input"><input type="text" value="${qesitmManageVO.qesitmQestnCo}" id="qesitmQestnCo" class="validate[required,custom[onlyNumber],length[0,2]] text-input" name="qesitmQestnCo" size="5" class="wi20;" maxlength="2" <c:if test="${qesitmManageVO.qesitmSn != 0}">readonly="readonly"</c:if>/></td>
							</tr>
							<c:if test="${qesitmManageVO.qesitmSn != 0}">
							<c:if test="${fn:length(iemNmList) == 0}">
								<tr>
								<th class="subject">만족도 조사항목 등록 여부</th>
									<td>
										<table class="inside02">
										<tr>
											<td class="input"><font color="red">만족도 조사 항목이 등록되어있지 않습니다. 항목을 등록해주세요</font></td>
										</tr>
										</table>
									</td>
								</tr>
							</c:if>
							</c:if>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table class="rbuttonarea">
							<tr>
								<c:choose>
								<c:when test="${qesitmManageVO.qesitmSn == 0}">
									<td><img src="/images/button/0210.png" alt="<spring:message code="button.save" />" title="<spring:message code="button.save" />" onclick = "fn_regist(2);" style="cursor:hand"></td>
								</c:when>
								<c:otherwise>
									<td><img src="/images/button/0210.png" alt="<spring:message code="button.save" />" title="<spring:message code="button.save" />" onclick = "fn_update(2);" style="cursor:hand"></td>
									<td><img src="/images/button/0204.png" alt="<spring:message code="button.delete" />" title="<spring:message code="button.delete" />" onclick = "fn_delete(2);" style="cursor:hand"></td>
								</c:otherwise>
								</c:choose>
								<td class="end"><img src="/images/button/0203.png" alt="<spring:message code="button.reset" />" title="<spring:message code="button.reset" />" onclick = "fn_list();" style="cursor:hand"></td>
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
		</form:form>
		<form:form commandName="iemManageVO" name="iemForm" method="post" enctype="multipart/form-data">
		<!-- 만족도 조사ID -->
		<form:hidden path="csnstId"/>
		<form:hidden path="sysId"/>
		<form:hidden path="csnstSn"/>
		<form:hidden path="qesitmSn"/>
		<form:hidden path="pageUnit" id="pageUnit"/>
		<form:hidden path="searchCondition" id="searchCondition"/>
		<form:hidden path="pageIndex" id="pageIndex"/>
		<form:hidden path="searchKeyword" id="searchKeyword"/>
		<form:hidden path="searchSysId"/>
		<div id="layer_iem" <c:if test="${fn:length(iemNmList) == 0}">style="display:none"</c:if>>
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
									<td class="title">
									<c:choose>
									<c:when test="${fn:length(iemNmList) == 0}">
										<c:out value="만족도 조사항목 등록"/>
									</c:when>
									<c:otherwise>
										<c:out value="만족도 조사항목 수정"/>
									</c:otherwise>
									</c:choose>
									</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table summary="만족도 조사항목 등록" class="write01">
								<caption>만족도 조사항목 등록</caption>
								
								<c:choose>
								<c:when test="${fn:length(iemNmList) == 0}">
								<c:forEach begin="1" end="${iemManageVO.qesitmQestnCo}" varStatus="status">
									<tr>
										<th class="reqsubject">만족도 조사 항목명<c:out value="${status.count}" /></th>
										<td class="input"><input type="text" name="iemNm" id="iemNm${status.count}" class="validate[required,length[0,50]] text-input; wi200;" size="50" maxlength="50"></td>
									</tr>
								</c:forEach>
								</c:when>
								<c:otherwise>
								<c:forEach begin="0" end="${iemManageVO.qesitmQestnCo}" var="resultInfo" items="${iemNmList}" varStatus="status">
									<tr>
										<th class="reqsubject">만족도 조사 항목명<c:out value="${status.count}" /></th>
										<td class="input"><input type="text" name="iemNm" value="${resultInfo.iemNm}" id="iemNm${status.count}" class="validate[required,length[0,50]] text-input; wi200;" size="50" maxlength="50" ></td>
									</tr>
									<input type="hidden" name="iemSn" value="${resultInfo.iemSn}"/>
								</c:forEach>
								</c:otherwise>
								</c:choose>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table class="rbuttonarea">
								<tr>
								<c:choose>
								<c:when test="${fn:length(iemNmList) == 0}">
									<td><img src="/images/button/0210.png" alt="<spring:message code="button.save" />" title="<spring:message code="button.save" />" onclick = "fn_regist(3);" style="cursor:hand"></td>
								</c:when>
								<c:otherwise>
									<td><img src="/images/button/0210.png" alt="<spring:message code="button.save" />" title="<spring:message code="button.save" />" onclick = "fn_update(3);" style="cursor:hand"></td>
									<td><img src="/images/button/0204.png" alt="<spring:message code="button.delete" />" title="<spring:message code="button.delete" />" onclick = "fn_delete(3);" style="cursor:hand"></td>
								</c:otherwise>
								</c:choose>
									<td class="end"><img src="/images/button/0203.png" alt="<spring:message code="button.reset" />" title="<spring:message code="button.reset" />" onclick = "fn_list();" style="cursor:hand"></td>
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
	</form:form>
	
</div>
<!-- container end -->
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>