 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>历史数据分析</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${ctx }/css/faultDiag/style.css">
	<script type="text/javascript" src="${ctx }/js/jquery-1.10.2.min.js"></script>
	<link href="${ctx }/js/date/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx }/js/date/WdatePicker.js"></script>
	<style type="text/css">
		html,body{
			height:100%;
			}
		html{
			overflow:hidden;
			}
		body{
			overflow:auto;
			background-color:#fbfbfb;
			}
		*{
			font-family: "宋体";
			font-size: 12px;
			color: #000;
		}
		a{
			font-family: "宋体";
			font-size: 12px;
			color: #000;
			text-decoration: none;
		}
		a:HOVER {
			color: #166535;
		}
	</style>
  </head>
  
 <body style="overflow:auto; background-color:#fbfbfb;">
<div class="address">
    <div class="space_left">&nbsp;</div>
    <div class="house"><img src="${ctx}/images/faultDiag/house.png"></div>
    <div class="address_infor">当前位置：<a href="#" onFocus="blur()">历史数据分析</a></div>
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
    	<form action="${ctx }/admin/faultDiag/user/list" method="post" id="findForm">
	        <div class="list_title">
	            <div class="st_title" style="width: 80px;">查询场景</div>
	            <div class="st_text" style="width: 240px;">
	            	<select id="group">
			        	<c:forEach items="${sceneGroups }" var="group">
			            	<option value="${group.value }">${group.title }</option>
			            </c:forEach>
			        	</select>
			        	
			        	<select id="scene" name="scene.id">
		            </select>
	            </div>
	            <div class="key" style="width: 80px;">选择时间</div>
	            <div class="txt">
	            	<input style="width: 75px;" id="beginTime" type="text" onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'endTime\',{y:-1});}'})">-
	  				<input style="width: 75px;" id="endTime" type="text" onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'endTime\',{y:-1});}'})">
	            </div>
	            <div class="button"><a href="javascript:loadCharts(1);" onfocus="blur()"><img src="${ctx}/images/faultDiag/search_button.png" /></a></div>
	            <input id="sceneId" type="hidden" value="">
	        </div>
        </form>
        <div class="list_table">
            <table id="container" width="98%" align="center" border="0" cellpadding="0" cellspacing="0" class="tab">
              <tr class="th_sty">
              	<td colspan="2">
                	<div style="border: 1 solid #BBD1FA;top:1px; left:-3px; width: 100%; text-align: center;">请选择查询条件</div>
                </td>
	       	  </tr>
            </table>
        </div>
    </div>
    <div id="page" style="clear:both; height:15px; line-height:15px;">
    </div>
    
</div>
</body>
</html>
<script type="text/javascript" src="${ctx }/js/dataformat.js"></script>
<script type="text/javascript">
<!--
	$(document).ready(function() {
		var w = $(window).width();
		if($.support.boxModel){
			$(".list_table table").css("width",w-20);
		}else{
			if(w > 0){
				$(".list_table table").css("width",w-20);
			}else{
				$(".list_table table").css("width","96%");
				var ltw1 = $(".list_table").width();
				$(".address").css("width",ltw1+20);
			}
		}
		$(window).resize(function() {
			var width = $(window).width();
			if($.support.boxModel){
				$(".list_table table").css("width",width-20);
			}else{
				if(width > 0){
					$(".list_table table").css("width",width-20);
				}else{
					$(".list_table table").css("width","96%");
					var ltw = $(".list_table").width();
					$(".address").css("width",ltw+20);
				}
			}
		});
		
		$("#group").change(function(){
			var group = $(this).val();
			$.post("${ctx}/admin/findSceneByGroup",{group:group},function(result){
				var html = "";
				if(result.length > 0){
					for(var i=0;i<result.length;i++){
						html += "<option value=\""+result[i].id+"\">"+result[i].name+"</option>";
					}
				}
				$("#scene").html(html);
			},"json");
		});
		$("#group").change();
	});
	
	function gotoPage(pageNo){
		loadCharts(pageNo);
	}
	//加载数据
	function loadCharts(pageNo){		
		var id = $("#scene :selected").val();		
		var beginTime = document.getElementById("beginTime").value;
		var endTime = document.getElementById("endTime").value;	
		if(beginTime.length == 0 || endTime.length == 0){
			alert("请选择查询时间");
			return;
		}
		
		$("#container div").html("正在加载数据，请等待...");
		$.post("${ctx}/admin/findDateQueryBySceneId",{id:id,beginTime:beginTime,endTime:endTime,pageNo:pageNo,random:Math.random()},
		function(data){
			var pageHtml = "当前第"+data.page.pageNo+"页，共"+data.page.totalPages+"页&nbsp;&nbsp;";
			pageHtml += "<a href=\"javascript:gotoPage(1)\">首&nbsp;&nbsp;页</a>&nbsp;&nbsp;<a href=\"javascript:gotoPage("+data.page.prePage+")\">上一页</a>&nbsp;&nbsp;";
			pageHtml += "<a href=\"javascript:gotoPage("+data.page.nextPage+")\">下一页</a>&nbsp;&nbsp;";
			pageHtml += "<a href=\"javascript:gotoPage("+data.page.totalPages+")\">尾页</a>";
			document.getElementById("page").innerHTML = pageHtml;
			var inner = "";
			inner += "<tr class=\"th_sty\">";
			inner += "<td nowrap=\"nowrap\" style=\"width: 150px;\">采集时间</td>";
			var allData = data.allData;
			for(var i = 0;i < allData.length;i++){
				inner += "<td nowrap=\"nowrap\">"+allData[i].name+"</td>";
			}
			inner += "</tr>";
			if(allData.length>0){	    
				var length = allData[0].data.length;
				for(var i = 0;i < length;i++){
					inner += "<tr align=\"center\">";
					inner += "<td style=\"width: 150px;\">"+allData[0].data[i][0]+"</td>";
					for(var j = 0;j < allData.length;j++){
						inner += "<td>"+parseInt(allData[j].data[i][1]).toFixed(2)+"</td>";
					}
					inner += "</tr>";
				}
			}
			else{
				$("#container div").html("没有找到指定的数据");
				return;
			}
			document.getElementById("container").innerHTML = "";
			document.getElementById("container").innerHTML = inner;	
		},"json");
	}
	
	function now(){
	  var date = new Date();
	 var dStr = date.format();
	 $(".time").html(dStr+"&nbsp;&nbsp;&nbsp;&nbsp;");
	}
	setInterval(now, 1000);
//-->
</script>