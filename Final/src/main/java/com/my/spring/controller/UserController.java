package com.my.spring.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.my.spring.dao.UserDAO;
import com.my.spring.exception.UserException;
import com.my.spring.pojo.Address;
import com.my.spring.pojo.User;
import com.my.spring.validator.UserValidator;

@Controller
@RequestMapping("/user/*")
public class UserController {

	@Autowired
    @Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		System.out.println("Inside initBinder");
		binder.setValidator(validator);
		System.out.println("Leaving initBinder");
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	protected String goToUserHome(HttpServletRequest request) throws Exception {
		return "user-home";
	}
	
	@RequestMapping(value = "/user/login.htm", method = RequestMethod.GET)
	protected String loginUser(HttpServletRequest request) throws Exception{
		return "login";
	}
	
	@RequestMapping(value = "/user/login.htm", method = RequestMethod.POST)
	protected String loginUserSuccess(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		
		try {
			
			System.out.print("loginUser");
			

			User u = userDao.get(request.getParameter("username"), request.getParameter("password"));
			
			if(u == null){
				System.out.println("UserName/Password does not exist");
				session.setAttribute("errorMessage", "UserName/Password does not exist");
				return "error";
			}
			
			session.setAttribute("user", u);
			if(u.getRole().equalsIgnoreCase("Customer"))
			return "user-home";
			else
			return "seller-home";

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			return "error";
		}

	}
	
	
	@RequestMapping(value = "/user/customer_register.htm", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.print("registerUser");

		User u= new User();
		u.setAddress(new ArrayList<Address>());
		return new ModelAndView("register-user", "user", u);

	}
	
	@RequestMapping(value = "/user/customer_register.htm", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request,  @ModelAttribute("user") User user, BindingResult result) throws Exception {

		System.out.println("Inside POST method");
//		validator.validate(user, result);
//
//		if (result.hasErrors()) {
//			return new ModelAndView("register-user", "user", user);
//		}

		try {
				int userPresent = userDao.get(user.getUsername());
				System.out.println("Checking if the user exists or not :"+userPresent);
			if(userPresent==0)
			{
				System.out.println("Controller /user/customer_register.htm - registerNewUser");
				System.out.println("User name >>> "+user.getName());
	
				user.setRole("Customer");
				//System.out.println("Address "+user.getAddress().getCity());
				userDao.register(user);
				
				
				
				return new ModelAndView("login", "user", null);
				
			}
			else
			{
				System.out.println("Inside else part");
				return new ModelAndView("register-user", "flag",true);
			}
			
		

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}
	
	@RequestMapping(value = "/user/seller_registration.htm", method = RequestMethod.GET)
	protected ModelAndView registerSeller() throws Exception {
		System.out.print("registerSeller");

		return new ModelAndView("register-seller", "user", new User());

	}
	
	@RequestMapping(value = "/user/seller_register.htm", method = RequestMethod.POST)
	protected ModelAndView registerNewSeller(HttpServletRequest request,  @ModelAttribute("user") User user, BindingResult result) throws Exception {
System.out.println("I'm inside");
		//validator.validate(user, result);

//		if (result.hasErrors()) {
//			return new ModelAndView("register-seller", "user", user);
//		}

		try {
			
			int userPresent = userDao.get(user.getUsername());
			int emailPresent = userDao.verifyUniqueEmail(user.getEmail().getEmailAddress());
			System.out.println("Checking if the user exists or not :"+userPresent);
			System.out.println("Checking if the email exists or not :"+emailPresent);
		if(userPresent==0 && emailPresent==0)
		{

			System.out.print("registerNewSeller");

			user.setRole("Seller");
			//System.out.println("Address "+user.getAddress().getCity());
			userDao.register(user);
			
			
			
			return new ModelAndView("login", "user", null);
		}
		else
		{
			if(userPresent==1&&emailPresent==0)
			{
				System.out.println("Inside else part");
				return new ModelAndView("register-seller", "flag",true);
			}
			else if(userPresent==0&&emailPresent==1)
			{
				return new ModelAndView("register-seller", "emailflag",true);
			}
			else
			{
				return new ModelAndView("register-seller", "both",true);
			}
		}
		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}


}