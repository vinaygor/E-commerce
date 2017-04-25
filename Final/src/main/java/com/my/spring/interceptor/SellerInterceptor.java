package com.my.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.my.spring.pojo.User;

public class SellerInterceptor extends HandlerInterceptorAdapter{

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
		if(admin!=null)
		{
			response.sendRedirect(request.getContextPath());	
			return false;
		}
		if(session.getAttribute("user") != null){
			System.out.println("Role :"+user.getRole());
			if(user.getRole().equalsIgnoreCase("Seller"))
			{
			System.out.println("in interceptor");
			return true;
			}
			else
			{
				System.out.println("The role is not Seller. Please sign in as Seller");
				System.out.println("Context Path is +"+request.getContextPath());
				response.addHeader("error","You are not logged in. Please Login in to your account.");
				response.sendRedirect(request.getContextPath());	
				return false;
			}
		}
		
		System.out.println("no user");
		System.out.println("Context Path is +"+request.getContextPath());
		response.sendRedirect(request.getContextPath());	
		return false;
		
		
		
		
	}
	
}
