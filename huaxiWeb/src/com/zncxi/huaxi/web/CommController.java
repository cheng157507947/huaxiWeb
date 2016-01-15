package com.zncxi.huaxi.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zncxi.huaxi.domain.CollHisData;
import com.zncxi.huaxi.domain.CollLatestData;
import com.zncxi.huaxi.domain.ControlDev;
import com.zncxi.huaxi.domain.ControlDevOperate;
import com.zncxi.huaxi.domain.ControlDevSts;
import com.zncxi.huaxi.domain.DtuDev;
import com.zncxi.huaxi.domain.DtuSts;
import com.zncxi.huaxi.domain.Scene;
import com.zncxi.huaxi.domain.User;
import com.zncxi.huaxi.domain.VideoDev;
import com.zncxi.huaxi.handle.CollDataHandleService;
import com.zncxi.huaxi.handle.ExcelHandleService;
import com.zncxi.huaxi.service.CollHisDataService;
import com.zncxi.huaxi.service.CollLatestDataService;
import com.zncxi.huaxi.service.ControlDevOperateService;
import com.zncxi.huaxi.service.ControlDevService;
import com.zncxi.huaxi.service.ControlDevStsService;
import com.zncxi.huaxi.service.DtuStsService;
import com.zncxi.huaxi.service.SceneService;
import com.zncxi.huaxi.service.VideoDevService;
import com.zncxi.huaxi.util.enumeration.SceneGroup;

/**
 * 公共业务处理
 * @author xiaoCheng
 *
 */
@Controller
@RequestMapping(value ="/admin")
public class CommController {

	private Logger logger = LoggerFactory.getLogger(CommController.class);
	
	@Autowired
	private SceneService sceneService;
//	@Autowired
//	private DtuDevService dtuDevService;
	@Autowired
	private DtuStsService dtuStsService;
	@Autowired
	private VideoDevService videoDevService;
	@Autowired
	private CollLatestDataService collLatestDataService;
	@Autowired
	private CollHisDataService collHisDataService;
	@Autowired
	private CollDataHandleService collDataHandleService;
	@Autowired
	private ControlDevService controlDevService;
	@Autowired
	private ControlDevOperateService controlDevOperateService;
	@Autowired
	private ControlDevStsService controlDevStsService;
	@Autowired
	private ExcelHandleService excelHandleService;

	@RequestMapping("/findSceneById")
	@ResponseBody
	public String findSceneById(String id){
		Scene scene = this.sceneService.findById(id);
		if(scene != null){
			return "{\"id\":\""+scene.getId()+"\",\"name\":\""+scene.getName()+"\",\"group\":\""+scene.getGroup().getValue()+"\",\"type\":\""+scene.getType()+"\"}";
		}
		return "";
	}
	
	/**
	 * 获得设备信息和状态信息
	 * @param sceneId
	 * @return 设备ID，设备类型，设备采集配置，设备状态，最近通讯时间，设备故障状态
	 */
	@RequestMapping(value = "/findDtuDevAndStsBySceneId")
	@ResponseBody
	public String findDtuDevAndStsBySceneId(String sceneId){
		try {
			DtuDev dtuDev = this.sceneService.findById(sceneId).getDtuDev();
			DtuSts dtuSts = this.dtuStsService.findById(dtuDev.getId());
			if(dtuSts != null){
				return "{\"devId\":\""+dtuDev.getId()+"\",\"devType\":\""+dtuDev.getType()+"\",\"devConfig\":\""+dtuDev.getConfig()+"\",\"online\":\""+dtuSts.getOnline()+"\"," +
						"\"commTime\":\""+dtuSts.getCommTime()+"\",\"defSts\":\""+dtuSts.getDefSts()+"\"}";
			}
		} catch (Exception e) {
			logger.error("根据场景ID获得设备信息和状态信息时出现未知错误",e);
		}
		return "{}";
	}
	
	
	/**
	 * 获得场景视频信息
	 * @param sceneId
	 * @return 视频设备ID，视频名称，IP，端口，用户名，密码，GUID
	 */
	@RequestMapping(value = "/findVideoBySceneId")
	@ResponseBody
	public String findVideoBySceneId(String sceneId){
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		
		List<VideoDev> videoDevs = this.videoDevService.findVideoBySceneId(sceneId);
		for(VideoDev videoDev: videoDevs){
			sb.append("{\"id\":\""+videoDev.getId()+"\",\"name\":\""+videoDev.getName()+"\",\"ip\":\""+videoDev.getIp()+"\"," +
					"\"port\":\""+videoDev.getPort()+"\",\"username\":\""+videoDev.getUsername()+"\",\"password\":\""+videoDev.getPassword()+"\",\"guid\":\""+videoDev.getGuid()+"\",\"type\":\""+videoDev.getType()+"\"},");
		}
		if(sb.length() > 2){
			sb.delete(sb.length()-1, sb.length());
		}
		sb.append("]");
		return sb.toString();
	}
	
