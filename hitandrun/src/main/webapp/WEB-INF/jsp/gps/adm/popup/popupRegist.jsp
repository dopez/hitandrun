<%-- 
/** 
 * 설명   : 팝업관리 - 팝업 등록화면
 * 파일명 : popupRegist.jsp
 * @author 범정부통계포털 이관형 
 * @since 2011.06.20 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.06.20     이관형           최초 생성 
 * </pre> 
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>

<%-- Header Start ==========================================================--%>
<jsp:include page="/gps/cmm/admHeader.do"></jsp:include>
<%-- Header End ==========================================================--%>

<%-- javascript start ==============================================--%>
<link rel="stylesheet" type="text/css" href="/css/common/jquery/jquery.ui.all.css" />
<link rel="stylesheet" type="text/css" href="/css/common/jquery/jquery.ui.theme.css" />

<script type="text/javaScript" language="javascript">

	JQ_setValidation('popupManageVO');
	
	JQ_setCalendar('actvtyBgnde');
	JQ_setCalendar('actvtyEndde');
	JQ_onload();

<%-- 
/************************************************************************ 
함수명 : fncPageOnload                                   
설   명 : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
인   자 : 없음        
사용법 : fncPageOnload()              
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형       

  date         author            note
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
함수명 : fn_list                                   
설   명 : "목록"버튼을 클릭했을때 팝업 리스트로 돌아가는 화면       
인   자 : 없음        
사용법 : fn_list()
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형           

  date         author            note
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
 function fn_list(){
   	JQ_request("popupManageVO", "<c:url value='/gps/adm/popup/selectPopupList.do'/>", "popupManageVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_regist                               
설   명 : 팝업 항목 수정후 재등록 하는 함수
		공통메시지를 띄워 저장여부 확인한 뒤 사용자가 확인을 하면 DB로 내용을 보내서 Update 시킴
인   자 : 없음(form 내용 modelAttribute 자동세팅)
사용법 : fn_regist()
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형       

      date    author            note
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
function fn_regist(){
	if(confirm("<spring:message code='common.save.msg' />")){
		if( JQ_dayBetweenValidate( 'actvtyBgnde', 'actvtyEndde' ) ) return;
		JQ_request("popupManageVO", "<c:url value='/gps/adm/popup/insertPopup.do'/>");
	}
}
</script>
<!-- contents_area start -->
<div class="contents_area">
	<form:form commandName="popupManageVO" name="insertForm" method="post" enctype="multipart/form-data" >
	<form:hidden path="pageUnit" id="pageUnit"/>
	<form:hidden path="searchSysId" id="searchSysId"/>
	<form:hidden path="searchCondition" id="searchCondition"/>
	<form:hidden path="pageIndex" id="pageIndex"/>
	<form:hidden path="searchKeyword" id="searchKeyword"/>
	<form:hidden path="searchActvtyAt" id="searchActvtyAt"/>
	<input type="hidden" name="htmlAt" value="Y">
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
							<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="팝업 등록" title="팝업 등록"/></td>
							<td class="title">팝업 등록</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table summary="팝업 등록" class="write01">
						<tr>
							<th class="subject">시스템</th>
							<td class="input">
							   <form:select path="sysId" cssClass="validate[required]">
			                       <form:option value="" label="선택"/>
			                       <form:options items="${sysId}" />
			                   </form:select>
							</td>
						</tr>			
						<tr>
							<th class="subject">분류코드</th>
							<td class="input"><form:input path="popupTy" size="50" cssClass="wi50 validate[required]" maxlength="50" /></td>
						</tr>
						<tr>
							<th class="subject">상태</th>
							<td class="no_padding">
								<table class="inside02">
								<tr>
									<td class="input"><form:radiobutton path="actvtyAt" id="actvtyAtY" value="Y" cssClass="noline validate[required]"/></td>
									<td><label for="actvtyAtY" title="보임"/>보임</label></td>
									<td><form:radiobutton path="actvtyAt" id="actvtyAtN" value="N" cssClass="noline validate[required]"/></td>
									<td><label for="actvtyAtN" title="숨김"/>숨김</label></td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th class="subject">정렬순서</th>
							<td class="input"><form:input path="ordr" size="50" cssClass="wi50" maxlength="50" /></td>
						</tr>
						<tr>
							<th class="subject">기간</th>
							<td class="no_padding">
								<table class="inside01">
								<tr>
									<th class="subject">시작일</th>
									<td class="no_padding">
										<table class="inside02">
										<tr>
											<td class="input">
												<form:input path="actvtyBgnde"  size="10" cssStyle="vertical-align:middle" cssClass="input_t input_nara_date validate[required]" onfocus="gfn_focus(this);" onblur="gfn_focusnot(this);"/>
											</td>
										</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="bottom_noline">종료일</th>
									<td class="bottom_noline">
										<table class="inside02">
										<tr>
											<td class="input">
												<form:input path="actvtyEndde"  size="10" cssStyle="vertical-align:middle" cssClass="input_t input_nara_date validate[required]" onfocus="gfn_focus(this);" onblur="gfn_focusnot(this);"/>
											</td>
										</tr>
										</table>
									</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th class="subject">제목</th>
							<td class="input"><form:input path="sj" size="50" cssClass="wi200  validate[required,length[0,80]]" maxlength="80" /></td>
						</tr>
						<tr>
							<th class="subject">내용</th>
							<td class="input"><form:textarea path="cn" rows="5" cols="65" /></td>
						</tr>
						<tr>
							<th class="subject">크기</th>
							<td class="no_padding">
								<table class="inside01">
									<tr>
										<th class="subject">넓이</th>
										<td class="no_padding">
											<table class="inside02">
											<tr>
												<td class="input">
													<form:input path="width" size="50" cssClass="wi50  validate[required,custom[onlyNumber],length[0,3]]" maxlength="10" />
												</td>
											</tr>
											</table>
										</td>
									</tr>
									<tr>
										<th class="bottom_noline">높이</th>
										<td class="bottom_noline">
											<table class="inside02">
											<tr>
												<td class="input">
													<form:input path="height" size="50" cssClass="wi50  validate[required,custom[onlyNumber],length[0,3]]" maxlength="10" />
												</td>
											</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th class="subject">위치</th>
							<td class="no_padding">
								<table class="inside01">
									<tr>
										<th class="subject">위쪽</th>
										<td class="no_padding">
											<table class="inside02">
											<tr>
												<td class="input">
													<form:input path="lcTop" size="50" cssClass="wi50 validate[required,custom[onlyNumber],length[0,3]]" maxlength="10" />
												</td>
											</tr>
											</table>
										</td>
									</tr>
									<tr>
										<th class="bottom_noline">왼쪽</th>
										<td class="bottom_noline">
											<table class="inside02">
											<tr>
												<td class="input">
													<form:input path="lcLeft" size="50" cssClass="wi50 validate[required,custom[onlyNumber],length[0,3]]" maxlength="10" />
												</td>
											</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th class="subject">스크롤</th>
							<td class="no_padding">
							<table class="inside02">
								<tr>
									<td class="input"><input type="radio" name="lcScroll" value="0" class="noline"></td>
									<td class="text">보임</td>
									<td class="input"><input type="radio" name="lcScroll" value="1" class="noline" checked></td>
									<td class="text">숨김</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<th class="subject">하루안보임</th>
							<td class="no_padding">
							<table class="inside02">
								<tr>
									<td class="input"><input type="radio" name="cookieSkll" value="N" class="noline"></td>
									<td class="text">사용안함</td>
									<td class="input"><input type="radio" name="cookieSkll" value="Y" class="noline" checked></td>
									<td class="text">사용</td>
								</tr>
							</table>
							</td>
						</tr>			
						<tr>
							<th class="subject">URL</th>
							<td class="input"><form:input path="url" size="50" cssClass="wi300  validate[length[0,250]]" maxlength="250" /></td>
						</tr>
						<tr>
							<th class="subject">메인이미지</th>
							<td class="input"><input type="file" name="atchmnflOneFile" maxlength="200" class="wi400"></td>
						</tr>
						<tr>
							<th class="subject">이미지</th>
							<td class="input"><input type="file" name="atchmnflTwoFile" maxlength="200" class="wi400"></td>
						</tr>
						<tr>
							<th class="subject">배경이미지</th>
							<td class="input"><input type="file" name="atchmnflThreeFile" maxlength="200" class="wi400"></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="rbuttonarea">
						<tr>
							<td><a onclick="fn_regist();" style="cursor: pointer;"><img src="/images/button/0210.png" alt="<spring:message code="button.save" />" title="<spring:message code="button.save" />"></a></td>
							<td class="end"><a onclick="fn_list();" style="cursor: pointer;"><img src="/images/button/0201.png" alt="<spring:message code="button.list" />" title="<spring:message code="button.list" />"></a></td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
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
<!-- container end -->
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>