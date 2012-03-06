<%-- 
/** 
 * outline   : 사용자코드관리 사용자코드목록 조회화면
 * filename : userTypeList.jsp
 * @author 범정부통계포털 이관형 
 * @since 2011.06.17
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.06.17     이관형           최초 생성 
 * 2011.07.21     이진우      사용자유형을 권한유형으로 변경
 * </pre> 
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>

<%-- Header Start ==========================================================--%>
<jsp:include page="/gps/cmm/admHeader.do"></jsp:include>
<%-- Header End ==========================================================--%>

<%-- javascript start ==============================================--%>
<script type="text/javaScript" language="javascript">
	JQ_onload();

<%-- 
/************************************************************************ 
함수명 : fncPageOnload                                   
설   명 : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
인   자 : 없음        
사용법 : fncPageOnload()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

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
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_modify(){
	
	// document.listForm.jssfcCode.value = codeId;
	// var options = {url:'/gps/adm/user/selectUserList.do'};
	// JQ_requestAjax('userManageVO', options);
	parent.frames.rightBody.location.href = "/gps/adm/user/modifyAdmin.do";
	
}

<%--
/************************************************************************ 
함수명 : fn_search                                   
설   명 : 조회조건에 따라 목록을 조회하는 함수
인   자 : 없음(form안에 존재하는 값)
사용법 : fn_search()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_search(type, searchKey){
	if (type == 'all') {
		// 전체 사용자 취득
		parent.frames.rightBody.location.href = "/gps/adm/user/selectUserList.do?listType=" + type;
	} else if (type == 'author') {
		// 권한에 의한 사용자 취득
		parent.frames.rightBody.location.href = "/gps/adm/user/selectUserList.do?authorId=" + searchKey;
	} else if (type == 'sys') {
		parent.frames.rightBody.location.href = "/gps/adm/user/selectUserList.do?sysId=" + searchKey;
	}
}

function fn_sysSearch(){
	JQ_request("listForm", "<c:url value='/gps/adm/user/selectUserTypeList.do'/>", "listForm");
}
</script>
<%-- javascript end ============================================--%>
<!-- page contents start -->
<div class="contents_area">
	<form:form name="listForm" commandName="systemManageVO" method="post" id="listForm">
	<input type="hidden" name="authorId" />
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
								<td class="title">사용자유형</td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td>
							<table class="default">
							<tr>
								<td class="tl">
									<table class="option">
									<tr>
										<td class="first">시스템구분</td>
										<td>
										<!--<img src="/images/button/0401.png" alt="정보수정" title="정보수정" onclick="fn_modify()" />-->
										<select name="sysId" onchange="javaScript:fn_sysSearch();" >
											<option value="">전체</option>
											<c:forEach var="sysInfoList" items="${sysInfo}" varStatus="status">
											<option value="<c:out value="${sysInfoList.sysId}" />" <c:if test="${systemManageVO.sysId == sysInfoList.sysId}">selected</c:if>><c:out value="${sysInfoList.sysNm}" /></option>
											</c:forEach>
										</select>
										</td>
									</tr>
									</table>
								</td>
								<c:if test="${gpsSessionVO.usrId eq 'admin'}">
									<td>
										<dl class="board_counter">
											<dt class="total">${allUserCnt}</dt>
											<dd class="total"><a onclick="fn_search('all', '');" style="cursor:hand">전체</a></dd>
										</dl>
									</td>
								</c:if>
							</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
						<table class="datalist01" summary="사용자코드유형리스트">
						<caption>사용자유형리스트</caption>
							<colgroup>
								<col class="column3"/>
								<col class="column2"/>
								<col class="column1"/>
							</colgroup>
							<thead>
							<tr>
								<th scope="col">시스템</th>
								<th scope="col">권한</th>
								<th scope="col" class="end">등록수</th>
							</tr>
							</thead>
							<tbody>
						<c:set var="totalCount" value="0"/>
						<c:forEach var="resultInfo" items="${userCodeList}" varStatus="status">
							<tr onmouseover="this.style.backgroundColor='#fafafa'" onmouseout="this.style.backgroundColor=''">
								<td class="column3"><a onclick="fn_search('sys', '${resultInfo.sysId}');"><c:out value="${resultInfo.sysNm}" /></a></td>
								<td class="column2"><a style="cursor:hand" onclick="javascript:fn_search('author', '${resultInfo.authorId}');"><c:out value="${resultInfo.authorNm}" /></a></td>
								<td class="column1"><c:out value="${resultInfo.userCnt}" /></td>
							</tr>
							<c:set var="totalCount" value="${totalCount + resultInfo.userCnt}"/>
						</c:forEach>
						<c:if test="${fn:length(userCodeList) eq 0}">
							<tr> 
								<td class="blank" colspan="4"><spring:message code="common.nodata.msg" /></td>
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
	</form:form>
</div>
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>