	/**
	 * 根据场景ID，获得最新数据
	 * @param sceneId
	 * @return
	 */
	@RequestMapping(value = "/findRealTimeDataBySceneId")
	@ResponseBody
	public String findRealTimeDataBySceneId(String sceneId){
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		
		try {
			Scene scene = this.sceneService.findById(sceneId);
			DtuDev dtuDev = scene.getDtuDev();
			if(dtuDev != null){
				CollLatestData collLatestData = this.collLatestDataService.findByDtuDevId(dtuDev.getId());
				int config = dtuDev.getConfig();
				int type = dtuDev.getType();
				
				sb.append("\"type\":"+scene.getType()+",\"collTime\":\""+collLatestData.getCollTime()+"\",");
				if(type == 1 && scene.getType() == 1){  //田块数据
					this.collDataHandleService.setFieldData(config, collLatestData, sb);
				}else if(type != 1 &&scene.getType() == 2){  //泵房数据
					this.collDataHandleService.setPumpData(config, collLatestData, sb);
				}else if(type == 2 &&scene.getType() == 3){  //水质数据
					this.collDataHandleService.setWaterData(config, collLatestData, sb);
				}else if(type == 3 &&scene.getType() == 4){  //气象数据
					this.collDataHandleService.setWeatherData(config, collLatestData, sb);
				}
				sb.delete(sb.length()-1, sb.length());
				sb.append("}");
				return sb.toString();
			}
		} catch (Exception e) {
			logger.error("根据场景ID查询最新数据时出现未知错误",e);
		}
		return "{}";
	}
	
	/**
	 * 根据分组获得场景组
	 * @param group
	 * @return
	 */
	@RequestMapping(value = "/findSceneByGroup")
	@ResponseBody
	public String findSceneByGroup(int group){
		String result = "";
		
		SceneGroup sceneGroup = SceneGroup.values()[group];
		List<Scene> scenes = this.sceneService.findSceneByGroup(sceneGroup);
		for(Scene scene: scenes){
			result += ("{\"id\":\""+scene.getId()+"\",\"name\":\""+scene.getName()+"\"},");
		}
		
		if(result.length() > 0){
			result = result.substring(0, result.length()-1);
		}
		return "["+result+"]";
	}
	
	/**
	 * 根据场景ID获得控制设备信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findControlDevBySceneId")
	@ResponseBody
	public String findControlDevBySceneId(String sceneId, HttpSession session){
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		
		try {
			User user = (User)session.getAttribute("user");
			sb.append("\"userType\":\"").append(user.getType()).append("\",");
			Scene scene = this.sceneService.findById(sceneId);
			DtuDev dtuDev = scene.getDtuDev();
			if(dtuDev != null){
				sb.append("\"sceneId\":\"").append(scene.getId()).append("\",\"dtuId\":\"").append(dtuDev.getId()).append("\",\"controls\":[");
				StringBuffer cons = new StringBuffer();
				List<ControlDev> controlDevs = this.controlDevService.findControlDevByDtuDevId(dtuDev.getId());
				for(ControlDev controlDev: controlDevs){
					ControlDevSts controlDevSts = this.controlDevStsService.findById(controlDev.getId());
					cons.append("{\"id\":\"").append(controlDev.getId()).append("\",\"name\":\"").append(controlDev.getName()).append("\",\"wlgIndex\":\"").append(controlDev.getWlgIndex());
					cons.append("\",\"state\":\"").append(controlDevSts.getState()).append("\",\"crlTime\":\"").append(controlDevSts.getCtlTime()).append("\",\"defSts\":\"").append(controlDevSts.getDefSts()).append("\"},");
				}
				
				if(cons.length() > 0){
					cons.delete(cons.length()-1, cons.length());
				}
				sb.append(cons.toString()).append("]}");
				return sb.toString();
			}
		} catch (Exception e) {
			logger.error("根据场景ID查询控制设备信息时出现未知错误",e);
		}
		return "{}";
	}
	
	/**
	 * 操作控制同DTU下的一组设备
	 * @param controlId  控制设备ID组
	 * @param state  目标状态
	 * @return
	 */
	@RequestMapping(value = "/operateControlDev")
	@ResponseBody
	public String operateControlDev(String controlIds, Integer state){
		StringBuffer sb = new StringBuffer();
		sb.append("{\"ok\":"+true+",\"controlIds\":[");
		
		try {
			String[] controlIdArray = controlIds.split(",");
			if(controlIdArray.length > 0){
				ControlDev controlDev = this.controlDevService.findById(controlIdArray[0]);
				DtuSts dtuSts = this.dtuStsService.findById(controlDev.getDtuDev().getId());
				if(dtuSts.getOnline() == 0){
					return "{\"errorMsg\":\"当前DTU设备离线，无法控制\",\"ok\":"+false+"}";
				}
			}
			
			StringBuffer cons = new StringBuffer();
			for(String controlId: controlIdArray){
				ControlDevSts controlDevSts = this.controlDevStsService.findById(controlId);
				if(controlDevSts != null){
					this.controlDevOperateService.operateControlDev(controlDevSts.getId(), state);
					cons.append("\""+controlId+"\",");
				}
			}
			
			if(cons.length() > 0){
				cons.delete(cons.length()-1, cons.length());
			}
			sb.append(cons.toString());
			
			return sb.append("]}").toString();
		} catch (Exception e) {
			logger.error("根据控制设备ID操作控制时出现未知错误",e);
		}
		return "{\"errorMsg\":\"设备操作出现问题\",\"ok\":"+false+"}";
	}
	
