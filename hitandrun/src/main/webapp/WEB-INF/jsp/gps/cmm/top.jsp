<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
*******************************************************************************
* @source      : top.jsp
* @description : 헤더  JSP - 세션/사용자/권한 처리
*                   
*******************************************************************************
* DATE         AUTHOR    DESCRIPTION
*==============================================================================
* 2011-04-18   이진우           최초작성
*******************************************************************************
--%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<%-- Header Start ==========================================================--%>
<jsp:include page="/gps/cmm/header.do"></jsp:include>
<script type="text/javascript">

function topShowStatLayer(val) {
	var LayerName;
	var menuLength = ${fn:length(topMenuList)};
	for (var i=1;i<=menuLength;i++) {
		LayerName = "topSubMenuLayer"+i;
		if (i==val) {
			document.getElementById(LayerName).style.display = "block";
			subTopStatMenuImage(0);
		} else {
			if($("#"+LayerName).length > 0) {
				document.getElementById(LayerName).style.display = "none";
			}
		}
	}
}

function topStatMenuImage(val) {
	statMenuImg = "#" + "topStatMenuImg";
	OverMenuImg = "#" + "topOverMenuImg";
	
	<c:forEach var="menuList" items="${topMenuList}" varStatus="status">

		<c:if test="${menuList.menuLv eq 2}">
			if (val == ${status.count}) {
				  $(statMenuImg + ${status.count}).hide();
				  $(OverMenuImg + ${status.count}).css( "visibility", "visible");
		 		  $(OverMenuImg + ${status.count}).show();
			} else {
		   		  $(statMenuImg + ${status.count}).show();
		 	   	  $(OverMenuImg + ${status.count}).hide();
			}
		</c:if>
	</c:forEach>
}

function topStatMenuImageOut() {
	statMenuImg = "#" + "topStatMenuImg";
	OverMenuImg = "#" + "topOverMenuImg";
	
	<c:forEach var="menuList" items="${topMenuList}" varStatus="status">
	  $(statMenuImg + ${status.count}).show();
 	  $(OverMenuImg + ${status.count}).hide();
	</c:forEach>
}

function subTopStatMenuImage(val) {
	statMenuImg = "#" + "topStatMenuImg";
	OverMenuImg = "#" + "topOverMenuImg";

	<c:forEach var="menuLists" items="${topMenuList}" varStatus="status">
		<c:if test="${menuLists.menuLv eq 2}">
			<c:forEach var="subMenuList" items="${topMenuList}" varStatus="subStatus">
				<c:if test="${menuLists.menuId eq subMenuList.upperMenuId}">
					if (val == ${subStatus.count}) {
						  $(statMenuImg + ${status.count} + "_" + ${subStatus.count}).hide();
						  $(OverMenuImg + ${status.count} + "_" + ${subStatus.count}).css( "visibility", "visible");
				 		  $(OverMenuImg + ${status.count} + "_" + ${subStatus.count}).show();
					} else {
				   		  $(statMenuImg + ${status.count} + "_" + ${subStatus.count}).show();
				 	   	  $(OverMenuImg + ${status.count} + "_" + ${subStatus.count}).hide();
					}
				</c:if>
			</c:forEach>
		</c:if>
	</c:forEach>
}

function subTopStatMenuImageOut() {
	statMenuImg = "#" + "topStatMenuImg";
	OverMenuImg = "#" + "topOverMenuImg";
	<c:forEach var="menuLists" items="${topMenuList}" varStatus="status">
		<c:if test="${menuLists.menuLv eq 2}">
			<c:forEach var="subMenuList" items="${topMenuList}" varStatus="subStatus">
				<c:if test="${menuLists.menuId eq subMenuList.upperMenuId}">
		   		  $(statMenuImg + ${status.count} + "_" + ${subStatus.count}).show();
		 	   	  $(OverMenuImg + ${status.count} + "_" + ${subStatus.count}).hide();
				</c:if>
			</c:forEach>
		</c:if>
	</c:forEach>
}

