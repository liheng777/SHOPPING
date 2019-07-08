package com.hqyj.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hqyj.bean.User;
import com.hqyj.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserAction {
	@Autowired
	private UserService userService;
	private String name;
	
	@RequestMapping(value = "meauType")
	public String list(Integer flag,Model model,HttpServletResponse response) {
		
		Cookie cookie = new Cookie("meauType",flag+"");
		cookie.setPath("/");
		response.addCookie(cookie);
		return "redirect:/user/userList.do";
	}
	@RequestMapping("/userList")
	public String userList(Model model){
		ArrayList<User> list = userService.findAll();
		model.addAttribute("list", list);
		return "userList";
	}
	@RequestMapping("/preAdd")
	public String preAdd(){
		return "userAdd";
	}
	@RequestMapping("/addUser")
	public String add(User u,Model model){
		boolean result = userService.add(u);
		if(result){
			return "redirect:/user/userList.do";
		}else{
			model.addAttribute("msg","用户名存在");
			return "userAdd";
		}
	}
	@RequestMapping(value="/preUpdate.do")
	public ModelAndView preUpdate(Integer id){
		User user = userService.findUserById(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		name=user.getUsername();
		mv.setViewName("userEidt");
		return mv;
	}

	@RequestMapping(value="/updateUser.do")
	public String updateUser(User user,Model model){
		boolean result = userService.edit(user);
		if(result){
			if(name.equals(user.getUsername())){
				return "redirect:/user/userList.do";
			}else{
				model.addAttribute("msg","用户名不允许修改");
				return "userEidt";
			}
		}else{
			model.addAttribute("msg","用户名不允许修改");
			return "userEidt";
		}
	}
	@RequestMapping("/delete.do")
	public String delete(Integer id){
		userService.delete(id);
		return "redirect:/user/userList.do";
	}
	@RequestMapping("/queryUserByCondition.do")
	public String queryUserByCondition(User user,Model model) throws UnsupportedEncodingException{
		user.setUsername(new String(user.getUsername().getBytes("ISO-8859-1"),"UTF-8"));
		List<User> list=userService.queryUserByCondition(user);
		model.addAttribute("list", list);
		return "userList";
	}
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
