package com.vinay.me.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="address")
public class Address {
	@Id
	@GeneratedValue(generator="generator")
	@GenericGenerator(name="generator", strategy="foreign", parameters=@Parameter(name="property", value="user"))
	@Column(name="ID", unique = true, nullable=false)
	private long Id;
	
	@Column(name="streetAddress")
	private String streetAddress;
	
	@Column(name="city")
	private String city;

	@Column(name="state")
	private String state;
	
	@Column(name="country")
	private String country;
	
	@Column(name="zipCode")
	private String zipCode;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private User user;
	
	public Address(){}
	
	public Address(String streetAddress, String city, String state, String country, String zipCode)
	{
		this.streetAddress=streetAddress;
		this.city=city;
		this.state=state;
		this.country=country;
		this.zipCode=zipCode;
		System.out.println("Inside :"+getCountry());
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getState() {
		return state;
	}

	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
