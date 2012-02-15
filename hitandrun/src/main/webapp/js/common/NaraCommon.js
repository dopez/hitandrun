/** 
 이곳은 공통 자바스크립트를 모아놓은 곳입니다. 
**/

/*
 * 팝업 Window 공통 *
 * 최초작성일 : 2011.06.08
 * 작성자 : 한상훈, 조현기
 * 
 * ======================================================================================================
 * 사용방법
 * popupWin(ctlid, url, winnm, height, width, scrollbars, resizable, popuptype)
 * 	ctlid : 이벤트발생객체ID(버튼ID)
 * 	url : 출력URL
 * 	winnm : 팝업window명
 * 	height : height
 * 	width : width
 * 	scrollbars : 스크롤바여부 (yes, no)
 *  resizable : 팝업화면  resize (yse, no[default])
 *  popuptype : 팝업 유형
 * 
 * 사용예)
 * popupWin("nclInputPopup", "/sps/sds/bsm/rsm/NclInputPopup.do", "보고통계 수치입력", 768, 1024, "no", 1);
 * ======================================================================================================
 */
function popupWin(ctlid, url, winnm, height, width, scrollbars, resizable, popuptype)
{
	winnm = winnm.replace(/ /g, "");
	
	if( typeof(popuptype) == 'undefined' )
		popuptype = 0;
	
	$('#' + ctlid).popupWindow
    ({
        windowURL:url,
        windowName:winnm,
        height:height,
        width:width,
        top:0,
        left:0,
        scrollbars:scrollbars,
		status:'yes', 
		resizable:resizable,
		type:popuptype
    });
	
	return;
}


 /*
 * Validate 체크
 * 최초작성일 : 2011.07.20
 * 작성자 : 한상훈
 * 
 * ======================================================================================================
 * 사용방법
 * fn_validate(valType, txtVal)
 * valType : validate type
 * txtVal = validate 대상 값
 * 
 * 사용예)
 * fn_validate('TBL_ID', 'DT_2345');
 * ======================================================================================================
 */
function fn_validate(valType, txtVal)
{
	var bIsEnable = false;
	var sRegExp   = "";

	//-- -----------------------------------------------------------------
	// TBL_ID        = 영문대문자,  숫자, _(언더바)            - 통계표ID
	// TMPLAT_TBL_ID = 영문대문자,  숫자, _(언더바)            - 공동서식ID
    // OBJ_VAR_ID    = 영문대문자,  숫자, _(언더바), .(마침표)  - 분류코드ID
	// DTACODE       = 영문대소문자, 숫자, _(언더바), .(마침표) - 자료코드ID
	// ITM_ID        = 영문대소문자, 숫자, _(언더바), .(마침표) - 항목코드ID
	// CD_ID         = 영문대소문자, 숫자, _(언더바), .(마침표) - 단위코드ID
	//-- -----------------------------------------------------------------
	switch(valType)
	{
		case "TBL_ID" :
		case "TMPLAT_TBL_ID":
		case "OBJ_VAR_ID" :
			sRegExp = "[^A-Z0-9\_\.]";
			break;
		case "DTACODE" :
		case "ITM_ID" :
			sRegExp = "[^a-z0-9A-Z\_\.]";
		case "CD_ID" :
			sRegExp = "[^a-z0-9A-Z\_\.]";
			break;
		default :
			break;
	}

	var regexp = new RegExp(sRegExp);
	bIsEnable = regexp.test(txtVal);

	// 메세지 처리
	if(bIsEnable)
	{
		switch(valType)
		{
			case "TMPLAT_TBL_ID" :
				alert("공동서식 ID는 영문대문자, 숫자, _(언더바)만 사용할 수 있습니다.");
                break;
			case "TBL_ID" :
				alert("통계표ID는 영문대문자, 숫자, _(언더바)만 사용할 수 있습니다.");
                break;
			case "OBJ_VAR_ID" :
				alert("분류ID는 영문대문자, 숫자, _(언더바), .(마침표)만 사용할 수 있습니다.");
				break;
			case "DTACODE" :
				alert("자료코드는 영문대소문자, 숫자, _(언더바), .(마침표)만 사용할 수 있습니다.");
				break;
			case "ITM_ID" :
				alert("항목ID는 영문대소문자, 숫자, _(언더바), .(마침표)만 사용할 수 있습니다.");
				break;
			case "CD_ID" :
				alert("단위코드는 영문대소문자, 숫자, _(언더바), .(마침표)만 사용할 수 있습니다.");
				break;
			default :
				break;
		}
	}
	return bIsEnable;
}



/*
* 팝업 Window 공통 *
* 최초작성일 : 2011.07.20
* 작성자 : 한상훈
* 
* ======================================================================================================
* 사용방법
* popup(url, winnm, height, width, scrollbars, resizable, popuptype)
* url : 출력URL
* winnm : 팝업window명
* height : height
* width : width
* scrollbars : 스크롤바여부 (yes, no)
* resizable : 팝업화면  resize (yse, no[default])
* popuptype : 팝업유형
* 
* 사용예)
* popup("/sps/sds/bsm/rsm/NclInputPopup.do", "보고통계 수치입력", 768, 1024, "no", 1);
* ======================================================================================================
*/
function popup(url, winnm, height, width, scrollbars, resizable, popuptype)
{
	winnm = winnm.replace(/ /g, "");
	if( popuptype == null )
		popuptype = 0;
	
	$.directPopupWindow({
	    windowURL:url,
	    windowName:winnm,
	    height:height,
	    width:width,
	    top:0,
	    left:0,
	    scrollbars:scrollbars,
	    status:'yes', 
	    resizable:resizable,
		type:popuptype
   });
	

	
	return;
}


/*
* 팝업 Window 공통 *
* 최초작성일 : 2011.07.20
* 작성자 : 송인겸 
* 
* ======================================================================================================
* 사용방법
* gfn_postPopupWin(frmid, url, winnm, height, width, scrollbars, resizable, popuptype)
* frmid: form의 id
* url : 출력URL
* winnm : 팝업window명
* height : height
* width : width
* scrollbars : 스크롤바여부 (yes, no)
* resizable : 팝업화면  resize (yse, no[default])
* popuptype : 팝업유형 0: window.popup, 1 : window.showModalDialog
* 
* 사용예)
* gfn_postPopupWin("SearchVO", "/sps/sds/bsm/rsm/NclInputPopup.do", "보고통계 수치입력", 768, 1024, "yes", "no");
* ======================================================================================================
*/

function gfn_postPopupWin(frmid, url, winnm, height, width, scrollbars, resizable, popuptype)
{
	var callUrl = "";	
	winnm = winnm.replace(/ /g, "");
	
	if( popuptype == null ) {
		popuptype = 0;
	} else if ( popuptype == 1 ) {
		var formData = $( "#"+frmid ).serialize();
		var rquery = /\?/;
		callUrl = url + (rquery.test(url) ? "&" : "?") + formData;
	}
	
	$.directPopupWindow({
	    windowURL:callUrl,
	    windowName:winnm,
	    height:height,
	    width:width,
	    top:0,
	    left:0,
	    scrollbars:scrollbars,
	    status:'yes', 
	    resizable:resizable,
		type:popuptype
    });
	
	if( popuptype != 1 ) { 
		var beforeTarget = document.getElementById(frmid).target;
		document.getElementById(frmid).method = "post";
		document.getElementById(frmid).action = url;
		document.getElementById(frmid).target = winnm;
		document.getElementById(frmid).submit();
		document.getElementById(frmid).target = beforeTarget;
	}
	
	return;
}

