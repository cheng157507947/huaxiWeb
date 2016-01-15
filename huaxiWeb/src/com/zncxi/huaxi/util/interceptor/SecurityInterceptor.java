package com.zncxi.huaxi.util.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SecurityInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(request.getSession().getAttribute("user") != null){
			return true;
		}else{
			response.setContentType("text/html; charset=UTF-8"); 
            PrintWriter out = response.getWriter(); 				            
            out.println("<script>");     
            out.println("window.parent.location='"+request.getContextPath()+"/'");
            out.println("</script>");
            return false;
		}
	}

}
