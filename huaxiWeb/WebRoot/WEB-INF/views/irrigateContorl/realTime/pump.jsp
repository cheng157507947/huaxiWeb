<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>实时监控</title>
</STYLE><LINK href="${ctx }/css/comm/css.css" type="text/css" rel="stylesheet">
</STYLE><LINK href="${ctx }/css/envirMonitor/main.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/realTime.js"></script>
<script type="text/javascript" src="${ctx }/js/dispense/dispense-ic.js"></script>
<script type="text/javascript">
var timer;
var ctx = "${ctx }";
var sceneId = "${id}";
$(document).ready(function(){
	IP_CAM.ResetTreePos(0,0);//树大小
	IP_CAM.VideoOne();//单画面
	//IP_CAM.ShowVideoTitle(0);//取消视频标签
	IP_CAM.SetVideoTitle("智慧农田灌溉控制系统");
	
	titleInfoResultSet(${titleInfo});
	otherInfoResultSet(${otherInfo});
	controlInfoResultSet(${controlInfo});
	
	$(".qxzbul").hide();
	timer = setInterval("updateControlSts('${id}');",5000);
});


function execute(id,name,type,group){
	closeVideo();  //关闭视频
	titleInfo(id,name,group); //头部标题
	otherInfo(id);  //视频数据（其他信息）
	showControlDevs(id)  //开始时显示控制数据
	
	$("#dataBtn").attr("href","javascript:showRealTimeData('"+id+"');");
	$("#contrBtn").attr("href","javascript:showControlDevs('"+id+"');");
}

//显示实时数据
function showRealTimeData(id){
	$(".alarmMsg").show();
	$(".dataTab").hide();
	$(".qxzbul").show();
	$("#dataBtn").parent().attr("class","btxxk-xz");
	$("#contrBtn").parent().attr("class","btxxk-fxz");
	
	realTimeData(id); //实时数据
	clearInterval(timer);  //关闭设备状态刷新
	clearControlIds();  //关闭指令状态刷新
	timer = null;
}
//显示实时控制列表
function showControlDevs(id){
	$(".alarmMsg").hide();
	$(".dataTab").show();
	$(".qxzbul").hide();
	$("#contrBtn").parent().attr("class","btxxk-xz");
	$("#dataBtn").parent().attr("class","btxxk-fxz");
	
	controlInfo(id);  //控制数据
	if(timer != null) return;
	timer = setInterval("updateControlSts('"+id+"');",10000);
}

var nowGuid = "";
//打开视频窗口
function openVideo(name,ip,port,loginName,pwd,guid,type){
	if(type == 0){
		name += "--球机";
	}
	if(type == 1){
		name += "--枪机";
	}
   	IP_CAM.Login(ip,parseInt(port),loginName,pwd,0);
   	//IP_CAM.SetVideoTitle(name);
   	nowGuid = guid;
}
//关闭视频窗口
function closeVideo(){
	IP_CAM.Logout();
}
function GetLoginInfoFunc(parameter){
	if(parameter == 1){
		IP_CAM.PlayRealVideo(nowGuid);
	}
}
</script>
</head>

<body>
<input type="hidden" value="2" id="hid_type"/>
<div class="main">
<div class="main-left">
     <div class="cjd">
          <div class="bk-bt"><span id="leftTitle">${title }</span></div>
          <div class="cjdtp">
			<object classid="CLSID:A3151DD9-AE91-4D4E-B872-2212A43F6AA4" width="100%" height="100%" id="IP_CAM">
		    	<param name="wmode" value="transparent" />
		    	<embed src="transparent" width="100%" height="100%"  wmode="transparent" ></embed>
		  	</object>
		  	<SCRIPT language="javascript" type="text/javascript" for="IP_CAM" event="GetLoginInfo(flag)">
				GetLoginInfoFunc(flag);
			</SCRIPT>
		  </div>
     </div>
     <div class="qtsj">
          <div class="bk-bt"><span>视频数据</span></div>
          <ul class="qtsj-ul" id="other">
               <li><span>监控摄像头1</span><a href="#">监控影像</a></li>
               <li><span>监控摄像头2</span><a href="#">监控影像</a></li>
               <li><span>监控摄像头3</span><a href="#">监控影像</a></li>
               <li><span>监控摄像头4</span><a href="#">监控影像</a></li>
               <li><span>监控摄像头5</span><a href="#">监控影像</a></li>
          </ul>
     </div>
</div>

<div class="main-right">
     <div class="ssjk">
          <div class="bk-bt-xxk">
                <span class="btxxk-fxz"><a id="dataBtn" href="javascript:showRealTimeData('${id }');">实时监控</a></span>
               <span class="btxxk-xz"><a id="contrBtn" href="javascript:showControlDevs('${id }');">控制设备</a></span>
               <div class="bjzt">DTU设备状态：<span id="online">在线 <img src="${ctx }/images/envirMonitor/online-green.gif"/></span> <span id="commTime">2015-04-26 23:40</span></div>
          </div>
          <div class="alarmMsg"></div>
          <!-- 实时数据 -->
          <ul class="qxzbul">
               <li id="pumpData1">
                    <div class="flash">
					<img src="${ctx }/images/envirMonitor/icon-bg.png"/>
                    <div class="cssz">26米</div>
					</div>
                    <div class="qxzb"><img src="${ctx }/images/envirMonitor/qx-icon5.png"/></div>
                    <div class="qxzbwz">水源地水位</div>
               </li>
               <li id="pumpData2">
                    <div class="flash">
					<img src="${ctx }/images/envirMonitor/icon-bg.png"/>
                    <div class="cssz">26米</div>
					</div>
                    <div class="qxzb"><img src="${ctx }/images/envirMonitor/qx-icon6.png"/></div>
                    <div class="qxzbwz">水泵出口压力</div>
               </li>
          </ul>
           <!-- 实时数据 结束 -->
           <!--控制列表-->
          <div class="dataTab" style="OVERFLOW-Y: auto; OVERFLOW-X:hidden;">
          	   <div class="title-div">
          	   	<input onClick="operateAllDev(1);" type="button" class="selbtn" value="全部开启"/>
          	   	<input onClick="operateAllDev(0);" type="button" class="noselbtn" value="全部关闭"/>
          	   </div>
               <table cellpadding="0" cellspacing="0" id="controlDev">
               </table>
          </div>
          <!--控制列表 结束-->
     </div>
</div>
</div>
</body>
</html>
