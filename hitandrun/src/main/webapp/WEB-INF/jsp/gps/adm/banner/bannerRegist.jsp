<%-- 
/** 
 * 설명   : 배너관리 - 배너 등록화면
 * 파일명 : bannerRegist.jsp
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
	JQ_setValidation('bannerManageVO');

	JQ_setCalendar('bgnde');
	JQ_setCalendar('endde');
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
설   명 : "목록"버튼을 클릭했을때 배너 리스트로 돌아가는 화면       
인   자 : 없음        
사용법 : fn_list()
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형           

  date         author            note
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
 function fn_list(){
   	JQ_request("bannerManageVO", "<c:url value='/gps/adm/banner/selectBannerList.do'/>", "bannerManageVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_regist                               
설   명 : 입력된 배너 정보를 등록 하는 함수
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
		if( JQ_dayBetweenValidate( 'bgnde', 'endde' ) ) return;
	}
	if(document.insertForm.logoImageFile.value == ''){
		alert('배너의 이미지를 업로드 해주세요.');
	} else {
		JQ_request("bannerManageVO", "<c:url value='/gps/adm/banner/insertBanner.do'/>");
	}
	
}
</script>
<form:form commandName="bannerManageVO" name="insertForm" method="post" enctype="multipart/form-data" >
<!-- contents_area start -->
<div class="contents_area">
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
						<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="배너 등록" title="배너 등록"/></td>
						<td class="title">배너 등록</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table summary="배너 등록" class="write01">
						<tr>
							<th class="subject">시스템</th>
							<td class="input">
								<select name="sysId">
									<c:forEach var="sysInfoList" items="${sysInfo}" varStatus="status">
									<option value="<c:out value="${sysInfoList.sysId}" />" ><c:out value="${sysInfoList.sysNm}" /></option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th class="subject">정렬순서</th>
							<td class="input"><form:input path="ordr" cssClass="wi50 validate[required,length[1,5],custom[onlyNumber]]" maxlength="5" /></td>
						</tr>
						<tr>
							<th class="subject">배너명</th>
							<td class="input"><form:input path="nm" cssClass="wi200 validate[required,length[1,80]] text-input" maxlength="80" /></td>
						</tr>
						<tr>
							<th class="subject">사용여부</th>
							<td class="no_padding">
							<table class="inside02">
								<tr>
									<td class="input"><input type="radio" name="actvtyAt" value="Y" class="noline"></td>
									<td class="text">사용</td>
									<td class="input"><input type="radio" name="actvtyAt" value="N" class="noline" checked></td>
									<td class="text">사용안함</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<th class="subject">기간</th>
							<td class="no_padding">
								<table class="inside01">
								<!-- 
								<tr>
									<th class="subject">기간없음</th>
									<td class="no_padding">
										<table class="inside02">
										<tr>
											<td class="input"><input type="checkbox" name="" value="Y" class="noline" checked></td>
											<td class="text">기간없이 설정</td>
										</tr>
										</table>
									</td>
								</tr>
								 -->
								<tr>
									<th class="subject">시작일</th>
									<td class="no_padding">
										<table class="inside02">
										<tr>
											<td class="input">
												<form:input path="bgnde"  size="10" cssStyle="vertical-align:middle" cssClass="input_t input_nara_date" onfocus="gfn_focus(this);" onblur="gfn_focusnot(this);"/>
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
												<form:input path="endde"  size="10" cssStyle="vertical-align:middle" cssClass="input_t input_nara_date" onfocus="gfn_focus(this);" onblur="gfn_focusnot(this);"/>
											</td>
										</tr>
										</table>
									</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th class="subject">배너이미지</th>
							<td class="no_padding">
							<table class="inside01">
								<tr>
									<th class="subject">배너이미지선택</th>
									<td class="no_padding">
										<table class="inside02">
											<tr>
												<td class="input"><input type="file" name="logoImageFile" maxlength="200" class="wi400"></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="bottom_noline">이미지주석</th>
									<td class="bottom_noline">
										<table class="inside02">
											<tr>
												<td class="input"><form:input path="imageAlt" cssClass="wi400 validate[optional,length[1,80]]" maxlength="80" /></td>
											</tr>
										</table>
									</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th class="subject">URL</th>
							<td class="input"><form:input path="url" cssClass="wi300 validate[optional,length[1,200]]" maxlength="200" /></td>
						</tr>
						<tr>
							<th class="subject">TARGET</th>
							<td class="input">
								<form:select path="trget">
									<form:option value="" label="- 선택 -"/>
									<form:option value="1" label="전체 시스템" />
								</form:select>
							</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="rbuttonarea">
						<tr>
							<td><a onclick = "fn_regist();" style="cursor: pointer;"><img src="/images/button/0210.png" alt="<spring:message code="button.create" />" title="<spring:message code="button.create" />"></a></td>
							<td class="end"><a onclick = "fn_list();" style="cursor: pointer;"><img src="/images/button/0203.png" alt="<spring:message code="button.reset" />" title="<spring:message code="button.reset" />"></a></td>
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
	</div>
	<!-- container end -->
	</form:form>
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>