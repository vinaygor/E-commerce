package com.my.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.my.spring.dao.CartDAO;
import com.my.spring.dao.ProductDAO;
import com.my.spring.dao.UserDAO;
import com.my.spring.pojo.Cart;
import com.my.spring.pojo.User;

@Controller
public class CustomerController {
	
	@Autowired
	ProductDAO productDao;
	
	@Autowired
	UserDAO userDao;
	
	@Autowired
	CartDAO cartDao;

	@RequestMapping(value = "/user/products.htm", method = RequestMethod.GET)
	protected ModelAndView viewProducts(HttpServletRequest request) throws Exception{
		ModelAndView mv=new ModelAndView();
		HttpSession session= (HttpSession)request.getSession();
		int startPage = (Integer)session.getAttribute("startPage");
		System.out.println("Start Page: "+startPage);
		String type=request.getParameter("side");
		if(type==null || type.equals("next"))
		{
		int endPage = startPage+5;
		
		int totalSize=productDao.getTotalCount();
		System.out.println("Total Count-"+totalSize);
		if(endPage>totalSize)
		{
			endPage=totalSize-5;
			System.out.println("If gt , startPage = "+endPage);
			mv.addObject("products",productDao.listPaginatedProductsUsingCriteria(endPage, 5));
		}
		else
		{
			mv.addObject("products",productDao.listPaginatedProductsUsingCriteria(startPage, 5));
		}
		mv.setViewName("view-product");
		
		session.setAttribute("startPage",endPage);
		}
		else if(type.equals("back"))
		{
			
			int endPage = startPage-5;
			if(endPage<0)
			{
				mv.addObject("products",productDao.listPaginatedProductsUsingCriteria(0, 5));
				mv.setViewName("view-product");
				session.setAttribute("startPage",0);
				return mv;
			}
			startPage=endPage;
			mv.addObject("products",productDao.listPaginatedProductsUsingCriteria(startPage, 5));
			mv.setViewName("view-product");
			
			session.setAttribute("startPage",endPage);
		}
		return mv;
	}
	
	@RequestMapping(value = "/user/viewCart.htm", method = RequestMethod.GET)
	protected ModelAndView viewCart(HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView();
		System.out.println("The user ID which we got is :"+request.getParameter("id"));
		long userId = Long.parseLong(request.getParameter("id"));
		User currentUser = userDao.get((int)userId);
		List<Cart> cartList = cartDao.getList(userId);
		mv.addObject("cartList", cartList);
		mv.setViewName("view-cart-new");
		return mv;
	}
	
	@RequestMapping(value = "/user/deleteFromCart.htm", method = RequestMethod.GET)
	protected @ResponseBody String deleteFromCart(HttpServletRequest request) throws Exception{
		long productId= Long.parseLong(request.getParameter("id"));
		System.out.println("Inside the remove from cart method");
		System.out.println("The productId received is "+productId);
		HttpSession session= (HttpSession)request.getSession();
		User user = (User)session.getAttribute("user");
		cartDao.removeFromCart(productId,user);
		System.out.println("The product has been deleted from Cart");
		return String.valueOf(productId);
	}
	
	
}
