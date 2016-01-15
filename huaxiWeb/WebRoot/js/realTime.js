//标题信息修改
function titleInfo(id,name,group){
	var leftTitle = "";
	if(group == 0){
		leftTitle = "华西南区-"+name;
	}else if(group == 1){
		leftTitle = "华西北区-"+name;
	}else{
		leftTitle = name;
	}
	$("#leftTitle").text(leftTitle);
	
	$.post(ctx+"/admin/findDtuDevAndStsBySceneId",{sceneId:id,random:Math.random()},function(result){
		titleInfoResultSet(result);
	},"json");
}

//视频信息修改（其他信息）
function otherInfo(id){
	$.post(ctx+"/admin/findVideoBySceneId",{sceneId:id,random:Math.random()},function(result){
		otherInfoResultSet(result);
	},"json");
}

//实时数据
function realTimeData(id){
	$.post(ctx+"/admin/findRealTimeDataBySceneId",{sceneId:id,random:Math.random()},function(result){
		realTimeDataResultSet(result);
	},"json");
}

//实时控制
function controlInfo(id){
	$.post(ctx+"/admin/findControlDevBySceneId",{sceneId:id,random:Math.random()},function(result){
		controlInfoResultSet(result);
	},"json");
}

//清空控制设备指令状态查询
function clearControlIds(){
	controlIds = "";
	clearInterval(controlOpTimer);
	controlOpTimer = null;
}

var controlIds = "";  //控制设备指令状态查询串
var controlOpTimer = null;
//设备开启
function openControlDev(controlId,btn){
	var opState = $(btn).parent().parent().prev().find("input").val();
	if(opState == 0 || opState == 1){
		alert("指令下发中，请稍后");
		return;
	}
	
	operateControlDev(controlId,1);
}

//设备关闭
function closeControlDev(controlId,btn){
	var opState = $(btn).parent().parent().prev().find("input").val();
	if(opState == 0 || opState == 1){
		alert("指令下发中，请稍后");
		return;
	}
	
	operateControlDev(controlId,0);
}

//点击全选
function selectAll(c){
	if(c.checked){
		$("[name='controlIdGroup']").attr("checked",true);
	}else{
		$("[name='controlIdGroup']").attr("checked",false);
	}
}

//批量控制
function operateAllDev(state){
	if($("[name='controlIdGroup']:checked").length <= 0){
		alert("请选择一个您要控制的设备！");
	} else{
		if (confirm("确定要批量控制吗？")){
			var controlIdArr = "";
			var all = $("[name='controlIdGroup']:checked"); 
			for(var i=0;i<all.length;i++){
				var control = all.eq(i);
				var opState = control.parent().next().next().next().find("input").val();
				if(opState == 0 || opState == 1){
					alert(control.parent().next().text()+"设备正在指令下发，无法加入批量控制");
					return;
				}
				controlIdArr += control.val()+",";
			}
			
			if(controlIdArr.length > 0){
				controlIdArr = controlIdArr.substring(0, controlIdArr.length-1);
			}
			operateControlDev(controlIdArr,state);
		}
	}
}

//控制设备操作处理
function operateControlDev(controlIdArr,state){
	$.post(ctx+"/admin/operateControlDev",{controlIds:controlIdArr,state:state,random:Math.random()},function(result){
		if(!result.ok){
			alert(result.errorMsg);
			return;
		}
		var idArray = result.controlIds;
		
		for(var i=0;i<idArray.length;i++){
			$("#"+idArray[i]).parent().next().html("<span style='color:;'>操作请求</span><input value=\"0\" type=\"hidden\"/>");
			
			if(controlIds.indexOf(idArray[i]) == -1){
				controlIds += idArray[i]+",";
			}
		}
		if(controlOpTimer == null){
			controlOpTimer = setInterval("updateControlOperate();",5000);
		}
		
	},"json");
}

//控制页面的设备状态刷新
function updateControlSts(sceneId){
	$.post(ctx+"/admin/findControlDevStsBySceneId",{sceneId:sceneId,random:Math.random()},function(result){
		for(var i=0;i<result.length;i++){
			var controlId = result[i].controlId;
			var state = result[i].state;
			
			var html = "";
			if(state == -1){
				html = "<span style='color:red;' id='"+controlId+"'>故障</span>";
			}
			if(state == 0){
				html = "<span style='color:;' id='"+controlId+"'>已关闭</span>";
			}
			if(state == 1){
				html = "<span style='color:;' id='"+controlId+"'>已开启</span>";
			}
			if(state == 2){
				html = "<span style='color:;' id='"+controlId+"'><image src=\""+ctx+"/images/envirMonitor/loading.gif\" style='width:20px;heigth:20px;'/>操作中</span>";
			}
			$("#"+controlId).parent().html(html);
		}
	},"json");
}