/*
* OZ 출력물 인쇄 팝업
* 최초작성일 : 2011.07.20
* 작성자 : 송인겸 
* 
* ======================================================================================================
* 사용방법
* gfn_printPopupWin(frmid, param, winnm, height, width, scrollbars, resizable, popuptype)
* frmid: form의 id
* url : 출력URL
* winnm : 팝업window명
* height : height
* width : width
* scrollbars : 스크롤바여부 (yes, no)
* resizable : 팝업화면  resize (yse, no[default])
* popuptype : 팝업유형
* 
* 사용예)
* gfn_printPopupWin("SearchVO", param,  "보고통계 수치입력", 768, 1024, "no", "no", 1);
* ======================================================================================================
*/
function gfn_printPopupWin(frmid, param, winnm, height, width, scrollbars, resizable, popuptype)
{
	var url = "/common/commonPrint.do";
	winnm = winnm.replace(/ /g, "");
	if( popuptype == null )
		popuptype = 0;
    var data = "";
    
	$.directPopupWindow({
	    windowURL:"",
	    windowName:winnm,
	    height:height,
	    width:width,
	    top:0,
	    left:0,
	    scrollbars:scrollbars,
	    status:'yes', 
	    resizable:resizable,
		type:popuptype
   });
	
	for(i=0; i<param.length; i++)
    {
       data = data + param[i] + "##";	
    }
	document.getElementById("printData").value  = data;
	
	document.getElementById(frmid).method = "post";
	document.getElementById(frmid).action = url;
	document.getElementById(frmid).target = winnm;
	document.getElementById(frmid).submit();
	
	return;
}

/*
* OZ 출력물 인쇄 
* 최초작성일 : 2011.07.20
* 작성자 : 송인겸 
* 
* ======================================================================================================
* 사용방법
* gfn_printPopupWin(frmid, param, targetid )
* frmid: form의 id
* url : 출력URL
* targetid : target id
* 
* 사용예)
* gfn_printPopupWin( "PrintVO", param,  "iprint" );
* ======================================================================================================
*/
function gfn_printWin(frmid, param, targetid )
{
	var url = "/common/commonPrint.do";
    var data = "";
    
	for(i=0; i<param.length; i++)
    {
       data = data + param[i] + "##";	
    }
	document.getElementById("printData").value  = data;
	
	document.getElementById(frmid).method = "post";
	document.getElementById(frmid).action = url;
	document.getElementById(frmid).target = targetid;
	document.getElementById(frmid).submit();
	
	return;
}

/*
* 달력 공통
* 최초작성일 : 2011.06.09
* 작성자 : 조현기
* 
* ======================================================================================================
* 사용방법
* SetDatePicker(ctlid)
* 	ctlid : 날짜가 출력될 inputbox id
* 
* 사용예)
* SetDatePicker("date");
* ======================================================================================================
*/
function SetDatePicker(theCtl)
{
 $(function(){
  $("#"+theCtl).datepicker({changeMonth: true, changeYear: true, showButtonPanel: true, dateFormat:"yy-mm-dd", showOn: 'button', buttonImage: '/images/common/btn_monthy.gif', buttonImageOnly: true});
 });
}

/*
* input box enterkey event에 의한 Form Submit 방지처리
* 최초작성일 : 2011.06.16
* 작성자 : 조현기
* 
* ======================================================================================================
* 사용방법
* return SubmitBefore(event) 	
* 
* 사용예)
* <form name="searchForm" id="searchForm" onsubmit="SubmitBefore(event)">
* ======================================================================================================
*/
function SubmitBefore(e)
{
	if(e.keyCode = 13){ 
	       return false;
	}	
}

/*
* jqGrid에서 Post방식으로 검색조건 전송시 해당 Form내의 컨트롤에 대한 ID 및 value 값을 JSON 형식으로 변환
* 최초작성일 : 2011.06.17
* 작성자 : 조현기
* 
* ======================================================================================================
* 사용방법
* getSearchPostData(formid) 	
* 
* 사용예)
* $("#scmList").jqGrid("setGridParam", {postData: getSearchPostData("formid")}).trigger("reloadGrid");
* ======================================================================================================
*/
function getSearchPostData(formid)
{
	/* Form내의 객체에 대한 ID와 Value 추출 */
	var searchParam = $("#" + formid).serializeArray();
	var param = "";
	
	/* 추출한 배열 Size만큼 루프 */
	$.each(searchParam, function(i, field) {
		/* JSON 형식으로 문자열 생성 "id":"value" */
		param += '"' + field.name + '"' + ":" + '"' + field.value + '"' + ",";
	});
	
	/* 생성된 문자열의 마지막 ',' 제거 */
	param = param.substring(0, param.length-1);
	param = "{" + param + "}";
	/* JSON형식으로 변환 */
	searchParam = JSON.parse(param);
	return searchParam;
}



/**
* 입력받은 OBJECT에 포커스를 맞춤
* @param {obj} obj object명
* @example
*          onload="gfn_setFocus(document.formName.cstm)"
* @returns 
*/ 
function gfn_setFocus(obj) {
	obj.select();
}

/**
* 입력필드에 입력이 끝나면 자동으로 focus 이동(onkeyup 이벤트에 사용)
* @param {obj} obj object명
* @param {int} len 입력자리수
* @example
*          onkeyup="gfn_tabOrder(this, 4)"
* @returns 
*/
function gfn_tabOrder(obj,len) 
{
    var mForm =  document.forms[0];     
    var iElements=mForm.elements.length;
    for(i=0; i<iElements; i++)
    {
        if (mForm.elements[i] == obj) {
            j = i + 1;
            break;
        }
    }
    obj.value = obj.value.replace(/_/g,"");
    if (obj.value.length == len)
    {
        
        for(k=j; k<iElements; k++) {
            var obj2 = mForm.elements[k];   
            if(obj2.type != "hidden") {
                mForm.elements[k].focus();
                if (obj2.type=="text") {
                    obj2.select();
                }
                break;
            }
        }
    }
    return;
}

/**
* 숫자만을 입력하기 위한 함수
* @param {obj} obj object명
* @example
*          onkeypress="gfn_onlyNumber1(this)" style="ime-mode:disabled"
* @returns 
*/
function gfn_onlyNumber1(obj)
{   
    if(!(event.keyCode > 47 && event.keyCode < 58)) {
        event.returnValue = false;
    }
}

/**
* 숫자만을 입력하기 위한 함수(Ctrl+C, Ctrl+V 가능) style="ime-mode:disabled"를 넣어준다
* @example
*          onkeydown="gfn_onlyNumber(this)" style="ime-mode:disabled"
* @returns 
*/
function gfn_onlyNumber() {  
    var ctrlPressed = (window.Event) ? event.modifiers & Event.CONTROL_MASK : event.ctrlKey;
    var shiftPressed = (window.Event) ? event.modifiers & Event.SHIFT_MASK : event.shiftKey;    
    if( !( (!shiftPressed && event.keyCode>47 && event.keyCode<58) ||
           (event.keyCode>34 && event.keyCode<41) ||
           (event.keyCode>95 && event.keyCode<106) ||
           (event.keyCode == 8) || (event.keyCode == 13) ||
           (event.keyCode == 46)|| (event.keyCode == 9) ||
           (event.keyCode == 3) || (event.keyCode == 16) ||  
           (event.keyCode == 94) || (ctrlPressed && event.keyCode == 67) ||
           (ctrlPressed && event.keyCode == 86) || (ctrlPressed && event.keyCode == 88) ||
           (event.keyCode == 118) ||(event.keyCode == 99)
        ) )
    {
        event.returnValue = false;
    }
}

/**
* 영문과 숫자만 입력받을수 있음 style="ime-mode:disabled"를 넣어준다
* @example
*           onkeydown="gfn_onlyAlphanumeric(this)" style="ime-mode:disabled"
* @returns 
*/
function gfn_onlyAlphanumeric()
{
    var key = event.keyCode;    

    if ( 
         ( key>=48 && key<=57  ) || ( key>=65 && key<=90  ) ||
         ( key>=96 && key<=105 ) ||
           key==32 || key==8 || key==37 || key==39 || key==46 ||
           key==35 || key==36  
       ){               
        return;
    } else
        event.returnValue=false;
}

/**
* 입력 문자열의 길이를 제한한다. (한글 2Byte)
* @param {String} str 입력박스값
* @param {int} max 최대길이
* @param {String} msg 출력메시지
* @example
*           if ( gfn_limitLen( value, 20, "성명은") )
* @returns 
*/
function gfn_limitLen( str, max, msg )
{
    var len = 0;   // 문자열 길이 (Byte단위)

    for ( iCnt = 0; iCnt < str.length; iCnt++)
    {
        if ( str.charCodeAt(iCnt) > 127 )  // 한글이면 2Byte 처리
            len += 2;
        else
            len += 1; 

        if ( len > max )   // 최대 길이 초과시
        {
            alert(msg+' 최대 영문 '+max+'자(한글 '+(max/2)+'자) 까지 입력하실 수 있습니다.');
            return true;
        }
    }
    return false;
}
/**
* 입력 문자열의 길이를 제한한다. (한글 3Byte)
* @param {String} str 입력박스값
* @param {int} max 최대길이
* @param {String} msg 출력메시지
* @example
*           if ( gfn_limitLen( value, 20, "성명은") )
* @returns 
*/
function gfn_limitLen2( str, max, msg )
{
    var len = 0;   // 문자열 길이 (Byte단위)

    for ( iCnt = 0; iCnt < str.length; iCnt++)
    {
        if ( str.charCodeAt(iCnt) > 127 )  // 한글이면 3Byte 처리
            len += 3;
        else
            len += 1; 

        if ( len > max )   // 최대 길이 초과시
        {
            alert(msg+' 최대 영문 '+max+'자(한글 '+parseInt(max/3)+'자)  까지 입력하실 수 있습니다.');
            return true;
        }
    }
    return false;
}


