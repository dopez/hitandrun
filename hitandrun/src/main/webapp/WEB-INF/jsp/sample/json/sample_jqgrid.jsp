<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	
	
	
	<title>기본통계생성</title>
	
	<jsp:include page="/sps/cmm/header.do" flush="false"/> 

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

<!-- javascript end -->
<script type="text/javascript">
JQ_onload(); 
<!--
	var lastsel;
	var curRow;
	var curCol;

	var myLayout;
	var gridwidth = 700;
	$(document).ready(function(){
		$("#fnSearch").click(function() {
			$("#list").jqGrid('setGridParam',{url:"/jqgrid_list.do?"+
			  $("#searchForm").serialize(),page:1}
			).trigger("reloadGrid");
		});
		
		$("#list").bind('keydown', function(event) {

			if (event.ctrlKey==true && (event.which == '118' || event.which == '86')) {				
				Paste("#list", curRow, curCol);
			}
		});
		
/*

		$(document).delegate('.ui-jqgrid', 'keydown', function(event) {
			alert('test');
		});
		*/

  

		gridwidth = $("#div_content").width();
		
            $("#list").jqGrid({
                datatype:'json',
                url:'/jqgrid_list.do',
                editurl: '/jqgrid_list.do',
                jsonReader:{
                    root: "resultList",
                    page: "paginationInfo.currentPageNo",
                    total: "paginationInfo.totalPageCount",
                    records: "paginationInfo.totalRecordCount",
                    repeatitems: false,
                    cell:"rnum"
                },
                //data: mydata,
                colNames:['순번','코드ID','코드','코드명','사용여부'],
                colModel:[
                    {name:'rnum',index:'id',width:30,align:'center',sorttype: 'int'},
                    {name:'codeId',index:'col1',width:100, align:'center'},
					{name:'code',index:'col2',width:100, align:'center', editable:true},
                    {name:'codeNm',index:'col3',width:200, align:'center', editable:true},
                    {name:'useAt',index:'col4',width:70, align:'center', editable:true}
                ],
                rowNum:10,
				cellEdit:true,
                rowList:[5,10,20],
                pager: '#pager',
                gridview:true,
                sortname: 'invdate',
                viewrecords: true,
                sortorder: 'desc',
                caption:'Just simple local grid',
                height: '250',
				width:gridwidth,
				//event : 모든 서버 요청 이후 실행.
				loadComplete: function(xhr){
				},
				//event : 요청이 실패한 경우
				loadError: function(xhr,status,error){
					gfn_ajaxerror( error );
                	
				},
				gridComplete : function() {
				 
				}
				,
				onCellSelect: function(aRowId, aCol, aData) {
					curRow = aRowId;
					curCol = aCol; 
					//alert($("#list").getCell(aRowId,aCol));
					//.attr("editable")
				}
            });

		
		
		$("#div_content").bind('resize', function() {
			gridwidth = $("#div_content").width();
//			gridwidth = document.body.clientWidth - 30;
			$("#list").setGridWidth(gridwidth); 
		}).trigger('resize');

	});

		function updateGrid () {
			var rows= jQuery("#list").jqGrid('getRowData');
			
			//alert(JSON.stringify(rows));
			
			$.ajax({
			type: "POST",
			url: "jqgrid_update.do",
			data: "json="+JSON.stringify(rows),
			success: function(msg){
				$("#list").trigger("reloadGrid");
			}
			});
			
		}

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
	
-->
	</script>
</head>
<body>
<div class="ui-layout-content">

<!-- page contents start -->
<div class="center_contents_area">
    <!-- page title / frameset controller start -->
    <div class="page_subject">
        <div class="page_title">공통상세코드 JQgrid 샘플</div>
        <div class="frmctl" id="frmctl"></div>
    </div>
    <!-- page title / frameset controller end -->

    <div class="center_inner_contents"  id="bassContents">
		
				<table class="default">
				<tr>
					<td>
						<form name="searchForm" id="searchForm">
						<table class="default" border="0" width="100%">
						<tr>
							<td>
								<table class="option">
								<tr>
									<td class="first" width="100">
								   	<select name="searchCondition" class="select" title="searchCondition">
										   <option selected value=''>--선택하세요--</option>
										   <option value='1' <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if>><label for="searchCondition">코드ID</label></option>
										   <option value='2' <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if>><label for="searchCondition">코드</label></option>
										   <option value='3' <c:if test="${searchVO.searchCondition == '3'}">selected="selected"</c:if>><label for="searchCondition">코드명</label></option>
									   </select>
									</td>
									<td  width="250">
										<input name="searchKeyword" type="text" size="35" value="${searchVO.searchKeyword}"  maxlength="35" id="searchCondition">
									</td>
		                            <td><img src="/images/button/0221.png" alt="<spring:message code='button.inquire'/>" title="<spring:message code='button.inquire'/>" id="fnSearch"></td>
									<td><img src="/images/button/0202.png" alt="<spring:message code='button.create'/>" title="<spring:message code='button.create'/>" onclick="updateGrid();"></td>										
								</tr>
								</table>
							</td>
						</tr>
						</table>
						</form>
					</td>
				</tr>
				<tr><td class="ht2"></td></tr>
				<tr>
					<td>
						<table id="list" class="scroll"></table>
						<div id="pager"></div>
					</td>
				</tr>
				</table>
			</div>

			
		</div>
		<!-- ontents_area end -->
</body>
</html>