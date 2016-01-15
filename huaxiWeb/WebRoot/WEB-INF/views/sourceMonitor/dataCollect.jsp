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
</STYLE><LINK href="${ctx }/css/sourceMonitor/main.css" type="text/css" rel="stylesheet">
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
                    <th colspan="13">水源地数据汇总</th>
               </tr>
               <tr>
                    <th>田块名称</th>
                    <th>源水PH</th>
                    <th>源水浊度</th>
                    <th>采集时间</th>
               </tr>
               <c:forEach items="${waterDatas }" var="waterData">
               <tr>
                    <td>${waterData.scene.name }</td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="2">${waterData.collLatestData.data3 }</fmt:formatNumber></td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="2">${waterData.collLatestData.data4 }</fmt:formatNumber></td>
                    <td><fmt:formatDate value="${waterData.collLatestData.collTime }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
               </tr>
               </c:forEach>
          </table>
          
          <div></div>
     </div>

</div>
</body>
</html>