/**
* 3자리마다 컴머(,)를 넣어주는 함수
* @param {obj} obj object명
* @example
*           onblur="gfn_checkDigits(this)"
* @returns 
*/
function gfn_checkDigits(obj) 
{
    var s=obj.value;
    for(j=0; j<s.length; j++)
        s= obj.value.replace(/,/g,"");
    var t="";
    var i;
    var j=0; 
    var tLen =s.length;
    
    if (s.length <= 3 )
    {
        obj.value=s;
        return;
    }
    
    for(i=0;i<tLen;i++)
    {
       if (i!=0 && ( i % 3 == tLen % 3) )     t += ",";
       if(i < s.length ) t += s.charAt(i);
    }
    
    obj.value = t;
    return;  
}  

/**
* 시간 데이터 중에서 Colon(:)을 붙이는 함수
* @param {obj} obj object명
* @example
*           onblur="gfn_makeColon(this)"
* @returns 
*/
function gfn_makeColon(obj) {
    var src = new String(obj.value);
    var split1 = src.substring(0,2);// 시간 저장
    var split2 = src.substring(2,4);// 분 저장
    obj.value=split1+':'+split2;
    return ;
}

/**
* 소수점 자리가 있는 숫자 중에서 Comma(,)를 붙이는 함수
* @param {obj} obj object명
* @example
*           onblur="gfn_makeComma(this)"
* @returns 
*/
function gfn_makeComma(obj) 
{
    var src = new String(obj.value);
    var len;
    var i = 0;
    var pos = 0;
    var split1 = '';     // Sign '-' 부호 저장
    var split2 = '';     // 정수부분 저장
    var split3 = '';     // 소숫점 이하자리 저장
    var rtn_value = '';
    gfn_RemoveComma(obj);
    if (src.charAt(0) == '-') 
    {
        split1 = '-';
        src = src.substr(1);
    }

    if (src.indexOf('.') >= 0) 
    {
        split2 = src.substring(0,src.indexOf('.'));
        split3 = src.substr(src.indexOf('.'));
    }
    else
    {
        split2 = src;
        split3 = '';    
    }
    len = split2.length;
    //  Comma ',' 추가 루틴
    for(var i = 0; i < len; i++) 
    {
        pos  = len - i;
        rtn_value = rtn_value + split2.charAt(i);
        if(pos != 1 && pos % 3 == 1) 
        {
            rtn_value = rtn_value + ',';
        }
    }
    obj.value=split1+rtn_value+split3;
    return ;
}

/**
* 특수기호들을 전부 없애는 함수
* @param {obj} obj object명
* @example
*           onfocus="gfn_removeAll(this)"
* @returns 
*/
function gfn_removeAll(obj) 
{
	
    for (i=0; i< obj.value.length; i++) 
       obj.value = obj.value.replace(/(\/|\$|\^|\*|\(|\)|\+|\.|\?|\\|\{|\}|\||\[|\]|-|:)/g,""); 
    obj.select();
    return;
} 

/**
* 원하는 값을 전부 없애는 함수
* @param {obj} obj object명
* @param {char} chr 삭제할문자
* @example
*           onfocus[or onblur]="gfn_removeChar(this, ',')"
*           onfocus[or onblur]="gfn_removeChar(this, '/')"
* @returns 
*/
function gfn_removeChar(obj, chr) {
    var temp = obj.value;
    var len = temp.length;
    for (i=0; i < len ; i++)  
        obj.value = obj.value.replace(chr,"");
}

/**
* 입력된 문자열이 최소길이, 최대길이를 벗어났는지 체크하는 함수.
* @param {obj} form_name form명
* @param {obj} input_name object명 
* @param {int} min_size 최소길이
* @param {int} max_size 최대길이
* @example 
*          gfn_checkLengthMinMax('form_name', 'ditc_name', 20, 1000)
* @returns 
*          -1: (문자열 < maxsize),    
*           1: (문자열 > maxsize), 
*           0: (문자열 = maxsize)
*/
function gfn_checkLengthMinMax(form_name, input_name, min_size, max_size) {
    var input_str;              // 입력된 문자열
    var len;                    // 문자열 길이(Unicode단위)
    var cur_size = 0;           // 문자열 길이(Byte단위)
        
    // 문자열과 문자열 길이를 구한다.
    input_str = eval("document." + form_name + "." + input_name).value;
    len = input_str.length;
    
    // Unicode인지 체크하여 문자열 길이를 Byte단위로 구한다.
    for (i = 0; i < len; i++) {
        if (input_str.charCodeAt(i) > 127)
            cur_size += 2;
        else
            cur_size += 1;
    }
    
    // 구해진 문자열 길이가 min_size보다 작으면 -1, max_size보다 크면 +1, 같으면 0을 리턴한다.
    if (cur_size > max_size)
        return 1;
    else if (cur_size < min_size)
        return -1;
    else
        return 0;
}

/**
* 입력된 문자열이 입력한 길이와 같은지 체크한다.
* @param {obj} form_name form명
* @param {obj} object_name object명
* @param {String} label_name 라벨이름
* @param {int} max_size 최대길이
* @example
*          gfn_checkEqualLength('form_name', 'text_cntn', '내용', 200);
* @returns 
*          문자열길이가 최대길이보다 큰 경우 메시지 출력
*/
function gfn_checkEqualLength(form_name, input_name, label_name, max_size) {
    var input_str;              // 입력된 문자열
    var len;                    // 문자열 길이(Unicode단위)
    var cur_size = 0;           // 문자열 길이(Byte단위)
    
    
    // 문자열과 문자열 길이를 구한다.
    input_str = eval("document." + form_name + "." + input_name).value;
    len = input_str.length;
    
    // Unicode인지 체크하여 문자열 길이를 Byte단위로 구한다.
    for (i = 0; i < len; i++) {
        if (input_str.charCodeAt(i) > 127)
            cur_size += 2;
        else
            cur_size += 1;
    }
    
    // 구해진 문자열 길이가 max_size보다 큰경우 에러메시지를 출력한다.
    if (cur_size != max_size) {
        alert(label_name + "의 입력길이가 잘못되었습니다.");
        eval("document." + form_name + "." + input_name).focus();
    }
}   

/**
* 입력값에 대해서 최대자리수를 체킹함
* @param {obj} obj object명
* @param {int} max_size 최대길이
* @example
*          onkeydown="gfn_checkLength(this, 1000)"
* @returns 
*          문자열길이가 최대길이보다 큰 경우 메세지 출력
*/
function gfn_checkLength(obj, max_size) {
    var input_str;              // 입력된 문자열
    var len;                    // 문자열 길이(Unicode단위)
    var cur_size = 0;           // 문자열 길이(Byte단위)


    // 문자열과 문자열 길이를 구한다.
    input_str = obj.value;
    len = input_str.length;

    // Unicode인지 체크하여 문자열 길이를 Byte단위로 구한다.
    for (i = 0; i < len; i++) {
        if (input_str.charCodeAt(i) > 127)
            cur_size += 2;
        else
            cur_size += 1;
    }

    // 구해진 문자열 길이가 MAX SIZE보다 크면 오류메시지 출력.
    if (cur_size >= max_size) {
    	// alert(event.keyCode);
    	if ( !((event.keyCode == 8)  || (event.keyCode == 13) ||
           	   (event.keyCode == 46) || (event.keyCode == 9)  ||
		   	   (event.keyCode == 3)  || (event.keyCode == 16) ||  
		   	   (event.keyCode == 94) || (event.keyCode == 118)||
		  	   (event.keyCode == 99)) 
		    ) {
    		
    		alert("해당 항목은 최대 " + max_size + "자리입니다.");
    		event.returnValue = false;	
    	}
    }
}

