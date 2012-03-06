<%-- 
/** 
 * 설명   : 사용자관리 사용자 등록화면
 * 파일명 : userRegist.jsp
 * @author 범정부통계포털 이관형 
 * @since 2011.06.21 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.06.21     이관형           최초 생성 
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
설   명 : "목록"버튼을 클릭했을때 사용자 리스트로 돌아가는 화면       
인   자 : 없음        
사용법 : fn_list()
작성일 : 2011-06-20   
작성자 : 범정부통계포털 이관형           

  date         author            note
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>
 function fn_list(){
   	JQ_request("userManageVO", "<c:url value='/gps/adm/user/selectUserList.do'/>", "userManageVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_regist                               
설   명 : 사용자 항목 등록
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
		JQ_request("userManageVO", "<c:url value='/gps/adm/user/insertUser.do'/>");
	}
	opener.parent.leftBody.location.reload(true);
}
</script>
<!-- contents_area start -->
<div class="contents_area">
	<form:form commandName="userManageVO" name="insertForm" method="post">
	<form:hidden path="jssfcCode"/>
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
								<td class="title">사용자관리</td>
							</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table summary="사용자 추가" class="write01">
							<caption>사용자 추가</caption>
								<tr>
									<th class="subject">아이디</th>
									<td class="input">
										<form:input path="userId" id="userId" title="사용자ID" cssClass="wi200"/>
										<img src="/images/button/0408.png" alt="중복확인" title="중복확인" />
									</td>
								</tr>
								<tr>
									<th class="subject">이름</th>
									<td class="input">
										<form:input path="nm" id="nm" title="이름" cssClass="wi300"/>
									</td>
								</tr>
								<tr>
									<th class="subject">영문이름</th>
									<td class="input">
										<form:input path="se" id="se" title="영문이름" cssClass="wi300"/>
									</td>
								</tr>
								<!-- 
								<tr>
									<th class="subject">사진</th>
									<td class="no_padding">
										<table class="inside02">
										<tr>
											<td class="input"><input type="file" name="logoImageFile" maxlength="200" class="wi400"></td>
										</tr>
										</table>
									</td>
								</tr>
								 -->
								<tr>
									<th class="subject">사용자구분</th>
									<td class="input"><form:input path="grad" id="grad" title="사용자구분" cssClass="wi300"/></td>
								</tr>
								<tr>
									<th class="subject">소속</th>
									<td class="input">
										<table class="inside01">
											<tr>
												<th class="subject">기관</th>
												<td>
													<form:select path="wrcNm" >
														<form:option value="" label="- 선택 -"/>
														<form:options items="${orgList}" />
													</form:select>
												</td>
											</tr>
											<tr>
												<th class="subject">부서</th>
												<td>
													<form:select path="deptNm" >
														<form:option value="" label="- 선택 -"/>
														<form:options items="${orgList}" />
													</form:select>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="subject">비밀번호</th>
									<td class="input"><form:password path="password" id="password" title="비밀번호" cssClass="wi300"/></td>
								</tr>
								<tr>
									<th class="subject">비밀번호 확인</th>
									<td class="input"><input type="password" id="password2" title="비밀번호 확인" class="wi300"/></td>
								</tr>
								<tr>
									<th class="subject">EMAIL</th>
									<td class="input"><form:input path="email" id="email" title="EMAIL" cssClass="wi300"/></td>
								</tr>
								<tr>
									<th class="subject">전화번호</th>
									<td class="input"><form:input path="phonCn" id="phonCn" title="전화번호" cssClass="wi300"/></td>
								</tr>
								<tr>
									<th class="subject">휴대전화</th>
									<td class="input"><form:input path="moblphonCn" id="moblphonCn" title="휴대전화" cssClass="wi300"/></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td align="center">
							<table class="rbuttonarea">
								<tr>
									<td><img src="/images/button/0210.png" alt="<spring:message code="button.save" />" title="<spring:message code="button.save" />" onclick = "fn_regist();"></td>
									<td><img src="/images/button/0203.png" alt="<spring:message code="button.list" />" title="<spring:message code="button.list" />" onclick = "fn_list();"></td>
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