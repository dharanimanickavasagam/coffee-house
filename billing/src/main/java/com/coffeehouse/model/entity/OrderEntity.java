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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="orders")
public class OrderEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id; 


	@Column(name="total_price")
	private double totalPrice;

	@Column(name="tax")
	private double tax;

	@Column(name="tendered_amount")
	private double tenderedAmount;
	
	
	///////////////////////////////////////////////////////////////
	//
	// Correct Mapping
	//
	//////////////////////////////////////////////////////////////
	
	@ManyToOne
	@JoinColumn(name="customer_id", referencedColumnName = "id")
	private CustomerEntity customerEntity;
	
	

	///////////////////////////////////////////////////////////////
	//
	// Which ever parent maps, cascade is set in it 
	//
	/////////////////////////////////////////////////////////////
	
	@OneToMany(mappedBy="orderEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<OrderItemsEntity> orderItemsEntities;


	public OrderEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Set<OrderItemsEntity> getOrderItemsEntities() {
		return orderItemsEntities;
	}


	public void setOrderItemsEntities(Set<OrderItemsEntity> orderItemsEntities) {
		this.orderItemsEntities = orderItemsEntities;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	// Getter method to retrieve customer_id
    public Long getCustomer_id(){
        return customerEntity.getId();
    }
    
    // Setter method for customer_id
    public void setCustomer_id(CustomerEntity customerEntity){
        this.customerEntity = customerEntity; 
    }


	@JsonIgnore
	public CustomerEntity getCustomerEntity() {
		return customerEntity;
	}

	@JsonIgnore
	public void setCustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getTenderedAmount() {
		return tenderedAmount;
	}

	public void setTenderedAmount(double tenderedAmount) {
		this.tenderedAmount = tenderedAmount;
	}



}