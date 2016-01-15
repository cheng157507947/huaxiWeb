<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>编辑视频设备</title>
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
			当前位置：<a href="#" onFocus="blur()">编辑视频设备</a>
		</div>
		<div class="time">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
	</div>
	<div class="list">
		<form action="${ctx }/admin/faultDiag/videoDev/save" method="post" id="myForm">
			<input id="id" name="id" type="hidden" value="${videoDev.id }"/>
			<table border="0" align="center" cellpadding="0" cellspacing="0" class="tab_sty">
				<tr>
					<td nowrap="nowrap" width="200px;">所属场景</td>
					<td class="td_sty">
						<select id="group" class="required" >
							<c:forEach items="${sceneGroups }" var="group">
								<option value="${group.value }">${group.title }</option>
							</c:forEach>
						</select> 
						<select id="scene" name="scene.id" class="required">
							<c:forEach items="${scenes }" var="scene">
								<option value="${scene.id }">${scene.name }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
		        <td nowrap="nowrap">设备名称</td>
			        <td class="td_sty">
			        	<input type="text" id="name" name="name" value="${videoDev.name }" class="required input_text"/>
			        </td>
	      		</tr>
	      		<td nowrap="nowrap">设备类型</td>
			        <td class="td_sty">
			        	<select name="type" id="type">
			        		<option value="0">球机</option>
			        		<option value="1">枪机</option>
			        	</select>
			        </td>
	      		</tr>
	      		<td nowrap="nowrap">IP地址</td>
			        <td class="td_sty">
			        	<input type="text" id="ip" name="ip" value="${videoDev.ip }" class="required input_text"/>
			        </td>
	      		</tr>
	      		<td nowrap="nowrap">端口</td>
			        <td class="td_sty">
			        	<input type="text" id="port" name="port" value="${videoDev.port }" class="required digits input_text"/>
			        </td>
	      		</tr>
	      		<td nowrap="nowrap">用户名</td>
			        <td class="td_sty">
			        	<input type="text" id="username" name="username" value="${videoDev.username }" class="required input_text"/>
			        </td>
	      		</tr>
	      		<td nowrap="nowrap">密码</td>
			        <td class="td_sty">
			        	<input type="text" id="password" name="password" value="${videoDev.password }" class="required input_text"/>
			        </td>
	      		</tr>
	      		<td nowrap="nowrap">视频GUID</td>
			        <td class="td_sty">
			        	<input type="text" id="guid" name="guid" value="${videoDev.guid }" class="required input_text"/>
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
<script type="text/javascript" src="${ctx }/js/dataformat.js"></script>
<script type="text/javascript">
	var validate;
	$(document).ready(function() {
		validate = $("#myForm").validate({});
		$("#group").change(function() {
			var group = $(this).val();
			$.post("${ctx}/admin/faultDiag/scene/findSceneByGroup", {
				group : group
			}, function(result) {
				var html = "";
				if (result.length > 0) {
					for (var i = 0; i < result.length; i++) {
						html += "<option value=\""+result[i].id+"\">" + result[i].name + "</option>";
					}
				}
				$("#scene").html(html);
			}, "json");
		});
		$("#group").val("${videoDev.scene.group.value}");
		$("#scene").val("${videoDev.scene.id}");
		$("#type").val("${videoDev.type}");
	});

	function changeState(obj) {
		if (validate.form()) {
			$("#myForm").submit();
			$(obj).attr("disabled", "disabled");
		}
	}
</script>
