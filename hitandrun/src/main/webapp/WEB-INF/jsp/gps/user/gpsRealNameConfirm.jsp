<%--
********************************************************************************
* @source      : header.jsp
* @description : 헤더  JSP - 세션/사용자/권한 처리
*                   
********************************************************************************
* DATE         AUTHOR    DESCRIPTION
*===============================================================================
* 2011-08-01   이진우           최초작성
********************************************************************************
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%-- 공통 taglib 선언 start	=================================================--%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<%-- 공통 taglib 선언 end	=====================================================--%>
<%-- header start	==========================================================--%>

<jsp:include page="/gps/cmm/left.do" flush="false"/> 
<%-- header end	==============================================================--%>
<script language='javascript' src='/gpkisecureweb/var.js'></script>
<script language='javascript' src='/gpkisecureweb/GPKIFunc.js'></script>
<script language='javascript' src='/gpkisecureweb/object.js'></script>
<%-- page javascript start	==================================================--%>
<script type="text/javascript">
JQ_onload();

function fncPageOnload() {
	topStatMenuImage(0);
	subTopStatMenuImage(0);
	leftStatMenuImage(0);
	<c:if test="${!empty message}">
	alert('<c:out value = "${message}"/>');
	</c:if>
}


	function fn_regist_form() {
		JQ_request("userRegisterVO", "<c:url value='/gps/user/actionRegistUserForm.do'/>");
	}


	<%-- 
	/************************************************************************ 
	fnc name : openCBAWindow                                   
	outline : 실명인증     
	parameter : 없음        
	directions :               
	since : 2011-08-01   
	author : 통계포털  황기연       
	
	    date      author             note  
	 ----------   -------     ------------------- 
	 2011.10.04    황기연                       최초 생성                 
	************************************************************************/ 
	--%>
     function openCBAWindow(){
   	 <c:choose>
 		<c:when test="${serverSe == 'naraext'}">
	 		// 가상식별 실명확인서비스 팝업창 호출
			CBA_window=window.open('','CbaWindow', 'width=410, height=450, resizable=0,scrollbars=no, status=0, titlebar=0, toolbar=0, left=300, top=200' );
			// 가상식별 실명확인서비스 요청 URL
			document.form1.action = 'https://name.siren24.com/vname/jsp/vname_j10.jsp'; 
			document.form1.target = 'CbaWindow'; 
			document.form1.submit();
		</c:when>
 		<c:otherwise>
 		alert('이용할 수 없습니다');
         return false;
 		</c:otherwise>
 	 </c:choose>
	}


	<%-- 
	/************************************************************************ 
	fnc name : fn_iPinConfirm                                   
	outline : i-pin     
	parameter : 없음        
	directions :               
	since : 2011-08-01   
	author : 통계포털  황기연       
	
	    date      author             note  
	 ----------   -------     ------------------- 
	 2011.10.04    황기연                       최초 생성                 
	************************************************************************/ 
	--%>
	function fn_iPinConfirm(){
	<c:choose>
		<c:when test="${serverSe == 'naraext'}">
			wWidth = 360;
		    wHight = 120;
		    wX = (window.screen.width - wWidth) / 2;
		    wY = (window.screen.height - wHight) / 2;
			var w = window.open("<c:url value='/G-PIN/AuthRequest.jsp'><c:param name='menuId' value='${userRegisterVO.menuId}'/><c:param name='leftMenuId' value='${userRegisterVO.leftMenuId}'/></c:url>", "gPinLoginWin", "directories=no,toolbar=no,left="+wX+",top="+wY+",width="+wWidth+",height="+wHight);
		</c:when>
		<c:otherwise>
		alert('이용할 수 없습니다');
        return false;
		</c:otherwise>
	</c:choose>
	}

	<%-- 
	/************************************************************************ 
	fnc name : fn_gpki                     
	outline : fn_gpki 
	parameter : 없음        
	directions :               
	since : 2011-08-01   
	author : 통계포털  황기연       
	
	    date      author             note  
	 ----------   -------     ------------------- 
	 2011.10.04    황기연                       최초 생성                 
	************************************************************************/ 
	--%>
	function fn_gpki(){
	<c:choose>
		<c:when test="${serverSe == 'naraint'||serverSe == 'naradev'}">
			Login(popForm);
		</c:when>
		<c:otherwise>
			alert('이용할 수 없습니다');
            return false;
		</c:otherwise>
	</c:choose>
	}
	
