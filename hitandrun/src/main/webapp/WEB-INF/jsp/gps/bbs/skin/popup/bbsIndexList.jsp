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
<script type="text/javascript" src="/js/common/jquery/jquery.popupWindow.js"></script>
<script type="text/javascript" src="/js/common/NaraCommon.js"></script>
<script type="text/javascript">
<%-- 
/************************************************************************ 
fnc name : fn_bbsDetail                                   
outline : 게시글상세보기 팝업호출      
parameter : 없음        
directions : fn_bbsDetail()              
since : 2011-07-05   
author : 통계포털  황기연       

    date      author             note  
 ----------   -------     ------------------- 
 2011.06.10    황기연                                          
************************************************************************/ 
--%>
function fn_bbsDetail(sysId,bbsId,bbsSn){
	$('#sysId').val(sysId);
	$('#bbsId').val(bbsId);
	$('#bbsSn').val(bbsSn);
	gfn_postPopupWin("bbsVO","<c:url value='/gps/bbs/selectBbsDetail.do'/>","게시글상세보기",700,720, "yes","no",0);
}
</script>
<form:form commandName="bbsVO">
		<input type="submit" class="hidden">
	<form:hidden path="sysId"/>
	<form:hidden path="bbsId"/>
	<form:hidden path="bbsSn"/>
</form:form>
<ul>
	<c:set var="titleLimit" value="30"/>
	<c:forEach var="list" items="${bbsList}" varStatus="status">
		<li>
			<span><c:out value="${list.registDt}"/></span>
			<a href="#LINK" onclick="fn_bbsDetail('<c:out value="${list.sysId}"/>','<c:out value="${list.bbsId}"/>','<c:out value="${list.bbsSn}"/>');return false;" onkeypress="this.onclick();return false;">
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