//控制页面的命令状态刷新
function updateControlOperate(){
	if(controlIds.length > 0){
		$.post(ctx+"/admin/findControlDevOperate",{controlIds:controlIds,random:Math.random()},function(result){
			for(var i=0;i<result.length;i++){
				var controlId = result[i].controlId;
				var state = result[i].state;
				
				var html = "";
				var over = true;
				if(state == -1){
					html = "<span style='color:red;'>出错</span>";
				}
				if(state == 0){
					html = "<span style='color:;'>操作请求</span>";
					over = false;
				}
				if(state == 1){
					html = "<span style='color:;'>命令下发</span>";
					over = false;
				}
				if(state == 2){
					html = "<span style='color:;'>命令下发完成</span>";
					over = false;
				}
				if(state == 3){
					html = "<span style='color:;'>成功</span>";
				}
				if(state == 4){
					html = "<span style='color:;'>失败</span>";
				}
				if(state == 5){
					html = "<span style='color:;'>超时</span>";
				}
				$("#"+controlId).parent().next().html(html+"<input value=\""+state+"\" type=\"hidden\"/>");
				
				if(over){
					controlIds.replace(controlId+",", "");
				}
			}
		},"json");
	}
}

//标题信息修改结果处理
function titleInfoResultSet(result){
	$("#online").html("离线 <img src=\""+ctx+"/images/envirMonitor/online-unknown.gif\"/>");
	$("#commTime").html("0000-00-00 00:00:00");
	
	var devId = result.devId;
	var devType = result.devType;
	var devConfig = result.devConfig;
	var online = result.online;
	var commTime = result.commTime;
	var defSts = result.defSts;
	
	var onlineHtml = "";
	if(online == 1){  //在线
		onlineHtml = "在线 <img src=\""+ctx+"/images/envirMonitor/online-green.gif\"/>";
	}else if(defSts == 0){  //故障
		onlineHtml = "故障 <img src=\""+ctx+"/images/envirMonitor/online-green.gif\"/>";
	}else{
		onlineHtml = "离线 <img src=\""+ctx+"/images/envirMonitor/online-unknown.gif\"/>";
	}
	var commTimeHtml = commTime;
	
	$("#online").html(onlineHtml);
	$("#commTime").html(commTimeHtml);
}
//视频信息修改（其他信息）结果处理
function otherInfoResultSet(result){
	var otherHtml = "";
	for(var i=0; result.length>0 && i<result.length; i++){
		var id = result[i].id;
		var name = result[i].name;
		var ip = result[i].ip;
		var port = result[i].port;
		var username = result[i].username;
		var password = result[i].password;
		var guid = result[i].guid;
		var type = result[i].type;
		
		otherHtml += "<li><span>"+name+"</span><a href=\"javascript:openVideo('"+name+"','"+ip+"','"+port+"','"+username+"','"+password+"','"+guid+"','"+type+"')\">监控影像</a></li>";
	}
	$("#other").html(otherHtml);
}
//实时数据结果处理
function realTimeDataResultSet(result){
	if(result.length <= 0){
		return;
	}
	var type = result.type;
	var collTime = result.collTime;
	var alarmMsg = "";
	
	$(".qxzbul li").hide();
	if(type == 1){  //田块数据
		alarmMsg = setFieldData(result);
	}else if(type == 2){  //泵房数据
		alarmMsg = setPumpData(result);
	}else if(type == 3){  //水质数据
		alarmMsg = setWaterData(result);
	}else if(type == 4){  //气象数据
		setWeatherData(result);
	}
	
	if(alarmMsg.length > 0){
		$(".alarmMsg").html("报警："+alarmMsg);
	}else{
		$(".alarmMsg").html("");
	}
}

