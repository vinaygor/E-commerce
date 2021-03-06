package com.my.spring.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

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
            Query q = getSession().createQuery("delete from Product where id=:id");
            q.setLong("id", product.getId());
            int r=q.executeUpdate();
            System.out.println("Rows deleted :"+q);
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
    
    public int getTotalCount(){
    	
    	try{
    		begin();
    		Query q=getSession().createQuery("from Product");
    		List<Product> p = q.list();
    		int sizeTotal = p.size();
    		commit();
    		close();
    		return sizeTotal;
    		
    	}
    	catch(HibernateException e)
    	{
    		System.out.println("Could not fetch total size of product list");
    	}
		return 0;
    }
    
    public List<Product> listPaginatedProductsUsingCriteria(int firstResult, int maxResults) {
        
        try {
        	begin();
            Criteria criteria = getSession().createCriteria(Product.class);
            criteria.setFirstResult(firstResult);
            criteria.setMaxResults(maxResults);
            List<Product> products = (List<Product>) criteria.list();
            commit();
            close();
            return products;
 
        } catch (HibernateException e) {
            e.printStackTrace();
        }
		return null; 
    }
    
    private static Criteria getCriteria() {
        Criteria criteria = getSession().createCriteria(Product.class);
        criteria.add(Restrictions.isNotNull("productName"));
        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("productName"))
                .add(Projections.property("id")));
        criteria.addOrder(Order.asc("id"));
        return criteria;
    }
    
    public List<Product> getProductList(String keyword){
    	try{
    		begin();
    		Criteria c = getSession().createCriteria(Product.class);
    		Criterion cr1 = Restrictions.ilike("description", "%"+keyword+"%");
    		Criterion cr2 = Restrictions.ilike("title", "%"+keyword+"%");
    		
    		LogicalExpression or = Restrictions.or(cr1,cr2);
    		c.add(or);
    		List<Product> list = c.list();
    		System.out.println("The size of the list with keywords is :"+list.size());
    		close();
    		return list;
    	}
    	catch(HibernateException e)
    	{
    		rollback();
    		System.out.println("Could not fetch list by applying restrictions to it.");
    		
    	}
    	return null;
    }
    
    
    public List<Product> getProductListOrder(String keyword){
    	try{
    		begin();
    		Criteria c = getSession().createCriteria(Product.class);
    		if(keyword.equalsIgnoreCase("asc"))
    			c.addOrder(Order.asc("price"));
    		else
    			c.addOrder(Order.desc("price"));
    		
    		List<Product> list = c.list();
    		System.out.println("The size of the list with Ordered price is :"+list.size());
    		close();
    		return list;
    	}
    	catch(HibernateException e)
    	{
    		rollback();
    		System.out.println("Could not fetch list by applying ordering restrictions to it.");
    		
    	}
    	return null;
    }
    
}