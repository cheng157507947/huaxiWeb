package com.zncxi.huaxi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zncxi.base.dao.BaseDao;
import com.zncxi.base.service.BaseService;
import com.zncxi.huaxi.dao.DtuStsDao;
import com.zncxi.huaxi.domain.DtuSts;

@Service
public class DtuStsService extends BaseService<DtuSts, String>{
	
	@Autowired
	private DtuStsDao dtuStsDao;
	
	@Override
	protected BaseDao<DtuSts, String> getEntityDao() {
		return this.dtuStsDao;
	}
	
}
