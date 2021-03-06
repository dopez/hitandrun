<%-- 
/** 
 * outline   : 프로그램관리 - 프로그램목록 조회화면
 * filename : programList.jsp
 * @author 범정부통계포털 이관형 
 * @since 2011.06.17
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.06.17     이관형           최초 생성 
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
	JQ_setValidation('programManageVO');
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
function fncPageOnload() {
	//frameset controller start
	//frameset controller end
	<c:if test="${!empty message}">
		alert("<c:out value='${message}'/>");
	</c:if>
	$("#selectall").click(select_all);
}

<%--
/************************************************************************ 
함수명 : select_all                                   
설   명 : 조회된 프로그램에 있는 체크박스를 전체선택/전체해제 시켜주는 함수       
인   자 : 없음        
사용법 : select_all()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

************************************************************************/
--%>
function select_all() {
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
설   명 : 조회된 프로그램에 있는 체크박스를 선택한 후 선택된 프로그램에 대해 삭제를 수행하는 함수       
		삭제할 프로그램에 있는 체크박스를 체크한 후 버튼을 누르면 삭제하시겠습니까 라는 확인 팝업 출력 후
		사용자가 확인을 누르면 삭제가 수행 됨
인   자 : 없음        
사용법 : fn_selective_delete()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형         

      date    author      note 
 ----------   -------     ------------------- 

************************************************************************/
--%>
function fn_selective_delete() {

	var num = 0;
	var checkVal = new Array();
	if($("[name^=chkbox]:checked").length == 0) {
		alert("<spring:message code="common.no.selected" />");
		return;
	}
	$("[name^=chkbox]:checked").each(function(){
		checkVal.push($(this).val());
	})	
	document.listForm.programIdList.value = checkVal;
	if (confirm("<spring:message code="common.delete.msg" />")) {
   		JQ_request("programManageVO", "<c:url value='/gps/adm/progaram/deleteProgramList.do'/>");
	}
}
<%-- 
/************************************************************************ 
함수명 : link_page                                   
설   명 : 페이지 이동 함수
		조회된 목록에서 다른 페이지 번호를 누르면 해당 페이지로 이동
인   자 : pageNo        
사용법 : link_page(pageNo)              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function link_page(pageNo){
	document.listForm.pageIndex.value = pageNo;
	JQ_request("programManageVO", "<c:url value='/gps/adm/program/selectProgramList.do'/>");
}

<%--
/************************************************************************ 
함수명 : fn_search                                   
설   명 : 조회조건에 따라 목록을 조회하는 함수
인   자 : 없음(form안에 존재하는 값)
사용법 : fn_search()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_search(){
	document.listForm.pageIndex.value = 1;
   	JQ_request("programManageVO", "<c:url value='/gps/adm/program/selectProgramList.do'/>", "programManageVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_regist                                   
설   명 : 프로그램 등록 화면으로 이동하는 함수
인   자 : 없음
사용법 : fn_regist()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
 function fn_regist(){
   	JQ_request("programManageVO", "<c:url value='/gps/adm/program/registerProgram.do'/>", "programManageVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_modify                                   
설   명 : 프로그램 상세 화면으로 이동하는 함수
인   자 : 없음
사용법 : fn_detail()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_modify(programId){
 	JQ_setValueObj('programId', programId); 
   	JQ_request("programManageVO", "<c:url value='/gps/adm/program/modifyProgram.do'/>", "programManageVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_excel_download                                   
설   명 : 현재 조회되어있는 프로그램 목록을 엑셀파일형식으로 다운받는 함수
인   자 : 없음
사용법 : fn_excel_download()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_excel_download(){
	JQ_request("programManageVO", "<c:url value='/gps/adm/program/selectProgramExcelDownload.do'/>", "programManageVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_excel_upload                                   
설   명 : 사용자의 엑셀파일을 DB에 업로드시켜주는 함수
인   자 : 없음
사용법 : fn_excel_upload()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_excel_upload(){
	JQ_request("programManageVO", "<c:url value='/gps/adm/program/registerProgramExcelUpload.do'/>", "programManageVO");
}
</script>
<!-- page contents start -->
<div class="contents_area">
	<form:form commandName="programManageVO" name="listForm" method="post">
	<form:hidden path="programId" />
	<form:hidden path="programIdList" />
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
							<td class="title">프로그램관리</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="default">
						<tr>
							<td class="tl">
								<table class="option">
								<tr>
									<td class="first">출력목록수</td>
									<td>
										<select name="pageUnit" onchange="javaScript:fn_search();" >
											<option value="10" <c:if test="${programManageVO.pageUnit == '10'}">selected</c:if>>10</option>
											<option value="20" <c:if test="${programManageVO.pageUnit == '20'}">selected</c:if>>20</option>
											<option value="30" <c:if test="${programManageVO.pageUnit == '30'}">selected</c:if>>30</option>
											<option value="50" <c:if test="${programManageVO.pageUnit == '50'}">selected</c:if>>50</option>
											<option value="100" <c:if test="${programManageVO.pageUnit == '100'}">selected</c:if>>100</option>
										</select>
									</td>
									<td>
										<select name="searchSysId" onchange="javaScript:fn_search();" >
											<option value="">전체</option>
											<c:forEach var="sysInfoList" items="${sysInfo}" varStatus="status">
											<option value="<c:out value="${sysInfoList.sysId}" />" <c:if test="${programManageVO.searchSysId == sysInfoList.sysId}">selected</c:if> ><c:out value="${sysInfoList.sysNm}" /></option>
											</c:forEach>
										</select>
									</td>						
									<td>
										<select name="searchCondition">
										<option value="">- 선택 -</option>
										<option value="1" <c:if test="${programManageVO.searchCondition == '1'}">selected</c:if>>프로그램명</option>
										<option value="2" <c:if test="${programManageVO.searchCondition == '2'}">selected</c:if>>한글명</option>
										</select>						
									</td>
									<td><input name="searchKeyword" type="text" size="35" value="${programManageVO.searchKeyword}"  maxlength="35" id="searchKeyword" ></td>
					    			<td><a onclick="fn_search()" style="cursor:pointer;"><img src="/images/button/0208.png" alt="<spring:message code='button.search'/>" title="<spring:message code='button.search'/>"></a></td>
									<c:if test="${fn:length(resultList) != 0}">
					    				<td><a onclick="fn_excel_download()" style="cursor:pointer;"><img src="/images/button/0613.png" alt="<spring:message code="button.exceldownload" />" title="<spring:message code="button.exceldownload" />"></a></td>
					    			</c:if>
									<td class="end"><a onclick="fn_excel_upload()" style="cursor:pointer;"><img src="/images/button/0705.png" alt="<spring:message code="button.excelupload" />" title="<spring:message code="button.excelupload" />"></a></td>
								</tr>
								</table>
							</td>
							<td>
								<dl class="board_counter">
									<dt class="total">전체</dt>
									<dd class="total"><c:out value='${paginationInfo.totalRecordCount}'/></dd>
									<dt class="page">페이지</dt>
									<dd class="page"><c:out value='${paginationInfo.currentPageNo}'/> / <c:out value='${paginationInfo.totalPageCount }'/></dd>
								</dl>
							</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="datalist01" summary="프로그램관리">
						<caption>프로그램리스트</caption>
						<colgroup>
							<col class="column10"/>
							<col class="column10"/>
							<col class="column2"/>
							<col class="column2"/>
							<col class="column2"/>
							<col class="column2"/>
							<col class="column2"/>
							<col class="column10"/>
							<col class="column10"/>
							<col class="column10"/>
							<col class="column10"/>
						</colgroup>
						<thead>
						<tr>
							<th scope="col"><input type="checkbox" id="selectall" title="전체선택" class="noline"></th>
							<th scope="col">번호</th>
							<th scope="col">시스템명</th>
							<th scope="col">프로그램코드</th>
							<th scope="col">프로그램명</th>
							<th scope="col">프로그램한글명</th>
							<th scope="col">프로그램URL</th>
							<th scope="col">읽기</th>
							<th scope="col">등록</th>
							<th scope="col">수정</th>
							<th scope="col" class="end">삭제</th>
						</tr>
						</thead>
						<tbody>
					<c:forEach var="resultInfo" items="${resultList}" varStatus="status">
						<tr onmouseover="this.style.backgroundColor='#fafafa'" onmouseout="this.style.backgroundColor=''">
							<td class="column10"><input type="checkbox" name="chkbox" value="${resultInfo.programId}" class="noline"/></td>
							<td class="column10"><c:out value="${paginationInfo.totalRecordCount - ((programManageVO.pageIndex - 1) * programManageVO.pageSize + (status.count-1))}"/></td>
							<td class="column2"><c:out value="${resultInfo.sysNm}"/></td>
							<td class="column2"><c:out value="${resultInfo.programStrePath}"/></td>
							<td class="column2" style="cursor:hand" onclick="javascript:fn_modify('${resultInfo.programId}');" >${resultInfo.programFileNm}</td>
							<td class="column2"><c:out value="${resultInfo.programKoreannm}"/></td>
							<td class="column2"><c:out value="${resultInfo.url}"/></td>
							<td class="column10"><c:if test="${resultInfo.programTyRead eq 'Y'}"><img src="/images/gps/cmm/icon/o.gif"></c:if><c:if test="${resultInfo.programTyRead ne 'N'}"><img src="/images/gps/cmm/icon/x.gif"></c:if></td>
							<td class="column10"><c:if test="${resultInfo.programTyCreate eq 'Y'}"><img src="/images/gps/cmm/icon/o.gif"></c:if><c:if test="${resultInfo.programTyCreate ne 'N'}"><img src="/images/gps/cmm/icon/x.gif"></c:if></td>
							<td class="column10"><c:if test="${resultInfo.programTyUpdate eq 'Y'}"><img src="/images/gps/cmm/icon/o.gif"></c:if><c:if test="${resultInfo.programTyUpdate ne 'N'}"><img src="/images/gps/cmm/icon/x.gif"></c:if></td>
							<td class="column10"><c:if test="${resultInfo.programTyDelete eq 'Y'}"><img src="/images/gps/cmm/icon/o.gif"></c:if><c:if test="${resultInfo.programTyDelete ne 'N'}"><img src="/images/gps/cmm/icon/x.gif"></c:if></td>
						</tr>
					 </c:forEach>
					<c:if test="${fn:length(resultList) == 0}">
						<tr>
							<td class="blank" colspan="10">
								<spring:message code="common.nodata.msg" />
							</td>
						</tr>
					</c:if>
						</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="datapaging" align="center">
						<tr>
							<td class="tc">
								<ui:pagination paginationInfo = "${paginationInfo}"	type="image" jsFunction="link_page" />
								<input name="pageIndex" type="hidden" value="<c:out value='${programManageVO.pageIndex}'/>"/>
							</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="rbtn">
						<table class="inner_btn" align="right">
						<tr>
							<td><a onclick="fn_regist()" style="cursor:pointer;"><img src="/images/button/0202.png" alt="<spring:message code='button.add'/>" title="<spring:message code='button.add'/>"></a></td>
							<td><a onclick="fn_selective_delete()" style="cursor:pointer;"><img src="/images/button/0428.png" alt="<spring:message code="button.selectedToDelete" />" title="<spring:message code="button.selectedToDelete" />"></a></td>
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
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>