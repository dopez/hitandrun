<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!--
****************************************************************************************************
* @source      : left.jsp
* @description : 좌측메뉴  JSP 
*                   
****************************************************************************************************
* DATE         AUTHOR    DESCRIPTION
*===================================================================================================
* 2011-04-18   이진우           최초작성
****************************************************************************************************
-->

<%-- Header Start =============================================================--%>

<jsp:include page="/sps/cmm/header.do" flush="false"/>
<%-- Header Start =============================================================--%>

<!-- import 선언 Start ==========================================================================--> 

<!-- import 선언 End==========================================================================--> 

<%-- 공통 CSS Start	========================================================--%>
<link rel="stylesheet" type="text/css" href="/css/common/jquery/jquery.treeview.css"></link>
<%-- 공통 CSS End	========================================================--%>

<%-- 공통 자바스크립트  Start =================================================--%>
<script src="/js/common/jquery/jquery.treeview.js" type="text/javascript"></script>
<%-- 공통 자바스크립트  End 	===============================================--%>
<!--  자바 스크립트  Start ==================================================================================--> 

<script>
<!--
$(document).ready(function(){
	$("#browser").treeview();
});
//-->
</script>
<!-- 자바 스크립트  End ==================================================================================-->


<!-- 좌측 메뉴 영역  End ==================================================================================-->
<div class="ui-layout-west">  
	 <ul id="browser" class="gpstree">
	 	<li><span class="folder">개발 샘플</span>
			<ul>
				<!--<li><span class="file"><a href="/sample/egovSampleList2.do" target="body">카테고리 관리</a></span></li>-->
				<li><span class="folder">템플릿</span>
				
				<ul>
					 <li><span class="file"><a href="/sample/tem/validator_help.do" target="frmbody">validate 템플릿</a></span></li>
					<!-- 					
					 <li><span class="file"><a href="/template/sample/fileUpload.jsp" target="frmbody">fileUpload 템플릿</a></span></li>
					-->
					 <li><span class="file"><a href="/sample/tem/selectTemplateList.do" target="frmbody">게시판 템플릿</a></span></li>
					 <li><span class="file"><a href="/sample/tem/registerTemplateFile.do" target="frmbody">파일 업로드 템플릿1</a></span></li>
 					<li><span class="file"><a href="/sample/tem/selectTemplateJqGridList.do" target="frmbody">파일첨부 유형3(jqGrid)</a></span></li>					
					<li><span class="file"><a href="/sample/tem/registerTemplateTable.do" target="frmbody">파일첨부 유형2</a></span></li> 
					 
					 <li><span class="file"><a href="/sample/tem/selectTemplateList2.do" target="frmbody">동적 콤보박스 설정</a></span></li>					 
					 <li><span class="file"><a href="/sample/tem/dragndropSample.do" target="frmbody">Drag&Drop </a></span></li>
					
				</ul>
					<li><span class="folder">에러처리</span>
				
				<ul>
					 <li><span class="file"><a href="/template/sample/help_error.jsp" target="frmbody">사용자 에러 발생</a></span></li>
				</ul>
				</li>
				
					<li><span class="folder">공통코드관리(미사용)</span>
				<ul>
				    
					<li><span class="file"><a href="/sample/ccm/ccc/EgovCcmCmmnClCodeList.do" target="frmbody">공통분류코드</a></span></li>
					<li><span class="file"><a href="/sample/ccm/cca/EgovCcmCmmnCodeList.do" target="frmbody">공통코드</a></span></li>
					<li><span class="file"><a href="/sample/ccm/cde/EgovCcmCmmnDetailCodeList.do" target="frmbody">공통상세코드</a></span></li>
				</ul>
				</li>
			
			</ul>
		</li>
		<!-- 
		<li><span class="folder">레이아웃 템플릿</span>
			<ul>
				<li><span class="folder">메뉴 2.1</span>
					<ul id="folder21">
					    
						<li><span class="file"><a href="/template/html/login.html" target="_blank">로그인</a></span></li>
						<li><span class="file"><a href="/template/html/template00.html" target="_blank">템플릿 1</a></span></li>
						<li><span class="file"><a href="/template/html/template01.html" target="_blank">템플릿 2</a></span></li>
						<li><span class="file"><a href="/template/html/template02.html" target="_blank">템플릿 3</a></span></li>
					</ul>
				</li>
			</ul>
		</li>
		 -->
		<li><span class="folder">Ajax 템플릿</span>
			<ul>
				<li><span class="folder">JqGrid </span>
					<ul id="folder21">
						<li><span class="file"><a href="/goSampleJqgrid.do" target="frmbody">jqGrid 공통상세코드</a></span></li>						
					</ul>
				</li>
			</ul>
		</li>
		
	</ul>
	
	
	
	
	
	
	
	
	
</div>


<!-- 좌측 메뉴 영역 End ==================================================================================-->


<%-- Footer Start ===========================================================--%>
<jsp:include page="/sps/cmm/bottom.do" flush="false"/>
<%-- Footer End =============================================================--%>