/**
*  "와 '의 입력을 허용하지 않는 함수
* @param {obj} obj object명
* @example
*          onkeypress="gfn_checkQuot()"
* @returns 
*/
function gfn_checkQuot(obj) {
    if ( ( obj.value.indexOf("'") != -1 ) || ( obj.value.indexOf("\"") != -1 ) ) {
        alert('작은 따옴표와 큰 따옴표는 입력을 허용하지 않습니다.');

        obj.focus();
        obj.select();
    }
    return ;
}
 
/**
* 공백 제거하는 함수
* @param {obj} obj object명
* @example
*          document.form_name.OBJECT_NAME.value = gfn_trim(document.OBJ_NAME.value);
* @returns 
*          공백이 제거된 value
*/
function gfn_trim(tmpStr) {
    var atChar;
    if( tmpStr == null || tmpStr == "")
    	return tmpStr;
    
    if (tmpStr.length > 0) atChar = tmpStr.charAt(0);
    while (gfn_isSpace(atChar)) {
        tmpStr = tmpStr.substring(1, tmpStr.length);
        atChar = tmpStr.charAt(0);
    }
    if (tmpStr.length > 0) atChar = tmpStr.charAt(tmpStr.length-1);
    while (gfn_isSpace(atChar)) {
        tmpStr = tmpStr.substring(0,( tmpStr.length-1));
        atChar = tmpStr.charAt(tmpStr.length-1);
    }
    return tmpStr;
}

function gfn_isSpace(inChar) {
  return (inChar == ' ' || inChar == '\t' || inChar == '\n');
}

/**
* 대소문자로 변환하는 함수 
* @param {obj} obj object명
* @param {char} type 타입 'U' : 대문자, 'L':소문자
* @example
*          onkeyup="gfn_upperLowerCase(this, 'U');"
* @returns 
*          타입(U:대문자 L:소문자)에 따른 변환된 value
*/
function gfn_upperLowerCase(fldName, type)
{
    if ((type == 'l') || (type == 'L'))
        fldName.value = $.trim(fldName.value.toLowerCase());
    else
        fldName.value = $.trim(fldName.value.toUpperCase());
}

/**
* 입력된 문자열에 공백을 넣는 함수
* @param {obj} obj object명
* @param {char} orient 'L' : 왼쪽에 공백 또는 'R' : 오른쪽에 공백
* @param {int} maxlength 최대길이
* @example
*          gfn_fillSpace(document.formName.Ditc_cntn, 'L', 10)
*          gfn_fillSpace(document.formName.From_date_yyyy, 'R', 4)
* @returns 
*         공백을 넣은 value
*/
function gfn_fillSpace(obj, orient, maxlength) 
{
    var str="";
    var mylength = obj.value.length;
   
    // 입력한 문자열이 존재 할때만 공백을 채운다 
    if (mylength != 0)
    {
        for(var i=0; i < (maxlength - mylength);i++) 
        {
            str = str + " ";
        }
        
        if(orient=='L') 
        {
            obj.value = str+obj.value;
        } 
        else if (orient=='R') 
        {
            obj.value = obj.value+str;
        }
   }
}

/**
* 입력된 문자열에 0을 채워 넣는 함수
* @param {obj} obj object명
* @param {char} orient 'L' : 왼쪽에 0 또는 'R' : 오른쪽에 0
* @param {int} maxlength 최대길이
* @example
*          onblur="gfn_fillZero(document.formName.BmenNo3, 'L', 5);"
* @returns 
*         0을 채워 넣은 value
*/
function gfn_fillZero(obj, orient, maxlength) 
{
    var str="";
    obj.value = obj.value.replace(/_/g,"");
    var mylength = obj.value.length;
    
   
    // 입력한 문자열이 존재 할때만 0을 채운다
    if (mylength != 0)
    {
        for(var i=0; i < (maxlength - mylength);i++) 
        {
            str = str + "0";
        }
        
        if(orient=='L') 
        {
            obj.value = str+obj.value;
        } 
        else if (orient=='R') 
        {
            obj.value = obj.value+str;
        }
   }
}

/**
* 사업자번호 체크 Digit
* @param {int} bmenNo 사업자번호
* @example
*          if(!gfn_checkBmenNo("1111111119"))
*             alert("사업자번호가 잘못되었습니다.");
* @returns 
*          사업자번호가 잘못된 경우 메세지 출력
*/
function gfn_checkBmenNo(bmenNo)
{
    var vv_RegNo;
    var vv_tmp;
    var vv_sum,vv_i;
    var num1,num2,num3,num4,num5,num6,num7,num8,num9,num10;

    while(bmenNo.search("-") > 0)
    {
        bmenNo = bmenNo.replace("-", "");
    }
    vv_RegNo = bmenNo;

    for(vv_i = 0 ; vv_i < 10; vv_i++)
    {
        vv_tmp = vv_RegNo.substr(vv_i, 1);
        if(vv_tmp == " " || vv_tmp == "")
        {
            return false;
        }
    }

    num1  = parseInt(vv_RegNo.substr(0,1));
    num2  = parseInt(vv_RegNo.substr(1,1));
    num3  = parseInt(vv_RegNo.substr(2,1));
    num4  = parseInt(vv_RegNo.substr(3,1));
    num5  = parseInt(vv_RegNo.substr(4,1));
    num6  = parseInt(vv_RegNo.substr(5,1));
    num7  = parseInt(vv_RegNo.substr(6,1));
    num8  = parseInt(vv_RegNo.substr(7,1));
    num9  = parseInt(vv_RegNo.substr(8,1));

    // 각자리별 check digit를 곱하여 더하고, 9자리는 곱한값의 10자리와 단자리를 합함
    vv_sum = (num1 * 1) + ((num2*3) % 10) + ((num3*7) % 10) +
             (num4 * 1) + ((num5*3) % 10) + ((num6*7) % 10) +
             (num7 * 1) + ((num8*3) % 10)
            + parseInt(   (gfn_fillZero((num9*5).toString(), 'L', 2)).substr( 0, 1)   )
            + parseInt(   (gfn_fillZero((num9*5).toString(), 'L', 2)).substr( 1, 1)   );

    num10 = 10 - (vv_sum % 10);
    if( num10 > 9 ) num10 = num10 - 10;
    if( num10 != parseInt(vv_RegNo.substr( 9, 1)) ) return false;

    return true;
}

/**
* 주민등록번호 체크 Digit
* @param {int} sJumin 주민등록번호
* @example
*          if(!gfn_checkPrnNo("4312350000000"))
*            alert("주민등록번호가 잘못되었습니다.");
* @returns 
*          주민등록번호가 잘못된 경우 메세지 출력
*/
function gfn_checkPrnNo(sJumin)
{
    var vv_RegNo;
    var vv_year,vv_tmp;
    var vv_sum,vv_i;
    var num1,num2,num3,num4, num5, num6;
    var num7,num8,num9,num10,num11,num12,num13;

    while(sJumin.search("-") > 0)
    {
        sJumin = sJumin.replace("-", "");
    }
    vv_RegNo = sJumin;

    for(vv_i = 1; vv_i < 13; vv_i++)
    {
        vv_tmp = vv_RegNo.substr(0, 1);

        if(vv_tmp == " " || vv_tmp == "") return false;
    }

    if(vv_RegNo.substr(6, 1) == "1" || vv_RegNo.substr(6, 1) == "2" )
        vv_year = "19"+vv_RegNo.substr(0, 6);
    else if(vv_RegNo.substr(6, 1) == "3" || vv_RegNo.substr(6, 1) == "4" )
        vv_year = "20"+vv_RegNo.substr(0, 6);
    else return false;

    if(!gfn_CheckDate2(vv_year)) return false;

    num1  = parseInt(vv_RegNo.substr(0,1));
    num2  = parseInt(vv_RegNo.substr(1,1));
    num3  = parseInt(vv_RegNo.substr(2,1));
    num4  = parseInt(vv_RegNo.substr(3,1));
    num5  = parseInt(vv_RegNo.substr(4,1));
    num6  = parseInt(vv_RegNo.substr(5,1));
    num7  = parseInt(vv_RegNo.substr(6,1));
    num8  = parseInt(vv_RegNo.substr(7,1));
    num9  = parseInt(vv_RegNo.substr(8,1));
    num10 = parseInt(vv_RegNo.substr(9,1));
    num11 = parseInt(vv_RegNo.substr(10,1));
    num12 = parseInt(vv_RegNo.substr(11,1));

    vv_sum = (num1  * 2) + (num2  * 3) + (num3  * 4) +
             (num4  * 5) + (num5  * 6) + (num6  * 7) +
             (num7  * 8) + (num8  * 9) + (num9  * 2) +
             (num10 * 3) + (num11 * 4) + (num12 * 5);

    num13 = 11 - (vv_sum % 11);
    if( num13 > 9 )
        num13 = num13 - 10;

    if( num13 != parseInt(vv_RegNo.substr(12,1)) )
        return false;

    return true;
}

