package com.zncxi.huaxi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zncxi.base.dao.BaseDao;
import com.zncxi.base.service.BaseService;
import com.zncxi.base.util.DateUtils;
import com.zncxi.huaxi.dao.FaultInfoDao;
import com.zncxi.huaxi.domain.FaultInfo;

@Service
public class FaultInfoService extends BaseService<FaultInfo, String>{
	
	@Autowired
	private FaultInfoDao faultInfoDao;
	
	@Override
	protected BaseDao<FaultInfo, String> getEntityDao() {
		return this.faultInfoDao;
	}
	
	public Page<FaultInfo> findPageByQuery(Pageable pageable, final String dtuId, final Date beginDate, final Date endDate) {
		Specification<FaultInfo> specification = new Specification<FaultInfo>() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public Predicate toPredicate(Root<FaultInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(StringUtils.hasText(dtuId)) {
					Path expression = root.get("dtuDevId");
					predicates.add(cb.equal(expression, dtuId));
				}
				if(!StringUtils.isEmpty(beginDate)) {
					Path expression = root.get("occurTime");
					predicates.add(cb.greaterThanOrEqualTo(expression, DateUtils.dateToDayBegin(beginDate)));
				}
				if(!StringUtils.isEmpty(endDate)) {
					Path expression = root.get("occurTime");
					predicates.add(cb.lessThanOrEqualTo(expression, DateUtils.dateToDayEnd(endDate)));
				}
				query.orderBy(cb.desc(root.get("occurTime")));
				if (predicates.size() > 0) {
					return cb.and(predicates.toArray(new Predicate[predicates.size()]));
				}
				return cb.conjunction();
			}
		};
		return getEntityDao().findAll(specification, pageable);
	}
	
}
