<%--
********************************************************************************
* @source      : service.jsp
* @description : 서비스안내 JSP
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
	<div class="serviceInformation">
		<p><img src="/images/gps/contents/guidance/sub_service.gif" alt="새로운 변화가 요구되는 시대에 나라통계의 거듭나는 모습을 지켜봐 주십시요." title="새로운 변화가 요구되는 시대에 나라통계의 거듭나는 모습을 지켜봐 주십시요."></p>
		<div class="service_subtitle">나라통계이란?</div>
		<div class="serviceCn">국가승인통계들을 생산하는 시스템으로 <span class="sv_bluetxt">통계작성 전 과정을 표준화하여 통계작성기관이 공동으로 활용할 수 있는 통계정보시스템 구축</span> 입니다.
			<p class="brLine2">통계품질을 제고하고 저비용 고효율의 통계생산 관리체계 확립 합니다.</p>				
			<p class="brLine2">통계정책(통계조정,품질관리 등)과 통계정보시스템을 연계하여 업무처리 과정을 모니터링하고 정보를 공유할 수 있는 체계로 운영하고 있습니다.</p>
			<p class="brLine2">통계생산에 있어 쉽고 빠르고 편리하게 통계생산에 이용할 수 있도록 계속 노력할 것입니다. </p>
		</div>
	</div>
	<!-- contents end -->
</div>
<jsp:include page="/gps/cmm/footer.do" flush="false"/>