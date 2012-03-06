<%-- 
/** 
 * outline   : 사용자관리 비밀번호 변경 팝업
 * filename : chgPwPopupOk.jsp
 * @author 범정부통계포털 이진우 
 * @since 2011.07.26
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.07.26     이진우           최초 생성 
 * </pre> 
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<%-- Header Start ==========================================================--%>
<jsp:include page="/gps/cmm/admHeader.do"></jsp:include>
<%-- Header End ==========================================================--%>
<div class="contents_area">
	<table class="default">
	<tr>
		<td>
			<table class="title">
			<tr>
				<td class="icon"><img src="/images/gps/adm/icon/026.gif" alt="타이틀이미지" title="타이틀이미지" /></td>
				<td class="title">비밀번호 변경</td>
			</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table class="datamessage">
			<tr>
				<td>비밀번호가 성공적으로 변경되었습니다.</td>
			</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table class="rbuttonarea">
			<tr>
				<td class="end"><img src="/images/button/0205.png" alt="닫기" title="닫기" onclick="window.close();"></td>
			</tr>
			</table>
		</td>
	</tr>
	</table>
</div>