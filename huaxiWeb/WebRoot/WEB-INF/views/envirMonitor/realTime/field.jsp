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
<script type="text/javascript" src="${ctx }/js/dispense/dispense-em.js"></script>
<script type="text/javascript">
var ctx = "${ctx }";
var sceneId = "${id}";
$(document).ready(function(){
	closeVideo();  //关闭视频
	titleInfoResultSet(${titleInfo});
	otherInfoResultSet(${otherInfo});
	realTimeDataResultSet(${realTimeData});
	
	$(".dataTab").hide();
});
function execute(id,name,type,group){
	sceneId = id;
	closeVideo();  //关闭视频
	titleInfo(id,name,group); //头部标题
	otherInfo(id);  //视频数据（其他信息）
	showRealTimeData(id);  //开始时显示实时数据
	
	$("#dataBtn").attr("href","javascript:showRealTimeData('"+id+"');");
	$("#contrBtn").attr("href","javascript:showControlDevs('"+id+"');");
}

var timer;
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


//打开视频窗口
function openVideo(name,ip,port,loginName,pwd,guid){
	window.top.openVideo(name,ip,port,loginName,pwd,guid);
}
//关闭视频窗口
function closeVideo(){
	window.top.closeVideo();
}
</script>
</head>

<body>
<input type="hidden" value="1" id="hid_type"/>
<div class="main">
<div class="main-left">
     <div class="cjd">
          <div class="bk-bt"><span id="leftTitle">${title }</span></div>
          <div class="cjdtp"><img src="${ctx }/images/envirMonitor/cjd.jpg"/></div>
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
               <span class="btxxk-xz"><a id="dataBtn" href="javascript:showRealTimeData('${id }');">实时监控</a></span>
               <span class="btxxk-fxz"><a id="contrBtn" href="javascript:showControlDevs('${id }');">控制设备</a></span>
               <div class="bjzt">DTU设备状态：<span id="online">在线 <img src="${ctx }/images/envirMonitor/online-green.gif"/></span> <span id="commTime">2015-04-26 23:40</span></div>
          </div>
          <div class="alarmMsg"></div>
          <!-- 实时数据 -->
          <ul class="qxzbul">
               <li id="fieldData1">
                    <div class="flash">
					<img src="${ctx }/images/envirMonitor/icon-bg.png"/>
                    <div class="cssz">26米</div>
					</div>
                    <div class="qxzb"><img src="${ctx }/images/envirMonitor/qx-icon1.gif"/></div>
                    <div class="qxzbwz">田块1水位</div>
               </li>
               <li id="fieldData2">
                    <div class="flash">
					<img src="${ctx }/images/envirMonitor/icon-bg.png"/>
                    <div class="cssz">26米</div>
					</div>
                    <div class="qxzb"><img src="${ctx }/images/envirMonitor/qx-icon1.gif"/></div>
                    <div class="qxzbwz">田块2水位</div>
               </li>
               <li id="fieldData3">
                    <div class="flash">
					<img src="${ctx }/images/envirMonitor/icon-bg.png"/>
                    <div class="cssz">26米</div>
					</div>
                    <div class="qxzb"><img src="${ctx }/images/envirMonitor/qx-icon1.gif"/></div>
                    <div class="qxzbwz">田块3水位</div>
               </li>
               <li id="fieldData4">
                    <div class="flash">
					<img src="${ctx }/images/envirMonitor/icon-bg.png"/>
                    <div class="cssz">26米</div>
					</div>
                    <div class="qxzb"><img src="${ctx }/images/envirMonitor/qx-icon1.gif"/></div>
                    <div class="qxzbwz">田块4水位</div>
               </li>
               <li id="fieldData5">
                    <div class="flash">
					<img src="${ctx }/images/envirMonitor/icon-bg.png"/>
                    <div class="cssz">30%</div>
					</div>
                    <div class="qxzb"><img src="${ctx }/images/envirMonitor/qx-icon2.gif"/></div>
                    <div class="qxzbwz">土壤水分</div>
               </li>
               <li id="fieldData6">
                    <div class="flash">
					<img src="${ctx }/images/envirMonitor/icon-bg.png"/>
                    <div class="cssz">26m3/h</div>
					</div>
                    <div class="qxzb"><img src="${ctx }/images/envirMonitor/qx-icon3.gif"/></div>
                    <div class="qxzbwz">片区1瞬时流量</div>
               </li>
              <li id="fieldData7">
                    <div class="flash">
					<img src="${ctx }/images/envirMonitor/icon-bg.png"/>
                    <div class="cssz">26m3</div>
					</div>
                    <div class="qxzb"><img src="${ctx }/images/envirMonitor/qx-icon4.gif"/></div>
                    <div class="qxzbwz">片区1累计流量</div>
               </li>
             <li id="fieldData8">
                    <div class="flash">
					<img src="${ctx }/images/envirMonitor/icon-bg.png"/>
                    <div class="cssz">26m3/h</div>
					</div>
                    <div class="qxzb"><img src="${ctx }/images/envirMonitor/qx-icon3.gif"/></div>
                    <div class="qxzbwz">片区2瞬时流量</div>
               </li>
              <li id="fieldData9">
                    <div class="flash">
					<img src="${ctx }/images/envirMonitor/icon-bg.png"/>
                    <div class="cssz">26m3</div>
					</div>
                    <div class="qxzb"><img src="${ctx }/images/envirMonitor/qx-icon4.gif"/></div>
                    <div class="qxzbwz">片区2累计流量</div>
               </li>
             <li id="fieldData10">
                    <div class="flash">
					<img src="${ctx }/images/envirMonitor/icon-bg.png"/>
                    <div class="cssz">26m3/h</div>
					</div>
                    <div class="qxzb"><img src="${ctx }/images/envirMonitor/qx-icon3.gif"/></div>
                    <div class="qxzbwz">片区3瞬时流量</div>
               </li>
              <li id="fieldData11">
                    <div class="flash">
					<img src="${ctx }/images/envirMonitor/icon-bg.png"/>
                    <div class="cssz">26m3</div>
					</div>
                    <div class="qxzb"><img src="${ctx }/images/envirMonitor/qx-icon4.gif"/></div>
                    <div class="qxzbwz">片区3累计流量</div>
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
