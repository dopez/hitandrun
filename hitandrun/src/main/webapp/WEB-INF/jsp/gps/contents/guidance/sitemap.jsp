<%--
********************************************************************************
* @source      : sitemap.jsp
* @description : 사이트맵 JSP
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
	<c:set var="count" value="0"/>
	<table class="default" summary="나라통계포털 사이트 맵">
	<tr>
		<td>
			<table class="gpssitemap" summary="나라통계포털 사이트 맵">
			<caption>나라통계포털 사이트 맵</caption>
		<c:out value="<tr><td><table class='inner'>" escapeXml="false"/>
		<c:forEach var="list" items="${sitemapList}" varStatus="status">
			<tr>
				<td class="<c:out value="${list.menuLv == 1?'title':''}" escapeXml="false"/>">
					<span>
						<c:if test="${list.menuLv > 1}">
						<a href="<c:url value="${list.menuUrl}">
								<c:param name="menuId" value="${list.menuId}"/>
								<c:param name="leftMenuId" value="${list.menuTy == 'F'?list.menuId : list.upperMenuId}"/>
								<c:param name="bbsId" value="${list.bbsId}"/>
							</c:url>">
						</c:if>
						<c:choose>
							<c:when test="${list.menuUrl == '/gps/user/modifyUser.do'}">
								<c:if test="${gpsSessionVO.trnsferInfo != 'U'}">
									<c:out value="${list.menuNm}" escapeXml="false"/>
								</c:if>
							</c:when>
							<c:otherwise>
								<c:out value="${list.menuNm}" escapeXml="false"/>
							</c:otherwise>
						</c:choose>
						<c:if test="${list.menuLv > 1}">
						</a>
						</c:if>
					</span>
				</td>
			</tr>
			<%-- 다음 레벨이 1일때 새테이블 시작--%>
			<c:if test="${list.nextMenuLv == 1}">
				<c:out value="</table></td>" escapeXml="false"/>
				<%-- 3열이끝나면 새로행시작 --%>
				<c:if test="${count%3 == 0}"><c:out value="</tr><tr>" escapeXml="false"/></c:if>
				<c:out value="<td><table class='inner'>" escapeXml="false"/>
			</c:if>
			<%-- 레벨이 1이면 count 1증가 --%>
			<c:if test="${list.menuLv == 1}"><c:set var="count" value="${count+1}"/></c:if>
			<%-- 목록이끝이면 모든 태그닫기--%>
			<c:if test="${fn:length(sitemapList) == status.count}"><c:out value="</table></td></tr>" escapeXml="false"/></c:if>	
		</c:forEach>
			</table>
		</td>
	</tr>
	</table>
	<!-- contents end -->
</div>
<jsp:include page="/gps/cmm/footer.do" flush="false"/>