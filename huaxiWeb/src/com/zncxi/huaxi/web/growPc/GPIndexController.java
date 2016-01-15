package com.zncxi.huaxi.web.growPc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zncxi.base.controller.BaseController;
import com.zncxi.huaxi.domain.growpc.PesticidesRecord;
import com.zncxi.huaxi.domain.growpc.PicPredict;
import com.zncxi.huaxi.domain.growpc.PicPredictInfo;
import com.zncxi.huaxi.service.growpc.PesticidesRecordService;
import com.zncxi.huaxi.service.growpc.PicPredictInfoService;
import com.zncxi.huaxi.service.growpc.PicPredictService;
import com.zncxi.huaxi.util.vo.PicPredictVO;

/**
 * 农作物生长及产量预测系统
 * @author xiaoCheng
 *
 */
@Controller
@RequestMapping(value = "/admin/growPc")
public class GPIndexController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(GPIndexController.class);
	
	@Autowired
	private PicPredictInfoService picPredictInfoService;
	@Autowired
	private PicPredictService picPredictService;
	@Autowired
	private PesticidesRecordService pesticidesRecordService;

	@RequestMapping(value = "/index")
	public String index(){
		
		return "/growPc/main/index";
	}
	
	@RequestMapping(value = "/picture")
	public String picture(Model model, RedirectAttributes ra){
		Page<PesticidesRecord> prs = this.pesticidesRecordService.findPage(new PageRequest(0, 1, new Sort(Direction.DESC, "date")));
		PicPredict picPredict = this.picPredictService.findNewOne();
		PesticidesRecord pesticidesRecord = null;
		if(prs.getContent() != null && prs.getContent().size() > 0){
			pesticidesRecord = prs.getContent().get(0);
		}
		if(picPredict == null){
			ra.addFlashAttribute("errMsg", "当前没有已经预测的信息，请先上传预测图片或等待预测完成。");
			ra.addFlashAttribute("indexTo", true);
			return "redirect:/admin/growPc/picPredict/list";
		}
		model.addAttribute("picPredictShow", this.setPicPredictVO(picPredict, pesticidesRecord));
		return "/growPc/picture";
	}
	
	@RequestMapping(value="/setup")
	public String setup(Model model){
		List<PicPredictInfo> infos = this.picPredictInfoService.findAll();
		if(infos != null && infos.size() > 0){
			model.addAttribute("picPredictInfo", infos.get(0));
		}
		return "/growPc/setup";
	}
	
	@RequestMapping(value="/setupSave")
	public String setupSave(RedirectAttributes ra, PicPredictInfo picPredictInfo){
		try {
			this.picPredictInfoService.save(picPredictInfo);
			ra.addFlashAttribute("okMsg", "图片预测配置保存成功");
		} catch (Exception e) {
			logger.error("图片预测配置保存失败！",e);
			ra.addFlashAttribute("errMsg", "图片预测配置保存失败！");
		}
		return "redirect:/admin/growPc/setup";
	}
	
	/**
	 * 设置预测显示VO
	 * @param picPredict
	 * @param pesticidesRecord
	 * @return
	 */
	private PicPredictVO setPicPredictVO(PicPredict picPredict, PesticidesRecord pesticidesRecord){
		String riceCycleStr = "采集时间错误";
		String cycleDescribe = "当前生长期监测错误，请检查上传图片信息";
		String pesticideDescribe = "系统中没有用药记录";
		String pesticideDesc = "系统中没有用药记录";
		String predictYieldStr = picPredict.getPredictYield()+" 斤";
		String totalYieldStr = picPredict.getReviseYield()+" 斤";
		
		String riceCycle = picPredict.getRiceCycle();
		int predictYield = picPredict.getPredictYield();
		if("1".equals(riceCycle)){
			riceCycleStr = "分蘖期";
			cycleDescribe = "对分蘖数量进行促控，建立合理的群体结构，增长根系的重要时期。";
		}else
		if("2".equals(riceCycle)){
			riceCycleStr = "拔节孕穗期";
			cycleDescribe = "水稻分裂末期，田间达到足够苗期的基础上，幼穗开始分化。";
		}else
		if("3".equals(riceCycle)){
			riceCycleStr = "抽穗扬花期";
			cycleDescribe = "叶片生长停止，穗粒生长，是决定实粒数多少的关键时期。";
		}else
		if("4".equals(riceCycle)){
			riceCycleStr = "灌浆成熟期";
			cycleDescribe = "水稻灌浆结实期，是从稻穗开花后到谷粒成熟的时期。";
		}
		
		if(predictYield == 0){
			predictYieldStr = "产量预测异常";
			totalYieldStr = "产量预测异常";
		}
		if(predictYield == -1){
			predictYieldStr = "非灌浆期无法预测";
			totalYieldStr = "非灌浆期无法预测";
		}
		if(predictYield == -2){
			predictYieldStr = "拍摄面积应大于10m2";
			totalYieldStr = "拍摄面积应大于10m2";
		}
		
		if(pesticidesRecord != null){
			pesticideDescribe = pesticidesRecord.getName()+"  "+pesticidesRecord.getAmount()+pesticidesRecord.getUnit();
			pesticideDesc = pesticidesRecord.getDesc();
		}
		
		PicPredictVO vo = new PicPredictVO();
		vo.setRiceCycle(riceCycleStr);
		vo.setSingleShowPath(picPredict.getSingleShowPath());
		vo.setCycleDescribe(cycleDescribe);
		vo.setPesticideDescribe(pesticideDescribe);
		vo.setPesticideDesc(pesticideDesc);
		vo.setTotalArea(picPredict.getTotalArea());
		vo.setTotalYield(totalYieldStr);
		vo.setPredictYield(predictYieldStr);
		vo.setArea(picPredict.getArea());
		vo.setCapTime(picPredict.getCapTime());
		vo.setFullShowPath(picPredict.getFullShowPath());
		
		return vo;
	}
}
