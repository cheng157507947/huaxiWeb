<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${ctx }/css/faultDiag/style.css">
  </head>
  
  <body>
    <div class="logo_area">
	<div class="logo_left"><img src="${ctx }/images/faultDiag/_01.jpg" /></div>
    <div class="logo_right">
    	<div class="space_top">&nbsp;</div>
        <div class="space_right">&nbsp;</div>
        <div class="infor">
        	<div onclick="exit();" class="exit"><a href="#" onFocus="blur()"><div class="close_icon"><img src="${ctx }/images/faultDiag/close.png"></div><div class="close_txt">&nbsp;退出</div></a></div>
        	<div class="logo_text">&nbsp;欢迎 管理员 登陆设备故障在线智能诊断系统</div>
        </div>
    </div>
</div>
  </body>
</html>
<script type="text/javascript">
<!--
function exit(){
	if (confirm("确定返回导航页么？")){
		parent.window.location.href="${ctx}/admin/nav";
	}
}
//-->
</script>