package com.zncxi.huaxi.handle;

import java.util.List;

import org.springframework.stereotype.Component;

import com.zncxi.huaxi.domain.CollHisData;
import com.zncxi.huaxi.domain.CollLatestData;
import com.zncxi.huaxi.util.DataUtil;

/**
 * 采集数据处理
 * @author xiaoCheng
 *
 */
@Component
public class CollDataHandleService {

	/**
	 * 根据配置设置田块历史数据
	 * @param config
	 * @param datas
	 * @param sb
	 */
	public void setFieldDataQuery(int config, List<CollHisData> datas, StringBuffer sb){
		StringBuffer[] dataArray = new StringBuffer[11];
		for(int i=0; i<dataArray.length; i++){
			dataArray[i] = new StringBuffer();
		}
		byte[] dataConfig = DataUtil.intToByteArray(config);
		
		for(CollHisData data: datas){
			dataArray[0].append("[\"").append(data.getCollTime()).append("\",").append(data.getData1()).append("],");
			dataArray[1].append("[\"").append(data.getCollTime()).append("\",").append(data.getData2()).append("],");
			dataArray[2].append("[\"").append(data.getCollTime()).append("\",").append(data.getData3()).append("],");
			dataArray[3].append("[\"").append(data.getCollTime()).append("\",").append(data.getData4()).append("],");
			dataArray[4].append("[\"").append(data.getCollTime()).append("\",").append(data.getData5()).append("],");
			dataArray[5].append("[\"").append(data.getCollTime()).append("\",").append(data.getData6()).append("],");
			dataArray[6].append("[\"").append(data.getCollTime()).append("\",").append(data.getData7()).append("],");
			dataArray[7].append("[\"").append(data.getCollTime()).append("\",").append(data.getData8()).append("],");
			dataArray[8].append("[\"").append(data.getCollTime()).append("\",").append(data.getData9()).append("],");
			dataArray[9].append("[\"").append(data.getCollTime()).append("\",").append(data.getData10()).append("],");
			dataArray[10].append("[\"").append(data.getCollTime()).append("\",").append(data.getData11()).append("],");
		}
		
		if(dataArray[0].length() <= 0) return;
		
		byte dataConfig0 = dataConfig[1];
		if(DataUtil.byteBitChecked(dataConfig0, 0)){  //田块1水位
			dataArray[0].delete(dataArray[0].length()-1, dataArray[0].length());
			sb.append("{\"name\":\"田块1水位\",\"data\":[").append(dataArray[0].toString()).append("]},");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 1)){  //田块2水位
			dataArray[1].delete(dataArray[1].length()-1, dataArray[1].length());
			sb.append("{\"name\":\"田块2水位\",\"data\":[").append(dataArray[1].toString()).append("]},");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 2)){  //田块3水位
			dataArray[2].delete(dataArray[2].length()-1, dataArray[2].length());
			sb.append("{\"name\":\"田块3水位\",\"data\":[").append(dataArray[2].toString()).append("]},");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 3)){  //田块4水位
			dataArray[3].delete(dataArray[3].length()-1, dataArray[3].length());
			sb.append("{\"name\":\"田块4水位\",\"data\":[").append(dataArray[3].toString()).append("]},");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 4)){  //土壤水分
			dataArray[4].delete(dataArray[4].length()-1, dataArray[4].length());
			sb.append("{\"name\":\"土壤水分\",\"data\":[").append(dataArray[4].toString()).append("]},");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 5)){  //片区1瞬时流量
			dataArray[5].delete(dataArray[5].length()-1, dataArray[5].length());
			sb.append("{\"name\":\"片区1瞬时流量\",\"data\":[").append(dataArray[5].toString()).append("]},");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 6)){  //片区1累计流量
			dataArray[6].delete(dataArray[6].length()-1, dataArray[6].length());
			sb.append("{\"name\":\"片区1累计流量\",\"data\":[").append(dataArray[6].toString()).append("]},");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 7)){  //片区2瞬时流量
			dataArray[7].delete(dataArray[7].length()-1, dataArray[7].length());
			sb.append("{\"name\":\"片区2瞬时流量\",\"data\":[").append(dataArray[7].toString()).append("]},");
		}
		byte dataConfig1 = dataConfig[0];
		if(DataUtil.byteBitChecked(dataConfig1, 0)){  //片区2累计流量
			dataArray[8].delete(dataArray[8].length()-1, dataArray[8].length());
			sb.append("{\"name\":\"片区2累计流量\",\"data\":[").append(dataArray[8].toString()).append("]},");
		}
		if(DataUtil.byteBitChecked(dataConfig1, 1)){  //片区3瞬时流量
			dataArray[9].delete(dataArray[9].length()-1, dataArray[9].length());
			sb.append("{\"name\":\"片区3瞬时流量\",\"data\":[").append(dataArray[9].toString()).append("]},");
		}
		if(DataUtil.byteBitChecked(dataConfig1, 2)){  //片区3累计流量
			dataArray[10].delete(dataArray[10].length()-1, dataArray[10].length());
			sb.append("{\"name\":\"片区3累计流量\",\"data\":[").append(dataArray[10].toString()).append("]},");
		}
	}
	
	/**
	 * 根据配置设置泵房历史数据
	 * @param config
	 * @param datas
	 * @param sb
	 */
	public void setPumpDataQuery(int config, List<CollHisData> datas, StringBuffer sb){
		StringBuffer[] dataArray = new StringBuffer[2];
		for(int i=0; i<dataArray.length; i++){
			dataArray[i] = new StringBuffer();
		}
		byte[] dataConfig = DataUtil.intToByteArray(config);
		
		for(CollHisData data: datas){
			dataArray[0].append("[\"").append(data.getCollTime()).append("\",").append(data.getData1()).append("],");
			dataArray[1].append("[\"").append(data.getCollTime()).append("\",").append(data.getData2()).append("],");
		}
		
		if(dataArray[0].length() <= 0) return;
		
		byte dataConfig0 = dataConfig[1];
		if(DataUtil.byteBitChecked(dataConfig0, 0)){  //水源地水位
			dataArray[0].delete(dataArray[0].length()-1, dataArray[0].length());
			sb.append("{\"name\":\"水源地水位\",\"data\":[").append(dataArray[0].toString()).append("]},");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 1)){  //水泵出口压力
			dataArray[1].delete(dataArray[1].length()-1, dataArray[1].length());
			sb.append("{\"name\":\"水泵出口压力\",\"data\":[").append(dataArray[1].toString()).append("]},");
		}
	}
	
	/**
	 * 根据配置设置水质历史数据
	 * @param config
	 * @param datas
	 * @param sb
	 */
	public void setWaterDataQuery(int config, List<CollHisData> datas, StringBuffer sb){
		StringBuffer[] dataArray = new StringBuffer[2];
		for(int i=0; i<dataArray.length; i++){
			dataArray[i] = new StringBuffer();
		}
		byte[] dataConfig = DataUtil.intToByteArray(config);
		
		for(CollHisData data: datas){
			dataArray[0].append("[\"").append(data.getCollTime()).append("\",").append(data.getData3()).append("],");
			dataArray[1].append("[\"").append(data.getCollTime()).append("\",").append(data.getData4()).append("],");
		}
		
		if(dataArray[0].length() <= 0) return;
		
		byte dataConfig0 = dataConfig[1];
		if(DataUtil.byteBitChecked(dataConfig0, 2)){  //源水PH
			dataArray[0].delete(dataArray[0].length()-1, dataArray[0].length());
			sb.append("{\"name\":\"源水PH\",\"data\":[").append(dataArray[0].toString()).append("]},");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 3)){  //源水浊度
			dataArray[1].delete(dataArray[1].length()-1, dataArray[1].length());
			sb.append("{\"name\":\"源水浊度\",\"data\":[").append(dataArray[1].toString()).append("]},");
		}
	}
	
	/**
	 * 根据配置设置气象历史数据
	 * @param config
	 * @param datas
	 * @param sb
	 */
	public void setWeatherDataQuery(int config, List<CollHisData> datas, StringBuffer sb){
		StringBuffer[] dataArray = new StringBuffer[7];
		for(int i=0; i<dataArray.length; i++){
			dataArray[i] = new StringBuffer();
		}
		byte[] dataConfig = DataUtil.intToByteArray(config);
		
		for(CollHisData data: datas){
			dataArray[0].append("[\"").append(data.getCollTime()).append("\",").append(data.getData1()).append("],");
			dataArray[1].append("[\"").append(data.getCollTime()).append("\",").append(data.getData2()).append("],");
			dataArray[2].append("[\"").append(data.getCollTime()).append("\",").append(data.getData3()).append("],");
			dataArray[3].append("[\"").append(data.getCollTime()).append("\",").append(data.getData4()).append("],");
			dataArray[4].append("[\"").append(data.getCollTime()).append("\",").append(data.getData5()).append("],");
			dataArray[5].append("[\"").append(data.getCollTime()).append("\",").append(data.getData6()).append("],");
			dataArray[6].append("[\"").append(data.getCollTime()).append("\",").append(data.getData7()).append("],");
		}
		
		if(dataArray[0].length() <= 0) return;
		
		byte dataConfig0 = dataConfig[1];
		if(DataUtil.byteBitChecked(dataConfig0, 2)){  //风速
			dataArray[0].delete(dataArray[0].length()-1, dataArray[0].length());
			sb.append("{\"name\":\"风速\",\"data\":[").append(dataArray[0].toString()).append("]},");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 3)){  //风向
			dataArray[1].delete(dataArray[1].length()-1, dataArray[1].length());
			sb.append("{\"name\":\"风向\",\"data\":[").append(dataArray[1].toString()).append("]},");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 4)){  //大气温度
			dataArray[2].delete(dataArray[2].length()-1, dataArray[2].length());
			sb.append("{\"name\":\"大气温度\",,\"data\":[").append(dataArray[2].toString()).append("]},");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 5)){  //大气湿度
			dataArray[3].delete(dataArray[3].length()-1, dataArray[3].length());
			sb.append("{\"name\":\"大气湿度\",\"data\":[").append(dataArray[3].toString()).append("]},");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 6)){  //照度
			dataArray[4].delete(dataArray[4].length()-1, dataArray[4].length());
			sb.append("{\"name\":\"照度\",\"data\":[").append(dataArray[4].toString()).append("]},");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 7)){  //小时雨量
			dataArray[5].delete(dataArray[5].length()-1, dataArray[5].length());
			sb.append("{\"name\":\"小时雨量\",\"data\":[").append(dataArray[5].toString()).append("]},");
		}
		byte dataConfig1 = dataConfig[0];
		if(DataUtil.byteBitChecked(dataConfig1, 0)){   //日雨量
			dataArray[6].delete(dataArray[6].length()-1, dataArray[6].length());
			sb.append("{\"name\":\"日雨量\",\"data\":[").append(dataArray[6].toString()).append("]},");
		}
	}
	
	/**
	 * 根据配置设置田块历史数据图表信息
	 * @param config
	 * @param datas
	 * @param sb
	 */
	public void setFieldDataCharts(int config, List<CollHisData> datas, StringBuffer sb){
		StringBuffer[] dataArray = new StringBuffer[11];
		for(int i=0; i<dataArray.length; i++){
			dataArray[i] = new StringBuffer();
		}
		byte[] dataConfig = DataUtil.intToByteArray(config);
		
		for(CollHisData data: datas){
			long time = data.getCollTime().getTime();
			dataArray[0].append("[").append(time).append(",").append(data.getData1()).append("],");
			dataArray[1].append("[").append(time).append(",").append(data.getData2()).append("],");
			dataArray[2].append("[").append(time).append(",").append(data.getData3()).append("],");
			dataArray[3].append("[").append(time).append(",").append(data.getData4()).append("],");
			dataArray[4].append("[").append(time).append(",").append(data.getData5()).append("],");
			dataArray[5].append("[").append(time).append(",").append(data.getData6()).append("],");
			dataArray[6].append("[").append(time).append(",").append(data.getData7()).append("],");
			dataArray[7].append("[").append(time).append(",").append(data.getData8()).append("],");
			dataArray[8].append("[").append(time).append(",").append(data.getData9()).append("],");
			dataArray[9].append("[").append(time).append(",").append(data.getData10()).append("],");
			dataArray[10].append("[").append(time).append(",").append(data.getData11()).append("],");
		}
		
		if(dataArray[0].length() <= 0) return;
		
		boolean show = true;
		byte dataConfig0 = dataConfig[1];
		if(DataUtil.byteBitChecked(dataConfig0, 0)){  //田块1水位
			dataArray[0].delete(dataArray[0].length()-1, dataArray[0].length());
			sb.append("{\"name\":\"田块1水位\",\"visible\":"+show+",\"data\":[").append(dataArray[0].toString()).append("]},");
			show = false;
		}
		if(DataUtil.byteBitChecked(dataConfig0, 1)){  //田块2水位
			dataArray[1].delete(dataArray[1].length()-1, dataArray[1].length());
			sb.append("{\"name\":\"田块2水位\",\"visible\":"+show+",\"data\":[").append(dataArray[1].toString()).append("]},");
			show = false;
		}
		if(DataUtil.byteBitChecked(dataConfig0, 2)){  //田块3水位
			dataArray[2].delete(dataArray[2].length()-1, dataArray[2].length());
			sb.append("{\"name\":\"田块3水位\",\"visible\":"+show+",\"data\":[").append(dataArray[2].toString()).append("]},");
			show = false;
		}
		if(DataUtil.byteBitChecked(dataConfig0, 3)){  //田块4水位
			dataArray[3].delete(dataArray[3].length()-1, dataArray[3].length());
			sb.append("{\"name\":\"田块4水位\",\"visible\":"+show+",\"data\":[").append(dataArray[3].toString()).append("]},");
			show = false;
		}
		if(DataUtil.byteBitChecked(dataConfig0, 4)){  //土壤水分
			dataArray[4].delete(dataArray[4].length()-1, dataArray[4].length());
			sb.append("{\"name\":\"土壤水分\",\"visible\":"+show+",\"data\":[").append(dataArray[4].toString()).append("]},");
			show = false;
		}
		if(DataUtil.byteBitChecked(dataConfig0, 5)){  //片区1瞬时流量
			dataArray[5].delete(dataArray[5].length()-1, dataArray[5].length());
			sb.append("{\"name\":\"片区1瞬时流量\",\"visible\":"+show+",\"data\":[").append(dataArray[5].toString()).append("]},");
			show = false;
		}
		if(DataUtil.byteBitChecked(dataConfig0, 6)){  //片区1累计流量
			dataArray[6].delete(dataArray[6].length()-1, dataArray[6].length());
			sb.append("{\"name\":\"片区1累计流量\",\"visible\":"+show+",\"data\":[").append(dataArray[6].toString()).append("]},");
			show = false;
		}
		if(DataUtil.byteBitChecked(dataConfig0, 7)){  //片区2瞬时流量
			dataArray[7].delete(dataArray[7].length()-1, dataArray[7].length());
			sb.append("{\"name\":\"片区2瞬时流量\",\"visible\":"+show+",\"data\":[").append(dataArray[7].toString()).append("]},");
			show = false;
		}
		byte dataConfig1 = dataConfig[0];
		if(DataUtil.byteBitChecked(dataConfig1, 0)){  //片区2累计流量
			dataArray[8].delete(dataArray[8].length()-1, dataArray[8].length());
			sb.append("{\"name\":\"片区2累计流量\",\"visible\":"+show+",\"data\":[").append(dataArray[8].toString()).append("]},");
			show = false;
		}
		if(DataUtil.byteBitChecked(dataConfig1, 1)){  //片区3瞬时流量
			dataArray[9].delete(dataArray[9].length()-1, dataArray[9].length());
			sb.append("{\"name\":\"片区3瞬时流量\",\"visible\":"+show+",\"data\":[").append(dataArray[9].toString()).append("]},");
			show = false;
		}
		if(DataUtil.byteBitChecked(dataConfig1, 2)){  //片区3累计流量
			dataArray[10].delete(dataArray[10].length()-1, dataArray[10].length());
			sb.append("{\"name\":\"片区3累计流量\",\"visible\":"+show+",\"data\":[").append(dataArray[10].toString()).append("]},");
			show = false;
		}
	}
	
	/**
	 * 根据配置设置泵房历史数据图表信息
	 * @param config
	 * @param datas
	 * @param sb
	 */
	public void setPumpDataCharts(int config, List<CollHisData> datas, StringBuffer sb){
		StringBuffer[] dataArray = new StringBuffer[2];
		for(int i=0; i<dataArray.length; i++){
			dataArray[i] = new StringBuffer();
		}
		byte[] dataConfig = DataUtil.intToByteArray(config);
		
		for(CollHisData data: datas){
			long time = data.getCollTime().getTime();
			dataArray[0].append("[").append(time).append(",").append(data.getData1()).append("],");
			dataArray[1].append("[").append(time).append(",").append(data.getData2()).append("],");
		}
		
		if(dataArray[0].length() <= 0) return;
		
		boolean show = true;
		byte dataConfig0 = dataConfig[1];
		if(DataUtil.byteBitChecked(dataConfig0, 0)){  //水源地水位
			dataArray[0].delete(dataArray[0].length()-1, dataArray[0].length());
			sb.append("{\"name\":\"水源地水位\",\"visible\":"+show+",\"data\":[").append(dataArray[0].toString()).append("]},");
			show = false;
		}
		if(DataUtil.byteBitChecked(dataConfig0, 1)){  //水泵出口压力
			dataArray[1].delete(dataArray[1].length()-1, dataArray[1].length());
			sb.append("{\"name\":\"水泵出口压力\",\"visible\":"+show+",\"data\":[").append(dataArray[1].toString()).append("]},");
			show = false;
		}
	}
	
	/**
	 * 根据配置设置气象历史数据图表信息
	 * @param config
	 * @param datas
	 * @param sb
	 */
	public void setWeatherDataCharts(int config, List<CollHisData> datas, StringBuffer sb){
		StringBuffer[] dataArray = new StringBuffer[7];
		for(int i=0; i<dataArray.length; i++){
			dataArray[i] = new StringBuffer();
		}
		byte[] dataConfig = DataUtil.intToByteArray(config);
		
		for(CollHisData data: datas){
			long time = data.getCollTime().getTime();
			dataArray[0].append("[").append(time).append(",").append(data.getData1()).append("],");
			dataArray[1].append("[").append(time).append(",").append(data.getData2()).append("],");
			dataArray[2].append("[").append(time).append(",").append(data.getData3()).append("],");
			dataArray[3].append("[").append(time).append(",").append(data.getData4()).append("],");
			dataArray[4].append("[").append(time).append(",").append(data.getData5()).append("],");
			dataArray[5].append("[").append(time).append(",").append(data.getData6()).append("],");
			dataArray[6].append("[").append(time).append(",").append(data.getData7()).append("],");
		}
		
		if(dataArray[0].length() <= 0) return;
		
		boolean show = true;
		byte dataConfig0 = dataConfig[1];
		if(DataUtil.byteBitChecked(dataConfig0, 2)){  //风速
			dataArray[0].delete(dataArray[0].length()-1, dataArray[0].length());
			sb.append("{\"name\":\"风速\",\"visible\":"+show+",\"data\":[").append(dataArray[0].toString()).append("]},");
			show = false;
		}
		if(DataUtil.byteBitChecked(dataConfig0, 3)){  //风向
			dataArray[1].delete(dataArray[1].length()-1, dataArray[1].length());
			sb.append("{\"name\":\"风向\",\"visible\":"+show+",\"data\":[").append(dataArray[1].toString()).append("]},");
			show = false;
		}
		if(DataUtil.byteBitChecked(dataConfig0, 4)){  //大气温度
			dataArray[2].delete(dataArray[2].length()-1, dataArray[2].length());
			sb.append("{\"name\":\"大气温度\",\"visible\":"+show+",\"data\":[").append(dataArray[2].toString()).append("]},");
			show = false;
		}
		if(DataUtil.byteBitChecked(dataConfig0, 5)){  //大气湿度
			dataArray[3].delete(dataArray[3].length()-1, dataArray[3].length());
			sb.append("{\"name\":\"大气湿度\",\"visible\":"+show+",\"data\":[").append(dataArray[3].toString()).append("]},");
			show = false;
		}
		if(DataUtil.byteBitChecked(dataConfig0, 6)){  //照度
			dataArray[4].delete(dataArray[4].length()-1, dataArray[4].length());
			sb.append("{\"name\":\"照度\",\"visible\":"+show+",\"data\":[").append(dataArray[4].toString()).append("]},");
			show = false;
		}
		if(DataUtil.byteBitChecked(dataConfig0, 7)){  //小时雨量
			dataArray[5].delete(dataArray[5].length()-1, dataArray[5].length());
			sb.append("{\"name\":\"小时雨量\",\"visible\":"+show+",\"data\":[").append(dataArray[5].toString()).append("]},");
			show = false;
		}
		byte dataConfig1 = dataConfig[0];
		if(DataUtil.byteBitChecked(dataConfig1, 0)){   //日雨量
			dataArray[6].delete(dataArray[6].length()-1, dataArray[6].length());
			sb.append("{\"name\":\"日雨量\",\"visible\":"+show+",\"data\":[").append(dataArray[6].toString()).append("]},");
			show = false;
		}
	}
	
	/**
	 * 根据配置设置水质历史数据图表信息
	 * @param config
	 * @param datas
	 * @param sb
	 */
	public void setWaterDataCharts(int config, List<CollHisData> datas, StringBuffer sb){
		StringBuffer[] dataArray = new StringBuffer[2];
		for(int i=0; i<dataArray.length; i++){
			dataArray[i] = new StringBuffer();
		}
		byte[] dataConfig = DataUtil.intToByteArray(config);
		
		for(CollHisData data: datas){
			long time = data.getCollTime().getTime();
			dataArray[0].append("[").append(time).append(",").append(data.getData3()).append("],");
			dataArray[1].append("[").append(time).append(",").append(data.getData4()).append("],");
		}
		
		if(dataArray[0].length() <= 0) return;
		
		boolean show = true;
		byte dataConfig0 = dataConfig[1];
		if(DataUtil.byteBitChecked(dataConfig0, 2)){  //源水PH
			dataArray[0].delete(dataArray[0].length()-1, dataArray[0].length());
			sb.append("{\"name\":\"源水PH\",\"visible\":"+show+",\"data\":[").append(dataArray[0].toString()).append("]},");
			show = false;
		}
		if(DataUtil.byteBitChecked(dataConfig0, 3)){  //源水浊度
			dataArray[1].delete(dataArray[1].length()-1, dataArray[1].length());
			sb.append("{\"name\":\"源水浊度\",\"visible\":"+show+",\"data\":[").append(dataArray[1].toString()).append("]},");
			show = false;
		}
	}
	
	/**
	 * 根据配置设置田块最新数据
	 * @param config
	 * @param collLatestData
	 * @param sb
	 */
	public void setFieldData(int config, CollLatestData collLatestData, StringBuffer sb){
		byte[] dataConfig = DataUtil.intToByteArray(config);
		
		byte dataConfig0 = dataConfig[1];
		if(DataUtil.byteBitChecked(dataConfig0, 0)){  //田块1水位
			sb.append("\"fieldData1\":\""+collLatestData.getData1()+"\",");
			sb.append("\"fieldData1State\":"+collLatestData.getData1State()+",");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 1)){  //田块2水位
			sb.append("\"fieldData2\":\""+collLatestData.getData2()+"\",");
			sb.append("\"fieldData2State\":"+collLatestData.getData2State()+",");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 2)){  //田块3水位
			sb.append("\"fieldData3\":\""+collLatestData.getData3()+"\",");
			sb.append("\"fieldData3State\":"+collLatestData.getData3State()+",");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 3)){  //田块4水位
			sb.append("\"fieldData4\":\""+collLatestData.getData4()+"\",");
			sb.append("\"fieldData4State\":"+collLatestData.getData4State()+",");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 4)){  //土壤水分
			sb.append("\"fieldData5\":\""+collLatestData.getData5()+"\",");
			sb.append("\"fieldData5State\":"+collLatestData.getData5State()+",");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 5)){  //片区1瞬时流量
			sb.append("\"fieldData6\":\""+collLatestData.getData6()+"\",");
			sb.append("\"fieldData6State\":"+collLatestData.getData6State()+",");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 6)){  //片区1累计流量
			sb.append("\"fieldData7\":\""+collLatestData.getData7()+"\",");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 7)){  //片区2瞬时流量
			sb.append("\"fieldData8\":\""+collLatestData.getData8()+"\",");
			sb.append("\"fieldData8State\":"+collLatestData.getData8State()+",");
		}
		byte dataConfig1 = dataConfig[0];
		if(DataUtil.byteBitChecked(dataConfig1, 0)){  //片区2累计流量
			sb.append("\"fieldData9\":\""+collLatestData.getData9()+"\",");
		}
		if(DataUtil.byteBitChecked(dataConfig1, 1)){  //片区3瞬时流量
			sb.append("\"fieldData10\":\""+collLatestData.getData10()+"\",");
			sb.append("\"fieldData10State\":"+collLatestData.getData10State()+",");
		}
		if(DataUtil.byteBitChecked(dataConfig1, 2)){  //片区3累计流量
			sb.append("\"fieldData11\":\""+collLatestData.getData11()+"\",");
		}
	}
	
	
	/**
	 * 根据配置设置泵房最新数据
	 * @param config
	 * @param collLatestData
	 * @param sb
	 */
	public void setPumpData(int config, CollLatestData collLatestData, StringBuffer sb){
		byte[] dataConfig = DataUtil.intToByteArray(config);
		
		byte dataConfig0 = dataConfig[1];
		if(DataUtil.byteBitChecked(dataConfig0, 0)){  //水源地水位
			sb.append("\"pumpData1\":\""+collLatestData.getData1()+"\",");
			sb.append("\"pumpData1State\":"+collLatestData.getData1State()+",");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 1)){  //水泵出口压力
			sb.append("\"pumpData2\":\""+collLatestData.getData2()+"\",");
			sb.append("\"pumpData2State\":"+collLatestData.getData2State()+",");
		}
	}
	
	/**
	 * 根据配置设置气象最新数据
	 * @param config
	 * @param collLatestData
	 * @param sb
	 */
	public void setWeatherData(int config, CollLatestData collLatestData, StringBuffer sb){
		byte[] dataConfig = DataUtil.intToByteArray(config);
		
		byte dataConfig0 = dataConfig[1];
		if(DataUtil.byteBitChecked(dataConfig0, 2)){  //风速
			sb.append("\"weaData1\":\""+collLatestData.getData3()+"\",");
			sb.append("\"weaData1State\":"+collLatestData.getData3State()+",");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 3)){  //风向
			sb.append("\"weaData2\":\""+collLatestData.getData4()+"\",");
			sb.append("\"weaData2State\":"+collLatestData.getData4State()+",");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 4)){  //大气温度
			sb.append("\"weaData3\":\""+collLatestData.getData5()+"\",");
			sb.append("\"weaData3State\":"+collLatestData.getData5State()+",");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 5)){  //大气湿度
			sb.append("\"weaData4\":\""+collLatestData.getData6()+"\",");
			sb.append("\"weaData4State\":"+collLatestData.getData6State()+",");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 6)){  //照度
			sb.append("\"weaData5\":\""+collLatestData.getData7()+"\",");
			sb.append("\"weaData5State\":"+collLatestData.getData7State()+",");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 7)){  //小时雨量
			sb.append("\"weaData6\":\""+collLatestData.getData8()+"\",");
			sb.append("\"weaData6State\":"+collLatestData.getData8State()+",");
		}
		byte dataConfig1 = dataConfig[0];
		if(DataUtil.byteBitChecked(dataConfig1, 0)){  //日雨量
			sb.append("\"weaData7\":\""+collLatestData.getData9()+"\",");
			sb.append("\"weaData7State\":"+collLatestData.getData9State()+",");
		}
	}
	
	/**
	 * 根据配置设置水质最新数据
	 * @param config
	 * @param collLatestData
	 * @param sb
	 */
	public void setWaterData(int config, CollLatestData collLatestData, StringBuffer sb){
		byte[] dataConfig = DataUtil.intToByteArray(config);
		
		byte dataConfig0 = dataConfig[1];
		if(DataUtil.byteBitChecked(dataConfig0, 2)){  //源水PH
			sb.append("\"waterData1\":\""+collLatestData.getData3()+"\",");
			sb.append("\"waterData1State\":"+collLatestData.getData3State()+",");
		}
		if(DataUtil.byteBitChecked(dataConfig0, 3)){  //源水浊度
			sb.append("\"waterData2\":\""+collLatestData.getData4()+"\",");
			sb.append("\"waterData2State\":"+collLatestData.getData4State()+",");
		}
	}
}
