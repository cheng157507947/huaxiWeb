package com.zncxi.huaxi.webservice.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 获取授权码返回结果
 * @author xiaoCheng
 *
 */
@XmlRootElement
public class UserDTO {

	/**
	 * 系统所分配的授权码
	 */
	public String authCode;
	
	/**
	 * 用户真实姓名
	 */
	public String userRealname;
	
	/**
	 * 用户编号
	 */
	public String userCode;
	
	/**
	 * 用户联系电话
	 */
	public String userPhone;
	
	/**
	 * 用户联系地址
	 */
	public String userAddress;
	
	/**
	 * 用户权限
	 */
	public String depart;
	/**
	 * 用户权限值
	 */
	public String departname;
}
