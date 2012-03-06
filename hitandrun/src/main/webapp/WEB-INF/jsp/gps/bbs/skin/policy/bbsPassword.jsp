<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<jsp:include page="/gps/cmm/left.do" flush="false"></jsp:include>
<%-- 
/** 
 * outline   : 게시글목록
 * filename : bbsList.jsp
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
	leftStatMenuImage(0);
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
함수명 : fn_list                                   
설   명 : 조회조건에 따라 목록을 조회하는 함수
인   자 : 없음(form안에 존재하는 값)
사용법 : fn_list()              
작성일 : 2011-09-15
작성자 : 범정부통계포털 황기연       

      date    author      note 
 ----------   -------     ------------------- 
2011-09-15 	  황기연
 ************************************************************************/
 --%>
function fn_list(){
   	JQ_request("bbsSearchVO", "<c:url value='/gps/bbs/selectBbsList.do'/>", "bbsSearchVO");
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
function fn_bbsPage(){
	JQ_setProcessMsg();
	JQ_request("bbsSearchVO", "<c:url value='${bbsSearchVO.returnUrl}'/>","bbsSearchVO");
}

</script>
<!-- content start -->
<div id="subpage_contents">
	<!-- subpagebody_contents start -->
	<div id="subpagebody_contents">
		
		<!-- bbs password start -->
		<div class="bbs_login">
			<div class="explanation">
				<h3 class="sub_title"></h3>
			</div>

			<div class="inside">
				<h3></h3>
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
					<div class="login_information">
					<fieldset>
						<legend>비밀번호 확인</legend>
						<ul>
							<li class="pass"><label for="pass"><img src="/images/gps/login/password_text.gif" alt="PASSWORD" title="PASSWORD"></label></li>
							<li class="input"><form:password path="authorPassword" maxlength="20" cssClass="wi200"/></li>
							<li class="btn"><a href="#LINK" onclick="fn_bbsPage();return false;" onkeypress="this.onclick();return false;"><img src="/images/gps/board/0206.gif" alt="확인"></a></li>
							<li class="btn"><a href="#LINK" onclick="fn_list();return false;" onkeypress="this.onclick();return false;"><img src="/images/gps/board/0201.gif" alt="목록" title="목록"></a></li>
						</ul>
					</fieldset>
				</div>
				</form:form>
			</div>
		</div>
		<!-- bbs password end -->

	</div>
	<!-- subpagebody_contents end -->
</div>
<!-- content end -->
<jsp:include page="/gps/cmm/footer.do" flush="false"/>