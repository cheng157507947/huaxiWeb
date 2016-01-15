package com.zncxi.huaxi.webservice.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.NotFoundException;
import com.zncxi.huaxi.domain.User;
import com.zncxi.huaxi.service.UserService;
import com.zncxi.huaxi.webservice.dto.UserDTO;
import com.zncxi.huaxi.webservice.exception.WebExceptionFactory;

/**
 * 登陆接口
 * @author xiaoCheng
 *
 */
@Component
@Path("/auth")
@Produces({ MediaType.APPLICATION_XML + ";charset=UTF-8", MediaType.APPLICATION_JSON + ";charset=UTF-8" })
public class UserResouceService {
	
	private static Logger logger = LoggerFactory.getLogger(UserResouceService.class);

	@Autowired
	private UserService userService;
	
	
	@GET
	public UserDTO getAuth(@QueryParam("username")String username, @QueryParam("password")String password){
		try {
			User userByname = userService.findByUserName(username);
			User user = userService.findByUsernameAndPassword(username, password);
			if(user != null){
				UserDTO dto = new UserDTO();
				dto.authCode = user.getId();
				dto.userRealname = user.getRealname();
				dto.userPhone = user.getPhone();
				return dto;
			}else if(userByname != null){
				UserDTO dto = new UserDTO();
				dto.authCode = "0";
				return dto;
			}else{
				logger.warn("username{},password:{} - has the null user or company.", username, password);
				throw new NotFoundException("code is not found");
			}
		} catch (RuntimeException e) {
			throw WebExceptionFactory.buildDefaultException(e, logger);
		}
	}
	
}
