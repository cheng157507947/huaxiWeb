package com.zncxi.huaxi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zncxi.base.dao.BaseDao;
import com.zncxi.base.service.BaseService;
import com.zncxi.huaxi.dao.CollHisDataDao;
import com.zncxi.huaxi.domain.CollHisData;

@Service
public class CollHisDataService extends BaseService<CollHisData, String>{
	
	@Autowired
	private CollHisDataDao collHisDataDao;
	
	@Override
	protected BaseDao<CollHisData, String> getEntityDao() {
		return this.collHisDataDao;
	}
	
	public List<CollHisData> findByDtuDevId(String dtuDevId){
		return this.collHisDataDao.findByDtuDevId(dtuDevId);
	}
	
	public List<CollHisData> findByDtuDevIdAndCollTimeBetween(String dtuDevId, Date begin, Date end){
		return this.collHisDataDao.findByDtuDevIdAndCollTimeBetweenOrderByCollTimeDesc(dtuDevId, begin, end);
	}
	
	public Page<CollHisData> findByDtuDevIdAndCollTimeBetween(String dtuDevId, Date begin, Date end, Pageable pageable){
		return this.collHisDataDao.findByDtuDevIdAndCollTimeBetween(dtuDevId, begin, end, pageable);
	}
	
	public Long findCountByDtuDevIdAndCollTimeBetween(String dtuDevId, Date begin, Date end){
		return this.collHisDataDao.findCountByDtuDevIdAndCollTimeBetween(dtuDevId, begin, end);
	}
}
