package com.zncxi.huaxi.domain.growpc;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * 农药记录表
 * @author xiaoCheng
 *
 */
@Entity
@Table(name = "t_pesticidesRecord")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PesticidesRecord {

	@Id
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "uuid")
	@Column(name = "pr_id")
	private String id;
	
	/**
	 * 农药名称
	 */
	@Column(name = "pr_name")
	private String name;
	
	/**
	 * 农药施用量
	 */
	@Column(name = "pr_amount")
	private Double amount;
	
	/**
	 * 农药单位
	 */
	@Column(name = "pr_unit")
	private String unit;
	
	/**
	 * 施用时间
	 */
	@Column(name = "pr_date")
	private Date date;
	
	/**
	 * 备注
	 */
	@Column(name = "pr_desc")
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
