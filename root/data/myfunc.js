function checkUsername() {
	var str = document.forms[0].username.value;
	if (str.length >= 8) {
		var Regx = /^[A-Za-z0-9]*$/;
		if (Regx.test(str)) {
			setSpanText("callback_name", "�û����Ϸ�", "GREEN");
			send_request("POST", "regist", "username=" + str,
					"text", checkUsernameCanUse);
//			send_request("GET","checkUsername.jsp?username="+str,null,"text",showFeedbackInfo);
		} else {
			setSpanText("callback_name", "�û������Ϸ�", "RED");
		}
	} else {
		setSpanText("callback_name", "�û������ȵ���8λ!", "RED");
	}
}

function checkPasswordLength() {
	var str = document.forms[0].password.value;
	if (str.length >= 6) {
		setSpanText("callback_pass", "OK", "GREEN");
	} else {
		setSpanText("callback_pass", "���볤�ȵ���6λ!", "RED");
	}
}
function checkRepeatPassword() {
	var pass = document.forms[0].password.value;
	var repeatpass = document.forms[0].repeatpass.value;
	if (repeatpass.length >= 6 && pass == repeatpass) {
		setSpanText("callback_repeatpass", "OK", "GREEN");
	} else if (repeatpass.length < 6) {
		setSpanText("callback_repeatpass", "���볤�Ȳ���!", "RED");
	} else {
		setSpanText("callback_repeatpass", "�������벻һ��!", "RED");
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
		setSpanText("callback_name", "�û������ȵ���8λ!", "RED");
		return;
	}
	var pass = document.forms[0].password.value;
	var repeat = document.forms[0].repeatpass.value;
	if (pass.length < 6) {
		setSpanText("callback_pass", "���볤�Ȳ���!", "RED");
		return;
	}
	if (repeat != pass) {
		setSpanText("callback_repeatpass", "�����������벻ͬ!", "RED");
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
			alert("���������ҳ�����쳣��#" + http_request.status);
		}
	}
}
function checkUsernameCanUse() {
	if (http_request.readyState == 4) { // �ж϶���״̬
		if (http_request.status == 200) { // ��Ϣ�Ѿ��ɹ����أ���ʼ������Ϣ
			var str = http_request.responseText;
			if (str.indexOf("SUCCESS") >= 0) {
				setSpanText("callback_name", "�û�������ʹ��", "GREEN");
			} else {
				setSpanText("callback_name", "�Ѵ��ڸ��û�", "RED");
			}
		} else { //ҳ�治����
			alert("���������ҳ�����쳣��#" + http_request.status);
		}
	}

}
function showFeedbackInfo() {
	if (http_request.readyState == 4) { // �ж϶���״̬
		if (http_request.status == 200) { // ��Ϣ�Ѿ��ɹ����أ���ʼ������Ϣ
			alert("ע����Ϣ��" + http_request.responseText);
		} else { //ҳ�治����
			alert("���������ҳ�����쳣��#" + http_request.status);
		}
	}
}
