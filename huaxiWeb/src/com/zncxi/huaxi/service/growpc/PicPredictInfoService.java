package com.zncxi.huaxi.service.growpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zncxi.base.dao.BaseDao;
import com.zncxi.base.service.BaseService;
import com.zncxi.huaxi.dao.growpc.PicPredictInfoDao;
import com.zncxi.huaxi.domain.growpc.PicPredictInfo;

@Service
public class PicPredictInfoService extends BaseService<PicPredictInfo, String> {

	@Autowired
	private PicPredictInfoDao picPredictInfoDao;
	
	@Override
	protected BaseDao<PicPredictInfo, String> getEntityDao() {
		return picPredictInfoDao;
	}

}
