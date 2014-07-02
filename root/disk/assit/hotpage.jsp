<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
    send_request("POST", "../getHot", "", "text", afterGetMyBook);
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
</div>