//实时控制结果处理
function controlInfoResultSet(result){
	$("#controlDev").html("");
	var dtuId = result.dtuId;
	var controls = result.controls;
	var userType = result.userType;
	
	if(controls == undefined || controls.length <= 0) return;
	
	var controlHtml = "<tr><th width=\"5%\">全选&nbsp;<input name=\"selAll\" id=\"all\" type=\"checkbox\" onClick=\"selectAll(this)\" /></th>" +
			"<th width=\"20%\">阀门名称</th><th width=\"18%\">阀门状态</th><th width=\"18%\">指令状态</th><th width=\"40%\">阀门控制</th></tr>";
	for(var i=0;i<controls.length;i++){
		var id = controls[i].id;
		var name = controls[i].name;
		var wlgIndex = controls[i].wlgIndex;
		var state = controls[i].state;
		var crlTime = controls[i].crlTime;
		var defSts = controls[i].defSts;
		
		var stateText = "未知";
		var nowState = 0;
		if(defSts == 0){
			stateText = "<span style='color:red;' id='"+id+"'>故障</span>";
			nowState = -1;
		}else if(state == 1){
			stateText = "<span style='color:;' id='"+id+"'>已开启</span>";
			nowState = 1;
		}else if(state == 0){
			stateText = "<span style='color:;' id='"+id+"'>已关闭</span>";
			nowState = 0;
		}else if(state == 2){
			stateText = "<span style='color:;' id='"+id+"'><image src=\""+ctx+"/images/envirMonitor/loading.gif\" style='width:20px;heigth:20px;'/>操作中</span>";
			nowState = 2;
		}
		
		var btns = "<span class=\"btnSpan\"><input onClick=\"openControlDev('"+id+"',this);\" type=\"button\" class=\"selbtn\" value=\"开 启\"/></span>"+
        	"<span class=\"btnSpan\"><input onClick=\"closeControlDev('"+id+"',this);\" type=\"button\" class=\"noselbtn\" value=\"关 闭\"/></span>";
		if(userType == 0){  //一般用户，不能进行控制操作
			btns = "当前用户不能进行控制操作";
		}
		controlHtml += "<tr><td><input type=\"checkbox\" name=\"controlIdGroup\" value=\""+id+"\" /></td>" +
				"<td>"+name+"</td><td>"+stateText+"</td>"+
				"<td>&nbsp;<input value=\"3\" type=\"hidden\"/></td>"+
                "<td>"+btns+"</td>"+
                "</tr>";
	}
	$("#controlDev").html(controlHtml);
	
	checkRelateControl();//监测是否有关联控制
}

function setFieldData(result){
	var alarmMsg = "";
	if(result.fieldData1 != null){
		setRealTimeDateShow("fieldData1", result.fieldData1, "米", result.fieldData1State);
		alarmMsg += getAlarmMsg(result.fieldData1State, "田块1水位",1);
	}
	if(result.fieldData2 != null){
		setRealTimeDateShow("fieldData2", result.fieldData2, "米", result.fieldData2State);
		alarmMsg += getAlarmMsg(result.fieldData2State, "田块2水位",2);
	}
	if(result.fieldData3 != null){
		setRealTimeDateShow("fieldData3", result.fieldData3, "米", result.fieldData3State);
		alarmMsg += getAlarmMsg(result.fieldData3State, "田块3水位",3);
	}
	if(result.fieldData4 != null){
		setRealTimeDateShow("fieldData4", result.fieldData4, "米", result.fieldData4State);
		alarmMsg += getAlarmMsg(result.fieldData4State, "田块4水位",4);
	}
	if(result.fieldData5 != null){
		setRealTimeDateShow("fieldData5", result.fieldData5, "%", result.fieldData5State);
		alarmMsg += getAlarmMsg(result.fieldData5State, "土壤水分",null);
	}
	if(result.fieldData6 != null){
		setRealTimeDateShow("fieldData6", result.fieldData6, "m3/h", result.fieldData6State);
		alarmMsg += getAlarmMsg(result.fieldData6State, "片区1瞬时流量",null);
	}
	if(result.fieldData7 != null){
		setRealTimeDateShow("fieldData7", result.fieldData7, "m3", result.fieldData7State);
	}
	if(result.fieldData8 != null){
		setRealTimeDateShow("fieldData8", result.fieldData8, "m3/h", result.fieldData8State);
		alarmMsg += getAlarmMsg(result.fieldData8State, "片区2瞬时流量",null);
	}
	if(result.fieldData9 != null){
		setRealTimeDateShow("fieldData9", result.fieldData9, "m3", result.fieldData9State);
	}
	if(result.fieldData10 != null){
		setRealTimeDateShow("fieldData10", result.fieldData10, "m3/h", result.fieldData10State);
		alarmMsg += getAlarmMsg(result.fieldData10State, "片区3瞬时流量",null);
	}
	if(result.fieldData11 != null){
		setRealTimeDateShow("fieldData11", result.fieldData11, "m3", result.fieldData11State);
	}
	return alarmMsg;
}

