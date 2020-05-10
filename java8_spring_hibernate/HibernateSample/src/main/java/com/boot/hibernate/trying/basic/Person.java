package com.boot.hibernate.trying.basic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "Person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Phone> phones = new ArrayList<>();

	public Long getId() {
		return id;
	}

//	public List<Phone> getPhones() {
//		return phones;
//	}
//
//	public void setPhones(List<Phone> phones) {
//		this.phones = phones;
//	}
	
//	The @OneToMany association links a parent entity with one or more child entities. 
//	If the @OneToMany doesn’t have a mirroring @ManyToOne association on the child side, 
//	the @OneToMany association is unidirectional. If there is a @ManyToOne association on the child side, 
//	the @OneToMany association is bidirectional and the application developer can navigate 
//	this relationship from both ends.

}
