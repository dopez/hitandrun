<%-- 
/** 
 * 설명   : 메인 화면에서 만족도 조사를 표시한다.
 * 파일명 : satisfactionReview.jsp
 * @author 범정부통계포털 이관형 
 * @since 2011.08.18 
 * @version 1.0 
 * @see 
 * 
 * <pre> 
 *  == Modification Information == 
 *   
 *    date       author                note 
 * ----------    -------    --------------------------- 
 * 2011.08.18     이관형           최초 생성 
 * </pre> 
 */
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>

<script type="text/javascript" src="/js/common/jqgrid/json2.js"></script>

<%-- javascript start ==============================================--%>
<script type="text/javaScript" language="javascript">
JQ_setValidation('csnstRspnsVO');


function fn_rspns() {
	
	var options = {         success     :fn_rstSuccess,
			                 error       :fn_error,
			                 url         :'/gps/csnst/insertSatisfactionRspns.do?'+'sysId='+'${reviewVO.sysId}' + "&csnstId=" + '${reviewVO.csnstId}' + "&csnstSn=" + '${reviewVO.csnstSn}' + "&qesitmSn=" + '${reviewVO.qesitmSn}',
			                 contentType :'application/x-www-form-urlencoded; charset=UTF-8',
			                 type        :'post',
			                 dataType    :'json'
			               };
    
	JQ_requestAjax('csnstRspnsVO', options);
	
}

<%-- 
/************************************************************************ 
fnc name : fn_success                                   
outline :  ajax 호출에 성공했을떄 호출뙤는 함수  
parameter : response json객체 
            status 성공여부 
directions : fn_success()              
since : 2011-08-18   
author : 범정부통계포털 이관형       

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_rstSuccess(response) 
{
	var message = response.message;
	alert(message);
}

<%-- 
/************************************************************************ 
fnc name : fn_error                                   
outline :  ajax 호출에 실패했을떄 호출뙤는 함수  
parameter : error json객체 
directions : fn_error()              
since : 2011-08-18   
author : 범정부통계포털 이관형       

    date      author             note  
 ----------   -------     ------------------- 

 ************************************************************************/
 --%>
function fn_error( error ) {
	alert('error');
	if( error.statusText == "error" ) {
	   alert( "<spring:message code="fail.common.msg" />" );
	}
}
</script>
<!-- contents_area start -->
<div class="subpage_satisfaction">
	<form:form commandName="csnstRspnsVO" name="csnstRspnsForm" method="post">
	<input type="submit" class="hidden">
		<p class="subject">${reviewVO.qesitmQestnNm}</p>
		<div class="satisfactionitem">
			<ul>
				<c:forEach var="resultInfo" items="${iemNmList}" varStatus="status">
				<li class="input">
				<c:if test="${reviewVO.qesitmTy eq 'radio'}">
					<form:radiobutton cssClass="validate[required] radio" path="iemSn" value="${resultInfo.iemSn}"/>
				</c:if>
				<c:if test="${reviewVO.qesitmTy eq 'checkbox'}">
					<form:checkbox cssClass="validate[minCheckbox[2]] checkbox" path="iemSn" value="${resultInfo.iemSn}"/>
				</c:if>
				</li>
				<li class="item"><c:out value="${resultInfo.iemNm}"></c:out></li>
			</c:forEach>
			<c:if test="${fn:length(iemNmList) eq 0}">
				<li class="item">확인 불가능한 만족도 조사입니다.</li>
			</c:if>
			</ul>
		</div>
		
		<div class="satisfactionimemo">
			<ul>
				<li class="input"><label for="exp1" class="hidden">좋은 제안이나 질책의 한마디를 부탁드립니다.(200자 내외)</label><input type="text" name="memoCn" class="wi623 validate[length[1,500]]" title="좋은 제안이나 질책의 한마디를 부탁드립니다.(200자 내외)" value="좋은 제안이나 질책의 한마디를 부탁드립니다.(200자 내외)" onclick="this.value=''" onkeypress="this.onclick();return false;"/></li>
			<c:if test="${fn:length(iemNmList) > 0}">
				<li class="btn"><a onclick="fn_rspns();"><img src="/images/common/bt_estimation_off.gif" onmouseover="overImg(this);" onFocus="overImg(this);" onmouseout="outImg(this);" onBlur="outImg(this);" alt="평가하기" title="평가하기"></a></li>
			</c:if>
			</ul>
		</div>
	</form:form>
</div>
<!-- container end -->