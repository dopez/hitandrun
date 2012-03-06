<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<%-- 
/** 
 * outline   : 메인페이지 게시글
 * filename : bbsIndexList.jsp
 * @author 통계포탈 황기연 
 * @since 2011.09.15
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == 개정이력(Modification Information) == 
 *   
 *   date        author     note 
 * ----------    -------    --------------------------- 
 * 2011.09.15     황기연           최초 생성 
 * </pre> 
 */
--%>
<ul>
	<c:set var="titleLimit" value="30"/>
	<c:forEach var="list" items="${bbsList}" varStatus="status">
		<li>
			<span><c:out value="${list.registDt}"/></span>
			<a href="<c:url value='/gps/bbs/selectBbsDetail.do'>
						<c:param name="sysId" value="${list.sysId}"/>
						<c:param name="bbsId" value="${list.bbsId}"/>
						<c:param name="bbsSn" value="${list.bbsSn}"/>
						<c:param name="menuId" value="${list.menuId}"/>
						<c:param name="leftMenuId" value="${list.menuId}"/>
					</c:url>">
				<c:choose>
					<c:when test="${fn:length(list.sj) > titleLimit}">
						<c:out value="${fn:substring(list.sj,0,titleLimit)}"/>...
					</c:when>
					<c:otherwise>
						<c:out value="${list.sj}"/>
					</c:otherwise>
				</c:choose>
			</a>
		</li>
	</c:forEach>
</ul>
