
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>故障信息列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${ctx }/css/faultDiag/style.css">
<script type="text/javascript" src="${ctx }/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/admin.js"></script>
<script type="text/javascript" src="${ctx }/js/date/WdatePicker.js"></script>
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
			当前位置：<a href="#" onFocus="blur()">故障信息管理</a>
		</div>
		<div class="time">&nbsp;&nbsp;&nbsp;&nbsp;</div>
	</div>
	<div class="list">
		<div class="list_case">
			<form action="${ctx }/admin/faultDiag/faultInfo/list" method="post" id="findForm">
				<div class="list_title">
					<div class="st_title">请选择</div>
					<div class="st_text">
						<select name="selDtu" id="selDtu">
							<option value="" selected="selected">--所属DTU--</option>
							<c:forEach items="${dtuDevs}" var="dtuDev">
								<option value="${dtuDev.id }" <c:if test="${selDtu==dtuDev.id}">selected</c:if>>${dtuDev.id }</option>
							</c:forEach>
						</select>
						&nbsp;&nbsp;
						开始日期:
						<input name="beginDate" value="${beginDate}" onFocus="WdatePicker({isShowClear:false,isShowWeek:true,readOnly:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="Wdate"/>
						&nbsp; - 
						结束日期:
						<input name="endDate" value="${endDate}" onFocus="WdatePicker({isShowClear:false,isShowWeek:true,readOnly:true,skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="Wdate"/>
	            		&nbsp;&nbsp;
					</div>
					<div class="buttonr">
						<a href="javascript:gotoPage(0);" onfocus="blur()"><img src="${ctx}/images/faultDiag/search_button.png" /></a>
					</div>
					<input type="hidden" name="page.page" id="pageNo" value="${page.number+1}" />
				</div>
			</form>
			<div class="list_table">
				<form action="${ctx }/admin/faultDiag/faultInfo/list" id="myForm" method="post">
					<table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" class="tab">
						<tr class="th_sty">
							<td nowrap="nowrap">DTU编号</td>
							<td nowrap="nowrap">故障对象</td>
							<td nowrap="nowrap">故障描述</td>
							<td nowrap="nowrap">故障等级</td>
							<td nowrap="nowrap">发生故障时间</td>
						</tr>
						<c:forEach items="${page.content}" var="faultInfo">
							<tr>
								<td>${faultInfo.dtuDevId }&nbsp;</td>
								<td>${faultInfo.objectStr }&nbsp;</td>
								<td>${faultInfo.desc }&nbsp;</td>
								<td>${faultInfo.gradeStr }&nbsp;</td>
								<td>${faultInfo.occurTime }&nbsp;</td>
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
	function gotoPage(pageNo) {
		$("#pageNo").val(pageNo);
		$("#findForm").submit();
	}

//-->
</script>
