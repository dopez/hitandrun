<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<jsp:include page="/gps/cmm/admHeader.do"></jsp:include>
<script type="text/javascript">
	JQ_setValidation('systemVO');
	JQ_setCalendar('bgnde','endde','from');
	JQ_setCalendar('endde','bgnde','to');

	<%-- 
	/************************************************************************ 
	fnc name : fn_insert                                 
	outline : 메뉴등록처리함수
	parameter : 없음  
	directions : fn_insert      
	since : 2011-07-13   
	author : 통계포털  황기연       
	
	    date      author             note  
	 ----------   -------     ------------------- 
	 2011.06.12    황기연                          최초 생성                
	************************************************************************/ 
	--%>
	function fn_insert(){
		JQ_setProcessMsg();
		JQ_request("systemVO", "<c:url value='/gps/adm/menu/insertSystem.do'/>");
	}

	<%-- 
	/************************************************************************ 
	fnc name : fn_searchPrdctn                                 
	outline : 조사회차검색
	parameter : 없음  
	directions : fn_searchPrdctn      
	since : 2011-07-13   
	author : 통계포털  황기연       
	
	    date      author             note  
	 ----------   -------     ------------------- 
	 2011.06.12    황기연                          최초 생성                
	************************************************************************/ 
	--%>
	function fn_searchPrdctn(val){
		if(val != ''){
		var options = { 
				success     :fn_success,
	            error       :fn_error,
                url         :'/gps/adm/menu/selectPrdctnList.do',			                 
                contentType :'application/x-www-form-urlencoded; charset=UTF-8',
                type        :'post',
                dataType    :'json'
              };   
			  JQ_requestAjax('systemVO',options);
		}else{
			$('#svyOdr').val('0');
		}
	}

	<%-- 
	/************************************************************************ 
	fnc name : fn_prdctn_success                                   
	outline :  조사목록 호출성공
	parameter : response json객체 
	directions : fn_prdctn_success()              
	since : 2011-05-10   
	author : 통계포털 황기연

	    date      author             note  
	 ----------   -------     ------------------- 

	 ************************************************************************/
	 --%>
	function fn_success(response) 
	{
		var svyOdr = response.svyOdr;
		$('#svyOdr').val(svyOdr);
	}

	<%-- 
	/************************************************************************ 
	fnc name : fn_error                                   
	outline :  ajax 호출에 실패했을떄 호출되는 함수  
	parameter : error json객체 
	directions : fn_error()              
	since : 2011-05-10   
	author : 기술지원 김일수       

	    date      author             note  
	 ----------   -------     ------------------- 

	 ************************************************************************/
	 --%>
	function fn_error( error ) {
		if( error.statusText == "error" ) {
		   alert( "<spring:message code="fail.common.msg" />" );
		}
	}
