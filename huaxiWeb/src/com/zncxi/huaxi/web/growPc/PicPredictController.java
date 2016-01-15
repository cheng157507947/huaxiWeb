package com.zncxi.huaxi.web.growPc;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zncxi.base.controller.BaseController;
import com.zncxi.huaxi.domain.growpc.PicPredict;
import com.zncxi.huaxi.domain.growpc.PicPredictInfo;
import com.zncxi.huaxi.service.growpc.PicPredictInfoService;
import com.zncxi.huaxi.service.growpc.PicPredictService;
import com.zncxi.huaxi.util.FileUtil;
import com.zncxi.huaxi.util.upload.UploadImg;

/**
 * 图片预测管理
 * @author xiaoCheng
 *
 */
@Controller
@RequestMapping(value = "/admin/growPc/picPredict")
public class PicPredictController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(PicPredictController.class);
	
	@Autowired
	private PicPredictInfoService picPredictInfoService;
	@Autowired
	private PicPredictService picPredictService;

	@RequestMapping(value = "/list")
	public String list(@PageableDefaults(value = 5, sort = "capTime", sortDir = Direction.DESC)Pageable pageable, Model model, String beginTime, String endTime){
		Page<PicPredict> page = this.picPredictService.findAllLikeByDateBetween(pageable, beginTime, endTime, null);
		model.addAttribute("page", page);
		model.addAttribute("beginTime", beginTime);
		model.addAttribute("endTime", endTime);
		return "/growPc/upload/list";
	}
	
	@RequestMapping(value = "/hisList")
	public String hisList(@PageableDefaults(value = 5, sort = "capTime", sortDir = Direction.DESC)Pageable pageable, Model model, String beginTime, String endTime){
		Page<PicPredict> page = this.picPredictService.findAllLikeByDateBetween(pageable, beginTime, endTime, 0);
		model.addAttribute("page", page);
		model.addAttribute("beginTime", beginTime);
		model.addAttribute("endTime", endTime);
		return "/growPc/piedictHis";
	}
	
	@RequestMapping(value = "/add")
	public String add(RedirectAttributes ra){
		List<PicPredictInfo> infos = this.picPredictInfoService.findAll();
		if(infos == null || infos.size() <= 0){
			ra.addFlashAttribute("errMsg", "请先进行系统设置！预测总亩数与图片拍摄面积不能为空。");
			return "redirect:/admin/growPc/picPredict/list";
		}
		
		return "/growPc/upload/add";
	}
	
	@RequestMapping(value = "/save")
	public String save(RedirectAttributes ra, PicPredict picPredict, HttpServletRequest request){
		try {
			PicPredictInfo picPredictInfo = this.picPredictInfoService.findAll().get(0);
			picPredict.setAngle(picPredictInfo.getAngle());  //图片拍摄角度
			picPredict.setArea(picPredictInfo.getArea());  //图片拍摄面积
			picPredict.setPlantTime(picPredictInfo.getPlantTime());  //水稻种植时间
			picPredict.setTotalArea(picPredictInfo.getTotalArea());  //总亩数
			picPredict.setNeedPredict(1);  //需要预测
			
			this.picPredictService.save(picPredict);
			ra.addFlashAttribute("okMsg", "预测图片上传信息保存成功，请等待后台预测。");
		} catch (Exception e) {
			logger.error("预测图片上传信息保存失败！",e);
			ra.addFlashAttribute("errMsg", "预测图片上传信息保存失败！");
		}
		return "redirect:/admin/growPc/picPredict/list";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(RedirectAttributes ra, String[] idGroup){
		try {
			List<PicPredict> picPredicts = this.picPredictService.findByIdIn(idGroup);
			for(PicPredict picPredict: picPredicts){
				FileUtil.delete(picPredict.getFullPath());
				FileUtil.delete(picPredict.getSinglePath());
			}
			this.picPredictService.delete(picPredicts);
			ra.addFlashAttribute("okMsg", "预测图片信息删除成功！");
		} catch (Exception e) {
			logger.error("预测图片信息删除失败！",e);
			ra.addFlashAttribute("errMsg", "预测图片信息删除失败！");
		}
		return "redirect:/admin/growPc/picPredict/list";
	}
	
	@RequestMapping(value = "/upload")
	@ResponseBody
	public String upload(MultipartHttpServletRequest multipartRequest,HttpServletRequest request){
		MultipartFile imgfile = multipartRequest.getFile("imgFile");
		String separator = File.separator;
		String savePath = request.getSession().getServletContext().getRealPath(separator) + "images"+separator+"growPc"+separator+"uploadImg"+separator;
		String saveUrl  = "images/growPc/uploadImg/";
		String json = new UploadImg().uploadImg(imgfile, savePath, saveUrl);
		return json;
	}
	
	@RequestMapping(value = "/reviseYield")
	@ResponseBody
	public String reviseYield(String id, Double yield){
		try {
			PicPredict picPredict = this.picPredictService.findById(id);
			if(picPredict != null){
				picPredict.setReviseYield(yield);
				this.picPredictService.save(picPredict);
				return "{\"ok\":true,\"reviseYield\":\""+yield+"\"}";
			}
		} catch (Exception e) {
			logger.error("id："+id+" 修正总产量出现未知错误！",e);
		}
		return "{\"ok\":false}";
	}
}
