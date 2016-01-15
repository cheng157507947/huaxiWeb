package com.zncxi.huaxi.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.zncxi.base.dao.BaseDao;
import com.zncxi.huaxi.domain.CollHisData;

public interface CollHisDataDao extends BaseDao<CollHisData, String> {

	List<CollHisData> findByDtuDevId(String dtuDevId);
	
	List<CollHisData> findByDtuDevIdAndCollTimeBetweenOrderByCollTimeDesc(String dtuDevId, Date begin, Date end);
	
	Page<CollHisData> findByDtuDevIdAndCollTimeBetween(String dtuDevId, Date begin, Date end, Pageable pageable);
	
	@Query("select count(id) from CollHisData c where c.dtuDev.id=?1 and c.collTime between ?2 and ?3")
	Long findCountByDtuDevIdAndCollTimeBetween(String dtuDevId, Date begin, Date end);
}
