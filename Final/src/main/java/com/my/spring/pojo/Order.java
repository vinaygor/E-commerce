package com.my.spring.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="order_table")
public class Order implements Serializable {
    
    @Id
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    
    
    @Id
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
    
    @Column(name="quantity")
    private int quantity;
    
    @Column(name="iscompleted")
    private boolean completed;
    
    @Column(name ="sellername")
    private String sellerName;
    
    @Column(name="date")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Calendar dateTime;
    
    @Id
    @Column(name="orderid")
    private String orderid;
    
    public Order() {
        super();
    }

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}


	
	


	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public java.util.Calendar getDateTime() {
        return dateTime;
    }




    public void setDateTime(java.util.Calendar dateTime) {
        this.dateTime = dateTime;
    }


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

    
    
}