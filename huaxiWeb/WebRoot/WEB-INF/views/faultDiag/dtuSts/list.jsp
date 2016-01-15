
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>设备状态列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${ctx }/css/faultDiag/style.css">
<script type="text/javascript" src="${ctx }/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/admin.js"></script>
<style type="text/css">
html,body {
	height: 100%;
}

html {
	overflow: hidden;
}

body {
	overflow: auto;
	background-color: #fbfbfb;
}
</style>
</head>

<body style="overflow:auto; background-color:#fbfbfb;">
	<div class="address">
		<div class="space_left">&nbsp;</div>
		<div class="house">
			<img src="${ctx}/images/faultDiag/house.png">
		</div>
		<div class="address_infor">
			当前位置：<a href="#" onFocus="blur()">设备状态管理</a>
		</div>
		<div class="time">&nbsp;&nbsp;&nbsp;&nbsp;</div>
	</div>
	<div class="list">
		<div class="list_case">
			<c:if test="${!empty messageOK}">
				<div class="flash notice">${messageOK }</div>
			</c:if>
			<c:if test="${!empty messageErr}">
				<div class="flash error">${messageErr }</div>
			</c:if>
			<form action="${ctx }/admin/faultDiag/dtuSts/list" method="post" id="findForm">
				<div class="list_title">
				
				</div>
			</form>
			<div class="list_table">
				<form action="${ctx }/admin/faultDiag/dtuSts/list" id="myForm" method="post">
					<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" class="tab">
						<tr class="th_sty">
							<td nowrap="nowrap">设备ID</td>
							<td nowrap="nowrap">在线状态</td>
							<td nowrap="nowrap">最近注册时间</td>
							<td nowrap="nowrap">最近通信时间</td>
							<td nowrap="nowrap">设备类型</td>
							<td nowrap="nowrap">传感器状态</td>
							<td nowrap="nowrap">DTU状态</td>
							<td nowrap="nowrap">状态描述</td>
						</tr>
						<c:forEach items="${dtuStsList}" var="dtuSts">
							<tr>
								<td>${dtuSts.id }&nbsp;</td>
								<td>${dtuSts.onlineStr }&nbsp;</td>
								<td>${dtuSts.regTime }&nbsp;</td>
								<td>${dtuSts.commTime }&nbsp;</td>
								<td>${dtuSts.typeStr }&nbsp;</td>
								<td>${dtuSts.senStsStr }&nbsp;</td>
								<td>${dtuSts.defStsStr }&nbsp;</td>
								<td>${dtuSts.statusDesc }&nbsp;</td>
							</tr>
						</c:forEach>
					</table>
				</form>
			</div>
		</div>
		<div style="clear:both; height:15px; line-height:15px;">
			
		</div>

	</div>
</body>
</html>
