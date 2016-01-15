package com.zncxi.huaxi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zncxi.base.dao.BaseDao;
import com.zncxi.base.service.BaseService;
import com.zncxi.huaxi.dao.ControlDevStsDao;
import com.zncxi.huaxi.domain.ControlDevSts;

@Service
public class ControlDevStsService extends BaseService<ControlDevSts, String>{
	
	@Autowired
	private ControlDevStsDao controlDevStsDao;
	
	@Override
	protected BaseDao<ControlDevSts, String> getEntityDao() {
		return this.controlDevStsDao;
	}
	
}
