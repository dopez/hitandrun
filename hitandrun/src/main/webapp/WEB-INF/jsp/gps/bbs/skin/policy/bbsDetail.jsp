<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<%-- 
/** 
 * outline   : 게시글등록페이지
 * filename : registerBbs.jsp
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
JQ_setValidation('bbsMemoVO');
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
	$('#bbsSearchVO').find('#bbsSn').val(bbsSn);
	JQ_request("bbsSearchVO", "<c:url value='/gps/bbs/selectBbsDetail.do'/>", "bbsSearchVO");
}

<%--
/************************************************************************ 
함수명 : fn_list                                   
설   명 : 목록페이지 호출
인   자 : 
사용법 : fn_list()              
작성일 : 2011-09-15
작성자 : 범정부통계포털 황기연       

      date    author      note 
 ----------   -------     ------------------- 
2011-09-15 	  황기연
 ************************************************************************/
 --%>
function fn_list(){
	JQ_setProcessMsg();
   	JQ_request("bbsSearchVO", "<c:url value='/gps/bbs/selectBbsList.do'/>", "bbsSearchVO");
}

<%--
/************************************************************************ 
함수명 : 게시글답글페이지호출                             
설   명 : 게시글답글페이지호출
인   자 : 
사용법 : fn_answer()              
작성일 : 2011-09-15
작성자 : 범정부통계포털 황기연       

      date    author      note 
 ----------   -------     ------------------- 
2011-09-15 	  황기연
 ************************************************************************/
 --%>
function fn_answer(){
	JQ_setProcessMsg();
   	JQ_request("bbsSearchVO","<c:url value='/gps/bbs/registerBbs.do'/>","bbsSearchVO");
}

<%--
/************************************************************************ 
함수명 : 게시글수정페이지호출                             
설   명 : 게시글수정페이지호출
인   자 : 
사용법 : fn_modify()              
작성일 : 2011-09-15
작성자 : 범정부통계포털 황기연       

      date    author      note 
 ----------   -------     ------------------- 
2011-09-15 	  황기연
 ************************************************************************/
 --%>
function fn_modify(){
	JQ_setProcessMsg();
   	JQ_request("bbsSearchVO","<c:url value='/gps/bbs/modifyBbs.do'/>","bbsSearchVO");
}

<%--
/************************************************************************ 
함수명 : 메모글등록                             
설   명 : 메모글등록
인   자 : 
사용법 : fn_list()              
작성일 : 2011-09-15
작성자 : 범정부통계포털 황기연       

      date    author      note 
 ----------   -------     ------------------- 
2011-09-15 	  황기연
 ************************************************************************/
 --%>
function fn_bbsMemoInsert(){
	JQ_setProcessMsg();
   	JQ_request("bbsMemoVO","<c:url value='/gps/bbs/insertBbsMemo.do'/>");
}


