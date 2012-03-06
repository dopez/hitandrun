<%-- 
/** 
 * outline   : 게시판관리 게시판 상세화면
 * filename : bbsDetail.jsp
 * @author 포탈통계 이관형
 * @since 2011.06.28
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == 개정이력(Modification Information) == 
 *   
 *   date        author     note 
 * ----------    -------    --------------------------- 
 * 2011.06.20     이관형           최초 생성 
 * </pre> 
 */
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- Header Start ==========================================================--%>
<%@include file="/WEB-INF/jsp/gps/cmm/admHeader.jsp" %>
<%-- Header Start ==========================================================--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko">
<script type="text/javaScript" language="javascript">
<!--

JQ_setValidation('searchVO');
JQ_onload();

<!--
<%-- 
/************************************************************************ 
함수명 : fncPageOnload                                   
설   명 : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
인   자 : 없음        
사용법 : fncPageOnload()              
작성일 : 2011-06-20   
작성자 : 통계포탈 이관형

      date    author      note 
 ----------   -------     ------------------- 
             
************************************************************************/
--%>
function fncPageOnload()
{
	//frameset controller start
	//parent.frmtop.setFrameCtl();
	//frameset controller end
}

<%-- 
/************************************************************************ 
함수명 : fn_list                                   
설   명 : "목록"버튼을 클릭했을때 게시물 리스트로 돌아가는 화면       
인   자 : 없음        
사용법 : fn_list()
작성일 : 2011-06-20   
작성자 : 통계포탈 이관형

      date    author      note 
 ----------   -------     ------------------- 
                                 
************************************************************************/
--%>
function fn_list(){
	JQ_request("bbsManageVO", "<c:url value='/gps/adm/bbs/selectBbsList.do'/>", "bbsManageVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_modify                                   
설   명 : "수정"버튼을 클릭했을때 게시물 수정 화면으로 이동하는 함수
		서버단으로 이동하여 게시물의 키값으로 게시물 정보를 다시 조회해옴
인   자 : 없음(form에 정의되어있는 인자)
사용법 : fn_modify()
작성일 : 2011-06-20   
작성자 : 통계포탈 이관형

      date    author      note 
 ----------   -------     ------------------- 
                                 
************************************************************************/
--%>
function fn_modify(){

	JQ_request("bbsManageVO", "<c:url value='/gps/adm/bbs/modifyBbs.do'/>", "bbsManageVO");
}

<%-- 
/************************************************************************ 
함수명 : fn_delete                                   
설   명 : "삭제"버튼을 클릭했을때 해당 키값에 대한 게시물을 삭제하는 함수
		게시물에대한 정보를 삭제한 후 컨트롤러를 통해 게시물 목록으로 이동한다
인   자 : 없음(form에 정의되어있는 인자)
사용법 : fn_delete()
작성일 : 2011-06-20   
작성자 : 통계포탈 이관형

      date    author      note 
 ----------   -------     ------------------- 
                                 
************************************************************************/ 
--%>

function fn_delete(){
	if (confirm("<spring:message code='common.delete.msg' />")) {
		JQ_request("bbsManageVO", "<c:url value='/gps/adm/bbs/deleteBbsDetail.do'/>", "bbsManageVO");
	}
}
-->
</script>
<div class="ui-layout-content">

	<form:form commandName="bbsManageVO" name="selectForm" method="post">
	<form:hidden path="bbsId"/>
	<form:hidden path="jssfcCode"/>
	<form:hidden path="pageIndex"/>
	<!-- contents_area start -->
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
					<table class="default">
						<tr>
							<td>
								<table class="title">
									<tr>
										<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="타이틀이미지" title="타이틀이미지"/></td>
										<td class="title">게시판관리</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table summary="게시판등록" class="write01">
								<caption>게시판등록</caption>
								<tr>
									<th class="subject">아이디</th>
									<td class="input">
										${result.bbsId}
									</td>
								</tr>
								<tr>
									<th class="subject">이름</th>
									<td class="input">
										${result.nm}
									</td>
								</tr>
								<tr>
									<th class="subject">영문이름</th>
									<td class="input">
										${result.engNm}
									</td>
								</tr>
								<tr>
									<th class="subject">게시판구분</th>
									<td class="input">
										${result.grad}
									</td>
								</tr>
								<tr>
									<th class="subject">등급</th>
									<td class="input">
										${result.psitn}
									</td>
								</tr>
								<tr>
									<th class="subject">회원소속</th>
									<td class="input">
										${result.se}
									</td>
								</tr>
								<tr>
									<th class="subject">사진 파일명</th>
									<td class="input">
										${result.photoCpcty}
									</td>
								</tr>
								<tr>
									<th class="subject">사진 마스크</th>
									<td class="input">
										${result.photoFileNm}
									</td>
								</tr>
								<tr>
									<th class="subject">사진 용량</th>
									<td class="input">
										${result.photoFileMask}
									</td>
								</tr>
								<tr>
									<th class="subject">우편번호</th>
									<td class="input">
										${result.zip}
									</td>
								</tr>
								<tr>
									<th class="subject">주소 읍면동 이상</th>
									<td class="input">
										${result.adres1}
									</td>
								</tr>
								<tr>
									<th class="subject">주소 읍면동 이후 나머지</th>
									<td class="input">
										${result.adres2}
									</td>
								</tr>
								<tr>
									<th class="subject">전화번호</th>
									<td class="input">
										${result.telNo}
									</td>
								</tr>
								<tr>
									<th class="subject">팩스번호</th>
									<td class="input">
										${result.faxNo}
									</td>
								</tr>
								<tr>
									<th class="subject">휴대전화</th>
									<td class="input">
										${result.moblphon}
									</td>
								</tr>
								<tr>
									<th class="subject">E-MAIL</th>
									<td class="input">
										${result.email}
									</td>
								</tr>
								<tr>
									<th class="subject">HOMEPAGE URL</th>
									<td class="input">
										${result.hmpgUrl}
									</td>
								</tr>
								<tr>
									<th class="subject">관심 분야</th>
									<td class="input">
										${result.intrstRealm}
									</td>
								</tr>
								<tr>
									<th class="subject">각종 코드</th>
									<td class="input">
										${result.jssfcCode}
									</td>
								</tr>
								<tr>
									<th class="subject">직장명</th>
									<td class="input">
										${result.wrcNm}
									</td>
								</tr>
								<tr>
									<th class="subject">부서명</th>
									<td class="input">
										${result.deptNm}
									</td>
								</tr>
								<tr>
									<th class="subject">직책</th>
									<td class="input">
										${result.rspofc}
									</td>
								</tr>
								<tr>
									<th class="subject">직장 전화번호</th>
									<td class="input">
										${result.wrcTelno}
									</td>
								</tr>
								<tr>
									<th class="subject">직장 우편번호</th>
									<td class="input">
										${result.wrcZip}
									</td>
								</tr>
								<tr>
									<th class="subject">직장주소 읍면동 이상</th>
									<td class="input">
										${result.wrcAdres1}
									</td>
								</tr>
								<tr>
									<th class="subject">직장주소 읍면도 이후 나머지</th>
									<td class="input">
										${result.wrcAdres2}
									</td>
								</tr>
								<tr>
									<th class="subject">우편물 발송 주소</th>
									<td class="input">
										${result.pstmtrSndngAdres}
									</td>
								</tr>
								<tr>
									<th class="subject">인사말</th>
									<td class="input">
										${result.grt}
									</td>
								</tr>
								<tr>
									<th class="subject">인사말 공개여부</th>
									<td class="input">
										${result.grtOthbcAt}
									</td>
								</tr>
								<tr>
									<th class="subject">약력 공개여부</th>
									<td class="input">
										${result.histOthbcAt}
									</td>
								</tr>
								<tr>
									<th class="subject">기타사항 공개여부</th>
									<td class="input">
										${result.etcMatterOthbcAt}
									</td>
								</tr>
								<tr>
									<th class="subject">사진 공개여부</th>
									<td class="input">
										${result.photoOthbcAt}
									</td>
								</tr>
								<tr>
									<th class="subject">홈페이지 공개여부</th>
									<td class="input">
										${result.hmpgOthbcAt}
									</td>
								</tr>
								<tr>
									<th class="subject">E-MAIL 공개여부</th>
									<td class="input">
										${result.emailOthbcAt}
									</td>
								</tr>
								<tr>
									<th class="subject">전화번호 공개여부</th>
									<td class="input">
										${result.telnoOthbcAt}
									</td>
								</tr>
								<tr>
									<th class="subject">휴대전화 공개여부</th>
									<td class="input">
										${result.moblphonOthbcAt}
									</td>
								</tr>
								<tr>
									<th class="subject">직장명 공개여부</th>
									<td class="input">
										${result.wrcOthbcAt}
									</td>
								</tr>
								<tr>
									<th class="subject">SMS 수신여부</th>
									<td class="input">
										${result.smsRecptnAt}
									</td>
								</tr>
								<tr>
									<th class="subject">뉴스레터 수신여부</th>
									<td class="input">
										${result.newsRecptnAt}
									</td>
								</tr>
								<tr>
									<th class="subject">로그인 횟수</th>
									<td class="input">
										${result.loginCo}
									</td>
								</tr>
								<tr>
									<th class="subject">마지막 로그인시간</th>
									<td class="input">
										${result.lastLoginTime}
									</td>
								</tr>
								<tr>
									<th class="subject">등록일시</th>
									<td class="input">
										${result.registDt}
									</td>
								</tr>
								<tr>
									<th class="subject">수정일시</th>
									<td class="input">
										${result.updtDt}
									</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td align="center">
								<table class="rbuttonarea">
									<tr>
										<td><img src="/images/button/0209.png" alt="<spring:message code='button.update'/>" title="<spring:message code='button.update'/>" onclick = "fn_modify();"></td>
										<td><img src="/images/button/0204.png" alt="<spring:message code='button.delete'/>" title="<spring:message code='button.delete'/>" onclick = "fn_delete();"></td>
										<td><img src="/images/button/0201.png" alt="<spring:message code='button.list'/>" title="<spring:message code='button.list'/>" onclick = "fn_list();"></td>
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
	</form:form>
</div>