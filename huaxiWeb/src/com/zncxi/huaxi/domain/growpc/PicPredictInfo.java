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
 * 图片预测信息表
 * @author xiaoCheng
 *
 */
@Entity
@Table(name = "t_picPredictInfo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PicPredictInfo {

	@Id
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "uuid")
	@Column(name = "pi_id")
	private String id;
	
	/**
	 * 总亩数
	 */
	@Column(name = "pi_totalArea")
	private Double totalArea;
	
	/**
	 * 图片拍摄角度
	 */
	@Column(name = "pi_angle")
	private Double angle;
	
	/**
	 * 水稻种植时间
	 */
	@Column(name = "pi_plantTime")
	private Date plantTime;
	
	/**
	 * 图片拍摄面积
	 * 应≥10平米
	 */
	@Column(name = "pi_area")
	private Integer area;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getTotalArea() {
		return totalArea;
	}

	public void setTotalArea(Double totalArea) {
		this.totalArea = totalArea;
	}

	public Double getAngle() {
		return angle;
	}

	public void setAngle(Double angle) {
		this.angle = angle;
	}

	public Date getPlantTime() {
		return plantTime;
	}

	public void setPlantTime(Date plantTime) {
		this.plantTime = plantTime;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

}
