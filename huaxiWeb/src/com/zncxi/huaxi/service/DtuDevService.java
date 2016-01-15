package com.zncxi.huaxi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zncxi.base.dao.BaseDao;
import com.zncxi.base.service.BaseService;
import com.zncxi.huaxi.dao.DtuDevDao;
import com.zncxi.huaxi.dao.DtuStsDao;
import com.zncxi.huaxi.domain.DtuDev;
import com.zncxi.huaxi.domain.DtuSts;

@Service
public class DtuDevService extends BaseService<DtuDev, String> {

	@Autowired
	private DtuDevDao dtuDevDao;

	@Autowired
	private DtuStsDao dtuStsDao;

	@Override
	protected BaseDao<DtuDev, String> getEntityDao() {
		return this.dtuDevDao;
	}

	@Override
	@Transactional
	public void save(DtuDev dtuDev) {
		dtuDev = dtuDevDao.save(dtuDev);
		DtuSts dtuSts = dtuStsDao.findOne(dtuDev.getId());
		if (dtuSts == null) {
			dtuSts = new DtuSts();
			dtuSts.setId(dtuDev.getId());
			dtuSts.setOnline(0);
			dtuSts.setType(dtuDev.getType());
			dtuSts.setSenSts(2047);
			dtuSts.setDefSts(1);
			dtuStsDao.save(dtuSts);
		}
	}
	
	@Override
	@Transactional
	public void delete(String[] ids) {	
		for(String id : ids){
			delete(id);
		}
	}

	@Override
	@Transactional
	public void delete(String id) {
		dtuStsDao.delete(id);
		dtuDevDao.delete(id);
	}
	
	@Override
	@Transactional
	public void delete(DtuDev dtuDev) {
		dtuStsDao.delete(dtuDev.getId());
		dtuDevDao.delete(dtuDev);
	}

//	public DtuDev findDtuDevBySceneId(String sceneId) {
//		return this.dtuDevDao.findDtuDevBySceneId(sceneId);
//	}
//
//	public List<DtuDev> findDtuDevBySceneType(Integer type) {
//		return this.dtuDevDao.findDtuDevBySceneType(type);
//	}
}
