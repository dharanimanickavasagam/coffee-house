package com.coffeehouse.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "totalPrice",
    "tax",
    "tenderedAmount"
})
public class OrdersDto {

    @JsonProperty("totalPrice")
    private Integer totalPrice;
    
    @JsonProperty("tax")
    private Double tax;
    
    @JsonProperty("tenderedAmount")
    private Double tenderedAmount;

   
    public OrdersDto() {
    }

    
    public OrdersDto(Integer totalPrice, Double tax, Double tenderedAmount) {
        super();
        this.totalPrice = totalPrice;
        this.tax = tax;
        this.tenderedAmount = tenderedAmount;
    }

    @JsonProperty("totalPrice")
    public Integer getTotalPrice() {
        return totalPrice;
    }

    @JsonProperty("totalPrice")
    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    @JsonProperty("tax")
    public Double getTax() {
        return tax;
    }

    @JsonProperty("tax")
    public void setTax(Double tax) {
        this.tax = tax;
    }

    @JsonProperty("tenderedAmount")
    public Double getTenderedAmount() {
        return tenderedAmount;
    }

    @JsonProperty("tenderedAmount")
    public void setTenderedAmount(Double tenderedAmount) {
        this.tenderedAmount = tenderedAmount;
    }


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tax == null) ? 0 : tax.hashCode());
		result = prime * result
				+ ((tenderedAmount == null) ? 0 : tenderedAmount.hashCode());
		result = prime * result
				+ ((totalPrice == null) ? 0 : totalPrice.hashCode());
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
		OrdersDto other = (OrdersDto) obj;
		if (tax == null) {
			if (other.tax != null)
				return false;
		} else if (!tax.equals(other.tax))
			return false;
		if (tenderedAmount == null) {
			if (other.tenderedAmount != null)
				return false;
		} else if (!tenderedAmount.equals(other.tenderedAmount))
			return false;
		if (totalPrice == null) {
			if (other.totalPrice != null)
				return false;
		} else if (!totalPrice.equals(other.totalPrice))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "OrdersDto [totalPrice=" + totalPrice + ", tax=" + tax
				+ ", tenderedAmount=" + tenderedAmount + "]";
	}

   
}

