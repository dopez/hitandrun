
(function($) {
	$.fn.validationEngineLanguage = function() {};
	$.validationEngineLanguage = {
		newLang: function() 
		{
		
			$.validationEngineLanguage.allRules = 	{"required":{    			
						"regex":"none",
						"alertText":"* 필수입력란 입니다.",
						"alertTextCheckboxMultiple":"* 옵션을 선택하세요",
						"alertTextCheckboxe":"* 필수체크란 입니다"},
					"length":{
						"regex":"none",
						"alertText":"* ",
						"alertText2":"자 이상, 최대  ",
						"alertText3": " 자의 입력이 가능합니다."},
					"length2":{
						"regex":"none",
						"alertText":"* ",
						"alertText2":"자 이상 입력이 가능합니다."},
					"maxCheckbox":{
						"regex":"none",
						"alertText":"* Checks allowed Exceeded"},	
					"minCheckbox":{
						"regex":"none",
						"alertText":"* 선택하세요 ",
						"alertText2":" 옵션"},	
					"confirm":{
						"regex":"none",
						"alertText":"* 값이 일치하지 않습니다."},		
					"telephone":{
						"regex":"/^0[0-9]{1,2}\-\[0-9]{3,4}\-\[0-9]{3,4}$/",
						"alertText":"* 전화번호 형식이 아닙니다.( - 포함)"},	
					"url":{
							"regex":"/^[a-zA-Z0-9\&\=\?\/\.\:\-\_\ \']+$/",
							"alertText":"* URL형식이 아닙니다."},
					"email":{
						"regex":"/^[a-zA-Z0-9_\.\-]+\@([a-zA-Z0-9\-]+\.)+[a-zA-Z0-9]{2,4}$/",
						"alertText":"* 이메일 형식이 아닙니다"},	
					"date":{
                         "regex":"/^[0-9]{4}\-\[0-9]{1,2}\-\[0-9]{1,2}$/",
                         "alertText":"* 날짜형식이 아닙니다. 'YYYY-MM-DD'"},
                    "year":{
                             "regex":"/^[0-9]{4}$/",
                             "alertText":"* 날짜형식이 아닙니다. 'YYYY'"},
					"onlyNumber":{
						"regex":"/^[0-9\ ]+$/",
						"alertText":"* 숫자만 가능합니다."},	
					"noSpecialCaracters":{
						"regex":"/^[0-9a-zA-Z]+$/",
						"alertText":"* 특수문자는 허용되지 않습니다"},			
					"onlyLetter":{
						"regex":"/^[a-zA-Z\ \']+$/",
						"alertText":"* 영문자만 가능합니다"},
					"onlyLetterUnd":{
							"regex":"/^[_A-Z']+$/",
							"alertText":"* 영어 대문자와 '_'만 가능합니다"},
					"onlyEngNumber":{
						"regex":"/^[a-zA-Z0-9\ \']+$/",
						"alertText":"* 영어와 숫자만 가능합니다"},
					"UpperEngNumber":{
							"regex":"/^[_A-Z0-9]+$/",
							"alertText":"* 영어 대문자와 숫자와 '_'만  가능합니다"},	
					"LowerEngNumber":{
						"regex":"/^[_a-z0-9]+$/",
						"alertText":"* 영어 소문자와 숫자와 '_'만  가능합니다"},	
					"onlyKor":{
						"regex":"/^[ 가-힣ㄱ-ㅎ]+$/",
						"alertText":"* 한글만 입력가능합니다"},	
					"noKor":{
						"regex":"/^[ _a-zA-Z0-9]+$/",
						"alertText":"* 영어와 숫자와 '-'만 가능합니다."},	
					"onlyKorEng":{
							"regex":"/^[ 가-힣a-zA-Zㄱ-ㅎ]+$/",
							"alertText":"* 한글과 영문만 입력가능합니다"},	
					"onlyKorEngNumber":{
							"regex":"/^[ 가-힣a-zA-Z0-9ㄱ-ㅎ]+$/",
							"alertText":"* 한글,영문,숫자만 입력가능합니다"},
					"reqSubject":{
							"regex":"/^[ ^']+$/",
							"alertText":"* 제목을 입력하세요"},	
					"reqContents":{
							"regex":"/^[ ^']+$/",
							"alertText":"* 내용을 입력하세요"},	
					"validate2fields":{
    					"nname":"validate2fields",
    					"alertText":"* 필수입력란 입니다"}	
					}
		}
	}
})(jQuery);


$(document).ready(function() {	
	$.validationEngineLanguage.newLang()
});