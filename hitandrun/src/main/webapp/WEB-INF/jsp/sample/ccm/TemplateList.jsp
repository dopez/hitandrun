<%-- 
/** 
 * outline   : 공통 탬플릿 게시판 목록 조회화면
 * filename : TemplateList.jsp
 * @author 기술지원 김일수 
 * @since 2011.05.10 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.05.10     김일수           최초 생성 
 * </pre> 
 */
--%>

<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>


<%-- Header Start ==========================================================--%>
<jsp:include page="/sps/cmm/header.do" flush="false"/>
<script src="/js/common/jquery/jquery.contextMenu.js" type="text/javascript"></script> 
<%-- Header Start ==========================================================--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<title>템플릿게시판 목록화면</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">

<%-- stylesheet Start	======================================================--%>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/common/jquery/validationEngine.jquery.css'/>" media="screen" title="no title" charset="utf-8" />

<link rel="stylesheet" type="text/css" href="/css/common/jquery/jquery.ui.all.css" />
<link rel="stylesheet" type="text/css" href="/css/common/jquery/layout-default-latest.css" />
<link rel="stylesheet" type="text/css" href="/css/common/jquery/jquery.ui.theme.css" />
<link rel="stylesheet" type="text/css" href="/css/common/jquery/jquery.contextMenu.css" />
<%-- stylesheet End	========================================================--%>

<%-- javascript start ==============================================--%>

<%-- javascript end ============================================--%>
<script type="text/javaScript" language="javascript">

<!--

JQ_setValidation('searchVO');
JQ_setCalendar('fromDate');
JQ_setCalendar('toDate');

JQ_onload(); 

<%-- 
/************************************************************************ 
fnc name : fncPageOnload                                   
outline : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
parameter : 없음        
directions : fncPageOnload()              
since : 2011-05-10   
author : 기술지원 김일수       

    date      author             note  
 ----------   -------     ------------------- 
 2011.05.10    김일수              프레임 처리 추가                                       
************************************************************************/
--%>
function fncPageOnload()
{

	//frameset controller start
	parent.frmtop.setFrameCtl();
	//frameset controller end
	$("#selectall").click(select_all);
	

	// Show menu when #myDiv is clicked
	$("#templateList").contextMenu({
		menu: 'ctxMenu'
	},
		function(action, el, pos) {
		if( action == "edit" ) {
			fn_excel_download();
		} else {
			//alert(
			//'Action: ' + action + '\n\n' +
			//'Element ID: ' + $(el).attr('id') + '\n\n' + 
			//'X: ' + pos.x + '  Y: ' + pos.y + ' (relative to element)\n\n' + 
			//'X: ' + pos.docX + '  Y: ' + pos.docY+ ' (relative to document)'
			//);
		}
	});

	$("#searchKeyword").focus();

}

