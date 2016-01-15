package com.zncxi.huaxi.web.faultDiag;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zncxi.huaxi.domain.DtuSts;
import com.zncxi.huaxi.service.DtuDevService;
import com.zncxi.huaxi.service.DtuStsService;
import com.zncxi.huaxi.service.FaultInfoService;

@Controller
@RequestMapping(value = "/admin/faultDiag/faultInfo")
public class FaultInfoController {

	@Autowired
	private FaultInfoService faultInfoService;

	@Autowired
	private DtuDevService dtuDevService;

	@Autowired
	private DtuStsService dtuStsService;

	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor dateEditor = new CustomDateEditor(df, true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}

	@RequestMapping(value = "/list")
	public String list(Model model, Pageable pageable, String selDtu, Date beginDate, Date endDate) {
		if (beginDate != null) {
			DateTime beginTime = new DateTime(beginDate);
			model.addAttribute("beginDate", beginTime.toString("yyyy-MM-dd"));
		}
		if (endDate != null) {
			DateTime endTime = new DateTime(endDate);
			model.addAttribute("endDate", endTime.toString("yyyy-MM-dd"));
		}
		model.addAttribute("page", faultInfoService.findPageByQuery(pageable, selDtu, beginDate, endDate));
		model.addAttribute("dtuDevs", dtuDevService.findAll());
		model.addAttribute("selDtu", selDtu);
		return "/faultDiag/faultInfo/list";
	}

	@RequestMapping(value = "/statistics")
	public String statistics(Model model) {
		List<DtuSts> dtuFaultList = new ArrayList<DtuSts>();
		List<DtuSts> dtuList = dtuStsService.findAll();
		int dtuCount = dtuList.size();//设备总数量
		int faultCount = 0;//故障数量
		int offLineCount = 0;//离线数量
		int faultSenCount = 0;//故障传感器数量

		for (DtuSts dtuSts : dtuList) {
			int status = 0;
			if(dtuSts.getOnline() == 0) {
				offLineCount++;
				status = 1;
			}
			if(dtuSts.getDefSts() == 0) {
				faultCount++;
				status = 1;
			}
			String senSts = Integer.toBinaryString(dtuSts.getSenSts());
			int senfCount =  senSts.replaceAll("1", "").length();
			if(senfCount > 0) {
				faultSenCount += senfCount;
				status = 1;
			}
			if(status == 1) {
				dtuFaultList.add(dtuSts);
			}
		}
		model.addAttribute("dtuCount", dtuCount);
		model.addAttribute("faultCount", faultCount);
		model.addAttribute("offLineCount", offLineCount);
		model.addAttribute("faultSenCount", faultSenCount);
		model.addAttribute("dtuFaultList", dtuFaultList);
		return "/faultDiag/faultInfo/statistics";
	}

}
