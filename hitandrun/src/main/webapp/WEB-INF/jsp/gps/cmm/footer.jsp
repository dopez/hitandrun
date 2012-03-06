<%--
*******************************************************************************
* @source      : footer.jsp
* @description : 관리자 아래쪽 부분 JSP - 세션/사용자/권한 처리
*                   
*******************************************************************************
* DATE         AUTHOR    DESCRIPTION
*==============================================================================
* 2011-04-18   이진우           최초작성
*******************************************************************************
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%-- 공통 taglib 선언 Start	================================================--%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<%-- 공통 taglib 선언 End	====================================================--%>

<%-- 공통 import 선언 Start	================================================--%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="java.util.*" %>
<%-- 공통 import 선언 End		============================================--%> 

<%-- 선행 로직  Start	====================================================--%>

<%-- 선행 로직  End	========================================================--%>
<c:choose>
	<c:when test="${empty menuManageVO.menuSkin}">
			</div>
			<!--content end -->
			<c:if test="${menuManageVO.snstUseAt eq 'Y'}">
				<c:if test="${not empty menuManageVO.csnstId}">
					<jsp:include page="/gps/csnst/satisfactionReview.do" flush="false">
						<jsp:param name="csnstId" value="${menuManageVO.csnstId}"></jsp:param>
					</jsp:include>
				</c:if>
			</c:if>
		</div>
		<!-- subpage_container end -->
	</div>
	<!-- sub_container end -->
	
	</c:when>
	<c:when test="${menuManageVO.menuSkin eq 'index'}">
			<%--<jsp:include page="/gps/banner/bannerMainList.do" flush="false">
				<jsp:param name="sysId" value="${menuManageVO.sysId}"></jsp:param>
			</jsp:include>
			--%>
		</div>
		<!-- container end -->
	</c:when>
</c:choose>

	<!-- footer start -->
	<div id="footer">
		<div class="footer_content">
			<div class="footer_menu">
				<ul>
					<li class="first"><a href="/gps/contents/guidance/service.do?menuId=0010001100126127&leftMenuId=0010001100126"><img src="/images/gps/bottom/bottom_manu_01.gif" alt="서비스안내" title="서비스안내"/></a></li>
					<li><a href="/gps/contents/guidance/agreement.do?menuId=0010001100126128&leftMenuId=0010001100126"><img src="/images/gps/bottom/bottom_manu_02.gif" alt="이용약관" title="이용약관"/></a></li>
					<li><a href="/gps/contents/guidance/person.do?menuId=0010001100126129&leftMenuId=0010001100126"><img src="/images/gps/bottom/bottom_manu_03.gif" alt="개인정보호정책" title="개인정보호정책"/></a></li>
					<li class="last"><a href="/gps/contents/guidance/email.do?menuId=0010001100126130&leftMenuId=0010001100126"><img src="/images/gps/bottom/bottom_manu_04.gif" alt="이메일무단수집거부" title="이메일무단수집거부"/></a></li>
					<li class="copyright"><img src="/images/gps/bottom/bottom_copyright.gif" alt="우편번호 302-701 대전광역시 서구 청사로 189(둔산동 920) 정부대전청사 3동청사 대표전화 042-481-4114 입니다." title="우편번호 302-701 대전광역시 서구 청사로 189(둔산동 920) 정부대전청사 3동청사 대표전화 042-481-4114 입니다."></li>
				</ul>
			</div>
			<div class="footer_logo"><img src="/images/gps/bottom/bottom_logo.gif" alt="통계청" title="통계청"></div>
		</div>
	</div>
	<!-- footer end -->
	<!-- footer end -->
</div>
<!-- wrap end -->
</body>
</html>