<%--
/************************************************************************ 
fnc name : select_all                                   
outline : 조회된 게시물에 있는 체크박스를 전체선택/전체해제 시켜주는 함수       
parameter : 없음        
directions : select_all()              
since : 2011-05-10   
author : 기술지원 김일수       

    date      author             note  
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
fnc name : fn_selective_delete                                   
outline : 조회된 게시물에 있는 체크박스를 선택한 후 선택된 게시물에 대해 삭제를 수행하는 함수       
		삭제할 게시물에 있는 체크박스를 체크한 후 버튼을 누르면 삭제하시겠습니까 라는 확인 팝업 출력 후
		사용자가 확인을 누르면 삭제가 수행 됨
parameter : 없음        
directions : fn_selective_delete()              
since : 2011-05-10   
author : 기술지원 김일수       

    date      author             note  
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
	document.listForm.templateIdList.value = checkVal;
	if (confirm("<spring:message code="common.delete.msg" />")) {
		JQ_setProcessMsg();
   		JQ_request("searchVO", "<c:url value='/sample/tem/deleteTemplateList.do'/>");
	}
}

<%-- 
/************************************************************************ 
fnc name : link_page                                   
outline : 페이지 이동 함수
		조회된 목록에서 다른 페이지 번호를 누르면 해당 페이지로 이동
parameter : pageNo        
directions : link_page(pageNo)              
since : 2011-05-10   
author : 기술지원 김일수       

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function link_page(pageNo){
	document.listForm.pageIndex.value = pageNo;
	JQ_setProcessMsg();
	JQ_request("searchVO", "<c:url value='/sample/tem/selectTemplateList.do'/>");
}

<%--
/************************************************************************ 
fnc name : fn_search                                   
outline : 조회조건에 따라 목록을 조회하는 함수
parameter : 없음(form안에 존재하는 값)
directions : fn_search()              
since : 2011-05-10   
author : 기술지원 김일수       

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_search(){
	document.listForm.pageIndex.value = 1;
	if( JQ_dayBetweenValidate( 'fromDate', 'toDate' ) ) return;

	JQ_setProcessMsg();
   	JQ_request("searchVO", "<c:url value='/sample/tem/selectTemplateList.do'/>", "searchVO");
}

<%-- 
/************************************************************************ 
fnc name : fn_regist                                   
outline : 게시물 등록 화면으로 이동하는 함수
parameter : 없음
directions : fn_regist()              
since : 2011-05-10   
author : 기술지원 김일수       

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
 function fn_regist(){
	
   	JQ_request("searchVO", "<c:url value='/sample/tem/registerTemplate.do'/>", "searchVO");
}

<%-- 
/************************************************************************ 
fnc name : fn_detail                                   
outline : 게시물 상세 화면으로 이동하는 함수
parameter : 없음
directions : fn_detail()              
since : 2011-05-10   
author : 기술지원 김일수       

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_detail(templateId){
 	JQ_setValueObj('templateId', templateId); 
 	
   	JQ_request("searchVO", "<c:url value='/sample/tem/selectTemplateDetail.do'/>", "searchVO");
}

<%-- 
/************************************************************************ 
fnc name : fn_excel_download                                   
outline : 현재 조회되어있는 게시물 목록을 엑셀파일형식으로 다운받는 함수
parameter : 없음
directions : fn_excel_download()              
since : 2011-05-10   
author : 기술지원 김일수       

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_excel_download(){
	if (confirm("<spring:message code="common.exceldownload.msg" />")){
		JQ_request("searchVO", "<c:url value='/sample/tem/selectTemplateExcelDownload.do'/>", "searchVO");
	}
}

<%-- 
/************************************************************************ 
fnc name : fn_excel_upload                                   
outline : 사용자의 엑셀파일을 DB에 업로드시켜주는 함수
parameter : 없음
directions : fn_excel_upload()              
since : 2011-05-10   
author : 기술지원 김일수       

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_excel_upload(){
	JQ_request("searchVO", "<c:url value='/sample/tem/registerTemplateExcelUpload.do'/>", "searchVO");
}
-->
</script>
</head>
<body>

<div class="ui-layout-content">
	<!-- page contents start -->
	<div class="center_contents_area">
		
		<!-- page title / frameset controller start -->
		<div class="page_subject">
			<div id="page_title" class="page_title">게시판</div>
			<div class="frmctl" id="frmctl"></div>
		</div>
		<!-- page title / frameset controller end -->
			
        <div class="center_inner_contents" id="contents_area">

<form:form commandName="searchVO" name="listForm" method="post">
<form:hidden path="templateId" />
<form:hidden path="templateIdList" />

<table class="rbuttonarea">
<tr>
	<td>
		<table class="title">
		<tr>

			<td class="rbtn">
				<table class="inner_btn">
				<tr>
					<td><img src="/images/button/0428.png" alt="<spring:message code="button.selectedToDelete" />" title="<spring:message code="button.selectedToDelete" />" style="cursor:hand" onclick="fn_selective_delete()"></td>
					<td><img src="/images/button/0613.png" alt="<spring:message code="button.exceldownload" />" title="<spring:message code="button.exceldownload" />" style="cursor:hand" onclick="fn_excel_download()"></td>
					<td><img src="/images/button/0705.png" alt="<spring:message code="button.excelupload" />" title="<spring:message code="button.excelupload" />" style="cursor:hand" onclick="fn_excel_upload()"></td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
<table class="default">
<tr>
	<td>
		<table class="default">
		<tr>
			<td width="80%" class="tl">
				<table class="option">
					<tr>
						
						<td>
						   <form:select path="searchCondition" >
                              <form:option value="" label="선택하세요"/>
                              <form:options items="${searchCondition}" />
                            </form:select>
						
							
						</td>
						<td>
							<input name="searchKeyword" type="text" size="35" value="${searchVO.searchKeyword}"  maxlength="35" id="searchKeyword" 
							onfocus="gfn_focus(this);" onblur="gfn_focusnot(this);" onkeypress="javascript:if(event.keyCode == 13) {fn_search();}">
						</td>
						<td>조회기간 </td>
				        <td>
				        <form:input path="fromDate" cssStyle="vertical-align:middle" size="10" cssClass="input_t input_nara_date" onfocus="gfn_focus(this);" onblur="gfn_focusnot(this);"/>
		        
				        ~
				        
						<form:input path="toDate"   cssStyle="vertical-align:middle" size="10" cssClass="input_t input_nara_date" onfocus="gfn_focus(this);" onblur="gfn_focusnot(this);"/>
							
					    </td>
					    <td><img src="/images/button/0221.png" alt="<spring:message code='button.inquire'/>" title="<spring:message code='button.inquire'/>" style="cursor:hand" onclick="fn_search()"></td> 
						<td><img src="/images/button/0202.png" alt="<spring:message code='button.create'/>" title="<spring:message code='button.create'/>" style="cursor:hand" onclick="fn_regist()"></td>
				        
					</tr>
				</table>
			</td>
			<td width="50%">
				<dl class="board_counter">
					<dt class="total"><spring:message code='common.template.total'/></dt>
					<dd class="total"><c:out value='${paginationInfo.totalRecordCount}'/></dd>
					<dt class="page"><spring:message code='common.template.page'/></dt>
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
		<table id="templateList" class="datalist01" summary="게시판 템플릿 입니다..">
			<colgroup>
				<col class = "column1"/>
				<col class = "column3"/>
				<col class = "column3"/>
				<col class = "column3"/>
				<col class = "column3"/>
				<col class = "column3"/>
				<col class = "column3"/>
			</colgroup> 
			<thead>
				<tr>  
					<th scope="col" width="3%">
					<input type="checkbox" id="selectall" class="check" title="전체선택" ></th>  
					<th scope="col" width="5%"><spring:message code='common.template.rowNumber'/></th>
					<th scope="col" width="10%" ><spring:message code='common.template.listID'/></th>
					<th scope="col"><spring:message code='common.template.title'/></th>
					<th scope="col" width="10%"><spring:message code='common.template.regUser'/></th>
					<th scope="col" width="10%"><spring:message code='common.template.regDate'/></th>
					<th scope="col" width="10%"><spring:message code='common.template.updateDate'/></th>
					<th scope="col" width="5%" class="end"><spring:message code='common.template.hasFile'/></th>
				</tr>
			</thead>    
			<tbody>
				<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
					<tr onMouseDown="this.style.background='#AFE8F7'"  onMouseOver="this.style.background='#B5C8EF'" onmouseout="this.style.background=''">
						<td class = "column3" ><input type="checkbox" name="chkbox" value="${resultInfo.templateId}"/></td>
						<td class = "column3" ><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
						<td class = "column3" >${resultInfo.templateId}</td>
						<td class = "column3" align="left" style="cursor: hand" onclick="javascript:fn_detail('${resultInfo.templateId}');" >${resultInfo.templateTitle}</td>
						<td class = "column3" >${resultInfo.templateRegister}</td>
						<td class = "column3" >${resultInfo.templateRegistDe}</td>
						<td class = "column3" >${resultInfo.templateUpdtDe}</td>
						<td class = "column3" >${resultInfo.templateHasfile}</td>
					</tr>
				</c:forEach>
				<c:if test="${fn:length(resultList) == 0}">
					<tr> 
						<td class="lt_text3" colspan=4>
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
				<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>
</form:form>
</div>

</div>
</div>
<ul id="ctxMenu" class="contextMenu">
			<li class="edit"><a href="#edit">엑셀다운로드</a></li>			
			<li class="quit separator"><a href="#quit">닫기</a></li>
</ul>
</body>
</html>
