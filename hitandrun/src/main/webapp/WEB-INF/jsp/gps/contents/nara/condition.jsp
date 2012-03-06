<%--
********************************************************************************
* @source      : condition.jsp
* @description : 국내통계현황 JSP
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
	<div class="nation_report">
		<h3><img src="/images/pms/main/nation_title.gif" alt="국가승인통계현황" title="국가승인통계현황"/></h3>
		<table class="nation">
		<thead>
		<tr>
			<th rowspan="2">구분</th>
			<th rowspan="2">작성기관수</th>
			<th rowspan="2">작성통계수</th>
			<th colspan="2">종류별</th>
			<th colspan="3">작성방법별</th>
		</tr>
		<tr>
			<th class="stat">지정</th>
			<th class="stat">일반</th>
			<th class="stat">조사</th>
			<th class="stat">보고</th>
			<th class="stat">가공</th>
		</tr>
		</thead>
		<tbody>
<c:forEach var="list" items="${resultList}" varStatus="status">
<c:choose>
	<c:when test="${status.index == 0}">
		<tr>
			<td class="total_text">${list.orgSe}</td>
			<td class="total_cnt">${list.writngInsttCnt}</td>
			<td class="total_cnt">${list.writngStatsCnt}</td>
			<td class="total_cnt">${list.statsKndCnt1}</td>
			<td class="total_cnt">${list.statsKndCnt2}</td>
			<td class="total_cnt">${list.writngStleCnt1}</td>
			<td class="total_cnt">${list.writngStleCnt2}</td>
			<td class="total_cnt">${list.writngStleCnt3}</td>
		</tr>
	</c:when>
	<c:when test="${fn:length(resultList) eq status.index+1}">
		<tr>
			<td class="total_text">${list.orgSe}</td>
			<td class="total_cnt">${list.writngInsttCnt}</td>
			<td class="total_cnt">${list.writngStatsCnt}</td>
			<td class="total_cnt">${list.statsKndCnt1}</td>
			<td class="total_cnt">${list.statsKndCnt2}</td>
			<td class="total_cnt">${list.writngStleCnt1}</td>
			<td class="total_cnt">${list.writngStleCnt2}</td>
			<td class="total_cnt">${list.writngStleCnt3}</td>
		</tr>
	</c:when>
	<c:otherwise>
		<tr>
			<td class="total_text">${list.orgSe}</td>
			<td class="total_cnt">${list.writngInsttCnt}</td>
			<td class="total_cnt">${list.writngStatsCnt}</td>
			<td class="total_cnt">${list.statsKndCnt1}</td>
			<td class="total_cnt">${list.statsKndCnt2}</td>
			<td class="total_cnt">${list.writngStleCnt1}</td>
			<td class="total_cnt">${list.writngStleCnt2}</td>
			<td class="total_cnt">${list.writngStleCnt3}</td>
		</tr>
	</c:otherwise>
</c:choose>
</c:forEach>
		</tbody>
		</table>
	</div>
	<!-- contents end -->
</div>
<jsp:include page="/gps/cmm/footer.do" flush="false"/>