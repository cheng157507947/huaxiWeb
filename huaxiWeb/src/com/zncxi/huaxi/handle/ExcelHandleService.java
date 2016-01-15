package com.zncxi.huaxi.handle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.zncxi.huaxi.domain.CollHisData;
import com.zncxi.huaxi.domain.DtuDev;
import com.zncxi.huaxi.domain.Scene;
import com.zncxi.huaxi.service.CollHisDataService;
import com.zncxi.huaxi.service.SceneService;
import com.zncxi.huaxi.util.enumeration.SceneGroup;
import com.zncxi.huaxi.util.view.FieldHisDataViewExcel;
import com.zncxi.huaxi.util.view.PumpHisDataViewExcel;
import com.zncxi.huaxi.util.view.WaterHisDataViewExcel;
import com.zncxi.huaxi.util.view.WeatherHisDataViewExcel;

/**
 * 导出Excel数据处理
 * @author xiaoCheng
 *
 */
@Component
public class ExcelHandleService {
	
	private Logger logger = LoggerFactory.getLogger(ExcelHandleService.class);
	
	@Autowired
	private SceneService sceneService;
	@Autowired
	private CollHisDataService collHisDataService;

	public ModelAndView findExcelDateQueryBySceneId(String id, String beginTime, String endTime){
		AbstractExcelView viewExcel = null;
		Map<String, Object> model = new HashMap<String, Object>();
		
		try {
			Scene scene = this.sceneService.findById(id);
			DtuDev dtuDev = scene.getDtuDev();
			if(dtuDev != null){
				beginTime += " 00:00:00";
				endTime += " 23:59:59";
				Date begin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beginTime);
				Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);
				
				List<CollHisData> datas = this.collHisDataService.findByDtuDevIdAndCollTimeBetween(dtuDev.getId(), begin, end);
				model.put("sceneName", this.getTitleName(scene.getGroup(), scene.getName()));
				model.put("datas", datas);
				
				if(datas != null && datas.size() > 0){
					if(scene.getType() == 1){ //田块数据
						viewExcel = new FieldHisDataViewExcel();
						return new ModelAndView(viewExcel, model);
					}
					if(scene.getType() == 2){ //泵房数据 
						viewExcel = new PumpHisDataViewExcel();
						return new ModelAndView(viewExcel, model);
					}
					if(scene.getType() == 3){ //水质数据
						viewExcel = new WaterHisDataViewExcel();
						return new ModelAndView(viewExcel, model);
					}
					if(scene.getType() == 4){ //气象数据
						viewExcel = new WeatherHisDataViewExcel();
						return new ModelAndView(viewExcel, model);
					}
				}
			}
		} catch (Exception e) {
			logger.error("根据场景ID和时间获得历史数据Excel表时出现未知错误",e);
		}
		return null;
	}
	
	/**
	 * 获得页面显示场景名称
	 * @param id
	 * @return
	 */
	private String getTitleName(SceneGroup group, String sceneName){
		String title = "-"+sceneName;
		if(group == SceneGroup.ONE_SOUTH){
			title = "华西南区"+title;
		}else if(group == SceneGroup.TWO_NORTH){
			title = "华西北区"+title;
		}else{
			title = sceneName;
		}
		return title;
	}
}