	/**
	 * 根据场景查询控制设备状态信息 
	 * state: -1.故障，0.关，1.开，2.操作执行中
	 * @param controlIds
	 * @return 
	 */
	@RequestMapping(value = "/findControlDevStsBySceneId")
	@ResponseBody
	public String findControlDevStsBySceneId(String sceneId){
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		
		try {
			DtuDev dtuDev = this.sceneService.findById(sceneId).getDtuDev();
			if(dtuDev != null){
				StringBuffer cons = new StringBuffer();
				List<ControlDev> controlDevs = this.controlDevService.findControlDevByDtuDevId(dtuDev.getId());
				for(ControlDev controlDev: controlDevs){
					int state = 0;
					
					ControlDevSts controlDevSts = this.controlDevStsService.findById(controlDev.getId());
					state = controlDevSts.getState();
					if(controlDevSts.getDefSts() == 0){
						state = -1;
					}
					cons.append("{\"controlId\":\"").append(controlDev.getId());
					cons.append("\",\"state\":\"").append(state).append("\"},");
				}
				
				if(cons.length() > 0){
					cons.delete(cons.length()-1, cons.length());
				}
				sb.append(cons.toString());
			}
			return sb.append("]").toString();
		} catch (Exception e) {
			logger.error("根据场景ID获取控制设备状态信息 时出现未知错误",e);
		}
		return "[]";
	}
	
	/**
	 * 根据设备ID组查询控制命令状态信息 
	 * state: -1.出错，0.操作请求，1.命令下发，2.操作中，3.成功，4.失败，5.超时
	 * @param controlIds
	 * @return 
	 */
	@RequestMapping(value = "/findControlDevOperate")
	@ResponseBody
	public String findControlDevOperate(String controlIds){
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		
		try {
			String[] controlIdArray = controlIds.split(",");
			StringBuffer cons = new StringBuffer();
			for(String controlId: controlIdArray){
				if(StringUtils.isEmpty(controlId)) continue;
				
				int state = 0;  
				ControlDevOperate controlDevOperate = this.controlDevOperateService.findById(controlId);
				if(controlDevOperate != null){
					if(controlDevOperate.getState() == 1){  //操作请求
						state = 0;
					}else
					if(controlDevOperate.getState() == 2){  //命令下发
						state = 1;
					}else 
					if(controlDevOperate.getState() == 0 && controlDevOperate.getResult() == 0){  //操作中
						state = 2;
					}else
					if(controlDevOperate.getState() == 0 && controlDevOperate.getResult() == 1){  //成功
						state = 3;
					}else
					if(controlDevOperate.getState() == 0 && controlDevOperate.getResult() == 2){  //失败
						state = 4;
					}else
					if(controlDevOperate.getState() == 0 && controlDevOperate.getResult() == 3){  //超时
						state = 5;
					}else{
						state = -1;
					}
				}else{
					state = -1;
				}
				cons.append("{\"controlId\":\"").append(controlId);
				cons.append("\",\"state\":\"").append(state).append("\"},");
			}
			if(cons.length() > 0){
				cons.delete(cons.length()-1, cons.length());
			}
			sb.append(cons.toString());
			
			return sb.append("]").toString();
		} catch (Exception e) {
			logger.error("根据设备ID组查询控制命令状态信息 时出现未知错误",e);
		}
		return "[]";
	}
	
