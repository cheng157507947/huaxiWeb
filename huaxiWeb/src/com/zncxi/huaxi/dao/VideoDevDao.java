package com.zncxi.huaxi.dao;

import java.util.List;

import com.zncxi.base.dao.BaseDao;
import com.zncxi.huaxi.domain.VideoDev;

public interface VideoDevDao extends BaseDao<VideoDev, String> {

	List<VideoDev> findVideoBySceneId(String sceneId);
}
