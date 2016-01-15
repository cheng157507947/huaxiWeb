package com.zncxi.huaxi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zncxi.base.dao.BaseDao;
import com.zncxi.base.service.BaseService;
import com.zncxi.huaxi.dao.ControlDevDao;
import com.zncxi.huaxi.dao.ControlDevOperateDao;
import com.zncxi.huaxi.dao.ControlDevStsDao;
import com.zncxi.huaxi.domain.ControlDev;
import com.zncxi.huaxi.domain.ControlDevOperate;
import com.zncxi.huaxi.domain.ControlDevSts;

@Service
public class ControlDevService extends BaseService<ControlDev, String>{
	
	@Autowired
	private ControlDevDao controlDevDao;
	
	@Autowired
	private ControlDevStsDao controlDevStsDao;
	
	@Autowired
	private ControlDevOperateDao controlDevOperateDao;
	
	@Override
	protected BaseDao<ControlDev, String> getEntityDao() {
		return this.controlDevDao;
	}

	@Override
	@Transactional
	public void save(ControlDev controlDev) {
		if(controlDev.getId() == null) {
			controlDev.setId(controlDev.getDtuDev().getId() + "-" + controlDev.getOrderNo());
		}
		controlDev = controlDevDao.save(controlDev);
		String ctrDevId = controlDev.getId();
		
		ControlDevSts sts = controlDevStsDao.findOne(ctrDevId);
		if(sts == null) {
			sts = new ControlDevSts(ctrDevId);
			controlDevStsDao.save(sts);
		}
		
		ControlDevOperate op = controlDevOperateDao.findOne(ctrDevId);
		if(op == null) {
			op = new ControlDevOperate();
			op.setId(ctrDevId);
			controlDevOperateDao.save(op);
		}
	}
	
	public List<ControlDev> findControlDevByDtuDevId(String dtuDevId){
		return this.controlDevDao.findControlDevByDtuDevIdOrderByOrderNoAsc(dtuDevId);
	}
	
	public List<ControlDev> findControlDevByDtuDevIdAndWlgIndex(String dtuDevId, Integer wlgIndex){
		return this.controlDevDao.findControlDevByDtuDevIdAndWlgIndexOrderByOrderNoAsc(dtuDevId,wlgIndex);
	}
}
