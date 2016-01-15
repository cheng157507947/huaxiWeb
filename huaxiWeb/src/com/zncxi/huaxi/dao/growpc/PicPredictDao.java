package com.zncxi.huaxi.dao.growpc;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zncxi.base.dao.BaseDao;
import com.zncxi.huaxi.domain.growpc.PicPredict;

public interface PicPredictDao extends BaseDao<PicPredict, String> {

	public List<PicPredict> findByIdIn(String[] ids);
	
	public Page<PicPredict> findByNeedPredict(Integer needPredict, Pageable pageable);
}
