<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>智慧农田灌溉控制系统</title>
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/lhgdialog/lhgdialog.min.js"></script>
<script type="text/javascript">

var video = null;
function openVideo(name,ip,port,loginName,pwd,guid){
	if(video != null){
		closeVideo();
	}
	video = $.dialog({
		width: 450,
    	height: 300,
		title:name,
		content: "url:${ctx}/admin/video?ip="+ip+"&port="+port+"&loginName="+loginName+"&pwd="+pwd+"&guid="+guid,
		close: function(){
			video = null;
			}
		});
}
function closeVideo(){
	if(video == null) return;
	video.close();
	video = null;
}

$(document).ready(function(){
	/********************
	 * 取窗口可视范围的高度
	 *******************/
	var clientHeight = document.documentElement.clientHeight;
	$("body").attr("style","margin: 0;padding: 0;width: 100%;height:"+ clientHeight +"px;");
});

//当浏览器窗口大小改变时，设置显示内容的高度  
window.onresize=function(){
     /********************
	 * 取窗口可视范围的高度
	 *******************/
	var clientHeight = document.documentElement.clientHeight;
	$("body").attr("style","margin: 0;padding: 0;width: 100%;height:"+ clientHeight +"px;");
}  
</script>
</head>

<body style="margin: 0;padding: 0;width: 100%;height: 100%;">
	<iframe name="indexFrame" src="${ctx }/admin/irrigateContorl/indexFrame" style="width: 100%;height: 100%;border: 0" scrolling="no"></iframe>
</body>
</html>