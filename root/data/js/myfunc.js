var _username = false;
var _userpass = false;
var _userrepeat = false;
var _usercheck = false;
function checkUsername() {
	var str = document.forms[0].username.value;
	if (str.length >= 8) {
		var Regx = /^[A-Za-z0-9]*$/;
		if (Regx.test(str)) {
			setSpanText("callback_name", "用户名合法", "GREEN");
			send_request("POST", "regist", "username=" + str,
					"text", checkUsernameCanUse);
//			send_request("GET","checkUsername.jsp?username="+str,null,"text",showFeedbackInfo);
		} else {
			setSpanText("callback_name", "用户名不合法", "RED");
			_username = false;
		}
	} else {
		setSpanText("callback_name", "用户名长度低于8位!", "RED");
		_username = false;
	}
}

function checkPasswordLength() {
	var str = document.forms[0].password.value;
	if (str.length >= 6) {
		setSpanText("callback_pass", "OK", "GREEN");
		_userpass = true;
	} else {
		setSpanText("callback_pass", "密码长度低于6位!", "RED");
		_userpass = false;
	}
}
function checkRepeatPassword() {
	var pass = document.forms[0].password.value;
	var repeatpass = document.forms[0].repeatpass.value;
	if (repeatpass.length >= 6 && pass == repeatpass) {
		setSpanText("callback_repeatpass", "OK", "GREEN");
		_userrepeat = true;
	} else if (repeatpass.length < 6) {
		setSpanText("callback_repeatpass", "密码长度不足!", "RED");
		_userrepeat = false;
	} else {
		setSpanText("callback_repeatpass", "两次密码不一致!", "RED");
		_userrepeat = false;
	}
}
function checkText() {
	var str = document.forms[0].checktext.value;
	if (str.length == 4)
		send_request("POST", "regist", "text=" + str,
			"text", checkImg);
	else {
		setSpanText("callback_check", "FAIL", "RED");
		_usercheck = false;
	}
}
function setSpanText(id, text, style) {
	if (style.length > 0) {
		document.getElementById(id).style.color = style;
	}
	document.getElementById(id).innerHTML = text;
}
function regist() {
	checkUsername();
	checkPasswordLength();
	checkRepeatPassword();
	checkText();
	var str = _username + _userpass + _userrepeat + _usercheck;
	alert(str);
	if (_username && _userpass && _userrepeat && _usercheck) {
		send_request("POST", "regist", "username=" + name + "&password=" + pass,
				"text", showFeedbackInfo);
	}
}
function checkImg() {
	if (http_request.readyState == 4) {
		if (http_request.status = 200) {
			var str = http_request.responseText;
			if (str.indexOf("SUCCESS") >= 0) {
				setSpanText("callback_check", "OK", "GREEN");
				_usercheck = true;
			} else {
				setSpanText("callback_check", "FAIL", "RED");
				_usercheck = false;
			}
		} else {
			alert("您所请求的页面有异常。#" + http_request.status);
		}
	}
}
function checkUsernameCanUse() {
	if (http_request.readyState == 4) { // 判断对象状态
		if (http_request.status == 200) { // 信息已经成功返回，开始处理信息
			var str = http_request.responseText;
			if (str.indexOf("SUCCESS") >= 0) {
				setSpanText("callback_name", "用户名可以使用", "GREEN");
				_username = true;
			} else {
				setSpanText("callback_name", "已存在该用户", "RED");
			}
		} else { //页面不正常
			alert("您所请求的页面有异常。#" + http_request.status);
		}
	}

}
function showFeedbackInfo() {
	if (http_request.readyState == 4) { // 判断对象状态
		if (http_request.status == 200) { // 信息已经成功返回，开始处理信息
			alert("注册信息：" + http_request.responseText);
		} else { //页面不正常
			alert("您所请求的页面有异常。#" + http_request.status);
		}
	}
}
