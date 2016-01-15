package com.zncxi.huaxi.dao;

import com.zncxi.base.dao.BaseDao;
import com.zncxi.huaxi.domain.User;

public interface UserDao extends BaseDao<User, String> {

	User findByUsername(String username);
	
	User findByUsernameAndPassword(String username, String password);
}
