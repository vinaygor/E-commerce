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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.my.spring.dao.CategoryDAO;
import com.my.spring.dao.ProductDAO;
import com.my.spring.dao.UserDAO;
import com.my.spring.exception.AdminException;
import com.my.spring.exception.AdvertException;
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
	
	@RequestMapping(value = "/seller/product/view.htm", method = RequestMethod.GET)
	public ModelAndView viewForm(ModelMap model,HttpServletRequest request) throws Exception {
		HttpSession session=request.getSession();
		User u=(User)session.getAttribute("user");
		System.out.println("PersoniD - "+u.getPersonID());
		ModelAndView mv = new ModelAndView();
		mv.addObject("productList",productDao.list(u.getPersonID()));
		mv.setViewName("product-list");
		return mv;

	}
	
	
	@RequestMapping(value="seller/product/updateProduct.htm", method=RequestMethod.GET)
	public ModelAndView updateProduct(HttpServletRequest request) throws AdvertException, CategoryException{
		String ids=request.getParameter("id");
		long id=Long.parseLong(ids);
		Product prod= productDao.get(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("categories",categoryDao.list());
		mv.addObject("product",prod);
		mv.setViewName("update-product");
		return mv;
	}
	
	
	@RequestMapping(value = "/seller/product/update.htm", method = RequestMethod.POST)
	public String handleUpdate(@ModelAttribute("product") Product product, HttpServletRequest request) {
		try {
			String personID = request.getParameter("personID");
			String title = request.getParameter("title");
			String Id=request.getParameter("id");
			product.setId(Long.parseLong(Id));
			product.setTitle(title);
			System.out.println("Person ID - "+personID);
			System.out.println("Title"+product.getTitle());
			System.out.println("Price - "+product.getPrice());
			
			//if (product.getTitle().trim() != "" || product.getTitle() != null) 
			{
				 {
					// We need to transfer to a file
					CommonsMultipartFile photoInMemory = product.getPhoto();

					String fileName = title+"_"+personID+"_"+photoInMemory.getOriginalFilename();
					// could generate file names as well

					File localFile = new File("E:/Semester 2/Web Tools/Project_Spring/Final/src/main/webapp/resources/", fileName);

					// move the file from memory to the file

					photoInMemory.transferTo(localFile);
					product.setFilename(fileName);
					System.out.println("File is stored at" + localFile.getPath());

					User u = userDao.get(product.getPostedBy());
					product.setUser(u);
					//product.setFilename(fileName);
					productDao.update(product);
					System.out.println("---Product has been updated Successfully.---");
					 
////		            for(Category c: product.getCategories()){
////		            	c = categoryDao.get(c.getTitle());
////		            	c.getProducts().add(product);
////		            	categoryDao.update(c);
////		            }
//		            
//		            System.out.println("Category has been updated");
		            return "product-success";

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

		//System.out.println("Inside FileController POST method");
		return "error";
	}

	
	
	@RequestMapping(value="seller/product/deleteProduct.htm", method=RequestMethod.GET)
	public @ResponseBody String deleteCategory(HttpServletRequest request) throws AdminException, CategoryException, AdvertException{
	
		
		System.out.println("Inside the Delete Product function");
		String id_s=request.getParameter("id");
		long id=Long.parseLong(id_s);
		productDao.delete(productDao.get(id));
		System.out.println("Deleted the Product :"+id_s);
		//System.out.println(String.valueOf(userId));
		return id_s;
		
		//return new ModelAndView("admin-home","null",null);
//		else
//			return new ModelAndView("error","null",null);
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
			
			//if (product.getTitle().trim() != "" || product.getTitle() != null) 
			{
				{					// We need to transfer to a file
					CommonsMultipartFile photoInMemory = product.getPhoto();

					String fileName = photoInMemory.getOriginalFilename();
					// could generate file names as well

					File localFile = new File("E:/Semester 2/Web Tools/Project_Spring/Final/src/main/webapp/resources/", fileName);

					// move the file from memory to the file

					photoInMemory.transferTo(localFile);
					product.setFilename(fileName);
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
