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
$(document).ready(function(){
	closeVideo();  //关闭视频
	titleInfoResultSet(${titleInfo});
	otherInfoResultSet(${otherInfo});
	realTimeDataResultSet(${realTimeData});
});
function execute(id,name,type,group){
	titleInfo(id,name,group); //头部标题
	otherInfo(id);  //视频数据（其他信息）
	realTimeData(id); //实时数据
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
<input type="hidden" value="4" id="hid_type"/>
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
               <span class="btxxk-xz"><a href="#">实时监控</a></span>
               <!-- <span class="btxxk-fxz"><a href="sskz.html">控制设备</a></span> -->
               <div class="bjzt">DTU设备状态：<span id="online">在线 <img src="${ctx }/images/envirMonitor/online-green.gif"/></span> <span id="commTime">2015-04-26 23:40</span></div>
          </div>
          <ul class="qxzbul">
               <li id="weaData1">
                    <div class="flash">
					<img src="${ctx }/images/envirMonitor/icon-bg.png"/>
                    <div class="cssz">26米</div>
					</div>
                    <div class="qxzb"><img src="${ctx }/images/envirMonitor/qx-icon5.gif"/></div>
                    <div class="qxzbwz">风速</div>
               </li>
               <li id="weaData2">
                    <div class="flash">
					<img src="${ctx }/images/envirMonitor/icon-bg.png"/>
                    <div class="cssz">26米</div>
					</div>
                    <div class="qxzb"><img src="${ctx }/images/envirMonitor/qx-icon5.gif"/></div>
                    <div class="qxzbwz">风向</div>
               </li>
               <li id="weaData3">
                    <div class="flash">
					<img src="${ctx }/images/envirMonitor/icon-bg.png"/>
                    <div class="cssz">26米</div>
					</div>
                    <div class="qxzb"><img src="${ctx }/images/envirMonitor/qx-icon2.gif"/></div>
                    <div class="qxzbwz">大气温度</div>
               </li>
               <li id="weaData4">
                    <div class="flash">
					<img src="${ctx }/images/envirMonitor/icon-bg.png"/>
                    <div class="cssz">26米</div>
					</div>
                    <div class="qxzb"><img src="${ctx }/images/envirMonitor/qx-icon1.gif"/></div>
                    <div class="qxzbwz">大气湿度</div>
               </li>
               <li id="weaData5">
                    <div class="flash">
					<img src="${ctx }/images/envirMonitor/icon-bg.png"/>
                    <div class="cssz">30%</div>
					</div>
                    <div class="qxzb"><img src="${ctx }/images/envirMonitor/qx-icon7.gif"/></div>
                    <div class="qxzbwz">照度</div>
               </li>
               <li id="weaData6">
                    <div class="flash">
					<img src="${ctx }/images/envirMonitor/icon-bg.png"/>
                    <div class="cssz">26m3/h</div>
					</div>
                    <div class="qxzb"><img src="${ctx }/images/envirMonitor/qx-icon8.gif"/></div>
                    <div class="qxzbwz">小时雨量</div>
               </li>
              <li id="weaData7">
                    <div class="flash">
					<img src="${ctx }/images/envirMonitor/icon-bg.png"/>
                    <div class="cssz">26m3</div>
					</div>
                    <div class="qxzb"><img src="${ctx }/images/envirMonitor/qx-icon8.gif"/></div>
                    <div class="qxzbwz">日雨量</div>
               </li>
          </ul>
     </div>
</div>
</div>
</body>
</html>
