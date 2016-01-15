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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zncxi.base.dao.BaseDao;
import com.zncxi.base.service.BaseService;
import com.zncxi.huaxi.dao.growpc.PicPredictDao;
import com.zncxi.huaxi.domain.growpc.PicPredict;
import com.zncxi.huaxi.domain.growpc.PicPredictInfo;

@Service
public class PicPredictService extends BaseService<PicPredict, String> {

	private Logger logger = LoggerFactory.getLogger(PicPredictService.class);
	
	@Autowired
	private PicPredictDao picPredictDao;
	@Autowired
	private PicPredictInfoService picPredictInfoService;
	
	@Override
	protected BaseDao<PicPredict, String> getEntityDao() {
		return picPredictDao;
	}
	
	/**
	 * 获得最新数据
	 * @return
	 */
	public PicPredict findNewOne(){
		Page<PicPredict> page = this.picPredictDao.findByNeedPredict(0, new PageRequest(0, 1, new Sort(Direction.DESC, "capTime")));
		if(page.getContent() == null || page.getContent().size() <= 0){
			return null;
		}
		return page.getContent().get(0);
	}
	
	public List<PicPredict> findByIdIn(String[] ids){
		return this.picPredictDao.findByIdIn(ids);
	}
	
	public Page<PicPredict> findAllLikeByDateBetween(Pageable pageable, String beginTime, String endTime, Integer needPredict){
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
			return this.findAllLikeByDateBetween(pageable, time1, time2, needPredict);
		} catch (Exception e) {
			logger.error("根据时间查询预测图片列表出现未知异常",e);
		}
		return null;
	}

	@Transactional(readOnly = true)
	public Page<PicPredict> findAllLikeByDateBetween(Pageable pageable, final Date beginTime, final Date endTime, final Integer needPredict){
		return this.picPredictDao.findAll(new Specification<PicPredict>() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Predicate toPredicate(Root<PicPredict> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if (beginTime != null && endTime != null) {
					Path expression = root.get("capTime");
					predicates.add(cb.between(expression, beginTime, endTime));
				}
				if(needPredict != null){
					Path expression = root.get("needPredict");
					predicates.add(cb.equal(expression, needPredict));
				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
	}
	
	public void save(String fullPath, String singlePath){
		PicPredict picPredict = new PicPredict();
		picPredict.setFullPath(fullPath);
		picPredict.setSinglePath(singlePath);
		picPredict.setCapTime(new Date());
		
		PicPredictInfo picPredictInfo = this.picPredictInfoService.findAll().get(0);
		picPredict.setAngle(picPredictInfo.getAngle());  //图片拍摄角度
		picPredict.setArea(picPredictInfo.getArea());  //图片拍摄面积
		picPredict.setPlantTime(picPredictInfo.getPlantTime());  //水稻种植时间
		picPredict.setTotalArea(picPredictInfo.getTotalArea());  //总亩数
		picPredict.setNeedPredict(1);  //需要预测
		
		this.save(picPredict);
	}
}