</script>
<noscript>현재 브라우저에서 자바스크립트 처리를 하지 못합니다.</noscript>
<%-- page javascript end	==================================================--%>
<!-- subpagebody_contents start -->
<div id="subpagebody_contents">
	<!-- contents start -->
	<div class="agreementprocess">
		<div class="agreementflow">
			<ul>
				<li><img src="/images/gps/contents/member/member_im_01_off.gif" alt="약관동의" title="약관동의"></li>
				<li><img src="/images/gps/contents/member/member_im_02_on.gif" alt="본인확인" title="본인확인"></li>
				<li><img src="/images/gps/contents/member/member_im_03_off.gif" alt="회원정보입력" title="회원정보입력"></li>
				<li><img src="/images/gps/contents/member/member_im_04_off.gif" alt="회원가입완료" title="회원가입완료"></li>
			</ul>
		</div>
	</div>
		<!-- content start -->
		<form:form commandName="userRegisterVO" method="post">
		<input type="submit" class="hidden" />
		<form:hidden path="leftMenuId"/>
		<form:hidden path="menuId"/>
		</form:form>
        <div class="realName">
	        <div class="realName_subtitle">본인확인</div>
	        <div class="realNameConfirm">
		       	<div class="identificationContents">
		       		<div class="identification1">
		       			<form id="form1" name="form1" method="post">
						<input type="submit" class="hidden" />
						<input type="hidden" name="reqInfo" value="<c:out value="${reqInfo}"/>">
						<input type="hidden" name="retUrl" value="<c:out value="${retUrl}"/>">
		       			<p><a href="#LINK" onclick="openCBAWindow();return false;" onkeypress="this.onclick();return false;"><img src="/images/gps/contents/member/identification_bt01.gif" alt="실명확인" title="실명확인"></a></p>
		       			<ul>
		       				<li>개인정보보호를 위하여 가상식별 실명확인 서비스를 실시하고 있습니다.</li>
		       				<li class="bluetext">가상식별 실명확인서비스란?</li>
		       				<li>주민번호를 해당웹사이트에 제공하지 않는 주민번호 수집제한 실명확인서비스</li>
		       				<li class="bluetext">실명확인이 안되시는 경우</li>
		       				<li>서울신용평가정보의 개인실명등록센터를 이용하여 실명등록 하세요.</li>
		       				<c:if test="${serverSe == 'naraext'}">
		       					<li class="linktext"><a href="http://www.sci.co.kr" target="_blank">바로가기</a></li>
		       				</c:if>
		       			</ul>
		       			</form>
		       		</div>
		       		<div class="identification2">
		       			<p><a href="#LINK" onclick="fn_iPinConfirm();return false;" onkeypress="this.onclick();return false;"><img src="/images/gps/contents/member/identification_bt02.gif" alt="공공아이핀" title="공공아이핀"></a></p>
		       			<ul>
		       				<li>공공아이핀(I-PIN)은 인터넷상의 개인식별 번호를 의미하며, 대면확인이 어려운 인터넷에서 주민등록번호를 사용하지 않고도 본인임을 확인할 수 있는 수단입니다.</li>
		       				<li class="orangetext">공공아이핀 발급받기</li>
		       				<li>콜센터 : 02-3279-3480 </li>
		       				<c:if test="${serverSe == 'naraext'}">
		       					<li class="linktext"><a href="http://www.g-pin.go.kr" target="_blank">바로가기</a></li>
		       				</c:if>
		       			</ul>
		       		</div>
		       		<div class="identification3">
		       			<form name="popForm" method="post" action="/gps/user/gpsGpkiConfirm.do">
						<input type="submit" class="hidden" />
						<input type="hidden" name="challenge" value="<c:out value="${challenge}" escapeXml="true"/>"/>
						<input type="hidden" name="returnUrl" value="<c:out value="${userRegisterVO.returnUrl}" escapeXml="true"/>"/>
						<input type="hidden" name="menuId" value="<c:out value="${userRegisterVO.menuId}" escapeXml="true"/>"/>
						<input type="hidden" name="leftMenuId" value="<c:out value="${userRegisterVO.leftMenuId}" escapeXml="true"/>"/>
						<input type="hidden" name="bbsId" value="<c:out value="${userRegisterVO.bbsId}" escapeXml="true"/>"/>
						<input type="hidden" name="bbsSn" value="<c:out value="${userRegisterVO.bbsSn}" escapeXml="true"/>"/>
		       			<p><a href="#LINK" onclick="fn_gpki();"><img src="/images/gps/contents/member/identification_bt03.gif" alt="GPKI인증" title="GPKI인증"></a></p>
		       			<ul>
		       				<li class="greentext">행정전자서명 인증서란? </li>
		       				<li>“행정전자서명 인증서”라 함은 행정전자서명이 진정한 것임을 확인ㆍ증명할 수 있도록 하기 위하여 행정기관, 보조기관, 보좌기관, 전자문서유통 및 행정정보 공공이용, 공공기반, 은행 또는 사용자에게 발급하는 전자적 정보를 말합니다.</li>
		       			</ul>
		       		</form>
		       		</div>
		       	</div>
	        </div>
	    </div>
        <!-- content end -->
	<!-- contents_area end -->
</div>
<jsp:include page="/gps/cmm/footer.do" flush="false"/>