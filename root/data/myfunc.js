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
		}
	} else {
		setSpanText("callback_name", "用户名长度低于8位!", "RED");
	}
}

function checkPasswordLength() {
	var str = document.forms[0].password.value;
	if (str.length >= 6) {
		setSpanText("callback_pass", "OK", "GREEN");
	} else {
		setSpanText("callback_pass", "密码长度低于6位!", "RED");
	}
}
function checkRepeatPassword() {
	var pass = document.forms[0].password.value;
	var repeatpass = document.forms[0].repeatpass.value;
	if (repeatpass.length >= 6 && pass == repeatpass) {
		setSpanText("callback_repeatpass", "OK", "GREEN");
	} else if (repeatpass.length < 6) {
		setSpanText("callback_repeatpass", "密码长度不足!", "RED");
	} else {
		setSpanText("callback_repeatpass", "两次密码不一致!", "RED");
	}
}
function checkText() {
	var str = document.forms[0].checktext.value;
	if (str.length == 4)
		send_request("POST", "regist", "text=" + str,
			"text", checkImg);
	else setSpanText("callback_check", "FAIL", "RED");
}
function setSpanText(id, text, style) {
	if (style.length > 0) {
		document.getElementById(id).style.color = style;
	}
	document.getElementById(id).innerHTML = text;
}
function regist() {
	var name = document.forms[0].username.value;
	if (name.length < 8) {
		setSpanText("callback_name", "用户名长度低于8位!", "RED");
		return;
	}
	var pass = document.forms[0].password.value;
	var repeat = document.forms[0].repeatpass.value;
	if (pass.length < 6) {
		setSpanText("callback_pass", "密码长度不足!", "RED");
		return;
	}
	if (repeat != pass) {
		setSpanText("callback_repeatpass", "两次密码输入不同!", "RED");
		return;
	}
	var str = document.forms[0].checktext.value;
	send_request("POST", "regist", "username=" + name + "&password=" + pass,
			"text", showFeedbackInfo);
}
function checkImg() {
	if (http_request.readyState == 4) {
		if (http_request.status = 200) {
			var str = http_request.responseText;
			if (str.indexOf("SUCCESS") >= 0) {
				setSpanText("callback_check", "OK", "GREEN");
			} else {
				setSpanText("callback_check", "FAIL", "RED");
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
