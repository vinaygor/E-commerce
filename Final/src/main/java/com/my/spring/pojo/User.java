package com.my.spring.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.my.spring.pojo.Address;

@Entity
@Table(name="user_table")
@PrimaryKeyJoinColumn(name="personID")
public class User extends Person {

	@Column(name="userName")
	private String username;

	@Column(name="password")
	private String password;

	@OneToOne(mappedBy="user", cascade=CascadeType.ALL)
	private Email email;
	
	@OneToOne(mappedBy="user", cascade=CascadeType.ALL)
	private Address address;
	
	@Column(name="role")
	private String role;
	
	@Column(name="activeStatus")
	private boolean activeStatus;
	
	@Column(name="cardDetails")
	private String cardDetails;
	
	public User(String username, String password, String role,String cardDetails) {
		this.username = username;
		this.password = password;
		this.role=role;
		this.activeStatus=true;
		this.cardDetails=cardDetails;
	}

	public User() {
	
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

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(boolean activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getCardDetails() {
		return cardDetails;
	}

	public void setCardDetails(String cardDetails) {
		this.cardDetails = cardDetails;
	}
	
	
}