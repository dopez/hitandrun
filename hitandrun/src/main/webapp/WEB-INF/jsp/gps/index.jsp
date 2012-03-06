<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%-- 공통 taglib 선언 Start	================================================--%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<%-- 공통 taglib 선언 End	====================================================--%>
<jsp:include page="/gps/cmm/left.do" flush="false">
	<jsp:param name="menuSkin" value="${menuManageVO.menuSkin}"/>
</jsp:include>
<script type="text/javascript">
function showSvyLayer(val) {
	var LayerName;
	for (var i=1;i<=2;i++) {
	LayerName = "#svyContentsLayer0"+i;
	i==val?$(LayerName).show():$(LayerName).hide();
	}
}
function svyMenuImage(val) {
	var img1 = document.getElementById("svyMenu1");
	var img2 = document.getElementById("svyMenu2");
	if(val==1){
		img1.src = "/images/gps/index/homepage_bar_on.gif";
		img2.src = "/images/gps/index/internet_bar_off.gif";
	} else if (val==2){
		img1.src = "/images/gps/index/homepage_bar_off.gif";
		img2.src = "/images/gps/index/internet_bar_on.gif";
	}
}

function showManualLayer(val) {
	var LayerName;
	for (var i=1;i<=3;i++) {
		LayerName = "#manualContents0"+i;
		i==val?$(LayerName).show():$(LayerName).hide();
	}
}
function statManualImage(val) {
	var img1 = document.getElementById("statManual1");
	var img2 = document.getElementById("statManual2");
	var img3 = document.getElementById("statManual3");
	if(val==1){
		img1.src = "/images/gps/index/im_01_on.gif";
		img2.src = "/images/gps/index/im_02_off.gif";
		img3.src = "/images/gps/index/im_03_off.gif";
	} else if (val==2){
		img1.src = "/images/gps/index/im_01_off.gif";
		img2.src = "/images/gps/index/im_02_on.gif";
		img3.src = "/images/gps/index/im_03_off.gif";
	} else if (val==3){
		img1.src = "/images/gps/index/im_01_off.gif";
		img2.src = "/images/gps/index/im_02_off.gif";
		img3.src = "/images/gps/index/im_03_on.gif";
	}
}

function showStatLayer(val) {
	var LayerName;
	for (var i=1;i<=4;i++) {
	LayerName = "#statContentsLayer0"+i;
	i==val?$(LayerName).show():$(LayerName).hide();
	}
}
function statMenuImage(val) {
	var img1 = document.getElementById("statMenu1");
	var img2 = document.getElementById("statMenu2");
	var img3 = document.getElementById("statMenu3");
	var img4 = document.getElementById("statMenu4");	
	if(val==1){
		img1.src = "/images/gps/index/main_manu_01_on.gif";
		img2.src = "/images/gps/index/main_manu_02_off.gif";
		img3.src = "/images/gps/index/main_manu_03_off.gif";
		img4.src = "/images/gps/index/main_manu_04_off.gif";
	} else if (val==2){
		img1.src = "/images/gps/index/main_manu_01_off.gif";
		img2.src = "/images/gps/index/main_manu_02_on.gif";
		img3.src = "/images/gps/index/main_manu_03_off.gif";
		img4.src = "/images/gps/index/main_manu_04_off.gif";
	} else if (val==3){
		img1.src = "/images/gps/index/main_manu_01_off.gif";
		img2.src = "/images/gps/index/main_manu_02_off.gif";
		img3.src = "/images/gps/index/main_manu_03_on.gif";
		img4.src = "/images/gps/index/main_manu_04_off.gif";
	} else {
		img1.src = "/images/gps/index/main_manu_01_off.gif";
		img2.src = "/images/gps/index/main_manu_02_off.gif";
		img3.src = "/images/gps/index/main_manu_03_off.gif";
		img4.src = "/images/gps/index/main_manu_04_on.gif";
	}
}

function fn_id_search(){
	gfn_postPopupWin("gpsLoginVO", "/gps/login/idSearch.do", "아이디 찾기", 400, 600, "yes", "no");
}

function fn_passwordReissue(){
	gfn_postPopupWin("gpsLoginVO", "/gps/login/passwordReissue.do", "비밀번호 찾기", 300, 600, "no", "no");
}
</script>
<script type="text/javascript" language="javascript">
	JQ_setValidation('gpsLoginVO');
	JQ_onload();
	<%-- 
	/************************************************************************ 
	fnc name : fncPageOnload                                   
	outline : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
	parameter : 없음        
	directions : fncPageOnload()              
	since : 2011-06-10   
	author : 통계포털  황기연       
	
	    date      author             note  
	 ----------   -------     ------------------- 
	 2011.06.10    황기연                      최초 생성                    
	************************************************************************/ 
	--%>
	function fncPageOnload(){
		//메시징처리
		topStatMenuImage(0);
		<c:if test="${!empty message}">
			alert('<c:out value = "${message}"/>');
		</c:if>
	}
	
	<%-- 
	/************************************************************************ 
	fnc name : actionLogin                                   
	outline : 로그인처리 함수      
	parameter : 없음        
	directions : actionLogin()              
	since : 2011-06-10   
	author : 통계포털  황기연       
	
	    date      author             note  
	 ----------   -------     ------------------- 
	 2011.06.10    황기연                       최초 생성                 
	************************************************************************/ 
	--%>
	function fn_login(){
		alert("로그인후 이용 가능 합니다.");
		location.href="<c:url value='/gps/login/gpsLoginUsr.do'><c:param name='menuId' value='0010001100122123'/><c:param name='leftMenuId' value='0010001100122'/></c:url>";
	}

	<%-- 
	/************************************************************************ 
	fnc name :fn_bbsList                             
	outline : 게시글목록페이지호출        
	parameter : 없음        
	directions : fn_bbsList()              
	since : 2011-06-10   
	author : 통계포털  황기연       
	
	    date      author             note  
	 ----------   -------     ------------------- 
	 2011.06.10    황기연                       최초 생성                 
	************************************************************************/ 
	--%>
	function fn_bbsList(bbsId,menuId,leftMenuId){
		$('#bbsId').val(bbsId);
		$('#menuId').val(menuId);
		$('#leftMenuId').val(leftMenuId);
		JQ_setProcessMsg();
		JQ_request("gpsLoginVO", "<c:url value='/gps/bbs/selectBbsList.do'/>","gpsLoginVO");
	}
	
