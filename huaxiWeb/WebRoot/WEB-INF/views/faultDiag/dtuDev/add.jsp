<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>添加设备</title>
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
    <div class="address_infor">当前位置：<a href="#" onFocus="blur()">添加设备</a></div>
    <div class="time">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
</div>
<div class="list">
	<form action="${ctx }/admin/faultDiag/dtuDev/save" method="post" id="myForm">
		<input id="config" name="config" type="hidden" value="2047"/>
	    <table border="0" align="center" cellpadding="0" cellspacing="0" class="tab_sty">
	      <tr>
	        <td nowrap="nowrap" width="200px;">设备ID</td>
	        <td class="td_sty">
	        	<input id="id" name="id" type="text" class="required input_text" />
	        </td>
	      </tr>
	      <tr>
	      	<td nowrap="nowrap">设备类型</td>
	        <td class="td_sty">
	        	<input type="radio" name="type" value="1" checked="checked"/> 田块
		        <input type="radio" name="type" value="2"/> 泵房+水质
		        <input type="radio" name="type" value="3"/> 泵房+气象
	        </td>
	      </tr>
      	<tr>
	        <td nowrap="nowrap">数据采集间隔</td>
	        <td class="td_sty">
	        	<input type="text" id="interval" name="interval" class="number input_text"/>(分钟)
	        </td>
      	</tr>
      	<!-- 
      	<tr>
	      	<td nowrap="nowrap">采集配置</td>
	        <td class="td_sty" id="config">
	        	<input type="checkbox" onclick="checkAll();" checked="checked"/> 全部采集<br/>
	        	<input type="checkbox" name="configs" value="1" checked="checked"/> 田块1水位
	        	<input type="checkbox" name="configs" value="2" checked="checked"/> 田块2水位
	        	<input type="checkbox" name="configs" value="3" checked="checked"/> 田块3水位
	        	<input type="checkbox" name="configs" value="4" checked="checked"/> 田块4水位
	        	<input type="checkbox" name="configs" value="5" checked="checked"/> 土壤含湿量
	        	<input type="checkbox" name="configs" value="6" checked="checked"/> 片区#1进水管瞬时流量
	        	<input type="checkbox" name="configs" value="7" checked="checked"/> 片区#1进水管累计流量
	        	<input type="checkbox" name="configs" value="8" checked="checked"/> 片区#2进水管瞬时流量
	        	<input type="checkbox" name="configs" value="9" checked="checked"/> 片区#2进水管累计流量
	        	<input type="checkbox" name="configs" value="10" checked="checked"/> 片区#3进水管瞬时流量
	        	<input type="checkbox" name="configs" value="11" checked="checked"/> 片区#3进水管累计流量
	        </td>
	     </tr>
	      -->
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

//更新采集配置
function changeConfig(obj){
	if($(obj).attr("checked") != "checked"){
		return;
	}
	var type = obj.value;
	if(type == 1){
		$("#config").html("<input type=\"checkbox\" onclick=\"checkAll();\" checked=\"checked\"/> 全部采集<br/>"+
	        	"+<input type=\"checkbox\" name=\"configs\" value=\"1\" checked=\"checked\"/> 田块1水位"+
	        	"+<input type=\"checkbox\" name=\"configs\" value=\"2\" checked=\"checked\"/> 田块2水位"+
	        	"+<input type=\"checkbox\" name=\"configs\" value=\"3\" checked=\"checked\"/> 田块3水位"+
	        	"+<input type=\"checkbox\" name=\"configs\" value=\"4\" checked=\"checked\"/> 田块4水位"+
	        	"+<input type=\"checkbox\" name=\"configs\" value=\"5\" checked=\"checked\"/> 土壤含湿量"+
	        	"+<input type=\"checkbox\" name=\"configs\" value=\"6\" checked=\"checked\"/> 片区#1进水管瞬时流量"+
	        	"+<input type=\"checkbox\" name=\"configs\" value=\"7\" checked=\"checked\"/> 片区#1进水管累计流量"+
	        	"+<input type=\"checkbox\" name=\"configs\" value=\"8\" checked=\"checked\"/> 片区#2进水管瞬时流量"+
	        	"+<input type=\"checkbox\" name=\"configs\" value=\"9\" checked=\"checked\"/> 片区#2进水管累计流量"+
	        	"+<input type=\"checkbox\" name=\"configs\" value=\"10\" checked=\"checked\"/> 片区#3进水管瞬时流量"+
	        	"+<input type=\"checkbox\" name=\"configs\" value=\"11\" checked=\"checked\"/> 片区#3进水管累计流量");
	}
	if(type == 2){
		$("#config").html("水源地水位，水泵出口压力，源水PH，源水浊度");
	}
	if(type == 3){
		$("#config").html("水源地水位，水泵出口压力，风速，风向，大气温度，大气湿度，照度，小时雨量，日雨量");
	}
}
</script>
