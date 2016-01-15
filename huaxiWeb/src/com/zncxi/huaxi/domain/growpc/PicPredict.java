package com.zncxi.huaxi.domain.growpc;

import java.io.File;
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
 * 图片预测表
 * @author xiaoCheng
 *
 */
@Entity
@Table(name = "t_picPredict")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PicPredict {

	@Id
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "uuid")
	@Column(name = "p_id")
	private String id;
	
	/**
	 * 全景图片存储路径
	 * 
	 * 本地存储的绝对路径
	 * 如果读入的路径无法正确读到图像，则在产量预测字段显示0，表示图片路径出错
	 */
	@Column(name = "p_fullPath")
	private String fullPath;
	
	/**
	 * 单株图片存储路径
	 * 
	 * 本地存储的绝对路径
	 */
	@Column(name = "p_singlePath")
	private String singlePath;
	
	/**
	 * 图片采集时间
	 * 
	 * 格式如：2015-06-12，如果采集时间错误，在生长期中显示“时间格式错误”，如果时间不在水稻生长期内，则显示“该时间段不属于水稻生长期”
	 */
	@Column(name = "p_capTime")
	private Date capTime;
	
	/**
	 * 图片拍摄角度
	 * 
	 * 保留，暂未使用
	 */
	@Column(name = "p_angle")
	private Double angle;
	
	/**
	 * 水稻种植时间
	 * 
	 * 保留，暂未使用
	 */
	@Column(name = "p_plantTime")
	private Date plantTime;
	
	/**
	 * 产量亩预测
	 * 
	 * 0：面积或路径出错；-1：非灌浆抽穗期无法预测；-2：面积应该大于10平米；其余表示预测得到的亩产量
	 */
	@Column(name = "p_predictYield")
	private Integer predictYield;
	
	/**
	 * 图片拍摄面积
	 * 
	 * 应≥10平米，如果小于10平米，则在产量预测字段显示0，表示图片路径出错
	 */
	@Column(name = "p_area")
	private Integer area;
	
	/**
	 * 总亩数
	 * 
	 * 预测地区总面积（亩）
	 */
	@Column(name = "pi_totalArea")
	private Double totalArea;
	
	/**
	 * 稻穗数量
	 * 
	 * 保留，暂未使用
	 */
	@Column(name = "p_riceAmount")
	private Integer riceAmount;
	
	/**
	 * 是否预测完毕
	 * 
	 * 初始化为1，表示需要预测，等我这边的程序处理完成后改为0，表示无需预测
	 */
	@Column(name = "p_needPredict")
	private Integer needPredict;
	
	/**
	 * 生长期
	 * 
	 * 0：错误的时间格式；
	 * 10：没有种植水稻的时间；
	 * 1：分蘖期		播种至7.31；
	 * 2：拔节孕穗期	8.1~8.25；
	 * 3：抽穗扬花期	8.26~9.5；
	 * 4：灌浆成熟期	9.6~10.31
	 */
	@Column(name = "p_riceCycle")
	private String riceCycle;
	
	/**
	 * 预测总产量
	 * 
	 * 预测后的亩产量*总亩数
	 */
	@Column(name = "p_totalYield")
	private Double totalYield;
	
	/**
	 * 修正总产量
	 */
	@Column(name = "p_reviseYield")
	private Double reviseYield;
	
	/**
	 * 备注
	 */
	@Column(name = "p_remarks")
	private String remarks;
	
	/**
	 * 全景图显示路径
	 */
	public String getFullShowPath() {
		return this.changeShowPath(this.getFullPath());
	}

	/**
	 * 单柱图显示路径
	 */
	public String getSingleShowPath() {
		return this.changeShowPath(this.getSinglePath());
	}
	
	/**
	 * 将绝对路径改为页面显示的相对路径
	 * @param path
	 * @return
	 */
	private String changeShowPath(String path){
		path = path.substring(path.indexOf("images"));
		return path.replaceAll("\\"+File.separator, "/");
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCapTime() {
		return capTime;
	}

	public void setCapTime(Date capTime) {
		this.capTime = capTime;
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

	public Integer getPredictYield() {
		return predictYield;
	}

	public void setPredictYield(Integer predictYield) {
		this.predictYield = predictYield;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Integer getRiceAmount() {
		return riceAmount;
	}

	public void setRiceAmount(Integer riceAmount) {
		this.riceAmount = riceAmount;
	}

	public Integer getNeedPredict() {
		return needPredict;
	}

	public void setNeedPredict(Integer needPredict) {
		this.needPredict = needPredict;
	}

	public String getRiceCycle() {
		return riceCycle;
	}

	public void setRiceCycle(String riceCycle) {
		this.riceCycle = riceCycle;
	}

	public Double getTotalYield() {
		return totalYield;
	}

	public void setTotalYield(Double totalYield) {
		this.totalYield = totalYield;
	}

	public Double getReviseYield() {
		return reviseYield;
	}

	public void setReviseYield(Double reviseYield) {
		this.reviseYield = reviseYield;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public String getSinglePath() {
		return singlePath;
	}

	public void setSinglePath(String singlePath) {
		this.singlePath = singlePath;
	}

	public Double getTotalArea() {
		return totalArea;
	}

	public void setTotalArea(Double totalArea) {
		this.totalArea = totalArea;
	}
}
