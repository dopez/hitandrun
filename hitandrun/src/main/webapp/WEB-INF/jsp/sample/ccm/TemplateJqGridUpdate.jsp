<%-- 
/** 
 * outline   : 공통 탬플릿 게시판 상세내용을 수정하는 화면
 * filename : TemplateUpdate.jsp
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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<title>템플릿게시판 수정화면- (JqGrid)</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">

<jsp:include page="/sps/cmm/header.do" flush="false"/> 

<script type="text/javascript" src="<c:url value='/js/common/fms/JqGridMultiFile.js'/>" ></script>

<%-- stylesheet Start	======================================================--%>
<link rel="stylesheet" type="text/css" href="/css/common/jquery/jquery.ui.all.css" />
<link rel="stylesheet" type="text/css" href="/css/common/jquery/layout-default-latest.css" />
<link rel="stylesheet" type="text/css" href="/css/common/jqgrid/jquery-ui-1.8.2.custom.css" />
<link rel="stylesheet" type="text/css" href="/css/common/jqgrid/ui.jqgrid.css" />
<%-- stylesheet End	========================================================--%>

<%-- javascript start ==============================================--%>
<script type="text/javascript" src="/js/sps/sds/cmm/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="/js/common/jquery/jquery.layout-latest.js"></script>
<script type="text/javascript" src="/js/common/jquery/debug.js"></script>
<script type="text/javascript" src="/js/common/jquery/themeswitchertool.js"></script>	 
<script type="text/javascript" src="/js/common/jqgrid/grid.locale-en.js"></script>
<script type="text/javascript" src="/js/common/jqgrid/jquery.jqGrid.MultiHeader.min.js"></script>
<script type="text/javascript" src="/js/common/jqgrid/grid.paste.js"></script>
<script type="text/javascript" src="/js/common/jqgrid/json2.js"></script>
<script type="text/javascript" src="/js/common/jqgrid/jquery.jqGrid.init.js"></script>
<script type="text/javascript" src="/js/common/jquery/jquery.blockUI.js"></script>
<script type="text/javascript" src="/js/common/jquery/jquery.popupWindow.js"></script>
<%-- javascript end ============================================--%>


<script type="text/javaScript" language="javascript">

var	curRow;
var	curCol;
var	gridwidth =	0;

$(document).ready(function(){
	
	gridwidth =$("#cbfile").width() * 0.99;

	$("#addFile").jqGrid({
		datatype:'json',
		url:'/cmm/fms/selectFileInfsJqGrid.do?param_atchFileId='+'${templateVO.templateFileid2}',
		jsonReader:{
			root: "resultList",
			page: "paginationInfo.currentPageNo",
			total: "paginationInfo.totalPageCount",
			records: "paginationInfo.totalRecordCount",
			repeatitems: false,
			cell:"rnum"
		},
		//data:	mydata,
		colNames:['파일명', "파일번호", "순번", "id"],
		colModel:[
			{name:'orignlFileNm',index:'orignlFileNm',align:'left',sorttype: 'string'},
			{name:'fileSn',index:'fileSn',align:'left',sorttype: 'string', hidden:'true'},
			{name:'atchFileId',index:'atchFileId',align:'left',sorttype: 'string', hidden:'true'},
			{name:'id',index:'id',align:'left',sorttype: 'string', hidden:'true'}
			 ],
		cellEdit:true,
		gridview:true,
		sortname: 'url',
		viewrecords: true,
		sortorder: 'desc',
		multiselect: true,
		height:	'100',
		width:gridwidth,
		onCellSelect: function(aRowId, aCol, aData)	{
			curRow = aRowId;
			curCol = aCol;
		}
	});
	BindGridResizeEvent("cbfile",	"addFile", 99);
	
});

JQ_setValidation('templateVO');
JQ_onload();
<!--
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
}
<%-- 
/************************************************************************ 
fnc name : fn_list                                   
outline : "목록"버튼을 클릭했을때 게시물 리스트로 돌아가는 화면       
parameter : 없음        
directions : fn_list()
since : 2011-05-10
author : 기술지원 김일수       

    date      author             note  
 ----------   -------     ------------------- 
                                 
************************************************************************/
--%>
 function fn_list(){
   	JQ_request("templateVO", "<c:url value='/sample/tem/selectTemplateList.do'/>", "templateVO");
}

<%-- 
/************************************************************************ 
fnc name : fn_regist                               
outline : 게시물 항목 수정후 재등록 하는 함수
		공통메시지를 띄워 저장여부 확인한 뒤 사용자가 확인을 하면 DB로 내용을 보내서 Update 시킴
parameter : 없음(form 내용 modelAttribute 자동세팅)
directions : fn_regist()
since : 2011-05-10
author : 기술지원 김일수       

    date      author             note  
 ----------   -------     ------------------- 
                                 
************************************************************************/
--%> 
function fn_regist(){
	if(confirm("<spring:message code='common.save.msg' />")){		
		JQ_request("templateVO", "<c:url value='/sample/tem/updateTemplateJqGrid.do'/>");
	}
}

