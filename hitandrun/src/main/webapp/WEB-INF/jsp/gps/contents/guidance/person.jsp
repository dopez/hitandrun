<%--
********************************************************************************
* @source      : person.jsp
* @description : 개인정보보호정책 JSP
*                   
********************************************************************************
* DATE         AUTHOR    DESCRIPTION
*===============================================================================
* 2011-08-01   박통계           최초작성
********************************************************************************
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%-- 공통 taglib 선언 start	=================================================--%>
<%@include file="/WEB-INF/jsp/common/cmm/taglibs.jsp" %>
<%-- 공통 taglib 선언 end	=====================================================--%>

<%-- header start	==========================================================--%>
<jsp:include page="/gps/cmm/left.do" flush="false"/> 
<%-- header end	==============================================================--%>
<%-- 선행 로직 start	======================================================--%>
<script type="text/javascript">
JQ_onload();

function fncPageOnload() {
	topStatMenuImage(0);
	subTopStatMenuImage(0);
	leftStatMenuImage(0);
}
</script>
<%-- 선행 로직 end	==========================================================--%>

<%-- page javascript start	==================================================--%>
<%-- page javascript end	==================================================--%>
<!-- login_contents contetns start -->
<!-- subpagebody_contents start -->
<div id="subpagebody_contents">
	<!-- contents start -->
	<div class="personInformation">
		<div class="personCn">
			<div class="personContents01">
				<ul>
					<li>통계청이 취급하는 모든 개인정보는 관련법령에 근거하거나 정보주체의 동의에 의하여 수집ㆍ보유 및 처리되고 있습니다.<span class="sr_redText">「공공기관의 개인정보보호에 관한 법률」</span> 및 <span class="sr_redText">「정보통신망 이용촉진 및 개인정보보호에 관한 법률」</span> 은 이러한 개인정보의 취급에 대한 일반적 규범을 제시하고 있으며, 통계청은 이러한 법령의 규정에 따라 수집ㆍ보유 및 처리하는 개인정보를 공공업무의 적절한 수행과 국민의 권익을 보호하기 위해 적법하고 적정하게 취급할 것입니다.</li>
					<li>또한, 통계청은 관련 법령에서 규정한 바에 따라 우리청에서 보유하고 있는 개인정보에 대한 열람청구권 및 정정청구권 등 여러분의 권익을 존중하며, 이러한 법령상 권익의 침해 등에 대하여 행정심판법에서 정하는 바에 따라 행정심판을 청구할 수 있습니다.</li>
					<li>다음은 통계청의 개인정보보호방침을 설명 드리겠습니다. 우리청의 개인정보보호방침은 통계청이 운영하는 여러 홈페이지에서 이용자 여러분의 개인정보를 보호하기 위한 <span class="sr_redText">「홈페이지 이용자의 개인정보 보호」</span>와 소관업무를 수행하는데 필요한 개인정보 취급에 대한 「컴퓨터에 의해 처리되는 개인정보 보호」 두 가지로 구성되어 있습니다.</li>
				</ul>
			</div>
			
			<p class="personTitle">I. 홈페이지 이용자의 개인정보 보호</p>
			
			<div class="personContents02">
				<ul>
					<li>여기는 통계청 웹사이트입니다. 우리청 홈페이지의 이용에 대해 감사드리며, 홈페이지에서의 개인정보보호방침에 대하여 설명을 드리겠습니다. 이는 현행 「공공기관의 개인정보보호에 관한법률」 및 「공공기관의 개인정보보호를 위한 기본지침」에 근거를 두고 있습니다. 우리청에서 운영하고 있는 웹사이트는 다음과 같으며, 이 방침은 별도의 설명이 없는 한 우리청에서 운용하는 모든 웹사이트에 적용됨을 알려드립니다.</li>
				</ul>
			</div>
			
			<div class="personContents03">
				<ul>
					<li><a href="http://kostat.go.kr" target="_blank" title="새창" class="sv_type02lLink">kostat.go.kr(통계청 홈페이지)</a></li>
					<li><a href="/index/index.jsp" target="_blank" title="새창" class="sv_type02lLink">kosis.kr(국가통계포털)</a></li>
					<li><a href="http://sti.kostat.go.kr" target="_blank" title="새창" class="sv_type02lLink">sti.kostat.go.kr(통계교육원 홈페이지)</a> 등</li>
					<li><a href="http://www.narastat.kr" target="_blank" title="새창" class="sv_type02lLink">www.narastat.kr(나라통계포털)</a> 등</li>
				</ul>
			</div>
			
			<div class="confirm_subtitle">자동으로 수집·저장되는 개인정보</div>
			<div class="personContents04">
				<ul>
					<li>○ 여러분이 우리청 홈페이지를 이용할 경우 다음의 정보는 자동적으로 수집·저장됩니다.</li>
					<li class="innerCn">
						<ul>
							<li>- 이용자 여러분의 인터넷서버 도메인과 우리 홈페이지를 방문할 때 거친 웹사이트의 주소</li>
							<li>- 이용자의 브라우져 종류 및 OS</li>
							<li>- 방문일시 등</li>
						</ul>
					</li>
					<li>○ 위와 같이 자동 수집ㆍ저장되는 정보는 이용자 여러분에게 보다 나은 서비스를 제공하기 위해 홈페이지의 개선과 보완을 위한   통계분석, 이용자와 웹사이트 간의 원활한 의사소통 등을 위해 이용되어질 것입니다. 다만, 법령의 규정에 따라 이러한 정보를   제출하게 되어 있을 경우도 있다는 것을 유념하시기 바랍니다.</li>
				</ul>
			</div>
			
			<div class="confirm_subtitle">이메일 및 웹 서식 등을 통한 수집 정보</div>
			<div class="personContents04">
				<ul>
					<li>○ 이용자 여러분은 우편, 전화 또는 온라인 전자서식 등을 통한 전자적 방법을 통해 의사를 표시할 수 있습니다. 이러한 방법의 선택에 있어 몇 가지 유의사항을 알려드립니다.</li>
					<li class="innerCn">
						<ul>
							<li>- 여러분이 홈페이지에 기재한 사항은 다른 사람들이 조회 또는 열람할 수도 있습니다.</li>
							<li>- 여러분이 기재한 사항은 관련 법규에 근거하여 필요한 다른 사람과 공유될 수 있으며, 관련법령의 시행과 정책개발의 자료로도 사용될 수 있습니다.</li>
							<li>- 또한, 이러한 정보는 타 부처와 공유되거나, 필요에 의하여 제공될 수도 있습니다.</li>
						</ul>
					</li>
					<li>○ 홈페이지 보안을 위해 관리적ㆍ기술적 노력을 하고 있으나, 만약의 침해사고 시 문제가 될 수 있는 민감한 정보의 기재는 피하여 주시기 바랍니다.</li>
				</ul>
			</div>
			
			<div class="confirm_subtitle">웹사이트에서 운영하는 보안조치</div>
			<div class="personContents04">
				<ul>
					<li>○ 홈페이지의 보안 또는 지속적인 서비스를 위해, 우리청은 네트워크 트래픽의 통제(Monitor)는 물론 불법적으로 정보를 변경하는 등의 시도를 탐지하기 위해 여러 가지 프로그램을 운영하고 있습니다.</li>
					<li class="innerCn">
						<ul>
							<li>- 여러분이 홈페이지에 기재한 사항은 다른 사람들이 조회 또는 열람할 수도 있습니다. 이용자 여러분이 홈페이지에 기재한 내용 중 개인정보가 포함되어 있는 경우 개인정보를 삭제 조치 후 게시하여야 합니다.</li>
						</ul>
					</li>
					<li>※“개인정보”라 함은 생존하는 개인에 대한 정보로서 당해 정보에 포함되어 있는 성명, 주민등록번호 및 화상 등의 사항에 의하여 당해 개인을 식별할 수 있는 정보(당해 정보만으로는 특정개인을 식별 할 수 없더라도 다른 정보와 용이하게 결합하여 식별할 수 있는 것을 포함한다)를 말한다.</li>
					<li>&lt;공공기관의 개인정보보호에 관한 법률  제2조 제2항)&gt;</li>
				</ul>
			</div>
			
			<div class="confirm_subtitle">링크 사이트·웹 페이지</div>
			<div class="personContents04">
				<ul>
					<li>○ 통계청이 운영하는 여러 웹페이지에 포함된 링크 또는 배너를 클릭하여 다른 사이트 또는 웹페이지로 옮겨갈 경우 개인정보보호방침은 그 사이트 운영기관이 게시한 방침이 적용됨으로 새로 방문한 사이트의 방침을 확인하시기 바랍니다.</li>
				</ul>
			</div>
			
			<div class="confirm_subtitle">웹사이트 이용 중 다른 사람의 개인정보 취득</div>
			<div class="personContents04">
				<ul>
					<li>○ 통계청이 운영하는 웹사이트에서 이메일 주소 등 식별할 수 있는 개인정보를 취득하여서는 아니 됩니다. 부정한 방법으로 이러한 개인정보를 열람 또는 제공받은 자는 「공공기관의 개인정보보호에 관한 법률」 제23조의 규정에 의하여 처벌을 받을 수 있습니다.</li>
				</ul>
			</div>
			
			<div class="confirm_subtitle">개인정보 침해사항의 신고</div>
			<div class="personContents04">
				<ul>
					<li>○ 우리청의 웹사이트 이용 중 개인정보의 유출 가능성 등 정보주체의 권익이 침해될 우려가 있는 사실을 발견하였을 경우는 다음의 연락처로 알려주시기 바랍니다.</li>
					<li class="insideCn">
						<ul>
							<li>담당부서 : 정보화기획과 김유진</li>
							<li>전화번호 : 042-481-2390</li>
							<li>Fax : 042-481-2474 </li>
							<li>주소 : 우) 302-701 대전광역시 서구 선사로 139(둔산동 920번지) 정부대전청사</li>
						</ul>
					</li>
					<li>○ 또는 행정안전부에서 운영하는 다음의 공공기관 개인정보 침해신고 센터에 신고하실 수 있습니다.</li>
					<li class="insideCn">
						<dl>
							<dd>ㆍ한국인터넷진흥원 개인정보침해신고센터</dd>
							<dd>ㆍ http://www.1336.or.kr, Tel: 1336</dd>
						</dl>
					</li>
					<li>○ 개인정보 침해신고 처리 절차는 다음과 같습니다.</li>
					<li class="innerCn">
						<ul>
							<li>- 개인정보 침해신고 민원처리 기간은 상담·안내는 7일 이내, 침해사고의      사실 조사 및 처리는 30일 이내에 완료하는 것을 원칙으로 하고       있습니다. </li>
						</ul>
					</li>
				</ul>
			</div>
			
			<p class="personTitle">II. 컴퓨터에 의해 처리되는 개인정보</p>
			
			<div class="confirm_subtitle">개인정보의 수집 및 보유</div>
			<div class="personContents04">
				<ul>
					<li>○ 우리청은 보유하고 있는 국민 여러분의 개인정보를 관계법령에 따라 적법하고 적정하게 처리하여, 권익이 침해받지 않도록 노력 할 것입니다.</li>
					<li>○ 통계청은 법령의 규정과 정보주체의 동의에 의해서만 개인정보를 수집ㆍ보유합니다.</li>
					<li class="innerCn">
						<ul>
							<li>- 통계청이 정보주체의 동의에 의하여 보유하고 있는 개인정보파일은 다음과 같습니다.</li>
						</ul>
					</li>
				</ul>
			</div>
			
			<div class="personContents04">
				<table class="innerTable" summary="개인정보파일명, 목적, 주요항목, 보유기간">
				<caption>&lt;통계청이 정보주체의 동의에 의하여 보유하고 있는 개인정보파일&gt;</caption>
				<thead>
				<tr>
					<th scope="col">개인정보파일명</th>
					<th scope="col">근거</th>
					<th scope="col">목적</th>
					<th scope="col">주요항목</th>
					<th scope="col">보유기간</th>
					<th scope="col" class="end">비고</th>
				</tr>
				</thead>
				<tbody>
				<tr>
					<td class="pointerKey">홈페이지 회원</td>
					<td>이용약관</td>
					<td>홈페이지서비스 <br/>운영 및 관리 </td>
					<td>이름, 생년월일, 주소,<br/> 이메일,  전화번호, 직업 등</td>
					<td>회원탈퇴 시 까지 </td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="pointerKey">MDSS(마이크로 <br/> 데이터서비스 <br/>시스템) 회원</td>
					<td>이용약관</td>
					<td>MDSS 이용자 관리 </td>
					<td>이름, 이메일, 전화번호 등 </td>
					<td>회원탈퇴 시 까지 </td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="pointerKey">통계교육원<br/> 홈페이지 회원</td>
					<td>이용약관</td>
					<td>교육운영 </td>
					<td>이름, 주민등록번호,<br/>이메일, 주소 등 </td>
					<td>회원탈퇴 시 까지 </td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="pointerKey">통계정책관리<br/>시스템 회원</td>
					<td>이용약관</td>
					<td>회원관리</td>
					<td>이름, 전화번호, 주소,<br/>이메일 등</td>
					<td>회원탈퇴 시 까지 </td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="pointerKey">e-나라지표<br/> 홈페이지 회원</td>
					<td>회원약관</td>
					<td>회원관리</td>
					<td>이름, 전화번호, 주소,<br/>이메일 등</td>
					<td>회원탈퇴시 까지</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="pointerKey">통계지리정보<br/>서비스 회원</td>
					<td>이용약관</td>
					<td>회원관리</td>
					<td>성명, 전화번호,주소,<br/>이메일 등</td>
					<td>회원탈퇴시 까지</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="pointerKey">국가통계포털<br/>회원</td>
					<td>이용약관</td>
					<td>회원관리</td>
					<td>이름, 전화번호, 주소,<br/>이메일 등</td>
					<td>회원탈퇴시 까지</td>
					<td>&nbsp;</td>
				</tr>
				</tbody>
				</table>
			</div>
			
			<div class="confirm_subtitle">수집한 개인정보 위탁</div>
			<div class="personContents04">
				<ul>
					<li>○ 통계청은 고객님의 동의없이 개인정보 취급을 외부 업체에 위탁하지 않습니다. 향후 그러한 필요가 생길 경우, 위탁 대상자와 위탁 업무 내용에 대해 고객님에게 통지하고 필요한 경우 사전 동의를 받도록 하겠습니다. </li>
					<li>○ 또한, 우리 청에서 관리하는 개인정보의 처리를 다른 공공기관 또는 다른 전문기관에 위탁하는 경우, 그 업무에 종사하거나 종사하였던 자에 대해 직무상 알게 된 개인정보를 누설 또는 권한없이 처리하거나 타인의 이용에 제공하는 등 부당한 목적을 위하여 사용하지 않도록 위탁부서에서 아래에 대한 사항에 관하여 필요한 제한이나 절차를 정하고 수탁기관으로 하여금 준수토록하고 있으며, 실태점검도 실시하도록 하겠습니다.</li>
					<li class="innerCn">
						<ul>
							<li>- 재위탁 금지에 관한 사항</li>
							<li>- 개인정보파일의 복사에 관한 사항</li>
							<li>- 개인정보의 관리상황 검사 및 소속직원의 교육에 관한 사항</li>
							<li>- 수탁기관에서 준수하여야 할 의무를 위반한 경우의 손해배상 등에 관한 사항</li>
						</ul>
					</li>
				</ul>
			</div>
			
			<div class="confirm_subtitle">개인정보의 파기절차 및 방법</div>
			<div class="personContents04">
				<ul>
					<li>○ 회원 탈퇴 등의 사유로 개인정보파일의 보유 기간이 지난 경우 해당 회원의 개인정보는 즉시 파기되며 통계청의 시스템에서 영구적으로 삭제됩니다.</li>
					<li>&lt;파기방법&gt;</li>
					<li class="innerCn">
						<ul>
							<li>- 종이에 출력된 개인정보는 파쇄기로 파쇄하거나 소각을 통하여 파기</li>
							<li>- 전자적 파일형태로 저장된 개인정보는 기록을 재생할 수 없는 기술적 방법을 사용하여 삭제합니다.</li>
						</ul>
					</li>
					<li>&lt;개인정보 파일 파기현황&gt;</li>
				</ul>
				<table class="innerTable" summary="개인정보파일명, 목적, 주요항목, 보유기간">
				<caption>&lt;개인정보 파일 파기현황&gt;</caption>
				<thead>
				<tr>
					<th scope="col">파일명</th>
					<th scope="col">파기사유 발생일자</th>
					<th scope="col">대상건수</th>
					<th scope="col">보유기간</th>
					<th scope="col" class="end">파기일자</th>
				</tr>
				</thead>
				<tbody>
				<tr>
					<td>국가통계포털 홈페이지 회원 명부</td>
					<td>2010.08.27</td>
					<td>130</td>
					<td>서비스 이용기간</td>
					<td>2010.08.27</td>
				</tr>
				</tbody>
				</table>
			</div>
			
			<div class="confirm_subtitle">개인정보의 이용 및 제공의 제한</div>
			<div class="personContents04">
				<ul>
					<li>○ 통계청이 수집ㆍ보유하고 있는 개인정보는 일반 행정정보와 달리 이용 및 제공에 엄격한 제한이 있는 정보입니다.<br />「공공기관의 개인정보보호에 관한 법률」 제10조(이용 및 제공의 제한)는 이에 관하여 다음과 같이 규정하고 있습니다. </li>
					<li class="innerCn">
						<ul>
							<li>- 다른 법률에 의해 보유기관 내부에서 이용하거나 보유기관이외의 자에게 제공하는 경우</li>
							<li>- 그 밖에 아래의 경우</li>
							<li class="insideCn">
								<dl>
									<dd>ㆍ정보주체의 동의가 있거나 또는 정보주체에게 제공하는 경우</dd>
									<dd>ㆍ다른 법률에서 정하는 소관업무를 수행하기 위해 당해 처리정보를 이용할 상당한 이유가 있는 경우</dd>
									<dd>ㆍ조약 기타 국제협정의 이행을 위해 외국정부 또는 국제기구에 제공하는 경우</dd>
									<dd>ㆍ통계작성 및 학술연구 등의 목적을 위해 특정개인을 식별할 수 없는 형태로 제공하는 경우</dd>
									<dd>ㆍ정보주체 또는 그 법정대리인이 의사표시를 할 수 없는 상태로 놓여 있거나 주소불명 등으로 동의를 할 수 없는 경우로써 정보주체외의 자에게 제공하는 것이 명백히 정보주체에게 이익이 된다고 인정되는 경우</dd>
									<dd>ㆍ범죄의 수사와 공소의 제기 및 유지에 필요한 경우</dd>
									<dd>ㆍ법원의 재판업무수행을 위하여 필요한 경우</dd>
									<dd>ㆍ기타 대통령령이 정하는 특별한 사유가 있는 경우</dd>
								</dl>
							</li>
						</ul>
					</li>
					<li>○ 통계청이 위 법령 및 기타 개별법에 근거하여 통상적으로 다른 기관에 제공하는 개인정보 현황은 없습니다.</li>
					<li>○ 우리청은 개인정보의 이용 및 제공에 있어 관계법령을 엄수하여 부당하게 이용되지 않도록 노력하겠습니다.</li>
				</ul>
			</div>
			
			<div class="confirm_subtitle">개인정보파일의 열람 및 정정 청구</div>
			<div class="personContents04">
				<ul>
					<li>○ 다음의 개인정보파일은 「공공기관의 개인정보보호에 관한 법률」 관련법령의 규정이 정하는 바에 따라 열람을 청구할 수 있습니다.</li>
				</ul>
				<table class="innerTable" summary="보유부서, 개인정보파일명, 열람장소, 연락처 ">
				<caption>&lt;개인정보파일의 열람&gt;</caption>
				<thead>
				<tr>
					<th scope="col">보유부서</th>
					<th scope="col">개인정보파일명</th>
					<th scope="col">열람장소</th>
					<th scope="col">연락처</th>
					<th scope="col" class="end">비고</th>
				</tr>
				</thead>
				<tbody>
				<tr>
					<td class="pointerKey">정보서비스팀</td>
					<td>홈페이지 회원</td>
					<td>정부대전청사3동1005호</td>
					<td>042-481-2318</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="pointerKey">정보서비스팀</td>
					<td>MDSS 회원</td>
					<td>정부대전청사3동1004호</td>
					<td>042-481-2334</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="pointerKey">통계교육원</td>
					<td>홈페이지 회원</td>
					<td>통계교육원 교육운영과</td>
					<td>042-366-6246</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="pointerKey">통계협력과</td>
					<td>통계정책관리시스템 회원</td>
					<td>정부대전청사3동 1406호</td>
					<td>042-481-2068</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="pointerKey">통계정책과</td>
					<td>e-나라지표 회원</td>
					<td>정부대전청사3동 1407호</td>
					<td>042-481-2419</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="pointerKey">공간정보서비스팀</td>
					<td>통계지리정보서비스 회원</td>
					<td>정부대전청사3동 1006호</td>
					<td>042-481-2498</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="pointerKey">통계포털운영과</td>
					<td>국가통계포털 회원</td>
					<td>정부대전청사3동 1002호</td>
					<td>042-481-2414</td>
					<td>&nbsp;</td>
				</tr>
				</tbody>
				</table>
			</div>
			
			<div class="personContents04">
				<ul>
					<li>○ 열람청구 절차(「공공기관의 개인정보보호에 관한 법률」의 경우)</li>
					<li class="img"><img src="/images/gps/contents/guidance/policy_im_01.gif" alt="열람청구 절차 이미지" /></li>
					<li>○ 다음사항은 법 제13조 규정에 의하여 열람을 제한할 수 있습니다.</li>
					<li>○ 본인의 개인정보를 열람한 정보주체는 다음의 경우 정정을 청구할 수 있습니다.</li>
					<li>○ 정정 청구의 범위</li>
					<li class="innerCn">
						<ul>
							<li>- 사실과 다르게 기록된 정보의 정정</li>
							<li>- 특정항목에 해당사실이 없는 내용에 대한 삭제</li>
						</ul>
					</li>
					<li>○ 정정 청구의 절차(｢공공기관의 개인정보보호에 관한 법률｣의 경우)</li>
					<li class="innerCn">
						<ul>
							<li>- 조세의 부과 · 징수 또는 환급에 관한 사항</li>
							<li>- 교육법에 의한 각종 학교에서의 성적의 평가 또는 입학자의 선발에 관한 업무</li>
							<li>- 학력 · 기능 및 채용에 관한 시험, 자격의 검사, 보상금 · 급부금의 산정 등 평가 또는 판단에 관한 업무</li>
							<li>- 다른 법률에 의한 감사 및 조사에 관한 업무</li>
							<li>- 토지 및 주택 등에 관한 부동산 투기를 방지하기 위한 업무</li>
							<li>- 증권거래법에 의한 불공정 증권거래를 방지하기 위한 업무</li>
						</ul>
					</li>
					<li class="img"><img src="/images/gps/contents/guidance/policy_im_02.gif" alt="열람청구 절차 이미지" /></li>
				</ul>
			</div>	
			
			<div class="confirm_subtitle">권익침해 구제방법(「공공기관의 개인정보보호에 관한 법률」의 경우)</div>
			<div class="personContents04">
				<ul>
					<li>「공공기관의 개인정보보호에 관한 법률」 제12조(처리정보의 열람) 제1항 및 제14조제1항(처리정보의 정정)의 규정에 의한 청구에 대하여 공공기관의 장이 행한 처분 또는 부작위로 인하여 권리 또는 이익이 침해를 받은 자는 행정심판법이 정하는 바에 따라 행정심판을 청구할 수 있습니다.</li>
					<li>○ 행정심판에 대한 자세한 사항은 법제처(<a href="http://www.moleg.go.kr" target="_blank" title="법제처(새창)">http://www.moleg.go.kr</a>)홈페이지를 참고하시기 바랍니다</li>
					<li>○ 행정심판위원회 전화번호 안내 중앙행정심판위원회 1588-1517</li>
				</ul>
			</div>
			
			<div class="confirm_subtitle">개인정보보호책임관의 지정관리 및 연락처</div>
			<div class="personContents04">
				<ul>
					<li>통계청은 개인정보의 적법성 및 절차의 적정성을 확보하여 국민의 권익보호 및 공공업무의 적정한 수행을 도모하기 위해 개인정보관리책임관을 다음과 같이 지정ㆍ운영하고 있습니다. 우리청이 보유하고 있는 개인정보파일과 우리청의 개인정보보호방침등에 관한 문의ㆍ확인 등은 다음의 연락처로 하여 주시기 바랍니다. </li>
					<li>○ 통계청 개인정보관리책임관 : 통계정보국장 </li>
					<li class="insideCn">
						<ul>
							<li>전화번호 : 042-481-2310 </li>
							<li>Fax : 042-481-2474</li>
							<li>주소 : 우) 302-701  대전광역시 서구 선사로 139(둔산동 920번지) 정부대전청사</li>
						</ul>
					</li>
					<li>○ 개인정보파일에 대한 문의 </li>
				
					<table class="innerTable" summary="보유부서, 개인정보파일명, 열람장소, 연락처 ">
					<caption>&lt;개인정보파일에 대한 문의&gt;</caption>
					<thead>
					<tr>
						<th scope="col">보유부서</th>
						<th scope="col">개인정보파일명</th>
						<th scope="col" class="end">연락처 (전화번호 / FAX)</th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<td class="pointerKey">정보서비스팀</td>
						<td>홈페이지 회원</td>
						<td class="right">042-481-2318/042-481-2478 </td>
					</tr>
					<tr>
						<td class="pointerKey">정보서비스팀</td>
						<td>MDSS 회원</td>
						<td class="right">042-481-2334/042-481-2478</td>
					</tr>
					<tr>
					
						<td class="pointerKey">통계교육원</td>
						<td>통계교육원 홈페이지 회원</td>
						<td class="right">042-366-6246/042-366-6499</td>
					</tr>
					<tr>
						<td class="pointerKey">통계협력과</td>
						<td>통계정책관리시스템 회원</td>
						<td class="right">042-481-2068/042-481-2463</td>
					</tr>
					<tr>
						<td class="pointerKey">통계정책과</td>
						<td>e-나라지표 회원</td>
						<td class="right">042-481-2419/042-481-2462</td>
					</tr>
					<tr>
						<td class="pointerKey">공간정보서비스팀</td>
						<td>통계정보지리정보서비스 회원</td>
						<td class="right">042-481-2498/042-481-2371</td>
					</tr>
					<tr>
						<td class="pointerKey">통계포털운영과</td>
						<td>국가통계포털 회원</td>
						<td>042-481-2414/042-481-2474</td>
					</tr>
					</tbody>
					</table>	
					
					<li>○ 법령의 규정 등에 의하여 수집한 개인정보가 수집 및 처리목적에 맞게 이용될 수 있도록 항시 지도ㆍ감독하겠습니다. </li>
				</ul>
			</div>
			
			<div class="confirm_subtitle">개인정보 수집·이용·제공에 대한 동의 철회</div>
			<div class="personContents04">
				<ul>
					<li>○ 회원가입을 통해 개인정보의 수집·이용·제공에 대한 귀하께서 동의하신 내용을 귀하는 언제든지 철회할 수 있습니다. 동의철회는 홈페이지 관리자에게 서면, 전화, E-mail등으로 연락하시면 즉시 개인정보의 삭제 등 필요한 조치를 하겠습니다.</li>
				</ul>
			</div>
			
			<div class="confirm_subtitle">개인정보 노출 확인</div>
			<div class="personContents04">
				<ul>
					<li>○ 게시판에 자료게재 및 입력시 개인정보가 포함될 경우 개인정보 노출 위험이 있으므로 개인정보 관련하여 주의하여 주시기 바랍니다.</li>
				</ul>
			</div>
			
			<div class="confirm_subtitle">통계청 사이트 내에서의 이메일 무단 수집 거부</div>
			<div class="personContents04">
				<ul>
					<li>○ 본 사이트에 게시된 주소가 전자우편 수집프로그램이나 그 밖의 기술적 장치를 이용하여 무단으로 수집되는 것을 거부하며, 이를 위반시 정보통신망법에 의해 형사처벌됨을 유의하시기 바랍니다.</li>
				</ul>
			</div>
			
		</div>
	</div>
	<!-- contents end -->
</div>
<jsp:include page="/gps/cmm/footer.do" flush="false"/>