<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>灌溉水源监控系统</title>
<LINK href="${ctx }/css/comm/css.css" type="text/css" rel="stylesheet">
<LINK href="${ctx }/css/sourceMonitor/title.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#nav").children().bind("click", function(){
			$("#nav").find(".nav-btn-xz").removeClass("nav-btn-xz").addClass("nav-btn-fxz");
			$(this).removeClass("nav-btn-fxz").addClass("nav-btn-xz");
		});
		
		var date = new Date();
		$("#date").text(date.getFullYear()+"年"+Add0(date.getMonth()+1)+"月"+Add0(date.getDate())+"日");
	});
	
	function Add0(num){
		if(num<10 && (num+"").length<2){
			return "0"+num;
		}
		return num+"";
	}
	function exit(){
		if (confirm("确定返回导航页么？")){
			parent.window.location.href="${ctx}/admin/nav";
		}
	}
</script>
</head>
<body> 
<div class="title">
     <div class="top">
          <a href="javascript:exit();"><img src="${ctx }/images/sourceMonitor/tcxt.png"/></a>
     </div>
     <div class="dqyh">当前用户：${user.realname } <span id="date">2014年09月13日</span></div>
      <div class="nav" id="nav">
          <span class="nav-btn-xz"><a href="${ctx }/admin/sourceMonitor/dispense" target="mainFrame">实时监控</a></span>
          <span><img src="${ctx }/images/sourceMonitor/fg.gif"/></span>
          <span class="nav-btn-fxz"><a href="${ctx }/admin/sourceMonitor/dataCollect" target="mainFrame">数据汇总</a></span>
          <span><img src="${ctx }/images/sourceMonitor/fg.gif"/></span>
          <span class="nav-btn-fxz"><a href="${ctx }/admin/sourceMonitor/charts" target="mainFrame">曲线分析</a></span>
          <span><img src="${ctx }/images/sourceMonitor/fg.gif"/></span>
          <span class="nav-btn-fxz"><a href="${ctx }/admin/sourceMonitor/dataQuery" target="mainFrame">数据查询</a></span>
          <span><img src="${ctx }/images/sourceMonitor/fg.gif"/></span>
		  <!--
          <span class="nav-btn-fxz"><a href="zhgl.html" target="mainFrame">综合管理</a></span>
          <span><img src="${ctx }/images/sourceMonitor/fg.gif"/></span>
          <span class="nav-btn-fxz"><a href="scgl.html" target="mainFrame">生产管理</a></span>
          <span><img src="${ctx }/images/sourceMonitor/fg.gif"/></span>
		  -->
          <span class="nav-btn-fxz"><a href="dtll.html" target="mainFrame">地图浏览</a></span>
     </div>
</div>          
</body>
</html>

