package com.zncxi.huaxi.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zncxi.huaxi.domain.User;
import com.zncxi.huaxi.service.UserService;

/**
 * 登陆管理
 * @author xiaoCheng
 *
 */
@Controller
@RequestMapping(value = "/")
public class LoginController {

//	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired 
	private UserService userService;
	
	@RequestMapping(value = "/toLogin")
	public String toLogin(String userName, String pwd, String code, HttpSession session, RedirectAttributes ra) {
		User user = userService.findByUserName(userName);
		if (user == null || !user.getPassword().equals(pwd)) {
 			ra.addFlashAttribute("message", "账号或密码错误！");
			ra.addFlashAttribute("userName", userName);
			ra.addFlashAttribute("pwd", pwd);
			return "redirect:/";
		}
		session.setAttribute("user", user);
		return "redirect:/admin/nav";
	}
	
	@RequestMapping(value = "/admin/nav")
	public String nav(){
		return "main/nav";
	}
	
	@RequestMapping(value = "/exit")
	public String exit(HttpServletRequest request){
		request.getSession().invalidate();
		return "redirect:/";
	}
}
