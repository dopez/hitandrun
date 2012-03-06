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

<%-- 
/************************************************************************ 
fnc name : link_page                                   
outline : 페이지 이동 함수
		조회된 목록에서 다른 페이지 번호를 누르면 해당 페이지로 이동
parameter : pageNo        
directions : link_page(pageNo)              
since : 2011-09-10   
author : 기술지원 김병욱       

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function link_page(pageNo){
	document.listForm.pageIndex.value = pageNo;
	JQ_setProcessMsg();
	JQ_request("searchVO", "<c:url value='/sample/tem/selectTemplateList.do'/>");
}

-->
</script>
<div id="subpagebody_contents">
<form:form commandName="searchVO" name="listForm" method="post">
<table class="default">
<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	<tr>
		<td>${resultInfo.svyNm}</td>
		<c:choose>
			<c:when test="${resultInfo.schdulBeginDt ne null}">
				<td>${resultInfo.schdulBeginDt}</td>
			</c:when>
			<c:otherwise>
				<td>image</td>
			</c:otherwise>
		</c:choose>
		<td>~</td> 
		<c:choose>
			<c:when test="${resultInfo.schdulEndDt ne null}">
				<td>${resultInfo.schdulEndDt}</td>
			</c:when>
			<c:otherwise>
				<td>image</td>
			</c:otherwise>
		</c:choose>
	</tr>
</c:forEach>
</table>
</form:form>
</div>
<jsp:include page="/gps/cmm/footer.do" flush="false"/>