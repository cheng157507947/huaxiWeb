<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>华西村智慧农田水利物联网管理平台</title>
</STYLE><LINK href="${ctx }/css/login.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var message = "${message}";
		if(message!=null && message.length > 0 && message != ""){
			alert(message);
	}
	
	document.onkeydown=function(event){
		var e = event || window.event || arguments.callee.caller.arguments[0];
		if(e && e.keyCode==13){ // enter 键
			loginSubmit();
		}
	}; 
});
	
function login(){
	$("#error").html("");
	var loginName = $("#loginName").val();
	var loginPwd = $("#loginPwd").val();
	if(loginName==""){
		$("#error").html("用户名不能为空！");
		$("#loginName").focus();
		return false;
	}
	if(loginPwd==""){
		$("#error").html("密码不能为空！");
		$("#loginPwd").focus();
		return false;
	}
	return true;
}
 
function loginSubmit(){
	if(login()){
		$("#myForm").submit();
	}
}

</script>

</head>
<body>
<div class="login">
     <div class="ptrk"><img src="${ctx }/images/ptrk.gif"/></div>
     <div class="dl">
          <div class="dlbj"></div>
          <div class="dlptxt">登录平台系统</div>
          <form class="login_admin" action="${ctx }/toLogin" method="post" id="myForm">
          <!--  上线后使用 
          <div class="xxkuang1"><span>登录名：</span><input name="userName" id="loginName" value="${userName }" type="text"  class="kuanginput"/></div>
          <div class="xxkuang2"><span>密码：</span><input name="pwd" id="loginPwd" value="${pwd }" type="text"  class="kuanginput"/></div>
           -->
          <div class="xxkuang1"><span>登录名：</span><input name="userName" id="loginName" value="admin" type="text"  class="kuanginput"/></div>
          <div class="xxkuang2"><span>密码：</span><input name="pwd" id="loginPwd" value="1" type="password"  class="kuanginput"/></div>
          <!-- <div class="zddl"><input name="" type="checkbox" value="" /> 自动登录</div> -->
          <div class="zddl"><span id="error" style="color: red;"></span></div>
          <div class="dlcz"><a href="javascript:loginSubmit();"><img src="images/ljdl.gif"/></a></div>
          </form>
     </div>
     
</div> 
</body>
</html>

