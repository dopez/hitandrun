<%--
********************************************************************************
* @source      : explanation.jsp
* @description : 통계설명자료 JSP
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
	<div class="explanation">
		<div class="d_above">
			<dl>
				<dt>통계 설명 메타</dt>
				<dd class="pl20">
					<ul>
						<li>통계청을 포함한 375개 통계작성기관에서 생산되는 836종 국가승인통계에 대한 설명정보 및 관련 자료 메타</li>
						<li class="pl15">- 내용 : 통계조사의 목적,조사주기 등 설명자료(조사명, 조사대상/주기/방법 등)</li>
						<li class="pl15">- 초기자료 : 정책관리DB(77개 항목), KOSIS(124개 항목)의 통계설명자료 및 중복항목(24개 항목) 일원화</li>
					</ul>
				</dd>
				<dt>통계 생산 메타</dt>
				<dd class="pl20">
					<ul>
						<li>통계 생산 시 필요한 조사표/집계표 설계 내용을 참조할 수 있도록 항목정보를 구조화 하고, 항목간 관계정보를  저장한 메타</li>
						<li class="pl15">- 내용 : 조사표/조사항목, 내검, 집계표/집계항목 및 항목간 매핑정보</li>
						<li class="pl15">- 초기자료 : 범정부통계생산시스템의 생산DB 내 설계정보</li>
					</ul>
				</dd>
				<dt>표준화 지원 메타</dt>
				<dd class="pl20">
					<ul>
						<li>통계조사에서 발생하는 항목정보의 분석을 통하여, 유사 및 중복 항목에 대한 관리를 지원하기 위한 메타</li>
						<li>- 내용 : 코드항목, 보기항목, 대표항목</li>
						<li>- 초기자료 : 홈페이지의 표준분류코드, KOSIS의 표준화코드, 행정표준용어</li>
					</ul>
				</dd>
			</dl>
		</div>
	</div>
	<!-- contents end -->
</div>
<jsp:include page="/gps/cmm/footer.do" flush="false"/>