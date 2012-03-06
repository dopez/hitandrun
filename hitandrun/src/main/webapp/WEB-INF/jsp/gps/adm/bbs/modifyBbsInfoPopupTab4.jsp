<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<jsp:include page="/gps/cmm/admHeader.do" flush="false"></jsp:include>
<%-- 
/** 
 * outline   : 게시판정보수정화면 TAB4
 * filename : modifyBbsInfoPopupTab4.jsp
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

	//공개방법사용여부
	othbcMthUse();
	if($('input[name="loginUseAt"]:checked').val()=='Y'){
		$('#passwordUseAt').val('N');
		$('#usePassword').attr('disabled',true);
	}
	if($('input[name="passwordUseAt"]:checked').val()=='Y'){
		$('#usePassword').attr('disabled',false);
		$('#usePassword').addClass('validate[required,length[1,10]]');
	}else{
		$('#usePassword').attr('disabled',true);
		$('#usePassword').removeClass('validate[required,length[1,10]]');
	}
	
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
	var listExAuthor = new Array();
	var bdtRedngAuthor = new Array();
	var bdtWriteAuthor = new Array();
	var answerWriteAuthor = new Array();
	var memoWriteAuthor = new Array();
	var webeditorWriteAuthor = new Array();
	
	$("[name^=listExAuthor_]:checked").each(function(){
		listExAuthor.push($(this).val());
		$('#listExAuthor').val(listExAuthor);
	});
	$("[name^=bdtRedngAuthor_]:checked").each(function(){
		bdtRedngAuthor.push($(this).val());
		$('#bdtRedngAuthor').val(bdtRedngAuthor);
	});
	$("[name^=bdtWriteAuthor_]:checked").each(function(){
		bdtWriteAuthor.push($(this).val());
		$('#bdtWriteAuthor').val(bdtWriteAuthor);
	});
	$("[name^=answerWriteAuthor_]:checked").each(function(){
		answerWriteAuthor.push($(this).val());
		$('#answerWriteAuthor').val(answerWriteAuthor);
	});
	$("[name^=memoWriteAuthor_]:checked").each(function(){
		memoWriteAuthor.push($(this).val());
		$('#memoWriteAuthor').val(memoWriteAuthor);
	});
	$("[name^=webeditorWriteAuthor_]:checked").each(function(){
		webeditorWriteAuthor.push($(this).val());
		$('#webeditorWriteAuthor').val(webeditorWriteAuthor);
	});
	
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
fnc name : othbcMthUse                                   
outline :  공개방법 사용여부
parameter : 
directions : othbcMthUse()              
since : 2011-05-10   
author : 통계포털 황기연

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function othbcMthUse(){
	if($('input[name="othbcMthUseAt"]:checked').val()=='Y'){
		$('#othbcMthUseAtLayer').attr('disabled',false);
		$('#bbsInfoVO').find(':checkbox').attr('disabled',false);
		if($('input[name="loginUseAt"]:checked').val()=='N' && $('input[name="passwordUseAt"]:checked').val()=='N'){
			$('#bbsInfoVO').find(':checkbox').each(function(idx, obj){
				if($(this).val() == '999'){
					$(this).attr('checked',true);
					$(this).attr('disabled',true);
				}
			});
		}
		$('#listExAuthor_').addClass('validate[minCheckbox[1]]');
		$('#bdtRedngAuthor_').addClass('validate[minCheckbox[1]]');
		$('#bdtWriteAuthor_').addClass('validate[minCheckbox[1]]');
		$('#answerWriteAuthor_').addClass('validate[minCheckbox[1]]');
		$('#memoWriteAuthor_').addClass('validate[minCheckbox[1]]');
		$('#webeditorWriteAuthor_').addClass('validate[minCheckbox[1]]');
	}else{
		$('#othbcMthUseAtLayer').attr('disabled',true);
		$('#usePassword').attr('disabled',true);
		$('#bbsInfoVO').find(':checkbox').attr('disabled',true);
		$('#listExAuthor_').removeClass('validate[minCheckbox[1]]');
		$('#bdtRedngAuthor_').removeClass('validate[minCheckbox[1]]');
		$('#bdtWriteAuthor_').removeClass('validate[minCheckbox[1]]');
		$('#answerWriteAuthor_').removeClass('validate[minCheckbox[1]]');
		$('#memoWriteAuthor_').removeClass('validate[minCheckbox[1]]');
		$('#webeditorWriteAuthor_').removeClass('validate[minCheckbox[1]]');
		$('#usePassword').removeClass('validate[required,length[1,10]]');
	}
}


<%-- 
/************************************************************************ 
fnc name : loginUse                                   
outline :  로그인후 사용시 암호입력 disable
parameter : 
directions : loginUse()              
since : 2011-05-10   
author : 통계포털 황기연

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function loginUse(){
	if($('input[name="loginUseAt"]:checked').val()=='Y'){
		$('#passwordUseAt').val('N');
		$('#usePassword').attr('disabled',true);
		$('#bbsInfoVO').find(':checkbox').each(function(idx, obj){
			if($(this).val() == '001'){
				$(this).attr('checked',true);
				$(this).attr('disabled',false);
			}else if($(this).val() == '999'){
				$(this).attr('checked',false);
			}
		});
		if($('input[name="loginUseAt"]:checked').val()=='Y' || $('input[name="passwordUseAt"]:checked').val()=='Y'){
			$('#bbsInfoVO').find(':checkbox').each(function(idx, obj){
				if($(this).val() == '999'){
					$(this).attr('checked',false);
					$(this).attr('disabled',true);
				}
			});
		}
	}else{
		$('#bbsInfoVO').find(':checkbox').each(function(idx, obj){
			if($(this).val() == '001'){
				$(this).attr('checked',false);
				$(this).attr('disabled',true);
			}
		});
		if($('input[name="loginUseAt"]:checked').val()=='N' && $('input[name="passwordUseAt"]:checked').val()=='N'){
			$('#bbsInfoVO').find(':checkbox').each(function(idx, obj){
				if($(this).val() == '999'){
					$(this).attr('checked',true);
					$(this).attr('disabled',true);
				}
			});
		}
	}
}

<%-- 
/************************************************************************ 
fnc name : passwordUse                                   
outline :  공개방법 사용여부 중 암호 방식 선택
parameter : 
directions : passwordUse()              
since : 2011-05-10   
author : 통계포털 황기연

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function passwordUse(){
	if($('input[name="passwordUseAt"]:checked').val()=='Y'){
		$('#usePassword').attr('disabled',false);
		$('#usePassword').addClass('validate[required,length[1,10]]');
		$('#bbsInfoVO').find(':checkbox').each(function(idx, obj){
			if($(this).val() == '002'){
				$(this).attr('checked',true);
				$(this).attr('disabled',false);
			}else if($(this).val() == '999'){
				$(this).attr('checked',false);
			}
		});
		if($('input[name="loginUseAt"]:checked').val()=='Y' || $('input[name="passwordUseAt"]:checked').val()=='Y'){
			$('#bbsInfoVO').find(':checkbox').each(function(idx, obj){
				if($(this).val() == '999'){
					$(this).attr('checked',false);
					$(this).attr('disabled',true);
				}
			});
		}
	}else{
		$('#usePassword').attr('disabled',true);
		$('#usePassword').removeClass('validate[required,length[1,10]]');
		$('#bbsInfoVO').find(':checkbox').each(function(idx, obj){
			if($(this).val() == '002'){
				$(this).attr('checked',false);
				$(this).attr('disabled',true);
			}
		});
		if($('input[name="loginUseAt"]:checked').val()=='N' && $('input[name="passwordUseAt"]:checked').val()=='N'){
			$('#bbsInfoVO').find(':checkbox').each(function(idx, obj){
				if($(this).val() == '999'){
					$(this).attr('checked',true);
					$(this).attr('disabled',true);
				}
			});
		}
	}
}


<%-- 
/************************************************************************ 
fnc name : requiredCheck                                   
outline :  체크박스값이 하나도선택되어있지않으면 전체선택
parameter : 
directions : passwordUse()              
since : 2011-05-10   
author : 통계포털 황기연

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function requiredCheck(id){
	if($('input[name='+id+']:checkbox:checked').length == 0){
		$('input[name='+id+']').each(function(){
			if($(this).val() == '999'){
				$(this).attr('checked',true);
			}
		});
	}else{
		$('input[name='+id+']').each(function(){
			if($(this).val() == '999'){
				$(this).attr('checked',false);
			}
		});
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
							<td class="title"><c:out value="${bbsInfoVO.bbsNm}"/> 게시판권한기능정보관리</td>
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
							<li><p><a onclick="fn_modifyBbsInfoPopupTab('3');return false;" onkeydown="this.onclick();return false;">확장기능</a></p></li>
							<li class="on"><p><a>접근권한</a></p></li>
							<li><p><a onclick="fn_modifyBbsInfoPopupTab('5');return false;" onkeydown="this.onclick();return false;">디자인기능</a></p></li>
						</ul>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<table summary="게시판권한기능정보관리" class="write01">
						<caption>게시판권한기능정보관리</caption>
						<tr>
							<th class="subject">공개방법 사용여부</th>
							<td>
								<table class="inside02">
								<tr>
									<td class="input"><form:radiobutton path="othbcMthUseAt" value="Y" cssClass="noline" onclick="othbcMthUse();" onkeypress="this.onclick();"/></td>
									<td>사용</td>
									<td class="input"><form:radiobutton path="othbcMthUseAt" value="N" cssClass="noline" onclick="othbcMthUse();" onkeypress="this.onclick();"/></td>
									<td>사용안함</td>
								</tr>
								</table>
							</td>
						</tr>
						<!-- 공개방법 사용여부 Y start -->
						<tr id="othbcMthUseAtLayer">
							<th class="subject">공개방법 권한</th>
							<td>
								<table class="inside01">
								<tr id="loginUseLayer">
									<th class="subject">로그인 후 사용</th>
									<td>
										<table class="inner01">
										<tr>
											<td class="input_bottom_noline"><form:radiobutton path="loginUseAt" value="Y" cssClass="noline" onclick="loginUse();" onkeypress="this.onclick();"/></td>
											<td class="text_bottom_noline">사용</td>
											<td class="input_bottom_noline"><form:radiobutton path="loginUseAt" value="N" cssClass="noline" onclick="loginUse();" onkeypress="this.onclick();"/></td>
											<td class="text_bottom_noline">사용안함</td>
										</tr>
										</table>
									</td>
								</tr>
								<tr id="passwordUseLayer">
									<th class="bottom_noline">암호 입력 후 사용</th>
									<td class="bottom_noline">
										<table class="inner01">
										<tr>
											<td class="input_bottom_noline"><form:radiobutton path="passwordUseAt" value="Y" cssClass="noline" onclick="passwordUse();" onkeypress="this.onclick();"/></td>
											<td class="text_bottom_noline">사용</td>
											<td class="input_bottom_noline"><form:radiobutton path="passwordUseAt" value="N" cssClass="noline" onclick="passwordUse();" onkeypress="this.onclick();"/></td>
											<td class="text_bottom_noline">사용안함</td>
										</tr>
										</table>
									</td>
								</tr>
								</table>
							</td>
						</tr>
						<!-- 공개방법 사용여부 Y end -->
						<!-- 암호 입력 후 사용 Y start -->
						<tr id="passwordUseAtLayer">
							<th class="subject">암호</th>
							<td class="input"><form:password path="usePassword" maxlength="10" cssClass=""/></td>
						</tr>
						<!-- 암호 입력 후 사용 Y end -->
						<tr id="listExAuthorLayer">
							<th class="subject">목록 보기 권한</th>
							<td class="input">
								<form:hidden path="listExAuthor"/>
								<c:forEach var="map" items="${othbcMthMap}" varStatus="status">
									<input type="checkbox" id="listExAuthor_" name="listExAuthor_" value="${map.key}" class="noline" <c:out value="${fn:contains(bbsInfoVO.listExAuthor,map.key)?'checked':''}"/> onclick="requiredCheck(this.id);"/>
									<c:out value="${map.value}"/>
								</c:forEach>
							</td>
						</tr>
						<tr id="bdtRedngAuthorLayer">
							<th class="subject">본문 읽기 권한</th>
							<td class="input">
								<form:hidden path="bdtRedngAuthor"/>
								<c:forEach var="map" items="${othbcMthMap}" varStatus="status">
									<input type="checkbox" id="bdtRedngAuthor_" name="bdtRedngAuthor_" value="${map.key}" class="noline" <c:out value="${fn:contains(bbsInfoVO.bdtRedngAuthor,map.key)?'checked':''}"/> onclick="requiredCheck(this.id);"/>
									<c:out value="${map.value}"/>
								</c:forEach>
							</td>
						</tr>
						<tr id="bdtWriteAuthorLayer">
							<th class="subject">본문 쓰기 권한</th>
							<td class="input">
								<form:hidden path="bdtWriteAuthor"/>
								<c:forEach var="map" items="${othbcMthMap}" varStatus="status">
									<input type="checkbox" id="bdtWriteAuthor_" name="bdtWriteAuthor_" value="${map.key}" class="noline" <c:out value="${fn:contains(bbsInfoVO.bdtWriteAuthor,map.key)?'checked':''}"/> onclick="requiredCheck(this.id);"/>
									<c:out value="${map.value}"/>
								</c:forEach>
							</td>
						</tr>
						<tr id="answerWriteAuthorLayer">
							<th class="subject">답글 쓰기 권한</th>
							<td class="input">
								<form:hidden path="answerWriteAuthor"/>
								<c:forEach var="map" items="${othbcMthMap}" varStatus="status">
									<input type="checkbox" id="answerWriteAuthor_" name="answerWriteAuthor_" value="${map.key}" class="noline" <c:out value="${fn:contains(bbsInfoVO.answerWriteAuthor,map.key)?'checked':''}"/> onclick="requiredCheck(this.id);"/>
									<c:out value="${map.value}"/>
								</c:forEach>
							</td>
						</tr>
						<!-- 메모 쓰기 권한은 메모 사용여부 Y 시 선택함 start -->
						<c:if test="${bbsInfoVO.memoUseAt == 'Y'}">
						<tr id="memoWriteAuthorLayer">
							<th class="subject">메모 쓰기 권한</th>
							<td class="input">
								<form:hidden path="memoWriteAuthor"/>
								<c:forEach var="map" items="${othbcMthMap}" varStatus="status">
									<input type="checkbox" id="memoWriteAuthor_" name="memoWriteAuthor_" value="${map.key}" class="noline" <c:out value="${fn:contains(bbsInfoVO.memoWriteAuthor,map.key)?'checked':''}"/> onclick="requiredCheck(this.id);"/>
									<c:out value="${map.value}"/>
								</c:forEach>
							</td>
						</tr>
						</c:if>
						<!-- 메모 쓰기 권한은 메모 사용여부 Y 시 선택함 end -->
						<!-- 웹에디터 쓰기 권한은 웹에디터 사용여부 Y 시 선택함 start -->
						<c:if test="${bbsInfoVO.webeditorUseAt == 'Y'}">
						<tr id="webeditorWriteAuthorLayer">
							<th class="subject">웹에디터 쓰기 권한</th>
							<td class="input">
								<form:hidden path="webeditorWriteAuthor"/>
								<c:forEach var="map" items="${othbcMthMap}" varStatus="status">
									<input type="checkbox" id="webeditorWriteAuthor_" name="webeditorWriteAuthor_" value="${map.key}" class="noline" <c:out value="${fn:contains(bbsInfoVO.webeditorWriteAuthor,map.key)?'checked':''}"/> onclick="requiredCheck(this.id);"/>
									<c:out value="${map.value}"/>
								</c:forEach>
							</td>
						</tr>
						</c:if>
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