<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>实时监控</title>
</STYLE><LINK href="${ctx }/css/comm/css.css" type="text/css" rel="stylesheet">
</STYLE><LINK href="${ctx }/css/videoMonitor/main.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	IP_CAM.ResetTreePos(0,0);//树大小
	IP_CAM.VideoOne();//单画面
	IP_CAM.SetVideoTitle("田间视频监控系统");
	//IP_CAM.ShowVideoTitle(0);//取消视频标签
});
function showVideo(name,ip,port,username,pwd,guid,type){
	if(type == 0){
		name += "--球机";
		isControl(true);
	}
	if(type == 1){
		name += "--枪机";
		isControl(false);
	}
	openVideo(name, ip, port, username, pwd, guid);
}
var nowGuid="";
//打开视频窗口
function openVideo(name,ip,port,loginName,pwd,guid){
   	nowGuid = guid;
   	IP_CAM.Login(ip,parseInt(port),loginName,pwd,0);
   	IP_CAM.SetVideoTitle(name);
}
//关闭视频窗口
function closeVideo(){
	IP_CAM.Logout();
}
function GetLoginInfoFunc(parameter){
	//alert(nowGuid);
	if(parameter == 1){
		IP_CAM.PlayRealVideo(nowGuid);
	}
}
var iSpeed = 5; //云台控球速度
//云台控制
function PtzControlFunc(strCmd, bFlag)
{
	var varRet = IP_CAM.PtzControl(nowGuid, bFlag, strCmd, iSpeed*11);
}
function PTZScanFunc(strType)
{
	var varRet = IP_CAM.PTZScan(nowGuid, strType, iSpeed*11);
}
//控制判断
function isControl(tag){
	if(tag){ //可控
		$("#control input").removeAttr("disabled");
	}else{
		$("#control input").attr("disabled","disabled");
	}
}
</script>
</head>

<body>
<input type="hidden" value="1" id="guid"/>
<div class="main">
<div class="main-left">
     <div class="ssjk">
          <div class="bk-bt-xxk">
               <span class="btxxk-xz">监控视频</span>
               <div class="bjzt">点击左侧视频树显示更换当前显示视频<a href="${ctx }/admin/videoMonitor/playback">回放</a></div>
          </div>
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
</div>

