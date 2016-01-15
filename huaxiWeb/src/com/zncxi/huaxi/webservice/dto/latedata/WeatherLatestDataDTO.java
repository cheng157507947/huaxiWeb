package com.zncxi.huaxi.webservice.dto.latedata;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.zncxi.huaxi.webservice.dto.adapter.DateTimeAdapter;

/**
 * 气象站最新数据反馈
 * 风速，风向，大气温度，大气湿度，照度，小时雨量，日雨量
 * @author xiaoCheng
 *
 */
@XmlRootElement
public class WeatherLatestDataDTO {

	/**
	 * 采集时间
	 */
	@XmlJavaTypeAdapter(DateTimeAdapter.class)
	public Date collTime;
	
	/**
	 * 数据1
	 */
	public Double data1;
	
	/**
	 * 数据1当前状态
	 * 0:正常，1:偏大，2:偏小，3:异常
	 */
	public Integer data1State;
	
	/**
	 * 数据2
	 */
	public Double data2;
	
	/**
	 * 数据2当前状态
	 * 0:正常，1:偏大，2:偏小，3:异常
	 */
	public Integer data2State;
	
	/**
	 * 数据3
	 */
	public Double data3;
	
	/**
	 * 数据3当前状态
	 * 0:正常，1:偏大，2:偏小，3:异常
	 */
	public Integer data3State;
	
	/**
	 * 数据4
	 */
	public Double data4;
	
	/**
	 * 数据4当前状态
	 * 0:正常，1:偏大，2:偏小，3:异常
	 */
	public Integer data4State;
	
	/**
	 * 数据5
	 */
	public Double data5;
	
	/**
	 * 数据5当前状态
	 * 0:正常，1:偏大，2:偏小，3:异常
	 */
	public Integer data5State;
	
	/**
	 * 数据6
	 */
	public Double data6;
	
	/**
	 * 数据6当前状态
	 * 0:正常，1:偏大，2:偏小，3:异常
	 */
	public Integer data6State;
	
	/**
	 * 数据7
	 */
	public Double data7;
	
	/**
	 * 数据7当前状态
	 * 0:正常，1:偏大，2:偏小，3:异常
	 */
	public Integer data7State;
}
