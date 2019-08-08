package com.coffeehouse.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "product_id",
    "product_name",
    "unitPrice",
    "quantity",
    "netPrice"
})
public class OrderItemsDto {

    @JsonProperty("product_id")
    private Integer productId;
    
    @JsonProperty("product_name")
    private String productName;
    
    @JsonProperty("unitPrice")
    private Integer unitPrice;
    
    @JsonProperty("quantity")
    private String quantity;
    
    @JsonProperty("netPrice")
    private Integer netPrice;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OrderItemsDto() {
    }

    public OrderItemsDto(Integer productId, String productName, Integer unitPrice, String quantity, Integer netPrice) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.netPrice = netPrice;
    }

    @JsonProperty("product_id")
    public Integer getProductId() {
        return productId;
    }

    @JsonProperty("product_id")
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @JsonProperty("product_name")
    public String getProductName() {
        return productName;
    }

    @JsonProperty("product_name")
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @JsonProperty("unitPrice")
    public Integer getUnitPrice() {
        return unitPrice;
    }

    @JsonProperty("unitPrice")
    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    @JsonProperty("quantity")
    public String getQuantity() {
        return quantity;
    }

    @JsonProperty("quantity")
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("netPrice")
    public Integer getNetPrice() {
        return netPrice;
    }

    @JsonProperty("netPrice")
    public void setNetPrice(Integer netPrice) {
        this.netPrice = netPrice;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((netPrice == null) ? 0 : netPrice.hashCode());
		result = prime * result
				+ ((productId == null) ? 0 : productId.hashCode());
		result = prime * result
				+ ((productName == null) ? 0 : productName.hashCode());
		result = prime * result
				+ ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result
				+ ((unitPrice == null) ? 0 : unitPrice.hashCode());
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
		OrderItemsDto other = (OrderItemsDto) obj;
		if (netPrice == null) {
			if (other.netPrice != null)
				return false;
		} else if (!netPrice.equals(other.netPrice))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (unitPrice == null) {
			if (other.unitPrice != null)
				return false;
		} else if (!unitPrice.equals(other.unitPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderItemsDto [productId=" + productId + ", productName="
				+ productName + ", unitPrice=" + unitPrice + ", quantity="
				+ quantity + ", netPrice=" + netPrice + "]";
	}



}
