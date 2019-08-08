package com.coffeehouse.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"customerId",
	"customerName",
	"customerMobile"
})

public class CustomerDto {

	@JsonProperty("customerName")
	private String customerName;
	
	@JsonProperty("customerMobile")
	private Long customerMobile;
	
	
	private Long customerId; 
	
	
	public CustomerDto() {
	}

	public CustomerDto(String customerName, Long customerMobile) {
		super();
		this.customerName = customerName;
		this.customerMobile = customerMobile;
	}
	
	public CustomerDto(String customerName, Long customerMobile, Long customerId) {
		super();
		this.customerId = customerId; 
		this.customerName = customerName;
		this.customerMobile = customerMobile;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@JsonProperty("customerName")
	public String getCustomerName() {
		return customerName;
	}

	@JsonProperty("customerName")
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@JsonProperty("customerMobile")
	public Long getCustomerMobile() {
		return customerMobile;
	}

	@JsonProperty("customerMobile")
	public void setCustomerMobile(Long customerMobile) {
		this.customerMobile = customerMobile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerMobile == null) ? 0 : customerMobile.hashCode());
		result = prime * result
				+ ((customerName == null) ? 0 : customerName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerDto other = (CustomerDto) obj;
		if (customerMobile == null) {
			if (other.customerMobile != null)
				return false;
		} else if (!customerMobile.equals(other.customerMobile))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CustomerDto [customerName=" + customerName
				+ ", customerMobile=" + customerMobile + "]";
	}

	
}