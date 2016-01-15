package com.zncxi.huaxi.web.sourceMonitor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zncxi.huaxi.domain.Scene;
import com.zncxi.huaxi.service.CollLatestDataService;
import com.zncxi.huaxi.service.SceneService;
import com.zncxi.huaxi.util.enumeration.SceneGroup;
import com.zncxi.huaxi.util.vo.CollLatestDataVO;
import com.zncxi.huaxi.web.CommController;

/**
 * 灌溉水源监测系统
 * 首页显示
 * @author xiaoCheng
 *
 */
@Controller
@RequestMapping(value = "/admin/sourceMonitor")
public class SMIndexContorller {
	
	@Autowired
	private CommController commController;
	@Autowired
	private SceneService sceneService;
	@Autowired
	private CollLatestDataService collLatestDataService;

	@RequestMapping(value = "/index")
	public String index(){
		return "/sourceMonitor/main/index";
	}
	
	@RequestMapping(value = "/left")
	public String left(Model model){
		model.addAttribute("sceneTree", this.commController.getSceneTree(new SceneGroup[]{SceneGroup.HEADWATERS}));
		return "/sourceMonitor/main/left";
	}
	
	/**
	 * 水源地实时监控
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/realTimeWater")
	public String realTimeWater(String id, Model model){
		model.addAttribute("id", id);
		model.addAttribute("title", getTitleName(id));
		model.addAttribute("titleInfo", this.commController.findDtuDevAndStsBySceneId(id));
		model.addAttribute("otherInfo", this.commController.findVideoBySceneId(id));
		model.addAttribute("realTimeData", this.commController.findRealTimeDataBySceneId(id));
		return "/sourceMonitor/realTime/water";
	}
	
	/**
	 * 数据汇总
	 * @return
	 */
	@RequestMapping(value = "/dataCollect")
	public String dataCollect(Model model){
		List<CollLatestDataVO> waterDatas = this.collLatestDataService.findCollLatesDataVOByDtuDevSceneType(3);
		
		model.addAttribute("waterDatas", waterDatas);
		return "/sourceMonitor/dataCollect";
	}
	
	/**
	 * 曲线分析
	 * @return
	 */
	@RequestMapping(value = "/charts")
	public String charts(Model model){
		model.addAttribute("sceneGroups", new SceneGroup[]{SceneGroup.HEADWATERS});
		return "/sourceMonitor/charts";
	}
	
	/**
	 * 数据查询
	 * @return
	 */
	@RequestMapping(value = "/dataQuery")
	public String dataQuery(Model model){
		model.addAttribute("sceneGroups", new SceneGroup[]{SceneGroup.HEADWATERS});
		return "/sourceMonitor/dataQuery";
	}
	
	/**
	 * 获得页面显示场景名称
	 * @param id
	 * @return
	 */
	private String getTitleName(String id){
		Scene scene = this.sceneService.findById(id);
		return scene.getName();
	}
}
