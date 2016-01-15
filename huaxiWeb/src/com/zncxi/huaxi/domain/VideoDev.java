package com.zncxi.huaxi.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * 视频设备
 * @author wangzz
 */
@Entity
@Table(name = "t_videoDev")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VideoDev {
	
	@Id
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "uuid")
	@Column(name = "v_id")
	private String id;
	
	/**
	 * 所属场景
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "scene_id")
	private Scene scene;
	
	/**
	 * 名称
	 */
	@Column(name = "v_name")
	private String name;
	
	/**
	 * 设备IP地址
	 */
	@Column(name = "v_ip")
	private String ip;
	
	/**
	 * 端口
	 */
	@Column(name = "v_port")
	private String port;
	
	/**
	 * 用户名
	 */
	@Column(name = "v_username")
	private String username;

	/**
	 * 密码
	 */
	@Column(name = "v_password")
	private String password;
	
	/**
	 * 视频GUID
	 */
	@Column(name = "v_guid")
	private String guid;
	
	/**
	 * 摄像头类型
	 * 0.球机
	 * 1.枪机
	 */
	@Column(name = "v_type")
	private Integer type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
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

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