	/**
	 * 根据场景ID获得历史曲线
	 * @param Id
	 * @return
	 */
	@RequestMapping(value = "/findChartsBySceneId")
	@ResponseBody
	public String findChartsBySceneId(String id, String beginTime, String endTime){
		StringBuffer dataCharts = new StringBuffer();
		dataCharts.append("[");
		
		try {
			Scene scene = this.sceneService.findById(id);
			DtuDev dtuDev = scene.getDtuDev();
			if(dtuDev != null){
				beginTime += " 00:00:00";
				endTime += " 23:59:59";
				Date begin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beginTime);
				Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);
				StringBuffer datasb = new StringBuffer();
				
				List<CollHisData> datas = this.collHisDataService.findByDtuDevIdAndCollTimeBetween(dtuDev.getId(), begin, end);
				if(scene.getType() == 1){ //田块数据
					collDataHandleService.setFieldDataCharts(dtuDev.getConfig(), datas, datasb);
				}
				if(scene.getType() == 2){ //泵房数据
					collDataHandleService.setPumpDataCharts(dtuDev.getConfig(), datas, datasb);
				}
				if(scene.getType() == 3){ //水质数据
					collDataHandleService.setWaterDataCharts(dtuDev.getConfig(), datas, datasb);
				}
				if(scene.getType() == 4){ //气象数据
					collDataHandleService.setWeatherDataCharts(dtuDev.getConfig(), datas, datasb);
				}
				
				if(datasb.length() > 1){
					datasb.delete(datasb.length()-1, datasb.length());
				}
				dataCharts.append(datasb.toString()).append("]");
				return dataCharts.toString();
			}
		} catch (Exception e) {
			logger.error("根据场景ID获得历史曲线时出现未知错误",e);
		}
		return "[]";
	}
	
	/**
	 * 根据场景ID获得历史数据
	 * @param Id
	 * @return
	 */
	@RequestMapping(value = "/findDateQueryBySceneId")
	@ResponseBody
	public String findDateQueryBySceneId(String id, String beginTime, String endTime, Integer pageNo){
		StringBuffer dataQuery = new StringBuffer();
		
		try {
			Scene scene = this.sceneService.findById(id);
			DtuDev dtuDev = scene.getDtuDev();
			if(dtuDev != null){
				beginTime += " 00:00:00";
				endTime += " 23:59:59";
				Date begin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beginTime);
				Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);
				Pageable pageable = new PageRequest(pageNo-1, 100, new Sort(Direction.DESC, "collTime"));
				
				Page<CollHisData> datas = this.collHisDataService.findByDtuDevIdAndCollTimeBetween(dtuDev.getId(), begin, end, pageable);
				dataQuery.append("{\"page\":{\"pageNo\":\"").append(datas.getNumber()+1).append("\",\"pageSize\":\"").append(datas.getSize()).append("\",\"totalPages\":\"").append(datas.getTotalPages()).append("\",\"prePage\":\"").append(pageNo>1?pageNo-1:pageNo).append("\",\"nextPage\":\"").append(pageNo+1).append("\"},");
				dataQuery.append("\"allData\":[");
				
				StringBuffer datasb = new StringBuffer();
				if(datas.getContent() != null && datas.getContent().size() > 0){
					if(scene.getType() == 1){ //田块数据
						collDataHandleService.setFieldDataQuery(dtuDev.getConfig(), datas.getContent(), datasb);
					}
					if(scene.getType() == 2){ //泵房数据 
						collDataHandleService.setPumpDataQuery(dtuDev.getConfig(), datas.getContent(), datasb);
					}
					if(scene.getType() == 3){ //水质数据
						collDataHandleService.setWaterDataQuery(dtuDev.getConfig(), datas.getContent(), datasb);
					}
					if(scene.getType() == 4){ //气象数据
						collDataHandleService.setWeatherDataQuery(dtuDev.getConfig(), datas.getContent(), datasb);
					}
					
					if(datasb.length() > 1){
						datasb.delete(datasb.length()-1, datasb.length());
					}
				}
				dataQuery.append(datasb.toString()).append("]}");
				return dataQuery.toString();
			}
		} catch (Exception e) {
			logger.error("根据场景ID获得历史曲线时出现未知错误",e);
		}
		return "[]";
	}
	
	/**
	 * 根据场景ID和时间检查历史数据条数
	 * @param Id
	 * @return
	 */
	@RequestMapping(value = "/verificationDateQuery")
	@ResponseBody
	public String verificationDateQuery(String id, String beginTime, String endTime, Integer pageNo){
		StringBuffer dataQuery = new StringBuffer();
		
		try {
			Scene scene = this.sceneService.findById(id);
			DtuDev dtuDev = scene.getDtuDev();
			if(dtuDev != null){
				beginTime += " 00:00:00";
				endTime += " 23:59:59";
				Date begin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beginTime);
				Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);
				
				long count = this.collHisDataService.findCountByDtuDevIdAndCollTimeBetween(dtuDev.getId(), begin, end);
				if(count > 50000){
					dataQuery.append("{\"message\":false}");
				}else{
					dataQuery.append("{\"message\":true}");
				}
				return dataQuery.toString();
			}
		} catch (Exception e) {
			logger.error("根据场景ID和时间查询历史数据条数出现未知错误",e);
		}
		return "{}";
	}
	
	/**
	 * 历史数据导出execl
	 * @param queryName
	 * @param queryValue
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@RequestMapping(value="/findDateQueryToExecl")
	@ResponseBody
	public ModelAndView findDateQueryToExecl(String id, String beginTime, String endTime) {
		return this.excelHandleService.findExcelDateQueryBySceneId(id, beginTime, endTime);
	}
	
	/**
	 * 获得场景树
	 * @return
	 */
	public String getSceneTree(SceneGroup[] groups){
		StringBuffer sb = new StringBuffer();
		sb.append("[{\"id\":\"1\", \"pId\":\"0\", \"name\":\"农田水利物联网应用平台\", \"open\":true, \"iconSkin\":\"pIcon\"},");
		sb.append("{\"id\":\"2\", \"pId\":\"1\", \"name\":\"江阴华西村物联网监测中心\", \"open\":true, \"iconSkin\":\"pIcon00\"},");
		
		int groupId = 11;
		for(SceneGroup group: groups){
			sb.append("{\"id\":\""+groupId+"\", \"pId\":\"2\", \"name\":\""+group.getTitle()+"\", \"open\":true, \"iconSkin\":\"pIcon01\", " +
					"\"click\":\"window.parent.frames['mainFrame'].echoSceneTree('"+group+"','"+group.getTitle()+"','-1','-1')\"},");
			List<Scene> scenes = this.sceneService.findSceneByGroup(group);
			for(Scene scene: scenes){
				String icon = "icon01";  //田块场景
				if(scene.getType() == 2){ //泵房场景
					icon = "icon02";
				}else if(scene.getType() == 3){ //水质场景
					icon = "icon03";
				}else if(scene.getType() == 4){ //气象场景
					icon = "icon04";
				}
				sb.append("{\"id\":\""+scene.getId()+"\", \"pId\":\""+groupId+"\", \"name\":\""+scene.getName()+"\", \"iconSkin\":\""+icon+"\"," +
						"\"click\":\"window.parent.frames['mainFrame'].echoSceneTree('"+scene.getId()+"','"+scene.getName()+"','"+scene.getType()+"','"+scene.getGroup().getValue()+"')\"},");
			}
			groupId ++;
		}
		
		sb.delete(sb.length()-1, sb.length());
		sb.append("]");
		return sb.toString();
	}
	
	/**
	 * 根据场景ID和控制设备绑定的传感器 获得所有控制设备
	 * @param sceneId
	 * @param controlIndex
	 * @return
	 */
	@RequestMapping(value = "/findControlDevByControlIndex")
	@ResponseBody
	public String findControlDevByControlIndex(String sceneId, Integer controlIndex,Integer state){
		StringBuffer sb = new StringBuffer();
		sb.append("{\"sceneId\":\"").append(sceneId).append("\",");
		sb.append("\"state\":").append(state).append(",\"cons\":[");
		
		try {
			DtuDev dtuDev = this.sceneService.findById(sceneId).getDtuDev();
			if(dtuDev != null){
				StringBuffer cons = new StringBuffer();
				List<ControlDev> controlDevs = this.controlDevService.findControlDevByDtuDevIdAndWlgIndex(dtuDev.getId(), controlIndex);
				for(ControlDev controlDev: controlDevs){
					cons.append("{\"conId\":\"").append(controlDev.getId()).append("\",\"name\":\"").append(controlDev.getName()).append("\"},");
				}
				if(cons.length() > 1){
					cons.delete(cons.length()-1, cons.length());
				}
				sb.append(cons).append("]}");
				return sb.toString();
			}
		} catch (Exception e) {
			logger.error("根据场景ID和控制设备绑定的传感器 获得所有控制设备时出现未知错误",e);
		}
		return sb.append("]}").toString();
	}
}
