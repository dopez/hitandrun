<%-- 
/** 
 * outline   : 코드관리 - 코드목록 조회화면
 * filename : codeList.jsp
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
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp"%>

<%-- Header Start ==========================================================--%>
<jsp:include page="/gps/cmm/admHeader.do"></jsp:include>
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
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 
 
************************************************************************/
--%>
function fncPageOnload(){
	//frameset controller start
	//frameset controller end
	$("#selectall").click(select_all);
}

<%--
/************************************************************************ 
함수명 : select_all                                   
설   명 : 조회된 코드의 체크박스를 전체선택/전체해제 시켜주는 함수       
인   자 : 없음        
사용법 : select_all()              
작성일 : 2011-07-28   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

************************************************************************/
--%>
function select_all(){
	var checked=$("#selectall").attr("checked");
	$("input:checkbox").each(function(){
		var subChecked=$(this).attr("checked");
		if(subChecked != checked)
			$(this).click();
	});
}

<%-- 
/************************************************************************ 
함수명 : fn_selective_delete                                   
설   명 : 조회된 코드의 체크박스를 선택한 후 선택된 코드에 대해 삭제를 수행하는 함수       
		삭제할 코드에 있는 체크박스를 체크한 후 버튼을 누르면 삭제하시겠습니까 라는 확인 팝업 출력 후
		사용자가 확인을 누르면 삭제가 수행 됨
인   자 : 없음        
사용법 : fn_selective_delete()              
작성일 : 2011-07-28   
작성자 : 범정부통계포털 이관형         

      date    author      note 
 ----------   -------     ------------------- 

************************************************************************/
--%>
function fn_selective_delete(){

	var num = 0;
	var checkVal = new Array();
	$("[name^=chkbox]:checked").each(function(){
		checkVal.push($(this).val());
	})	
	document.clForm.codeIdList.value = checkVal;
	if (confirm("<spring:message code="common.delete.msg" />")) {
   		JQ_request("codeManageVO", "<c:url value='/gps/adm/code/deleteCodeList.do'/>");
	}
}
 
 <%-- 
 /************************************************************************ 
 함수명 : fn_modify                                   
 설   명 : 코드 수정 화면으로 이동하는 함수
 인   자 : 없음
 사용법 : fn_modify()              
 작성일 : 2011-06-17   
 작성자 : 범정부통계포털 이관형       

       date    author      note 
  ----------   -------     ------------------- 

  ************************************************************************/
  --%>
 function fn_modify(codeId){
	 parent.subCodeFrame.location.href = "/gps/adm/code/modifyCode.do?codeId=" + codeId + "&upperCodeId=" + document.clForm.upperCodeId.value;
 }
 
<%-- 
/************************************************************************ 
함수명 : fn_regist                                   
설   명 : 코드 등록 화면으로 이동하는 함수
인   자 : 없음
사용법 : fn_regist()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
 function fn_regist(){
	 parent.subCodeFrame.location.href = "/gps/adm/code/registerCode.do?upperCodeId=" + document.clForm.upperCodeId.value;
}

 <%-- 
 /************************************************************************ 
 함수명 : fn_excel_download                                   
 설   명 : 현재 조회되어있는 코드 목록을 엑셀파일형식으로 다운받는 함수
 인   자 : 없음
 사용법 : fn_excel_download()              
 작성일 : 2011-06-17   
 작성자 : 범정부통계포털 이관형       

       date    author      note 
  ----------   -------     ------------------- 

  ************************************************************************/
  --%>
 function fn_excel_download(){
	 if (confirm("<spring:message code="common.exceldownload.msg" />")){
		JQ_request("codeManageVO", "<c:url value='/gps/adm/code/selectCodeExcelDownload.do'/>");
	 }
 }

 <%-- 
 /************************************************************************ 
 함수명 : fn_excel_upload                                   
 설   명 : 코드의 엑셀파일을 DB에 업로드시켜주는 함수
 인   자 : 없음
 사용법 : fn_excel_upload()              
 작성일 : 2011-06-17   
 작성자 : 범정부통계포털 이관형       

       date    author      note 
  ----------   -------     ------------------- 

  ************************************************************************/
  --%>
 function fn_excel_upload(){
	gfn_postPopupWin("codeManageVO", "/gps/adm/code/registerCodeExcelUpload.do", "코드관리 코드등록", 200, 400, "no", "no");
 }
