<%-- 
/** 
 * outline   : 코드관리 - 분류코드목록 조회화면
 * filename : grpCodeList.jsp
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
function fncPageOnload()
{
	//frameset controller start
	//frameset controller end
	//parent.subCodeFrame.location.reload();
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
		parent.subCodeFrame.location.href = "/gps/adm/code/modifyGrpCode.do?codeId=" + codeId;
}
 
<%-- 
 /************************************************************************ 
 함수명 : fn_regist                                   
 설   명 : 코드 검색 화면으로 이동하는 함수
 인   자 : 없음
 사용법 : fn_regist()              
 작성일 : 2011-06-17   
 작성자 : 범정부통계포털 이관형       

       date    author      note 
  ----------   -------     ------------------- 

  ************************************************************************/
--%>
  function fn_regist(){
	if (document.gclForm.upperCodeId.value == '') {
		 alert('시스템 구분을 선택해주세요.');	
		 return false;
	} else {
		parent.subCodeFrame.location.href = "/gps/adm/code/registerGrpCode.do?upperCodeId=" + document.gclForm.upperCodeId.value;
	}
 }
  
<%-- 
/************************************************************************ 
함수명 : fn_search                                   
설   명 : 코드 리스트 화면으로 이동하는 함수
인   자 : 없음
사용법 : fn_search()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
 function fn_search(codeId){
	 if (codeId == '') {
	 	JQ_setValueObj('upperCodeId', document.gclForm.upperCodeId.value); 
		JQ_request("codeManageVO", "<c:url value='/gps/adm/code/selectGrpCodeList.do'/>");
		parent.subCodeFrame.location.href = "/gps/adm/code/selectCodeList.do";
	 } else {
		parent.subCodeFrame.location.href = "/gps/adm/code/selectCodeList.do?upperCodeId=" + codeId;
	 }

}
</script>
<%-- javascript end ============================================--%>
<!-- page contents start -->
<div class="contents_area">
	<form:form commandName="codeManageVO" name="gclForm" method="post" action="/gps/adm/code/selectGrpCodeList.do">
	<form:hidden path="codeId" />
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
						<td class="select">
							<form:select id="upperCodeId" path="upperCodeId" onchange="javaScript:fn_search('');">
								<form:option value="" label="전체코드"/>
								<form:options items="${upperCodeList}" />
							</form:select>
						</td>
						<td class="rbtn">
							<table class="inner_btn">
							<tr>
								<td><a id="codeRst"><img src="/images/button/0202.png" alt="<spring:message code='button.add'/>" title="<spring:message code='button.add'/>" style="cursor:pointer;" onclick="fn_regist()" /></a></td>
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
					<col class="column11" />
					<col class="column2" />
					<col class="column10" />
				</colgroup>
				<thead>
					<tr>
						<th scope="col">분류</th>
						<th scope="col">코드명</th>
						<th scope="col" class="end">사용</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="resultInfo" items="${codeList}" varStatus="status">
						<tr onmouseover="this.style.backgroundColor='#fafafa'" onmouseout="this.style.backgroundColor=''">
							<td class="column11"><a onclick="javascript:fn_modify('${resultInfo.codeId}');"><c:out value="${resultInfo.useCodeId}" /></a></td>
							<td class="column2"><a onclick="javascript:fn_search('${resultInfo.codeId}');"><c:out value="${resultInfo.codeNm}" /></a></td>
							<td class="column10"><c:if test="${resultInfo.fncValAt eq 'Y'}"><img src="/images/gps/cmm/icon/o.gif"></c:if><c:if test="${resultInfo.fncValAt eq 'N'}"><img src="/images/gps/cmm/icon/x.gif"></c:if></td>
						</tr>
					</c:forEach>
					<c:if test="${fn:length(codeList) == 0}">
						<tr>
							<td class="blank" colspan="3"><spring:message code="common.nodata.msg" /></td>
						</tr>
					</c:if>
				</tbody>
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