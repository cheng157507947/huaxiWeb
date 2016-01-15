package com.zncxi.huaxi.webservice.dto.controldev;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 设备操作信息
 * @author xiaoCheng
 *
 */
@XmlRootElement(name = "operaterDTO")
public class OperaterDTO {

	/**
	 * 控制设备ID
	 * 多个设备ID以，隔开
	 */
	public String controlIds;
	
	/**
	 * 控制目标状态
	 * 0：关
	 * 1：开
	 */
	public Integer orderState;
}