</script>
<!-- container start -->
<div class="contents_area">
	<form:form commandName="systemVO" method="post">
	<c:set var="sysSe" value="${systemVO.sysSe=='sys'?'시스템':'사이트'}"></c:set>
	<input type="hidden" name="orgId" id="orgId" value="${gpsSessionVO.orgId}"/>
	<form:hidden path="rootSysId"/>
	<form:hidden path="sysSe"/>
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
				<!-- 시스템메뉴 -->
				<tr>
					<td>
						<table class="title">
						<tr>
							<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="<spring:message code="gps.titleImage"/>" title="<spring:message code="gps.titleImage"/>"/></td>
							<td class="title"><c:out value="${sysSe}"/>추가</td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="write01">
						<tr>
							<th class="reqsubject"><c:out value="${sysSe}"/>명</th>
							<td class="input"><form:input path="sysNm" cssClass="wi250 validate[required,length[1,50]]"/></td>
						</tr>
						<tr>
							<th class="subject"><c:out value="${sysSe}"/>단축명</th>
							<td class="input"><form:input path="sysAbrv" cssClass="wi250 validate[optional,length[1,50]]"/></td>
						</tr>
						<tr>
							<th class="subject"><c:out value="${sysSe}"/>영문명</th>
							<td class="input"><form:input path="sysEngNm" cssClass="wi250 validate[optional,length[1,50]]"/></td>
						</tr>
					<c:if test="${systemVO.sysSe eq 'site'}">
						<tr>
							<th class="subject">생산ID</th>
							<td class="input">
								<form:select path="prdctnId" cssClass="validate[required]" onchange="fn_searchPrdctn(this.value);">
									<form:option value="">- 선택 -</form:option>
									<c:forEach var="list" items="${prdctnList}" varStatus="status">
										<form:option value="${list.prdctnId}"><c:out value="${list.svyNm}"/></form:option>
									</c:forEach>
								</form:select>
							</td>
						</tr>
						<tr>
							<th class="subject">회차</th>
							<td class="input">
								<form:input path="svyOdr" cssClass="wi30" readonly="true"/> 회차
							</td>
						</tr>
					</c:if>
						<tr>
							<th class="subject"><c:out value="${sysSe}"/>관리 메뉴</th>
							<td>
								<table class="inside01">
								<tr>
									<th class="subject">사용자관리</th>
									<td>
										<table class="inner01">
										<tr>
											<td class="bottom_noline"><form:radiobutton path="userManageUseAt" id="userManageUseAtY" value="Y" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="userManageUseAtY" title="사용">사용</label></td>
											<td class="input_bottom_noline"><form:radiobutton path="userManageUseAt" id="userManageUseAtN" value="N" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="userManageUseAtN" title="사용안함">사용안함</label></td>
										</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="subject">권한관리</th>
									<td>
										<table class="inner01">
										<tr>
											<td class="bottom_noline"><form:radiobutton path="authorManageUseAt" id="authorManageUseAtY" value="Y" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="authorManageUseAtY" title="사용">사용</label></td>
											<td class="input_bottom_noline"><form:radiobutton path="authorManageUseAt" id="authorManageUseAtN" value="N" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="authorManageUseAtN" title="사용안함">사용안함</label></td>
										</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="subject">메뉴관리</th>
									<td>
										<table class="inner01">
										<tr>
											<td class="bottom_noline"><form:radiobutton path="menuManageUseAt" id="menuManageUseAtY" value="Y" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="menuManageUseAtY" title="사용">사용</label></td>
											<td class="input_bottom_noline"><form:radiobutton path="menuManageUseAt" id="menuManageUseAtN" value="N" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="menuManageUseAtN" title="사용안함">사용안함</label></td>
										</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="subject">공통코드관리</th>
									<td>
										<table class="inner01">
										<tr>
											<td class="bottom_noline"><form:radiobutton path="codeManageUseAt" id="codeManageUseAtY" value="Y" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="codeManageUseAt" title="사용">사용</label></td>
											<td class="input_bottom_noline"><form:radiobutton path="codeManageUseAt" id="codeManageUseAtN" value="N" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="codeManageUseAt" title="사용안함">사용안함</label></td>
										</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="bottom_noline">프로그램관리</th>
									<td class="bottom_noline">
										<table class="inner01">
										<tr>
											<td class="bottom_noline"><form:radiobutton path="programManageUseAt" id="programManageUseAtY" value="Y" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="programManageUseAt" title="사용">사용</label></td>
											<td class="input_bottom_noline"><form:radiobutton path="programManageUseAt" id="programManageUseAtN" value="N" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="programManageUseAt" title="사용안함">사용안함</label></td>
										</tr>
										</table>
									</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th class="subject">콘텐츠관리 메뉴</th>
							<td>
								<table class="inside01">
								<tr>
									<th class="subject">메인이미지관리</th>
									<td>
										<table class="inner01">
										<tr>
											<td class="bottom_noline"><form:radiobutton path="mainImageManageUseAt" id="mainImageManageUseAtY" value="Y" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="mainImageManageUseAtY" title="사용">사용</label></td>
											<td class="input_bottom_noline"><form:radiobutton path="mainImageManageUseAt" id="mainImageManageUseAtN" value="N" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="mainImageManageUseAt" title="사용안함">사용안함</label></td>
										</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="subject">로그인이미지관리</th>
									<td>
										<table class="inner01">
										<tr>
											<td class="bottom_noline"><form:radiobutton path="loginImageManageUseAt" id="loginImageManageUseAtY" value="Y" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="loginImageManageUseAt" title="사용">사용</label></td>
											<td class="input_bottom_noline"><form:radiobutton path="loginImageManageUseAt" id="loginImageManageUseAtN" value="N" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="loginImageManageUseAt" title="사용안함">사용안함</label></td>
										</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="subject">게시판관리</th>
									<td>
										<table class="inner01">
										<tr>
											<td class="bottom_noline"><form:radiobutton path="boardManageUseAt" id="boardManageUseAtY" value="Y" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="boardManageUseAt" title="사용">사용</label></td>
											<td class="input_bottom_noline"><form:radiobutton path="boardManageUseAt" id="boardManageUseAtN" value="N" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="boardManageUseAt" title="사용안함">사용안함</label></td>
										</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="subject">추천사이트관리</th>
									<td>
										<table class="inner01">
										<tr>
											<td class="bottom_noline"><form:radiobutton path="recomendSiteManageUseAt" id="recomendSiteManageUseAtY" value="Y" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="recomendSiteManageUseAt" title="사용">사용</label></td>
											<td class="input_bottom_noline"><form:radiobutton path="recomendSiteManageUseAt" id="recomendSiteManageUseAtN" value="N" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="recomendSiteManageUseAt" title="사용안함">사용안함</label></td>
										</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="subject">약관관리</th>
									<td>
										<table class="inner01">
										<tr>
											<td class="bottom_noline"><form:radiobutton path="stplatManageUseAt" id="stplatManageUseAtY" value="Y" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="stplatManageUseAt" title="사용">사용</label></td>
											<td class="input_bottom_noline"><form:radiobutton path="stplatManageUseAt" id="stplatManageUseAtN" value="N" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="stplatManageUseAt" title="사용안함">사용안함</label></td>
										</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="subject">만족도조사</th>
									<td>
										<table class="inner01">
										<tr>
											<td class="bottom_noline"><form:radiobutton path="snstUseAt" id="snstUseAtY" value="Y" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="snstUseAtY" title="사용">사용</label></td>
											<td class="input_bottom_noline"><form:radiobutton path="snstUseAt" id="snstUseAtN" value="N" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="snstUseAtN" title="사용안함">사용안함</label></td>
										</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="subject">배너관리</th>
									<td>
										<table class="inner01">
										<tr>
											<td class="bottom_noline"><form:radiobutton path="bannerManageUseAt" id="bannerManageUseAtY" value="Y" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="bannerManageUseAtY" title="사용">사용</label></td>
											<td class="input_bottom_noline"><form:radiobutton path="bannerManageUseAt" id="bannerManageUseAtN" value="N" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="bannerManageUseAtN" title="사용안함">사용안함</label></td>
										</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="subject">설문조사</th>
									<td>
										<table class="inner01">
										<tr>
											<td class="bottom_noline"><form:radiobutton path="qestnarUseAt" id="qestnarUseAtY" value="Y" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="qestnarUseAtY" title="사용">사용</label></td>
											<td class="input_bottom_noline"><form:radiobutton path="qestnarUseAt" id="qestnarUseAtN" value="N" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="qestnarUseAtN" title="사용안함">사용안함</label></td>
										</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="subject">스케줄관리</th>
									<td>
										<table class="inner01">
										<tr>
											<td class="bottom_noline"><form:radiobutton path="schdulManageUseAt" id="schdulManageUseAtY" value="Y" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="schdulManageUseAtY" title="사용">사용</label></td>
											<td class="input_bottom_noline"><form:radiobutton path="schdulManageUseAt" id="schdulManageUseAtN" value="N" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="schdulManageUseAtN" title="사용안함">사용안함</label></td>
										</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="subject">이벤트관리</th>
									<td>
										<table class="inner01">
										<tr>
											<td class="bottom_noline"><form:radiobutton path="eventManageUseAt" id="eventManageUseAtY" value="Y" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="eventManageUseAtY" title="사용">사용</label></td>
											<td class="input_bottom_noline"><form:radiobutton path="eventManageUseAt" id="eventManageUseAtN" value="N" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="eventManageUseAtN" title="사용안함">사용안함</label></td>
										</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="subject">접속통계</th>
									<td>
										<table class="inner01">
										<tr>
											<td class="bottom_noline"><form:radiobutton path="conectStatsUseAt" id="conectStatsUseAtY" value="Y" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="conectStatsUseAtY" title="사용">사용</label></td>
											<td class="input_bottom_noline"><form:radiobutton path="conectStatsUseAt" id="conectStatsUseAtN" value="N" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="conectStatsUseAtN" title="사용안함">사용안함</label></td>
										</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="bottom_noline">팝업관리</th>
									<td class="bottom_noline">
										<table class="inner01">
										<tr>
											<td class="bottom_noline"><form:radiobutton path="popupManageUseAt" id="popupManageUseAtY" value="Y" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="popupManageUseAtY" title="사용">사용</label></td>
											<td class="input_bottom_noline"><form:radiobutton path="popupManageUseAt" id="popupManageUseAtN" value="N" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="popupManageUseAtN" title="사용안함">사용안함</label></td>
										</tr>
										</table>
									</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th class="subject">기간</th>
							<td>
								<table class="inside01">
								<tr>
									<th class="subject">시작일</th>
									<td>
										<table class="inner01">
										<tr>
											<td class="input_bottom_noline"><form:input path="bgnde" cssClass="input_t input_nara_date wi100" /></td>
										</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="bottom_noline">종료일</th>
									<td class="bottom_noline">
										<table class="inner01">
										<tr>
											<td class="input_bottom_noline"><form:input path="endde" size="10" cssClass="input_t input_nara_date wi100" /></td>
										</tr>
										</table>
									</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th class="subject">사용여부</th>
							<td>
								<table class="inside02">
								<tr>
									<td class="input"><form:radiobutton path="useAt" id="useAtY" value="Y" cssClass="noline"/></td>
									<td><label for="useAtY" title="사용">사용</label></td>
									<td class="input"><form:radiobutton path="useAt" id="useAtN" value="N" cssClass="noline"/></td>
									<td><label for="useAtN" title="사용안함">사용안함</label></td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th class="subject">비고</th>
							<td class="input"><form:textarea path="sysRm" cssClass="wi500 ht50 validate[optional,length[1,1000]]"/></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="rbuttonarea">
						<tr>
							<td class="end"><img src="/images/button/0210.png" style="cursor:hand;" alt="<spring:message code="button.save"/>" title="<spring:message code="button.save"/>" onclick="fn_insert();"/></td>
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