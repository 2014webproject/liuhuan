<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	function regist() {
		var username = document.getElementById("username").value;
		if (username == null || username.length < 6) {
			alert("用户名长度不足六位!");
			return;
		}
		var password = document.getElementById("password").value;
		if (password == null || password.length < 6) {
			alert("密码长度不足六位!");
			return;
		}
		var repeat = document.getElementById("repeat").value;
		if (repeat == null || repeat != password) {
			alert("两次密码必须相同!");
			return;
		}
		var email=document.getElementById("usermail").value;
		var reg=/^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/gi;
        if(!reg.test(document.getElementById("usermail").value))
       {
           alert("非法邮箱!");
           return false;
       }
		send_request("POST", "../regist", "username=" + username
				+ "&password=" + password+"&email="+email, "text", afterRegist);
	}
	function afterRegist() {
		if (http_request.readyState == 4) { // 判断对象状态
			if (http_request.status == 200) { // 信息已经成功返回，开始处理信息
				var x = http_request.responseText;
				var y = window.location.href;
				if (x.indexOf("SUCCESS") >= 0) {
					alert("注册成功!");
					var z = y.match(/location=[a-z]+/);
					y = "index.jsp?";
					if (z != null && z.length > 0) {
						y += z + "&";
					} 
					y += "state=login";
					window.location.href = y;
				} else {
					alert("注册失败!");
				}
			} else { //页面不正常
				alert("您所请求的页面有异常。#" + http_request.status);
			}
		}
	}
</script>
<div style="width:22%;float:left" id="left">
	<h4 align="center">注册</h4>
	<div class="input-group" style="width:100%;float:left;">
		<form>
			用户名： <input type="text" class="form-control" placeholder="在这里输入用户名"
				align="absmiddle" id="username"> 密码： <input type="password"
				class="form-control" placeholder="在这里输入密码" align="absmiddle"
				id="password"> 重复密码： <input type="password"
				class="form-control" placeholder="在这里输入密码" align="absmiddle"
				id="repeat">
			常用邮箱： <input type="text" class="form-control" placeholder="在这里输入用户名"
				align="absmiddle" id="usermail"> 
	</div>
	<div class="btn-group" style="width:100%;float:left;">
		<button type="button" class="btn btn-primary"
			style="width:50%;float:left;" onclick="regist()">注册</button>
		<input type="reset" value="重置" class="btn btn-info"
			style="width:50%;float:left;">
		</form>
	</div>
	<br>
	<div>
		<a href="index.jsp?state=login" width="100%"><h5>已有账号</h5> </a>
	</div>
</div>

