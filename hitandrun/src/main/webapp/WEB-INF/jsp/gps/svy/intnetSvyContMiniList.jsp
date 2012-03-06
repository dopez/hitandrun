<%-- 
/** 
 * outline   : 인터넷조사 컨텐츠 목록 조회화면
 * filename : intnetSvyContList.jsp
 * @author 기술지원 김병욱 
 * @since 2011.09.10 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.09.10     김병욱           최초 생성 
 * </pre> 
 */
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<script type="text/javaScript" language="javascript">
</script>
<form:form commandName="systemVO" method="post">
<input type="submit" class="hidden">
	<ul>
<c:set var="titleLimit" value="25"/>
<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	<a href="<c:url value="/survey/index.do"><c:param name="prdctnId" value="${resultInfo.prdctnId}"/><c:param name="svyOdr" value="${resultInfo.svyOdr}"/></c:url>" target="_blank">
	<c:choose>
		<c:when test="${fn:length(resultInfo.svyNm) > titleLimit}">
			<li><span class="svyTitile" title="${resultInfo.svyNm} - ${resultInfo.schdulBeginDt}~${resultInfo.schdulEndDt}">${fn:substring(resultInfo.svyNm,0,titleLimit)}...</span></li>
			<!-- <li><span class="date">${resultInfo.schdulBeginDt}~${resultInfo.schdulEndDt}</span></li> -->
			<!-- 
			<c:choose>
				<c:when test="${resultInfo.beforeBgn eq Y}">
					진행전 image
				</c:when>
				<c:otherwise>
					<span class="ingicon"><img src="/images/gps/cmm/icon/ing.gif" alt="진행중" title="진행중"/></span>
				</c:otherwise>
			</c:choose>
			 -->
		</c:when>
		<c:otherwise>
			<li><span class="svyTitile" title="${resultInfo.svyNm} - ${resultInfo.schdulBeginDt}~${resultInfo.schdulEndDt}">${resultInfo.svyNm}</span></li>
			<!-- <li><span class="date">${resultInfo.schdulBeginDt}~${resultInfo.schdulEndDt}</span></li> -->
			<!-- 
			<c:choose>
				<c:when test="${resultInfo.beforeBgn eq Y}">
					진행전 image
				</c:when>
				<c:otherwise>
					<span class="ingicon"><img src="/images/gps/cmm/icon/ing.gif" alt="진행중" title="진행중"/></span>
				</c:otherwise>
			</c:choose>
			-->
		</c:otherwise>
	</c:choose>
	</a>
</c:forEach>
	</ul>
</form:form>