/**
* 주민등록번호 체크시 사용하는 함수
* @param {int} date 년도
* @example
* @returns 
*/
function gfn_CheckDate2(date)
{
        var nyy, nmm, ndd;
        var yyt, mmt, ddt;
        var nMon = new Array(13);
        var leapyear;

        nMon[1] = 31;  nMon[2] = 29;   nMon[3] = 31;   nMon[4] = 30;
        nMon[5] = 31;  nMon[6] = 30;   nMon[7] = 31;   nMon[8] = 31;
        nMon[9] = 30;  nMon[10] = 31;  nMon[11] = 30;  nMon[12] = 31;

        nyy = parseInt(date.substr(0, 4 ), 10);
        nmm = parseInt(date.substr(4, 2 ), 10);
        ndd = parseInt(date.substr(6, 2 ), 10);

        if( (nyy > 0) && (nyy < 9999) && (nmm > 0) && (nmm < 13) && (ndd > 0) )
        {
//               윤년 계산
                 if(( nyy % 4 ) != 0)
                          leapyear = false;
                 else if(( nyy % 100 ) != 0)
                          leapyear = true;
                 else if(( nyy % 400 ) != 0)
                          leapyear = false;
                 else
                          leapyear = true;

                 if(leapyear) nMon[2] = 29;
                 else nMon[2] = 28;

//               일자 검사
                 if( ( ndd > 0 ) && ( ndd < ( nMon[nmm] + 1 )) )
                 {
                          return true;
                 }
                 else
                 {
                          return false;
                 }
        }
        else
        {
                 return false;
        }

        return false;
}

/**
* 한번에 전체를 모두 선택하게 하는 함수
* @param {obj} checkbox checkbox명
* @example
*          onclick="gfn_selectAll(document.listForm.CheckBox)"
* @returns 
*/
function gfn_selectAll(checkbox) {
    var i, cnt;

    if(typeof(checkbox) == "undefined") return;
    cnt =  checkbox.length;

    if( cnt > 1) {
        for(i=0; i<cnt; i++) {
            checkbox[i].checked = true;
        }
    }else {
        checkbox.checked = true;
    }   
}

/**
* 한번에 전체를 모두 해제시키는 함수
* @param {obj} checkbox checkbox명
* @example
*          onclick="gfn_undo(document.listForm.CheckBox)"
* @returns 
*/
function gfn_undo(checkbox) {
    var i, cnt;

    if(typeof(checkbox) == "undefined") return;
    cnt =  checkbox.length;

    if( cnt > 1) {
        for(i=0; i<cnt; i++) {
            checkbox[i].checked = false;
        }
    }else {
        checkbox.checked = false;
    }
}

/**
* 전체 선택 및 해제 토글 함수
* @param {obj} checkbox checkbox명
* @example
*          onclick="gfn_toggleSelect(document.listForm.CheckBox)"
* @returns 
*/
function gfn_toggleSelect(checkbox) {
    var i, cnt, flag;
    flag = false;

    if(typeof(checkbox) == "undefined") return;

    cnt = checkbox.length;
    
    if( cnt > 1) {
        for(i=0; i<cnt; i++) {
            if(checkbox[i].checked == true) {
                flag = false;
                break;
            }
            else
            {
                flag = true;
                break;               
            }
        }
        
        for(i=0; i<cnt; i++) {
            checkbox[i].checked = !flag;
        }           
    }else {
        checkbox.checked = !checkbox.checked;
    }
}

/**
* 체크박스 반전(전체선택, 전체해제)
* @param {obj} checkbox checkbox명
* @example
*          < sapn id="SEL_TEXT">해제< /span>    
*          onclick="gfn_switchAll(document.CheckBox)"
* @returns 
*/
function gfn_switchAll(chkName)
{
    var txt = eval("document.all.SEL_TEXT");
    var check = false;

    if ( txt.innerText == '선택')
    {
        txt.innerText = '해제';
        check = true;
    }
    else
    {
        txt.innerText = '선택';
    }

    var obj = eval("document.forms[0]."+chkName);

    var set;
    if ( obj.length )
    {
        for( iPos = 0; iPos < obj.length; iPos++ )
        {
            set = eval("document.forms[0]."+chkName+"["+iPos+"]");
            if ( set.disabled == false )
                set.checked = check;
        }
    }
    else
    {
        if ( obj.disabled == false )
            obj.checked = check;
    }
}

/**
* 툴팁의 위치를 지정하는 함수
* @example
*          < div id="TIP_BOX" style="width:0px; height:0px; position:absolute; left:0px; top:0px;" >< /div> 을 body에 추가해 둔 후
*          툴팁을 표시할 내용에 다음과 같이 이벤트를 추가한다
*          < td onmousemove="gfn_tipPos();" onmouseout="gfn_tipClr();" onmouseover="gfn_tipSet('툴팁내용')" >툴팁내용< /td>        
* @returns 
*/
function gfn_tipPos()
{
    var gap = 15;
    var left = event.x + document.body.scrollLeft;
    var top  = event.y + document.body.scrollTop;

    if ( TIP_BOX.innerHTML != '' )
    {
        var cw = TIP_BOX_TBL.clientWidth + gap;

        if ( event.x > document.body.clientWidth - cw )
            left = left - cw;
        else
            left = left + gap;

        var ch = TIP_BOX_TBL.clientHeight + gap;

        if ( event.y > document.body.clientHeight - ch )
            top = top - TIP_BOX_TBL.clientHeight;
        else
            top = top + gap;
    }
    else
    {
        left = left + gap;
        top = top + gap;
    }
    TIP_BOX.style.posLeft = left;
    TIP_BOX.style.posTop  = top;
}


/**
* 문자열을 테이블형으로 보여준다
* @param {obj} str 문자열
* @example
*          onmouseover="gfn_tipSet('툴팁내용','테두리색','테두리두께')"
* @returns 
*/
function gfn_tipSet(str,borderColor,borderWidth)
{
    var text = '';
    var sBorderColor = '#cccccc'; //테두리색
    if(borderColor != "undefined") sBorderColor = borderColor;
    var sBorderWidth = '3'; //테두리두께
    if(borderWidth != "undefined") sBorderWidth = borderWidth;
    if ( str.length > 1 )
        text = "<table align='center' border='0' cellpadding='10' cellspacing='0' style='border-width:"+sBorderWidth+"; border-color:"+sBorderColor+"; border-style:solid; font-size:9pt;' id='TIP_BOX_TBL'>"
             + "<tr><td style='padding: 5;white-space:nowrap;' bgcolor=#ffffff  >"+str+"</td></tr></table>";

    TIP_BOX.innerHTML = text;
}

/**
* 타이틀 관련 Tip 표시
* @param {obj} str 문자열
* @example
*          onmouseover="gfn_tipTit('툴팁내용')"
* @returns 
*/
function gfn_tipTit(str)
{
    var text = '';

    if ( str.length > 1 )
        text = "<table border='0' cellpadding='3' cellspacing='0' style='border-width:2; border-color:#EFEFEF; border-style:solid;' id='TIP_BOX_TBL'>"
             + "<tr><td style='font-size:9pt; font-weight:bold; text-align:center; color:#EF6D18; background-color:#FFFFFF'>"+str+"</td></tr></table>";

    TIP_BOX.innerHTML = text;
}

/**
* 툴팁 표시를 클리어한다
* @example
*          onmouseout="gfn_tipClr();"
* @returns 
*/
function gfn_tipClr()
{
    TIP_BOX.innerHTML = '';
}

