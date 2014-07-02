<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>注册页面</title>
<script type="text/javascript" src="data/js/myfunc.js" charset="GBK"></script>
<script type="text/javascript" src="data/js/ajax.js"></script>
<script type="text/javascript">
function reloadImg() {
	var img = document.getElementById("checkimg");
	img.src = "data/assit/image.jsp?" + Math.random();
}
</script>
</head>
<body>
	<form name="registform" method="post" action="">

		<table style="font-size:12px;">
			<tr>
				<td>&nbsp;&nbsp;&nbsp;常用邮箱:<input type="text"
					name="username" onblur="checkUsername()"> <span
					id="callback_name" style="color:RED"></span></td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;&nbsp;密&nbsp;码:<input type="password"
					name="password" onblur="checkPasswordLength()"> <span
					id="callback_pass" style="color:RED"></span></td>
			</tr>
			<tr>
				<td>重复密码:<input type="password" name="repeatpass"
					onblur="checkRepeatPassword()"> <span
					id="callback_repeatpass" style="color:RED"></span></td>
			</tr>
			<tr>
				<td>验证码:<input name="checktext" type="text"
					onblur="checkText()"><img id="checkimg" 
					alt="30" src="data/assit/image.jsp" onclick="reloadImg();">
					<span id="callback_check" style="COLOR:RED"></span>
				</td>
			</tr>
			<tr>
				<td><input type="button" value="注册" onclick="regist()">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>