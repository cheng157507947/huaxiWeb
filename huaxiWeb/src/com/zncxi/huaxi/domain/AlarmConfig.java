package com.zncxi.huaxi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * 报警配置
 * @author xiaoCheng
 *
 */
@Entity
@Table(name = "t_alarmConfig")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlarmConfig {

	@Id
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "uuid")
	@Column(name = "a_id")
	private String id;
	
	/**
	 * 报警邮箱
	 */
	@Column(name = "a_emails")
	private String emails;
	
	/**
	 * 报警电话
	 */
	@Column(name = "a_phones")
	private String phones;
	
	/**
	 * 时间间隔
	 */
	@Column(name = "a_interval")
	private Integer interval;
	
	/**
	 * 水位最大值
	 */
	@Column(name = "a_waterLevelMax")
	private Double waterLevelMax;
	
	/**
	 * 水位最小值
	 */
	@Column(name = "a_waterLevelMin")
	private Double waterLevelMin;
	
	/**
	 * 土壤水分最大值
	 */
	@Column(name = "a_moistureMax")
	private Double moistureMax;
	
	/**
	 * 土壤水分最小值
	 */
	@Column(name = "a_moistureMin")
	private Double moistureMin;
	
	/**
	 * 瞬时流量最大值
	 */
	@Column(name = "a_inFlowMax")
	private Double inFlowMax;
	
	/**
	 * 瞬时流量最小值
	 */
	@Column(name = "a_inFlowMin")
	private Double inFlowMin;
	
	
	/**
	 * 水源地水位最大值
	 */
	@Column(name = "a_sourceWLMax")
	private Double sourceWLMax;
	
	/**
	 * 水源地水位最小值
	 */
	@Column(name = "a_sourceWLMin")
	private Double sourceWLMin;
	
	/**
	 * 水泵出口压力最大值
	 */
	@Column(name = "a_outPressureMax")
	private Double outPressureMax;
	
	/**
	 * 水泵出口压力最小值
	 */
	@Column(name = "a_outPressureMin")
	private Double outPressureMin;
	
	
	/**
	 * 源水PH最大值
	 */
	@Column(name = "a_sourcePHMax")
	private Double sourcePHMax;
	
	/**
	 * 源水PH最小值
	 */
	@Column(name = "a_sourcePHMin")
	private Double sourcePHMin;
	
	/**
	 * 源水浊度最大值
	 */
	@Column(name = "a_sourceNTUMax")
	private Double sourceNTUMax;
	
	/**
	 * 源水浊度最小值
	 */
	@Column(name = "a_sourceNTUMin")
	private Double sourceNTUMin;

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public Double getWaterLevelMax() {
		return waterLevelMax;
	}

	public void setWaterLevelMax(Double waterLevelMax) {
		this.waterLevelMax = waterLevelMax;
	}

	public Double getWaterLevelMin() {
		return waterLevelMin;
	}

	public void setWaterLevelMin(Double waterLevelMin) {
		this.waterLevelMin = waterLevelMin;
	}

	public Double getMoistureMax() {
		return moistureMax;
	}

	public void setMoistureMax(Double moistureMax) {
		this.moistureMax = moistureMax;
	}

	public Double getMoistureMin() {
		return moistureMin;
	}

	public void setMoistureMin(Double moistureMin) {
		this.moistureMin = moistureMin;
	}

	public Double getInFlowMax() {
		return inFlowMax;
	}

	public void setInFlowMax(Double inFlowMax) {
		this.inFlowMax = inFlowMax;
	}

	public Double getInFlowMin() {
		return inFlowMin;
	}

	public void setInFlowMin(Double inFlowMin) {
		this.inFlowMin = inFlowMin;
	}

	public Double getSourceWLMax() {
		return sourceWLMax;
	}

	public void setSourceWLMax(Double sourceWLMax) {
		this.sourceWLMax = sourceWLMax;
	}

	public Double getSourceWLMin() {
		return sourceWLMin;
	}

	public void setSourceWLMin(Double sourceWLMin) {
		this.sourceWLMin = sourceWLMin;
	}

	public Double getOutPressureMax() {
		return outPressureMax;
	}

	public void setOutPressureMax(Double outPressureMax) {
		this.outPressureMax = outPressureMax;
	}

	public Double getOutPressureMin() {
		return outPressureMin;
	}

	public void setOutPressureMin(Double outPressureMin) {
		this.outPressureMin = outPressureMin;
	}

	public Double getSourcePHMax() {
		return sourcePHMax;
	}

	public void setSourcePHMax(Double sourcePHMax) {
		this.sourcePHMax = sourcePHMax;
	}

	public Double getSourcePHMin() {
		return sourcePHMin;
	}

	public void setSourcePHMin(Double sourcePHMin) {
		this.sourcePHMin = sourcePHMin;
	}

	public Double getSourceNTUMax() {
		return sourceNTUMax;
	}

	public void setSourceNTUMax(Double sourceNTUMax) {
		this.sourceNTUMax = sourceNTUMax;
	}

	public Double getSourceNTUMin() {
		return sourceNTUMin;
	}

	public void setSourceNTUMin(Double sourceNTUMin) {
		this.sourceNTUMin = sourceNTUMin;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhones() {
		return phones;
	}

	public void setPhones(String phones) {
		this.phones = phones;
	}

	public String getEmails() {
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

}
