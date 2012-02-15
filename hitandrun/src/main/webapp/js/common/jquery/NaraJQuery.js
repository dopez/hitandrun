//******************************************************************************
 //* <PRE>
 //* JS name   : NaraJQuery.js
 //* Comment   : 범정부통계 project에서 사용되는 jquery관련 함수 reference입니다
 //*              순위가 높은 함수일수록 페이(function($)지가 parsing되는 시점에 먼저 호출됩니다. 
 //*                 순위 : [11] 함수는 순위 : [12]함수보다 먼저 호출됩니다
 //* History    : 1.0
 //*            
 //* </PRE>
 //* DATE       : 2011/05/04
 //* @author    : 송인겸
 //* Copyright (C) 2011 by Samsung SDS All right reserved.
//*****************************************************************************    


var isOpenedLeftMenu=false;   
var naraValidateSubmit = true;   
 
//현재 사용자가 텍스트박스에 입력한 가장 최근의 키코드를 가집니다 
var naraKeyCode;

//사용자가 가장 최근에 클릭한 테이블의 row index를 가집니다. 특정TR의 checkbox나 라디오버턴을 클릭한경우에도 선택한 row의 index를 가지고 있습니다
var naraTableSelectedRow;
 
//사용자가 가장 최근에 클릭한 테이블의 TR의 ID를 가집니다 
var naraTableSelectedRowId;

//사용자가 가장 최근에 클릭한 테이블의 TR의 Name을 가집니다
var naraTableSelectedRowName;

//달력의 입력값 관련, 현제 validate가 정상인지를 체크함 
var naraCalendarValidate;

var tableRowIndex=0; 
var tooltipRowIndex=0;

// PARAMS
var IdleCheckerInterval;
var IdleCheckerCountdown;
var IdleCountdownClock;



/**
* 설명 : view(.jsp)내부의 object(type=text, radio, checkbox, textarea, password,,,의 값을 가져옵니다. 
*       getElementById()와 동일하게 작동됩니다.
*       trim()이 적용됩니다
* 사용방식 : JQ_getValueObj(값을 가져올 object의 id명)
*          <input type="text" id="txtRegId" value="rrrrr"....
*          
*          var retVal = JQ_getValueObj('txtRegId'); 
*          
* 주의 : object에 id attribute가 반드시 존재해야 합니다
* 리턴 : txtRegId의 value
*/
function JQ_getValueObj(ObjId)
{
	return $.trim($('#'+ObjId).val());
}

/**
* 설명 : view(.jsp)내부의 object(type=text, radio, checkbox, textarea, password,,,의 값을 설정합니다. 
*       getElementById()와 동일하게 작동됩니다.
*       trim()이 적용됩니다
* 사용방식 : JQ_setValueObj(값을 적용할 object의 id명, 적용될 값)
*          <input type="text" id="txtRegId" value="rrrrr"....
*          
*          JQ_setValueObj('txtRegId', 'ㅈㅈㅈㅈㅈㅈ'); 
*          
* 주의 : object에 id attribute가 반드시 존재해야 합니다
* 리턴 : 없음
*/
function JQ_setValueObj(ObjId,Value)
{
	$('#'+ObjId).val($.trim(Value));
}


/**
* 설명 : view(.jsp)내부의 object(table, tr, td, div, ul, li..의 값을 가져옵니다. 
*       trim()이 적용됩니다
* 사용방식 : JQ_getValue(값을 적용할 object의 id명, 적용될 값)
*          <div id="tmpDiv1">테스트입니다</div>

*          var retText = JQ_getValue('tmpDiv1'); 
*          
* 주의 : object에 id attribute가 반드시 존재해야 합니다
* 리턴 : tmpDiv1의 text
*/
function JQ_getValue(ObjId)
{
	return $.trim($('#'+ObjId).text());
}

/**
* 설명 : view(.jsp)내부의 object(table, tr, td, div, ul, li..의 값을 설정합니다. 
*       trim()이 적용됩니다
* 사용방식 : JQ_setValue(값을 적용할 object의 id명, 적용될 값)
*          <div id="tmpDiv1"></div>
*          
*          JQ_setValue('tmpDiv1','테스트입니다'); 
*          
* 주의 : object에 id attribute가 반드시 존재해야 합니다
* 리턴 : 없음
*/
function JQ_setValue(ObjId,Value)
{	
	$('#'+ObjId).text($.trim(Value));
}

/**
* 설명 : onload관련 함수 입니다 <body onload="fncInit()" 형식의 함수보다 먼저 호출됩니다.
*       body onload형식과 같이 사용해도 무방하며 대체하여 사용해도 무방합니다
* 사용방식 : 자바스크립트 상단영역에 
*           JQ_onload(); 
*           를 기술하고
*           하단의 아무 영역에서 아래와 같이
*           
*           function fncPageOnload()
*           {
*           	//여기에다 onload시 적용하고픈 로직을 구현
*           }
* 순위 : [21]           
*/
function JQ_onload()
{
	
	$(function()
 	{
		if (typeof(fncPageOnload) != 'undefined')
		{	
			fncPageOnload();	
		}
	});
}

/**
* 설명 : 툴팁 validation을 적용하기 위한 초기 설정을 세팅합니다
* 
* 사용방식 :예1)
*            JQ_setValidation(폼 아이디); 
*            JQ_setValidation('baseInfoFormVo');
*           
*           자바스크립트의 상단영역에 구현합니다 
*          예2) 실시간으로 Validation을 체크하는 경우 
*            JQ_setValidation(폼 아이디 , true ); 
*            JQ_setValidation('baseInfoFormVo', true);
*
* 주의 : form id가 반드시 존재해야 합니다
* 리턴 : 없음
*/
function JQ_setValidation(frmId, inline, ttPosition )
{ 
	var retFrmId = '#' + frmId;
	var retPos = $.trim(ttPosition);
	var inlineVal = $.trim(inline);
	var inlineyeobu = false;
	if (retPos == '')
	{
		retPos = "topRight";
	}
	if (inlineVal == '')
	{
		inlineyeobu = false;
	} else {
		inlineyeobu = true;
	}
	
	$(document).ready(function() 
	{	
		$(retFrmId).validationEngine(
		{
			promptPosition:retPos,
			validationEventTriggers:"keyup click",
			inlineValidation: inlineyeobu
		});
		

	});
}

/**
* 설명 : 현재 사용자를 위해 열린 툴팁을 즉시 닫습니다. nara 프로젝트에서는 기본적으로 5초이후 툴팁이 자동으로 지워집니다
* 사용방식 : JQ_closePrompt();
*          툴팁이 닫혀야 하는 시점에 구현합니다
*          
* 주의 : 
* 리턴 : 없음
*/
function JQ_closePrompt()
{
	$.validationEngine.closePrompt(".formError",true)
}

/**
* 설명 : validation 체크를 통한 form submit을 수행합니다
* 사용방식 : 1.폼내부의 validtaion에 일치하지 않는 object가 존재하면 submit을 수행하지 않습니다
*           사용자의 대용량 데이터 조회를 방지하거나 일반적인 데이터 등록,수정,삭제 조건으로 사용할수 있습니다 
 			JQ_request("boardFormVO", "<c:url value='/board.do?method=noticeManage'/>");
 
 			2.validtaion에 일치하지 않아도 submit을 수행합니다, 마지막에 첫번째 파라미터인 form id를 한번더 적어줍니다
 			form validation을 적용하였지만 페이지이동등의 조건없는 조회수행에 사용할수 있습니다
 			JQ_request("boardFormVO", "<c:url value='/board.do?method=noticeManage'/>", "boardFormVO");
 			
 			3. 엑셀다운로드에서 targetframe에 "Y"로 설정하면 됨. 
 			JQ_request("boardFormVO", "<c:url value='/board.do?method=noticeManage'/>", "boardFormVO", "Y");
*          
*          
* 주의 : 
* 리턴 : 없음
*/
function JQ_request(frmId, Url, validate, targetframe)
{
	
	var retFrmId = '#' + frmId;
	var beforeTarget = "";
	
	if (naraValidateSubmit == false) {
		return;
	} 
	
	JQ_SetTextNumber_unmask();
	if (naraValidateSubmit == true)
	{
		
		$("input[type=text]").each(function()
		{
			if($(this).val() == $(this).attr('alt')){$(this).val('');}
		});
		
		$("input[type=textarea]").each(function()
		{
			if($(this).val() == $(this).attr('title')){$(this).text('');}
		});
		
		if( targetframe != null ) {
			if( targetframe == "Y")	{
				targetframe = "iExecFrame" ;
			}
  		    
			beforeTarget = document.getElementById(frmId).target;
				
			if( targetframe == "iExecFrame") {	 
				if( $("#" + targetframe).length <= 0 ) {
					var strBuf = "<iframe src='' id='"+targetframe+"' name='"+targetframe+"'  width='0' height='0'></iframe>";
					$("body").append( strBuf );
				}
			}
		}
		
		if (validate == frmId)
		{
			document.getElementById(frmId).method = "post";
			document.getElementById(frmId).action = Url;
			if( targetframe != null ) {				
				document.getElementById(frmId).target = targetframe;
				document.getElementById(frmId).submit();				
				document.getElementById(frmId).target = beforeTarget;
			} else {	
			    document.getElementById(frmId).submit();
			}
		}
		else
		{
			if( targetframe != null ) {
				$(retFrmId).attr({action:Url, method:'post', target:targetframe}).submit();
				document.getElementById(frmId).target = beforeTarget;

			} else {
			    $(retFrmId).attr({action:Url, method:'post'}).submit();
			}
			
		}
		
	}
}

/**
* 설명 : ajax를 통한 form submit을 수행합니다. validation은 적용하지 않습니다
* 사용방식 : 호출하기전 options를 구성하여 넘겨주어야 합니다 options는 필수입니다
* var options = {
*                 beforeSubmit: fncUserDefine; //선택옵션입니다. user define된 함수를 submit전에 호출합니다. fncUserDefine은 함수명입니다 ex:) function fncUserDefine()
*                 success     : fncSuccess; //선택옵션입니다. submit이 정상적으로 수행된후 호출될 함수를 정의합니다.
*                 url         : '/nara/board.do?method=getNoticeDetail' //필수옵션입니다. submit을 수행할 대상 url입니다
*                 contentType : 'application/x-www-form-urlencoded; charset=UTF-8' //선택옵션입니다. contenttype을 지정합니다
*                 type        : 'post' //선택옵션입니다. action type을 지정합니다 'get' 혹은 'post' 둥중 하나입니다
*                 dataType    : 'html' //선택옵션입니다. 리턴데이터 타입을 지정합니다 xml, html, scriptm json
*                 target      : 'targeId' //선택옵션입니다. submit target을 지정합니다 예를 들어 iframe으로 target이 잡힐경우 iframe의 id값을 넘겨줍니다
*               };
*               
* 예) var options = {url:'/nara/board.do?method=getNoticeDetail', target:'ifrmDetail', success:fncXXXX};
*     JQ_requestAjax('boardFormVO', options);     
* 
*/
function JQ_requestAjax(frmId, options)
{
	var retFrmId = '#' + frmId;
	JQ_SetTextNumber_unmask();
	$(retFrmId).ajaxSubmit(options);
}

/**
* 설명 : submit시 이미지를 바탕화면에 설정하여 사용자로 하여금 double submit및 등의 과도한 데이터조회를 방지합니다 
* 사용방식 : 
* * 주의 : 
* 리턴 : 없음
*/
function JQ_requestDblSubmit(url, retForm)
{
	JQ_SetTextNumber_unmask();
	var retParams = Form.serialize($(retForm));
	$('naraAjaxWait').startWaiting('bigWaiting');
	var ajaxRequest = new Ajax.Request(url,
			{
				method: 'POST', 
				parameters: retParams, 
				asynchronous : true,
				onCreate: function(){$('naraAjaxWait').startWaiting('bigWaiting');},
				onFailure:  function(){$('naraAjaxWait').stopWaiting('bigWaiting');},
				onComplete: function(){$('naraAjaxWait').stopWaiting('bigWaiting');}
			}	

		);	
}

/**
* 설명 : 폼 내부의 object의 값을 전부 지웁니다.
* 사용방식 : JQ_clearForm(폼 아이디)
*           JQ_clearForm(baseInfoFormVo);
*          
*          
* 주의 : 
* 리턴 : 없음
*/
function JQ_clearForm(frm)
{
	$(frm).clearForm();
}

 

/**
* 설명 : 사용자의 double submit을 방지 혹은 과도한 데이터 요청을 client에서 방지합니다 
* 사용방식 : body on load에 JQ_DoubleSubmit()를 구현합니다. 
*          
* 주의 : 이 함수는 JQ_setValidation()이 선행 구현.호출 되어 있어야 정상작동합니다
* 리턴 : 없음
*/
function JQ_DoubleSubmit()
{	
	//$("body").wrapInner("<div id='naraAjaxWait' style='width:780px;' ></div>");

	//$('naraAjaxWait').stopWaiting('bigWaiting');
}
/**
* 설명 : 폼 내부의 object의 값을 초기화 합니다. onload시점의 값을 기억하여 초기화 합니다. onload시점에 값이 들어가 있는 object는 값을 유지합니다
* 사용방식 : JQ_resetForm(폼 아이디)
* 구현    : JQ_resetForm(baseInfoFormVo);
*          
*          
* 주의 : 
* 리턴 : 없음
*/
function JQ_resetForm(frm)
{
	$(frm).resetForm();	
}

/**
* 설명 : 특정 콤보박스에 item을 추가합니다
* 사용방식 : JQ_addComboItem('programRoleCombo',콤보박스에 추가할 값,콤보박스에 보여질 텍스트);
* 구현    : JQ_addComboItem('programRoleCombo','','========전체========');
*          
*          
* 주의 : 
* 리턴 : 없음
*/
function JQ_addComboItem(obj, retValue, retText)
{
	var retObj = "#" + obj;
	$(retObj).prepend("<option value='" + retValue + "'>" + retText + "</option>");
}

function JQ_setScroller(set)
{
	if (set == true)
	{
		$("#naraScroller").attr({style:'display:none'});
	}	
}
 
/**
* 설명 : 화면 우측 상단에 즉시 화면 최상단,최하단으로 이동할수 있는 scroller를 추가합니다
* 사용방식 : 자바스크립트 최상단 영역에 JQ_addScroller()를 구현합니다
* 구현    : JQ_addScroller();
*          
*          
* 주의 : style css의 값을 참조합니다. nara proect에서는 /portal/css/style.css의 naraFloating class를 참조합니다
* 리턴 : 없음
*/
function JQ_addScroller()
{
	$(function()
	{
		var tmpUpImg = "<c:url value='/images/bullet/bull_pagetop.gif'/>";
		var tmpDnImg = "<c:url value='/images/bullet/bull_pagedown.gif'/>";
		
		var tmpScroller = "<div id='naraScroller' style='position:absolute; display:block; top:10px;left:800px; width:100px; border:0px solid #000000' class='naraFloating'>";
		tmpScroller = tmpScroller + "<a href='javascript:window.scrollTo(0,0);'><img id='img1' src='"+ "/images/bullet/bull_pagetop.gif" +"' border='0'></a><br />";
		tmpScroller = tmpScroller + "<a href='javascript:window.scrollTo(0,document.body.scrollHeight);'><img src='" + "/images/bullet/bull_pagedown.gif" + "' border='0'></a>";
		tmpScroller = tmpScroller + "</div>";
		
		$("body>form").append(tmpScroller);	
		
		var currentPosition = parseInt($(".naraFloating").css("top"));
	    $(window).scroll(function() {
	        var position = $(window).scrollTop(); // 현재 스크롤바의 위치값을 반환합니다.
	        $(".naraFloating").stop().animate({"top":position+currentPosition+"px"},1000);
	    });
	});
}

