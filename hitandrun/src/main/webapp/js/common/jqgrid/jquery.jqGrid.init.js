//******************************************************************************
 //* <PRE>
 //* JS name   : jquery.jqGrid.init.js
 //* Comment   : jqGrid의 width 처리관련 함수 reference입니다
 //* History    : 1.0
 //*            
 //* </PRE>
 //* DATE       : 2011/05/23
 //* @author    : 조현기
 //* Copyright (C) 2011 by NurimSoft All right reserved.
//*****************************************************************************  

/**
* 설명 : Grid를 포함하는 div tag의 width를 추출합니다.
* 사용방식 : getGridWidth(div tag id)
*          <div class="center_inner_contents" id="div_content">
*          
*          var retVal = getGridWidth('div_content'); 
*          
* 주의 : object에 id attribute가 반드시 존재해야 합니다
* 리턴 : div tag의 width-10;
*/
function getGridWidth(divid)
{
	var es=$("#" + divid);
	var width = es.width();
	if( width < 0 )
		width = 700;
	return width;
}

/**
* 설명 : Grid를 포함하는 div가 resize 이벤트가 발생시 해당 Grid의 width를 resize 처리합니다.
* 사용방식 : BindGridResizeEvent(div tag id, grid id)
*          <div class="center_inner_contents" id="div_content">
*          	<table id="list" class="scroll"></table>
*          
*          BindGridResizeEvent("div_content", "list");
*          
* 주의 : object에 id attribute가 반드시 존재해야 합니다
* 리턴 : 없음
*/
function BindGridResizeEvent(divid, gridid)
{	
	var es=$("#" + gridid);
	$("#" + divid).bind('resize', function() {
		es.setGridWidth($("#" + divid).width()); 
	}).trigger('resize');
}


function BindGridResizeEvent(divid, gridid, pt)
{
	var es=$("#" + gridid);
	$("#" + divid).bind('resize', function() {
		es.setGridWidth(($("#" + divid).width() * pt / 100) - 12); 
	}).trigger('resize');
}

