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
	<td>�û���:<input type="text" name="uname"></td>
</tr>

<tr>
	<td>��&nbsp;&nbsp;��:<input type="password" name="upass"></td>
</tr>
<tr>
	<td><input type="checkbox" name="savedata" value="savedata">�����¼��Ϣ
	<input name="submit" type="submit" value="��½">
	<input name="regist" type="button" value="ע��" onclick="window.location.href='regist.jsp'"></td>
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