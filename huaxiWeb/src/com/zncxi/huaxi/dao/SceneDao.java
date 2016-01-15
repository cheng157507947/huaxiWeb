package com.zncxi.huaxi.dao;

import java.util.List;

import com.zncxi.base.dao.BaseDao;
import com.zncxi.huaxi.domain.Scene;
import com.zncxi.huaxi.util.enumeration.SceneGroup;

public interface SceneDao extends BaseDao<Scene, String> {
	
	List<Scene> findByGroupOrderByNameAsc(SceneGroup sceneGroup);
	
	List<Scene> findByGroupAndType(SceneGroup sceneGroup, Integer type);
	
	List<Scene> findSceneByType(Integer type);
}
