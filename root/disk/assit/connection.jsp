<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
    var t=0;
    
      x="num="+t;
      send_request("POST", "../displayComment", x, "text", aftersss); 
    function aftersss()
    {
       if (http_request.readyState == 4) { // 判断对象状态
			if (http_request.status == 200) { // 信息已经成功返回，开始处理信息
			   //document.getElementById("content").innerHTML="";
			   if(http_request.responseText.indexOf("table")<0) 
			   {
			     alert(http_request.responseText);
			     t--;
			   }			      
			  else document.getElementById("content").innerHTML=http_request.responseText;
			} else { //页面不正常
				alert("您所请求的页面有异常。#" + http_request.status);
			}
		}
		
    }
    function submitComment()
    {
       var x=document.getElementById("comment").innerHTML;
       x="content="+x;
       //alert(x);
       send_request("POST", "../insertComment", x, "text", afterSubmit); 
    }
    function afterSubmit()
    {
       document.getElementById("comment").innerHTML="";
       x=0;
    }
    function lastComment()
    {
      if(t==0) 
      {
        alert("已经是第一条评论！");
        return;
       }
       else
       {
         t--;
         x="num="+t;
         send_request("POST", "../displayComment", x, "text", aftersss); 
       }
    }
    function nextComment()
    {
         t=t+1;
        x="num="+t;
        //alert(x);
        send_request("POST", "../displayComment", x, "text", aftersss);
    }
</script>
 <div style="width:70%;float:right" id="right">
      <div class="page-header">当前评论列表
    </div>
      
      <div id="content">当前暂无用户发表评论！</div>
      <div style="margin:0 auto;width:300px;">
        <ul class="pager">
        <li><a onclick="lastComment()">上一条</a></li>
        <li><a onclick="nextComment()">下一条</a></li>
        </ul>

        </div>
     <textarea style="width:100%;float:right" rows="10" id="comment"></textarea>
     <div style="margin:0 auto;width:100px;"> <button  class="btn btn-primary" onClick="submitComment()" >提交</button></div>
    </div>