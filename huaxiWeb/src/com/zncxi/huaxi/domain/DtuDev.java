package com.zncxi.huaxi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * DTU设备
 * @author wangzz
 */
@Entity
@Table(name = "t_dtuDev")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DtuDev {

	/**
	 * 设备ID
	 */
	@Id
	@Column(name = "d_id")
	private String id;
	
	/**
	 * 设备IP
	 */
	@Column(name = "d_ip")
	private String ip;
	
	/**
	 * 设备端口
	 */
	@Column(name = "d_port")
	private String port;
	
	/**
	 * 设备类型
	 * 1. 田块
	 * 2. 泵房+水质
	 * 3. 泵房+气象
	 */
	@Column(name = "d_type")
	private Integer type;
	
	/**
	 * 数据采集间隔(分钟)
	 */
	@Column(name = "d_interval")
	private Integer interval;
	
	/**
	 * 采集配置<br>
	 * 全部采集为:1111 1111 1111 1111<br>
	 * 不采集则为0
	 * 配置依次为（右向左）：
	 * 	田块：
	 *   田块1水位，田块2水位，田块3水位，田块4水位，土壤含湿量，片区#1进水管瞬时流量，片区#1进水管累计流量，片区#2进水管瞬时流量，片区#2进水管累计流量，片区#3进水管瞬时流量，片区#3进水管累计流量
	 *  泵房+水质：
	 *   水源地水位，水泵出口压力，源水PH，源水浊度
	 *  泵房+气象：
	 *   水源地水位，水泵出口压力，风速，风向，大气温度，大气湿度，照度，小时雨量，日雨量
	 */
	@Column(name = "d_config")
	private Integer config;
	
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
	
	public String getConfigStr() {
		StringBuffer sbf = new StringBuffer();
		if(config != null) {
			int sen = config.intValue();
			if(type != null) {
				if(type == 1) { //田块
					if((sen & 1) == 1) { //0001
						sbf.append("田块1水位;");
					}
					if((sen & 2) == 2) { //0010
						sbf.append("田块2水位;");
					}
					if((sen & 4) == 4) { //0100
						sbf.append("田块3水位;");
					}
					if((sen & 8) == 8) { //1000
						sbf.append("田块4水位;");
					}
					if((sen & 16) == 16) { //0001 0000
						sbf.append("土壤含湿量;");
					}
					if((sen & 32) == 32) { //0010 0000
						sbf.append("片区#1进水管瞬时流量;");
					}
					if((sen & 64) == 64) { //0100 0000
						sbf.append("片区#1进水管累计流量;");
					}
					if((sen & 128) == 128) { //1000 0000
						sbf.append("片区#2进水管瞬时流量;");
					}
					if((sen & 256) == 256) { //0001 0000 0000
						sbf.append("片区#2进水管累计流量;");
					}
					if((sen & 512) == 512) { //0010 0000 0000
						sbf.append("片区#3进水管瞬时流量;");
					}
					if((sen & 1024) == 1024) { //0100 0000 0000
						sbf.append("片区#3进水管累计流量;");
					}
				}
				if(type == 2) { //泵房+水质
					if((sen & 1) == 1) { //0001
						sbf.append("水源地水位;");
					}
					if((sen & 2) == 2) { //0010
						sbf.append("水泵出口压力;");
					}
					if((sen & 4) == 4) { //0100
						sbf.append("源水PH;");
					}
					if((sen & 8) == 8) { //1000
						sbf.append("源水浊度;");
					}
				}
				if(type == 3) { //泵房+气象
					if((sen & 1) == 1) { //0001
						sbf.append("水源地水位;");
					}
					if((sen & 2) == 2) { //0010
						sbf.append("水泵出口压力;");
					}
					if((sen & 4) == 4) { //0100
						sbf.append("风速;");
					}
					if((sen & 8) == 8) { //1000
						sbf.append("风向;");
					}
					if((sen & 16) == 16) { //0001 0000
						sbf.append("大气温度;");
					}
					if((sen & 32) == 32) { //0010 0000
						sbf.append("大气湿度;");
					}
					if((sen & 64) == 64) { //0100 0000
						sbf.append("照度;");
					}
					if((sen & 128) == 128) { //1000 0000
						sbf.append("小时雨量;");
					}
					if((sen & 256) == 256) { //0001 0000 0000
						sbf.append("日雨量;");
					}
				}
			}
			
		}
		String str = sbf.toString();
		if("".equals(str)) {
			str = "无";
		}
		return str;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public Integer getConfig() {
		return config;
	}

	public void setConfig(Integer config) {
		this.config = config;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
