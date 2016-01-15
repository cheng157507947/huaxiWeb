<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>华西村智慧农田水利物联网管理平台</title>
</STYLE><LINK href="${ctx }/css/css.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
function showDate(){
	var date = new Date();
	var Y = date.getFullYear();    //获取完整的年份(4位,1970-????)
	var M = Add0(date.getMonth()+1);       //获取当前月份(0-11,0代表1月)
	var D = Add0(date.getDate());        //获取当前日(1-31)
	
	var E = date.getDay();         //获取当前星期X(0-6,0代表星期天)
	
	var H = Add0(date.getHours());       //获取当前小时数(0-23)
	var m = Add0(date.getMinutes());     //获取当前分钟数(0-59)
	var s = Add0(date.getSeconds());     //获取当前秒数(0-59)
	
	$("#date").text(Y+"年"+M+"月"+D+"日 "+H+":"+m+":"+s);
}

function Add0(num){
	if(num<10 && (num+"").length<2){
		return "0"+num;
	}
	return num+"";
}

setInterval(function(){
		  showDate();
	}, 1000);
function exit(){
	if (confirm("确定退出系统吗？")){
		parent.window.location.href="${ctx }/exit";
	}
}
</script>

</head>
<body>
<div class="title-nr">
          <div class="title-nr-left">
               <span>欢迎您：${user.realname } 先生登录系统</span>
               <span>当前时间：<span id="date"></span></span>
          </div>
          <div class="title-nr-right"><a href="javascript:exit();"><img src="${ctx }/images/tcxt.png"/></a></div>
</div>   

<div class="mid">
     <div class="bt">华西村智慧农田水利物联网管理平台</div>
     <div class="btn">
          <span><a href="${ctx }/admin/envirMonitor/index" target="_blank"><img src="${ctx }/images/btn1.png"/></a></span>
          <span><a href="${ctx }/admin/irrigateContorl/index" target="_blank"><img src="${ctx }/images/btn2.png"/></a></span>
          <span><a href="${ctx }/admin/growPc/index" target="_blank"><img src="${ctx }/images/btn3.png"/></a></span><br/>
          <span><a href="${ctx }/admin/faultDiag/index" target="_blank"><img src="${ctx }/images/btn4.png"/></a></span>
          <span><a href="${ctx }/admin/sourceMonitor/index" target="_blank"><img src="${ctx }/images/btn5.png"/></a></span>
          <span><a href="${ctx }/admin/videoMonitor/index" target="_blank"><img src="${ctx }/images/btn6.png"/></a></span>
     </div>
</div>
<div class="foot">建设单位：江阴市水利农机局</div>
</body>
</html>