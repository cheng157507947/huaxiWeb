<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统设置</title>
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
</script>
</head>
	
<body style="min-width:1250px;
 width:expression_r(document.body.clientWidth < 1250? "1250px": "auto" ); }
">
<div class="main">
    
     <div class="ssjk">
          <div class="bk-bt">
               <span>图片预测配置</span>
          </div>
          <form id="myForm" name="myForm" action="${ctx}/admin/growPc/setupSave" method="post" >
          <input type="hidden" name="id" value="${picPredictInfo.id }"/>
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
		       		<td width="30%">预测总亩数：</td>
		  			<td width="70%" style="text-align: left;">
		  				<input name="totalArea" class="setupInput number required" value="${picPredictInfo.totalArea }"/> 
		  				&nbsp;请填入需要预测产量的种植总亩数（单位亩）
					</td>
				</tr>
				<tr>
					<td>图片拍摄面积：</td>
		  			<td style="text-align: left;">
		  				<input name="area" class="setupInput digits required" value="${picPredictInfo.area }"/>
		  				&nbsp;请填入摄像头抓拍时全景图抓拍到的总面积，应大于10平米（单位平米）
					</td>
				</tr>
				<tr>
					<td>图片拍摄角度：</td>
		  			<td style="text-align: left;">
		  				<input name="angle" class="setupInput number" value="${picPredictInfo.angle }"/>
		  				&nbsp;请填入需摄像头抓拍时的拍摄角度（可为空）
					</td>
				</tr>
				<tr>
					<td>水稻种植时间：</td>
		  			<td style="text-align: left;">
		  				<input class="setupInput" name="plantTime" type="text" onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" 
		  				value="<fmt:formatDate value="${picPredictInfo.plantTime }" pattern="yyyy-MM-dd"></fmt:formatDate>">
		  				&nbsp;请填入预测水稻的种植时间（可为空）
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