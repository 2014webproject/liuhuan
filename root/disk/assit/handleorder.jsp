<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="width:70%;float:right" id="handleorder_id"></div>

<script>
	send_request("POST", "../handleorder", "", "text", getAllOrder);

	function getAllOrder() {
		if (http_request.readyState == 4) {
			if (http_request.status == 200) {
				document.getElementById("handleorder_id").innerHTML = http_request.responseText;
			} else {
				alert("Error with:" + http_request.status);
			}
		}
	}
	function aaa(s) {
		send_request("POST", "../deleteorder", s, "text", handleDelete);
	}
	function handleDelete() {
		if (http_request.readyState == 4) {
			if (http_request.status == 200) {
				if (http_request.responseText.indexOf("true") >= 0) {
				    
					send_request("POST", "../handleorder", "", "text", getAllOrder);
				} else {
					alert("今天没吃药 不知道发生了什么")
				}
			} else {
				alert("Error with:" + http_request.status);
			}
		}
	}
	
</script>