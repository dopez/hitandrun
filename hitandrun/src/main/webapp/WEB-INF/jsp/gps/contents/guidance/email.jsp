<%--
********************************************************************************
* @source      : email.jsp
* @description : 이메일무단수집거부 JSP
*                   
********************************************************************************
* DATE         AUTHOR    DESCRIPTION
*===============================================================================
* 2011-08-01   박통계           최초작성
********************************************************************************
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%-- 공통 taglib 선언 start	=================================================--%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<%-- 공통 taglib 선언 end	=====================================================--%>

<%-- header start	==========================================================--%>
<jsp:include page="/gps/cmm/left.do" flush="false"/> 
<%-- header end	==============================================================--%>
<%-- 선행 로직 start	======================================================--%>
<script type="text/javascript">
JQ_onload();

function fncPageOnload() {
	topStatMenuImage(0);
	subTopStatMenuImage(0);
	leftStatMenuImage(0);
}
</script>
<%-- 선행 로직 end	==========================================================--%>

<%-- page javascript start	==================================================--%>
<%-- page javascript end	==================================================--%>
<!-- login_contents contetns start -->
<!-- subpagebody_contents start -->
<div id="subpagebody_contents">
	<!-- contents start -->
	<div class="emailInformation">
		<p><img src="/images/gps/contents/guidance/sub_email.gif" alt="본 웹사이트에 게시된 이메일 주소가 전자우편 수집 프로그램이나 그 박의 기술적 장치를 이용하여 무단으로 수집된 것을 거부하며, 이를 위반시 [정보통신망이용촉진및정보보호등에관한법률]에 의해 형사처벌됨을 유념하시길 바랍니다." title="본 웹사이트에 게시된 이메일 주소가 전자우편 수집 프로그램이나 그 박의 기술적 장치를 이용하여 무단으로 수집된 것을 거부하며, 이를 위반시 [정보통신망이용촉진및정보보호등에관한법률]에 의해 형사처벌됨을 유념하시길 바랍니다."></p>
		
		<div class="confirm_subtitle">정보통신망 이용촉진 및 정보보호 등에 관한 법률</div>
		<div class="emailtCn">
			<ul>
				<li class="pointerKey">제50조의2 (전자우편주소의 무단 수집행위 등 금지)</li>
				<li>① 누구든지 전자우편주소의 수집을 거부하는 의사가 명시된 인터넷 홈페이지에서 자동으로 전자우편주소를 수집하는 프로그램 그 밖의 기술적 장치를 이용하여 전자우편주소를 수집하여서는아니된다.<개정 2004. 12. 30></li> 
				<li>②누구든지 제 1항의 규정을 위반하여 수집된 전자우편주소를 판매ㆍ유통하여서는 아니된다.</li> 
				<li>③누구든지 제 1항 및 제 2항의 규정에 의하여 수집ㆍ판매ㆍ유통이 금지된 전자우편주소임을 알고 이를 정보전송에 이용하여서는 아니된다.[본조신설 2002. 12. 18]</li> 
			</ul>
		</div>
	</div>
	<!-- contents end -->
</div>
<jsp:include page="/gps/cmm/footer.do" flush="false"/>