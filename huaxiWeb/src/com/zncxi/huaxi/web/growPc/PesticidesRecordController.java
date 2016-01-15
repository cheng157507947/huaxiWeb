package com.zncxi.huaxi.web.growPc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zncxi.base.controller.BaseController;
import com.zncxi.huaxi.domain.growpc.PesticidesRecord;
import com.zncxi.huaxi.service.growpc.PesticidesRecordService;

/**
 * 农作物生长及产量预测系统
 * @author xiaoCheng
 *
 */
@Controller
@RequestMapping(value = "/admin/growPc/pesticidesRecord")
public class PesticidesRecordController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(PesticidesRecordController.class);
	
	@Autowired
	private PesticidesRecordService pesticidesRecordService;

	@RequestMapping(value = "/list")
	public String list(@PageableDefaults(value = 10, sort = "date", sortDir = Direction.DESC)Pageable pageable, Model model, String beginTime, String endTime, String name){
		Page<PesticidesRecord> page = this.pesticidesRecordService.findAllLikeByNameAndDateBetween(pageable, beginTime, endTime, name);
		model.addAttribute("page", page);
		model.addAttribute("beginTime", beginTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("name", name);
		return "/growPc/pesticide/list";
	}
	
	@RequestMapping(value = "/add")
	public String add(){
		return "/growPc/pesticide/add";
	}
	
	@RequestMapping(value = "/edit")
	public String edit(Model model, String id){
		model.addAttribute("pesticidesRecord", this.pesticidesRecordService.findById(id));
		return "/growPc/pesticide/edit";
	}
	
	@RequestMapping(value = "/save")
	public String save(RedirectAttributes ra, PesticidesRecord pesticidesRecord){
		try {
			this.pesticidesRecordService.save(pesticidesRecord);
			ra.addFlashAttribute("okMsg", "农药信息保存成功。");
		} catch (Exception e) {
			logger.error("农药信息保存失败！",e);
			ra.addFlashAttribute("errMsg", "农药信息保存失败！");
		}
		return "redirect:/admin/growPc/pesticidesRecord/list";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(RedirectAttributes ra, String[] idGroup){
		try {
			this.pesticidesRecordService.delete(idGroup);
			ra.addFlashAttribute("okMsg", "农药信息删除成功！");
		} catch (Exception e) {
			logger.error("农药信息删除失败！",e);
			ra.addFlashAttribute("errMsg", "农药信息删除失败！");
		}
		return "redirect:/admin/growPc/pesticidesRecord/list";
	}
}
