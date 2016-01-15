package com.zncxi.huaxi.webservice.dto.controldev;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 控制命令状态信息反馈
 * @author xiaoCheng
 *
 */
@XmlRootElement
public class OperateStateDTO {

	public String id;
	
	/**
	 * 开关状态
	 * -1.出错，0.操作请求，1.命令下发，2.操作中，3.成功，4.失败，5.超时
	 */
	public Integer state;
}
