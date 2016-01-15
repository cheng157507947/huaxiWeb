<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>添加控制设备</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${ctx }/css/faultDiag/style.css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/validate/jquery.metadata.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx }/js/validate/jquery.validate.css" />
<script type="text/javascript" src="${ctx }/js/validate/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx }/js/admin.js"></script>
</head>

<body style="background-color:#fbfbfb;">
	<div class="address1">
		<div class="space_left">&nbsp;</div>
		<div class="house">
			<img src="${ctx}/images/faultDiag/house.png" />
		</div>
		<div class="address_infor">
			当前位置：<a href="#" onFocus="blur()">添加控制设备</a>
		</div>
		<div class="time">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
	</div>
	<div class="list">
		<form action="${ctx }/admin/faultDiag/controlDev/save" method="post" id="myForm">
			<table border="0" align="center" cellpadding="0" cellspacing="0" class="tab_sty">
				<tr>
					<td nowrap="nowrap">所属DTU设备</td>
					<td class="td_sty">
						<select name="dtuDev.id" id="dtuDevs" class="required">
							<option value="" selected="selected">--请选择DTU设备--</option>
							<c:forEach items="${dtuDevs}" var="dtuDev">
								<option value="${dtuDev.id }">${dtuDev.id }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap">顺序号</td>
					<td class="td_sty"><input type="text" id="orderNo" name="orderNo" class="required number input_text" /></td>
				</tr>
				<tr>
					<td nowrap="nowrap">控制设备名称</td>
					<td class="td_sty"><input id="name" name="name" type="text" class="required input_text" /></td>
				</tr>
				<tr>
					<td nowrap="nowrap">所属水位计</td>
					<td class="td_sty">
						<select name="wlgIndex" id="wlgIndex">
							<option value="1">田块1水位计</option>
							<option value="2">田块2水位计</option>
							<option value="3">田块3水位计</option>
							<option value="4">田块4水位计</option>
						</select>
					</td>
				</tr>

			</table>
		</form>
	</div>
	<div class="cr">
		<div class="save">
			<img src="${ctx}/images/faultDiag/save_button.png" onclick="changeState(this);" />
		</div>
		<div class="return">
			<img src="${ctx}/images/faultDiag/return_button.png" onclick="javascript:history.back();" />
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	var validate;
	$(document).ready(function() {
		validate = $("#myForm").validate({});
	});
	
	function changeState(obj){
		if(validate.form()){
			$("#myForm").submit();
			$(obj).attr("disabled","disabled");
		}
	}
</script>
