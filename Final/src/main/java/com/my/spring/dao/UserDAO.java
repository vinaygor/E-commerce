package com.my.spring.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.my.spring.exception.UserException;
import com.my.spring.pojo.Address;
import com.my.spring.pojo.Email;
import com.my.spring.pojo.User;

public class UserDAO extends DAO {

	public UserDAO() {
	}
	
	public int get(String username) throws UserException{
		begin();
		Query q=getSession().createQuery("from User where username =:username");
		q.setString("username",username);
		
		User user=(User)q.uniqueResult();
		if(user==null)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
	

	public int verifyUniqueEmail(String email) throws UserException{
		begin();
		Query q=getSession().createQuery("from Email where emailAddress =:emailAddress");
		q.setString("emailAddress",email);
		
		Email emailObj=(Email)q.uniqueResult();
		if(emailObj==null)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}

	public User get(String username, String password) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);			
			User user = (User) q.uniqueResult();
			commit();
			close();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + username, e);
		}
	}
	
	public User get(int userId) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where personID= :personID");
			q.setInteger("personID", userId);		
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + userId, e);
		}
	}

	public void register(User u)
			throws UserException {
		try {
			begin();
			System.out.println("Register -- inside DAO");
			System.out.println("Inside userDAO : Name of User"+u.getName());
			Email email = new Email(u.getEmail().getEmailAddress());
			User user = new User(u.getUsername(), u.getPassword(),u.getRole(),u.getCardDetails());
			List<Address> addressList = u.getAddress();
			Iterator<Address> it = addressList.iterator();
			int i=0;
			while(it.hasNext())
			{
				System.out.println("Inside 1");
				
				Address ua = new Address();
				ua=it.next();
				if(i==0)
				{
					System.out.println("Inside 2");
					ua.setAddressType("Billing");
					i++;
				}
				else
				{
					System.out.println("Inside 3");
					ua.setAddressType("Shipping");
				}
				

			System.out.println("Inside 5--- Value of user in Address :"+user.getPersonID());
				ua.setUser(user);
				System.out.println("Inside 6");
			}
			
			
			
			
			user.setName(u.getName());
			user.setEmail(email);
			
			email.setUser(user);
			System.out.println("Inside UserDAO -- Username: "+user.getName());
			
			System.out.println("Iterator Address 1 detail: "+addressList.get(0).getAddressType());
			
			
			user.setAddress(addressList);
			
			
			getSession().save(user);
			commit();
			

		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}

	public void delete(User user) throws UserException {
		try {
			begin();
			getSession().delete(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not delete user " + user.getUsername(), e);
		}
	}
}