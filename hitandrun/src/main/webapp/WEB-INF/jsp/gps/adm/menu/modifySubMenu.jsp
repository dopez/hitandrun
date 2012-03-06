<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<jsp:include page="/gps/cmm/admHeader.do" flush="false"></jsp:include>
<script type="text/javascript">
	JQ_setValidation('menuManageVO');
	JQ_onload();

	<%-- 
	/************************************************************************ 
	fnc name : fncPageOnload                                   
	outline : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
	parameter : 없음        
	directions : fncPageOnload()              
	since : 2011-06-10   
	author : 통계포털  황기연       
	
	    date      author             note  
	 ----------   -------     ------------------- 
	 2011.06.10    황기연                      최초 생성                    
	************************************************************************/ 
	--%>
	function fncPageOnload(){
		//메시징처리
	<c:if test="${!empty message}">
		alert('<c:out value = "${message}"/>');
		parent.leftBody.location.reload();
		parent.parent.frmtop.location.reload();
		parent.parent.frmleft.location.reload();
	</c:if>
	
	$('#menuTy').val()=='A'||$('#menuTy').val()=='B'?$('#boardForm').show():'';
	$('#menuUrlTarget').val()=='3'?$('#popupForm').show():'';
	}

	<%-- 
	/************************************************************************ 
	fnc name : fn_register                                   
	outline : 하위메뉴등록페이지호출
	parameter : 없음        
	directions : fn_register()              
	since : 2011-06-10   
	author : 통계포털  황기연       
	
	    date      author             note  
	 ----------   -------     ------------------- 
	 2011.06.10    황기연                       최초 생성                 
	************************************************************************/ 
	--%>
	function fn_register(){
		$('#menuManageVO').attr('target','_self');
		JQ_request("menuManageVO", "<c:url value='/gps/adm/menu/registerSubMenu.do'/>","menuManageVO");
	}
	
	<%-- 
	/************************************************************************ 
	fnc name : fn_update                                   
	outline : 메뉴수정처리요청함수      
	parameter : 없음        
	directions : fn_update()              
	since : 2011-06-10   
	author : 통계포털  황기연       
	
	    date      author             note  
	 ----------   -------     ------------------- 
	 2011.06.10    황기연                       최초 생성                 
	************************************************************************/ 
	--%>
	function fn_update(){
		JQ_setProcessMsg();
		$('#menuManageVO').attr('target','_self');
		JQ_request("menuManageVO", "<c:url value='/gps/adm/menu/updateMenu.do'/>");
	}

	<%-- 
	/************************************************************************ 
	fnc name : fn_delete                                   
	outline : 메뉴삭제처리요청함수      
	parameter : 없음        
	directions : fn_delete()              
	since : 2011-06-10   
	author : 통계포털  황기연       
	
	    date      author             note  
	 ----------   -------     ------------------- 
	 2011.06.10    황기연                      최초 생성                    
	************************************************************************/ 
	--%>
	function fn_delete(){
		if (confirm("<spring:message code="gps.menu.delete.msg" />")){
		JQ_setProcessMsg();
		$('#menuManageVO').attr('target','_self');
		JQ_request("menuManageVO", "<c:url value='/gps/adm/menu/deleteMenu.do'/>","menuManageVO");
		}
	}

	<%-- 
	/************************************************************************ 
	fnc name : fn_popup_set                                   
	outline : 링크target 팝업일때 팝업창사이즈 입력 DIV 보이기 
	parameter : value      
	directions : fn_popup_set()              
	since : 2011-06-10   
	author : 통계포털  황기연       

	    date      author             note  
	 ----------   -------     ------------------- 
	 2011.06.10    황기연                         최초 생성                 
	************************************************************************/ 
	--%>
	function fn_popup_set(){
		$('#menuUrlTarget').val()=="3"?$('#popupForm').show():$('#popupForm').hide();
	}

	<%-- 
	/************************************************************************ 
	fnc name : fn_board_set                                   
	outline : 메뉴형태  게시판링크 일때 게시판ID검색 textbox DIV 보이기 
	parameter : value   
	directions : fn_board_set()              
	since : 2011-06-10   
	author : 통계포털  황기연       

	    date      author             note  
	 ----------   -------     ------------------- 
	 2011.06.10    황기연                          최초 생성                
	************************************************************************/ 
	--%>
	function fn_board_set(){
		var url = "/gps/bbs/selectBbsList.do";
		if($('#menuTy').val()=="A" || $('#menuTy').val()=="B"){
			$('#menuUrl').val(url);
			$('#boardForm').show();
		}else{
			$('#menuUrl').val('<c:out value="${menuManageVO.menuUrl}"/>');
			$('#boardForm').hide();
		}
	}
	
	
	<%-- 
	/************************************************************************ 
	fnc name : fn_fileInspct(/js/gps/gpsCommon.js)                                 
	outline : 첨부파일 확장자검사
	parameter : 첨부파일 id       
	directions : fn_fileInspct      
	since : 2011-07-13   
	author : 통계포털  황기연       
	
	    date      author             note  
	 ----------   -------     ------------------- 
	 2011.07.13    황기연                       최초 생성                  
	************************************************************************/ 
	--%>
	function fn_fileInspct(id){
		uploadFileInspct('img',id);
	}


	<%-- 
	/************************************************************************ 
	fnc name : fn_searchPopup                       
	outline : 프로그램,게시판검색 팝업
	parameter : 첨부파일 id       
	directions : fn_searchPopup      
	since : 2011-07-13   
	author : 통계포털  황기연       
	
	    date      author             note  
	 ----------   -------     ------------------- 
	 2011.07.13    황기연                       최초 생성                  
	************************************************************************/ 
	--%>
	function fn_searchPopup(searchSe){
		var url = "";
		var popupNm = "";
		if(searchSe == 'board'){
			popupNm = "게시판";
			url = "/gps/adm/menu/boardListPopup.do";
		}else{
			popupNm = "프로그램";
			url = "/gps/adm/menu/programListPopup.do";
		}
		gfn_postPopupWin("menuManageVO",url,popupNm+"조회",600,1000, "yes","no",0);
	}

	function fn_csnstPopup(){
		gfn_postPopupWin("menuManageVO", "/gps/adm/menu/selectCsnstList.do", "만족도 결과조회", 600, 1000, "yes", "no");
	}
	
