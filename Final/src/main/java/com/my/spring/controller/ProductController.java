package com.my.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.my.spring.dao.ProductDAO;
import com.my.spring.dao.CategoryDAO;
import com.my.spring.dao.UserDAO;
import com.my.spring.exception.AdvertException;
import com.my.spring.pojo.Product;
import com.my.spring.pojo.Category;
import com.my.spring.pojo.User;

@Controller
//@RequestMapping("/seller/product/*")
public class ProductController {
		
		@Autowired
		ProductDAO productDao;
		
		@Autowired
		@Qualifier("categoryDao")
		CategoryDAO categoryDAO;
		
		@Autowired
		@Qualifier("userDao")
		UserDAO userDao;
		

		//@RequestMapping(value = "/seller/product/add.htm", method = RequestMethod.POST)
		public ModelAndView addCategory(@ModelAttribute("product") Product product, BindingResult result) throws Exception {

			try {			
				
//				User u = userDao.get(product.getPostedBy());
//				product.setUser(u);
//				product = productDao.create(product);
//				
//	            
//	            for(Category c: product.getCategories()){
//	            	c = categoryDAO.get(c.getTitle());
//	            	c.getAdverts().add(product);
//	            	categoryDAO.update(c);
//	            }
				
				return new ModelAndView("advert-success", "advert", product);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while creating product");
			}
			
			
		}
		
		//@RequestMapping(value = "/advert/list", method = RequestMethod.GET)
		public ModelAndView addCategory(HttpServletRequest request) throws Exception {

			try {			
				
				List<Product> adverts = productDao.list();
				return new ModelAndView("advert-list", "adverts", adverts);
				
			} catch (AdvertException e) {
				System.out.println(e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while login");
			}
			
			
		}

//		@RequestMapping(value="/seller/product/add.htm", method = RequestMethod.GET)
		public ModelAndView initializeForm(HttpServletRequest request) throws Exception {		
			ModelAndView mv = new ModelAndView();
			mv.addObject("categories", categoryDAO.list());			
			mv.addObject("product", new Product());
			mv.setViewName("product-form");
			return mv;
		}


}