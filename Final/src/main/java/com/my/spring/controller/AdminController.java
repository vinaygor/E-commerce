package com.my.spring.controller;

import java.lang.invoke.MethodType;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.my.spring.dao.AdminDAO;
import com.my.spring.dao.CategoryDAO;
import com.my.spring.dao.OrderDAO;
import com.my.spring.exception.AdminException;
import com.my.spring.exception.CategoryException;
import com.my.spring.pojo.Admin;
import com.my.spring.pojo.Category;
import com.my.spring.pojo.Order;
import com.my.spring.pojo.User;
import com.my.spring.validator.CategoryValidator;

@Controller
public class AdminController {
	
	@Autowired
	AdminDAO adminDao;
	
	@Autowired
	@Qualifier("categoryValidator")
	CategoryValidator categoryValidator;
	
	@Autowired
	@Qualifier("categoryDao")
	CategoryDAO categoryDAO;
	
	@Autowired
	OrderDAO orderDao;

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
			session.setAttribute("admin", "admin");
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
	
	@RequestMapping(value="/admin/view-seller.htm", method=RequestMethod.GET)
	public String viewSellersPage() throws AdminException{
		
		return "admin-view-sellers";
	}
	
	@RequestMapping(value="admin/category/deleteCategory.htm", method=RequestMethod.GET)
	public @ResponseBody String deleteCategory(HttpServletRequest request) throws AdminException, CategoryException{
	
		
		System.out.println("Inside the DeleteCategory function");
		String title=request.getParameter("title");
		categoryDAO.delete(categoryDAO.get(title));
		System.out.println("Deleted the category :");
		//System.out.println(String.valueOf(userId));
		return title;
		
		//return new ModelAndView("admin-home","null",null);
//		else
//			return new ModelAndView("error","null",null);
	}
	
	@RequestMapping(value="/admin/category/delete.htm", method=RequestMethod.GET)
	public ModelAndView deleteCategory() throws Exception{
		System.out.println("Inside GET method Admin Controller / delete category");
		return new ModelAndView("delete-category","category",categoryDAO.list());
	}
	

	
	@RequestMapping(value="/admin/category/add.htm", method=RequestMethod.GET)
	public ModelAndView createNewCategory() throws Exception{
		System.out.println("Inside GET method Admin Controller / Categoories");
		return new ModelAndView("category-form","category",new Category());
	}
	
	@RequestMapping(value="/admin/category/add.htm", method=RequestMethod.POST)
	public ModelAndView addCategory(@ModelAttribute("category") Category category, BindingResult result) throws Exception {
		categoryValidator.validate(category, result);
		
		if (result.hasErrors()) {
			return new ModelAndView("category-form", "category", category);
		}

		try {			
			//System.out.println("Inside Admin Controller / Category");
			Category checkCat = categoryDAO.get(category.getTitle());
			if(checkCat==null)
 			category = categoryDAO.create(category.getTitle());
			else
				return new ModelAndView("error", "errorMessage", "Category Name already present. Try a different name"); 
		} catch (CategoryException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("category-form", "errorMessage", "Could not create a new Category");
		}
		return new ModelAndView("category-form", "message", "New Category "+category.getTitle()+" has been created!");
		
	}

	@RequestMapping(value="/admin/dashboard.htm", method=RequestMethod.GET)
	public String dashBoard() throws AdminException{
		
		return "admin-home";
	}
	
	@RequestMapping(value="/logout.htm", method = RequestMethod.GET)
	public String goToLoginPage(HttpServletRequest req){
		HttpSession session = req.getSession();
		session.invalidate();
		return "login";
	}
	
	@RequestMapping(value="/logout.htm", method = RequestMethod.POST)
	public String goToLogin(HttpServletRequest req){
		HttpSession session = req.getSession();
		session.invalidate();
		return "login";
	}
	
	@RequestMapping(value="/seller/vieworders.htm", method=RequestMethod.GET)
	public ModelAndView getOrderForSeller(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		User user=(User)session.getAttribute("user");
		String sellerName=user.getName();
		List<Order> list = orderDao.sellerOrderList(sellerName);
		System.out.println("Size of list for user :"+sellerName+" is - "+list.size());
		return new ModelAndView("view-order-sellers","list",list);
	}
	

	
}
	
