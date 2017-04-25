package com.my.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.my.spring.pojo.User;

public class AdminInterceptor extends HandlerInterceptorAdapter{

	String errorPage;

	public String getErrorPage() {
		return errorPage;
	}

	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		User user = (User)session.getAttribute("user");
		String admin = (String)session.getAttribute("admin");
		if(admin!=null && user==null)
		{
			return true;
		}
		else
		{
			System.out.println("You are not admin. Please sign in as admin.");
			response.sendRedirect(request.getContextPath());	
			return false;
		}
		
		
		
	}
	
}
