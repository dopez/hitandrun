<%-- 
/** 
 * outline   : 통합권한 관리 - 권한트리화면
 * filename : iamList.jsp
 * @author 범정부통계포털 이관형 
 * @since 2011.08.18
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.06.23     이관형           최초 생성 
 * </pre> 
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<jsp:include page="/gps/cmm/admHeader.do"></jsp:include>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#navigation").treeview({
			collapsed:false,
			animated:"fast",
			control:"#sidetreecontrol"
		});
	});
	
	<%-- 
	/************************************************************************ 
	함수명 : fn_regist                                   
	설   명 : 권한 등록 화면으로 이동하는 함수
	인   자 : 없음
	사용법 : fn_regist()              
	작성일 : 2011-06-17   
	작성자 : 범정부통계포털 이관형       

 	*    date       author                note 
 	* ----------    -------    --------------------------- 
 	* 2011.06.23     이관형           최초 생성 
	************************************************************************/
	 --%>
	 function fn_regist(){
		 parent.rightBody.location.href = "/gps/adm/author/intergrated/registerIam.do?authorId=" + $("#authorId").val() + "&sysId=" + $("#sysId").val() + "&upperMenuId=" + $("#upperMenuId").val();
	}
		
	 <%-- 
	 /************************************************************************ 
	 함수명 : fn_modify                                   
	 설   명 : 권한 조회/수정 화면으로 이동하는 함수
	 인   자 : 없음
	 사용법 : fn_modify()              
	 작성일 : 2011-06-17   
	 작성자 : 범정부통계포털 이관형       

	 *    date       author                note 
	 * ----------    -------    --------------------------- 
	 * 2011.06.23     이관형           최초 생성 
	 ************************************************************************/
	 --%>
	 function fn_modify(){
		parent.rightBody.location.href = "/gps/adm/author/intergrated/modifyIam.do?authorId=" + $("#authorId").val() + "&sysId=" + $("#sysId").val() + "&upperMenuId=" + $("#upperMenuId").val();

	}
	 <%-- 
	 /************************************************************************ 
	 함수명 : fn_sublist                                   
	 설   명 : 권한 리스트 화면으로 이동하는 함수
	 인   자 : 없음
	 사용법 : fn_sublist()              
	 작성일 : 2011-06-17   
	 작성자 : 범정부통계포털 이관형       

	 *    date       author                note 
	 * ----------    -------    --------------------------- 
	 * 2011.06.23     이관형           최초 생성 
	 ************************************************************************/
	 --%>
	function fn_sublist(sysId, authorId, authorNm) {
		parent.rightBody.location.href = "/gps/adm/author/intergrated/selectIamMenuList.do?sysId=" + sysId + "&authorId=" + authorId + "&authorNm=" + escape(encodeURIComponent(authorNm));
		
	 	JQ_setValueObj('authorId', authorId);
	 	JQ_setValueObj('authorNm', authorNm);
	 	$("#sysId").val(sysId);
   		JQ_request("authorManageVO", "<c:url value='/gps/adm/author/intergrated/selectIamList.do'/>");
		
	}

	</script>
<!-- container start -->
<div class="contents_area">
	<form:form commandName="authorManageVO" name="listForm" method="post">
	<form:hidden path="upperMenuId"/>
	<form:hidden path="sysId"/>
	<form:hidden path="authorId"/>
	<form:hidden path="authorNm"/>
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
							<td class="title">
								<c:choose>
									<c:when test="${!empty authorManageVO.authorNm}">
										<c:out value="${authorManageVO.authorNm}" />
									</c:when>
									<c:otherwise>
										<c:out value="권한관리" />
									</c:otherwise>
								</c:choose>
							</td>
							<c:if test="${authorManageVO.authorId != null}">
								<td class="rbtn">
									<table class="inner_btn">
									<tr>
										<td><a onclick="javascript:fn_modify();" style="cursor: pointer;"><img src="/images/gps/adm/author/0461.png" alt="<spring:message code="button.update"/>" title="<spring:message code="button.update"/>"></a></td>
										<td class="end"><a onclick="javascript:fn_regist();" style="cursor: pointer;"><img src="/images/button/0419.png" alt="권한추가" title="권한추가"></a></td>
									</tr>
									</table>
								</td>
							</c:if>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="default">
						<tr>
							<td>
								<c:choose>
									<c:when test="${!empty authorList}">
									<div id="sidetreecontrol"><a href="?#"><img src="/images/gps/adm/icon/042.gif" alt="접기" title="접기"/></a>&nbsp;<a href="?#"><img src="/images/gps/adm/icon/041.gif" alt="펼치기" title="펼치기"/></a></div>
									<ul id="navigation">
										<c:set var="tempTreeLv" value="0" />
										<c:forEach var="list" items="${authorList}" varStatus="status">
											<c:choose>
											<c:when test="${list.treeLv == 1}">
												<c:if test="${status.count > 1}">
													<c:forEach begin="1" end="${tempTreeLv}" step="1" varStatus="ulCnt">
														<c:out value="</li>" escapeXml="false"/>
														<c:if test="${ulCnt.count < tempTreeLv}">
															<c:out value="</ul>" escapeXml="false"/>
														</c:if>
													</c:forEach>
												</c:if>
												<c:out value="<li>" escapeXml="false"/>
												<a onclick="javascript:fn_sublist('<c:out value="${list.sysId}"/>', '<c:out value="${list.authorId}"/>', '<c:out value="${list.authorNm}"/>');" style="cursor:hand">
													<span class="<c:out value="folder"/>" <c:if test="${list.authorId eq authorManageVO.authorId}">style="font-weight:bold;"</c:if>>
														<c:out value="${list.sysNm}"/> - <c:out value="${list.authorNm}"/>
													</span>
												</a>
											</c:when>
											<c:otherwise>
												<c:if test="${tempTreeLv > list.treeLv}">
													<c:forEach begin="1" end="${tempTreeLv-list.treeLv + 1}" step="1" varStatus="ulCnt">
														<c:out value="</li>" escapeXml="false"/>
														<c:if test="${ulCnt.count < tempTreeLv-list.treeLv + 1}">
															<c:out value="</ul>" escapeXml="false"/>
														</c:if>
													</c:forEach>
												</c:if>
												<c:if test="${tempTreeLv < list.treeLv}">
													<c:out value="<ul>" escapeXml="false"/>
												</c:if>
												<c:out value="<li>" escapeXml="false"/>
												<a onclick="javascript:fn_sublist('<c:out value="${list.sysId}"/>', '<c:out value="${list.authorId}"/>', '<c:out value="${list.authorNm}"/>');">
													<span class="<c:out value="file"/>" <c:if test="${list.authorId eq authorManageVO.authorId}">style="font-weight:bold;"</c:if>>
														<c:out value="${list.authorNm}"/>
													</span>
												</a>
											</c:otherwise>
											</c:choose>
											
											<c:set var="tempTreeLv" value="${list.treeLv}" />
											<c:out value="${status.count == fn:length(authorList) ? '</li>' : ''}" escapeXml="false"/>
			
										</c:forEach>
				
									</ul>
								</c:when>
								<c:otherwise>
									<div><spring:message code="common.search.nodata.msg"/></div>
								</c:otherwise>
							</c:choose>						
							</td>
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