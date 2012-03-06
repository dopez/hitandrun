<%-- 
/** 
 * 설명   : 만족도관리 - 만족도조사 결과보기 화면
 * 파일명 : csnstRspns.jsp
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
	JQ_setValidation('csnstMenoVO');
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
	<c:if test="${!empty message}">
		alert('<c:out value = "${message}"/>');
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
	document.csnstRspnsForm.memoSnList.value = checkVal;
	if (confirm("<spring:message code="common.delete.msg" />")) {
   		JQ_request("csnstMenoVO", "<c:url value='/gps/adm/csnst/deleteCsnstMemoList.do'/>");
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
	document.csnstRspnsForm.pageIndex.value = pageNo;
 	JQ_setValueObj('csnstId', $('#csnstId').val());
 	JQ_setValueObj('csnstSn', $('#csnstSn').val());
 	JQ_setValueObj('qesitmSn', $('#qesitmSn').val());
	JQ_request("csnstMenoVO", "<c:url value='/gps/adm/csnst/csnstRspnsResult.do'/>");
}

</script>

<!-- contents_area start -->
<div class="contents_area">
	<div id="layer_csnst">
		<form:form commandName="csnstMenoVO" name="csnstRspnsForm" method="post" enctype="multipart/form-data">
		<form:hidden path="sysId"/>
		<form:hidden path="csnstId"/>
		<form:hidden path="csnstSn"/>
		<form:hidden path="qesitmSn"/>
		<form:hidden path="memoSnList"/>
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
									<c:out value="${rsnpnsResultVO.csnstNm}"/> 결과
								</td>
							</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table summary="만족도조사 결과" class="write01">
							<caption>
								만족도조사 결과
							</caption>
								<tr>
									<th class="subject">만족도 조사 명</th>
									<td class="input">
										<c:out value="${rsnpnsResultVO.csnstNm}"/>
									</td>
								</tr>
								<tr>
									<th class="subject">만족도 조사 문항</th>
									<td class="text">
										<c:out value="${rsnpnsResultVO.qesitmQestnNm}"/>
									</td>
								</tr>
								<tr>
									<th class="subject">만족도 조사 결과</th>
									<td>
										<table class="inside01">
											<c:forEach var="resultInfo" items="${iemNmList}" varStatus="status">
												<tr>
													<td class="input">
														<c:out value="${resultInfo.iemNm}"></c:out>
													</td>
													<td class="text">
														<c:choose>
															<c:when test="${resultInfo.rspnsTotal > 0}">
																<img src="/images/button/0101.png" title="${resultInfo.rspnsCnt div resultInfo.rspnsTotal * 100}%" alt="${resultInfo.rspnsCnt div resultInfo.rspnsTotal * 100}%" height="10" width="${resultInfo.rspnsCnt / resultInfo.rspnsTotal * 100}%">
																<c:out value="${resultInfo.rspnsCnt div resultInfo.rspnsTotal * 100}%" />
															</c:when>
															<c:otherwise>
																<img src="/images/button/0101.png" title="${resultInfo.rspnsTotal}%" alt="${resultInfo.rspnsTotal}%" height="10" width="${resultInfo.rspnsTotal}%">
																<c:out value="${resultInfo.rspnsTotal}%" />
															</c:otherwise>
														</c:choose>
		
													</td>
												</tr>
											</c:forEach>
										<c:if test="${fn:length(iemNmList) == 0}">
											<tr>
												<td class="blank" colspan="10">
													확인 불가능한 만족도 조사입니다. 1.만족도조사 - 2.만족도 조사 문항 - 3.만족도 조사항목 을 등록해주세요.
												</td>
											</tr>
										</c:if>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<c:if test="${fn:length(memoList) > 0}">
						<tr>
							<td>
								<table class="datalist01" summary="메모리스트">
								<caption>메모리스트</caption>
								<colgroup>
									<col class="column10"/>
									<col class="column1"/>
									<col class="column2"/>
									<col class="column3"/>
									<col class="column10"/>
								</colgroup>
								<thead>
								<tr>
									<th scope="col"><input type="checkbox" id="selectall" title="전체선택" class="noline"></th>
									<th scope="col">번호</th>
									<th scope="col">메모</th>
									<th scope="col">등록일</th>
									<th scope="col" class="end">등록IP</th>
								</tr>
								</thead>
								<tbody>
							<c:forEach var="resultInfo" items="${memoList}" varStatus="status">
								<tr onmouseover="this.style.backgroundColor='#fafafa'" onmouseout="this.style.backgroundColor=''">
									<td class="column10"><input type="checkbox" name="chkbox" value="${resultInfo.memoSn}" class="noline"/></td>
									<td class="column1"><c:out value="${paginationInfo.totalRecordCount - ((csnstMenoVO.pageIndex - 1) * csnstMenoVO.pageSize + (status.count-1))}"/></td>
									<td class="column2"><c:out value="${resultInfo.memoCn}"/></td>
									<td class="column3"><c:out value="${resultInfo.registDt}"/></td>
									<td class="column10"><c:out value="${resultInfo.registerIp}"/></td>
			
								</tr>
							</c:forEach>
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
										<input name="pageIndex" type="hidden" value="<c:out value='${csnstMenoVO.pageIndex}'/>"/>
									</td>
								</tr>
								</table>
							</td>
						</tr>
					</c:if>
					<tr>
						<td>
							<table class="rbuttonarea">
							<tr>
								<c:if test="${fn:length(memoList) > 0}">
									<td><a onclick="fn_selective_delete()" style="cursor:hand"><img src="/images/button/0428.png" alt="<spring:message code="button.selectedToDelete" />" title="<spring:message code="button.selectedToDelete" />"></a></td>
								</c:if>
								<td class="end"><a onclick="window.close();" style="cursor:hand"><img src="/images/button/0205.png" alt="<spring:message code='button.close'/>" title="<spring:message code='button.close'/>"></a></td>
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
</div>
<!-- container end -->
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>