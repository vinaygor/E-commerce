package com.my.spring.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.my.spring.exception.AdvertException;
import com.my.spring.pojo.Product;
import com.my.spring.pojo.Category;

public class ProductDAO extends DAO {

    public Product create(Product product)
            throws AdvertException {
        try {
            begin();            
            getSession().save(product);     
            commit();
            return product;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create advert", e);
            throw new AdvertException("Exception while creating product: " + e.getMessage());
        }
    }

    public void delete(Product product)
            throws AdvertException {
        try {
            begin();
            getSession().delete(product);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not delete product", e);
        }
    }
    
    public List<Product> list() throws AdvertException{
    	
    	try {
            begin();
            Query q = getSession().createQuery("from Product");
            List<Product> adverts = q.list();
            commit();
            return adverts;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not delete product", e);
        }
    	
    }
    
    public List<Product> list(long user_personID) throws AdvertException{
    	
    	try {
            begin();
            Query q = getSession().createQuery("from Product where user= :personID");
            q.setLong("personID", user_personID);
            List<Product> adverts = q.list();
            commit();
            return adverts;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not delete product", e);
        }
    	
    }
    
    public Product get(long id) throws AdvertException{
    	try{
    		begin();
    		Query q=getSession().createQuery("from Product where id= :id");
    		q.setLong("id",id);
    		Product p = (Product)q.uniqueResult();
    		commit();
    		return p;
    	}
    	catch(HibernateException e){
    		rollback();
    		throw new AdvertException("Could not get product",e);
    	}
    }
    
    public void update(Product p) throws AdvertException
    {
    	try{
    		begin();
    		Query q=getSession().createQuery("Update Product set description=:description,filename=:filename,"
    				+ "price=:price where id=:id");
    		
    		q.setString("description", p.getDescription());
    		q.setString("filename", p.getFilename());
    		q.setLong("price",p.getPrice());
    		q.setLong("id", p.getId());
    		System.out.println("Desc:"+p.getDescription()+" filename:"+p.getFilename()+" price:"+p.getPrice()+"ID:"+p.getId());
    		int result=q.executeUpdate();
    		System.out.println("Product updated Successfully "+result);
    		commit();
    		close();
    		
    	}
    	catch(HibernateException e){
    		rollback();
    		throw new AdvertException("Could Not update product");
    	}
    }
}