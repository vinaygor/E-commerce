package com.my.spring.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.my.spring.dao.CategoryDAO;
import com.my.spring.dao.ProductDAO;
import com.my.spring.dao.UserDAO;
import com.my.spring.exception.CategoryException;
import com.my.spring.pojo.Category;
import com.my.spring.pojo.Product;
import com.my.spring.pojo.User;
import com.my.spring.validator.UserValidator;


@Controller
public class FileUploadController {

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

//	@Autowired
//	@Qualifier("userValidator")
//	UserValidator validator;
	
	@Autowired
	@Qualifier("categoryDao")
	CategoryDAO categoryDao;
	
	@Autowired
	ProductDAO productDao;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		//binder.setValidator(validator);
	}

	@Autowired
	ServletContext servletContext;

	@RequestMapping(value = "/seller/product/add.htm", method = RequestMethod.GET)
	public ModelAndView showForm(ModelMap model,HttpServletRequest request) throws Exception {
		HttpSession session=request.getSession();
		User u=(User)session.getAttribute("user");
		System.out.println("PersoniD - "+u.getPersonID());
		ModelAndView mv = new ModelAndView();
		mv.addObject("categories", categoryDao.list());
		Product prod = new Product();
		mv.addObject("product", prod);
		mv.addObject("personID",u.getPersonID());
		mv.setViewName("product-form");
		return mv;

	}
	
	@RequestMapping(value = "/seller/seller-home", method = RequestMethod.GET)
	public ModelAndView homePage() throws Exception {
		return new ModelAndView("seller-home");
	}

	@RequestMapping(value = "/seller/product/add.htm", method = RequestMethod.POST)
	public String handleUpload(@ModelAttribute("product") Product product, HttpServletRequest request) {
		try {
			String personID = request.getParameter("personID");
			System.out.println("Person ID - "+personID);
			System.out.println("Title");
			System.out.println("Price - "+product.getPrice());
			
			if (product.getTitle().trim() != "" || product.getTitle() != null) {
				File directory;
				String check = File.separator; // Checking if system is linux
												// based or windows based by
												// checking seprator used.
				String path = null;
				if (check.equalsIgnoreCase("\\")) {
					path = servletContext.getRealPath("").replace("build\\", ""); // gives real path as Lab9/build/web/
																				  // so we need to replace build in the path
																						}

				if (check.equalsIgnoreCase("/")) {
					path = servletContext.getRealPath("").replace("build/", "");
					path += "/"; // Adding trailing slash for Mac systems.
				}
				directory = new File(path + "\\" + product.getFilename());
				boolean temp = directory.exists();
				if (!temp) {
					temp = directory.mkdir();
				}
				if (temp) {
					// We need to transfer to a file
					CommonsMultipartFile photoInMemory = product.getPhoto();

					String fileName = photoInMemory.getOriginalFilename();
					// could generate file names as well

					File localFile = new File(directory.getPath(), fileName);

					// move the file from memory to the file

					photoInMemory.transferTo(localFile);
					product.setFilename(localFile.getPath());
					System.out.println("File is stored at" + localFile.getPath());

					User u = userDao.get(product.getPostedBy());
					product.setUser(u);
					//product.setFilename(fileName);
					product = productDao.create(product);
					System.out.println("---Product has been added.---");
					 
		            for(Category c: product.getCategories()){
		            	c = categoryDao.get(c.getTitle());
		            	c.getProducts().add(product);
		            	categoryDao.update(c);
		            }
		            
		            System.out.println("Category has been updated");
		            return "product-success";

				} else {
					System.out.println("Failed to create directory!");
				}
			}

		} catch (IllegalStateException e) {
			System.out.println("*** IllegalStateException: " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("*** IOException: " + e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Inside FileController POST method");
		return "error";
	}
//
//	@RequestMapping(value = "/search.htm", method = RequestMethod.GET)
//	public String giveSearchForm(ModelMap model) {
////		User user = new User(); // should be AutoWired
////
////		// command object
////		model.addAttribute("user", user);
//
//		// return form view
//		return "search-user";
//
//	}
//
//	@RequestMapping(value = "/search.htm", method = RequestMethod.POST)
//	public ModelAndView seacrhUser(HttpServletRequest request) {
//		String key=request.getParameter("inputtext");
//        String searchKey=request.getParameter("searchkey");
//        List<User> userList = null;
//		try {
//			userList = userDao.get(key,searchKey);
//		} catch (UserException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        return new ModelAndView("search-result", "userList", userList);
//	}
}
