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

import com.zncxi.huaxi.domain.ControlDev;
import com.zncxi.huaxi.service.ControlDevService;
import com.zncxi.huaxi.service.DtuDevService;

@Controller
@RequestMapping(value = "/admin/faultDiag/controlDev")
public class ControlDevController {

	private Logger logger = LoggerFactory.getLogger(ControlDevController.class);

	@Autowired
	private ControlDevService controlDevService;

	@Autowired
	private DtuDevService dtuDevService;

	@RequestMapping(value = "/list")
	public String list(Model model, String selDtu, Pageable pageable) {
		model.addAttribute("dtuDevs", dtuDevService.findAll());
		model.addAttribute("page", controlDevService.findAllEq(pageable, "dtuDev.id", selDtu));
		model.addAttribute("selDtu", selDtu);
		return "/faultDiag/controlDev/list";
	}

	@RequestMapping(value = "/add")
	public String add(Model model) {
		model.addAttribute("dtuDevs", dtuDevService.findAll());
		return "/faultDiag/controlDev/add";
	}

	@RequestMapping(value = "/edit/{id}")
	public String edit(Model model, @PathVariable("id") String id) {
		model.addAttribute("dtuDevs", dtuDevService.findAll());
		model.addAttribute("controlDev", controlDevService.findById(id));
		return "/faultDiag/controlDev/edit";
	}

	@RequestMapping(value = "/save")
	public String save(ControlDev controlDev, RedirectAttributes ra) {
		try {
			controlDevService.save(controlDev);
			ra.addFlashAttribute("messageOK", "保存成功");
		} catch (Exception e) {
			ra.addFlashAttribute("messageErr", "保存异常");
			logger.error("保存用户异常", e);
		}
		return "redirect:/admin/faultDiag/controlDev/list";
	}

	@RequestMapping(value = "/delete")
	public String delete(String[] idGroup, RedirectAttributes ra) {
		try {
			controlDevService.delete(idGroup);
			ra.addFlashAttribute("messageOK", "删除成功");
		} catch (Exception e) {
			ra.addFlashAttribute("messageErr", "删除异常");
			logger.error("保存用户异常", e);
		}
		return "redirect:/admin/faultDiag/controlDev/list";
	}
}
