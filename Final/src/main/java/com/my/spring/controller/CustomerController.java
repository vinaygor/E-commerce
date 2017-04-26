package com.my.spring.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.my.spring.dao.CartDAO;
import com.my.spring.dao.OrderDAO;
import com.my.spring.dao.ProductDAO;
import com.my.spring.dao.UserDAO;
import com.my.spring.pojo.Address;
import com.my.spring.pojo.Cart;
import com.my.spring.pojo.Order;
import com.my.spring.pojo.Product;
import com.my.spring.pojo.User;

@Controller
public class CustomerController {
	
	@Autowired
	ProductDAO productDao;
	
	@Autowired
	UserDAO userDao;
	
	@Autowired
	CartDAO cartDao;
	
	@Autowired
	OrderDAO orderDao;
	

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
		HttpSession session = request.getSession();
		User currentUser = (User)session.getAttribute("user");
		System.out.println("The user ID which we got is :"+currentUser.getName());
		List<Cart> cartList = cartDao.getList(currentUser.getPersonID());
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
	
	@RequestMapping(value="/user/checkout.htm", method=RequestMethod.GET)
	private void goToCheckOutGet(HttpServletRequest request){
	}
	
	@RequestMapping(value="/user/checkout.htm", method=RequestMethod.POST)
	private ModelAndView goToCheckOut(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		User user= (User)session.getAttribute("user");
		List<Cart> cartList = cartDao.getList(user.getPersonID());
		
//		SimpleDateFormat newFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//		String salesOrderId = newFormat.format(new Date());
//		
//		System.out.println("The cart size before inserting into the order table is :"+cartList.size());
//		for(Cart cart:cartList)
//		{
//			if(orderDao.checkIfPresent(cart.getUser().getPersonID(), cart.getProduct().getId()))
//			{
//				Order order = new Order();
//				order.setOrderid(salesOrderId);
//				order.setProduct(cart.getProduct());
//				order.setQuantity(cart.getQuantity());
//				order.setUser(cart.getUser());
//				order.setSellerName(cart.getProduct().getUser().getName());
//				order.setDateTime(Calendar.getInstance());
//				order.setCompleted(false);
//				orderDao.insertIntoOrder(order);
//			}
//		}
//		
//		
//		
//		System.out.println("All the details have been put into the order table");
//		mv.addObject("orderList", orderDao.getOrderList(user.getPersonID()));
		
		mv.addObject("cartList", cartList);
		mv.addObject("address",userDao.getShippingAddress(user.getPersonID()));
		mv.setViewName("checkout");
		return mv;
	}
	
	@RequestMapping(value="/user/placeOrder.htm", method=RequestMethod.GET)
	public void getMethod(){
		
	}

	@RequestMapping(value="/user/placeOrder.htm", method=RequestMethod.POST)
	private ModelAndView goToPlaceOrder(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		User user= (User)session.getAttribute("user");
		List<Cart> cartList = cartDao.getList(user.getPersonID());
		
		SimpleDateFormat newFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String salesOrderId = newFormat.format(new Date());
		
		System.out.println("The cart size before inserting into the order table is :"+cartList.size());
		for(Cart cart:cartList)
		{
			if(orderDao.checkIfPresent(cart.getUser().getPersonID(), cart.getProduct().getId()))
			{
				Order order = new Order();
				order.setOrderid(salesOrderId);
				order.setProduct(cart.getProduct());
				order.setQuantity(cart.getQuantity());
				order.setUser(cart.getUser());
				order.setSellerName(cart.getProduct().getUser().getName());
				order.setDateTime(Calendar.getInstance());
				order.setCompleted(true);
				orderDao.insertIntoOrder(order);
			}
		}
		
		cartDao.removeAllForAUser(user);
		System.out.println("Cart has been cleared for user "+user.getName());
		
		Address add = userDao.getShippingAddress(user.getPersonID());
		
		//Sending out the email to the user
		try{
            Email email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("vinaygor11@gmail.com", "delllatitude"));
            email.setSSLOnConnect(true);
            email.setFrom("vinaygor11@gmail.com");
            email.setSubject("Order Confirmation -"+new Date());
            email.setMsg("Thank you for placing the order. Your order id is "+salesOrderId+". It will take around 5-7 days for products to be delivered at your location. Your shipping address:"+add.getStreetAddress()+", "+add.getCity()+", "+add.getState()+", "+add.getCountry()+" - "+add.getZipCode());
            email.addTo(user.getEmail().getEmailAddress());
            email.send();
        }
        catch(Exception e)
		{
        	System.out.println("Could not send the email to the user. "+e.getMessage()+"---"+e);
		}
		
		System.out.println("All the details have been put into the order table");
		mv.addObject("orderList", orderDao.getOrdersBySalesOrderId(salesOrderId));
		mv.addObject("salesOrder",salesOrderId);
		session.setAttribute("salesOrderID", salesOrderId);
		mv.addObject("address",userDao.getShippingAddress(user.getPersonID()));
		mv.setViewName("orderConfirmation");
		return mv;
	}
	
	@RequestMapping(value="/user/order.pdf", method = RequestMethod.GET)
    public void pdfviewGet(HttpServletRequest request) throws Exception{
		
	}
	
	@RequestMapping(value="/user/order.pdf", method = RequestMethod.POST)
    public ModelAndView pdfview(HttpServletRequest request) throws Exception
    {
        System.out.println("In Order pdf method");
        HttpSession session = (HttpSession) request.getSession();
                        
        View view = new PDFController();
        System.out.println("In Order pdf method");
        User user = (User)session.getAttribute("user");
        Long userId = user.getPersonID();
        
        List<Order> list = orderDao.getOrdersBySalesOrderId((String)session.getAttribute("salesOrderID"));
        System.out.println("OrderList size"+list.size());
        //int orderList =Integer.parseInt(request.getParameter(("id")));
        System.out.println("In Order pdf method");
        System.out.println("orderId"+session.getAttribute("salesOrderID"));
        ModelAndView mv = new ModelAndView();
        mv.addObject("list", list);
        mv.addObject("salesOrderID", session.getAttribute("salesOrderID"));
        mv.addObject("address",userDao.getShippingAddress(user.getPersonID()));
        mv.setView(view);
        return mv;
        //return new ModelAndView(view,"list",list);
    }
	
	@RequestMapping(value="/user/getProductList.htm", method=RequestMethod.GET, produces = MediaType.ALL_VALUE)
	public @ResponseBody String getProductListKeyword(HttpServletRequest request,HttpServletResponse response)
	{
		String keyword = request.getParameter("val");
		System.out.println("Inside the Controller of getProductList() method");
		List<Product> productList = productDao.getProductList(keyword);
		HttpSession session = request.getSession();
		session.setAttribute("productList",productList);
		session.setAttribute("size",productList.size());
		return String.valueOf(productList.size());
		
		
	}
	
	@RequestMapping(value="/user/searchproducts.htm", method=RequestMethod.GET)
	public String searchProducts(HttpServletRequest request)
	{
		return "search-product";
	}
	{
		
	}
	
}
