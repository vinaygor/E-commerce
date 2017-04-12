package com.my.spring.controller;

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
		binder.setValidator(validator);
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
			
			return "user-home";

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			return "error";
		}

	}
	
	
	@RequestMapping(value = "/user/customer_register.htm", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.print("registerUser");

		return new ModelAndView("register-user", "user", new User());

	}
	
	@RequestMapping(value = "/user/customer_register.htm", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request,  @ModelAttribute("user") User user, BindingResult result) throws Exception {

		validator.validate(user, result);

		if (result.hasErrors()) {
			return new ModelAndView("register-user", "user", user);
		}

		try {

			System.out.print("registerNewUser");

			user.setRole("Customer");
			System.out.println("Address "+user.getAddress().getCity());
			userDao.register(user);
			
			
			
			return new ModelAndView("login", "user", null);

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

		validator.validate(user, result);

		if (result.hasErrors()) {
			return new ModelAndView("register-seller", "user", user);
		}

		try {

			System.out.print("registerNewSeller");

			user.setRole("Seller");
			System.out.println("Address "+user.getAddress().getCity());
			userDao.register(user);
			
			
			
			return new ModelAndView("login", "user", null);

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}


}