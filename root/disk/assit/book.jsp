<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div style="width:70%;float:right" id="right">
             <!-- <div style="width:10%;float:left"><p>地址</p></div> -->
          
	<div style="width:100%;float:left;">
		<h3 style="width:50%;float:left;">请选择您要选购的产品</h3>
		<button type="button" class="btn btn-defalut" onclick="submitOrder()"
			style="width:25%;float:right;">确认订单</button>
	</div>
	<script>
		document.getElementById('time').innerHTML = "<h3>"
				+ new Date().toLocaleString() + "星期"
				+ "日一二三四五六".charAt(new Date().getDay()) + "</h3>";
		setInterval(
				"document.getElementById('time').innerHTML='<h3>'+new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay()+'</h3>');",
				1000);

		function function_sum(x) {
			var text = "Text" + x;
			var price = "price" + x;

			var num = document.getElementById(text).value;
			var total = parseInt(document.getElementById(price).innerHTML);
			 if(isNaN(num))
					  {
						  document.getElementById(text).value="";
						  return false;
					  }
			document.getElementById("sum" + x).innerHTML = num * total;
			var sum = 0;
			for (i = 1; i <= 8; i++) {
				sum += parseInt(document.getElementById("sum" + i).innerHTML);
			}
			document.getElementById("sum").innerHTML = sum;
		}

		function submitOrder() {
			var sum = document.getElementById("sum").innerHTML;
			var address=document.getElementById("address").value;
			if (sum != "NaN") {
				if (parseInt(sum) > 0) {
					var y = "";
					var z;
					for (i = 1; i <= 8; i++) {
						var x = parseInt(document.getElementById("sum" + i).innerHTML);
						if (x != 0) {
							z = document.getElementById("name" + i).innerHTML
									+ ":"
									+ document.getElementById("Text" + i).value
									+ ":"
									+ document.getElementById("sum" + i).innerHTML
									+ ";";
							y+= z;
						}
					}
					y=y+"&address="+address;
					send_request("POST", "../handlebook", "booklist=" + y, "text", afterBook);
				} else {
					alert("总和不能小于等于0");
				}
			} else
				alert("are you kidding!");
		}
		function afterBook() {
			if (http_request.readyState == 4) {
				if (http_request.status == 200) {
				   alert("成功提交！");
					var url = "index.jsp?state=user&location=mybook";
					window.location.href = url;
				} else {
					alert(http_request.status);
				}
			}
		}
</script>
	<table class="table table-hover">
		<tr>
			<td>产品名称</td>
			<td>单价</td>
			<td>数量</td>
			<td>总价</td>
		</tr>
		<tr>
			<td id="name1">番茄馄饨</td>
			<td id="price1">18</td>
			<td><input id="Text1" onkeyup="function_sum(1)" maxlength="2"></td>
			<td id="sum1">0</td>

		</tr>
		<tr>
			<td id="name2">老鸭煲馄饨</td>
			<td id="price2">19</td>
			<td><input id="Text2" onkeyup="function_sum(2)" maxlength="2"></td>
			<td id="sum2">0</td>
		</tr>
		<tr>
			<td id="name3">名禾鲜鱼馄饨</td>
			<td id="price3">19</td>
			<td><input id="Text3" onkeyup="function_sum(3)" maxlength="2"></td>
			<td id="sum3">0</td>
		</tr>
		<tr>
			<td id="name4">奇香牛肉馄饨</td>
			<td id="price4">19</td>
			<td><input id="Text4" onkeyup="function_sum(4)" maxlength="2"></td>
			<td id="sum4">0</td>
		</tr>
		<tr>
			<td id="name5">虾仁馄饨</td>
			<td id="price5">14</td>
			<td><input id="Text5" onkeyup="function_sum(5)" maxlength="2"></td>
			<td id="sum5">0</td>
		</tr>
		<tr>
			<td id="name6">三鲜馄饨</td>
			<td id="price6">14</td>
			<td><input id="Text6" onkeyup="function_sum(6)" maxlength="2"></td>
			<td id="sum6">0</td>
		</tr>
		<tr>
			<td id="name7">芹菜馄饨</td>
			<td id="price7">11</td>
			<td><input id="Text7" onkeyup="function_sum(7)" maxlength="2"></td>
			<td id="sum7">0</td>
		</tr>
		<tr>
			<td id="name8">美味锅贴</td>
			<td id="price8">8</td>
			<td><input id="Text8" onkeyup="function_sum(8)" maxlength="2"></td>
			<td id="sum8">0</td>
		</tr>
		<tr>
			<td>总计</td>
			<td></td>
			<td></td>
			<td id="sum">0</td>
		</tr>
	</table>
	<div style="width:100%;float:left;">
          请输入送货地址，电话<input id="address" style="width:70%;float:right;"></input>
	</div>
	
</div>
