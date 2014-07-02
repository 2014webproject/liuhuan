<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
   function logOut()
   {
     send_request("POST", "../closeconnection", "", "text", afterLogin);
    
   }
   function afterLogin()
   {
    window.location.href="index.jsp";
   }
</script>
<div style="width:22%;float:left" id="left">
	<c:if test="${empty sessionScope.username}">
		<script type="text/javascript">
			window.location.href="index.jsp";
		</script>
	</c:if>
	<c:out value="${sessionScope.username}的个人空间"></c:out>
	<c:if var="xxx" test="${not empty sessionScope.manager && sessionScope.username==sessionScope.manager}">
		<a onClick="logOut()" width="100%"><h5>关闭数据库连接</h5></a>
	</c:if>
	<c:if test="${not xxx}">
	<div>
		<a href="index.jsp?state=user&location=book" width="100%"><h5>订餐</h5>
		</a>
	</div>
	<div>
		<a href="index.jsp?state=user&location=mybook" width="100%"><h5>我的订单</h5>
		</a>
	</div>
	<div>
		<a   width="100%" onClick="logOut()"><h5>退出登录</h5>
		</a>
	</div>
	</c:if>
	
</div>