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

import com.zncxi.huaxi.domain.DtuDev;
import com.zncxi.huaxi.service.DtuDevService;
import com.zncxi.huaxi.util.enumeration.SceneGroup;

/**
 * DTU设备管理
 * @author xiaoCheng
 */
@Controller
@RequestMapping(value = "/admin/faultDiag/dtuDev")
public class DtuDevController {

	private Logger logger = LoggerFactory.getLogger(DtuDevController.class);

	@Autowired
	private DtuDevService dtuDevService;

//	@Autowired
//	private DtuStsService dtuStsService;

	@RequestMapping(value = "/list")
	public String list(Model model, String queryName, String queryValue, Pageable pageable) {
		model.addAttribute("page", dtuDevService.findAllLike(pageable, queryName, queryValue));
		model.addAttribute("queryName", queryName);
		model.addAttribute("queryValue", queryValue);
		return "/faultDiag/dtuDev/list";
	}

	@RequestMapping(value = "/add")
	public String add(Model model) {
		model.addAttribute("sceneGroups", SceneGroup.values());
		return "/faultDiag/dtuDev/add";
	}

	@RequestMapping(value = "/edit/{id}")
	public String edit(Model model, @PathVariable("id") String id) {
		DtuDev dtuDev = dtuDevService.findById(id);
		model.addAttribute("sceneGroups", SceneGroup.values());
		model.addAttribute("dtuDev", dtuDev);
		return "/faultDiag/dtuDev/edit";
	}

	@RequestMapping(value = "/save")
	public String save(DtuDev dtuDev, RedirectAttributes ra) {
		try {
			dtuDevService.save(dtuDev);
			ra.addFlashAttribute("messageOK", "DTU设备保存成功");
		} catch (Exception e) {
			ra.addFlashAttribute("messageErr", "DTU设备保存异常");
			logger.error("保存DTU设备异常", e);
		}
		return "redirect:/admin/faultDiag/dtuDev/list";
	}

	@RequestMapping(value = "/delete")
	public String delete(String[] idGroup, RedirectAttributes ra) {
		try {
			dtuDevService.delete(idGroup);
			ra.addFlashAttribute("messageOK", "DTU设备删除成功");
		} catch (Exception e) {
			ra.addFlashAttribute("messageErr", "DTU设备删除异常");
			logger.error("保存DTU设备异常", e);
		}
		return "redirect:/admin/faultDiag/dtuDev/list";
	}
	
	@RequestMapping(value = "/config")
	public String config(Model model, String queryName, String queryValue, Pageable pageable) {
		model.addAttribute("page", dtuDevService.findAllLike(pageable, queryName, queryValue));
		model.addAttribute("queryName", queryName);
		model.addAttribute("queryValue", queryValue);
		return "/faultDiag/dtuDev/config";
	}
}
