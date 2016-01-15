package com.zncxi.huaxi.webservice.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.zncxi.huaxi.webservice.dto.adapter.DateTimeAdapter;

/**
 * 场景查询返回信息
 * @author xiaoCheng
 *
 */
@XmlRootElement
public class SceneDTO {

	public String id;
	

	/**
	 * 名称
	 */
	public String name;
	
	/**
	 * 类型
	 * 1. 田块
	 * 2. 泵房
	 * 3. 水质
	 * 4. 气象
	 */
	public Integer type;
	
	/**
	 * 所属分组
	 * 0. 华西南区（华西一站）
	 * 1. 华西北区（华西二站）
	 * 2. 气象站
	 * 3. 水源地
	 */
	public Integer group;
	
	/**
	 * 备注
	 */
	public String desc;
	
	/**
	 * 设备是否在线
	 * 1:在线
	 * 0:离线
	 */
	public Integer online;
	
	/**
	 * 设备故障状态
	 * 1:正常
	 * 0:故障
	 */
	public Integer defSts;
	
	/**
	 * 设备最近通信时间
	 */
	@XmlJavaTypeAdapter(DateTimeAdapter.class)
	public Date commTime;
}
