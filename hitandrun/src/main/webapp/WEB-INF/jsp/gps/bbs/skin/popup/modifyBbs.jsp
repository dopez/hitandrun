<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<jsp:include page="/gps/cmm/popupLeft.do" flush="false"></jsp:include>
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
JQ_setValidation('bbsVO');
JQ_setCalendar('bgnde','endde','from');
JQ_setCalendar('endde','bgnde','to');
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
	<c:if test="${noticeWriteAt eq 'N'}">
	$('#notice').attr('disabled',true);
	</c:if>
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
함수명 : fn_update                                  
설   명 : 수정처리
인   자 : 
사용법 : fn_update()              
작성일 : 2011-09-15
작성자 : 범정부통계포털 황기연       

      date    author      note 
 ----------   -------     ------------------- 
2011-09-15 	  황기연
 ************************************************************************/
 --%>
function fn_update(){
	JQ_setProcessMsg();
   	JQ_request("bbsVO", "<c:url value='/gps/bbs/updateBbs.do'/>");
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
함수명 : 게시글삭제                             
설   명 : 게시글삭제
인   자 : 
사용법 : fn_delete()              
작성일 : 2011-09-15
작성자 : 범정부통계포털 황기연       

      date    author      note 
 ----------   -------     ------------------- 
2011-09-15 	  황기연
 ************************************************************************/
 --%>
function fn_delete(){
	if (confirm("<spring:message code="common.delete.msg" />")) {
		JQ_setProcessMsg();
   		JQ_request("bbsVO","<c:url value='/gps/bbs/deleteBbs.do'/>");
	}
}

<%-- 
/************************************************************************ 
fnc name : fn_fileInspct(/js/gps/gpsCommon.js)                                 
outline : 첨부파일 확장자검사
parameter : 첨부파일 id       
directions : fn_fileInspct      
since : 2011-09-15   
author : 통계포털  황기연       

    date      author             note  
 ----------   -------     ------------------- 
 2011-09-15    황기연                       최초 생성                  
************************************************************************/ 
--%>
function fn_fileInspct(se,id){
	uploadFileInspct(se,id);
}
</script>
<!-- content start -->
	<!-- subpagebody_contents start -->
	<div id="subpagebody_contents">
		<form:form commandName="bbsSearchVO" method="post">
			<input type="submit" class="hidden" />
			<form:hidden path="bbsId"/>
			<form:hidden path="bbsSn"/>
			<form:hidden path="pageIndex"/>
			<form:hidden path="searchCtgryCode"/>
			<form:hidden path="searchCondition"/>
			<form:hidden path="searchKeyword"/>
			<form:hidden path="menuId"/>
			<form:hidden path="leftMenuId"/>
		</form:form>
		<form:form commandName="bbsVO" method="post" enctype="multipart/form-data">
			<input type="hidden" name="posblAtchFileNumber" id="posblAtchFileNumber" value="${bbsInfoVO.uploadFileCo}" />
			<input type="hidden" name="returnUrl" id="returnUrl" value="/gps/bbs/modifyBbs.do" />
			<input type="hidden" name="uploadableFileNum" id="uploadableFileNum"/>
			<input type="hidden" name="pageIndex" id="pageIndex" value="${bbsSearchVO.pageIndex}" />
			<input type="hidden" name="searchCtgryCode" id="searchCtgryCode" value="${bbsSearchVO.searchCtgryCode}" />
			<input type="hidden" name="searchCondition" id="searchCondition" value="${bbsSearchVO.searchCondition}" />
			<input type="hidden" name="searchKeyword" id="searchKeyword" value="${bbsSearchVO.searchKeyword}" />
			<input type="hidden" name="menuId" id="menuId" value="${bbsSearchVO.menuId}" />
			<input type="hidden" name="leftMenuId" id="leftMenuId" value="${bbsSearchVO.leftMenuId}" />
			<form:hidden path="bbsId"/>
			<form:hidden path="wrterId"/>
			<form:hidden path="bbsSn"/>
			<form:hidden path="fileListCnt"/>
			<form:hidden path="atchmnflId"/>
			<form:hidden path="imageFileId"/>
		<table class="default" width="${bbsInfoVO.tableSize}" summary="${bbsInfoVO.bbsNm}게시판 쓰기">
		<caption>${bbsInfoVO.bbsNm}쓰기</caption>
		<tr>
			<td>
				<!-- bbs write start -->
				<table class="default" width="${bbsInfoVO.tableSize}" summary="${bbsInfoVO.bbsNm}게시판 쓰기">
				<caption>${bbsInfoVO.bbsNm}쓰기</caption>
				<tr>
					<td>
						<table class="boardWrith01" width="${bbsInfoVO.tableSize}"summary="${bbsInfoVO.bbsNm}게시판 쓰기">
						<caption>${bbsInfoVO.bbsNm}쓰기</caption>
						<colgroup>
							<col width="150" />
							<col width="*" />
						</colgroup>
						<thead>
						<c:if test="${!empty bbsInfoVO.ctgryCode}">
							<tr>
								<th>분류#${bbsInfoVO.ctgryCode}</th>
								<td>
									<select name="ctgryCode" title="분류">
									<c:forEach var="code01" items="${cateList}">
										<option value="${code01.code_code}" <c:if test="${bbs_code01 eq code01.code_code}">selected</c:if>>${code01.code_fname}</option>
									</c:forEach>
									</select>
								</td>
							</tr>
						</c:if>
						<c:if test="${bbsInfoVO.noticeUseAt eq 'Y'}">
							<tr>
								<th>공지사항등록여부</th>
								<td>
									<table class="inside02" id="notice">
									<tr>
										<td class="input"><form:radiobutton path="noticeAt" value="Y" cssClass="noline"/></td>
										<td class="text">등록</td>
										<td class="input"><form:radiobutton path="noticeAt" value="N" cssClass="noline"/></td>
										<td class="text">미등록</td>
									</tr>
									</table>
								</td>
							</tr>
						</c:if>
						<tr>
							<th>제목</th>
							<td><form:input path="sj" maxlength="100" cssClass="wi300 validate[required,length[1,100]]" cssStyle="ime-mode:active"/></td>
						</tr>
						<c:if test="${bbsInfoVO.titleDecoUseAt eq 'Y'}">
							<tr>
								<th>제목색</th>
								<td><form:input path="sjColor" maxlength="100" cssClass="wi100 validate[optional,length[1,100]]" /></td>
							</tr>
						</c:if>
						<tr>
							<th>작성자</th>
							<td><form:input path="wrterNm" maxlength="10" cssClass="wi100 validate[required,length[1,20]]" cssStyle="ime-mode:active"/></td>
						</tr>
						<tr>
							<th>이메일</th>
							<td><form:input path="email" maxlength="50" cssClass="wi300 validate[optional,length[1,50],custom[email]" cssStyle="ime-mode:inactive"/></td>
						</tr>
						<tr>
							<th>홈페이지</th>
							<td><form:input path="hmpg" maxlength="50" cssClass="wi300 validate[optional,length[1,50]]" cssStyle="ime-mode:inactive"/></td>
						</tr>
						<tr>
							<th>내용</th>
							<td><form:textarea path="cn" cssClass="wi500 ht200"/></td>
							<%-- 에디터 사용여부 미정
							<c:choose>
								<c:when test="${bbsInfoVO.webeditorUseAt eq 'Y'}">
									<td>
									<form:hidden path="cn"/>
									에디터사용
									</td>
								</c:when>
								<c:otherwise>
									<td><form:textarea path="cn" cssClass="wi500 ht200"/></td>
								</c:otherwise>
							</c:choose>
							--%>
						</tr>
					<c:if test="${gpsSessionVO.usrId != bbsVO.registerId}">
						<tr>
							<th>비밀번호</th>
							<td>
								<table class="inside02">
								<tr>
									<td class="input"><input type="password" id="password" name="password" maxlength="10" class="wi80 validate[required,length[1,10]]"/></td>
									<td class="text">*로그인 정보가 없으면 게시글 수정/삭제시 비밀번호가 필요합니다.</td>
								</tr>
								</table>
							</td>
						</tr>
					</c:if>
					<c:if test="${bbsInfoVO.albumAt eq 'Y'}">
						<tr>
							<th>이미지파일</th>
							<td class="bottom_noline">
								<table class="inside01">
								<tr>
									<td class="img">
										<c:choose>
											<c:when test="${!empty bbsVO.imageFileId}">
												<img src="<c:url value="/cmm/fms/getImage.do"><c:param name="atchFileId" value="${bbsVO.imageFileId}"/><c:param name="fileSn" value="0"/></c:url>" alt="이미지" title="이미지" width="200">
											</c:when>
											<c:otherwise>
												<img src="/images/common/no_image01.gif" alt="<spring:message code="gps.noImage.msg"/>" title="<spring:message code="gps.noImage.msg"/>">
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
								<tr>
									<td><input name="imgFile" id="imgFile" type="file" onfocus="gfn_focus(this);" onblur="gfn_focusnot(this);" onchange="fn_fileInspct('img',this.id);return false;" title="첨부파일"/></td>
								</tr>
								</table>
							</td>
						</tr>
					</c:if>
					<c:if test="${bbsInfoVO.fileUploadUseAt eq 'Y'}">
						<tr>
							<th class="subject">첨부파일</th>
							<td>
								<table class="inside01">
								<tr>
									<td class="<c:out value="${!empty bbsVO.atchmnflId?'input':'input_bottom_noline'}"/>"><input type="file" name="file_1" id="egovComFileUploader" onfocus="gfn_focus(this);" onblur="gfn_focusnot(this);" title="첨부파일"/></td>
								</tr>
								<tr>
									<td class="bottom_noline">
										<div id="egovComFileList"></div>
										<c:if test="${!empty bbsVO.atchmnflId}">
										<c:import url="/cmm/fms/selectFileInfsForUpdate.do">
											<c:param name="param_atchFileId"  value="${bbsVO.atchmnflId}"/>					
										</c:import>
										</c:if>
									</td>
								</tr>
								</table>
							</td>
						</tr>
					</c:if>
						<c:if test="${bbsInfoVO.ntcePdUseAt eq 'Y'}">
							<tr>
								<th>게시기간</th>
								<td>
									<table class="inside01">
									<tr>
										<th class="subject">시작일</th>
										<td class="input"><form:input path="bgnde" cssClass="input_t input_nara_date wi100 validate[optional]"/></td>
									</tr>
									<tr>
										<th class="bottom_noline">종료일</th>
										<td class="input_bottom_noline"><form:input path="endde" cssClass="input_t input_nara_date wi100 validate[optional]"/></td>
									</tr>
									</table>
								</td>
							</tr>
						</c:if>
						</thead>
						</table>
					</td>
				</tr>
				<tr>
					<td class="pt5 pb5 fr">
						<table class="databutton">
							<tr>
								<td><img src="/images/gps/board/0201.gif" alt="<spring:message code="gps.button.list"/>" title="<spring:message code="gps.button.list"/>" style="cursor:hand;" onclick="fn_list();return false;" onkeypress="this.onclick();return false;"/></td>
								<c:if test="${bbsInfoVO.answerUseAt eq 'Y' && bbsVO.noticeAt ne 'Y' && answerButtonUseAt}">
									<td><img src="/images/gps/board/0219.gif" alt="<spring:message code="gps.button.answer"/>" title="<spring:message code="gps.button.answer"/>" style="cursor:hand;" onclick="fn_answer('<c:out value="${bbsVO.bbsSn}"/>');return false;" onkeypress="this.onclick();return false;"/></td>
								</c:if>
								<td><img src="/images/gps/board/0209.gif" alt="<spring:message code="gps.button.update"/>" title="<spring:message code="gps.button.update"/>" style="cursor:hand;" onclick="fn_update();return false;" onkeypress="this.onclick();return false;"/></td>
								<td><img src="/images/gps/board/0204.gif" alt="<spring:message code="gps.button.delete"/>" title="<spring:message code="gps.button.delete"/>" style="cursor:hand;" onclick="fn_delete();return false;" onkeypress="this.onclick();return false;"/></td>
							</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		</table>
	</form:form>
	</div>
	<!-- subpagebody_contents end -->
<!-- content end -->
<c:if test="${bbsInfoVO.fileUploadUseAt eq 'Y'}">
<script type="text/javascript">
	  var existFileNum; 
	  var maxFileNum; 
	  var uploadableFileNum;
	  existFileNum = $('#fileListCnt').val();// 이 값은 File List를 조회하는 부분에 담겨온다.
	  maxFileNum = $('#posblAtchFileNumber').val(); // 각 비즈니스 로직에서는 해당하는 폼 값에 첨부가능한 최대파일 숫자를 세팅해둬야 함
	  uploadableFileNum = maxFileNum - existFileNum; // 최대등록가능한 파일숫자에서 기존에 등록된 숫자를 뺀다.
	  //alert("uploadableFileNum = " + uploadableFileNum + "\n" + "maxFileNum = " + maxFileNum + "\n" + "existFileNum = " + existFileNum);
	  if(uploadableFileNum <= 0) {
		  $('#uploadableFileNum').val(0);
	  }else{
		  $('#uploadableFileNum').val(uploadableFileNum);
	  }
      var multi_selector = new MultiSelector(document.getElementById( 'egovComFileList' ) , $('#uploadableFileNum').val() );
	  multi_selector.addElement(document.getElementById('egovComFileUploader' )); 
 </script>
 </c:if>
<jsp:include page="/gps/cmm/popupFooter.do" flush="false"/>