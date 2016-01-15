<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" style=" overflow-x:hidden;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>数据汇总</title>
</STYLE><LINK href="${ctx }/css/comm/css.css" type="text/css" rel="stylesheet">
</STYLE><LINK href="${ctx }/css/envirMonitor/main.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
});
</script>  
</head>
	
<body>
<div class="main">

     <div class="ssjk">
          <div class="bk-bt">
               <span>数据汇总</span>
          </div>
          <table id="data" cellpadding="0" cellspacing="0" class="tab01">
               <tr>
                    <th colspan="13">田块数据汇总</th>
               </tr>
               <tr>
                    <th>田块名称</th>
                    <th>田块1水位</th>
                    <th>田块2水位</th>
                    <th>田块3水位</th>
                    <th>田块4水位</th>
                    <th>土壤水分</th>
                    <th>片区1瞬时流量</th>
                    <th>片区1累计流量</th>
                    <th>片区2瞬时流量</th>
                    <th>片区2累计流量</th>
                    <th>片区3瞬时流量</th>
                    <th>片区3累计流量</th>
                    <th>采集时间</th>
               </tr>
               <c:forEach items="${fieldDatas }" var="fieldData">
               <tr>
                    <td>${fieldData.scene.group.title }-${fieldData.scene.name }</td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="2">${fieldData.collLatestData.data1 }</fmt:formatNumber></td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="2">${fieldData.collLatestData.data2 }</fmt:formatNumber></td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="2">${fieldData.collLatestData.data3 }</fmt:formatNumber></td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="2">${fieldData.collLatestData.data4 }</fmt:formatNumber></td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="2">${fieldData.collLatestData.data5 }</fmt:formatNumber></td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="2">${fieldData.collLatestData.data6 }</fmt:formatNumber></td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="2">${fieldData.collLatestData.data7 }</fmt:formatNumber></td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="2">${fieldData.collLatestData.data8 }</fmt:formatNumber></td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="2">${fieldData.collLatestData.data9 }</fmt:formatNumber></td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="2">${fieldData.collLatestData.data10 }</fmt:formatNumber></td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="2">${fieldData.collLatestData.data11 }</fmt:formatNumber></td>
                    <td><fmt:formatDate value="${fieldData.collLatestData.collTime }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
               </tr>
               </c:forEach>
          </table>
          
          <table id="data" cellpadding="0" cellspacing="0" class="tab01">
               <tr>
                    <th colspan="4">泵房数据汇总</th>
               </tr>
               <tr>
                    <th>泵房名称</th>
                    <th>水源地水位</th>
                    <th>水泵出口压力</th>
                    <th>采集时间</th>
               </tr>
               <c:forEach items="${pumpDatas }" var="pumpData">
               <tr>
                    <td>${pumpData.scene.group.title }-${pumpData.scene.name }</td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="2">${pumpData.collLatestData.data1 }</fmt:formatNumber></td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="2">${pumpData.collLatestData.data2 }</fmt:formatNumber></td>
                    <td><fmt:formatDate value="${pumpData.collLatestData.collTime }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
               </tr>
               </c:forEach>
          </table>
          
          <table id="data" cellpadding="0" cellspacing="0" class="tab01">
               <tr>
                    <th colspan="9">气象数据汇总</th>
               </tr>
               <tr>
                    <th>气象站名称</th>
                    <th>风速</th>
                    <th>风向</th>
                    <th>大气温度</th>
                    <th>大气湿度</th>
                    <th>照度</th>
                    <th>小时雨量</th>
                    <th>日雨量</th>
                    <th>采集时间</th>
               </tr>
               <c:forEach items="${weatherDatas }" var="weatherData">
               <tr>
                    <td>${weatherData.scene.name }</td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="2">${weatherData.collLatestData.data3 }</fmt:formatNumber></td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="2">${weatherData.collLatestData.data4 }</fmt:formatNumber></td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="2">${weatherData.collLatestData.data5 }</fmt:formatNumber></td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="2">${weatherData.collLatestData.data6 }</fmt:formatNumber></td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="2">${weatherData.collLatestData.data7 }</fmt:formatNumber></td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="2">${weatherData.collLatestData.data8 }</fmt:formatNumber></td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="2">${weatherData.collLatestData.data9 }</fmt:formatNumber></td>
                    <td><fmt:formatDate value="${weatherData.collLatestData.collTime }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
               </tr>
               </c:forEach>
          </table>
          <div></div>
     </div>

</div>
</body>
</html>