<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</STYLE><LINK href="${ctx }/css/comm/css.css" type="text/css" rel="stylesheet">
</STYLE><LINK href="${ctx }/css/comm/left.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="${ctx }/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${ctx }/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript">
var setting = {
		view: {
				dblClickExpand: dblClickExpand
			},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: true
			//onClick: onClick
		}
	};

var zNodes = ${sceneTree};

function dblClickExpand(treeId, treeNode) {
	return treeNode.level > 0;
}
		
//关闭全部
function closeAll(){
	pnode = zTree.getNodeByTId("2");
	var nodes = zTree.getNodesByParam("pId", "2", pnode);
	for(var i=0;i<nodes.length; i++){
		zTree.setting.view.fontCss = {};
		zTree.expandNode(nodes[i], false, true, null, null);
	}
}

//点击事件(可直接使用Nodes中的click)
function onClick(event, treeId, treeNode, clickFlag) {
	alert(treeNode.click);
}		

var zTree;
$(document).ready(function(){
	if ((navigator.userAgent.indexOf('MSIE') >= 0) 
    && (navigator.userAgent.indexOf('Opera') < 0)){
		var height = window.parent.frames['mainFrame'].document.body.clientHeight;
		document.getElementsByTagName('body')[0].style.height = height+'px';
	}

	zTree = $.fn.zTree.init($("#sceneTree"), setting, zNodes);
	if(zTree.getSelectedNodes().length == 0){
		var pnode = zTree.getNodeByTId("2")
		var pnode1 = zTree.getNodeByParam("pId", "2", pnode);
		var node = zTree.getNodeByParam("pId", pnode1.id, pnode1);
		zTree.selectNode(node);
	}
	window.parent.frames['mainFrame'].location = "${ctx }/admin/envirMonitor/dispense";
});
</script>
<style type="text/css">
.ztree li span.button.switch.level0 {visibility:hidden; width:1px;}
.ztree li ul.level0 {padding:0; background:none;}
.ztree li span.button.pIcon_ico_open, .ztree li span.button.pIcon00_ico_close{margin-right:2px; background: url(${ctx}/images/tree/base.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li span.button.pIcon00_ico_open, .ztree li span.button.pIcon00_ico_close{margin-right:2px; background: url(${ctx}/images/tree/icon_21_2.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li span.button.pIcon01_ico_open, .ztree li span.button.pIcon01_ico_close{margin-right:2px; background: url(${ctx}/images/tree/icon_21_3.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li span.button.icon01_ico_docu{margin-right:2px; background: url(${ctx}/images/tree/icon_11_0.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li span.button.icon02_ico_docu{margin-right:2px; background: url(${ctx}/images/tree/devctrl.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li span.button.icon03_ico_docu{margin-right:2px; background: url(${ctx}/images/tree/icon_21_0.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li span.button.icon04_ico_docu{margin-right:2px; background: url(${ctx}/images/tree/301.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
</style>
</head>

<body>
<div class="left" id="left">
	<div id="mian">
		 <div class="zkgbc">
			  <a onFocus="blur()" onclick="zTree.expandAll(true);"><img src="${ctx }/images/envirMonitor/zk-btn.gif"/></a>&nbsp;&nbsp;&nbsp;&nbsp;
			  <a onFocus="blur()" onclick="closeAll();"><img src="${ctx }/images/envirMonitor/gb-btn.gif"/></a>
		 </div>
		 <div class="ztree" id="sceneTree">
		 
		 </div>
	</div>
</div>
</body>
</html>