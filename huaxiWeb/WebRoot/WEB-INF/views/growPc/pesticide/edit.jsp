<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>农药记录</title>
</STYLE><LINK href="${ctx }/css/comm/css.css" type="text/css" rel="stylesheet">
</STYLE><LINK href="${ctx }/css/growPc/main.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/js/jquery-1.10.2.min.js"></script>
<link href="${ctx }/js/date/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/date/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx }/js/validate/jquery.metadata.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx }/js/validate/jquery.validate.css"/>
<script type="text/javascript" src="${ctx }/js/validate/jquery.validate.js"></script> 
<script type="text/javascript">
var validate;
$(document).ready(function() {
	validate = $("#myForm").validate({});
});
function submitForm(){
	if(validate.form()){
		$("#myForm").submit();
	}
}
function goBlack(){
	history.go(-1);
}
</script>
</head>
	
<body style="min-width:1250px;
 width:expression_r(document.body.clientWidth < 1250? "1250px": "auto" ); }
">
<div class="main">
    
     <div class="ssjk">
          <div class="bk-bt">
               <span>农药记录编辑</span>
          </div>
          <form id="myForm" name="myForm" action="${ctx}/admin/growPc/pesticidesRecord/save" method="post" >
          <input type="hidden" name="id" value="${pesticidesRecord.id }"/>
          <table cellpadding="0" cellspacing="0" class="tab01" id="table01">
               <tr>
                    <th style="width: 100%" colspan="3">
                    	<span style="float: left; padding-left: 30px;color: red; font-weight: normal;"></span>
                    	<input style="float: right; margin-right: 30px;" type="button" class="btn" value="返回" onclick="goBlack();"/>
                    	<input style="float: right; margin-right: 30px;" type="button" class="btn" value="保存" onclick="submitForm();"/>
                    </th>
               </tr>
		       <tr>
		       		<td width="20%">农药名称：</td>
		  			<td width="80%" class="addTd">
		  				<input class="required" name="name" type="text" value="${pesticidesRecord.name }"/>
					</td>
				</tr>
				<tr>
		       		<td>农药施用量：</td>
		  			<td class="addTd">
		  				<input class="required number" name="amount" type="text" value="${pesticidesRecord.amount }"/>
					</td>
				</tr>
				<tr>
		       		<td>农药单位：</td>
		  			<td class="addTd">
		  				<input class="required" name="unit" type="text" value="${pesticidesRecord.unit }"/>
					</td>
				</tr>
				<tr>
					<td>施用时间：</td>
		  			<td class="addTd">
		  				<input class="required" name="date" type="text" onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" 
		  				value="<fmt:formatDate value="${pesticidesRecord.date }" pattern="yyyy-MM-dd"></fmt:formatDate>"/>
		  				&nbsp;请配合预测图片采集时间填入时间农药施用时间。
					</td>
				</tr>
				<tr>
					<td>备注：</td>
		  			<td class="addTd">
		  				<input name="desc" style="width: 300px;" value="${pesticidesRecord.desc }"/>
					</td>
				</tr>
		       <tr>
                    <th style="width: 100%" colspan="3">
                    	<input style="float: right; margin-right: 30px;" type="button" class="btn" value="返回" onclick="goBlack();"/>
                    	<input style="float: right; margin-right: 30px;" type="button" class="btn" value="保存" onclick="submitForm();"/>
                    </th>
               </tr>
          </table>
     </div>
</div>
</body>
</html>