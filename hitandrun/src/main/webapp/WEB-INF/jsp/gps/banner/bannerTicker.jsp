<%-- 
/** 
 * 설명   : 메인화면에서 베너표시를표시한다.
 * 파일명 : bannerTicker.jsp
 * @author 범정부통계포털 이관형 
 * @since 2011.10.18 
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

<script type="text/javascript" src="/js/gps/jquery.rolling.js"></script>

<%-- javascript start ==============================================--%>
<script type="text/javaScript" language="javascript">
function containsCharsOnly(input, chars) {
    for (var inx = 0; inx < input.length; inx++) {
       if (chars.indexOf(input.charAt(inx)) == -1) {
           return false;
       }
    }
    return true;
}

function isNumber(input) {
    var chars = "0123456789";
    if (!containsCharsOnly(input, chars)) {
		alert("Please enter number only");
		return false;
    }
    return true;
}

function isWithinRange(input, min, max) {
	if (!isNumber(input)) {
		return false;
	}
	
	var intValue = parseInt(input, 10);
	if (min <= intValue &&  intValue <= max) {
		return true;
	} else {
		alert("Please enter " + min + " ~ " + max + ".");
		return false;
	}	
}

function rollingStop(rollingId) {
	var button = $("input[type='button'][name='controler']");
	if (button.val() == "stop") {
		$("#" + rollingId).stopRolling();
		button.val("resume");
	} else if (button.val() == "resume") {
		$("#" + rollingId).resumeRolling();
		button.val("stop");
	}
}

function reverse(rollingId) {
alert($("#" + rollingId).getRollingDirection());
	$("#" + rollingId).reverseRolling();
}

function left(speed) {
	if($("#rolling").getRollingDirection() == 'right') {
		$("#rolling").reverseRolling();	
	}
}

function right(speed) {
	if($("#rolling").getRollingDirection() == 'left') {
		$("#rolling").reverseRolling();
	}
}

$(function() {
var rollingDiv = $("#rolling");
var bannerLayerSize = '${fn:length(bannerList)}' > 5? 5: '${fn:length(bannerList)}';
rollingDiv.rolling("left", 130, 100, bannerLayerSize);
	<c:forEach var="resultInfo" items="${bannerList}" varStatus="status">
		<c:choose>
		<c:when test="${not empty resultInfo.logoImageFileNmMask}"><c:set var="logoImageFileMask" value="${WebImagePath}?imageid=web/gps/banner/${resultInfo.logoImageFileMask}&ext=${resultInfo.logoImageFileMime}"/></c:when>
		<c:otherwise><c:set var="logoImageFileMask" value="/images/gps/cmm/icon/x.gif"/></c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${fn:containsIgnoreCase(resultInfo.url,'http://')}"><c:set var="url" value="${resultInfo.url}"/></c:when>
			<c:otherwise><c:set var="url" value="http://${resultInfo.url}"/></c:otherwise>
		</c:choose>

		rollingDiv.addRollingItem("<a href='${url}' target='${resultInfo.urlTrget}'><img src='${WebImagePath}?imageid=web/gps/banner/${resultInfo.logoImageFileMask}&ext=${resultInfo.logoImageFileMime}' alt='${resultInfo.nm}' title='${resultInfo.nm}' width='120' height='90' border='0'/></a>");
	</c:forEach>

var opacity = 0.9;

$('img', rollingDiv.getRollingItems()).css("opacity", opacity);

$('img', rollingDiv.getRollingItems()).hover(
	function() {
		$(this).animate({ 
			width: "130",
			height: "100",
			opacity: 1
		}, 300);
		$("#rollingHeadComment").html("<b><font color='blue'>" + $(this).attr('alt') + "</font></b>");
	},
	function() {
		$(this).animate({ 
			width: "120",
			height: "90",
			opacity: opacity
		}, 300);
		$("#rollingHeadComment").html("<b><font color='white'>...</font></b>");
	}
);
rollingDiv.startRolling(37, 0, 50);	

rollingDiv.bindViewingEvent(function(event, currentRollingItem) {
	$("#status").text("viewing");
});

rollingDiv.bindRollingEvent(function(event) {
	$("#status").text("rolling");
});

rollingDiv.bindStartEvent(function(event) {
	$("#status").text("start");
});

rollingDiv.bindStopEvent(function(event) {
	$("#status").text("stop");
});

		if (rollingDiv.getRollingDirection() == "right") {
			rollingDiv.reverseRolling();
		}
		rollingDiv.resumeRolling();
});	

</script>
<table>
	<tr>
		<td width="120" align="center">
			<img src='/images/button/0102.png' onmouseover="javascript:left()" title="&lt" alt="&lt"/>
		</td>
		<td>
			<div id="rolling">ready</div>
		</td>
		<td>
			<img src='/images/button/0101.png' onmouseover="javascript:right()" title="&gt" alt="&gt"/>
		</td>
	</tr>

</table>
<!-- container end -->