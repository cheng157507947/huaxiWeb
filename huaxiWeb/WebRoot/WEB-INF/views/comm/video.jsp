<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>窗口视频显示</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${ctx }/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/lhgdialog/lhgdialog.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
    function getDataMsg(){
    	IP_CAM.ResetTreePos(0,0);//树大小
		IP_CAM.VideoOne();//单画面
		IP_CAM.SetVideoTitle("视频监控");
		//IP_CAM.ShowVideoTitle(0);//取消视频标签
		
    	iret=IP_CAM.Login("${param.ip}",${param.port},"${param.loginName}","${param.pwd}",0);	
		//document.body.style.cursor = "wait";
    }
    
    function GetLoginInfoFunc(parameter)
	{
		if(parameter == 1)
		{
			IP_CAM.PlayRealVideo("${param.guid}");
		}
		else //0
		{
			
		}
	}
	
	function OnLeave()
	{
		IP_CAM.Logout();
	}
	</script>

  </head>
  
  <body onload="getDataMsg()" onunload="OnLeave()" style="margin: 0;padding: 0;text-align: center;">
  <div style="text-align: center;margin-left: 12px;">
   	<object classid="CLSID:A3151DD9-AE91-4D4E-B872-2212A43F6AA4" width="100%" height="100%" id="IP_CAM">
    	<param name="wmode" value="transparent" />
    	<embed src="transparent" width="100%" height="100%"  wmode="transparent" ></embed>
  	</object>
  </body>
  </div>
</html>
<SCRIPT language="javascript" type="text/javascript" for="IP_CAM" event="GetLoginInfo(flag)">
	GetLoginInfoFunc(flag);
</SCRIPT>
