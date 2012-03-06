<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<jsp:include page="/${bbsInfoVO.sysSe}/cmm/left.do" flush="false"></jsp:include>
<%-- 
/** 
 * outline   : 게시글등록페이지
 * filename : registerBbs.jsp
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
JQ_setValidation('bbsMemoVO');
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
	topStatMenuImage(0);
	subTopStatMenuImage(0);
	leftStatMenuImage(0);
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
	$('#bbsSearchVO').find('#bbsSn').val(bbsSn);
	JQ_request("bbsSearchVO", "<c:url value='/gps/bbs/selectBbsDetail.do'/>", "bbsSearchVO");
}

<%--
/************************************************************************ 
함수명 : fn_list                                   
설   명 : 목록페이지 호출
인   자 : 
사용법 : fn_list()              
작성일 : 2011-09-15
작성자 : 범정부통계포털 황기연       

      date    author      note 
 ----------   -------     ------------------- 
2011-09-15 	  황기연
 ************************************************************************/
 --%>
function fn_list(){
	JQ_setProcessMsg();
   	JQ_request("bbsSearchVO", "<c:url value='/gps/bbs/selectBbsList.do'/>", "bbsSearchVO");
}

<%--
/************************************************************************ 
함수명 : 게시글답글페이지호출                             
설   명 : 게시글답글페이지호출
인   자 : 
사용법 : fn_answer()              
작성일 : 2011-09-15
작성자 : 범정부통계포털 황기연       

      date    author      note 
 ----------   -------     ------------------- 
2011-09-15 	  황기연
 ************************************************************************/
 --%>
function fn_answer(){
	JQ_setProcessMsg();
   	JQ_request("bbsSearchVO","<c:url value='/gps/bbs/registerBbs.do'/>","bbsSearchVO");
}

<%--
/************************************************************************ 
함수명 : 게시글수정페이지호출                             
설   명 : 게시글수정페이지호출
인   자 : 
사용법 : fn_modify()              
작성일 : 2011-09-15
작성자 : 범정부통계포털 황기연       

      date    author      note 
 ----------   -------     ------------------- 
2011-09-15 	  황기연
 ************************************************************************/
 --%>
function fn_modify(){
	JQ_setProcessMsg();
   	JQ_request("bbsSearchVO","<c:url value='/gps/bbs/modifyBbs.do'/>","bbsSearchVO");
}

<%--
/************************************************************************ 
함수명 : 메모글등록                             
설   명 : 메모글등록
인   자 : 
사용법 : fn_list()              
작성일 : 2011-09-15
작성자 : 범정부통계포털 황기연       

      date    author      note 
 ----------   -------     ------------------- 
2011-09-15 	  황기연
 ************************************************************************/
 --%>
function fn_bbsMemoInsert(){
	JQ_setProcessMsg();
   	JQ_request("bbsMemoVO","<c:url value='/gps/bbs/insertBbsMemo.do'/>");
}


