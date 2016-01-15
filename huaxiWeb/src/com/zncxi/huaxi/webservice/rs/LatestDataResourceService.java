package com.zncxi.huaxi.webservice.rs;

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

import com.sun.jersey.api.NotFoundException;
import com.zncxi.huaxi.domain.CollLatestData;
import com.zncxi.huaxi.domain.DtuDev;
import com.zncxi.huaxi.domain.Scene;
import com.zncxi.huaxi.service.CollLatestDataService;
import com.zncxi.huaxi.service.SceneService;
import com.zncxi.huaxi.util.DataUtil;
import com.zncxi.huaxi.webservice.dto.latedata.FieldLatestDataDTO;
import com.zncxi.huaxi.webservice.dto.latedata.PumpLatestDataDTO;
import com.zncxi.huaxi.webservice.dto.latedata.WaterLatestDataDTO;
import com.zncxi.huaxi.webservice.dto.latedata.WeatherLatestDataDTO;
import com.zncxi.huaxi.webservice.exception.WebExceptionFactory;
import com.zncxi.huaxi.webservice.security.Secure;

/**
 * 获得最新数据接口
 * @author xiaoCheng
 *
 */
@Component
@Path("/latestData")
@Produces({ MediaType.APPLICATION_XML + ";charset=UTF-8", MediaType.APPLICATION_JSON + ";charset=UTF-8" })
public class LatestDataResourceService {

	private static Logger logger = LoggerFactory.getLogger(LatestDataResourceService.class);
	
	@Autowired
	private SceneService sceneService;
	@Autowired
	private CollLatestDataService collLatestDataService;
	
	
	@GET
	@Secure
	@Path("/fieldDataList")
	public FieldLatestDataDTO getFieldLatestDataList(@HeaderParam("authCode")String code, @QueryParam("sceneId")String sceneId){
		try{
			FieldLatestDataDTO dto = new FieldLatestDataDTO();
			Scene scene = this.sceneService.findById(sceneId);
			if(scene != null){
				if(scene.getType() != 1){  //不是田块场景
					logger.warn("sceneId{} - is not field.", sceneId);
					throw new NotFoundException("scene is not field");
				}
				DtuDev dtuDev = scene.getDtuDev();
				if(dtuDev != null && dtuDev.getType() == 1){
					CollLatestData collLatestData = this.collLatestDataService.findByDtuDevId(dtuDev.getId());
					dto.collTime = collLatestData.getCollTime();
					byte[] dataConfig = DataUtil.intToByteArray(dtuDev.getConfig());
					
					byte dataConfig0 = dataConfig[1];
					if(DataUtil.byteBitChecked(dataConfig0, 0)){  //田块1水位
						dto.data1 = collLatestData.getData1();
						dto.data1State = collLatestData.getData1State();
					}
					if(DataUtil.byteBitChecked(dataConfig0, 1)){  //田块2水位
						dto.data2 = collLatestData.getData2();
						dto.data2State = collLatestData.getData2State();
					}
					if(DataUtil.byteBitChecked(dataConfig0, 2)){  //田块3水位
						dto.data3 = collLatestData.getData3();
						dto.data3State = collLatestData.getData3State();
					}
					if(DataUtil.byteBitChecked(dataConfig0, 3)){  //田块4水位
						dto.data4 = collLatestData.getData4();
						dto.data4State = collLatestData.getData4State();
					}
					if(DataUtil.byteBitChecked(dataConfig0, 4)){  //土壤水分
						dto.data5 = collLatestData.getData5();
						dto.data5State = collLatestData.getData5State();
					}
					if(DataUtil.byteBitChecked(dataConfig0, 5)){  //片区1瞬时流量
						dto.data6 = collLatestData.getData6();
						dto.data6State = collLatestData.getData6State();
					}
					if(DataUtil.byteBitChecked(dataConfig0, 6)){  //片区1累计流量
						dto.data7 = collLatestData.getData7();
					}
					if(DataUtil.byteBitChecked(dataConfig0, 7)){  //片区2瞬时流量
						dto.data8 = collLatestData.getData8();
						dto.data8State = collLatestData.getData8State();
					}
					byte dataConfig1 = dataConfig[0];
					if(DataUtil.byteBitChecked(dataConfig1, 0)){  //片区2累计流量
						dto.data9 = collLatestData.getData9();
					}
					if(DataUtil.byteBitChecked(dataConfig1, 1)){  //片区3瞬时流量
						dto.data10 = collLatestData.getData10();
						dto.data10State = collLatestData.getData10State();
					}
					if(DataUtil.byteBitChecked(dataConfig1, 2)){  //片区3累计流量
						dto.data11 = collLatestData.getData11();
					}
				}
				return dto;
			}
			logger.warn("sceneId{} - has the null scene.", sceneId);
			throw new NotFoundException("scene is not found");
		} catch (RuntimeException e) {
			throw WebExceptionFactory.buildDefaultException(e, logger);
		}
	}
	
