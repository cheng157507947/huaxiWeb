package com.zncxi.huaxi.web.videoMonitor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zncxi.huaxi.domain.Scene;
import com.zncxi.huaxi.domain.VideoDev;
import com.zncxi.huaxi.service.SceneService;
import com.zncxi.huaxi.service.VideoDevService;
import com.zncxi.huaxi.util.enumeration.SceneGroup;

/**
 * 田间视频监控系统
 * @author xiaoCheng
 *
 */
@Controller
@RequestMapping(value = "/admin/videoMonitor")
public class VMIndexController {
	
	@Autowired
	private SceneService sceneService;
	@Autowired
	private VideoDevService videoDevService;

	@RequestMapping(value = "/index")
	public String index(){
		return "/videoMonitor/main/index";
	}
	
	@RequestMapping(value = "/index2")
	public String index2(){
		return "/videoMonitor/main/index2";
	}
	
	@RequestMapping(value = "/left")
	public String left(Model model){
		model.addAttribute("sceneTree", this.getVideoSceneTree(new SceneGroup[]{SceneGroup.ONE_SOUTH, SceneGroup.TWO_NORTH, SceneGroup.HEADWATERS, SceneGroup.WEETHER_SITE}));
		return "/videoMonitor/main/left";
	}
	
	@RequestMapping(value = "/video")
	public String video(){
		return "/videoMonitor/video";
	}
	
	@RequestMapping(value = "/playback")
	public String playback(){
		return "/videoMonitor/playback";
	}
	
	/**
	 * 获得视频场景树
	 * @return
	 */
	private String getVideoSceneTree(SceneGroup[] groups){
		StringBuffer sb = new StringBuffer();
		sb.append("[{\"id\":\"1\", \"pId\":\"0\", \"name\":\"农田水利物联网应用平台\", \"open\":true, \"iconSkin\":\"pIcon\"},");
		sb.append("{\"id\":\"2\", \"pId\":\"1\", \"name\":\"江阴华西村物联网监测中心\", \"open\":true, \"iconSkin\":\"pIcon00\"},");
		
		int groupId = 11;
		for(SceneGroup group: groups){
			sb.append("{\"id\":\""+groupId+"\", \"pId\":\"2\", \"name\":\""+group.getTitle()+"\", \"open\":true, \"iconSkin\":\"pIcon01\", " +
					"\"click\":\"\"},");
			List<Scene> scenes = this.sceneService.findSceneByGroup(group);
			for(Scene scene: scenes){
				String iconTop = "i";
				List<VideoDev> videoDevs = this.videoDevService.findVideoBySceneId(scene.getId());
				if(videoDevs.size() > 0){
					iconTop = "pI";
				}
				String icon = iconTop+"con02";  //田块场景
				if(scene.getType() == 2){ //泵房场景
					icon = iconTop+"con03";
				}else if(scene.getType() == 3){ //水质场景
					icon = iconTop+"con04";
				}else if(scene.getType() == 4){ //气象场景
					icon = iconTop+"con05";
				}
				sb.append("{\"id\":\""+scene.getId()+"\", \"pId\":\""+groupId+"\", \"name\":\""+scene.getName()+"\", \"iconSkin\":\""+icon+"\"," +
						"\"click\":\"\"},");
				for(VideoDev videoDev: videoDevs){
					sb.append("{\"id\":\""+videoDev.getId()+"\", \"pId\":\""+scene.getId()+"\", \"name\":\""+videoDev.getName()+"\", \"iconSkin\":\"icon01\"," +
							"\"click\":\"window.parent.frames['mainFrame'].showVideo('"+videoDev.getName()+"','"+videoDev.getIp()+"','"+videoDev.getPort()+"','"+videoDev.getUsername()+"','"+videoDev.getPassword()+"','"+videoDev.getGuid()+"','"+videoDev.getType()+"')\"},");
				}
			}
			groupId ++;
		}
		
		sb.delete(sb.length()-1, sb.length());
		sb.append("]");
		return sb.toString();
	}
}
