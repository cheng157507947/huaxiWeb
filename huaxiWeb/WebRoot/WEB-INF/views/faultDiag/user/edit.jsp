<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>编辑用户</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${ctx }/css/faultDiag/style.css"/>
	<script type="text/javascript" src="${ctx }/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/validate/jquery.metadata.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx }/js/validate/jquery.validate.css"/>
	<script type="text/javascript" src="${ctx }/js/validate/jquery.validate.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$(".pwd").hide();
	});
	var change = false;
	function changePWD(){
		if(change){
			$("#loginPwd").val("");
			$(".pwd").hide();
			change = false;
		}else{
			$(".pwd").show();
			change = true;
		}
	}
	
	</script>
  </head>
  
<body style="background-color:#fbfbfb;">
<div class="address1">
    <div class="space_left">&nbsp;</div>
    <div class="house"><img src="${ctx}/images/faultDiag/house.png"/></div>
    <div class="address_infor">当前位置：<a href="#" onFocus="blur()">编辑用户</a></div>
    <div class="time">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
</div>
<div class="list">
	<form action="${ctx }/admin/faultDiag/user/save" method="post" id="myForm">
		<input type="hidden" name="id" value="${user.id }" />
    	<input type="hidden" name="username" value="${user.username }" />
	    <table border="0" align="center" cellpadding="0" cellspacing="0" class="tab_sty">
	      <tr>
	        <td nowrap="nowrap" width="200px;">登录名</td>
	        <td class="td_sty">
	        	${user.username }<a href="javascript:changePWD();" style="margin-left: 50px;font-size: 9pt;">修改密码</a>
	        </td>
	      </tr>
	      <tr class="pwd">
	        <td nowrap="nowrap">原密码</td>
	        <td class="td_sty">
				<input type="password" id="loginPwd" name="password" class="input_text"/>
	        </td>
	      </tr>
	      <tr class="pwd">
	        <td nowrap="nowrap">新密码</td>
	        <td class="td_sty">
				<input type="password" id="newPwd" name="newPwd" class="input_text"/>
	        </td>
	      </tr>
	      <tr class="pwd">
	        <td nowrap="nowrap">重复密码</td>
	        <td class="td_sty">
				<input type="password" id="newPwd2" name="newPwd2" class="input_text"/>
	        </td>
	      </tr>
	      <tr>
	        <td nowrap="nowrap">真实姓名</td>
	        <td class="td_sty">
				<input type="text" id="name" name="realname" class="required input_text" value="${user.realname}"/>
	        </td>
	      </tr>
	      	<tr>
		        <td nowrap="nowrap">联系电话</td>
		        <td class="td_sty">
		        	<input type="text" id="phone" name="phone" class="isPhone input_text" value="${user.phone}"/>
		        </td>
	      	</tr>
	      	<tr>
		        <td nowrap="nowrap">管理员类别</td>
		        <td class="td_sty">
		        	<c:if test="${user.type == 0 }">
		        	<input type="checkbox" name="type" value="0" checked="checked"/> 一般用户（不能控制）
		        	<input type="checkbox" name="type" value="1"/> 超级管理员
		        	</c:if>
		        	<c:if test="${user.type == 1 }">
		        	<input type="checkbox" name="type" value="0"/> 一般用户（不能控制）
		        	<input type="checkbox" name="type" value="1" checked="checked"/> 超级管理员
		        	</c:if>
		        </td>
	      	</tr>
	    </table>
	  </form>
</div>
<div class="cr">
	<div class="save"><img src="${ctx}/images/faultDiag/save_button.png" onclick="changeState(this);" /></div>
    <div class="return"><img src="${ctx}/images/faultDiag/return_button.png" onclick="javascript:history.back();" /></div>
</div>
</body>
</html>
<script type="text/javascript" src="${ctx }/js/dataformat.js"></script>
<script type="text/javascript">
<!--
var validate;
$(document).ready(function() {
	var w = $(window).width();
	if($.support.boxModel){
		$(".tab_sty").css("width",w-20);
	}else{
		if(w > 0){
			$(".tab_sty").css("width",w-20);
		}else{
			$(".tab_sty").css("width","96%");
			var ad = $(".list").width();
			$(".address1").css("width",ad+20);
		}
	}
	validate = $("#myForm").validate({
		rules: {
		}
	});
	$(window).resize(function() {
		var width = $(window).width();
		if($.support.boxModel){
			$(".tab_sty").css("width",width-20);
		}else{
			if(width > 0){
				$(".tab_sty").css("width",width-20);
			}else{
				$(".tab_sty").css("width","96%");
				var ad1 = $(".list").width();
				$(".address1").css("width",ad1+20);
			}
		}
	});
});

function changeState(obj){
	if(validate.form()){
		if(change){
			if($("#loginPwd").val().length == 0){
				alert("原密码不能为空");
				return;
			}
			if($("#newPwd").val().length == 0 || $("#newPwd").val() != $("#newPwd2").val()){
				alert("新密码不能为空，且重复密码必须与新密码相同");
				return;
			}
		}
		$("#myForm").submit();
		$(obj).attr("disabled","disabled");
	}
}
function now(){
    var date = new Date();
   var dStr = date.format();
   $(".time").html(dStr+"&nbsp;&nbsp;&nbsp;&nbsp;");
  }
  setInterval(now, 1000);
//-->
</script>