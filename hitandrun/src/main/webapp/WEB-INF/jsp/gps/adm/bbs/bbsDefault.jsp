<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<jsp:include page="/gps/cmm/admHeader.do" flush="false"></jsp:include>
<script type="text/javascript">
JQ_onload();

function fncPageOnload()
{
	<c:if test="${!empty message}">
	alert('<c:out value = "${message}"/>');
	parent.leftBody.location.reload();
	</c:if>
}
</script>
<!-- ui-layout-center start  -->
<div class="ui-layout-center">
	<!-- container start -->
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
					<table class = "default">
						<tr>
							<td>
								<table class="title">
									<tr>
										<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="타이틀이미지" title="타이틀이미지"/></td>
										<td class="title">게시판명을 선택하세요.</td>
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
</div>
<!-- ui-layout-center end  -->
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>