</script>
<!-- container start -->
<div class="contents_area">
	<form:form commandName="menuManageVO" name="form1" method="post" enctype="multipart/form-data">
	<input type="hidden" name="returnUrl" id="returnUrl" value="/gps/adm/menu/modifySubMenu.do" />
	<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />
	<input type="hidden" name="uploadableFileNum" id="uploadableFileNum"/>
	<form:hidden path="attachmentFileId"/>
	<form:hidden path="menuId"/>
	<form:hidden path="programId"/>
	<form:hidden path="sysUseTy"/>
	<form:hidden path="fileListCnt"/>
	<form:hidden path="csnstId"/>
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
				<!-- 하위메뉴 -->
				<tr>
					<td>
						<table class="title">
							<tr>
								<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="타이틀이미지" title="타이틀이미지"/></td>
								<td class="title"><c:out value="${menuManageVO.menuNm}"/></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class = "write01">
						<tr>
							<th class="reqsubject">메뉴명</th>
							<td class="input"><form:input path="menuNm" cssClass="wi300 validate[required,length[1,50]]"/></td>
						</tr>
						<tr>
							<th class="subject">메뉴단축명</th>
							<td class="input"><form:input path="menuAbrv" cssClass="wi300 validate[optional,length[1,50]]"/></td>
						</tr>
						<tr>
							<th class="subject">메뉴영문명</th>
							<td class="input"><form:input path="menuEngNm" cssClass="wi300 validate[optional,length[1,50]]"/></td>
						</tr>
						<tr>
							<th class="subject">순서</th>
							<td class="input">
								<form:select path="menuOrdr">
									<c:forEach var="ordr" begin="1" end="30" step="1">
										<form:option value="${ordr}" label="${ordr}"/>
									</c:forEach>
								</form:select>
							</td>
						</tr>
						<tr>
							<th class="subject">메뉴사용여부</th>
							<td>
								<table class="inside01">
								<tr>
									<th class="subject">전체 사용여부</th>
									<td>
										<table class="inner01">
										<tr>
											<td class="bottom_noline"><form:radiobutton path="useAt" id="useAtY" value="Y" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="useAtY" title="사용">사용</label></td>
											<td class="input_bottom_noline"><form:radiobutton path="useAt" id="useAtN" value="N" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="useAtN" title="사용안함">사용안함</label></td>
										</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="subject">TOP메뉴 사용여부</th>
									<td>
										<table class="inner01">
										<tr>
											<td class="bottom_noline"><form:radiobutton path="topMenuUseAt" id="topMenuUseAtY" value="Y" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="topMenuUseAtY" title="사용">사용</label></td>
											<td class="input_bottom_noline"><form:radiobutton path="topMenuUseAt" id="topMenuUseAtN" value="N" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="topMenuUseAtN" title="사용안함">사용안함</label></td>
										</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="<c:out value="${menuManageVO.sysUseTy != 'Y'?'subject':'bottom_noline'}"/>">LEFT메뉴 사용여부</th>
									<td class="<c:out value="${menuManageVO.sysUseTy != 'Y'?'':'bottom_noline'}"/>">
										<table class="inner01">
										<tr>
											<td class="bottom_noline"><form:radiobutton path="leftMenuUseAt" id="leftMenuUseAtY" value="Y" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="leftMenuUseAtY" title="사용">사용</label></td>
											<td class="input_bottom_noline"><form:radiobutton path="leftMenuUseAt" id="leftMenuUseAtN" value="N" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="leftMenuUseAtN" title="사용안함">사용안함</label></td>
										</tr>
										</table>
									</td>
								</tr>
								<c:if test="${menuManageVO.sysUseTy != 'Y'}">
								<tr>
									<th class="subject">BOTTOM메뉴 사용여부</th>
									<td>
										<table class="inner01">
										<tr>
											<td class="bottom_noline"><form:radiobutton path="bottomMenuUseAt" id="bottomMenuUseAtY" value="Y" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="bottomMenuUseAtY" title="사용">사용</label></td>
											<td class="input_bottom_noline"><form:radiobutton path="bottomMenuUseAt" id="bottomMenuUseAtN" value="N" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="bottomMenuUseAtN" title="사용안함">사용안함</label></td>
										</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="bottom_noline">SITEMAP 사용여부</th>
									<td class="bottom_noline">
										<table class="inner01">
										<tr>
											<td class="bottom_noline"><form:radiobutton path="sitemapUseAt" id="sitemapUseAtY" value="Y" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="sitemapUseAtY" title="사용">사용</label></td>
											<td class="input_bottom_noline"><form:radiobutton path="sitemapUseAt" id="sitemapUseAtN" value="N" cssClass="noline"/></td>
											<td class="text_bottom_noline"><label for="sitemapUseAtN" title="사용안함">사용안함</label></td>
										</tr>
										</table>
									</td>
								</tr>
								</c:if>
								</table>
							</td>
						</tr>
						<tr>
							<th class="subject">메뉴형태</th>
							<td class="input">
								<form:select path="menuTy" onchange="fn_board_set()">
									<form:option value="F" label="폴더"/>
									<c:if test="${menuManageVO.sysUseTy != 'Y'}">
									<form:option value="A" label="게시판폴더링크"/>
									<form:option value="B" label="게시판링크"/>
									</c:if>
									<form:option value="H" label="HTML링크"/>
								</form:select>
							</td>
						</tr>
						<tr id="boardForm" style="display:none">
							<th class="subject">게시판정보 선택</th>
							<td>
								<table class="inside02">
								<tr>
									<td class="input">
										<form:hidden path="bbsId"/>
										<form:input path="bbsNm" cssClass="wi100 validate[optional,length[1,30]]" readonly="true"/>
									</td>
									<td class="btn">
										<img src="/images/button/0208.png" alt="<spring:message code="button.search"/>" title="<spring:message code="button.search"/>" style="cursor:hand;" onclick="fn_searchPopup('board');" onkeydown="this.onclick();"/>
									</td>
								</tr>
								</table>
							</td>
						</tr>
						<%-- 시스템메뉴일때는 사용안함--%>
						<c:if test="${menuManageVO.sysUseTy != 'Y'}">
						<tr>
							<th class="subject">메뉴스킨</th>
							<td class="input"><form:input path="menuSkin"/></td>
						</tr>
						</c:if>
						<tr>
							<th class="subject">메뉴URL</th>
							<td>
								<table class="inside02">
								<tr>
									<td class="input"><form:input path="menuUrl" cssClass="wi400 validate[optional,length[1,150]]" /></td>
									<td class="btn">
										<img src="/images/button/0208.png" alt="<spring:message code="button.search"/>" title="<spring:message code="button.search"/>" style="cursor:hand;" onclick="fn_searchPopup('program');" onkeydown="this.onclick();"/>
									</td>
								</tr>
								</table>
							</td>
						</tr>
						<%-- 시스템메뉴일때는 사용안함--%>
						<c:if test="${menuManageVO.sysUseTy != 'Y'}">
						<tr>
							<th class="subject">링크TARGET</th>
							<td class="input">
								<form:select path="menuUrlTarget" onchange="fn_popup_set()">
									<form:option value = "1" label="현재창"/>
									<form:option value = "2" label="새창"/>
									<form:option value = "3" label="팝업창"/>
								</form:select>
							</td>
						</tr>
						<tr id="popupForm" style="display:none">
							<th class="subject">팝업창 정보</th>
							<td>
								<table class="inside01">
								<tr>
									<th class="subject">전체 팝업창크기</th>
									<td>
										<table class="inner02">
										<tr>
											<th class="subject">넓이</th>
											<td class="input"><form:input path="popupWidth" cssClass="wi50 validate[optional,custom[onlyNumber],length[1,5]] "/> px</td>
										</tr>
										<tr>
											<th class="bottom_noline">높이</th>
											<td class="bottom_noline"><form:input path="popupHeight" cssClass="wi50 validate[optional,custom[onlyNumber],length[1,5]] "/> px</td>
										</tr>
										</table>
									</td>
								</tr>
								<tr>
									<th class="bottom_noline">팝업창위치</th>
									<td class="bottom_noline">
										<table class="inner02">
										<tr>
											<th class="subject">위쪽</th>
											<td class="input"><form:input path="popupTop" cssClass="wi50 validate[optional,custom[onlyNumber],length[1,5]] "/> px</td>
										</tr>
										<tr>
											<th class="bottom_noline">왼쪽</th>
											<td class="bottom_noline"><form:input path="popupLeft" cssClass="wi50 validate[optional,custom[onlyNumber],length[1,5]] "/> px</td>
										</tr>
										</table>
									</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th class="subject">이미지</th>
							<td class="bottom_noline">
								<table class="inside01"">
							<c:set var="menuKind" value="top,top,left,left,bottom,bottom,sitemap,sitemap"></c:set>
							<c:forTokens var="menuNm" items="${menuKind}" delims="," varStatus="status">
							<c:choose>
								<c:when test="${status.count eq 1}">
									<c:set var="fileNm" value="${menuManageVO.topImageNm}"/>
									<c:set var="fileMask" value="${menuManageVO.topImageMask}"/>
									<c:set var="fileTitle" value="${menuManageVO.topImageCm}"/>
									<c:set var="fileMime" value="${menuManageVO.topImageMime}"/>
								</c:when>
								<c:when test="${status.count eq 2}">
									<c:set var="fileNm" value="${menuManageVO.topImageMouseoverNm}"/>
									<c:set var="fileMask" value="${menuManageVO.topImageMouseoverMask}"/>
									<c:set var="fileTitle" value="${menuManageVO.topImageMouseoverCm}"/>
									<c:set var="fileMime" value="${menuManageVO.topImageMouseoverMime}"/>
								</c:when>
								<c:when test="${status.count eq 3}">
									<c:set var="fileNm" value="${menuManageVO.leftImageNm}"/>
									<c:set var="fileMask" value="${menuManageVO.leftImageMask}"/>
									<c:set var="fileTitle" value="${menuManageVO.leftImageCm}"/>
									<c:set var="fileMime" value="${menuManageVO.leftImageMime}"/>
								</c:when>
								<c:when test="${status.count eq 4}">
									<c:set var="fileNm" value="${menuManageVO.leftImageMouseoverNm}"/>
									<c:set var="fileMask" value="${menuManageVO.leftImageMouseoverMask}"/>
									<c:set var="fileTitle" value="${menuManageVO.leftImageMouseoverCm}"/>
									<c:set var="fileMime" value="${menuManageVO.leftImageMouseoverMime}"/>
								</c:when>
								<c:when test="${status.count eq 5}">
									<c:set var="fileNm" value="${menuManageVO.bottomImageNm}"/>
									<c:set var="fileMask" value="${menuManageVO.bottomImageMask}"/>
									<c:set var="fileTitle" value="${menuManageVO.bottomImageCm}"/>
									<c:set var="fileMime" value="${menuManageVO.bottomImageMime}"/>
								</c:when>
								<c:when test="${status.count eq 6}">
									<c:set var="fileNm" value="${menuManageVO.bottomImageMouseoverNm}"/>
									<c:set var="fileMask" value="${menuManageVO.bottomImageMouseoverMask}"/>
									<c:set var="fileTitle" value="${menuManageVO.bottomImageMouseoverCm}"/>
									<c:set var="fileMime" value="${menuManageVO.bottomImageMouseoverMime}"/>
								</c:when>
								<c:when test="${status.count eq 7}">
									<c:set var="fileNm" value="${menuManageVO.sitemapImageNm}"/>
									<c:set var="fileMask" value="${menuManageVO.sitemapImageMask}"/>
									<c:set var="fileTitle" value="${menuManageVO.sitemapImageCm}"/>
									<c:set var="fileMime" value="${menuManageVO.sitemapImageMime}"/>
								</c:when>
								<c:when test="${status.count eq 8}">
									<c:set var="fileNm" value="${menuManageVO.sitemapImageMouseoverNm}"/>
									<c:set var="fileMask" value="${menuManageVO.sitemapImageMouseoverMask}"/>
									<c:set var="fileTitle" value="${menuManageVO.sitemapImageMouseoverCm}"/>
									<c:set var="fileMime" value="${menuManageVO.sitemapImageMouseoverMime}"/>
								</c:when>
							</c:choose>
								<tr>
									<th class="subject"><c:out value="${fn:toUpperCase(menuNm)}"/>메뉴<br/>오버로드<c:out value="${status.count%2 > 0?'전':'후'}"/>이미지</th>
									<td>
										<table class="inside01">
										<tr>
											<th class="subject">이미지선택</th>
											<td class="input"><input <c:out value="${!empty fileMask?'disabled=\"disabled\"':''}" escapeXml="false"/> type="file" name="menuImg_<c:out value="${status.count}"/>" id="menuImg_<c:out value="${status.count}"/>" onchange="fn_fileInspct(this.id);" class="wi300"/></td>
										</tr>
										<tr>
											<th class="<c:out value="${!empty fileMask?'subject':'bottom_noline'}"/>">이미지주석</th>
											<td class="<c:out value="${!empty fileMask?'input':'input_bottom_noline'}"/>"><form:input path="${menuNm}Image${status.count%2==0?'Mouseover':''}Cm" cssClass="wi300"/></td>
										</tr>
									<c:if test="${!empty fileMask}">
										<form:hidden path="${menuNm}Image${status.count%2==0?'Mouseover':''}Nm"/>
										<form:hidden path="${menuNm}Image${status.count%2==0?'Mouseover':''}Mask"/>
										<form:hidden path="${menuNm}Image${status.count%2==0?'Mouseover':''}Mime"/>
										<form:hidden path="${menuNm}Image${status.count%2==0?'Mouseover':''}Size"/>
										<tr>
											<%-- 이미지미리보기 --%>
											<th class="subject">이미지보기</th>
											<td class="img"><img src="${WebImagePath}?imageid=web/gps/menu/${fileMask}&ext=${fileMime}" title="${menuManageVO.menuNm}" alt="${menuManageVO.menuNm}" width="100"/></td>
										</tr>
										<tr>
											<th class="bottom_noline">파일명</th>
											<td class="bottom_noline">
												<table class="inner01">
												<tr>
													<td><c:out value="${fileNm}"/></td>
													<td><a href="<c:url value="/gps/adm/menu/deleteImgFile.do"> <c:param name="menuId" value="${menuManageVO.menuId}"/> <c:param name="deleteImgSe" value="${status.count}"/> </c:url>" onclick="this.href(); return false;" onkeydown="this.onclick(); return false;"><img src="/images/gps/adm/icon/027.gif" alt="<spring:message code="button.delete"/>" title="<spring:message code="button.delete"/>"></a></td>
												</tr>
												</table>
											</td>
										</tr>
									</c:if>
										</table>
									</td>
								</tr>
							</c:forTokens>
								<tr>
									<th class="subject">타이틀 이미지</th>
									<td>
										<table class="inside01">
										<tr>
											<th class="subject">이미지선택</th>
											<td class="input"><input <c:out value="${!empty menuManageVO.titleImageMask?'disabled=\"disabled\"':''}" escapeXml="false"/> type="file" name="menuImg_9" id="menuImg_9" onchange="fn_fileInspct(this.id);" class="wi300"/></td>
										</tr>
									<c:if test="${!empty menuManageVO.titleImageMask}">
										<form:hidden path="titleImageNm"/>
										<form:hidden path="titleImageMask"/>
										<form:hidden path="titleImageMime"/>
										<form:hidden path="titleImageSize"/>
										<tr>
											<%-- 이미지미리보기 --%>
											<th class="subject">이미지보기</th>
											<td class="img">
												<img src="${WebImagePath}?imageid=web/gps/menu/${menuManageVO.titleImageMask}&ext=${menuManageVO.titleImageMime}" title="${menuManageVO.menuNm}" alt="${menuManageVO.menuNm}" width="100"/>
											</td>
										</tr>
										<tr>
											<th class="bottom_noline">파일명</th>
											<td class="bottom_noline">
												<table class="inner01">
												<tr>
													<td><c:out value="${menuManageVO.titleImageNm}"/></td>
													<td><a href="<c:url value="/gps/adm/menu/deleteImgFile.do"> <c:param name="menuId" value="${menuManageVO.menuId}"/> <c:param name="deleteImgSe" value="9"/> </c:url>" onclick="this.href(); return false;" onkeydown="this.onclick(); return false;"><img src="/images/gps/adm/icon/027.gif" alt="<spring:message code="button.delete"/>" title="<spring:message code="button.delete"/>"></a></td>
												</tr>
												</table>
											</td>
										</tr>
									</c:if>
										</table>
									</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th class="subject">만족도조사 사용여부</th>
							<td>
								<table class="inside02">
								<tr>
									<td class="input"><form:radiobutton path="snstUseAt" id="snstUseAtY" value="Y" cssClass="noline" onclick="document.getElementById('csnstSearch').style.display = 'block';"/></td>
									<td><label for="snstUseAtY" title="사용">사용</label></td>
									<td class="input"><form:radiobutton path="snstUseAt" id="snstUseAtN" value="N" cssClass="noline" onclick="document.getElementById('csnstSearch').style.display = 'none';"/></td>
									<td><label for="snstUseAtN" title="사용안함">사용안함</label></td>
									<td id="csnstSearch" <c:if test="${menuManageVO.snstUseAt eq 'N'}">style="display:none"</c:if>>
										<a onclick="fn_csnstPopup()" style="cursor:hand"><img src="/images/button/0208.png" alt="<spring:message code='button.search'/>" title="<spring:message code='button.search'/>"></a>
									</td>
								</tr>
								</table>
							</td>
						</tr>
						</c:if>
						<tr>
							<th class="subject">비고</th>
							<td class="input"><form:textarea path="menuRm" cssClass = "wi500 ht50 validate[optional,length[1,1000]]"/></td>
						</tr>
						<tr>
							<th class="subject">첨부파일</th>
							<td>
								<table class="inside01">
									<tr>
										<td class="<c:out value="${!empty menuManageVO.attachmentFileId?'input':'input_bottom_noline'}"/>"><input type="file" name="file_1" id="egovComFileUploader" class="wi300" onfocus="gfn_focus(this);" onblur="gfn_focusnot(this);"/></td>
									</tr>
									<tr>
										<td class="file_bottom_noline">
											<div id="egovComFileList"></div>
											<c:if test="${!empty menuManageVO.attachmentFileId}">
											<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="UTF-8">
												<c:param name="param_atchFileId"  value="${menuManageVO.attachmentFileId}"/>					
											</c:import>
											</c:if>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th class="subject">작성자</th>
							<td class="text"><c:out value="${menuManageVO.updtusrId}"/></td>
						</tr>
						<tr>
							<th class="subject">작성자IP</th>
							<td class="text"><c:out value="${menuManageVO.registerIp}"/></td>
						</tr>
						<tr>
							<th class="subject">작성일</th>
							<td class="text"><c:out value="${menuManageVO.updtDt}"/></td>
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="rbuttonarea">
						<tr>
							<td><img src="/images/button/0211.png" style="cursor:hand;" alt="<spring:message code="button.add"/>" title="<spring:message code="button.add"/>" onclick="fn_register();"/></td>
							<td><img src="/images/button/0209.png" style="cursor:hand;" alt="<spring:message code="button.update"/>" title="<spring:message code="button.update"/>" onclick="fn_update();"/></td>
							<td class="end"><img src="/images/button/0204.png" style="cursor:hand;" alt="<spring:message code="button.delete"/>" title="<spring:message code="button.delete"/>" onclick="fn_delete();"/></td>
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
<script type="text/javascript">
	  var existFileNum; 
	  var maxFileNum; 
	  var uploadableFileNum;
	  existFileNum = $('#fileListCnt').val();// 이 값은 File List를 조회하는 부분에 담겨온다.
	  maxFileNum = $('#posblAtchFileNumber').val(); // 각 비즈니스 로직에서는 해당하는 폼 값에 첨부가능한 최대파일 숫자를 세팅해둬야 함
	  uploadableFileNum = maxFileNum - existFileNum; // 최대등록가능한 파일숫자에서 기존에 등록된 숫자를 뺀다.
	  if(uploadableFileNum <= 0) {
		  $('#uploadableFileNum').val(0);
	  }else{
		  $('#uploadableFileNum').val(uploadableFileNum);
	  }
      var multi_selector = new MultiSelector(document.getElementById( 'egovComFileList' ) , $('#uploadableFileNum').val() );
	  multi_selector.addElement(document.getElementById('egovComFileUploader' )); 
  </script>
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>