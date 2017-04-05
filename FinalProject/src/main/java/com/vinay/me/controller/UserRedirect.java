package com.vinay.me.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vinay.me.validator.UserValidator;
import com.vinay.me.dao.UserDAO;
import com.vinay.me.exception.UserException;
import com.vinay.me.pojo.User;

/**
 * Handles requests for the application home page.
 */

@Controller
public class UserRedirect {
	
	
	@Autowired
    @Qualifier("userDao")
	UserDAO userDao;
	
	
	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	  
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	protected String goToUserHome(HttpServletRequest request) throws Exception {
		return "index";
	}
	
	@RequestMapping(value = "/user/customer_registration.htm", method = RequestMethod.GET)
	public ModelAndView CustomerRegistration() {
		
		return new ModelAndView("register_customer", "user", new User());
		
		
	}
	
	@RequestMapping(value = "/user/customer_registration.htm", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request,  @ModelAttribute("user") User user, BindingResult result) throws Exception {

		validator.validate(user, result);

		if (result.hasErrors()) {
			return new ModelAndView("register_customer", "user", user);
		}

		try {

			System.out.print("registerNewUser");

			User u = userDao.register(user);
			
			request.getSession().setAttribute("user", u);
			
			return new ModelAndView("login", "user", u);

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}
	
}
