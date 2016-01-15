<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>录像回放</title>
</STYLE><LINK href="${ctx }/css/comm/css.css" type="text/css" rel="stylesheet">
</STYLE><LINK href="${ctx }/css/videoMonitor/main.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript"> 
$(document).ready(function(){
	var date = new Date();
	$("#TextQueryDate").val(date.getFullYear()+"-"+Add0(date.getMonth()+1)+"-"+Add0(date.getDate()));
	IP_CAM1.ResetTreePos(0,0);
	IP_CAM1.TreeListAreaView(0);
	IP_CAM1.RecordAreaView();
});
function showVideo(name,ip,port,username,pwd,guid,type){
	openVideo(name, ip, port, username, pwd, guid);
}
var nowGuid="";
//打开视频窗口
function openVideo(name,ip,port,loginName,pwd,guid){
	nowGuid = guid;
	var a = IP_CAM1.Login(ip,parseInt(port),loginName,pwd,0);
}
//关闭视频窗口
function closeVideo(){
}
function GUSelectChange()
{
	var GUID =  document.getElementById("GUSelect").value;
	var iRet = IP_CAM1.GetVODServerInfo(GUID);
	if (iRet == -1)
	{
		alert("查询VOD服务器失败！");	
	}
}
function PlayRecord()
{
	var GUID =  document.getElementById("GUSelect").value;
	var recordDate = document.getElementById("TextQueryDate").value;
	var startTime = document.getElementById("TextTimeStart").value;
	var endTime = document.getElementById("TextTimeEnd").value;
	var iRet = IP_CAM1.QueryPuRecordAndPlay(GUID, 2, recordDate, startTime, endTime, 1); 
	if (iRet == 0)
	{
	
	}
	else if (iRet = -2)
	{
		alert("时间段内没录像！");
	}
	else
	{
		alert("连续播放失败！");
	}
}
function stopPlayRec()
{
	var iRet = IP_CAM1.StopPlayRecordFile();
	return;
}
function Add0(num){
	if(num<10 && (num+"").length<2){
		return "0"+num;
	}
	return num+"";
}
</script>
</head>

<body>
<input type="hidden" value="1" id="hid_type"/>
<div class="main">
<div class="main-left">
     <div class="ssjk">
          <div class="bk-bt-xxk">
               <span class="btxxk-xz">录像回放</span>
               <div class="bjzt">点击左侧视频树视频点选择右侧业务点和时间段开始回放视频<a href="${ctx }/admin/videoMonitor/video">实时</a></div>
          </div>
          <div class="cjdtp">
			<object classid="CLSID:A3151DD9-AE91-4D4E-B872-2212A43F6AA4" width="760" height="480" id="IP_CAM1">
			  <param name="wmode" value="transparent" />
			</object>
			<SCRIPT language="javascript" type="text/javascript" for="IP_CAM1" event="GetLoginInfo(flag)">
			//登陆事件
			if(flag == 1)
			{
				isLogin = 1;
				//获取业务列表
				IP_CAM1.GetAllVideoInfo();
				GUSelectChange();
			}
			else
			{
				isLogin = 0;
				alert("登陆失败");
			}
			</SCRIPT>
			<SCRIPT language="javascript" type="text/javascript" for="IP_CAM1" event = "VideoGuInfo(GUName, GUID, bState)">
				if(nowGuid == GUID){
					$("#GUSelect").html("");
					var GUName = "业务点:"+" " + GUName;
					var varItem = new Option(GUName, GUID);
					document.all.GUSelect.options.add(varItem);
				}
			</SCRIPT>
          </div>
     </div>
</div>

<div class="main-right">
     <div class="cjd">
          <div class="bk-bt"><span id="leftTitle">回放控制</span></div>
          <div class="cjdtp">
			<table class="playback">
				<tr id="VodPos">
				  <td colspan="2">视频业务点：</td>
				</tr>
			  	<tr>
				  <td colspan="2" valign="middle">
				  	<select name="GUSelect" class="select_style" id="GUSelect" onchange="GUSelectChange()">
				  	</select>
				  </td>
				</tr>
				<tr>
				   <td class="des">日期：</td>
				   <td class="content"><input name="TextQueryDate" type="text" id="TextQueryDate" style="width:80px;height:21px" value="2015-05-25" />
				   </td>
				</tr>
				<tr>
				  <td class="des">开始时间：</td>
				  <td class="content"><input name="TextTimeStart" type="text" id="TextTimeStart" style="width:80px;height:21px" value="00:00:00" />
				  </td>
				</tr>
				<tr>
				  <td class="des">结束时间：</td>
				  <td class="content"><input  name="TextTimeEnd" type="text" id="TextTimeEnd" style="width:80px;height:21px" value="23:59:59" />
				  </td>
				</tr>
				<tr>
				  	<td colspan="2" align="center">
					<input type="submit" style="height:25px; width:80px" name="Submit1032" value="录像播放" onclick="PlayRecord();"/>
					<input type="submit" style="height:25px; width:80px" name="Submit10322" value="停止回放" onclick="stopPlayRec();"/></td>
				</tr>
			</table>
          </div>
     </div>
</div>
</div>
</body>
</html>
