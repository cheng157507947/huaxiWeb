<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" style=" overflow-x:hidden;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>报警配置</title>
</STYLE><LINK href="${ctx }/css/comm/css.css" type="text/css" rel="stylesheet">
</STYLE><LINK href="${ctx }/css/irrigateContorl/main.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
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

jQuery.validator.addMethod("phones", function(value, element) {
	var phones = value.split(",");
	var ok = true;
	for(var i=0;i<phones.length;i++){
		if(!/^((1)+\d{10})$/.test(phones[i])){
			ok = false;
			break;
		}
	}
    return this.optional(element) || ok;
}, "手机号格式输入错误");   
jQuery.validator.addMethod("emails", function(value, element) {
	var emails = value.split(",");
	var ok = true;
	for(var i=0;i<emails.length;i++){
		if(!/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/.test(emails[i])){
			ok = false;
			break;
		}
	}
    return this.optional(element) || ok;
}, "邮箱格式输入错误");   
</script>
</head>
	
<body>
<div class="main">

     <div class="ssjk">
          <div class="bk-bt-xxk">
               <span class="btxxk-xz"><a href="#">报警配置</a></span>
          	   <div class="bjzt">该配置填写后将会提示报警信息，不需报警的数据请填空值</div>
          </div>
          <form id="myForm" name="myForm" action="${ctx}/admin/irrigateContorl/saveAlarmConfig" method="post" >
          <table cellpadding="0" cellspacing="0" class="tab01" id="table01">
               <tr>
                    <th style="width: 100%" colspan="3">
						<c:if test="${!empty okMsg }">
                    	<span style="float: left; padding-left: 30px;color:green;">${okMsg }</span>
          				</c:if>
          				<c:if test="${!empty errMsg }">
                    	<span style="float: left; padding-left: 30px;color: red;">${errMsg }</span>
          				</c:if>
                    	<input style="float: right; margin-right: 30px;" type="button" class="btn" value="保存" onclick="submitForm();"/>
                    </th>
               </tr>
		       <tr>
		       		<td colspan="3">
		       		<input type="hidden" name="id" value="${alarmConfig.id }"/>
					手机号：<input type="text" name="phones" style="width:150px;" class="phones" value="${alarmConfig.phones }"/>&nbsp;&nbsp;&nbsp;
					邮箱：<input type="text" name="emails" style="width:150px;" class="emails" value="${alarmConfig.emails }"/>&nbsp;&nbsp;&nbsp;
					通知间隔：<input type="text" name="interval" style="width:70px;" class="digits" value="${alarmConfig.interval }"/>分钟（不得小于15分钟）
					<div class="error">
					</div>
					</td>
		       </tr>
		       <th style="width: 100%" colspan="3">
	               	<span>田块数据报警配置</span>
               </th>
		       <tr>
		       		<td>田块水位报警配置</td>
		  			<td>
		  				最小值：<input name="waterLevelMin" class="number" value="${alarmConfig.waterLevelMin }"/>
		  				----
						最大值：<input name="waterLevelMax" class="number" value="${alarmConfig.waterLevelMax }"/>
					</td>
				</tr>
				<tr>
					<td>土壤水分报警配置</td>
		  			<td>
		  				最小值：<input name="moistureMin" class="number" value="${alarmConfig.moistureMin }"/>
		  				----
						最大值：<input name="moistureMax" class="number" value="${alarmConfig.moistureMax }"/>
					</td>
				</tr>
				<tr>
					<td>瞬时流量报警配置</td>
		  			<td>
		  				最小值：<input name="inFlowMin" class="number" value="${alarmConfig.inFlowMin }"/>
		  				----
						最大值：<input name="inFlowMax" class="number" value="${alarmConfig.inFlowMax }"/>
					</td>
				</tr>
				<th style="width: 100%" colspan="3">
	               	<span>泵房数据报警配置</span>
               	</th>
				<tr>
					<td>水源地水位报警配置</td>
		  			<td>
		  				最小值：<input name="sourceWLMin" class="number" value="${alarmConfig.sourceWLMin }"/>
		  				----
						最大值：<input name="sourceWLMax" class="number" value="${alarmConfig.sourceWLMax }"/>
					</td>
				</tr>
				<tr>
					<td>水泵出口压力报警配置</td>
		  			<td>
		  				最小值：<input name="outPressureMin" class="number" value="${alarmConfig.outPressureMin }"/>
		  				----
						最大值：<input name="outPressureMax" class="number" value="${alarmConfig.outPressureMax }"/>
					</td>
				</tr>
				<th style="width: 100%" colspan="3">
	               	<span>水源数据报警配置</span>
               	</th>
				<tr>
					<td>源水PH报警配置</td>
		  			<td>
		  				最小值：<input name="sourcePHMin" class="number" value="${alarmConfig.sourcePHMin }"/>
		  				----
						最大值：<input name="sourcePHMax" class="number" value="${alarmConfig.sourcePHMax }"/>
					</td>
				</tr>
				<tr>
					<td>源水浊度报警配置</td>
		  			<td>
		  				最小值：<input name="sourceNTUMin" class="number" value="${alarmConfig.sourceNTUMin }"/>
		  				----
						最大值：<input name="sourceNTUMax" class="number" value="${alarmConfig.sourceNTUMax }"/>
					</td>
		       </tr>
		       <tr>
                    <th style="width: 100%" colspan="3">
                    	<input style="float: right; margin-right: 30px;" type="button" class="btn" value="保存" onclick="submitForm();"/>
                    </th>
               </tr>
          </table>
          </form>
     </div>

</div>
</body>
</html>