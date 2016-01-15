<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>农药记录</title>
</STYLE><LINK href="${ctx }/css/comm/css.css" type="text/css" rel="stylesheet">
</STYLE><LINK href="${ctx }/css/growPc/main.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/js/jquery-1.10.2.min.js"></script>
<link href="${ctx }/js/date/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/date/WdatePicker.js"></script>
<script type="text/javascript">
$(document).ready(function() {
});

function selectAll(c){
	if(c.checked){
		$("[name='idGroup']").attr("checked",true);
	}else{
		$("[name='idGroup']").attr("checked",false);
	}
}

function del(){
	if($("[name='idGroup']:checked").length <= 0){
		alert("请选择一个您要删除的！");
	} else{
		if (confirm("确定要删除吗？")){
			$("#myForm").submit();
		}
	}
}
function gotoPage(pageNo){
	$("#pageNo").val(pageNo);
	$("#findForm").submit();
}
</script>
</head>
	
<body style="min-width:1250px;
 width:expression_r(document.body.clientWidth < 1250? "1250px": "auto" ); }
">
<div class="main">
    
     <div class="ssjk">
          <div class="bk-bt">
               <span>农药记录列表</span>
          </div>
          <c:if test="${!empty okMsg}">
    		<div class="noticeMsg">${okMsg }</div>
	      </c:if>
	      <c:if test="${!empty errMsg}">
	    	<div class="errorMsg">${errMsg }</div>
	      </c:if>
          <form action="${ctx }/admin/growPc/pesticidesRecord/list" method="post" id="findForm">
          <div class="operateTop">
          	农药名称：
          	<input name="name" type="text" value="${name }">
          	施用时间：
          	<input class="dateQuery" name="beginTime" type="text"  onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="${beginTime }">-
		  	<input class="dateQuery" name="endTime" type="text" onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="${endTime }">
          	<input class="querybtn" type="button" value="查询" onclick="gotoPage(0);">
		  	<input class="querybtn" type="button" value="添加" onclick="location.href='${ctx}/admin/growPc/pesticidesRecord/add';"> 
          	<input class="deletebtn" type="button" value="删除" onclick="del();">
          	<input type="hidden" name="page.page" id="pageNo" value="${page.number+1}"/>
          </div>
          </form>
          <form action="${ctx }/admin/growPc/pesticidesRecord/delete" id="myForm" method="post">
          <table id="data" cellpadding="0" cellspacing="0" class="tab01">
          	<tr>
	          	<th width="10%">全选&nbsp;<input name="selAll" id="all" type="checkbox" onClick="selectAll(this)" /></th>
	          	<th width="15%">农药名称</th>
	          	<th width="10%">农药施用量</th>
	          	<th width="10%">农药单位</th>
	          	<th width="10%">施用时间</th>
	          	<th width="20%">备注</th>
	          	<th width="10%">操作</th>
          	</tr>
          	<c:forEach items="${page.content }" var="pesticidesRecord">
          	<tr>
	          	<td width="10%"><input name="idGroup" type="checkbox" value="${pesticidesRecord.id }"/></td>
	          	<td width="15%">${pesticidesRecord.name }</td>
	          	<td width="10%">${pesticidesRecord.amount }</td>
	          	<td width="10%">${pesticidesRecord.unit }</td>
	          	<td width="10%"><fmt:formatDate value="${pesticidesRecord.date }" pattern="yyyy-MM-dd"></fmt:formatDate></td>
	          	<td width="20%">${pesticidesRecord.desc }</td>
	          	<td width="10%"><a href="${ctx }/admin/growPc/pesticidesRecord/edit?id=${pesticidesRecord.id }">编辑</a></td>
          	</tr>
          	</c:forEach>
          </table>
          </form>
		  <div class="page">
			 <jsp:include page="../page.jsp"></jsp:include>
		  </div>
     </div>
</div>
</body>
</html>