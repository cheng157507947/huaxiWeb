<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>设备故障在线智能诊断系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${ctx }/css/faultDiag/style.css">
	
	<frameset rows="80,*,35" frameborder="no" framespacing="0" border="0" style="overflow:hidden;"> 
		<frame src="${ctx }/admin/faultDiag/top" frameborder="no" marginheight="0" marginwidth="0" scrolling="no" noresize="noresize"> 
		<frameset cols="210,*" frameborder="no" framespacing="0" border="0" style="overflow:hidden;"> 
			<frame id="menu_iframe_height" src="${ctx }/admin/faultDiag/left" name="menu_iframe_height" frameborder="no" marginheight="0" marginwidth="0" scrolling="auto" noresize="noresize">
			<frame id="iframe_height" src="${ctx }/admin/faultDiag/dtuSts/list" name="iframe_height" frameborder="no" marginheight="0" marginwidth="0" scrolling="auto" noresize="noresize">
		</frameset> 
		<frame src="${ctx }/admin/faultDiag/bottom" frameborder="no" marginheight="0" marginwidth="0" scrolling="no" noresize="noresize">
	</frameset>
<noframes></noframes>
</head>

<body>
</body>
</html>