</script>
<%-- javascript end ============================================--%>
<div class="contents_area">
	<form:form commandName="codeManageVO" name="clForm" method="post" action="/gps/adm/code/selectCodeList.do">
	<form:hidden path="upperCodeId" />
	<form:hidden path="codeIdList" />
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
		<c:choose>
			<c:when test="${null eq resultList || codeManageVO.upperCodeNm eq null}">
			<tr>
				<td>
					<table class="title">
					<tr>
						<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="타이틀이미지" title="타이틀이미지" /></td>
						<td class="title">코드명을 선택하세요.</td>
					</tr>
					</table>
				</td>
			</tr>
			</c:when>
			<c:otherwise>
			<tr>
				<td>
					<table class="title">
					<tr>
						<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="타이틀이미지" title="타이틀이미지" /></td>
						<td class="title">코드관리 - [<c:out value="${codeManageVO.upperCodeNm}" />]</td>
						<td class="rbtn">
							<table class="inner_btn" align="right">
							<tr>
								<td><c:if test="${fn:length(resultList) > 0}"><img src="/images/button/0613.png" alt="<spring:message code="button.exceldownload" />" title="<spring:message code="button.exceldownload" />" onclick="fn_excel_download()" style="cursor: pointer;"></c:if></td>
								<td class="end"><img src="/images/button/0705.png" alt="<spring:message code="button.excelupload" />" title="<spring:message code="button.excelupload" />" onclick="fn_excel_upload()" style="cursor: pointer;"></td>
							</tr>
							</table>
						</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table class="datalist01" summary="코드관리">
					<caption>코드리스트</caption>
					<colgroup>
						<col class="column10"/>
						<col class="column1" />
						<col class="column2" />
						<col class="column2" />
						<col class="column2" />
						<col class="column2" />
						<col class="column1" />
						<col class="column3" />
						<col class="column3" />
						<col class="column3" />
					</colgroup>
					<thead>
					<tr>
						<th scope="col"><input type="checkbox" id="selectall" class="noline" title="전체선택" ></th>  
						<th scope="col">코드</th>
						<th scope="col">한글코드명</th>
						<th scope="col">한글코드약명</th>
						<th scope="col">영문코드명</th>
						<th scope="col">영문코드약명</th>
						<th scope="col">사용여부</th>
						<th scope="col">등록자</th>
						<th scope="col">등록일</th>
						<th scope="col" class="end">수정일</th>
					</tr>
					</thead>
					<tbody>
				<c:forEach var="resultInfo" items="${resultList}" varStatus="status">
					<tr onmouseover="this.style.backgroundColor='#fafafa'" onmouseout="this.style.backgroundColor=''">
						<td class="column10"><input type="checkbox" name="chkbox" class="noline" value="${resultInfo.codeId}"/></td>
						<td class="column1"><c:out value="${resultInfo.useCodeId}" /></td>
						<td class="column2"><a onclick="javascript:fn_modify('${resultInfo.codeId}');" style="cursor: pointer;"><c:out value="${resultInfo.codeNm}" /></a></td>
						<td class="column2"><c:out value="${resultInfo.codeAbbrNm}" /></td>
						<td class="column2"><c:out value="${resultInfo.codeEngNm}" /></td>
						<td class="column2"><c:out value="${resultInfo.codeAbbrEngNm}" /></td>
						<td class="column1">
							<c:if test="${resultInfo.fncValAt eq 'Y'}">
								<img src="/images/gps/cmm/icon/o.gif">
							</c:if><c:if test="${resultInfo.fncValAt eq 'N'}">
								<img src="/images/gps/cmm/icon/x.gif">
							</c:if>
						</td>
						<td class="column3"><c:out value="${resultInfo.registerId}" /></td>
						<td class="column3"><c:out value="${resultInfo.registDt}" /></td>
						<td class="column3"><c:out value="${resultInfo.updtDt}" /></td>
					</tr>
				</c:forEach>
				<c:if test="${fn:length(resultList) eq 0}">
					<tr>
						<td class="blank" colspan="10"><spring:message code="common.nodata.msg" /></td>
					</tr>
				</c:if>
					</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table class="rbuttonarea">
					<tr>
						<td><a id="codeRst" style="cursor: pointer;"><img src="/images/button/0202.png" alt="<spring:message code='button.add'/>" title="<spring:message code='button.add'/>" onclick="fn_regist()" /></a></td>
						<td class="end"><img src="/images/button/0428.png" alt="<spring:message code="button.selectedToDelete" />" title="<spring:message code="button.selectedToDelete" />" onclick="fn_selective_delete()" style="cursor: pointer;"></td>
					</tr>
					</table>
				</td>
			</tr>
			</c:otherwise>
		</c:choose>
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
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>