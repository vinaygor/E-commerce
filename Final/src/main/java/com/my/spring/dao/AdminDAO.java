package com.my.spring.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.my.spring.exception.AdminException;
import com.my.spring.exception.AdvertException;
import com.my.spring.exception.UserException;
import com.my.spring.pojo.Admin;
import com.my.spring.pojo.Advert;
import com.my.spring.pojo.User;

public class AdminDAO extends DAO{

	public AdminDAO(){}
	
	public Admin verifyAdmin(String username,String password) throws AdminException
	{
		
		try {
			begin();
			Query q = getSession().createQuery("from Admin where adminUserName = :adminUserName and adminPassword = :adminPassword");
			q.setString("adminUserName", username);
			q.setString("adminPassword", password);			
			Admin admin = (Admin) q.uniqueResult();
			commit();
			close();
			return admin;
		} catch (HibernateException e) {
			rollback();
			throw new AdminException("Could not get admin " + username, e);
		}
		
	}
	
	public void insertData() throws AdminException
	{
		try{
			begin();
			System.out.println("Inside AdminDAO---------Inserting Admin details-----------");
			
			Admin admin = new Admin("admin","admin");
			getSession().save(admin);
			commit();
			close();
		}
		catch(HibernateException e)
		{
			rollback();
			throw new AdminException("Could not create admin " + e);
		}
	}
	
	public List<User> listOfUsers() throws AdminException{
		try {
            begin();
            Query q = getSession().createQuery("from User");
            List<User> users = q.list();
            commit();
            return users;
            
        } catch (HibernateException e) {
            rollback();
            throw new AdminException("Could not get Users", e);
        }
	}
	
	public String updateActiveStatus(int personID) throws AdminException{
		try{
			begin();
			Query q=getSession().createQuery("from User where personID = :personID");
			q.setInteger("personID", personID);
			User user=(User) q.uniqueResult();
			boolean status= user.isActiveStatus();
			System.out.println("Printing status before changes :"+user.isActiveStatus());
			if(status==true)
			{
				q=getSession().createQuery("Update User set activeStatus = :status where personID = :personID");
				q.setBoolean("status",false);
				q.setLong("personID", personID);
				int result = q.executeUpdate();
				commit();
				q=getSession().createQuery("from User where personID = :personID");
				q.setInteger("personID", personID);
				user=(User) q.uniqueResult();
				System.out.println("True : Printing status after changing: "+user.isActiveStatus());
				close();
				return "false";
			}
			else
			{
				q=getSession().createQuery("Update User set activeStatus = :status where personID = :personID");
				q.setBoolean("status",true);
				q.setLong("personID", personID);
				int result = q.executeUpdate();
				commit();
				q=getSession().createQuery("from User where personID = :personID");
				q.setInteger("personID", personID);
				user=(User) q.uniqueResult();
				System.out.println("False : Printing status after changing: "+user.isActiveStatus());
				close();
				return "true";
			}
			
		}
		catch(HibernateException e)
		{
			rollback();
			System.out.println("Inside the AdminDAO catch block");
			throw new AdminException("Could not Change the status " + e);
		}
	}
}
