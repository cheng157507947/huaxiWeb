package com.zncxi.huaxi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zncxi.base.dao.BaseDao;
import com.zncxi.base.service.BaseService;
import com.zncxi.huaxi.dao.AlarmConfigDao;
import com.zncxi.huaxi.domain.AlarmConfig;

@Service
public class AlarmConfigService extends BaseService<AlarmConfig, String> {

	@Autowired
	private AlarmConfigDao alarmConfigDao;
	
	@Override
	protected BaseDao<AlarmConfig, String> getEntityDao() {
		return alarmConfigDao;
	}

}
