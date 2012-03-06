<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<jsp:include page="/gps/cmm/popupLeft.do" flush="false"></jsp:include>
<%-- 
/** 
 * outline   : 게시글목록(앨범 리스트형)
 * filename : bbsExtAlbumList.jsp
 * @author 통계포탈 황기연 
 * @since 2011.09.15
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == 개정이력(Modification Information) == 
 *   
 *   date        author     note 
 * ----------    -------    --------------------------- 
 * 2011.09.15     황기연           최초 생성 
 * </pre> 
 */
--%>
<script type="text/javascript">
JQ_onload();
<%-- 
/************************************************************************ 
fnc name : fncPageOnload                                   
outline : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
parameter : 없음        
directions : fncPageOnload()              
since : 2011-07-05   
author : 통계포털  황기연       

    date      author             note  
 ----------   -------     ------------------- 
 2011.06.10    황기연                                          
************************************************************************/ 
--%>
function fncPageOnload(){
	//메시징처리
	<c:if test="${!empty message}">
		alert('<c:out value = "${message}"/>');
	</c:if>
}

<%-- 
/************************************************************************ 
함수명 : link_page                                   
설   명 : 페이지 이동 함수
		조회된 목록에서 다른 페이지 번호를 누르면 해당 페이지로 이동
인   자 : pageNo        
사용법 : link_page(pageNo)              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 황기연       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function link_page(pageNo){
	$('#pageIndex').val(pageNo);
	JQ_request("bbsSearchVO", "<c:url value='/gps/bbs/selectBbsList.do'/>","bbsSearchVO");
}

<%--
/************************************************************************ 
함수명 : fn_search                                   
설   명 : 조회조건에 따라 목록을 조회하는 함수
인   자 : 없음(form안에 존재하는 값)
사용법 : fn_search()              
작성일 : 2011-09-15
작성자 : 범정부통계포털 황기연       

      date    author      note 
 ----------   -------     ------------------- 
2011-09-15 	  황기연
 ************************************************************************/
 --%>
function fn_search(){
	$('#pageIndex').val(1);
   	JQ_request("bbsSearchVO", "<c:url value='/gps/bbs/selectBbsList.do'/>", "bbsSearchVO");
}

<%--
/************************************************************************ 
함수명 : fn_detail                               
설   명 : 게시글상세보기
인   자 : bbsSn
사용법 : fn_detail()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 황기연       

      date    author      note 
 ----------   -------     ------------------- 
2011-09-15 	  황기연
 ************************************************************************/
 --%>
function fn_detail(bbsSn){
	$('#bbsSn').val(bbsSn);
	JQ_request("bbsSearchVO", "<c:url value='/gps/bbs/selectBbsDetail.do'/>", "bbsSearchVO");
}
<%-- 
/************************************************************************ 
fnc name : fn_register                      
outline : 게시글등록화면호출
parameter : 없음        
directions : fn_register()              
since : 2011-09-15    
author : 통계포털  황기연       

    date      author             note  
 ----------   -------     ------------------- 
 2011-09-15    황기연                                          
************************************************************************/ 
--%>
function fn_register(){
	$('#bbsSn').val(0);
	JQ_setProcessMsg();
	JQ_request("bbsSearchVO", "<c:url value='/gps/bbs/registerBbs.do'/>", "bbsSearchVO");
}

