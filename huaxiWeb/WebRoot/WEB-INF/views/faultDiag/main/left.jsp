<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>现代渔业物联网信息服务平台</title>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/faultDiag/style.css">
	<style type="text/css">
	html,body{
		height:100%;
		}
</style>
  </head>
  
<body style="overflow:auto;">
<div class="menu">
    <div class="menu_title"><img src="${ctx }/images/faultDiag/_05.png"></div>
    <div class="menu_area">
    	<ul class="tree">
            <li class="li_bg"><img src="${ctx }/images/faultDiag/li_title01.png" class="li_png"/><a href="javascript:blur(0);"><div class="first_ft">设备状态监视分析</div><div class="first_icon"><img src="${ctx }/images/faultDiag/li_show.png"></div></a>
                <div style="display:block;">
                    <div class="submenu"><a href="${ctx }/admin/faultDiag/dtuSts/list" target="iframe_height"><div class="second_txt">DTU设备状态</div></a></div>
                    <div class="submenu"><a href="${ctx }/admin/faultDiag/controlDevSts/list" target="iframe_height"><div class="second_txt">控制设备状态</div></a></div>
                </div>
            </li>
            <li class="li_bg"><img src="${ctx }/images/faultDiag/li_title01.png" class="li_png"/><a href="javascript:blur(1);"><div class="first_ft">设备数据监视分析</div><div class="first_icon"><img src="${ctx }/images/faultDiag/li_hide.png"></div></a>
                <div style="display:none;">
                	<div class="submenu"><a href="${ctx }/admin/faultDiag/dataAnalyze/hisData" target="iframe_height" ><div class="second_txt">历史数据分析</div></a></div>
                    <div class="submenu"><a href="${ctx }/admin/faultDiag/dataAnalyze/dataCurve" target="iframe_height" ><div class="second_txt">曲线分析</div></a></div>
                </div>
            </li>
			<li class="li_bg"><img src="${ctx }/images/faultDiag/li_title01.png" class="li_png"/><a href="javascript:blur(2);"><div class="first_ft">系统故障监视分析</div><div class="first_icon"><img src="${ctx }/images/faultDiag/li_hide.png"></div></a>
                <div style="display:none;">
                	<div class="submenu"><a href="${ctx }/admin/faultDiag/faultInfo/statistics" target="iframe_height" ><div class="second_txt">故障统计</div></a></div>
                    <div class="submenu"><a href="${ctx }/admin/faultDiag/faultInfo/list" target="iframe_height" ><div class="second_txt">故障信息查询</div></a></div>
                </div>
            </li>
			<li class="li_bg"><img src="${ctx }/images/faultDiag/li_title01.png" class="li_png"/><a href="javascript:blur(3);"><div class="first_ft">基础信息管理</div><div class="first_icon"><img src="${ctx }/images/faultDiag/li_hide.png"></div></a>
                <div style="display:none;">
                	<div class="submenu"><a href="${ctx }/admin/faultDiag/scene/list" target="iframe_height" ><div class="second_txt">场景管理</div></a></div>
                    <div class="submenu"><a href="${ctx }/admin/faultDiag/dtuDev/list" target="iframe_height" ><div class="second_txt">DTU设备管理</div></a></div>
					<div class="submenu"><a href="${ctx }/admin/faultDiag/controlDev/list" target="iframe_height" ><div class="second_txt">控制设备管理</div></a></div>
					<div class="submenu"><a href="${ctx }/admin/faultDiag/videoDev/list" target="iframe_height" ><div class="second_txt">视频设备管理</div></a></div>
					<div class="submenu"><a href="${ctx }/admin/faultDiag/dtuDev/config" target="iframe_height" ><div class="second_txt">设备数据配置管理</div></a></div>
                </div>
            </li>
            <li class="li_bg"><img src="${ctx }/images/faultDiag/li_title01.png" class="li_png"/><a href="javascript:blur(4);"><div class="first_ft">系统用户管理</div><div class="first_icon"><img src="${ctx }/images/faultDiag/li_hide.png"></div></a>
                <div style="display:none;">
                    <div class="submenu"><a href="${ctx }/admin/faultDiag/user/list" target="iframe_height" ><div class="second_txt">用户管理</div></a></div>
                </div>
            </li>
        </ul>
   </div>
</div>
</body>
</html>

<script type="text/javascript" src="${ctx }/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
<!--
function blur(index){
	   var li = $(".li_bg").eq(index)
       var obj = li.children("div:last-child");
       var d = obj.css("display");
       if("none" == d){
            closeMenu();
            changeImg(li,"block");
            obj.css("display","block");
       }
       if("block" == d){
       		changeImg(li,"none");
            obj.css("display","none");
       }
}

function changeImg(o,f){
    if(f == "block"){
        o.find("img").eq(1).attr("src","${ctx }/images/faultDiag/li_show.png");
    }else if(f == "none"){
        o.find("img").eq(1).attr("src","${ctx }/images/faultDiag/li_hide.png");
    }
}

function closeMenu(){
    $(".li_bg").each(function(){
        var liObj = $(this).children("div:last-child");
        var divD = liObj.css("display");
        if("block" == divD){
        	changeImg($(this),"none");
            liObj.css("display","none");
        }
    });
}

//-->
</script>
