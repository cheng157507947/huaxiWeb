<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>图片上传</title>
</STYLE><LINK href="${ctx }/css/comm/css.css" type="text/css" rel="stylesheet">
</STYLE><LINK href="${ctx }/css/growPc/main.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/js/jquery-1.10.2.min.js"></script>
<link href="${ctx }/js/date/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/js/date/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx }/js/validate/jquery.metadata.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx }/js/validate/jquery.validate.css"/>
<script type="text/javascript" src="${ctx }/js/validate/jquery.validate.js"></script> 
<script src="${ctx }/uploadify/jquery.uploadify.min.js?f=<%=System.currentTimeMillis()%>"></script>
<link rel="stylesheet" type="text/css" href="${ctx }/uploadify/uploadify.css">
<script type="text/javascript">
var ctx = "${ctx}";
var validate;
$(document).ready(function() {
	validate = $("#myForm").validate({});
	$("#fileError1").hide();
	$("#fileError2").hide();
	
	$("#file_upload_1").uploadify({
		"auto":true,
		"height" : 20,
		"fileObjName" : "imgFile",
		"swf" : "${ctx}/uploadify/uploadify.swf",
		"uploader" : "${ctx}/admin/growPc/picPredict/upload",
		"width" : 100,
		"multi":false,
		//"buttonImage": "${ctx}/uploadify/browse-btn.png",
		"fileTypeDesc" : "Image Files",
        "fileTypeExts" : "*.bmp; *.jpg; *.png",
        "buttonText":"请选择文件",
		"onUploadComplete" : function(file) {
	           //alert("Flash was not detected."+file.name);
	    },
	    "onUploadSuccess" : function(file,data,response){
	    	var obj = jQuery.parseJSON(data);
	    	if(obj.error == "0"){
	       		$("#breedImg1").attr("src","${ctx}/"+obj.url);
	       		$("#breedImgHidden1").val(obj.savePath);
	       		$("#fileError1").hide();
	       	}
	    }
	});
	$("#file_upload_2").uploadify({
		"auto":true,
		"height" : 20,
		"fileObjName" : "imgFile",
		"swf" : "${ctx}/uploadify/uploadify.swf",
		"uploader" : "${ctx}/admin/growPc/picPredict/upload",
		"width" : 100,
		"multi":false,
		//"buttonImage": "${ctx}/uploadify/browse-btn.png",
		"fileTypeDesc" : "Image Files",
        "fileTypeExts" : "*.bmp; *.jpg; *.png",
        "buttonText":"请选择文件",
		"onUploadComplete" : function(file) {
	           //alert("Flash was not detected."+file.name);
	    },
	    "onUploadSuccess" : function(file,data,response){
	    	var obj = jQuery.parseJSON(data);
	    	if(obj.error == "0"){
	       		$("#breedImg2").attr("src","${ctx}/"+obj.url);
	       		$("#breedImgHidden2").val(obj.savePath);
	       		$("#fileError2").hide();
	       	}
	    }
	});
});
function submitForm(){
	if($("#breedImgHidden1").val().length == 0){
		$("#fileError1").show();
		return;
	}
	if($("#breedImgHidden2").val().length == 0){
		$("#fileError2").show();
		return;
	}
	if(validate.form()){
		$("#myForm").submit();
	}
}
function goBlack(){
	history.go(-1);
}
</script>
</head>
	
<body style="min-width:1250px;
 width:expression_r(document.body.clientWidth < 1250? "1250px": "auto" ); }
">
<div class="main">
    
     <div class="ssjk">
          <div class="bk-bt">
               <span>图片上传添加</span>
          </div>
          <form id="myForm" name="myForm" action="${ctx}/admin/growPc/picPredict/save" method="post" >
          <input type="hidden" name="id" value="${picPredictInfo.id }"/>
          <table cellpadding="0" cellspacing="0" class="tab01" id="table01">
               <tr>
                    <th style="width: 100%" colspan="3">
                    	<span style="float: left; padding-left: 30px;color: red; font-weight: normal;">上传图片要求：图片分辨率不能大于1920*1080，不能小于352*288，大小统一，宽高比为4:3或16:9，宽高均为4的倍数，常见的图片格式：bmp、jpeg、png等</span>
                    	<input style="float: right; margin-right: 30px;" type="button" class="btn" value="返回" onclick="goBlack();"/>
                    	<input style="float: right; margin-right: 30px;" type="button" class="btn" value="保存" onclick="submitForm();"/>
                    </th>
               </tr>
		       <tr>
		       		<td width="20%">图片上传：</td>
		  			<td width="40%" class="addTd">
		  				全景：
		  				<input type="file" name="file_upload" id="file_upload_1" />
		  				<label id="fileError1" style="display: inline;color: red;">请先上传全景图片</label>
					</td>
					<td width="40%" class="addTd">
						单株：
		  				<input type="file" name="file_upload" id="file_upload_2" />
		  				<label id="fileError2" style="display: inline;color: red;">请先上传单株图片</label>
					</td>
				</tr>
				<tr>
					<td>上传图片缩略图：</td>
			        <td class="addTd">
	      				<img id="breedImg1" width="215" height="152" src="${ctx }/images/growPc/default_avatar.jpg">
	      				<input type="hidden" id="breedImgHidden1" name="fullPath" value="" />
			        </td>
			        <td class="addTd">
	      				<img id="breedImg2" width="215" height="152" src="${ctx }/images/growPc/default_avatar.jpg">
	      				<input type="hidden" id="breedImgHidden2" name="singlePath" value="" />
			        </td>
				</tr>
				<tr>
					<td>图片采集时间：</td>
		  			<td class="addTd" colspan="2">
		  				<input class="required" name="capTime" type="text" onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" />
		  				&nbsp;请填入上传图片的采集时间（不可为空）
					</td>
				</tr>
				<tr>
					<td>备注：</td>
		  			<td class="addTd" colspan="2">
		  				<input name="remarks" style="width: 300px;" value=""/>
					</td>
				</tr>
		       <tr>
                    <th style="width: 100%" colspan="3">
                    	<input style="float: right; margin-right: 30px;" type="button" class="btn" value="返回" onclick="goBlack();"/>
                    	<input style="float: right; margin-right: 30px;" type="button" class="btn" value="保存" onclick="submitForm();"/>
                    </th>
               </tr>
          </table>
     </div>
</div>
</body>
</html>