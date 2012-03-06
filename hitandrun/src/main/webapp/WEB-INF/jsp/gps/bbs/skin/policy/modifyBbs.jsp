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

<c:if test="${bbsInfoVO.fileUploadUseAt eq 'Y'}">
	<script type="text/javascript" src="/js/common/fms/EgovMultiFile.js"></script>
</c:if>
<%-- javascript end ============================================--%>
<script type="text/javascript">
JQ_setValidation('bbsVO');
JQ_setCalendar('bgnde','endde','from');
JQ_setCalendar('endde','bgnde','to');
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
	<c:if test="${noticeWriteAt eq 'N'}">
	$('#notice').attr('disabled',true);
	</c:if>
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
함수명 : fn_update                                  
설   명 : 수정처리
인   자 : 
사용법 : fn_update()              
작성일 : 2011-09-15
작성자 : 범정부통계포털 황기연       

      date    author      note 
 ----------   -------     ------------------- 
2011-09-15 	  황기연
 ************************************************************************/
 --%>
function fn_update(){
	JQ_setProcessMsg();
   	JQ_request("bbsVO", "<c:url value='/gps/bbs/updateBbs.do'/>");
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
함수명 : 게시글삭제                             
설   명 : 게시글삭제
인   자 : 
사용법 : fn_delete()              
작성일 : 2011-09-15
작성자 : 범정부통계포털 황기연       

      date    author      note 
 ----------   -------     ------------------- 
2011-09-15 	  황기연
 ************************************************************************/
 --%>
function fn_delete(){
	if (confirm("<spring:message code="common.delete.msg" />")) {
		JQ_setProcessMsg();
   		JQ_request("bbsVO","<c:url value='/gps/bbs/deleteBbs.do'/>");
	}
}

<%-- 
/************************************************************************ 
fnc name : fn_fileInspct(/js/gps/gpsCommon.js)                                 
outline : 첨부파일 확장자검사
parameter : 첨부파일 id       
directions : fn_fileInspct      
since : 2011-09-15   
author : 통계포털  황기연       

    date      author             note  
 ----------   -------     ------------------- 
 2011-09-15    황기연                       최초 생성                  
************************************************************************/ 
--%>
function fn_fileInspct(se,id){
	uploadFileInspct(se,id);
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
		<form:form commandName="bbsVO" method="post" enctype="multipart/form-data">
			<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="${bbsInfoVO.uploadFileCo}" />
			<input type="hidden" name="returnUrl" id="returnUrl" value="/gps/bbs/modifyBbs.do" />
			<input type="hidden" name="uploadableFileNum" id="uploadableFileNum"/>
			<input type="hidden" name="pageIndex" id="pageIndex" value="${bbsSearchVO.pageIndex}" />
			<input type="hidden" name="searchCtgryCode" id="searchCtgryCode" value="${bbsSearchVO.searchCtgryCode}" />
			<input type="hidden" name="searchCondition" id="searchCondition" value="${bbsSearchVO.searchCondition}" />
			<input type="hidden" name="searchKeyword" id="searchKeyword" value="${bbsSearchVO.searchKeyword}" />
			<input type="hidden" name="menuId" id="menuId" value="${bbsSearchVO.menuId}" />
			<input type="hidden" name="leftMenuId" id="leftMenuId" value="${bbsSearchVO.leftMenuId}" />
			<form:hidden path="bbsId"/>
			<form:hidden path="wrterId"/>
			<form:hidden path="bbsSn"/>
			<form:hidden path="fileListCnt"/>
			<form:hidden path="atchmnflId"/>
			<form:hidden path="imageFileId"/>
			
			<table class="NotiWrite" summary="${bbsInfoVO.bbsNm} 게시판">
			<caption>${bbsInfoVO.bbsNm}</caption>
			<colgroup>
			   <col width="110" />
			   <col width="606" />
			</colgroup>
			<tbody>
			<c:if test="${!empty bbsInfoVO.ctgryCode}">
			  <tr>
			    <th class="WriTILE"><label for="">분류</label></th>
			    <td class="WriTIRI">
					<select name="ctgryCode">
					<c:forEach var="code01" items="${cateList}">
						<option value="${code01.code_code}" <c:if test="${bbs_code01 eq code01.code_code}">selected</c:if>>${code01.code_fname}</option>
					</c:forEach>
					</select>
			    </td>
			  </tr>
			</c:if>
			  <tr>
			    <th class="WriTILE"><label for="sj">제목</label></th>
			    <td class="WriTIRI"><form:input path="sj" maxlength="100" cssClass="wi500 validate[required,length[1,100]]" cssStyle="ime-mode:active"/> </td>
			  </tr>
			<c:if test="${bbsInfoVO.titleDecoUseAt eq 'Y'}">
				<tr>
					<th>제목색</th>
					<td><form:input path="sjColor" maxlength="100" cssClass="wi100 validate[optional,length[1,100]]" /></td>
				</tr>
			</c:if>
			  <tr>
			    <th class="WriBg01"><label for="wrterNm">작성자</label></th>
			    <td><form:input path="wrterNm" maxlength="10" cssClass="wi100 validate[required,length[1,10]]" cssStyle="ime-mode:active"/></td>
			    </tr>
			  <tr>
			    <th class="WriBg01"><label for="email">이메일</label></th>
			    <td><form:input path="email" maxlength="50" cssClass="wi300 validate[optional,length[1,50],custom[email]" cssStyle="ime-mode:inactive"/></td>
				</tr>
			  <tr>
			    <th class="WriBg01"><label for="hmpg">홈페이지</label></th>
			    <td  class="WriBg03"><form:input path="hmpg" maxlength="50" cssClass="wi300 validate[optional,length[1,50]]" cssStyle="ime-mode:inactive"/></td>
			  </tr>
			  <tr>
			    <th>내용</th>
			    <td class="cont">
					<c:choose>
						<c:when test="${bbsInfoVO.webeditorUseAt eq 'Y'}">
							<form:hidden path="cn"/>
							에디터사용
						</c:when>
						<c:otherwise>
							<form:textarea path="cn" cssClass="wi500 ht200"/>
						</c:otherwise>
					</c:choose>
			    </td>
			  </tr>
			  <c:if test="${gpsSessionVO.usrId != bbsVO.registerId}">
			  <tr>
			    <th><label for="password">비밀번호</label></th>
			    <td><form:password path="password" maxlength="10" cssClass="wi100 validate[required,length[1,10]]"/>* 로그인 정보가 없으면 게시글 수정/삭제시 비밀번호가 필요합니다.</td>
			  </tr>
			  </c:if>
				<c:if test="${bbsInfoVO.fileUploadUseAt eq 'Y'}">
					<tr>
						<th><label for="file_1">첨부파일</label></th>
						<td>
							<table border="0" width="100%">
								<tr>
									<td class="<c:out value="${!empty bbsVO.atchmnflId?'input':'input_bottom_noline'}"/>"><input type="file" name="file_1" id="egovComFileUploader" onfocus="gfn_focus(this);" onblur="gfn_focusnot(this);"/></td>
								</tr>
								<tr>
									<td class="file_bottom_noline">
										<div id="egovComFileList"></div>
										<c:if test="${!empty bbsVO.atchmnflId}">
										<c:import url="/cmm/fms/selectFileInfsForUpdate.do">
											<c:param name="param_atchFileId"  value="${bbsVO.atchmnflId}"/>					
										</c:import>
										</c:if>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</c:if>
			  <tr>
			    <th rowspan="2">게시기간</th>
			    <td><label for="bgnde">시작일</label> <form:input path="bgnde" cssClass="input_t input_nara_date wi100 validate[optional]"/> </td>
				</tr>
				<tr>
				  <td><label for="endde">종료일</label> <form:input path="endde" cssClass="input_t input_nara_date wi100 validate[optional]"/> </td>
				  </tr>
			</tbody>
			</table>
			<ul class="buttonSet">
			  <li class="fl"><span class="btn_pack bt02ico"><span class="del"></span>
			  <button type="button" alt="<spring:message code="gps.button.list"/>" title="<spring:message code="gps.button.list"/>" style="cursor:hand;" onclick="fn_list();">목록</button></span></li>
			  <li class="fr"> 
			  <span class="btn_pack bt03"><button type="button" alt="<spring:message code="gps.button.update"/>" title="<spring:message code="gps.button.update"/>" style="cursor:hand;" onclick="fn_update();" >수정</button></span>
			  <span class="btn_pack bt03"><button type="button" alt="<spring:message code="gps.button.delete"/>" title="<spring:message code="gps.button.delete"/>" style="cursor:hand;" onclick="fn_delete();" >삭제</button></span>
			  </li>
			</ul>
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
<c:if test="${bbsInfoVO.fileUploadUseAt eq 'Y'}">
<script type="text/javascript">
	  var existFileNum; 
	  var maxFileNum; 
	  var uploadableFileNum;
	  existFileNum = $('#fileListCnt').val();// 이 값은 File List를 조회하는 부분에 담겨온다.
	  maxFileNum = $('#posblAtchFileNumber').val(); // 각 비즈니스 로직에서는 해당하는 폼 값에 첨부가능한 최대파일 숫자를 세팅해둬야 함
	  uploadableFileNum = maxFileNum - existFileNum; // 최대등록가능한 파일숫자에서 기존에 등록된 숫자를 뺀다.
	  //alert("uploadableFileNum = " + uploadableFileNum + "\n" + "maxFileNum = " + maxFileNum + "\n" + "existFileNum = " + existFileNum);
	  if(uploadableFileNum <= 0) {
		  $('#uploadableFileNum').val(0);
	  }else{
		  $('#uploadableFileNum').val(uploadableFileNum);
	  }
      var multi_selector = new MultiSelector(document.getElementById( 'egovComFileList' ) , $('#uploadableFileNum').val() );
	  multi_selector.addElement(document.getElementById('egovComFileUploader' )); 
 </script>
 </c:if>
</body>
</html>