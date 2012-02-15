<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>기본통계생성</title>
	<!-- style sheet start -->
	<link rel="stylesheet" href="/css/common/jqgrid/default.css" type="text/css">
	<link rel="stylesheet" href="/css/common/jqgrid/layer.css" type="text/css">
	<link rel="stylesheet" type="text/css" href="/css/common/jqgrid/jquery-ui-1.8.2.custom.css" />
	<link rel="stylesheet" href="/css/common/jqgrid/ui.jqgrid.css" type="text/css" >

	<!-- style sheet end -->
	<!-- javascript start	 -->
	<script type="text/javascript" src="/js/common/jquery/jquery-1.5.2.js"></script>
	<script type="text/javascript" src="/js/common/jqgrid/grid.locale-en.js"></script>
	<script type="text/javascript" src="/js/common/jqgrid/jquery.jqGrid.MultiHeader.min.js"></script>
	<!-- 그리드의 width resize 처리 이용시 -->
	<script type="text/javascript" src="/js/jqgrid/jquery.jqGrid.init.js"></script>
	<!-- 그리드에 붙여넣기 이벤트 사용시 -->
	<script type="text/javascript" src="/js/common/jqgrid/grid.paste.js"></script>
	<script type="text/javascript" src="/js/common/jqgrid/json2.js"></script>

	<!-- javascript end -->
	<script type="text/javascript">
	var lastsel;
	
	var myLayout;
	var gridwidth = 0;
	$(document).ready(function(){
		gridwidth = getGridWidth("div_content");	
		
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
            headerDepth:2,
			headerSpanModel : [
				{index:'rnum', sortindex:0,rowindex : 0, colindex : 0, rowspan : 2, colspan : 1, headertitle : ""},
				{index:'codeId', sortindex:1, rowindex : 0, colindex : 1, rowspan : 2, colspan : 1, headertitle : ""},
				{index:'code', sortindex:2, rowindex : 0, colindex : 2, rowspan : 2, colspan : 1, headertitle : ""},
				{index:'colspan1', sortindex:999, rowindex : 0, colindex : 3, rowspan : 1, colspan : 2, headertitle : "테스트"},
				{index:'codeNm', sortindex:3, rowindex : 1, colindex : 3, rowspan : 1, colspan : 1, headertitle : ""},
				{index:'useAt', sortindex:4, rowindex : 1, colindex : 4, rowspan : 1, colspan : 1, headertitle : ""}
			],
            //data: mydata,
            colNames:['순번','코드ID','코드','코드명','사용여부'],
            colModel:[
                {name:'rnum',index:'rnum',width:30,align:'center',sorttype: 'int',resizable:false},
                {name:'codeId',index:'codeId', align:'center',resizable:false},
				{name:'code',index:'code',width:100, align:'center', editable:true,resizable:false},
                {name:'codeNm',index:'codeNm',width:100, align:'center', editable:true,resizable:false},
                {name:'useAt',index:'useAt',width:100, align:'center', editable:true,resizable:false}
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
			width:gridwidth
        });
		// grid를 감싸고 있는 div의 width resize시 Grid의 width resize 처리
		BindGridResizeEvent("div_content", "list");

	});

	function submitTest () {
		var rows= jQuery("#list").jqGrid('getRowData');
		 var paras=new Array();
		 for(var i=0;i<rows.length;i++){
		 var row=rows[i];
		 //alert($.param(row));
		 paras.push($.param(row));
		 }
		 
		 $.ajax({
		 type: "POST",
		 url: "jqgrid_edit.do",
		 data: paras.join('&'),
		 success: function(msg){
		 alert(msg);
		 }
		 });
	}

	function submitTest2 () {
		var rows= jQuery("#list").jqGrid('getRowData');
		var msgyn="Y";
		
		 for(var i=0;i<rows.length;i++){
		 var row=rows[i];		 

			 if(msgyn=='Y'){
			 //alert($.param(row));
				 $.ajax({
					 type: "POST",
					 url: "jqgrid_edit2.do",
					 data: $.param(row),
					 success: function(msg){
						 alert(msg);
						 //alert(JSON.stringify(msg))
						 //alert('ok');
						 msgyn='Y';
					 },
					 error: function(fail){
						 alert(fail);	
						 msgyn='N'
					 }
					 });
			 }else{
				alert('fail');
				return;
			 }
		 }
		 alert('success');
	}

		function submitTest3 () {
			var rows= jQuery("#list").jqGrid('getRowData');
			//alert(JSON.stringify(rows)); 
			//for(var i=0;i<rows.length;i++){
			// var row=rows[i];
			// alert(row);
			//}
			
			
			alert(JSON.stringify(rows));
			
			$.ajax({
			type: "POST",
			url: "jqgrid_edit3.do",
			data: "json="+JSON.stringify(rows),
			success: function(msg){
			alert(msg);
			}
			});
		}


	
	
	</script>
</head>
<body>

		<!-- ontents_area start -->
		<div class="center_contents_area">
			<div class="page_title">
				<span>기본통계현황</span>
			</div>
			<div class="center_inner_contents" id="div_content">
				<table class="default">
				<tr>
					<td>
						<table class="default">
						<tr>
							<td>
								<table class="option">
								<tr>
									<td class="first">
										<select>
											<option>조사년도</option>
										</select>
									</td>
									<td>
										<select>
											<option>구분</option>
										</select>
									</td>
									<td>
										<select>
											<option>전체</option>
											<option>사용자ID</option>
											<option>사용자명</option>
										</select>
									</td>
									<td>
										<input type="text" name="" maxlength="200" value="" class="wi200">
									</td>
									<td><button>검색</button></td>
									<td><button>목록다운로드</button></td>
									<td><button>수치입력</button></td>
									<td><button type="button" onclick="submitTest3()">구성정보다운로드</button></td>
								</tr>
								</table>
							</td>
						</tr>
						</table>
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