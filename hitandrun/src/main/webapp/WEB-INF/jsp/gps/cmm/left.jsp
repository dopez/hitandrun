<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%--
**********************************************************************************
* @source      : header.jsp
* @description : 헤더  JSP - 세션/사용자/권한 처리
*                   
**********************************************************************************
* DATE         AUTHOR    DESCRIPTION
*=================================================================================
* 2011-04-18   이진우           최초작성
**********************************************************************************
--%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<%-- Top Start ==========================================================--%>
<jsp:include page="/gps/cmm/top.do"></jsp:include>

<script type="text/javascript">
	
	function leftStatMenuImage(val) {
		statMenuImg = "#" + "leftStatMenuImg";
		OverMenuImg = "#" + "leftOverMenuImg";
		<c:forEach var="menuList" items="${leftMenuList}" varStatus="status">
			if (val == ${status.count}) {
				  $(statMenuImg + ${status.count}).hide();
				  $(OverMenuImg + ${status.count}).css( "visibility", "visible");
			 	  $(OverMenuImg + ${status.count}).show();
			} else {
			  	  $(statMenuImg + ${status.count}).show();
			   	  $(OverMenuImg + ${status.count}).hide();
			}
		</c:forEach>
	}

	function leftStatMenuImageOut() {
		statMenuImg = "#" + "leftStatMenuImg";
		OverMenuImg = "#" + "leftOverMenuImg";
		<c:forEach var="menuList" items="${leftMenuList}" varStatus="status">
	  	  $(statMenuImg + ${status.count}).show();
	   	  $(OverMenuImg + ${status.count}).hide();
		</c:forEach>
	}

</script>
<%-- Top Start ==========================================================--%>
	
