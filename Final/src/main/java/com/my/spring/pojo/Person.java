package com.my.spring.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="person_table")
@Inheritance(strategy=InheritanceType.JOINED)
//table per subclass
public class Person {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="personID", unique=true, nullable=false)
	private long personID;
	
	@Column(name="name")
	private String name;
	
	public Person(){
		
	}
	
	public long getPersonID() {
		return personID;
	}

	public void setPersonID(long personID) {
		this.personID = personID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