function JQ_addTableColor()
{
	// mouseover, mouseout Event
	$(function(){
		$('table.sortable tr').live('mouseover',function(){
			$(this).each(function(index){
				$(this).children().css("background-color","" ).addClass('striped');
			});
		}).live('mouseout',function(){
				$(this).each(function(index){
					//alert ($(this).children());
					$(this).children().children().toggleClass('striped');
					$(this).children().toggleClass('striped');
			});
		});
	});
}


/**
* 설명 : 특정 페이지 자체를 툴팁으로 열어줍니다
* 사용방식 : 툴팁으로 열려질 페이지의 url을 넘겨줍니다
* 구현    : 1.자바스크립트 상단영역에 JQ_addScroller();를 구현합니다 
*            툴팁이 표시될 object를 아래와 같이 구현합니다 
*          2.툴팁갯수만큼 아래와 같이 구현합니다 
*          
           <a id="chartTest1" class="NARApopupTrigger" href="" rel="http://localhost:8080/nara/portal/test/main33.jsp,25,30,0"></a>
           <a id="chartTest2" class="NARApopupTrigger" href="" rel="http://localhost:8080/nara/portal/test/main34.jsp,100,150,10"></a>
           
           http://localhost:8080/nara/portal/test/main33.jsp <--호출될 url page입니다
           ,25 <-- top위치 값입니다
           ,30 <-- left위치 값입니다
           ,0 <-- div투명도입니다 0이면 div가 완전 투명해집니다
            
           3. function fncBodyOnload()
			{
				$("#chartTest1").trigger("mouseover");
				$("#chartTest2").delay(1000,function(){$("#chartTest2").trigger("mouseover");});
			}
           를 구현하여 툴팁을 화면에 표시합니다 
  
*          
* 주의 : 열려질 페이지의 '(싱글쿼테이션)값이 잘못코딩되어 있을경우 스크립트 오류가 발생할수 있습니다...
* 리턴 : 없음
*/
function JQ_addTooltipView()
{
	$(function()  
	{  
			   
			var hideDelay = 500;    
			  var currentID;  
			  var hideTimer = null; 
			  var tmpUrl, tmpLeft, tmpTop, tmpOp;
			  var container;
			  $('.NARApopupTrigger').live('mouseover', function()  
			  {  
				  var hideDelay = 500;    
				  var currentID;  
				  var hideTimer = null; 
				  
				  var retObj = $(this).attr('id');
				  //tooltipRowIndex = tooltipRowIndex + 1;
				  
				  container = $('<div id="NARApopupContainer' + retObj + '" class="NARApopupContainerCls">'  
					      + '<table width="" border="0" cellspacing="0" cellpadding="0" align="center" class="NARApopupPopup">'  
					      + '<tr>'  
					      + '   <td class="corner topLeft"></td>'  
					      + '   <td class="top"></td>'  
					      + '   <td class="corner topRight"></td>'  
					      + '</tr>'  
					      + '<tr>'  
					      + '   <td class="left">&nbsp;</td>'  
					      + '   <td><div id="NARApopupContent' + retObj + '" class="NARApopupContentCls"></div></td>'  
					      + '   <td class="right">&nbsp;</td>'  
					      + '</tr>'  
					      + '<tr>'  
					      + '   <td class="corner bottomLeft">&nbsp;</td>'  
					      + '   <td class="bottom">&nbsp;</td>'  
					      + '   <td class="corner bottomRight"></td>'  
					      + '</tr>'  
					      + '</table>'  
					      + '</div>');  
				 
				  $('body>form').append(container);
				  
				  var splitK = $(this).attr('rel').split(",");
				  for(var tmpcnt=1; tmpcnt <= splitK.length; tmpcnt++)
				  {
				    //
				  	if (tmpcnt == 1)
				  	{
				  		tmpUrl = splitK[tmpcnt-1];
				  	}
				  	else if (tmpcnt == 2)
				  	{
				  		tmpTop = splitK[tmpcnt-1];
				  		
				  	}
				  	else if (tmpcnt == 3)
				  	{
				  		tmpLeft = splitK[tmpcnt-1];
				  	}
				  	else if (tmpcnt == 4)
				  	{
				  		tmpOp = splitK[tmpcnt-1];
				  	}
				  }
				  
			      if (hideTimer)  
			          clearTimeout(hideTimer);  
			  
			      var pos = $(this).offset();  
			      var width = $(this).width();  
			      container.css({  
			          //left: (pos.left + width) + 'px',  
			          //top: pos.top - 5 + 'px'
			    	  left: tmpLeft,
			    	   top: tmpTop,
			    	   filter:"alpha(opacity=" + tmpOp + ")"
			      });  
			
			      $('#NARApopupContent' + retObj).html('&nbsp;');  

			      $.ajax({  
			          type: 'GET',  
			          url: tmpUrl,  
			          data: 'page=' + 'temp1' + '&guid=' + 'temp2',  
			          
			          success: function(data)  
			          {  
			    	  	//alert('data'+data);
			    	     $('#NARApopupContent' + retObj).html(data);  
			    	    
			          }  
			      });  
			      //alert('3333'+container);
			      container.css('display', 'block');  
			  });  
			  
			  $('.NARApopupTrigger').live('mouseout', function()  
			  {  

			      if (hideTimer)  
			          clearTimeout(hideTimer);  
			      hideTimer = setTimeout(function()  
			      { 
			         container.css('display', 'none'); 
			      }, hideDelay);  
			  });  
			  
			  // 팝업상세에 대한 마우스 클릭시
			  $('#NARApopupContainer').click(function()  
			  {
				  alert('clicked');
			  });
			  
			  $('#NARApopupContainer').mouseover(function()  
			  {  
			      if (hideTimer)  
			          clearTimeout(hideTimer);  
			  });  
			  
			  // 마우스가 빠지면 숨겨짐
			  $('#NARApopupContainer').mouseout(function()  
			  {  
			      if (hideTimer)  
			          clearTimeout(hideTimer);  
			      hideTimer = setTimeout(function()  
			      {  
			          container.css('display', 'none');  
			      }, hideDelay);  
			  });  
			  //이벤트 관련 내용 끝
			  
			});  
}

/**
* 설명 : 화면 상단 혹은 하단에 네비게이션바를 생성하여 팜업되도록 합니다
* 사용방식 : 자바스크립트 최상단 영역에 JQ_addNavigator(styller)를 구현합니다
*          styller값은 'top', 'bottom' 둘중 하나이며 필수입력입니다 
* 구현    : JQ_addNavigator('top');
*              
* 주의 : DTD가 설정되어 있지 않는경우 페이지 오류가 발생합니다 
*       panel구현과 상관없이 
*       모든 페이지에 DTD는 필수적으로 추가되어야 합니다
*       
*       <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
*       
* 리턴 : 없음
*/


/**
* 설명 : 테이블 내부 row의 색상을 홀,짝수 에 맞춰 반전합니다
* 사용방식 : 내부 함수로 사용합니다 따로 호출하여 사용하지 않습니다
* 구현    : 
*          
*          
* 주의 : 
* 리턴 : 없음
*/
jQuery.fn.alternateRowColors = function() {
	$('tbody tr:odd', this)
	.removeClass('even').addClass('odd');
	$('tbody tr:even', this)
	.removeClass('odd').addClass('even');
	return this;
};

/**
* 설명 : 사용자의 중복 submit을 방지하기 위해 사용됩니다 
* 사용방식 : 내부 함수로 사용합니다 따로 호출하여 사용하지 않습니다
* 구현    : 
*          
*          
* 주의 : 
* 리턴 : 없음
*/
jQuery.fn.naraDbSubmit = function()
{
	$(this).submit
	(
		function()
		{
			if(this.beenSubmitted)
			{
				return false;
			}
			else
			{
				this.beenSubmitted = true;
			}
			
		}
	);
};

