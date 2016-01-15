package com.zncxi.huaxi.web.faultDiag;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 设备故障在线智能诊断系统
 * 首页显示
 * @author xiaoCheng
 *
 */
@Controller
@RequestMapping(value = "/admin/faultDiag")
public class FDIndexContorller {

	@RequestMapping(value = "/index")
	public String index(){
		return "/faultDiag/main/index";
	}
}
