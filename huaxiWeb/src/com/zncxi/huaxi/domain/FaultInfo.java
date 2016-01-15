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
 * 故障信息
 * @author wangzz
 */
@Entity
@Table(name = "t_faultInfo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FaultInfo {
	
	@Id
	@GenericGenerator(name = "uuid",strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "uuid")
	@Column(name = "f_id")
	private String id;
	
	/**
	 * 所属DTU设备编号
	 */
	@Column(name = "f_dtuDevId")
	private String dtuDevId;
	
	/**
	 * 故障对象 
	 * 0:平台
	 * 1:DTU网关
	 * 2:传感器
	 */
	@Column(name = "f_object")
	private Integer object = 0;
	
	/**
	 * 故障描述
	 */
	@Column(name = "f_desc")
	private String desc;
	
	
	/**
	 * 故障等级    0: 轻微 	1: 中度     2: 严重
	 */
	@Column(name = "f_grade")
	private Integer grade = 0;
	
	/**
	 * 发生故障时间
	 */
	@Column(name = "f_occurTime")
	private Date occurTime = new Date();
	
	public String getObjectStr() {
		String str = "";
		if(object == 0) {
			str = "平台";
		}
		if(object == 1) {
			str = "DTU网关";
		}
		if(object == 2) {
			str = "传感器";
		}
		return str;
	}
	
	public String getGradeStr() {
		String str = "";
		if(grade == 0) {
			str = "轻微";
		}
		if(grade == 1) {
			str = "中度";
		}
		if(grade == 2) {
			str = "严重";
		}
		return str;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getObject() {
		return object;
	}

	public void setObject(Integer object) {
		this.object = object;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Date getOccurTime() {
		return occurTime;
	}

	public void setOccurTime(Date occurTime) {
		this.occurTime = occurTime;
	}

	public String getDtuDevId() {
		return dtuDevId;
	}

	public void setDtuDevId(String dtuDevId) {
		this.dtuDevId = dtuDevId;
	}
	
	
	
}