/**
* 설명 : nara project에서 사용되는 함수 확장을 위한 내용입니다. extend로 구현되는 내용을 집합합니다. 순서에 맞춰 구현하되 공통적용사항에 대해 반드시 
*       인지 및 공유하도록 합니다
* 사용방식 : 내부 함수로 사용합니다 따로 호출하여 사용하지 않습니다
* 구현    : 
*          
*          
* 주의 : 
* 리턴 : 없음
* 순위 : [11]
*/
(function($) 
{
	
	var pasteEventName = ($.browser.msie ? 'paste' : 'input') + ".mask";
	var iPhone = (window.orientation != undefined);
	
	$.mask = {
		//문자열 정의 
		definitions: {
			'9': "[0-9]",
			'a': "[A-Za-z]",
			'*': "[A-Za-z0-9]"
		}
	};
	
	//달력관련 국가별세팅(ko)시작
	$.datepicker.regional['ko'] = {
			closeText: '닫기',
			prevText: '',
			nextText: '',
			currentText: '오늘',
			monthNames: ['1월','2월','3월','4월','5월','6월',
			'7월','8월','9월','10월','11월','12월'],
			monthNamesShort: ['1월','2월','3월','4월','5월','6월',
			'7월','8월','9월','10월','11월','12월'],
			dayNames: ['일','월','화','수','목','금','토'],
			dayNamesShort: ['일','월','화','수','목','금','토'],
			dayNamesMin: ['일','월','화','수','목','금','토'],
			weekHeader: 'Wk',
			dateFormat: 'yy-mm-dd',
			firstDay: 0,
			isRTL: false,
			showMonthAfterYear: false,
			yearSuffix: ''};
	
	$.datepicker.setDefaults($.datepicker.regional['ko']);
		
				
	//달력관련 국가별세팅(ko)끝
			
	//filestyle시작
		$.fn.filestyle = function(options){
			var settings = {
					width : 250
			};
			
			if(options){
				$.extend(settings, options);
			};
			
			return this.each(function(){
				
				var self = this;
				var wrapper = $("<div>")
								.css({
									"width":settings.imagewidth+"px",
									"height":settings.imageheight+"px",
									"background":"url("+settings.image+") 0 0 no-repeat",
									"background-position": "right",
									"display": "inline",
									"position": "absolute",
									"overflow": "hidden"
								});
				var filename = $('<input class="file">')
								.addClass($(self).attr("class"))
								.css({
									"display": "inline",
									"width": settings.width + "px"
								});
				
				$(self).before(filename);
				$(self).wrap(wrapper);
				
				$(self).css({
							"position": "relative",
							"height": settings.imageheight + "px",
							"width": settings.width + "px",
							"display": "inline",
							"cursor": "pointer",
							"opacity" : "0.0"
				});
				
				if($.browser.mozilla){
					if(/Win/.test(navigator.platform)){
						$(self).css("margin-left","-142px");
					}else{
						$(self).css("margin-left","-168px");
					};
				}else{
					$(self).css("margin-left", settings.imagewidth - settings.width + "px");
				};
				
				$(self).bind("change",function(){
					filename.val($(self).val());
				});
			});
		};
	//filestyle끝
	
	//텍스트박스 힌트관련 함수확장 시작
	$.fn.hint = function (blurClass) 
	{
		  if (!blurClass) 
		  { 
			blurClass = 'blur';
		  }
			
		  return this.each(function() 
		  {
		  	    //alert($(this).attr('type'));
				var $input = $(this),
				
				  //변수값 리셋을 위한 힌트내용 세팅(alt속성의 값을 읽어오도록 함)
				  
				  naraAltHint = $input.attr('alt'),
				  $form = $(this.form),
				  $win = $(window);
				  
				  if ($input.attr('type') == 'text')
				  {
				  	naraAltHint = $input.attr('alt');
				  }	
				  else if ($input.attr('type') == 'textarea')
				  {
				  	naraAltHint = $input.attr('title');
				  }
				  
				function remove() 
				{
					  if ($input.val() === naraAltHint && $input.hasClass(blurClass)) 
					  {
						$input.val('').removeClass(blurClass);
					  }
				}
		
				if (naraAltHint) 
				{ 
					
					  //onblur이벤트, 값이 비어있는경우 세팅함
					  $input.blur(function () {
						if (this.value === '') {
						  $input.val(naraAltHint).addClass(blurClass);
						}
					  }).focus(remove).blur(); 
					  
					  $form.submit(remove);
					  $win.unload(remove);
				}
		  });
	};
	//텍스트박스 힌트관련 함수확장 끗
	
	//텍스트박스 masking관련 시작
	$.fn.extend({
		//캐럿지정을 위한 핼퍼함수
		caret: function(begin, end) {
			if (this.length == 0) return;
			if (typeof begin == 'number') {
				end = (typeof end == 'number') ? end : begin;
				return this.each(function() {
					if (this.setSelectionRange) {
						this.focus();
						this.setSelectionRange(begin, end);
					} else if (this.createTextRange) {
						var range = this.createTextRange();
						range.collapse(true);
						range.moveEnd('character', end);
						range.moveStart('character', begin);
						range.select();
					}
				});
			} else {
				if (this[0].setSelectionRange) {
					begin = this[0].selectionStart;
					end = this[0].selectionEnd;
				} else if (document.selection && document.selection.createRange) {
					var range = document.selection.createRange();
					begin = 0 - range.duplicate().moveStart('character', -100000);
					end = begin + range.text.length;
				}
				return { begin: begin, end: end };
			}
		},
		unmask: function() { return this.trigger("unmask"); },
		mask: function(mask, settings) {
			if (!mask && this.length > 0) {
				var input = $(this[0]);
				var tests = input.data("tests");
				return $.map(input.data("buffer"), function(c, i) {
					return tests[i] ? c : null;
				}).join('');
			}
			settings = $.extend({
				placeholder: "_",
				completed: null
			}, settings);

			var defs = $.mask.definitions;
			var tests = [];
			var partialPosition = mask.length;
			var firstNonMaskPos = null;
			var len = mask.length;

			$.each(mask.split(""), function(i, c) {
				if (c == '?') {
					len--;
					partialPosition = i;
				} else if (defs[c]) {
					tests.push(new RegExp(defs[c]));
					if(firstNonMaskPos==null)
						firstNonMaskPos =  tests.length - 1;
				} else {
					tests.push(null);
				}
			});

			return this.each(function() {
				var input = $(this);
				var buffer = $.map(mask.split(""), function(c, i) { if (c != '?') return defs[c] ? settings.placeholder : c });
				var ignore = false;  			//콘트롤키, 특수키 무시
				var focusText = input.val();

				input.data("buffer", buffer).data("tests", tests);

				function seekNext(pos) {
					while (++pos <= len && !tests[pos]);
					return pos;
				};

				function shiftL(pos) {
					while (!tests[pos] && --pos >= 0);
					for (var i = pos; i < len; i++) {
						if (tests[i]) {
							buffer[i] = settings.placeholder;
							var j = seekNext(i);
							if (j < len && tests[i].test(buffer[j])) {
								buffer[i] = buffer[j];
							} else
								break;
						}
					}
					writeBuffer();
					input.caret(Math.max(firstNonMaskPos, pos));
				};

				function shiftR(pos) {
					for (var i = pos, c = settings.placeholder; i < len; i++) {
						if (tests[i]) {
							var j = seekNext(i);
							var t = buffer[i];
							buffer[i] = c;
							if (j < len && tests[j].test(t))
								c = t;
							else
								break;
						}
					}
				};

				function keydownEvent(e) {
					var pos = $(this).caret();
					var k = e.keyCode;
					ignore = (k < 16 || (k > 16 && k < 32) || (k > 32 && k < 41));

					//이전값 삭제
					if ((pos.begin - pos.end) != 0 && (!ignore || k == 8 || k == 46))
						clearBuffer(pos.begin, pos.end);

					//backspace, delete, esc 
					if (k == 8 || k == 46 || (iPhone && k == 127)) {//backspace/delete
						shiftL(pos.begin + (k == 46 ? 0 : -1));
						return false;
					} else if (k == 27) {//escape
						input.val(focusText);
						input.caret(0, checkVal());
						return false;
					}
				};

				function keypressEvent(e) {
					if (ignore) {
						ignore = false;
						
						return (e.keyCode == 8) ? false : null;
					}
					e = e || window.event;
					var k = e.charCode || e.keyCode || e.which;
					var pos = $(this).caret();

					if (e.ctrlKey || e.altKey || e.metaKey) {
						return true;
					} else if ((k >= 32 && k <= 125) || k > 186) {//입력가능 케릭터 지정
						var p = seekNext(pos.begin - 1);
						if (p < len) {
							var c = String.fromCharCode(k);
							if (tests[p].test(c)) {
								shiftR(p);
								buffer[p] = c;
								writeBuffer();
								var next = seekNext(p);
								$(this).caret(next);
								if (settings.completed && next == len)
									settings.completed.call(input);
							}
						}
					}
					return false;
				};

				function clearBuffer(start, end) {
					for (var i = start; i < end && i < len; i++) {
						if (tests[i])
							buffer[i] = settings.placeholder;
					}
				};

				function writeBuffer() { return input.val(buffer.join('')).val(); };

				function checkVal(allow) {
					
					var test = input.val();
					var lastMatch = -1;
					for (var i = 0, pos = 0; i < len; i++) {
						if (tests[i]) {
							buffer[i] = settings.placeholder;
							while (pos++ < test.length) {
								var c = test.charAt(pos - 1);
								if (tests[i].test(c)) {
									buffer[i] = c;
									lastMatch = i;
									break;
								}
							}
							if (pos > test.length)
								break;
						} else if (buffer[i] == test[pos] && i!=partialPosition) {
							pos++;
							lastMatch = i;
						} 
					}
					if (!allow && lastMatch + 1 < partialPosition) {
						input.val("");
						clearBuffer(0, len);
					} else if (allow || lastMatch + 1 >= partialPosition) {
						writeBuffer();
						if (!allow) input.val(input.val().substring(0, lastMatch + 1));
					}
					return (partialPosition ? i : firstNonMaskPos);
				};

				if (!input.attr("readonly"))
					input
					.one("unmask", function() {
						input
							.unbind(".mask")
							.removeData("buffer")
							.removeData("tests");
					})
					.bind("focus.mask", function() {
						focusText = input.val();
						var pos = checkVal();
						writeBuffer();
						setTimeout(function() {
							if (pos == mask.length)
								input.caret(0, pos);
							else
								input.caret(pos);
						}, 0);
					})
					.bind("blur.mask", function() {
						checkVal();
						if (input.val() != focusText)
							input.change();
					})
					.bind("keydown.mask", keydownEvent)
					.bind("keypress.mask", keypressEvent)
					.bind(pasteEventName, function() {
						setTimeout(function() { input.caret(checkVal(true)); }, 0);
					});

				checkVal(); 
			});
		}
	});
	//텍스트박스 masking관련 끝
	
	//block ui관련 시작
	(function($) 
	{
			if (/1\.(0|1|2)\.(0|1|2)/.test($.fn.jquery) || /^1.1/.test($.fn.jquery)) {
				alert('blockUI requires jQuery v1.2.3 or later!  You are using v' + $.fn.jquery);
				return;
			}
			
			$.fn._fadeIn = $.fn.fadeIn;
			
			var noOp = function() {};
			
			// this bit is to ensure we don't call setExpression when we shouldn't (with extra muscle to handle
			// retarded userAgent strings on Vista)
			var mode = document.documentMode || 0;
			var setExpr = $.browser.msie && (($.browser.version < 8 && !mode) || mode < 8);
			var ie6 = $.browser.msie && /MSIE 6.0/.test(navigator.userAgent) && !mode;
			
			// global $ methods for blocking/unblocking the entire page
			$.blockUI   = function(opts) { install(window, opts); };
			$.unblockUI = function(opts) { remove(window, opts); };
			
			// convenience method for quick growl-like notifications  (http://www.google.com/search?q=growl)
			$.growlUI = function(title, message, timeout, onClose) {
				var $m = $('<div class="growlUI"></div>');
				if (title) $m.append('<h1>'+title+'</h1>');
				if (message) $m.append('<h2>'+message+'</h2>');
				if (timeout == undefined) timeout = 3000;
				$.blockUI({
					message: $m, fadeIn: 700, fadeOut: 1000, centerY: false,
					timeout: timeout, showOverlay: false,
					onUnblock: onClose, 
					css: $.blockUI.defaults.growlCSS
				});
			};
			
			// plugin method for blocking element content
			$.fn.block = function(opts) {
				return this.unblock({ fadeOut: 0 }).each(function() {
					if ($.css(this,'position') == 'static')
						this.style.position = 'relative';
					if ($.browser.msie)
						this.style.zoom = 1; // force 'hasLayout'
					install(this, opts);
				});
			};
			
			// plugin method for unblocking element content
			$.fn.unblock = function(opts) {
				return this.each(function() {
					remove(this, opts);
				});
			};
			
			$.blockUI.version = 2.35; // 2nd generation blocking at no extra cost!
			
			// override these in your code to change the default behavior and style
			$.blockUI.defaults = {
				// message displayed when blocking (use null for no message)
				message:  '<h1>Please wait...</h1>',
				//message:  "<img id='displayBox' src='/images/common/loading.gif' border='0'/>",

			
				title: null,	  // title string; only used when theme == true
				draggable: false,  // only used when theme == true (requires jquery-ui.js to be loaded)
				
				theme: false, // set to true to use with jQuery UI themes
				
				// styles for the message when blocking; if you wish to disable
				// these and use an external stylesheet then do this in your code:
				// $.blockUI.defaults.css = {};
				css: {
					padding:	0,
					margin:		0,
					width:		'30%',
					top:		'40%',
					left:		'35%',
					textAlign:	'center',
					color:		'#000',
					border:		'3px solid #aaa',
					backgroundColor:'#fff',
					cursor:		'wait'
				},
				
				// minimal style set used when themes are used
				themedCSS: {
					width:	'30%',
					top:	'40%',
					left:	'35%'
				},
			
				// styles for the overlay
				overlayCSS:  {
					backgroundColor: '#000',
					opacity:	  	 0.3,
					cursor:		  	 'wait'
				},
			
				// styles applied when using $.growlUI
				growlCSS: {
					width:  	'350px',
					top:		'10px',
					left:   	'',
					right:  	'10px',
					border: 	'none',
					padding:	'5px',
					opacity:	0.6,
					cursor: 	'default',
					color:		'#fff',
					backgroundColor: '#000',
					'-webkit-border-radius': '10px',
					'-moz-border-radius':	 '10px',
					'border-radius': 		 '10px'
				},
				
				// IE issues: 'about:blank' fails on HTTPS and javascript:false is s-l-o-w
				// (hat tip to Jorge H. N. de Vasconcelos)
				iframeSrc: /^https/i.test(window.location.href || '') ? 'javascript:false' : 'about:blank',
			
				// force usage of iframe in non-IE browsers (handy for blocking applets)
				forceIframe: false,
			
				// z-index for the blocking overlay
				baseZ: 1000,
			
				// set these to true to have the message automatically centered
				centerX: true, // <-- only effects element blocking (page block controlled via css above)
				centerY: true,
			
				// allow body element to be stetched in ie6; this makes blocking look better
				// on "short" pages.  disable if you wish to prevent changes to the body height
				allowBodyStretch: true,
			
				// enable if you want key and mouse events to be disabled for content that is blocked
				bindEvents: true,
			
				// be default blockUI will supress tab navigation from leaving blocking content
				// (if bindEvents is true)
				constrainTabKey: true,
			
				// fadeIn time in millis; set to 0 to disable fadeIn on block
				fadeIn:  200,
			
				// fadeOut time in millis; set to 0 to disable fadeOut on unblock
				fadeOut:  400,
			
				// time in millis to wait before auto-unblocking; set to 0 to disable auto-unblock
				timeout: 0,
			
				// disable if you don't want to show the overlay
				showOverlay: true,
			
				// if true, focus will be placed in the first available input field when
				// page blocking
				focusInput: true,
			
				// suppresses the use of overlay styles on FF/Linux (due to performance issues with opacity)
				applyPlatformOpacityRules: true,
				
				// callback method invoked when fadeIn has completed and blocking message is visible
				onBlock: null,
			
				// callback method invoked when unblocking has completed; the callback is
				// passed the element that has been unblocked (which is the window object for page
				// blocks) and the options that were passed to the unblock call:
				//	 onUnblock(element, options)
				onUnblock: null,
			
				// don't ask; if you really must know: http://groups.google.com/group/jquery-en/browse_thread/thread/36640a8730503595/2f6a79a77a78e493#2f6a79a77a78e493
				quirksmodeOffsetHack: 4,
			
				// class name of the message block
				blockMsgClass: 'blockMsg'
			};
			
			// private data and functions follow...
			
			var pageBlock = null;
			var pageBlockEls = [];
			
			function install(el, opts) {
				var full = (el == window);
				var msg = opts && opts.message !== undefined ? opts.message : undefined;
				opts = $.extend({}, $.blockUI.defaults, opts || {});
				opts.overlayCSS = $.extend({}, $.blockUI.defaults.overlayCSS, opts.overlayCSS || {});
				var css = $.extend({}, $.blockUI.defaults.css, opts.css || {});
				var themedCSS = $.extend({}, $.blockUI.defaults.themedCSS, opts.themedCSS || {});
				msg = msg === undefined ? opts.message : msg;
			
				// remove the current block (if there is one)
				if (full && pageBlock)
					remove(window, {fadeOut:0});
			
				// if an existing element is being used as the blocking content then we capture
				// its current place in the DOM (and current display style) so we can restore
				// it when we unblock
				if (msg && typeof msg != 'string' && (msg.parentNode || msg.jquery)) {
					var node = msg.jquery ? msg[0] : msg;
					var data = {};
					$(el).data('blockUI.history', data);
					data.el = node;
					data.parent = node.parentNode;
					data.display = node.style.display;
					data.position = node.style.position;
					if (data.parent)
						data.parent.removeChild(node);
				}
			
				var z = opts.baseZ;
			
				// blockUI uses 3 layers for blocking, for simplicity they are all used on every platform;
				// layer1 is the iframe layer which is used to supress bleed through of underlying content
				// layer2 is the overlay layer which has opacity and a wait cursor (by default)
				// layer3 is the message content that is displayed while blocking
			
				var lyr1 = ($.browser.msie || opts.forceIframe) 
					? $('<iframe class="blockUI" style="z-index:'+ (z++) +';display:none;border:none;margin:0;padding:0;position:absolute;width:100%;height:100%;top:0;left:0" src="'+opts.iframeSrc+'"></iframe>')
					: $('<div class="blockUI" style="display:none"></div>');
				var lyr2 = $('<div class="blockUI blockOverlay" style="z-index:'+ (z++) +';display:none;border:none;margin:0;padding:0;width:100%;height:100%;top:0;left:0"></div>');
				
				var lyr3, s;
				if (opts.theme && full) {
					s = '<div class="blockUI ' + opts.blockMsgClass + ' blockPage ui-dialog ui-widget ui-corner-all" style="z-index:'+z+';display:none;position:fixed">' +
							'<div class="ui-widget-header ui-dialog-titlebar blockTitle">'+(opts.title || '&nbsp;')+'</div>' +
							'<div class="ui-widget-content ui-dialog-content"></div>' +
						'</div>';
				}
				else if (opts.theme) {
					s = '<div class="blockUI ' + opts.blockMsgClass + ' blockElement ui-dialog ui-widget ui-corner-all" style="z-index:'+z+';display:none;position:absolute">' +
							'<div class="ui-widget-header ui-dialog-titlebar blockTitle">'+(opts.title || '&nbsp;')+'</div>' +
							'<div class="ui-widget-content ui-dialog-content"></div>' +
						'</div>';
				}
				else if (full) {
					s = '<div class="blockUI ' + opts.blockMsgClass + ' blockPage" style="z-index:'+z+';display:none;position:fixed"></div>';
				}			
				else {
					s = '<div class="blockUI ' + opts.blockMsgClass + ' blockElement" style="z-index:'+z+';display:none;position:absolute"></div>';
				}
				lyr3 = $(s);
			
				// if we have a message, style it
				if (msg) {
					if (opts.theme) {
						lyr3.css(themedCSS);
						lyr3.addClass('ui-widget-content');
					}
					else 
						lyr3.css(css);
				}
			
				// style the overlay
				if (!opts.applyPlatformOpacityRules || !($.browser.mozilla && /Linux/.test(navigator.platform)))
					lyr2.css(opts.overlayCSS);
				lyr2.css('position', full ? 'fixed' : 'absolute');
			
				// make iframe layer transparent in IE
				if ($.browser.msie || opts.forceIframe)
					lyr1.css('opacity',0.0);
			
				//$([lyr1[0],lyr2[0],lyr3[0]]).appendTo(full ? 'body' : el);
				var layers = [lyr1,lyr2,lyr3], $par = full ? $('body') : $(el);
				$.each(layers, function() {
					this.appendTo($par);
				});
				
				if (opts.theme && opts.draggable && $.fn.draggable) {
					lyr3.draggable({
						handle: '.ui-dialog-titlebar',
						cancel: 'li'
					});
				}
			
				// ie7 must use absolute positioning in quirks mode and to account for activex issues (when scrolling)
				var expr = setExpr && (!$.boxModel || $('object,embed', full ? null : el).length > 0);
				if (ie6 || expr) {
					// give body 100% height
					if (full && opts.allowBodyStretch && $.boxModel)
						$('html,body').css('height','100%');
			
					// fix ie6 issue when blocked element has a border width
					if ((ie6 || !$.boxModel) && !full) {
						var t = sz(el,'borderTopWidth'), l = sz(el,'borderLeftWidth');
						var fixT = t ? '(0 - '+t+')' : 0;
						var fixL = l ? '(0 - '+l+')' : 0;
					}
			
					// simulate fixed position
					$.each([lyr1,lyr2,lyr3], function(i,o) {
						var s = o[0].style;
						s.position = 'absolute';
						if (i < 2) {
							full ? s.setExpression('height','Math.max(document.body.scrollHeight, document.body.offsetHeight) - (jQuery.boxModel?0:'+opts.quirksmodeOffsetHack+') + "px"')
								 : s.setExpression('height','this.parentNode.offsetHeight + "px"');
							full ? s.setExpression('width','jQuery.boxModel && document.documentElement.clientWidth || document.body.clientWidth + "px"')
								 : s.setExpression('width','this.parentNode.offsetWidth + "px"');
							if (fixL) s.setExpression('left', fixL);
							if (fixT) s.setExpression('top', fixT);
						}
						else if (opts.centerY) {
							if (full) s.setExpression('top','(document.documentElement.clientHeight || document.body.clientHeight) / 2 - (this.offsetHeight / 2) + (blah = document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop) + "px"');
							s.marginTop = 0;
						}
						else if (!opts.centerY && full) {
							var top = (opts.css && opts.css.top) ? parseInt(opts.css.top) : 0;
							var expression = '((document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop) + '+top+') + "px"';
							s.setExpression('top',expression);
						}
					});
				}
			
				// show the message
				if (msg) {
					if (opts.theme)
						lyr3.find('.ui-widget-content').append(msg);
					else
						lyr3.append(msg);
					if (msg.jquery || msg.nodeType)
						$(msg).show();
				}
			
				if (($.browser.msie || opts.forceIframe) && opts.showOverlay)
					lyr1.show(); // opacity is zero
				if (opts.fadeIn) {
					var cb = opts.onBlock ? opts.onBlock : noOp;
					var cb1 = (opts.showOverlay && !msg) ? cb : noOp;
					var cb2 = msg ? cb : noOp;
					if (opts.showOverlay)
						lyr2._fadeIn(opts.fadeIn, cb1);
					if (msg)
						lyr3._fadeIn(opts.fadeIn, cb2);
				}
				else {
					if (opts.showOverlay)
						lyr2.show();
					if (msg)
						lyr3.show();
					if (opts.onBlock)
						opts.onBlock();
				}
			
				// bind key and mouse events
				bind(1, el, opts);
			
				if (full) {
					pageBlock = lyr3[0];
					pageBlockEls = $(':input:enabled:visible',pageBlock);
					if (opts.focusInput)
						setTimeout(focus, 20);
				}
				else
					center(lyr3[0], opts.centerX, opts.centerY);
			
				if (opts.timeout) {
					// auto-unblock
					var to = setTimeout(function() {
						full ? $.unblockUI(opts) : $(el).unblock(opts);
					}, opts.timeout);
					$(el).data('blockUI.timeout', to);
				}
			};
			
			// remove the block
			function remove(el, opts) {
				var full = (el == window);
				var $el = $(el);
				var data = $el.data('blockUI.history');
				var to = $el.data('blockUI.timeout');
				if (to) {
					clearTimeout(to);
					$el.removeData('blockUI.timeout');
				}
				opts = $.extend({}, $.blockUI.defaults, opts || {});
				bind(0, el, opts); // unbind events
				
				var els;
				if (full) // crazy selector to handle odd field errors in ie6/7
					els = $('body').children().filter('.blockUI').add('body > .blockUI');
				else
					els = $('.blockUI', el);
			
				if (full)
					pageBlock = pageBlockEls = null;
			
				if (opts.fadeOut) {
					els.fadeOut(opts.fadeOut);
					setTimeout(function() { reset(els,data,opts,el); }, opts.fadeOut);
				}
				else
					reset(els, data, opts, el);
			};
			
			// move blocking element back into the DOM where it started
			function reset(els,data,opts,el) {
				els.each(function(i,o) {
					// remove via DOM calls so we don't lose event handlers
					if (this.parentNode)
						this.parentNode.removeChild(this);
				});
			
				if (data && data.el) {
					data.el.style.display = data.display;
					data.el.style.position = data.position;
					if (data.parent)
						data.parent.appendChild(data.el);
					$(el).removeData('blockUI.history');
				}
			
				if (typeof opts.onUnblock == 'function')
					opts.onUnblock(el,opts);
			};
			
			// bind/unbind the handler
			function bind(b, el, opts) {
				var full = el == window, $el = $(el);
			
				// don't bother unbinding if there is nothing to unbind
				if (!b && (full && !pageBlock || !full && !$el.data('blockUI.isBlocked')))
					return;
				if (!full)
					$el.data('blockUI.isBlocked', b);
			
				// don't bind events when overlay is not in use or if bindEvents is false
				if (!opts.bindEvents || (b && !opts.showOverlay)) 
					return;
			
				// bind anchors and inputs for mouse and key events
				var events = 'mousedown mouseup keydown keypress';
				b ? $(document).bind(events, opts, handler) : $(document).unbind(events, handler);
			
			// former impl...
			//	   var $e = $('a,:input');
			//	   b ? $e.bind(events, opts, handler) : $e.unbind(events, handler);
			};
			
			// event handler to suppress keyboard/mouse events when blocking
			function handler(e) {
				// allow tab navigation (conditionally)
				if (e.keyCode && e.keyCode == 9) {
					if (pageBlock && e.data.constrainTabKey) {
						var els = pageBlockEls;
						var fwd = !e.shiftKey && e.target == els[els.length-1];
						var back = e.shiftKey && e.target == els[0];
						if (fwd || back) {
							setTimeout(function(){focus(back)},10);
							return false;
						}
					}
				}
				var opts = e.data;
				// allow events within the message content
				if ($(e.target).parents('div.' + opts.blockMsgClass).length > 0)
					return true;
			
				// allow events for content that is not being blocked
				return $(e.target).parents().children().filter('div.blockUI').length == 0;
			};
			
			function focus(back) {
				if (!pageBlockEls)
					return;
				var e = pageBlockEls[back===true ? pageBlockEls.length-1 : 0];
				if (e)
					e.focus();
			};
			
			function center(el, x, y) {
				var p = el.parentNode, s = el.style;
				var l = ((p.offsetWidth - el.offsetWidth)/2) - sz(p,'borderLeftWidth');
				var t = ((p.offsetHeight - el.offsetHeight)/2) - sz(p,'borderTopWidth');
				if (x) s.left = l > 0 ? (l+'px') : '0';
				if (y) s.top  = t > 0 ? (t+'px') : '0';
			};
			
			function sz(el, p) {
				return parseInt($.css(el,p))||0;
			};
			
			})(jQuery);
	//block ui관련 끝 
	
	//updown slide panel관련 시작
	
	 (function($){
	    $.fn.naraTopPanel = function(givenOpts) {
	        opts = $.extend({
				position: 'top',			// 패널위치(top:화면상단, bottom:화면하단)
				height: '128px',				// 패널높이
				speed: 'fast',			// 패널오프닝 속도 : 'slow', 'normal', 'fast', 
				moveContainer: true,		// 패널페이지 컨테이사용여부: 사용하지않음 
				container: '#container',	// 컨테이너obj : 사용하지않음
				openBtn: '.open',			// 오픈버턴 이벤트를 받을 obj(id혹은 class) : 'naraToptrigger' div내부에 존재함 
				closeBtn: '.close',			// 닫기버턴 이벤트를 받을 obj(id혹은 class) : 'naraToptrigger' div내부에 존재함
				openLink: '.openpanel',		// 오픈링크 이벤트를 받을 obj(id혹은 class) : 'naraToptrigger' div내부에 존재함
				closeLink: '.closepanel',	// 닫기링크 이벤트를 받을 obj(id혹은 class) : 'naraToptrigger' div내부에 존재함
				keepOpenCheck: '#keepopen',	
				showTrigger: true,			
				showOnLoad: false				
	        }, givenOpts);
			
			
			var $this = $(this);
			
			
			var containerpadding;
			var aniOpenArgs = {};
			var aniCloseArgs = {};
			
			
			if (opts.position == 'top') {
				//$this.addClass('top');
				//$('#naraToptrigger').addClass('top');
				containerpadding = 'padding-top';
			} else {
				//$this.addClass('bottom');
				//$('#naraToptrigger').addClass('bottom');
				$('#naraToppaneloptions').css('margin-right','14px');
				containerpadding = 'padding-bottom';
			};
			
			//패널높이 설정
			$this.css('height', opts.height);
			
			
			var newpadding = opts.height.replace("px","");

			//기본적으로 패널높이 25++
			newpadding = parseInt(opts.height) + 25;
			
			aniOpenArgs[containerpadding] = newpadding;
			aniCloseArgs[containerpadding] = 0;
			

			if (!opts.showTrigger) {
				$('#naraToptrigger').css('display', 'none');
				$('#naraToppaneloptions').css('margin-right','14px');
				$('#naraToppaneloptions').prepend('<p><a href="#" class="closepanel">Close Panel</a></p>');
			};
			
			
			if ((readCookie('naraTopPanel') == "1")||(opts.showOnLoad)) {
				if (opts.moveContainer) { $(opts.container).css(containerpadding,newpadding); };
				$this.css({'display':'block'});
				$(opts.openBtn).css({'display':'none'});
				$(opts.closeBtn).css({'display':'block'});
				$(opts.keepOpenCheck).attr('checked', true);
			};
			
	
			$(opts.openBtn).click(function(){
				//top페이지의 상단 프레임을 조절함
				naraTopFuncSlideDown();
				
				$this.slideDown(opts.speed);
				if (opts.moveContainer) { $(opts.container).animate(aniOpenArgs, opts.speed); };
				return false;
			});	
			

			$(opts.openLink).click(function(){
				
				
				$this.slideDown(opts.speed);
				if (opts.moveContainer) { $(opts.container).animate(aniOpenArgs, opts.speed); };
				$(opts.openBtn).css({'display':'none'});
				$(opts.closeBtn).css({'display':'block'});
				return false;
			});	
			

			$(opts.closeBtn).click(function(){
				//top페이지의 상단 프레임을 조절함
				naraTopFuncSlideUp();
				
				$this.slideUp(opts.speed);
				if (opts.moveContainer) { $(opts.container).animate(aniCloseArgs, opts.speed); };
				return false;
			});
			
			
			$(opts.closeLink).click(function(){
				
				
				$this.slideUp(opts.speed);
				if (opts.moveContainer) { $(opts.container).animate(aniCloseArgs, opts.speed); };
				$(opts.openBtn).css({'display':'block'});
				$(opts.closeBtn).css({'display':'none'});
				return false;
			});
			
			
			$('#naraToptrigger a').click(function () {
				//alert($(this).attr('class'));
				$('#naraToptrigger a').toggle();
				return false;
			});
			
			
			$(opts.keepOpenCheck).click(function () {
				if ($(opts.keepOpenCheck).attr('checked')) {
					
					createCookie('naraTopPanel','1', 0);
					
				} else {
					
					createCookie('naraTopPanel','0', 0);
					
				};
			});
			
			function createCookie(name,value,days) {
				if (days) {
					var date = new Date();
					date.setTime(date.getTime()+(days*24*60*60*1000));
					var expires = "; expires="+date.toGMTString();
				} else {
					var expires = "";
				}
				document.cookie = name+"="+value+expires+"; path=/";
			}
		
			function readCookie(name) {
				var nameEQ = name + "=";
				var ca = document.cookie.split(';');
				for(var i=0;i < ca.length;i++) {
					var c = ca[i];
					while (c.charAt(0)==' ') c = c.substring(1,c.length);
					if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
				}
				return null;
			}
			
			function eraseCookie(name) {
				createCookie(name,"",-1);
			}
		};
	})(jQuery);
	//updown slide panel관련 끝
	 
	//div corner관련 시작
	 (function($) { 

		 var style = document.createElement('div').style,
		     moz = style['MozBorderRadius'] !== undefined,
		     webkit = style['WebkitBorderRadius'] !== undefined,
		     radius = style['borderRadius'] !== undefined || style['BorderRadius'] !== undefined,
		     mode = document.documentMode || 0,
		     noBottomFold = $.browser.msie && (($.browser.version < 8 && !mode) || mode < 8),

		     expr = $.browser.msie && (function() {
		         var div = document.createElement('div');
		         try { div.style.setExpression('width','0+0'); div.style.removeExpression('width'); }
		         catch(e) { return false; }
		         return true;
		     })();

		 $.support = $.support || {};
		 $.support.borderRadius = moz || webkit || radius; // so you can do:  if (!$.support.borderRadius) $('#myDiv').corner();

		 function sz(el, p) { 
		     return parseInt($.css(el,p))||0; 
		 };
		 function hex2(s) {
		     var s = parseInt(s).toString(16);
		     return ( s.length < 2 ) ? '0'+s : s;
		 };
		 function gpc(node) {
		     while(node) {
		         var v = $.css(node,'backgroundColor'), rgb;
		         if (v && v != 'transparent' && v != 'rgba(0, 0, 0, 0)') {
		             if (v.indexOf('rgb') >= 0) { 
		                 rgb = v.match(/\d+/g); 
		                 return '#'+ hex2(rgb[0]) + hex2(rgb[1]) + hex2(rgb[2]);
		             }
		             return v;
		         }
		         if (node.nodeName.toLowerCase() == 'html')
		             break;
		         node = node.parentNode; // keep walking if transparent
		     }
		     return '#ffffff';
		 };

		 function getWidth(fx, i, width) {
		     switch(fx) {
		     case 'round':  return Math.round(width*(1-Math.cos(Math.asin(i/width))));
		     case 'cool':   return Math.round(width*(1+Math.cos(Math.asin(i/width))));
		     case 'sharp':  return Math.round(width*(1-Math.cos(Math.acos(i/width))));
		     case 'bite':   return Math.round(width*(Math.cos(Math.asin((width-i-1)/width))));
		     case 'slide':  return Math.round(width*(Math.atan2(i,width/i)));
		     case 'jut':    return Math.round(width*(Math.atan2(width,(width-i-1))));
		     case 'curl':   return Math.round(width*(Math.atan(i)));
		     case 'tear':   return Math.round(width*(Math.cos(i)));
		     case 'wicked': return Math.round(width*(Math.tan(i)));
		     case 'long':   return Math.round(width*(Math.sqrt(i)));
		     case 'sculpt': return Math.round(width*(Math.log((width-i-1),width)));
		     case 'dogfold':
		     case 'dog':    return (i&1) ? (i+1) : width;
		     case 'dog2':   return (i&2) ? (i+1) : width;
		     case 'dog3':   return (i&3) ? (i+1) : width;
		     case 'fray':   return (i%2)*width;
		     case 'notch':  return width; 
		     case 'bevelfold':
		     case 'bevel':  return i+1;
		     }
		 };

		 $.fn.corner = function(options) {
		     // in 1.3+ we can fix mistakes with the ready state
		     if (this.length == 0) {
		         if (!$.isReady && this.selector) {
		             var s = this.selector, c = this.context;
		             $(function() {
		                 $(s,c).corner(options);
		             });
		         }
		         return this;
		     }

		     return this.each(function(index){
		         var $this = $(this),
		             // meta values override options
		             o = [$this.attr($.fn.corner.defaults.metaAttr) || '', options || ''].join(' ').toLowerCase(),
		             keep = /keep/.test(o),                       // keep borders?
		             cc = ((o.match(/cc:(#[0-9a-f]+)/)||[])[1]),  // corner color
		             sc = ((o.match(/sc:(#[0-9a-f]+)/)||[])[1]),  // strip color
		             width = parseInt((o.match(/(\d+)px/)||[])[1]) || 10, // corner width
		             re = /round|bevelfold|bevel|notch|bite|cool|sharp|slide|jut|curl|tear|fray|wicked|sculpt|long|dog3|dog2|dogfold|dog/,
		             fx = ((o.match(re)||['round'])[0]),
		             fold = /dogfold|bevelfold/.test(o),
		             edges = { T:0, B:1 },
		             opts = {
		                 TL:  /top|tl|left/.test(o),       TR:  /top|tr|right/.test(o),
		                 BL:  /bottom|bl|left/.test(o),    BR:  /bottom|br|right/.test(o)
		             },
		             // vars used in func later
		             strip, pad, cssHeight, j, bot, d, ds, bw, i, w, e, c, common, $horz;
		         
		         if ( !opts.TL && !opts.TR && !opts.BL && !opts.BR )
		             opts = { TL:1, TR:1, BL:1, BR:1 };
		             
		         // support native rounding
		         if ($.fn.corner.defaults.useNative && fx == 'round' && (radius || moz || webkit) && !cc && !sc) {
		             if (opts.TL)
		                 $this.css(radius ? 'border-top-left-radius' : moz ? '-moz-border-radius-topleft' : '-webkit-border-top-left-radius', width + 'px');
		             if (opts.TR)
		                 $this.css(radius ? 'border-top-right-radius' : moz ? '-moz-border-radius-topright' : '-webkit-border-top-right-radius', width + 'px');
		             if (opts.BL)
		                 $this.css(radius ? 'border-bottom-left-radius' : moz ? '-moz-border-radius-bottomleft' : '-webkit-border-bottom-left-radius', width + 'px');
		             if (opts.BR)
		                 $this.css(radius ? 'border-bottom-right-radius' : moz ? '-moz-border-radius-bottomright' : '-webkit-border-bottom-right-radius', width + 'px');
		             return;
		         }
		             
		         strip = document.createElement('div');
		         $(strip).css({
		             overflow: 'hidden',
		             height: '1px',
		             minHeight: '1px',
		             fontSize: '1px',
		             backgroundColor: sc || 'transparent',
		             borderStyle: 'solid'
		         });
		     
		         pad = {
		             T: parseInt($.css(this,'paddingTop'))||0,     R: parseInt($.css(this,'paddingRight'))||0,
		             B: parseInt($.css(this,'paddingBottom'))||0,  L: parseInt($.css(this,'paddingLeft'))||0
		         };

		         if (typeof this.style.zoom != undefined) this.style.zoom = 1; // force 'hasLayout' in IE
		         if (!keep) this.style.border = 'none';
		         strip.style.borderColor = cc || gpc(this.parentNode);
		         cssHeight = $(this).outerHeight();

		         for (j in edges) {
		             bot = edges[j];
		             // only add stips if needed
		             if ((bot && (opts.BL || opts.BR)) || (!bot && (opts.TL || opts.TR))) {
		                 strip.style.borderStyle = 'none '+(opts[j+'R']?'solid':'none')+' none '+(opts[j+'L']?'solid':'none');
		                 d = document.createElement('div');
		                 $(d).addClass('jquery-corner');
		                 ds = d.style;

		                 bot ? this.appendChild(d) : this.insertBefore(d, this.firstChild);

		                 if (bot && cssHeight != 'auto') {
		                     if ($.css(this,'position') == 'static')
		                         this.style.position = 'relative';
		                     ds.position = 'absolute';
		                     ds.bottom = ds.left = ds.padding = ds.margin = '0';
		                     if (expr)
		                         ds.setExpression('width', 'this.parentNode.offsetWidth');
		                     else
		                         ds.width = '100%';
		                 }
		                 else if (!bot && $.browser.msie) {
		                     if ($.css(this,'position') == 'static')
		                         this.style.position = 'relative';
		                     ds.position = 'absolute';
		                     ds.top = ds.left = ds.right = ds.padding = ds.margin = '0';
		                     
		                     // fix ie6 problem when blocked element has a border width
		                     if (expr) {
		                         bw = sz(this,'borderLeftWidth') + sz(this,'borderRightWidth');
		                         ds.setExpression('width', 'this.parentNode.offsetWidth - '+bw+'+ "px"');
		                     }
		                     else
		                         ds.width = '100%';
		                 }
		                 else {
		                     ds.position = 'relative';
		                     ds.margin = !bot ? '-'+pad.T+'px -'+pad.R+'px '+(pad.T-width)+'px -'+pad.L+'px' : 
		                                         (pad.B-width)+'px -'+pad.R+'px -'+pad.B+'px -'+pad.L+'px';                
		                 }

		                 for (i=0; i < width; i++) {
		                     w = Math.max(0,getWidth(fx,i, width));
		                     e = strip.cloneNode(false);
		                     e.style.borderWidth = '0 '+(opts[j+'R']?w:0)+'px 0 '+(opts[j+'L']?w:0)+'px';
		                     bot ? d.appendChild(e) : d.insertBefore(e, d.firstChild);
		                 }
		                 
		                 if (fold && $.support.boxModel) {
		                     if (bot && noBottomFold) continue;
		                     for (c in opts) {
		                         if (!opts[c]) continue;
		                         if (bot && (c == 'TL' || c == 'TR')) continue;
		                         if (!bot && (c == 'BL' || c == 'BR')) continue;
		                         
		                         common = { position: 'absolute', border: 'none', margin: 0, padding: 0, overflow: 'hidden', backgroundColor: strip.style.borderColor };
		                         $horz = $('<div/>').css(common).css({ width: width + 'px', height: '1px' });
		                         switch(c) {
		                         case 'TL': $horz.css({ bottom: 0, left: 0 }); break;
		                         case 'TR': $horz.css({ bottom: 0, right: 0 }); break;
		                         case 'BL': $horz.css({ top: 0, left: 0 }); break;
		                         case 'BR': $horz.css({ top: 0, right: 0 }); break;
		                         }
		                         d.appendChild($horz[0]);
		                         
		                         var $vert = $('<div/>').css(common).css({ top: 0, bottom: 0, width: '1px', height: width + 'px' });
		                         switch(c) {
		                         case 'TL': $vert.css({ left: width }); break;
		                         case 'TR': $vert.css({ right: width }); break;
		                         case 'BL': $vert.css({ left: width }); break;
		                         case 'BR': $vert.css({ right: width }); break;
		                         }
		                         d.appendChild($vert[0]);
		                     }
		                 }
		             }
		         }
		     });
		 };

		 $.fn.uncorner = function() { 
		     if (radius || moz || webkit)
		         this.css(radius ? 'border-radius' : moz ? '-moz-border-radius' : '-webkit-border-radius', 0);
		     $('div.jquery-corner', this).remove();
		     return this;
		 };

		 // expose options
		 $.fn.corner.defaults = {
		     useNative: true, // true if plugin should attempt to use native browser support for border radius rounding
		     metaAttr:  'data-corner' // name of meta attribute to use for options
		 };
		     
		 })(jQuery);
	 //div corner관련 끝
	 
	 //live query관련 시작
	 $.extend($.fn, {
			livequery: function(type, fn, fn2) {
				var self = this, q;
				
				// Handle different call patterns
				if ($.isFunction(type))
					fn2 = fn, fn = type, type = undefined;
					
				// See if Live Query already exists
				$.each( $.livequery.queries, function(i, query) {
					if ( self.selector == query.selector && self.context == query.context &&
						type == query.type && (!fn || fn.$lqguid == query.fn.$lqguid) && (!fn2 || fn2.$lqguid == query.fn2.$lqguid) )
							// Found the query, exit the each loop
							return (q = query) && false;
				});
				
				// Create new Live Query if it wasn't found
				q = q || new $.livequery(this.selector, this.context, type, fn, fn2);
				
				// Make sure it is running
				q.stopped = false;
				
				// Run it immediately for the first time
				q.run();
				
				// Contnue the chain
				return this;
			},
			
			expire: function(type, fn, fn2) {
				var self = this;
				
				// Handle different call patterns
				if ($.isFunction(type))
					fn2 = fn, fn = type, type = undefined;
					
				// Find the Live Query based on arguments and stop it
				$.each( $.livequery.queries, function(i, query) {
					if ( self.selector == query.selector && self.context == query.context && 
						(!type || type == query.type) && (!fn || fn.$lqguid == query.fn.$lqguid) && (!fn2 || fn2.$lqguid == query.fn2.$lqguid) && !this.stopped )
							$.livequery.stop(query.id);
				});
				
				// Continue the chain
				return this;
			}
		});

		$.livequery = function(selector, context, type, fn, fn2) {
			this.selector = selector;
			this.context  = context || document;
			this.type     = type;
			this.fn       = fn;
			this.fn2      = fn2;
			this.elements = [];
			this.stopped  = false;
			
			// The id is the index of the Live Query in $.livequery.queries
			this.id = $.livequery.queries.push(this)-1;
			
			// Mark the functions for matching later on
			fn.$lqguid = fn.$lqguid || $.livequery.guid++;
			if (fn2) fn2.$lqguid = fn2.$lqguid || $.livequery.guid++;
			
			// Return the Live Query
			return this;
		};

		$.livequery.prototype = {
			stop: function() {
				var query = this;
				
				if ( this.type )
					// Unbind all bound events
					this.elements.unbind(this.type, this.fn);
				else if (this.fn2)
					// Call the second function for all matched elements
					this.elements.each(function(i, el) {
						query.fn2.apply(el);
					});
					
				// Clear out matched elements
				this.elements = [];
				
				// Stop the Live Query from running until restarted
				this.stopped = true;
			},
			
			run: function() {
				// Short-circuit if stopped
				if ( this.stopped ) return;
				var query = this;
				
				var oEls = this.elements,
					els  = $(this.selector, this.context),
					nEls = els.not(oEls);
				
				// Set elements to the latest set of matched elements
				this.elements = els;
				
				if (this.type) {
					// Bind events to newly matched elements
					nEls.bind(this.type, this.fn);
					
					// Unbind events to elements no longer matched
					if (oEls.length > 0)
						$.each(oEls, function(i, el) {
							if ( $.inArray(el, els) < 0 )
								$.event.remove(el, query.type, query.fn);
						});
				}
				else {
					// Call the first function for newly matched elements
					nEls.each(function() {
						query.fn.apply(this);
					});
					
					// Call the second function for elements no longer matched
					if ( this.fn2 && oEls.length > 0 )
						$.each(oEls, function(i, el) {
							if ( $.inArray(el, els) < 0 )
								query.fn2.apply(el);
						});
				}
			}
		};

		$.extend($.livequery, {
			guid: 0,
			queries: [],
			queue: [],
			running: false,
			timeout: null,
			
			checkQueue: function() {
				if ( $.livequery.running && $.livequery.queue.length ) {
					var length = $.livequery.queue.length;
					// Run each Live Query currently in the queue
					while ( length-- )
						$.livequery.queries[ $.livequery.queue.shift() ].run();
				}
			},
			
			pause: function() {
				// Don't run anymore Live Queries until restarted
				$.livequery.running = false;
			},
			
			play: function() {
				// Restart Live Queries
				$.livequery.running = true;
				// Request a run of the Live Queries
				$.livequery.run();
			},
			
			registerPlugin: function() {
				$.each( arguments, function(i,n) {
					// Short-circuit if the method doesn't exist
					if (!$.fn[n]) return;
					
					// Save a reference to the original method
					var old = $.fn[n];
					
					// Create a new method
					$.fn[n] = function() {
						// Call the original method
						var r = old.apply(this, arguments);
						
						// Request a run of the Live Queries
						$.livequery.run();
						
						// Return the original methods result
						return r;
					}
				});
			},
			
			run: function(id) {
				if (id != undefined) {
					// Put the particular Live Query in the queue if it doesn't already exist
					if ( $.inArray(id, $.livequery.queue) < 0 )
						$.livequery.queue.push( id );
				}
				else
					// Put each Live Query in the queue if it doesn't already exist
					$.each( $.livequery.queries, function(id) {
						if ( $.inArray(id, $.livequery.queue) < 0 )
							$.livequery.queue.push( id );
					});
				
				// Clear timeout if it already exists
				if ($.livequery.timeout) clearTimeout($.livequery.timeout);
				// Create a timeout to check the queue and actually run the Live Queries
				$.livequery.timeout = setTimeout($.livequery.checkQueue, 20);
			},
			
			stop: function(id) {
				if (id != undefined)
					// Stop are particular Live Query
					$.livequery.queries[ id ].stop();
				else
					// Stop all Live Queries
					$.each( $.livequery.queries, function(id) {
						$.livequery.queries[ id ].stop();
					});
			}
		});

		// Register core DOM manipulation methods
		$.livequery.registerPlugin('append', 'prepend', 'after', 'before', 'wrap', 'attr', 'removeAttr', 'addClass', 'removeClass', 'toggleClass', 'empty', 'remove');

		// Run Live Queries when the Document is ready
		$(function() { $.livequery.play(); });


		// Save a reference to the original init method
		var init = $.prototype.init;

		// Create a new init method that exposes two new properties: selector and context
		$.prototype.init = function(a,c) {
			// Call the original init and save the result
			var r = init.apply(this, arguments);
			
			// Copy over properties if they exist already
			if (a && a.selector)
				r.context = a.context, r.selector = a.selector;
				
			// Set properties
			if ( typeof a == 'string' )
				r.context = c || document, r.selector = a;
			
			// Return the result
			return r;
		};

		// Give the init function the jQuery prototype for later instantiation (needed after Rev 4091)
		$.prototype.init.prototype = $.prototype;
	 //live query관련 끝
	
	 	
	 
	
})(jQuery);

//jquery.mb.extruder
//JBJ
(function($) {
	  document.extruder=new Object();
	  document.extruder.left = 0;
	  document.extruder.top = 0;
	  document.extruder.bottom = 0;
	  document.extruder.right = 0;
	  document.extruder.idx=0;
	  var isIE=$.browser.msie;

	  $.mbExtruder= {
	    author:"Matteo Bicocchi",
	    version:"2.1",
	    defaults:{
	      width:350,
	      positionFixed:true,
	      sensibility:800,
	      position:"top",
	      accordionPanels:true,
	      top:"auto",
	      extruderOpacity:1,
	      flapMargin:35,
	      textOrientation:"bt", // or "tb" (top-bottom or bottom-top)
	      onExtOpen:function(){},
	      onExtContentLoad:function(){},
	      onExtClose:function(){},
	      hidePanelsOnClose:true,
	      autoCloseTime:0,
	      slideTimer:300
	    },

	    buildMbExtruder: function(options){
	      return this.each (function (){
	        this.options = {};
	        $.extend (this.options, $.mbExtruder.defaults);
	        $.extend (this.options, options);
	        this.idx=document.extruder.idx;
	        document.extruder.idx++;
	        var extruder,extruderContent,wrapper,extruderStyle,wrapperStyle,txt,timer;
	        extruder= $(this);
	        extruderContent=extruder.html();

	        extruder.css("zIndex",100);

	        var isVertical = this.options.position=="left" || this.options.position=="right";

	        var extW= isVertical?1: this.options.width;

	        var c= $("<div/>").addClass("content").css({overflow:"hidden", width:extW});
	        c.append(extruderContent);
	        extruder.html(c);

	        var position=this.options.positionFixed?"fixed":"absolute";
	        extruder.addClass("extruder");
	        extruder.addClass(this.options.position);
	        var isHorizontal = this.options.position=="top" || this.options.position=="bottom";
	        extruderStyle=
	                this.options.position=="top"?
	                {position:position,top:0,left:"50%",marginLeft:-this.options.width/2,width:this.options.width}:
	                        this.options.position=="bottom"?
	                        {position:position,bottom:0,left:"50%",marginLeft:-this.options.width/2,width:this.options.width}:
	                                this.options.position=="left"?
	                                {position:position,top:0,left:0,width:1}:
	                                {position:position,top:0,right:0,width:1};
	        extruder.css(extruderStyle);
	        if(!isIE) extruder.css({opacity:this.options.extruderOpacity});
	        extruder.wrapInner("<div class='ext_wrapper'></div>");
	        wrapper= extruder.find(".ext_wrapper");

	        wrapperStyle={position:"absolute", width:isVertical?1:this.options.width};
	        wrapper.css(wrapperStyle);


	        if (isHorizontal){
	          this.options.position=="top"?document.extruder.top++:document.extruder.bottom++;
	          if (document.extruder.top>1 || document.extruder.bottom>1){
	            alert("more than 1 mb.extruder on top or bottom is not supported jet... hope soon!");
	            return;
	          }
	        }

	        if ($.metadata){
	          $.metadata.setType("class");
	          if (extruder.metadata().title) extruder.attr("extTitle",extruder.metadata().title);
	          if (extruder.metadata().url) extruder.attr("extUrl",extruder.metadata().url);
	          if (extruder.metadata().data) extruder.attr("extData",extruder.metadata().data);
	        }
	        var flapFooter=$("<div class='footer'/>");
	        var flap=$("<div class='flap'><span class='flapLabel'/></div>");
	        if (document.extruder.bottom){
	          wrapper.prepend(flapFooter);
	          wrapper.prepend(flap);
	        }else{
	          wrapper.append(flapFooter);
	          wrapper.append(flap);
	        }

	        txt=extruder.attr("extTitle")?extruder.attr("extTitle"): "";
	        var flapLabel = extruder.find(".flapLabel");
	        flapLabel.text(txt);
	        if(isVertical){
	          flapLabel.html(txt).css({whiteSpace:"noWrap"});//,height:this.options.flapDim
	          var orientation= this.options.textOrientation == "tb";
	          var labelH=extruder.find('.flapLabel').getFlipTextDim()[1];
	          extruder.find('.flapLabel').mbFlipText(orientation);
	        }else{
	          flapLabel.html(txt).css({whiteSpace:"noWrap"});
	        }

	        if (extruder.attr("extUrl")){
	            extruder.setMbExtruderContent({
	              url:extruder.attr("extUrl"),
	              data:extruder.attr("extData"),
	              callback: function(){
	                if (extruder.get(0).options.onExtContentLoad) extruder.get(0).options.onExtContentLoad();
	              }
	          })
	        }else{
	          var container=$("<div>").addClass("text").css({width:extruder.get(0).options.width-20, height:extruder.height()-20, overflowY:"auto"});
	          c.wrapInner(container);
	          extruder.setExtruderVoicesAction();
	        }

	        flap.bind("click",function(){
	          if (!extruder.attr("open")){
	            extruder.openMbExtruder();
	          }else{
	            extruder.closeMbExtruder();
	          }
	        });

	        c.bind("mouseleave", function(){
	          $(document).one("click.extruder"+extruder.get(0).idx,function(){extruder.closeMbExtruder();});
	          timer=setTimeout(function(){

	            if(extruder.get(0).options.autoCloseTime > 0){
	              extruder.closeMbExtruder();
	            }
	          },extruder.get(0).options.autoCloseTime);
	        }).bind("mouseenter", function(){clearTimeout(timer); $(document).unbind("click.extruder"+extruder.get(0).idx);});

	        if (isVertical){
	          c.css({ height:"100%"});
	          if(this.options.top=="auto") {
	            flap.css({top:100+(this.options.position=="left"?document.extruder.left:document.extruder.right)});
	            this.options.position=="left"?document.extruder.left+=labelH+this.options.flapMargin:document.extruder.right+= labelH+this.options.flapMargin;
	          }else{
	            flap.css({top:this.options.top});
	          }
	          var clicDiv=$("<div/>").css({position:"absolute",top:0,left:0,width:"100%",height:"100%",background:"transparent"});
	          flap.append(clicDiv);
	        }
	      });
	    },

	    setMbExtruderContent: function(options){
	      this.options = {
	        url:false,
	        data:"",
	        callback:function(){}
	      };
	      $.extend (this.options, options);
	      if (!this.options.url || this.options.url.length==0){
	        alert("internal error: no URL to call");
	        return;
	      }
	      var url=this.options.url;
	      var data=this.options.data;
	      var where=$(this), voice;
	      var cb= this.options.callback;
	      var container=$("<div>").addClass("container");
	      if (!($.browser.msie && $.browser.version<=7))
	        container.css({width:$(this).get(0).options.width});
	      where.find(".content").wrapInner(container);
	      $.ajax({
	        type: "POST",
	        url: url,
	        data: data,
	        success: function(html){
	          where.find(".container").append(html);
	          voice=where.find(".voice");
	          voice.hover(function(){$(this).addClass("hover");},function(){$(this).removeClass("hover");});
	          where.setExtruderVoicesAction();
	          if (cb) {
	            setTimeout(function(){cb();},100);
	          }
	        }
	      });
	    },

	    openMbExtruder:function(c){
	      var extruder= $(this);
	      extruder.attr("open",true);
	      $(document).unbind("click.extruder"+extruder.get(0).idx);
	      var opt= extruder.get(0).options;
	      extruder.addClass("open");
	      if(!isIE) extruder.css("opacity",1);
	      var position= opt.position;
	      extruder.mb_bringToFront();
	      if (position=="top" || position=="bottom"){
	        extruder.find('.content').slideDown( opt.slideTimer);
	        if(opt.onExtOpen) opt.onExtOpen();
	      }else{

	        if(!isIE) $(this).css("opacity",1);
	        extruder.find('.ext_wrapper').css({width:""});
	        extruder.find('.content').css({overflowX:"hidden", display:"block"});
	        extruder.find('.content').animate({ width: opt.width}, opt.slideTimer);
	        if(opt.onExtOpen) opt.onExtOpen();
	      }
	      if (c) {
	        setTimeout(function(){
	          $(document).one("click.extruder"+extruder.get(0).idx,function(){extruder.closeMbExtruder();});
	        },100);
	      }
	    },

	    closeMbExtruder:function(){
	      var extruder= $(this);
	      extruder.removeAttr("open");
	      var opt= extruder.get(0).options;
	      extruder.removeClass("open");
	      $(document).unbind("click.extruder"+extruder.get(0).idx);
	      if(!isIE) extruder.css("opacity",opt.extruderOpacity);
	      if(opt.hidePanelsOnClose) extruder.hidePanelsOnClose();
	      if (opt.position=="top" || opt.position=="bottom"){
	        extruder.find('.content').slideUp(opt.slideTimer);
	        if(opt.onExtClose) opt.onExtClose();
	      }else if (opt.position=="left" || opt.position=="right"){
	        extruder.find('.content').css({overflow:"hidden"});
	        extruder.find('.content').animate({ width: 1 }, opt.slideTimer,function(){
	          extruder.find('.ext_wrapper').css({width:1});
	          extruder.find('.content').css({overflow:"hidden",display:"none"});
	          if(opt.onExtClose) opt.onExtClose();
	        });
	      }
	    }
	  };

	  jQuery.fn.mb_bringToFront= function(){
	    var zi=10;
	    $('*').each(function() {
	      if($(this).css("position")=="absolute" ||$(this).css("position")=="fixed"){
	        var cur = parseInt($(this).css('zIndex'));
	        zi = cur > zi ? parseInt($(this).css('zIndex')) : zi;
	      }
	    });
	    $(this).css('zIndex',zi+=1);
	    return zi;
	  };

	  /*
	   * EXTRUDER CONTENT
	   */

	  $.fn.setExtruderVoicesAction=function(){
	    var extruder=$(this);
	    var opt=extruder.get(0).options;
	    var voices= $(this).find(".voice");
	    voices.each(function(){
	      var voice=$(this);
	      if ($.metadata){
	        $.metadata.setType("class");
	        if (voice.metadata().panel) voice.attr("panel",voice.metadata().panel);
	        if (voice.metadata().data) voice.attr("data",voice.metadata().data);
	        if (voice.metadata().disabled) voice.attr("setDisabled", voice.metadata().disabled);
	      }

	      if (voice.attr("setDisabled"))
	        voice.disableExtruderVoice();

	      if (voice.attr("panel") && voice.attr("panel")!="false"){
	        voice.append("<span class='settingsBtn'/>");
	        voice.find(".settingsBtn").css({opacity:.5});
	        voice.find(".settingsBtn").hover(
	                function(){
	                  $(this).css({opacity:1});
	                },
	                function(){
	                  $(this).not(".sel").css({opacity:.5});
	                }).click(function(){
	          if ($(this).parents().hasClass("sel")){
	            if(opt.accordionPanels)
	              extruder.hidePanelsOnClose();
	            else
	              $(this).closePanel();
	            return;
	          }

	          if(opt.accordionPanels){
	            extruder.find(".optionsPanel").slideUp(400,function(){$(this).remove();});
	            voices.removeClass("sel");
	            voices.find(".settingsBtn").removeClass("sel").css({opacity:.5});
	          }
	          var content=$("<div class='optionsPanel'></div>");
	          voice.after(content);
	          $.ajax({
	            type: "POST",
	            url: voice.attr("panel"),
	            data: voice.attr("data"),
	            success: function(html){
	              var c= $(html);
	              content.html(c);
	              content.children().not(".text")
	                      .addClass("panelVoice")
	                      .click(function(){
	                extruder.closeMbExtruder();
	              });
	              content.slideDown(400);
	            }
	          });
	          voice.addClass("sel");
	          voice.find(".settingsBtn").addClass("sel").css({opacity:1});
	        });
	      }

	      if (voice.find("a").length==0 && voice.attr("panel")){
	        voice.find(".label").not(".disabled").css("cursor","pointer").click(function(){
	          voice.find(".settingsBtn").click();
	        });
	      }

	      if ((!voice.attr("panel") ||voice.attr("panel")=="false" ) && (!voice.attr("setDisabled") || voice.attr("setDisabled")!="true")){
	        voice.find(".label").click(function(){
	          extruder.hidePanelsOnClose();
	          extruder.closeMbExtruder();
	        });
	      }
	    });
	  };

	  $.fn.disableExtruderVoice=function(){
	    var voice=$(this);
	    var label = voice.find(".label");
	    voice.removeClass("sel");
	    voice.next(".optionsPanel").slideUp(400,function(){$(this).remove();});
	    voice.attr("setDisabled",true);
	    label.css("opacity",.4);
	    voice.hover(function(){$(this).removeClass("hover");},function(){$(this).removeClass("hover");});
	    label.addClass("disabled").css("cursor","default");
	    voice.find(".settingsBtn").hide();
	    voice.bind("click",function(event){
	      event.stopPropagation();
	      return false;
	    });
	  };

	  $.fn.enableExtruderVoice=function(){
	    var voice=$(this);
	    voice.attr("setDisabled",false);
	    voice.find(".label").css("opacity",1);
	    voice.find(".label").removeClass("disabled").css("cursor","pointer");
	    voice.unbind("click");
	    voice.find(".settingsBtn").show();
	  };

	  $.fn.hidePanelsOnClose=function(){
	    var voices= $(this).find(".voice");
	    $(this).find(".optionsPanel").slideUp(400,function(){$(this).remove();});
	    voices.removeClass("sel");
	    voices.find(".settingsBtn").removeClass("sel").css("opacity",.5);
	  };

	  $.fn.openPanel=function(){
	    var voice=$(this).hasClass("voice") ? $(this) : $(this).find(".voice");
	    voice.each(function(){
	      if($(this).hasClass("sel")) return;
	      $(this).find(".settingsBtn").click();
	    })
	  };

	  $.fn.closePanel=function(){
	    var voice=$(this).hasClass("voice") ? $(this) : $(this).parent(".voice");
	    voice.next(".optionsPanel").slideUp(400,function(){$(this).remove();});
	    voice.removeClass("sel");
	    $(this).removeClass("sel").css("opacity",.5);
	  };

	  $.fn.buildMbExtruder=$.mbExtruder.buildMbExtruder;
	  $.fn.setMbExtruderContent=$.mbExtruder.setMbExtruderContent;
	  $.fn.closeMbExtruder=$.mbExtruder.closeMbExtruder;
	  $.fn.openMbExtruder=$.mbExtruder.openMbExtruder;

	})(jQuery);


/**
* 설명 : 사용자가 object에 입력한 가장 최근의 키보드 입력에 대한 keycode를 가져옵니다 
*       
* 사용방식 : 기존의 window.event를 넘겨받아 onkeypress에서 if keycode == 13 형식으로 구현된 코드를 대체합니다
*          onkeyxxxx 이벤트에 이와같은 형식의 코드는 사용하지 않도록 하거나 코드를 삭제합니다
* 구현    : if JQ_GetKeyCode == 13 ....
*          
*          
* 주의 : 
* 리턴 : 가장 최근에 사용자가 입력한 keycode
*/
function JQ_GetKeyCode()
{
	return naraKeyCode; 
}

/*
 * 설명 : 특정테이블에 행을 삭제합니다
 * naraTable : 필수입력입니다. 대상 테이블의  id 스트링을 넘깁니다 예)'tblBoard'
 * delIndex : 필수 입력입니다 '1'이외의 모든 정수 숫자값 삭제할 대상 row를 지정합니다  
 *                         '0'이면 제목이외의 모든 타이틀을 삭제합니다 
 * table id가 'dataTable'의 3번째 로우를 지웁니다 -> naraTableDeleteRow('dataTable', '3');
 */
function JQ_TableDeleteRow(naraTable, delIndex)
{
	var setNaraTable = "#" + naraTable;
	var delCnt;
	if (delIndex == '1')
	{
		alert('1외의 다른값을 입력해주세요. 최상단 행는 삭제할수 없습니다');
		return;
	}
	
	if ($(setNaraTable + " tr").length <= 2)
	{
		return;
	}
	
	if (delIndex == '0')
	{
		delCnt = 0;
		$(setNaraTable + " tr").each(function() 
		{
			if (delCnt != 0)
			{
				//타이틀은 지우지 않습니다
				$(this).remove();
			}
			delCnt = delCnt + 1;
		});
	}
	else 
	{
		$(setNaraTable + " tr:eq("+ delIndex +")").remove();
	}
	
	currIndex = $(setNaraTable + " tr").length;
	//alert(currIndex);
}

/*
 * 설명 : 특정 테이블의 특정 TR(row)의 특정 TD에 존재하는 텍스트 값을 읽어옵니다
 * tableId : 테이블 id입니다
 * trRowIndex : TR의 위치 인덱스입니다. 테이블의 헤더부터 시작하며 헤더의 인덱스는 0입니다 
 * tdColIndex : TD의 위치 인덱스입니다. 테이블의 TR의 맨 처음부터 시작하며 인덱스는 0부터 시작합니다 
 * 사용예 : naraTablGetTDtext('dataTable','1','5');
 */
function naraTablGetTDtext(tableId, trRowIndex, tdColIndex)
{
	var tmpObj = "#" + tableId + " " + "tr:eq(" + trRowIndex + ")" + " td:eq(" + tdColIndex + ")";
	return $(tmpObj).text();
}

/*
 * 설명 : 특정 테이블의 특정 TR(row)의 특정 TD에 존재하는 object(textbox, checkbox, radiobutton..)의 값을 읽어옵니다 
 *       textbox인경우 값을 
 *       checkbox인 경우 체크여부에 따른 true,false
 *       radio인 경우 선택여부에 따른 true,false
 *       를 반환합니다  
 *       
 * tableId : 테이블 id입니다
 * trRowIndex : TR의 위치 인덱스입니다. 테이블의 헤더부터 시작하며 헤더의 인덱스는 0입니다 
 * tdColIndex : TD의 위치 인덱스입니다. 테이블의 TR의 맨 처음부터 시작하며 인덱스는 0부터 시작합니다
 * objIndex   : TD안에 존재하는 object의 index입니다. td안에 textbox가 하나 존재한다면 '0'이며 
 *              checkbox와 textboxr가 각각 순서대로 하나씩 존재하는경우 checkbox의 값을 읽어올 경우 '0', textbox는 '1'입니다
 * 사용예 : 
 * <table id='datatable'>
 * 	<tr>
 *     <td><input type="text" value="2222"/><input type="text" value="3333"/>
 *     </td>
 *     <td><input type="text" value="7777"/>
 *     </td>
 *  </tr>
 *  <tr>
 *     <td><input type="text" value="333"/><input type="text" value="6666"/>
 *     </td>
 *  </tr>
 *  <tr>
 *     <td><input type="text" value="4444"/>
 *     </td>
 *  </tr>
 * </table>
 * 
 * naraTablGetTDtext('dataTable','0','0','0'); <-- '2222'를 반환합니다 
 * naraTablGetTDtext('dataTable','0','1','0'); <-- '7777'를 반환합니다
 * naraTablGetTDtext('dataTable','1','0','1'); <-- '6666'를 반환합니다
 * naraTablGetTDtext('dataTable','2','0','0'); <-- '4444'를 반환합니다
 */
function naraTablGetTDtextObject(tableId, trRowIndex, tdColIndex, objIndex)
{
	var tmpObjT = "table#" + tableId + " tr:eq(" + trRowIndex + ")" + " td:eq(" + tdColIndex + ")";
	var tmpObjI = "input:eq(" + objIndex + ")";
	
	var tmpVal = $(tmpObjT).find(tmpObjI).attr('type');
	var retVal;
	
	if (tmpVal == 'checkbox')
	{
		retVal = $(tmpObjT).find(tmpObjI).attr('checked');
	}
	else if (tmpVal == 'radio')
	{
		retVal = $(tmpObjT).find(tmpObjI).attr('checked');
	}
	else
	{
		retVal = $(tmpObjT).find(tmpObjI).attr('value');
	}

	return retVal;
	
			
	
}

/* 설명 : 특정테이블에 행을 추가합니다
 * naraTable : 필수입력입니다. 대상 테이블의  id 스트링을 넘깁니다 예)'tblBoard'
 * src : 필수입력입니다. 복사할 대상을 지정합니다 'first','last'둘중 하나 입니다 맨위 로우와 맨 아래 로우를 정합니다
 * tar : 필수입력입니다. 복사한 대상을 삽입할 위치를 지정합니다 'first','last'둘중 하나 입니다 맨위 로우와 맨 아래 로우를 정합니다
 * valueYN : 복사하는 시점에 원본row의 값을 유지할지를 정합니다 'Y'이면 값도 그대로 가져오며 'N'인경우 전부 빈값으로 가져옵니다
 * 
 * 사용 예 : JQ_TableInsertRow('dataTable','last','first','Y'); 맨 마지막줄을  맨위에 추가, 값은 유지 
 *           JQ_TableInsertRow('dataTable','last','last','N'); 맨 마지막줄을 한줄더 추가 , 값은 초기화
 */
function JQ_TableInsertRow(naraTable, src, tar, valueYN)
{
	//alert(src+":"+ tar+":"+ valueYN);
	var setNaraTable = "#" + naraTable;
	var lastRow;
	var currIndex; 
	
	currIndex = $(setNaraTable + " tr").length;
	tableRowIndex = tableRowIndex + 1;
	//원본 세팅
	if (src == "last")
	{
		lastRow = $(setNaraTable + " tr:last").clone();
	}
	else if (src == "first")
	{
		lastRow = $(setNaraTable + " tr:eq(1)").clone();
	}
	
	//타겟세팅
	if (tar == 'last')
	{
		
		$(setNaraTable).append(lastRow);
	}
	else if(tar == 'first')
	{
		$(setNaraTable).prepend(lastRow);
	}
	
	//값 세팅
	if (valueYN == 'N')
	{
		$(setNaraTable + " tr:last td").each(function() 
		{
			var $imgExist = $(this);
			var $tmpval = $(this).find("input");
			var getvalue = $tmpval.attr('value');
			log(typeof(getvalue));
			if (typeof(getvalue) == 'undefined')
			{

			}
			else
			{
				$tmpval.attr({'value':''});
			}
		
	    });
	}
	else
	{
	}
	
	
	$(setNaraTable + " tr:last td").each(function() 
	{
		var $tmpFile = $(this).find("input[type=file]");
		$tmpFile.attr({'name':naraTable + 'tr' + tableRowIndex});
	});
	
	
}


/**
* 설명 : 사용자가 object에 입력한 가장 최근의 키보드 입력에 대한 keycode를 저장합니다 
*       
* 사용방식 : nara project에서는 내부 함수로 사용합니다 따로 호출하여 사용하지 않습니다
* 구현    : 
*          
*          
* 주의 : 
* 리턴 : 없음
*/
function JQ_SetKeyCode(keycode)
{
	naraKeyCode = keycode;
}

/**
* 설명 : 텍스트박스에 사용자에게 알려질 힌트를 설정합니다(화면 초기화시 texxtbox에 '조회조건을 입력하세요'등의 메세지를 보여주며, 
*       사용자가 focus를 가져가면 자동으로 사라집니다 
*       
* 사용방식 : nara project에서는 내부 함수로 사용합니다 따로 호출하여 사용하지 않습니다. 
*          힌트가 필요한 텍스트 박스의 alt속성에 값을 지정하면 자동으로 적용됩니다 
* 구현    : <input type="text" id="txtid1" value="dddddd" alt="조회조건을 입력하세요!!"/>
*          
*          
* 주의 : 
* 리턴 : 없음
*/
function JQ_SetInputHint()
{
	/*
	$("input[type=text]").each(function()
	{
		//alert($(this).val() + ":" + $(this).attr('alt'));
		if($(this).val() == $(this).attr('alt')){$(this).val('');}
	});
	*/
	$("textarea").each(function() 
	{
		if (!$(this).attr("readonly") == true)
		{
			// $("textarea").attr({'title':'내용을 입력하세요.'});
		}
	});
	
	$("input[alt!='']").hint();
	$("textarea[title!='']").hint();
	$("textarea").each(function(){$(this).attr({"title":""});});
}

/*
function JQ_SetFileChk()
{
	$("input[type=file]").each(function()
	{
		if (setFileChk($(this).attr('value')) == false)
		{
			naraFileValidateCheck = false;
			$.validationEngine.buildPrompt($(this), "" + "" + ""+ ""+"등록할수 없는 파일형식 입니다", "error");
			return;
		}
		else
		{
			naraFileValidateCheck = true;
			$.validationEngine.closePrompt($(this));
		}
		
	});
}
*/



/**
* 설명 : 대상 object의 size attribute에서 size를 체크하여 size 크기만큼 숫자만 입력가능하도록 속성을 설정합니다
*       
* 사용방식 : nara project에서는 내부 함수로 사용합니다 따로 호출하여 사용하지 않습니다. page onload시 자동으로 적용됩니다 
*          적용할 object의 id를 넘겨줍니다 
* 구현    : 기존 예:
           <input type="text" 
                  name="monMaxCnAry" 
                  value="<%=vo.getMonMaxCnt() %>" 
                  size=4 
                  maxlength="4" 
                  onkeyDown="gfn_onlyNumber(this)" 
                  style="ime-mode: disabled"
                  class="input_t"> 

           적용방법
               1.1 maxlength삭제, 
               1.2 onkeydown이벤트시 gfn_onlynumber삭제, 
               1.3 숫자입력을 위한 style삭제 

               2.1 아래와 같이 class에 input_nara_number 를 적어주는것으로 대체 
                   input_nara_number : input text의 size크기만큼 숫자만 입력받습니다. 
                   입력크기만큼 언더라인을 표시하여 사용자에게 입력길이(maxlength)를 알립니다

                 
           적용 예:
           <input type="text" 
                  name="monMaxCnAry" 
                  value="<%=vo.getMonMaxCnt() %>" 
                  size=4 
                  class="input_t input_nara_number"> 
*          
*          
* 주의 : 
* 리턴 : 없음
*/
function JQ_setNaraNumber(obj)
{
	var $obj;
	var tmpVal;
	$obj = obj;
	tmpVal = $obj.attr('value');
	var getSize = 0;
	var setMask = "";
	getSize = $obj.attr('size');
	$obj.attr({'style':'ime-mode:disabled'});
	if (getSize > 0)
	{
		for (var tmpcnt = 1; tmpcnt <= getSize; tmpcnt++)
		{
			setMask = setMask + "9";
		}
		
		$obj.mask(setMask);
		$obj.val(tmpVal);
	}
}

/**
* 설명 : JQ_setNaraNumber와 작동방식은 동일, yyyy-mm-dd 날짜형식을 세팅합니다
*        
* 사용방식 : 위 JQ_setNaraNumber()를 참조하세요
*/
function JQ_setNaraDate(obj)
{
	var $obj;
	$obj = obj;
	
	$obj.attr({'style':'ime-mode:disabled'});
	$obj.mask("9999-99-99",{placeholder:""});
}

function JQ_setNaraDateMonth(obj)
{
	var $obj;
	$obj = obj;
	
	$obj.attr({'style':'ime-mode:disabled'});
	$obj.mask("9999-99",{placeholder:""});
}








/**
* 설명 : 화면 내 textbox들에 대한 masking을 적용을 해제합니다
*       
* 사용방식 : nara project에서는 내부 함수로 사용합니다 따로 호출하여 사용하지 않습니다.
*          주로 행추가시의 .innerHTML형식의 구현시 mask를 적용하거나 해제할경우 사용됩니다 
*           masking을 해제할 시점에 아래와 같이 구현합니다
* 구현    : JQ_SetTextNumber_unmask();
*          
*          
* 주의 : 호출시 masking이 해제되므로 사용자는 일반 텍스트박스와 마찬가지로 아무 값이나 입력할수 있습니다
* 리턴 : 없음
*/
function JQ_SetTextNumber_unmask()
{
	$("input[type=text]").each(function() 
	{
		var tmpval;
		
		//$(this).unmask();
		
		tmpval = $(this).val();
		tmpval = tmpval.replace(/'/g,"`");
		if (($(this).hasClass('input_nara_date') == true) ||
		($(this).hasClass('input_nara_sin') == true) ||
		($(this).hasClass('input_nara_jmn') == true) ||
		($(this).hasClass('input_nara_sjn') == true) ||
		($(this).hasClass('input_nara_bdn') == true))
		{
			tmpval = tmpval.replace(/-/g,"");
			
			
		}
		
		if (($(this).hasClass('input_nara_sin') == true))
		{
			tmpval = tmpval.replace('(','');
			tmpval = tmpval.replace(')','');
		}
		
		$(this).val(tmpval);
	});
	
	$("textarea").each(function() 
	{
		// 내용을 없애버려서 주석처리함. (에러발생 ) 
		// $(this).text($(this).text().replace(/'/g,"`"));
		
	});
	
}




/**
* 설명 : 달력을 만듭니다
* 사용방식 : 1.1 기존 달력 이미지코드를 수정 및 삭제, 
            기존코드가 보통 아래와 일치하는 경우 <a 부터 </a> 까지의 달력그림 관련코드를 삭제 
            (코드를 삭제해도 달력은 나옵니다 코드가 NaraJQuery.js로 통합되엇습니다)
            <form:input path="fromRegDate" size="10" maxlength="10" onkeydown="javascript:gfn_onlyNumber(this);"
						onkeyup="gfn_getDateFormat(this,'-');" onblur="gfn_DateType(this,'-');" cssStyle="ime-mode:disabled;" />
			여기부터 삭제 
            <a href="javascript:gfn_calendaropen('');"><img src="<c:url value='/portal/images/bullet/date_go.gif'/>" border="0"></a> 
            여기까지 삭제
            
            1.2 자바스크립트 상단 영역에 달력의 갯수만큼 아래와 같이 추가, fromRegDate는 달력이 있던 위치의 textbox의 id(id가 존재하는지 꼭 확인하세요
            없는경우 id='fromRegDate' 와같이 먼저 작성해야 합니다

            달력이 2개인경우 아래와 같이 최상단에 아래와 같이
            <script type="text/javascript">
	              JQ_setValidation('boardFormVO');
	              JQ_session();
                  .................
                  케이스1 : 기본달력을 세팅합니다
	              JQ_setCalendar('fromRegDate');
	              JQ_setCalendar('toRegDate');
	              
	              케이스2 : 기본달력을 설정하며 두 달력간의 날짜비교기능을 추가합니다, 범위를 벋어나면 submit을 수행하지 않습니다
	              JQ_setCalendar('fromRegDate','toRegDate','from');
				  JQ_setCalendar('toRegDate','fromRegDate','to');
				  
				  케이스3 : 기본달력을 설정하며 두 달력간의 날짜비교, 입력범위 기능을 추가합니다 12개월 이하의 기간설정인 경우만 submit을 수행합니다  
				  JQ_setCalendar('fromRegDate','toRegDate','from',-12);
	              JQ_setCalendar('toRegDate','fromRegDate','to');
            
*          
* 주의 : object에 id attribute가 반드시 존재해야 합니다. 이 함수는 NARACommonLib.js와 상호작용합니다
* 리턴 : 없음
*     : JQ_setCalendar('fromRegDate'); 달력이미지를 표시합니다, 기본적으로 'yyyy-mm-dd'형식으로 보여줍니다 class값이 'input_nara_date'로 설정되어야 합니다
*     : JQ_setCalendar('toRegDate','fromRegDate','to');  toRegDate는 시작일, fromRegDate는 종료일이며 두 달력의 날짜입력을 비교합니다 
*     : $("#fromRegDate").datepick('disable'); <-- 달력을 사용금지합니다
* 순위 : [13]
*/
function JQ_setCalendar(calendarId, compareID, inMethod, compareMonth)
{
	var obj = '#' + calendarId;
	
	$(function()
	{
		$(obj).datepicker({
			dateFormat: 'yy-mm-dd', 
			showOnFocus: false,
			showOn: "button",
			buttonText:"달력",
		    buttonImage: "/images/egovframework/common/ccm/icon/icon_calendar.gif",
			buttonImageOnly: true,
			changeMonth: true,
			changeYear: true,
			showButtonPanel: true,
			onSelect: function(dates)
			{ 
				
				if (typeof(JQ_setDateValuesChange) != 'undefined')
				{
					JQ_setDateValuesChange(calendarId);
				}
				
				if ((inMethod == 'from') || (inMethod == 'to'))  {
					JQ_setCalendarValidate(calendarId, compareID, inMethod, compareMonth);
				}
			}
		    });
		
		
		$(obj).focusout(function()
		{
			
			if ((inMethod == 'from') || (inMethod == 'to'))  {
					 JQ_setCalendarValidate(calendarId, compareID, inMethod, compareMonth);
			}	
			if (typeof(JQ_setDateValuesFocusOut) != 'undefined')
			{
					JQ_setDateValuesFocusOut(calendarId);
			}
				
		
					
		});
	});
		
}

function JQ_dayBetweenValidate( from, to ) 
{
	return ! JQ_setCalendarValidate(from, to, "from");
}	

function JQ_setCalendarValidate(calendarId, compareID, inMethod, compareMonth)
{
	
	var valFromDate = "";
	var valToDate = "";
	var validateTarget = "";
	var valChkOriginDate;
	var valChkDate;
	
	if ((calendarId != '') && (compareID != ''))
	{
		if (inMethod == 'from')
		{
			valFromDate = JQ_getValueObj(calendarId);
			valToDate = JQ_getValueObj(compareID);	
		}
		else if (inMethod == 'to')
		{
			valToDate = JQ_getValueObj(calendarId);
			valFromDate = JQ_getValueObj(compareID);	
		}
		else{
			return false;
		}
		
		valFromDate = valFromDate.replace(/-/g,"");
		valToDate = valToDate.replace(/-/g,"");
		
		if (gfn_dateType(document.getElementById(calendarId), '-') == false) {
			
			naraValidateSubmit = false;
			$.validationEngine.closePrompt($("#" + compareID));
			$.validationEngine.buildPrompt($('#' + calendarId), "입력일자를 확인하세요", "pass")
		}
		else 
		{
			
			if ((compareMonth != '') && (typeof(compareMonth) == 'number'))
			{
				if (inMethod = 'from')
				{
					valChkDate = gfn_addDate( "m", compareMonth, valToDate, "-" );
					valChkOriginDate = valFromDate;
				}
				else if (inMethod = 'to')
				{
					valChkDate = gfn_addDate( "m", compareMonth, valFromDate, "-" );
					valChkOriginDate = valToDate;
				}
				
		    	valChkDate = gfn_addDate( "d", +1, valChkDate, "-" );
		    	valChkDate = valChkDate.replace(/-/g,"");
		    	//alert("2:" + valChkDate + ":" + JQ_getValueObj(calendarId).replace(/-/g,""));
		    	//alert(valChkDate +":" +valChkOriginDate);
		    	if (valChkDate > valChkOriginDate)
		    	{
		    		naraValidateSubmit = false;
					$.validationEngine.closePrompt($("#" + compareID));
					$.validationEngine.buildPrompt($('#' + calendarId), "조회범위기간을  초과하였습니다", "pass")
					return false;;
		    	}
			}
	    	
			if ( valFromDate != '' && valToDate != ''  && valFromDate > valToDate ) 
			{
				naraValidateSubmit = false;
				$.validationEngine.closePrompt($("#" + compareID));
				$.validationEngine.buildPrompt($('#' + calendarId), "시작일이 종료일보다 큽니다", "error")
				return false;
			}
			else {
				//
				
				naraValidateSubmit = true;
				$.validationEngine.closePrompt($("#" + calendarId));
				$.validationEngine.closePrompt($("#" + compareID));
				return true;
			}
		}
	} 
}

function JQ_getTopRows()
{
	
	if (typeof(naraTopFuncGetRows) != 'undefined')
	{
		var tmpval = naraTopFuncGetRows();
		return tmpval;
	}
	
}

/**
 * 설명 : 폼의 Validation을 체크한다.
 *
 * @param frmid
 * @return 정상(true)/오류(false) 여부 
 *
 * 사용예) JQ_CheckValidation( "templateVO" );
 *           
 */
 
function JQ_CheckValidation( frmid ) {
	var val = $.validationEngine.submitValidation( $("#"+frmid ), $.validationEngine.settings );
	return ! val;
}

	

/**
* 설명 : 화면 좌측상단에 jquery debugging을 수행할수 있는 창을 표시합니다
*       
* 사용방식 : 자바스크립트 최상단영역에 아래과 같이 한줄 구현
* 구현    : JQ_debugConsoleView();
* 적용    : 
* 그외 참조 :            
*      
*          
* 주의 : 배포 및 commit시에는 꼭 주석처리를 해주세욤
* 리턴 : 없음
*/
function JQ_debugConsoleView(){
	//jQuery test
	$('body').append('<div  id="inputConsole" style="position:absolute; left:800px; top:1px; width:400px;  border: 1px solid blue" ><table><tr><textarea id="opField"  class="textarea_t" cols="47" rows="3" style="font-size:11pt"></textarea></tr><tr><input type ="button" id="executeButton" value="jQuery 실행" onclick="fncExecute();"/><input type ="button" id="deleteContext" value="Console 내용삭제" onclick="fncDeleteContext();"/></tr></table></div>');
	//DebugConsole
	$('body').append('<div  id="debugConsole" style="position:absolute; left:800px; top:100px; width:400px;  border: 2px solid green" ></div>');
}

function JQ_setProcessMsg()
{
	if (naraValidateSubmit == true)
	{	
		$.blockUI({message:'<img id="displayBox" src="/images/sps/sds/bsm/loading.gif" border="0"/>', css:{width:'317'}, fadeIn:0, fadeOut:40});
	}	
}

function JQ_setProcessMsg_delete()
{
	$.unblockUI();
}

function JQ_setFileStyle()
{
	$("input[type=file]").filestyle({
		image:'/images/button/btn_tb_filesearch.gif',
		imageheight:22,
		imagewidth:56
	});
}

function JT_init(){
	       $("a.jTip")
		   .hover(function(){JT_show(this.href,this.id,this.name)},function(){$('#JT').remove()})
           .click(function(){return false});	   
}

function JT_show(url,linkId,title){
	if(title == false)title="&nbsp;";
	var de = document.documentElement;
	var w = self.innerWidth || (de&&de.clientWidth) || document.body.clientWidth;
	var hasArea = w - getAbsoluteLeft(linkId);
	var clickElementy = getAbsoluteTop(linkId) - 3; //set y position
	
	var queryString = url.replace(/^[^\?]+\??/,'');
	var params = parseQuery( queryString );
	if(params['width'] === undefined){params['width'] = 250};
	if(params['link'] !== undefined){
	$('#' + linkId).bind('click',function(){window.location = params['link']});
	$('#' + linkId).css('cursor','pointer');
	}
	
	if(hasArea>((params['width']*1)+75)){
		$("body").append("<div id='JT' style='width:"+params['width']*1+"px'><div id='JT_arrow_left'></div><div id='JT_close_left'>"+title+"</div><div id='JT_copy'><div class='JT_loader'><div></div></div>");//right side
		var arrowOffset = getElementWidth(linkId) + 11;
		var clickElementx = getAbsoluteLeft(linkId) + arrowOffset; //set x position
	}else{
		$("body").append("<div id='JT' style='width:"+params['width']*1+"px'><div id='JT_arrow_right' style='left:"+((params['width']*1)+1)+"px'></div><div id='JT_close_right'>"+title+"</div><div id='JT_copy'><div class='JT_loader'><div></div></div>");//left side
		var clickElementx = getAbsoluteLeft(linkId) - ((params['width']*1) + 15); //set x position
	}
	
	$('#JT').css({left: clickElementx+"px", top: clickElementy+"px"});
	$('#JT').show();
	$('#JT_copy').load(url);

}

function getElementWidth(objectId) {
	x = document.getElementById(objectId);
	return x.offsetWidth;
}

function getAbsoluteLeft(objectId) {
	// Get an object left position from the upper left viewport corner
	o = document.getElementById(objectId)
	oLeft = o.offsetLeft            // Get left position from the parent object
	while(o.offsetParent!=null) {   // Parse the parent hierarchy up to the document element
		oParent = o.offsetParent    // Get parent object reference
		oLeft += oParent.offsetLeft // Add parent left position
		o = oParent
	}
	return oLeft
}

function getAbsoluteTop(objectId) {
	// Get an object top position from the upper left viewport corner
	o = document.getElementById(objectId)
	oTop = o.offsetTop            // Get top position from the parent object
	while(o.offsetParent!=null) { // Parse the parent hierarchy up to the document element
		oParent = o.offsetParent  // Get parent object reference
		oTop += oParent.offsetTop // Add parent top position
		o = oParent
	}
	return oTop
}

function parseQuery ( query ) {
   var Params = new Object ();
   if ( ! query ) return Params; // return empty object
   var Pairs = query.split(/[;&]/);
   for ( var i = 0; i < Pairs.length; i++ ) {
      var KeyVal = Pairs[i].split('=');
      if ( ! KeyVal || KeyVal.length != 2 ) continue;
      var key = unescape( KeyVal[0] );
      var val = unescape( KeyVal[1] );
      val = val.replace(/\+/g, ' ');
      Params[key] = val;
   }
   return Params;
}

function blockEvents(evt) {
              if(evt.target){
              evt.preventDefault();
              }else{
              evt.returnValue = false;
              }
}


//Debug 로그창
function log(msg)
{
	var console = document.getElementById("debugConsole");
	
	if ( console != null )
	{
	    console.innerHTML += msg + "<br/>"; 
	}
}

//jQery 실행시 선택된 Element 표시
function applySelector( operation ){
	$('*').removeClass('redBorder');
	eval('var wrappedSet = ' + operation + ';');
	wrappedSet.addClass('redBorder');
	//wrapperSet.css("border","2px solid red");
	return wrappedSet;
}

function fncExecute(){
    var operation = $('#opField').val();
	log(operation);
	try{
		if ( operation.length > 0 ){
	    	var wrappedSet = applySelector(operation);
	    	$('#debugConsole').html(wrappedSet.formatForDisplay());
		}
	}catch(e){
		log('error : ' + e );
	}
}
// debugConsole창 지우기
function fncDeleteContext(){
	var console = document.getElementById("debugConsole");

	if ( console != null ){
		console.innerHTML = "";
	}
}
/**
* 설명 : document의 시작시점에 공통으로 적용될 사항을 기술합니다. (document).ready에 대한 사항은 관련 문서를 참조바랍니다          
* 주의 : NaraJQuery.js 를 include하는 모든 view(.jsp)에 적용됨을 인지합니다
*     : page onload시 최초 한번 적용됩니다
* 리턴 : 없음
* 순위 : [17]
*/
$(document).ready(function() 
{
	
	$("select").attr({multiple:false});
	JQ_session();
	
	JQ_SetInputHint();
	//JQ_SetTextNumber();
	JQ_addTableColor();
	
	$("table tr td").click(function()
	{
		try {
			naraTableSelectedRow = this.parentNode.rowIndex;
			naraTableSelectedRowId = this.parentNode.id;
			naraTableSelectedRowName = this.parentNode.Name;
		}
		catch(e)
		{
			
		}
	});
	
});

/**
* 설명 : 페이지가 브라우져에서 모두 다운로드(complete)된 이후의 실행시점의 코드를 작성합니다 
*       document.ready()보다 이후에 실행됩니다. 기존 body onload와 동일압니다   
*       fncPageonlaod()이후에 실행되며 페이지가 전부 로딩되고 난이후 최초 호출되어야 할 대상로직을 기술하셈요
*       아래와같이 페이지 최상단에  
*        
*       function fncBodyOnload()
	    {
		    .............
	    }
	    와 같이 구현하면됩니다 
* 주의 : NaraJQuery.js 를 include하는 모든 view(.jsp)에 적용됨을 인지합니다
*     : page onload시 최초 한번 적용됩니다
* 리턴 : 없음
* 순위 : [25]
*/
$(window).load(function()
{
	if (typeof(fncBodyOnload) != 'undefined')
	{
		fncBodyOnload();
		
	}
	
	if (typeof(JQ_setValidation) != 'undefined')
	{
		if ( $("#page_title").attr("id") == "page_title" )
		{			
			//alert('n10:'+'JQ_DoubleSubmit');
			JQ_DoubleSubmit();
		}
	}
});

$.fn.delay = function(time,func){
	this.each(function(){
		setTimeout(func,time);
	});
	
	return this;
};

$.fn.formatForDisplay = function(){
	if ( this.size() == 0 )
		return "<em>wrapped set is empty</em>";

	var text = '';
	this.each(function(){
		text += '<div>' + this.tagName;
		if ( this.id )
			text += '#'+this.id;
		text += '</div>';
	});
	return text;
};


/**
* 설명 : nara 세션관련 설정을 적용합니다
* 사용방식 : global하게 적용됩니다 document.ready()에서 한번 적용됩니다. 
*          프로그램(DAAQxxx, DAAIxxx)별 적용시 global설정과 상관없이 프로그램 설정 적용값이 적용됩니다
*          페이지별 세션타임아웃적용이 필요한 경우 페이지 .ready()에서 적용할수 있습니다
*            TimeOutAfter: 1800000, // 세션타임아웃(30분), 30분 이후에 로그아웃 페이지로 이동합니다
			 CountDownFor: 5000, // 5초마다 타임아웃을 카운팅합니다
			 CheckInterval: 1000, // 체크 주기입니다 CountDownFor보다 큰값인 경우 의미없습니다
			 logoutUrl: isPopupLogOut, // 세션아웃시 로그아웃되는 페이지 입니다
			 logoutUrlParams : logoutUrl뒤의 파라미터 값을 get방식으로 넘깁니다
			 windowTitle : 창 타이틀
*          적용 예:JQ_session();
* 리턴 : 없음
*/
function JQ_session()
{
	
	var isPopupLogOut;
	//자동로그아웃시  오픈한 모든 팝업은 종료됩니다 
	//메인페이지는 session.remove이후 최초 로그인페이지로 이동합니다
	
	if (typeof(opener) == "object")
	{
		//window.open으로 띄운경우
		isPopupLogOut = "/nara/popOut.do";
	}
	else
	{
		
		if (typeof(window.dialogArguments) == "object")
		{
			//모달로 띄운경우
			isPopupLogOut = "/nara/popOut.do";
		}
		else
		{
			isPopupLogOut = "/nara/login.do?method=autoLogOut";
		}
	}

	$("body").append("<input id='checktimeoutstatus' type='hidden' value='' /><div style='display: none;'><img id='callImg' src='/images/egovframework/common/ccm/icon/bu_icon_carlendar.gif' alt='Popup' class='trigger'></div>");
	var options = {
			 Cookie:"IdleCheckerTimeOut",
			 CookieExpires:1,
			 CookiePath:"/",
			 CookieDomain:"kostat.go.kr",
			 CookieSecure:false,
			 TimeOutAfter: 1800000,
			 //TimeOutAfter: 10000, //10초 : 테스트를 위함입니다 
			 CountDownFor: 5000, 
			 CheckInterval: 10000, 
			 logoutUrl: isPopupLogOut, 
			 logoutUrlParams: "", 
			 keepAliveUrl: "../nara/blank.jsp", 
			 windowTitle:document.title 
	}
	
	//$.IdleChecker(options);
};