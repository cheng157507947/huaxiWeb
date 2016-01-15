package com.zncxi.huaxi.dao;

import java.util.List;

import com.zncxi.base.dao.BaseDao;
import com.zncxi.huaxi.domain.ControlDev;

public interface ControlDevDao extends BaseDao<ControlDev, String> {

	List<ControlDev> findControlDevByDtuDevIdOrderByOrderNoAsc(String dtuDevId);
	
	List<ControlDev> findControlDevByDtuDevIdAndWlgIndexOrderByOrderNoAsc(String dtuDevId, Integer wlgIndex);
}
