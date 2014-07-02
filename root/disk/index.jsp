<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<style>
body {
	font-family: Verdana;
	font-size: 12px;
	margin: 0 auto;
}

#container {
	margin: 0 auto;
	width: 80%;
}

#left {
	word-wrap: break-word;
	overflow: hidden;
	background: #000;
	color: #FFF
}

div {
	margin: 6;
	border: 6;
	padding: 6;
}
</style>
<head>
<title>名禾馄饨在线点餐系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript" src="js/myfunc.js"></script>
<script type="text/javascript">
	function changeIndex(k) {
		var x = document.URL;
		var url = "index.jsp?location=" + k;
		var y = window.location.href;
		if (y.indexOf("state") >= 0) {
			url += "&state=";
			if (y.indexOf("user") >= 0)
				url += "user";
			else if (y.indexOf("login") >= 0)
				url += "login";
			else if(y.indexOf("password")>=0)
			    url+="password";
			else
				url += "regist";
		}
		if (x.indexOf("username") > 0) {
			var loc = getQueryString("username");
			url += "&username=" + loc;
		}
		window.location.href = url;
	}
</script>
</head>
<body onload="createCode()" >
	<div id="container">
		<div style="width:100%;float:left;">
			<div style="width:50%;float:left;">
				<h2>名禾馄饨在线点餐系统</h2>
			</div>
			<div id="time" style="width:40%;float:right;"></div>
		</div>
		<script>
			document.getElementById('time').innerHTML = "<h3>"
					+ new Date().toLocaleString() + "星期"
					+ "日一二三四五六".charAt(new Date().getDay()) + "</h3>";
			setInterval("document.getElementById('time').innerHTML='<h3>'+new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay())+'</h3>';",1000);
		</script>
		<div class="btn-group" style="width:100%;float:left;">
			<button type="button" class="btn btn-primary"
				style="width:25%;float:left;" onclick="changeIndex('com')">首页</button>
			<button type="button" class="btn btn-success"
				style="width:25%;float:left;" onclick="changeIndex('introduce')">产品简介</button>
			<button type="button" class="btn btn-warning"
				style="width:25%;float:left;" onclick="changeIndex('hotpage')">最热产品</button>
			<button type="button" class="btn btn-danger"
				style="width:25%;float:left;" onclick="changeIndex('connection')">我要评论</button>
		</div>
		<br> <br>
		<c:catch var="xxx">
			<c:choose>
			  <c:when test="${param.state=='regist'}">
			       <c:import url="assit/regist.jsp"></c:import>
			    </c:when>
				<c:when test="${empty param.state || param.state.length() <= 0}">
					<c:import url="assit/login.jsp"></c:import>
				</c:when>
				<c:otherwise>
					<c:import url="assit/${param.state}.jsp"></c:import>
				</c:otherwise>
			</c:choose>
			<c:choose>
			    <c:when
			       test="${param.state=='password'}">
			       <c:import url="assit/password.jsp"></c:import>
			    </c:when>
			  
				<c:when
					test="${not empty sessionScope.manager && not empty param.state && param.state=='user' && sessionScope.username==sessionScope.manager}">
					<c:import url="assit/handleorder.jsp"></c:import>
				</c:when>
				<c:when
					test="${empty param.location || param.location.length() <= 0}">
					<c:import url="assit/com.jsp"></c:import>
				</c:when>
				<c:otherwise>
					<c:import url="assit/${param.location}.jsp"></c:import>
				</c:otherwise>
			</c:choose>
		</c:catch>
		<c:if test="${not empty xxx}">
			<c:redirect url="index.jsp"></c:redirect>
		</c:if>
	</div>
</body>
</html>