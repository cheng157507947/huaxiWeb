package com.zncxi.huaxi.web.faultDiag;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zncxi.huaxi.domain.User;
import com.zncxi.huaxi.service.UserService;

/**
 * 用户管理
 * @author xiaoCheng
 *
 */
@Controller
@RequestMapping(value = "/admin/faultDiag/user")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/list")
	public String list(Model model, HttpSession session, String queryName, String queryValue, Pageable pageable)
	{
		User user = (User)session.getAttribute("user");
		String id = null;
		if(user.getType() == 0){
			id = user.getId();
		}
		Page<User> userPage = this.userService.findAllLikeByUser(pageable, queryName, queryValue, id);
		
		model.addAttribute("page", userPage);
		model.addAttribute("queryName", queryName);
		model.addAttribute("queryValue", queryValue);
		return "/faultDiag/user/list";
	}
	
	@RequestMapping(value = "/add")
	public String add()
	{
		return "/faultDiag/user/add";
	}
	@RequestMapping(value = "/edit/{id}")
	public String edit(Model model,@PathVariable("id")String id)
	{
		model.addAttribute("user",userService.findById(id));
		return "/faultDiag/user/edit";
	}
	
	/**
	 * 保存
	 * @param user
	 * @param newPwd
	 * @param session
	 * @param ra
	 * @return
	 */
	@RequestMapping(value = "/save")
	public String save(User user, String newPwd, RedirectAttributes ra){
		try {
			if(user.getId() != null){  //修改
				User oldUser = this.userService.findById(user.getId());
				if(user.getPassword() != null && user.getPassword().length() > 0){
					if(!oldUser.getPassword().equals(user.getPassword())){
						ra.addFlashAttribute("messageErr", "用户信息修改失败，密码不正确");
						return "redirect:/admin/faultDiag/user/list";
					}
					user.setPassword(newPwd);
				}else{
					user.setPassword(oldUser.getPassword());
				}
			}
			this.userService.save(user);
			ra.addFlashAttribute("messageOK", "用户保存成功");
		} catch (Exception e) {
			ra.addFlashAttribute("messageErr", "用户保存异常");
			logger.error("保存用户异常", e);
		}
		return "redirect:/admin/faultDiag/user/list";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(String[] idGroup, RedirectAttributes ra){
		try {
			userService.delete(idGroup);
			ra.addFlashAttribute("messageOK", "用户删除成功");
		} catch (Exception e) {
			ra.addFlashAttribute("messageErr", "用户删除异常");
			logger.error("保存用户异常", e); 
		}
		return "redirect:/admin/faultDiag/user/list";
	}
	
	@RequestMapping(value = "/validateLoginName")
	@ResponseBody
	public String validateLoginName(String userName){
		boolean b = userService.validateUserName(userName);
		return String.valueOf(b);
	}
}
