<%@ page contentType="text/html" pageEncoding="GBK" import="java.lang.*, java.util.*"%>

<html>
<head>
<title>success</title>
</head>
<body>
<%
	String s1 = request.getParameter("uname");
	String s2 = (String)session.getAttribute(s1);
	if (s2 == null || s2.length() <= 0) {
		response.sendRedirect("login.jsp");
	}
%>
<h1>hahahah</h1>
</body>
</html>