<%-- 
/************************************************************************ 
fnc name : fn_memoDelete    
outline :  메모삭제    
parameter : 메모순번
directions : fn_mngrSearch()              
since : 2011-05-10   
author : 통계포털 황기연       

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_memoDelete(memoSn,memoDeletePassword) {
	if (confirm("<spring:message code="common.delete.msg" />")) {
		$('#memoSn').val(memoSn);
		$('#memoDeletePassword').val(memoDeletePassword);
		JQ_setProcessMsg();
	   	JQ_request("bbsMemoVO","<c:url value='/gps/bbs/deleteBbsMemo.do'/>","bbsMemoVO");
	}
}

</script>
<!-- content start -->
	<!-- subpagebody_contents start -->
	<div id="subpagebody_contents">
	<%-- 검색조건 form start --%>
		<form:form commandName="bbsSearchVO" method="post">
		<input type="submit" class="hidden">
			<form:hidden path="bbsId"/>
			<form:hidden path="bbsSn"/>
			<form:hidden path="pageIndex"/>
			<form:hidden path="searchCtgryCode"/>
			<form:hidden path="searchCondition"/>
			<form:hidden path="searchKeyword"/>
			<form:hidden path="menuId"/>
			<form:hidden path="leftMenuId"/>
		</form:form>
	<%-- 검색조건 form end --%>
		<table class="default" width="${bbsInfoVO.tableSize}" summary="${bbsInfoVO.bbsNm}게시판 정보">
		<caption>${bbsInfoVO.bbsNm}</caption>
		<tr>
			<td>
				<!-- bbs read start -->
					<%-- 게시글 form start --%>
					<form:form commandName="bbsVO" method="post">
					<input type="submit" class="hidden">
						<table class="default" width="${bbsInfoVO.tableSize}" summary="${bbsInfoVO.bbsNm}게시판 정보">
						<caption>${bbsInfoVO.bbsNm}</caption>
						<tr>
							<td>
								<table class="boardViewList01" width="${bbsInfoVO.tableSize}"summary="${bbsInfoVO.bbsNm}게시판 정보">
								<caption>${bbsInfoVO.bbsNm}</caption>
								<colgroup>
									<col class="*" />
									<col class="writedate" />
									<col class="hit" />
								</colgroup>
								<thead>
									<tr>
										<th scope="col">제목</th>
										<th scope="col">등록일</th>
										<th scope="col" class="end">조회</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td class="subject">
											<c:if test="${bbsVO.noticeAt eq 'Y'}">[공지]</c:if>
											<c:out value="${bbsVO.sj}"/>
										</td>
										<td class="writedate"><c:out value="${bbsVO.registDt}"/></td>
										<td class="hit"><c:out value="${bbsVO.co}"/></td>
									</tr>
								</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table class="boardView01" width="${bbsInfoVO.tableSize}" summary="${bbsInfoVO.bbsNm}게시판 정보">
								<caption>${bbsInfoVO.bbsNm}</caption>
								<colgroup>
									<col width="15%" />
									<col width="*" />
								</colgroup>
								<thead>
									<tr>
										<th>등록자</th>
										<td><c:out value="${bbsVO.wrterNm}"/></td>
									</tr>
									<c:if test="${bbsInfoVO.albumAt eq 'Y'}">
									<tr id="TitleImageViewLayer">
										<th class="subject">이미지</th>
										<td class="img">
											<c:choose>
												<c:when test="${!empty bbsVO.imageFileId}">
													<img src='${WebImagePath}?imageid=web/gps/bbs/${bbsVO.imageMask}&ext=${bbsVO.imageExt}' alt='<c:out value="${bbsVO.sj}"/>' title='<c:out value="${bbsVO.sj}"/>' style="width:100px;"/>
												</c:when>
												<c:otherwise>
													<img src="/images/common/no_image01.gif" alt="<spring:message code="gps.noImage.msg"/>" title="<spring:message code="gps.noImage.msg"/>">
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
									</c:if>
									<tr>
										<th>이메일</th>
										<td class="mail"><c:out value="${bbsVO.email}"/></td>
									</tr>
									<tr>
										<th>홈페이지</th>
										<td class="homepage"><c:out value="${bbsVO.hmpg}"/></td>
									</tr>
									<!-- file list start -->
									<c:if test="${bbsInfoVO.fileUploadUseAt eq 'Y' && !empty bbsVO.atchmnflId}">
										<tr>
											<th class="subject">첨부파일</th>
											<td>			
											<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="UTF-8">
												<c:param name="param_atchFileId" value="${bbsVO.atchmnflId}" />				
											</c:import>
											</td>
										</tr>
									</c:if>
									<!-- file list end -->
								</thead>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table class="boardViewContent01" width="${bbsInfoVO.tableSize}" summary="${bbsInfoVO.bbsNm}게시판 정보">
								<caption>${bbsInfoVO.bbsNm}</caption>
								<tr>
									<td class="content">
									<c:out value="${bbsVO.cn}" escapeXml="false"/>
									</td>
								</tr>
								</table>
							</td>
						</tr>
						<!-- front and rear list start -->
						<c:if test="${bbsInfoVO.arndUseAt eq 'Y' && bbsVO.noticeAt ne 'Y'}">
						<tr>
							<td>
								<table width="${bbsInfoVO.tableSize}" class="prenext" summary="${bbsInfoVO.bbsNm}게시판 정보">
								<caption>${bbsInfoVO.bbsNm}</caption>
								<tr>
									<td>
										<table class="prenextList" summary="${bbsVO.sj} 이전글 입니다.">
										<caption>${bbsVO.sj} 이전글 입니다</caption>
										<tr>
											<td><img src="/images/gps/board/prev_icon.gif" alt="이전글" title="이전글"></td>
								<c:choose>
									<c:when test="${bbsVO.beforeBbsSn > 0}">
										<c:if test="${bbsInfoVO.albumAt eq 'Y'}">
											<td>
												<a href="#LINK" onclick="fn_detail('<c:out value="${bbsVO.beforeBbsSn}"/>');return false;" onkeypress="this.onclick();return false;">
													<c:choose>
														<c:when test="${!empty bbsVO.beforeImageFileId}">
															<img src='${WebImagePath}?imageid=web/gps/bbs/${bbsVO.beforeImageMask}&ext=${bbsVO.beforeImageExt}' alt='<c:out value="${bbsVO.beforeSj}"/>' title='<c:out value="${bbsVO.beforeSj}"/>' style="width:100px;"/>
														</c:when>
														<c:otherwise>
															<img src="/images/common/no_image01.gif" alt="<spring:message code="gps.noImage.msg"/>" title="<spring:message code="gps.noImage.msg"/>">
														</c:otherwise>
													</c:choose>
												</a>
											</td>
										</c:if>
											<td class="icon"><img src="/images/gps/board/secret.gif" alt="비공개" title="비공개"></td>
											<td class="subject"><a href="#LINK" onclick="fn_detail('<c:out value="${bbsVO.beforeBbsSn}"/>');return false;"><c:out value="${bbsVO.beforeSj}"/></a></td>
									</c:when>
										<c:otherwise>
											<td class="subject">이전글이 없습니다.</td>
										</c:otherwise>
									</c:choose>
										</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table class="prenextList" summary="${bbsVO.sj} 다음글 입니다.">
										<caption>${bbsVO.sj} 다음글 입니다.</caption>
										<tr>
											<td><img src="/images/gps/board/next_icon.gif" alt="다음글" title="다음글"></td>
								<c:choose>
									<c:when test="${bbsVO.nextBbsSn > 0}">
										<c:if test="${bbsInfoVO.albumAt eq 'Y'}">
											<td>
												<a href="#LINK" onclick="fn_detail('<c:out value="${bbsVO.nextBbsSn}"/>');return false;" onkeypress="this.onclick();return false;">
													<c:choose>
														<c:when test="${!empty bbsVO.nextImageFileId}">
															<img src='${WebImagePath}?imageid=web/gps/bbs/${bbsVO.nextImageMask}&ext=${bbsVO.nextImageExt}' alt='<c:out value="${bbsVO.nextSj}"/>' title='<c:out value="${bbsVO.nextSj}"/>' style="width:100px;"/>
														</c:when>
														<c:otherwise>
															<img src="/images/common/no_image01.gif" alt="<spring:message code="gps.noImage.msg"/>" title="<spring:message code="gps.noImage.msg"/>">
														</c:otherwise>
													</c:choose>
												</a>
											</td>
										</c:if>
										<td class="icon"><img src="/images/gps/board/secret.gif" alt="비공개" title="비공개"></td>
										<td class="subject"><a href="#LINK" onclick="fn_detail('<c:out value="${bbsVO.nextBbsSn}"/>');return false;"><c:out value="${bbsVO.nextSj}"/></a></td>
									</c:when>
									<c:otherwise>
										<td class="subject">다음글이 없습니다.</td>
									</c:otherwise>
								</c:choose>
										</tr>
										</table>
									</td>
								</tr>
								</table>
							</td>
						</tr>
						</c:if>
						<!-- front and rear list end -->
						
						<!-- button start -->
						<tr>
							<td class="pt5 pb5 fr">
								<table class="databutton">
								<tr>
									<c:if test="${bbsInfoVO.answerUseAt eq 'Y' && bbsVO.noticeAt ne 'Y' && answerButtonUseAt}">
										<td><img src="/images/gps/board/0219.gif" alt="<spring:message code="gps.button.answer"/>" title="<spring:message code="gps.button.answer"/>" style="cursor:hand;" onclick="fn_answer();return false;" onkeypress="this.onclick();return false;"/></td>
									</c:if>
									<c:if test="${writeButtonUseAt}">
									<td><img src="/images/gps/board/0209.gif" alt="<spring:message code="gps.button.update"/>" title="<spring:message code="gps.button.update"/>" style="cursor:hand;" onclick="fn_modify();return false;" onkeypress="this.onclick();return false;"/></td>
									</c:if>
									<td><img src="/images/gps/board/0201.gif" alt="<spring:message code="gps.button.list"/>" title="<spring:message code="gps.button.list"/>" style="cursor:hand;" onclick="fn_list();return false;" onkeypress="this.onclick();return false;"/></td>
								</tr>
								</table>
							</td>
						</tr>
						<!-- button end -->
						</table>
					<!-- bbs read end -->
					</form:form>
					<%-- 게시글 form end --%>
				</td>
			</tr>
			<tr>
				<td>
					<%-- 게시글 관련 메모 form start --%>
					<form:form commandName="bbsMemoVO" method="post">
						<input type="submit" class="hidden">
						<form:hidden path="bbsId"/>
						<form:hidden path="bbsSn"/>
						<form:hidden path="memoSn"/>
						<form:hidden path="memoDeletePassword"/>
						<form:hidden path="pageIndex"/>
						<form:hidden path="searchCtgryCode"/>
						<form:hidden path="searchCondition"/>
						<form:hidden path="searchKeyword"/>
						<form:hidden path="menuId"/>
						<form:hidden path="leftMenuId"/>
						<form:hidden path="memoWrterId"/>
						<table class="default" width="${bbsInfoVO.tableSize}" summary="${bbsInfoVO.bbsNm}게시판 정보">
							<!-- memo start -->
							<c:if test="${bbsInfoVO.memoUseAt eq 'Y' && bbsVO.noticeAt ne 'Y'}">
								<tr>
									<td>
										<table width="${bbsInfoVO.tableSize}" class="prenext" summary="${bbsInfoVO.bbsNm}게시판 정보 메모작성">
										<caption>${bbsInfoVO.bbsNm} 메모작성</caption>
									<%-- 아바타 기능 사용여부 미정
										<!-- avata list start -->
									<c:if test="${bbsInfoVO.avataUseAt eq 'Y'}">
										<tr>
											<td>
												<table width="${bbsInfoVO.tableSize}">
												<tr>
												<c:forEach begin="1" end="10" varStatus="i">
													<td><img src="" title="아바타이미지" alt="아바타이미지"/></td>
												</c:forEach>
												</tr>
												<tr>
												<c:forEach begin="1" end="10" varStatus="i">
													<td><input type="radio" name="memo_avata" style="border:0;" value="avata<fmt:formatNumber value="${i.index}" pattern="00"/>.gif" <c:if test="${i.first}">checked</c:if>></td>
												</c:forEach>
												</tr>
												</table>
											</td>
										</tr>
									</c:if>
									--%>
										<tr>
											<td>
												<table class="default" width="${bbsInfoVO.tableSize}">
												<!-- memo input form start -->
												<tr>
													<td>
														<form:textarea path="memoCn" cssClass="wi500 ht100 validate[required,length[1,1000]]" />
													</td>
												</tr>
												<tr>
													<td>
														<table border="0" cellpadding="0" cellspacing="0" align="right">
														<tr>
															<td>작성자</td>
															<td class="wi10"></td>
															<td><form:input path="memoWrterNm" maxlength="10" cssClass="wi100 validate[required,length[1,10]]"/></td>
															<td class="wi10"></td>
															<td>비밀번호</td>
															<td class="wi10"></td>
															<td><form:password path="memoWrterPassword" maxlength="10" cssClass="wi100 validate[required,length[1,10]]"/></td>
															<td class="wi2"></td>
															<td class="pt1"><img src="/images/gps/board/0220.gif" class="noline" alt="<spring:message code="gps.button.save"/>" title="<spring:message code="gps.button.save"/>" style="cursor:hand;" onclick="fn_bbsMemoInsert();return false;" onkeypress="this.onclick();return false;"/></td>
														</tr>
														</table>
													</td>
												</tr>
												<!-- memo input form end -->
												</table>
											</td>
										</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td><img src="/img/memo_list.gif" alt="메모작성" border="0"></td>
								</tr>
								<tr>
									<td>
										<table class="default" width="${bbsInfoVO.tableSize}" summary="${bbsInfoVO.bbsNm}게시판 관련글 정보">
										<caption>${bbsVO.sj}의 관련글 입니다.</caption>
											<colgroup>
												<col class="name" />
												<col class="*" />
												<col class="writedate" />
												<col class="delete" />
											</colgroup>
											<thead>
												<tr>
													<th scope="col" class="first">작성자</th>
													<th scope="col">내용</th>
													<th scope="col">작성일</th>
													<th scope="col" class="end">삭제</th>
												</tr>
											</thead>
											<tbody>
											<c:forEach var="list" items="${bbsMemoList}" varStatus="status">
												<tr>
													<td class="name">${list.memoWrterNm}</td>
													<td class="subject"><c:out value="${list.memoCn}" escapeXml="false"/></td>
													<td class="writedate"><c:out value="${list.registDt}"/></td>
													<td class="tc">
														<input type="text" id="memoDeletePassword_<c:out value="${status.count }"/>" title="비밀번호"/>
														<a href="#LINK" onclick="fn_memoDelete('${list.memoSn}',$('#memoDeletePassword_<c:out value="${status.count }"/>').val());" onkeypress="this.onclick();">삭제</a>
													</td>
												</tr>
											</c:forEach>
											<c:if test="${empty bbsMemoList}">
												<tr>
													<td colspan="4" class="tc">작성된 메모가 없습니다.</td>
												</tr>
											</c:if>
											</tbody>
										</table>
									</td>
								</tr>
							</c:if>
							<!-- memo list end -->
						</table>
					</form:form>
					<%-- 게시글 form end --%>
				</td>
			</tr>
		</table>
	</div>
	<!-- subpagebody_contents end -->
<!-- content end -->
<jsp:include page="/${bbsInfoVO.sysSe}/cmm/footer.do" flush="false"/>