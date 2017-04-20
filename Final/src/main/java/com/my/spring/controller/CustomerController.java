package com.my.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.my.spring.dao.ProductDAO;

@Controller
public class CustomerController {
	
	@Autowired
	ProductDAO productDao;

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
}
