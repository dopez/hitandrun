<%-- 
/** 
 * outline   : Drag & Drop 샘플화면
 * filename : TemplateDragndrop.jsp
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
<title>드래그앤드롭 샘플화면</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<%-- Header Start ==========================================================--%>

<jsp:include page="/sps/cmm/header.do" flush="false"/> 

<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common/jquery/jquery.ui.1.8.12.custom.min.js'/>"></script>
<script type="text/javaScript" language="javascript">
<!--
$(document).ready( function() 
{
	$('#ClickWordList li').click(function() { 
		$("#txtMessage").insertAtCaret($(this).text());
		return false
	});
	$("#DragWordList td:nth-child(3)").draggable({helper: 'clone'});
	$(".txtDropTarget").droppable({
		accept: "#DragWordList td:nth-child(3)",
		drop: function(ev, ui) {
		
			$(this).insertAtCaret(ui.draggable.text());
		}
	});
});

$.fn.insertAtCaret = function (myValue) {
	return this.each(function(){
			//IE support
			if (document.selection) {
					this.focus();
					sel = document.selection.createRange();
					sel.text = myValue;
					this.focus();
			}
			//MOZILLA / NETSCAPE support
			else if (this.selectionStart || this.selectionStart == '0') {
					var startPos = this.selectionStart;
					var endPos = this.selectionEnd;
					var scrollTop = this.scrollTop;
					this.value = this.value.substring(0, startPos)+ myValue+ this.value.substring(endPos,this.value.length);
					this.focus();
					this.selectionStart = startPos + myValue.length;
					this.selectionEnd = startPos + myValue.length;
					this.scrollTop = scrollTop;
			} else {
					this.value += myValue + " ";
					this.focus();
			}
	});
};
-->
</script>
</head>
<body>
<div class="ui-layout-content"><!-- page contents start -->
<div class="center_contents_area"><!-- page title / frameset controller start -->
<div class="page_subject">
<div class="page_title">Drag & Drop Sample</div>
<div class="frmctl" id="frmctl"></div>
</div>
<!-- page title / frameset controller end -->

<div id="maincontainer">

<div id="navtoplistline">&nbsp;</div>
<div id="contentwrapper">
<div id="maincolumn">
<div class="text">
<h2>Message #1</h2>
<textarea name="txtMessage" id="txtMessage" class="txtDropTarget"
	cols="80" rows="5"></textarea>
<h2>Message #2</h2>
<textarea name="txtMessage2" id="txtMessage2" class="txtDropTarget"
	cols="80" rows="5"></textarea></div>
</div>
</div>
<div id="leftcolumn">
<ul>
	<li>
		Click to insert: Message #1
	</li>
<ul id="ClickWordList">
	<li>나이</li>
	<li>혼인상태</li>
	<li>교육정도</li>
</ul>

<br/>

<table id="DragWordList">
	<tr>
		<td colspan="3">
			Drag to insert: Message #2
		</td>
	<tr>
		<td>1</td>
		<td>101</td>
		<td>교육정도</td>
	</tr>
	<tr>
		<td>2</td>
		<td>102</td>
		<td>혼인상태</td>
	</tr>
</table>

</div>

</div>
</div>
</body>
</html>