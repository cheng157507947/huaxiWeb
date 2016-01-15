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

import com.zncxi.huaxi.util.enumeration.SceneGroup;

/**
 * 场景信息
 * @author wangzz
 */
@Entity
@Table(name = "t_region")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Scene {
	
	@Id
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "uuid")
	@Column(name = "s_id")
	private String id;
	
	/**
	 * 名称
	 */
	@Column(name = "s_name")
	private String name;
	
	/**
	 * 类型
	 * 1. 田块
	 * 2. 泵房
	 * 3. 水质
	 * 4. 气象
	 */
	@Column(name = "s_type")
	private Integer type;
	
	/**
	 * 所属分组
	 * 0. 华西南区（华西一站）
	 * 1. 华西北区（华西二站）
	 * 2. 气象站
	 * 3. 水源地
	 */
	@Column(name = "s_group")
	private SceneGroup group;
	
	/**
	 * 对应DTU设备
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "dtuDev_id")
	private DtuDev dtuDev;
	
	/**
	 * 备注
	 */
	@Column(name = "s_desc")
	private String desc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public SceneGroup getGroup() {
		return group;
	}

	public void setGroup(SceneGroup group) {
		this.group = group;
	}

	public DtuDev getDtuDev() {
		return dtuDev;
	}

	public void setDtuDev(DtuDev dtuDev) {
		this.dtuDev = dtuDev;
	}
	
}
