package com.my.spring.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.my.spring.exception.AdvertException;
import com.my.spring.pojo.Product;
import com.my.spring.pojo.Category;

public class AdvertDAO extends DAO {

    public Product create(Product advert)
            throws AdvertException {
        try {
            begin();            
            getSession().save(advert);     
            commit();
            return advert;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create advert", e);
            throw new AdvertException("Exception while creating advert: " + e.getMessage());
        }
    }

    public void delete(Product advert)
            throws AdvertException {
        try {
            begin();
            getSession().delete(advert);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not delete advert", e);
        }
    }
    
    public List<Product> list() throws AdvertException{
    	
    	try {
            begin();
            Query q = getSession().createQuery("from Advert");
            List<Product> adverts = q.list();
            commit();
            return adverts;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not delete advert", e);
        }
    	
    }
}