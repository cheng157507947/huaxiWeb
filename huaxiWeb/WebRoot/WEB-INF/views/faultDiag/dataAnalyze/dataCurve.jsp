 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>曲线分析</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="${ctx }/css/faultDiag/style.css">
	<script type="text/javascript" src="${ctx }/js/jquery-1.4.4.min.js"></script>
	<link href="${ctx }/js/date/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx }/js/date/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx }/js/highcharts/highcharts.js"></script>
	<script type="text/javascript" src="${ctx }/js/highcharts/exporting.js"></script>  
	<script type="text/javascript" src="${ctx }/js/lhgdialog/lhgdialog.min.js"></script>
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
    <div class="address_infor">当前位置：<a href="#" onFocus="blur()">曲线分析</a></div>
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
	            <div class="button"><a href="javascript:loadCharts();" onfocus="blur()"><img src="${ctx}/images/faultDiag/search_button.png" /></a></div>
	            <input id="sceneId" type="hidden" value="">
	        </div>
        </form>
        <div class="list_table">
            <table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" class="tab">
              <tr>
	          	<td id="td_container" style="width:100%;height:420px;position: relative;" >
					请选择查询条件
 				</td>
	       	  </tr>
            </table>
        </div>
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
	
	function now(){
		var date = new Date();
		var dStr = date.format();
		$(".time").html(dStr+"&nbsp;&nbsp;&nbsp;&nbsp;");
	}
	setInterval(now, 1000);
//-->
</script>