/**
* 필수 입력 체크를 하는 공통함수
* @param {obj} form_name form명
* @example
*       1.필수항목 체크 / 최대길이 / 최소길이 제한 
*         필수항목   attribute : msg 메세지앞부분만 적기.
*         최소글자수 attribute : minlen 
*         최대글자수 attribute : maxlen 
*         ex) < INPUT TYPE="text" NAME="title" namekor="제목" VALUE="" msg="[제목]을" minlen="2" maxlen="10"/>
*        
*       2.숫자만 입력하도록 attribute 추가 하기 
*         입력필드가 숫자일 경우 chknum attribute 입력필드에 부여( chknum="Y") 
*         ex) < INPUT TYPE="text" NAME="telnum" nameKor="전화번호" chknum="Y" minlen="10" maxlen="11" VALUE="숫자만입력하기" msg="[전화번호]를"/>
*      
*       3.Email 체크하기 
*         Email유효성 체크할때에는 chkmail attribute부여(chkmail="Y")                            
*         ex) < INPUT TYPE="text" name="email" nameKor="이메일" chkmail="Y"  msg="[Email]을"/>
*       
*       4.필수로 선택해야할 radio button 체크하기
*         필수로 선택해야할 radio button항목의 경우에 
*         radio elements요소중 하나에만 msg attribute를 부여하면됨.                            
*         ex) < INPUT TYPE="radio"   NAME="animal" VALUE="rabbit"  msg="[동물]을">토끼
*             < INPUT TYPE="radio"   NAME="animal" VALUE="hamster">햄스터
*             < INPUT TYPE="radio"   NAME="animal" VALUE="turtle">거북이                            
*       
*       5.필수로 선택해야할 check box 체크하기
*         필수로 선택해야할 check box 경우에 
*         check elements요소중 하나에만 msg attribute를 부여하면됨.
*         ex) < INPUT TYPE="checkbox"   NAME="office" VALUE="pencil"  msg="[사무용품]을">연필
*             < INPUT TYPE="checkbox"   NAME="office" VALUE="computer">컴퓨터
*             < INPUT TYPE="checkbox"   NAME="office" VALUE="note">노트   
* @returns 
*/
function gfn_checkForm(formName) 
{
    var fLen = formName.elements.length;
	var fObj;	// 폼 요소
	var fTyp;	// 폼 요소 Type
	var fVal;	// 폼 요소 Value
	var fMsg;	// 경고 메시지 속성
	var fNum;	// 숫자만 입력 속성
	var fMax;	// 최대 길이 지정
	var fMin;	// 최소 길이 지정
	var fMxN;	// 최대값 지정
	var fMnN;	// 최소값 지정
	var fMal;	// 메일 FORMAT
	var fNameKor; //입력필드한글명
		
	for (i=0;i<fLen;i++) 
	{
        fObj     = formName.elements[i];
        fTyp     = fObj.getAttribute( "type" ).toUpperCase();
        fVal     = fObj.getAttribute( "value"   );
        fMsg     = fObj.getAttribute( "msg"     );	// 경고 메시지
        fNum     = fObj.getAttribute( "chknum"  );	// 숫자만 기입 가능하도록
        fMax     = fObj.getAttribute( "maxlen"  );	// 최대 입력글자수 제한
        fMin     = fObj.getAttribute( "minlen"  );	// 최소 입력글자수 제한
        fMxN     = fObj.getAttribute( "maxnum"  );	// 최대 숫자 제한
        fMnN     = fObj.getAttribute( "minnum"  );	// 최소 숫자 제한
        fMal     = fObj.getAttribute( "chkmail" );	// 이메일 체크
        fNameKor = fObj.getAttribute( "nameKor" );  //입력필드의 한글명

        if( fNameKor == null )
        {
            fNameKor = "";
        }
        
        if (fMsg != null && (fTyp == "RADIO" || fTyp == "CHECKBOX") && gfn_checkChecked(fObj) == false) 
        {
            alert(fMsg + " 선택해 주세요. ");
            fObj.focus(); return false;
        }

	    if (fMsg != null && (fTyp == "TEXT" || fTyp == "HIDDEN" || fTyp == "TEXTAREA" || fTyp == "PASSWORD") && fVal.replace(/ /gi,"") == "") 
	    {
            alert(fMsg + " 입력해 주시기 바랍니다. ");
            if (fTyp != "HIDDEN") 
            {
                fObj.focus();
            }
            return false;
	    }
	
		if (fMsg != null && (fTyp == "FILE" ) && fVal.replace(/ /gi,"") == "") 
		{
            alert(fMsg + " 선택해 주시기 바랍니다. ");
            if (fTyp != "HIDDEN") 
            { 
                fObj.focus();
            }
            return false;
		}
		if (fMsg != null && (fTyp == "SELECT-ONE" || fTyp == "SELECT-MULTIPLE") && fVal =="") 
		{
            alert(fMsg + " 11선택해 주세요. ");
            fObj.focus(); return false;
		}

        if (fNum != null && isNaN(fVal)) 
        {
            alert( fNameKor + "는(은) 숫자로만 입력해 주세요. ");
            fObj.focus(); return false;
        }
	
		if (fMax != null && fMax < gfn_getLen(fVal)) 
		{
            if( fNum != null )
            {
                alert( fNameKor+ "의 입력된 글자수가 "+fMax+"자보다 작아야합니다.\n(숫자"+fMax+"자 까지 가능합니다.)");  
            }
            else
            {
                alert( fNameKor + "의 입력된 글자수가 "+fMax+"자보다 작아야합니다.\n(영문"+fMax+"자, 한글 "+Math.floor(fMax/2)+"자 까지 가능합니다.)");
            }
            
            fObj.focus(); return false;
		}
	
		if (fMin != null && fMin > gfn_getLen(fVal)) 
		{
            alert( fNameKor + "의 입력된 글자수가 "+fMin+"자보다 커야합니다.");
            fObj.focus(); return false;
		}
	
        if (fMxN != null && parseInt(fMxN) < parseInt(fVal)) 
        {
            alert( fNameKor + "의 입력된 숫자는 "+fMxN+"보다 작아야합니다.");
            fObj.focus(); return false;
        }
        
        if (fMnN != null && parseInt(fMnN) > parseInt(fVal)) 
        {
            alert( fNameKor +"의 입력된 숫자는 "+fMnN+"보다 커야합니다.");
            fObj.focus(); return false;
        }
        
        if (fMal != null && gfn_checkEmail(fVal) == false && fVal != "") 
        {
            alert("이메일 주소가 올바르지 않습니다");
            fObj.focus(); return false;
        }
	}		
    return true;
}

/**
* 문자 길이 반환(영문 1byte, 한글 2byte 계산)
* @param {String} str 문자열
* @example
*          gfn_checkEqualLength('form_name', 'text_cntn', '내용', 200);
* @returns
*          문자열 길이 
*/
function gfn_getLen(str) 
{
    var len;
    var temp;

    len = str.length;
    var tot_cnt = 0;

    for( k = 0 ;k < len ; k++ )
    {
    	temp = str.charAt(k);
    	if(escape(temp).length > 4)
    	{
    	    tot_cnt += 2;
    	}
    	else
    	{
    	    tot_cnt++;
    	}
    }
    return tot_cnt;
}
	
/**
* 문자 길이 반환(영문 1byte, 한글 2byte 계산)
* @param {String} str 문자열
* @example
          if (gfn_checkEmail(strEmail) == false ) alert("이메일 주소가 올바르지 않습니다");
* @returns
*/ 
function gfn_checkEmail(str)
{
    var reg = /^((\w|[\-\.])+)@((\w|[\-\.])+)\.([A-Za-z]+)$/;
    if (str.search(reg) != -1) 
    {
	    return true;
	}
	return false;
}

/**
* 배열 요소일 경우 checked 된것이 있는지 확인하는 함수
* @param {obj} obj object명
* @example
           if (gfn_checkChecked(obj) == false)  alert("~을(를) 선택해 주십시오.");
* @returns
*/
function gfn_checkChecked(obj) 
{
    var fname = obj.form.name;
    var objnm = obj.name;
    var oElem = eval(fname+"."+objnm);
    var ret   = false;

    if (typeof(oElem.length) == "undefined") 
    {
        if (oElem.checked) 
        {
            ret = true;
        }
    } 
    else 
    {
        for (var i=0;i<oElem.length;i++) 
        {
            if (oElem[i].checked) 
            {
                ret = true;
            }
        }
    }	
    return ret;
}

