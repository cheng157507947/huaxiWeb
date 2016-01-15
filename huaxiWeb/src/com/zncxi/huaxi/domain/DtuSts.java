package com.zncxi.huaxi.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * DTU设备状态
 * @author wangzz
 */
@Entity
@Table(name = "t_dtuSts")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DtuSts {
	
	/**
	 * 设备ID
	 */
	@Id
	@Column(name = "d_id")
	private String id;
	
	/**
	 * 是否在线
	 * 1:在线
	 * 0:离线
	 */
	@Column(name = "d_online")
	private Integer online;
	
	/**
	 * 最近注册时间
	 */
	@Column(name = "d_regTime")
	private Date regTime;
	
	/**
	 * 最近通信时间
	 */
	@Column(name = "d_commTime")
	private Date commTime;
	
	/**
	 * 设备类型
	 * 1. 田块
	 * 2. 泵房+水质
	 * 3. 泵房+气象
	 */
	@Column(name = "d_type")
	private Integer type;
	
	/**
	 * 传感器状态<br>
	 * 全部健康为:1111 1111 1111 1111<br>
	 * 有故障则为0
	 */
	@Column(name = "d_senSts")
	private Integer senSts;
	
	/**
	 * DTU故障状态
	 * 1:正常
	 * 0:故障
	 */
	@Column(name = "d_defSts")
	private Integer defSts;
	
	/**
	 * 状态描述
	 */
	@Column(name = "d_stsDesc")
	private String statusDesc;
	
	public String getOnlineStr() {
		String str = "";
		if(online == 1) {
			str = "在线";
		}
		if(online == 0) {
			str = "离线";
		}
		return str;
	}
	
	public String getTypeStr() {
		String str = "";
		if(type == 1) {
			str = "田块";
		}
		if(type == 2) {
			str = "泵房+水质";
		}
		if(type == 3) {
			str = "泵房+气象";
		}
		return str;
	}
	
	public String getDefStsStr() {
		String str = "";
		if(defSts == 1) {
			str = "正常";
		}
		if(defSts == 0) {
			str = "故障";
		}
		return str;
	}
	
	public String getSenStsStr() {
		StringBuffer sbf = new StringBuffer();
		if(senSts != null) {
			int sen = senSts.intValue();
			if(type != null) {
				if(type == 1) { //田块
					if((sen & 1) == 0) { //0001
						sbf.append("田块1水位传感器故障;");
					}
					if((sen & 2) == 0) { //0010
						sbf.append("田块2水位传感器故障;");
					}
					if((sen & 4) == 0) { //0100
						sbf.append("田块3水位传感器故障;");
					}
					if((sen & 8) == 0) { //1000
						sbf.append("田块4水位传感器故障;");
					}
					if((sen & 16) == 0) { //0001 0000
						sbf.append("土壤含湿量传感器故障;");
					}
					if((sen & 32) == 0) { //0010 0000
						sbf.append("片区#1进水管瞬时流量传感器故障;");
					}
					if((sen & 64) == 0) { //0100 0000
						sbf.append("片区#1进水管累计流量传感器故障;");
					}
					if((sen & 128) == 0) { //1000 0000
						sbf.append("片区#2进水管瞬时流量传感器故障;");
					}
					if((sen & 256) == 0) { //0001 0000 0000
						sbf.append("片区#2进水管累计流量传感器故障;");
					}
					if((sen & 512) == 0) { //0010 0000 0000
						sbf.append("片区#3进水管瞬时流量传感器故障;");
					}
					if((sen & 1024) == 0) { //0100 0000 0000
						sbf.append("片区#3进水管累计流量传感器故障;");
					}
				}
				if(type == 2) { //泵房+水质
					if((sen & 1) == 0) { //0001
						sbf.append("水源地水位传感器故障;");
					}
					if((sen & 2) == 0) { //0010
						sbf.append("水泵出口压力传感器故障;");
					}
					if((sen & 4) == 0) { //0100
						sbf.append("源水PH传感器故障;");
					}
					if((sen & 8) == 0) { //1000
						sbf.append("源水浊度传感器故障;");
					}
				}
				if(type == 3) { //泵房+气象
					if((sen & 1) == 0) { //0001
						sbf.append("水源地水位传感器故障;");
					}
					if((sen & 2) == 0) { //0010
						sbf.append("水泵出口压力传感器故障;");
					}
					if((sen & 4) == 0) { //0100
						sbf.append("风速传感器故障;");
					}
					if((sen & 8) == 0) { //1000
						sbf.append("风向传感器故障;");
					}
					if((sen & 16) == 0) { //0001 0000
						sbf.append("大气温度传感器故障;");
					}
					if((sen & 32) == 0) { //0010 0000
						sbf.append("大气湿度传感器故障;");
					}
					if((sen & 64) == 0) { //0100 0000
						sbf.append("照度传感器故障;");
					}
					if((sen & 128) == 0) { //1000 0000
						sbf.append("小时雨量传感器故障;");
					}
					if((sen & 256) == 0) { //0001 0000 0000
						sbf.append("日雨量传感器故障;");
					}
				}
			}
			
		}
		String str = sbf.toString();
		if("".equals(str)) {
			str = "正常";
		}
		return str;
	}
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public Date getCommTime() {
		return commTime;
	}

	public void setCommTime(Date commTime) {
		this.commTime = commTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSenSts() {
		return senSts;
	}

	public void setSenSts(Integer senSts) {
		this.senSts = senSts;
	}

	public Integer getDefSts() {
		return defSts;
	}

	public void setDefSts(Integer defSts) {
		this.defSts = defSts;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}


}
