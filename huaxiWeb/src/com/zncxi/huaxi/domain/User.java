package com.zncxi.huaxi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User {

	@Id
	@GenericGenerator(name = "uuid",strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "uuid")
	@Column(name = "u_id")
	private String id;
	
	/**
	 * 用户名
	 */
	@Column(name = "u_username")
	private String username;

	/**
	 * 登陆密码
	 */
	@Column(name = "u_password")
	private String password;
	
	/**
	 * 真实姓名
	 */
	@Column(name = "u_realname")
	private String realname;
	
	
	/**
	 * 联系电话
	 */
	@Column(name = "u_phone")
	private String phone;
	
	
	/**
	 * 超级管理员标志
	 * 1:超级管理员，0:一般用户(不能控制)
	 */
	@Column(name = "u_type")
	private Integer type = 0;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRealname() {
		return realname;
	}


	public void setRealname(String realname) {
		this.realname = realname;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}
	


}
