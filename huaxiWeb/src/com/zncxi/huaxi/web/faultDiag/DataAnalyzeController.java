package com.zncxi.huaxi.web.faultDiag;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zncxi.huaxi.util.enumeration.SceneGroup;

@Controller
@RequestMapping(value = "/admin/faultDiag/dataAnalyze")
public class DataAnalyzeController {

	/**
	 * 历史数据分析
	 * @return
	 */
	@RequestMapping(value = "/hisData")
	public String hisData(Model model){
		model.addAttribute("sceneGroups", new SceneGroup[]{SceneGroup.ONE_SOUTH, SceneGroup.TWO_NORTH, SceneGroup.HEADWATERS, SceneGroup.WEETHER_SITE});
		return "/faultDiag/dataAnalyze/hisData";
	}
	
	/**
	 * 曲线分析
	 * @return
	 */
	@RequestMapping(value = "/dataCurve")
	public String dataCurve(Model model){
		model.addAttribute("sceneGroups", new SceneGroup[]{SceneGroup.ONE_SOUTH, SceneGroup.TWO_NORTH, SceneGroup.HEADWATERS, SceneGroup.WEETHER_SITE});
		return "/faultDiag/dataAnalyze/dataCurve";
	}
}
