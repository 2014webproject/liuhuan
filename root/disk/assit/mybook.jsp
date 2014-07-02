<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="width:70%;float:right" id="right"></div>

<script>
	send_request("POST", "../getmybook", "", "text", afterGetMyBook);
	
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
