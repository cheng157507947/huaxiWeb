<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>农田物联网生态环境监测系统</title>
</STYLE><LINK href="${ctx }/css/comm/css.css" type="text/css" rel="stylesheet">
</STYLE><LINK href="${ctx }/css/growPc/main.css" type="text/css" rel="stylesheet">
</head>
	
<body style="min-width:1250px;
 width:expression_r(document.body.clientWidth < 1250? "1250px": "auto" ); }
">
<div class="main">
<div class="main-left">
    
     <div class="qtsj">
          <div class="bk-bt">
               <span>生长状态监测</span>
               <div class="more"><a href="${ctx }/admin/growPc/picPredict/hisList">查看更多>></a></div>
          </div>
          <div class="three">${picPredictShow.riceCycle }</div>
          <div class="threepic"><img src="${ctx }/${picPredictShow.singleShowPath}"/></div>
          <ul class="dyul">
               <li style="height:60px;white-space:normal;font-size: 13px;"><span>${picPredictShow.cycleDescribe }</span></li>
               <li>用药：${picPredictShow.pesticideDescribe }</li>
               <li>备注：${picPredictShow.pesticideDesc }</li>
          </ul>
     </div>
     <div class="qtsj" style=" margin-top:-1px;">
          <div class="bk-bt">
               <span>产量预测</span>
               <div class="more"><a href="${ctx }/admin/growPc/picPredict/hisList">查看更多>></a></div>
          </div>
          <ul class="dyul">
          	   <li>种植总面积：${picPredictShow.totalArea } 亩</li>
               <li>总产量：${picPredictShow.totalYield }</li>
               <li>亩产量：${picPredictShow.predictYield }</li>
               <li>图片拍摄面积：${picPredictShow.area } 平方米</li>
          </ul>
     </div>
     
</div>

<div class="main-right">
     <div class="ssjk">
          <div class="showpic">
               <div class="showpic-name">图片展示</div>
               <div class="showpic-time">图片采集时间：<fmt:formatDate value="${picPredictShow.capTime }" pattern="yyyy-MM-dd"></fmt:formatDate></div>
          </div> 
          <div class="showpicture"><img src="${ctx }/${picPredictShow.fullShowPath }"/></div>  
          <!-- <div class="rightpic"><img src="${ctx }/images/growPc/rightpic.jpg"/></div> -->  
     </div>
</div>
</div>
</body>
</html>