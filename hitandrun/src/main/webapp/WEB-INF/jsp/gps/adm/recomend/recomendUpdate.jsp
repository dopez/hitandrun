<%-- 
/** 
 * 설명   : 추천사이트관리 - 추천사이트 상세내용조회하거나 수정하는 화면
 * 파일명 : recomendUpdate.jsp
 * @author 범정부통계포털 이관형 
 * @since 2011.11.1
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
<script type="text/javaScript" language="javascript">
	JQ_setValidation('recomendManageVO');
	JQ_onload();
<%-- 
/************************************************************************ 
함수명 : fncPageOnload                                   
설   명 : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
인   자 : 없음        
사용법 : fncPageOnload()              
작성일 : 2011.06.20  
작성자 : 범정부통계포털 이관형  

      date        author   note  
 ----------   -------     ------------------- 
                              
************************************************************************/ 
--%>
function fncPageOnload() {
	//frameset controller start
	
	//frameset controller end
}
<%-- 
/************************************************************************ 
함수명 : fn_list                                   
설   명 : "목록"버튼을 클릭했을때 추천사이트 리스트로 돌아가는 화면       
인   자 : 없음        
사용법 : fn_list()
작성일 : 2011.06.20
작성자 : 범정부통계포털 이관형  

      date        author   note  
 ----------   -------     ------------------- 
                                 
************************************************************************/
--%>
 function fn_list(){
   	JQ_request("recomendManageVO", "<c:url value='/gps/adm/recomend/selectRecomendList.do'/>", "recomendManageVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_update                               
설   명 : 추천사이트 항목 수정후 재등록 하는 함수
		공통메시지를 띄워 저장여부 확인한 뒤 사용자가 확인을 하면 DB로 내용을 보내서 Update 시킴
인   자 : 없음(form 내용 modelAttribute 자동세팅)
사용법 : fn_update()
작성일 : 2011.06.20
작성자 : 범정부통계포털 이관형       

      date        author   note  
 ----------   -------     ------------------- 
                                 
************************************************************************/
--%> 
function fn_update(){
	if(confirm("<spring:message code='common.update.msg' />")){		
		JQ_request("recomendManageVO", "<c:url value='/gps/adm/recomend/updateRecomend.do'/>");
	}
}

<%-- 
/************************************************************************ 함수명 : fn_delete                               
설   명 : 추천사이트 삭제함수
		공통메시지를 띄워 저장여부 확인한 뒤 사용자가 확인을 하면 DB로 내용을 보내서 Update 시킴
인   자 : 없음(form 내용 modelAttribute 자동세팅)
사용법 : fn_delete()
작성일 : 2011.06.20
작성자 : 범정부통계포털 이관형       

      date        author   note  
 ----------   -------     ------------------- 
                                 
************************************************************************/
--%> 
function fn_delete(){
	if(confirm("<spring:message code='common.delete.msg' />")){		
		JQ_request("recomendManageVO", "<c:url value='/gps/adm/recomend/deleteRecomendDetail.do'/>");
	}
}

<%-- 
/************************************************************************ 
함수명 : fn_img_layer                               
설   명 : 레이어조작
인   자 : 없음(form 내용 modelAttribute 자동세팅)
사용법 : fn_img_layer()
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형       

      date    author            note
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>

function fn_img_layer() {
	if ($("#fileChk").attr("checked") == true) {
		document.getElementById('layer_file').style.display = 'block';
	} else {
		document.getElementById('layer_file').style.display = 'none';
	}
}

</script>

<!-- contents_area start -->
<div class="contents_area">
	<form:form commandName="recomendManageVO" name="updateForm" method="post" enctype="multipart/form-data">
	<form:hidden path="sysId" />
	<form:hidden path="siteId" />
	<form:hidden path="siteSn" />
	<form:hidden path="siteImageNm" id="siteImageNm"/>
	<form:hidden path="siteMageMask" id="siteMageMask"/>
	<form:hidden path="siteImageMime" id="siteImageMime"/>
	<form:hidden path="siteImageSize" id="siteImageSize"/>
	<form:hidden path="pageUnit" id="pageUnit"/>
	<form:hidden path="searchSysId" id="searchSysId"/>
	<form:hidden path="searchCondition" id="searchCondition"/>
	<form:hidden path="pageIndex" id="pageIndex"/>
	<form:hidden path="searchKeyword" id="searchKeyword"/>
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
								<td class="icon"><img src="/images/gps/adm/icon/026.gif"
									alt="추천사이트 수정" title="추천사이트 수정" /></td>
								<td class="title">추천사이트 수정</td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td>
						<table summary="추천사이트 수정" class="write01">
							<caption>추천사이트 수정</caption>
							<tr>
								<th class="subject">시스템</th>
								<td class="input"><select name="sysId" disabled="disabled">
									<c:forEach var="sysInfoList" items="${sysInfo}" varStatus="status">
										<option value="<c:out value="${sysInfoList.sysId}" />" <c:if test="${sysInfoList.sysId eq recomendManageVO.sysId}">selected="selected"</c:if>><c:out
											value="${sysInfoList.sysNm}" /></option>
									</c:forEach>
								</select></td>
							</tr>
							<tr>
								<th class="reqsubject">사이트명</th>
								<td class="input"><form:input path="siteNm" cssClass="wi200 validate[required,length[0,50]] text-input" maxlength="50" /></td>
							</tr>
							<tr>
								<th class="subject">사이트 설명</th>
								<td class="input"><form:textarea path="siteDc" cssClass="wi500 validate[optional,length[6,300]] text-input"/></td>
							</tr>
							<tr>
								<th class="subject">사이트URL1</th>
								<td class="input"><form:input path="siteUrl1" cssClass="wi200 validate[optional,length[0,100]] text-input" maxlength="100" /></td>
							</tr>
							<tr>
								<th class="subject">사이트URL2</th>
								<td class="input"><form:input path="siteUrl2" cssClass="wi200 validate[optional,length[0,100]] text-input" maxlength="100" /></td>
							</tr>
							<tr>
								<th class="subject">사이트URL3</th>
								<td class="input"><form:input path="siteUrl3" cssClass="wi200 validate[optional,length[0,100]] text-input" maxlength="100" /></td>
							</tr>
							<tr>
								<th class="subject">이미지</th>
								<td class="img">
									<img src='${WebImagePath}?imageid=web/gps/image/${recomendManageVO.siteMageMask}&ext=${recomendManageVO.siteImageMime}' alt='${recomendManageVO.siteImageNm}' title='${recomendManageVO.siteImageNm}' width='140' height='40' border='0'/>
									<input type="checkbox" id="fileChk" onclick="fn_img_layer();"/><font color="red">이미지 변경</font>
								</td>
							</tr>
							<tr id="layer_file" style="display:none">
								<th class="subject">이미지 파일</th>
								<td class="input"><input type="file" name="imageFile" maxlength="200" class="wi400"></td>
							</tr>
							<tr>
								<th class="reqsubject">사이트 이미지 높이</th>
								<td class="input"><form:input id="siteImageHeight" path="siteImageHeight" cssClass="wi50 validate[required,custom[onlyNumber],length[0,7]] text-input" maxlength="7" /></td>
							</tr>
							<tr>
								<th class="reqsubject">사이트 이미지 넓이</th>
								<td class="input"><form:input id="siteImageWidth" path="siteImageWidth" cssClass="wi50 validate[required,custom[onlyNumber],length[0,7]] text-input" maxlength="7" /></td>
							</tr>
							<tr>
								<th class="reqsubject">사이트 활성 여부</th>
								<td>
									<table class="inside02">
									<tr>
										<td class="input"><form:radiobutton id="siteActiveAtY" cssClass="validate[required] radio" value="Y" path="siteActiveAt"/></td>
										<td class="text">활성</td>
										<td class="input"><form:radiobutton id="siteActiveAtN" cssClass="validate[required] radio" value="N" path="siteActiveAt"/></td>
										<td class="text">비활성</td>
									</tr>
									</table>
								</td>
							</tr>
							<tr>
								<th class="reqsubject">사이트 사용 여부</th>
								<td>
									<table class="inside02">
									<tr>
										<td class="input"><form:radiobutton id="siteUseAtY" cssClass="validate[required] radio" value="Y" path="siteUseAt"/></td>
										<td class="text">사용</td>
										<td class="input"><form:radiobutton id="siteUseAtN" cssClass="validate[required] radio" value="N" path="siteUseAt"/></td>
										<td class="text">사용안함</td>
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
								<td><a onclick="fn_update();" style="cursor: pointer;"><img src="/images/button/0210.png" alt="<spring:message code="button.save" />" title="<spring:message code="button.save" />"></a></td>
								<td><a onclick="fn_delete();" style="cursor:hand"><img src="/images/button/0204.png" alt="<spring:message code="button.delete" />" title="<spring:message code="button.delete" />"></a></td>
								<td class="end"><a onclick="fn_list();" style="cursor: pointer;"><img src="/images/button/0203.png" alt="<spring:message code="button.list" />" title="<spring:message code="button.list" />"></a></td>
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
<!-- container end -->
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>