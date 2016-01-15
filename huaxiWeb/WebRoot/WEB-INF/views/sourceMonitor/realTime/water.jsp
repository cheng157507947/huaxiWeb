<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>实时监控</title>
</STYLE><LINK href="${ctx }/css/comm/css.css" type="text/css" rel="stylesheet">
</STYLE><LINK href="${ctx }/css/sourceMonitor/main.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/realTime.js"></script>
<script type="text/javascript" src="${ctx }/js/dispense/dispense-sm.js"></script>
<script type="text/javascript">
var ctx = "${ctx }";
$(document).ready(function(){
	titleInfoResultSet(${titleInfo});
	otherInfoResultSet(${otherInfo});
	realTimeDataResultSet(${realTimeData});
});
function execute(id,name,type,group){
	closeVideo();  //关闭视频
	titleInfo(id,name,group); //头部标题
	otherInfo(id);  //视频数据（其他信息）
	realTimeData(id); //实时数据
	
	$("#dataBtn").attr("href","javascript:showRealTimeData('"+id+"');");
	$("#contrBtn").attr("href","javascript:showControlDevs('"+id+"');");
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
<input type="hidden" value="2" id="hid_type"/>
<div class="main">
<div class="main-left">
     <div class="cjd">
          <div class="bk-bt"><span id="leftTitle">${title }</span></div>
          <div class="cjdtp"><img src="${ctx }/images/sourceMonitor/cjd.jpg"/></div>
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
                <span class="btxxk-xz"><a id="dataBtn" href="#');">实时监控</a></span>
               <!-- <span class="btxxk-fxz"><a id="contrBtn" href="javascript:showControlDevs('${id }');">控制设备</a></span> -->
               <div class="bjzt">DTU设备状态：<span id="online">在线 <img src="${ctx }/images/sourceMonitor/online-green.gif"/></span> <span id="commTime">2015-04-26 23:40</span></div>
          </div>
          <div class="alarmMsg"></div>
          <!-- 实时数据 -->
          <ul class="qxzbul">
               <li id="waterData1">
                    <div class="flash">
					<img src="${ctx }/images/sourceMonitor/icon-bg.png"/>
                    <div class="cssz">26米</div>
					</div>
                    <div class="qxzb"><img src="${ctx }/images/sourceMonitor/qx-icon10.gif"/></div>
                    <div class="qxzbwz">源水PH</div>
               </li>
               <li id="waterData2">
                    <div class="flash">
					<img src="${ctx }/images/sourceMonitor/icon-bg.png"/>
                    <div class="cssz">26米</div>
					</div>
                    <div class="qxzb"><img src="${ctx }/images/sourceMonitor/qx-icon3.gif"/></div>
                    <div class="qxzbwz">源水浊度</div>
               </li>
          </ul>
           <!-- 实时数据 结束 -->
     </div>
</div>
</div>
</body>
</html>