/**
* 년월일이 같이 있는 Input box 상에서 년월일을 체크하는 함수
* 인자값중에서 type으로 년월일의 구분자를 선택할 수 있다.
* @param {obj} obj object명
* @param {String} type "/"
* @example
*          onblur="gfn_dateType(this, "/")"
* @returns
*          년월일을 /로 구분 (2010/05/10)
*/
function gfn_dateType(obj, type)
{
   var sDate=obj.value.replace(/(\,|\.|\-|\/|[ ])/g,""); 
//   var sFormat="YYYYMMDD";
   var aDaysInMonth=new Array(31,28,31,30,31,30,31,31,30,31,30,31);
   if (sDate.length == 0)
        return;
   if (sDate.length != 8 )
   {    
        //alert("날짜를 잘못 입력하였습니다.\n날짜형식은 YYYYMMDD입니다.");       
        //return ; 
		return false;
   }
   else
   {
        var iYear = sDate.substr(0,4);
        var iMonth=eval(sDate.substr(4,2));
        var iDay=eval(sDate.substr(6,2));

        var iDaysInMonth=(iMonth!=2)?aDaysInMonth[iMonth-1]:((eval(iYear)%4==0 && eval(iYear)%100!=0 || eval(iYear)%400==0)?29:28); 
        if( (iDay!=null && iMonth!=null && iYear!=null && eval(iYear) != 0 && iMonth<13 && iMonth>0 && iDay>0 && iDay<=iDaysInMonth) == false )
        {   
            	obj.focus();
            	return false;	
        }
        else
        {
            iMonth = (iMonth >=10)? iMonth:"0"+iMonth;            
            iDay = (iDay>=10 )? iDay:"0"+iDay;
            obj.value=""+iYear+type+iMonth+type+iDay; 
            return true; 
        }
   }
}


/**
* 입력필드 입력가능 문자 제어
* @param {obj} obj object명
* @param {String} flag 한/영구분
* @example
*         onkeyup="gfn_checkKorNum(this)"
* @returns
*/
function gfn_checkFilter( obj, flag ) 
{
	//alert( event.keyCode); //0:48 57 65~90
	  var comp;
	  
	  //한글만입력가능
	  if( flag == "KOREAN" )
	  {
	      comp = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_.:;!@#$%^&*()=+<>";
	  }
	  //영문만입력가능
	  else if( flag == "ENG" )
	  {
	  	  //ime-mode가 disabled일때 가능 
	      comp = "0123456789-_.:;!@#$%^&*()=+<>";
	  }
	  else
	  {
	  	  //한글과 숫자입력가능 
        comp = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_.:;!@#$%^&*()=+<>";
    }
    var len  = obj.value.length;
    
    if( len > 0 ) 
    {
        for( i = 0 ; i < len ; i++ ) 
        {
            if( comp.indexOf( obj.value.substring(i,i+1))>0 ) 
        	  {
                obj.value = obj.value.substring(0,len-1);
            }
            else
            {
                
            }
        }
    }
}

/**
* 특수기호들을 전부 없애는 함수
* @param {obj} obj object명
* @example
*         onkeyup="gfn_chkFilter(this)"
* @returns
*/
function gfn_removeSpecialChar(obj)
{
	val       = obj.value;
	re        = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\'\"\\\(\=]/gi;
	obj.value = val.replace(re,""); 
}

/**
* 특정날짜에 대하여 지정한값 만큼 +,-한 날짜를 반환한다.
* @param {obj} pInterval  'yyyy'는 년도 가감, 'm'은 월가감, 'd'는 일가감
* @param {obj} pAddVal    가감하고자 하는 값(정수형)
* @param {obj} pYyyymmdd  가감의 기준이 되는 날짜
* @param {obj} pDelimiter pYyyymmdd값에 사용된 구분자를 설정하고 없으면 ""를 입력한다.
* @example
*         2008-01-01dp 3일 더하기=>  addDate("d",3,"2008-08-01", "-" );
* @returns
*/
function gfn_addDate(pInterval, pAddVal, pYyyymmdd, pDelimiter)
{
    var yyyy;
    var mm;
    var dd;
    var cDate;
    var oDate;
    var cYear, cMonth, cDay;
 
     if (pDelimiter != "") 
     {
         pYyyymmdd = pYyyymmdd.replace(eval("/\\" + pDelimiter + "/g"), "");
     }
 

     yyyy = pYyyymmdd.substr(0, 4);
     mm   = pYyyymmdd.substr(4, 2);
     dd   = pYyyymmdd.substr(6, 2);
 
     if (pInterval == "yyyy") 
     {
         yyyy = (yyyy * 1) + (pAddVal * 1); 
     } 
     else if (pInterval == "m") 
     {
         mm  = (mm * 1) + (pAddVal * 1);
     }
     else if (pInterval == "d") 
     {
         dd  = (dd * 1) + (pAddVal * 1);
     }
 
     cDate  = new Date(yyyy, mm - 1, dd); // 12월, 31일을 초과하는 입력값에 대해 자동으로 계산된 날짜가 만들어짐.
     cYear  = cDate.getFullYear();
     cMonth = cDate.getMonth() + 1;
     cDay   = cDate.getDate();
 
     cMonth = cMonth < 10 ? "0" + cMonth : cMonth;
     cDay   = cDay < 10 ? "0" + cDay : cDay;

     if (pDelimiter != "") 
     {
 	       //alert( cYear + pDelimiter + cMonth + pDelimiter + cDay);
         return cYear + pDelimiter + cMonth + pDelimiter + cDay;
     }
     else
     {
 	       //alert( cYear + cMonth + cDay );
         return cYear + cMonth + cDay;
     }
 }
 
/**
* 날짜필드에 입력된 숫자로된 문자열을 체크하여 YYYY-MM-DD형태로 보여줌.
* @param {obj} obj        날짜 Input Box
* @param {obj} delimiter  날짜구분자 
* @example
*          onKeyUp="gfn_getDateFormat(this,'-');"
* @returns
*/
 function gfn_getDateFormat( obj, delimiter )
 {
	 gfn_removeSpecialChar( obj, delimiter );
     
	 var str = obj.value;
	 var len = str.length;
     
	 switch( len )
     {
		 case 1:
	     case 2:
	     case 3:
	     case 4: 
			 obj.value = str; 
		     break;
	     case 5:
		 case 6: 
			 obj.value = str.substring(0,4) + "-" + str.substring(4);
		     break;
	     case 7:
	     case 8:
			 obj.value = str.substring(0,4) + "-" + str.substring(4,6) + "-" + str.substring(6);
             //gfn_DateType( obj,"-" );
			 break;
	 }
 }

