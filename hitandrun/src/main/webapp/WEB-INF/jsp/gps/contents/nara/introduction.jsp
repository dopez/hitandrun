<%--
********************************************************************************
* @source      : introduction.jsp
* @description : 나라통계시스템이란  JSP
*                   
********************************************************************************
* DATE         AUTHOR    DESCRIPTION
*===============================================================================
* 2011-08-01   박통계           최초작성
********************************************************************************
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

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
	<div class="introducation">
		<p class="title"><img src="/images/gps/contents/introduce/im_06.gif" alt="통계기획에서 생산ㆍ자료관리까지 일련의 과정을 일원화하여  국가통계 생산 및 관리체계의 표준화로 통계품질 제고 합니다." title="통계기획에서 생산ㆍ자료관리까지 일련의 과정을 일원화하여  국가통계 생산 및 관리체계의 표준화로 통계품질 제고 합니다."></p>
		<div class="d_above">
			<dl>
				<dt>목적</dt>
				<dd class="pl20">
					<ul>
						<li>통계작성 전 과정을 표준화하여 통계작성기관이 공동으로 활용할 수 있는 통계정보시스템 구축으로 통계품질을 제고하고 저비용 고효율의 통계생산 관리체계 확립</li>
						<li class="pl15">- 표준화된 통계시스템의 공동 활용에 따른 통계품질 향상 및 국가예산 절감</li>
						<li class="pl15">- 범용통계정보시스템 구축으로 국가자산의 효율적 관리 및 업무생산성 향상</li>
						<li class="pt10"><img src="/images/gps/contents/introduce/im_05.gif" alt="통계기획,생산,분석, 서비스,자료관리를 한번에 처리합니다." title="통계기획,생산,분석, 서비스,자료관리를 한번에 처리합니다."></li>
					</ul>
				</dd>
				<dt>추진 배경</dt>
				<dd class="pl20">
					<ul>
						<li>- 통계생산 및 관리 프로세스 비표준화로 업무효율성 저하</li>
						<li>- 분산형 국가통계체계하에서 통계조사별 시스템 개발로 인한 예산낭비 초래</li>
						<li>- 열악한 통계작성기관의 통계 생산활동 지원을 위한   통계청의 허브역할 요구</li>
						<li>- 정보화ㆍ지식화시대 도래에 따른 다양한 통계정보   요구증대에 대응할 수 있는 통계생산체계 구축 요구</li>
					</ul>
				</dd>
				<dt>필요성</dt>
				<dd class="pl20">
					<ul>
						<li>- 통계 기획에서 생산․서비스․자료보관까지 통계작성과정을 표준화하여 국가통계 품질제고</li>
						<li>- 새로운 정책 수요에 필요한 통계를 신속하고 효율적인 방법으로 생산할 수 있는 지원체계 마련</li>
						<li>- 공동 활용할 수 있는 시스템 구축으로 통계시스템의 기관별 조사별 개별 구축과 유지보수로 인한 국가적 부담 최소화</li>
						<li>- 통계정책(통계조정,품질관리 등)과 통계정보시스템을 연계하여 업무처리 과정을 모니터링하고 정보를 공유할 수 있는 체계 마련</li>
					</ul>
				</dd>
			</dl>
		</div>
	</div>
	<!-- contents end -->
</div>
<jsp:include page="/gps/cmm/footer.do" flush="false"/>
