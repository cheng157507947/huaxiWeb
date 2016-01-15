package com.zncxi.huaxi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zncxi.base.dao.BaseDao;
import com.zncxi.base.service.BaseService;
import com.zncxi.huaxi.dao.VideoDevDao;
import com.zncxi.huaxi.domain.VideoDev;

@Service
public class VideoDevService extends BaseService<VideoDev, String>{
	
	@Autowired
	private VideoDevDao videoDevDao;
	
	@Override
	protected BaseDao<VideoDev, String> getEntityDao() {
		return this.videoDevDao;
	}
	
	public List<VideoDev> findVideoBySceneId(String sceneId){
		return this.videoDevDao.findVideoBySceneId(sceneId);
	}
}
