<%-- 
/** 
 * 설명   : 게시판 상세내용을 수정하는 화면
 * 파일명 : bbsUpdate.jsp
 * @author 통계포탈 이관형 
 * @since 2011.06.20
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == 개정이력(Modification Information) == 
 *   
 *   date         author    note  
 * ----------    -------    --------------------------- 
 * 2011.06.20     이관형           최초 생성 
 * </pre> 
 */
 --%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- Header Start ==========================================================--%>
<%@include file="/WEB-INF/jsp/gps/cmm/admHeader.jsp" %>
<%@ page import="go.narastat.gps.adm.bbs.service.BbsManageVO"%>
<%-- Header Start ==========================================================--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko">
<%-- javascript start ==============================================--%>

<%-- javascript end ============================================--%>

<script type="text/javaScript" language="javascript">
JQ_setValidation('bbsManageVO');
JQ_onload();
<!--
<%-- 
/************************************************************************ 
함수명 : fncPageOnload                                   
설   명 : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
인   자 : 없음        
사용법 : fncPageOnload()              
작성일 : 2011.06.20  
작성자 : 통계포탈 이관형  

      date        author   note  
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
설   명 : "목록"버튼을 클릭했을때 게시물 리스트로 돌아가는 화면       
인   자 : 없음        
사용법 : fn_list()
작성일 : 2011.06.20
작성자 : 통계포탈 이관형  

      date        author   note  
 ----------   -------     ------------------- 
                                 
************************************************************************/
--%>
 function fn_list(){
   	JQ_request("bbsManageVO", "<c:url value='/gps/adm/bbs/selectBbsList.do'/>", "bbsManageVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_regist                               
설   명 : 게시물 항목 수정후 재등록 하는 함수
		공통메시지를 띄워 저장여부 확인한 뒤 게시판가 확인을 하면 DB로 내용을 보내서 Update 시킴
인   자 : 없음(form 내용 modelAttribute 자동세팅)
사용법 : fn_regist()
작성일 : 2011.06.20
작성자 : 통계포탈 이관형       

      date        author   note  
 ----------   -------     ------------------- 
                                 
************************************************************************/
--%> 
function fn_regist(){
	if(confirm("<spring:message code='common.save.msg' />")){		
		JQ_request("bbsManageVO", "<c:url value='/gps/adm/bbs/updateBbs.do'/>");
	}
}
-->
</script>
<div class="ui-layout-content">

	<form:form commandName="bbsManageVO" name="insertForm" method="post">
	<form:hidden path="jssfcCode"/>
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
										<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="타이틀이미지" title="타이틀이미지"/></td>
										<td class="title">게시판관리</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table summary="게시판등록" class="write01">
								<caption>게시판등록</caption>
									<tr>
										<th class="subject">아이디</th>
										<td class="input">
											<form:input path="bbsId" id="bbsId" title="게시판ID" cssClass="wi200"/>
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
									<tr>
										<th class="subject">게시판구분</th>
										<td class="input"><form:input path="grad" id="grad" title="게시판구분" cssClass="wi300"/></td>
									</tr>
									<tr>
										<th class="subject">소속</th>
										<td class="input">
											<table class="inside01">
												<tr>
													<th class="subject">기관</th>
													<td>
														<form:select path="searchCondition" >
															<form:option value="" label="선택하세요"/>
															<form:options items="${searchCondition}" />
														</form:select>
													</td>
												</tr>
												<tr>
													<th class="subject">부서</th>
													<td>
														<form:select path="searchCondition" >
															<form:option value="" label="선택하세요"/>
															<form:options items="${searchCondition}" />
														</form:select>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<th class="subject">비밀번호</th>
										<td class="input">
											<form:password path="password" id="password" title="비밀번호" cssClass="wi300"/>
										</td>
									</tr>
									<tr>
										<th class="subject">비밀번호 확인</th>
										<td class="input">
											<input type="password" id="password2" title="비밀번호 확인" class="wi300"/>
										</td>
									</tr>
									<tr>
										<th class="subject">EMAIL</th>
										<td class="input">
											<form:input path="email" id="email" title="EMAIL" cssClass="wi300"/>
										</td>
									</tr>
									<tr>
										<th class="subject">전화번호</th>
										<td class="input">
											<form:input path="telNo" id="telNo" title="전화번호" cssClass="wi300"/>
										</td>
									</tr>
									<tr>
										<th class="subject">휴대전화</th>
										<td class="input">
											<form:input path="moblphon" id="moblphon" title="휴대전화" cssClass="wi300"/>
										</td>
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
	</div>
	<input name="cmd" type="hidden" value="<c:out value='save'/>" />
	<input name="returnUrl" type="hidden" value="/gps/adm/bbs/modifyBbs.do" />
	<!-- container end -->
	</form:form>
</div>