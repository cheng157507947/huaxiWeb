package com.zncxi.huaxi.web.faultDiag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zncxi.huaxi.service.DtuStsService;

@Controller
@RequestMapping(value = "/admin/faultDiag/dtuSts")
public class DtuStsController {

	@Autowired
	private DtuStsService dtuStsService;

	@RequestMapping(value = "/list")
	public String list(Model model) {
		model.addAttribute("dtuStsList", dtuStsService.findAll());
		return "/faultDiag/dtuSts/list";
	}

}
