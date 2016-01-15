<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>历史预测数据</title>
</STYLE><LINK href="${ctx }/css/comm/css.css" type="text/css" rel="stylesheet">
</STYLE><LINK href="${ctx }/css/growPc/main.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/js/jquery-1.10.2.min.js"></script>
<link href="${ctx }/js/date/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/date/WdatePicker.js"></script>
<script type="text/javascript">
$(document).ready(function() {
});

function gotoPage(pageNo){
	$("#pageNo").val(pageNo);
	$("#findForm").submit();
}
function changeRevise(id,old){
	var revise=prompt("请输入修正总产量",old);//将输入的内容赋给变量 name ，
	if(revise){
		if(isNaN(revise) && revise<=0){
			alert("请输入有效数字！");
			return;
		}
		$("#reviseYield").text("正在修正");
		$.post("${ctx }/admin/growPc/picPredict/reviseYield",{id:id,yield:revise},function(result){
			if(result.ok){
				$("#reviseYield").text(result.reviseYield+" 斤");
			}else{
				$("#reviseYield").text("修正失败");
			}
		},"json");
	}
}
</script>
</head>
	
<body style="min-width:1250px;
 width:expression_r(document.body.clientWidth < 1250? "1250px": "auto" ); }
">
<div class="main">
    
     <div class="ssjk">
          <div class="bk-bt">
               <span>历史预测数据列表</span>
          </div>
          <c:if test="${!empty okMsg}">
    		<div class="noticeMsg">${okMsg }</div>
	      </c:if>
	      <c:if test="${!empty errMsg}">
	    	<div class="errorMsg">${errMsg }</div>
	      </c:if>
          <form action="${ctx }/admin/growPc/picPredict/hisList" method="post" id="findForm">
          <div class="operateTop">
          	采集时间：
          	<input class="dateQuery" name="beginTime" type="text"  onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="${beginTime }">-
		  	<input class="dateQuery" name="endTime" type="text" onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="${endTime }">
          	<input class="querybtn" type="button" value="查询" onclick="gotoPage(0);">
          	<input type="hidden" name="page.page" id="pageNo" value="${page.number+1}"/>
          </div>
          </form>
          <form action="${ctx }/admin/growPc/picPredict/delete" id="myForm" method="post">
          <table id="data" cellpadding="0" cellspacing="0" class="tab01">
          	<tr>
	          	<th width="8%">图片采集时间</th>
	          	<th width="15%">全景缩略图</th>
	          	<th width="15%">单株缩略图</th>
	          	<th width="10%">生长期</th>
	          	<th width="10%">预测亩产量</th>
	          	<th width="10%">预测总产量</th>
	          	<th width="10%">修正总产量</th>
	          	<th width="15%">备注</th>
	          	<th width="5%">操作</th>
          	</tr>
          	<c:forEach items="${page.content }" var="picPredict">
          	<tr>
	          	<td width="8%"><fmt:formatDate value="${picPredict.capTime }" pattern="yyyy-MM-dd"></fmt:formatDate></td>
	          	<td width="15%"><img style="width: 90px;height: 70px;margin: 5px 0;" src="${ctx }/${picPredict.fullShowPath }"/></td>
	          	<td width="15%"><img style="width: 90px;height: 70px;margin: 5px 0;" src="${ctx }/${picPredict.singleShowPath }"/></td>
	          	<td width="10%">
	          		<c:if test="${picPredict.riceCycle == 1 }">分蘖期</c:if>
	          		<c:if test="${picPredict.riceCycle == 2 }">拔节孕穗期</c:if>
	          		<c:if test="${picPredict.riceCycle == 3 }">抽穗扬花期</c:if>
	          		<c:if test="${picPredict.riceCycle == 4 }">灌浆成熟期</c:if>
	          		<c:if test="${picPredict.riceCycle == 0 }">时间错误</c:if>
	          		<c:if test="${picPredict.riceCycle == 10 }">图片采集时间不在水稻种植时间内</c:if>
	          	</td>
	          	<td width="10%">
	          		<c:choose>
					<c:when test="${picPredict.predictYield == 0 }">产量预测异常</c:when>
					<c:when test="${picPredict.predictYield == -1 }">非灌浆期无法预测</c:when>
					<c:when test="${picPredict.predictYield == -2 }">拍摄面积应大于10m2</c:when>
					<c:otherwise>${picPredict.predictYield } 斤</c:otherwise>
					</c:choose>
	          	</td>
	          	<td width="10%">
	          		<c:choose>
					<c:when test="${picPredict.totalYield <= 0 }">产量预测异常</c:when>
					<c:otherwise>${picPredict.totalYield } 斤</c:otherwise>
					</c:choose>
	          	</td>
	          	<td width="10%" id="reviseYield">
	          		<c:choose>
					<c:when test="${picPredict.reviseYield <= 0 }">产量预测异常</c:when>
					<c:otherwise>${picPredict.reviseYield } 斤</c:otherwise>
					</c:choose>
	          	</td>
	          	<td width="15%">${picPredict.remarks }</td>
	          	<td width="5%">
	          		<c:choose>
					<c:when test="${picPredict.reviseYield == 0 }"></c:when>
					<c:otherwise><a href="javascript:changeRevise('${picPredict.id }','${picPredict.reviseYield }');">产量修正</a></c:otherwise>
					</c:choose>
	          	</td>
          	</tr>
          	</c:forEach>
          </table>
          </form>
		  <div class="page">
			 <jsp:include page="page.jsp"></jsp:include>
		  </div>
     </div>
</div>
</body>
</html>