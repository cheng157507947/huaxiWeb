package com.zncxi.huaxi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zncxi.base.dao.BaseDao;
import com.zncxi.base.service.BaseService;
import com.zncxi.huaxi.dao.CollLatestDataDao;
import com.zncxi.huaxi.domain.CollLatestData;
import com.zncxi.huaxi.domain.Scene;
import com.zncxi.huaxi.util.vo.CollLatestDataVO;

@Service
public class CollLatestDataService extends BaseService<CollLatestData, String>{
	
	@Autowired
	private CollLatestDataDao collLatestDataDao;
	@Autowired
	private SceneService sceneService;
	
	@Override
	protected BaseDao<CollLatestData, String> getEntityDao() {
		return this.collLatestDataDao;
	}
	
	public CollLatestData findByDtuDevId(String devId){
		return this.collLatestDataDao.findByDtuDevId(devId);
	}
	
	public List<CollLatestDataVO> findCollLatesDataVOByDtuDevSceneType(Integer type){
		List<CollLatestDataVO> datas = new ArrayList<CollLatestDataVO>();
		
		List<Scene> scenes = this.sceneService.findSceneByType(type);
		for(Scene scene: scenes){
			if(scene.getDtuDev() != null){
				CollLatestData latestData = this.collLatestDataDao.findByDtuDevId(scene.getDtuDev().getId());
				if(latestData != null){
					CollLatestDataVO vo = new CollLatestDataVO();
					vo.setCollLatestData(latestData);
					vo.setScene(scene);
					datas.add(vo);
				}
			}
		}
		return datas;
	}
}
