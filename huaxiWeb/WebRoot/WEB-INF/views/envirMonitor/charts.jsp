<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" style=" overflow-x:hidden;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>曲线分析</title>
<LINK href="${ctx }/css/comm/css.css" type="text/css" rel="stylesheet"/>
<LINK href="${ctx }/css/envirMonitor/main.css" type="text/css" rel="stylesheet"/>  
<script type="text/javascript" src="${ctx }/js/jquery-1.4.4.min.js"></script>
<link href="${ctx }/js/date/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/date/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx }/js/highcharts/highcharts.js"></script>
<script type="text/javascript" src="${ctx }/js/highcharts/exporting.js"></script>  
<script type="text/javascript" src="${ctx }/js/lhgdialog/lhgdialog.min.js"></script>
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

function loadCharts(){
	var id = $("#scene :selected").val();
	var beginTime = document.getElementById("beginTime").value;
	var endTime = document.getElementById("endTime").value;
	prompt();
	$.ajax({
		url : "${ctx}/admin/findChartsBySceneId",
		type : "POST",
		dataType : "json",//xml,html,script,json
		data : {
			id : id,
			beginTime : beginTime,
			endTime : endTime
		},
		error : function() {
			closeWin();
		},
		success : function(json) {
		
			if (json.message != undefined) {
				alert(json.message);
				closeWin();
				return;
			}
			if (json.length < 1) {
				$("#td_container").html("没有查到数据……");
				closeWin();
				return;
			}
			
			chart = new Highcharts.Chart({
				chart: {
					renderTo: 'td_container',
					defaultSeriesType: 'spline'
				},
				title: {
					text: $("#group :selected").text()+"-"+$("#scene :selected").text()
				},
				subtitle: {
					text: ''
				},
				xAxis: {
					type: 'datetime'
				},
				yAxis: {
					title: {
						text: '当前值'
					},
					labels: {
						formatter: function() {
							return this.value //+'°'
						}
					}
				},
				tooltip: {
					crosshairs: true,
					formatter: function() {
						return Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +' ' + '监测值:<b>' + Highcharts.numberFormat(this.y, 2) + '</b>';
					}
				},
				plotOptions: {
					spline: {
						marker: {
							enabled: false,
							radius: 4,
							lineColor: '#666666',
							lineWidth: 1
						}
					}
				},
				series: json,
				credits: {
					enabled: false
				}
			});
			var h = document.getElementById("table01").height;
			$("ssjk").attr("style","height="+(h+100));
			closeWin();
		}
	});
}



var DG;
function prompt() {
	DG = $.dialog({
			max: false,
		    min: false,
			title:"提示",
			content: "url:${ctx}/admin/chartsMsg"
			});
}
function closeWin() {
	DG.close();
}
</script>   
</head>
	
<body>
<div class="main">
<input name="lbl_hid" style="display: none;"/>
     <div class="ssjk" id="ssjk">
          <div class="bk-bt">
               <span>曲线分析</span>
          </div>
          <table cellpadding="0" cellspacing="0" class="tab01" id="table01">
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
                    <td>起止时间</td>
                    <td>
                    	开始：<input type="text" id="beginTime" name="beginTime" onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'endTime\',{y:-1});}'})" value="${beginTime }" />
						结束：<input type="text" id="endTime" name="endTime" onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'beginTime\',{y:1});}'})" value="${endTime }" />
		  				<input type="button" style="height: 20px;" onclick="loadCharts()" value="提交">
					</td>
               </tr>
               <tr>
                    <td id="td_container" colspan="2" style="width:100%;height:450px;;position: relative;" >
						&nbsp;
  					</td>
               </tr>
          </table>
     </div>

</div>
</body>
</html>
