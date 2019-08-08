package com.coffeehouse.model.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="customer")
public class CustomerEntity  implements Serializable  {

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id; 
	
	
	@NotNull
	@Column(name = "Name")
    @Size(max = 65)
	private String name; 
	

	@Column(name = "MobileNumber ", unique=true)
	private long mobileNumber;

	
	
	///////////////////////////////////////////////////////////////
	//
	// Correct Mapping
	//
	//////////////////////////////////////////////////////////////
	
	@OneToMany(mappedBy ="customerEntity", cascade =CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<OrderEntity> orderEntities ;


	/*public CustomerEntity(long id, String name, long mobileNumber) {
		super();
		this.id = id;
		this.name = name;
		this.mobileNumber = mobileNumber;
	}*/

	public Set<OrderEntity> getOrderEntities() {
		return orderEntities;
	}

	public void setOrderEntities(Set<OrderEntity> orderEntities) {
		this.orderEntities = orderEntities;
	}


	public CustomerEntity() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


}