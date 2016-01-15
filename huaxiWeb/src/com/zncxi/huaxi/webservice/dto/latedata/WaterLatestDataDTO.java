package com.zncxi.huaxi.webservice.dto.latedata;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.zncxi.huaxi.webservice.dto.adapter.DateTimeAdapter;

/**
 * 水源地最新数据反馈
 * 源水PH，源水浊度
 * @author xiaoCheng
 *
 */
@XmlRootElement
public class WaterLatestDataDTO {

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
}
