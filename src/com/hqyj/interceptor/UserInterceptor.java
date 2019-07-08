package com.hqyj.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserInterceptor implements HandlerInterceptor{

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
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		if(request.getRequestURI().indexOf("login")>=0){
			return true;
		}
		Cookie[] cookie = request.getCookies();
		String usertype = "";
		if (cookie != null) {
			for (Cookie c : cookie) {
				if ("userType".equals(c.getName())) {
					usertype = c.getValue();
				}
			}
		}
//		if (!"0".equals(usertype)&&request.getRequestURI().indexOf("user")>=0){
//			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
//			return false;
//		}
//		else if ("1".equals(usertype)&&(request.getRequestURI().indexOf("stockDrug")>=0)) {
//				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
//				return false;
//		} else if ("2".equals(usertype)&&(request.getRequestURI().indexOf("saleDrug")>=0)) {
//				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
//				return false;
//		} else {
//			return true;
//		}
		if ("2".equals(usertype)) {
			if ((request.getRequestURI().indexOf("stockDrug")>=0) || (request.getRequestURI().indexOf("wareHouse")>=0)) {
				return true;
			} else {
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
				return false;
			}
		} else if ("1".equals(usertype)) {
			if ((request.getRequestURI().indexOf("wareHouse")>=0) || (request.getRequestURI().indexOf("saleDrug")>=0)) {
				return true;
			} else {
				request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
				return false;
			}
		} else {
			return true;
		}
	}
}
