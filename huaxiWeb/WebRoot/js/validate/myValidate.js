
function myValidate(){
	var msg = true; 
	$("select").each(function(){
		if($(this).children(":selected").val().length == 0){
			$(this).next().html("&nbsp;&nbsp;不能为空，请选择！").attr("style","color:red");
			msg = false;
		}else{
			$(this).next().attr("style","");
		}
	});
	
	$(".checkVal").each(function(){
		if($.trim($(this).children("input").val()).length == 0){
			$(this).children("span").attr("style","color:red");
			msg = false;
		}else{
			$(this).children("span").attr("style","");
		}
	});
	return msg;
}