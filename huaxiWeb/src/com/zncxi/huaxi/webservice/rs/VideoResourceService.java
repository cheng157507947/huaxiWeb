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

import com.zncxi.huaxi.domain.VideoDev;
import com.zncxi.huaxi.service.VideoDevService;
import com.zncxi.huaxi.webservice.dto.VideoDTO;
import com.zncxi.huaxi.webservice.exception.WebExceptionFactory;
import com.zncxi.huaxi.webservice.security.Secure;

/**
 * 场景信息查询接口
 * @author xiaoCheng
 *
 */
@Component
@Path("/video")
@Produces({ MediaType.APPLICATION_XML + ";charset=UTF-8", MediaType.APPLICATION_JSON + ";charset=UTF-8" })
public class VideoResourceService {

	private static Logger logger = LoggerFactory.getLogger(VideoResourceService.class);
	
	@Autowired
	private VideoDevService videoDevService;
	
	@GET
	@Secure
	@Path("/list")
	public List<VideoDTO> getVideoList(@HeaderParam("authCode")String code, @QueryParam("sceneId")String sceneId){
		try{
			List<VideoDTO> dtos = new ArrayList<VideoDTO>();
			List<VideoDev> videoDevs = this.videoDevService.findVideoBySceneId(sceneId);
			for(VideoDev video: videoDevs){
				VideoDTO dto = new VideoDTO();
				dto.name = video.getName();
				dto.ip = video.getIp();
				dto.port = video.getPort();
				dto.username = video.getUsername();
				dto.password = video.getPassword();
				dto.guid = video.getGuid();
				dto.type = video.getType();
				
				dtos.add(dto);
			}
			return dtos;
		} catch (RuntimeException e) {
			throw WebExceptionFactory.buildDefaultException(e, logger);
		}
	}
}
