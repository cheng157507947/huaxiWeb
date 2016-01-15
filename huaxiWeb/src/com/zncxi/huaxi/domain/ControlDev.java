package com.zncxi.huaxi.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 控制设备
 * @author wangzz
 */
@Entity
@Table(name = "t_controlDev")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ControlDev {
	
	/**
	 * DTU控制设备Id生成规则：
	 * dtuDev_id-设备序号(从1开始)
	 * 泵房控制设备默认：dtuDev_id-1
	 */
	@Id
	@Column(name = "c_id")
	private String id;
	
	/**
	 * 所属DTU设备
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "dtuDev_id")
	private DtuDev dtuDev;
	
	/**
	 * 所属DTU顺序号
	 */
	@Column(name = "c_orderNo")
	private Integer orderNo;
	
	/**
	 * 设备名称
	 */
	@Column(name = "c_name")
	private String name;
	
	/**
	 * 对应田块水位计
	 * 1：田块1水位计
	 * 2：田块2水位计
	 * 3：田块3水位计
	 * 4：田块4水位计
	 */
	@Column(name = "c_wlgIndex")
	private Integer wlgIndex;
	
	/**
	 * 状态
	 */
	@Transient
	private ControlDevSts controlDevSts;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DtuDev getDtuDev() {
		return dtuDev;
	}

	public void setDtuDev(DtuDev dtuDev) {
		this.dtuDev = dtuDev;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getWlgIndex() {
		return wlgIndex;
	}

	public void setWlgIndex(Integer wlgIndex) {
		this.wlgIndex = wlgIndex;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public ControlDevSts getControlDevSts() {
		return controlDevSts;
	}

	public void setControlDevSts(ControlDevSts controlDevSts) {
		this.controlDevSts = controlDevSts;
	}

}
