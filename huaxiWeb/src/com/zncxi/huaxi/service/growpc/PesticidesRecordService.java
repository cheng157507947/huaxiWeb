package com.zncxi.huaxi.service.growpc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zncxi.base.dao.BaseDao;
import com.zncxi.base.service.BaseService;
import com.zncxi.huaxi.dao.growpc.PesticidesRecordDao;
import com.zncxi.huaxi.domain.growpc.PesticidesRecord;

@Service
public class PesticidesRecordService extends BaseService<PesticidesRecord, String> {
	
	private Logger logger = LoggerFactory.getLogger(PesticidesRecordService.class);

	@Autowired
	private PesticidesRecordDao pesticidesRecordDao;
	
	@Override
	protected BaseDao<PesticidesRecord, String> getEntityDao() {
		return pesticidesRecordDao;
	}
	
	public Page<PesticidesRecord> findAllLikeByNameAndDateBetween(Pageable pageable, String beginTime, String endTime, String name){
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date time1 = format.parse("2015-01-01 00:00:00");
			Date time2 = new Date();
			if(StringUtils.isNotEmpty(beginTime)){
				time1 = format.parse(beginTime+" 00:00:00");
			}
			if(StringUtils.isNotEmpty(endTime)){
				time2 = format.parse(endTime+" 23:59:59");
			}
			return this.findAllLikeByNameAndDateBetween(pageable, time1, time2, name);
		} catch (Exception e) {
			logger.error("根据时间查询农药列表出现未知异常",e);
		}
		return null;
	}

	@Transactional(readOnly = true)
	public Page<PesticidesRecord> findAllLikeByNameAndDateBetween(Pageable pageable, final Date beginTime, final Date endTime, final String name){
		return this.pesticidesRecordDao.findAll(new Specification<PesticidesRecord>() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Predicate toPredicate(Root<PesticidesRecord> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if (beginTime != null && endTime != null) {
					Path expression = root.get("date");
					predicates.add(cb.between(expression, beginTime, endTime));
				}
				if(name != null){
					Path expression = root.get("name");
					predicates.add(cb.equal(expression, name));
				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
	}
}
