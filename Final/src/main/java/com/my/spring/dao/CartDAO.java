package com.my.spring.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.my.spring.pojo.Cart;
import com.my.spring.pojo.Product;
import com.my.spring.pojo.User;

public class CartDAO extends DAO{

	public void insertIntoCart(Cart c){
		try{
			begin();
			getSession().save(c);
			commit();
		}
		catch(HibernateException e){
			System.out.println("Error while inserting the Cart object");
		}
		finally{
			close();
		}
	}
	
	public boolean getAProduct(User user, Product product)
	{
		try{
			begin();
			Query q = getSession().createQuery("from Cart where user= :user AND products = :products");
			q.setLong("user",user.getPersonID());
			q.setLong("products", product.getId());
			
			List<Cart> list=q.list();
			close();
			if(list.isEmpty())
			{
				return false;
			}
			else
			{
				if(list.size()>0)
				{
					return true;
				}
			}
			
		}
		catch(HibernateException e)
		{
			System.out.println("The Cart object could not be fetched");
		}
		return false;
	}
	
	public void updateCart(Cart c){
		
		try{
			begin();
			int qty = getQuantity(c.getUser(),c.getProduct());
			int newQty = qty + c.getQuantity();
			c.setQuantity(newQty);
			System.out.println("The new Quantity is :"+c.getQuantity());
			Query q= getSession().createQuery("Update Cart set quantity = :quantity where user =:user AND products =:product");
			q.setInteger("quantity",newQty);
			q.setLong("user", c.getUser().getPersonID());
			q.setLong("product", c.getProduct().getId());
			System.out.println("The user id is :"+c.getUser().getPersonID());
			q.executeUpdate();
			commit();
			close();
		}
		catch(HibernateException e)
		{
			System.err.println("Could not update the cart");
		}
	}
	
	public int getQuantity(User user, Product product)
	{
		try{
			begin();
			System.out.println("Inside get Quantity method");
			Query q = getSession().createQuery("from Cart where user= :user AND products = :products");
			q.setLong("user",user.getPersonID());
			q.setLong("products", product.getId());
			Cart c = (Cart)q.uniqueResult();
			int quantity = c.getQuantity();
			System.out.println("Quantity is "+quantity);
			close();
			return quantity;
		}
		catch(HibernateException e)
		{
			System.out.println("The Cart object could not be fetched");
		}
		return -1;
	}
	
	public List<Cart> getList(long userId){
		try{
			begin();
			Query q=  getSession().createQuery("from Cart where user= :user");
			q.setLong("user", userId);
			List<Cart> list= q.list();
			close();
			return list;
		}
		catch(HibernateException e)
		{
			rollback();
			System.out.println("There was an error in retrieving user details in getList() method.");
			
		}
		return null;
	}
	
	public void removeFromCart(long productId, User user)
	{
		try{
			begin();
			System.out.println("Inside the CartDAO removeFromCart method");
			Query q= getSession().createQuery("delete from Cart where user =:user and products= :product");
			q.setLong("user", user.getPersonID());
			q.setLong("product", productId);
			q.executeUpdate();
			System.out.println("The product has been deleted from the cart");
			commit();
			close();
		}
		catch(HibernateException e)
		{
			rollback();
			System.out.println("The value could not be Deleted from cart");
		}
		
	}
	
	public void removeAllForAUser(User user)
	{
		try{
			begin();
			System.out.println("Inside the CartDAO removeAllForAUser method");
			Query q= getSession().createQuery("delete from Cart where user =:user");
			q.setLong("user", user.getPersonID());
			q.executeUpdate();
			System.out.println("All the products has been deleted from the cart for user :"+user.getName());
			commit();
			close();
		}
		catch(HibernateException e)
		{
			rollback();
			System.out.println("All the products for user "+user.getName()+" could not be Deleted from cart");
		}
		
	}
	
	
	
	

}
