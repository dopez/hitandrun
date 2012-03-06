<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<jsp:include page="/gps/cmm/admHeader.do" flush="false"></jsp:include>
<%-- 
/** 
 * outline   : 게시판정보수정화면 TAB3
 * filename : modifyBbsInfoPopupTab3.jsp
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

	//설정별화면변경
	changeAlbumFunc();
	ctgryCodeUse();//분류사용여부
	fileUploadUse();//첨부파일사용여부
	
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

<%-- 
/************************************************************************ 
fnc name : changeAlbumFunc                                   
outline :  앨범사용여부
parameter : 
directions : changeAlbumFunc()              
since : 2011-05-10   
author : 통계포털 황기연

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function changeAlbumFunc(){
	if($('input[name="albumAt"]:checked').val()=='Y'){
		$('#noticeUseAt').val('N');
		$('#answerUseAt').val('N');
		$('#noticeForm').attr('disabled',true);
		$('#noticeUseLayer').hide();
		$('#answerForm').attr('disabled',true);
		$('#normalPageListCnt').hide();
		$('#albumForm').attr('disabled',false);
		$('#albumPageListCnt').show();
		albumOptionSet();
	}else{
		$('#noticeForm').attr('disabled',false);
		$('#answerForm').attr('disabled',false);
		$('#normalPageListCnt').show();
		$('#albumForm').attr('disabled',true);
		$('#albumPageListCnt').hide();
		noticeUse();
	}
}

<%-- 
/************************************************************************ 
fnc name : ctgryCodeUse                                   
outline :  분류 사용여부
parameter : 
directions : ctgryCodeUse()              
since : 2011-05-10   
author : 통계포털 황기연

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function ctgryCodeUse(){
	$('input[name="ctgryCodeUseAt"]:checked').val()=='Y'?$('#ctgryCodeUseLayer').show():$('#ctgryCodeUseLayer').hide();
}

<%-- 
/************************************************************************ 
fnc name : noticeUse                                   
outline :  공지사항 사용여부 사용여부
parameter : 
directions : noticeUse()              
since : 2011-05-10   
author : 통계포털 황기연

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function noticeUse(){
	$('input[name="noticeUseAt"]:checked').val()=='Y'?$('#noticeUseLayer').show():$('#noticeUseLayer').hide();
}


<%-- 
/************************************************************************ 
fnc name : fileUploadUse                                   
outline :  파일업로드 사용여부
parameter : 
directions : fileUploadUse()              
since : 2011-05-10   
author : 통계포털 황기연

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fileUploadUse(){
	$('input[name="fileUploadUseAt"]:checked').val()=='Y'?$('#fileUploadLayer').show():$('#fileUploadLayer').hide();
}

<%-- 
/************************************************************************ 
fnc name : albumOptionSet                                   
outline :  앨범형태설정시 페이지옵션 변경
parameter : 
directions : albumOptionSet()              
since : 2011-05-10   
author : 통계포털 황기연

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function albumOptionSet(){
	if($('#albumStleAt').val()=='2'){
		$('#normalPageListCnt').show();
		$('#albumPageListCnt').hide();
	}else{
		$('#normalPageListCnt').hide();
		$('#albumPageListCnt').show();
	}
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
							<td class="title"><c:out value="${bbsInfoVO.bbsNm}"/> 게시판확장기능정보관리</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="subtitle">
						<tr>
							<td class="icon"><img src="/images/gps/adm/icon/030.gif" alt="<spring:message code="gps.titleImage"/>" title="<spring:message code="gps.titleImage"/>"/></td>
							<td class="title"><c:out value="${bbsInfoVO.bbsNm}"/> 게시판 정보</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<div class="contents_tab clfix">
						<ul>
							<li><p><a onclick="fn_modifyBbsInfoPopupTab('1');return false;" onkeydown="this.onclick();return false;">생성정보</a></p></li>
							<li><p><a onclick="fn_modifyBbsInfoPopupTab('2');return false;" onkeydown="this.onclick();return false;">기본정보</a></p></li>
							<li class="on"><p><a>확장기능</a></p></li>
							<li><p><a onclick="fn_modifyBbsInfoPopupTab('4');return false;" onkeydown="this.onclick();return false;">접근권한</a></p></li>
							<li><p><a onclick="fn_modifyBbsInfoPopupTab('5');return false;" onkeydown="this.onclick();return false;">디자인기능</a></p></li>
						</ul>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<table class="caption">
						<tr>
							<td class="form"><form:radiobutton path="albumAt" cssClass="noline" value="N" onclick="changeAlbumFunc();" onkeypress="this.onclick();"/></td>
							<td class="title">일반리스트형태</td>
							<td class="form pl10"><form:radiobutton path="albumAt" cssClass="noline" value="Y" onclick="changeAlbumFunc();" onkeypress="this.onclick();"/></td>
							<td class="title">앨범형태</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<!-- 일반리스트형태가 F start -->
						<table summary="게시판확장기능정보관리" class="write01">
						<caption>게시판확장기능정보관리</caption>
						<tr>
							<th class="subject">분류 사용여부</th>
							<td>
								<table class="inside02">
								<tr>
									<td class="input"><form:radiobutton path="ctgryCodeUseAt" value="Y" cssClass="noline" onclick="ctgryCodeUse();" onkeypress="this.onclick();"/></td>
									<td>사용</td>
									<td class="input"><form:radiobutton path="ctgryCodeUseAt" value="N" cssClass="noline" onclick="ctgryCodeUse();" onkeypress="this.onclick();"/></td>
									<td>사용안함</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr id="ctgryCodeUseLayer">
							<th class="subject">분류코드 선택</th>
							<td class="input">
								<form:select path="ctgryCode">
									<form:options items="${ctgryCodeMap}"/>
								</form:select>
							</td>
						</tr>
						<tr>
							<th class="subject">관련글 사용여부</th>
							<td>
								<table class="inside02">
								<tr>
									<td class="input"><form:radiobutton path="relateUseAt" value="Y" cssClass="noline"/></td>
									<td>사용</td>
									<td class="input"><form:radiobutton path="relateUseAt" value="N" cssClass="noline"/></td>
									<td>사용안함</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th class="subject">주윗글 사용여부</th>
							<td>
								<table class="inside02">
								<tr>
									<td class="input"><form:radiobutton path="arndUseAt" value="Y" cssClass="noline"/></td>
									<td>사용</td>
									<td class="input"><form:radiobutton path="arndUseAt" value="N" cssClass="noline"/></td>
									<td>사용안함</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th class="subject">게시기간 사용여부</th>
							<td>
								<table class="inside02">
								<tr>
									<td class="input"><form:radiobutton path="ntcePdUseAt" value="Y" cssClass="noline"/></td>
									<td>사용</td>
									<td class="input"><form:radiobutton path="ntcePdUseAt" value="N" cssClass="noline"/></td>
									<td>사용안함</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th class="subject">게시대상 사용여부</th>
							<td>
								<table class="inside02">
								<tr>
									<td class="input"><form:radiobutton path="ntceTrgetUseAt" value="Y" cssClass="noline"/></td>
									<td>사용</td>
									<td class="input"><form:radiobutton path="ntceTrgetUseAt" value="N" cssClass="noline"/></td>
									<td>사용안함</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th class="subject">별칭 사용여부</th>
							<td>
								<table class="inside02">
								<tr>
									<td class="input"><form:radiobutton path="ncmUseAt" value="Y" cssClass="noline"/></td>
									<td>사용</td>
									<td class="input"><form:radiobutton path="ncmUseAt" value="N" cssClass="noline"/></td>
									<td>사용안함</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr id="noticeForm">
							<th class="subject">공지사항 사용여부</th>
							<td>
								<table class="inside02">
								<tr>
									<td class="input"><form:radiobutton path="noticeUseAt" value="Y" cssClass="noline" onclick="noticeUse();" onkeypress="this.onclick();"/></td>
									<td>사용</td>
									<td class="input"><form:radiobutton path="noticeUseAt" value="N" cssClass="noline" onclick="noticeUse();" onkeypress="this.onclick();"/></td>
									<td>사용안함</td>
								</tr>
								</table>
							</td>
						</tr>
						<!-- 공지사항 사용여부 Y 일때 start -->
						<tr id="noticeUseLayer">
							<th class="subject">공지사항 갯수</th>
							<td class="input"><form:input path="noticeCo" cssClass="wi20 validate[optional,custom[onlyNumber],length[1,2]]" maxlength="2"/></td>
						</tr>
						<!-- 공지사항 사용여부 Y 일때 end -->
						<tr id="answerForm">
							<th class="subject">답글 사용여부</th>
							<td>
								<table class="inside02">
								<tr>
									<td class="input"><form:radiobutton path="answerUseAt" value="Y" cssClass="noline"/></td>
									<td>사용</td>
									<td class="input"><form:radiobutton path="answerUseAt" value="N" cssClass="noline"/></td>
									<td>사용안함</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr id="albumForm">
							<th class="subject">앨범형태</th>
							<td class="input">
								<form:select path="albumStleAt" onchange="albumOptionSet();">
									<form:options items="${albumSeMap}"/>
								</form:select>
							</td>
						</tr>
						<tr id="normalPageListCnt">
							<th class="subject">페이지 리스트 갯수</th>
							<td class="input"><form:input path="pgeListCo" cssClass="wi20 validate[optional,custom[onlyNumber],length[1,2]]" maxlength="2"/></td>
						</tr>
						<tr id="albumPageListCnt">
							<th class="subject">페이지 리스트 갯수</th>
							<td>
								<table>
									<tr>
										<th class="subject">컬럼수</th>
										<td class="input"><form:input path="albumColumnCo" cssClass="wi20 validate[optional,custom[onlyNumber],length[1,1]]" maxlength="1"/></td>
									</tr>
									<tr>
										<th class="subject">줄수</th>
										<td class="input"><form:input path="albumLineCo" cssClass="wi20 validate[optional,custom[onlyNumber],length[1,1]]" maxlength="1"/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th class="subject">페이지 그룹 갯수</th>
							<td class="input"><form:input path="pgeGroupCo" cssClass="wi20 validate[optional,custom[onlyNumber],length[1,2]]" maxlength="2"/></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="caption">
						<tr>
							<td class="text">파일사용여부</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table summary="파일업로드기능정보관리" class="write01">
						<!-- 파일업로드 사용여부 Y 일때 start -->
						<tr>
							<th class="subject">파일업로드 사용여부</th>
							<td>
								<table class="inside02">
								<tr>
									<td class="input"><form:radiobutton path="fileUploadUseAt" value="Y" cssClass="noline" onclick="fileUploadUse();" onkeypress="this.onclick();"/></td>
									<td>사용</td>
									<td class="input"><form:radiobutton path="fileUploadUseAt" value="N" cssClass="noline" onclick="fileUploadUse();" onkeypress="this.onclick();"/></td>
									<td>사용안함</td>
								</tr>
								</table>
							</td>
						</tr>
						<!-- 파일업로드 사용여부 Y 일때 end -->
						<tr id="fileUploadLayer">
							<th class="subject">업로드 파일갯수</th>
							<td class="input"><form:input path="uploadFileCo" cssClass="wi20 validate[optional,custom[onlyNumber],length[1,1]]" maxlength="1"/></td>
						</tr>
						<!-- 파일업로드 사용여부 Y 일때 end -->
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