<div class="main-right">
     <div class="cjd">
          <div class="bk-bt"><span id="leftTitle">视频控制</span></div>
          <div class="cjdtp">
          	<table id="control">
				<tr>
			        <td height="23" colspan="5" align="center">云台控球</td>
			        </tr>
			      <tr>
			        <td width="67" height="30"><label></label></td>
			        <td width="35"><input type="submit" style="height:35px; width:35px" name="Submit" value="左上" onmousedown="PtzControlFunc('TUPL',1);" onmouseup="PtzControlFunc('TUPL', 0);"/></td>
			        <td width="35" align=""><input type="submit" style="height:35px; width:35px" name="Submit2" value="向上" onMouseDown="PtzControlFunc('TU',1);" onMouseUp="PtzControlFunc('TU', 0);"/></td>
			        <td width="35" align=""><input type="submit" style="height:35px; width:35px" name="Submit3" value="右上" onmousedown="PtzControlFunc('TUPR',1);" onmouseup="PtzControlFunc('TUPR', 0);"/></td>
			        <td width="64">&nbsp;</td>
			      </tr>
			      <tr>
			        <td height="37">&nbsp;</td>
			        <td height="37"><input type="button" style="height:35px; width:35px" name="Submit4" value="向左" onmousedown="PtzControlFunc('PL',1);" onmouseup="PtzControlFunc('PL', 0);"/></td>
			        <td><input type="submit" style="height:35px; width:35px" name="ptzSpeed" id="ptzSpeed" value="5" onclick="onChangeSpeed();"/></td>
			        <td><input type="submit" style="height:35px; width:35px" name="Submit6" value="向右" onmousedown="PtzControlFunc('PR',1);" onmouseup="PtzControlFunc('PR', 0);"/></td>
			        <td>&nbsp;</td>
			      </tr>
			      <tr>
			        <td height="39">&nbsp;</td>
			        <td height="39"><input type="submit" style="height:35px; width:35px" name="Submit7" value="左下" onmousedown="PtzControlFunc('TDPL',1);" onmouseup="PtzControlFunc('TDPL', 0);"/></td>
			        <td><input type="submit" style="height:35px; width:35px" name="Submit8" value="向下" onmousedown="PtzControlFunc('TD',1);" onmouseup="PtzControlFunc('TD', 0);"/></td>
			        <td><input type="submit" style="height:35px; width:35px" name="Submit9" value="右下" onmousedown="PtzControlFunc('TDPR',1);" onmouseup="PtzControlFunc('TDPR', 0);"/></td>
			        <td>&nbsp;</td>
			      </tr>
			      <tr>
			        <td height="16" colspan="5">&nbsp;</td>
			        </tr>
			      <tr>
			        <td height="39">&nbsp;</td>
			        <td height="39"><input type="submit" style="height:35px; width:35px" name="Submit7223" value="－" onmousedown="PtzControlFunc('IO',1);" onmouseup="PtzControlFunc('IO', 0);"/></td>
			        <td align="center">光圈</td>
			        <td align="center"><input type="submit" style="height:35px; width:35px" name="Submit724" value="＋"  onmousedown="PtzControlFunc('IC',1);" onmouseup="PtzControlFunc('IC', 0);"/></td>
			        <td>&nbsp;</td>
			      </tr>
			      <tr>
			        <td height="39">&nbsp;</td>
			        <td height="39"><input type="submit" style="height:35px; width:35px" name="Submit7222" value="－"  onmousedown="PtzControlFunc('ZIN',1);" onmouseup="PtzControlFunc('ZIN', 0);"/></td>
			        <td align="center">缩放</td>
			        <td align="center"><input type="submit" style="height:35px; width:35px" name="Submit723" value="＋"  onmousedown="PtzControlFunc('ZOUT',1);" onmouseup="PtzControlFunc('ZOUT', 0);"/></td>
			        <td>&nbsp;</td>
			      </tr>
			      <tr>
			        <td height="39">&nbsp;</td>
			        <td height="39"><input type="submit" style="height:35px; width:35px" name="Submit722" value="－"  onmousedown="PtzControlFunc('FR',1);" onmouseup="PtzControlFunc('FR', 0);"/></td>
			        <td align="center">聚集</td>
			        <td align="center"><input type="submit" style="height:35px; width:35px" name="Submit72" value="＋"  onmousedown="PtzControlFunc('FN',1);" onmouseup="PtzControlFunc('FN', 0);"/></td>
			        <td>&nbsp;</td>
			      </tr>
			      <tr>
			        <td height="16" colspan="5">&nbsp;</td>
			        </tr>
			      <tr>
			        <td height="39">&nbsp;</td>
			        <td height="39"><input type="submit" style="height:35px; width:35px" name="Submit72232" value="开始" onclick="PTZScanFunc(1);"/></td>
			        <td align="center"><p>自动线扫</p>          </td>
			        <td align="center"><input type="submit" style="height:35px; width:35px" name="Submit72233" value="停止" onclick="PTZScanFunc(-1)"/></td>
			        <td>&nbsp;</td>
			      </tr>
			      <tr>
			        <td height="39">&nbsp;</td>
			        <td height="39"><input type="submit" style="height:35px; width:35px" name="Submit722322" value="开始" onclick="PTZScanFunc(2);"/></td>
			        <td align="center">随机线扫</td>
			        <td align="center"><input type="submit" style="height:35px; width:35px" name="Submit722332" value="停止" onclick="PTZScanFunc(-2)"/></td>
			        <td>&nbsp;</td>
			      </tr>
			</table>
          </div>
     </div>
</div>
</div>
</body>
</html>
