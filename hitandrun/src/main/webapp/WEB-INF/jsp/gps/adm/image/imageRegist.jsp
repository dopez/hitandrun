<%-- 
/** 
 * 설명   : 이미지관리 - 이미지 등록화면
 * 파일명 : imageRegist.jsp
 * @author 범정부통계포털 이관형 
 * @since 2011.10.10 
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp"%>

<%-- Header Start ==========================================================--%>
<jsp:include page="/gps/cmm/admHeader.do"></jsp:include>
<%-- Header End ==========================================================--%>

<%-- javascript start ==============================================--%>
<script type="text/javaScript" language="javascript">
	JQ_setValidation('imageManageVO');
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
}

<%-- 
/************************************************************************ 
함수명 : fn_list                                   
설   명 : "목록"버튼을 클릭했을때 이미지 리스트로 돌아가는 화면       
인   자 : 없음        
사용법 : fn_list()
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형           

  date         author            note
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
 function fn_list(){
   	JQ_request("imageManageVO", "<c:url value='/gps/adm/image/selectImageList.do'/>", "imageManageVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_regist                               
설   명 : 이미지 항목 수정후 재등록 하는 함수
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
		if ($("#imageFile").val() != '') {
			if ($("#imageWidth").val() <= 1) {
				alert('이미지넓이는 1이상 입력해주세요.');
			} else {
				if ($("#imageHeight").val() <= 1) {
					alert('이미지높이는 1이상 입력해주세요.');
				} else {
					JQ_request("imageManageVO", "<c:url value='/gps/adm/image/insertImage.do'/>");
				}
			}
		} else {
			alert('이미지 파일을 업로드 해주세요.');
		}
	}
}
</script>
<!-- contents_area start -->
<div class="contents_area">
	<form:form commandName="imageManageVO" name="insertForm" method="post" enctype="multipart/form-data">
	<form:hidden path="pageUnit" id="pageUnit" />
	<form:hidden path="searchSysId" id="searchSysId" />
	<form:hidden path="searchCondition" id="searchCondition" />
	<form:hidden path="pageIndex" id="pageIndex" />
	<form:hidden path="searchKeyword" id="searchKeyword" />
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
									alt="이미지 등록" title="이미지 등록" /></td>
								<td class="title">이미지 등록</td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td>
						<table summary="이미지 수정" class="write01">
							<caption>이미지 수정</caption>
							<tr>
								<th class="reqsubject">시스템</th>
								<td class="input"><select name="sysId">
									<c:forEach var="sysInfoList" items="${sysInfo}" varStatus="status">
										<option value="<c:out value="${sysInfoList.sysId}" />"><c:out
											value="${sysInfoList.sysNm}" /></option>
									</c:forEach>
								</select></td>
							</tr>
							<tr>
								<th class="reqsubject">이미지 구분</th>
								<td class="input">
									<form:select path="imageSe">
											<form:options items="${imageSeInfo}" />
									</form:select>
								</td>
							</tr>
							<tr>
								<th class="reqsubject">이미지명</th>
								<td class="input"><form:input path="imageNm" cssClass="wi200 validate[required,length[0,50]] text-input" maxlength="50" /></td>
							</tr>
							<tr>
								<th class="subject">이미지 파일</th>
								<td class="input"><input type="file" name="imageFile" id="imageFile" maxlength="200" class="wi400"></td>
							</tr>
							<tr>
								<th class="reqsubject">이미지 넓이</th>
								<td class="input"><form:input path="imageWidth" cssClass="wi50 validate[required,custom[onlyNumber],length[0,7]] text-input" maxlength="7" /></td>
							</tr>
							<tr>
								<th class="reqsubject">이미지 높이</th>
								<td class="input"><form:input path="imageHeight" cssClass="wi50 validate[required,custom[onlyNumber],length[0,7]] text-input" maxlength="7" /></td>
							</tr>
							<tr>
								<th class="reqsubject">이미지반영여부</th>
								<td>
									<table class="inside02">
									<tr>
										<td class="input"><form:radiobutton id="imageReflctAtY" cssClass="validate[required] radio" value="Y" path="imageReflctAt"/></td>
										<td class="text">사용</td>
										<td class="input"><form:radiobutton id="imageReflctAtN" cssClass="validate[required] radio" value="N" path="imageReflctAt"/></td>
										<td class="text">사용안함</td>
									</tr>
									</table>
								</td>
							</tr>
							<tr>
								<th class="subject">이미지URL</th>
								<td class="input"><form:input path="imageReflctUrl" cssClass="wi200 validate[optional,length[0,100]] text-input" maxlength="100" /></td>
							</tr>
							<tr>
								<th class="subject">이미지 설명</th>
								<td class="input"><form:textarea path="imageDc" cssClass="wi500 validate[optional,length[6,300]] text-input"/></td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td>
						<table class="rbuttonarea">
							<tr>
								<td><a onclick="fn_regist();" style="cursor: pointer;"><img src="/images/button/0210.png" alt="<spring:message code="button.create" />" title="<spring:message code="button.create" />"></a></td>
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