<%-- 
/************************************************************************ 
fnc name : fn_del                               
outline : 첨부파일을 삭제하는 함수 
		  서버에 있는 자료는 서버자료까지 삭제, 그외는 그리드에서만 삭제
parameter : 없음(form 내용 modelAttribute 자동세팅)
directions : fn_del()
since : 2011-05-10
author : 기술지원 김일수       

    date      author             note  
 ----------   -------     ------------------- 
                                 
************************************************************************/
--%> 
function fn_del() {
	 var ab  = $("#addFile").jqGrid('getGridParam','selarrrow');
	 
	 if( ab.length < 1 ) 
	 {
		alert("선택된 자료가 없습니다");
		return;
	 }	
	
	 for(var i = ab.length-1; i >= 0; i--){

		var rowdata = $("#addFile").getRowData(ab[i]);	
		if (rowdata.id != "") {
			$("#" + rowdata.id ).remove();
		}
        $("#addFile").jqGrid('delRowData',(ab[i]));

        if( rowdata.atchFileId != "" ) {
            
	     	$.ajax({
	 		   type:"POST",
	 		   url:"/cmm/fms/deleteFileInfJqGrid.do",
	 		   data:"param_atchFileId="+rowdata.atchFileId+"&param_fileSn="+rowdata.fileSn ,
	 		   dataType:"json",
	 		   success:function( data ){
	
	 		   }, error: function(data, status) { alert("<spring:message code="fail.common.msg" />"); }
	 		  }); 	 
        }
	 }
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
        <div class="page_title">템플릿 게시판 수정 - (JqGrid)</div>
        <div class="frmctl" id="frmctl"></div>
    </div>
    <!-- page title / frameset controller end -->
    
<div class="center_inner_contents"  id="bassContents">
	<form:form commandName="templateVO" name="insertForm" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="posblAtchFileNumber" value="최대등록가능파일숫자" />
	<input type="hidden" name="templateId" value="${templateVO.templateId}"/>
	<input type="hidden" name="templateFileid" value="${templateVO.templateFileid}"/>
	<input type="hidden" name="templateFileid2" value="${templateVO.templateFileid2}"/>
	
		<table class="default">			
			<tr>
				<td>
					<table summary="summary" class="write01">
						<tr>
							<th class="subject"><spring:message code='common.template.seCode'/></th>
							<td class="input">
								<form:select path="templateSeCode" cssClass="validate[required]">
									<form:option value="" label="-선택-" />
									<form:option value="001" label="분류1" />
									<form:option value="002" label="분류2" />
									<form:option value="003" label="분류3" />
								</form:select>
							</td>
						</tr>
						<tr>
							<th class="subject"><spring:message code='common.template.title'/></th>
							<td class="input">
							<form:input path="templateTitle" cssClass="validate[required,length[1,255]]" size="60" />
							</td>
						</tr>						
						<tr>
							<th class="subject"><spring:message code='common.template.content'/></th>
							<td class="input">
							<form:textarea path="templateContent" cssClass="validate[required,length[1,255]]" rows="5" cols="65" />
							</td>
						</tr>
						<tr>
							<th class="subject" rowspan="2"><spring:message code='common.template.addFile'/></th>
							<td><input name="mfile_1" id="egovComFileUploader" type="file" /></td>
							<!-- 이부분때문에 저장버튼 누르고 validation 체크 할때 빨간 팝업이 안뜸. 폼을 분리시키거나 아니면 다른 방법을 찾아볼 것 -->
							<!-- <td><input name="file_1" id="egovComFileUploader" type="file" /></td> -->
						</tr>
						<tr>
							<td>
							<div id="egovComFileList"></div>
							<c:import url="/cmm/fms/selectFileInfsForUpdate.do">
								<c:param name="param_atchFileId"  value="${templateVO.templateFileid}" />				
								
							</c:import>
							</td>
						</tr>
						<tr>
							<th	class="subject"><spring:message code='common.template.hasFile'/></th>
							<td id="cbfile">
								<table class="rbuttonarea">
								<tr>
									<td>
									<input type="file" id="file" name="file"  >
									
									</td>
									<td class="end"><img src="/images/button/0204.png" alt="<spring:message code='button.delete'/>" title="<spring:message code='button.delete'/>" onClick="fn_del()"></td>
								</tr>
								</table>
								<table class="inside01">							
								<tr>
									<td	class="grid">
										<table id="addFile"	class="scroll"></table>
									</td>
								</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table class="rbuttonarea">
						<tr>
						    <td><img src="/images/button/0210.png" alt="<spring:message code="button.save" />" title="<spring:message code="button.save" />" onclick = "fn_regist();"></td>
							<td><img src="/images/button/0201.png" alt="<spring:message code="button.list" />" title="<spring:message code="button.list" />" onclick = "fn_list();"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	<input name="cmd" type="hidden" value="<c:out value='save'/>" />
	<input name="returnUrl" type="hidden" value="/sample/tem/modifyTemplate.do" />
	
	</form:form>
	</div>
	
</div>
</div>
</body>
<script type="text/javascript">
	var maxFileNum = document.insertForm.posblAtchFileNumber.value;
	if(maxFileNum==null || maxFileNum==""){
    	maxFileNum = 3;
    }     
	var multi_selector = new MultiSelector( document.getElementById( 'addFile' ), maxFileNum, "file", "addFile" );
	multi_selector.addElement( document.getElementById( 'file' ) );			
</script>
</html>