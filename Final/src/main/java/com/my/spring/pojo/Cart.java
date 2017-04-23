package com.my.spring.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cart_table")
public class Cart implements Serializable{
	
	@Id
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Id
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product products;
	
	@Column(name="quantity")
	private int quantity;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return products;
	}

	public void setProduct(Product product) {
		this.products = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
