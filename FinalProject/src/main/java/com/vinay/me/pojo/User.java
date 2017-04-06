package com.vinay.me.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.vinay.me.pojo.Email;

@Entity
@Table(name="user_table")
@PrimaryKeyJoinColumn(name="personID")
public class User extends Person{

	@Column(name="userName")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="dateOfBirth")
	private String dateOfBirth;
	
	@OneToOne(mappedBy="user", cascade=CascadeType.ALL)
	private Email email;
	
	@OneToOne(mappedBy="user", cascade=CascadeType.ALL)
	private Address address;
	

	
	
	public User(String username, String password, String dateOfBirth)
	{
		this.username=username;
		this.password=password;
		this.dateOfBirth=dateOfBirth;
//		this.address = new Address();
	}
	
	public User(){
		
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
		
}
