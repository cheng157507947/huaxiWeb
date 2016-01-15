package com.zncxi.huaxi.webservice.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 视频信息
 * @author xiaoCheng
 *
 */
@XmlRootElement
public class VideoDTO {
	/**
	 * 名称
	 */
	public String name;
	
	/**
	 * 设备IP地址
	 */
	public String ip;
	
	/**
	 * 端口
	 */
	public String port;
	
	/**
	 * 用户名
	 */
	public String username;

	/**
	 * 密码
	 */
	public String password;
	
	/**
	 * 视频GUID
	 */
	public String guid;
	
	/**
	 * 摄像头类型
	 * 0.球机
	 * 1.枪机
	 */
	public Integer type;
}
