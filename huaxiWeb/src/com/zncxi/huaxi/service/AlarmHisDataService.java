package com.zncxi.huaxi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zncxi.base.dao.BaseDao;
import com.zncxi.base.service.BaseService;
import com.zncxi.huaxi.dao.AlarmHisDataDao;
import com.zncxi.huaxi.domain.AlarmHisData;

@Service
public class AlarmHisDataService extends BaseService<AlarmHisData, String> {

	@Autowired
	private AlarmHisDataDao alarmHisDataDao;
	
	@Override
	protected BaseDao<AlarmHisData, String> getEntityDao() {
		return alarmHisDataDao;
	}

}
