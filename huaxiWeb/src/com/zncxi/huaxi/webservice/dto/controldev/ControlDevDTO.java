package com.zncxi.huaxi.webservice.dto.controldev;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.zncxi.huaxi.webservice.dto.adapter.DateTimeAdapter;

/**
 * 控制设备信息反馈
 * @author xiaoCheng
 *
 */
@XmlRootElement
public class ControlDevDTO {

	public String id;
	
	/**
	 * 设备名称
	 */
	public String name;
	
	/**
	 * 开关状态
	 * 1:开
	 * 0:关
	 * 2:执行中
	 */
	public Integer state;
	
	/**
	 * 故障状态
	 * 1:正常
	 * 0:故障
	 */
	public Integer defSts;
	
	/**
	 * 最新操作时间
	 */
	@XmlJavaTypeAdapter(DateTimeAdapter.class)
	public Date ctlTime;
}
