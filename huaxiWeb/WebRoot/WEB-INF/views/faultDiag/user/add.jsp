<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>添加用户</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${ctx }/css/faultDiag/style.css"/>
	<script type="text/javascript" src="${ctx }/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/validate/jquery.metadata.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx }/js/validate/jquery.validate.css"/>
	<script type="text/javascript" src="${ctx }/js/validate/jquery.validate.js"></script>
  </head>
  
   <body style="background-color:#fbfbfb;">
<div class="address1">
    <div class="space_left">&nbsp;</div>
    <div class="house"><img src="${ctx}/images/faultDiag/house.png"/></div>
    <div class="address_infor">当前位置：<a href="#" onFocus="blur()">添加用户</a></div>
    <div class="time">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
</div>
<div class="list">
	<form action="${ctx }/admin/faultDiag/user/save" method="post" id="myForm">
	    <table border="0" align="center" cellpadding="0" cellspacing="0" class="tab_sty">
	      <tr>
	        <td nowrap="nowrap" width="200px;">登录名</td>
	        <td class="td_sty">
	        	<input id="username" name="username" type="text" class="input_text" />
	        </td>
	      </tr>
	      <tr>
	      	<td nowrap="nowrap">密码</td>
	        <td class="td_sty">
	        	<input id="password" name="password" type="password" class="required input_password" />
	        </td>
	      </tr>
	      <tr>
	        <td nowrap="nowrap">重复密码</td>
	        <td class="td_sty">
	            <input id="rLoginPwd" name="rLoginPwd" type="password" class="input_password" />
	        </td>
	      </tr>
	      <tr>
	        <td nowrap="nowrap">真实姓名</td>
	        <td class="td_sty">
				<input type="text" id="name" name="realname" class="required input_text"/>
	        </td>
	      </tr>
	      	<tr>
		        <td nowrap="nowrap">联系电话</td>
		        <td class="td_sty">
		        	<input type="text" id="phone" name="phone" class="isPhone input_text"/>
		        </td>
	      	</tr>
	      	<tr>
		        <td nowrap="nowrap">管理员类别</td>
		        <td class="td_sty">
		        	<input type="radio" name="type" value="0" checked="checked"/> 一般用户（不能控制）
		        	<input type="radio" name="type" value="1"/> 超级管理员
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
			username: {
				required:true,
				remote: {
					url:"${ctx}/admin/faultDiag/user/validateLoginName",
					type:"post",
					data: {
						userName: function(){
                        	return $("#username").val();
                        }
                    }
				}
			},
			rLoginPwd:{
				required:true,
				equalTo:'#password'
			}
		},
		messages: {
			loginName: {remote:"登录名已存在！"}
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
