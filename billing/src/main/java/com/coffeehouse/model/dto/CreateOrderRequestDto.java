package com.coffeehouse.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateOrderRequestDto {
	
	@JsonProperty("orderDetails")
	private OrderDetailsDto orderDetailsDto;
	
	public CreateOrderRequestDto(){
	}

	public CreateOrderRequestDto(OrderDetailsDto orderDetailsDto) {
		this.orderDetailsDto = orderDetailsDto;
	}

	public OrderDetailsDto getOrderDetailsDto() {
		return orderDetailsDto;
	}

	public void setOrderDetailsDto(OrderDetailsDto orderDetailsDto) {
		this.orderDetailsDto = orderDetailsDto;
	}

}