/**
* 조회조건의 시작일짜 날짜일자를 당일/3일/1주일/1개월/3개월/6개월/12개월로 계산하여
* 자동으로 날짜입력필드에 Setting 
* 시작일짜와 날짜 필드의 input box name은 fromDate, toDate이어야 함.
* @param {obj} flag       날짜term 구분(0d:당일, 3d:3일, 1w:1주일, 1m:1개월, 3m:3개월, 6m:6개월,12m:12개월)
* @example
*          gfn_setDateInputField( "0d" );
* @returns
*          당일,3일,1주일,1개월,3개월,6개월,12개월관련된 날짜 return
*/
 function gfn_setDateInputField(flag,fromSearchDate,toSearchDate)
 {
     //사용자가 조회기간을 당일/3일/1주일/1개월등의 버튼으로 날짜를 세팅하고자 할때
	 //사용자가 입력한 toDate필드 날짜를 항상 가준으로 fromDate날짜필드를 세팅하도록 함.
	 
	 var fromDate  = ""; //조회시작일
	 var toDate    = document.getElementById(toSearchDate).value; //조회끝일
	 var tmpObjFrom;
	 var tmpObjTo;
	 
	 //fromDate필드를 세팅하기전에 toDate필드의 날짜 유효성을 체크하도록 함.
	 gfn_dateType(document.getElementById(toSearchDate),"-");
       
	 //toDate부분이 존재하지 않는 경우 
	 //default로 toDate는 현재날짜를 구하여 setting하여 날짜를 가감하도록 함.
	 if( toDate == null || toDate.length <= 0 )
	 {
	     var year  = new Date().getFullYear();
		 var month = new Date().getMonth() + 1;
		 var date  = new Date().getDate();
		 
		 if( month < 10 ) month = "0" + month;
		 if( date  < 10  ) date  = "0" + date;

		 if ($("#"+fromSearchDate).hasClass('input_nara_month'))
		 {
			 toDate = year + "-" + month;
		 }
		 else
		 {
			 toDate = year + "-" + month + "-" + date;
		 }
		 
	}

	//당일
	if( flag == "0d" )
	{
	    fromDate = gfn_addDate( "d", 0, toDate, "-");
	}
	//3일전 
	else if( flag == "3d" )
	{   
	    fromDate = gfn_addDate( "d", -3, toDate, "-" );
	    fromDate = gfn_addDate( "d", +1, fromDate, "-" );
	}
	//1주일전
	else if( flag == "1w" )
	{
	    fromDate = gfn_addDate( "d", -7, toDate, "-" );
	    fromDate = gfn_addDate( "d", +1, fromDate, "-" );
	}
	//1개월전
	else if( flag == "1m" )
	{
	    fromDate = gfn_addDate( "m", -1, toDate, "-" );
	    fromDate = gfn_addDate( "d", +1, fromDate, "-" );
	}
	//1개월후
	else if( flag == "1ml" )
	{
		fromDate = gfn_addDate( "m", +1, toDate, "-" );
		fromDate = gfn_addDate( "d", -1, fromDate, "-" );
		var tempDate;
		tempDate = fromDate;
		fromDate = toDate;
		toDate = tempDate;
	}
	//3개월전
	else if( flag == "3m" )
	{
	    fromDate = gfn_addDate( "m", -3 , toDate, "-" );
	    fromDate = gfn_addDate( "d", +1, fromDate, "-" );
	}
	//6개월전 
	else if( flag == "6m" )
	{
	    fromDate = gfn_addDate( "m", -6, toDate, "-" );
	    fromDate = gfn_addDate( "d", +1, fromDate, "-" );
	}
	//12개월전
	else if( flag == "12m" )
	{
	    fromDate = gfn_addDate( "m", -12, toDate, "-" );
	    fromDate = gfn_addDate( "d", +1, fromDate, "-" );
	}
	//24개월전
	else if( flag == "24m" )
	{
	    fromDate = gfn_addDate( "m", -24, toDate, "-" );
	    fromDate = gfn_addDate( "d", +1, fromDate, "-" );
	    
	}
	//36개월전
	else if( flag == "36m" )
	{
	    fromDate = gfn_addDate( "m", -36, toDate, "-" );
	    fromDate = gfn_addDate( "d", +1, fromDate, "-" );
	}
	toDate = gfn_addDate( "d", 0, toDate, "-");
	tmpObjFrom = "#" + fromSearchDate;
	tmpObjTo = "#" + toSearchDate;
	
	if ($("#"+fromSearchDate).hasClass('input_nara_month'))
	{
		
		fromDate = fromDate.substring(0,7);
		toDate = toDate.substring(0,7);
		
	}
	
	$(tmpObjFrom).val(fromDate);
	$(tmpObjTo).val(toDate);
}


/**
* 특정폼을 주어진 actionUrl로 submit 
* @param {obj} formName  submit할 폼이름
* @param {obj} actionUrl actionUrl
* @example
*          gfn_formSubmit( "searchForm", "/nara/test.do" );
* @returns
*/
 function gfn_formSubmit( formName , actionUrl )
 {
     var obj    = document.getElementById( formName );
	 obj.action = actionUrl;
	 obj.submit();
 }



/**
 * 한글을 2바이트 씩 계산하여 입력받은 문자열이 DB에 저장될 때 총 몇바이트를 차지하는지 계산한다.
 * 엔터(\r\n)는 2바이트를 차지한다.
 * @param val : 입력받은 문자열
 */
function gfn_callength(val)
{
  // 입력받은 문자열을 escape() 를 이용하여 변환한다.
  // 변환한 문자열 중 유니코드(한글 등)는 공통적으로 %uxxxx로 변환된다.
  var temp_estr = escape(val);
  var s_index   = 0;
  var e_index   = 0;
  var temp_str  = "";
  var cnt       = 0;

  // 문자열 중에서 유니코드를 찾아 제거하면서 갯수를 센다.
  while ((e_index = temp_estr.indexOf("%u", s_index)) >= 0)  // 제거할 문자열이 존재한다면
  {
    temp_str += temp_estr.substring(s_index, e_index);
    s_index = e_index + 6;
    cnt ++;
  }

  temp_str += temp_estr.substring(s_index);

  temp_str = unescape(temp_str);  // 원래 문자열로 바꾼다.

  // 유니코드는 2바이트 씩 계산하고 나머지는 1바이트씩 계산한다.
  return ((cnt * 2) + temp_str.length) + "";
}

String.prototype.comma = function() {
    tmp = this.split('.');
    var str = new Array();
    var v = tmp[0].replace(/,/gi,'');
    for(var i=0; i<=v.length; i++) {
        str[str.length] = v.charAt(v.length-i);
        if(i%3==0 && i!=0 && i!=v.length) {
            str[str.length] = '.';
        }
    }
    str = str.reverse().join('').replace(/\./gi,',');
    return (tmp.length==2) ? str + '.' + tmp[1] : str;
}


/*
 입력값이 숫자를 포함하고 있는지를 체크합니다  
 */
function gfn_isContainNumericData(strField)
{
              var i;
              var ch;
              var isNumeric = true;
              for (i = 0; i < strField.length; i++)
              {
                            ch = strField.charAt(i);
                            if (!((ch >= '0') && (ch <= '9')))
                                          isNumeric = false;
              }
              return isNumeric;
}
 
 /*
  파일이 잘못된 확장자를 포함하고 있는지  체크합니다  
 */
 
 function gfn_setFileChk(target)
 {
 	var existExt = ".exe,.asp,.cmd,.class,.html,.js,.jsp,.php,.sh,.csh";
 	var existExtArray = existExt.split(",");
 	
 	var Temp_file_name = target;
 	if(Temp_file_name != "")
 	{
 		Temp_strExt_num = Temp_file_name.slice(Temp_file_name.indexOf(".")).toLowerCase();
 		for(var tmpcnt = 0; tmpcnt < existExtArray.length; tmpcnt++)
 		{
 			if(Temp_strExt_num == existExtArray[tmpcnt])
 			{
 				return false;
 			}
 		}
 	}
 	return true; 	
 }

 /*
  input에서 focus가 왔을때  Border색깔을 변경한다.   
 */
function gfn_focus( obj ){		 
		 $(obj).css( "border-color", "#85a0c7");
		 
}
/*
  input에서 focus가 빠져나갈때 Border색깔을 원래대로 변경한다.   
*/
function gfn_focusnot( obj ){		 
		 $(obj).css( "border-color", "#d0d0cc"); 
		 
}
  
  /*
  * 엑셀다운로드 
  * 최초작성일 : 2011.09.06
  * 작성자 : 송인겸
  * 
  * ======================================================================================================
  * 사용방법
  * gfn_fileDownLoad( atchFileId, fileSn )
  * 	atchFileId : 파일첨부ID
  * 	fileSn : 순번 
  * 
  * 사용예)
  * gfn_fileDownLoad("FILE002", "0" );
  * ======================================================================================================
  */
function gfn_fileDownLoad( atchFileid, fileSn ) {
	if( $("#iFileDownFrame").length <= 0 ) {
	   var strBuf = "<iframe src='' id='iFileDownFrame' name='iFileDownFrame'  width='0' height='0'></iframe>";
	   $("body").append( strBuf );
	}
	var iframeDiv = document.getElementById('iFileDownFrame');
	
	iframeDiv.src = "/cmm/fms/FileDown.do?atchFileId="+atchFileid+"&fileSn="+fileSn;
	
}

/*
   * ajax 에러 확인 
   * 최초작성일 : 2011.09.06
   * 작성자 : 송인겸
   * 
   * ======================================================================================================
   * 사용방법
   * gfn_ajaxerror( error )
   * ajax전송시에 에러발생시에 에러 리턴하여 에러표시  
   *  
   * 사용예)
   * gfn_ajaxerror( error )
   * ======================================================================================================
*/  
function gfn_ajaxerror( error )
{	   
   var msg =  error.replace('Invalid JSON: ', '' );   
   alert( msg );
}
  