</script>
<div id="contents">
	<div class="inner_content01">
		<div class="visual_contents">
			<img src="/images/gps/index/main_im.gif" alt="통계산업화의 첫걸음 국가통계 품질향상을 주도하는 국가통계의 발전을 선도합니다." title="통계산업화의 첫걸음 국가통계 품질향상을 주도하는 국가통계의 발전을 선도합니다.">
		</div>
	</div>
	
	<div class="inner_content02">
		<div class="inside_contents">
			<div class="stat_contents">
				<ul>
					<li class="title"><img src="/images/gps/index/title_01.gif" alt="현재 진행중인 조사" title="현재 진행중인 조사"></li>
					<!--<li class="more"><img src="/images/gps/index/bt_more.gif" alt="더보기" title="더보기"></li>-->
				</ul>
				
				<div class="svyContentsLayer01">
					<jsp:include page="/gps/svy/selectIntnetSvyContMiniList.do" flush="true"/>
				</div>
			</div>
			
			<div class="innerContents">
				<div class="policyTitle">
					<c:choose>
						<c:when test="${!empty gpsSessionVO}">
							<a href="/pms/index.do" target="_blank"><img src="/images/gps/index/sys_01.gif" alt="통계정책관리시스템" title="통계정책관리시스템"/></a>
						</c:when>
						<c:otherwise>
							<a href="#LINK" onclick="fn_login();return false;" onkeypress="this.onclick();return false;"><img src="/images/gps/index/sys_01.gif" alt="통계정책관리시스템" title="통계정책관리시스템"/></a>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="produceTitle">
					<c:choose>
						<c:when test="${!empty gpsSessionVO}">
							<a href="/sps/index.do" target="_blank"><img src="/images/gps/index/sys_02.gif" alt="통계생산시스템" title="통계생산시스템"/></a>
						</c:when>
						<c:otherwise>
							<a href="#LINK" onclick="fn_login();return false;" onkeypress="this.onclick();return false;"><img src="/images/gps/index/sys_02.gif" alt="통계생산시스템" title="통계생산시스템"/></a>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="metaTitle">
					<c:choose>
						<c:when test="${!empty gpsSessionVO}">
						<c:if test="${serverSe eq 'naraext'}">
							<c:set var="url" value="www.narastat.kr"/>
						</c:if>
						<c:if test="${serverSe eq 'naraint'}">
							<c:set var="url" value="10.184.70.80"/>
						</c:if>
						<c:if test="${serverSe eq 'naradev'}">
							<c:set var="url" value="10.184.70.83"/>
						</c:if>
							<a href="http://<c:out value="${url}" escapeXml="true"/>/meta/loginMeta_ins.jsp?txtID=<c:out value="${gpsSessionVO.usrId}" escapeXml="true"/>&PW=<c:out value="${pw}" escapeXml="true"/>" target="_blank"><img src="/images/gps/index/sys_03.gif" alt="통계메타관리시스템" title="통계메타관리시스템"/></a>
						</c:when>
						<c:otherwise>
							<a href="#LINK" onclick="fn_login();return false;" onkeypress="this.onclick();return false;"><img src="/images/gps/index/sys_03.gif" alt="통계메타관리시스템" title="통계메타관리시스템"/></a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			
			<div class="questionContents">
				<div class="qna_contents">
					<ul>
						<!--<li><a href="/gps/bbs/selectBbsList.do?menuId=0010001100110113&leftMenuId=0010001100110&bbsId=NARA103"><img src="/images/gps/index/bt_faq_off.gif" onmouseover="overImg(this);" onFocus="overImg(this);" onmouseout="outImg(this);" onBlur="outImg(this);" alt="faq" title="faq"></a></li>-->
						<li><a href="/gps/bbs/selectBbsList.do?menuId=0010001100116114&leftMenuId=0010001100116&bbsId=NARA105"><img src="/images/gps/index/bt_qna_off.gif" onmouseover="overImg(this);" onFocus="overImg(this);" onmouseout="outImg(this);" onBlur="outImg(this);" alt="질의응답" title="질의응답"></a></li>
					</ul>
				</div>
				<div class="return_contents">
					<ul>
						<li><a href="/gps/bbs/selectBbsList.do?menuId=0010001100116115&leftMenuId=0010001100116&bbsId=NARA104"><img src="/images/gps/index/bt_go_off.gif" onmouseover="overImg(this);" onFocus="overImg(this);" onmouseout="outImg(this);" onBlur="outImg(this);" alt="시스템개선의견바로가기" title="시스템개선의견바로가기"></a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	
</div>
<jsp:include page="/gps/cmm/footer.do" flush="false">
	<jsp:param name="menuSkin" value="${menuManageVO.menuSkin}"/>
</jsp:include>