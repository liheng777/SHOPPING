package com.hqyj.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hqyj.bean.User;
import com.hqyj.service.LoginService;

@Controller
@RequestMapping(value="/login")
public class LoginAction {
	@Autowired
	private LoginService loginService;
	@RequestMapping("/login.do")
	public String login(HttpServletResponse response,User user,Model model) throws UnsupportedEncodingException{
		int result = loginService.login(user);
		if(result==1){
			String name = URLEncoder.encode(user.getUsername(),"utf-8");
			
			Cookie cookie = new Cookie("loginName",name);
			cookie.setPath("/");
			Cookie cookie1 = new Cookie("userType",user.getUsertype()+"");
			cookie1.setPath("/");
			response.addCookie(cookie);
			response.addCookie(cookie1);
			model.addAttribute("msg","欢迎光临");
			return "redirect:/login/userBefore.do";
		}else{
			if(result==0){
				model.addAttribute("msg","密码错误");
			}else if(result==-1){
				model.addAttribute("msg","用户名不存在");
			}
			return "login";
		}
	}
	@RequestMapping("/userBefore")
	public String userBefore(){
		return "userBefore";
	}
	@RequestMapping("/logout.do")
	public String loginOut(HttpServletResponse response){
		Cookie c=new Cookie("loginName","");
		c.setPath("/");
		c.setMaxAge(0);
		Cookie cookie1 = new Cookie("userType","-1");
		cookie1.setPath("/");
		cookie1.setMaxAge(0);
		response.addCookie(c);
		response.addCookie(cookie1);
		return "login";
	}
	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
}
