'use strict';

// 넘어온 값이 빈값인지 체크합니다.
// !value 하면 생기는 논리적 오류를 제거하기 위해
// 명시적으로 value == 사용
// [], {} 도 빈값으로 처리
// https://sanghaklee.tistory.com/3
var isEmpty = function(value) {
	if (   value == "" 
		|| value == null 
		|| value == undefined 
		|| ( value != null && typeof value == "object" && !Object.keys(value).length ) 
		){
		return true;
	}else{
		return false;
	}
};

// formData > Object 변환
// https://sshkim.tistory.com/122
var frmToObj = function($frm) {
	var frmArr = $frm.serializeArray();
	var obj = {};
	$.each(frmArr, function(i,elem) {
		obj[elem.name] = elem.value;
	});
	return obj;
}

// Object > JSONStr 변환
// https://cofs.tistory.com/400
var objToJSONStr = function(obj) {
	var json = JSON.stringify(obj);
	return json;
}
// formData > JSONStr 변환
var frmToJSONStr = function($frm) {
	var frmArr = $frm.serializeArray();
	var obj = {};
	$.each(frmArr, function(i,elem) {
		obj[elem.name] = elem.value;
	});
	return JSON.stringify(obj);
}

// Ajax common function
var cmmAjax = function(url,param) {
	var result = {};
	// json string 일 경우
	if ((typeof param == "string")) {
		result.ajax = 
			$.ajax({
				url:url,
				method:'POST',
				dataType: 'json',
				contentType:'application/json; charset=utf-8',
				data:param
			});
	// FormData 일 경우
	} else if (param instanceof FormData) {
		result.ajax = 
			$.ajax({
				url:url,
				type:'POST',
				dataType:'json',
				cache:false,
				contentType:false,
				processData:false,
				enctype: 'multipart/form-data',
				data:param
			});
	// Object일 경우
	} else if (param instanceof Object) {
		result.ajax = 
			$.ajax({
				url:url,
				method:'POST',
				data:param
			});
	}
	return result.ajax;
//	return result;
//	// 클로저 리턴
//	return {
//		XHR : 'javascript xhrAjax function',
//		Promise:'javascript promise'
//	};
}