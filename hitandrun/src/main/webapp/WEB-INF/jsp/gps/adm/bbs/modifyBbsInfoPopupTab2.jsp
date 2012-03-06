<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<jsp:include page="/gps/cmm/admHeader.do" flush="false"></jsp:include>
<%-- 
/** 
 * outline   : 게시판정보수정화면 TAB2
 * filename : modifyBbsInfoPopupTab2.jsp
 * @author 통계포탈 황기연 
 * @since 2011.06.28
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == 개정이력(Modification Information) == 
 *   
 *   date        author     note 
 * ----------    -------    --------------------------- 
 * 2011.06.28     황기연           최초 생성 
 * </pre> 
 */
--%>
<script type="text/javascript">
JQ_setValidation('bbsInfoVO');
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
	opener.location.href="<c:url value='/gps/adm/bbs/selectBbsInfoList.do'><c:param name='dbTname' value='${bbsInfoVO.dbTname}'/></c:url>";
	<c:if test="${popupCloseAt == 'Y'}">
		window.self.close();
	</c:if>
	</c:if>
}

<%-- 
/************************************************************************ 
fnc name : fn_update                               
outline : 게시판DB수정처리
parameter : 없음        
directions : fn_update()              
since : 2011-07-05    
author : 통계포털  황기연       

    date      author             note  
 ----------   -------     ------------------- 
 2011.06.10    황기연                                          
************************************************************************/ 
--%>
function fn_update(){
	JQ_setProcessMsg();
	JQ_request("bbsInfoVO", "<c:url value='/gps/adm/bbs/updateBbsInfo.do'/>");
}

<%-- 
/************************************************************************ 
fnc name : fn_delete                                 
outline : 게시판DB삭제    
parameter : 없음        
directions : fn_delete()              
since : 2011-07-05    
author : 통계포털  황기연       

    date      author             note  
 ----------   -------     ------------------- 
 2011.06.10    황기연                                          
************************************************************************/ 
--%>
function fn_delete(){
	if (confirm("<spring:message code="gps.bbsInfo.delete.msg" />")){
	JQ_setProcessMsg();
	JQ_request("bbsInfoVO", "<c:url value='/gps/adm/bbs/deleteBbsInfo.do'/>","bbsInfoVO");
	}
}

<%-- 
/************************************************************************ 
fnc name : fn_close                           
outline : 팝업창닫힘
parameter : 없음        
directions : fn_close()              
since : 2011-07-05    
author : 통계포털  황기연       

    date      author             note  
 ----------   -------     ------------------- 
 2011.07.05    황기연                                          
************************************************************************/ 
--%>
function fn_close(){
	window.close();
}

