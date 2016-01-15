package com.zncxi.huaxi.dao;

import com.zncxi.base.dao.BaseDao;
import com.zncxi.huaxi.domain.CollLatestData;

public interface CollLatestDataDao extends BaseDao<CollLatestData, String> {

	CollLatestData findByDtuDevId(String devId);
	
}
