package com.zncxi.huaxi.webservice.rs;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zncxi.huaxi.domain.ControlDev;
import com.zncxi.huaxi.domain.ControlDevOperate;
import com.zncxi.huaxi.domain.ControlDevSts;
import com.zncxi.huaxi.domain.DtuDev;
import com.zncxi.huaxi.domain.DtuSts;
import com.zncxi.huaxi.domain.Scene;
import com.zncxi.huaxi.service.ControlDevOperateService;
import com.zncxi.huaxi.service.ControlDevService;
import com.zncxi.huaxi.service.ControlDevStsService;
import com.zncxi.huaxi.service.DtuStsService;
import com.zncxi.huaxi.service.SceneService;
import com.zncxi.huaxi.webservice.dto.controldev.ControlDevDTO;
import com.zncxi.huaxi.webservice.dto.controldev.OperateStateDTO;
import com.zncxi.huaxi.webservice.dto.controldev.OperaterDTO;
import com.zncxi.huaxi.webservice.exception.WebExceptionFactory;
import com.zncxi.huaxi.webservice.security.Secure;

/**
 * 控制设备接口
 * @author xiaoCheng
 *
 */
@Component
@Path("/controlDev")
@Produces({ MediaType.APPLICATION_XML + ";charset=UTF-8", MediaType.APPLICATION_JSON + ";charset=UTF-8" })
public class ContorlDevResourceService {

	private static Logger logger = LoggerFactory.getLogger(ContorlDevResourceService.class);
	
	@Autowired
	private SceneService sceneService;
	@Autowired
	private ControlDevService controlDevService;
	@Autowired
	private ControlDevStsService controlDevStsService;
	@Autowired
	private DtuStsService dtuStsService;
	@Autowired
	private ControlDevOperateService controlDevOperateService;
	
	@GET
	@Secure
	@Path("/list")
	public List<ControlDevDTO> getControlDevList(@HeaderParam("authCode")String code, @QueryParam("sceneId")String sceneId){
		try{
			List<ControlDevDTO> dtos = new ArrayList<ControlDevDTO>();
			Scene scene = this.sceneService.findById(sceneId);
			DtuDev dtuDev = scene.getDtuDev();
			if(dtuDev != null){
				List<ControlDev> controlDevs = this.controlDevService.findControlDevByDtuDevId(dtuDev.getId());
				for(ControlDev controlDev: controlDevs){
					ControlDevSts controlDevSts = this.controlDevStsService.findById(controlDev.getId());
					
					ControlDevDTO dto = new ControlDevDTO();
					dto.id = controlDev.getId();
					dto.name = controlDev.getName();
					dto.state = controlDevSts.getState();
					dto.defSts = controlDevSts.getDefSts();
					dto.ctlTime = controlDevSts.getCtlTime();
					dtos.add(dto);
				}
			}
			return dtos;
		} catch (RuntimeException e) {
			throw WebExceptionFactory.buildDefaultException(e, logger);
		}
	}
	
	@POST
	@Secure
	@Path("/operate")
	public Response operate(@HeaderParam("authCode")String code, OperaterDTO dto){
		try {
			String[] controlIdArray = dto.controlIds.split(",");
			for(String controlId: controlIdArray){
				ControlDev controlDev = this.controlDevService.findById(controlId);
				DtuSts dtuSts = this.dtuStsService.findById(controlDev.getDtuDev().getId());
				if(dtuSts.getOnline() == 0){
					logger.error("控制设备："+controlId+" 所属的DTU离线");
					return Response.status(Status.FORBIDDEN).build();
				}
				
				ControlDevSts controlDevSts = this.controlDevStsService.findById(controlId);
				if(controlDevSts != null){
					this.controlDevOperateService.operateControlDev(controlDevSts.getId(), dto.orderState);
				}
			}
			return Response.ok().build();
		} catch (RuntimeException e) {
			throw WebExceptionFactory.buildDefaultException(e, logger);
		}
	}
	
	@GET
	@Secure
	@Path("/operateState")
	public List<OperateStateDTO> getOperateStateList(@HeaderParam("authCode")String code, @QueryParam("controlIds")String controlIds){
		try{
			List<OperateStateDTO> dtos = new ArrayList<OperateStateDTO>();
			
			String[] controlIdArray = controlIds.split(",");
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
				
				OperateStateDTO dto = new OperateStateDTO();
				dto.id = controlId;
				dto.state = state;
				dtos.add(dto);
			}
			return dtos;
		} catch (RuntimeException e) {
			throw WebExceptionFactory.buildDefaultException(e, logger);
		}
	}
}