function setPumpData(result){
	var alarmMsg = "";
	setRealTimeDateShow("pumpData1", result.pumpData1, "米", result.pumpData1State);
	alarmMsg += getAlarmMsg(result.pumpData1State, "水源地水位",null);
	setRealTimeDateShow("pumpData2", result.pumpData2, "MPa", result.pumpData2State);
	alarmMsg += getAlarmMsg(result.pumpData2State, "水泵出口压力",null);
	return alarmMsg;
}

function setWaterData(result){
	var alarmMsg = "";
	setRealTimeDateShow("waterData1", result.waterData1, "", result.waterData1State);
	alarmMsg += getAlarmMsg(result.waterData1State, "源水PH",null);
	setRealTimeDateShow("waterData2", result.waterData2, "NTU", result.waterData2State);
	alarmMsg += getAlarmMsg(result.waterData2State, "源水浊度",null);
	return alarmMsg;
}

function setWeatherData(result){
	if(result.weaData1 != null){
		setRealTimeDateShow("weaData1", result.weaData1, "米/秒", result.weaData1State);
	}
	if(result.weaData2 != null){
		setRealTimeDateShow("weaData2", result.weaData2, "°", result.weaData2State);
	}
	if(result.weaData3 != null){
		setRealTimeDateShow("weaData3", result.weaData3, "℃", result.weaData3State);
	}
	if(result.weaData4 != null){
		setRealTimeDateShow("weaData4", result.weaData4, "RH", result.weaData4State);
	}
	if(result.weaData5 != null){
		setRealTimeDateShow("weaData5", result.weaData5, "lux", result.weaData5State);
	}
	if(result.weaData6 != null){
		setRealTimeDateShow("weaData6", result.weaData6, "mm/h", result.weaData6State);
	}
	if(result.weaData7 != null){
		setRealTimeDateShow("weaData7", result.weaData7, "mm/天", result.weaData7State);
	}
}

function setRealTimeDateShow(dataTag,data,unit,dataState){
	var dataMsg = "异常";
	if(dataState != 3){
		dataMsg = parseInt(data).toFixed(2)+unit;
	}
	$("#"+dataTag).show();
	$("#"+dataTag+" .cssz").text(dataMsg);
}
function getAlarmMsg(dataState, dataName, controlIndex){
	var control = "";
	if(dataState == 1){
		if(controlIndex != null){
			control = "<a href='javascript:relateControl("+controlIndex+",0);'>[关联控制]</a>";
		}
		return dataName+"偏大"+control+"；";
	}else if(dataState == 2){
		if(controlIndex != null){
			control = "<a href='javascript:relateControl("+controlIndex+",1);'>[关联控制]</a>";
		}
		return dataName+"偏小"+control+"；";
	}else if(dataState == 3){
		return dataName+"数据异常；";
	}else{
		return "";
	}
}

var reConIds = null;
var reState = null;
//关联控制（数据下标，目标操作）
function relateControl(controlIndex, state){
	$.post(ctx+"/admin/findControlDevByControlIndex",{sceneId:sceneId,controlIndex:controlIndex,state:state,random:Math.random()},function(result){
		var sceneId = result.sceneId;
		var state = result.state;
		var cons = result.cons;
		
		if(cons.length == 0){
			alert("该采集数据无对应控制设备");
			return;
		}
		var conIds = new Array();
		var msg = "";
		for(var i=0;i<cons.length;i++){
			conIds[i] = cons[i].conId;
			msg += cons[i].name+","
		}
		msg.substring(0, msg.length-1);
		var operate = "开启";
		if(state == 0){
			operate = "关闭";
		}
		
		if(confirm("该采集数据对应控制设备为："+msg+"。确定要对这些设置执行'"+operate+"'操作吗？")){
			reConIds = conIds;
			reState = state;
			showControlDevs(sceneId);
			
		}
	},"json");
}

//监测是否有关联控制
function checkRelateControl(){
	var conIds = reConIds;
	var state = reState;
	if(conIds == null || state == null){
		return;
	}
	
	for(var i=0;i<conIds.length;i++){
		$("#controlDev input[value="+conIds[i]+"]").attr("checked","checked");
	}
	operateAllDev(state);
	
	reConIds = null;
	reState = null;
}