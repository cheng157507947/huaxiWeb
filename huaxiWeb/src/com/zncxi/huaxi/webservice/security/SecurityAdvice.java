package com.zncxi.huaxi.webservice.security;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zncxi.huaxi.service.UserService;
import com.zncxi.huaxi.webservice.exception.WebExceptionFactory;


@Aspect
public class SecurityAdvice {

	static final Logger logger = LoggerFactory.getLogger(SecurityAdvice.class);
	
	@Autowired UserService userService;
	
	@Before("@annotation(com.caiot.xsas.webservice.security.Secure)")
	public void check(JoinPoint jp) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String code = request.getHeader("authCode");
		final Signature signature = jp.getSignature();
		logger.info("remote ip:" + request.getRemoteAddr()  + " access " + signature.getDeclaringTypeName() + "." + signature.getName() + ", code:" + code);
		if(code == null || "".equals(code) || !userService.isAuth(code)) {
			throw WebExceptionFactory.buildException(401, "Not Authorized", logger);
		} 
	}
}
