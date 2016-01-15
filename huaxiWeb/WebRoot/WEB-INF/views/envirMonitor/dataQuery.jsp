<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" style=" overflow-x:hidden;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<LINK href="${ctx }/css/comm/css.css" type="text/css" rel="stylesheet"/>
<LINK href="${ctx }/css/envirMonitor/main.css" type="text/css" rel="stylesheet"/>  
<script type="text/javascript" src="${ctx }/js/jquery-1.10.2.min.js"></script>
<link href="${ctx }/js/date/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/date/WdatePicker.js"></script>
<script type="text/javascript">
$(document).ready(function(){
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
	
	document.getElementById("container").innerHTML = "正在加载数据，请等待...";
	$.post("${ctx}/admin/findDateQueryBySceneId",{id:id,beginTime:beginTime,endTime:endTime,pageNo:pageNo,random:Math.random()},
	function(data){
		var pageHtml = "当前第"+data.page.pageNo+"页，共"+data.page.totalPages+"页&nbsp;&nbsp;";
		pageHtml += "<a href=\"javascript:gotoPage(1)\">首&nbsp;&nbsp;页</a>&nbsp;&nbsp;<a href=\"javascript:gotoPage("+data.page.prePage+")\">上一页</a>&nbsp;&nbsp;";
		pageHtml += "<a href=\"javascript:gotoPage("+data.page.nextPage+")\">下一页</a>&nbsp;&nbsp;";
		pageHtml += "<a href=\"javascript:gotoPage("+data.page.totalPages+")\">尾页</a>";
		document.getElementById("page").innerHTML = pageHtml;
		document.getElementById("container").innerHTML = "";
		var inner = "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:100%;\">";
		inner += "<tr align=\"center\">";
		inner += "<th style=\"width: 150px;\">采集时间</th>";
		var allData = data.allData;
		for(var i = 0;i < allData.length;i++){
			inner += "<th>"+allData[i].name+"</th>";
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
			inner = "没有找到指定的数据";
		}
		inner += "</table>";
		document.getElementById("container").innerHTML = inner;	
	},"json");
}

function dataExport(){
	var id = $("#scene :selected").val();		
	var beginTime = document.getElementById("beginTime").value;
	var endTime = document.getElementById("endTime").value;	
	if(beginTime.length == 0 || endTime.length == 0){
		alert("请选择查询时间");
		return;
	}
	
	$.post("${ctx}/admin/verificationDateQuery",{id:id,beginTime:beginTime,endTime:endTime,random:Math.random()},
	function(data){
		var is = data.message;
		if(is){
			var id = $("#scene :selected").val();		
			var beginTime = document.getElementById("beginTime").value;
			var endTime = document.getElementById("endTime").value;	
			if(beginTime.length != 0 || endTime.length != 0){
				window.location.href="${ctx}/admin/findDateQueryToExecl?id="+id+"&beginTime="+beginTime+"&endTime="+endTime;
			}
		}else{
			window.confirm("数据量过大，请减少通道数或者缩短时间范围!");
			return false;
		}
	},"json");
}
</script>
</head>
	
<body>
<div class="main">

     <div class="ssjk">
          <div class="bk-bt">
               <span>数据查询</span>
          </div>
          <table cellpadding="0" cellspacing="0" class="tab01">
               <tr>
                    <td>选择查询场景</td>
                    <td>
                    	<select id="group">
			        	<c:forEach items="${sceneGroups }" var="group">
			            	<option value="${group.value }">${group.title }</option>
			            </c:forEach>
			        	</select>
			        	
			        	<select id="scene" name="scene.id">
			            </select>
					</td>
               </tr>
               <tr>
                    <td>选择时间</td>
                    <td>
	                    <input id="beginTime" type="text"  onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'endTime\',{y:-1});}'})" value="${dateView_beginTime }">-
		  				<input id="endTime" type="text" onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'endTime\',{y:-1});}'})" value="${dateView_endTime }">
		  				<input type="button" onclick="loadCharts(1)" value="提交" style="height: 20px;">
		  				<input type="button" onclick="dataExport()" value="导出数据" style="height: 20px;">
		  				<input id="sceneId" type="hidden" value="">
					</td>
               </tr>
          </table>
          <table cellpadding="0" cellspacing="0" class="tab01">
               <tr>
                    <td colspan="2">
                    	<div id="container" style="border: 1 solid #BBD1FA;top:1px; left:-3px; width: 100%; text-align: center;">请选择查询条件</div>
                    </td>
               </tr>
               <tr align="center">
                    <td colspan="2">
                    	<div id="page">
  					
  						</div>
					</td>
               </tr>
          </table>
     </div>
</div>
</body>
</html>



