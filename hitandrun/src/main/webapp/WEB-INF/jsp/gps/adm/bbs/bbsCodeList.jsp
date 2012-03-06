<%-- 
/** 
 * outline   : 게시판코드관리 게시판코드목록 조회화면
 * filename : bbsList.jsp
 * @author 통계포탈 이관형 
 * @since 2011.06.28
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == 개정이력(Modification Information) == 
 *   
 *   date        author     note 
 * ----------    -------    --------------------------- 
 * 2011.06.28     이관형           최초 생성 
 * </pre> 
 */
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- Header Start ==========================================================--%>
<%@include file="/WEB-INF/jsp/gps/cmm/admHeader.jsp" %>
<%@ page import="go.narastat.gps.adm.bbs.service.BbsManageVO"%>
<%-- Header Start ==========================================================--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.bbs/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.bbs/1999/xhtml" lang="ko">

<%-- javascript start ==============================================--%>

<link rel="stylesheet" type="text/css" href="/css/common/jquery/jquery.ui.all.css" />
<link rel="stylesheet" type="text/css" href="/css/common/jquery/jquery.ui.theme.css" />

<script type="text/javascript" src="/js/common/jquery/jquery.form.js"></script>
<%-- javascript end ============================================--%>
<script type="text/javaScript" language="javascript">
<!--

JQ_setValidation('searchVO');
JQ_onload();

<%-- 
/************************************************************************ 
함수명 : fncPageOnload                                   
설   명 : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
인   자 : 없음        
사용법 : fncPageOnload()              
작성일 : 2011-06-17   
작성자 : 포탈 개발팀 이관형       

      date    author      note 
 ----------   -------     ------------------- 
 
************************************************************************/
--%>
function fncPageOnload()
{
	//frameset controller start
	//frameset controller end
}

<%--
/************************************************************************ 
함수명 : fn_modify                                   
설   명 : 관리자정보를를 수정하는 화면을 호출하는 함수
인   자 : 없음(form안에 존재하는 값)
사용법 : fn_modify()              
작성일 : 2011-06-17   
작성자 : 포탈 개발팀 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_admModify(){
	
	// document.listForm.jssfcCode.value = codeId;
	// var options = {url:'/gps/adm/bbs/selectBbsList.do'};
	// JQ_requestAjax('searchVO', options);
	parent.frames.rightBody.location.href = "/gps/adm/bbs/modifyBbsCode.do";
	
}

<%--
/************************************************************************ 
함수명 : fn_search                                   
설   명 : 조회조건에 따라 목록을 조회하는 함수
인   자 : 없음(form안에 존재하는 값)
사용법 : fn_search()              
작성일 : 2011-06-17   
작성자 : 포탈 개발팀 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_search(codeId){
	
	// document.listForm.jssfcCode.value = codeId;
	// var options = {url:'/gps/adm/bbs/selectBbsList.do'};
	// JQ_requestAjax('searchVO', options);
	parent.frames.rightBody.location.href = "/gps/adm/bbs/selectBbsList.do?jssfcCode=" + codeId;
	
}

-->
</script>

<!-- ui-layout-center start  -->
<div class="ui-layout-content">
	<!-- page contents start -->
		<form:form commandName="searchVO" name="listForm" method="post">
		<input type="hidden" name="jssfcCode" />
       <div id="contents_area">
			<table class="managerLayer">
				<tr>
					<td class="td01"></td>
					<td class="td02"></td>
					<td class="td03"></td>
				</tr>
				<tr>
					<td class="td04"></td>
					<td class="pl5 cb vt">
						<table class="default">
							<tr>
								<td>
									<table class="title">
									<tr>
										<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="타이틀이미지" title="타이틀이미지" /></td>
										<td class="title">게시판관리</td>
										<td class="btn"><img src="/images/button/0401.png" alt="정보수정" title="정보수정" onclick="fn_admModify()" /></td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td>
								<table class="datalist01" summary="게시판코드유형리스트">
								<caption>게시판유형리스트</caption>
									<colgroup>
										<col class="column1"/>
										<col class="column3"/>
										<col class="column3"/>
									</colgroup>
									<thead>
									<tr>
										<th scope="col">코드</th>
										<th scope="col">게시판유형</th>
										<th scope="col">등록수</th>
									</tr>
									</thead>
									<tbody>
									<c:forEach var="resultInfo" items="${bbsCodeList}" varStatus="status">
											<tr onmouseover="this.style.backgroundColor='#fafafa'" onmouseout="this.style.backgroundColor=''">
											<td class="column1"><c:out value="${resultInfo.codeId}" /></td>
											<td class="column3" onclick="javascript:fn_search('${resultInfo.codeId}');"><c:out value="${resultInfo.codeNm}" /></td>
											<td class="column3"><c:out value="${resultInfo.usrCnt}" /></td>
										</tr>
									</c:forEach>
									<c:if test="${fn:length(bbsCodeList) == 0}">
										<tr> 
											<td class="lt_text3" colspan="4">
												<spring:message code="common.nodata.msg" />
											</td>
										</tr>   	          				 			   
									</c:if>
									</tbody>
								</table>
								</td>
							</tr>
						</table>
					</td>
					<td class="td05"></td>
				</tr>
				<tr>
					<td class="td06"></td>
					<td class="td07"></td>
					<td class="td08"></td>
				</tr>
			</table>
		</div>
		</form:form>
</div>
</html>