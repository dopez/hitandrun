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
		</c:if>
		
		$('#menuTy').val()=='A'||$('#menuTy').val()=='B'?$('#boardForm').show():'';
		$('#menuUrlTarget').val()=='3'?$('#popupForm').show():'';
	}	

	<%-- 
	/************************************************************************ 
	fnc name : fn_insert                                   
	outline : 메뉴등록처리요청함수      
	parameter : 없음        
	directions : fn_update()              
	since : 2011-06-10   
	author : 통계포털  황기연       
	
	    date      author             note  
	 ----------   -------     ------------------- 
	 2011.06.10    황기연                           최초 생성          
	************************************************************************/ 
	--%>
	function fn_insert(){
		$('#menuManageVO').attr('target','_self');
		JQ_setProcessMsg();
		JQ_request("menuManageVO", "<c:url value='/gps/adm/menu/insertMenu.do'/>");
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
	parameter : id 첨부파일 id        
	directions : fn_fileInspct      
	since : 2011-07-13   
	author : 통계포털  황기연       

	    date      author             note  
	 ----------   -------     ------------------- 
	 2011.07.13    황기연                          최초 생성                
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
</script>
<!-- ui-layout-center start  -->
<div class="ui-layout-center">
	<!-- container start -->
	<div class="contents_area">
		<form:form commandName="menuManageVO" name="form1" method="post" enctype="multipart/form-data" >
		<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="3" />
		<form:hidden path="sysUseTy"/>
		<form:hidden path="upperMenuId"/>
		<form:hidden path="programId"/>
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
										<td class="title">메뉴추가</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table class = "write01">
									<tr>
										<th class="reqsubject">메뉴명</th>
										<td class = "input">
											<form:input path="menuNm" cssClass="validate[required,length[1,50]] wi300"/>
										</td>
									</tr>
									<tr>
										<th class="subject">메뉴단축명</th>
										<td class = "input">
											<form:input path="menuAbrv" cssClass="validate[optional,length[1,50]] wi300"/>
										</td>
									</tr>
									<tr>
										<th class="subject">메뉴영문명</th>
										<td class="input">
											<form:input path="menuEngNm" cssClass="wi300 validate[optional,length[1,50]]"/>
										</td>
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
														<td class="input_bottom_noline"><form:radiobutton path="useAt" value="Y" cssClass="noline"/></td>
														<td class="text_bottom_noline"><label for="useAt" title="사용">사용</label></td>
														<td class="input_bottom_noline"><form:radiobutton path="useAt" value="N" cssClass="noline"/></td>
														<td class="text_bottom_noline"><label for="useAt" title="사용안함">사용안함</label></td>
													</tr>
													</table>
												</td>
											</tr>
											<tr>
												<th class="subject">TOP메뉴 사용여부</th>
												<td>
													<table class="inner01">
													<tr>
														<td class="input_bottom_noline"><form:radiobutton path="topMenuUseAt" value="Y" cssClass="noline"/></td>
														<td class="text_bottom_noline"><label for="topMenuUseAt" title="사용">사용</label></td>
														<td class="input_bottom_noline"><form:radiobutton path="topMenuUseAt" value="N" cssClass="noline"/></td>
														<td class="text_bottom_noline"><label for="topMenuUseAt" title="사용안함">사용안함</label></td>
													</tr>
													</table>
												</td>
											</tr>
											<tr>
												<th class="<c:out value="${menuManageVO.sysUseTy != 'Y'?'subject':'bottom_noline'}"/>">LEFT메뉴 사용여부</th>
												<td class="<c:out value="${menuManageVO.sysUseTy != 'Y'?'':'bottom_noline'}"/>">
													<table class="inner01">
													<tr>
														<td class="input_bottom_noline"><form:radiobutton path="leftMenuUseAt" value="Y" cssClass="noline"/></td>
														<td class="text_bottom_noline"><label for="leftMenuUseAt" title="사용">사용</label></td>
														<td class="input_bottom_noline"><form:radiobutton path="leftMenuUseAt" value="N" cssClass="noline"/></td>
														<td class="text_bottom_noline"><label for="leftMenuUseAt" title="사용안함">사용안함</label></td>
													</tr>
													</table>
												</td>
											</tr>
											<%-- 시스템메뉴일때는 사용안함--%>
											<c:if test="${menuManageVO.sysUseTy != 'Y'}">
											<tr>
												<th class="subject">BOTTOM메뉴 사용여부</th>
												<td>
													<table class="inner01">
													<tr>
														<td class="input_bottom_noline"><form:radiobutton path="bottomMenuUseAt" value="Y" cssClass="noline"/></td>
														<td class="text_bottom_noline"><label for="bottomMenuUseAt" title="사용">사용</label></td>
														<td class="input_bottom_noline"><form:radiobutton path="bottomMenuUseAt" value="N" cssClass="noline"/></td>
														<td class="text_bottom_noline"><label for="bottomMenuUseAt" title="사용안함">사용안함</label></td>
													</tr>
													</table>
												</td>
											</tr>
											<tr>
												<th class="bottom_noline">SITEMAP 사용여부</th>
												<td class="bottom_noline">
													<table class="inner01">
													<tr>
														<td class="input_bottom_noline"><form:radiobutton path="sitemapUseAt" value="Y" cssClass="noline"/></td>
														<td class="text_bottom_noline"><label for="sitemapUseAt" title="사용">사용</label></td>
														<td class="input_bottom_noline"><form:radiobutton path="sitemapUseAt" value="N" cssClass="noline"/></td>
														<td class="text_bottom_noline"><label for="sitemapUseAt" title="사용안함">사용안함</label></td>
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
										<td class="input">
											<form:input path="menuSkin"/>
										</td>
									</tr>
									</c:if>
									<tr>
										<th class="subject">메뉴URL</th>
										<td>
											<table class="inside02">
											<tr>
												<td class="input"><form:input path="menuUrl" cssClass="wi400 validate[optional,length[1,150]]"/></td>
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
										<td>
											<table class="inside01">
											<c:set var="menuKind" value="top,top,left,left,bottom,bottom,sitemap,sitemap"></c:set>
										<c:forTokens var="menuNm" items="${menuKind}" delims="," varStatus="status">
											<tr>
												<th class="subject">${fn:toUpperCase(menuNm)}메뉴<br/>오버로드<c:out value="${status.count%2 > 0?'전':'후'}"/>이미지</th>
												<td>
													<table class="inner01">
													<tr>
														<th class="bottom_noline">이미지선택</th>
														<td class="bottom_noline">
															<input type="file" 
																   name="menuImg_<c:out value="${status.count}"/>" 
																   id="menuImg_<c:out value="${status.count}"/>"
																   onchange="fn_fileInspct(this.id);" class="wi300"/>
														</td>
													</tr>
													<tr>
														<th class="bottom_noline">이미지주석</th>
														<td class="bottom_noline">
															<form:input path="${menuNm}Image${status.count%2==0?'Mouseover':''}Cm" cssClass="wi300"/>
														</td>
													</tr>
													</table>
												</td>
											</tr>
										</c:forTokens>
											<tr>
												<th class="bottom_noline">타이틀 이미지</th>
												<td class="bottom_noline">
													<table class="inside01">
													<tr>
														<th class="bottom_noline">이미지선택</th>
														<td class="input_bottom_noline">
															<input type="file" name="menuImg_9" id="menuImg_9" onchange="fn_fileInspct(this.id);" class="wi300"/>
														</td>
													</tr>
													</table>
												</td>
											</tr>
											</table>
										</td>
									</tr>
									<tr>
										<th class="subject">만족도조사 사용여부</th>
										<td>
											<form:radiobutton path="snstUseAt" value="Y" label="사용" cssClass="noline"/>
											<form:radiobutton path="snstUseAt" value="N" label="미사용" cssClass="noline"/>
										</td>
									</tr>
									</c:if>
									<tr>
										<th class="subject">비고</th>
										<td>
											<form:textarea path="menuRm" cssClass = "wi500 ht50 validate[optional,length[1,1000]]"/>
										</td>
									</tr>
									<tr>
										<th class="subject">첨부파일</th>
										<td>
											<input name="file_1" id="egovComFileUploader" type="file" onfocus="gfn_focus(this);" onblur="gfn_focusnot(this);" />
											<div id="egovComFileList"></div>
										</td>
									</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td align="center">
									<table class="rbuttonarea">
										<tr>
											<td class="end"><img src="/images/button/0210.png" style="cursor:hand;" alt="<spring:message code="button.save"/>" title="<spring:message code="button.save"/>" onclick = "fn_insert()"/></td>
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
</div>
<!-- ui-layout-center end  -->
<script type="text/javascript">
	var maxFileNum = $('#posblAtchFileNumber').val();
	if(maxFileNum==null || maxFileNum==""){
	  maxFileNum = 3;
	}
	var multi_selector = new MultiSelector(document.getElementById( 'egovComFileList' ), maxFileNum );
	multi_selector.addElement(document.getElementById( 'egovComFileUploader' ) );
</script>
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>
