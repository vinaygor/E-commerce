package com.my.spring.controller;

import java.lang.invoke.MethodType;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.my.spring.dao.AdminDAO;
import com.my.spring.exception.AdminException;
import com.my.spring.pojo.Admin;
import com.my.spring.pojo.Category;
import com.my.spring.pojo.User;

@Controller
public class AdminController {
	
	@Autowired
	AdminDAO adminDao;

	static int i=0;
	@RequestMapping(value="admin/login.htm", method=RequestMethod.GET)
	public String loginPage() throws AdminException{
		if(i==0)
		{
		System.out.println("--------------Inserting admin details---------- ");
		//adminDao.insertData();
		}
		i++;
		return "admin-login";
	}
	
	@RequestMapping(value="admin/login.htm", method=RequestMethod.POST)
	public ModelAndView loginPage(HttpServletRequest request){
		HttpSession session = (HttpSession) request.getSession();
		try {
			
			System.out.print("Inside admin login post method");
			
			Admin admin= adminDao.verifyAdmin(request.getParameter("username"),request.getParameter("username"));
			if(admin == null){
				System.out.println("UserName/Password does not exist");
				session.setAttribute("errorMessage", true);
				
				return new ModelAndView ("admin-login","errorMessage",true);
			}
			
			List<User> users = adminDao.listOfUsers();
			System.out.println("Printing the size of users List: "+users.size());
			session.setAttribute("users", users);
			
			return new ModelAndView("admin-home", "users", users);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			return new ModelAndView("error","errorMessage",e.getMessage());
		}
	}
	
	@RequestMapping(value="admin/updateStatus.htm", method=RequestMethod.GET)
	public @ResponseBody String updateActiveStatus(HttpServletRequest request) throws AdminException{
	
		
		System.out.println("Inside the updateStatus function");
		int userId=Integer.parseInt(request.getParameter("id"));
		String result = adminDao.updateActiveStatus(userId);
		System.out.println("Printing the result :"+result);
		System.out.println(String.valueOf(userId));
		return String.valueOf(userId);
		
		//return new ModelAndView("admin-home","null",null);
//		else
//			return new ModelAndView("error","null",null);
	}
	
	@RequestMapping(value="admin/category/add.htm", method=RequestMethod.GET)
	public ModelAndView createNewCategory() throws Exception{
		return new ModelAndView("category-form","category",new Category());
	}
	
}
