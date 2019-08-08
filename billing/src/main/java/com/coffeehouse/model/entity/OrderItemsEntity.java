package com.coffeehouse.model.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="order_items")
public class OrderItemsEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderItemsEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(name="productId")
	private long productId; 


	@Column(name="productName")
	private String productName; 


	@Column(name="quantity")
	private int quantity; 

	@Column(name="unit_price")
	private float unitPrice;

	@Column(name="net_price")
	private float netPrice;

	@ManyToOne
	@JoinColumn(name="order_id", referencedColumnName = "id")
	private OrderEntity orderEntity;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public float getNetPrice() {
		return netPrice;
	}

	public void setNetPrice(float netPrice) {
		this.netPrice = netPrice;
	}
	
	
	// Getter method to retrieve order_id
    public Long getOrder_id(){
        return orderEntity.getId();
    }
    
    
	@JsonIgnore
	public OrderEntity getOrderEntity() {
		return orderEntity;
	}

	@JsonIgnore
	public void setOrderEntity(OrderEntity orderEntity) {
		this.orderEntity = orderEntity;
	}

}
