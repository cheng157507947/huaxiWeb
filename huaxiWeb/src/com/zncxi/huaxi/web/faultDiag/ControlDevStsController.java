package com.zncxi.huaxi.web.faultDiag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zncxi.huaxi.domain.ControlDev;
import com.zncxi.huaxi.domain.ControlDevSts;
import com.zncxi.huaxi.service.ControlDevService;
import com.zncxi.huaxi.service.ControlDevStsService;
import com.zncxi.huaxi.service.DtuDevService;

@Controller
@RequestMapping(value = "/admin/faultDiag/controlDevSts")
public class ControlDevStsController {
	
	@Autowired
	private ControlDevService controlDevService;

	@Autowired
	private ControlDevStsService controlDevStsService;

	@Autowired
	private DtuDevService dtuDevService;

	@RequestMapping(value = "/list")
	public String list(Model model, String selDtu) {
		if(StringUtils.hasText(selDtu)) {
			List<ControlDev> controlDevList = controlDevService.findAllEq("dtuDev.id", selDtu);
			for (ControlDev controlDev : controlDevList) {
				ControlDevSts controlDevSts = controlDevStsService.findById(controlDev.getId());
				controlDev.setControlDevSts(controlDevSts);
			}
			model.addAttribute("controlDevStsList", controlDevList);
		}
		model.addAttribute("dtuDevs", dtuDevService.findAll());
		model.addAttribute("selDtu", selDtu);
		return "/faultDiag/controlDevSts/list";
	}

}