<%-- 좌측 메뉴 영역  Start ======================================================--%>
<c:choose>
	<c:when test="${empty menuManageVO.menuSkin}">
	<!-- subpage layer start -->
	<!-- sub container start -->
	<div id="sub_container">
		<div id="subpage_visual">
			<p class="visual_contents"><img src="/images/gps/cmm/subpage/sub_im.gif" alt="통계산업화의 첫걸음 국가통계 품질향상을 주도하는 국가통계의 발전을 선도합니다." title="통계산업화의 첫걸음 국가통계 품질향상을 주도하는 국가통계의 발전을 선도합니다."></p>
		</div>
		<!-- subpage container start -->
		<div id="subpage_container">
			<!-- sub menu start -->
			<div id="subpage_menu">
		<c:choose>
			<c:when test="${menuManageVO.menuImgFlg eq true}">
				<c:forEach var="menuList" items="${leftMenuList}" varStatus="status">
					<ul>
						<li>
							<span>
								<c:choose>
									<c:when test="${menuList.menuLv > 1}">
										<c:if test="${((menuList.menuUrl ne '/gps/login/gpsLoginUsr.do' && menuList.menuUrl ne '/gps/user/actionRegistUser.do') || empty gpsSessionVO)}">
											<c:if test="${((menuList.menuUrl ne '/gps/login/actionLogout.do' && menuList.menuUrl ne '/gps/user/modifyUser.do') || !empty gpsSessionVO)}">
												<a onmouseout="leftStatMenuImageOut()" onmouseover="leftStatMenuImage(${status.count});" onblur="leftStatMenuImageOut()" onfocus="leftStatMenuImage(${status.count})" href="<c:url value="${menuList.menuUrl}"><c:param name="leftMenuId" value="${menuList.upperMenuId}"/><c:param name="menuId" value="${menuList.menuId}"/><c:param name="bbsId" value="${menuList.bbsId}"/></c:url>">
									<c:choose>
										<c:when test="${menuManageVO.menuId eq menuList.menuId}">
											<img title="${menuList.menuNm}" alt="${menuList.menuNm}" src="${WebImagePath}?imageid=web/gps/menu/${menuList.leftImageMouseoverMask}&ext=${menuList.leftImageMouseoverMime}" />
										</c:when>
										<c:otherwise>
											<img title="${menuList.menuNm}" alt="${menuList.menuNm}" id="leftStatMenuImg${status.count}" src="${WebImagePath}?imageid=web/gps/menu/${menuList.leftImageMask}&ext=${menuList.leftImageMime}"/>
											<img title="${menuList.menuNm}" alt="${menuList.menuNm}" id="leftOverMenuImg${status.count}" src="${WebImagePath}?imageid=web/gps/menu/${menuList.leftImageMouseoverMask}&ext=${menuList.leftImageMouseoverMime}" style="visibility:hidden" />
										</c:otherwise>
									</c:choose>
												</a>
											</c:if>
										</c:if>
									</c:when>
									<c:otherwise>
										<img title="${menuList.menuNm}" alt="${menuList.menuNm}" id="leftStatMenuImg${status.count}" src="${WebImagePath}?imageid=web/gps/menu/${menuList.leftImageMask}&ext=${menuList.leftImageMime}"/>
									</c:otherwise>
								</c:choose>
							</span>
						</li>
					</ul>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<c:forEach var="menuList" items="${leftMenuList}" varStatus="status">
					<ul>
						<li>
							<span <c:if test="${menuList.menuId eq menuManageVO.menuId}">style="font-weight:bold;"</c:if>>
								<c:choose>
									<c:when test="${menuList.menuLv > 1}">
										<c:if test="${((menuList.menuUrl ne '/gps/login/gpsLoginUsr.do' && menuList.menuUrl ne '/gps/user/actionRegistUser.do') || empty gpsSessionVO)}">
											<c:if test="${((menuList.menuUrl ne '/gps/login/actionLogout.do' && menuList.menuUrl ne '/gps/user/modifyUser.do') || !empty gpsSessionVO)}">
												<a <c:if test="${menuList.menuLv > 1}"> onmouseover="leftStatMenuImage(${status.count});" onblur="leftStatMenuImage(${status.count});" href="<c:url value="${menuList.menuUrl}"><c:param name="leftMenuId" value="${menuList.upperMenuId}"/><c:param name="menuId" value="${menuList.menuId}"/><c:param name="bbsId" value="${menuList.bbsId}"/></c:url>"</c:if>>
													<c:out value="${menuList.menuNm}"/>
												</a>
											</c:if>
										</c:if>
									</c:when>
									<c:otherwise>
										<c:out value="${menuList.menuNm}"/>
									</c:otherwise>
								</c:choose>
							</span>
						</li>
					</ul>
				</c:forEach>
			</c:otherwise>
		</c:choose>
			</div>
			<!-- sub menu end -->
		
			<!-- content start -->
			<div id="subpage_contents">
				<!-- contents page_title start -->
				<div class="page_title">
					<div class="location">
						<ul>
							<li class="home"><a href="/"><img src="/images/gps/cmm/icon/icon_history_home.gif" alt="처음으로" title="처음으로"></a></li>
							<c:forEach var="naviList" items="${naviList}" varStatus="status">
								<li class="path"> > <c:out value="${naviList.menuNm}" /></li>
							</c:forEach>
						</ul>
					</div>
				</div>
				
		<c:choose>
			<c:when test="${menuManageVO.titleImageSize > 0}">
				<div class="page_Imgtitle">
					<h3>
						<img src="${WebImagePath}?imageid=web/gps/menu/${menuManageVO.titleImageMask}&ext=${menuManageVO.titleImageMime}" title="${menuManageVO.menuNm} 타이틀입니다" alt="${menuManageVO.menuNm} 타이틀입니다"/>
					</h3>
				</div>
			</c:when>
			<c:otherwise>
				<div class="page_Texttitle">
					<h3><c:out value="${menuManageVO.menuNm}"/></h3>
				</div>
			</c:otherwise>
		</c:choose>
				<!-- contents page_title end -->
		</c:when>
	
	<c:when test="${menuManageVO.menuSkin eq 'index'}">
	<!-- index layer start -->
	<!-- container start -->
	<div id="container">
	</c:when>
</c:choose>
<%-- 좌측 메뉴 영역  Start ======================================================--%>