<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<%-- 
/** 
 * outline   : 게시글목록
 * filename : bbsList.jsp
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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>통계정책시스템</title>
<%-- stylesheet Start	======================================================--%>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/common/jquery/validationEngine.jquery.css'/>" media="screen" title="no title" charset="utf-8" />
<link href="<c:url value='/css/sample/ccm/button.css' />" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/css/common/jquery/jquery.ui.all.css" />
<link rel="stylesheet" type="text/css" href="/css/common/jquery/layout-default-latest.css" />
<link rel="stylesheet" type="text/css" href="/css/common/jquery/jquery.ui.theme.css" />
<link rel="stylesheet" type="text/css" href="/css/common/jquery/validationEngine.jquery.css" media="screen" title="no title" charset="utf-8" /> <!-- kms add -->
<link rel="stylesheet" type="text/css" href="/css/pms/policycss.css" />

<link rel="stylesheet" type="text/css" href="/css/gps/cmm/default.css"></link>
<link rel="stylesheet" type="text/css" href="/css/gps/cmm/table.css"></link>
<%-- stylesheet End	========================================================--%>

<%-- javascript start ==============================================--%>
<script type="text/javascript" src="/js/common/NaraCommon.js"></script>
<script type="text/javascript" src="/js/common/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/common/jquery/jquery.ui.core.js"></script>
<script type="text/javascript" src="/js/common/jquery/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/js/common/jquery/jquery.validationEngine-ko.js"></script>
<script type="text/javascript" src="/js/common/jquery/jquery.validationEngine.js"></script>
<script type="text/javascript" src="/js/common/jquery/NaraJQuery.js"></script>
<script type="text/javascript" src="/js/common/jquery/jquery.ui.widget.js"></script>
<script type="text/javascript" src="/js/common/jquery/jquery.popupWindow.js"></script>  <!-- kms add -->
<script type="text/javascript" src="<c:url value='/js/pms/pmsCommon.js'/>"></script>
<%-- javascript end ============================================--%>

<script type="text/javascript">
JQ_onload();
<%-- 
/************************************************************************ 
fnc name : fncPageOnload                                   
outline : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
parameter : 없음        
directions : fncPageOnload()              
since : 2011-07-05   
author : 통계포털  황기연       

    date      author             note  
 ----------   -------     ------------------- 
 2011.06.10    황기연                                          
************************************************************************/ 
--%>
function fncPageOnload(){
	//메시징처리
	<c:if test="${!empty message}">
		alert('<c:out value = "${message}"/>');
	</c:if>
}

<%-- 
/************************************************************************ 
함수명 : link_page                                   
설   명 : 페이지 이동 함수
		조회된 목록에서 다른 페이지 번호를 누르면 해당 페이지로 이동
인   자 : pageNo        
사용법 : link_page(pageNo)              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 황기연       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function link_page(pageNo){
	$('#pageIndex').val(pageNo);
	JQ_request("bbsSearchVO", "<c:url value='/gps/bbs/selectBbsList.do'/>","bbsSearchVO");
}

<%--
/************************************************************************ 
함수명 : fn_search                                   
설   명 : 조회조건에 따라 목록을 조회하는 함수
인   자 : 없음(form안에 존재하는 값)
사용법 : fn_search()              
작성일 : 2011-09-15
작성자 : 범정부통계포털 황기연       

      date    author      note 
 ----------   -------     ------------------- 
2011-09-15 	  황기연
 ************************************************************************/
 --%>
function fn_search(){
	$('#pageIndex').val(1);
   	JQ_request("bbsSearchVO", "<c:url value='/gps/bbs/selectBbsList.do'/>", "bbsSearchVO");
}

<%--
/************************************************************************ 
함수명 : fn_detail                               
설   명 : 게시글상세보기
인   자 : bbsSn
사용법 : fn_detail()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 황기연       

      date    author      note 
 ----------   -------     ------------------- 
2011-09-15 	  황기연
 ************************************************************************/
 --%>
