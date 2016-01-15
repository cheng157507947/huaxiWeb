
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>控制设备列表</title>
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
			当前位置：<a href="#" onFocus="blur()">控制设备管理</a>
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
			<form action="${ctx }/admin/faultDiag/controlDev/list" method="post" id="findForm">
				<div class="list_title">
					<div class="st_title">请选择</div>
					<div class="st_text">
						<select name="selDtu" id="selDtu">
							<option value="" selected="selected">--所属DTU--</option>
							<c:forEach items="${dtuDevs}" var="dtuDev">
								<option value="${dtuDev.id }" <c:if test="${selDtu==dtuDev.id}">selected</c:if>>${dtuDev.id }</option>
							</c:forEach>
						</select>
					</div>
					<div class="button">
						<a href="javascript:gotoPage(0);" onfocus="blur()"><img src="${ctx}/images/faultDiag/search_button.png" /></a>
					</div>
					<div class="button">
						<a href="javascript:location.href='${ctx}/admin/faultDiag/controlDev/add';" onfocus="blur()"><img src="${ctx}/images/faultDiag/add_button.png" /></a>
					</div>
					<div class="button">
						<a href="javascript:del();" onfocus="blur()"><img src="${ctx}/images/faultDiag/del_button.png" /></a>
					</div>
					<input type="hidden" name="page.page" id="pageNo" value="${page.number+1}" />
				</div>
			</form>
			<div class="list_table">
				<form action="${ctx }/admin/faultDiag/controlDev/delete" id="myForm" method="post">
					<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" class="tab">
						<tr class="th_sty">
							<td nowrap="nowrap" width="10%">全选&nbsp;<input name="selAll" id="all" type="checkbox" onClick="selectAll(this)" /></td>
							<td nowrap="nowrap">控制设备编号</td>
							<td nowrap="nowrap">名称</td>
							<td nowrap="nowrap">所属DTU</td>
							<td nowrap="nowrap">顺序号</td>
							<td nowrap="nowrap">所属水位计</td>
							<td nowrap="nowrap">操作</td>
						</tr>
						<c:forEach items="${page.content }" var="controlDev">
							<tr>
								<td><input type="checkbox" name="idGroup" value="${controlDev.id }" /></td>
								<td>${controlDev.id }&nbsp;</td>
								<td>${controlDev.name }&nbsp;</td>
								<td>${controlDev.dtuDev.id }&nbsp;</td>
								<td>${controlDev.orderNo }&nbsp;</td>
								<td>水位${controlDev.wlgIndex }&nbsp;</td>
								<td><a href="${ctx }/admin/faultDiag/controlDev/edit/${controlDev.id}">编辑</a>&nbsp;</td>
							</tr>
						</c:forEach>
					</table>
				</form>
			</div>
		</div>
		<div style="clear:both; height:15px; line-height:15px;">
			<jsp:include page="../page.jsp"></jsp:include>
		</div>

	</div>
</body>
</html>
<script type="text/javascript">
<!--
	function selectAll(c) {
		if (c.checked) {
			$("[name='idGroup']").attr("checked", true);
		} else {
			$("[name='idGroup']").attr("checked", false);
		}
	}

	function del() {
		if ($("[name='idGroup']:checked").length <= 0) {
			alert("请选择一个您要删除的！");
		} else {
			if (confirm("确定要删除吗？")) {
				$("#myForm").submit();
			}
		}
	}

	function gotoPage(pageNo) {
		$("#pageNo").val(pageNo);
		$("#findForm").submit();
	}

//-->
</script>
