package com.zncxi.huaxi.webservice.dto.latedata;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.zncxi.huaxi.webservice.dto.adapter.DateTimeAdapter;

/**
 * 田块最新数据反馈
 * 田块1水位，田块2水位，田块3水位，田块4水位，土壤含湿量，片区#1进水管瞬时流量，片区#1进水管累计流量，片区#2进水管瞬时流量，片区#2进水管累计流量，片区#3进水管瞬时流量，片区#3进水管累计流量
 * @author xiaoCheng
 *
 */
@XmlRootElement
public class FieldLatestDataDTO {

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
	 * 0:正常，1:偏大，2:偏小，3:异常，null：此状态不需判断
	 */
	public Integer data1State;
	
	/**
	 * 数据2
	 */
	public Double data2;
	
	/**
	 * 数据2当前状态
	 * 0:正常，1:偏大，2:偏小，3:异常，null：此状态不需判断
	 */
	public Integer data2State;
	
	/**
	 * 数据3
	 */
	public Double data3;
	
	/**
	 * 数据3当前状态
	 * 0:正常，1:偏大，2:偏小，3:异常，null：此状态不需判断
	 */
	public Integer data3State;
	
	/**
	 * 数据4
	 */
	public Double data4;
	
	/**
	 * 数据4当前状态
	 * 0:正常，1:偏大，2:偏小，3:异常，null：此状态不需判断
	 */
	public Integer data4State;
	
	/**
	 * 数据5
	 */
	public Double data5;
	
	/**
	 * 数据5当前状态
	 * 0:正常，1:偏大，2:偏小，3:异常，null：此状态不需判断
	 */
	public Integer data5State;
	
	/**
	 * 数据6
	 */
	public Double data6;
	
	/**
	 * 数据6当前状态
	 * 0:正常，1:偏大，2:偏小，3:异常，null：此状态不需判断
	 */
	public Integer data6State;
	
	/**
	 * 数据7
	 */
	public Double data7;
	
	/**
	 * 数据7当前状态
	 * 0:正常，1:偏大，2:偏小，3:异常，null：此状态不需判断
	 */
	public Integer data7State;
	
	/**
	 * 数据8
	 */
	public Double data8;
	
	/**
	 * 数据8当前状态
	 * 0:正常，1:偏大，2:偏小，3:异常，null：此状态不需判断
	 */
	public Integer data8State;
	
	/**
	 * 数据9
	 */
	public Double data9;
	
	/**
	 * 数据9当前状态
	 * 0:正常，1:偏大，2:偏小，3:异常，null：此状态不需判断
	 */
	public Integer data9State;
	
	/**
	 * 数据10
	 */
	public Double data10;
	
	/**
	 * 数据10当前状态
	 * 0:正常，1:偏大，2:偏小，3:异常，null：此状态不需判断
	 */
	public Integer data10State;
	
	/**
	 * 数据11
	 */
	public Double data11;
	
	/**
	 * 数据11当前状态
	 * 0:正常，1:偏大，2:偏小，3:异常，null：此状态不需判断
	 */
	public Integer data11State;
}
