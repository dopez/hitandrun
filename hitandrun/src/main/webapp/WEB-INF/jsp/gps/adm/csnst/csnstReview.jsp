<%-- 
/** 
 * 설명   : 만족도관리 - 만족도조사 미리보기화면
 * 파일명 : csnstReview.jsp
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
<link rel="stylesheet" type="text/css" href="/css/common/jquery/jquery.ui.all.css" />
<link rel="stylesheet" type="text/css" href="/css/common/jquery/jquery.ui.theme.css" />

<%-- javascript start ==============================================--%>
<script type="text/javaScript" language="javascript">
	JQ_setValidation('csnstRspnsVO');
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
function fncPageOnload() {
	<c:if test="${!empty message}">
		alert('<c:out value = "${message}"/>');
	</c:if>
}

<%-- 
/************************************************************************ 
함수명 : fn_rspns                                   
설   명 : 만족도조사 미리보기에서 결과반영함수
인   자 : 없음
사용법 : fn_rspns()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_rspns() {
	JQ_setValueObj('sysId', '${reviewVO.sysId}');
 	JQ_setValueObj('csnstId', '${reviewVO.csnstId}');
 	JQ_setValueObj('csnstSn', '${reviewVO.csnstSn}');
 	JQ_setValueObj('qesitmSn', '${reviewVO.qesitmSn}');
	JQ_request("csnstRspnsVO", "<c:url value='/gps/adm/csnst/insertCsnstRspns.do'/>");
}
</script>

<!-- contents_area start -->
<div class="contents_area">
	<div id="layer_csnst">
		<form:form commandName="csnstRspnsVO" name="csnstRspnsForm" method="post">
		<form:hidden path="sysId"/>
		<form:hidden path="csnstId"/>
		<form:hidden path="csnstSn"/>
		<form:hidden path="qesitmSn"/>
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
								<td class="title">
									<c:out value="만족도조사" />
								</td>
							</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table summary="만족도조사 미리보기" class="write01">
							<caption>
								만족도조사 미리보기
							</caption>
								<tr>
									<th class="subject">만족조사 명</th>
									<td class="input">
										<c:out value="${reviewVO.csnstNm}"/>
									</td>
								</tr>
								<tr>
									<th class="subject">만족도조사 질문명</th>
									<td class="text">
										<c:out value="${reviewVO.qesitmQestnNm}"/>
									</td>
								</tr>
								<tr>
									<th class="subject">만족도조사 항목</th>
									<td>
										<table class="inside01">
											<c:forEach var="resultInfo" items="${iemNmList}" varStatus="status">
												<tr>
													<td class="input">
														<c:out value="${resultInfo.iemNm}"></c:out>
													</td>
													<td class="text">
														<c:if test="${reviewVO.qesitmTy eq 'radio'}">
															<form:radiobutton cssClass="validate[required] radio" path="iemSn" value="${resultInfo.iemSn}"/>
														</c:if>
														<c:if test="${reviewVO.qesitmTy eq 'checkbox'}">
															<form:checkbox cssClass="validate[minCheckbox[2]] checkbox" path="iemSn" value="${resultInfo.iemSn}"/>
														</c:if>
													</td>
												</tr>
											</c:forEach>
											<c:if test="${fn:length(iemNmList) == 0}">
												<tr>
													<td class="blank" colspan="10">
														확인 불가능한 만족도 조사입니다. 1.만족도조사 - 2.만족도 조사 문항 - 3.만족도 조사항목 을 등록해주세요.
													</td>
												</tr>
											</c:if>
										</table>
									</td>
								</tr>
								<tr>
									<th class="subject">만족도조사 메모</th>
									<td class="text">
										<form:textarea path="memoCn" rows="5" cols="65" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<%-- 
					<tr>
						<td>
							<table class="rbuttonarea">
							<tr>
							<c:if test="${fn:length(iemNmList) > 0}">
								<td><a onclick="fn_rspns();"><img src="/images/button/0414.png" alt="참여하기" title="참여하기"></a></td>
							</c:if>
								<td class="end"><a onclick="window.close();" style="cursor:hand"><img src="/images/button/0205.png" alt="<spring:message code='button.close'/>" title="<spring:message code='button.close'/>"></a></td>
							</tr>
							</table>
						</td>
					</tr>
					--%>
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
</div>
<!-- container end -->
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>