//时间格式化
Date.prototype.format = function(format, date) {
	/*
	 * eg:format="yyyy-MM-dd hh:mm:ss";
	 */
	if (!format) {
		format = "yyyy-MM-dd hh:mm:ss";
	}

	if (date == '' || date == null) {
		date = new Date();
	}

	var o = {
		"M+" : date.getMonth() + 1, // month
		"d+" : date.getDate(), // day
		"h+" : date.getHours(), // hour
		"m+" : date.getMinutes(), // minute
		"s+" : date.getSeconds(), // second
		"q+" : Math.floor((date.getMonth() + 3) / 3), // quarter
		"S" : date.getMilliseconds()
	// millisecond
	};

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};

function now() {
	var date = new Date();
	var dStr = date.format();
	$(".time").html(dStr + "&nbsp;&nbsp;&nbsp;&nbsp;");
}

$(document).ready(function() {
	var w = $(window).width();
	if ($.support.boxModel) {
		$(".tab_sty").css("width", w - 20);
	} else {
		if (w > 0) {
			$(".tab_sty").css("width", w - 20);
		} else {
			$(".tab_sty").css("width", "96%");
			var ad = $(".list").width();
			$(".address1").css("width", ad + 20);
		}
	}
	if ($.support.boxModel) {
		$(".list_table table").css("width", w - 20);
	} else {
		if (w > 0) {
			$(".list_table table").css("width", w - 20);
		} else {
			$(".list_table table").css("width", "96%");
			var ltw1 = $(".list_table").width();
			$(".address").css("width", ltw1 + 20);
		}
	}
	$(window).resize(function() {
		var width = $(window).width();
		if ($.support.boxModel) {
			$(".list_table table").css("width", width - 20);
		} else {
			if (width > 0) {
				$(".list_table table").css("width", width - 20);
			} else {
				$(".list_table table").css("width", "96%");
				var ltw = $(".list_table").width();
				$(".address").css("width", ltw + 20);
			}
		}
		if ($.support.boxModel) {
			$(".tab_sty").css("width", width - 20);
		} else {
			if (width > 0) {
				$(".tab_sty").css("width", width - 20);
			} else {
				$(".tab_sty").css("width", "96%");
				var ad1 = $(".list").width();
				$(".address1").css("width", ad1 + 20);
			}
		}
	});
	setInterval(now, 1000);
});
