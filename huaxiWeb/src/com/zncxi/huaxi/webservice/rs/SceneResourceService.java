package com.zncxi.huaxi.webservice.rs;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zncxi.huaxi.domain.DtuDev;
import com.zncxi.huaxi.domain.DtuSts;
import com.zncxi.huaxi.domain.Scene;
import com.zncxi.huaxi.service.DtuStsService;
import com.zncxi.huaxi.service.SceneService;
import com.zncxi.huaxi.util.enumeration.SceneGroup;
import com.zncxi.huaxi.webservice.dto.SceneDTO;
import com.zncxi.huaxi.webservice.exception.WebExceptionFactory;
import com.zncxi.huaxi.webservice.security.Secure;

/**
 * 场景信息查询接口
 * @author xiaoCheng
 *
 */
@Component
@Path("/scene")
@Produces({ MediaType.APPLICATION_XML + ";charset=UTF-8", MediaType.APPLICATION_JSON + ";charset=UTF-8" })
public class SceneResourceService {

	private static Logger logger = LoggerFactory.getLogger(SceneResourceService.class);
	
	@Autowired
	private SceneService sceneService;
	@Autowired
	private DtuStsService dtuStsService;
	
	@GET
	@Secure
	@Path("/list")
	public List<SceneDTO> getSceneList(@HeaderParam("authCode")String code, @QueryParam("group")Integer group){
		try{
			List<SceneDTO> dtos = new ArrayList<SceneDTO>();
			List<Scene> list = this.sceneService.findSceneByGroup(SceneGroup.values()[group]);
			for(Scene scene: list){
				SceneDTO dto = new SceneDTO();
				dto.id = scene.getId();
				dto.name = scene.getName();
				dto.type = scene.getType();
				dto.group = scene.getGroup().getValue();
				dto.desc = scene.getDesc();
				
				DtuDev dtuDev = scene.getDtuDev();
				if(dtuDev != null){
					DtuSts dtuSts = this.dtuStsService.findById(dtuDev.getId());
					dto.online = dtuSts.getOnline();
					dto.defSts = dtuSts.getDefSts();
					dto.commTime = dtuSts.getCommTime();
				}
				
				dtos.add(dto);
			}
			return dtos;
		} catch (RuntimeException e) {
			throw WebExceptionFactory.buildDefaultException(e, logger);
		}
	}
}
