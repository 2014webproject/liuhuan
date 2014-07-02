//全局变量
var http_request = false;
// 异步发送请求
function send_request(method, url, content, responseType, callback) {
	http_request = false;
	if (window.XMLHttpRequest) { //Mozilla 浏览器
		http_request = new XMLHttpRequest();
		if (http_request.overrideMimeType) {
			http_request.overrideMimeType("text/xml");
		}
	} else if (window.ActiveXObject) { // IE 浏览器
		try {
			http_request = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				http_request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
			}
		}
	}
	if (!http_request) { // 创建对象失败
		window.alert("创建XMLHttpRequest失败.");
		return false;
	}
	if (responseType.toLowerCase() == "text") {
		//http_request.onreadystatechange = processTextResponse;
		http_request.onreadystatechange = callback;
	} else if (responseType.toLowerCase() == "xml") {
		//http_request.onreadystatechange = processXMLResponse;
		http_request.onreadystatechange = callback;
	} else {
		window.alert("参数错误");
		return false;
	}
	// 提交方式
	if (method.toLowerCase() == "get") {
		http_request.open(method, url, true);
	} else if (method.toLowerCase() == "post") { // POST提交需要额外设置头信息
		http_request.open(method, url, true);
		http_request.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
	} else {
		window.alert("http提交方式错误");
		return false;
	}
	http_request.send(content);
}
