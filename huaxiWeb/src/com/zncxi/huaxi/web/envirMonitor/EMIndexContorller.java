package com.zncxi.huaxi.web.envirMonitor;

import java.util.List;

import javax.servlet.http.HttpSession;

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
 * 农作物生长环境监测系统
 * 首页显示
 * @author xiaoCheng
 *
 */
@Controller
@RequestMapping(value = "/admin/envirMonitor")
public class EMIndexContorller {
	
	@Autowired
	private CommController commController;
	@Autowired
	private SceneService sceneService;
	@Autowired
	private CollLatestDataService collLatestDataService;

	@RequestMapping(value = "/index")
	public String index(){
		return "/envirMonitor/main/index";
	}
	
	@RequestMapping(value = "/left")
	public String left(Model model){
		model.addAttribute("sceneTree", this.commController.getSceneTree(new SceneGroup[]{SceneGroup.ONE_SOUTH, SceneGroup.TWO_NORTH, SceneGroup.WEETHER_SITE}));
		return "/envirMonitor/main/left";
	}
	
	/**
	 * 田块实时监控
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/realTimeField")
	public String realTimeField(String id, Model model){
		model.addAttribute("id", id);
		model.addAttribute("title", getTitleName(id));
		model.addAttribute("titleInfo", this.commController.findDtuDevAndStsBySceneId(id));
		model.addAttribute("otherInfo", this.commController.findVideoBySceneId(id));
		model.addAttribute("realTimeData", this.commController.findRealTimeDataBySceneId(id));
		return "/envirMonitor/realTime/field";
	}
	
	/**
	 * 泵房实时监控
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/realTimePump")
	public String realTimePump(String id, Model model){
		model.addAttribute("id", id);
		model.addAttribute("title", getTitleName(id));
		model.addAttribute("titleInfo", this.commController.findDtuDevAndStsBySceneId(id));
		model.addAttribute("otherInfo", this.commController.findVideoBySceneId(id));
		model.addAttribute("realTimeData", this.commController.findRealTimeDataBySceneId(id));
		return "/envirMonitor/realTime/pump";
	}
	
	/**
	 * 气象实时监控
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/realTimeWeather")
	public String realTimeWeather(String id, Model model){
		model.addAttribute("id", id);
		model.addAttribute("title", getTitleName(id));
		model.addAttribute("titleInfo", this.commController.findDtuDevAndStsBySceneId(id));
		model.addAttribute("otherInfo", this.commController.findVideoBySceneId(id));
		model.addAttribute("realTimeData", this.commController.findRealTimeDataBySceneId(id));
		return "/envirMonitor/realTime/weather";
	}
	
	/**
	 * 实时控制
	 * @param id 场景ID
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/realTimeControl")
	public String realTimeControl(String id, Model model, HttpSession session){
		model.addAttribute("id", id);
		model.addAttribute("title", getTitleName(id));
		model.addAttribute("titleInfo", this.commController.findDtuDevAndStsBySceneId(id));
		model.addAttribute("otherInfo", this.commController.findVideoBySceneId(id));
		model.addAttribute("controlInfo", this.commController.findControlDevBySceneId(id,session));
		return "/envirMonitor/controlDev";
	}
	
	/**
	 * 数据汇总
	 * @return
	 */
	@RequestMapping(value = "/dataCollect")
	public String dataCollect(Model model){
		List<CollLatestDataVO> fieldDatas = this.collLatestDataService.findCollLatesDataVOByDtuDevSceneType(1);
		List<CollLatestDataVO> pumpDatas = this.collLatestDataService.findCollLatesDataVOByDtuDevSceneType(2);
		List<CollLatestDataVO> weatherDatas = this.collLatestDataService.findCollLatesDataVOByDtuDevSceneType(4);
		
		model.addAttribute("fieldDatas", fieldDatas);
		model.addAttribute("pumpDatas", pumpDatas);
		model.addAttribute("weatherDatas", weatherDatas);
		return "/envirMonitor/dataCollect";
	}
	
	/**
	 * 曲线分析
	 * @return
	 */
	@RequestMapping(value = "/charts")
	public String charts(Model model){
		model.addAttribute("sceneGroups", new SceneGroup[]{SceneGroup.ONE_SOUTH, SceneGroup.TWO_NORTH, SceneGroup.WEETHER_SITE});
		return "/envirMonitor/charts";
	}
	
	/**
	 * 数据查询
	 * @return
	 */
	@RequestMapping(value = "/dataQuery")
	public String dataQuery(Model model){
		model.addAttribute("sceneGroups", new SceneGroup[]{SceneGroup.ONE_SOUTH, SceneGroup.TWO_NORTH, SceneGroup.WEETHER_SITE});
		return "/envirMonitor/dataQuery";
	}
	
	/**
	 * 获得页面显示场景名称
	 * @param id
	 * @return
	 */
	private String getTitleName(String id){
		Scene scene = this.sceneService.findById(id);
		String title = "-"+scene.getName();
		if(scene.getGroup() == SceneGroup.ONE_SOUTH){
			title = "华西南区"+title;
		}else if(scene.getGroup() == SceneGroup.TWO_NORTH){
			title = "华西北区"+title;
		}else{
			title = scene.getName();
		}
		return title;
	}
}