function fn_searchSubmit(){
	var frm = document.search_form;
	//frm.query.value = document.getElementById("keyword").value;
	JQ_setValueObj("query", JQ_getValueObj("keyword"));
	window.open('',"Search","menubar=no,toolbar=no,location=no,status=no,scrollbars=yes,width=1000,height=650");
	frm.action = "/wsn/search.do";
	frm.target = "Search";
	frm.method = "post";
	frm.submit();
}
</script>
<%-- Header Start ========================================================== ${menuList.topImageMouseoverMask} --%>
	<%-- 상단 메뉴 영역  start --%>
	<div id="header">
		<form:form commandName="menuManageVO" method="post" onsubmit="return false;">
		<input type="submit" class="hidden">
		<form:hidden path="menuId"/>
			<div class="topMenu">
				<div class="top_imgmenu">
					<ul>
						<li class="logs"><a href="/"><img src="/images/gps/cmm/logo/nara_logo.gif" alt="나라통계포털" title="나라통계포털"></a></li>
		<c:choose>
			<c:when test="${menuManageVO.menuImgFlg eq true}">
				<c:forEach var="menuList" items="${topMenuList}" varStatus="status">
					<c:if test="${menuList.menuLv eq 2}">
						<li>
							<span>
								<a onmouseout="topStatMenuImageOut()" onmouseover="topShowStatLayer(${status.count});topStatMenuImage(${status.count});" onblur="topStatMenuImageOut()" onfocus="topShowStatLayer(${status.count});topStatMenuImage(${status.count});" href="<c:url value="${menuList.menuUrl}"><c:param name="leftMenuId" value="${menuList.menuId}"/><c:param name="menuId" value="${menuList.menuId}"/><c:param name="bbsId" value="${menuList.bbsId}" /></c:url>">
									<c:choose>
										<c:when test="${menuManageVO.menuId eq menuList.menuId || menuManageVO.leftMenuId eq menuList.menuId}">
											<img title="${fn:escapeXml(menuList.menuNm)}" alt="${fn:escapeXml(menuList.menuNm)}" src="${fn:escapeXml(WebImagePath)}?imageid=web/gps/menu/${fn:escapeXml(menuList.topImageMouseoverMask)}&ext=${fn:escapeXml(menuList.topImageMouseoverMime)}" />
										</c:when>
										<c:otherwise>
											<img title="${fn:escapeXml(menuList.menuNm)}" alt="${fn:escapeXml(menuList.menuNm)}" id="topStatMenuImg${status.count}" src="${fn:escapeXml(WebImagePath)}?imageid=web/gps/menu/${fn:escapeXml(menuList.topImageMask)}&ext=${fn:escapeXml(menuList.topImageMime)}"/>
											<img title="${fn:escapeXml(menuList.menuNm)}" alt="${fn:escapeXml(menuList.menuNm)}" id="topOverMenuImg${status.count}" src="${fn:escapeXml(WebImagePath)}?imageid=web/gps/menu/${fn:escapeXml(menuList.topImageMouseoverMask)}&ext=${fn:escapeXml(menuList.topImageMouseoverMime)}" class="vh"/>
										</c:otherwise>
									</c:choose>
								</a>
							</span>
						</li>
					</c:if>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<c:forEach var="menuList" items="${topMenuList}" varStatus="status">
					<c:if test="${menuList.menuLv eq 2}">
						<li>
						<span>
							<a onmouseover="topShowStatLayer(${status.count});" onfocus="topShowStatLayer(${status.count});" href="<c:url value="${menuList.menuUrl}"><c:param name="menuId" value="${menuList.menuId}"/><c:param name="leftMenuId" value="${menuList.menuId}"/><c:param name="bbsId" value="${menuList.bbsId}"/></c:url>" >
								<c:out value="${menuList.menuNm}" escapeXml="true"/>
							</a>
						</span>
						</li>
					</c:if>
				</c:forEach>
			</c:otherwise>
		</c:choose>
					</ul>
				</div>
				<%-- main search start --%>
				<div class="main_search">
					<div class="search_key">
					<!-- 
					<form name="search_form">
					 -->
						<fieldset>
						<legend>통합검색</legend>
							<ul>
								<li class="keyinput"><input type="text" title="검색어를 입력하세요" name="keyword" id="keyword" class="wnsearch" value="<%=request.getParameter("query")!=null?request.getParameter("query").replaceAll("<","&lt;").replaceAll(">","&gt;"):""%>" autocomplete="off" onkeypress="if(event.keyCode==13){fn_searchSubmit();return false;}"></li>
								<li class="keybuttom"><img src="/images/gps/cmm/icon/bt_search.gif" class="noline" title="검색" alt="검색" onclick="fn_searchSubmit();return false;" onkeypress="this.onclick();return false;"></li>
							</ul>
						</fieldset>
					<!--
					</form>
					-->
					</div>
				</div>
				<%-- main search end --%>
			</div>
	<%-- 1.start <div><ul> 2.sub menu print 3.end<div><ul>  --%>
	<c:forEach var="menuLists" items="${topMenuList}" varStatus="status">
		<c:if test="${menuLists.menuLv eq 2}">
			<div id="topSubMenuLayer${status.count}" class="topSubMenuLayer${status.count}" 
				<c:choose>
					<c:when test="${menuManageVO.leftMenuId eq menuLists.menuId}">
						style="display:block"
					</c:when>
					<c:otherwise>
						style="display:none"
					</c:otherwise>
				</c:choose>>
				<ul>
					<c:forEach var="subMenuList" items="${topMenuList}" varStatus="subStatus">
						<c:if test="${menuLists.menuId eq subMenuList.upperMenuId}">
							<li>
								<a onmouseout="subTopStatMenuImageOut()" onmouseover="subTopStatMenuImage(${subStatus.count})" onblur="topStatMenuImageOut()" onfocus="subTopStatMenuImage(${subStatus.count})" href="<c:url value="${subMenuList.menuUrl}"><c:param name="menuId" value="${subMenuList.menuId}"/><c:param name="leftMenuId" value="${subMenuList.upperMenuId}"/><c:param name="bbsId" value="${subMenuList.bbsId}"/></c:url>">
									<c:choose>
										<c:when test="${menuManageVO.menuId eq subMenuList.menuId}">
											<img title="${subMenuList.menuNm}" alt="${subMenuList.menuNm}" src="${WebImagePath}?imageid=web/gps/menu/${subMenuList.topImageMouseoverMask}&ext='${subMenuList.topImageMouseoverMime}"/>
										</c:when>
										<c:otherwise>
											<img title="${subMenuList.menuNm}" alt="${subMenuList.menuNm}" id="topStatMenuImg${status.count}_${subStatus.count}" src="${WebImagePath}?imageid=web/gps/menu/${subMenuList.topImageMask}&ext=${subMenuList.topImageMime}" />
											<img title="${subMenuList.menuNm}" alt="${subMenuList.menuNm}" id="topOverMenuImg${status.count}_${subStatus.count}" src="${WebImagePath}?imageid=web/gps/menu/${subMenuList.topImageMouseoverMask}&ext='${subMenuList.topImageMouseoverMime}" class="vh"/>
										</c:otherwise>
									</c:choose>
								</a>
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		</c:forEach>
		</form:form>
	</div>
	<!--<div id="header">
		<div class="top_imgmenu"> 
			<form:form commandName="menuManageVO" method="post">
			<form:hidden path="menuId"/>
			<ul>
		<c:forEach var="menuList" items="${topMenuList}" varStatus="status">
			<c:if test="${menuList.menuLv eq 2}">
				<li><img src="/images/gps/menu/head/head_menu_bt01.gif"></li>
			</c:if>
		</c:forEach>
			</ul>
			</form:form>
		</div>
	</div>
	-->
	<form name="search_form">
	<input type="hidden" id="query" name="query"/>
	</form>
	<!-- header end -->
	<%-- 상단 메뉴 영역  end --%>