	@GET
	@Secure
	@Path("/pumpDataList")
	public PumpLatestDataDTO getPumpLatestDataList(@HeaderParam("authCode")String code, @QueryParam("sceneId")String sceneId){
		try{
			PumpLatestDataDTO dto = new PumpLatestDataDTO();
			Scene scene = this.sceneService.findById(sceneId);
			if(scene != null){
				if(scene.getType() != 2){  //不是泵房场景
					logger.warn("sceneId{} - is not pump.", sceneId);
					throw new NotFoundException("scene is not pump");
				}
				DtuDev dtuDev = scene.getDtuDev();
				if(dtuDev != null && dtuDev.getType() != 1){
					CollLatestData collLatestData = this.collLatestDataService.findByDtuDevId(dtuDev.getId());
					dto.collTime = collLatestData.getCollTime();
					byte[] dataConfig = DataUtil.intToByteArray(dtuDev.getConfig());
					
					byte dataConfig0 = dataConfig[1];
					if(DataUtil.byteBitChecked(dataConfig0, 0)){  //水源地水位
						dto.data1 = collLatestData.getData1();
						dto.data1State = collLatestData.getData1State();
					}
					if(DataUtil.byteBitChecked(dataConfig0, 1)){  //水泵出口压力
						dto.data2 = collLatestData.getData2();
						dto.data2State = collLatestData.getData2State();
					}
				}
				return dto;
			}
			logger.warn("sceneId{} - has the null scene.", sceneId);
			throw new NotFoundException("scene is not found");
		} catch (RuntimeException e) {
			throw WebExceptionFactory.buildDefaultException(e, logger);
		}
	}
	
	@GET
	@Secure
	@Path("/waterDataList")
	public WaterLatestDataDTO getWaterLatestDataList(@HeaderParam("authCode")String code, @QueryParam("sceneId")String sceneId){
		try{
			WaterLatestDataDTO dto = new WaterLatestDataDTO();
			Scene scene = this.sceneService.findById(sceneId);
			if(scene != null){
				if(scene.getType() != 3){  //不是水质场景
					logger.warn("sceneId{} - is not water.", sceneId);
					throw new NotFoundException("scene is not water");
				}
				DtuDev dtuDev = scene.getDtuDev();
				if(dtuDev != null && dtuDev.getType() == 2){
					CollLatestData collLatestData = this.collLatestDataService.findByDtuDevId(dtuDev.getId());
					dto.collTime = collLatestData.getCollTime();
					byte[] dataConfig = DataUtil.intToByteArray(dtuDev.getConfig());
					
					byte dataConfig0 = dataConfig[1];
					if(DataUtil.byteBitChecked(dataConfig0, 0)){  //源水PH
						dto.data1 = collLatestData.getData3();
						dto.data1State = collLatestData.getData3State();
					}
					if(DataUtil.byteBitChecked(dataConfig0, 1)){  //源水浊度
						dto.data2 = collLatestData.getData4();
						dto.data2State = collLatestData.getData4State();
					}
				}
				return dto;
			}
			logger.warn("sceneId{} - has the null scene.", sceneId);
			throw new NotFoundException("scene is not found");
		} catch (RuntimeException e) {
			throw WebExceptionFactory.buildDefaultException(e, logger);
		}
	}
	
	@GET
	@Secure
	@Path("/weatherDataList")
	public WeatherLatestDataDTO getWeatherLatestDataList(@HeaderParam("authCode")String code, @QueryParam("sceneId")String sceneId){
		try{
			WeatherLatestDataDTO dto = new WeatherLatestDataDTO();
			Scene scene = this.sceneService.findById(sceneId);
			if(scene != null){
				if(scene.getType() != 4){  //不是气象场景
					logger.warn("sceneId{} - is not weather.", sceneId);
					throw new NotFoundException("scene is not weather");
				}
				DtuDev dtuDev = scene.getDtuDev();
				if(dtuDev != null && dtuDev.getType() == 3){
					CollLatestData collLatestData = this.collLatestDataService.findByDtuDevId(dtuDev.getId());
					dto.collTime = collLatestData.getCollTime();
					byte[] dataConfig = DataUtil.intToByteArray(dtuDev.getConfig());
					
					byte dataConfig0 = dataConfig[1];
					if(DataUtil.byteBitChecked(dataConfig0, 0)){  //风速
						dto.data1 = collLatestData.getData3();
						dto.data1State = collLatestData.getData3State();
					}
					if(DataUtil.byteBitChecked(dataConfig0, 1)){  //风向
						dto.data2 = collLatestData.getData4();
						dto.data2State = collLatestData.getData4State();
					}
					if(DataUtil.byteBitChecked(dataConfig0, 2)){  //大气温度
						dto.data3 = collLatestData.getData5();
						dto.data3State = collLatestData.getData5State();
					}
					if(DataUtil.byteBitChecked(dataConfig0, 3)){  //大气湿度
						dto.data4 = collLatestData.getData6();
						dto.data4State = collLatestData.getData6State();
					}
					if(DataUtil.byteBitChecked(dataConfig0, 4)){  //照度
						dto.data5 = collLatestData.getData7();
						dto.data5State = collLatestData.getData7State();
					}
					if(DataUtil.byteBitChecked(dataConfig0, 5)){  //小时雨量
						dto.data6 = collLatestData.getData8();
						dto.data6State = collLatestData.getData8State();
					}
					if(DataUtil.byteBitChecked(dataConfig0, 6)){  //日雨量
						dto.data7 = collLatestData.getData9();
						dto.data7State = collLatestData.getData9State();
					}
				}
				return dto;
			}
			logger.warn("sceneId{} - has the null scene.", sceneId);
			throw new NotFoundException("scene is not found");
		} catch (RuntimeException e) {
			throw WebExceptionFactory.buildDefaultException(e, logger);
		}
	}
}
