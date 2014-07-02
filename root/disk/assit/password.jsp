<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
   function findPassword()
   {
       var username = document.getElementById("username_find").value;
       var x = "username=" + username;
       send_request("POST", "../getpassword", x, "text", afterGetMyBook);
    }
   
       function afterGetMyBook() {
		if (http_request.readyState == 4) {
			if (http_request.status == 200) {
				document.getElementById("right").innerHTML = http_request.responseText;
			} else {
				alert("Error with:" + http_request.status);
			}
		}
	}   
</script>
<div style="width:70%;float:right" id="right">
        用户名：  <input type="text" class="form-control" placeholder="在这里您要找回密码的用户"
			align="absmiddle" id="username_find">
<button type="button" class="btn btn-primary"
			style="width:30%;float:left;" onclick="findPassword()" >找回密码</button>
</div>