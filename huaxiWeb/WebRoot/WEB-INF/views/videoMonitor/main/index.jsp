<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>田间视频监控系统</title>
</head>

<frameset rows="96,*,35" cols="*" framespacing="0" frameborder="no" border="0">
  <frame src="${ctx }/admin/videoMonitor/top" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" border="0" />
  <frameset rows="*" cols="200,*" frameborder="no" border="0" framespacing="0"  scrolling="auto">
     <frame src="${ctx }/admin/videoMonitor/left" name="leftFrame" scrolling="auto" noresize="noresize" id="leftFrame" border="0" />
     <frame src="${ctx }/admin/videoMonitor/video" name="mainFrame"  id="mainFrame" scrolling="auto" noresize="noresize">
     
  </frameset>
  <frame src="${ctx }/admin/commBottom" name="bottomFrame" scrolling="no" noresize="noresize" id="bottomFrame" />
</frameset>

<noframes>
<body><p>此网页使用了框架，但您的浏览器不支持框架</p></body>
</noframes>
</html>