package com.zncxi.huaxi.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zncxi.base.dao.BaseDao;
import com.zncxi.base.service.BaseService;
import com.zncxi.huaxi.dao.SceneDao;
import com.zncxi.huaxi.domain.Scene;
import com.zncxi.huaxi.util.enumeration.SceneGroup;

@Service
public class SceneService extends BaseService<Scene, String>{
	
	@Autowired
	private SceneDao sceneDao;
	
	@Override
	protected BaseDao<Scene, String> getEntityDao() {
		return this.sceneDao;
	}
	
	public List<Scene> findSceneByGroup(SceneGroup sceneGroup){
		return this.sceneDao.findByGroupOrderByNameAsc(sceneGroup);
	}
	
	public List<Scene> findSceneByType(Integer type){
		return this.sceneDao.findSceneByType(type);
	}
	
	/**
	 * 若id为null，则查询所有分组下的田块信息，否则查询ID
	 * @param id
	 * @param sceneGroup
	 * @return
	 */
	public List<Scene> findSceneByIdORGroupType(String id, SceneGroup sceneGroup){
		if(StringUtils.isEmpty(id)){
			return this.sceneDao.findByGroupAndType(sceneGroup,1);
		}
		List<Scene> scenes = new ArrayList<Scene>();
		scenes.add(this.findById(id));
		return scenes;
	}
}
