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
	topStatMenuImage(0);
	subTopStatMenuImage(0);
	leftStatMenuImage(0);
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
함수명 : fn_insert                                   
설   명 : 입력처리
인   자 : 
사용법 : fn_insert()              
작성일 : 2011-09-15
작성자 : 범정부통계포털 황기연       

      date    author      note 
 ----------   -------     ------------------- 
2011-09-15 	  황기연
 ************************************************************************/
 --%>
function fn_insert(){
	JQ_setProcessMsg();
   	JQ_request("bbsVO", "<c:url value='/gps/bbs/insertBbs.do'/>");
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
			<form:hidden path="bbsId"/>
			<form:hidden path="bbsSn"/>
			<form:hidden path="wrterId"/>
			<form:hidden path="pageIndex"/>
			<form:hidden path="searchCtgryCode"/>
			<form:hidden path="searchCondition"/>
			<form:hidden path="searchKeyword"/>
			<form:hidden path="menuId"/>
			<form:hidden path="leftMenuId"/>
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
								<th>분류</th>
								<td>
									<select name="ctgryCode">
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
						<c:if test="${bbsInfoVO.titleDecoUseAt eq 'Y'}">
							<tr>
								<th>제목색</th>
								<td><form:input path="sjColor" maxlength="100" cssClass="wi300 validate[optional,length[1,100]]" /></td>
							</tr>
						</c:if>
						<tr>
							<th>제목</th>
							<td><form:input path="sj" maxlength="100" cssClass="wi500 validate[required,length[1,100]]" cssStyle="ime-mode:active"/></td>
						</tr>
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
							<td><form:textarea path="cn" cssClass="wi500 ht200" cssStyle="ime-mode:active" /></td>
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
						<tr>
							<th>비밀번호</th>
							<td>
								<form:password path="password" maxlength="20" cssClass="wi100 validate[required,length[1,20]]"/><br/>
								* 로그인 정보가 없으면 게시글 수정/삭제시 비밀번호가 필요합니다.
							</td>
						</tr>
						<c:if test="${bbsInfoVO.albumAt eq 'Y'}">
							<tr>
								<th>이미지파일</th>
								<td><input name="imgFile" id="imgFile" type="file" onfocus="gfn_focus(this);" onblur="gfn_focusnot(this);" onchange="fn_fileInspct('img',this.id);return false;" title="첨부파일"/></td>
							</tr>
						</c:if>
						<c:if test="${bbsInfoVO.fileUploadUseAt eq 'Y'}">
							<tr>
								<th class="subject">첨부파일</th>
								<td><input name="file_1" id="egovComFileUploader" type="file" onfocus="gfn_focus(this);" onblur="gfn_focusnot(this);" onchange="fn_fileInspct('file',this.id);return false;" title="첨부파일"/><div id="egovComFileList"></div></td>
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
							<td><img src="/images/gps/board/0210.gif" alt="<spring:message code="gps.button.save"/>" title="<spring:message code="gps.button.save"/>" style="cursor:hand;" onclick="fn_insert();" onkeypress="this.onclick();"/></td>
							<td><img src="/images/gps/board/0201.gif" alt="<spring:message code="gps.button.list"/>" title="<spring:message code="gps.button.list"/>" style="cursor:hand;" onclick="fn_list();" onkeypress="this.onclick();"/></td>
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
	var maxFileNum = $('#posblAtchFileNumber').val();
	if(maxFileNum==null || maxFileNum==""){
	  maxFileNum = <c:out value="${bbsInfoVO.uploadFileCo}"/>;
	}
	var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
	multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
</script>
</c:if>
<jsp:include page="/${bbsInfoVO.sysSe}/cmm/footer.do" flush="false"/>