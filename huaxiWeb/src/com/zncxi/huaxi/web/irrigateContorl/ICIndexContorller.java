package com.zncxi.huaxi.web.irrigateContorl;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zncxi.huaxi.domain.AlarmConfig;
import com.zncxi.huaxi.domain.Scene;
import com.zncxi.huaxi.service.AlarmConfigService;
import com.zncxi.huaxi.service.SceneService;
import com.zncxi.huaxi.util.enumeration.SceneGroup;
import com.zncxi.huaxi.web.CommController;

/**
 * 智慧农田灌溉控制系统
 * 首页显示
 * @author xiaoCheng
 *
 */
@Controller
@RequestMapping(value = "/admin/irrigateContorl")
public class ICIndexContorller {
	
	private Logger logger = LoggerFactory.getLogger(ICIndexContorller.class);
	
	@Autowired
	private CommController commController;
	@Autowired
	private SceneService sceneService;
//	@Autowired
//	private CollLatestDataService collLatestDataService;
	@Autowired
	private AlarmConfigService alarmConfigService;

	@RequestMapping(value = "/index")
	public String index(){
		return "/irrigateContorl/main/index";
	}
	
	@RequestMapping(value = "/left")
	public String left(Model model){
		model.addAttribute("sceneTree", this.commController.getSceneTree(new SceneGroup[]{SceneGroup.ONE_SOUTH, SceneGroup.TWO_NORTH}));
		return "/irrigateContorl/main/left";
	}
	
	/**
	 * 田块实时控制
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/realTimeField")
	public String realTimeField(String id, Model model, HttpSession session){
		model.addAttribute("id", id);
		model.addAttribute("title", getTitleName(id));
		model.addAttribute("titleInfo", this.commController.findDtuDevAndStsBySceneId(id));
		model.addAttribute("otherInfo", this.commController.findVideoBySceneId(id));
		model.addAttribute("controlInfo", this.commController.findControlDevBySceneId(id,session));
		return "/irrigateContorl/realTime/field";
	}
	
	/**
	 * 泵房实时控制
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/realTimePump")
	public String realTimePump(String id, Model model, HttpSession session){
		model.addAttribute("id", id);
		model.addAttribute("title", getTitleName(id));
		model.addAttribute("titleInfo", this.commController.findDtuDevAndStsBySceneId(id));
		model.addAttribute("otherInfo", this.commController.findVideoBySceneId(id));
		model.addAttribute("controlInfo", this.commController.findControlDevBySceneId(id,session));
		return "/irrigateContorl/realTime/pump";
	}
	
	/**
	 * 报警配置
	 * @return
	 */
	@RequestMapping(value = "/alarmConfig")
	public String alarmConfig(Model model){
		model.addAttribute("alarmConfig", this.alarmConfigService.findAll().get(0));
		return "/irrigateContorl/alarmConfig";
	}
	
	/**
	 * 保存报警配置
	 * @return
	 */
	@RequestMapping(value = "/saveAlarmConfig")
	public String saveAlarmConfig(AlarmConfig alarmConfig, RedirectAttributes ra){
		try {
			this.alarmConfigService.save(alarmConfig);
			ra.addFlashAttribute("okMsg", "报警配置保存成功");
		} catch (Exception e) {
			logger.error("保存报警配置成功",e);
			ra.addFlashAttribute("errMsg", "报警配置保存失败");
		}
		return "redirect:/admin/irrigateContorl/alarmConfig";
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
