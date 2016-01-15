package com.zncxi.huaxi.util.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zncxi.huaxi.domain.AlarmConfig;
import com.zncxi.huaxi.domain.User;
import com.zncxi.huaxi.service.AlarmConfigService;
import com.zncxi.huaxi.service.UserService;

public class IniDbManage {
	
	private final static Logger logger = LoggerFactory.getLogger(IniDbManage.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private AlarmConfigService alarmConfigService;
	

	public void iniDB() {
		iniUser();
		iniAlarmConfig();
		logger.info("初始化数据库配置...");
	}
	
	private void iniUser(){
		if(userService.findAll().size() == 0){
			User user = new User();
			user.setUsername("admin");
			user.setPassword("1");
			user.setRealname("管理员");
			user.setType(1);
			this.userService.save(user);
			logger.info("初始化管理员...");
		}
	}
	
	private void iniAlarmConfig(){
		if(alarmConfigService.findAll().size() == 0){
			AlarmConfig alarmConfig = new AlarmConfig();
			this.alarmConfigService.save(alarmConfig);
			logger.info("初始化报警配置...");
		}
	}
	
}
