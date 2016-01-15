package com.zncxi.huaxi.util.vo;

import java.util.Date;

/**
 * 图片预测信息展示类
 * @author xiaoCheng
 *
 */
public class PicPredictVO {

	/**
	 * 生长期
	 */
	private String riceCycle;
	
	/**
	 * 单株图片路径
	 */
	private String singleShowPath;

	/**
	 * 生长期描述
	 */
	private String cycleDescribe;
	
	/**
	 * 用药描述
	 */
	private String pesticideDescribe;
	
	/**
	 * 用药备注
	 */
	private String pesticideDesc;
	
	/**
	 * 种植总面积
	 */
	private Double totalArea;
	
	/**
	 * 预测总产量
	 */
	private String totalYield;
	
	/**
	 * 预测亩产量
	 */
	private String predictYield;
	
	/**
	 * 全景图拍摄面积
	 */
	private Integer area;
	
	/**
	 * 图片采集时间
	 */
	private Date capTime;
	
	/**
	 * 全景图路径
	 */
	private String fullShowPath;

	public String getRiceCycle() {
		return riceCycle;
	}

	public void setRiceCycle(String riceCycle) {
		this.riceCycle = riceCycle;
	}

	public String getSingleShowPath() {
		return singleShowPath;
	}

	public void setSingleShowPath(String singleShowPath) {
		this.singleShowPath = singleShowPath;
	}

	public String getCycleDescribe() {
		return cycleDescribe;
	}

	public void setCycleDescribe(String cycleDescribe) {
		this.cycleDescribe = cycleDescribe;
	}

	public String getPesticideDescribe() {
		return pesticideDescribe;
	}

	public void setPesticideDescribe(String pesticideDescribe) {
		this.pesticideDescribe = pesticideDescribe;
	}

	public String getPesticideDesc() {
		return pesticideDesc;
	}

	public void setPesticideDesc(String pesticideDesc) {
		this.pesticideDesc = pesticideDesc;
	}

	public Double getTotalArea() {
		return totalArea;
	}

	public void setTotalArea(Double totalArea) {
		this.totalArea = totalArea;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Date getCapTime() {
		return capTime;
	}

	public void setCapTime(Date capTime) {
		this.capTime = capTime;
	}

	public String getFullShowPath() {
		return fullShowPath;
	}

	public void setFullShowPath(String fullShowPath) {
		this.fullShowPath = fullShowPath;
	}

	public String getTotalYield() {
		return totalYield;
	}

	public void setTotalYield(String totalYield) {
		this.totalYield = totalYield;
	}

	public String getPredictYield() {
		return predictYield;
	}

	public void setPredictYield(String predictYield) {
		this.predictYield = predictYield;
	}
}
