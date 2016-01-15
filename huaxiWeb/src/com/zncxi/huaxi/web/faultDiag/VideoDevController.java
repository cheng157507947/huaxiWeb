package com.zncxi.huaxi.web.faultDiag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zncxi.huaxi.domain.VideoDev;
import com.zncxi.huaxi.service.SceneService;
import com.zncxi.huaxi.service.VideoDevService;
import com.zncxi.huaxi.util.enumeration.SceneGroup;

@Controller
@RequestMapping(value = "/admin/faultDiag/videoDev")
public class VideoDevController {

	private Logger logger = LoggerFactory.getLogger(VideoDevController.class);

	@Autowired
	private VideoDevService videoDevService;

	@Autowired
	private SceneService sceneService;

	@RequestMapping(value = "/list")
	public String list(Model model, String queryName, String queryValue, Pageable pageable) {
		model.addAttribute("page", videoDevService.findAllLike(pageable, queryName, queryValue));
		model.addAttribute("queryName", queryName);
		model.addAttribute("queryValue", queryValue);
		return "/faultDiag/videoDev/list";
	}

	@RequestMapping(value = "/add")
	public String add(Model model) {
		model.addAttribute("sceneGroups", SceneGroup.values());
		return "/faultDiag/videoDev/add";
	}

	@RequestMapping(value = "/edit/{id}")
	public String edit(Model model, @PathVariable("id") String id) {
		VideoDev videoDev = videoDevService.findById(id);
		model.addAttribute("sceneGroups", SceneGroup.values());
		model.addAttribute("scenes", sceneService.findSceneByGroup(videoDev.getScene().getGroup()));
		model.addAttribute("videoDev", videoDev);
		return "/faultDiag/videoDev/edit";
	}

	@RequestMapping(value = "/save")
	public String save(VideoDev videoDev, RedirectAttributes ra) {
		try {
			videoDevService.save(videoDev);
			ra.addFlashAttribute("messageOK", "保存成功");
		} catch (Exception e) {
			ra.addFlashAttribute("messageErr", "保存异常");
			logger.error("保存用户异常", e);
		}
		return "redirect:/admin/faultDiag/videoDev/list";
	}

	@RequestMapping(value = "/delete")
	public String delete(String[] idGroup, RedirectAttributes ra) {
		try {
			videoDevService.delete(idGroup);
			ra.addFlashAttribute("messageOK", "删除成功");
		} catch (Exception e) {
			ra.addFlashAttribute("messageErr", "删除异常");
			logger.error("保存用户异常", e);
		}
		return "redirect:/admin/faultDiag/videoDev/list";
	}
}