function fn_detail(bbsSn){
	$('#bbsSn').val(bbsSn);
	JQ_request("bbsSearchVO", "<c:url value='/gps/bbs/selectBbsDetail.do'/>", "bbsSearchVO");
}
<%-- 
/************************************************************************ 
fnc name : fn_register                      
outline : 게시글등록화면호출
parameter : 없음        
directions : fn_register()              
since : 2011-09-15    
author : 통계포털  황기연       

    date      author             note  
 ----------   -------     ------------------- 
 2011-09-15    황기연                                          
************************************************************************/ 
--%>
function fn_register(){
	$('#bbsSn').val(0);
	JQ_setProcessMsg();
	JQ_request("bbsSearchVO", "<c:url value='/gps/bbs/registerBbs.do'/>", "bbsSearchVO");
}

</script>

<body>
<!--PolicyWrapper start-->
<div id="PolicyWrapper">
	<!--wrap start-->
	<div id="wrap">

	<!-- PageLeft start -->
	<div id="PageLeft"></div>
	<!-- PageLeft end -->

	<!-- Pagewrapper start -->
	<div id="Pagewrapper">

	<%-- top Start ==========================================================--%>
	<jsp:include page="/pms/common/top.do" flush="false"/> 
	<%-- top End ==========================================================--%>

	<!-- Container start -->
	<div id="Container">
	
	<%-- left Start ==========================================================--%>
	<jsp:include page="/pms/common/left.do" flush="false"/> 
	<%-- left End ==========================================================--%>

	<!-- Contents_Area start -->
	<div id="Contents_Area">

	<%-- navi Start ==========================================================--%>
	<jsp:include page="/pms/common/navi.do" flush="false"/> 
	<%-- navi End ==========================================================--%>

		<div id="Contents">
		
		<p class="total"><span>Total:</span> <c:out value='${paginationInfo.totalRecordCount}'/> &nbsp;&nbsp;&nbsp;   <span>Page:</span> <c:out value='${paginationInfo.currentPageNo}'/> / <c:out value='${paginationInfo.totalPageCount }'/> </p>
		
		<form:form commandName="bbsSearchVO" method="post">
		<input type="submit" class="hidden" />
			<form:hidden path="bbsId"/>
			<form:hidden path="bbsSn"/>
			<form:hidden path="pageIndex"/>
			<form:hidden path="menuId"/>
			<form:hidden path="leftMenuId"/>
			<table class="Notice" summary="번호,분류,제목,파일,작성자,조회,등록일">
			<caption>공지사항</caption>
			<colgroup>
			    <col width="70" />
			    <c:if test="${bbsInfoVO.ctgryCodeUseAt eq 'Y'}">
			   		<col width="80" />
			   	</c:if>
			    <col width="290" />
			    <c:if test="${bbsInfoVO.fileUploadUseAt eq 'Y'}">
			    	<col width="50" />
			    </c:if>
			    <col width="70" />
			    <col width="70" />
			    <col width="80" />
			</colgroup>
			 <thead>
			  <tr>
			    <th class="le">번호</th>
			    <c:if test="${bbsInfoVO.ctgryCodeUseAt eq 'Y'}">
			    	<th><span class="num">분류</span></th>
			    </c:if>
			    <th>제목</th>
			    <c:if test="${bbsInfoVO.fileUploadUseAt eq 'Y'}">
					<th>파일</th>
				</c:if>
				<th>작성자</th>
				<th>조회</th>
			    <th class="ri">등록일</th>
			  </tr>
			 </thead>
			 <tfoot>
			 <tr>
			    <td colspan="8" class="Paginate footnone">
					<ui:pagination paginationInfo = "${paginationInfo}"	type="image" jsFunction="link_page" />
				</td>
			  </tr>
			  </tfoot>
			
			 <tbody>
			 <c:forEach var="list" items="${bbsList}" varStatus="status">
				  <tr>
				    <td>
						<c:choose>
							<c:when test="${bbsInfoVO.noticeUseAt eq 'Y'}">
								<c:choose>
									<c:when test="${status.count <= bbsInfoVO.noticeCo}">
										<img src="/images/gps/board/default_news.gif" alt="공지사항" title="공지사항" />
									</c:when>
									<c:otherwise>
										<c:out value="${paginationInfo.totalRecordCount - ((bbsSearchVO.pageIndex - 1) * bbsSearchVO.pageSize + (status.count-1))}"/>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:out value="${paginationInfo.totalRecordCount - ((bbsSearchVO.pageIndex - 1) * bbsSearchVO.pageSize + (status.count-1))}"/>
							</c:otherwise>
						</c:choose>
				    </td>
				    <c:if test="${bbsInfoVO.ctgryCodeUseAt eq 'Y'}">
						<td><c:out value='${codeNm}'/></td>
					</c:if>
				    <td class="ALL">
						<c:if test="${list.bbsLevel > 1}">
							<c:forEach var="depth" begin="1" end="${list.bbsLevel}" step="1" varStatus="status">
								<c:out value="&nbsp;&nbsp;" escapeXml="false"/>
								<c:if test="${status.count == list.bbsLevel}"><c:out value="└" escapeXml="false"/></c:if>
							</c:forEach>
						</c:if>
						<c:if test="${list.newIconUseAt eq 'Y' && !empty bbsInfoVO.newIconImage}">
								<img src="<c:url value="/cmm/fms/getImage.do" ><c:param name="atchFileId" value="${bbsInfoVO.newIconImage}"/><c:param name="fileSn" value="0"/></c:url>" alt="new" title="new"/>
						</c:if>
						<c:if test="${list.hotIconUseAt eq 'Y' && !empty bbsInfoVO.hotIconImage}">
								<img src="<c:url value="/cmm/fms/getImage.do" ><c:param name="atchFileId" value="${bbsInfoVO.hotIconImage}"/><c:param name="fileSn" value="0"/></c:url>" alt="hot" title="hot"/>
						</c:if>
						<c:if test="${list.coolIconUseAt eq 'Y' && !empty bbsInfoVO.coolIconImage}">
								<img src="<c:url value="/cmm/fms/getImage.do" ><c:param name="atchFileId" value="${bbsInfoVO.coolIconImage}"/><c:param name="fileSn" value="0"/></c:url>" alt="cool" title="cool"/>
						</c:if>
						<a href="#LINK" onclick="fn_detail('<c:out value="${list.bbsSn}"/>');return false;" onkeypress="this.onclick();return false;"><c:out value="${list.sj}"/></a>
				    </td>
				    <c:if test="${bbsInfoVO.fileUploadUseAt eq 'Y'}">
				    	<td><c:if test="${!empty list.atchmnflId}"><img src="/images/pms/common/btn_file.gif" alt="파일" /></c:if></td>
				    </c:if>
					<td><c:out value="${list.wrterNm}"/></td>
					<td><c:out value="${list.co}"/></td>
					<td><c:out value="${list.registDt}"/></td>
				  </tr>
			 </c:forEach>
			<c:if test="${empty bbsList}">
				<tr> 
					<td align="center" colspan="8">
						<spring:message code="common.search.nodata.msg" />
					</td>
				</tr>   	          				 			   
			</c:if>
			 </tbody>
			</table>
			<p class="buttonSet fr">
				<c:if test="${writeButtonUseAt}">
					<span class="btn_pack bt03"><button type="button" style="cursor:hand;" onclick="fn_register();return false;" onkeydown="this.onclick();return false;">글쓰기</button></span>
				</c:if>          
			</p>  
			 <div class="NotiSC">
			 <label for="Part2" class="Hidden">선택하세요</label>
				<form:select path="searchCondition" id="searchCondition" title="검색조건">
					<form:option value="">- 선택 -</form:option>
					<form:option value="1">제목</form:option>
					<form:option value="2">내용</form:option>
					<form:option value="3">작성자</form:option>
				</form:select>
				<form:input path="searchKeyword" cssClass="wi150" maxlength="35" id="searchKeyword" title="검색 키워드" onkeypress="if(event.keyCode==13){fn_search();return false;}"/>
				<img src="/images/pms/common/btn_noti_search.gif" alt="<spring:message code='button.search'/>" title="<spring:message code='button.search'/>" style="cursor:hand;" onclick="fn_search()"/>
			</div>
		</form:form>
		</div>
	</div>
	<!-- //Contents_Area  -->
	
	<%-- footer Start ==========================================================--%>
	<jsp:include page="/pms/common/footer.do" flush="false"/> 
	<%-- footer End ==========================================================--%>
	
	  </div>
	  <!-- Container end -->
	</div>
	<!-- Pagewrapper end -->
</div>
<!--wrap end-->
</div>
<!--PolicyWrapper end-->
</body>
</html>