<%-- 
/************************************************************************ 
fnc name : fn_modifyBbsInfoTab                                   
outline :  탭이동
parameter : val
directions : fn_modifyBbsInfoTab()              
since : 2011-05-10   
author : 통계포털 황기연

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_modifyBbsInfoPopupTab(tabSe){
	$('#tabSe').val(tabSe);
	JQ_setProcessMsg();
	JQ_request("bbsInfoVO","<c:url value='/gps/adm/bbs/modifyBbsInfoPopupTab.do'/>","bbsInfoVO");
}
</script>
<div class="contents_area">
	<form:form commandName="bbsInfoVO" method="post" enctype="multipart/form-data">
	<form:hidden path="dbTname"/>
	<form:hidden path="bbsId"/>
	<form:hidden path="bbsNm"/>
	<form:hidden path="tabSe"/>
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
								<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="<spring:message code="gps.titleImage"/>" title="<spring:message code="gps.titleImage"/>"/></td>
								<td class="title"><c:out value="${bbsInfoVO.bbsNm}"/> 게시판기본정보관리</td>
							</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table class="subtitle">
							<tr>
								<td class="icon"><img src="/images/gps/adm/icon/030.gif" alt="<spring:message code="gps.titleImage"/>" title="<spring:message code="gps.titleImage"/>"/></td>
								<td class="title"><c:out value="${bbsInfoVO.bbsNm}"/> 게시판정보</td>
							</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<div class="contents_tab clfix">
							<ul>
								<li><p><a onclick="fn_modifyBbsInfoPopupTab('1');return false;" onkeydown="this.onclick();return false;">생성정보</a></p></li>
								<li class="on"><p><a>기본정보</a></p></li>
								<li><p><a onclick="fn_modifyBbsInfoPopupTab('3');return false;" onkeydown="this.onclick();return false;">확장기능</a></p></li>
								<li><p><a onclick="fn_modifyBbsInfoPopupTab('4');return false;" onkeydown="this.onclick();return false;">접근권한</a></p></li>
								<li><p><a onclick="fn_modifyBbsInfoPopupTab('5');return false;" onkeydown="this.onclick();return false;">디자인기능</a></p></li>
							</ul>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<table summary="게시판기본정보관리" class="write01">
							<caption>게시판기본정보관리</caption>
							<tr>
								<th class="subject">웹에디터 사용여부</th>
								<td>
									<table class="inside02">
									<tr>
										<td class="input"><form:radiobutton path="webeditorUseAt" value="Y" cssClass="noline"/></td>
										<td>사용</td>
										<td class="input"><form:radiobutton path="webeditorUseAt" value="N" cssClass="noline"/></td>
										<td>사용안함</td>
									</tr>
									</table>
								</td>
							</tr>
							<tr>
								<th class="subject">메모 사용여부</th>
								<td>
									<table class="inside02">
									<tr>
										<td class="input"><form:radiobutton path="memoUseAt" value="Y" cssClass="noline"/></td>
										<td>사용</td>
										<td class="input"><form:radiobutton path="memoUseAt" value="N" cssClass="noline"/></td>
										<td>사용안함</td>
									</tr>
									</table>
								</td>
							</tr>
							<tr>
								<th class="subject">제목 꾸밈 사용여부</th>
								<td>
									<table class="inside02">
									<tr>
										<td class="input"><form:radiobutton path="titleDecoUseAt" value="Y" cssClass="noline"/></td>
										<td>사용</td>
										<td class="input"><form:radiobutton path="titleDecoUseAt" value="N" cssClass="noline"/></td>
										<td>사용안함</td>
									</tr>
									</table>
								</td>
							</tr>
							<tr>
								<th class="subject">아바타 사용여부</th>
								<td>
									<table class="inside02">
									<tr>
										<td class="input"><form:radiobutton path="avataUseAt" value="Y" cssClass="noline"/></td>
										<td>사용</td>
										<td class="input"><form:radiobutton path="avataUseAt" value="N" cssClass="noline"/></td>
										<td>사용안함</td>
									</tr>
									</table>
								</td>
							</tr>
							<tr>
								<th class="subject">추천 사용여부</th>
								<td>
									<table class="inside02">
									<tr>
										<td class="input"><form:radiobutton path="recommendUseAt" value="Y" cssClass="noline"/></td>
										<td>사용</td>
										<td class="input"><form:radiobutton path="recommendUseAt" value="N" cssClass="noline"/></td>
										<td>사용안함</td>
									</tr>
									</table>
								</td>
							</tr>
							<tr>
								<th class="subject">리스트 사용여부</th>
								<td>
									<table class="inside02">
									<tr>
										<td class="input"><form:radiobutton path="listUseAt" value="Y" cssClass="noline"/></td>
										<td>사용</td>
										<td class="input"><form:radiobutton path="listUseAt" value="N" cssClass="noline"/></td>
										<td>사용안함</td>
									</tr>
									</table>
								</td>
							</tr>
							<tr>
								<th class="subject">RSS 사용여부</th>
								<td>
									<table class="inside02">
									<tr>
										<td class="input"><form:radiobutton path="rssUseAt" value="Y" cssClass="noline"/></td>
										<td>사용</td>
										<td class="input"><form:radiobutton path="rssUseAt" value="N" cssClass="noline"/></td>
										<td>사용안함</td>
									</tr>
									</table>
								</td>
							</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table class="rbuttonarea">
							<tr>
								<td><img src="/images/button/0209.png" alt="<spring:message code='button.update'/>" title="<spring:message code='button.update'/>" style="cursor:hand;" onclick="fn_update();return false;" onkeydown="this.onclick();"/></td>
								<td><img src="/images/button/0204.png" alt="<spring:message code='button.delete'/>" title="<spring:message code='button.delete'/>" style="cursor:hand;" onclick="fn_delete();return false;" onkeydown="this.onclick();"></td>
								<td class="end"><img src="/images/button/0205.png" alt="<spring:message code='button.close'/>" title="<spring:message code='button.close'/>" style="cursor:hand;" onclick="fn_close();return false;" onkeydown="this.onclick();"/></td>
							</tr>
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