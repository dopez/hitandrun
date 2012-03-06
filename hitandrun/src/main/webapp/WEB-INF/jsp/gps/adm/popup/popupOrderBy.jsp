<%-- 
/** 
 * outline   : 팝업관리 - 순서변경 화면
 * filename : popupOrderBy.jsp
 * @author 범정부통계포털 이관형 
 * @since 2011.06.17
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.06.17     이관형           최초 생성 
 * </pre> 
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko">
<%@include file="/WEB-INF/jsp/gps/cmm/admHeader.jsp" %>
<script type="text/javaScript" language="javascript">

<%-- 
/************************************************************************ 
함수명 : openWin                                   
설   명 : 팝업 화면 호출
인   자 : 없음
사용법 : openWin()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function openWin(url, name, width, height, scrollbar){
    scrollbar_str = scrollbar ? "yes" : "no";
    window.open(url, name, "top=100,left=100,toolbar=no,derectories=no,status=no,menubar=no,width="+width+",height="+height+",resizable=no");
}

<%-- 
/************************************************************************ 
함수명 : gps_popupOrderBy                                   
설   명 : 수정액션 호출
인   자 : 없음
사용법 : gps_popupOrderBy()              
작성일 : 2011-06-17   
작성자 : 범정부통계포털 이관형       

      date    author      note 
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function gps_popupOrderBy() {
	window.close();
   	document.frmDbInfo.action = "<c:url value='/gps/adm/fman/updatePopupOrderBy.do'/>";
   	document.frmDbInfo.submit();		
}
</script>
<!-- contents start  -->
<div class="contents_area">
	<table class="default">
		<tr>
			<td>
				<table class="title">
				<tr>
					<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="팝업 순서변경" title="팝업 순서변경"></td>
					<td class="title">팝업 순서변경</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table class="write01">			
				<tr>
					<th class="subject">위치</th>
					<td>
						<table class="inner01">
						<tr>
							<td class="bottom_noline">
								<select name="">
									<option value="default" selected="selected">팝업위치선택</option>
								</select>
							</td>
						</tr>
						</table>
					</td>
				</tr>								
				<tr>
					<th class="subject">팝업명</th>
					<td>
						<table class="inner01">
						<tr>
							<td class="bottom_noline">
								<select name="">
									<option value="default" selected="selected">등록된 팝업가 없습니다.</option>
								</select>
							</td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="center">
			<form:form name="frmDbInfo"method="post">
				<table class="cbuttonarea">
					<tr>
						<td align="left">▲▼</td>
						<td align="right"><button onclick="javascript:gps_popupOrderBy();">저장</button></td>
						<td align="right"><button onclick = "javascript:window.close();">취소</button></td>
					</tr>
				</table>
			</form:form>
			</td>
		</tr>		
	</table>
</div>
<!-- container end -->
<jsp:include page="/gps/cmm/admBottom.do"></jsp:include>