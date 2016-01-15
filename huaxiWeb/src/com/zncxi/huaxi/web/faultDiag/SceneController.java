package com.zncxi.huaxi.web.faultDiag;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zncxi.huaxi.domain.Scene;
import com.zncxi.huaxi.service.DtuDevService;
import com.zncxi.huaxi.service.SceneService;
import com.zncxi.huaxi.util.enumeration.SceneGroup;

/**
 * 场景管理
 * @author xiaoCheng
 */
@Controller
@RequestMapping(value = "/admin/faultDiag/scene")
public class SceneController {

	private Logger logger = LoggerFactory.getLogger(SceneController.class);

	@Autowired
	private SceneService sceneService;

	@Autowired
	private DtuDevService dtuDevService;

	@RequestMapping(value = "/list")
	public String list(Model model, HttpSession session, String queryName, String queryValue, Pageable pageable) {
		Page<Scene> scenePage = this.sceneService.findAllLike(pageable, queryName, queryValue);
		model.addAttribute("page", scenePage);
		model.addAttribute("queryName", queryName);
		model.addAttribute("queryValue", queryValue);
		return "/faultDiag/scene/list";
	}

	@RequestMapping(value = "/add")
	public String add(Model model) {
		model.addAttribute("groups", SceneGroup.values());
		model.addAttribute("dtuDevList", dtuDevService.findAll());
		return "/faultDiag/scene/add";
	}

	@RequestMapping(value = "/edit/{id}")
	public String edit(Model model, @PathVariable("id") String id) {
		model.addAttribute("groups", SceneGroup.values());
		model.addAttribute("dtuDevList", dtuDevService.findAll());
		model.addAttribute("scene", sceneService.findById(id));
		return "/faultDiag/scene/edit";
	}

	@RequestMapping(value = "/save")
	public String save(Scene scene, RedirectAttributes ra) {
		try {
			this.sceneService.save(scene);
			ra.addFlashAttribute("messageOK", "场景保存成功");
		} catch (Exception e) {
			ra.addFlashAttribute("messageErr", "场景保存异常");
			logger.error("保存场景异常", e);
		}
		return "redirect:/admin/faultDiag/scene/list";
	}

	@RequestMapping(value = "/delete")
	public String delete(String[] idGroup, RedirectAttributes ra) {
		try {
			sceneService.delete(idGroup);
			ra.addFlashAttribute("messageOK", "场景删除成功");
		} catch (Exception e) {
			ra.addFlashAttribute("messageErr", "场景删除异常");
			logger.error("保存场景异常", e);
		}
		return "redirect:/admin/faultDiag/scene/list";
	}

	@RequestMapping(value = "/findSceneByGroup")
	@ResponseBody
	public String findSceneByGroup(int group) {
		String result = "";

		SceneGroup sceneGroup = SceneGroup.values()[group];
		List<Scene> scenes = this.sceneService.findSceneByGroup(sceneGroup);
		for (Scene scene : scenes) {
			result += ("{\"id\":\"" + scene.getId() + "\",\"name\":\"" + scene.getName() + "\"},");
		}

		if (result.length() > 0) {
			result = result.substring(0, result.length() - 1);
		}
		return "[" + result + "]";
	}
}
