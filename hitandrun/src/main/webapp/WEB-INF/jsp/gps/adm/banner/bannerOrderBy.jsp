<%-- 
/** 
 * outline   : 배너관리 - 순서변경 화면
 * filename : bannerOrderBy.jsp
 * @author 범정부통계포털 이관형 
 * @since 2011.06.17
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.06.17     이관형           최초 생성 
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

<%-- 
/************************************************************************ 
함수명 : fn_search                                   
설   명 : 배너 순서변경
인   자 : 없음        
사용법 : fn_search()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 
 
************************************************************************/
--%>
function fn_search(){
	JQ_request("bannerManageVO", "<c:url value='/gps/adm/banner/bannerOrderBy.do'/>", "bannerManageVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_regist                                   
설   명 : 글등록 함수
인   자 : 없음        
사용법 : fn_regist()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 
 
************************************************************************/
--%>
function fn_regist() {
	window.close();
   	document.updateForm.action = "<c:url value='/gps/adm/banner/updateBannerOrderBy.do'/>";
   	document.updateForm.submit();		
   	opener.fn_search();
}
</script>
<%-- javascript end ============================================--%>

<!-- contents start  -->
<form:form commandName="bannerManageVO" name="updateForm" method="post">
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
						<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="배너 순서변경" title="배너 순서변경"></td>
						<td class="title">배너 순서변경</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table class="write01">			
					<tr>
						<th class="subject">시스템명</th>
						<td>
							<table class="inner01">
							<tr>
								<td class="bottom_noline">
									<form:select path="sysId" onchange="javaScript:fn_search();">
										<form:option value="" label="- 선택 -"/>
										<form:options items="${sysMap}"/>
									</form:select>
								</td>
							</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th class="subject">배너명</th>
						<td>
							<table class="inner01">
							<tr>
								<td class="bottom_noline">
									<form:select path="bannerSn">
										<form:option value="" label="- 선택 -"/>
										<form:options items="${bannerMap}"/>
									</form:select>
								</td>
							</tr>
							</table>
						</td>
					</tr>
					<tr>
						<th class="subject">위치</th>
						<td>
							<table class="inner01">
							<tr>
								<td class="bottom_noline">
								<form:select path="lc" >
									<form:option value="" label="- 선택 -"/>
									<form:option value="1" label="위치1" />
									<form:option value="2" label="위치2" />
									<form:option value="3" label="위치3" />
								</form:select>
								</td>
							</tr>
							</table>
						</td>
					</tr>									
					<tr>
						<th class="subject">순서</th>
						<td>
							<table class="inner01">
							<tr>
								<td class="bottom_noline">
									<form:input path="ordr" size="50" cssClass="wi50" maxlength="50" />
								</td>
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
						<td><a onclick="fn_regist();" style="cursor: pointer;"><img src="/images/button/0210.png" alt="<spring:message code="button.save" />" title="<spring:message code="button.save" />"></a></td>
						<td class="end"><a onclick="window.close();" style="cursor: pointer;"><img src="/images/button/0205.png" alt="<spring:message code="button.close" />" title="<spring:message code="button.close" />"></a></td>
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
</form:form>
<!-- container end -->
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>