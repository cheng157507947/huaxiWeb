package com.zncxi.huaxi.domain;

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
 * 报警历史信息
 * @author xiaoCheng
 *
 */
@Entity
@Table(name = "t_alarmHisData")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlarmHisData {

	@Id
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "uuid")
	@Column(name = "ah_id")
	private String id;
	
	/**
	 * 场景ID
	 */
	@Column(name = "ah_sceneId")
	private String sceneId;
	
	/**
	 * 设备ID
	 */
	@Column(name = "ah_dtuDevId")
	private String dtuDevId;
	
	/**
	 * 报警时间
	 */
	@Column(name = "ah_date")
	private Date date;
	
	/**
	 * 描述
	 */
	@Column(name = "ah_describe")
	private String describe;

	public String getSceneId() {
		return sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}

	public String getDtuDevId() {
		return dtuDevId;
	}

	public void setDtuDevId(String dtuDevId) {
		this.dtuDevId = dtuDevId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
