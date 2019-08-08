package com.coffeehouse.model.dto;


import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "orders",
    "customer",
    "orderItems"
})

public class OrderDetailsDto {

    @JsonProperty("orders")
    private OrdersDto ordersDto;
    
    @JsonProperty("customer")
    private CustomerDto customerDto;
    
    @JsonProperty("orderItems")
    private Set<OrderItemsDto> orderItemsDto = null;

    
    public OrderDetailsDto() {
    }


    public OrderDetailsDto(OrdersDto ordersDto, CustomerDto customerDto, Set<OrderItemsDto> orderItemsDto) {
        super();
        this.ordersDto = ordersDto;
        this.customerDto = customerDto;
        this.orderItemsDto = orderItemsDto;
    }

    @JsonProperty("orders")
    public OrdersDto getOrdersDto() {
        return ordersDto;
    }

    @JsonProperty("orders")
    public void setOrdersDto(OrdersDto ordersDto) {
        this.ordersDto = ordersDto;
    }

    @JsonProperty("customer")
    public CustomerDto getCustomerDto() {
        return customerDto;
    }

    @JsonProperty("customer")
    public void setCustomerDto(CustomerDto customerDto) {
        this.customerDto = customerDto;
    }

    @JsonProperty("orderItems")
    public Set<OrderItemsDto> getOrderItemsDto() {
        return orderItemsDto;
    }

    @JsonProperty("orderItems")
    public void setOrderItemsDto(Set<OrderItemsDto> orderItemsDto) {
        this.orderItemsDto = orderItemsDto;
    }
	
}
