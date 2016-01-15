package com.zncxi.huaxi.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 控制设备状态
 * @author wangzz
 */
@Entity
@Table(name = "t_controlDevSts")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ControlDevSts {
	
	/**
	 * 控制设备ID
	 */
	@Id
	@Column(name = "c_id")
	private String id;
	
	/**
	 * 开关状态
	 * 1:开
	 * 0:关
	 * 2:执行中
	 */
	@Column(name = "c_state")
	private Integer state;
	
	/**
	 * 最新操作时间
	 */
	@Column(name = "c_ctlTime")
	private Date ctlTime;
	
	/**
	 * 故障状态
	 * 1:正常
	 * 0:故障
	 */
	@Column(name = "c_defSts")
	private Integer defSts;
	
	public ControlDevSts() {}
	
	public ControlDevSts(String ctrDevId) {
		this.id = ctrDevId;
		this.state = 0;
		this.defSts = 1;
	}
	
	public String getStateStr() {
		String str = "";
		if(state != null) {
			if(state == 1) {
				str = "开";
			}
			if(state == 0) {
				str = "关";
			}
			if(state == 1) {
				str = "执行中";
			}
		}
		return str;
	}
	
	public String getDefStsStr() {
		String str = "";
		if(defSts != null) {
			if(defSts == 1) {
				str = "正常";
			}
			if(defSts == 0) {
				str = "故障";
			}
		}
		return str;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getCtlTime() {
		return ctlTime;
	}

	public void setCtlTime(Date ctlTime) {
		this.ctlTime = ctlTime;
	}

	public Integer getDefSts() {
		return defSts;
	}

	public void setDefSts(Integer defSts) {
		this.defSts = defSts;
	}
	

}
