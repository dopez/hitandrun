<%-- 
/** 
 * outline   : 게시판코드관리 게시판코드목록 조회화면
 * filename : bbsList.jsp
 * @author 통계포탈 이관형 
 * @since 2011.06.28
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == 개정이력(Modification Information) == 
 *   
 *   date        author     note 
 * ----------    -------    --------------------------- 
 * 2011.06.28     이관형           최초 생성 
 * </pre> 
 */
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- Header Start ==========================================================--%>
<%@include file="/WEB-INF/jsp/gps/cmm/admHeader.jsp" %>
<%@ page import="go.narastat.gps.adm.bbs.service.BbsManageVO"%>
<%-- Header Start ==========================================================--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.bbs/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.bbs/1999/xhtml" lang="ko">

<%-- javascript start ==============================================--%>

<link rel="stylesheet" type="text/css" href="/css/common/jquery/jquery.ui.all.css" />
<link rel="stylesheet" type="text/css" href="/css/common/jquery/jquery.ui.theme.css" />

<script type="text/javascript" src="/js/common/jquery/jquery.form.js"></script>
<%-- javascript end ============================================--%>
<script type="text/javaScript" language="javascript">
<!--

JQ_setValidation('searchVO');
JQ_onload();

<%-- 
/************************************************************************ 
함수명 : fncPageOnload                                   
설   명 : 페이지가 처음 로딩된 후 자동으로 동작해야 할 내용등을 정의해 놓은 함수(id와 function 매핑 등)       
인   자 : 없음        
사용법 : fncPageOnload()              
작성일 : 2011-06-17   
작성자 : 포탈 개발팀 이관형       

      date    author      note 
 ----------   -------     ------------------- 
 
************************************************************************/
--%>
function fncPageOnload()
{
	//frameset controller start
	//frameset controller end
}

<%--
/************************************************************************ 
함수명 : fn_search                                   
설   명 : 조회조건에 따라 목록을 조회하는 함수
인   자 : 없음(form안에 존재하는 값)
사용법 : fn_search()              
작성일 : 2011-06-17   
작성자 : 포탈 개발팀 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_modify(codeId){
	
}

-->
</script>

<!-- ui-layout-center start  -->
<div class="ui-layout-content">
	<!-- page contents start -->
	<div class="contents_area">
		<form:form commandName="searchVO" name="listForm" method="post">
		<input type="hidden" name="jssfcCode" />
        <div id="contents_area">
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
									<td class="title">정보수정</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table summary="정보수정" class="write01">
								<caption>정보수정</caption>
								<tr>
									<th class="subject">아이디</th>
									<td class="input">master</td>
								</tr>
								<tr>
									<th class="subject">성명</th>
									<td class="input">
										<input type = "text" class = "wi300" value = "관리자"/>
									</td>
								</tr>
								<tr>
									<th class="subject">게시판구분</th>
									<td class="input">관리자</td>
								</tr>
								<tr>
									<th class="subject">기존비밀번호</th>
									<td class="input">
										<input type = "password" class = "wi300"/>
									</td>
								</tr>
								<tr>
									<th class="subject">변경비밀번호</th>
									<td class="input">
										<input type = "password" class = "wi300"/>
									</td>
								</tr>
								<tr>
									<th class="subject">비밀번호확인</th>
									<td class="input">
										<input type = "password" class = "wi300"/>
									</td>
								</tr>
								<tr>
									<th class="subject">방문횟수</th>
									<td class="input">10</td>
								</tr>
								<tr>
									<th class="subject">마지막로그인</th>
									<td class="input">2011-05-24 17:56:53.0</td>
								</tr>
								<tr>
									<th class="subject">IP</th>
									<td class="input">127.0.0.1</td>
								</tr>
								<tr>
									<th class="subject">작업자</th>
									<td class="input">master</td>
								</tr>
								<tr>
									<th class="subject">작업일</th>
									<td class="input">2011-05-24 14:07:32.0</td>
								</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td align="center">
								<table class="rbuttonarea">
									<tr>
										<td><button>수정</button></td>
										<td><button>취소</button></td>
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
		</form:form>
	</div>
</div>
</html>