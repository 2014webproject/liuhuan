<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style type="text/css">
.code
    {
           /*  background:url(code_bg.jpg); */
            font-family:Arial;
            font-style:italic;
             color:blue;
             font-size:20px;
             border:0;
             letter-spacing:1px;
             font-weight:bolder;             
            /*  float:left;  */           
             cursor:pointer;
             width:90px;
             height:40px;
             /* line-height:60px; */
           /*   text-align:center; */
            /*  vertical-align:middle; */
            /* vertical-align:left; */

    }
</style>
<script type="text/javascript">
    document.getElementById("checkCode").src = "image.jsp?"+Math.random(); 
	function login() { 
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		var inputCode=document.getElementById("inputCode").value;
		var x = "username=" + username + "&password=" + password+ "&inputCode=" + inputCode;
		var y = window.location.href;
		var z = y.match(/location=[a-z]+/);
		/*  var inputCode = document.getElementById("inputCode").value;
            if (inputCode.length <= 0) 
            {
                alert("请输入验证码！");
                return;
            }
            else if (inputCode.toUpperCase() != code.toUpperCase()) 
            {
                alert("验证码输入有误！");
                createCode();
                return;
            }    */
		if (z != null && z.length > 0) {
			x = z + "&" + x;
		}
		send_request("POST", "../checklogin", x, "text", afterLogin);
	}
	function afterLogin() {
	    var error="";
		if (http_request.readyState == 4) { // 判断对象状态
			if (http_request.status == 200) { // 信息已经成功返回，开始处理信息
				if (http_request.responseText.indexOf("login") >= 0) {
				    error="用户名或密码错误！"
				}
				if(http_request.responseText.indexOf("inputCodeError")>=0){
				    error+="验证码错误";
				}
				if(error!="") 
				{
				    alert(error);
				    return;
				 }
				window.location.href = http_request.responseText;
			} else { //页面不正常
				alert("您所请求的页面有异常。#" + http_request.status);
			}
		}
	}
	function changeForRegist() {
		var y = window.location.href;
		var z = y.match(/location=[a-z]+/);
		y = "index.jsp?";
		if (z != null && z.length > 0) {
			y += z + "&";
		}
		y += "state=regist";
		window.location.href = y;
	}
	function refresh(obj) {obj.src = "../imageServlet?"+Math.random();}
</script>
<div style="width:22%;float:left" id="left">
	<h4 align="center">登陆</h4>
	<div class="input-group"  >
		用户名： <input type="text" class="form-control" placeholder="在这里输入用户名"
			align="absmiddle" id="username"> 密码： <input type="password"
			class="form-control" placeholder="在这里输入密码" align="absmiddle"
			id="password">
	        验证码： 
	        </br>
	        <input type="text" class="form-control" placeholder="验证码"
			align="absmiddle" id="inputCode" style="width:40%;float:left" maxlength="4">
         <img  onclick="refresh(this)" src="../imageServlet" style="width:40%;float:right"></img>
           <!--  <a  href="#" onclick="createCode()">看不清换一张</a> -->
         
	</div>
	<!-- <div class="btn-group" style="width:100%;float:left;"> -->
		<button type="button" class="btn btn-primary"
			style="width:50%;float:left;" onclick="login()" >登陆</button>
		<button type="button" class="btn btn-info"
			style="width:50%;float:left;" onclick="changeForRegist()">注册</button>
	<!-- </div> -->
	<a  width="100%" onclick="changeIndex('password')"><h5>忘记密码</h5>
		</a>
</div>