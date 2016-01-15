package com.zncxi.huaxi.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zncxi.base.dao.BaseDao;
import com.zncxi.base.service.BaseService;
import com.zncxi.huaxi.dao.ControlDevOperateDao;
import com.zncxi.huaxi.domain.ControlDevOperate;
import com.zncxi.huaxi.domain.ControlDevSts;

@Service
@Transactional
public class ControlDevOperateService extends BaseService<ControlDevOperate, String>{
	
	@Autowired
	private ControlDevOperateDao controlDevOperateDao;
	@Autowired
	private ControlDevStsService controlDevStsService;
	
	@Override
	protected BaseDao<ControlDevOperate, String> getEntityDao() {
		return this.controlDevOperateDao;
	}
	
	/**
	 * 更新设备操作表，进行设备操作
	 * @param id
	 * @param state
	 */
	public void operateControlDev(String id, Integer state){
		Date date = new Date();
		
		ControlDevOperate operate = new ControlDevOperate();
		operate.setId(id);
		operate.setDate(date);
		operate.setType(state);
		operate.setState(1);
		operate.setResult(0);
		this.save(operate);
		
		ControlDevSts controlDevSts = this.controlDevStsService.findById(id);
		controlDevSts.setCtlTime(date);
		this.controlDevStsService.save(controlDevSts);
	}
}