</script>
<!-- content start -->
	<!-- subpagebody_contents start -->
	<div id="subpagebody_contents">
		<form:form commandName="bbsSearchVO" method="post">
		<input type="submit" class="hidden">
			<form:hidden path="bbsId"/>
			<form:hidden path="bbsSn"/>
			<form:hidden path="pageIndex"/>
			<form:hidden path="menuId"/>
			<form:hidden path="leftMenuId"/>
		<table class="default" width="${bbsInfoManageVO.tableSize}" summary="${bbsInfoManageVO.bbsNm}게시판 정보">
		<caption>${bbsInfoVO.bbsNm}게시판 정보</caption>
		<tr>
			<td>
				<form name="frmBBSList" method="post">
				<table class="default" width="${bbsInfoManageVO.tableSize}" summary="${bbsInfoManageVO.bbsNm}게시판 정보">
				<caption>${bbsInfoVO.bbsNm}게시판 정보</caption>
				<!-- 게시물 정보 start -->
					<tr>
						<td>
							<table class="default" width="${bbsInfoVO.tableSize}" summary="${bbsInfoVO.bbsNm}게시판 정보">
							<caption>${bbsManageVO.bbsNm}게시판 정보</caption>
							<tr>
								<td>
									<table class="default" width="${bbsInfoVO.tableSize}" summary="${bbsInfoVO.bbsNm}게시판 정보">
									<caption>${bbsManageVO.bbsNm}게시판 정보</caption>
									<tr>
										<td>
											<table class="default" width="100%" summary="${bbsInfoVO.bbsNm}게시판 정보">
											<caption>${bbsInfoVO.bbsNm}게시판 정보</caption>
												<tr>
													<td class="tl">
														<table class="option">
														<tr>
															<c:if test="${bbsInfoVO.ctgryCodeUseAt eq 'Y'}">
											    			<td>
																<form:select path="searchCtgryCode" onchange="movePage()" cssClass="select">
																	<form:option value="">- 선택 -</form:option>
																</form:select>
															</td>
															</c:if>			
															<td><label for="searchCondition_1">검색조건</label></td>
															<td>
																<form:select path="searchCondition" id="searchCondition" title="검색조건">
																	<form:option value="">- 선택 -</form:option>
																	<form:option value="1">제목</form:option>
																	<form:option value="2">내용</form:option>
																	<form:option value="3">작성자</form:option>
																</form:select>
															</td>
															<td><label for="searchKeyword_1">검색키워드</label></td>
															<td>
																<form:input path="searchKeyword" cssClass="wi200" maxlength="35" id="searchKeyword" title="검색키워드"/>
															</td>
											    			<td><img src="/images/gps/board/0208.gif" alt="<spring:message code='button.search'/>" title="<spring:message code='button.search'/>" style="cursor:hand;" onclick="fn_search()"></td>
											    			<td><a href="<c:url value="/gps/bbs/selectBbsList.do"><c:param name="bbsId" value="${bbsSearchVO.bbsId}"/><c:param name="menuId" value="${bbsSearchVO.menuId}"/><c:param name="leftMenuId" value="${bbsSearchVO.leftMenuId}"/></c:url>"></td>
											    			<!-- <td><img src="/images/button/0318.png" alt="<spring:message code='button.initialization'/>" title="<spring:message code='button.initialization'/>"></a></td> -->
														</tr>
														</table>
													</td>
													<td>
														<dl class="board_counter">
															<dt class="total">전체</dt>
															<dd class="total"><c:out value='${paginationInfo.totalRecordCount}'/></dd>
															<dt class="page">페이지</dt>
															<dd class="page"><c:out value='${paginationInfo.currentPageNo}'/> / <c:out value='${paginationInfo.totalPageCount }'/></dd>
														</dl>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									</table>
								</td>
							</tr>
							</table>
						</td>
					</tr>
					<!-- 게시물 정보 end -->

				<!-- 게시물 리스트 start -->
				<tr>
					<td>
						<table class="boardImgList01" width="${bbsInfoManageVO.tableSize}" summary="${bbsInfoManageVO.bbsNm}에 ${paginationInfo.totalRecordCount}개의 게시물이 있습니다.">
						<caption>${bbsInfoManageVO.bbsNm} 게시물 입니다.</caption>
						<colgroup>
							<col class="number" />
							<col class="image" />
							<col class="*" />
							<col class="name" />
							<col class="hit" />
							<col class="writedate" />
						</colgroup>
						<thead>
							<tr>
								<th scope="col" class="first">번호</th>
								<th scope="col">이미지</th>
								<th scope="col">제목</th>
								<th scope="col">작성자</th>
								<th scope="col">조회</th>
								<th scope="col" class="end">등록일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="list" items="${bbsList}" varStatus="status">
							<tr>
								<td class="number"><c:out value="${paginationInfo.totalRecordCount - ((bbsSearchVO.pageIndex - 1) * bbsSearchVO.pageSize + (status.count-1))}"/></td>
								<td class="image">
								<a href="#LINK" onclick="fn_detail('<c:out value="${list.bbsSn}"/>');return false;" onkeypress="this.onclick();return false;">
									<c:choose>
										<c:when test="${!empty list.imageFileId}">
											<img src='${WebImagePath}?imageid=web/gps/bbs/${list.imageMask}&ext=${list.imageExt}' alt='<c:out value="${list.sj}"/>' title='<c:out value="${list.sj}"/>' style="width:100px;"/>
										</c:when>
										<c:otherwise>
											<img src="/images/common/no_image01.gif" alt="<spring:message code="gps.noImage.msg"/>" title="<spring:message code="gps.noImage.msg"/>">
										</c:otherwise>
									</c:choose>
								</a>
								</td>
								<td class="subject">
								<c:if test="${list.newIconUseAt eq 'Y' && !empty bbsInfoVO.newIconImage}">
										<img src='${WebImagePath}?imageid=web/gps/bbs/${bbsInfoVO.newIconImageMask}&ext=${bbsInfoVO.newIconImageExt}' alt='NEW ICON 이미지' title='NEW ICON 이미지'/>								
								</c:if>
								<c:if test="${list.hotIconUseAt eq 'Y' && !empty bbsInfoVO.hotIconImage}">
										<img src='${WebImagePath}?imageid=web/gps/bbs/${bbsInfoVO.hotIconImageMask}&ext=${bbsInfoVO.hotIconImageExt}' alt='HOT ICON 이미지' title='HOT ICON 이미지'/>
								</c:if>
								<c:if test="${list.coolIconUseAt eq 'Y' && !empty bbsInfoVO.coolIconImage}">
										<img src='${WebImagePath}?imageid=web/gps/bbs/${bbsInfoVO.coolIconImageMask}&ext=${bbsInfoVO.coolIconImageExt}' alt='COOL ICON 이미지' title='COOL ICON 이미지'/>
								</c:if>
								<c:if test="${bbsInfoVO.othbcMthUseAt eq 'Y'}">
									<img src="/images/gps/cmm/icon/secret.gif" alt="비공개">
								</c:if>
									<a href="#LINK" onclick="fn_detail('<c:out value="${list.bbsSn}"/>');return false;" onkeypress="this.onclick();return false;" title="<c:out value="${list.sj}" escapeXml="false"/>">
								<c:choose>
									<c:when test="${fn:length(list.sj) > titleLimit}">
										<c:out value="${fn:substring(list.sj,0,titleLimit)}" escapeXml="false"/>...
									</c:when>
									<c:otherwise>
										<c:out value="${list.sj}" escapeXml="false"/>
									</c:otherwise>
								</c:choose>
									</a>
								</td>
								<td class="name"><c:out value="${list.wrterNm}" escapeXml="true"/></td>
								<td class="hit">${list.co}</td>
								<td class="writedate"><c:out value="${list.registDt}"/></td>
							</tr>
							</c:forEach>
							<c:if test="${empty bbsList}">
								<tr> 
									<td align="center" colspan="6">
										<spring:message code="common.search.nodata.msg" />
									</td>
								</tr>   	          				 			   
							</c:if>
						</tbody>
						</table>
					</td>
				</tr>
				</table>
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<!-- 페이징 start -->
				<table class="datapaging" align="center" summary="${bbsInfoVO.bbsNm}게시판 정보">
				<caption>${bbsInfoVO.bbsNm}게시판 정보 페이지번호</caption>
				<tr>
					<td class="tc">
						<ui:pagination paginationInfo = "${paginationInfo}"	type="image" jsFunction="link_page" />
					</td>
				</tr>
				</table>
				<!-- 페이징 end -->
			</td>
		</tr>
		<tr>
			<td>
				<!-- 버튼 start -->
				<table class="default" summary="작성 버튼">
					<tr>
						<td class="fr">
							<table class="databutton" align="right">
							<!-- 작성 버튼 -->
							<tr>
								<td><img src="/images/gps/board/0202.gif" alt="<spring:message code='button.create'/>" title="<spring:message code='button.create'/>" style="cursor:hand;" onclick="fn_register();return false;" onkeydown="this.onclick();return false;"></td>
							</tr>
							<!-- 작성 버튼 종료 -->
							</table>
						</td>
					</tr>
				</table>
				<!-- 버튼 end -->
			</td>
		</tr>
		</table>
		</form:form>
	</div>
	<!-- subpagebody_contents end -->
<!-- content end -->
<jsp:include page="/gps/cmm/popupFooter.do" flush="false"/>