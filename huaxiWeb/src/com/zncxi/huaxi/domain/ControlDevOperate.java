package com.zncxi.huaxi.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 控制设备操作
 * @author wangzz
 */
@Entity
@Table(name = "t_controlDevOperate")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ControlDevOperate {
	
	/**
	 * 控制设备ID
	 */
	@Id
	@Column(name = "co_id")
	private String id;
	
	/**
	 * 操作类型
	 * 0：关
	 * 1：开
	 */
	@Column(name = "co_type")
	private Integer type;
	
	/**
	 * 请求时间
	 */
	@Column(name = "co_date")
	private Date date;
	
	/**
	 * 操作状态
	 * 0：完成
	 * 1：操作请求
	 * 2：命令下发
	 */
	@Column(name = "co_state")
	private Integer state;
	
	/**
	 * 操作结果
	 * 0 操作中  1 成功  2 失败  3 超时  4 设备无效  5数据库配置错误
	 */
	@Column(name = "co_result")
	private Integer result;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}
	
	
	
	
}
