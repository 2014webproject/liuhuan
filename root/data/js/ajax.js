//定义XMLHttpRequest对象实例
var http_request = false;
//定义可复用的http请求发送函数
function send_request(method,url,content,responseType,callback) {//初始化、指定处理函数、发送请求的函数
	http_request = false;
    //开始初始化XMLHttpRequest对象
    if(window.XMLHttpRequest) { //Mozilla 浏览器
        http_request = new XMLHttpRequest();
        if (http_request.overrideMimeType) {//设置MiME类别
            http_request.overrideMimeType("text/xml");
        }
    } else if (window.ActiveXObject) { // IE浏览器
        try {
            http_request = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                http_request = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {}
        }
    }
    if (!http_request) { // 异常，创建对象实例失败
        window.alert("不能创建XMLHttpRequest对象实例.");
        return false;
    }
    if(responseType.toLowerCase()=="text") {
        //http_request.onreadystatechange = processTextResponse;
        http_request.onreadystatechange = callback;
    } else if(responseType.toLowerCase()=="xml") {
        //http_request.onreadystatechange = processXMLResponse;
        http_request.onreadystatechange = callback;
    } else {
        window.alert("响应类别参数错误。");
        return false;
    }
    // 确定发送请求的方式和URL以及是否异步执行下段代码
    if(method.toLowerCase()=="get") {
        http_request.open(method, url, true);
    } else if(method.toLowerCase()=="post") { // POST方法提交需要设置头信息
        http_request.open(method, url, true);
        http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    } else {
        window.alert("http请求类别参数错误。");
        return false;
    }
    http_request.send(content);
}
