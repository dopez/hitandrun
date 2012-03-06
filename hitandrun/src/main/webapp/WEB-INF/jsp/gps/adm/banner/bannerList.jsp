<%-- 
/** 
 * outline   : 배너관리 - 배너목록 조회화면
 * filename : bannerList.jsp
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

JQ_setValidation('bannerManageVO');
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
	$("#selectall").click(select_all);
}

<%--
/************************************************************************ 
함수명 : select_all                                   
설   명 : 조회된 배너에 있는 체크박스를 전체선택/전체해제 시켜주는 함수       
인   자 : 없음        
사용법 : select_all()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

************************************************************************/
--%>
function select_all()
{
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
설   명 : 조회된 배너에 있는 체크박스를 선택한 후 선택된 배너에 대해 삭제를 수행하는 함수       
		삭제할 배너에 있는 체크박스를 체크한 후 버튼을 누르면 삭제하시겠습니까 라는 확인 팝업 출력 후
		사용자가 확인을 누르면 삭제가 수행 됨
인   자 : 없음        
사용법 : fn_selective_delete()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형         

      date    author      note 
 ----------   -------     ------------------- 

************************************************************************/
--%>
function fn_selective_delete()
{

	var num = 0;
	var checkVal = new Array();
	$("[name^=chkbox]:checked").each(function(){
		checkVal.push($(this).val());
	})	
	document.listForm.bannerSnList.value = checkVal;
	if (confirm("<spring:message code="common.delete.msg" />")) {
   		JQ_request("bannerManageVO", "<c:url value='/gps/adm/progaram/deleteBannerList.do'/>");
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
	JQ_request("bannerManageVO", "<c:url value='/gps/adm/banner/selectBannerList.do'/>");
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
 	JQ_setValueObj('pageUnit', document.listForm.pageUnit.value);
   	JQ_request("bannerManageVO", "<c:url value='/gps/adm/banner/selectBannerList.do'/>", "bannerManageVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_regist                                   
설   명 : 배너 등록 화면으로 이동하는 함수
인   자 : 없음
사용법 : fn_regist()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
 function fn_regist(){
   	JQ_request("bannerManageVO", "<c:url value='/gps/adm/banner/registerBanner.do'/>", "bannerManageVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_modify                                   
설   명 : 배너 수정 화면으로 이동하는 함수
인   자 : 없음
사용법 : fn_modify()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_modify(bannerSn,sysId){
 	JQ_setValueObj('bannerSn', bannerSn);
 	JQ_setValueObj('sysId', sysId); 
   	JQ_request("bannerManageVO", "<c:url value='/gps/adm/banner/modifyBanner.do'/>", "bannerManageVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_order                                   
설   명 : 배너 순서변경 화면으로 이동하는 함수
인   자 : 없음
사용법 : fn_order()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
 function fn_order(){
	 gfn_postPopupWin("bannerManageVO","<c:url value='/gps/adm/banner/bannerOrderBy.do'/>","배너관리 순서변경",400,400,"no","no",0);
}
</script>
<!-- page contents start -->
<div class="contents_area">
	<form:form commandName="bannerManageVO" name="listForm" method="post">
	<form:hidden path="bannerSn" />
	<form:hidden path="sysId" />
	<form:hidden path="bannerSnList" />
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
						<td class="title">배너관리</td>
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
										<option value="10" <c:if test="${bannerManageVO.pageUnit == '10'}">selected</c:if>>10</option>
										<option value="20" <c:if test="${bannerManageVO.pageUnit == '20'}">selected</c:if>>20</option>
										<option value="30" <c:if test="${bannerManageVO.pageUnit == '30'}">selected</c:if>>30</option>
										<option value="50" <c:if test="${bannerManageVO.pageUnit == '50'}">selected</c:if>>50</option>
										<option value="100" <c:if test="${bannerManageVO.pageUnit == '100'}">selected</c:if>>100</option>
									</select>
								</td>
								<td>
									<select name="searchSysId" onchange="javaScript:fn_search();" >
										<option value="">전체</option>
										<c:forEach var="sysInfoList" items="${sysInfo}" varStatus="status">
										<option value="<c:out value="${sysInfoList.sysId}" />" <c:if test="${bannerManageVO.searchSysId == sysInfoList.sysId}">selected</c:if> ><c:out value="${sysInfoList.sysNm}" /></option>
										</c:forEach>
									</select>
								</td>
								<td>
									<form:select path="actvtyAtSearch" onchange="javaScript:fn_search();">
									<form:option value="" label="- 선택 -"/>
									<form:option value="Y" label="사용"/>
									<form:option value="N" label="사용안함"/>
									</form:select>
								</td>
								<td>
									<form:select path="searchCondition">
									<form:option value="" label="- 선택 -"/>
									<form:option value="1" label="배너명"/>
									</form:select>
								</td>
								<td><input name="searchKeyword" type="text" size="35" value="${bannerManageVO.searchKeyword}" maxlength="35" id="searchKeyword" ></td>
								<td><a onclick="fn_search()"><img src="/images/button/0208.png" alt="<spring:message code='button.search'/>" title="<spring:message code='button.inquire'/>"></a></td>
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
			<tr><td class="ht2"></td></tr>
			<tr>
				<td>
					<table class="datalist01" summary="배너관리">
					<caption>배너리스트</caption>
					<colgroup>
						<col class="column10" />
						<col class="column10" />
						<col class="column2" />
						<col class="column3" />
						<col class="column5" />
						<col class="column3" />
						<col class="column2" />
						<col class="column3" />
						<col class="column3" />
						<col class="column3" />
						<col class="column3" />
					</colgroup> 
					<thead>
					<tr>
						<th scope="col"><input type="checkbox" id="selectall" class="noline" title="전체선택" ></th>  
						<th scope="col">번호</th>
						<th scope="col">시스템구분</th>
						<th scope="col">위치</th>
						<th scope="col">정렬순서</th>
						<th scope="col">사용여부</th>
						<th scope="col">배너명</th>
						<th scope="col">이미지</th>
						<th scope="col">등록자</th>
						<th scope="col">시작일</th>
						<th scope="col" class="end">종료일</th>
					</tr>
					</thead>
					<tbody>
				<c:forEach var="resultInfo" items="${resultList}" varStatus="status">
					<tr onmouseover="this.style.backgroundColor='#fafafa'" onmouseout="this.style.backgroundColor=''">
						<td class="column10"><input type="checkbox" name="chkbox" value="${resultInfo.sysId}^${resultInfo.bannerSn}" class="noline" /></td>
						<td class="column10"><c:out value="${(bannerManageVO.pageIndex - 1) * bannerManageVO.pageUnit + status.count}"/></td>
						<td class="column2"><c:out value="${resultInfo.sysNm}"/></td>
						<td class="column3"><c:out value="${resultInfo.lc}"/></td>
						<td class="column5"><c:out value="${resultInfo.ordr}"/></td>
						<td class="column3"><c:if test="${resultInfo.actvtyAt eq 'Y'}"><img src="/images/gps/cmm/icon/o.gif"></c:if><c:if test="${resultInfo.actvtyAt eq 'N'}"><img src="/images/gps/cmm/icon/x.gif"></c:if></td>
						<td class="column2"><a onclick="javascript:fn_modify('${resultInfo.bannerSn}','${resultInfo.sysId}');" style="cursor: pointer;"><c:out value="${resultInfo.nm}"/></a></td>
						<td class="column3">
							<c:choose>
								<c:when test="${not empty resultInfo.logoImageFileMask}">
									<img src='${WebImagePath}?imageid=web/gps/banner/${resultInfo.logoImageFileMask}&ext=${resultInfo.logoImageFileMime}' alt='${resultInfo.nm}' title='${resultInfo.nm}' width='140' height='40' border='0'/>
								</c:when>
								<c:otherwise>
									<img src='/images/gps/cmm/icon/x.gif' alt='배너 이미지 없음' title='배너 이미지 없음' border='0'/>
								</c:otherwise>
							</c:choose>
						</td>
						<td class="column3"><c:out value="${resultInfo.registerId}"/></td>
						<td class="column3"><c:out value="${resultInfo.bgnde}"/></td>
						<td class="column3"><c:out value="${resultInfo.endde}"/></td>
					</tr>
				</c:forEach>
				<c:if test="${fn:length(resultList) == 0}">
					<tr> 
						<td class="blank" colspan="11"><spring:message code="common.nodata.msg" /></td>
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
							<input name="pageIndex" type="hidden" value="<c:out value='${bannerManageVO.pageIndex}'/>"/>
						</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table class="rbuttonarea">
					<tr>
						<td><a onclick="fn_regist()"><img src="/images/button/0202.png" alt="<spring:message code='button.create'/>" title="<spring:message code='button.create'/>"></a></td>
						<!--
						<td><a onclick="fn_order()"><img src="/images/button/0426.png" alt="선택보임" title="선택보임"></a></td>
						<td><a onclick="fn_order()"><img src="/images/button/0427.png" alt="선택숨김" title="선택숨김"></a></td>
						 -->
						<td class="end"><a onclick="fn_selective_delete()"><img src="/images/button/0428.png" alt="<spring:message code='button.selectedToDelete'/>" title="<spring:message code='button.selectedToDelete'/>"></a></td>
						<td><a onclick="fn_order()"><img src="/images/button/0429.png" alt="순서변경" title="순서변경"></a></td>
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