<%-- 
/************************************************************************ 
fnc name : fn_memoDelete    
outline :  메모삭제    
parameter : 메모순번
directions : fn_mngrSearch()              
since : 2011-05-10   
author : 통계포털 황기연       

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_memoDelete(memoSn,memoDeletePassword) {
	if (confirm("<spring:message code="common.delete.msg" />")) {
		$('#memoSn').val(memoSn);
		$('#memoDeletePassword').val(memoDeletePassword);
		JQ_setProcessMsg();
	   	JQ_request("bbsMemoVO","<c:url value='/gps/bbs/deleteBbsMemo.do'/>","bbsMemoVO");
	}
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
		
		<form:form commandName="bbsSearchVO" method="post">
		<input type="submit" class="hidden" />
			<form:hidden path="bbsId"/>
			<form:hidden path="bbsSn"/>
			<form:hidden path="pageIndex"/>
			<form:hidden path="searchCtgryCode"/>
			<form:hidden path="searchCondition"/>
			<form:hidden path="searchKeyword"/>
			<form:hidden path="menuId"/>
			<form:hidden path="leftMenuId"/>
		</form:form>
		<%-- 검색조건 form end --%>
		<table class="NotiView"summary="${bbsInfoVO.bbsNm} 게시판">
		<caption>${bbsInfoVO.bbsNm}</caption>
		<colgroup>
		   <col width="80" />
		   <col width="320" />
		   <col width="80" />
		   <col width="220" />
		</colgroup>
		<thead>
		  <tr>
		    <th class="WriTILE">제목</th>
		    <td colspan="3" class="WriTIRI">
				<c:if test="${bbsVO.noticeAt eq 'Y'}">[공지]</c:if>
				<c:out value="${bbsVO.sj}"/>
		    </td>
		    </tr>
		  <tr>
		    <th class="WriBg01">등록자</th>
		    <td><c:out value="${bbsVO.wrterNm}"/></td>
		    <th class="WriBg02">등록일</th>
		    <td class="WriBg03"><c:out value="${bbsVO.registDt}"/></td>
		  </tr>
		  <tr>
		    <th class="WriBg01">이메일</th>
		    <td><c:out value="${bbsVO.email}"/></td>
		    <th class="WriBg02">첨부파일</th>
		    <td class="WriBg03">
				<c:if test="${bbsInfoVO.fileUploadUseAt eq 'Y' && !empty bbsVO.atchmnflId}">
					<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="UTF-8">
						<c:param name="param_atchFileId" value="${bbsVO.atchmnflId}" />				
					</c:import>
				</c:if>
		    </td>
		  </tr>
		  <tr>
		    <th class="WriBg01">홈페이지</th>
		    <td><c:out value="${bbsVO.hmpg}"/></td>
			<th class="WriBg02">조회</th>
		    <td class="WriBg03"><c:out value="${bbsVO.co}"/></td>
		  </tr>
		 </thead>
		<tbody>
		  <tr>
		    <td colspan="4" class="cont">
		    	<c:out value="${bbsVO.cn}" escapeXml="false"/>
		    </td>
		    </tr>
		  <tr>
		    <td class="NX pl5"><img src="/images/pms/common/tdnext.gif" alt="다음글" /></td>
		    <td colspan="3" class="NX">
				<c:choose>
					<c:when test="${bbsVO.nextBbsSn > 0}">
						<c:if test="${bbsInfoVO.albumAt eq 'Y'}">
							<a href="#LINK" onclick="fn_detail('<c:out value="${bbsVO.nextBbsSn}"/>');return false;" onkeypress="this.onclick();return false;">
								<c:choose>
									<c:when test="${!empty bbsVO.nextImageFileId}">
										<img src="<c:url value="/cmm/fms/getImage.do">
											<c:param name="atchFileId" value="${bbsVO.nextImageFileId}"/>
											<c:param name="fileSn" value="0"/>
											</c:url>" alt="<c:out value="${bbsVO.nextSj}"/>" title="<c:out value="${bbsVO.nextSj}"/>" width="100" />
									</c:when>
									<c:otherwise>
										<img src="/images/common/no_image01.gif" alt="<spring:message code="gps.noImage.msg"/>" title="<spring:message code="gps.noImage.msg"/>" />
									</c:otherwise>
								</c:choose>
							</a>
						</c:if>
						<a href="#LINK" onclick="fn_detail('<c:out value="${bbsVO.nextBbsSn}"/>');return false;"><c:out value="${bbsVO.nextSj}"/></a>
					</c:when>
					<c:otherwise>
						다음글이 없습니다.
					</c:otherwise>
				</c:choose>
		    </td>
		    </tr>
		  <tr>
		    <td class="PRE pl5"><img src="/images/pms/common/tdpre.gif" alt="이전글" /></td>
		    <td colspan="3" class="PRE">
				<c:choose>
				<c:when test="${bbsVO.beforeBbsSn > 0}">
					<c:if test="${bbsInfoVO.albumAt eq 'Y'}">
						<a href="#LINK" onclick="fn_detail('<c:out value="${bbsVO.beforeBbsSn}"/>');return false;" onkeypress="this.onclick();return false;">
							<c:choose>
								<c:when test="${!empty bbsVO.beforeImageFileId}">
									<img src="<c:url value="/cmm/fms/getImage.do">
										<c:param name="atchFileId" value="${bbsVO.beforeImageFileId}"/>
										<c:param name="fileSn" value="0"/>
										</c:url>" alt="<c:out value="${bbsVO.beforeSj}"/>" title="<c:out value="${bbsVO.beforeSj}"/>" width="100" />
								</c:when>
								<c:otherwise>
									<img src="/images/common/no_image01.gif" alt="<spring:message code="gps.noImage.msg"/>" title="<spring:message code="gps.noImage.msg"/>" />
								</c:otherwise>
							</c:choose>
						</a>
					</c:if>
					<a href="#LINK" onclick="fn_detail('<c:out value="${bbsVO.beforeBbsSn}"/>');return false;"><c:out value="${bbsVO.beforeSj}"/></a>
				</c:when>
				<c:otherwise>
					이전글이 없습니다.
				</c:otherwise>
				</c:choose>
		    </td>
		    </tr>
		</tbody>
		</table>
		
		<ul class="buttonSet">
		  <li class="fl">
		  	<span class="btn_pack bt02ico"><span class="del"></span>
		  	<button type="button" alt="<spring:message code="gps.button.list"/>" title="<spring:message code="gps.button.list"/>" style="cursor:hand;" onclick="fn_list();">목록</button></span></li>
		  <li class="fr"> 
			<span class="btn_pack bt03"><button type="button" title="<spring:message code="gps.button.update"/>" style="cursor:hand;" onclick="fn_modify();">수정</button></span>
			<c:if test="${bbsInfoVO.answerUseAt eq 'Y' && bbsVO.noticeAt ne 'Y' && answerButtonUseAt}">
				<span class="btn_pack bt03"><button type="button" alt="<spring:message code="gps.button.answer"/>" title="<spring:message code="gps.button.answer"/>" style="cursor:hand;" onclick="fn_answer();">답글</button></span>
			</c:if>
		  </li>
		</ul>
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