package com.vinay.me.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="productId", unique=true,nullable=false)
	private String productID;
	
	@Column(name="productName")
	private String productName;
	
	@Column(name="description")
	private String description;
	
	@Column(name="price")
	private String price;
	
	private Seller seller;
	private Set<Category> categories = new HashSet<Category>();	
	
	
	int postedBy;
	
}
