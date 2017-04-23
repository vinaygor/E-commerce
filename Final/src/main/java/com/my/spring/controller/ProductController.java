package com.my.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.my.spring.dao.CartDAO;
import com.my.spring.dao.CategoryDAO;
import com.my.spring.dao.UserDAO;
import com.my.spring.exception.AdvertException;
import com.my.spring.pojo.Product;
import com.my.spring.pojo.Cart;
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
		
		@Autowired
		CartDAO cartDao;
		

	@RequestMapping(value="/user/user/addToCart.htm", method = RequestMethod.GET)
	public boolean insertIntoCart(HttpServletRequest request) throws AdvertException
	{
		System.out.println("Hello... I'm inside Add to cart!");
		HttpSession session = request.getSession();
		Long productId= Long.parseLong(request.getParameter("id"));
		int quantity= Integer.parseInt(request.getParameter("qty"));
		User currentUser = (User)session.getAttribute("user");
		System.out.println("Inside the Add to cart method. Values :"+productId+" and Qty :"+quantity);
		
		System.out.println("Checking if the object has been added to the cart before!");
		
		Cart cart= new Cart();
		cart.setUser(currentUser);
		cart.setQuantity(quantity);
		cart.setProduct(productDao.get(productId));
		boolean isPresent=cartDao.getAProduct(currentUser, productDao.get(productId));
		if(isPresent==false)
		{
		cartDao.insertIntoCart(cart);
		return true;
		}
		else
		{
			cartDao.updateCart(cart);
			return true;
		}
	}

}