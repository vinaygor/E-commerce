package com.my.spring.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.my.spring.pojo.Order;

public class OrderDAO extends DAO{

	public void insertIntoOrder(Order order)
	{
		try{
			begin();
			System.out.println("Inserting Data into the Order table");
			System.out.println("Order details input: "+order.getQuantity()+" SalesOrderID:"+order.getOrderid());
			getSession().save(order);
			System.out.println("Saving Data into the Order table");
			commit();
			close();
		}
		catch(HibernateException e)
		{
			System.out.println("Could not insert the details in the order");
		}
	}
	
	
	public List<Order> getOrderList(long id)
	{
		try{
			begin();
			System.out.println("Selecting data into the Order table");
			Query q = getSession().createQuery("From Order where user = :user AND isCompleted= :isCompleted");
			q.setLong("user", id);
			q.setBoolean("isCompleted", false);
			List<Order> orderList = q.list();
			System.out.println("The size of the list is :"+orderList.size());
			
			commit();
			close();
			return orderList;
		}
		catch(HibernateException e)
		{
			System.out.println("Could not get the list of the order");
		}
		return null;
	}
	
	public List<Order> getOrdersBySalesOrderId(String salesOrder)
	{
		try{
			begin();
			System.out.println("Selecting data into the Order table for SalesOrder:"+salesOrder);
			Query q = getSession().createQuery("From Order where orderid = :orderid");
			q.setString("orderid", salesOrder);
			List<Order> orderList = q.list();
			System.out.println("The size of the list is :"+orderList.size());
			commit();
			close();
			return orderList;
		}
		catch(HibernateException e)
		{
			System.out.println("Could not get the list of the order for salesOrder "+salesOrder);
		}
		return null;
	}
	
	public void completedOrder(long id)
	{
		try{
			begin();
			Query q = getSession().createQuery("Update Order set isCompleted =:isCompleted where user = :user");
			q.setBoolean("isCompleted",true);
			q.setLong("user",id);
			q.executeUpdate();
			commit();
			close();
			System.out.println("The Order Table has been updated");
			
		}
		catch(HibernateException e)
		{
			System.out.println("Could not update the completed status of the order table");
		}
	}
	
	public boolean checkIfPresent(long userId, long productId)
	{
		try{
			begin();
			Query q = getSession().createQuery("from Order where user = :user and product= :product and isCompleted =:isCompleted");
			q.setLong("product",productId);
			q.setLong("user", userId);
			q.setBoolean("isCompleted", false);
			
			List<Order> list= q.list();
			if(list.size()==1)
				{
				System.out.println("Inside the Delete function of repeating orders");
				Query q1 = getSession().createQuery("delete Order where user = :user and product= :product and isCompleted =:isCompleted");
				q.setLong("product",productId);
				q.setLong("user", userId);
				q.setBoolean("isCompleted", false);
				System.out.println("Deleting the values now!!");
				q.executeUpdate();
				commit();
				close();
				return true;
				}
			else
			{
				close();
				return true;
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Could not check if the user is present or not");
		}
		return false;
	}
	
	
}
