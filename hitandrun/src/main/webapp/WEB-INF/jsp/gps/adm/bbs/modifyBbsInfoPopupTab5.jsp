<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<jsp:include page="/gps/cmm/admHeader.do" flush="false"></jsp:include>
<%-- 
/** 
 * outline   : 게시판정보수정화면 TAB5
 * filename : modifyBbsInfoPopupTab5.jsp
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


	//타이틀이미지
	sjUseMthTy();
	//타이틀 이미지 사용자 선택
	userTitleImageUse();
	//NEW 이미지 사용자 선택
	userNewImageUse();
	//COOL 이미지 사용자 선택
	userCoolImageUse();
	//HOT 이미지 사용자 선택
	userHotImageUse();
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
fnc name : sjUseMthTy                                   
outline :  타이틀 사용방법
parameter : 
directions : sjUseMthTy()              
since : 2011-05-10   
author : 통계포털 황기연

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>

function sjUseMthTy(){
	if($('input[name="sjUseMth"]:checked').val()=='I'){
		$('#sjUseImageLayer').show();
		$('#userTitleImageLayer').show();
		$('#TitleImageViewLayer').show();
		$('#sjUseTextLayer').hide();
	}else{
		$('#sjUseImageLayer').hide();
		$('#userTitleImageLayer').hide();
		$('#TitleImageViewLayer').hide();
		$('#sjUseTextLayer').show();
	}
}

<%-- 
/************************************************************************ 
fnc name : userTitleImageUse                                   
outline :  타이틀 이미지 사용자 선택
parameter : 
directions : userTitleImageUse()              
since : 2011-05-10   
author : 통계포털 황기연

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>

function userTitleImageUse(){
	if($('input[name="userTitleImageUseAt"]:checked').val()=='Y'){
		$('#sjUseImageLayer').attr('disabled',true);
		$('#TitleImageViewLayer').attr('disabled',false);
	}else{
		$('#sjUseImageLayer').attr('disabled',false);
		$('#TitleImageViewLayer').attr('disabled',true);
	}
}

<%-- 
/************************************************************************ 
fnc name : userNewImageUse                                   
outline :  New 이미지 사용자 선택
parameter : 
directions : userNewImageUse()              
since : 2011-05-10   
author : 통계포털 황기연

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>

function userNewImageUse(){
	if($('input[name="userNewImageUseAt"]:checked').val()=='Y'){
		$('#NewUseImageLayer').attr('disabled',true);
		$('#NewImageViewLayer').attr('disabled',false);
	}else{
		$('#NewUseImageLayer').attr('disabled',false);
		$('#NewImageViewLayer').attr('disabled',true);
	}
}

<%-- 
/************************************************************************ 
fnc name : userCoolImageUse                                   
outline :  COOL 이미지 사용자 선택
parameter : 
directions : userCoolImageUse()              
since : 2011-05-10   
author : 통계포털 황기연

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>

function userCoolImageUse(){
	if($('input[name="userCoolImageUseAt"]:checked').val()=='Y'){
		$('#CoolUseImageLayer').attr('disabled',true);
		$('#CoolImageViewLayer').attr('disabled',false);
	}else{
		$('#CoolUseImageLayer').attr('disabled',false);
		$('#CoolImageViewLayer').attr('disabled',true);
	}
}

<%-- 
/************************************************************************ 
fnc name : userHotImageUse                                   
outline :  HOT 이미지 사용자 선택
parameter : 
directions : userHotImageUse()              
since : 2011-05-10   
author : 통계포털 황기연

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>

function userHotImageUse(){
	if($('input[name="userHotImageUseAt"]:checked').val()=='Y'){
		$('#HotUseImageLayer').attr('disabled',true);
		$('#HotImageViewLayer').attr('disabled',false);
	}else{
		$('#HotUseImageLayer').attr('disabled',false);
		$('#HotImageViewLayer').attr('disabled',true);
	}
}

<%-- 
/************************************************************************ 
fnc name : fn_subPageSearchPopup                                   
outline :  적용페이지검색
parameter : 
directions : fn_subPageSearchPopup()              
since : 2011-05-10   
author : 통계포털 황기연

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>

function fn_subPageSearchPopup(){
	gfn_postPopupWin("bbsInfoVO", "/gps/adm/bbs/subPageSearchPopup.do", "적용페이지검색팝업",600,300, "yes", 1);
}

</script>
<div class="contents_area">
	<form:form commandName="bbsInfoVO" method="post" enctype="multipart/form-data">
	<form:hidden path="dbTname"/>
	<form:hidden path="sysId"/>
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
							<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="타이틀이미지" title="타이틀이미지"/></td>
							<td class="title"><c:out value="${bbsInfoVO.bbsNm}"/> 게시판디자인정보관리</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="subtitle">
						<tr>
							<td class="icon"><img src="/images/gps/adm/icon/030.gif" alt="타이틀이미지" title="타이틀이미지"/></td>
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
							<li><p><a onclick="fn_modifyBbsInfoPopupTab('3');return false;" onkeydown="this.onclick();return false;">확장기능</a></p></li>
							<li><p><a onclick="fn_modifyBbsInfoPopupTab('4');return false;" onkeydown="this.onclick();return false;">접근권한</a></p></li>
							<li class="on"><p><a>디자인기능</a></p></li>
						</ul>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<table class="subtitle">
						<tr>
							<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="타이틀이미지" title="타이틀이미지"/></td>
							<td class="title">기본정보</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table summary="게시판디자인정보관리" class="write01">
						<caption>게시판디자인정보관리</caption>
						<tr>
							<th class="subject">적용 스킨</th>
							<td class="input">
								<form:select path="skinInfo">
									<form:options items="${bbsSkinList}"/>
								</form:select>
							</td>
						</tr>
						<tr>
							<th class="subject">적용 페이지</th>
							<td>
								<table class="inside02">
								<tr>
									<td class="input"><form:hidden path="subpageId"/>
									<form:input path="subpageNm" cssClass="wi200" readonly="true"/></td>
									<td class="img"><a href="<c:url value="/gps/adm/bbs/subPageSearchPopup.do"><c:param name="sysId" value="${bbsInfoVO.sysId}"/></c:url>" target="_blank" onclick="fn_subPageSearchPopup();return false;" onkeypress="this.onclick();return false;"><img src="/images/button/0207.png" alt="<spring:message code="gps.button.find"/>" title="<spring:message code="gps.button.find"/>"></a></td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th class="reqsubject">테이블 사이즈</th>
							<td class="input"><form:input path="tableSize" maxlength="4" cssClass="wi40 validate[required,custom[onlyNumber],length[0,4]]"/></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="subtitle">
						<tr>
							<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="<spring:message code="gps.titleImage"/>" title="<spring:message code="gps.titleImage"/>"/></td>
							<td class="title">타이틀부분</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table summary="게시판타이틀정보관리" class="write01">
						<caption>게시판타이틀정보관리</caption>
						<tr>
							<th class="subject">타이틀 사용</th>
							<td>
								<table class="inside02">
								<tr>
									<td class="input"><form:radiobutton path="sjUseMth" value="I" cssClass="noline" onclick="sjUseMthTy();" onkeypress="this.onclick();"/></td>
									<td>이미지</td>
									<td class="input"><form:radiobutton path="sjUseMth" value="T" cssClass="noline" onclick="sjUseMthTy();" onkeypress="this.onclick();"/></td>
									<td>텍스트</td>
								</tr>
								</table>
							</td>
						</tr>
						<!-- 타이틀 이미지 사용 start -->
						<tr id="userTitleImageLayer">
							<th class="subject">사용자 선택</th>
							<td>
								<table class="inside02">
								<tr>
									<td class="input"><input type="radio" name="userTitleImageUseAt" id="userTitleImageUseAt" value="Y" class="noline" onClick="userTitleImageUse()" onkeypress="userTitleImageUse()"/></td>
									<td>현재이미지 사용</td>
									<td class="input"><input type="radio" name="userTitleImageUseAt" id="userTitleImageUseAt" value="N" class="noline" onClick="userTitleImageUse()" onkeypress="userTitleImageUse()" checked/></td>
									<td>사용자 선택</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr id="TitleImageViewLayer">
							<th class="subject">이미지미리보기</th>
							<td class="img">
								<c:choose>
									<c:when test="${!empty bbsInfoVO.titleImage}">
										<img src='${WebImagePath}?imageid=web/gps/bbs/${bbsInfoVO.titleImageMask}&ext=${bbsInfoVO.titleImageExt}' alt='타이틀 이미지' title='타이틀 이미지'/>
									</c:when>
									<c:otherwise>
										<img src="/images/common/no_image01.gif" alt="<spring:message code="gps.noImage.msg"/>" title="<spring:message code="gps.noImage.msg"/>">
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr id="sjUseImageLayer">
							<th class="subject">이미지 선택</th>
							<td>
								<table class="inside01">
								<tr>
									<th class="subject">이미지파일선택</th>
									<td class="input">
										<form:hidden path="titleImage"/>
										<input type="file" name="file_1" id="file_1" class="wi200">
									</td>
								</tr>
								<tr>
									<th class="bottom_noline">이미지사이즈</th>
									<td class="bottom_noline">100 px / 500 px</td>
								</tr>
								</table>
							</td>
						</tr>
						<!-- 타이틀 이미지 사용 end -->
						<!-- 타이틀 이미지 사용 start -->
						<tr id="sjUseTextLayer">
							<th class="subject">타이틀 텍스트 CSS</th>
							<td class="input"><form:input path="sjChrctrCss" cssClass="wi100" maxlength="20"/></td>
						</tr>
						<!-- 타이틀 이미지 사용 end -->
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="subtitle">
						<tr>
							<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="<spring:message code="gps.titleImage"/>" title="<spring:message code="gps.titleImage"/>"/></td>
							<td class="title">New 아이콘</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table summary="New 아이콘정보관리" class="write01">
						<caption>New 아이콘정보관리</caption>
						<tr>
							<th class="subject">설정시간</th>
							<td>
								<table class="inside02">
								<tr>
									<td class="input"><form:input path="newIconIndictPd" maxlength="2" cssClass="wi20"/></td>
									<td class="text">시간까지</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th class="subject">사용자 선택</th>
							<td>
								<table class="inside02">
								<tr>
									<td class="input"><input type="radio" name="userNewImageUseAt" id="userNewImageUseAt" value="Y" class="noline" onClick="userNewImageUse()" onkeypress="userNewImageUse()"/></td>
									<td>현재이미지 사용</td>
									<td class="input"><input type="radio" name="userNewImageUseAt" id="userNewImageUseAt" value="N" class="noline" onClick="userNewImageUse()" onkeypress="userNewImageUse()" checked/></td>
									<td>사용자 선택</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr id="NewImageViewLayer">
							<th class="subject">이미지미리보기</th>
							<td class="img">
								<c:choose>
									<c:when test="${!empty bbsInfoVO.newIconImage}">
										<img src='${WebImagePath}?imageid=web/gps/bbs/${bbsInfoVO.newIconImageMask}&ext=${bbsInfoVO.newIconImageExt}' alt='NEW ICON 이미지' title='NEW ICON 이미지'/>
									</c:when>
									<c:otherwise>
										<img src="/images/common/no_image01.gif" alt="<spring:message code="gps.noImage.msg"/>" title="<spring:message code="gps.noImage.msg"/>">
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<!-- >New 아이콘 이미지 사용 start -->
						<tr id="NewUseImageLayer">
							<th class="subject">이미지 선택</th>
							<td>
								<table class="inside01">
								<tr>
									<th class="subject">이미지파일선택</th>
									<td class="input">
										<form:hidden path="newIconImage"/>
										<input type="file" name="file_2" id="file_2" class="wi200">
									</td>
								</tr>
								<tr>
									<th class="bottom_noline">이미지사이즈</th>
									<td class="bottom_noline">100 px / 500 px</td>
								</tr>
								</table>
							</td>
						</tr>
						<!-- New 이미지 사용 end -->
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="subtitle">
						<tr>
							<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="<spring:message code="gps.titleImage"/>" title="<spring:message code="gps.titleImage"/>"/></td>
							<td class="title">COOL 아이콘</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table summary="COOL 아이콘정보관리" class="write01">
						<caption>COOL 아이콘정보관리</caption>
						<tr>
							<th class="subject">설정시간</th>
							<td>
								<table class="inside02">
								<tr>
									<td class="input"><form:input path="coolIconIndictRdcnt" maxlength="2" cssClass="wi20"/></td>
									<td class="text">회 이상</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th class="subject">사용자 선택</th>
							<td>
								<table class="inside02">
								<tr>
									<td class="input"><input type="radio" name="userCoolImageUseAt" id="userCoolImageUseAt" value="Y" class="noline" onClick="userCoolImageUse()" onkeypress="userCoolImageUse()"/></td>
									<td>현재이미지 사용</td>
									<td class="input"><input type="radio" name="userCoolImageUseAt" id="userCoolImageUseAt" value="N" class="noline" onClick="userCoolImageUse()" onkeypress="userCoolImageUse()" checked/></td>
									<td>사용자 선택</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr id="CoolImageViewLayer">
							<th class="subject">이미지미리보기</th>
							<td class="img">
								<c:choose>
									<c:when test="${!empty bbsInfoVO.coolIconImage}">
										<img src='${WebImagePath}?imageid=web/gps/bbs/${bbsInfoVO.coolIconImageMask}&ext=${bbsInfoVO.coolIconImageExt}' alt='COOL ICON 이미지' title='COOL ICON 이미지'/>
									</c:when>
									<c:otherwise>
										<img src="/images/common/no_image01.gif" alt="<spring:message code="gps.noImage.msg"/>" title="<spring:message code="gps.noImage.msg"/>">
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<!-- >New 아이콘 이미지 사용 start -->
						<tr id="CoolUseImageLayer">
							<th class="subject">이미지 선택</th>
							<td>
								<table class="inside01">
								<tr>
									<th class="subject">이미지파일선택</th>
									<td class="input">
										<form:hidden path="coolIconImage"/>
										<input type="file" name="file_3" id="file_3" class="wi200">
									</td>
								</tr>
								<tr>
									<th class="bottom_noline">이미지사이즈</th>
									<td class="bottom_noline">100 px / 500 px</td>
								</tr>
								</table>
							</td>
						</tr>
						<!-- Cool 이미지 사용 end -->
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="subtitle">
						<tr>
							<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="<spring:message code="gps.titleImage"/>" title="<spring:message code="gps.titleImage"/>"/></td>
							<td class="title">HOT 아이콘</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table summary="HOT 아이콘정보관리" class="write01">
						<caption>HOT 아이콘정보관리</caption>
						<tr>
							<th class="subject">설정시간</th>
							<td>
								<table class="inside02">
								<tr>
									<td class="input"><form:input path="hotIconIndictRdcnt" cssClass="wi20" maxlength="2"/></td>
									<td class="text">회 이상</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th class="subject">사용자 선택</th>
							<td>
								<table class="inside02">
								<tr>
									<td class="input"><input type="radio" name="userHotImageUseAt" id="userHotImageUseAt" value="Y" class="noline" onClick="userHotImageUse()" onkeypress="userHotImageUse()"/></td>
									<td>현재이미지 사용</td>
									<td class="input"><input type="radio" name="userHotImageUseAt" id="userHotImageUseAt" value="N" class="noline" onClick="userHotImageUse()" onkeypress="userHotImageUse()" checked/></td>
									<td>사용자 선택</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr id="HotImageViewLayer">
							<th class="subject">이미지미리보기</th>
							<td class="img">
								<c:choose>
									<c:when test="${!empty bbsInfoVO.hotIconImage}">
										<img src='${WebImagePath}?imageid=web/gps/bbs/${bbsInfoVO.hotIconImageMask}&ext=${bbsInfoVO.hotIconImageExt}' alt='HOT ICON 이미지' title='HOT ICON 이미지'/>
									</c:when>
									<c:otherwise>
										<img src="/images/common/no_image01.gif" alt="<spring:message code="gps.noImage.msg"/>" title="<spring:message code="gps.noImage.msg"/>">
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<!-- >New 아이콘 이미지 사용 start -->
						<tr id="HotUseImageLayer">
							<th class="subject">이미지 선택</th>
							<td>
								<table class="inside01">
								<tr>
									<th class="subject">이미지파일선택</th>
									<td class="input">
										<form:hidden path="hotIconImage"/>
										<input type="file" name="file_4" id="file_4" class="wi200">
									</td>
								</tr>
								<tr>
									<th class="bottom_noline">이미지사이즈</th>
									<td class="bottom_noline">100 px / 500 px</td>
								</tr>
								</table>
							</td>
						</tr>
						<!-- Cool 이미지 사용 end -->
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