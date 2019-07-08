package com.hqyj.interceptor;

import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		if(arg0.getRequestURI().indexOf("login")>=0){
			return true;
		}
		String value="";
		Cookie[] arr = arg0.getCookies();
		if(arr!=null){
				for (Cookie c : arr) {
					if("loginName".equals(c.getName())){
						 value = URLDecoder.decode(c.getValue(), "utf-8");
					}
				}
		}
		if(!"".equals(value)){
			return true;
		}else{
			arg0.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(arg0, arg1);
			return false;
		}
	}

}
