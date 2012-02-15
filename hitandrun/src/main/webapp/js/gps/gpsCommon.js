/** 
 포털 공통 자바스크립트
**/

/**
 * 첨부파일 확장자검사 *
 * 최초작성일 : 2011.07.13
 * 작성자 : 황기연
 * 
 * ======================================================================================================
 * 사용방법
 * uploadFileInspct(se,id)
 * se : 구분
 * id : 파일아이디
 * 
 * 사용예)
 * uploadFileInspct('img',id)   //이미지파일인지 검사
 * uploadFileInspct('file',id)  //.exe,.asp,.jsp 등 업로드금지 파일 확장자 검사
 * ======================================================================================================
 */
function uploadFileInspct(se,id){
	var existExt = "";
	var existExtArray = new Array();
	var msg = "";
	var val = "";
	var f_name = "";
	var extsn = "";
	var result = true;
	
	// 확장자 
	if(se == "file"){
		existExt = ".exe,.asp,.cmd,.class,.html,.js,.jsp,.php,.sh,.csh,.jar";
	}else{
		existExt = ".jpg,.gif,.png,.bmp";
	}
	
	existExtArray = existExt.split(",");
	val = $('#'+id).val().split("\\");
	f_name = val[val.length-1];
	extsn = f_name.substring(f_name.length-4,f_name.length);
	
	for(var i=0;i<existExtArray.length;i++){
		if(extsn.toLowerCase() == existExtArray[i]){
			if(se == "file"){
				msg = "업로드 금지 파일입니다.";
				result = false;
				break;
			}else{
				result = true;
				break;
			}
		}else{
			if(se == "img"){
				result = false;
				msg = "이미지 파일만 업로드 가능합니다.";
			}
		}
	}
	//메시지출력,첨부파일초기화
	if(!result){
		alert(msg);
		$('#'+id).replaceWith($('#'+id).clone(true));
	}
}

/**
* 이미지 mouseover mouseout 
*/
function MM_preloadImages() { //v3.0
	var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
	var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
	if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_swapImgRestore() { //v3.0
	var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_findObj(n, d) { //v4.01
	var p,i,x;if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
	d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
	if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
	for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
	if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
	var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
	if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

/**
* 입력값에 스페이스 이외의 의미있는 값이 있는지 체크
*/
function isEmpty(data){
   if (data.value == null || data.value.replace(/ /gi, "") == "") {
       return true;
   }
	return false;
}

/*
* 이미지 마우스 오버
* 최초작성일 : 2011.10.06
*/
function overImg(obj) {
	obj.src = obj.src.replace( 'off.gif', 'on.gif' );
}
function outImg(obj) {
	obj.src = obj.src.replace( 'on.gif', 'off.gif' );
}