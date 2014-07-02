<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK" %>
<html>
<head>
<title>Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
</head>

<body>
<form name="loginform" action="checklogin" method="post">
<table style="font-size:12px;">
<tr>
	<td>用户名:<input type="text" name="uname"></td>
</tr>

<tr>
	<td>密&nbsp;&nbsp;码:<input type="password" name="upass"></td>
</tr>
<tr>
	<td><input type="checkbox" name="savedata" value="savedata">保存登录信息
	<input name="submit" type="submit" value="登陆">
	<input name="regist" type="button" value="注册" onclick="window.location.href='regist.jsp'"></td>
</tr>
</table>
</form>
<%
	Cookie[] cookies = request.getCookies();
	if (cookies == null) return;
	for (int i = 0; i < cookies.length; i++) {
		Cookie c = cookies[i];
		if (c.getName().equals("JSESSIONID")) continue;
		if (c.getName() != null && c.getValue() != null) {
			response.sendRedirect("checklogin?uname=" + c.getName() + "&upass=" + c.getValue());
			break;
		}
	}
%>
</body>
</html>